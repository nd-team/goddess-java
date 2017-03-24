package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.WorkRangeAPI;
import com.bjike.goddess.organize.dto.WorkRangeDTO;
import com.bjike.goddess.organize.to.DepartmentWorkRangeTO;
import com.bjike.goddess.organize.to.WorkRangeTO;
import com.bjike.goddess.organize.vo.WorkRangeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 工作范围信息设置操作
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("workRange")
public class WorkRangeAct {

    @Autowired
    private WorkRangeAPI workRangeAPI;


    /**
     * 查询部门工作范围信息详细
     *
     * @param department_id 部门ID
     * @param dto           部门工作范围数据传输
     * @return class WorkRangeVO
     * @version v1
     */
    @GetMapping("v1/findDepartmentWorkRangeView")
    public Result findDepartmentWorkRangeView(String department_id, WorkRangeDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findDepartmentWorkRangeView(department_id, dto), WorkRangeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据部门详细ID查询工作范围信息
     *
     * @param departmentId 部门详细ID
     * @return class WorkRangeVO
     * @version v1
     */
    @GetMapping("v1/findByDepartment")
    public Result findByDepartment(String departmentId) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findByDepartment(departmentId), WorkRangeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据工作范围ID查询部门详细信息
     *
     * @param rangeId 工作范围ID
     * @return class WorkRangeVO
     * @version v1
     */
    @GetMapping("v1/findByRange")
    public Result findByRange(String rangeId) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findByRange(rangeId), WorkRangeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 部门详细增加工作范围
     *
     * @param to 部门工作范围传输对象
     * @version v1
     */
    @PostMapping("v1/departmentAddRange")
    public Result departmentAddRange(DepartmentWorkRangeTO to) throws ActException {
        try {
            workRangeAPI.departmentAddRange(to);
            return ActResult.initialize(BeanTransform.copyProperties(to, WorkRangeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据方向查询工作范围
     *
     * @param direction 方向
     * @return class WorkRangeVO
     * @version v1
     */
    @GetMapping("v1/findByDirection")
    public Result findByDirection(String direction) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findByDirection(direction), WorkRangeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据方向和科目查询工作范围
     *
     * @param direction 方向
     * @param project   科目
     * @return class WorkRangeVO
     * @version v1
     */
    @GetMapping("v1/findByDirectionProject")
    public Result findByDirectionProject(String direction, String project) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findByDirectionProject(direction, project), WorkRangeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据科目查询工作范围
     *
     * @param project 科目
     * @return class WorkRangeVO
     * @version v1
     */
    @GetMapping("v1/findByProject")
    public Result findByProject(String project) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findByProject(project), WorkRangeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存工作范围信息
     *
     * @param to 工作范围传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(WorkRangeTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.save(to), WorkRangeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改工作范围信息
     *
     * @param to 工作范围传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@PathVariable String id, WorkRangeTO to) throws ActException {
        try {
            to.setId(id);
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.update(to), WorkRangeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
