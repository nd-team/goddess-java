package com.bjike.goddess.organize.action.organize;

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
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("departmentDetail")
public class DepartmentDetailAct {

    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

    /**
     * 保存部门项目组详细信息
     *
     * @param to 部门项目组传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated DepartmentDetailTO to) throws ActException {
        try {
            DepartmentDetailVO vo = BeanTransform.copyProperties(departmentDetailAPI.save(to), DepartmentDetailVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改部门项目组详细信息
     *
     * @param to 部门项目组传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated DepartmentDetailTO to) throws ActException {
        try {
            DepartmentDetailVO vo = BeanTransform.copyProperties(departmentDetailAPI.update(to), DepartmentDetailVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未冻结部门项目组详细信息
     *
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.findStatus(), DepartmentDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
