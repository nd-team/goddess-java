package com.bjike.goddess.democraticmeet.action.democraticmeet;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.democraticmeet.api.MeetDesignAPI;
import com.bjike.goddess.democraticmeet.to.MeetDesignTO;
import com.bjike.goddess.organize.vo.OpinionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 会议组织部分内容
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-02 11:27 ]
 * @Description: [ 会议组织部分内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("meetdesign")
public class MeetDesignAction {


    @Autowired
    private MeetDesignAPI meetDesignAPI;


    /**
     * 所有会议层面
     *
     * @des 所有会议层面
     * @version v1
     */
    @GetMapping("v1/listLevel")
    public Result listLevel(  ) throws ActException {
        try {
            List<String> list = meetDesignAPI.listLevel( );
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 所有计划参会岗位
     *
     * @des 所有计划参会岗位
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("v1/listPostion")
    public Result listPostion(  ) throws ActException {
        try {
            List<OpinionVO> list = BeanTransform.copyProperties(meetDesignAPI.listPosition( ),OpinionVO.class);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 所有计划参会人员
     *
     * @des 所有计划参会人员
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("v1/listPostionPerson")
    public Result listPostionPerson(MeetDesignTO meetDesignTO) throws ActException {
        try {
            List<String> list = meetDesignAPI.listPostionPerson(meetDesignTO );
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 所有会议形式
     *
     * @des 所有会议形式
     * @version v1
     */
    @GetMapping("v1/listForm")
    public Result listForm(  ) throws ActException {
        try {
            List<String> list = meetDesignAPI.listForm( );
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 所有会议主持人
     *
     * @des 所有会议主持人
     * @version v1
     */
    @GetMapping("v1/listHost")
    public Result listHost(  ) throws ActException {
        try {
            List<String> list = meetDesignAPI.listHost( );
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}