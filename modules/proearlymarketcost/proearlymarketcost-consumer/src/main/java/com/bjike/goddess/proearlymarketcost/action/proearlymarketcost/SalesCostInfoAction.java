package com.bjike.goddess.proearlymarketcost.action.proearlymarketcost;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.proearlymarketcost.api.SalesCostInfoAPI;
import com.bjike.goddess.proearlymarketcost.bo.SalesCostInfoBO;
import com.bjike.goddess.proearlymarketcost.dto.SalesCostInfoDTO;
import com.bjike.goddess.proearlymarketcost.to.SalesCostInfoTO;
import com.bjike.goddess.proearlymarketcost.vo.SalesCostInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 销售费用信息
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-23 03:36 ]
 * @Description: [ 销售费用信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("proearlymarketcost/salescostinfo")
public class SalesCostInfoAction {
    @Autowired
    private SalesCostInfoAPI salesCostInfoAPI;

    /**
     * 添加销售费用信息
     *
     * @param salesCostInfoTO 销售费用信息
     * @des 添加销售费用信息
     * @return class SalesCostInfoVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(SalesCostInfoTO salesCostInfoTO) throws ActException {
        try {
            SalesCostInfoBO salesCostInfoBO = salesCostInfoAPI.save(salesCostInfoTO);
            SalesCostInfoVO salesCostInfoVO = BeanTransform.copyProperties(salesCostInfoBO,SalesCostInfoVO.class,true);
            return ActResult.initialize(salesCostInfoVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取销售费用信息
     * @return class SalesCostInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(SalesCostInfoDTO salesCostInfoDTO) throws ActException {
        try {

            List<SalesCostInfoBO> salesCostInfoBOs = salesCostInfoAPI.list(salesCostInfoDTO);
            List<SalesCostInfoVO> salesCostInfoVOs = BeanTransform.copyProperties(
                    salesCostInfoBOs, SalesCostInfoVO.class, true);
            return ActResult.initialize(salesCostInfoVOs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑销售费用信息
     *
     * @param salesCostInfoTO 销售费用信息
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(SalesCostInfoTO salesCostInfoTO) throws ActException {
        try {
            salesCostInfoAPI.update(salesCostInfoTO);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除销售费用信息
     *
     * @param id 销售信息唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            salesCostInfoAPI.remove(id);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /** 汇总
     * @param salesCostInfoBO
     * @return
     * @throws ActException
     */
    @GetMapping("v1/collect")
    public Result collect(SalesCostInfoBO salesCostInfoBO)throws ActException{
        try{
            List<SalesCostInfoBO> salesCostInfoBOs = salesCostInfoAPI.collect(salesCostInfoBO);
            List<SalesCostInfoVO> salesCostInfoVOs = BeanTransform.copyProperties(
                    salesCostInfoBOs,SalesCostInfoVO.class,true);
            return ActResult.initialize(salesCostInfoVOs);
        }catch(SerException e){
            throw new ActException(e.getMessage());
        }
    }
}