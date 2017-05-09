package com.bjike.goddess.subjectcollect.action.subjectcollect;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.subjectcollect.api.SubjectCollectAPI;
import com.bjike.goddess.subjectcollect.bo.SubjectCollectBO;
import com.bjike.goddess.subjectcollect.to.SubjectCollectTO;
import com.bjike.goddess.subjectcollect.vo.SubjectCollectVO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 科目汇总表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-07 04:02 ]
 * @Description: [ 科目汇总表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("subjectcollect")
public class SubjectCollectAction {
    @Autowired
    private SubjectCollectAPI subjectCollectAPI;
    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;
    /**
     * 导出
     *
     * @version v1
     */
    @PostMapping("v1/exportExcel")
    public Result exportExcel() throws ActException {
        String excel = null;
        try {
            excel = subjectCollectAPI.exportExcel();
            return new ActResult(excel);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 删除科目汇总表
     *
     * @param id 用户id
     * @des 根据id删除科目汇总表
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result removeSubjectCollect(@PathVariable String id) throws ActException {
        try {
            subjectCollectAPI.removeSubjectCollect(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param firstSubject 科目
     * @des 根据科目汇总
     * @return  class SubjectCollectVO
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collectSubjectCollect(@NotBlank String[] firstSubject) throws ActException {
        try {
            List<SubjectCollectVO> subjectCollectVOS = BeanTransform.copyProperties(
                    subjectCollectAPI.collectSubjectCollect(firstSubject),SubjectCollectVO.class,true);
            return ActResult.initialize(subjectCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总地区
     *
     * @param area 地区
     * @des 根据地区汇总
     * @return  class SubjectCollectVO
     * @version v1
     */
    @GetMapping("v1/collectArea")
    public Result collectArea ( @NotBlank String[] area ) throws ActException {
        try {
            List<SubjectCollectVO> subjectCollectVOS = BeanTransform.copyProperties(
                    subjectCollectAPI.collectArea(area),SubjectCollectVO.class,true);
            return ActResult.initialize(subjectCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总项目名称
     *
     * @param projectName 项目名称
     * @des 根据项目名称汇总
     * @return  class SubjectCollectVO
     * @version v1
     */
    @GetMapping("v1/collectProjectName")
    public Result collectProjectName ( @NotBlank String[] projectName ) throws ActException {
        try {
            List<SubjectCollectVO> subjectCollectVOS = BeanTransform.copyProperties(
                    subjectCollectAPI.collectProjectName(projectName),SubjectCollectVO.class,true);
            return ActResult.initialize(subjectCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总项目组
     *
     * @param projectGroup 项目名称
     * @des 根据项目名称汇总
     * @return  class SubjectCollectVO
     * @version v1
     */
    @GetMapping("v1/collectProjectGroup")
    public Result collectProjectGroup ( @NotBlank String[] projectGroup ) throws ActException {
        try {
            List<SubjectCollectVO> subjectCollectVOS = BeanTransform.copyProperties(
                    subjectCollectAPI.collectProjectGroup(projectGroup),SubjectCollectVO.class,true);
            return ActResult.initialize(subjectCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 对比汇总
     *
     * @param subjectCollectTO 对比汇总数据to
     * @return class SubjectCollectVO
     * @version v1
     */
    @GetMapping("v1/collectCompare")
    public Result collectCompare(SubjectCollectTO subjectCollectTO) throws ActException {
        try {
            List<SubjectCollectBO> subjectCollectBOS = subjectCollectAPI.collectCompare(subjectCollectTO);
            return ActResult.initialize(subjectCollectBOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



}