package com.bjike.goddess.attainment.action.attainment;

import com.bjike.goddess.attainment.api.SurveyDemandAPI;
import com.bjike.goddess.attainment.dto.SurveyDemandDTO;
import com.bjike.goddess.attainment.enums.SurveyStatus;
import com.bjike.goddess.attainment.to.CloseDemandTO;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.attainment.to.SurveyDemandTO;
import com.bjike.goddess.attainment.vo.SurveyDemandVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 调研需求
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:28 ]
 * @Description: [ 调研需求 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("surveydemand")
public class SurveyDemandAct {

    @Autowired
    private SurveyDemandAPI surveyDemandAPI;

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

            Boolean isHasPermission = surveyDemandAPI.guidePermission(guidePermissionTO);
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
     * 保存
     *
     * @param to 调研需求传输对象
     * @return class SurveyDemandVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SurveyDemandTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyDemandAPI.save(to), SurveyDemandVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 调研需求传输对象
     * @return class SurveyDemandVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) SurveyDemandTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyDemandAPI.update(to), SurveyDemandVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 调研需求数据id
     * @return class SurveyDemandVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyDemandAPI.delete(id), SurveyDemandVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 清除需求
     *
     * @param to 关闭需求传输对象
     * @return class SurveyDemandVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/close/{id}")
    public Result close(@Validated(EDIT.class) CloseDemandTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyDemandAPI.close(to), SurveyDemandVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据状态查询调研需求
     *
     * @param status 调研状态
     * @return class SurveyDemandVO
     * @version v1
     */
    @GetMapping("v1/findByStatus")
    public Result findByStatus(SurveyStatus status, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyDemandAPI.findByStatus(status), SurveyDemandVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 调研需求数据传输对象
     * @return class SurveyDemandVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(SurveyDemandDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyDemandAPI.maps(dto), SurveyDemandVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取调研需求数据
     *
     * @param id 调研需求数据id
     * @return class SurveyDemandVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyDemandAPI.getById(id), SurveyDemandVO.class));
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
            return ActResult.initialize(surveyDemandAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取调研需求id
     *
     * @version v1
     */
    @GetMapping("v1/getDemandId")
    public Result getDemandId() throws ActException {
        try {
            return ActResult.initialize(surveyDemandAPI.getDemandId());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}