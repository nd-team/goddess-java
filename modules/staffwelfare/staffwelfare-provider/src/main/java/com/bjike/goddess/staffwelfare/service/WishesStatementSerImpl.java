package com.bjike.goddess.staffwelfare.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffwelfare.bo.WishesStatementBO;
import com.bjike.goddess.staffwelfare.dto.WishesStatementDTO;
import com.bjike.goddess.staffwelfare.entity.WishesStatement;
import com.bjike.goddess.staffwelfare.enums.GuideAddrStatus;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfare.to.WishesStatementTO;
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
 * 祝福语业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 03:21 ]
 * @Description: [ 祝福语业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffwelfaremanageSerCache")
@Service
public class WishesStatementSerImpl extends ServiceImpl<WishesStatement, WishesStatementDTO> implements WishesStatementSer {

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
    public WishesStatementBO insertModel(WishesStatementTO to) throws SerException {
        checkAddIdentity();
        WishesStatement model = BeanTransform.copyProperties(to, WishesStatement.class, true);
        model.setCreateUser(getCurrentUser().getUsername());
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, WishesStatementBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public WishesStatementBO updateModel(WishesStatementTO to) throws SerException {
        checkAddIdentity();
        if (!StringUtils.isEmpty(to.getId())) {
            WishesStatement model = super.findById(to.getId());
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
        return BeanTransform.copyProperties(to, WishesStatementBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<WishesStatementBO> pageList(WishesStatementDTO dto) throws SerException {
        checkAddIdentity();
        dto.getSorts().add("createTime=desc");
        //查询当前用户的祝福语
        dto.getConditions().add(Restrict.or("createUser", getCurrentUser().getUsername()));
        List<WishesStatement> list = super.findByPage(dto);
        List<WishesStatementBO> boList = BeanTransform.copyProperties(list, WishesStatementBO.class);
        if(boList !=null && boList.size() > 0){
            for(WishesStatement wishesStatement : list){
                for(WishesStatementBO wishesStatementBO : boList){
                    if(org.apache.commons.lang3.StringUtils.isNotBlank(wishesStatement.getModifyTime().toString())) {
                        wishesStatementBO.setUpdateDate(wishesStatement.getModifyTime().toString());
                    }else{
                        wishesStatementBO.setUpdateDate(wishesStatement.getCreateTime().toString());
                    }
                }
            }
        }
        return boList;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<WishesStatementBO> findAllStatement() throws SerException {
        checkAddIdentity();
        WishesStatementDTO dto = new WishesStatementDTO();
        dto.getSorts().add("createTime=desc");
        //查询当前用户的非冻结感谢语
        dto.getConditions().add(Restrict.or("createUser", getCurrentUser().getUsername()));
        List<WishesStatement> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, WishesStatementBO.class);
    }

    //获取当前用户
    public UserBO getCurrentUser() throws SerException {
        UserBO userBO = userAPI.currentUser();
        if (userBO != null) {
            return userBO;
        } else {
            throw new SerException("用户未登录或已超时!");
        }
    }

    @Override
    public WishesStatementBO findOne(String id) throws SerException {
        WishesStatement wishesStatement = super.findById(id);
        WishesStatementBO bo = BeanTransform.copyProperties(wishesStatement,WishesStatementBO.class);
        return bo;
    }
}