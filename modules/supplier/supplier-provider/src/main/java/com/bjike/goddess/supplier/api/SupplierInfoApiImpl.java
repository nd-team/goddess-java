package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.supplier.bo.OptionBO;
import com.bjike.goddess.supplier.bo.SummationBO;
import com.bjike.goddess.supplier.bo.SupplierInfoBO;
import com.bjike.goddess.supplier.bo.SupplierInfoRegistraDataBO;
import com.bjike.goddess.supplier.dto.SupplierInfoDTO;
import com.bjike.goddess.supplier.service.SupplierInfoSer;
import com.bjike.goddess.supplier.to.GuidePermissionTO;
import com.bjike.goddess.supplier.to.SupplierInfoRegistraDataTO;
import com.bjike.goddess.supplier.to.SupplierInfoTO;
import com.bjike.goddess.supplier.vo.SonPermissionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应商信息管理业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 10:33 ]
 * @Description: [ 供应商信息管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("supplierInfoApiImpl")
public class SupplierInfoApiImpl implements SupplierInfoAPI {
    @Autowired
    private SupplierInfoSer supplierInfoSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return supplierInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return supplierInfoSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countSupplierInfo(SupplierInfoDTO supplierInfoDTO) throws SerException {
        return supplierInfoSer.countSupplierInfo(supplierInfoDTO);
    }

    @Override
    public SupplierInfoBO getOneById(String id) throws SerException {
        return supplierInfoSer.getOneById(id);
    }

    @Override
    public List<SupplierInfoBO> listSupplierInfo(SupplierInfoDTO headersCustomDTO) throws SerException {
        return supplierInfoSer.listSupplierInfo(headersCustomDTO);
    }

    @Override
    public SupplierInfoBO addSupplierInfo(SupplierInfoTO supplierInfoTO) throws SerException {
        return supplierInfoSer.addSupplierInfo(supplierInfoTO);
    }

    @Override
    public SupplierInfoBO editSupplierInfo(SupplierInfoTO supplierInfoTO) throws SerException {
        return supplierInfoSer.editSupplierInfo(supplierInfoTO);
    }

    @Override
    public void deleteSupplierInfo(String id) throws SerException {
        supplierInfoSer.deleteSupplierInfo(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return supplierInfoSer.exportExcel();
    }

    @Override
    public byte[] templateExport() throws SerException {
        return supplierInfoSer.templateExport();
    }

    @Override
    public void importExcel(List<SupplierInfoTO> supplierInfoTOList) throws SerException {
        supplierInfoSer.importExcel(supplierInfoTOList);
    }

    @Override
    public SupplierInfoRegistraDataBO linkSupplierData(String id) throws SerException {
       return supplierInfoSer.linkSupplierData(id);
    }

    @Override
    public void addSupplierDetail(SupplierInfoRegistraDataTO supplierInfoRegistraDataTO) throws SerException {
        supplierInfoSer.addSupplierDetail(supplierInfoRegistraDataTO);
    }

    @Override
    public List<SummationBO> summaDay(String summDate) throws SerException {
        return supplierInfoSer.summaDay(summDate);
    }

    @Override
    public List<SummationBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
        return supplierInfoSer.summaWeek(year,month,week);
    }

    @Override
    public List<SummationBO> summaMonth(Integer year, Integer month) throws SerException {
        return supplierInfoSer.summaMonth(year,month);
    }

    @Override
    public List<SummationBO> summaQuarter(Integer year, Integer quarter) throws SerException {
        return supplierInfoSer.summaQuarter(year,quarter);
    }

    @Override
    public List<SummationBO> summaYear(Integer year) throws SerException {
        return supplierInfoSer.summaYear(year);
    }

    @Override
    public List<SummationBO> summaTotal(String endDate) throws SerException {
        return supplierInfoSer.summaTotal(endDate);
    }

    @Override
    public OptionBO figureShowDay(String summDate) throws SerException {
        return supplierInfoSer.figureShowDay(summDate);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        return supplierInfoSer.figureShowWeek(year,month,week);
    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        return supplierInfoSer.figureShowMonth(year,month);
    }

    @Override
    public OptionBO figureShowQuarter(Integer year, Integer quarter) throws SerException {
        return supplierInfoSer.figureShowQuarter(year,quarter);
    }

    @Override
    public OptionBO figureShowYear(Integer year) throws SerException {
        return supplierInfoSer.figureShowYear(year);
    }

    @Override
    public OptionBO figureShowTotal(String endDate) throws SerException {
        return supplierInfoSer.figureShowTotal(endDate);
    }
}