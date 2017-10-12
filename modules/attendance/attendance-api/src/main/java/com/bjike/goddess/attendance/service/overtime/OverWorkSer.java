package com.bjike.goddess.attendance.service.overtime;

import com.bjike.goddess.attendance.bo.overtime.AreaBO;
import com.bjike.goddess.attendance.bo.overtime.OverWorkBO;
import com.bjike.goddess.attendance.dto.overtime.OverLongAndRelaxdayDTO;
import com.bjike.goddess.attendance.to.overtime.OverWorkTO;
import com.bjike.goddess.attendance.vo.overtime.OverLongAndRelaxDayVO;
import com.bjike.goddess.attendance.vo.overtime.PositionAndDepartVO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.attendance.entity.overtime.OverWork;
import com.bjike.goddess.attendance.dto.overtime.OverWorkDTO;

import java.util.List;

/**
 * 加班业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OverWorkSer extends Ser<OverWork, OverWorkDTO> {

    /**
     * 加班列表总条数
     */
    default Long countOverWork(OverWorkDTO overWorkDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取加班
     * @return class OverWorkBO
     */
    default OverWorkBO getOneById(String id) throws SerException {return null;}



    /**
     * 加班列表
     *
     * @return class OverWorkBO
     */
    default List<OverWorkBO> listOverWork(OverWorkDTO overWorkDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param overWorkTO 加班信息
     * @return class OverWorkBO
     */
    default OverWorkBO addOverWork(OverWorkTO overWorkTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteOverWork(String id) throws SerException {
        return;
    }

    /**
     * 获取地区
     *
     */
    default List<AreaBO> areaList(   ) throws SerException {
        return null;
    }

    /**
     * 获取任务下达人或加班人员
     *
     */
    default List<String> peopleList(   ) throws SerException {
        return null;
    }



    /**
     * 根据加班人员获取职位和部门
     *
     */
    default PositionAndDepartVO getPositAndDepart(String overWorker   ) throws SerException {
        return null;
    }


    /**
     * 获取加班时长和可休天数
     */
    default OverLongAndRelaxDayVO caculateTime(OverLongAndRelaxdayDTO overLongAndRelaxdayDTO) throws SerException {
        return null;
    }

}