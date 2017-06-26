package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.CusPermissionAPI;
import com.bjike.goddess.customer.bo.CustomerLevelBO;
import com.bjike.goddess.customer.dto.CustomerLevelDTO;
import com.bjike.goddess.customer.entity.CustomerLevel;
import com.bjike.goddess.customer.enums.GuideAddrStatus;
import com.bjike.goddess.customer.to.CustomerLevelTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.customer.vo.SonPermissionObject;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户级别业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T17:00:40.879 ]
 * @Description: [ 客户级别业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class CustomerLevelSerImpl extends ServiceImpl<CustomerLevel, CustomerLevelDTO> implements CustomerLevelSer {

    @Autowired
    private CusPermissionSer cusPermissionSer ;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CustomerBaseInfoSer customerBaseInfoSer;
    @Autowired
    private CustomerDetailSer customerDetailSer;
    @Autowired
    private CusEmailSer cusEmailSer;



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
                throw new SerException("您不是相应部门的人员，不可以查看");
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
     * 导航栏核对查看权限（部门级别）
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
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("customerlevel");
        obj.setDescribesion("客户级别设置");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = customerBaseInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("customerbaseinfo");
        obj.setDescribesion("客户基本信息");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = customerDetailSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("customerdetail");
        obj.setDescribesion("客户详细信息");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = cusEmailSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("cusemail");
        obj.setDescribesion("邮件发送定制");
        if (flagSeeEmail) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
            case DELETE:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }



    @Override
    public Long countCustomerLevel(CustomerLevelDTO customerLevelDTO) throws SerException {
        Long count = super.count(customerLevelDTO);
        return count;
    }

    @Override
    public List<CustomerLevelBO> listCustomerLevel(CustomerLevelDTO customerLevelDTO) throws SerException {
        checkSeeIdentity();
        customerLevelDTO.getSorts().add("name=asc");
        List<CustomerLevel> list = super.findByCis(customerLevelDTO,true);

        return BeanTransform.copyProperties(list, CustomerLevelBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerLevelBO addCustomerLevel(CustomerLevelTO customerLevelTO) throws SerException {
        //商务模块添加权限
        checkAddIdentity();
        CustomerLevel customerLevel = BeanTransform.copyProperties(customerLevelTO,CustomerLevel.class,true);
        customerLevel.setCreateTime(LocalDateTime.now());
        super.save( customerLevel );
        return BeanTransform.copyProperties(customerLevel, CustomerLevelBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerLevelBO editCustomerLevel(CustomerLevelTO customerLevelTO) throws SerException {
        //商务模块编辑权限
        checkAddIdentity();
        CustomerLevel customerLevel = BeanTransform.copyProperties(customerLevelTO,CustomerLevel.class,true);
        CustomerLevel cusLevel = super.findById( customerLevelTO.getId() );

        cusLevel.setName( customerLevel.getName() );
        cusLevel.setRemark( customerLevel.getRemark() );
        cusLevel.setName( customerLevel.getName() );
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(customerLevel, CustomerLevelBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCustomerLevel(String id) throws SerException {
        //商务模块编辑权限
        checkAddIdentity();
        super.remove( id );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealCustomerLevel(String id) throws SerException {
        //商务模块冻结权限
        checkAddIdentity();
        CustomerLevel customerLevel = super.findById( id );
        customerLevel.setStatus(Status.CONGEAL);
        customerLevel.setModifyTime(LocalDateTime.now());
        super.update( customerLevel );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawCustomerLevel(String id) throws SerException {
        //商务模块解冻权限
        checkAddIdentity();
        CustomerLevel customerLevel = super.findById( id );
        customerLevel.setStatus(Status.THAW);
        customerLevel.setModifyTime(LocalDateTime.now());
        super.update( customerLevel );
    }

    
    @Override
    public CustomerLevelBO getCustomerLevelByName(String name) throws SerException {

        CustomerLevelDTO dto = new CustomerLevelDTO();
        dto.getConditions().add(Restrict.eq("name",name));

        CustomerLevel customerLevel = super.findOne( dto );
        return BeanTransform.copyProperties(customerLevel ,CustomerLevelBO.class);
    }

    @Override
    public List<String> getAllLevel() throws SerException {
        String[] fields = new String[]{"name"};
        List<CustomerLevelBO> customerLevelBOS =super.findBySql("select name from customer_customerlevel order by name asc ", CustomerLevelBO.class, fields);

        List<String> levelList  = customerLevelBOS.stream().map(CustomerLevelBO::getName).distinct().collect(Collectors.toList());


        return levelList;
    }
}