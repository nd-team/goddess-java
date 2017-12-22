package com.bjike.goddess.businessabilityshow.action.businessabilityshow;

import com.bjike.goddess.businessabilityshow.api.BusinessAbilityAPI;
import com.bjike.goddess.businessabilityshow.bo.BACollectEchartBO;
import com.bjike.goddess.businessabilityshow.bo.BusinessAbilityBO;
import com.bjike.goddess.businessabilityshow.bo.BusinessAbilityCollectBO;
import com.bjike.goddess.businessabilityshow.dto.BusinessAbilityDTO;
import com.bjike.goddess.businessabilityshow.to.GuidePermissionTO;
import com.bjike.goddess.businessabilityshow.vo.BACollectEchartVO;
import com.bjike.goddess.businessabilityshow.vo.BusinessAbilityCollectVO;
import com.bjike.goddess.businessabilityshow.vo.BusinessAbilityVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商业能力汇总
 *
 * @Author: [caiwenxian]
 * @Date: [2017-12-16 17:07]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Controller
@RequestMapping("businessabilitycollect")
public class BusinessAbilityCollectAction {

    @Autowired
    BusinessAbilityAPI businessAbilityAPI;

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

            Boolean isHasPermission = businessAbilityAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取商业能力展示列表
     *
     * @param dto dto
     * @return class BusinessAbilityCollectVO
     * @version v1
     */
    @GetMapping("v1/businessAbility")
    @LoginAuth
    public Result businessAbilityCollect(BusinessAbilityDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<BusinessAbilityCollectBO> bos = businessAbilityAPI.businessAbilitycollect();
            List<BusinessAbilityCollectVO> vos = BeanTransform.copyProperties(bos, BusinessAbilityCollectVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取商业能力展示图形数据
     *
     * @param dto dto
     * @return class BACollectEchartVO
     * @version v1
     */
    @GetMapping("v1/businessAbilityEchart")
    @LoginAuth
    public Result businessAbilityEchartCollect(BusinessAbilityDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<BACollectEchartBO> bos = businessAbilityAPI.businessAbilityEchartCollect();
            List<BACollectEchartVO> vos = BeanTransform.copyProperties(bos, BACollectEchartVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取公司参与项目情况图形数据
     *
     * @param dto dto
     * @return class BACollectEchartVO
     * @version v1
     */
    @GetMapping("v1/businessProjectEchart")
    @LoginAuth
    public Result businessProjectEchartCollect(BusinessAbilityDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<BACollectEchartBO> bos = businessAbilityAPI.businessProjectEchartcollect();
            List<BACollectEchartVO> vos = BeanTransform.copyProperties(bos, BACollectEchartVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
