package com.bjike.goddess.foreigntax.action.foreigntax;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.api.IncomeInvoiceAPI;
import com.bjike.goddess.foreigntax.bo.IncomeInvoiceBO;
import com.bjike.goddess.foreigntax.dto.IncomeCollectDTO;
import com.bjike.goddess.foreigntax.dto.IncomeInvoiceDTO;
import com.bjike.goddess.foreigntax.to.DeleteFileTO;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.IncomeInvoiceTO;
import com.bjike.goddess.foreigntax.vo.CompareCollectVO;
import com.bjike.goddess.foreigntax.vo.IncomeInvoiceVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * 对比汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:45 ]
 * @Description: [ 对比汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("comparecollect")
public class CompareCollectAction {
    @Autowired
    private IncomeInvoiceAPI incomeInvoiceAPI;

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

            Boolean isHasPermission = incomeInvoiceAPI.guidePermission(guidePermissionTO);
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
     * 对比汇总
     *
     * @param dto
     * @return class CompareCollectVO
     * @des 根据开始时间结束时间对比汇总
     * @version v1
     */
    @GetMapping("v1/compareCollect")
    public Result compareCollect(@Validated(IncomeCollectDTO.collect.class) IncomeCollectDTO dto) throws ActException {
        try {
            List<CompareCollectVO> compareCollectVOS = BeanTransform.copyProperties(incomeInvoiceAPI.compareCollect(dto),CompareCollectVO.class);
            return ActResult.initialize(compareCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}