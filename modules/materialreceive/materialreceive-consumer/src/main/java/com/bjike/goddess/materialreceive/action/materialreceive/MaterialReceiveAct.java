package com.bjike.goddess.materialreceive.action.materialreceive;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialreceive.api.MaterialReceiveAPI;
import com.bjike.goddess.materialreceive.bo.MaterialReceiveBO;
import com.bjike.goddess.materialreceive.dto.MaterialReceiveDTO;
import com.bjike.goddess.materialreceive.to.MaterialReceiveTO;
import com.bjike.goddess.materialreceive.vo.MaterialReceiveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 物资领用
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:41 ]
 * @Description: [ 物资领用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("materialreceive")
public class MaterialReceiveAct {

    @Autowired
    private MaterialReceiveAPI materialReceiveAPI;

    /**
     * 根据id查询物资领用
     *
     * @param id      物资领用唯一标识
     * @return class MaterialReceiveVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/materialreceive/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MaterialReceiveBO bo = materialReceiveAPI.findById(id);
            MaterialReceiveVO vo = BeanTransform.copyProperties(bo, MaterialReceiveVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 物资领用dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated MaterialReceiveDTO dto, BindingResult result) throws ActException {
        try {
            Long count = materialReceiveAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询物资领用
     *
     * @param dto           物资领用dto
     * @return class MaterialInStockVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated MaterialReceiveDTO dto, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<MaterialReceiveBO> boList = materialReceiveAPI.list(dto);
            List<MaterialReceiveVO> voList = BeanTransform.copyProperties(boList, MaterialReceiveVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加物资领用
     *
     * @param to      物资领用to
     * @return class MaterialInStockVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) MaterialReceiveTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            MaterialReceiveBO bo = materialReceiveAPI.save(to);
            MaterialReceiveVO vo = BeanTransform.copyProperties(bo, MaterialReceiveVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除物资领用
     *
     * @param id 物资领用唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            materialReceiveAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑物资领用
     *
     * @param to     物资领用to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) MaterialReceiveTO to, BindingResult result) throws ActException {
        try {
            materialReceiveAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param to     物资领用to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/audit")
    public Result audit(@Validated(MaterialReceiveTO.AUDIT.class) MaterialReceiveTO to, BindingResult result) throws ActException {
        try {
            materialReceiveAPI.audit(to);
            return new ActResult("audit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 领用完成
     *
     * @param to     物资领用to
     * @throws ActException
     */
    @PutMapping("v1/receiveover")
    public Result receiveOver(@Validated(MaterialReceiveTO.RECEIVEOVER.class) MaterialReceiveTO to, BindingResult result) throws ActException {
        try {
            materialReceiveAPI.receiveOver(to);
            return new ActResult("receiveover success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 物资领用后归还
     *
     * @param to     物资领用to
     * @throws ActException
     */
    @PutMapping("v1/materialreturn")
    public Result materialReturn(@Validated(MaterialReceiveTO.MATERIALRETURN.class) MaterialReceiveTO to, BindingResult result) throws ActException {
        try {
            materialReceiveAPI.materialReturn(to);
            return new ActResult("materialreturn success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}