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

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * 待确认薪资
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-16 03:22 ]
 * @Description: [ 待确认薪资 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("waitconfirm")
public class WaitConfirmAct {

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
            dto.getConditions().add(Restrict.eq("findType", FindType.WAIT));
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
            dto.getConditions().add(Restrict.eq("findType", FindType.WAIT));
            Long count = salaryconfirmAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 确认薪资
     *
     * @param id 薪资审核确认Id
     * @version v1
     */
    @PatchMapping("v1/confirm/{id}")
    public Result confirm(@PathVariable String id) throws ActException {
        try {
            salaryconfirmAPI.confirm(id);
            return new ActResult("确认成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
