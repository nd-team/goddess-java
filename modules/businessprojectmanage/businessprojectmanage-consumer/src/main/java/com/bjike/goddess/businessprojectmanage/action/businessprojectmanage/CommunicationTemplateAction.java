package com.bjike.goddess.businessprojectmanage.action.businessprojectmanage;

import com.bjike.goddess.businessprojectmanage.api.CommunicationTemplateAPI;
import com.bjike.goddess.businessprojectmanage.api.CusPermissionAPI;
import com.bjike.goddess.businessprojectmanage.bo.CommunicationTemplateBO;
import com.bjike.goddess.businessprojectmanage.dto.CommunicationTemplateDTO;
import com.bjike.goddess.businessprojectmanage.to.CommunicationTemplateTO;
import com.bjike.goddess.businessprojectmanage.to.GuidePermissionTO;
import com.bjike.goddess.businessprojectmanage.vo.CommunicationTemplateVO;
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
 * 各类沟通交流模板
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-11 05:41 ]
 * @Description: [ 各类沟通交流模板 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("communicationtemple")
public class CommunicationTemplateAction {
    @Autowired
    private CommunicationTemplateAPI communicationTempleAPI;

    @Autowired
    CusPermissionAPI cusPermissionAPI;

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

            Boolean isHasPermission = cusPermissionAPI.guidePermission(guidePermissionTO);
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
     * 各类沟通交流模板总条数
     *
     * @param communicationTempleDTO 各类沟通交流模板dto
     * @des 获取所有各类沟通交流模板总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CommunicationTemplateDTO communicationTempleDTO) throws ActException {
        try {
            Long count = communicationTempleAPI.countCommuni(communicationTempleDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个各类沟通交流模板
     *
     * @param id 各类沟通交流模板id
     * @return class CommunicationTemplateVO
     * @des 根据id获取各类沟通交流模板
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            CommunicationTemplateVO communicationTempleVO = BeanTransform.copyProperties(
                    communicationTempleAPI.getOneById(id), CommunicationTemplateVO.class, true);
            return ActResult.initialize(communicationTempleVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 各类沟通交流模板列表
     *
     * @param communicationTempleDTO 各类沟通交流模板dto
     * @return class CommunicationTemplateVO
     * @des 获取所有各类沟通交流模板
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListProjectCarry(CommunicationTemplateDTO communicationTempleDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<CommunicationTemplateVO> projectCarryVOList = BeanTransform.copyProperties(
                    communicationTempleAPI.listCommuni(communicationTempleDTO), CommunicationTemplateVO.class, request);
            return ActResult.initialize(projectCarryVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加各类沟通交流模板
     *
     * @param communicationTempleTO 各类沟通交流模板数据to
     * @return class CommunicationTemplateVO
     * @des 添加各类沟通交流模板
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addProjectCarry(@Validated({ADD.class}) CommunicationTemplateTO communicationTempleTO, BindingResult bindingResult) throws ActException {
        try {
            CommunicationTemplateBO communicationTempleBO = communicationTempleAPI.addCommuni(communicationTempleTO);
            return ActResult.initialize(BeanTransform.copyProperties(communicationTempleBO, CommunicationTemplateVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑各类沟通交流模板
     *
     * @param communicationTempleTO 各类沟通交流模板bo
     * @return class CommunicationTemplateVO
     * @des 添加各类沟通交流模板
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editProjectCarry(@Validated({EDIT.class}) CommunicationTemplateTO communicationTempleTO) throws ActException {
        try {
            CommunicationTemplateBO communicationTempleBO = communicationTempleAPI.editCommuni(communicationTempleTO);
            return ActResult.initialize(BeanTransform.copyProperties(communicationTempleBO, CommunicationTemplateVO.class, true));
        } catch (SerException e) {
            return new ActResult(1, e.getMessage());
//            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除各类沟通交流模板
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProjectCarry(@PathVariable String id) throws ActException {
        try {
            communicationTempleAPI.deleteNode(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }
}