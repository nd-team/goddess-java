package com.bjike.goddess.enterpriseculturemanage.action.enterpriseculturemanage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.enterpriseculturemanage.api.PeriodicalProgramInfoAPI;
import com.bjike.goddess.enterpriseculturemanage.dto.PeriodicalProgramInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.to.PeriodicalProgramInfoTO;
import com.bjike.goddess.enterpriseculturemanage.vo.EnterpriseCultureInfoVO;
import com.bjike.goddess.enterpriseculturemanage.vo.PeriodicalProgramInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 刊物方案信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-01 09:07 ]
 * @Description: [ 刊物方案信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("enterpriseculturemanage/periodicalprograminfo")
public class PeriodicalProgramInfoAct {

    @Autowired
    private PeriodicalProgramInfoAPI periodicalProgramInfoAPI;

    /**
     * 查询企业文化信息
     *
     * @version v1
     */
    @GetMapping("v1/findInfo")
    public Result findInfo() throws ActException {
        try {
            List<EnterpriseCultureInfoVO> voList = BeanTransform.copyProperties(periodicalProgramInfoAPI.findInfo(), EnterpriseCultureInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增刊物方案信息
     *
     * @param to 刊物方案信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(PeriodicalProgramInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            PeriodicalProgramInfoVO vo = BeanTransform.copyProperties(periodicalProgramInfoAPI.addModel(to), PeriodicalProgramInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑刊物方案信息
     *
     * @param to 刊物方案信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(PeriodicalProgramInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            PeriodicalProgramInfoVO vo = BeanTransform.copyProperties(periodicalProgramInfoAPI.editModel(to), PeriodicalProgramInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核刊物方案信息
     *
     * @param to 刊物方案信息
     * @version v1
     */
    @PostMapping("v1/audit")
    public Result audit(PeriodicalProgramInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            periodicalProgramInfoAPI.audit(to);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除刊物方案信息
     *
     * @param id 刊物方案信息id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            periodicalProgramInfoAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 刊物方案信息分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(PeriodicalProgramInfoDTO dto) throws ActException {
        try {
            List<PeriodicalProgramInfoVO> voList = BeanTransform.copyProperties(periodicalProgramInfoAPI.pageList(dto), PeriodicalProgramInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}