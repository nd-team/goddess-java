package com.bjike.goddess.feedback.action.feedback;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.api.RelevancyDetailAPI;
import com.bjike.goddess.feedback.bo.RelevancyDetailBO;
import com.bjike.goddess.feedback.dto.RelevancyDetailDTO;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.RelevancyDetailTO;
import com.bjike.goddess.feedback.vo.RelevancyDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 各模块关联明细
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:23 ]
 * @Description: [ 各模块关联明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("relevancydetail")
public class RelevancyDetailAction {
    @Autowired
    private RelevancyDetailAPI relevancyDetailAPI;

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

            Boolean isHasPermission = relevancyDetailAPI.guidePermission(guidePermissionTO);
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
     * 各模块关联明细列表总条数
     *
     * @param dto 各模块关联明细dto
     * @des 获取所有各模块关联明细总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(RelevancyDetailDTO dto) throws ActException {
        try {
            Long count = relevancyDetailAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个各模块关联明细
     *
     * @param id
     * @return class RelevancyDetailVO
     * @des 获取一个各模块关联明细
     * @version v1
     */
    @GetMapping("v1/detail/{id}")
    public Result detail(@PathVariable String id) throws ActException {
        try {
            RelevancyDetailBO bo = relevancyDetailAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RelevancyDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各模块关联明细列表
     *
     * @param dto 各模块关联明细dto
     * @return class RelevancyDetailVO
     * @des 获取所有各模块关联明细
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(RelevancyDetailDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<RelevancyDetailVO> relevancyDetailVOS = BeanTransform.copyProperties
                    (relevancyDetailAPI.list(dto), RelevancyDetailVO.class, request);
            return ActResult.initialize(relevancyDetailVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加各模块关联明细
     *
     * @param to 各模块关联明细数据to
     * @return class RelevancyDetailVO
     * @des 添加各模块关联明细
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) RelevancyDetailTO to, BindingResult bindingResult) throws ActException {
        try {
            RelevancyDetailBO relevancyDetailBO = relevancyDetailAPI.insert(to);
            return ActResult.initialize(BeanTransform.copyProperties(relevancyDetailBO, RelevancyDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑各模块关联明细
     *
     * @param to 各模块关联明细数据to
     * @return class RelevancyDetailVO
     * @des 编辑各模块关联明细
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) RelevancyDetailTO to, BindingResult bindingResult) throws ActException {
        try {
            RelevancyDetailBO relevancyDetailBO = relevancyDetailAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(relevancyDetailBO, RelevancyDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除各模块关联明细
     *
     * @param id 用户id
     * @des 根据用户id删除各模块关联明细记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            relevancyDetailAPI.remove(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有主功能
     *
     * @des 获取所有主功能
     * @version v1
     */
    @GetMapping("v1/main")
    public Result main() throws ActException {
        try {
            List<String> list = relevancyDetailAPI.getMainFunction();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}