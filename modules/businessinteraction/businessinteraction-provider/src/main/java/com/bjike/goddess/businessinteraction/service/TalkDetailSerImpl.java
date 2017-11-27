package com.bjike.goddess.businessinteraction.service;

import com.bjike.goddess.businessinteraction.bo.ContactObjectBO;
import com.bjike.goddess.businessinteraction.bo.TalkDetailBO;
import com.bjike.goddess.businessinteraction.dto.TalkDetailDTO;
import com.bjike.goddess.businessinteraction.entity.TalkDetail;
import com.bjike.goddess.businessinteraction.enums.GuideAddrStatus;
import com.bjike.goddess.businessinteraction.to.GuidePermissionTO;
import com.bjike.goddess.businessinteraction.to.TalkDetailTO;
import com.bjike.goddess.capability.api.CooperCapabilityAPI;
import com.bjike.goddess.capability.bo.CooperCapabilityBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.competitormanage.api.CompetitorAPI;
import com.bjike.goddess.competitormanage.bo.CompetitorBO;
import com.bjike.goddess.customer.api.CustomerBaseInfoAPI;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.supplier.api.SupplierInformationAPI;
import com.bjike.goddess.supplier.bo.SupplierInformationBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 洽谈详情业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:27 ]
 * @Description: [ 洽谈详情业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessinteractionSerCache")
@Service
public class TalkDetailSerImpl extends ServiceImpl<TalkDetail, TalkDetailDTO> implements TalkDetailSer {

    @Autowired
    private CooperCapabilityAPI cooperCapabilityAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private SupplierInformationAPI supplierInformationAPI;
    @Autowired
    private CustomerBaseInfoAPI customerBaseInfoAPI;
//    @Autowired
//    private MarketInfoAPI marketInfoAPI;
    @Autowired
    private CompetitorAPI competitorAPI;
    @Autowired
    private UserAPI userAPI;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            case MESSAGE:
                flag = guideSeeIdentity();
                break;
            case ADDMESSAGE:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    public Long countInter(TalkDetailDTO talkDetailDTO) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        RpcTransmit.transmitUserToken(userToken);
//        if (!permissionLevel) {
//            throw new SerException("您的帐号没有权限");
//        }
        checkSeeIdentity();
        Long count = super.count(talkDetailDTO);
        return count;
    }

    @Override
    public TalkDetailBO getOneById(String id) throws SerException {

        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        TalkDetail talkDetail = super.findById(id);

        return BeanTransform.copyProperties(talkDetail, TalkDetailBO.class);
    }

    @Override
    public List<TalkDetailBO> listTalkDetail(TalkDetailDTO talkDetailDTO) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        RpcTransmit.transmitUserToken(userToken);
//        if (!permissionLevel) {
//            throw new SerException("您的帐号没有权限");
//        }
        checkSeeIdentity();
        talkDetailDTO.getSorts().add("createTime=desc");
        List<TalkDetail> list = super.findByCis(talkDetailDTO, true);

        return BeanTransform.copyProperties(list, TalkDetailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TalkDetailBO addTalkDetail(TalkDetailTO talkDetailTO) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        //商务模块添加权限
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        if (!permissionLevel) {
//            throw new SerException("您不是相应的人员，不可以进行添加操作");
//        }
        checkAddIdentity();
        TalkDetail talkDetail = null;
        try {
            talkDetail = BeanTransform.copyProperties(talkDetailTO, TalkDetail.class, true);
            talkDetail.setCreateTime(LocalDateTime.now());
            super.save(talkDetail);
        } catch (SerException e) {
            throw new SerException("洽谈详情添加失败");
        }
        return BeanTransform.copyProperties(talkDetail, TalkDetailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TalkDetailBO editTalkDetail(TalkDetailTO talkDetailTO) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        //商务模块编辑权限
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        if (!permissionLevel) {
//            throw new SerException("您不是相应的人员，不可以进行编辑操作");
//        }
        checkAddIdentity();
        TalkDetail talkDetailTarget = null;
        try {
            TalkDetail talkDetail = BeanTransform.copyProperties(talkDetailTO, TalkDetail.class, true);

            talkDetailTarget = super.findById(talkDetailTO.getId());
            BeanUtils.copyProperties(talkDetail, talkDetailTarget, "id", "createTime");
            talkDetailTarget.setModifyTime(LocalDateTime.now());
            super.save(talkDetailTarget);
        } catch (SerException e) {
            throw new SerException("洽谈详情更新失败");
        }
        return BeanTransform.copyProperties(talkDetailTarget, TalkDetailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteTalkDetail(String id) throws SerException {
//        //商务模块编辑权限
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        if (!permissionLevel) {
//            throw new SerException("您不是相应的人员，不可以进行删除操作");
//        }
        checkAddIdentity();
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("洽谈详情id不能为空");
        }
    }

    @Override
    public List<ContactObjectBO> getContactWays(TalkDetailDTO talkDetailDTO) throws SerException {
        if (StringUtils.isBlank(talkDetailDTO.getCooperCompany())) {
            throw new SerException("合作对象公司名称为空，不能获取");
        }
        String companyName = talkDetailDTO.getCooperCompany();
        List<ContactObjectBO> list = new ArrayList<>();

        //从供应商信息管理获取
        List<SupplierInformationBO> supplierInformationBOs = supplierInformationAPI.findByName(companyName);
        if ((supplierInformationBOs != null) && (!supplierInformationBOs.isEmpty())) {
            for (SupplierInformationBO supplierInformationBO : supplierInformationBOs) {
                ContactObjectBO contactObjectBO = new ContactObjectBO();
                contactObjectBO.setCompanyName(supplierInformationBO.getSupplierName());
                contactObjectBO.setContactName(supplierInformationBO.getContacts());
                contactObjectBO.setContactTel(supplierInformationBO.getTelephone());
                contactObjectBO.setMobilePhone(supplierInformationBO.getTelephone());
                list.add(contactObjectBO);
            }
        }
        //从客户处获取
        List<CustomerBaseInfoBO> customerBaseInfoBOs = customerBaseInfoAPI.findByOriganizion(companyName);
        if ((customerBaseInfoBOs != null) && (!customerBaseInfoBOs.isEmpty())) {
            for (CustomerBaseInfoBO customerBaseInfoBO : customerBaseInfoBOs) {
                ContactObjectBO contactObjectBO = new ContactObjectBO();
                contactObjectBO.setCompanyName(customerBaseInfoBO.getOriganizion());
                contactObjectBO.setContactName(customerBaseInfoBO.getCustomerName());
                contactObjectBO.setContactTel(customerBaseInfoBO.getPhone());
                contactObjectBO.setMobilePhone(customerBaseInfoBO.getTel());
                list.add(contactObjectBO);
            }
        }
        //从市场信息管理处获取
//        List<MarketInfoBO> marketInfoBOs = marketInfoAPI.findByOriganizion(companyName);
//        if ((marketInfoBOs != null) && (!marketInfoBOs.isEmpty())) {
//            for (MarketInfoBO marketInfoBO : marketInfoBOs) {
//                ContactObjectBO contactObjectBO = new ContactObjectBO();
//                contactObjectBO.setCompanyName(marketInfoBO.getOriganizion());
//                contactObjectBO.setContactName(marketInfoBO.getCustomerName());
//                list.add(contactObjectBO);
//            }
//        }
        //从竞争对手管理处获取
        List<CompetitorBO> competitorBOs = competitorAPI.findByOrganization(companyName);
        if ((competitorBOs != null) && (!competitorBOs.isEmpty())) {
            for (CompetitorBO competitorBO : competitorBOs) {
                ContactObjectBO contactObjectBO = new ContactObjectBO();
                contactObjectBO.setCompanyName(competitorBO.getOrganization());
                contactObjectBO.setContactName(competitorBO.getChargeMan());
                contactObjectBO.setContactTel(competitorBO.getPhoneNum());
                contactObjectBO.setMobilePhone(competitorBO.getPhoneNum());
                list.add(contactObjectBO);
            }
        }
        //从商业能力处获取
        List<CooperCapabilityBO> cooperCapabilityBOS = cooperCapabilityAPI.listCompanyContact(companyName);
        cooperCapabilityBOS.stream().forEach(str -> {
            ContactObjectBO contactObjectBO = new ContactObjectBO();
            contactObjectBO.setCompanyName(str.getCompanyName());
            contactObjectBO.setContactName(str.getContactName());
            contactObjectBO.setContactTel(str.getContactWay());
            contactObjectBO.setMobilePhone(str.getContactWay());
            list.add(contactObjectBO);
        });
        return list;
    }
}