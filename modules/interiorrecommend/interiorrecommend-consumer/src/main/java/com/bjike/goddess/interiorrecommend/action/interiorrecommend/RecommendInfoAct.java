package com.bjike.goddess.interiorrecommend.action.interiorrecommend;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.api.RecommendInfoAPI;
import com.bjike.goddess.interiorrecommend.dto.RecommendInfoDTO;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.RecommendInfoTO;
import com.bjike.goddess.interiorrecommend.vo.RecommendContentVO;
import com.bjike.goddess.interiorrecommend.vo.RecommendInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 推荐信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 02:54 ]
 * @Description: [ 推荐信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("recommendinfo")
public class RecommendInfoAct {

    @Autowired
    private RecommendInfoAPI recommendInfoAPI;

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

            Boolean isHasPermission = recommendInfoAPI.guidePermission(guidePermissionTO);
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
     * 新增
     *
     * @param to 推荐信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) RecommendInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            RecommendInfoVO vo = BeanTransform.copyProperties(recommendInfoAPI.addModel(to), RecommendInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 推荐信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) RecommendInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            RecommendInfoVO vo = BeanTransform.copyProperties(recommendInfoAPI.editModel(to), RecommendInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 采纳审核
     *
     * @param id     推荐信息id
     * @param reason 原因
     * @param accept 是否采纳
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/acceptAudit")
    public Result acceptAudit(String id, String reason, Boolean accept) throws ActException {
        try {
            recommendInfoAPI.acceptAudit(id, reason, accept);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 奖励审核
     *
     * @param id 推荐信息id
     * @param id 推荐信息id
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/audit")
    public Result conformAudit(String id, Boolean conform) throws ActException {
        try {
            recommendInfoAPI.conformAudit(id, conform);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 推荐信息id
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            recommendInfoAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 分页条件
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result pageList(RecommendInfoDTO dto) throws ActException {
        try {
            List<RecommendInfoVO> voList = BeanTransform.copyProperties(recommendInfoAPI.pageList(dto), RecommendInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}