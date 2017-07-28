package com.bjike.goddess.carinfo.api;

import com.bjike.goddess.carinfo.bo.CarSendEmailBO;
import com.bjike.goddess.carinfo.entity.CarSendEmail;
import com.bjike.goddess.carinfo.to.CarSendEmailTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.entity.DepartmentDetail;
import com.bjike.goddess.organize.entity.PositionDetail;

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
public interface CarSendEmailAPI {
    /**
     * 发送邮箱方法
     * @throws SerException
     */
   void sendEmail() throws SerException;

    /**
     * 获取项目组信息
     * @return
     * @throws SerException
     */
    default List<DepartmentDetailBO> findDepartMent() throws SerException{
        return null;
    }

    /**
     * 根据部门id来查询该部门所有岗位
     * @param id
     * @return
     * @throws SerException
     */
    default List<PositionDetailBO> findPosition(String id) throws SerException{
        return null;
    }

    /**
     * 添加项目经理和商务人员
     * @param to
     * @return
     * @throws SerException
     */
    default CarSendEmailBO add(CarSendEmailTO to) throws SerException{
        return null;
    }

    /**
     * 查询发送对象的部门和岗位人员
     * @return
     * @throws SerException
     */
    default List<CarSendEmailBO> list() throws SerException{
        return null;
    }


    /**
     * 修改项目经理和商务人员
     * @param to
     * @return
     * @throws SerException
     */
    default CarSendEmailBO edit(CarSendEmailTO to) throws SerException{
        return null;
    }

}