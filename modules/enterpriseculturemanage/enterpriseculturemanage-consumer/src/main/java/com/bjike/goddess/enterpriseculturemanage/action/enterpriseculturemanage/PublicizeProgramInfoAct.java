package com.bjike.goddess.enterpriseculturemanage.action.enterpriseculturemanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.enterpriseculturemanage.api.EnterpriseCultureInfoAPI;
import com.bjike.goddess.enterpriseculturemanage.api.PublicizeProgramInfoAPI;
import com.bjike.goddess.enterpriseculturemanage.dto.PublicizeProgramInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.enums.AuditResult;
import com.bjike.goddess.enterpriseculturemanage.to.GuidePermissionTO;
import com.bjike.goddess.enterpriseculturemanage.to.PublicizeProgramInfoTO;
import com.bjike.goddess.enterpriseculturemanage.vo.EnterpriseCultureInfoVO;
import com.bjike.goddess.enterpriseculturemanage.vo.PublicizeProgramInfoVO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 宣传方案信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 05:28 ]
 * @Description: [ 宣传方案信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("publicizeprograminfo")
public class PublicizeProgramInfoAct {

    @Autowired
    private PublicizeProgramInfoAPI publicizeProgramInfoAPI;
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

            Boolean isHasPermission = publicizeProgramInfoAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询宣传方案信息
     *
     * @param id 宣传方案信息id
     * @return class PublicizeProgramInfoVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            PublicizeProgramInfoVO vo = BeanTransform.copyProperties(publicizeProgramInfoAPI.findById(id), PublicizeProgramInfoVO.class, request);
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
    public Result count(PublicizeProgramInfoDTO dto) throws ActException {
        try {
            Long count = publicizeProgramInfoAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增宣传方案信息
     *
     * @param to 宣传方案信息
     * @return class PublicizeProgramInfoVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) PublicizeProgramInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            PublicizeProgramInfoVO vo = BeanTransform.copyProperties(publicizeProgramInfoAPI.addModel(to), PublicizeProgramInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑宣传方案信息
     *
     * @param to 宣传方案信息
     * @return class PublicizeProgramInfoVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) PublicizeProgramInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            PublicizeProgramInfoVO vo = BeanTransform.copyProperties(publicizeProgramInfoAPI.editModel(to), PublicizeProgramInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核宣传方案信息
     *
     * @param id              id
     * @param auditResult     审核结果
     * @param auditSuggestion 审核意见
     * @version v1
     */
    @PutMapping("v1/audit")
    public Result audit(@RequestParam String id, @RequestParam AuditResult auditResult, @RequestParam String auditSuggestion) throws ActException {
        try {
            publicizeProgramInfoAPI.audit(id, auditResult, auditSuggestion);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除宣传方案信息
     *
     * @param id 宣传方案信息id
     * @version v1
     */
    @PutMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            publicizeProgramInfoAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 宣传方案信息分页查询
     *
     * @param dto 分页条件
     * @return class PublicizeProgramInfoVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(PublicizeProgramInfoDTO dto) throws ActException {
        try {
            List<PublicizeProgramInfoVO> voList = BeanTransform.copyProperties(publicizeProgramInfoAPI.pageList(dto), PublicizeProgramInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}