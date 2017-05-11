package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.BeforeAddBO;
import com.bjike.goddess.secure.dto.BeforeAddDTO;
import com.bjike.goddess.secure.to.BeforeAddTO;

import java.util.List;

/**
 * 增员前业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-27 10:10 ]
 * @Description: [ 增员前业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BeforeAddAPI {
    /**
     * 添加
     *
     * @param to 增员前信息
     * @return class BeforeAddBO
     * @throws SerException
     */
    default BeforeAddBO save(BeforeAddTO to) throws SerException {
        return null;
    }

    /**
     * 补全信息和确认是否购买
     *
     * @param to 增员前信息
     * @return class BeforeAddBO
     * @throws SerException
     */
    default BeforeAddBO completeAndConfirm(BeforeAddTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 增员前id
     * @return class BeforeAddBO
     * @throws SerException
     */
    default BeforeAddBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 查找
     *
     * @param dto 增员分页信息
     * @return class BeforeAddBO
     * @throws SerException
     */
    default List<BeforeAddBO> find(BeforeAddDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 增员前id
     * @return class BeforeAddBO
     * @throws SerException
     */
    default BeforeAddBO findByID(String id) throws SerException {
        return null;
    }
}