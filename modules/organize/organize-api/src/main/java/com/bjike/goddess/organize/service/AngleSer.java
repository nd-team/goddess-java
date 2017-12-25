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
     * @param to 角度传输对象
     * @return
     * @throws SerException
     */
    default AngleBO saveAsTo(AngleTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 角度传输对象
     * @throws SerException
     */
    void updateAsTo(AngleTO to) throws SerException;

    /**
     * 删除
     *
     * @param id 角度数据id
     * @return
     * @throws SerException
     */
    default AngleBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 关闭
     *
     * @param id 角度数据id
     * @return
     * @throws SerException
     */
    default AngleBO close(String id) throws SerException {
        return null;
    }

    /**
     * 开启
     *
     * @param id 角度数据id
     * @return
     * @throws SerException
     */
    default AngleBO open(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 角度数据传输
     * @return
     * @throws SerException
     */
    default List<AngleBO> maps(AngleDTO dto) throws SerException {
        return null;
    }


}
