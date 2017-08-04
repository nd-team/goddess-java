package com.bjike.goddess.checkhost.action.checkhost;

import com.bjike.goddess.checkhost.api.StayDaysAPI;
import com.bjike.goddess.checkhost.bo.StayDaysBO;
import com.bjike.goddess.checkhost.dto.StayDaysDTO;
import com.bjike.goddess.checkhost.enums.CheckStatus;
import com.bjike.goddess.checkhost.to.GuidePermissionTO;
import com.bjike.goddess.checkhost.to.StayDaysTO;
import com.bjike.goddess.checkhost.vo.CollectNameVO;
import com.bjike.goddess.checkhost.vo.StayDaysVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = stayDaysAPI.guidePermission(guidePermissionTO);
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
     * 员工住宿天数汇总列表总条数
     *
     * @param stayDaysDTO 员工住宿天数汇总dto
     * @des 获取所有员工住宿天数汇总总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(StayDaysDTO stayDaysDTO) throws ActException {
        try {
            Long count = stayDaysAPI.countStayDays(stayDaysDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个员工住宿天数汇总
     *
     * @param id
     * @return class StayDaysVO
     * @des 获取一个员工住宿天数汇总
     * @version v1
     */
    @GetMapping("v1/days/{id}")
    public Result days(@PathVariable String id) throws ActException {
        try {
            StayDaysBO stayDaysBO = stayDaysAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(stayDaysBO, StayDaysVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 员工住宿天数汇总列表
     *
     * @param stayDaysDTO 员工住宿天数汇总dto
     * @return class StayDaysVO
     * @des 获取所有员工住宿天数汇总
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(StayDaysDTO stayDaysDTO, HttpServletRequest request) throws ActException {
        try {
            List<StayDaysVO> stayDaysVOS = BeanTransform.copyProperties
                    (stayDaysAPI.findListStayDays(stayDaysDTO), StayDaysVO.class, request);
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
    public Result add(@Validated(ADD.class) StayDaysTO stayDaysTO, BindingResult bindingResult) throws ActException {
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
    public Result edit(@Validated(EDIT.class) StayDaysTO stayDaysTO, BindingResult bindingResult) throws ActException {
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
    public Result delete(@PathVariable String id) throws ActException {
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
     * @param id          id
     * @param dto dto dto
     * @return class StayDaysVO
     * @des 审核员工住宿天数汇总
     * @version v1
     */
    @PostMapping("v1/audit/{id}")
    public Result audit(@PathVariable String id, @Validated(StayDaysDTO.AUDIT.class) StayDaysDTO dto,BindingResult result) throws ActException {
        try {
            StayDaysBO stayDaysBO = stayDaysAPI.auditStayDays(id, dto);
            return ActResult.initialize(BeanTransform.copyProperties(stayDaysBO, StayDaysVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总名字员工住宿天数汇总
     *
     * @param names 名字
     * @return class CollectNameVO
     * @des 汇总员工住宿天数汇总
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(@RequestParam String[] names) throws ActException {
        try {
            List<CollectNameVO> collectNameVOS = BeanTransform.copyProperties(
                    stayDaysAPI.collectName(names), CollectNameVO.class);
            return ActResult.initialize(collectNameVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取名字
     *
     * @des 获取名字集合
     * @version v1
     */
    @GetMapping("v1/name")
    public Result name() throws ActException {
        try {
            List<String> namesList = stayDaysAPI.getNames();
            return ActResult.initialize(namesList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}