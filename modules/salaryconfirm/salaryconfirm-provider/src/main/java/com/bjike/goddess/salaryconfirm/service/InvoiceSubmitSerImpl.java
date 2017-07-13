package com.bjike.goddess.salaryconfirm.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.salaryconfirm.bo.InvoiceSubmitBO;
import com.bjike.goddess.salaryconfirm.dto.InvoiceSubmitDTO;
import com.bjike.goddess.salaryconfirm.entity.InvoiceSubmit;
import com.bjike.goddess.salaryconfirm.enums.GuideAddrStatus;
import com.bjike.goddess.salaryconfirm.excel.SonPermissionObject;
import com.bjike.goddess.salaryconfirm.to.GuidePermissionTO;
import com.bjike.goddess.salaryconfirm.to.InvoiceSubmitTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 上交发票业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-17 05:47 ]
 * @Description: [ 上交发票业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "salaryconfirmSerCache")
@Service
public class InvoiceSubmitSerImpl extends ServiceImpl<InvoiceSubmit, InvoiceSubmitDTO> implements InvoiceSubmitSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private SalaryconfirmSer salaryconfirmSer;

    @Override
    public InvoiceSubmitBO insertModel(InvoiceSubmitTO to) throws SerException {
        String userName = userAPI.currentUser().getUsername();
        InvoiceSubmit model = BeanTransform.copyProperties(to, InvoiceSubmit.class, true);
        model.setCreateUser(userName);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, InvoiceSubmitBO.class);
    }

    @Override
    public InvoiceSubmitBO updateModel(InvoiceSubmitTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            InvoiceSubmit model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                model.setModifyUser(userAPI.currentUser().getUsername());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, InvoiceSubmitBO.class);
    }

    @Override
    public List<InvoiceSubmitBO> pageList(InvoiceSubmitDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), InvoiceSubmitBO.class);
    }

    @Override
    public List<InvoiceSubmitBO> findByCondition(String employeeNumber, Integer year, Integer month) throws SerException {
        InvoiceSubmitDTO dto = new InvoiceSubmitDTO();
        dto.getConditions().add(Restrict.eq("employeeNumber", employeeNumber));
        //查询指定月份前的发票数据
        dto.getConditions().add(Restrict.lt("submitDate", LocalDate.of(year, month, 1)));
        return BeanTransform.copyProperties(super.findByCis(dto), InvoiceSubmitBO.class);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();

        String userToken = RpcTransmit.getUserToken();
        Boolean flagAddSign = guideSeeIdentity();
        SonPermissionObject obj = new SonPermissionObject();
        obj = new SonPermissionObject();
        obj.setName("invoicesubmit");
        obj.setDescribesion("发票管理");
        if (flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSalarycon = salaryconfirmSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("salaryconfirm");
        obj.setDescribesion("薪资核算");
        if (flagSalarycon) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagWait = salaryconfirmSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("waitconfirm");
        obj.setDescribesion("待确认薪资");
        if (flagWait) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagConfirm = salaryconfirmSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("confirmed");
        obj.setDescribesion("已确认薪资");
        if (flagConfirm) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagPay = salaryconfirmSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("payed");
        obj.setDescribesion("已付薪资");
        if (flagPay) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagCollect = salaryconfirmSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("collect");
        obj.setDescribesion("汇总分析");
        if (flagCollect) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
     * 导航栏核对添加修改删除审核权限（部门级别）
     */
    private Boolean guideAddIdentity() throws SerException {
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

    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = to.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {

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
}