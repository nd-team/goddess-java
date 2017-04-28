package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.bo.HierarchyBO;
import com.bjike.goddess.organize.dto.HierarchyDTO;
import com.bjike.goddess.organize.entity.Hierarchy;
import com.bjike.goddess.organize.to.HierarchyTO;

import java.util.List;

/**
 * 体系业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface HierarchySer extends Ser<Hierarchy, HierarchyDTO> {

    /**
     * 查询正常状态的体系
     *
     * @return
     * @throws SerException
     */
    default List<HierarchyBO> findStatus() throws SerException {
        return null;
    }

    /**
     * 保存体系
     *
     * @param to 体系传输对象
     * @return
     * @throws SerException
     */
    default HierarchyBO save(HierarchyTO to) throws SerException {
        return null;
    }

    /**
     * 修改体系
     *
     * @param to 体系传输对象
     * @return
     * @throws SerException
     */
    default HierarchyBO update(HierarchyTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 体系数据id
     * @return
     * @throws SerException
     */
    default HierarchyBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id 体系数据id
     * @return
     * @throws SerException
     */
    default HierarchyBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param id 体系数据id
     * @return
     * @throws SerException
     */
    default HierarchyBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 体系数据传输
     * @return
     * @throws SerException
     */
    default List<HierarchyBO> maps(HierarchyDTO dto) throws SerException {
        return null;
    }

}
