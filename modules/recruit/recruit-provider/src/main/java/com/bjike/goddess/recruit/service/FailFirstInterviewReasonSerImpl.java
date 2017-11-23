package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.FailFirstInterviewReasonBO;
import com.bjike.goddess.recruit.dto.FailFirstInterviewReasonDTO;
import com.bjike.goddess.recruit.entity.FailFirstInterviewReason;
import com.bjike.goddess.recruit.to.FailFirstInterviewReasonTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.type.GuideAddrStatus;
import com.bjike.goddess.recruit.vo.SonPermissionObject;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 未应约初试原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 08:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class FailFirstInterviewReasonSerImpl extends ServiceImpl<FailFirstInterviewReason, FailFirstInterviewReasonDTO> implements FailFirstInterviewReasonSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private FailInviteReasonSer failInviteReasonSer;
    @Autowired
    private FailPhoneReasonSer failPhoneReasonSer;
    @Autowired
    private FirstPhoneRecordSer firstPhoneRecordSer;
    @Autowired
    private InterviewAddressInforSer interviewAddressInforSer;
    @Autowired
    private InterviewInforSer interviewInforSer;
    @Autowired
    private NotEntryReasonSer notEntryReasonSer;
    @Autowired
    private RecruitProSer recruitProSer;
    @Autowired
    private RecruitWaySer recruitWaySer;
    @Autowired
    private ReportAddressInforSer reportAddressInforSer;
    @Autowired
    private TemplateManageSer templateManageSer;
    @Autowired
    private RecruitDemandPlanSer recruitDemandPlanSer;

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
            flag = cusPermissionSer.getCusPermission("1",null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 福利模块
     */
    private void checkModuleIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3",null);
            if (!flag) {
                throw new SerException("您不是福利模块的人员，不可以操作");
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
            flag = cusPermissionSer.getCusPermission("1",null);
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
            flag = cusPermissionSer.busCusPermission("2",null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 福利模块
     */
    private Boolean guideModuleIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3",null);
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
//        String userToken = RpcTransmit.getUserToken();
//        Boolean flagSeeSign = guideSeeIdentity();//getCusPermission("1");
//        RpcTransmit.transmitUserToken(userToken);
//        Boolean flagAddModule = guideModuleIdentity();//busCusPermission("3");
//        RpcTransmit.transmitUserToken(userToken);

        Boolean flag1 = false;
        Boolean flag2 = false;
        Boolean flag3 = false;
        Boolean flag4 = false;
        Boolean flag5 = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag1 = cusPermissionSer.getCusPermission("1",userBO);
            flag2 = cusPermissionSer.getCusPermission("2",userBO);
            flag3 = cusPermissionSer.busCusPermission("3",userBO);
            flag4 = cusPermissionSer.getCusPermission("4",userBO);
            flag5 = cusPermissionSer.manCusPermission("5",userBO);
        } else {
            flag1 = true;
            flag2 = true;
            flag3 = true;
            flag4 = true;
            flag5 = true;
        }


        SonPermissionObject obj = new SonPermissionObject();

        //1-3
        obj = new SonPermissionObject();
        obj.setName("failfirstinterviewreason");
        obj.setDescribesion("未应约初试原因");
        if (flag1 || flag3) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        //1-2
//        RpcTransmit.transmitUserToken(userToken);
        //getCusPermission("2");
        //getCusPermission("1");
//        Boolean flagSeeDis = failInviteReasonSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("failinvitereason");
        obj.setDescribesion("未邀约成功原因");
        if (flag1||flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        //1-2
//        RpcTransmit.transmitUserToken(userToken);
        //getCusPermission("2");
        //getCusPermission("1");
//        Boolean flagSeePhone = failPhoneReasonSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("failurephonereason");
        obj.setDescribesion("未成功通话原因");
        if (flag1||flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        //1-2
//        RpcTransmit.transmitUserToken(userToken);
        //getCusPermission("2");
        //getCusPermission("1");
//        Boolean flagSeefPhone = firstPhoneRecordSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("firstphonerecord");
        obj.setDescribesion("第一次电访记录");
        if (flag1||flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        //1-3
//        RpcTransmit.transmitUserToken(userToken);
        //.busCusPermission("3");
        //getCusPermission("1");
//        Boolean flagSeefi = interviewAddressInforSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("interviewaddressinfor");
        obj.setDescribesion("面试地址信息");
        if (flag1||flag3) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        //1-2
//        RpcTransmit.transmitUserToken(userToken);
        //getCusPermission("2");
        //getCusPermission("1");
//        Boolean flagSeefin = interviewInforSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("interviewinfor");
        obj.setDescribesion("面试信息");
        if (flag1||flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        //1-3
//        RpcTransmit.transmitUserToken(userToken);
        //.busCusPermission("3");
        //getCusPermission("1");
//        Boolean flagSeeE = notEntryReasonSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("notentryreason");
        obj.setDescribesion("未入职原因");
        if (flag1||flag3) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        //1-2
//        RpcTransmit.transmitUserToken(userToken);
        //getCusPermission("2");
        //getCusPermission("1");
//        Boolean flagSeeRp = recruitDemandPlanSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("recruitdemandplan");
        obj.setDescribesion("招聘需求与计划");
        if (flag1||flag3) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        //1-2-4-5
        //getCusPermission("2");
        //getCusPermission("1");
        //getCusPermission("4");
//        manCusPermission("5")
//        RpcTransmit.transmitUserToken(userToken);
//        Boolean flagSeeRpo = recruitProSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("recruitpro");
        obj.setDescribesion("招聘方案");
        if (flag1||flag2||flag4||flag5) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        //1-2
        //getCusPermission("2");
        //getCusPermission("1");
//        RpcTransmit.transmitUserToken(userToken);
//        Boolean flagSeeRw = recruitWaySer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("recruitway");
        obj.setDescribesion("招聘渠道");
        if (flag1||flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        //1-3
//        RpcTransmit.transmitUserToken(userToken);
        //.busCusPermission("3");
        //getCusPermission("1");
//        RpcTransmit.transmitUserToken(userToken);
//        Boolean flagSeeRa = reportAddressInforSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("reportaddressinfor");
        obj.setDescribesion("报道地址信息");
        if (flag1||flag3) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        //1-3
//        RpcTransmit.transmitUserToken(userToken);
        //.busCusPermission("3");
        //getCusPermission("1");
//        Boolean flagSeeT = templateManageSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("templatemanage");
        obj.setDescribesion("模板管理");
        if (flag1||flag3) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            case FULI:
                flag = guideModuleIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    /**
     * 分页查询未应约初试原因
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<FailFirstInterviewReasonBO> list(FailFirstInterviewReasonDTO dto) throws SerException {
        checkSeeIdentity();
        List<FailFirstInterviewReason> list = super.findByPage(dto);
        List<FailFirstInterviewReasonBO> listBO = BeanTransform.copyProperties(list, FailFirstInterviewReasonBO.class);
        return listBO;
    }

    /**
     * 保存未应约初试原因
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public FailFirstInterviewReasonBO save(FailFirstInterviewReasonTO to) throws SerException {
        checkModuleIdentity();
        FailFirstInterviewReason failFirstInterviewReason = BeanTransform.copyProperties(to, FailFirstInterviewReason.class, true);
        failFirstInterviewReason = super.save(failFirstInterviewReason);
        FailFirstInterviewReasonBO bo = BeanTransform.copyProperties(failFirstInterviewReason, FailFirstInterviewReasonBO.class);
        return bo;
    }

    /**
     * 更新未应约初试原因
     *
     * @param to 未应约初试原因to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(FailFirstInterviewReasonTO to) throws SerException {
        checkModuleIdentity();
        if (StringUtils.isNotEmpty(to.getId())) {
            FailFirstInterviewReason model = super.findById(to.getId());
            if (model != null) {
                updateFailFirstInterviewReason(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新未应约初试原因
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateFailFirstInterviewReason(FailFirstInterviewReasonTO to, FailFirstInterviewReason model) throws SerException {
        LocalDateTime createTime = model.getCreateTime();
        model = BeanTransform.copyProperties(to, FailFirstInterviewReason.class, true);
        model.setCreateTime(createTime);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 删除未应约初试原因
     *
     * @param entity
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(FailFirstInterviewReason entity) throws SerException {
        checkModuleIdentity();
        super.remove(entity);
    }

    @Override
    public Set<String> allReason() throws SerException {
        List<FailFirstInterviewReason> list = super.findAll();
        Set<String> set = new HashSet<>();
        for (FailFirstInterviewReason f : list) {
            set.add(f.getFailFirstInterviewReasonType());
        }
        return set;
    }
}
