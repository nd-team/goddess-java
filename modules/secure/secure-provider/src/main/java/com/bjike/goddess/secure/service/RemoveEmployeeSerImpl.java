package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.secure.bo.EmployeeSecureBO;
import com.bjike.goddess.secure.bo.RemoveEmployeeBO;
import com.bjike.goddess.secure.dto.RemoveEmployeeDTO;
import com.bjike.goddess.secure.entity.EmployeeSecure;
import com.bjike.goddess.secure.entity.RemoveEmployee;
import com.bjike.goddess.secure.enums.GuideAddrStatus;
import com.bjike.goddess.secure.to.EmployeeSecureTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.RemoveEmployeeTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 减员名单业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 09:48 ]
 * @Description: [ 减员名单业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class RemoveEmployeeSerImpl extends ServiceImpl<RemoveEmployee, RemoveEmployeeDTO> implements RemoveEmployeeSer {
    @Autowired
    private EmployeeSecureSer employeeSecureSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;
    @Autowired
    private MessageAPI messageAPI;

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

    //总经办
    private void checkMIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
            if (!flag) {
                throw new SerException("您不是总经理，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    //社保管理负责人
    private void checkSBIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("5");
            if (!flag) {
                throw new SerException("您不是社保管理负责人，不可以操作");
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

    //总经办
    private Boolean guideMIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
        } else {
            flag = true;
        }
        return flag;
    }

    //社保管理负责人
    private Boolean guideSBIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("5");
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
            case BOSS:
                flag = guideMIdentity();
                break;
            case CHARGE:
                flag = guideSBIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEARCH:
                flag = guideSBIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public RemoveEmployeeBO save(RemoveEmployeeTO to) throws SerException {
        checkAddIdentity();
        RemoveEmployee removeEmployee = BeanTransform.copyProperties(to, RemoveEmployee.class, true);
        super.save(removeEmployee);
        return BeanTransform.copyProperties(removeEmployee, RemoveEmployeeBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public RemoveEmployeeBO edit(RemoveEmployeeTO to) throws SerException {
        checkAddIdentity();
        RemoveEmployee removeEmployee = super.findById(to.getId());
        LocalDateTime a = removeEmployee.getCreateTime();
        removeEmployee = BeanTransform.copyProperties(to, RemoveEmployee.class);
        removeEmployee.setCreateTime(a);
        removeEmployee.setModifyTime(LocalDateTime.now());
        super.update(removeEmployee);
        return BeanTransform.copyProperties(removeEmployee, RemoveEmployeeBO.class);
    }

    @Override
    public List<RemoveEmployeeBO> find(RemoveEmployeeDTO dto) throws SerException {
        checkSeeIdentity();
        String removeName = dto.getRemoveName();
        String employeeId = dto.getEmployeeId();
        if (StringUtils.isNotBlank(removeName)) {
            dto.getConditions().add(Restrict.eq("removeName", removeName));
        }
        if (StringUtils.isNotBlank(employeeId)) {
            dto.getConditions().add(Restrict.eq("employeeId", employeeId));
        }
        List<RemoveEmployee> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, RemoveEmployeeBO.class);
    }

    @Override
    public RemoveEmployeeBO findByID(String id) throws SerException {
        RemoveEmployee removeEmployee = super.findById(id);
        return BeanTransform.copyProperties(removeEmployee, RemoveEmployeeBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public RemoveEmployeeBO delete(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
        return null;
    }

    @Override
    public RemoveEmployeeBO findByNameAndId(RemoveEmployeeTO to) throws SerException {
        checkSBIdentity();
        String removeName = to.getRemoveName();
        String employeeId = to.getEmployeeId();
        String[] names = new String[]{removeName};
        List<RemoveEmployeeBO> list = null;
        if ((removeName != null) && (employeeId != null) && (StringUtils.isNotBlank(employeeId))) {
            String[] ids = new String[]{employeeId};
            for (int i = 0; i < names.length; i++) {
                String[] fields = new String[]{"id", "removeName", "employeeId", "countCompany", "countCity", "removeType", "company", "removeCity", "quantityName", "secureTime", "removeCount", "description", "confirmRemove"};
                String sql = "select id,removeName,employeeId,countCompany,countCity,removeType,company,removeCity,quantityName,secureTime,removeCount,description,confirmRemove from " +
                        "secure_removeemployee where removeName='" + names[i] + "' and employeeId='" + ids[i] + "'";
                list = this.findBySql(sql, RemoveEmployeeBO.class, fields);
            }
        } else {
            for (int i = 0; i < names.length; i++) {
                String[] fields = new String[]{"id", "removeName", "countCompany", "countCity", "removeType", "company", "removeCity", "quantityName", "secureTime", "removeCount", "description", "confirmRemove"};
                String sql = "select id,removeName,countCompany,countCity,removeType,company,removeCity,quantityName,secureTime,removeCount,description,confirmRemove from " +
                        "secure_removeemployee where removeName='" + names[i] + "'";
                list = this.findBySql(sql, RemoveEmployeeBO.class, fields);
            }
        }
        if ((list != null) && (!list.isEmpty())) {
            return list.get(0);
        } else {
            return null;
        }
    }

    private String[] yyEmails() throws SerException {
        Set<String> set = new HashSet<>();
        List<DepartmentDetailBO> list = departmentDetailAPI.findStatus();
        for (DepartmentDetailBO departmentDetailBO : list) {
            if ("运营商务部".equals(departmentDetailBO.getDepartment())) {
                CommonalityBO commonality = commonalityAPI.findByDepartment(departmentDetailBO.getId());
                if (commonality != null) {
                    set.add(commonality.getEmail());
                }
            }
        }
        String[] strings = new String[set.size()];
        strings = set.toArray(strings);
        return strings;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void confirmRemove(String id) throws SerException {
        checkSBIdentity();
        String userToken = RpcTransmit.getUserToken();
        RemoveEmployee removeEmployee = super.findById(id);
        removeEmployee.setConfirmRemove(true);
        removeEmployee.setModifyTime(LocalDateTime.now());
        super.update(removeEmployee);
        String name = removeEmployee.getRemoveName();
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle("有员工确认社保减员成功");
        messageTO.setContent("员工编号为" + removeEmployee.getEmployeeId() + "的" + name + "确认社保减员成功");
        messageTO.setReceivers(yyEmails());
        messageAPI.send(messageTO);
        EmployeeSecure entity = findByNumAndName(removeEmployee.getEmployeeId(), removeEmployee.getRemoveName());
        if (entity != null) {
//            EmployeeSecure employeeSecure = new EmployeeSecure();
//            BeanUtils.copyProperties(removeEmployee, employeeSecure);
//            employeeSecure.setName(removeEmployee.getRemoveName());
//            employeeSecure.setEmployeeNum(removeEmployee.getEmployeeId());
//            employeeSecure.setStatus("已减员成功");
//            employeeSecureSer.save(employeeSecure);
            EmployeeSecureBO bo = employeeSecureSer.findByID(entity.getId());
            EmployeeSecureTO employeeSecureTO = BeanTransform.copyProperties(bo, EmployeeSecureTO.class);
            employeeSecureTO.setStatus("已减员成功");
            RpcTransmit.transmitUserToken(userToken);
            employeeSecureSer.edit(employeeSecureTO);
        }
    }

    @Override
    public List<RemoveEmployeeBO> findALL() throws SerException {
        List<RemoveEmployee> list = super.findAll();
        return BeanTransform.copyProperties(list, RemoveEmployeeBO.class);
    }

    @Override
    public Long count(RemoveEmployeeDTO dto) throws SerException {
        String removeName = dto.getRemoveName();
        String employeeId = dto.getEmployeeId();
        if (StringUtils.isNotBlank(removeName)) {
            dto.getConditions().add(Restrict.eq("removeName", removeName));
        }
        if (StringUtils.isNotBlank(employeeId)) {
            dto.getConditions().add(Restrict.eq("employeeId", employeeId));
        }
        return super.count(dto);
    }

    /**
     * 通过员工编号和姓名查找员工社保信息
     *
     * @param employeeNum 员工编号
     * @param name        姓名
     * @return
     * @throws SerException
     */
    private EmployeeSecure findByNumAndName(String employeeNum, String name) throws SerException {
        String[] names = new String[]{name};
        List<EmployeeSecure> list = null;
        if ((employeeNum != null) && (name != null) && (StringUtils.isNotBlank(employeeNum))) {
            String[] nums = new String[]{employeeNum};
            for (int i = 0; i < names.length; i++) {
                String sql = "select id from secure_employeesecure " +
                        "where employeeNum='" + nums[i] + "' AND name='" + names[i] + "'";
                String[] fileds = new String[]{"id"};
                list = super.findBySql(sql, EmployeeSecure.class, fileds);
            }
        } else if ((employeeNum == null) || (StringUtils.isBlank(employeeNum))) {
            for (int i = 0; i < names.length; i++) {
                String sql = "select id from secure_employeesecure " +
                        "where name='" + names[i] + "'";
                String[] fileds = new String[]{"id"};
                list = super.findBySql(sql, EmployeeSecure.class, fileds);
            }
        }
        if ((list != null) && (!list.isEmpty())) {
            return list.get(0);
        }
        return null;
    }
}