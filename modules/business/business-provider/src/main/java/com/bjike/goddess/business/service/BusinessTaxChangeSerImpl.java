package com.bjike.goddess.business.service;

import com.bjike.goddess.business.bo.BusinessRegisterBO;
import com.bjike.goddess.business.bo.BusinessTaxChangeBO;
import com.bjike.goddess.business.dto.BusinessTaxChangeDTO;
import com.bjike.goddess.business.entity.BusinessRegister;
import com.bjike.goddess.business.entity.BusinessTaxChange;
import com.bjike.goddess.business.enums.ChangeDataName;
import com.bjike.goddess.business.enums.GuideAddrStatus;
import com.bjike.goddess.business.enums.Status;
import com.bjike.goddess.business.to.BusinessTaxChangeTO;
import com.bjike.goddess.business.to.GuidePermissionTO;
import com.bjike.goddess.business.to.ShareholdersTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 工商税务变更业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:56 ]
 * @Description: [ 工商税务变更业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessSerCache")
@Service
public class BusinessTaxChangeSerImpl extends ServiceImpl<BusinessTaxChange, BusinessTaxChangeDTO> implements BusinessTaxChangeSer {
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private BusinessRegisterSer businessRegisterSer;

    /**
     * 核对查看权限(部门级别)
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
                throw new SerException("您不是相对应部门的人员,不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加编辑删除操作(岗位级别)
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
                throw new SerException("你不是相对应部门的人员,不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限(部门级别)
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
     * 核对添加编辑删除权限(岗位级别)
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
            default:
                flag = true;
                break;
        }
        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countBusinessTaxChange(BusinessTaxChangeDTO businessTaxChangeDTO) throws SerException {
        businessTaxChangeDTO.getSorts().add("createTime=desc");
        Long counts = super.count(businessTaxChangeDTO);
        return counts;
    }

    @Override
    public BusinessTaxChangeBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        BusinessTaxChange businessTaxChange = super.findById(id);
        return BeanTransform.copyProperties(businessTaxChange, BusinessTaxChangeBO.class);
    }

    @Override
    public List<BusinessTaxChangeBO> findListBusinessTaxChange(BusinessTaxChangeDTO businessTaxChangeDTO) throws SerException {
        checkSeeIdentity();
        List<BusinessTaxChange> businessTaxChanges = super.findByCis(businessTaxChangeDTO, true);
        List<BusinessTaxChangeBO> businessTaxChangeBOS = BeanTransform.copyProperties(businessTaxChanges, BusinessTaxChangeBO.class);
        return businessTaxChangeBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessTaxChangeBO insertBusinessTaxChange(BusinessTaxChangeTO businessTaxChangeTO) throws SerException {
        checkAddIdentity();

        BusinessTaxChange businessTaxChange = BeanTransform.copyProperties(businessTaxChangeTO, BusinessTaxChange.class, true, "changeAfterContent", "shareholdersTOList");

        //转换股东:占股比例
        String share = "";
        if (businessTaxChangeTO.getChangeDataName().equals(ChangeDataName.SHAREHOLDERS)) {
            StringBuffer shareholders = new StringBuffer();
            List<ShareholdersTO> shareholdersTOList = businessTaxChangeTO.getShareholdersTOList();
            Double sum = 0d;
            if (null != shareholdersTOList && shareholdersTOList.size() > 0) {
                for (ShareholdersTO shareholdersTO : shareholdersTOList) {
                    sum += shareholdersTO.getPercentageShares();
                    shareholders.append(shareholdersTO.getShareholders() + ":" + shareholdersTO.getPercentageShares());
                    shareholders.append(";");
                }
            }
            if (sum != 1) {
                throw new SerException("股东占股之和必须为1");
            }


            if (null != shareholders && shareholders.length() > 0) {
                share = shareholders.substring(0, shareholders.lastIndexOf(";"));
            }
            businessTaxChange.setChangeAfterContent(share);
        } else {
            businessTaxChange.setChangeAfterContent(businessTaxChangeTO.getChangeAfterContent());
        }

        businessTaxChange.setCreateTime(LocalDateTime.now());


        BusinessRegister businessRegister = businessRegisterSer.findById(businessTaxChangeTO.getBusinessRegisterId());
        businessRegister.setModifyTime(LocalDateTime.now());
        switch (businessTaxChangeTO.getChangeDataName()) {
            case REGISTERCOMPANYNAME:
                businessRegister.setRegisterCompanyName(businessTaxChangeTO.getChangeAfterContent());
                break;
            case REGISTERNUM:
                businessRegister.setRegisterNum(businessTaxChangeTO.getChangeAfterContent());
                break;
            case OPERATIONPERIOD:
                businessRegister.setOperationPeriod(businessTaxChangeTO.getChangeAfterContent());
                break;
            case REGISTERTYPE:
                businessRegister.setRegisterType(businessTaxChangeTO.getChangeAfterContent());
                break;
            case REGISTERCAPITAL:
                businessRegister.setRegisterCapital(businessTaxChangeTO.getChangeAfterContent());
                break;
            case OPERATIONSCOPE:
                businessRegister.setOperationScope(businessTaxChangeTO.getChangeAfterContent());
                break;
            case LEGALPERSON:
                businessRegister.setLegalPerson(businessTaxChangeTO.getChangeAfterContent());
                break;
            case SHAREHOLDERS:
                businessRegister.setShareholders(share);
                break;
            case ADDRESS:
                businessRegister.setAddress(businessTaxChangeTO.getChangeAfterContent());
                break;
            case STATUS:
                Status status = null;
                if (("开业").equals(businessTaxChangeTO.getChangeAfterContent())) {
                    status = Status.OPING;
                } else if (("歇业").equals(businessTaxChangeTO.getChangeAfterContent())) {
                    status = Status.BUSSGOOUT;
                } else if (("结业").equals(businessTaxChangeTO.getChangeAfterContent())) {
                    status = Status.GRADUATION;
                }
                businessRegister.setStatus(status);
                break;
            case ISSUINGDATE:
                businessRegister.setIssuingDate(DateUtil.parseDate(businessTaxChangeTO.getChangeAfterContent()));
                break;
            case REGISTRATIONAUTHOR:
                businessRegister.setRegistrationAuthor(businessTaxChangeTO.getChangeAfterContent());
                break;
            case ORGANIZATIONNEMNAME:
                businessRegister.setOrganizationNemName(businessTaxChangeTO.getChangeAfterContent());
                break;
            case POSITION:
                businessRegister.setPosition(businessTaxChangeTO.getChangeAfterContent());
                break;
            case POSITIONWAY:
                businessRegister.setPositionWay(businessTaxChangeTO.getChangeAfterContent());
                break;
            case REPRESENTATIVELEGAL:
                Boolean bool = null;
                if ("是".equals(businessTaxChangeTO.getChangeAfterContent())) {
                    bool = true;
                } else if("否".equals(businessTaxChangeTO.getChangeAfterContent())) {
                    bool = false;
                }
                businessRegister.setRepresentativeLegal(bool);
                break;
            default:
                throw new SerException("请正确选择变更资料名称");
        }
        businessRegister.setModifyTime(LocalDateTime.now());
        businessRegisterSer.update(businessRegister);

        super.save(businessTaxChange);
        return BeanTransform.copyProperties(businessTaxChange, BusinessTaxChangeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessTaxChangeBO editBusinessTaxChange(BusinessTaxChangeTO businessTaxChangeTO) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(businessTaxChangeTO.getId())) {
            throw new SerException("id不能为空");
        }
        BusinessTaxChange businessTaxChange = super.findById(businessTaxChangeTO.getId());
        BeanTransform.copyProperties(businessTaxChangeTO, businessTaxChange, true);
        businessTaxChange.setModifyTime(LocalDateTime.now());
        super.update(businessTaxChange);
        return BeanTransform.copyProperties(businessTaxChangeTO, BusinessTaxChangeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeBusinessTaxChange(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        BusinessTaxChange businessTaxChange = super.findById(id);

        //恢复数据
        BusinessRegister businessRegister =businessRegisterSer.findById(businessTaxChange.getBusinessRegisterId());

        switch (businessTaxChange.getChangeDataName()) {
            case REGISTERCOMPANYNAME:
                businessRegister.setRegisterCompanyName(businessTaxChange.getChangeBeforeContent());
                break;
            case REGISTERNUM:
                businessRegister.setRegisterNum(businessTaxChange.getChangeBeforeContent());
                break;
            case OPERATIONPERIOD:
                businessRegister.setOperationPeriod(businessTaxChange.getChangeBeforeContent());
                break;
            case REGISTERTYPE:
                businessRegister.setRegisterType(businessTaxChange.getChangeBeforeContent());
                break;
            case REGISTERCAPITAL:
                businessRegister.setRegisterCapital(businessTaxChange.getChangeBeforeContent());
                break;
            case OPERATIONSCOPE:
                businessRegister.setOperationScope(businessTaxChange.getChangeBeforeContent());
                break;
            case LEGALPERSON:
                businessRegister.setLegalPerson(businessTaxChange.getChangeBeforeContent());
                break;
            case SHAREHOLDERS:
                businessRegister.setShareholders(businessTaxChange.getChangeBeforeContent());
                break;
            case ADDRESS:
                businessRegister.setAddress(businessTaxChange.getChangeBeforeContent());
                break;
            case STATUS:
                Status status = null;
                if (("开业").equals(businessTaxChange.getChangeBeforeContent())) {
                    status = Status.OPING;
                } else if (("歇业").equals(businessTaxChange.getChangeBeforeContent())) {
                    status = Status.BUSSGOOUT;
                } else if (("结业").equals(businessTaxChange.getChangeBeforeContent())) {
                    status = Status.GRADUATION;
                }
                businessRegister.setStatus(status);
                break;
            case ISSUINGDATE:
                businessRegister.setIssuingDate(DateUtil.parseDate(businessTaxChange.getChangeBeforeContent()));
                break;
            case REGISTRATIONAUTHOR:
                businessRegister.setRegistrationAuthor(businessTaxChange.getChangeBeforeContent());
                break;
            case ORGANIZATIONNEMNAME:
                businessRegister.setOrganizationNemName(businessTaxChange.getChangeBeforeContent());
                break;
            case POSITION:
                businessRegister.setPosition(businessTaxChange.getChangeBeforeContent());
                break;
            case POSITIONWAY:
                businessRegister.setPositionWay(businessTaxChange.getChangeBeforeContent());
                break;
            case REPRESENTATIVELEGAL:
                Boolean bool = null;
                if ("是".equals(businessTaxChange.getChangeBeforeContent())) {
                    bool = true;
                } else if ("否".equals(businessTaxChange.getChangeBeforeContent())){
                    bool = false;
                }
                businessRegister.setRepresentativeLegal(bool);
                break;
            default:
                throw new SerException("请正确选择变更资料名称");
        }
        businessRegister.setModifyTime(LocalDateTime.now());
        businessRegisterSer.update(businessRegister);

        //删除这条数据之后所操作的数据
        BusinessTaxChangeDTO businessTaxChangeDTO = new BusinessTaxChangeDTO();
        businessTaxChangeDTO.getConditions().add(Restrict.eq("businessRegisterId",businessTaxChange.getBusinessRegisterId()));
        businessTaxChangeDTO.getConditions().add(Restrict.eq("changeDataName",businessTaxChange.getChangeDataName()));
        businessTaxChangeDTO.getConditions().add(Restrict.gt_eq("createTime",businessTaxChange.getCreateTime()));
        List<BusinessTaxChange> businessTaxChangeList = super.findByCis(businessTaxChangeDTO);
        super.remove(businessTaxChangeList);


    }

}