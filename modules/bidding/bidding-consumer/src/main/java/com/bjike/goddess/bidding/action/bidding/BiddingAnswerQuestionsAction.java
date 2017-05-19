package com.bjike.goddess.bidding.action.bidding;

import com.bjike.goddess.bidding.api.BiddingAnswerQuestionsAPI;
import com.bjike.goddess.bidding.bo.BiddingAnswerQuestionsBO;
import com.bjike.goddess.bidding.dto.BiddingAnswerQuestionsDTO;
import com.bjike.goddess.bidding.to.BiddingAnswerQuestionsTO;
import com.bjike.goddess.bidding.vo.BiddingAnswerQuestionsVO;
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
import java.util.List;

/**
 * 投标答疑问题记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:54:11.904 ]
 * @Description: [ 投标答疑问题记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("biddinganswerquestions")
public class BiddingAnswerQuestionsAction {
    @Autowired
    private BiddingAnswerQuestionsAPI biddingAnswerQuestionsAPI;
    /**
     * 投标答疑问题记录列表总条数
     *
     * @param biddingAnswerQuestionsDTO 投标答疑问题记录dto
     * @des 获取所有投标答疑问题记录
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BiddingAnswerQuestionsDTO biddingAnswerQuestionsDTO) throws ActException {
        try {
            Long count = biddingAnswerQuestionsAPI.countBiddingAnswerQuestions(biddingAnswerQuestionsDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个投标答疑问题记录
     *
     * @param id
     * @return class BiddingAnswerQuestionsVO
     * @des 获取一个投标答疑问题记录
     * @version v1
     */
    @GetMapping("v1/answer/{id}")
    public Result answer(@PathVariable String id) throws ActException {
        try {
            BiddingAnswerQuestionsBO biddingAnswerQuestionsBO = biddingAnswerQuestionsAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(biddingAnswerQuestionsBO, BiddingAnswerQuestionsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 投标答疑问题列表
     *
     * @param biddingAnswerQuestionsDTO 投标答疑问题记录dto
     * @return class BiddingAnswerQuestionsVO
     * @des 获取所有投标答疑问题记录
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BiddingAnswerQuestionsDTO biddingAnswerQuestionsDTO, HttpServletRequest request) throws ActException {
        try {
            List<BiddingAnswerQuestionsVO> biddingAnswerQuestionsVOS = BeanTransform.copyProperties(
                    biddingAnswerQuestionsAPI.findListBiddingAnswerQuestions(biddingAnswerQuestionsDTO), BiddingAnswerQuestionsVO.class,request);
            return ActResult.initialize(biddingAnswerQuestionsVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加投标答疑问题记录
     *
     * @param biddingAnswerQuestionsTO 投标答疑问题记录to
     * @return class BiddingAnswerQuestionsVO
     * @des 添加投标答疑问题记录
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) BiddingAnswerQuestionsTO biddingAnswerQuestionsTO, BindingResult bindingResult) throws ActException {
        try {
            BiddingAnswerQuestionsBO biddingAnswerQuestionsBO = biddingAnswerQuestionsAPI.insertBiddingAnswerQuestions(biddingAnswerQuestionsTO);
            return ActResult.initialize(biddingAnswerQuestionsBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑投标答疑问题记录
     *
     * @param biddingAnswerQuestionsTO 投标答疑问题记录数据to
     * @return class BiddingAnswerQuestionsVO
     * @des 添加投标答疑问题记录
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) BiddingAnswerQuestionsTO biddingAnswerQuestionsTO, BindingResult bindingResult) throws ActException {
        try {
            BiddingAnswerQuestionsBO biddingAnswerQuestionsBO = biddingAnswerQuestionsAPI.editBiddingAnswerQuestions(biddingAnswerQuestionsTO);
            return ActResult.initialize(biddingAnswerQuestionsBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除投标答疑问题记录
     *
     * @param id 用户id
     * @des 根据用户id删除投标答疑问题记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            biddingAnswerQuestionsAPI.removeBiddingAnswerQuestions(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 投标答疑问题记录导出
     *
     * @param projectName 项目名称
     * @version v1
     */
    @PostMapping("v1/exportExcel")
    public Result exportExcel(String projectName) throws ActException {
        String excel = null;
        try {
            excel = biddingAnswerQuestionsAPI.exportExcel(projectName);
            return new ActResult(excel);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 上传
     *
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload() throws ActException {
        try {
            biddingAnswerQuestionsAPI.upload();
            return new ActResult("upload success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


}