package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.*;
import com.bjike.goddess.customer.dto.CustomerBaseInfoDTO;
import com.bjike.goddess.customer.service.CustomerBaseInfoSer;
import com.bjike.goddess.customer.to.CustomerBaseInfoTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户基本信息业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T16:23:28.063 ]
 * @Description: [ 客户基本信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("customerBaseInfoApiImpl")
public class CustomerBaseInfoApiImpl implements CustomerBaseInfoAPI {

    @Autowired
    private CustomerBaseInfoSer customerBaseInfoSer;


    @Override
    public Boolean sonPermission() throws SerException {
        return customerBaseInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return customerBaseInfoSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countCustomerBaseInfo(CustomerBaseInfoDTO customerBaseInfoDTO) throws SerException {
        return customerBaseInfoSer.countCustomerBaseInfo(customerBaseInfoDTO);
    }

    @Override
    public CustomerBaseInfoBO generateCustomerNum() throws SerException {
        return customerBaseInfoSer.generateCustomerNum();
    }

    @Override
    public List<CustomerBaseInfoBO> listCustomerBaseInfo(CustomerBaseInfoDTO customerBaseInfoDTO) throws SerException {
        return customerBaseInfoSer.listCustomerBaseInfo(customerBaseInfoDTO);
    }

    @Override
    public CustomerBaseInfoBO addCustomerBaseInfo(CustomerBaseInfoTO customerBaseInfoTO) throws SerException {
        return customerBaseInfoSer.addCustomerBaseInfo(customerBaseInfoTO);
    }

    @Override
    public CustomerBaseInfoBO editCustomerBaseInfo(CustomerBaseInfoTO customerBaseInfoTO ) throws SerException {
        return customerBaseInfoSer.editCustomerBaseInfo(customerBaseInfoTO );
    }

    @Override
    public void deleteCustomerBaseInfo(String id) throws SerException {
        customerBaseInfoSer.deleteCustomerBaseInfo(id);
    }

    @Override
    public void congealCustomerBaseInfo(String id) throws SerException {
        customerBaseInfoSer.congealCustomerBaseInfo(id);
    }

    @Override
    public void thawCustomerBaseInfo(String id) throws SerException {
        customerBaseInfoSer.thawCustomerBaseInfo(id);
    }

    @Override
    public List<String> getCustomerBaseInfoCusNum() throws SerException {
        return customerBaseInfoSer.getCustomerBaseInfoCusNum();
    }

    @Override
    public List<String> getCustomerBaseInfoArea() throws SerException {
        return customerBaseInfoSer.getCustomerBaseInfoArea();
    }

    @Override
    public List<String> getCustomerBaseInfoName() throws SerException {
        return customerBaseInfoSer.getCustomerBaseInfoName();
    }


    @Override
    public CustomerBaseInfoBO addMarketCustomerInfo(@NotBlank String customerName,String origanizion) throws SerException {
        return customerBaseInfoSer.addMarketCustomerInfo( customerName ,origanizion );
    }

    @Override
    public CustomerBaseInfoBO getCustomerInfoByNum(String customerNum) throws SerException {
        return customerBaseInfoSer.getCustomerInfoByNum(customerNum);
    }

    @Override
    public List<String> getCustomerBaseInfoWorks() throws SerException {
        return customerBaseInfoSer.getCustomerBaseInfoWorks();
    }

    @Override
    public List<CustomerBaseInfoBO> findByOriganizion(String origanizion) throws SerException {
        return customerBaseInfoSer.findByOriganizion(origanizion);
    }

    @Override
    public List<CustomerNameNumBO> findNameNum() throws SerException {
        return customerBaseInfoSer.findNameNum();
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return customerBaseInfoSer.exportExcel();
    }

    @Override
    public byte[] templateExport() throws SerException {
        return customerBaseInfoSer.templateExport();
    }

    @Override
    public List<SummationBO> summaDay(String summDate) throws SerException {
        return customerBaseInfoSer.summaDay(summDate);
    }

    @Override
    public List<SummationBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
        return customerBaseInfoSer.summaWeek(year,month,week);
    }

    @Override
    public List<SummationBO> summaMonth(Integer year, Integer month) throws SerException {
        return customerBaseInfoSer.summaMonth(year,month);
    }

    @Override
    public List<SummationBO> summaQuarter(Integer year, Integer quarter) throws SerException {
        return customerBaseInfoSer.summaQuarter(year,quarter);
    }

    @Override
    public List<SummationBO> summaYear(Integer year) throws SerException {
        return customerBaseInfoSer.summaYear(year);
    }

    @Override
    public List<SummationBO> summaTotal(String endDate) throws SerException {
        return customerBaseInfoSer.summaTotal(endDate);
    }

    @Override
    public OptionBO figureShowDay(String summDate) throws SerException {
        return customerBaseInfoSer.figureShowDay(summDate);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        return customerBaseInfoSer.figureShowWeek(year,month,week);
    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        return customerBaseInfoSer.figureShowMonth(year,month);
    }

    @Override
    public OptionBO figureShowQuarter(Integer year, Integer quarter) throws SerException {
        return customerBaseInfoSer.figureShowQuarter(year,quarter);
    }

    @Override
    public OptionBO figureShowYear(Integer year) throws SerException {
        return customerBaseInfoSer.figureShowYear(year);
    }

    @Override
    public OptionBO figureShowTotal(String endDate) throws SerException {
        return customerBaseInfoSer.figureShowTotal(endDate);
    }

    @Override
    public PieOptionBO areaPieShow() throws SerException {
        return customerBaseInfoSer.areaPieShow();
    }

    @Override
    public PieOptionBO areaBussTypePieShow(String area) throws SerException {
        return customerBaseInfoSer.areaBussTypePieShow(area);
    }

    @Override
    public List<String> findArea() throws SerException {
        return customerBaseInfoSer.findArea();
    }

    @Override
    public OptionBO bussTypeAreaBaiShow() throws SerException {
        return customerBaseInfoSer.bussTypeAreaBaiShow();
    }

    @Override
    public List<String> findBussType() throws SerException {
        return customerBaseInfoSer.findBussType();
    }

    @Override
    public OptionBO resouceBaiShow() throws SerException {
        return customerBaseInfoSer.resouceBaiShow();
    }

    @Override
    public PieOptionBO resoucePieShowBybussType(String bussType) throws SerException {
        return customerBaseInfoSer.resoucePieShowBybussType(bussType);
    }

    @Override
    public List<CustomerBaseInfoBO> computations() throws SerException {
        return customerBaseInfoSer.computations();
    }

    @Override
    public CustomerInfoBO findByNum(String customerNum) throws SerException {
        return customerBaseInfoSer.findByNum(customerNum);
    }
}