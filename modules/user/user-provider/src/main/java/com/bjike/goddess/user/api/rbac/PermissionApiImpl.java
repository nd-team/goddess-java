package com.bjike.goddess.user.api.rbac;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.rbac.PermissionBO;
import com.bjike.goddess.user.bo.rbac.PermissionTreeBO;
import com.bjike.goddess.user.service.UserSer;
import com.bjike.goddess.user.service.rbac.PermissionSer;
import com.bjike.goddess.user.session.valid_right.LoginUser;
import com.bjike.goddess.user.session.valid_right.UserSession;
import com.bjike.goddess.user.to.rbac.PermissionTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-11 14:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("permissionApiImpl")
public class PermissionApiImpl implements PermissionAPI {
    @Autowired
    private PermissionSer permissionSer;
    @Autowired
    private UserSer userSer;

    @Override
    public List<PermissionBO> findByUserId(String userId) throws SerException {
        return permissionSer.findByUserId(userId);
    }

    @Override
    public List<PermissionTreeBO> treeData(String id) throws SerException {
        return permissionSer.treeData(id);
    }

    @Override
    public PermissionBO save(PermissionTO permissionTO) throws SerException {
        return permissionSer.save(permissionTO);
    }

    @Override
    public void remove(String id) throws SerException {
        permissionSer.remove(id);
    }

    @Override
    public void update(PermissionTO permissionTO) throws SerException {
        permissionSer.update(permissionTO);
    }

    @Override
    public List<PermissionBO> currentPermissions() throws SerException {

        List<PermissionBO> permissions = userSer.currentPermissions();
        if (null != permissions) {
            return permissions;
        } else {
            String token = RpcContext.getContext().getAttachment("userToken");
            if(StringUtils.isNotBlank(token)){
                UserBO userBO = userSer.currentUser(token);
                permissions = permissionSer.findByUserId(userBO.getId());
                if (null != permissions && permissions.size() > 0) {
                    LoginUser loginUser = BeanTransform.copyProperties(userBO, LoginUser.class);
                    loginUser.setPermissions(permissions);
                    UserSession.put(token, loginUser);
                }
            }
            return permissions;
        }

    }
}
