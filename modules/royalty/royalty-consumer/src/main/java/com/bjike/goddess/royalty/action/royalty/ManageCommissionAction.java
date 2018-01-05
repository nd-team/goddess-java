package com.bjike.goddess.royalty.action.royalty;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.royalty.api.IndexLibraryAPI;
import com.bjike.goddess.royalty.api.JobsBetAPI;
import com.bjike.goddess.royalty.bo.IndexLibraryBO;
import com.bjike.goddess.royalty.bo.ManageCommissionBO;
import com.bjike.goddess.royalty.dto.IndexLibraryDTO;
import com.bjike.goddess.royalty.dto.JobsBetADTO;
import com.bjike.goddess.royalty.entity.IndexLibrary;
import com.bjike.goddess.royalty.to.CollectTO;
import com.bjike.goddess.royalty.to.GuidePermissionTO;
import com.bjike.goddess.royalty.to.IndexLibraryTO;
import com.bjike.goddess.royalty.vo.IndexLibraryVO;
import com.bjike.goddess.royalty.vo.ManageCommissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 总呈现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 11:23 ]
 * @Description: [ 总呈现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("managecommission")
public class ManageCommissionAction {
    @Autowired
    private JobsBetAPI jobsBetAPI;
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

            Boolean isHasPermission = jobsBetAPI.guidePermission(guidePermissionTO);
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
     * 对赌指标总呈现
     *
     * @param to to
     * @return class ManageCommissionVO
     * @des 根据项目名称呈现
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(@Validated(CollectTO.TestCollect.class) CollectTO to) throws ActException {
        try {
            List<ManageCommissionVO> manageCommissionVOS = BeanTransform.copyProperties(jobsBetAPI.collect(to),ManageCommissionVO.class);
            return ActResult.initialize(manageCommissionVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取项目名称
     *
     * @des 获取项目名称
     * @version v1
     */
    @GetMapping("v1/projectName")
    public Result projectName() throws ActException {
        try {
            List<String> projectName = jobsBetAPI.getProjectName();
            return ActResult.initialize(projectName);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}