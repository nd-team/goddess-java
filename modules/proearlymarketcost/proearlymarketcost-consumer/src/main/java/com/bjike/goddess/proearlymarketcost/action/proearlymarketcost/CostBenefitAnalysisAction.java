package com.bjike.goddess.proearlymarketcost.action.proearlymarketcost;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.proearlymarketcost.api.CostBenefitAnalysisAPI;
import com.bjike.goddess.proearlymarketcost.bo.CostBenefitAnalysisBO;
import com.bjike.goddess.proearlymarketcost.dto.CostBenefitAnalysisDTO;
import com.bjike.goddess.proearlymarketcost.to.CostBenefitAnalysisTO;
import com.bjike.goddess.proearlymarketcost.vo.CostBenefitAnalysisVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 费用效益分析
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-23 03:58 ]
 * @Description: [ 费用效益分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("proearlymarketcost/costbenefitanalysis")
public class CostBenefitAnalysisAction {
    @Autowired
    private CostBenefitAnalysisAPI costBenefitAnalysisAPI;

    /**
     * 添加费用效益分析信息
     *
     * @param costBenefitAnalysisTO 费用效益分析信息
     * @Des 添加费用效益分析信息
     * @return class
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result save(CostBenefitAnalysisTO costBenefitAnalysisTO) throws ActException {
        try {
            CostBenefitAnalysisBO costBenefitAnalysisBO = costBenefitAnalysisAPI.save(costBenefitAnalysisTO);
            return ActResult.initialize(costBenefitAnalysisBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取费用效益分析信息
     *
     * @param dto
     * @return class CostBenefitAnalysisVO
     * @throws ActException
     *
     */
    @GetMapping("v1/list")
    public Result list(CostBenefitAnalysisDTO dto) throws ActException {
        try {
            List<CostBenefitAnalysisVO> costBenefitAnalysisVOs = BeanTransform.copyProperties(
                    costBenefitAnalysisAPI.list(dto),
                    CostBenefitAnalysisVO.class, true);
            return ActResult.initialize(costBenefitAnalysisVOs);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑费用效益分析信息
     * @param costBenefitAnalysisTO 费用效益分析信息
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(CostBenefitAnalysisTO costBenefitAnalysisTO)throws ActException{
        try{
            costBenefitAnalysisAPI.update(costBenefitAnalysisTO);
            return ActResult.initialize("edit success");
        }catch(SerException e){
            throw  new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除费用效益分析
     *
     * @param id 费用效益分析唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete")
    public Result delete(@PathVariable String id)throws ActException{
        try{
           costBenefitAnalysisAPI.remove(id);
            return ActResult.initialize("delete success");
        }catch(SerException e){
            throw new ActException(e.getMessage());
        }

    }
}