package com.bjike.goddess.reportmanagement.action.reportmanagement;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.api.AssetAPI;
import com.bjike.goddess.reportmanagement.api.FormulaAPI;
import com.bjike.goddess.reportmanagement.bo.*;
import com.bjike.goddess.reportmanagement.dto.AssetDTO;
import com.bjike.goddess.reportmanagement.to.AssetTO;
import com.bjike.goddess.reportmanagement.vo.*;
import com.bjike.goddess.subjectcollect.api.SubjectCollectAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

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
            return ActResult.initialize(BeanTransform.copyProperties(list, AssetVO.class, request));
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
            return ActResult.initialize(BeanTransform.copyProperties(bo, AssetVO.class, request));
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
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        String projectGroup = dto.getProjectGroup();
        request.getSession().setAttribute("id", id);
        try {
            List<FormulaBO> list = formulaAPI.findByFid(id, startTime, endTime, projectGroup);
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
            return ActResult.initialize(BeanTransform.copyProperties(bo, AssetVO.class, request));
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
     * 获取所有项目组/部门
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allProjectGroups")
    public Result allProjectGroups() throws ActException {
        try {
            return ActResult.initialize(subjectCollectAPI.allProjectGroups());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}