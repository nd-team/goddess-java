package com.bjike.goddess.fundrecords.action.fundrecords;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.bo.BaseParameterBO;
import com.bjike.goddess.financeinit.dto.BaseParameterDTO;
import com.bjike.goddess.financeinit.vo.BaseParameterVO;
import com.bjike.goddess.fundrecords.api.CapitalFlowRecordAPI;
import com.bjike.goddess.fundrecords.dto.CapitalFlowRecordDTO;
import com.bjike.goddess.fundrecords.dto.FundRecordDTO;
import com.bjike.goddess.fundrecords.vo.FundRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 资金流水记录
 *
 * @Author: [xiexiaoting]
 * @Date: [2018-04-03 14:12]
 * @Description: [资金流水记录]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("fundrecord")
public class CapitalFlowRecordAction extends BaseFileAction {

    @Autowired
    private CapitalFlowRecordAPI capitalFlowRecordAPI;


    /**
     * 分页查询资金流水记录列表xiexiaoting
     *
     * @param dto
     * @return class FundRecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/searchByCondition")
    public Result searchByCondition(CapitalFlowRecordDTO dto) throws ActException {
        try {
            List<FundRecordVO> fundVOList = BeanTransform.copyProperties(capitalFlowRecordAPI.searchByCondition(dto, null), FundRecordVO.class);
            return ActResult.initialize(fundVOList);
        } catch (SerException e) {
            e.printStackTrace();
            throw new ActException("资金流水列表查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询资金流水记录总数xiexiaoting
     *
     * @param dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/countRecord")
    public Result countRecord(FundRecordDTO dto) throws ActException {
        try {
            Long count = capitalFlowRecordAPI.countRecord(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            e.printStackTrace();
            throw new ActException("统计资金流水记录数失败: " + e.getMessage());
        }
    }

    /**
     * 处理数据同步记账凭证和资金流水删除地区字段相应数据
     *
     * @param id
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/deleteArea")
    public Result deleteArea(String id) throws ActException {
        try {
            capitalFlowRecordAPI.deleteArea(id);
            return new ActResult("删除成功!");
        } catch (SerException e) {
            e.printStackTrace();
            throw new ActException("删除记录时数据同步失败: " + e.getMessage());
        }
    }

    /**
     * 获取开账时间财务初始化基本参数的账套会计期间启用日期xiexiaoting
     *
     * @param dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/listBasicPara")
    public Result listBasicPara(BaseParameterDTO dto) throws ActException, SerException {
        List<BaseParameterBO> boList = null;

            boList = capitalFlowRecordAPI.listBaseDate(dto);

        List<BaseParameterVO> voList = BeanTransform.copyProperties(boList, BaseParameterBO.class);
        return ActResult.initialize(voList);
    }


}
