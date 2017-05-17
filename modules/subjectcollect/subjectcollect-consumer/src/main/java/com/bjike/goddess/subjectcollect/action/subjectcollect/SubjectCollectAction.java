package com.bjike.goddess.subjectcollect.action.subjectcollect;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.subjectcollect.api.SubjectCollectAPI;
import com.bjike.goddess.subjectcollect.bo.SubjectCollectBO;
import com.bjike.goddess.subjectcollect.to.SubjectCollectTO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import com.bjike.goddess.voucher.vo.VoucherGenerateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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



}