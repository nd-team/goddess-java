package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ArchiveAccessBO;
import com.bjike.goddess.archive.bo.EnclosureTypeBO;
import com.bjike.goddess.archive.dto.EnclosureTypeDTO;
import com.bjike.goddess.archive.entity.EnclosureType;
import com.bjike.goddess.archive.to.EnclosureTypeTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.api.type.Status;

import java.util.List;

/**
 * 附件类型业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 11:08 ]
 * @Description: [ 附件类型业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EnclosureTypeSer extends Ser<EnclosureType, EnclosureTypeDTO> {

    /**
     * 保存
     * @param to 附件类型传输对象
     * @return
     * @throws SerException
     */
    default EnclosureTypeBO save(EnclosureTypeTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     * @param to 附件类型传输对象
     * @return
     * @throws SerException
     */
    default EnclosureTypeBO update(EnclosureTypeTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     * @param id 附件类型数据id
     * @return
     * @throws SerException
     */
    default EnclosureTypeBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 冻结
     * @param id 附件类型数据id
     * @return
     * @throws SerException
     */
    default EnclosureTypeBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     * @param id 附件类型数据id
     * @return
     * @throws SerException
     */
    default EnclosureTypeBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 根据状态查询附件类型数据
     * @param status 状态
     * @return
     * @throws SerException
     */
    default List<EnclosureTypeBO> findByStatus(Status status) throws SerException {
        return null;
    }

    /**
     * 列表
     * @param dto 附件类型数据传输对象
     * @return
     * @throws SerException
     */
    default List<EnclosureTypeBO> maps(EnclosureTypeDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取附件类型数据
     *
     * @param id 附件类型数据id
     * @return
     * @throws SerException
     */
    default EnclosureTypeBO getById(String id) throws SerException {
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
}