package com.bjike.goddess.businsurance.api;

import com.bjike.goddess.businsurance.bo.CarInsureBO;
import com.bjike.goddess.businsurance.dto.CarInsureDTO;
import com.bjike.goddess.businsurance.to.CarInsureTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 车险信息管理业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 11:00 ]
 * @Description: [ 车险信息管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CarInsureAPI {

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
     * 车险信息列表总条数
     *
     */
    default Long countCarInsure(CarInsureDTO carInsureDTO) throws SerException {
        return null;
    }
    /**
     * 车险信息列表
     * @return class CarInsureBO
     */
    default List<CarInsureBO> listCarInsure(CarInsureDTO carInsureDTO) throws SerException {return null;}
    /**
     *  添加
     * @param carInsureTO 车险信息信息
     * @return class CarInsureBO
     */
    default CarInsureBO addCarInsure(CarInsureTO carInsureTO) throws SerException { return null;}

    /**
     *  编辑
     * @param carInsureTO 车险信息信息
     * @return class CarInsureBO
     */
    default CarInsureBO editCarInsure(CarInsureTO carInsureTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteCarInsure(String id ) throws SerException {return;};

    /**
     *  编辑被保险机动车信息
     * @param carInsureTO 车险信息信息
     * @return class CarInsureBO
     */
    default CarInsureBO editCar(CarInsureTO carInsureTO) throws SerException { return null;}

    /**
     *  编辑要约内容
     * @param carInsureTO 车险信息信息
     * @return class CarInsureBO
     */
    default CarInsureBO editContext(CarInsureTO carInsureTO) throws SerException { return null;}
    /**
     *  编辑特别约定
     * @param carInsureTO 车险信息信息
     * @return class CarInsureBO
     */
    default CarInsureBO editAppoint(CarInsureTO carInsureTO) throws SerException { return null;}
    /**
     *  编辑销售机构信息
     * @param carInsureTO 车险信息信息
     * @return class CarInsureBO
     */
    default CarInsureBO editSale(CarInsureTO carInsureTO) throws SerException { return null;}
    /**
     * 查看明细
     * @return class CarInsureBO
     */
    default CarInsureBO getCarInsure(String id ) throws SerException {return null;}


    
}