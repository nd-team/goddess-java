package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.ShareChangeBO;
import com.bjike.goddess.shareholdersmanage.dto.ShareChangeDTO;
import com.bjike.goddess.shareholdersmanage.to.ShareChangeTO;

import java.util.List;

/**
 * 股东变更业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 03:37 ]
 * @Description: [ 股东变更业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ShareChangeAPI {
    /**
     * 股东变更列表总条数
     */
    default Long countShareChange(ShareChangeDTO shareChangeDTO) throws SerException {
        return null;
    }

    /**
     * 一个股东变更
     *
     * @return class ShareChangeBO
     */
    default ShareChangeBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 股东变更列表
     *
     * @param shareChangeDTO 股东变更dto
     * @return class ShareChangeBO
     * @throws SerException
     */
    default List<ShareChangeBO> findList(ShareChangeDTO shareChangeDTO) throws SerException {
        return null;
    }

    /**
     * 股东变更添加
     *
     * @param shareChangeTO 股东变更数据to
     * @throws SerException
     */
    default ShareChangeBO save(ShareChangeTO shareChangeTO) throws SerException {
        return null;
    }

    /**
     * 股东变更编辑
     *
     * @param shareChangeTO 股权交易记录详情数据to
     * @throws SerException
     */
    default ShareChangeBO edit(ShareChangeTO shareChangeTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除股东变更
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }
}