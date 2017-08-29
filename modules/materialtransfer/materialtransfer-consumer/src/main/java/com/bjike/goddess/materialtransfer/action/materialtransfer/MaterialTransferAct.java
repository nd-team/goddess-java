package com.bjike.goddess.materialtransfer.action.materialtransfer;

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
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialtransfer.api.MaterialTransferAPI;
import com.bjike.goddess.materialtransfer.bo.MaterialTransferBO;
import com.bjike.goddess.materialtransfer.dto.MaterialTransferDTO;
import com.bjike.goddess.materialtransfer.excel.SonPermissionObject;
import com.bjike.goddess.materialtransfer.to.GuidePermissionTO;
import com.bjike.goddess.materialtransfer.to.MaterialTransferTO;
import com.bjike.goddess.materialtransfer.type.AuditState;
import com.bjike.goddess.materialtransfer.vo.MaterialTransferVO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.user.bo.UserBO;
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
 * 物资调动
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-28 10:47 ]
 * @Description: [ 物资调动 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("materialtransfer")
public class MaterialTransferAct {

    @Autowired
    private MaterialTransferAPI materialTransferAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private MaterialInStockAPI materialInStockAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = materialTransferAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = materialTransferAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询物资调动
     *
     * @param id      物资调动唯一标识
     * @param request Http请求
     * @return class MaterialTransferVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/materialtransfer/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MaterialTransferBO bo = materialTransferAPI.findById(id);
            MaterialTransferVO vo = BeanTransform.copyProperties(bo, MaterialTransferVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总条数
     *
     * @param dto 物资调动dto
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/count")
    public Result count(@Validated MaterialTransferDTO dto, BindingResult bindingResult) throws ActException {
        try {
            Long count = materialTransferAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询物资调动
     *
     * @param dto 物资调动dto
     * @return class MaterialTransferVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result list(@Validated MaterialTransferDTO dto, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<MaterialTransferBO> boList = materialTransferAPI.list(dto);
            List<MaterialTransferVO> voList = BeanTransform.copyProperties(boList, MaterialTransferVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加物资调动
     *
     * @param to 物资调动to
     * @return class MaterialTransferVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) MaterialTransferTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            MaterialTransferBO bo = materialTransferAPI.save(to);
            MaterialTransferVO vo = BeanTransform.copyProperties(bo, MaterialTransferVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除物资调动
     *
     * @param id 物资调动唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            materialTransferAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑物资调动
     *
     * @param to 物资调动to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) MaterialTransferTO to, BindingResult result) throws ActException {
        try {
            materialTransferAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目经理审核
     *
     * @param id           物资调动唯一标识
     * @param pmAuditState 项目经理审核状态
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/pmAudit/{id}")
    public Result pmAudit(@PathVariable String id, @RequestParam(value = "pmAuditState") AuditState pmAuditState) throws ActException {
        try {
            materialTransferAPI.pmAudit(id, pmAuditState);
            return new ActResult("pmAudit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 福利模块负责人审核
     *
     * @param id           物资调动唯一标识
     * @param welfareState 福利模块负责人审核状态
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/wealModAudit/{id}")
    public Result wealModAudit(@PathVariable String id, @RequestParam(value = "welfareState") AuditState welfareState) throws ActException {
        try {
            materialTransferAPI.wealModAudit(id, welfareState);
            return new ActResult("wealModAudit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 福利模块负责人确认调配成功
     *
     * @param id               物资调动唯一标识
     * @param recipient        领用人
     * @param confirmDeploy    福利模块负责人确认调配成功
     * @param finishDeployTime 调配成功
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/wealModConfirm/{id}")
    public Result wealModConfirm(@PathVariable(value = "id") String id, @RequestParam(value = "recipient") String recipient, @RequestParam(value = "confirmDeploy") Boolean confirmDeploy, @RequestParam(value = "finishDeployTime") String finishDeployTime) throws ActException {
        try {
            materialTransferAPI.wealModConfirm(id, recipient, confirmDeploy, finishDeployTime);
            return new ActResult("wealModConfirm success!");
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
            List<String> detail = new ArrayList<>();
            detail = materialTransferAPI.findAddAllDetails();
            return ActResult.initialize(detail);
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
            List<String> getPerson = new ArrayList<>(0);
//            getPerson = materialTransferAPI.findallMonUser();
            if (moduleAPI.isCheck("organize")) {
                List<UserBO> userBOs = positionDetailUserAPI.findUserListInOrgan();
                if (!CollectionUtils.isEmpty(userBOs)) {
                    getPerson = userBOs.stream().map(UserBO::getUsername).distinct().collect(Collectors.toList());
                }
            }
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

    /**
     * 获取物资名称
     *
     * @version v1
     */
    @GetMapping("v1/findMaterialName")
    public Result findMaterialName() throws ActException {
        try {
            List<String> list = new ArrayList<>(0);
            if (moduleAPI.isCheck("materialinstock")) {
                List<MaterialInStockBO> materialInStockBOs = materialInStockAPI.findAll();
                if (!CollectionUtils.isEmpty(materialInStockBOs)) {
                    list = materialInStockBOs.stream().map(MaterialInStockBO::getMaterialName).distinct().collect(Collectors.toList());
                }
            }
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取物资型号
     *
     * @version v1
     */
    @GetMapping("v1/findModel")
    public Result findModel() throws ActException {
        try {
            List<String> list = materialTransferAPI.findModel();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取单位
     *
     * @version v1
     */
    @GetMapping("v1/findUnit")
    public Result findUnit() throws ActException {
        try {
            List<String> list = materialTransferAPI.findUnit();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}