package com.bjike.goddess.enterpriseculturemanage.action.enterpriseculturemanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.enterpriseculturemanage.api.EnterpriseCultureInfoAPI;
import com.bjike.goddess.enterpriseculturemanage.dto.EnterpriseCultureInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.to.EnterpriseCultureInfoEditTO;
import com.bjike.goddess.enterpriseculturemanage.to.EnterpriseCultureInfoTO;
import com.bjike.goddess.enterpriseculturemanage.to.GuidePermissionTO;
import com.bjike.goddess.enterpriseculturemanage.vo.EnterpriseCultureInfoVO;
import com.bjike.goddess.enterpriseculturemanage.vo.PeriodicalProgramInfoVO;
import com.bjike.goddess.enterpriseculturemanage.vo.PublicizeProgramInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 企业文化信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 05:05 ]
 * @Description: [ 企业文化信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("enterprisecultureinfo")
public class EnterpriseCultureInfoAct {

    @Autowired
    private EnterpriseCultureInfoAPI enterpriseCultureInfoAPI;

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

            Boolean isHasPermission = enterpriseCultureInfoAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询企业文化信息
     *
     * @param id 企业文化信息id
     * @return class EnterpriseCultureInfoVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            EnterpriseCultureInfoVO vo = BeanTransform.copyProperties(enterpriseCultureInfoAPI.findById(id), EnterpriseCultureInfoVO.class, request);
            return ActResult.initialize(vo);
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
    public Result count(EnterpriseCultureInfoDTO dto) throws ActException {
        try {
            Long count = enterpriseCultureInfoAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增
     *
     * @param to 企业文化信息
     * @return class EnterpriseCultureInfoVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) EnterpriseCultureInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            EnterpriseCultureInfoVO vo = BeanTransform.copyProperties(enterpriseCultureInfoAPI.addModel(to), EnterpriseCultureInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 企业文化信息
     * @return class EnterpriseCultureInfoVO
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) EnterpriseCultureInfoEditTO to, BindingResult bindingResult) throws ActException {
        try {
            EnterpriseCultureInfoVO vo = BeanTransform.copyProperties(enterpriseCultureInfoAPI.editModel(to), EnterpriseCultureInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 企业文化信息id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            enterpriseCultureInfoAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 分页条件
     * @return class EnterpriseCultureInfoVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(EnterpriseCultureInfoDTO dto) throws ActException {
        try {
            List<EnterpriseCultureInfoVO> voList = BeanTransform.copyProperties(enterpriseCultureInfoAPI.pageList(dto), EnterpriseCultureInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 宣传方案
     *
     * @param id 企业文化信息id
     * @return class PublicizeProgramInfoVO
     * @version v1
     */

    @GetMapping("v1/publicize/{id}")
    public Result findPublicize(@PathVariable String id) throws ActException {
        try {
           List<PublicizeProgramInfoVO> vo = BeanTransform.copyProperties(enterpriseCultureInfoAPI.findPublicize(id), PublicizeProgramInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 刊物方案
     *
     * @param id 企业文化信息id
     * @return class EnterpriseCultureInfoVO
     * @version v1
     */
    @GetMapping("v1/periodical/{id}")
    public Result findPeriodical(@PathVariable String id) throws ActException {
        try {
            PeriodicalProgramInfoVO vo = BeanTransform.copyProperties(enterpriseCultureInfoAPI.findPeriodical(id), PeriodicalProgramInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}