package com.bjike.goddess.materialbuy.action.materialbuy;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialbuy.api.MaterialBuyAPI;
import com.bjike.goddess.materialbuy.bo.MaterialBuyBO;
import com.bjike.goddess.materialbuy.dto.MaterialBuyDTO;
import com.bjike.goddess.materialbuy.to.MaterialBuyTO;
import com.bjike.goddess.materialbuy.vo.MaterialBuyVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物资购买
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("materialbuy")
public class MaterialBuyAct {

    @Autowired
    private MaterialBuyAPI materialBuyAPI;

    /**
     * 根据id查询物资购买
     *
     * @param id 物资购买唯一标识
     * @return class MaterialBuyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findbyid/{id}")
    public Result findById(@PathVariable String id) throws ActException {
        try {
            MaterialBuyBO bo = materialBuyAPI.findById(id);
            MaterialBuyVO vo = BeanTransform.copyProperties(bo, MaterialBuyVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询物资购买
     *
     * @param dto 物资购买dto
     * @param bindingResult
     * @return class MaterialBuyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated MaterialBuyDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<MaterialBuyBO> boList = materialBuyAPI.list(dto);
            List<MaterialBuyVO> voList = BeanTransform.copyProperties(boList, MaterialBuyVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加物资购买
     *
     * @param to 物资购买to
     * @param result
     * @return class MaterialBuyVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) MaterialBuyTO to, BindingResult result) throws ActException {
        try {
            MaterialBuyBO bo = materialBuyAPI.save(to);
            MaterialBuyVO vo = BeanTransform.copyProperties(bo, MaterialBuyVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除物资购买
     *
     * @param id 物资购买唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            materialBuyAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑物资购买
     *
     * @param to 物资购买to
     * @param result
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated MaterialBuyTO to, BindingResult result) throws ActException {
        try {
            materialBuyAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看详情
     *
     * @param id 物资购买唯一标识
     * @return class MaterialBuyVO
     * @throws SerException
     * @version v1
     */
    @GetMapping("v1/checkDetail/{id}")
    public Result checkDetail(@PathVariable String id) throws ActException {
        try {
            MaterialBuyBO bo = materialBuyAPI.checkDetail(id);
            MaterialBuyVO vo = BeanTransform.copyProperties(bo, MaterialBuyVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传文件
     *
     * @param request
     * @throws ActException
     */
    @PutMapping("v1/upload")
    public Result upload(HttpServletRequest request) throws ActException {
        return new ActResult("upload success!");
    }

    /**
     * 地区负责人审核
     *
     * @param to 物资购买to
     * @param result
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/areaPrincipalAudit")
    public Result areaPrincipalAudit(@Validated(MaterialBuyTO.PrincipalAudit.class) MaterialBuyTO to, BindingResult result) throws ActException {
        try {
            materialBuyAPI.areaPrincipalAudit(to);
            return new ActResult("areaPrincipalAudit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}