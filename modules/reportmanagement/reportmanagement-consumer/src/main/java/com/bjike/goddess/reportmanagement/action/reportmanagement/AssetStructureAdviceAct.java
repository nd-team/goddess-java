package com.bjike.goddess.reportmanagement.action.reportmanagement;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.api.AssetStructureAdviceAPI;
import com.bjike.goddess.reportmanagement.bo.AssetStructureAdviceBO;
import com.bjike.goddess.reportmanagement.dto.AssetStructureAdviceDTO;
import com.bjike.goddess.reportmanagement.to.AssetStructureAdviceTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.vo.AssetStructureAdviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 资产结构管理建议设计结构管理建议设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 04:38 ]
 * @Description: [ 资产结构管理建议设计结构管理建议设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("assetstructureadvice")
public class AssetStructureAdviceAct {
    @Autowired
    private AssetStructureAdviceAPI assetStructureAdviceAPI;
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

            Boolean isHasPermission = assetStructureAdviceAPI.guidePermission(guidePermissionTO);
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
     * @param dto 资产结构管理建议设计数据传输
     * @return class AssetStructureAdviceVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(AssetStructureAdviceDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<AssetStructureAdviceBO> list = assetStructureAdviceAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, AssetStructureAdviceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 资产结构管理建议设计传输对象
     * @return class AssetStructureAdviceVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) AssetStructureAdviceTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            AssetStructureAdviceBO bo = assetStructureAdviceAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, AssetStructureAdviceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 资产结构管理建议设计id
     * @return class AssetStructureAdviceVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/assetstructureadvice/{id}")
    public Result assetstructureadvice(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AssetStructureAdviceBO bo = assetStructureAdviceAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, AssetStructureAdviceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 资产结构管理建议设计传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) AssetStructureAdviceTO to, BindingResult result) throws ActException {
        try {
            assetStructureAdviceAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 资产结构管理建议设计id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            assetStructureAdviceAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 资产结构管理建议设计数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AssetStructureAdviceDTO dto) throws ActException {
        try {
            return ActResult.initialize(assetStructureAdviceAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}