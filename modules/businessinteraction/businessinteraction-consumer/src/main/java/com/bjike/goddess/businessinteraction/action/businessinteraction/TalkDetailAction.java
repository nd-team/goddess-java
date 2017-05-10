package com.bjike.goddess.businessinteraction.action.businessinteraction;

import com.bjike.goddess.businessinteraction.api.TalkDetailAPI;
import com.bjike.goddess.businessinteraction.bo.TalkDetailBO;
import com.bjike.goddess.businessinteraction.dto.TalkDetailDTO;
import com.bjike.goddess.businessinteraction.to.TalkDetailTO;
import com.bjike.goddess.businessinteraction.vo.ContactObjectVO;
import com.bjike.goddess.businessinteraction.vo.TalkDetailVO;
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
import javax.validation.Valid;
import java.util.List;

/**
 * 洽谈详情
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:27 ]
 * @Description: [ 洽谈详情 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("talkdetail")
public class TalkDetailAction {
    @Autowired
    private TalkDetailAPI talkDetailAPI;

    /**
     *  列表总条数
     *
     * @param talkDetailDTO  洽谈详情信息dto
     * @des 获取所有洽谈详情信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(TalkDetailDTO talkDetailDTO) throws ActException {
        try {
            Long count = talkDetailAPI.countInter(talkDetailDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个洽谈详情
     *
     * @param id 洽谈详情信息id
     * @des 根据id获取所有洽谈详情信息
     * @return  class TalkDetailVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne( @PathVariable String id ) throws ActException {
        try {
            TalkDetailVO talkDetailVOList = BeanTransform.copyProperties(
                    talkDetailAPI.getOneById(id), TalkDetailVO.class);
            return ActResult.initialize(talkDetailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    
    /**
     * 洽谈详情列表
     *
     * @param talkDetailDTO 洽谈详情信息dto
     * @param request 前端过滤参数
     * @des 获取所有洽谈详情信息
     * @return  class TalkDetailVO
     * @version v1
     */
    @GetMapping("v1/listTalkDetail")
    public Result findListTalkDetail(TalkDetailDTO talkDetailDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<TalkDetailVO> talkDetailVOList = BeanTransform.copyProperties(
                    talkDetailAPI.listTalkDetail(talkDetailDTO), TalkDetailVO.class, request);
            return ActResult.initialize(talkDetailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加洽谈详情
     *
     * @param talkDetailTO 洽谈详情基本信息数据to
     * @des 添加洽谈详情
     * @return  class TalkDetailVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addTalkDetail(@Validated TalkDetailTO talkDetailTO, BindingResult bindingResult) throws ActException {
        try {
            TalkDetailBO talkDetailBO1 = talkDetailAPI.addTalkDetail(talkDetailTO);
            return ActResult.initialize(BeanTransform.copyProperties(talkDetailBO1,TalkDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑洽谈详情
     *
     * @param talkDetailTO 洽谈详情基本信息数据bo
     * @des 添加洽谈详情
     * @return  class TalkDetailVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editTalkDetail(@Validated TalkDetailTO talkDetailTO) throws ActException {
        try {
            TalkDetailBO talkDetailBO1 = talkDetailAPI.editTalkDetail(talkDetailTO);
            return ActResult.initialize(BeanTransform.copyProperties(talkDetailBO1,TalkDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除洽谈详情信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteTalkDetail(@PathVariable String id) throws ActException {
        try {
            talkDetailAPI.deleteTalkDetail(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 获取合作对象联系方式
     *
     * @param talkDetailDTO 洽谈详情信息dto
     * @des 获取对象联系方式从供应商管理和商业能力展示和市场信息管理和客户信息管理获取
     * @return  class TalkDetailVO
     * @version v1
     */
    @GetMapping("v1/getContactWays")
    public Result getContactWays(@Validated({TalkDetailDTO.TESTTalkDetailDTO.class}) TalkDetailDTO talkDetailDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ContactObjectVO> talkDetailVOList = BeanTransform.copyProperties(
                    talkDetailAPI.getContactWays(talkDetailDTO), TalkDetailVO.class);
            return ActResult.initialize(talkDetailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}