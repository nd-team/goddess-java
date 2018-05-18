package com.bjike.goddess.materialreceive.action.materialreceive;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialinstock.api.MaterialInStockAPI;
import com.bjike.goddess.materialreceive.api.MaterialReceiveAPI;
import com.bjike.goddess.materialreceive.bo.MaterialReceiveBO;
import com.bjike.goddess.materialreceive.dto.MaterialReceiveDTO;
import com.bjike.goddess.materialreceive.to.GuidePermissionTO;
import com.bjike.goddess.materialreceive.to.MaterialReceiveTO;
import com.bjike.goddess.materialreceive.to.MaterialReturnTO;
import com.bjike.goddess.materialreceive.type.AuditState;
import com.bjike.goddess.materialreceive.vo.MaterialReceiveVO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 物资领用归还登记
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:41 ]
 * @Description: [ 物资领用归还登记 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("materialreceive")
public class MaterialReceiveAct {

    @Autowired
    private MaterialReceiveAPI materialReceiveAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private MaterialInStockAPI materialInStockAPI;
    @Autowired
    private ModuleAPI moduleAPI;

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

            Boolean isHasPermission = materialReceiveAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询物资领用归还登记
     *
     * @param id 物资领用归还登记唯一标识
     * @return class MaterialReceiveVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/materialreceive/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MaterialReceiveBO bo = materialReceiveAPI.findById(id);
            MaterialReceiveVO vo = BeanTransform.copyProperties(bo, MaterialReceiveVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 物资领用归还登记dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated MaterialReceiveDTO dto, BindingResult result) throws ActException {
        try {
            Long count = materialReceiveAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询物资领用归还登记
     *
     * @param dto 物资领用归还登记dto
     * @return class MaterialReceiveVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated MaterialReceiveDTO dto, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<MaterialReceiveBO> boList = materialReceiveAPI.list(dto);
            List<MaterialReceiveVO> voList = BeanTransform.copyProperties(boList, MaterialReceiveVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加物资领用归还登记
     *
     * @param to 物资领用归还登记to
     * @return class MaterialReceiveVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) MaterialReceiveTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            MaterialReceiveBO bo = materialReceiveAPI.save(to);
            MaterialReceiveVO vo = BeanTransform.copyProperties(bo, MaterialReceiveVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除物资领用归还登记
     *
     * @param id 物资领用归还登记唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            materialReceiveAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑物资领用归还登记
     *
     * @param to 物资领用归还登记to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) MaterialReceiveTO to, BindingResult result) throws ActException {
        try {
            materialReceiveAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param id           物资领用归还登记id
     * @param auditState   审核状态
     * @param auditOpinion 审核意见
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/audit")
    public Result audit(@RequestParam(value = "id") String id, @RequestParam(value = "auditState") AuditState auditState, @RequestParam(value = "auditOpinion") String auditOpinion) throws ActException {
        try {
            materialReceiveAPI.audit(id, auditState, auditOpinion);
            return new ActResult("audit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 领用完成
     *
     * @param to 物资领用归还登记to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/receiveover")
    public Result receiveOver(@Validated(MaterialReceiveTO.RECEIVEOVER.class) MaterialReceiveTO to, BindingResult result) throws ActException {
        try {
            materialReceiveAPI.receiveOver(to);
            return new ActResult("receiveover success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 物资领用归还登记后归还
     *
     * @param to 物资归还to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/materialreturn")
    public Result materialReturn(@Validated(MaterialReturnTO.MATERIALRETURN.class) MaterialReturnTO to, BindingResult result) throws ActException {
        try {
            materialReceiveAPI.materialReturn(to);
            return new ActResult("materialreturn success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加所有部门下拉值
     *
     * @version v1
     */
    @GetMapping("v1/allOrageDepartment")
    public Result allOrageDepartment() throws ActException {
        try {
//            List<String> detail = new ArrayList<>();
//            detail = materialReceiveAPI.findAddAllDetails();
//            return ActResult.initialize(detail);
            List<String> list = new ArrayList<>(0);
            if (moduleAPI.isCheck("organize")) {
                List<OpinionBO> opinionBOs = departmentDetailAPI.findThawOpinion();
                if (!CollectionUtils.isEmpty(opinionBOs)) {
                    list = opinionBOs.stream().map(OpinionBO::getValue).distinct().collect(Collectors.toList());
                }
            }
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加中所有的地区
     *
     * @version v1
     */
    @GetMapping("v1/allArea")
    public Result allArea() throws ActException {
        try {
            List<AreaBO> area = new ArrayList<>(0);
            if (moduleAPI.isCheck("organize")) {
                area = departmentDetailAPI.findArea();
            }
            return ActResult.initialize(area);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有用户
     *
     * @version v1
     */
    @GetMapping("v1/allGetPerson")
    public Result allGetPerson() throws ActException {
        try {
            List<String> getPerson = new ArrayList<>();
            getPerson = materialReceiveAPI.findallMonUser();
            return ActResult.initialize(getPerson);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有入库编号
     *
     * @version v1
     */
    @GetMapping("v1/allGetNo")
    public Result allGetNo() throws ActException {
        try {
            Set<String> getNo = new HashSet<>();
            getNo = materialInStockAPI.allstockEncoding();
            return ActResult.initialize(new ArrayList<>(getNo));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}