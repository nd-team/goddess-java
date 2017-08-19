package com.bjike.goddess.reportmanagement.action.reportmanagement;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.reportmanagement.api.AssetAPI;
import com.bjike.goddess.reportmanagement.api.FormulaAPI;
import com.bjike.goddess.reportmanagement.bo.*;
import com.bjike.goddess.reportmanagement.dto.AssetDTO;
import com.bjike.goddess.reportmanagement.dto.FormulaDTO;
import com.bjike.goddess.reportmanagement.to.AssetTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.vo.*;
import com.bjike.goddess.subjectcollect.api.SubjectCollectAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 资产表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:19 ]
 * @Description: [ 资产表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("asset")
public class AssetAct {
    @Autowired
    private AssetAPI assetAPI;
    @Autowired
    private FormulaAPI formulaAPI;
    @Autowired
    private SubjectCollectAPI subjectCollectAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

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

            List<SonPermissionObject> hasPermissionList = assetAPI.sonPermission();
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

            Boolean isHasPermission = assetAPI.guidePermission(guidePermissionTO);
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
     * 列表
     *
     * @param dto 资产数据传输
     * @return class AssetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated(AssetDTO.A.class) AssetDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<AssetBO> list = assetAPI.list(dto);
            List<AssetVO> vos = new ArrayList<>();
            for (AssetBO bo : list) {
                AssetVO vo = BeanTransform.copyProperties(bo, AssetVO.class, request);
                vo.setAssetId(bo.getId());
                vos.add(vo);
            }
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑列表
     *
     * @param dto 资产数据传输
     * @return class AssetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list1")
    public Result list1(AssetDTO dto,HttpServletRequest request) throws ActException {
        try {
            List<AssetBO> list = assetAPI.list1(dto);
            List<AssetVO> vos = new ArrayList<>();
            for (AssetBO bo : list) {
                AssetVO vo = BeanTransform.copyProperties(bo, AssetVO.class, request);
                vo.setAssetId(bo.getId());
                vos.add(vo);
            }
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 资产传输对象
     * @return class AssetVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) AssetTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            AssetBO bo = assetAPI.save(to);
            AssetVO vo = BeanTransform.copyProperties(bo, AssetVO.class, request);
            vo.setAssetId(bo.getId());
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看资产结构表
     *
     * @param dto 资产数据传输
     * @return class StructureVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/assetStructure")
    public Result assetStructure(@Validated(AssetDTO.A.class) AssetDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<StructureBO> list = assetAPI.assetStructure(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, StructureVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 偿还能力分析
     *
     * @param dto 资产数据传输
     * @return class RepayAnalyzeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/repayAnalyze")
    public Result repayAnalyze(@Validated(AssetDTO.A.class) AssetDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<RepayAnalyzeBO> list = assetAPI.repayAnalyze(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, RepayAnalyzeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看金额明细
     *
     * @param id  资产id
     * @param dto 资产数据传输
     * @return class DetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findDetails/{id}")
    public Result findDetails(@PathVariable String id, @Validated(AssetDTO.A.class) AssetDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<DetailBO> list = assetAPI.findDetails(id, dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, DetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看对应的公式
     *
     * @param id  资产id
     * @param dto 资产数据传输
     * @return class FormulaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/lookFormula/{id}")
    public Result lookFormula(@PathVariable String id, @Validated(AssetDTO.A.class) AssetDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        FormulaDTO formulaDTO = new FormulaDTO();
        BeanUtils.copyProperties(dto, formulaDTO);
        request.getSession().setAttribute("id", id);
        try {
            List<FormulaBO> list = formulaAPI.findByFid(id, formulaDTO);
            return ActResult.initialize(BeanTransform.copyProperties(list, FormulaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 资产id
     * @return class AssetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/asset/{id}")
    public Result asset(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AssetBO bo = assetAPI.findByID(id);
            AssetVO vo = BeanTransform.copyProperties(bo, AssetVO.class, request);
            vo.setAssetId(bo.getId());
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 资产传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) AssetTO to, BindingResult result) throws ActException {
        try {
            assetAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 资产id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            assetAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 资产数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AssetDTO dto) throws ActException {
        try {
            return ActResult.initialize(assetAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有科目
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allFirstSubjects")
    public Result allFirstSubjects() throws ActException {
        try {
            return ActResult.initialize(subjectCollectAPI.allFirstSubjects());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有项目名称
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allProjectNames")
    public Result allProjectNames() throws ActException {
        try {
            return ActResult.initialize(subjectCollectAPI.allProjectNames());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有项目组，部门
     *
     * @return class DepartmentDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allDepart")
    public Result allDepart(HttpServletRequest request) throws ActException {
        try {
            List<DepartmentDetailBO> list = departmentDetailAPI.findStatus();
            return ActResult.initialize(BeanTransform.copyProperties(list, DepartmentDetailVO.class, request));
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
    @GetMapping("v1/allArea")
    public Result allArea(HttpServletRequest request) throws ActException {
        try {
            List<AreaBO> list = departmentDetailAPI.findArea();
            return ActResult.initialize(BeanTransform.copyProperties(list, AreaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}