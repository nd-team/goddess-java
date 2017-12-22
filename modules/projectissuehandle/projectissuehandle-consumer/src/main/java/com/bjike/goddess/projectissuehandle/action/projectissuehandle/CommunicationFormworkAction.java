package com.bjike.goddess.projectissuehandle.action.projectissuehandle;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectissuehandle.api.CommunicationFormworkAPI;
import com.bjike.goddess.projectissuehandle.bo.CommunicationFormworkBO;
import com.bjike.goddess.projectissuehandle.dto.CommunicationFormworkDTO;
import com.bjike.goddess.projectissuehandle.entity.CommunicationFormwork;
import com.bjike.goddess.projectissuehandle.to.CommunicationFormworkTO;
import com.bjike.goddess.projectissuehandle.to.GuidePermissionTO;
import com.bjike.goddess.projectissuehandle.vo.CommunicationFormworkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 各类沟通模板
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-09 10:17 ]
 * @Description: [ 各类沟通模板 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("communicationformwork")
public class CommunicationFormworkAction {
    @Autowired
    private CommunicationFormworkAPI communicationFormworkAPI;

    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = communicationFormworkAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各类沟通模板列表总条数
     *
     * @param communicationFormworkDTO 各类沟通模板dto
     * @des 获取所有各类沟通模板总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CommunicationFormworkDTO communicationFormworkDTO) throws ActException {
        try {
            Long count = communicationFormworkAPI.countCommuni(communicationFormworkDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个各类沟通模板
     *
     * @param id
     * @return class CommunicationFormworkVO
     * @des 获取一个各类沟通模板
     * @version v1
     */
    @GetMapping("v1/problem/{id}")
    public Result problem(@PathVariable String id) throws ActException {
        try {
            CommunicationFormworkBO communicationFormworkBO = communicationFormworkAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(communicationFormworkBO, CommunicationFormworkVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各类沟通模板列表
     *
     * @param communicationFormworkDTO 各类沟通模板dto
     * @return class CommunicationFormworkVO
     * @des 获取所有各类沟通模板
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CommunicationFormworkDTO communicationFormworkDTO, HttpServletRequest request) throws ActException {
        try {
            List<CommunicationFormworkVO> problemAcceptVOS = BeanTransform.copyProperties
                    (communicationFormworkAPI.findListCommuni(communicationFormworkDTO), CommunicationFormworkVO.class, request);
            return ActResult.initialize(problemAcceptVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加各类沟通模板
     *
     * @param communicationFormworkTO 各类沟通模板数据to
     * @return class CommunicationFormworkVO
     * @des 添加各类沟通模板
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) CommunicationFormworkTO communicationFormworkTO, BindingResult result) throws ActException {
        try {
            CommunicationFormworkBO communicationFormworkBO = communicationFormworkAPI.insertCommuni(communicationFormworkTO);
            return ActResult.initialize(BeanTransform.copyProperties(communicationFormworkBO, CommunicationFormworkVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑各类沟通模板
     *
     * @param communicationFormworkTO 各类沟通模板to
     * @return class CommunicationFormworkVO
     * @des 编辑各类沟通模板
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editProblemAccept(@Validated(EDIT.class) CommunicationFormworkTO communicationFormworkTO, BindingResult result) throws ActException {
        try {
            CommunicationFormworkBO communicationFormworkBO = communicationFormworkAPI.editCommuni(communicationFormworkTO);
            return ActResult.initialize(BeanTransform.copyProperties(communicationFormworkBO, CommunicationFormworkVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除各类沟通模板
     *
     * @param id 用户id
     * @des 根据用户id删除项目中问题受理和处理
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProblemAccept(@PathVariable String id) throws ActException {
        try {
            communicationFormworkAPI.removeCommuni(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}