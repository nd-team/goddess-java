package com.bjike.goddess.democraticmeet.action.democraticmeet;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.democraticmeet.api.DemocraticContentAPI;
import com.bjike.goddess.democraticmeet.bo.AttenderBO;
import com.bjike.goddess.democraticmeet.bo.DemocraticContentBO;
import com.bjike.goddess.democraticmeet.bo.MeetDesignBO;
import com.bjike.goddess.democraticmeet.bo.MeetTitleOpinionBO;
import com.bjike.goddess.democraticmeet.dto.AttenderDTO;
import com.bjike.goddess.democraticmeet.dto.DemocraticContentDTO;
import com.bjike.goddess.democraticmeet.dto.MeetDesignDTO;
import com.bjike.goddess.democraticmeet.entity.Attender;
import com.bjike.goddess.democraticmeet.entity.DemocraticContent;
import com.bjike.goddess.democraticmeet.entity.MeetDesign;
import com.bjike.goddess.democraticmeet.to.DemocraticContentTO;
import com.bjike.goddess.democraticmeet.vo.AttenderVO;
import com.bjike.goddess.democraticmeet.vo.DemocraticContentVO;
import com.bjike.goddess.democraticmeet.vo.MeetDesignVO;
import com.bjike.goddess.democraticmeet.vo.MeetTitleOpinionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 民主生活会议组织内容
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-02 11:20 ]
 * @Description: [ 民主生活会议组织内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("democraticcontent")
public class DemocraticContentAction {


    @Autowired
    private DemocraticContentAPI democraticContentAPI;

    /**
     * 列表总条数
     *
     * @param customerBaseInfoDTO 民主生活会议组织内容信息dto
     * @des 获取所有民主生活会议组织内容信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DemocraticContentDTO customerBaseInfoDTO) throws ActException {
        try {
            Long count = democraticContentAPI.countDemocraticContent(customerBaseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个民主生活会议组织内容
     *
     * @param id 民主生活会议组织内容信息id
     * @return class DemocraticContentVO
     * @des 根据id获取民主生活会议组织内容信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {

            DemocraticContentBO bo = democraticContentAPI.getOneById(id);
            DemocraticContentVO vo = BeanTransform.copyProperties(bo, DemocraticContentVO.class);

            MeetDesignBO meetDesignBO = bo.getMeetDesignBO();
            MeetDesignVO meVO = BeanTransform.copyProperties(meetDesignBO, MeetDesignVO.class);
            vo.setMeetDesignVO(meVO == null ? new MeetDesignVO() : meVO);

            if (vo.getMeetDesignVO() != null) {
                List<AttenderBO> attenders = bo.getMeetDesignBO().getAttenderBOs();
                List<AttenderVO> attenderVOList = BeanTransform.copyProperties(attenders, AttenderVO.class);
                vo.getMeetDesignVO().setAttenderVOs(attenderVOList);
            }

            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 民主生活会议组织内容列表
     *
     * @param democraticContentDTO 民主生活会议组织内容信息dto
     * @return class DemocraticContentVO
     * @des 获取所有民主生活会议组织内容信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListDemocraticContent(DemocraticContentDTO democraticContentDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<DemocraticContentBO> democraticContentBOList = democraticContentAPI.listDemocraticContent(democraticContentDTO);

            List<DemocraticContentVO> democraticContentVOList = new ArrayList<>();
            for (DemocraticContentBO str : democraticContentBOList) {
                DemocraticContentVO bo = BeanTransform.copyProperties(str, DemocraticContentVO.class, request);

                MeetDesignBO meetDesignBO = str.getMeetDesignBO();
                MeetDesignVO meVO = BeanTransform.copyProperties(meetDesignBO, MeetDesignVO.class);
                bo.setMeetDesignVO(meVO == null ? new MeetDesignVO() : meVO);

                if (bo.getMeetDesignVO() != null) {
                    List<AttenderBO> attenders = str.getMeetDesignBO().getAttenderBOs();
                    List<AttenderVO> attenderVOList = BeanTransform.copyProperties(attenders, AttenderVO.class);
                    bo.getMeetDesignVO().setAttenderVOs(attenderVOList);
                }

                democraticContentVOList.add(bo);
            }

            return ActResult.initialize(democraticContentVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加民主生活会议组织内容
     *
     * @param democraticContentTO 民主生活会议组织内容基本信息数据to
     * @return class DemocraticContentVO
     * @des 添加民主生活会议组织内容
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addDemocraticContent(@Validated DemocraticContentTO democraticContentTO, BindingResult bindingResult) throws ActException {
        try {
            DemocraticContentBO democraticContentBO1 = democraticContentAPI.addDemocraticContent(democraticContentTO);
            return ActResult.initialize(BeanTransform.copyProperties(democraticContentBO1, DemocraticContentVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑民主生活会议组织内容
     *
     * @param democraticContentTO 民主生活会议组织内容基本信息数据bo
     * @return class DemocraticContentVO
     * @des 添加民主生活会议组织内容
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editDemocraticContent(@Validated DemocraticContentTO democraticContentTO) throws ActException {
        try {
            DemocraticContentBO democraticContentBO1 = democraticContentAPI.editDemocraticContent(democraticContentTO);
            return ActResult.initialize(BeanTransform.copyProperties(democraticContentBO1, DemocraticContentVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除民主生活会议组织内容信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteDemocraticContent(@PathVariable String id) throws ActException {
        try {
            democraticContentAPI.deleteDemocraticContent(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }


    /**
     * 所有会议议题
     *
     * @des 所有会议议题
     * @version v1
     */
    @GetMapping("v1/listTitle")
    public Result listTitle() throws ActException {
        try {
            List<String> list = democraticContentAPI.listTitle();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 所有没有会议纪要的会议议题
     *
     * @des 所有会议议题用于会议纪要
     * @return class MeetTitleOpinionVO
     * @version v1
     */
    @GetMapping("v1/listOpinionTitle")
    public Result listOpinionTitle() throws ActException {
        try {
            List<MeetTitleOpinionVO> list =BeanTransform.copyProperties(
                    democraticContentAPI.listOpinionTitle() , MeetTitleOpinionBO.class);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}