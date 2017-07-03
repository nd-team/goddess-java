package com.bjike.goddess.reportmanagement.action.reportmanagement;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.api.ProfitIndicatorAdviceAPI;
import com.bjike.goddess.reportmanagement.bo.ProfitIndicatorAdviceBO;
import com.bjike.goddess.reportmanagement.dto.ProfitIndicatorAdviceDTO;
import com.bjike.goddess.reportmanagement.to.ProfitIndicatorAdviceTO;
import com.bjike.goddess.reportmanagement.vo.ProfitIndicatorAdviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 利润分析指标管理建议设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 05:24 ]
 * @Description: [ 利润分析指标管理建议设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("profitindicatoradvice")
public class ProfitIndicatorAdviceAct {
    @Autowired
    private ProfitIndicatorAdviceAPI profitIndicatorAdviceAPI;

    /**
     * 列表
     *
     * @param dto 利润分析指标管理建议设计数据传输
     * @return class ProfitIndicatorAdviceVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProfitIndicatorAdviceDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ProfitIndicatorAdviceBO> list = profitIndicatorAdviceAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProfitIndicatorAdviceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 利润分析指标管理建议设计传输对象
     * @return class ProfitIndicatorAdviceVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ProfitIndicatorAdviceTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ProfitIndicatorAdviceBO bo = profitIndicatorAdviceAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ProfitIndicatorAdviceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 利润分析指标管理建议设计id
     * @return class ProfitIndicatorAdviceVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/profitindicatoradvice/{id}")
    public Result profitindicatoradvice(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ProfitIndicatorAdviceBO bo = profitIndicatorAdviceAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ProfitIndicatorAdviceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 利润分析指标管理建议设计传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ProfitIndicatorAdviceTO to, BindingResult result) throws ActException {
        try {
            profitIndicatorAdviceAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 利润分析指标管理建议设计id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            profitIndicatorAdviceAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 利润分析指标管理建议设计数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProfitIndicatorAdviceDTO dto) throws ActException {
        try {
            return ActResult.initialize(profitIndicatorAdviceAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}