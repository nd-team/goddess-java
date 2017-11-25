package com.bjike.goddess.projectprocing.action.projectprocing;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.api.CommunicationTempleAPI;
import com.bjike.goddess.projectprocing.bo.CommunicationTempleBO;
import com.bjike.goddess.projectprocing.dto.CommunicationTempleDTO;
import com.bjike.goddess.projectprocing.to.CommunicationTempleTO;
import com.bjike.goddess.projectprocing.vo.CommunicationTempleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 各类沟通交流模板
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-25 05:41 ]
 * @Description: [ 各类沟通交流模板 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("communicationtemple")
public class CommunicationTempleAction {
    @Autowired
    private CommunicationTempleAPI communicationTempleAPI;

    /**
     * 各类沟通交流模板总条数
     *
     * @param communicationTempleDTO 各类沟通交流模板dto
     * @des 获取所有各类沟通交流模板总条数
     * @version v1
     */
    @GetMapping("v1/count")

    public Result count(CommunicationTempleDTO communicationTempleDTO) throws ActException {
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
     * @return class HeadersCustomVO
     * @des 根据id获取各类沟通交流模板
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            CommunicationTempleVO communicationTempleVO = BeanTransform.copyProperties(
                    communicationTempleAPI.getOneById(id), CommunicationTempleVO.class, true);
            return ActResult.initialize(communicationTempleVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 各类沟通交流模板列表
     *
     * @param communicationTempleDTO 各类沟通交流模板dto
     * @return class CommunicationTempleVO
     * @des 获取所有各类沟通交流模板
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListProjectCarry(CommunicationTempleDTO communicationTempleDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<CommunicationTempleVO> projectCarryVOList = BeanTransform.copyProperties(
                    communicationTempleAPI.listCommuni(communicationTempleDTO), CommunicationTempleVO.class, request);
            return ActResult.initialize(projectCarryVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加各类沟通交流模板
     *
     * @param communicationTempleTO 各类沟通交流模板数据to
     * @return class CommunicationTempleVO
     * @des 添加各类沟通交流模板
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addProjectCarry(@Validated({ADD.class}) CommunicationTempleTO communicationTempleTO, BindingResult bindingResult) throws ActException {
        try {
            CommunicationTempleBO communicationTempleBO = communicationTempleAPI.addCommuni(communicationTempleTO);
            return ActResult.initialize(BeanTransform.copyProperties(communicationTempleBO, CommunicationTempleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑各类沟通交流模板
     *
     * @param communicationTempleTO 各类沟通交流模板bo
     * @return class CommunicationTempleVO
     * @des 添加各类沟通交流模板
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editProjectCarry(@Validated({EDIT.class}) CommunicationTempleTO communicationTempleTO) throws ActException {
        try {
            CommunicationTempleBO communicationTempleBO = communicationTempleAPI.editCommuni(communicationTempleTO);
            return ActResult.initialize(BeanTransform.copyProperties(communicationTempleBO, CommunicationTempleVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
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