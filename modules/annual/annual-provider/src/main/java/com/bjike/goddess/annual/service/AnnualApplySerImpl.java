package com.bjike.goddess.annual.service;

import com.bjike.goddess.annual.bo.AnnualApplyBO;
import com.bjike.goddess.annual.bo.AnnualInfoBO;
import com.bjike.goddess.annual.dto.AnnualApplyDTO;
import com.bjike.goddess.annual.entity.AnnualApply;
import com.bjike.goddess.annual.entity.AnnualInfo;
import com.bjike.goddess.annual.enums.AuditType;
import com.bjike.goddess.annual.enums.GuideAddrStatus;
import com.bjike.goddess.annual.to.AnnualApplyAuditTo;
import com.bjike.goddess.annual.to.AnnualApplyTO;
import com.bjike.goddess.annual.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.PositionAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.PositionBO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 年假申请业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:13 ]
 * @Description: [ 年假申请业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "annualSerCache")
@Service
public class AnnualApplySerImpl extends ServiceImpl<AnnualApply, AnnualApplyDTO> implements AnnualApplySer {

    @Autowired
    private AnnualInfoSer annualInfoSer;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionAPI positionAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是本部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(项目经理)
     *
     * @throws SerException
     */
    private void checkPonsPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.positCusPermission("2");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是该岗位人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 权限
     */
    private Boolean guideAllTrueIdentity() throws SerException {
        return true;
    }


    /**
     * 核对总经办审核权限（岗位级别）
     */
    private Boolean guidePosinIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.positCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagPosin = guidePosinIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagTrue = guideAllTrueIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagPosin || flagTrue) {
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
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case SEE:
                flag = guideIdentity();
                break;
            case AUDIT:
                flag = guidePosinIdentity();
                break;
            case CONGEL:
                flag = guideIdentity();
                break;
            case THAW:
                flag = guideIdentity();
                break;
            case XXLIST:
                flag = guideAllTrueIdentity();
                break;
            case XXADD:
                flag = guideAllTrueIdentity();
                break;
            case XXAPPLY:
                flag = guideAllTrueIdentity();
                break;
            case XXSEE:
                flag = guideAllTrueIdentity();
                break;
            case XXMYSELF:
                flag = guideAllTrueIdentity();
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 转换年假申请传输对象
     *
     * @param entity 年假申请实体数据
     * @return
     */
    private AnnualApplyBO transformBO(AnnualApply entity) {
        AnnualApplyBO bo = BeanTransform.copyProperties(entity, AnnualApplyBO.class, true);
        AnnualInfo info = entity.getInfo();
        if (null != info) {
            bo.setInfoId(info.getId());
            bo.setInfoUsername(info.getUsername());
        }
        return bo;
    }

    /**
     * 转换年假申请传输对象集合
     *
     * @param list 年假申请实体数据集合
     * @return
     */
    private List<AnnualApplyBO> transformBOList(List<AnnualApply> list) {
        List<AnnualApplyBO> bos = new ArrayList<>(list.size());
        for (AnnualApply entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    /**
     * 计算请假时间
     *
     * @param entity 年假申请实体数据
     * @return
     */
    private Double countLeave(AnnualApply entity) {
        long hour = entity.getStartTime().until(entity.getEndTime(), ChronoUnit.HOURS);
        //@TODO 计算请假时间(日总工时/本次请假的时长 = 请假时间)
        return 0d;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualApplyBO save(AnnualApplyTO to) throws SerException {
        UserBO userBO = userAPI.currentUser();
        AnnualApply entity = BeanTransform.copyProperties(to, AnnualApply.class, true);
        entity.setAudit(AuditType.NONE);
        entity.setInfo(annualInfoSer.findById(to.getInfoId()));
        entity.setLeave(this.countLeave(entity));
        if (entity.getLeave() > entity.getInfo().getSurplus())
            throw new SerException("请不要超出剩余年假数");
        if (!userBO.getUsername().equals(entity.getInfo().getUsername()))
            throw new SerException("请不要替他人提交年假申请");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualApplyBO delete(AnnualApplyTO to) throws SerException {
        UserBO userBO = userAPI.currentUser();
        AnnualApply entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        if (!userBO.getUsername().equals(entity.getInfo().getUsername()))
            throw new SerException("请不要对他人的年假申请做处理");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualApplyBO audit(AnnualApplyAuditTo to) throws SerException {
        checkPonsPermission();
        UserBO auditor = userAPI.currentUser();
        AnnualApply entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        UserBO user = userAPI.findByUsername(entity.getInfo().getUsername());
        UserDetailBO auditorDetailBO = userDetailAPI.findByUserId(auditor.getId()), userDetailBO = userDetailAPI.findByUserId(user.getId());
        List<PositionBO> positionBOs = positionAPI.findChild(auditorDetailBO.getPositionId());
        boolean adopt = false;
        for (PositionBO position : positionBOs)
            if (position.getId().equals(userDetailBO.getPositionId())) {
                adopt = true;
                break;
            }
        if (!adopt)
            return null;
        entity.setAuditTime(LocalDateTime.now());
        entity.setAuditor(auditor.getUsername());
        entity.setAudit(AuditType.DENIED);
        if (to.isFruit()) {
            entity.setAudit(AuditType.ALLOWED);
            //通过则修改年假信息
            entity.getInfo().setSurplus(entity.getInfo().getSurplus() - entity.getLeave());
            entity.getInfo().isAlready(Boolean.TRUE);
            annualInfoSer.update(entity.getInfo());
        }
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<AnnualApplyBO> findByUsername(String username) throws SerException {
        if (StringUtils.isBlank(username))
            return new ArrayList<>(0);
        List<AnnualInfoBO> infoBOList = annualInfoSer.findByUsername(username);
        if (null != infoBOList && infoBOList.size() > 0)
            return new ArrayList<>(0);
        AnnualApplyDTO dto = new AnnualApplyDTO();
        dto.getConditions().add(Restrict.in("info.id", infoBOList.stream().map(AnnualInfoBO::getId).collect(Collectors.toList()).toArray(new String[0])));
        List<AnnualApply> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public List<AnnualApplyBO> findByInfo(String infoId) throws SerException {
        AnnualApplyDTO dto = new AnnualApplyDTO();
        dto.getConditions().add(Restrict.eq("info.id", infoId));
        List<AnnualApply> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public List<AnnualApplyBO> maps(AnnualApplyDTO dto) throws SerException {
        dto.getSorts().add("startTime");
        dto.getSorts().add("info.id");
        List<AnnualApply> list = super.findByPage(dto);
        return this.transformBOList(list);
    }

    @Override
    public AnnualApplyBO getById(String id) throws SerException {
        AnnualApply entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public Long getTotal() throws SerException {
        AnnualApplyDTO dto = new AnnualApplyDTO();
        return super.count(dto);
    }
}