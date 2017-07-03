package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.RepayAnalyzeAdviceBO;
import com.bjike.goddess.reportmanagement.dto.RepayAnalyzeAdviceDTO;
import com.bjike.goddess.reportmanagement.to.RepayAnalyzeAdviceTO;

import java.util.List;

/**
 * 偿还能力分析管理建议设计业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 04:04 ]
 * @Description: [ 偿还能力分析管理建议设计业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RepayAnalyzeAdviceAPI {
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<RepayAnalyzeAdviceBO> list(RepayAnalyzeAdviceDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    RepayAnalyzeAdviceBO save(RepayAnalyzeAdviceTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(RepayAnalyzeAdviceTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    RepayAnalyzeAdviceBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(RepayAnalyzeAdviceDTO dto) throws SerException;
}