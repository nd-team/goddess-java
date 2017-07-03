package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectSingleUI;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.MultipleProjectSingleUITO;
import com.bjike.goddess.projectmeasure.type.GuideAddrStatus;
import com.bjike.goddess.projectmeasure.type.ProjectCategory;
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
 * 多项目单个界面业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class MultipleProjectSingleUISerImpl extends ServiceImpl<MultipleProjectSingleUI, MultipleProjectSingleUIDTO> implements MultipleProjectSingleUISer {

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private UserAPI userAPI;


    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        //商务模块权限
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是商务模块人员,没有该操作权限");
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
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 分页查询多项目单个界面
     *
     * @return class MultipleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    public List<MultipleProjectSingleUIBO> list(MultipleProjectSingleUIDTO dto) throws SerException {
        checkPermission();
        List<MultipleProjectSingleUI> list = super.findByPage(dto);
        List<MultipleProjectSingleUIBO> listBO = BeanTransform.copyProperties(list, MultipleProjectSingleUIBO.class);
        return listBO;
    }

    /**
     * 保存多项目单个界面
     *
     * @param to 多项目单个界面to
     * @return class MultipleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MultipleProjectSingleUIBO save(MultipleProjectSingleUITO to) throws SerException {
        checkPermission();
        MultipleProjectSingleUI entity = BeanTransform.copyProperties(to, MultipleProjectSingleUI.class, true);
        entity.setProjectCategory(ProjectCategory.MULTIPLE_SINGLE);
        entity = super.save(entity);
        MultipleProjectSingleUIBO bo = BeanTransform.copyProperties(entity, MultipleProjectSingleUIBO.class);
        return bo;
    }

    /**
     * 更新多项目单个界面
     *
     * @param to 多项目单个界面to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MultipleProjectSingleUITO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
            MultipleProjectSingleUI model = super.findById(to.getId());
            if (model != null) {
                updateMultipleProjectSingleUI(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新多项目单个界面
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMultipleProjectSingleUI(MultipleProjectSingleUITO to, MultipleProjectSingleUI model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除多项目单个界面
     *
     * @param id 多项目单个界面唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
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
                flag = guideSeeIdentity();
                break;
            case EDIT:
                flag = guideSeeIdentity();
                break;
            case DELETE:
                flag = guideSeeIdentity();
                break;
            case CONGEL:
                flag = guideSeeIdentity();
                break;
            case THAW:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
}