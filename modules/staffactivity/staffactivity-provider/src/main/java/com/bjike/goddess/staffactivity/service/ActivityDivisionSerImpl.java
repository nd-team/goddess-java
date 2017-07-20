package com.bjike.goddess.staffactivity.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.bo.ActivityDivisionBO;
import com.bjike.goddess.staffactivity.dto.ActivityDivisionDTO;
import com.bjike.goddess.staffactivity.entity.ActivityDivision;
import com.bjike.goddess.staffactivity.enums.GuideAddrStatus;
import com.bjike.goddess.staffactivity.to.ActivityDivisionTO;
import com.bjike.goddess.staffactivity.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 活动分工业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 08:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffactivitySerCache")
@Service
public class ActivityDivisionSerImpl extends ServiceImpl<ActivityDivision, ActivityDivisionDTO> implements ActivityDivisionSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
                throw new SerException("您不是相应部门的人员，不可以操作");
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
     * 核对查看权限（部门级别）
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
     * 核对添加修改删除审核权限（岗位级别）
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
            case JOIN:
                flag = guideAddIdentity();
                break;
            case EXIT:
                flag = guideAddIdentity();
                break;
            case BUSINESSADVICE:
                flag = guideAddIdentity();
                break;
            case CARRY:
                flag = guideAddIdentity();
                break;
            case FUNDEVALUATE:
                flag = guideAddIdentity();
                break;
            case OVERSEERVALUATE:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 分页查询活动分工
     *
     * @param dto 活动分工dto
     * @return class ActivityDivisionBO
     * @throws SerException
     */
    @Override
    public List<ActivityDivisionBO> list(ActivityDivisionDTO dto) throws SerException {
        checkSeeIdentity();
        List<ActivityDivision> list = super.findByPage(dto);
        List<ActivityDivisionBO> boList = BeanTransform.copyProperties(list, ActivityDivisionBO.class);
        return boList;
    }

    /**
     * 保存活动分工
     *
     * @param to 活动分工to
     * @return class ActivityDivisionBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public ActivityDivisionBO save(ActivityDivisionTO to) throws SerException {
        checkAddIdentity();
        ActivityDivision entity = BeanTransform.copyProperties(to, ActivityDivision.class, true);
        entity = super.save(entity);
        ActivityDivisionBO bo = BeanTransform.copyProperties(entity, ActivityDivisionBO.class);
        return bo;
    }

    /**
     * 根据id删除活动分工
     *
     * @param id 活动分工唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    /**
     * 更新活动分工
     *
     * @param to 活动分工to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(ActivityDivisionTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotEmpty(to.getId())){
            ActivityDivision model = super.findById(to.getId());
            if (model != null) {
                updateActivityDivision(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新活动分工
     *
     * @param to 活动分工to
     * @param model 活动分工
     */
    private void updateActivityDivision(ActivityDivisionTO to, ActivityDivision model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }
}