package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.AttainmentWayBO;
import com.bjike.goddess.attainment.to.AttainmentWayTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 调研方式业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:51 ]
 * @Description: [ 调研方式业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AttainmentWayAPI {

    /**
     * 保存
     *
     * @param to 调研方式传输对象
     * @return
     * @throws SerException
     */
    default AttainmentWayBO save(AttainmentWayTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 调研方式传输对象
     * @return
     * @throws SerException
     */
    default AttainmentWayBO update(AttainmentWayTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 调研方式数据id
     * @return
     * @throws SerException
     */
    default AttainmentWayBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id 调研方式数据id
     * @return
     * @throws SerException
     */
    default AttainmentWayBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param id 调研方式数据id
     * @return
     * @throws SerException
     */
    default AttainmentWayBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 查询未冻结数据
     *
     * @return
     * @throws SerException
     */
    default List<AttainmentWayBO> findThaw() throws SerException {
        return null;
    }


}