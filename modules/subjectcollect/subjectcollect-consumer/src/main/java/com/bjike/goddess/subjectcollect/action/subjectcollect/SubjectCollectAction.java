package com.bjike.goddess.subjectcollect.action.subjectcollect;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.subjectcollect.api.SubjectCollectAPI;
import com.bjike.goddess.subjectcollect.bo.SubjectCollectBO;
import com.bjike.goddess.subjectcollect.dto.SubjectCollectDTO;
import com.bjike.goddess.subjectcollect.to.SubjectCollectTO;
import com.bjike.goddess.subjectcollect.vo.CompareCollectVO;
import com.bjike.goddess.subjectcollect.vo.SubjectCollectVO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import com.bjike.goddess.voucher.vo.VoucherGenerateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
     * 科目汇总表列表总条数
     *
     * @param subjectCollectDTO 科目汇总表记录dto
     * @des 获取所有科目汇总表
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SubjectCollectDTO subjectCollectDTO) throws ActException {
        try {
            Long count = subjectCollectAPI.countSubjectCollect(subjectCollectDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个科目汇总表
     *
     * @param id
     * @return class SubjectCollectVO
     * @des 获取一个科目汇总表
     * @version v1
     */
    @GetMapping("v1/subject/{id}")
    public Result subject(@PathVariable String id) throws ActException {
        try {
            SubjectCollectBO subjectCollectBO = subjectCollectAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(subjectCollectBO, SubjectCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 科目汇总表列表
     *
     * @param subjectCollectDTO 科目汇总表记录dto
     * @return class SubjectCollectVO
     * @des 获取所有科目汇总表
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(SubjectCollectDTO subjectCollectDTO, HttpServletRequest request) throws ActException {
        try {
            List<SubjectCollectVO> subjectCollectVOS = BeanTransform.copyProperties(
                    subjectCollectAPI.findListSubjectCollect(subjectCollectDTO),SubjectCollectVO.class, request);
            return ActResult.initialize(subjectCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加科目汇总表
     *
     * @param subjectCollectTO 科目汇总表to
     * @return class SubjectCollectVO
     * @des 添加科目汇总表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) SubjectCollectTO subjectCollectTO, BindingResult bindingResult) throws ActException {
        try {
            SubjectCollectBO subjectCollectBO = subjectCollectAPI.insertSubjectCollect(subjectCollectTO);
            return ActResult.initialize(subjectCollectBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑科目汇总表
     *
     * @param subjectCollectTO 科目汇总表数据to
     * @return class SubjectCollectVO
     * @des 添加科目汇总表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) SubjectCollectTO subjectCollectTO, BindingResult bindingResult) throws ActException {
        try {
            SubjectCollectBO subjectCollectBO = subjectCollectAPI.editSubjectCollect(subjectCollectTO);
            return ActResult.initialize(subjectCollectBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
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
    public Result delete(@PathVariable String id) throws ActException {
        try {
            subjectCollectAPI.removeSubjectCollect(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已审核科目汇总
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 在所有已审核记账凭证信息进行科目汇总
     * @version v1
     */
    @GetMapping("v1/collectSub")
    public Result collectSub(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.collectSub(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已审核地区汇总
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 在所有已审核记账凭证信息进行地区汇总
     * @version v1
     */
    @GetMapping("v1/collectArea")
    public Result collectArea(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.collectArea(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已审核项目组汇总
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 在所有已审核记账凭证信息进行项目组汇总
     * @version v1
     */
    @GetMapping("v1/collectGroup")
    public Result collectGroup(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.collectGroup(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已审核项目名称汇总
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 在所有已审核记账凭证信息进行项目名称汇总
     * @version v1
     */
    @GetMapping("v1/collectPname")
    public Result collectPname(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.collectPname(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有一级科目
     *
     * @des 获取所有一级科目
     * @version v1
     */
    @GetMapping("v1/listFirstSubject")
    public Result listFirstSubject() throws ActException {
        try {
            List<String> userList = voucherGenerateAPI.listFirstSubject();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有二级科目
     *
     * @des 根据一级科目获取所有二级科目
     * @version v1
     */
    @GetMapping("v1/listSubByFirst")
    public Result listSubByFirst(@RequestParam String firstSub) throws ActException {
        try {
            List<String> userList = voucherGenerateAPI.listSubByFirst(firstSub);
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有三级科目
     *
     * @des 根据一级二级科目获取所有一级科目
     * @version v1
     */
    @GetMapping("v1/listTubByFirst")
    public Result listTubByFirst(@RequestParam String firstSub, @RequestParam String secondSub) throws ActException {
        try {
            List<String> userList = voucherGenerateAPI.listTubByFirst(firstSub, secondSub);
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有地区
     *
     * @des 获取所有地区
     * @version v1
     */
    @GetMapping("v1/listArea")
    public Result listArea() throws ActException {
        try {
            List<String> userList = voucherGenerateAPI.listArea();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有项目名称
     *
     * @des 获取所有项目名称
     * @version v1
     */
    @GetMapping("v1/listProject")
    public Result listProject() throws ActException {
        try {
            List<String> userList = voucherGenerateAPI.listProject();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有项目组
     *
     * @des 获取所有项目组
     * @version v1
     */
    @GetMapping("v1/listGroup")
    public Result listGroup() throws ActException {
        try {
            List<String> userList = voucherGenerateAPI.listGroup();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总对比
     *
     * @param months 月份
     * @return class CompareCollectVO
     * @des 根据月份
     * @version v1
     */
    @GetMapping("v1/collectCompare")
    public Result collectCompare(@RequestParam Integer [] months) throws ActException {
        try {
            List<CompareCollectVO> compareCollectVOS = BeanTransform.copyProperties(
                    subjectCollectAPI.collectCompare(months),CompareCollectVO.class);
            return ActResult.initialize(compareCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



}