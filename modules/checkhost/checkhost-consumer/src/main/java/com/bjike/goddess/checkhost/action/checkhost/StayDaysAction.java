package com.bjike.goddess.checkhost.action.checkhost;

import com.bjike.goddess.checkhost.api.StayDaysAPI;
import com.bjike.goddess.checkhost.bo.StayDaysBO;
import com.bjike.goddess.checkhost.dto.StayDaysDTO;
import com.bjike.goddess.checkhost.to.StayDaysTO;
import com.bjike.goddess.checkhost.vo.StayDaysVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工住宿天数汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:03 ]
 * @Description: [ 员工住宿天数汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("staydays")
public class StayDaysAction {
    @Autowired
    private StayDaysAPI stayDaysAPI;
    /**
     * 获取员工住宿天数汇总
     *
     * @param stayDaysDTO 员工住宿天数汇总dto
     * @return class StayDaysVO
     * @des 获取所有员工住宿天数汇总
     * @version v1
     */
    @GetMapping("v1/listStayDays")
    public Result findListStayDays(StayDaysDTO stayDaysDTO) throws ActException {
        try {
            List<StayDaysVO> stayDaysVOS = BeanTransform.copyProperties
                    (stayDaysAPI.findListStayDays(stayDaysDTO),StayDaysVO.class);
            return ActResult.initialize(stayDaysVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加员工住宿天数汇总
     *
     * @param stayDaysTO 员工住宿天数汇总数据to
     * @return class StayDaysVO
     * @des 添加员工住宿天数汇总
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addStayDays(@Validated StayDaysTO stayDaysTO) throws ActException {
        try {
            StayDaysBO stayDaysBO = stayDaysAPI.insertStayDays(stayDaysTO);
            return ActResult.initialize(stayDaysBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑员工住宿天数汇总
     *
     * @param stayDaysTO 员工住宿天数汇总数据to
     * @return class StayDaysVO
     * @des 编辑员工住宿天数汇总
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editStayDays(@Validated StayDaysTO stayDaysTO) throws ActException {
        try {
            StayDaysBO stayDaysBO = stayDaysAPI.editStayDays(stayDaysTO);
            return ActResult.initialize(stayDaysBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除员工住宿天数汇总
     *
     * @param id 用户id
     * @des 根据用户id删除员工住宿天数汇总记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result removeStayDays(@PathVariable String id) throws ActException {
        try {
            stayDaysAPI.removeStayDays(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 审核
     *
     * @param stayDaysTO 员工住宿天数汇总数据bo
     * @return class StayDaysVO
     * @des 审核员工住宿天数汇总
     * @version v1
     */
    @PostMapping("v1/audit")
    public Result auditStayDays(@Validated StayDaysTO stayDaysTO) throws ActException {
        try {
            StayDaysBO stayDaysBO = stayDaysAPI.auditStayDays(stayDaysTO);
            return ActResult.initialize(BeanTransform.copyProperties(stayDaysBO, StayDaysVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}