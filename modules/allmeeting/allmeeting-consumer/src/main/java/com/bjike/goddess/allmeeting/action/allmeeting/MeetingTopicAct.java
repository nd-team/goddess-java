package com.bjike.goddess.allmeeting.action.allmeeting;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.allmeeting.api.MeetingTopicAPI;
import com.bjike.goddess.allmeeting.dto.MeetingTopicDTO;
import com.bjike.goddess.allmeeting.to.MeetingTopicTO;
import com.bjike.goddess.allmeeting.vo.MeetingTopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 议题管理
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-24 04:49 ]
 * @Description: [ 议题管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("topic")
public class MeetingTopicAct {

    @Autowired
    private MeetingTopicAPI meetingTopicAPI;

    /**
     * 新增议题管理
     *
     * @param to 议题管理
     * @return class MeetingTopicVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) MeetingTopicTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            MeetingTopicVO voList = BeanTransform.copyProperties(meetingTopicAPI.add(to), MeetingTopicVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑议题管理
     *
     * @param to 议题管理
     * @return class MeetingTopicVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) MeetingTopicTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            MeetingTopicVO vo = BeanTransform.copyProperties(meetingTopicAPI.edit(to), MeetingTopicVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除议题管理
     *
     * @param id 议题管理ID
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            meetingTopicAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class MeetingTopicVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(MeetingTopicDTO dto) throws ActException {
        try {
            List<MeetingTopicVO> voList = BeanTransform.copyProperties(meetingTopicAPI.pageList(dto), MeetingTopicVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(MeetingTopicDTO dto) throws ActException {
        try {
            Long count = meetingTopicAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询议题管理
     *
     * @param id 议题管理id
     * @return class MeetingTopicVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MeetingTopicVO vo = BeanTransform.copyProperties(meetingTopicAPI.findById(id), MeetingTopicVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}