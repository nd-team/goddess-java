package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.ArrangementBO;
import com.bjike.goddess.organize.to.ArrangementTO;

import java.util.List;

/**
 * 岗位层级业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ArrangementAPI {

    /**
     * 查询正常状态的岗位层级
     *
     * @return
     * @throws SerException
     */
    default List<ArrangementBO> findStatus() throws SerException {
        return null;
    }

    /**
     * 保存岗位层级
     *
     * @param to
     * @return
     */
    default ArrangementBO save(ArrangementTO to) throws SerException {
        return null;
    }

    /**
     * 修改岗位层级
     *
     * @param to
     * @return
     */
    default ArrangementBO update(ArrangementTO to) throws SerException {
        return null;
    }

    /**
     * 根据ID查询数据
     *
     * @param id
     * @return
     */
    default ArrangementBO findById(String id) throws SerException {
        return null;
    }


    /**
     * 查询下级岗位层次
     *
     * @param id
     * @return
     * @throws SerException
     */
    default List<ArrangementBO> findChild(String id) throws SerException {
        return null;
    }
}
