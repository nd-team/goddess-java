package com.bjike.goddess.version.action.version;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.version.api.HelpAPI;
import com.bjike.goddess.version.bo.HelpBO;
import com.bjike.goddess.version.bo.HelpBO1;
import com.bjike.goddess.version.dto.HelpDTO;
import com.bjike.goddess.version.to.AnswerTO;
import com.bjike.goddess.version.to.HelpTO;
import com.bjike.goddess.version.vo.HelpVO;
import com.bjike.goddess.version.vo.HelpVO1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 帮助与解答
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:07 ]
 * @Description: [ 帮助与解答 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("help")
public class HelpAction {
    @Autowired
    private HelpAPI helpAPI;

    /**
     * 列表
     *
     * @param dto 帮助与解答数据传输
     * @return class HelpVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated(HelpDTO.LIST.class) HelpDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<HelpBO> list = helpAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, HelpVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 添加
//     *
//     * @param to 帮助与解答传输对象
//     * @return class HelpVO
//     * @throws ActException
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/save")
//    public Result save(@Validated(ADD.class) HelpTO to, BindingResult result, HttpServletRequest request) throws ActException {
//        try {
//            HelpBO bo = helpAPI.save(to);
//            return ActResult.initialize(BeanTransform.copyProperties(bo, HelpVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 通过id查找
     *
     * @param id 帮助与解答id
     * @return class HelpVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/help/{id}")
    public Result help(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            HelpBO bo = helpAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, HelpVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 帮助与解答传输对象
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) HelpTO to, BindingResult result) throws ActException {
        try {
            helpAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 删除
//     *
//     * @param id 帮助与解答id
//     * @throws ActException
//     * @version v1
//     */
//    @DeleteMapping("v1/delete/{id}")
//    public Result delete(@PathVariable String id) throws ActException {
//        try {
//            helpAPI.delete(id);
//            return new ActResult("删除成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 查找总记录数
     *
     * @param dto 帮助与解答数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated(HelpDTO.LIST.class) HelpDTO dto, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(helpAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 详情
     *
     * @param id id
     * @return class HelpVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findDetail/{id}")
    public Result findDetail(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            HelpBO1 helpBO = helpAPI.findDetail(id);
            return ActResult.initialize(BeanTransform.copyProperties(helpBO, HelpVO1.class, request));
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
    public Result answer(@PathVariable String id, @Validated(AnswerTO.AnswerADD.class) AnswerTO answerTO, BindingResult result) throws ActException {
        try {
            helpAPI.answer(id, answerTO);
            return new ActResult("解答成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}