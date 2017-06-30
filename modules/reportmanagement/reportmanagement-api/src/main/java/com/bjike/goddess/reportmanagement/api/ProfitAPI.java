package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.*;
import com.bjike.goddess.reportmanagement.dto.AssetDTO;
import com.bjike.goddess.reportmanagement.dto.ProfitDTO;
import com.bjike.goddess.reportmanagement.to.ProfitTO;

import java.util.List;

/**
 * 利润表业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-22 06:03 ]
 * @Description: [ 利润表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProfitAPI {
    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    ProfitBO save(ProfitTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ProfitBO> list(ProfitDTO dto) throws SerException;

    /**
     * 水平分析
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ProfitLevelBO> levelAnalyze(ProfitDTO dto) throws SerException;

    /**
     * 垂直分析
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ProfitVerticalBO> verticalAnalyze(ProfitDTO dto) throws SerException;

    /**
     * 分析指标
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ProfitAnalyzeIndicatorBO> analyzeIndicator(ProfitDTO dto) throws SerException;

    /**
     * 查看金额明细
     *
     * @param id
     * @param dto
     * @return
     * @throws SerException
     */
    List<DetailBO> findDetails(String id, AssetDTO dto) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    ProfitBO findByID(String id) throws SerException;
}