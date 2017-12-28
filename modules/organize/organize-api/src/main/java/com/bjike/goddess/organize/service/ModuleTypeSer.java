package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.organize.bo.ModuleTypeBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.dto.ModuleTypeDTO;
import com.bjike.goddess.organize.entity.ModuleType;
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
public interface ModuleTypeSer extends Ser<ModuleType, ModuleTypeDTO> {

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
     * 查询模块类型选项
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findThawOpinion() throws SerException{
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

    /**
     * 获取全部类型选项选项
     *
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findAllOpinion() throws SerException {
        return null;
    }

    /**
     * 根据模块名获取模块的id
     *
     * @return
     * @throws SerException
     */
    default String findModuleId(String moduleName) throws SerException {
        return null;
    }
}