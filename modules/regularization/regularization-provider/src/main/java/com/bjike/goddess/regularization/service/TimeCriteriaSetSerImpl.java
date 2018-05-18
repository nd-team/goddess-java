package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.bo.TimeCriteriaSetBO;
import com.bjike.goddess.regularization.dto.TimeCriteriaSetDTO;
import com.bjike.goddess.regularization.entity.TimeCriteriaSet;
import com.bjike.goddess.regularization.to.GuidePermissionTO;
import com.bjike.goddess.regularization.to.TimeCriteriaSetTO;
import com.bjike.goddess.regularization.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 时间条件设置业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:21 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "regularizationSerCache")
@Service
public class TimeCriteriaSetSerImpl extends ServiceImpl<TimeCriteriaSet, TimeCriteriaSetDTO> implements TimeCriteriaSetSer {


    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        String userName = userBO.getUsername();
        RpcTransmit.transmitUserToken(userToken);
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
     * 检查权限(层次)
     *
     * @throws SerException
     */
    private void checkArrPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.arrCusPermission("2");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员，没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(福利模块考察)
     *
     * @throws SerException
     */
    private void checkModWPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("4");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员，没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(规划模块考察)
     *
     * @throws SerException
     */
    private void checkModPPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("5");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员，没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(预算模块考察)
     *
     * @throws SerException
     */
    private void checkModBPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("6");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员，没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(模块负责人审核)
     *
     * @throws SerException
     */
    private void checkModPepolPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("7");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是模块负责人,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 检查权限(总经理岗位)
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
            flag = cusPermissionSer.jobsCusPermission("3");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是总经理,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(项目经理岗位)
     *
     * @throws SerException
     */
    private void checkManagePermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("8");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是项目经理,没有该操作权限");
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
     * 核对层次审核权限（层次）
     */
    private Boolean guideArrIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.arrCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }


    /**
     * 检查权限(福利模块考察)
     *
     * @throws SerException
     */
    private Boolean guideModWIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("4");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 检查权限(规划模块考察)
     *
     * @throws SerException
     */
    private Boolean guideModPIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("5");
        } else {
            flag = true;
        }
        return flag;

    }

    /**
     * 检查权限(预算模块考察)
     *
     * @throws SerException
     */
    private Boolean guideModBIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("6");
        } else {
            flag = true;
        }
        return flag;

    }

    /**
     * 核对模块负责人审核权限(岗位级别)
     *
     * @throws SerException
     */
    private Boolean guideModPepolIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("7");
        } else {
            flag = true;
        }
        return flag;
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
            flag = cusPermissionSer.jobsCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对项目经理审核权限(岗位)
     *
     * @throws SerException
     */
    private Boolean guideManageIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("8");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagGuide = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagGuide) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 权限
     */
    private Boolean guideAllTrueIdentity() throws SerException {
        return true;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case ZZLIST:
                flag = guideAllTrueIdentity();
                break;
            case ZZADD:
                flag = guideAllTrueIdentity();
                break;
            case ZZEDIT:
                flag = guideAllTrueIdentity();
                break;
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
            case MANAGSCORE:
                flag = guideArrIdentity();
                break;
            case DECISIONSCORE:
                flag = guideArrIdentity();
                break;
            case PLANMODUL:
                flag = guideModPIdentity();
                break;
            case BUDGETMODUL:
                flag = guideModBIdentity();
                break;
            case AUDIT:
                flag = guidePosinIdentity();
                break;
            case WELFAREASSESS:
                flag = guideModWIdentity();
                break;
            case PLANASSESS:
                flag = guideModPIdentity();
                break;
            case BUDGETASSESS:
                flag = guideModBIdentity();
                break;
            case MODULERESPON:
                flag = guideModPepolIdentity();
                break;
            case PROJECTMANAGE:
                flag = guideManageIdentity();
                break;
            case GENMANAGE:
                flag = guidePosinIdentity();
                break;
            case COLLECT:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    /**
     * 分页查询时间条件设置
     *
     * @return class TimeCriteriaSetBO
     * @throws SerException
     */
    @Override
    public List<TimeCriteriaSetBO> list(TimeCriteriaSetDTO dto) throws SerException {
        checkPermission();
        List<TimeCriteriaSet> list = super.findByPage(dto);
        List<TimeCriteriaSetBO> listBO = BeanTransform.copyProperties(list, TimeCriteriaSetBO.class);
        return listBO;
    }

    /**
     * 保存时间条件设置
     *
     * @param to 时间条件设置to
     * @return class TimeCriteriaSetBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public TimeCriteriaSetBO save(TimeCriteriaSetTO to) throws SerException {
        checkPermission();
        checkParameterNameUnique(to);
        TimeCriteriaSet entity = BeanTransform.copyProperties(to, TimeCriteriaSet.class, true);
        entity = super.save(entity);
        TimeCriteriaSetBO bo = BeanTransform.copyProperties(entity, TimeCriteriaSetBO.class);
        return bo;
    }

    /**
     * 校验参数名称是否唯一
     *
     * @param to
     * @throws SerException
     */
    private void checkParameterNameUnique(TimeCriteriaSetTO to) throws SerException {
        String parameterName = to.getParameterName();
        TimeCriteriaSetDTO dto = new TimeCriteriaSetDTO();
        dto.getConditions().add(Restrict.eq("parameterName", parameterName));
        List<TimeCriteriaSet> list = super.findByCis(dto);
        if (!CollectionUtils.isEmpty(list)) {
            throw new SerException("参数名称必须唯一,已经存在相同的参数名称,请直接在原有基础上编辑.");
        }
    }

    /**
     * 根据id删除时间条件设置
     *
     * @param id 时间条件设置唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }

    /**
     * 更新时间条件设置
     *
     * @param to 时间条件设置to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void update(TimeCriteriaSetTO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())){
            TimeCriteriaSet model = super.findById(to.getId());
            if (model != null) {
                updateTimeCriteriaSet(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新时间条件设置
     *
     * @param to 时间条件设置to
     * @param model
     * @throws SerException
     */
    private void updateTimeCriteriaSet(TimeCriteriaSetTO to, TimeCriteriaSet model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }
}