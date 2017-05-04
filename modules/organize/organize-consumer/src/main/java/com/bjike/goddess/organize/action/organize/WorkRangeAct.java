package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.WorkRangeAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.dto.WorkRangeDTO;
import com.bjike.goddess.organize.to.DepartmentWorkRangeTO;
import com.bjike.goddess.organize.to.WorkRangeTO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.organize.vo.WorkRangeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
     * 查询部门工作范围信息详细列表
     *
     * @param departmentId 部门ID
     * @param dto           部门工作范围数据传输
     * @return class WorkRangeVO
     * @version v1
     */
    @GetMapping("v1/findDepartmentWorkRangeView")
    public Result findDepartmentWorkRangeView(String departmentId, WorkRangeDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findDepartmentWorkRangeView(departmentId, dto), WorkRangeVO.class));
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
    public Result findByDepartment(String departmentId, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findByDepartment(departmentId), WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据工作范围ID查询部门详细信息
     *
     * @param rangeId 工作范围ID
     * @return class DepartmentDetailVO
     * @version v1
     */
    @GetMapping("v1/findByRange")
    public Result findByRange(String rangeId, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findByRange(rangeId), DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 部门详细增加工作范围
     *
     * @param to 部门工作范围传输对象
     * @return class WorkRangeVO
     * @version v1
     */
    @PostMapping("v1/departmentAddRange")
    public Result departmentAddRange(DepartmentWorkRangeTO to, HttpServletRequest request) throws ActException {
        try {
            workRangeAPI.departmentAddRange(to);
            return new ActResult("添加成功");
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
    public Result findByDirection(String direction, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findByDirection(direction), WorkRangeVO.class, request));
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
    public Result findByDirectionProject(String direction, String project, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findByDirectionProject(direction, project), WorkRangeVO.class, request));
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
    public Result findByProject(String project, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findByProject(project), WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存工作范围信息
     *
     * @param to 工作范围传输对象
     * @return class WorkRangeVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) WorkRangeTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.save(to), WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改工作范围信息
     *
     * @param to 工作范围传输对象
     * @return class WorkRangeVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) WorkRangeTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.update(to), WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 工作范围数据id
     * @return class WorkRangeVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.delete(id), WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param dto 工作范围数据传输
     * @return class WorkRangeVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(WorkRangeDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.maps(dto), WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(workRangeAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据ID查询工作范围
     *
     * @param id 工作范围数据id
     * @return class WorkRangeVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findById(id), WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
