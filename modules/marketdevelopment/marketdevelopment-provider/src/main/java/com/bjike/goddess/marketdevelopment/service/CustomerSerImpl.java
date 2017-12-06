package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.marketdevelopment.bo.CustomerBO;
import com.bjike.goddess.marketdevelopment.dto.CustomerDTO;
import com.bjike.goddess.marketdevelopment.entity.Customer;
import com.bjike.goddess.marketdevelopment.enums.Status;
import com.bjike.goddess.marketdevelopment.excel.CustomerExportExcel;
import com.bjike.goddess.marketdevelopment.excel.CustomerImportExcel;
import com.bjike.goddess.marketdevelopment.to.CustomerTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户接触阶段业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-29 09:37 ]
 * @Description: [ 客户接触阶段业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class CustomerSerImpl extends ServiceImpl<Customer, CustomerDTO> implements CustomerSer {

    @Override
    public List<CustomerBO> maps(CustomerDTO dto) throws SerException {
        dto.getSorts().add("code=asc");
        dto.getSorts().add("createTime=asc");
        List<Customer> customers = super.findByPage(dto);
        List<CustomerBO> bos = BeanTransform.copyProperties(customers, CustomerBO.class);
        return bos;
    }

    @Override
    public void save(CustomerTO to) throws SerException {
        Customer entity = BeanTransform.copyProperties(to, Customer.class);
        char code = 'A';
        List<Customer> customers = super.findAll();
        if (null != customers && customers.size() > 0) {
            List<String> stringList = customers.stream().map(Customer::getStage).distinct().collect(Collectors.toList());
            code = (char) (code + stringList.size());
        }
        entity.setStatus(Status.THAW);
        entity.setCode("YWJD-" + String.valueOf(code));
        int num = entity.getResults().split("、").length;
        entity.setStageNum(String.valueOf(num));
        super.save(entity);
    }

    @Override
    public void update(CustomerTO to) throws SerException {
        char code = 'A';
        Customer entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        BeanTransform.copyProperties(to, entity);
//        CustomerDTO dto = new CustomerDTO();
//        dto.getConditions().add(Restrict.eq("stage", entity.getStage()));
//        List<Customer> customers = super.findByCis(dto);
//        if (null != customers && customers.size() > 0) {
//            entity.setCode(customers.get(0).getCode());
//            entity.setModifyTime(LocalDateTime.now());
//            super.update(entity);
//        } else {
//            List<Customer> customers1 = super.findAll();
//            if (null != customers1 && customers1.size() > 0) {
//                List<String> stringList = customers1.stream().map(Customer::getStage).distinct().collect(Collectors.toList());
//                code = (char) (stringList.size() + 1);
//            }
//            entity.setCode("YWJD-" + String.valueOf(code));
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
//        }
    }

    @Override
    public void congeal(CustomerTO to) throws SerException {
        Customer entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        entity.setStatus(Status.CONGEAL);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void thaw(CustomerTO to) throws SerException {
        Customer entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        entity.setStatus(Status.THAW);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void delete(CustomerTO to) throws SerException {
        Customer entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        super.remove(entity);
    }

    @Override
    public CustomerBO getById(String id) throws SerException {
        Customer entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }

        CustomerBO bo = BeanTransform.copyProperties(entity, CustomerBO.class);
        return bo;
    }

    @Override
    public Long getTotal(CustomerDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public byte[] templateExcel() throws SerException {
        CustomerImportExcel customerImportExcel = new CustomerImportExcel();
        List<CustomerImportExcel> list = new ArrayList<>(0);
        list.add(customerImportExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(list, excel);
        return bytes;
    }

    @Override
    public byte[] exportExcel(CustomerDTO dto) throws SerException {
        dto.getSorts().add("code=asc");
        List<Customer> customers = super.findByCis(dto);
        List<CustomerExportExcel> exportExcels = BeanTransform.copyProperties(customers, CustomerExportExcel.class);

        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exportExcels, excel);
        return bytes;
    }

    @Override
    public void upload(List<CustomerImportExcel> tos) throws SerException {
        for(CustomerImportExcel excel : tos){
            CustomerTO to = BeanTransform.copyProperties(excel, CustomerTO.class);
            save(to);
        }
    }
}