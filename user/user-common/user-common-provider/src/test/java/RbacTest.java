import com.bjike.goddess.user.common.entity.*;
import com.bjike.goddess.user.common.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.bjike.goddess.dbs.jpa.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import user_common_code.ApplicationConfiguration;

import java.time.LocalDateTime;
import java.util.*;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [权限认证业务测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class RbacTest {
    @Autowired
    private IUserSer userSer;
    @Autowired
    private IRoleSer roleSer;
    @Autowired
    private IUserRoleSer userRoleSer;
    @Autowired
    private IPermissionSer permissionSer;
    @Autowired
    private IDepartmentSer departmentSer;
    @Autowired
    private IGroupSer groupSer;
    /**
     * 添加角色
     *
     * @throws SerException
     */
    @Test
    public void addRole() throws SerException {
        Role root = new Role();
        root.setName("根角色１");
        root.setDescription("无描述");
        Role child = new Role();
        child.setName("子角色１");
        child.setDescription("无描述");
        child.setParent(root);
        roleSer.save(child);
    }

    /**
     * 添加用户角色
     *
     * @throws SerException
     */
    @Test
    public void addUserRole() throws SerException {
        Role role = roleSer.findById("524fa88b-086f-4d9c-b962-4c6a3810a454");
        User user = userSer.findByPhone("18097910240");
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        userRoleSer.save(userRole);
    }

    /**
     * 添加权限资源
     *
     * @throws SerException
     */
    @Test
    public void addPermission() throws SerException {
        Permission root = new Permission();
        root.setDescription("无描述");
        root.setResource("/");
        root.setName("根资源");
        Permission child = new Permission();
        child.setDescription("无描述");
        child.setResource("/service");
        child.setName("用户资源");
        child.setParent(root);
        permissionSer.save(child);
    }

    /**
     * 查找用户角色
     *
     * @throws SerException
     */
    @Transactional
    @Test
    public void findUserRole() throws SerException {
        List<UserRole> userRoles = userRoleSer.findAll();
        if (null != userRoles) {
            for (UserRole userRole : userRoles) {
                userRole.getRole().getPermissionList().size();
                System.out.println(userRole);
            }
        }
    }

    /**
     * 添加角色权限资源
     *
     * @throws SerException
     */
    @Test
    public void addRolePermission() throws SerException {

        Role role = roleSer.findById("524fa88b-086f-4d9c-b962-4c6a3810a454");
        if (null != role) {
            List<Permission> permissions_list = permissionSer.findAll();
            role.setPermissionList(permissions_list);
            roleSer.update(role);
        }
    }


    /**
     * 更新权限资源
     *
     * @throws SerException
     */
    @Test
    public void updatePermissions() throws SerException {

        Permission permissions = permissionSer.findById("72ae9d8f-9a25-45c1-b068-4387b2667b31"); //儿子
        Permission permission = permissions;
        Permission parent = new Permission();
        parent.setId("99ae9d8f-9a25-45c1-b068-4387b2667b33");//更改父节点为孙节点测试
        permission.setParent(parent);
        permissionSer.update(permission);
        System.out.println(permission);
    }


    /**
     * 查询用户所拥有的所有权限资源
     *
     * @throws SerException
     */
    @Transactional
    @Test
    public void findAllPermissionByUserId() throws SerException {

        Set<Permission> permissions = permissionSer.findAllByUserId("672551a4-4082-4d3f-9314-26851153b3c3");
        System.out.println(permissions);
    }

    /**
     * 查询用户所拥有角色
     *
     * @throws SerException
     */
    @Test
    public void findRoleByUserId() throws SerException {

        Set<Role> roles = roleSer.findRoleByUserId("672551a4-4082-4d3f-9314-26851153b3c3");
        System.out.println(roles);
    }

    /**
     * 通过角色查询其子角色
     *
     * @throws SerException
     */
    @Test
    public void findChildByRoleId() throws SerException {

        Set<Role> roles = roleSer.findChildByRoleId("939aa9a9-4b5e-4542-80ea-de7374a25b5c");
        System.out.println(roles);
    }

    /**
     * 添加部门角色
     *
     * @throws SerException
     */
    @Test
    public void addDeptRole() throws SerException {

        Department department = new Department();
        department.setName("a研发部");
        department.setDescription("111");
        department.setCreateTime(LocalDateTime.now());

        Role role = new Role();
        role.setDescription("1d11无描述");
        role.setCreateTime(LocalDateTime.now());
        role.setName("d11aa");
        department.setRoleList(Arrays.asList(role));
        department.setCreateTime(LocalDateTime.now());
        departmentSer.save(department);
    }


}
