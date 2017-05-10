package com.bjike.goddess.courier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.courier.bo.CourierBO;
import com.bjike.goddess.courier.bo.CourierCountBO;
import com.bjike.goddess.courier.dto.CourierDTO;
import com.bjike.goddess.courier.service.CourierSer;
import com.bjike.goddess.courier.to.CourierTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 快递收发业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-28 10:25 ]
 * @Description: [ 快递收发业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("courierApiImpl")
public class CourierApiImpl implements CourierAPI {
    @Autowired
    private CourierSer courierSer;

    @Override
    public CourierBO save(CourierTO to) throws SerException {
        return courierSer.save(to);
    }

    @Override
    public CourierBO edit(CourierTO to) throws SerException {
        return courierSer.edit(to);
    }

    @Override
    public List<CourierBO> list(CourierDTO dto) throws SerException {
        return courierSer.list(dto);
    }

    @Override
    public void delete(String id) throws SerException {
        courierSer.delete(id);
    }

    @Override
    public CourierBO findByID(String id) throws SerException {
        return courierSer.findByID(id);
    }

    @Override
    public List<String> findNameByDepartment(String[] departmentName) throws SerException {
        return courierSer.findNameByDepartment(departmentName);
    }

    @Override
    public List<String> findNameByGroup(String[] groupName) throws SerException {
        return courierSer.findNameByGroup(groupName);
    }

    @Override
    public Double findRemainSum() throws SerException {
        return courierSer.findRemainSum();
    }

    @Override
    public List<CourierCountBO> dayCount(boolean arrival, String sendTime, boolean courierCompany, boolean department) throws SerException {
        return courierSer.dayCount(arrival, sendTime, courierCompany, department);
    }

    @Override
    public List<CourierCountBO> weekCount(boolean arrival, boolean courierCompany, boolean department,boolean lastWeek) throws SerException {
        return courierSer.weekCount(arrival, courierCompany, department,lastWeek);
    }

    @Override
    public List<CourierCountBO> monthCount(boolean arrival, boolean courierCompany, boolean department, Integer month) throws SerException {
        return courierSer.monthCount(arrival, courierCompany, department, month);
    }

    @Override
    public List<CourierCountBO> yearCount(boolean arrival, boolean courierCompany, boolean department, Integer year) throws SerException {
        return courierSer.yearCount(arrival, courierCompany, department, year);
    }

    @Override
    public Set<String> findAllAreas() throws SerException {
        return courierSer.findAllAreas();
    }

    @Override
    public List<String> findAllNames() throws SerException {
        return courierSer.findAllNames();
    }

    @Override
    public List<String> findAllCompanys() throws SerException {
        return courierSer.findAllCompanys();
    }
}