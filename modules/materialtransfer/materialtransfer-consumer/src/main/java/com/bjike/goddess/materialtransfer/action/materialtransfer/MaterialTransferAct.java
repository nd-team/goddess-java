package com.bjike.goddess.materialtransfer.action.materialtransfer;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialtransfer.api.MaterialTransferAPI;
import com.bjike.goddess.materialtransfer.bo.MaterialTransferBO;
import com.bjike.goddess.materialtransfer.dto.MaterialTransferDTO;
import com.bjike.goddess.materialtransfer.to.MaterialTransferTO;
import com.bjike.goddess.materialtransfer.type.AuditState;
import com.bjike.goddess.materialtransfer.vo.MaterialTransferVO;
import com.bjike.goddess.user.to.DepartmentTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 物资调动
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-28 10:47 ]
 * @Description: [ 物资调动 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("materialtransfer")
public class MaterialTransferAct {

    @Autowired
    private MaterialTransferAPI materialTransferAPI;

    /**
     * 根据id查询物资调动
     *
     * @param id      物资调动唯一标识
     * @param request Http请求
     * @return class MaterialTransferVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/materialreceive/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MaterialTransferBO bo = materialTransferAPI.findById(id);
            MaterialTransferVO vo = BeanTransform.copyProperties(bo, MaterialTransferVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总条数
     *
     * @param dto 物资调动dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated MaterialTransferDTO dto, BindingResult bindingResult) throws ActException {
        try {
            Long count = materialTransferAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询物资调动
     *
     * @param dto 物资调动dto
     * @return class MaterialTransferVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated MaterialTransferDTO dto, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<MaterialTransferBO> boList = materialTransferAPI.list(dto);
            List<MaterialTransferVO> voList = BeanTransform.copyProperties(boList, MaterialTransferVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加物资调动
     *
     * @param to 物资调动to
     * @return class MaterialTransferVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) MaterialTransferTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            MaterialTransferBO bo = materialTransferAPI.save(to);
            MaterialTransferVO vo = BeanTransform.copyProperties(bo, MaterialTransferVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除物资调动
     *
     * @param id 物资调动唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            materialTransferAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑物资调动
     *
     * @param to 物资调动to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) MaterialTransferTO to, BindingResult result) throws ActException {
        try {
            materialTransferAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目经理审核
     *
     * @param id 物资调动唯一标识
     * @param pmAuditState 项目经理审核状态
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/pmAudit/{id}")
    public Result pmAudit(@PathVariable String id, @RequestParam(value = "pmAuditState") AuditState pmAuditState) throws ActException {
        try {
            materialTransferAPI.pmAudit(id, pmAuditState);
            return new ActResult("pmAudit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 福利模块负责人审核
     *
     * @param id 物资调动唯一标识
     * @param welfareState 福利模块负责人审核状态
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/wealModAudit/{id}")
    public Result wealModAudit(@PathVariable String id, @RequestParam(value = "welfareState") AuditState welfareState) throws ActException {
        try {
            materialTransferAPI.wealModAudit(id, welfareState);
            return new ActResult("wealModAudit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 福利模块负责人确认调配成功
     *
     * @param id 物资调动唯一标识
     * @param recipient 领用人
     * @param confirmDeploy 福利模块负责人确认调配成功
     * @param finishDeployTime 调配成功
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/wealModConfirm/{id}")
    public Result wealModConfirm(@PathVariable String id,
                                 @RequestParam(value = "recipient") String recipient,
                                 @RequestParam(value = "confirmDeploy") Boolean confirmDeploy,
                                 @RequestParam(value = "finishDeployTime") String finishDeployTime) throws ActException {
        try {
            materialTransferAPI.wealModConfirm(id, recipient, confirmDeploy, finishDeployTime);
            return new ActResult("wealModConfirm success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}