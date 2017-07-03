package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.SingleProjectMultipleUIBO;
import com.bjike.goddess.projectmeasure.dto.SingleProjectMultipleUIDTO;
import com.bjike.goddess.projectmeasure.entity.SingleProjectMultipleUI;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.SingleProjectMultipleUITO;
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
 * 单个项目多个界面业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:51 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class SingleProjectMultipleUISerImpl extends ServiceImpl<SingleProjectMultipleUI, SingleProjectMultipleUIDTO> implements SingleProjectMultipleUISer {

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
        UserBO userBO = userAPI.currentUser( );
        RpcTransmit.transmitUserToken( userToken );
        String userName = userBO.getUsername();
        //商务模块权限
        if( !"admin".equals( userName.toLowerCase())){
            flag = cusPermissionSer.busCusPermission("1");
        }else{
            flag = true;
        }
        if ( !flag) {
            throw new SerException("您不是商务模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken( userToken );

    }


    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException{
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser( );
        RpcTransmit.transmitUserToken( userToken );
        String userName = userBO.getUsername();
        if( !"admin".equals( userName.toLowerCase())){
            flag = cusPermissionSer.busCusPermission("1");
        }else{
            flag = true;
        }
        return flag;
    }

    /**
     * 分页查询单个项目多个界面
     *
     * @return class SingleProjectMultipleUIBO
     * @throws SerException
     */
    @Override
    public List<SingleProjectMultipleUIBO> list(SingleProjectMultipleUIDTO dto) throws SerException {
        checkPermission();
        List<SingleProjectMultipleUI> list = super.findByPage(dto);
        List<SingleProjectMultipleUIBO> listBO = BeanTransform.copyProperties(list, SingleProjectMultipleUIBO.class);
        return listBO;
    }

    /**
     * 单个项目多个界面
     *
     * @param to 单个项目多个界面to
     * @return class SingleProjectMultipleUIBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public SingleProjectMultipleUIBO save(SingleProjectMultipleUITO to) throws SerException {
        checkPermission();
        SingleProjectMultipleUI entity = BeanTransform.copyProperties(to, SingleProjectMultipleUI.class, true);
        entity.setProjectCategory(ProjectCategory.SINGLE_MULTIPLE);
        entity = super.save(entity);
        SingleProjectMultipleUIBO bo = BeanTransform.copyProperties(entity, SingleProjectMultipleUIBO.class);
        return bo;
    }

    /**
     * 更新单个项目多个界面
     *
     * @param to 单个项目多个界面to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(SingleProjectMultipleUITO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
            SingleProjectMultipleUI model = super.findById(to.getId());
            if (model != null) {
                updateSingleProjectMultipleUI(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新单个项目多个界面
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateSingleProjectMultipleUI(SingleProjectMultipleUITO to, SingleProjectMultipleUI model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除单个项目多个界面
     *
     * @param id 单个项目多个界面唯一标识
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
        RpcTransmit.transmitUserToken( userToken );
        if( flagSee ){
            return true;
        }else{
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