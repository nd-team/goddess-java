package com.bjike.goddess.contractware.action.contractware;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.api.InvoiceManagementCollectAPI;
import com.bjike.goddess.contractware.bo.InvoiceCollectBO;
import com.bjike.goddess.contractware.bo.InvoiceYearCollectBO;
import com.bjike.goddess.contractware.bo.OptionBO;
import com.bjike.goddess.contractware.dto.InvoiceManagementCollectDTO;
import com.bjike.goddess.contractware.to.GuidePermissionTO;
import com.bjike.goddess.contractware.vo.InvoiceCollectVO;
import com.bjike.goddess.contractware.vo.InvoiceYearCollectVO;
import com.bjike.goddess.contractware.vo.OptionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
* 发票管理汇总
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-02 09:12 ]
* @Description:	[ 发票管理汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("invoicemanagementcollect")
public class InvoiceManagementCollectAction {
    @Autowired
    private InvoiceManagementCollectAPI invoiceManagementCollectAPI;

    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = invoiceManagementCollectAPI.guidePermission(guidePermissionTO);
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
     * 月汇总
     * @param invoiceManagementCollectDTO 汇总条件
     * @return class InvoiceCollectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect(InvoiceManagementCollectDTO invoiceManagementCollectDTO) throws ActException{
        try {
            InvoiceCollectBO invoiceCollectBO = invoiceManagementCollectAPI.monthCollect(invoiceManagementCollectDTO);
            InvoiceCollectVO invoiceCollectVO = BeanTransform.copyProperties(invoiceCollectBO,InvoiceCollectVO.class);
            return ActResult.initialize(invoiceCollectVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 年汇总
     * @param invoiceManagementCollectDTO 汇总条件
     * @return class InvoiceCollectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/yearCollect")
    public Result yearCollect(InvoiceManagementCollectDTO invoiceManagementCollectDTO) throws ActException{
        try {
            InvoiceYearCollectBO invoiceYearCollectBO = invoiceManagementCollectAPI.yearCollect(invoiceManagementCollectDTO);
            InvoiceYearCollectVO invoiceYearCollectVO = BeanTransform.copyProperties(invoiceYearCollectBO,InvoiceYearCollectVO.class);
            return ActResult.initialize(invoiceYearCollectVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 发票管理图形化
     * @param year 年份
     * @return class OptionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/figureShow")
    public Result figureShowYear(Integer year) throws ActException{
        try {
            OptionBO optionBO = invoiceManagementCollectAPI.figureShowYear(year);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO,OptionVO.class);
            return ActResult.initialize(optionVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


 }