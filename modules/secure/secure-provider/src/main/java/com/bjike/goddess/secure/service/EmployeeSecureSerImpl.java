package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.secure.bo.EmployeeSecureBO;
import com.bjike.goddess.secure.dto.EmployeeSecureDTO;
import com.bjike.goddess.secure.entity.EmployeeSecure;
import com.bjike.goddess.secure.enums.GuideAddrStatus;
import com.bjike.goddess.secure.to.EmployeeSecureTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.NameTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 员工社保基本信息业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 10:19 ]
 * @Description: [ 员工社保基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class EmployeeSecureSerImpl extends ServiceImpl<EmployeeSecure, EmployeeSecureDTO> implements EmployeeSecureSer {
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

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
            case SEE:
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
    @Transactional(rollbackFor = {SerException.class})
    public EmployeeSecureBO save(EmployeeSecureTO to) throws SerException {
        checkAddIdentity();
        EmployeeSecure employeeSecure = BeanTransform.copyProperties(to, EmployeeSecure.class, true);
        super.save(employeeSecure);
        return BeanTransform.copyProperties(employeeSecure, EmployeeSecureBO.class);
//        List<AddEmployeeBO> list=addEmployeeSer.findALL();
//        for(AddEmployeeBO bo:list){
//            EmployeeSecure employeeSecure=new EmployeeSecure();
//            BeanUtils.copyProperties(bo,employeeSecure);
//            employeeSecure.setGroup1(bo.getGroup());
//            employeeSecure.setStatus("购买中");
//            super.save(employeeSecure);
//        }
////        List<AbandonBO> lis1=abandonSer.findALL();
////        for(AbandonBO bo:lis1){
////            EmployeeSecure employeeSecure=new EmployeeSecure();
////            BeanUtils.copyProperties(bo,employeeSecure);
////            employeeSecure.setStatus("放弃购买");
////            super.save(employeeSecure);
////        }
//        List<RemoveEmployeeBO> list2=removeEmployeeSer.findALL();
//        for (RemoveEmployeeBO bo:list2){
//            if(bo.isConfirm_remove()){
////                RemoveEmployee removeEmployee=new RemoveEmployee();
////                removeEmployee=BeanTransform.copyProperties(bo,RemoveEmployee.class,true);
////                String id=removeEmployee.getEmployeeId();
////                List<EmployeeSecureBO> l=this.findBySql(new String[]{id});
////                for(EmployeeSecureBO bo1:l){
////                    EmployeeSecureTO employeeSecureTO=new EmployeeSecureTO();
////                    BeanUtils.copyProperties(bo1,employeeSecureTO);
////                    this.remove(employeeSecureTO);
////                    EmployeeSecure employeeSecure=BeanTransform.copyProperties(employeeSecureTO,EmployeeSecure.class,true);
////                    super.save(employeeSecure);
////                }
//                EmployeeSecure employeeSecure=new EmployeeSecure();
//                BeanUtils.copyProperties(bo,employeeSecure);
//                employeeSecure.setName(bo.getRemoveName());
//                employeeSecure.setEmployeeNum(bo.getEmployeeId());
//                employeeSecure.setStatus("已减员成功");
//                super.save(employeeSecure);
//            }

    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public EmployeeSecureBO edit(EmployeeSecureTO to) throws SerException {
        checkAddIdentity();
        EmployeeSecure employeeSecure = super.findById(to.getId());
        if (employeeSecure == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime a = employeeSecure.getCreateTime();
        employeeSecure = BeanTransform.copyProperties(to, EmployeeSecure.class,true);
        employeeSecure.setCreateTime(a);
        LocalDate time=null;
        try {
            time= DateUtil.parseDate(to.getStartTime());
        }catch (Exception e){
            throw new SerException("日期格式不正确");
        }
        employeeSecure.setStartTime(time);
        employeeSecure.setModifyTime(LocalDateTime.now());
        super.update(employeeSecure);
        return BeanTransform.copyProperties(employeeSecure, EmployeeSecureBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(EmployeeSecureTO to) throws SerException {
//        EmployeeSecure employeeSecure= super.findById(to.getId());
//        employeeSecure.setStatus("已减员成功");
//        super.update(employeeSecure);
    }

    @Override
    public List<EmployeeSecureBO> find(EmployeeSecureDTO dto) throws SerException {
        checkSeeIdentity();
        List<EmployeeSecure> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, EmployeeSecureBO.class);
    }

    @Override
    public EmployeeSecureBO findByID(String id) throws SerException {
        EmployeeSecure employeeSecure = super.findById(id);
        if (employeeSecure == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(employeeSecure, EmployeeSecureBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public EmployeeSecureBO delete(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
        return null;
    }

    @Override
    public EmployeeSecureBO findBySql(String name, String num) throws SerException {
        String[] names = new String[]{name};
        List<EmployeeSecureBO> list = null;
        if ((name != null) && (num != null) && (StringUtils.isNotBlank(num))) {
            String[] nums = new String[]{num};
            for (int i = 0; i < names.length; i++) {
                String[] fields = new String[]{"id", "status"};
                String sql = "select id,status from secure_employeesecure where employeeNum='" + nums[i] + "' and name='" + names[i] + "'";
                list = this.findBySql(sql, EmployeeSecureBO.class, fields);
            }
        } else if ((num == null) || (StringUtils.isBlank(num))) {
            for (int i = 0; i < names.length; i++) {
                String[] fields = new String[]{"id", "status"};
                String sql = "select id,status from secure_employeesecure where name='" + names[i] + "'";
                list = this.findBySql(sql, EmployeeSecureBO.class, fields);
            }
        }
        if ((list != null) && (!list.isEmpty())) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Long count(EmployeeSecureDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public List<EmployeeSecureBO> byName(NameTO to) throws SerException {
        List<EmployeeSecure> employeeSecures = new ArrayList<>();
        if(to.getNames()!=null){
            EmployeeSecureDTO dto = new EmployeeSecureDTO();
            dto.getConditions().add(Restrict.eq("name",to.getNames()));
            employeeSecures = super.findAll();
        }
        List<EmployeeSecureBO> boList = BeanTransform.copyProperties(employeeSecures,EmployeeSecureBO.class);
        return boList;
    }

    @Override
    public Set<String> allName() throws SerException {
        Set<String> set =new HashSet<>();
        List<EmployeeSecure> list = super.findAll();
        for(EmployeeSecure employeeSecure:list){
            set.add(employeeSecure.getName());
        }
        return set;
    }
}