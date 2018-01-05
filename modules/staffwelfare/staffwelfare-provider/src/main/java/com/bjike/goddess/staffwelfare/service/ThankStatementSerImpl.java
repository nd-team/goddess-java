package com.bjike.goddess.staffwelfare.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffwelfare.bo.ThankStatementBO;
import com.bjike.goddess.staffwelfare.dto.ThankStatementDTO;
import com.bjike.goddess.staffwelfare.entity.ThankStatement;
import com.bjike.goddess.staffwelfare.enums.GuideAddrStatus;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfare.to.ThankStatementTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 感谢语业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 09:14 ]
 * @Description: [ 感谢语业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffwelfaremanageSerCache")
@Service
public class ThankStatementSerImpl extends ServiceImpl<ThankStatement, ThankStatementDTO> implements ThankStatementSer {

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
    @Transactional(rollbackFor = SerException.class)
    public ThankStatementBO insertModel(ThankStatementTO to) throws SerException {
        checkAddIdentity();
        ThankStatement model = BeanTransform.copyProperties(to, ThankStatement.class, true);
        model.setCreateUser(getCurrentUser().getUsername());
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, ThankStatementBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ThankStatementBO updateModel(ThankStatementTO to) throws SerException {
        checkAddIdentity();
        if (!StringUtils.isEmpty(to.getId())) {
            ThankStatement model = super.findById(to.getId());
            if (model != null) {
                model.setUpdateUser(getCurrentUser().getUsername());
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, ThankStatementBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ThankStatementBO> pageList(ThankStatementDTO dto) throws SerException {
        checkAddIdentity();
        dto.getSorts().add("createTime=desc");
        //查询当前用户及共享的感谢语
        dto.getConditions().add(Restrict.or("createUser",getCurrentUser().getUsername()));
        dto.getConditions().add(Restrict.or("share",1));
        List<ThankStatement> list = super.findByPage(dto);
        List<ThankStatementBO> boList = BeanTransform.copyProperties(list, ThankStatementBO.class);
        if(boList !=null && boList.size() > 0){
            for(ThankStatement thankStatement : list) {
                for (ThankStatementBO thankStatementBO : boList) {
                    if(org.apache.commons.lang3.StringUtils.isNotBlank(thankStatement.getModifyTime().toString())) {
                        thankStatementBO.setUpdateDate(thankStatement.getModifyTime().toString());
                    }else{
                        thankStatementBO.setUpdateDate(thankStatement.getCreateTime().toString());
                    }
                }
            }
        }
        return boList;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ThankStatementBO> findAllStatement() throws SerException {
        checkAddIdentity();
        ThankStatementDTO dto = new ThankStatementDTO();
        dto.getSorts().add("createTime=desc");
        //查询当前用户及共享的非冻结感谢语
        dto.getConditions().add(Restrict.eq("status",Status.THAW));
        dto.getConditions().add(Restrict.or("createUser",getCurrentUser().getUsername()));
        dto.getConditions().add(Restrict.or("share",1));
        List<ThankStatement> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, ThankStatementBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {
        checkAddIdentity();

        if (!StringUtils.isEmpty(id)) {
            ThankStatement model = super.findById(id);
            if (model != null) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.CONGEAL);
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空!");
            }

        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void breakFreeze(String id) throws SerException {
        checkAddIdentity();
        if (!StringUtils.isEmpty(id)) {
            ThankStatement model = super.findById(id);
            if (model != null) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.THAW);
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空!");
            }

        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    //获取当前用户
    public UserBO getCurrentUser() throws SerException{
        UserBO userBO = userAPI.currentUser();
        if (userBO != null){
            return userBO;
        }else{
            throw new SerException("用户未登录或已超时!");
        }
    }

    @Override
    public ThankStatementBO findOne(String id) throws SerException {
        ThankStatement thankStatement = super.findById(id);
        ThankStatementBO bo = BeanTransform.copyProperties(thankStatement,ThankStatementBO.class);
        return bo;
    }
}