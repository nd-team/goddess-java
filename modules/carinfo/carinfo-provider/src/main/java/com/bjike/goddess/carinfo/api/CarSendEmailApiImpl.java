package com.bjike.goddess.carinfo.api;

import com.bjike.goddess.carinfo.bo.CarSendEmailBO;
import com.bjike.goddess.carinfo.dto.CarSendEmailDTO;
import com.bjike.goddess.carinfo.service.CarSendEmailSer;
import com.bjike.goddess.carinfo.to.CarSendEmailTO;
import com.bjike.goddess.carinfo.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.entity.PositionDetail;
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
    public Boolean sonPermission() throws SerException {
        return carSendEmailSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return carSendEmailSer.guidePermission(guidePermissionTO);
    }

    @Override
    public void sendEmail() throws SerException {
        carSendEmailSer.sendEmail();
    }

    @Override
    public List<DepartmentDetailBO> findDepartMent() throws SerException {
        return carSendEmailSer.findDepartMent();
    }

    @Override
    public List<PositionDetailBO> findPosition(String id) throws SerException {
        return carSendEmailSer.findPosition(id);
    }

    @Override
    public void add(CarSendEmailTO to) throws SerException {
        carSendEmailSer.add(to);
    }

    @Override
    public List<CarSendEmailBO> list() throws SerException {
        return carSendEmailSer.list();
    }

    @Override
    public void edit(CarSendEmailTO to) throws SerException {
         carSendEmailSer.edit(to);
    }

    @Override
    public Long counts(CarSendEmailDTO dto) throws SerException {
        return carSendEmailSer.counts(dto);
    }

    @Override
    public void delete(String id) throws SerException {
        carSendEmailSer.delete(id);
    }

    @Override
    public CarSendEmailBO findOne(String id) throws SerException {
        return carSendEmailSer.findOne(id);
    }
}