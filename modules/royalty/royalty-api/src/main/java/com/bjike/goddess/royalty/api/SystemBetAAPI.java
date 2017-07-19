package com.bjike.goddess.royalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.royalty.bo.SystemBetABO;
import com.bjike.goddess.royalty.dto.SystemBetADTO;
import com.bjike.goddess.royalty.to.SystemBetATO;

import java.util.List;

/**
 * 体系间对赌表A业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 04:22 ]
 * @Description: [ 体系间对赌表A业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SystemBetAAPI {
    /**
     * 体系间对赌表A列表总条数
     */
    default Long count(SystemBetADTO dto) throws SerException {
        return null;
    }

    /**
     * 一个体系间对赌表A
     *
     * @return class SystemBetABO
     */
    default SystemBetABO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 体系间对赌表A
     *
     * @param dto 体系间对赌表A数据dto
     * @return class SystemBetABO
     * @throws SerException
     */
    default List<SystemBetABO> list(SystemBetADTO dto) throws SerException {
        return null;
    }

    /**
     * 添加体系间对赌表A
     *
     * @param systemBetATO 体系间对赌表A数据to
     * @return class SystemBetABO
     * @throws SerException
     */
    default SystemBetABO insert(SystemBetATO systemBetATO) throws SerException {
        return null;
    }

    /**
     * 编辑体系间对赌表A
     *
     * @param systemBetATO 体系间对赌表A数据to
     * @return class SystemBetABO
     * @throws SerException
     */
    default SystemBetABO edit(SystemBetATO systemBetATO) throws SerException {
        return null;
    }

    /**
     * 根据id删除体系间对赌表A
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }

}