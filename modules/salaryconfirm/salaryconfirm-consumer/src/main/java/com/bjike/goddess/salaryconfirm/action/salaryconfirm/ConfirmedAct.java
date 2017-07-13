package com.bjike.goddess.salaryconfirm.action.salaryconfirm;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.salaryconfirm.api.SalaryconfirmAPI;
import com.bjike.goddess.salaryconfirm.dto.SalaryconfirmDTO;
import com.bjike.goddess.salaryconfirm.enums.FindType;
import com.bjike.goddess.salaryconfirm.vo.SalaryconfirmVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 已确认薪资
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-16 03:22 ]
 * @Description: [ 已确认薪资 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("confirmed")
public class ConfirmedAct {

    @Autowired
    private SalaryconfirmAPI salaryconfirmAPI;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class SalaryconfirmVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(SalaryconfirmDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("findType", FindType.CONFIRM));
            List<SalaryconfirmVO> voList = BeanTransform.copyProperties(salaryconfirmAPI.pageList(dto), SalaryconfirmVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SalaryconfirmDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("findType", FindType.CONFIRM));
            Long count = salaryconfirmAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 第一次付款
     *
     * @param id 薪资审核确认Id
     * @version v1
     */
    @PatchMapping("v1/first/{id}")
    public Result first(@PathVariable String id) throws ActException {
        try {
            salaryconfirmAPI.firstPay(id);
            return new ActResult("第一次付款成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 第二次付款
     *
     * @param id 薪资审核确认Id
     * @version v1
     */
    @PatchMapping("v1/second/{id}")
    public Result second(@PathVariable String id) throws ActException {
        try {
            salaryconfirmAPI.secondPay(id);
            return new ActResult("第二次付款成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
