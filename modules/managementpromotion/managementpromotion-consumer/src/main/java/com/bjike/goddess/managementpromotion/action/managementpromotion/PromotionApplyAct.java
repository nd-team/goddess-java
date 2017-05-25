package com.bjike.goddess.managementpromotion.action.managementpromotion;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managementpromotion.api.GradeLevelAPI;
import com.bjike.goddess.managementpromotion.api.PromotionApplyAPI;
import com.bjike.goddess.managementpromotion.bo.PromotionApplyBO;
import com.bjike.goddess.managementpromotion.dto.PromotionApplyDTO;
import com.bjike.goddess.managementpromotion.to.PromotionApplyTO;
import com.bjike.goddess.managementpromotion.vo.PromotionApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 管理等级晋升申请
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 02:04 ]
 * @Description: [ 管理等级晋升申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("promotionapply")
public class PromotionApplyAct {
    @Autowired
    private PromotionApplyAPI promotionApplyAPI;
    @Autowired
    private GradeLevelAPI gradeLevelAPI;

    /**
     * 管理等级晋升申请列表总条数
     *
     * @param dto 管理等级晋升申请dto
     * @des 获取所有管理等级晋升申请总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(PromotionApplyDTO dto) throws ActException {
        try {
            Long count = promotionApplyAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个管理等级晋升申请
     *
     * @param id id
     * @return class PromotionApplyVO
     * @des 获取一个管理等级晋升申请
     * @version v1
     */
    @GetMapping("v1/promotionapply/{id}")
    public Result promotionapply(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            PromotionApplyBO bo = promotionApplyAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, PromotionApplyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 管理等级晋升申请列表
     *
     * @param dto 管理等级晋升申请信息dto
     * @return class PromotionApplyVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(PromotionApplyDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<PromotionApplyVO> VOS = BeanTransform.copyProperties
                    (promotionApplyAPI.find(dto), PromotionApplyVO.class, request);
            return ActResult.initialize(VOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加管理等级晋升申请信息
     *
     * @param to 管理等级晋升申请信息数据to
     * @return class PromotionApplyVO
     * @version v1
     */
  //  @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) PromotionApplyTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            PromotionApplyBO bo = promotionApplyAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, PromotionApplyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑管理等级晋升申请信息
     *
     * @param to 管理等级晋升申请信息数据to
     * @return class PromotionApplyVO
     * @des 编辑管理等级晋升申请信息
     * @version v1
     */
  //  @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) PromotionApplyTO to, BindingResult bindingResult) throws ActException {
        try {
            promotionApplyAPI.edit(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除管理等级晋升申请信息
     *
     * @param id 用户id
     * @des 根据用户id删除管理等级晋升申请信息记录
     * @version v1
     */
   // @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            promotionApplyAPI.delete(id);
            return new ActResult("删除成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 规划填写是否符合晋升条件
     *
     * @param to 管理等级晋升申请to
     * @throws ActException
     * @version v1
     */
  //  @LoginAuth
    @PatchMapping("v1/conform")
        public Result conform(@Validated(PromotionApplyTO.Conform.class) PromotionApplyTO to, BindingResult bindingResult) throws ActException {
        try {
            promotionApplyAPI.conform(to);
            return new ActResult("填写成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 综合素养模块填写晋升标准达标数
     *
     * @param to 管理等级晋升申请to
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/writePromotionCriteria")
    public Result writePromotionCriteria(@Validated(PromotionApplyTO.PromotionCriteria.class) PromotionApplyTO to, BindingResult bindingResult) throws ActException {
        try {
            promotionApplyAPI.writePromotionCriteria(to);
            return new ActResult("填写成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目经理审核
     *
     * @param to 管理等级晋升申请to
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/writeProjectManager")
    public Result writeProjectManager(@Validated(PromotionApplyTO.ProjectManager.class) PromotionApplyTO to, BindingResult bindingResult) throws ActException {
        try {
            promotionApplyAPI.writeProjectManager(to);
            return new ActResult("填写成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 综合资源部规划模块审核
     *
     * @param to 管理等级晋升申请to
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/writeResourceDepartment")
    public Result writeResourceDepartment(@Validated(PromotionApplyTO.ResourceDepartment.class) PromotionApplyTO to, BindingResult bindingResult) throws ActException {
        try {
            promotionApplyAPI.writeResourceDepartment(to);
            return new ActResult("填写成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 运营商务部审核
     *
     * @param to 管理等级晋升申请to
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/writeCommerceDepartment")
    public Result writeCommerceDepartment(@Validated(PromotionApplyTO.CommerceDepartment.class) PromotionApplyTO to, BindingResult bindingResult) throws ActException {
        try {
            promotionApplyAPI.writeCommerceDepartment(to);
            return new ActResult("填写成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 模块负责人审核
     *
     * @param to 管理等级晋升申请to
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/writeModuler")
    public Result writeModuler(@Validated(PromotionApplyTO.Moduler.class) PromotionApplyTO to, BindingResult bindingResult) throws ActException {
        try {
            promotionApplyAPI.writeModuler(to);
            return new ActResult("填写成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办审核和填写本次晋升等级获得时间
     *
     * @param to 管理等级晋升申请to
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/writeManager")
    public Result writeManager(@Validated(PromotionApplyTO.Manager.class) PromotionApplyTO to, BindingResult bindingResult) throws ActException {
        try {
            promotionApplyAPI.writeManager(to);
            return new ActResult("填写成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看晋升申请排名
     *
     * @return class PromotionApplyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/rank")
    public Result rank(HttpServletRequest request) throws ActException {
        try {
            List<PromotionApplyBO> list = promotionApplyAPI.rank();
            return ActResult.initialize(BeanTransform.copyProperties(list, PromotionApplyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有体系部门
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allDepartments")
    public Result allDepartments() throws ActException {
        try {
            Set<String> set = gradeLevelAPI.allDepartments();
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}