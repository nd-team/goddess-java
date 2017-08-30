package com.bjike.goddess.carinfo.api;

import com.bjike.goddess.carinfo.bo.CarSendEmailBO;
import com.bjike.goddess.carinfo.dto.CarSendEmailDTO;
import com.bjike.goddess.carinfo.entity.CarSendEmail;
import com.bjike.goddess.carinfo.to.CarSendEmailTO;
import com.bjike.goddess.carinfo.to.GuidePermissionTO;
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
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 发送邮箱方法
     * @throws SerException
     */
   void sendEmail() throws SerException;

    /**
     * 获取项目组信息
     * @throws SerException
     */
    default List<DepartmentDetailBO> findDepartMent() throws SerException{
        return null;
    }

    /**
     * 根据部门id来查询该部门所有岗位
     * @param id
     * @throws SerException
     */
    default List<PositionDetailBO> findPosition(String id) throws SerException{
        return null;
    }

    /**
     * 添加岗位人员
     * @param to
     * @throws SerException
     */
    default void add(CarSendEmailTO to) throws SerException{
        return;
    }

    /**
     * 查询发送对象的部门和岗位人员
     * @throws SerException
     */
    default List<CarSendEmailBO> list() throws SerException{
        return null;
    }


    /**
     * 修改岗位人员
     * @param to
     * @throws SerException
     */
    default void edit(CarSendEmailTO to) throws SerException{
        return;
    }

    /**
     * 总条数
     */
    default Long counts(CarSendEmailDTO dto) throws SerException {
        return null;
    }

    /**
     * 删除发送对象
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 根据id来查询单个发送对象所有信息
     * @param id
     * @throws SerException
     */
    CarSendEmailBO findOne(String id) throws SerException;

}