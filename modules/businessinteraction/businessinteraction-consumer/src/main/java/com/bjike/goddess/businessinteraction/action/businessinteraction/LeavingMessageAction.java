package com.bjike.goddess.businessinteraction.action.businessinteraction;

import com.bjike.goddess.businessinteraction.api.LeavingMessageAPI;
import com.bjike.goddess.businessinteraction.bo.LeavingMessageBO;
import com.bjike.goddess.businessinteraction.dto.LeavingMessageDTO;
import com.bjike.goddess.businessinteraction.to.LeavingMessageTO;
import com.bjike.goddess.businessinteraction.vo.LeavingMessageVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 留言
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:11 ]
 * @Description: [ 留言 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessinteraction/leavingmessage")
public class LeavingMessageAction {

    @Autowired
    private LeavingMessageAPI leavingMessageAPI;


    /**
     * 留言列表
     *
     * @param interactionId 商业能力互动联系信息id
     * @des 获取所有商业能力互动联系信息
     * @return  class LeavingMessageVO
     * @version v1
     */
    @GetMapping("v1/listLeavingMessage")
    public Result findListLeavingMessage(@RequestParam String interactionId) throws ActException {
        try {
            List<LeavingMessageVO> leavingMessageVOList = BeanTransform.copyProperties(
                    leavingMessageAPI.listLeavingMessage(interactionId), LeavingMessageVO.class, true);
            return ActResult.initialize(leavingMessageVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加留言
     *
     * @param leavingMessageTO 留言基本信息数据to
     * @des 添加留言
     * @return  class LeavingMessageVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addLeavingMessage(@Validated LeavingMessageTO leavingMessageTO, BindingResult bindingResult) throws ActException {
        try {
            LeavingMessageBO leavingMessageBO1 = leavingMessageAPI.addLeavingMessage(leavingMessageTO);
            return ActResult.initialize(BeanTransform.copyProperties(leavingMessageBO1,LeavingMessageVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}