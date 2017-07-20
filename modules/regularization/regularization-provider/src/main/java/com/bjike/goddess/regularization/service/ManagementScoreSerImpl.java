package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.bo.ManagementScoreBO;
import com.bjike.goddess.regularization.dto.ManagementScoreDTO;
import com.bjike.goddess.regularization.entity.ManagementScore;
import com.bjike.goddess.regularization.excel.SonPermissionObject;
import com.bjike.goddess.regularization.to.GuidePermissionTO;
import com.bjike.goddess.regularization.to.ManagementScoreTO;
import com.bjike.goddess.regularization.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理层评分业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-17 11:01 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "regularizationSerCache")
@Service
public class ManagementScoreSerImpl extends ServiceImpl<ManagementScore, ManagementScoreDTO> implements ManagementScoreSer {

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
     * 检查权限(模块)
     *
     * @throws SerException
     */
    private void checkModPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员，没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查员工转正查看权限(模块)
     *
     * @throws SerException
     */
    private Boolean checkzzSeePermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
        } else {
            flag = true;
        }
        RpcTransmit.transmitUserToken(userToken);

        return flag;
    }

    /**
     * 检查权限(岗位)
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
            flag = cusPermissionSer.jobsCusPermission("4");
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
     * 核对模块审核权限（模块级别）
     */
    private Boolean guideMondIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
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
            flag = cusPermissionSer.jobsCusPermission("4");
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
        Boolean flagGuideMod = guideMondIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuidePosi = guidePosinIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuideArr = guideArrIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagGuide || flagGuideMod || flagGuidePosi || flagGuideArr) {
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
            case ZZLIST:
                flag = true;
                break;
            case ZZADD:
                flag = true;
                break;
            case ZZEDIT:
                flag = true;
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
                flag = guideMondIdentity();
                break;
            case BUDGETMODUL:
                flag = guideMondIdentity();
                break;
            case AUDIT:
                flag = guidePosinIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    /**
     * 分页查询管理层评分
     *
     * @return class ManagementScoreBO
     * @throws SerException
     */
    @Override
    public List<ManagementScoreBO> list(ManagementScoreDTO dto) throws SerException {
        checkPermission();
        List<ManagementScore> list = super.findByPage(dto);
        List<ManagementScoreBO> listBO = BeanTransform.copyProperties(list, ManagementScoreBO.class);
        return listBO;
    }

    /**
     * 保存管理层评分
     *
     * @param to 管理层评分to
     * @return class ManagementScoreBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public ManagementScoreBO save(ManagementScoreTO to) throws SerException {
        checkPermission();
        ManagementScore entity = BeanTransform.copyProperties(to, ManagementScore.class, true);
        entity = super.save(entity);
        ManagementScoreBO bo = BeanTransform.copyProperties(entity, ManagementScoreBO.class);
        return bo;
    }

    /**
     * 更新管理层评分
     *
     * @param to 管理层评分to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void update(ManagementScoreTO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())){
            ManagementScore model = super.findById(to.getId());
            if (model != null) {
                updateManagementScore(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新管理层评分
     *
     * @param to 管理层评分to
     * @param model 管理层评分
     * @throws SerException
     */
    private void updateManagementScore(ManagementScoreTO to, ManagementScore model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除管理层评分
     *
     * @param id 管理层评分唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }

}