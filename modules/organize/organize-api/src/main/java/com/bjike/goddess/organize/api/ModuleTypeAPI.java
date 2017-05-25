package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.organize.bo.ModuleTypeBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.dto.ModuleTypeDTO;
import com.bjike.goddess.organize.to.ModuleTypeTO;

import java.util.List;

/**
 * 模块类型业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:19 ]
 * @Description: [ 模块类型业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ModuleTypeAPI {

    /**
     * 保存
     *
     * @param to 模块类型传输对象
     * @return
     * @throws SerException
     */
    default ModuleTypeBO save(ModuleTypeTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 模块类型传输对象
     * @return
     * @throws SerException
     */
    default ModuleTypeBO update(ModuleTypeTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 模块类型数据id
     * @return
     * @throws SerException
     */
    default ModuleTypeBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id 模块类型数据id
     * @return
     * @throws SerException
     */
    default ModuleTypeBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param id 模块类型数据id
     * @return
     * @throws SerException
     */
    default ModuleTypeBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 根据状态查询模块类型
     *
     * @param status 状态
     * @return
     * @throws SerException
     */
    default List<ModuleTypeBO> findByStatus(Status status) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 模块类型数据传输对象
     * @return
     * @throws SerException
     */
    default List<ModuleTypeBO> maps(ModuleTypeDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

    /**
     * 根据id查询模块类型数据
     *
     * @param id 模块类型数据id
     * @return
     * @throws SerException
     */
    default ModuleTypeBO findById(String id) throws SerException {
        return null;
    }


    /**
     * 查询未冻结模块选项
     *
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findThawOpinion() throws SerException {
        return null;
    }

    /**
     * 根据id查询模块类型数据
     *
     * @param ids 模块类型数据id数组
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findByIds(String... ids) throws SerException {
        return null;
    }
}