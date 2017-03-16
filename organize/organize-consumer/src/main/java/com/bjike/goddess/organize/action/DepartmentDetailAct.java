package com.bjike.goddess.organize.action;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.to.DepartmentDetailTO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门详细操作
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:54]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("{version}/departmentDetail")
public class DepartmentDetailAct {

    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

    @PostMapping("save")
    public Result save(@Validated DepartmentDetailTO to) throws ActException {
        try {
            DepartmentDetailVO vo = BeanTransform.copyProperties(departmentDetailAPI.save(to), DepartmentDetailVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @GetMapping("findStatus")
    public Result findStatus() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.findStatus(), DepartmentDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
