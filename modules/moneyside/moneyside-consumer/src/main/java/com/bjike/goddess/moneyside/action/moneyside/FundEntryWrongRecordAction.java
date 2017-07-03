package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.FundEntryWrongRecordAPI;
import com.bjike.goddess.moneyside.bo.FundEntryConfirmedBO;
import com.bjike.goddess.moneyside.bo.FundEntryWrongRecordBO;
import com.bjike.goddess.moneyside.dto.FundEntryConfirmedDTO;
import com.bjike.goddess.moneyside.dto.FundEntryWrongRecordDTO;
import com.bjike.goddess.moneyside.vo.FundEntryConfirmedVO;
import com.bjike.goddess.moneyside.vo.FundEntryWrongRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 资金进入申请有误记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:07 ]
 * @Description: [ 资金进入申请有误记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("fundentrywrongrecord")
public class FundEntryWrongRecordAction {
    @Autowired
    private FundEntryWrongRecordAPI fundEntryWrongRecordAPI;

    /**
     * 资金进入申请有误记录列表总条数
     *
     * @param fundEntryWrongRecordDTO 资金进入申请有误记录dto
     * @des 获取所有资金进入申请有误记录总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FundEntryWrongRecordDTO fundEntryWrongRecordDTO) throws ActException {
        try {
            Long count = fundEntryWrongRecordAPI.countFundEntryWrongRecord(fundEntryWrongRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个资金进入申请有误记录
     *
     * @param id
     * @return class FundEntryWrongRecordVO
     * @des 获取一个资金进入申请有误记录
     * @version v1
     */
    @GetMapping("v1/wrong/{id}")
    public Result wrong(@PathVariable String id) throws ActException {
        try {
            FundEntryWrongRecordBO fundEntryWrongRecordBO = fundEntryWrongRecordAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(fundEntryWrongRecordBO, FundEntryWrongRecordVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 资金进入申请有误记录列表
     *
     * @param fundEntryWrongRecordDTO 资金进入申请有误记录dto
     * @return class FundEntryWrongRecordVO
     * @des 获取所有资金进入申请有误记录
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FundEntryWrongRecordDTO fundEntryWrongRecordDTO, HttpServletRequest request) throws ActException {
        try {
            List<FundEntryWrongRecordVO> fundEntryWrongRecordVOS = BeanTransform.copyProperties
                    (fundEntryWrongRecordAPI.findListFundEntryWrongRecord(fundEntryWrongRecordDTO), FundEntryWrongRecordVO.class, request);
            return ActResult.initialize(fundEntryWrongRecordVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 删除资金进入申请有误记录
     *
     * @param id 用户id
     * @des 根据用户id删除资金进入申请有误记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            fundEntryWrongRecordAPI.removeFundEntryWrongRecord(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}