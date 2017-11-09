package com.bjike.goddess.attendance.api.overtime;

import com.bjike.goddess.attendance.bo.overtime.ExtralOverWorkBO;
import com.bjike.goddess.attendance.dto.overtime.ExtralOverWorkDTO;
import com.bjike.goddess.attendance.dto.overtime.ExtralOverWorkDayDTO;
import com.bjike.goddess.attendance.to.ExtralOverWorkTO;
import com.bjike.goddess.attendance.vo.ExtralOverWorkDayVO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 补班设置业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-12 04:42 ]
 * @Description: [ 补班设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ExtralOverWorkAPI {

    /**
     * 补班列表总条数
     */
    default Long countExtralOverWork(ExtralOverWorkDTO extralOverWorkDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取补班
     *
     * @return class ExtralOverWorkBO
     */
    default ExtralOverWorkBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 补班列表
     *
     * @return class ExtralOverWorkBO
     */
    default List<ExtralOverWorkBO> listExtralOverWork(ExtralOverWorkDTO extralOverWorkDTO) throws SerException {
        return null;
    }

    /**
     * 添补
     *
     * @param extralOverWorkTO 补班信息
     * @return class ExtralOverWorkBO
     */
    default ExtralOverWorkBO addExtralOverWork(ExtralOverWorkTO extralOverWorkTO) throws SerException {
        return null;
    }
    /**
     * 编辑
     *
     * @param extralOverWorkTO 编辑补班信息
     * @return class ExtralOverWorkBO
     */
    default ExtralOverWorkBO editExtralOverWork(ExtralOverWorkTO extralOverWorkTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteExtralOverWork(String id) throws SerException {
        return;
    }


    /**
     * 获取补班天数
     */
    default ExtralOverWorkDayVO caculateTime(ExtralOverWorkDayDTO extralOverWorkDayDTO) throws SerException {
        return null;
    }


}