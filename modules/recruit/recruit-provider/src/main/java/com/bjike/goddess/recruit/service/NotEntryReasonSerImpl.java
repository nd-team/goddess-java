package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.NotEntryReasonBO;
import com.bjike.goddess.recruit.dto.NotEntryReasonDTO;
import com.bjike.goddess.recruit.entity.FailPhoneReason;
import com.bjike.goddess.recruit.entity.NotEntryReason;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.NotEntryReasonTO;
import com.bjike.goddess.recruit.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 未入职原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:12]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class NotEntryReasonSerImpl extends ServiceImpl<NotEntryReason, NotEntryReasonDTO> implements NotEntryReasonSer {
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
            flag = cusPermissionSer.getCusPermission("1",null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
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
     * 核对查看权限（部门级别）
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
     * 核对添加修改删除审核权限（岗位级别）
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
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagM = guideModuleIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagM) {
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
            case FULI:
                flag = guideModuleIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 分页查询未入职原因
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<NotEntryReasonBO> list(NotEntryReasonDTO dto) throws SerException {
        checkSeeIdentity();
        List<NotEntryReason> list = super.findByPage(dto);
        List<NotEntryReasonBO> listBO = BeanTransform.copyProperties(list, NotEntryReasonBO.class);
        return listBO;
    }

    /**
     * 保存未入职原因
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public NotEntryReasonBO save(NotEntryReasonTO to) throws SerException {
        checkModuleIdentity();
        NotEntryReason failFirstInterviewReason = BeanTransform.copyProperties(to, NotEntryReason.class, true);
        failFirstInterviewReason = super.save(failFirstInterviewReason);
        NotEntryReasonBO bo = BeanTransform.copyProperties(failFirstInterviewReason, NotEntryReasonBO.class);
        return bo;
    }

    /**
     * 更新未入职原因
     *
     * @param to 未入职原因to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(NotEntryReasonTO to) throws SerException {
        checkModuleIdentity();
        if (StringUtils.isNotEmpty(to.getId())) {
            NotEntryReason model = super.findById(to.getId());
            if (model != null) {
                updateNotEntryReason(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新未入职原因
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateNotEntryReason(NotEntryReasonTO to, NotEntryReason model) throws SerException {
        LocalDateTime createTime = model.getCreateTime();
        model = BeanTransform.copyProperties(to, NotEntryReason.class, true);
        model.setCreateTime(createTime);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 删除未入职原因
     *
     * @param entity
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(NotEntryReason entity) throws SerException {
        checkModuleIdentity();
        super.remove(entity);
    }

    @Override
    public Set<String> allReason() throws SerException {
        List<NotEntryReason> list = super.findAll();
        Set<String> set = new HashSet<>();
        for (NotEntryReason f : list) {
            set.add(f.getNotEntryReasonType());
        }
        return set;
    }
}
