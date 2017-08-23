package com.bjike.goddess.attainment.action.attainment;

import com.bjike.goddess.attainment.api.SurveyActualizeAPI;
import com.bjike.goddess.attainment.api.SurveyPlanAPI;
import com.bjike.goddess.attainment.bo.SurveyQuestionnairesBO;
import com.bjike.goddess.attainment.dto.SurveyActualizeDTO;
import com.bjike.goddess.attainment.to.*;
import com.bjike.goddess.attainment.vo.SurPlanvo;
import com.bjike.goddess.attainment.vo.SurveyActualizeVO;
import com.bjike.goddess.common.api.entity.ADD;
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
import java.util.List;

/**
 * 实施记录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:58 ]
 * @Description: [ 调研实施记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("surveyactualize")
public class SurveyActualizeAct {

    @Autowired
    private SurveyActualizeAPI surveyActualizeAPI;
    @Autowired
    private SurveyPlanAPI surveyPlanAPI;


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

            Boolean isHasPermission = surveyActualizeAPI.guidePermission(guidePermissionTO);
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
     * @param to 调研实施记录传输对象
     * @return class SurveyActualizeVO
     * @version v1
     * @des 需求文档中与调研计划的调研实施为同一接口
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SurveyActualizeTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyActualizeAPI.save(to), SurveyActualizeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 调研实施记录传输对象
     * @return class SurveyActualizeVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) SurveyActualizeTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyActualizeAPI.update(to), SurveyActualizeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 调研实施记录数据id
     * @return class SurveyActualizeVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyActualizeAPI.delete(id), SurveyActualizeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 结束调研
     *
     * @param id 调研实施记录数据id
     * @return class SurveyActualizeVO
     * @version v1
     */
    @PutMapping("v1/over/{id}")
    public Result over(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyActualizeAPI.over(id), SurveyActualizeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 调研实施记录数据传输对象
     * @return class SurveyActualizeVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(SurveyActualizeDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyActualizeAPI.maps(dto), SurveyActualizeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取调研实施记录数据
     *
     * @param id 调研实施记录数据id
     * @return class SurveyActualizeVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyActualizeAPI.getById(id), SurveyActualizeVO.class));
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
            return ActResult.initialize(surveyActualizeAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取调研计划
     *
     * @return class SurPlanvo
     * @version v1
     */
    @GetMapping("v1/getSurveyPlan")
    public Result getSurveyPlan() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyPlanAPI.getSurveyPlan(), SurPlanvo.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 建立问卷
     *
     * @param to 问卷数据
     * @version v1
     */
    @PostMapping("v1/questionnaire")
    public Result questionnaire(@Validated(ADD.class) SurveyActualizesTO to, BindingResult result) throws ActException {
        try {
            surveyPlanAPI.questionnaire(to);
            return ActResult.initialize("建立问卷成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据实施记录id查看问卷
     *
     * @return class SurveyQuestionnairesBO
     * @version v1
     */
    @GetMapping("v1/getQuestionnaire/{id}")
    public Result getQuestionnaire(@PathVariable String id) throws ActException {
        try {
            List<SurveyQuestionnairesBO> list = surveyPlanAPI.getQuestionnaire(id);
//            return ActResult.initialize(BeanTransform.copyProperties(list, SurveyActualizesVO.class));
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 问卷调研
     *
     * @param to 问卷数据
     * @version v1
     */
    @PostMapping("v1/editQuestionnaire")
    public Result editQuestionnaire(@Validated(ADD.class) SurveyQuestionnaireOptionUsersTO to, BindingResult result) throws ActException {
        try {
            surveyPlanAPI.editQuestionnaire(to);
            return ActResult.initialize("问卷填写成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据调研表id修改问卷
     *
     * @param to 问卷数据
     * @version v1
     */
    @PutMapping("v1/edit/{id}")
    public Result edit(@Validated(EDIT.class) SurveyActualizesTO to, BindingResult result) throws ActException {
        try {
            surveyPlanAPI.edit(to);
            return ActResult.initialize("修改成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有的调研表名称
     *
     * @version v1
     */
    @GetMapping("v1/getName")
    public Result getName() throws ActException {
        try {
            return ActResult.initialize(surveyActualizeAPI.getName());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}