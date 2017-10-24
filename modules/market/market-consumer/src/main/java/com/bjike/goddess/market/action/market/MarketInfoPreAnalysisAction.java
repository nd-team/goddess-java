package com.bjike.goddess.market.action.market;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.market.api.MarketInfoPreAnalysisAPI;
import com.bjike.goddess.market.bo.MarketInfoPreAnalysisBO;
import com.bjike.goddess.market.dto.MarketInfoPreAnalysisDTO;
import com.bjike.goddess.market.to.GuidePermissionTO;
import com.bjike.goddess.market.to.MarketInfoPreAnalysisTO;
import com.bjike.goddess.market.vo.MarketInfoPreAnalysisVO;
import com.bjike.goddess.market.vo.MarketInfoRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 市场信息初步分析
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-20 01:36 ]
 * @Description: [ 市场信息初步分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("marketinfopreanalysis")
public class MarketInfoPreAnalysisAction {
    @Autowired
    private MarketInfoPreAnalysisAPI marketInfoPreAnalysisAPI;
    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = marketInfoPreAnalysisAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 市场信息初步分析列表总条数
     *
     * @param marketInfoPreAnalysisDTO 市场信息初步分析dto
     * @des 获取所有市场信息初步分析总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(MarketInfoPreAnalysisDTO marketInfoPreAnalysisDTO) throws ActException {
        try {
            Long count = marketInfoPreAnalysisAPI.countAnalysis(marketInfoPreAnalysisDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个市场信息初步分析
     *
     * @param id
     * @return class MarketInfoPreAnalysisVO
     * @des 获取一个市场信息初步分析
     * @version v1
     */
    @GetMapping("v1/market/{id}")
    public Result market(@PathVariable String id) throws ActException {
        try {
            MarketInfoPreAnalysisBO marketInfoPreAnalysisBO = marketInfoPreAnalysisAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(marketInfoPreAnalysisBO, MarketInfoPreAnalysisVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场信息初步分析列表
     *
     * @param marketInfoPreAnalysisDTO 市场信息初步分析dto
     * @return class MarketInfoPreAnalysisVO
     * @des 获取所有市场信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(MarketInfoPreAnalysisDTO marketInfoPreAnalysisDTO, HttpServletRequest request) throws ActException {
        try {
            List<MarketInfoRecordVO> marketInfoVOS = BeanTransform.copyProperties
                    (marketInfoPreAnalysisAPI.findListAnalysis(marketInfoPreAnalysisDTO), MarketInfoPreAnalysisVO.class, request);
            return ActResult.initialize(marketInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场信息初步分析
     *
     * @param marketInfoPreAnalysisTO 市场信息初步分析数据to
     * @des 市场信息初步分析
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/analysis")
    public Result analysis(@Validated(MarketInfoPreAnalysisTO.testAnalysis.class) MarketInfoPreAnalysisTO marketInfoPreAnalysisTO, BindingResult bindingResult) throws ActException {
        try {
            marketInfoPreAnalysisAPI.analysis(marketInfoPreAnalysisTO);
            return new ActResult(" analysis success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 预算模块初步分析
     *
     * @param marketInfoPreAnalysisTO 市场信息初步分析数据to
     * @des 预算模块初步分析
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/budgetAnalysis")
    public Result budgetAnalysis(@Validated(MarketInfoPreAnalysisTO.budgetAnalysis.class) MarketInfoPreAnalysisTO marketInfoPreAnalysisTO, BindingResult bindingResult) throws ActException {
        try {
            marketInfoPreAnalysisAPI.budgetAnalysis(marketInfoPreAnalysisTO);
            return new ActResult(" budgetAnalysis success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 规划模块初步分析
     *
     * @param marketInfoPreAnalysisTO 市场信息初步分析数据to
     * @des 预算模块初步分析
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/planAnalysis")
    public Result planAnalysis(@Validated(MarketInfoPreAnalysisTO.planAnalysis.class) MarketInfoPreAnalysisTO marketInfoPreAnalysisTO, BindingResult bindingResult) throws ActException {
        try {
            marketInfoPreAnalysisAPI.planAnalysis(marketInfoPreAnalysisTO);
            return new ActResult(" planAnalysis success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除市场信息初步分析
     *
     * @param id 用户id
     * @des 根据用户id删除市场信息初步分析
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            marketInfoPreAnalysisAPI.removeAnalysis(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}