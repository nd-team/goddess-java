package com.bjike.goddess.annual.action.annual;

import com.bjike.goddess.annual.api.AnnualArrangementStandardAPI;
import com.bjike.goddess.annual.dto.AnnualArrangementStandardDTO;
import com.bjike.goddess.annual.to.AnnualArrangementStandardTO;
import com.bjike.goddess.annual.to.GuidePermissionTO;
import com.bjike.goddess.annual.vo.AnnualArrangementStandardVO;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 年假层级标准
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:33 ]
 * @Description: [ 年假层级标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("annualarrangementstandard")
public class AnnualArrangementStandardAct {

    @Autowired
    private AnnualArrangementStandardAPI annualArrangementStandardAPI;

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

            Boolean isHasPermission = annualArrangementStandardAPI.guidePermission(guidePermissionTO);
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
     * 更新年假层级标准实体数据
     *
     * @param to 年假层级标准传输对象
     * @return class AnnualArrangementStandardVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) AnnualArrangementStandardTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualArrangementStandardAPI.update(to), AnnualArrangementStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据年假标准查询年假层级标准
     *
     * @param id 年假标准id
     * @return class AnnualArrangementStandardVO
     * @version v1
     */
    @GetMapping("v1/findByStandard/{id}")
    public Result findByStandard(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualArrangementStandardAPI.findByStandard(id), AnnualArrangementStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 年假层级标准列表
     *
     * @param dto 年假层级标准数据传输对象
     * @return class AnnualArrangementStandardVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(AnnualArrangementStandardDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualArrangementStandardAPI.maps(dto), AnnualArrangementStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据标准和岗位层级查询年假层级标准
     *
     * @param standardId    年假标准ID
     * @param arrangementId 岗位层级ID
     * @return class AnnualArrangementStandardVO
     * @version v1
     */
    @GetMapping("v1/findByArrangementStandard")
    public Result findByArrangementStandard(String standardId, String arrangementId) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualArrangementStandardAPI.findByArrangementStandard(standardId, arrangementId), AnnualArrangementStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id获取年假层级标准数据
     *
     * @param id 年假层级标准数据id
     * @return class AnnualArrangementStandardVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualArrangementStandardAPI.getById(id), AnnualArrangementStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(annualArrangementStandardAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}