package com.bjike.goddess.checkfunds.api;


import com.bjike.goddess.checkfunds.bo.PassAuditBO;
import com.bjike.goddess.checkfunds.dto.PassAuditDTO;
import com.bjike.goddess.checkfunds.to.PassAuditTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 已完成核对记录业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:18 ]
 * @Description: [ 已完成核对记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PassAuditAPI {
    /**
     * 添加
     *
     * @param to 已完成核对记录
     * @return
     * @throws SerException
     */
    PassAuditBO save(PassAuditTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<PassAuditBO> list(PassAuditDTO dto) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long countNum(PassAuditDTO dto) throws SerException;
}

