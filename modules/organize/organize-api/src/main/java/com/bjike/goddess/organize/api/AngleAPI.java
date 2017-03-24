package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.AngleBO;
import com.bjike.goddess.organize.dto.AngleDTO;
import com.bjike.goddess.organize.to.AngleTO;

import java.util.List;

/**
 * 对外角度业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:08]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface AngleAPI {

    /**
     * 查询未冻结数据
     *
     * @return
     * @throws SerException
     */
    default List<AngleBO> findStatus() throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    default AngleBO saveAsTo(AngleTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to
     * @throws SerException
     */
    void updateAsTo(AngleTO to) throws SerException;

    /**
     * 根据ID查询角度
     *
     * @param id
     * @return
     * @throws SerException
     */
    default AngleBO findById(String id) throws SerException {
        return null;
    }

    /**
     * 根据查询条件查询角度
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default AngleBO findOne(AngleDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据条件查询角度
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<AngleBO> findByCis(AngleDTO dto) throws SerException {
        return null;
    }

}
