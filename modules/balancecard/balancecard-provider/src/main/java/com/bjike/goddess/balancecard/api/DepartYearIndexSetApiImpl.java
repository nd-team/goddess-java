package com.bjike.goddess.balancecard.api;

import com.bjike.goddess.balancecard.bo.DepartYearIndexSetBO;
import com.bjike.goddess.balancecard.dto.DepartYearIndexSetDTO;
import com.bjike.goddess.balancecard.service.DepartYearIndexSetSer;
import com.bjike.goddess.balancecard.to.DepartYearIndexSetTO;
import com.bjike.goddess.balancecard.to.ExportExcelDepartTO;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门年度指标设置业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:28 ]
 * @Description: [ 部门年度指标设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("departYearIndexSetApiImpl")
public class DepartYearIndexSetApiImpl implements DepartYearIndexSetAPI {


    @Autowired
    private DepartYearIndexSetSer departYearIndexSetSer;

    @Override
    public Long countDepartYearIndexSet(DepartYearIndexSetDTO departYearIndexSetDTO) throws SerException {
        return departYearIndexSetSer.countDepartYearIndexSet( departYearIndexSetDTO);
    }

    @Override
    public DepartYearIndexSetBO getOneById(String id) throws SerException {
        return departYearIndexSetSer.getOneById(id);
    }

    @Override
    public List<DepartYearIndexSetBO> listDepartYearIndexSet(DepartYearIndexSetDTO departYearIndexSetDTO) throws SerException {
        return departYearIndexSetSer.listDepartYearIndexSet(departYearIndexSetDTO);
    }

    @Override
    public DepartYearIndexSetBO addDepartYearIndexSet(DepartYearIndexSetTO departYearIndexSetTO) throws SerException {
        return departYearIndexSetSer.addDepartYearIndexSet(departYearIndexSetTO);
    }

    @Override
    public DepartYearIndexSetBO editDepartYearIndexSet(DepartYearIndexSetTO departYearIndexSetTO) throws SerException {
        return departYearIndexSetSer.editDepartYearIndexSet(departYearIndexSetTO);
    }

    @Override
    public void deleteDepartYearIndexSet(String id) throws SerException {
        departYearIndexSetSer.deleteDepartYearIndexSet(id);
    }

    @Override
    public DepartYearIndexSetBO seperateDepartYear(DepartYearIndexSetTO departYearIndexSetTO) throws SerException {
        return departYearIndexSetSer.seperateDepartYear(departYearIndexSetTO);
    }

    @Override
    public byte[] departYearReport(ExportExcelDepartTO to) throws SerException {
        return departYearIndexSetSer.departYearReport(to);
    }

    @Override
    public List<String> listArea() throws SerException {
        return departYearIndexSetSer.listArea();
    }

    @Override
    public List<String> listDepart() throws SerException {
        return departYearIndexSetSer.listDepart();
    }

    @Override
    public List<String> listEmp() throws SerException {
        return departYearIndexSetSer.listEmp();
    }

    public DepartYearIndexSetApiImpl() {
        super();
    }

    @Override
    public void leadExcel(List<DepartYearIndexSetTO> toList) throws SerException {
         departYearIndexSetSer.leadExcel(toList);
    }

    @Override
    public byte[] exportExcel(ExportExcelDepartTO to) throws SerException {
        return departYearIndexSetSer.exportExcel(to);
    }

    @Override
    public List<DepartYearIndexSetBO> dendrogram(String id) throws SerException {
        return departYearIndexSetSer.dendrogram(id);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return departYearIndexSetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return departYearIndexSetSer.guidePermission(guidePermissionTO);
    }
}
