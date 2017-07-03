package com.bjike.goddess.reportmanagement.action.reportmanagement;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.api.RepayAnalyzeAdviceAPI;
import com.bjike.goddess.reportmanagement.bo.RepayAnalyzeAdviceBO;
import com.bjike.goddess.reportmanagement.dto.RepayAnalyzeAdviceDTO;
import com.bjike.goddess.reportmanagement.to.RepayAnalyzeAdviceTO;
import com.bjike.goddess.reportmanagement.vo.RepayAnalyzeAdviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 偿还能力分析管理建议设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 04:04 ]
 * @Description: [ 偿还能力分析管理建议设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("repayanalyzeadvice")
public class RepayAnalyzeAdviceAct {
    @Autowired
    private RepayAnalyzeAdviceAPI repayAnalyzeAdviceAPI;

    /**
     * 列表
     *
     * @param dto 偿还能力分析管理建议设计数据传输
     * @return class RepayAnalyzeAdviceVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(RepayAnalyzeAdviceDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<RepayAnalyzeAdviceBO> list = repayAnalyzeAdviceAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, RepayAnalyzeAdviceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 偿还能力分析管理建议设计传输对象
     * @return class RepayAnalyzeAdviceVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) RepayAnalyzeAdviceTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            RepayAnalyzeAdviceBO bo = repayAnalyzeAdviceAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RepayAnalyzeAdviceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 偿还能力分析管理建议设计id
     * @return class RepayAnalyzeAdviceVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/repayanalyzeadvice/{id}")
    public Result repayanalyzeadvice(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            RepayAnalyzeAdviceBO bo = repayAnalyzeAdviceAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RepayAnalyzeAdviceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 偿还能力分析管理建议设计传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) RepayAnalyzeAdviceTO to, BindingResult result) throws ActException {
        try {
            repayAnalyzeAdviceAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 偿还能力分析管理建议设计id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            repayAnalyzeAdviceAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 偿还能力分析管理建议设计数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(RepayAnalyzeAdviceDTO dto) throws ActException {
        try {
            return ActResult.initialize(repayAnalyzeAdviceAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}