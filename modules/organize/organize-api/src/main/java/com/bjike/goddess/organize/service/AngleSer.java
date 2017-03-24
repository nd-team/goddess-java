package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.bo.AngleBO;
import com.bjike.goddess.organize.dto.AngleDTO;
import com.bjike.goddess.organize.entity.Angle;
import com.bjike.goddess.organize.to.AngleTO;

import java.util.List;

/**
 * 角度业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:08]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface AngleSer extends Ser<Angle, AngleDTO> {

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

}
