package com.bjike.goddess.fundrecords.action.fundrecords;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
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
     * 获取填充资金流水列表
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/listRecord")
    public Result listRecord() throws ActException {
        try {
            capitalFlowRecordAPI.deleteFundRecord(); // 更新数据前删除表数据
            capitalFlowRecordAPI.generateData(); // 更新第一条数据
            capitalFlowRecordAPI.listRecord();

            return new ActResult("数据更新成功");
        } catch (SerException e) {
            e.printStackTrace();
            throw new ActException("数据更新时产生问题: " + e.getMessage());
        }
    }

    /**
     * 分页查询资金流水记录列表
     *
     * @param dto
     * @param fundRecordDTO
     * @return class FundRecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/searchByCondition")
    public Result searchByCondition(CapitalFlowRecordDTO dto, FundRecordDTO fundRecordDTO) throws ActException {
        try {
            List<FundRecordVO> fundVOList = BeanTransform.copyProperties(capitalFlowRecordAPI.searchByCondition(dto, fundRecordDTO), FundRecordVO.class);
            return ActResult.initialize(fundVOList);
        } catch (SerException e) {
            e.printStackTrace();
            throw new ActException("资金流水列表查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询资金流水记录总数
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


}
