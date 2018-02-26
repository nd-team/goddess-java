package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.ArrestPointAPI;
import com.bjike.goddess.attendance.bo.ArrestPointBO;
import com.bjike.goddess.attendance.dto.ArrestPointDTO;
import com.bjike.goddess.attendance.to.ArrestPointTO;
import com.bjike.goddess.attendance.vo.ArrestPointVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.vo.AreaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 驻点设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:12 ]
 * @Description: [ 驻点设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("arrestpoint")
public class ArrestPointAction {
    @Autowired
    private ArrestPointAPI arrestPointAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

    /**
     * 列表
     *
     * @param dto 驻点设置数据传输
     * @return class ArrestPointVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ArrestPointDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ArrestPointBO> list = arrestPointAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ArrestPointVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 驻点设置传输对象
     * @return class ArrestPointVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ArrestPointTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ArrestPointBO bo = arrestPointAPI.save(to);
            return ActResult .initialize(BeanTransform.copyProperties(bo, ArrestPointVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 驻点设置id
     * @return class ArrestPointVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/arrestPoint/{id}")
    public Result ArrestPoint(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ArrestPointBO bo = arrestPointAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ArrestPointVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 驻点设置传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ArrestPointTO to, BindingResult result) throws ActException {
        try {
            arrestPointAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 驻点设置id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            arrestPointAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 驻点设置数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ArrestPointDTO dto) throws ActException {
        try {
            return ActResult.initialize(arrestPointAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 启用
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/start/{id}")
    public Result start(@PathVariable String id) throws ActException {
        try {
            arrestPointAPI.start(id);
            return new ActResult("启用成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 禁用
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/stop/{id}")
    public Result stop(@PathVariable String id) throws ActException {
        try {
            arrestPointAPI.stop(id);
            return new ActResult("禁用成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有驻点地点
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/pointAreas")
    public Result pointAreas() throws ActException {
        try {
            return ActResult.initialize(arrestPointAPI.pointAreas());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有地区
     *
     * @return class AreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areas")
    public Result areas(HttpServletRequest request) throws ActException {
        try {
            List<AreaBO> areaBOS = departmentDetailAPI.findArea();
            return ActResult.initialize(BeanTransform.copyProperties(areaBOS, AreaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}