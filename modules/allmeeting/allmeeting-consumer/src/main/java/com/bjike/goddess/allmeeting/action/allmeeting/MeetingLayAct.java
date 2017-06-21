package com.bjike.goddess.allmeeting.action.allmeeting;

import com.bjike.goddess.allmeeting.api.MeetingLayAPI;
import com.bjike.goddess.allmeeting.dto.MeetingLayDTO;
import com.bjike.goddess.allmeeting.to.MeetingLayTO;
import com.bjike.goddess.allmeeting.vo.MeetingLayVO;
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
 * 会议层面
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-26 10:16 ]
 * @Description: [ 会议层面 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
    @RequestMapping("meetinglay")
public class MeetingLayAct {

    @Autowired
    private MeetingLayAPI meetingLayAPI;

    /**
     * 新增会议层面
     *
     * @param to 会议层面
     * @return class MeetingLayVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) MeetingLayTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            MeetingLayVO voList = BeanTransform.copyProperties(meetingLayAPI.add(to), MeetingLayVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑会议层面
     *
     * @param to 会议层面
     * @return class MeetingLayVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) MeetingLayTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            MeetingLayVO vo = BeanTransform.copyProperties(meetingLayAPI.edit(to), MeetingLayVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除会议层面
     *
     * @param id 会议层面ID
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            meetingLayAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class MeetingLayVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(MeetingLayDTO dto) throws ActException {
        try {
            List<MeetingLayVO> voList = BeanTransform.copyProperties(meetingLayAPI.pageList(dto), MeetingLayVO.class);
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
    public Result count(MeetingLayDTO dto) throws ActException {
        try {
            Long count = meetingLayAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询会议层面
     *
     * @param id 会议层面id
     * @return class MeetingLayVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MeetingLayVO vo = BeanTransform.copyProperties(meetingLayAPI.findById(id), MeetingLayVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}