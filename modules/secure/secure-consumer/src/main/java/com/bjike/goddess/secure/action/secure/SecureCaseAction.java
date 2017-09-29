package com.bjike.goddess.secure.action.secure;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.api.SecureCaseAPI;
import com.bjike.goddess.secure.bo.SecureCaseBO;
import com.bjike.goddess.secure.bo.SecureCaseCollectBO;
import com.bjike.goddess.secure.dto.SecureCaseDTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.SecureCaseCollectTO;
import com.bjike.goddess.secure.vo.SecureCaseCollectVO;
import com.bjike.goddess.secure.vo.SecureCaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 社保购买情况（汇总明细表）
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-23 02:59 ]
 * @Description: [ 社保购买情况（汇总明细表） ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("securecase")
public class SecureCaseAction {
    @Autowired
    private SecureCaseAPI secureCaseAPI;

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

            Boolean isHasPermission = secureCaseAPI.guidePermission(guidePermissionTO);
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
     * 社保购买情况列表
     *
     * @param dto dto
     * @return class SecureCaseVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(SecureCaseDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<SecureCaseBO> bos = BeanTransform.copyProperties(secureCaseAPI.list(dto), SecureCaseVO.class, request);
            return ActResult.initialize(bos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 社保购买情况汇总
     *
     * @param to to
     * @return class SecureCaseCollectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(SecureCaseCollectTO to) throws ActException {
        try {
            List<SecureCaseCollectBO> bos = BeanTransform.copyProperties(secureCaseAPI.collect(to), SecureCaseCollectVO.class);
            return ActResult.initialize(bos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有地区
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/area")
    public Result area() throws ActException {
        try {
            Set<String> areas = secureCaseAPI.allArea();
            return ActResult.initialize(areas);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有项目组
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/projectGroup")
    public Result projectGroup() throws ActException {
        try {
            Set<String> projectGroups = secureCaseAPI.allProjectGroup();
            return ActResult.initialize(projectGroups);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有参保单位
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/unit")
    public Result unit() throws ActException {
        try {
            Set<String> units = secureCaseAPI.allUnit();
            return ActResult.initialize(units);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}