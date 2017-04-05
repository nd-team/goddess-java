package com.bjike.goddess.businessevaluate.action.abilitygrowup;

import com.bjike.goddess.businessevaluate.api.AbilityGrowUpAPI;
import com.bjike.goddess.businessevaluate.dto.AbilityGrowUpDTO;
import com.bjike.goddess.businessevaluate.to.AbilityGrowUpTO;
import com.bjike.goddess.businessevaluate.vo.AbilityGrowUpVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目能力成长
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 04:13 ]
 * @Description: [ 项目能力成长 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessevaluate/abilitygrowup")
public class AbilityGrowUpAct {

    @Autowired
    private AbilityGrowUpAPI abilityGrowUpAPI;

    /**
     * 新增能力成长
     *
     * @param to 能力成长
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(AbilityGrowUpTO to, BindingResult bindingResult) throws ActException {
        try {
            AbilityGrowUpVO vo = BeanTransform.copyProperties(abilityGrowUpAPI.addModel(to), AbilityGrowUpVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑能力成长
     *
     * @param to 能力成长
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(AbilityGrowUpTO to, BindingResult bindingResult) throws ActException {
        try {
            AbilityGrowUpVO vo = BeanTransform.copyProperties(abilityGrowUpAPI.editModel(to), AbilityGrowUpVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除能力成长
     *
     * @param id 能力成长id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            abilityGrowUpAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result delete(AbilityGrowUpDTO dto) throws ActException {
        try {
            List<AbilityGrowUpVO> voList = BeanTransform.copyProperties(abilityGrowUpAPI.pageList(dto), AbilityGrowUpVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}