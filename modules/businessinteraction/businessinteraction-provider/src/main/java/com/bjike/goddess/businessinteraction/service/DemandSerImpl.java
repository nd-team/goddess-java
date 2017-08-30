package com.bjike.goddess.businessinteraction.service;

import com.bjike.goddess.businessinteraction.bo.DemandBO;
import com.bjike.goddess.businessinteraction.bo.DemandObjectBO;
import com.bjike.goddess.businessinteraction.dto.DemandDTO;
import com.bjike.goddess.businessinteraction.entity.Demand;
import com.bjike.goddess.businessinteraction.enums.GuideAddrStatus;
import com.bjike.goddess.businessinteraction.to.DemandTO;
import com.bjike.goddess.businessinteraction.to.GuidePermissionTO;
import com.bjike.goddess.businessinteraction.to.SonPermissionObject;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.competitormanage.api.CompetitorAPI;
import com.bjike.goddess.competitormanage.bo.CompetitorBO;
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
 * 互动平台需求描述业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:21 ]
 * @Description: [ 互动平台需求描述业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessinteractionSerCache")
@Service
public class DemandSerImpl extends ServiceImpl<Demand, DemandDTO> implements DemandSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private CollectEmailSer collectEmailSer;
    @Autowired
    private InteractionRelationSer interactionRelationSer;
    @Autowired
    private LeavingMessageSer leavingMessageSer;
    @Autowired
    private TalkDetailSer talkDetailSer;
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

//    @Override
//    public Boolean sonPermission() throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        Boolean flagSee = guideSeeIdentity();
//        RpcTransmit.transmitUserToken(userToken);
//        Boolean flagAdd = guideAddIdentity();
//        if (flagSee || flagAdd) {
//            return true;
//        } else {
//            return false;
//        }
//    }

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
        return flag;
    }

    @Override
    public Long countInter(DemandDTO demandDTO) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        RpcTransmit.transmitUserToken(userToken);
//        if (!permissionLevel) {
//            throw new SerException("您的帐号没有权限");
//        }
        checkSeeIdentity();
        if (StringUtils.isNotBlank(demandDTO.getName())) {
            demandDTO.getConditions().add(Restrict.like("name", demandDTO.getName()));
        }
        Long count = super.count(demandDTO);
        return count;
    }

    @Override
    public DemandBO getOneById(String id) throws SerException {


        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        Demand demand = super.findById(id);

        return BeanTransform.copyProperties(demand, DemandBO.class);
    }


    @Override
    public List<DemandBO> listDemand(DemandDTO demandDTO) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        RpcTransmit.transmitUserToken(userToken);
//        if (!permissionLevel) {
//            throw new SerException("您的帐号没有权限");
//        }
        checkSeeIdentity();
        if (StringUtils.isNotBlank(demandDTO.getName())) {
            demandDTO.getConditions().add(Restrict.like("name", demandDTO.getName()));
        }
        List<Demand> list = super.findByCis(demandDTO, true);

        return BeanTransform.copyProperties(list, DemandBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DemandBO addDemand(DemandTO demandTO) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        //商务模块添加权限
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        if (!permissionLevel) {
//            throw new SerException("您不是相应的人员，不可以进行添加操作");
//        }
        checkAddIdentity();
        Demand demand = null;
        try {
            demand = BeanTransform.copyProperties(demandTO, Demand.class, true);
            demand.setCreateTime(LocalDateTime.now());
            super.save(demand);
        } catch (SerException e) {
            throw new SerException("互动平台需求描述添加失败");
        }
        return BeanTransform.copyProperties(demand, DemandBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DemandBO editDemand(DemandTO demandTO) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        //商务模块添加权限
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        if (!permissionLevel) {
//            throw new SerException("您不是相应的人员，不可以进行编辑操作");
//        }

        checkAddIdentity();
        Demand demandTarget = null;
        try {
            Demand demand = BeanTransform.copyProperties(demandTO, Demand.class, true);

            demandTarget = super.findById(demandTO.getId());
            BeanUtils.copyProperties(demand, demandTarget, "id", "createTime");
            demandTarget.setModifyTime(LocalDateTime.now());
            super.save(demandTarget);
        } catch (SerException e) {
            throw new SerException("互动平台需求描述更新失败");
        }
        return BeanTransform.copyProperties(demandTarget, DemandBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteDemand(String id) throws SerException {
//        //商务模块删除权限
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        if (!permissionLevel) {
//            throw new SerException("您不是相应的人员，不可以进行删除操作");
//        }

        checkAddIdentity();
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("互动平台需求描述id不能为空");
        }
    }

    @Override
    public List<DemandBO> searchList(DemandDTO demandDTO) throws SerException {
        if (StringUtils.isNotBlank(demandDTO.getName())) {
            demandDTO.getConditions().add(Restrict.like("name", demandDTO.getName()));
        }

        List<Demand> list = super.findByCis(demandDTO, true);

        return BeanTransform.copyProperties(list, DemandBO.class);
    }

    @Override
    public List<DemandObjectBO> searchDemand(DemandDTO demandDTO) throws SerException {
        //TODO : tanghaixiang 2017-03-29 从市场信息管理根据规模和行业拿公司名称，地区
        List<CompetitorBO> competitorBOs = null;
        if (StringUtils.isNotBlank(demandDTO.getBusinessTarget())) {
            demandDTO.getConditions().add(Restrict.like("businessTarget", demandDTO.getBusinessTarget()));
            competitorBOs = competitorAPI.findByBusinessType(demandDTO.getBusinessTarget());
        }
        if (StringUtils.isNotBlank(demandDTO.getSize())) {
            demandDTO.getConditions().add(Restrict.like("size", demandDTO.getSize()));
        }
        if (StringUtils.isNotBlank(demandDTO.getProfession())) {
            demandDTO.getConditions().add(Restrict.like("profession", demandDTO.getProfession()));
        }
        List<Demand> list = super.findByCis(demandDTO);
        List<DemandObjectBO> boList= BeanTransform.copyProperties(list, DemandObjectBO.class);
        if ((competitorBOs != null) && (!competitorBOs.isEmpty())){
            for (CompetitorBO competitorBO:competitorBOs) {
                DemandObjectBO demandObjectBO = new DemandObjectBO();
                demandObjectBO.setArea(competitorBO.getArea());
                boList.add(demandObjectBO);
            }
        }
        return boList;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("demand");
        obj.setDescribesion("互动平台需求描述");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeEmail = collectEmailSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("collectemail");
        obj.setDescribesion("邮件发送定制");
        if (flagSeeEmail) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeInter = interactionRelationSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("interactionrelation");
        obj.setDescribesion("商业能力互动联系");
        if (flagSeeInter) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeLeav = leavingMessageSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("leavingmessage");
        obj.setDescribesion("留言");
        if (flagSeeLeav) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeTalk = talkDetailSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("talkdetail");
        obj.setDescribesion("洽谈详情");
        if (flagSeeTalk) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
    }
}