package com.bjike.goddess.reportmanagement.action.reportmanagement;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.api.DebtStructureAdviceAPI;
import com.bjike.goddess.reportmanagement.bo.DebtStructureAdviceBO;
import com.bjike.goddess.reportmanagement.dto.DebtStructureAdviceDTO;
import com.bjike.goddess.reportmanagement.to.DebtStructureAdviceTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.vo.DebtStructureAdviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 负债与权益结构管理建议设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 04:56 ]
 * @Description: [ 负债与权益结构管理建议设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("debtstructureadvice")
public class DebtStructureAdviceAct {
    @Autowired
    private DebtStructureAdviceAPI debtStructureAdviceAPI;

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

            Boolean isHasPermission = debtStructureAdviceAPI.guidePermission(guidePermissionTO);
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
     * 列表
     *
     * @param dto 负债与权益结构管理建议设计数据传输
     * @return class DebtStructureAdviceVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(DebtStructureAdviceDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<DebtStructureAdviceBO> list = debtStructureAdviceAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, DebtStructureAdviceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 负债与权益结构管理建议设计传输对象
     * @return class DebtStructureAdviceVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) DebtStructureAdviceTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            DebtStructureAdviceBO bo = debtStructureAdviceAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, DebtStructureAdviceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 负债与权益结构管理建议设计id
     * @return class DebtStructureAdviceVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/debtstructureadvice/{id}")
    public Result debtstructureadvice(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            DebtStructureAdviceBO bo = debtStructureAdviceAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, DebtStructureAdviceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 负债与权益结构管理建议设计传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) DebtStructureAdviceTO to, BindingResult result) throws ActException {
        try {
            debtStructureAdviceAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 负债与权益结构管理建议设计id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            debtStructureAdviceAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 负债与权益结构管理建议设计数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DebtStructureAdviceDTO dto) throws ActException {
        try {
            return ActResult.initialize(debtStructureAdviceAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}