package com.bjike.goddess.intromanage.action.intromanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.api.FirmIntroAPI;
import com.bjike.goddess.intromanage.bo.FirmIntroBO;
import com.bjike.goddess.intromanage.dto.FirmIntroDTO;
import com.bjike.goddess.intromanage.to.FirmDisplayFieldTO;
import com.bjike.goddess.intromanage.to.FirmIntroTO;
import com.bjike.goddess.intromanage.to.GuidePermissionTO;
import com.bjike.goddess.intromanage.vo.*;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司简介
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:20 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("firmintro")
public class FirmIntroAct {

    @Autowired
    private FirmIntroAPI firmIntroAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = firmIntroAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据id查询公司简介
     *
     * @param id 公司简介唯一标识
     * @return class FirmIntroVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/firmintro/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            FirmIntroBO bo = firmIntroAPI.findById(id);
            //查询荣誉与资质
            List<HonorAndQualityVO> honorAndQualitieVOs = BeanTransform.copyProperties( bo.getHonorAndQualityBOS() ,HonorAndQualityVO.class );
            //查询主业介绍
            List<MainBusinessIntroVO> mainBusinessIntroVOS = BeanTransform.copyProperties( bo.getMainBusinessIntroBOS() ,MainBusinessIntroVO.class );
            //查询成功案例
            List<SuccessStoriesVO> successStoriesVOS = BeanTransform.copyProperties( bo.getSuccessStoriesBOS() ,SuccessStoriesVO.class );
            //查询客户及合作伙伴
            List<CustomerAndPartnerVO> customerAndPartnerVOS = BeanTransform.copyProperties( bo.getCustomerAndPartnerBOS() ,CustomerAndPartnerVO.class );
            //查询通讯途径
            List<CommunicationPathVO> communicationPathVOS = BeanTransform.copyProperties( bo.getCommunicationPathBOS() ,CommunicationPathVO.class );

            FirmIntroVO vo = BeanTransform.copyProperties(bo, FirmIntroVO.class, request);
            vo.setHonorAndQualityVOS( honorAndQualitieVOs );
            vo.setMainBusinessIntroVOS( mainBusinessIntroVOS );
            vo.setSuccessStoriesVOS( successStoriesVOS );
            vo.setCustomerAndPartnerVOS( customerAndPartnerVOS );
            vo.setCommunicationPathVOS( communicationPathVOS );

            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 公司简介dto
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/count")
    public Result count(@Validated FirmIntroDTO dto, BindingResult result) throws ActException {
        try {
            Long count = firmIntroAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询公司简介
     *
     * @param dto 公司简介dto
     * @return class FirmIntroVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result list(@Validated FirmIntroDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<FirmIntroBO> boList = firmIntroAPI.list(dto);
            List<FirmIntroVO> voList = BeanTransform.copyProperties(boList, FirmIntroVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加公司简介
     *
     * @param to 公司简介to
     * @return class FirmIntroVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) FirmIntroTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            FirmIntroBO bo = firmIntroAPI.save(to);
            FirmIntroVO vo = BeanTransform.copyProperties(bo, FirmIntroVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除公司简介
     *
     * @param id 公司简介唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            firmIntroAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑公司简介
     *
     * @param to 公司简介to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) FirmIntroTO to, BindingResult result) throws ActException {
        try {
            firmIntroAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 设置公司简介显示的字段
     *
     * @param username 用户姓名数组
     * @param to       公司简介显示字段to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/setFirmDisplayField")
    public Result setFirmDisplayField(String[] username, @Validated(value = {ADD.class}) FirmDisplayFieldTO to, BindingResult result) throws ActException {
        try {
            firmIntroAPI.setFirmDisplayField(username, to);
            return new ActResult("setFirmDisplayField success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有的用户
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/findAllUser")
    public Result findAllUser() throws ActException {
        try {
            List<String> userName = new ArrayList<>();
            userName = firmIntroAPI.findallMonUser();
            return ActResult.initialize(userName);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}