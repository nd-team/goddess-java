package com.bjike.goddess.socialinsurance.action.socialinsurance;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.socialinsurance.api.SocialInsuranceCollectAPI;
import com.bjike.goddess.socialinsurance.bo.SocialInsuranceCollectBO;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceCollectDTO;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceVoucherDTO;
import com.bjike.goddess.socialinsurance.to.GuidePermissionTO;
import com.bjike.goddess.socialinsurance.to.SocialInsuranceVoucherTO;
import com.bjike.goddess.socialinsurance.vo.SocialInsuranceCollectVO;
import com.bjike.goddess.socialinsurance.vo.SocialInsuranceVoucherVO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.to.VoucherGenerateTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成记账凭证
 * @Author: [caiwenxian]
 * @Date: [2017-12-21 17:20]
 * @Description: [ 生成记账凭证 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@RestController
@RequestMapping("createvoucher")
public class CreateVoucherAction {

    @Autowired
    SocialInsuranceCollectAPI socialInsuranceCollectAPI;

    @Autowired
    VoucherGenerateAPI voucherGenerateAPI;

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

            Boolean isHasPermission = socialInsuranceCollectAPI.guidePermission(guidePermissionTO);
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
     * 社保缴费汇总
     *
     * @param dto dto
     * @return class SocialInsuranceVoucherVO
     * @version v1
     */
    @GetMapping("v1/department")
    public Result departmentCollect(SocialInsuranceVoucherDTO dto, BindingResult bindingResult) throws ActException {
        try {
            LocalDate start = LocalDate.of(dto.getYear(),  dto.getMonth(), 01);
            LocalDate end = start.with(TemporalAdjusters.lastDayOfMonth());

            SocialInsuranceCollectDTO newDto = new SocialInsuranceCollectDTO();
            newDto.setStartDate(start.toString());
            newDto.setEndDate(end.toString());
            newDto.setDepartment(dto.getDepartment());
            List<SocialInsuranceCollectBO> bos = socialInsuranceCollectAPI.voucherCollect(newDto);
            List<SocialInsuranceVoucherVO> vos = new ArrayList<>();

            if (bos.size() != 0) {
                vos = BeanTransform.copyProperties(bos, SocialInsuranceVoucherVO.class);
            }
            for (SocialInsuranceVoucherVO vo : vos) {
                vo.setYear(dto.getYear());
                vo.setMonth(dto.getMonth());
            }
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 生成记账凭证
     *
     * @param to to
     * @return class
     * @version v1
     */
    @PostMapping("v1/createVoucher")
    public Result areaCollect(@Validated(SocialInsuranceVoucherTO.Add.class)SocialInsuranceVoucherTO to, BindingResult bindingResult) throws ActException {
        try {
            //修改社保合同付款状态
            LocalDate start = LocalDate.of(to.getYear(), to.getMonth(), 01);
            LocalDate end = start.with(TemporalAdjusters.lastDayOfMonth());
            socialInsuranceCollectAPI.updateSocialInsurancePayStatus(to.getDepartment(), start.toString(), end.toString());

            //添加记账凭证
            VoucherGenerateTO voucherGenerateTO = new VoucherGenerateTO();
            LocalDate voucherDate = LocalDate.of(to.getYear(), to.getMonth(), 01);
            voucherDate = voucherDate.with(TemporalAdjusters.lastDayOfMonth());
            List firstSubjects = new ArrayList();
            firstSubjects.add("应付职工薪酬");
            List secondSubjects = new ArrayList();
            secondSubjects.add("社保费");
            List thirdSubjects = new ArrayList();
            thirdSubjects.add(to.getDepartment());
            List borrowMoneys = new ArrayList();
            borrowMoneys.add(to.getAmountDue());
            voucherGenerateTO.setIds(to.getIds());
            voucherGenerateTO.setVoucherWord("记");
            voucherGenerateTO.setVoucherDate(voucherDate.toString());
            voucherGenerateTO.setFirstSubjects(firstSubjects);
            voucherGenerateTO.setSecondSubjects(secondSubjects);
            voucherGenerateTO.setThirdSubjects(thirdSubjects);
            voucherGenerateTO.setBorrowMoneys(borrowMoneys);
            voucherGenerateTO.setSumary("支付"+ to.getYear() +"年"+ to.getMonth() +"月社保费");
            voucherGenerateTO.setSource("北京基本户");
            voucherGenerateTO.setArea("北京");
            voucherGenerateTO.setProjectName("项目名称");
            voucherGenerateTO.setProjectGroup("项目组");
            voucherGenerateTO.setTicketNum(1.0);
            voucherGenerateAPI.addVoucherGenerate(voucherGenerateTO);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
