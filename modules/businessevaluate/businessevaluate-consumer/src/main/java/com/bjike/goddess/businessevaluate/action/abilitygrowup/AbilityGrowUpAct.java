package com.bjike.goddess.businessevaluate.action.abilitygrowup;

import com.bjike.goddess.businessevaluate.api.AbilityGrowUpAPI;
import com.bjike.goddess.businessevaluate.api.EvaluateProjectInfoAPI;
import com.bjike.goddess.businessevaluate.dto.AbilityGrowUpDTO;
import com.bjike.goddess.businessevaluate.to.AbilityGrowUpTO;
import com.bjike.goddess.businessevaluate.vo.AbilityGrowUpVO;
import com.bjike.goddess.businessevaluate.vo.EvaluateProjectInfoVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("abilitygrowup")
public class AbilityGrowUpAct {

    @Autowired
    private AbilityGrowUpAPI abilityGrowUpAPI;

    @Autowired
    private EvaluateProjectInfoAPI evaluateProjectInfoAPI;

    /**
     * 查询所有项目
     *
     * @return class EvaluateProjectInfoVO
     * @version v1
     */
    @GetMapping("v1/porjects")
    public Result porjects(HttpServletRequest request) throws ActException {
        try {
            List<EvaluateProjectInfoVO> voList = BeanTransform.copyProperties(evaluateProjectInfoAPI.findAll(), EvaluateProjectInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AbilityGrowUpDTO dto) throws ActException {
        try {
            Long count = abilityGrowUpAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询能力成长
     *
     * @param id 能力成长id
     * @return class AbilityGrowUpVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AbilityGrowUpVO vo = BeanTransform.copyProperties(abilityGrowUpAPI.findById(id), AbilityGrowUpVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增能力成长
     *
     * @param to 能力成长
     * @return class AbilityGrowUpVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) AbilityGrowUpTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            AbilityGrowUpVO vo = BeanTransform.copyProperties(abilityGrowUpAPI.addModel(to), AbilityGrowUpVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑能力成长
     *
     * @param to 能力成长
     * @return class AbilityGrowUpVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) AbilityGrowUpTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            AbilityGrowUpVO vo = BeanTransform.copyProperties(abilityGrowUpAPI.editModel(to), AbilityGrowUpVO.class, request);
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
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
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
     * @return class AbilityGrowUpVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(AbilityGrowUpDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<AbilityGrowUpVO> voList = BeanTransform.copyProperties(abilityGrowUpAPI.pageList(dto), AbilityGrowUpVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}