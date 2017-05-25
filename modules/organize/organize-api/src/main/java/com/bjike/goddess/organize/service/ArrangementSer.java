package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.bo.ArrangementBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.dto.ArrangementDTO;
import com.bjike.goddess.organize.entity.Arrangement;
import com.bjike.goddess.organize.enums.ArrangementType;
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
     * @param to 岗位层级传输对象
     * @return
     */
    default ArrangementBO save(ArrangementTO to) throws SerException {
        return null;
    }

    /**
     * 修改岗位层级
     *
     * @param to 岗位层级传输对象
     * @return
     */
    default ArrangementBO update(ArrangementTO to) throws SerException {
        return null;
    }

    /**
     * 查询下级岗位层次
     *
     * @param id 岗位层级数据id
     * @return
     * @throws SerException
     */
    default List<ArrangementBO> findChild(String id) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 岗位层级下级id
     * @return
     * @throws SerException
     */
    default ArrangementBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 岗位层级数据传输
     * @return
     * @throws SerException
     */
    default List<ArrangementBO> maps(ArrangementDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取岗位层级所在层次
     *
     * @param id 岗位层级数据id
     * @return
     * @throws SerException
     */
    default ArrangementType getLayer(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取岗位层级数据
     *
     * @param id 岗位层级数据id
     * @return
     * @throws SerException
     */
    default ArrangementBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取岗位层级选线
     *
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findThawOpinion() throws SerException {
        return null;
    }


    /**
     * 根据id查询岗位层级数据
     *
     * @param ids 岗位层级数据id数组
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findByIds(String... ids) throws SerException {
        return null;
    }

    /**
     * 获取全部岗位层级选项
     *
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findAllOpinion() throws SerException {
        return null;
    }
}
