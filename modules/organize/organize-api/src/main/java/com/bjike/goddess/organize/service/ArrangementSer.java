package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.bo.ArrangementBO;
import com.bjike.goddess.organize.dto.ArrangementDTO;
import com.bjike.goddess.organize.entity.Arrangement;
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
public interface ArrangementSer extends Ser<Arrangement, ArrangementDTO> {

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
