package com.bjike.goddess.courier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.courier.bo.CourierBO;
import com.bjike.goddess.courier.bo.CourierCountBO;
import com.bjike.goddess.courier.dto.CourierDTO;
import com.bjike.goddess.courier.service.CourierSer;
import com.bjike.goddess.courier.to.CourierTO;
import com.bjike.goddess.courier.to.GuidePermissionTO;
import com.bjike.goddess.courier.vo.SonPermissionObject;
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
    public Set<String> findAllAreas() throws SerException {
        return courierSer.findAllAreas();
    }

    @Override
    public List<String> findAllNames() throws SerException {
        return courierSer.findAllNames();
    }

    @Override
    public Double lastCourierSum() throws SerException {
        return courierSer.lastCourierSum();
    }

    @Override
    public List<CourierCountBO> dayCount(CourierDTO dto1) throws SerException {
        return courierSer.dayCount(dto1);
    }

    @Override
    public List<CourierCountBO> weekCount(CourierDTO dto1) throws SerException {
        return courierSer.weekCount(dto1);
    }

    @Override
    public List<CourierCountBO> monthCount(CourierDTO dto1) throws SerException {
        return courierSer.monthCount(dto1);
    }

    @Override
    public List<CourierCountBO> yearCount(CourierDTO dto1) throws SerException {
        return courierSer.yearCount(dto1);
    }

    @Override
    public Set<Integer> allYear() throws SerException {
        return courierSer.allYear();
    }

    @Override
    public Set<Integer> allMonth() throws SerException {
        return courierSer.allMonth();
    }

    @Override
    public Long count(CourierDTO dto) throws SerException {
        return courierSer.count(dto);
    }

    @Override
    public void send() throws SerException {
        courierSer.send();
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return courierSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return courierSer.guidePermission(guidePermissionTO);
    }
}