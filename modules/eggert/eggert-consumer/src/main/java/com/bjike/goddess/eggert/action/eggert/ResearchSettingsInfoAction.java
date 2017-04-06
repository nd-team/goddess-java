package com.bjike.goddess.eggert.action.eggert;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.eggert.api.ResearchSettingsInfoAPI;
import com.bjike.goddess.eggert.bo.ExamQuestionsBO;
import com.bjike.goddess.eggert.bo.ResearchSettingsInfoBO;
import com.bjike.goddess.eggert.dto.ResearchSettingsInfoDTO;
import com.bjike.goddess.eggert.to.ExamQuestionsTO;
import com.bjike.goddess.eggert.to.ResearchSettingsInfoTO;
import com.bjike.goddess.eggert.vo.ResearchSettingsInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 调研设置信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-31 05:22 ]
 * @Description: [ 调研设置信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("eggert/researchsettingsinfo")
public class ResearchSettingsInfoAction {
    @Autowired
    private ResearchSettingsInfoAPI researchSettingsInfoAPI;

    /**
     * 获取调研设置信息
     *
     * @param researchSettingsInfoDTO 调研设置信息dto
     * @return class ResearchSettingsInfoVO
     * @des 获取所有调研设置信息
     * @version v1
     */
    @GetMapping("v1/listResearchSettingsInfo")
    public Result findListResearchSettingsInfo(ResearchSettingsInfoDTO researchSettingsInfoDTO) throws ActException {
        try {
            List<ResearchSettingsInfoVO> researchSettingsInfoVOS = BeanTransform.copyProperties
                    (researchSettingsInfoAPI.findListResearchSettingsInfo(researchSettingsInfoDTO), ResearchSettingsInfoVO.class);
            return ActResult.initialize(researchSettingsInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加调研设置信息
     *
     * @param researchSettingsInfoTO 调研设置信息数据to
     * @return class ResearchSettingsInfoVO
     * @des 添加调研设置信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addResearchSettingsInfo(ResearchSettingsInfoTO researchSettingsInfoTO) throws ActException {
        try {
            ResearchSettingsInfoBO researchSettingsInfoBO = researchSettingsInfoAPI.insertResearchSettingsInfo(researchSettingsInfoTO);
            return ActResult.initialize(researchSettingsInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑调研设置信息
     *
     * @param researchSettingsInfoTO 调研设置信息数据to
     * @return class ResearchSettingsInfoVO
     * @des 编辑调研设置信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editResearchSettingsInfo(ResearchSettingsInfoTO researchSettingsInfoTO) throws ActException {
        try {
            ResearchSettingsInfoBO researchSettingsInfoBO = researchSettingsInfoAPI.editResearchSettingsInfo(researchSettingsInfoTO);
            return ActResult.initialize(researchSettingsInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除调研设置信息
     *
     * @param id 用户id
     * @des 根据用户id删除调研设置信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result removeResearchSettingsInfo(String id) throws ActException {
        try {
            researchSettingsInfoAPI.removeResearchSettingsInfo(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 设置考题
     *
     * @param examQuestionsTO 设置考题数据to
     * @des 设置考题
     * @version v1
     */
    @PostMapping("v1/set")
    public Result setExamQuestions(ExamQuestionsTO examQuestionsTO) throws ActException {
        try {
            ExamQuestionsBO examQuestionsBO = researchSettingsInfoAPI.setExamQuestions(examQuestionsTO);
            return ActResult.initialize(examQuestionsBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}