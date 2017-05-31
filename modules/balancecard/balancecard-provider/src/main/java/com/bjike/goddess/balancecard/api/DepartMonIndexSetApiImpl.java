package com.bjike.goddess.balancecard.api;

import com.bjike.goddess.balancecard.bo.DepartMonIndexSetBO;
import com.bjike.goddess.balancecard.dto.DepartMonIndexSetDTO;
import com.bjike.goddess.balancecard.service.DepartMonIndexSetSer;
import com.bjike.goddess.balancecard.to.DepartMonIndexSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门月度指标设置业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:33 ]
 * @Description: [ 部门月度指标设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("departMonIndexSetApiImpl")
public class DepartMonIndexSetApiImpl implements DepartMonIndexSetAPI {


    @Autowired
    private DepartMonIndexSetSer departMonIndexSetSer;

    @Override
    public Long countDepartMonIndexSet(DepartMonIndexSetDTO departMonIndexSetDTO) throws SerException {
        return departMonIndexSetSer.countDepartMonIndexSet( departMonIndexSetDTO);
    }

    @Override
    public DepartMonIndexSetBO getOneById(String id) throws SerException {
        return departMonIndexSetSer.getOneById(id);
    }

    @Override
    public List<DepartMonIndexSetBO> listDepartMonIndexSet(DepartMonIndexSetDTO departMonIndexSetDTO) throws SerException {
        return departMonIndexSetSer.listDepartMonIndexSet(departMonIndexSetDTO);
    }

    @Override
    public DepartMonIndexSetBO addDepartMonIndexSet(DepartMonIndexSetTO departMonIndexSetTO) throws SerException {
        return departMonIndexSetSer.addDepartMonIndexSet(departMonIndexSetTO);
    }

    @Override
    public DepartMonIndexSetBO editDepartMonIndexSet(DepartMonIndexSetTO departMonIndexSetTO) throws SerException {
        return departMonIndexSetSer.editDepartMonIndexSet(departMonIndexSetTO);
    }

    @Override
    public void deleteDepartMonIndexSet(String id) throws SerException {
        departMonIndexSetSer.deleteDepartMonIndexSet(id);
    }

    @Override
    public DepartMonIndexSetBO seperateDepartYear(DepartMonIndexSetTO departMonIndexSetTO) throws SerException {
        return departMonIndexSetSer.seperateDepartYear(departMonIndexSetTO);
    }

    @Override
    public Long countNow(DepartMonIndexSetDTO departMonIndexSetDTO) throws SerException {
        return departMonIndexSetSer.countNow(departMonIndexSetDTO);
    }

    @Override
    public List<DepartMonIndexSetBO> listNow(DepartMonIndexSetDTO departMonIndexSetDTO) throws SerException {
        return departMonIndexSetSer.listNow(departMonIndexSetDTO);
    }
}