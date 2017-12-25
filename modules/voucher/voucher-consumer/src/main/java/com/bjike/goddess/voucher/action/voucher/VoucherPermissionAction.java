package com.bjike.goddess.voucher.action.voucher;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.vo.CusOperateVO;
import com.bjike.goddess.organize.vo.OpinionVO;
import com.bjike.goddess.voucher.api.VoucherPermissionAPI;
import com.bjike.goddess.voucher.bo.VoucherOperateBO;
import com.bjike.goddess.voucher.bo.VoucherPermissionBO;
import com.bjike.goddess.voucher.dto.VoucherPermissionDTO;
import com.bjike.goddess.voucher.to.VoucherPermissionTO;
import com.bjike.goddess.voucher.vo.VoucherOperateVO;
import com.bjike.goddess.voucher.vo.VoucherPermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户权限配置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 客户权限配置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("voucherpermission")
public class VoucherPermissionAction {

    @Autowired
    private VoucherPermissionAPI voucherPermissionAPI;

    /**
     * 列表总条数
     *
     * @param voucherPermissionDTO 客户权限信息dto
     * @des 获取所有客户权限信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(VoucherPermissionDTO voucherPermissionDTO) throws ActException {
        try {
            Long count = voucherPermissionAPI.countPermission(voucherPermissionDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表总条数
     *
     * @param voucherPermissionDTO 明细账权限信息dto
     * @des 获取所有明细账权限信息总条数
     * @version v1
     */
    @GetMapping("v1/account/count")
    public Result countAccount(VoucherPermissionDTO voucherPermissionDTO) throws ActException {
        try {
            Long count = voucherPermissionAPI.countAccountPermission(voucherPermissionDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 明细账权限列表
     *
     * @param voucherPermissionDTO 客户权限信息dto
     * @param request              前端过滤参数
     * @return class VoucherPermissionVO
     * @des 获取所有明细账权限信息
     * @version v1
     */
    @GetMapping("v1/account/list")
    public Result findListAccountPermission(VoucherPermissionDTO voucherPermissionDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<VoucherPermissionBO> boList = voucherPermissionAPI.listAccount(voucherPermissionDTO);
            List<VoucherPermissionVO> voucherPermissionVOList = new ArrayList<>();
            boList.stream().forEach(str -> {
                VoucherPermissionVO temp = BeanTransform.copyProperties(str, VoucherPermissionVO.class, request);
                List<VoucherOperateVO> covo = new ArrayList<>();
                if (null != str.getCusOperateBO()) {
                    covo = BeanTransform.copyProperties(str.getCusOperateBO(), VoucherOperateVO.class);
                }
                temp.setCusOperateVO(covo);
                voucherPermissionVOList.add(temp);
            });

            return ActResult.initialize(voucherPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个客户权限
     *
     * @param id 客户权限信息id
     * @return class VoucherPermissionVO
     * @des 根据id获取所有客户权限信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            VoucherPermissionBO temp = voucherPermissionAPI.getOneById(id);
            VoucherPermissionVO voucherPermissionVOList = BeanTransform.copyProperties(
                    temp, VoucherPermissionVO.class);
            if (null != temp) {
                List<VoucherOperateBO> cboList = temp.getCusOperateBO();
                List<VoucherOperateVO> cvoList = new ArrayList<>();
                if (cboList != null && cboList.size() > 0) {
                    cvoList = BeanTransform.copyProperties(
                            cboList, CusOperateVO.class);
                }
                voucherPermissionVOList.setCusOperateVO(cvoList);
            }
            return ActResult.initialize(voucherPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取类型下对象
     *
     * @param id 客户权限信息id
     * @return class VoucherPermissionVO
     * @des 根据id获取所有客户权限获取类型下对象
     * @version v1
     */
    @GetMapping("v1/listOperateById/{id}")
    public Result listOperateById(@PathVariable String id) throws ActException {
        try {
            List<OpinionVO> list = BeanTransform.copyProperties(voucherPermissionAPI.listOperateById(id), OpinionVO.class);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 客户权限列表
     *
     * @param voucherPermissionDTO 客户权限信息dto
     * @param request              前端过滤参数
     * @return class VoucherPermissionVO
     * @des 获取所有客户权限信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListVoucherPermission(VoucherPermissionDTO voucherPermissionDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<VoucherPermissionBO> boList = voucherPermissionAPI.list(voucherPermissionDTO);
            List<VoucherPermissionVO> voucherPermissionVOList = new ArrayList<>();
            boList.stream().forEach(str -> {
                VoucherPermissionVO temp = BeanTransform.copyProperties(str, VoucherPermissionVO.class, request);
                List<VoucherOperateVO> covo = new ArrayList<>();
                if (null != str.getCusOperateBO()) {
                    covo = BeanTransform.copyProperties(str.getCusOperateBO(), VoucherOperateVO.class);
                }
                temp.setCusOperateVO(covo);
                voucherPermissionVOList.add(temp);
            });

            return ActResult.initialize(voucherPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑客户权限
     *
     * @param voucherPermissionTO 客户权限基本信息数据bo
     * @return class VoucherPermissionVO
     * @des 编辑客户权限
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editVoucherPermission(@Validated VoucherPermissionTO voucherPermissionTO) throws ActException {
        try {
            VoucherPermissionBO voucherPermissionBO1 = voucherPermissionAPI.edit(voucherPermissionTO);
            return ActResult.initialize(BeanTransform.copyProperties(voucherPermissionBO1, VoucherPermissionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 科目汇总列表总条数
     *
     * @param voucherPermissionDTO 明细账权限信息dto
     * @des 获取所有明细账权限信息总条数
     * @version v1
     */
    @GetMapping("v1/Subject/count")
    public Result countSubject(VoucherPermissionDTO voucherPermissionDTO) throws ActException {
        try {
            Long count = voucherPermissionAPI.countSubjectPermission(voucherPermissionDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 科目汇总权限列表
     *
     * @param voucherPermissionDTO 客户权限信息dto
     * @param request              前端过滤参数
     * @return class VoucherPermissionVO
     * @des 获取所有明细账权限信息
     * @version v1
     */
    @GetMapping("v1/Subject/list")
    public Result findListSubjectPermission(VoucherPermissionDTO voucherPermissionDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<VoucherPermissionBO> boList = voucherPermissionAPI.listSubject(voucherPermissionDTO);
            List<VoucherPermissionVO> voucherPermissionVOList = new ArrayList<>();
            boList.stream().forEach(str -> {
                VoucherPermissionVO temp = BeanTransform.copyProperties(str, VoucherPermissionVO.class, request);
                List<VoucherOperateVO> covo = new ArrayList<>();
                if (null != str.getCusOperateBO()) {
                    covo = BeanTransform.copyProperties(str.getCusOperateBO(), VoucherOperateVO.class);
                }
                temp.setCusOperateVO(covo);
                voucherPermissionVOList.add(temp);
            });

            return ActResult.initialize(voucherPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}