package com.bjike.goddess.enterpriseculturemanage.action.enterpriseculturemanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.enterpriseculturemanage.api.EnterpriseCultureInfoAPI;
import com.bjike.goddess.enterpriseculturemanage.api.PeriodicalProgramInfoAPI;
import com.bjike.goddess.enterpriseculturemanage.dto.PeriodicalProgramInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.enums.AuditResult;
import com.bjike.goddess.enterpriseculturemanage.to.GuidePermissionTO;
import com.bjike.goddess.enterpriseculturemanage.to.PeriodicalProgramInfoTO;
import com.bjike.goddess.enterpriseculturemanage.vo.EnterpriseCultureInfoVO;
import com.bjike.goddess.enterpriseculturemanage.vo.PeriodicalProgramInfoVO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 刊物方案信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-01 09:07 ]
 * @Description: [ 刊物方案信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("periodicalprograminfo")
public class PeriodicalProgramInfoAct {

    @Autowired
    private PeriodicalProgramInfoAPI periodicalProgramInfoAPI;
    @Autowired
    private EnterpriseCultureInfoAPI enterpriseCultureInfoAPI;
    @Autowired
    private PositionDetailUserAPI detailUserAPI;

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

            Boolean isHasPermission = periodicalProgramInfoAPI.guidePermission(guidePermissionTO);
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
     * 查询用户列表
     *
     * @return class UserVO
     * @version v1
     */
    @PostMapping("v1/findUserInfo")
    public Result findUserInfo(HttpServletRequest request) throws ActException {
        try {
            List<UserVO> voList = BeanTransform.copyProperties(detailUserAPI.findUserList(), UserVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 企业文化信息列表
     *
     * @return class EnterpriseCultureInfoVO
     * @version v1
     */
    @GetMapping("v1/infos")
    public Result infos(HttpServletRequest request) throws ActException {
        try {
            List<EnterpriseCultureInfoVO> vo = BeanTransform.copyProperties(enterpriseCultureInfoAPI.infos(), EnterpriseCultureInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询刊物方案信息
     *
     * @param id 刊物方案信息id
     * @return class PeriodicalProgramInfoVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            PeriodicalProgramInfoVO vo = BeanTransform.copyProperties(periodicalProgramInfoAPI.findById(id), PeriodicalProgramInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(PeriodicalProgramInfoDTO dto) throws ActException {
        try {
            Long count = periodicalProgramInfoAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增刊物方案信息
     *
     * @param to 刊物方案信息
     * @return class PeriodicalProgramInfoVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) PeriodicalProgramInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            PeriodicalProgramInfoVO vo = BeanTransform.copyProperties(periodicalProgramInfoAPI.addModel(to), PeriodicalProgramInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑刊物方案信息
     *
     * @param to 刊物方案信息
     * @return class PeriodicalProgramInfoVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) PeriodicalProgramInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            PeriodicalProgramInfoVO vo = BeanTransform.copyProperties(periodicalProgramInfoAPI.editModel(to), PeriodicalProgramInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核刊物方案信息
     *
     * @param id              id
     * @param auditResult     审核结果
     * @param auditSuggestion 审核意见
     * @version v1
     */
    @PutMapping("v1/audit")
    public Result audit(@RequestParam String id, @RequestParam AuditResult auditResult, @RequestParam String auditSuggestion) throws ActException {
        try {
            periodicalProgramInfoAPI.audit(id, auditResult, auditSuggestion);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除刊物方案信息
     *
     * @param id 刊物方案信息id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            periodicalProgramInfoAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 分页条件
     * @return class PeriodicalProgramInfoVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(PeriodicalProgramInfoDTO dto) throws ActException {
        try {
            List<PeriodicalProgramInfoVO> voList = BeanTransform.copyProperties(periodicalProgramInfoAPI.pageList(dto), PeriodicalProgramInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}