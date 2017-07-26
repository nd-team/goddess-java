package com.bjike.goddess.rentcar.api;


import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.entity.DepartmentDetail;
import com.bjike.goddess.organize.entity.PositionDetail;
import com.bjike.goddess.rentcar.bo.CarSendEmailBO;
import com.bjike.goddess.rentcar.service.CarSendEmailSer;
import com.bjike.goddess.rentcar.to.CarSendEmailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 发送邮件业务实现
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-07-25 09:50 ]
 * @Description: [ 发送邮件业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("carSendEmailApiImpl")
public class CarSendEmailApiImpl implements CarSendEmailAPI {
    @Autowired
    private CarSendEmailSer carSendEmailSer;
    @Override
    public void sendEmail() throws SerException {
        carSendEmailSer.sendEmail();
    }

    @Override
    public List<DepartmentDetail> findDepartMent() throws SerException {
        return carSendEmailSer.findDepartMent();
    }

    @Override
    public List<PositionDetail> findPosition(String id) throws SerException {
        return carSendEmailSer.findPosition(id);
    }

    @Override
    public CarSendEmailBO add(CarSendEmailTO to) throws SerException {
        return carSendEmailSer.add(to);
    }

    @Override
    public List<CarSendEmailBO> list() throws SerException {
        return carSendEmailSer.list();
    }
}