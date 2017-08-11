package com.bjike.goddess.rentutilitiespay.action.rentutilitiespay;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentutilitiespay.api.StayUtilitiesAPI;
import com.bjike.goddess.rentutilitiespay.bo.CollectNameBO;
import com.bjike.goddess.rentutilitiespay.bo.RentPayBO;
import com.bjike.goddess.rentutilitiespay.bo.StayUtilitiesBO;
import com.bjike.goddess.rentutilitiespay.dto.StayUtilitiesDTO;
import com.bjike.goddess.rentutilitiespay.to.GuidePermissionTO;
import com.bjike.goddess.rentutilitiespay.to.RentPayTO;
import com.bjike.goddess.rentutilitiespay.to.StayUtilitiesTO;
import com.bjike.goddess.rentutilitiespay.vo.CollectAreaVO;
import com.bjike.goddess.rentutilitiespay.vo.CollectNameVO;
import com.bjike.goddess.rentutilitiespay.vo.RentPayVO;
import com.bjike.goddess.rentutilitiespay.vo.StayUtilitiesVO;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 员工住宿水电费
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-13 07:44 ]
 * @Description: [ 员工住宿水电费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("stayutilities")
public class StayUtilitiesAction {
    @Autowired
    private StayUtilitiesAPI stayUtilitiesAPI;
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

            Boolean isHasPermission = stayUtilitiesAPI.guidePermission(guidePermissionTO);
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
     * 员工住宿水电费列表总条数
     *
     * @param stayUtilitiesDTO 员工住宿水电费记录dto
     * @des 获取所有员工住宿水电费
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(StayUtilitiesDTO stayUtilitiesDTO) throws ActException {
        try {
            Long count = stayUtilitiesAPI.countStayUtilities(stayUtilitiesDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个员工住宿水电费
     *
     * @param id
     * @return class StayUtilitiesVO
     * @des 获取一个员工住宿水电费
     * @version v1
     */
    @GetMapping("v1/stay/{id}")
    public Result stay(@PathVariable String id) throws ActException {
        try {
            StayUtilitiesBO stayUtilitiesBO = stayUtilitiesAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(stayUtilitiesBO, StayUtilitiesVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取员工住宿水电费
     *
     * @param stayUtilitiesDTO 员工住宿水电费dto
     * @return class StayUtilitiesVO
     * @des 获取所有员工住宿水电费
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(StayUtilitiesDTO stayUtilitiesDTO, HttpServletRequest request) throws ActException {
        try {
            List<StayUtilitiesVO> stayUtilitiesVOS = BeanTransform.copyProperties
                    (stayUtilitiesAPI.findListStayUtilities(stayUtilitiesDTO),StayUtilitiesVO.class,request);
            return ActResult.initialize(stayUtilitiesVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加员工住宿水电费
     *
     * @param stayUtilitiesTO 员工住宿水电费数据to
     * @return class StayUtilitiesVO
     * @des 添加员工住宿水电费
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) StayUtilitiesTO stayUtilitiesTO, BindingResult bindingResult) throws ActException {
        try {
            StayUtilitiesBO stayUtilitiesBO = stayUtilitiesAPI.insertStayUtilities(stayUtilitiesTO);
            return ActResult.initialize(stayUtilitiesBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑员工住宿水电费
     *
     * @param stayUtilitiesTO 员工住宿水电费数据to
     * @return class StayUtilitiesVO
     * @des 编辑员工住宿水电费
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) StayUtilitiesTO stayUtilitiesTO, BindingResult bindingResult) throws ActException {
        try {
            StayUtilitiesBO stayUtilitiesBO = stayUtilitiesAPI.editStayUtilities(stayUtilitiesTO);
            return ActResult.initialize(stayUtilitiesBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除员工住宿水电费
     *
     * @param id 用户id
     * @des 根据用户id删除员工住宿水电费记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            stayUtilitiesAPI.removeStayUtilities(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总
     *
     * @param names 名字
     * @des 根据名字汇总
     * @return  class CollectNameVO
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect ( @RequestParam String[] names ) throws ActException {
        try {
            List<CollectNameBO> collectNameBOS = BeanTransform.copyProperties(
                    stayUtilitiesAPI.collectName(names),CollectNameVO.class,true);
            return ActResult.initialize(collectNameBOS);
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
            List<String> nameList = stayUtilitiesAPI.getName();
            return ActResult.initialize(nameList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 员工核实
     *
     * @param to 房租缴费数据to
     * @return class StayUtilitiesVO
     * @des 员工核实
     * @version v1
     */
    @PostMapping("v1/employeeVerify")
    public Result employeeVerify(@Validated(StayUtilitiesTO.employeeVerify.class) StayUtilitiesTO to, BindingResult bindingResult) throws ActException {
        try {

            StayUtilitiesBO bo = stayUtilitiesAPI.employeeVerify(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo,StayUtilitiesVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 运营财务部
     *
     * @param to 房租缴费数据to
     * @return class StayUtilitiesVO
     * @des 运营财务部
     * @version v1
     */
    @PostMapping("v1/financeAudit")
    public Result financeAudit(@Validated(StayUtilitiesTO.financeAudit.class) StayUtilitiesTO to, BindingResult bindingResult) throws ActException {
        try {
            StayUtilitiesBO bo = stayUtilitiesAPI.financeAudit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo,StayUtilitiesVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}