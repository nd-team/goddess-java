package com.bjike.goddess.voucher.action.voucher;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import com.bjike.goddess.voucher.to.VoucherGenerateTO;
import com.bjike.goddess.voucher.vo.VoucherGenerateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 记账凭证生成
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:33 ]
 * @Description: [ 记账凭证生成 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("vouchergenerate")
public class VoucherGenerateAction {

    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;

    /**
     * 记账凭证列表总条数
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @des 获取所有记账凭证信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(VoucherGenerateDTO voucherGenerateDTO) throws ActException {
        try {
            Long count = voucherGenerateAPI.countVoucherGenerate(voucherGenerateDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 记账凭证列表
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 获取所有记账凭证信息
     * @version v1
     */
    @GetMapping("v1/listVoucher")
    public Result findList(VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.listVoucherGenerate(voucherGenerateDTO), VoucherGenerateVO.class);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个记账凭证
     *
     * @param id 记账凭证信息id
     * @des 一个记账凭证
     * @return class VoucherGenerateVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            VoucherGenerateVO voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.getById(id), VoucherGenerateVO.class);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加记账凭证
     *
     * @param voucherGenerateTO 记账凭证基本信息数据to
     * @return class VoucherGenerateVO
     * @des 添加记账凭证
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(VoucherGenerateTO.TestAdd.class) VoucherGenerateTO voucherGenerateTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateBO> voucherGenerateBO1 = voucherGenerateAPI.addVoucherGenerate(voucherGenerateTO);
            return ActResult.initialize(BeanTransform.copyProperties(voucherGenerateBO1, VoucherGenerateVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑记账凭证
     *
     * @param voucherGenerateTO 记账凭证基本信息数据bo
     * @return class VoucherGenerateVO
     * @des 编辑记账凭证
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(VoucherGenerateTO.TestAdd.class) VoucherGenerateTO voucherGenerateTO) throws ActException {
        try {
            VoucherGenerateBO voucherGenerateBO1 = voucherGenerateAPI.editVoucherGenerate(voucherGenerateTO);
            return ActResult.initialize(BeanTransform.copyProperties(voucherGenerateBO1, VoucherGenerateVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除记账凭证信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            voucherGenerateAPI.deleteVoucherGenerate(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }


    /**
     * 审核列表总条数
     *
     * @param customerBaseInfoDTO 记账凭证信息dto
     * @des 获取所有记账凭证信息总条数
     * @version v1
     */
    @GetMapping("v1/countAudit")
    public Result countAudit(VoucherGenerateDTO customerBaseInfoDTO) throws ActException {
        try {
            Long count = voucherGenerateAPI.countAudit(customerBaseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核列表
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 获取所有记账凭证信息
     * @version v1
     */
    @GetMapping("v1/listAudit")
    public Result listAudit(VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.listAudit(voucherGenerateDTO), VoucherGenerateVO.class);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 审核记账凭证
     *
     * @param id 记账凭证基本信息数据id
     * @return class VoucherGenerateVO
     * @des 审核记账凭证
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/audit/{id}")
    public Result audit(@PathVariable String id) throws ActException {
        try {
            VoucherGenerateBO voucherGenerateBO1 = voucherGenerateAPI.audit(id);
            return ActResult.initialize(BeanTransform.copyProperties(voucherGenerateBO1, VoucherGenerateVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 拆分记账凭证
     *
     * @param voucherGenerateTO 记账凭证基本信息数据bo
     * @return class VoucherGenerateVO
     * @des 拆分记账凭证
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/split")
    public Result split(@Validated(VoucherGenerateTO.TestAdd.class) VoucherGenerateTO voucherGenerateTO) throws ActException {
        try {
            VoucherGenerateBO voucherGenerateBO1 = voucherGenerateAPI.split(voucherGenerateTO);
            return ActResult.initialize(BeanTransform.copyProperties(voucherGenerateBO1, VoucherGenerateVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已审核总条数
     *
     * @param customerBaseInfoDTO 记账凭证信息dto
     * @des 获取所有已审核记账凭证信息总条数
     * @version v1
     */
    @GetMapping("v1/countAudited")
    public Result countAudited(VoucherGenerateDTO customerBaseInfoDTO) throws ActException {
        try {
            Long count = voucherGenerateAPI.countAudited(customerBaseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已审核列表
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 获取所有已审核记账凭证信息
     * @version v1
     */
    @GetMapping("v1/listAudited")
    public Result listAudited(VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.listAudited(voucherGenerateDTO), VoucherGenerateVO.class);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 过账
     *
     * @param voucherGenerateTO 记账凭证基本信息数据voucherGenerateTO
     * @return class VoucherGenerateVO
     * @des 过账
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/posting")
    public Result posting(@Validated(VoucherGenerateTO.TestPost.class) VoucherGenerateTO voucherGenerateTO) throws ActException {
        try {
            VoucherGenerateBO voucherGenerateBO1 = voucherGenerateAPI.posting(voucherGenerateTO);
            return ActResult.initialize(BeanTransform.copyProperties(voucherGenerateBO1, VoucherGenerateVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 反审核
     *
     * @param id 记账凭证基本信息数据id
     * @return class VoucherGenerateVO
     * @des 反审核
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/antiAudit/{id}")
    public Result antiAudit(@PathVariable String id) throws ActException {
        try {
            VoucherGenerateBO voucherGenerateBO1 = voucherGenerateAPI.antiAudit(id);
            return ActResult.initialize(BeanTransform.copyProperties(voucherGenerateBO1, VoucherGenerateVO.class, true));
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
     * 已过账总条数
     *
     * @param customerBaseInfoDTO 记账凭证信息dto
     * @des 获取所有已过账记账凭证信息总条数
     * @version v1
     */
    @GetMapping("v1/countChecked")
    public Result countChecked(VoucherGenerateDTO customerBaseInfoDTO) throws ActException {
        try {
            Long count = voucherGenerateAPI.countChecked(customerBaseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已过账列表
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 获取所有已过账记账凭证信息
     * @version v1
     */
    @GetMapping("v1/listChecked")
    public Result listChecked(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.listChecked(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 反过账
     *
     * @param id 记账凭证基本信息数据id
     * @return class VoucherGenerateVO
     * @des 反过账
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/antiPosting/{id}")
    public Result antiPosting(@PathVariable String id) throws ActException {
        try {
            VoucherGenerateBO voucherGenerateBO1 = voucherGenerateAPI.antiPosting(id);
            return ActResult.initialize(BeanTransform.copyProperties(voucherGenerateBO1, VoucherGenerateVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 结账
     *
     * @param voucherGenerateTO 记账凭证基本信息数据voucherGenerateTO
     * @return class VoucherGenerateVO
     * @des 结账
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/checkAccount")
    public Result checkAccount(@Validated(VoucherGenerateTO.TestPost.class) VoucherGenerateTO voucherGenerateTO) throws ActException {
        try {
            VoucherGenerateBO voucherGenerateBO1 = voucherGenerateAPI.checkAccount(voucherGenerateTO);
            return ActResult.initialize(BeanTransform.copyProperties(voucherGenerateBO1, VoucherGenerateVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 已过账科目汇总
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 在所有已过账记账凭证信息进行科目汇总
     * @version v1
     */
    @GetMapping("v1/ctTransSub")
    public Result ctTransSub(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.ctTransSub(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已过账地区汇总
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 在所有已过账记账凭证信息进行地区汇总
     * @version v1
     */
    @GetMapping("v1/ctTransArea")
    public Result ctTransArea(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.ctTransArea(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已过账项目组汇总
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 在所有已过账记账凭证信息进行项目组汇总
     * @version v1
     */
    @GetMapping("v1/ctTransGroup")
    public Result ctTransGroup(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.ctTransGroup(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已过账项目名称汇总
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 在所有已过账记账凭证信息进行项目名称汇总
     * @version v1
     */
    @GetMapping("v1/ctTransPname")
    public Result ctTransPname(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.ctTransPname(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 结账记录总条数
     *
     * @param customerBaseInfoDTO 记账凭证信息dto
     * @des 获取所有结账记录记账凭证信息总条数
     * @version v1
     */
    @GetMapping("v1/countCkRecord")
    public Result countCkRecord(VoucherGenerateDTO customerBaseInfoDTO) throws ActException {
        try {
            Long count = voucherGenerateAPI.countCkRecord(customerBaseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 结账记录列表
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 获取所有结账记录记账凭证信息
     * @version v1
     */
    @GetMapping("v1/listCkRecord")
    public Result listCkRecord(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.listCkRecord(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 已结帐科目汇总
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 在所有已结帐记账凭证信息进行科目汇总
     * @version v1
     */
    @GetMapping("v1/ctCkSub")
    public Result ctCkSub(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.ctCkSub(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已结帐地区汇总
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 在所有已结帐记账凭证信息进行地区汇总
     * @version v1
     */
    @GetMapping("v1/ctCkArea")
    public Result ctCkArea(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.ctCkArea(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已结帐项目组汇总
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 在所有已结帐记账凭证信息进行项目组汇总
     * @version v1
     */
    @GetMapping("v1/ctCkGroup")
    public Result ctCkGroup(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.ctCkGroup(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已结帐项目名称汇总
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 在所有已结帐记账凭证信息进行项目名称汇总
     * @version v1
     */
    @GetMapping("v1/ctCkPname")
    public Result ctCkPname(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.ctCkPname(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 记账凭证记录总条数
     *
     * @param customerBaseInfoDTO 记账凭证信息dto
     * @des 获取所有记账凭证记录记账凭证信息总条数
     * @version v1
     */
    @GetMapping("v1/countRecord")
    public Result countRecord(VoucherGenerateDTO customerBaseInfoDTO) throws ActException {
        try {
            Long count = voucherGenerateAPI.countRecord(customerBaseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 记账凭证记录列表
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 获取所有记账凭证记录记账凭证信息
     * @version v1
     */
    @GetMapping("v1/listRecord")
    public Result listRecord(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.listRecord(voucherGenerateDTO), VoucherGenerateVO.class, true);
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