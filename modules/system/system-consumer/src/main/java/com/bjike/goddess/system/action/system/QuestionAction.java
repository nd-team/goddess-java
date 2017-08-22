package com.bjike.goddess.system.action.system;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.system.api.QuestionAPI;
import com.bjike.goddess.system.bo.QuestionBO;
import com.bjike.goddess.system.dto.QuestionDTO;
import com.bjike.goddess.system.to.AuswerTO;
import com.bjike.goddess.system.to.QuestionTO;
import com.bjike.goddess.system.vo.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 问题
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:48 ]
 * @Description: [ da ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("question")
public class QuestionAction {
    @Autowired
    private QuestionAPI questionAPI;

    /**
     * 问题列表总条数
     *
     * @param dto 问题记录dto
     * @des 获取所有问题
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(QuestionDTO dto) throws ActException {
        try {
            Long count = questionAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个问题
     *
     * @param id
     * @return class QuestionVO
     * @des 获取一个问题
     * @version v1
     */
    @GetMapping("v1/wait/{id}")
    public Result wait(@PathVariable String id) throws ActException {
        try {
            QuestionBO questionBO = questionAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(questionBO, QuestionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 问题列表
     *
     * @param dto 问题记录dto
     * @return class QuestionVO
     * @des 获取所有问题
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(QuestionDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<QuestionVO> auswerVOS = BeanTransform.copyProperties(
                    questionAPI.list(dto), QuestionVO.class, request);
            return ActResult.initialize(auswerVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 添加问题
//     *
//     * @param to 问题to
//     * @return class QuestionVO
//     * @des 添加问题
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/add")
//    public Result add(QuestionTO to, BindingResult bindingResult) throws ActException {
//        try {
//            QuestionBO questionBO = questionAPI.insert(to);
//            return ActResult.initialize(BeanTransform.copyProperties(questionBO,QuestionVO.class));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 编辑问题
     *
     * @param to 问题数据to
     * @return class QuestionVO
     * @des 编辑问题
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(QuestionTO to, BindingResult bindingResult) throws ActException {
        try {
            QuestionBO questionBO = questionAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(questionBO, QuestionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 删除问题
//     *
//     * @param id 用户id
//     * @des 根据用户id删除问题
//     * @version v1
//     */
//    @LoginAuth
//    @DeleteMapping("v1/delete/{id}")
//    public Result delete(@PathVariable String id) throws ActException {
//        try {
//            questionAPI.remove(id);
//            return new ActResult("delete success!");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 详情
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/detail/{id}")
    public Result detail(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(questionAPI.detail(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解答
     *
     * @param id       id
     * @param answerTO answerTO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/answer/{id}")
    public Result answer(@PathVariable String id, @Validated(AuswerTO.TestAdd.class) AuswerTO answerTO, BindingResult result) throws ActException {
        try {
            questionAPI.answer(id, answerTO);
            return new ActResult("解答成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}