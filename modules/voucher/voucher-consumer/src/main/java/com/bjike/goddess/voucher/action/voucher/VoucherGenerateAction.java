package com.bjike.goddess.voucher.action.voucher;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.api.ProofWordsAPI;
import com.bjike.goddess.financeinit.bo.ProofWordsBO;
import com.bjike.goddess.financeinit.dto.ProofWordsDTO;
import com.bjike.goddess.financeinit.enums.ProofCharacter;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.entity.rbac.Group;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.*;
import com.bjike.goddess.voucher.dto.*;
import com.bjike.goddess.voucher.enums.ExportStatus;
import com.bjike.goddess.voucher.excel.SonPermissionObject;
import com.bjike.goddess.voucher.excel.VoucherTemplateImportExcel;
import com.bjike.goddess.voucher.to.*;
import com.bjike.goddess.voucher.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

/**
 * 记账凭证生成s
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:33 ]
 * @Description: [ 记账凭证生成 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("vouchergenerate")
public class VoucherGenerateAction extends BaseFileAction {

    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private ProofWordsAPI proofWordsAPI;
    @Autowired
    private UserAPI userAPI;



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

            List<SonPermissionObject> hasPermissionList = voucherGenerateAPI.sonPermission();
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

            Boolean isHasPermission = voucherGenerateAPI.guidePermission(guidePermissionTO);
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
     * @param uId 记账凭证信息id
     * @return class VoucherGenerateVO
     * @des 一个记账凭证
     * @version v1
     */
    @GetMapping("v1/getOne/{uId}")
    public Result getOne(@PathVariable String uId) throws ActException {
        try {
            VoucherGenerateVO voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.getById(uId), VoucherGenerateVO.class);
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
    public Result edit(VoucherGenerateTO voucherGenerateTO, BindingResult bindingResult) throws ActException {
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
     * @param uId uid
     * @des 根据id删除记账凭证信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{uId}")
    public Result delete(@PathVariable String uId) throws ActException {
        try {
            voucherGenerateAPI.deleteVoucherGenerate(uId);
            return new ActResult("delete success!");
        } catch (SerException e) {                                                                                                                                                                                                                  
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 批量删除
     *
     * @param to uIds
     * @des 批量删除记账凭证信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/deleteBatch")
    public Result deleteBatch(VoucherGenerateTO to) throws ActException {
        try {

            voucherGenerateAPI.deleteVoucherGenerateBatch(to.getuIds());
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
     * @param voucherGenerateTO 记账凭证基本信息数据
     * @return class VoucherGenerateVO
     * @des 审核记账凭证
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/audit")
    public Result audit(@Validated(VoucherGenerateTO.Audit.class) VoucherGenerateTO voucherGenerateTO) throws ActException {
        try {
            voucherGenerateAPI.audit(voucherGenerateTO);
            return new ActResult("audit success");
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
    @PutMapping("v1/split")
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
    @PutMapping("v1/posting")
    public Result posting(@Validated(VoucherGenerateTO.Audit.class) VoucherGenerateTO voucherGenerateTO) throws ActException {
        try {
//            VoucherGenerateBO voucherGenerateBO1 = voucherGenerateAPI.posting(voucherGenerateTO);
            return ActResult.initialize(voucherGenerateAPI.posting(voucherGenerateTO));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 反审核
     *
     * @param voucherGenerateTO 记账凭证基本信息数据
     * @des 反审核
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/antiAudit")
    public Result antiAudit(@Validated(VoucherGenerateTO.Audit.class) VoucherGenerateTO voucherGenerateTO) throws ActException {
        try {
            voucherGenerateAPI.antiAudit(voucherGenerateTO);
            return new ActResult("antiAudit success");
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
     * @param to 只需要传uId就行
     * @return class VoucherGenerateVO
     * @des 反过账
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/antiPosting")
    public Result antiPosting(@Validated(VoucherGenerateTO.Audit.class) VoucherGenerateTO to) throws ActException {
        try {
//            VoucherGenerateBO voucherGenerateBO1 = voucherGenerateAPI.antiPosting ( ids );
//            return ActResult.initialize ( BeanTransform.copyProperties ( voucherGenerateBO1, VoucherGenerateVO.class, true ) );
            voucherGenerateAPI.antiPosting(to);
            return ActResult.initialize("反过账成功");
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
    @PutMapping("v1/checkAccount")
    public Result checkAccount(@Validated(VoucherGenerateTO.Audit.class) VoucherGenerateTO voucherGenerateTO) throws ActException {
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
     * 反结账
     *
     * @param voucherGenerateTO 记账凭证基本信息数据
     * @return class VoucherGenerateVO
     * @des 反结账
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/antiCheckAccount")
    public Result antiCheckAccount(@Validated(VoucherGenerateTO.Audit.class) VoucherGenerateTO voucherGenerateTO, BindingResult bindingResult) throws ActException {
        try {
            voucherGenerateAPI.antiCheckAccount(voucherGenerateTO);
            return new ActResult("antiCheckAccount success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看月度季度年度的结账记录
     *
     * @return class VoucherGenerateVO
     * @version v1
     */
    @GetMapping("v1/findCkRecordByTime")
    public Result findCkRecordByTime(String month, Integer quart, String year) throws ActException {
        try {
            List<VoucherGenerateBO> voucherGenerateBOs = voucherGenerateAPI.findCkRecordByTime(month, quart, year);
            return ActResult.initialize(BeanTransform.copyProperties(voucherGenerateBOs, VoucherGenerateVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    public static void main(String args[]) {
        String ll = DateUtil.dateToString(DateUtil.getStartQuart());
        String lll = DateUtil.dateToString(DateUtil.getEndQuart());
        System.out.println(DateUtil.getStartQuart());
        System.out.println(String.valueOf(LocalDate.now().getMonth().getValue() - 1));
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
     * 记账凭证记录科目汇总
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 在所有记账凭证记录记账凭证信息进行科目汇总
     * @version v1
     */
    @GetMapping("v1/ctReSub")
    public Result ctReSub(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.ctReSub(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 记账凭证记录柱状图
     *
     * @param dto VoucherChartDTO
     * @return class OptionVO
     * @des 根据月份汇总借方金额和贷方金额
     * @version v1
     */
    @GetMapping("v1/ctReSubHistogram")
    public Result ctReSubHistogram(VoucherChartDTO dto) throws ActException {
        try {
            OptionBO optionBO = voucherGenerateAPI.ctReSubHistogram(dto);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 记账凭证记录地区汇总
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 在所有记账凭证记录记账凭证信息进行地区汇总
     * @version v1
     */
    @GetMapping("v1/ctReArea")
    public Result ctReArea(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.ctReArea(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 记账凭证记录项目组汇总
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 在所有记账凭证记录记账凭证信息进行项目组汇总
     * @version v1
     */
    @GetMapping("v1/ctReGroup")
    public Result ctReGroup(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.ctReGroup(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 记账凭证记录项目名称汇总
     *
     * @param voucherGenerateDTO 记账凭证信息dto
     * @return class VoucherGenerateVO
     * @des 在所有记账凭证记录记账凭证信息进行项目名称汇总
     * @version v1
     */
    @GetMapping("v1/ctRePname")
    public Result ctRePname(@Validated VoucherGenerateDTO voucherGenerateDTO, BindingResult bindingResult) throws ActException {
        try {
            List<VoucherGenerateVO> voucherGenerateVOList = BeanTransform.copyProperties(
                    voucherGenerateAPI.ctRePname(voucherGenerateDTO), VoucherGenerateVO.class, true);
            return ActResult.initialize(voucherGenerateVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 记账凭证记录分析
     *
     * @param to 分析数据
     * @return class AnalysisVO
     * @version v1
     */
    @GetMapping("v1/analysis")
    public Result analysis(@Validated(ADD.class) AnalysisTO to, BindingResult bindingResult) throws ActException {
        try {
            List<AnalysisBO> analysisBOs = voucherGenerateAPI.analysis(to);
            return ActResult.initialize(BeanTransform.copyProperties(analysisBOs, AnalysisVO.class));
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
     * 明细账列表
     *
     * @param dto dto
     * @return class AccountInfoVO
     * @throws ActException
     * @des 根据日期或地区或项目名称或项目组部门或科目进行列表查看
     * @version v1
     */
    @GetMapping("v1/account")
    public Result account(VoucherGenerateDTO dto) throws ActException {
        try {
            List<AccountInfoVO> accountInfoVOS = BeanTransform.copyProperties(voucherGenerateAPI.accountCollect(dto), AccountInfoVO.class);
            for (AccountInfoVO accountInfoVO : accountInfoVOS) {
                accountInfoVO.setId(UUID.randomUUID().toString());
            }
            return ActResult.initialize(accountInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @param dto 明细账
     * @des 导出明细账
     * @version v1
     */
    @GetMapping("v1/exportAccount")
    public Result exportReport(VoucherGenerateDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "明细账.xlsx";
            super.writeOutFile(response, voucherGenerateAPI.exportExcelAccount(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }




    /**
     * 获取所有已过账的地区
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/accountArea")
    public Result accountArea() throws ActException {
        try {
            List<String> areaList = voucherGenerateAPI.accountArea();
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有已过账的项目名称
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/accountProjectName")
    public Result accountProjectName() throws ActException {
        try {
            List<String> projectNameList = voucherGenerateAPI.accountProjectName();
            return ActResult.initialize(projectNameList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有已过账的项目组部门
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/accountProjectGroup")
    public Result accountProjectGroup() throws ActException {
        try {
            List<String> projectGroupList = voucherGenerateAPI.accountProjectGroup();
            return ActResult.initialize(projectGroupList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有已过账的一级科目
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/accountSubject")
    public Result accountSubject() throws ActException {
        try {
            List<String> subjectList = voucherGenerateAPI.accountSubject();
            return ActResult.initialize(subjectList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有已过账的根据一级科目获取二级科目
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/subSubject")
    public Result subSubject(String firstSubject) throws ActException {
        try {
            List<String> subjectList = voucherGenerateAPI.subSubject(firstSubject);
            return ActResult.initialize(subjectList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有已过账的根据一级科目二级科目获取三级科目
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/thirdSubject")
    public Result thirdSubject(String firstSubject, String subSubject) throws ActException {
        try {
            List<String> subjectList = voucherGenerateAPI.thirdSubject(firstSubject, subSubject);
            return ActResult.initialize(subjectList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 上传附件
     *
     * @des 审核项目签订与立项
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String paths = "/voucher/vouchergenerate/" + id;
            List<InputStream> inputStreams = getInputStreams(request, paths);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id 签订与立项id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /voucher/id/....
            String path = "/voucher/vouchergenerate/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件下载
     *
     * @param path 文件信息路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("download success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 删除文件或文件夹
     *
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(VoucherFileTO.TestDEL.class) VoucherFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }


    /**
     * 多选导出
     *
     * @param dto 导出数据
     * @des 根据地区项目名称数据状态一级科目二级科目三级科目时间导出记账凭证数据
     * @version v1
     */
    @GetMapping("v1/export")
    public Result exportReport(@Validated(VoucherGenerateExportDTO.TestExport.class) VoucherGenerateExportDTO dto, HttpServletResponse response, BindingResult bindingResult) throws ActException {
        try {

            String fileName = "记账凭证记录.xlsx";
            ExportStatus exportStatus = dto.getExportStatus();
            switch (exportStatus) {
                case NONE:
                    fileName = "未审核的记账凭证记录.xlsx";
                    break;
                case AUDIT:
                    fileName = "已审核的记账凭证记录.xlsx";
                    break;
                case TRANS:
                    fileName = "已过账的记账凭证记录.xlsx";
                    break;
                case CHECK:
                    fileName = "已结帐的记账凭证记录.xlsx";
                    break;
                case RECORD:
                    fileName = "记账凭证记录.xlsx";
                    break;
                default:
                    throw new ActException("请输入正确的数据状态");
            }
            super.writeOutFile(response, voucherGenerateAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * excel模板下载
     *
     * @des 下载模板项目签订与立项
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "记账凭证数据导入模板.xlsx";
            super.writeOutFile(response, voucherGenerateAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<VoucherTemplateImportExcel> tos = ExcelUtil.mergeExcelToClazz(is, VoucherTemplateImportExcel.class, excel);
            List<VoucherGenerateTO> tocs = new ArrayList<>();
//            for (SiginManageExcel str : tos) {
//                SiginManageTO siginManageTO = BeanTransform.copyProperties(str, SiginManageTO.class, "startProjectTime", "endProjectTime",
//                        "siginStatus", "makeProject", "manager", "auditAdvice");
//                siginManageTO.setStartProjectTime(String.valueOf(str.getStartProjectTime()));
//                siginManageTO.setEndProjectTime(String.valueOf(str.getEndProjectTime()));
//                siginManageTO.setSiginStatus(convertSiginStatus(str.getSiginStatus()));
//                siginManageTO.setMakeProject(convertMakeProject(str.getMakeProject()));
//                siginManageTO.setManager("");
//                siginManageTO.setAuditAdvice("");
//                tocs.add(siginManageTO);
//            }
//            //注意序列化
//            voucherGenerateAPI.importExcel(tocs);

            tocs = convertVoucherGenerateTO(tos);
            voucherGenerateAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    private List<VoucherGenerateTO> convertVoucherGenerateTO(List<VoucherTemplateImportExcel> tos) throws ActException {
        List<VoucherGenerateTO> voucherGenerateTOS = new ArrayList<>();
        if (tos != null && tos.size() > 0) {
            List<String> firstSubjects = new ArrayList<>();
            List<String> secondSubjects = new ArrayList<>();
            List<String> thirdSubjects = new ArrayList<>();
            List<Double> borrowMoneys = new ArrayList<>();
            List<Double> loanMoneys = new ArrayList<>();

            String num = tos.get(0).getNum();
            int index = 0;
            VoucherGenerateTO voucherGenerateTO = new VoucherGenerateTO();
            for (VoucherTemplateImportExcel str : tos) {
                checkVoucherWord(str);
                if (num.equals(str.getNum())) {
                    firstSubjects.add(str.getFirstSubject());
                    secondSubjects.add(str.getSecondSubject());
                    thirdSubjects.add(str.getThirdSubject());
                    borrowMoneys.add(str.getBorrowMoney());
                    loanMoneys.add(str.getLoanMoney());
                } else {
                    voucherGenerateTO = BeanTransform.copyProperties(tos.get(index - 1), VoucherGenerateTO.class, "firstSubject",
                            "secondSubject", "thirdSubject", "borrowMoney", "loanMoney");
                    voucherGenerateTO.setFirstSubjects(firstSubjects);
                    voucherGenerateTO.setSecondSubjects(secondSubjects);
                    voucherGenerateTO.setThirdSubjects(thirdSubjects);
                    voucherGenerateTO.setBorrowMoneys(borrowMoneys);
                    voucherGenerateTO.setLoanMoneys(loanMoneys);

                    voucherGenerateTOS.add(voucherGenerateTO);

                    firstSubjects = new ArrayList<>();
                    secondSubjects = new ArrayList<>();
                    thirdSubjects = new ArrayList<>();
                    borrowMoneys = new ArrayList<>();
                    loanMoneys = new ArrayList<>();

                    firstSubjects.add(str.getFirstSubject());
                    secondSubjects.add(str.getSecondSubject());
                    thirdSubjects.add(str.getThirdSubject());
                    borrowMoneys.add(str.getBorrowMoney());
                    loanMoneys.add(str.getLoanMoney());
                }
                num = str.getNum();
                index++;
            }

//            String temp = tos.get(index - 1).getNum();
//            if (index == tos.size() && !num.equals(tos.get(index - 2).getNum())) {
//                firstSubjects = new ArrayList<>();
//                secondSubjects = new ArrayList<>();
//                thirdSubjects = new ArrayList<>();
//                borrowMoneys = new ArrayList<>();
//                loanMoneys = new ArrayList<>();
//
//
//            }
//            firstSubjects.add(tos.get(index - 2).getFirstSubject());
//            secondSubjects.add(tos.get(index - 2).getSecondSubject());
//            thirdSubjects.add(tos.get(index - 2).getThirdSubject());
//            borrowMoneys.add(tos.get(index - 2).getBorrowMoney());
//            loanMoneys.add(tos.get(index - 2).getLoanMoney());

            voucherGenerateTO = BeanTransform.copyProperties(tos.get(index - 2), VoucherGenerateTO.class, "firstSubject",
                    "secondSubject", "thirdSubject", "borrowMoney", "loanMoney");

            voucherGenerateTO.setFirstSubjects(firstSubjects);
            voucherGenerateTO.setSecondSubjects(secondSubjects);
            voucherGenerateTO.setThirdSubjects(thirdSubjects);
            voucherGenerateTO.setBorrowMoneys(borrowMoneys);
            voucherGenerateTO.setLoanMoneys(loanMoneys);

            voucherGenerateTOS.add(voucherGenerateTO);
        }
        return voucherGenerateTOS;
    }

    private String checkVoucherWord(VoucherTemplateImportExcel voucherTemplateImportExcel) throws ActException {
        String word = "";
        if (null == voucherTemplateImportExcel.getVoucherWord()) {
            throw new ActException("凭证字填写不正确,导入失败,正确填写方式（付/转/记/收）");
        }
        switch (voucherTemplateImportExcel.getVoucherWord()) {
            case "付":
                word = "付";
                break;
            case "转":
                word = "转";
                break;
            case "记":
                word = "记";
                break;
            case "收":
                word = "收";
                break;
            default:
                throw new ActException("凭证字填写不正确,导入失败,正确填写方式（付/转/记/收）");
        }
        return word;
    }


    /**
     * 获取组织结构所有地区
     *
     * @return class AreaBO
     * @des 获取组织结构所有地区
     * @version v1
     */
    @GetMapping("v1/listOrganArea")
    public Result listOrganArea() throws ActException {
        try {
            List<AreaBO> userList = departmentDetailAPI.findArea();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取组织结构所有项目组和部门
     *
     * @return class OpinionBO
     * @des 获取组织结构所有项目组和部门
     * @version v1
     */
    @GetMapping("v1/listOrganDepart")
    public Result listOrganDepart() throws ActException {
        try {
            List<OpinionBO> userList = departmentDetailAPI.findThawOpinion();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取组织结构所有用户
     *
     * @return class UserBO
     * @des 获取组织结构所有用户
     * @version v1
     */
    @GetMapping("v1/listOrganUser")
    public Result listOrganUser() throws ActException {
        try {
            List<UserBO> userList = positionDetailUserAPI.findUserListInOrgan();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 在已过账记录里面根据二级或三级统计金额
     *
     * @param dto dto
     * @version v1
     */
    @GetMapping("v1/findByMoney")
    public Result findByMoney(VoucherGenerateDTO dto) throws ActException {
        try {
            List<PartBO> userList = voucherGenerateAPI.findByMoney(dto);
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 科目汇总列表
     *
     * @param subjectCollectsDTO 更新条件
     * @return class FirstSubjectVO
     * @version v1
     */
    @GetMapping("v1/subject/pagelist")
    public Result pageList(SubjectCollectsDTO subjectCollectsDTO) throws ActException {
        try {
            List<FirstSubjectBO> bos = voucherGenerateAPI.collect(subjectCollectsDTO);
            List<FirstSubjectVO> vos = BeanTransform.copyProperties(bos, FirstSubjectVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 科目汇总导出
     *
     * @param to       导出条件
     * @param response response
     * @version v1
     */

    @GetMapping("v1/subject/exprot")
    public Result exportExcel(ExportSubjectCollectTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "科目汇总.xlsx";
            super.writeOutFile(response, voucherGenerateAPI.exportExcel(to), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 获取所有会计科目
     *
     * @param request request
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findFirstSubject")
    public Result findFirstSubject(HttpServletRequest request) throws ActException {
        try {
            List<String> list = voucherGenerateAPI.findFirstSubject();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 从财务初始化获取凭证字列表
     *
     * @param request request
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/proofWords")
    public Result listProofWords(HttpServletRequest request) throws ActException {
        try {
            List<ProofWordsBO> list = proofWordsAPI.listProof(new ProofWordsDTO());
            Set<String> set = new HashSet<>();
            for (ProofWordsBO bo : list) {  //@see 若是ProofCharacter添加新的类型，则需修改if语句
                String name = "";
                if (bo.getProofCharacter().equals(ProofCharacter.POCTAA)) {
                    name = "记账凭证";
                } else if (bo.getProofCharacter().equals(ProofCharacter.POP)) {
                    name = "付款凭证";
                } else if (bo.getProofCharacter().equals(ProofCharacter.TV)) {
                    name = "转账凭证";
                } else if (bo.getProofCharacter().equals(ProofCharacter.WTROP)) {
                    name = "收款凭证";
                }

                set.add(name);
            }
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 凭证汇总N科目
     *
     * @param dto dto
     * @return class VoucherSummaryVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/summaryListN")
    public Result summaryListN(VoucherSummaryDTO dto) throws ActException {
        try {
            List<VoucherSummanryBO> list = voucherGenerateAPI.summaryListN(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list,VoucherSummaryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 凭证汇总W科目
     *
     * @param dto dto
     * @return class VoucherSummaryVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/summaryListW")
    public Result summaryListw(VoucherSummaryDTO dto) throws ActException {
        try {
            List<VoucherSummanryBO> list = voucherGenerateAPI.summaryListW(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list,VoucherSummaryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 获取当前用户名
     *
     * @param request 请求
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/currentUserName")
    public Result getCurrentUserName(HttpServletRequest request) throws ActException {
        try {
            String name = userAPI.currentUser().getUsername();
            return ActResult.initialize(name);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 凭证汇总导出
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */

    @PostMapping("v1/exportAccountTo")
    public Result exportReportTo(VoucherSummaryDTO dto, HttpServletResponse response,HttpServletRequest request) throws ActException {
        try {
            RpcContext.getContext().setAttachment("userToken", request.getParameter("userToken"));
            String fileName = "记账凭证汇总.xlsx";
            super.writeOutFile(response, voucherGenerateAPI.exportExcelVocher(dto),fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {

            throw new ActException(e.getMessage());
        } catch (IOException e1) {

            throw new ActException(e1.getMessage());
        }
    }
    /**
     * 凭证数据导出
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/voucherGenerateExport")
    public Result voucherGenerateExport(HttpServletResponse response,HttpServletRequest request) throws ActException {
        try {
            //RpcContext.getContext().setAttachment("userToken", request.getParameter("userToken"));
            String fileName = "记账凭证全部数据.xlsx";
            super.writeOutFile(response, voucherGenerateAPI.voucherGenerateExport(),fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {

            throw new ActException(e.getMessage());
        } catch (IOException e1) {

            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 凭证汇总基本信息
     *
     * @param startTime
     * @param endTime
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/informationTo")
    public Result information(String startTime,String endTime) throws ActException {
        try {
            VoucherInformationBO vb= voucherGenerateAPI.information(startTime,endTime);
            return ActResult.initialize(BeanTransform.copyProperties(vb,VoucherInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 凭证数量
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/voucherCount")
    public Result voucherCount(VoucherSummaryDTO dto) throws ActException {
        try {
            Long count = voucherGenerateAPI.countSummary(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 附件数量
     *
     * @param request request
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/attachmentCount")
    public Result attachmentCount(HttpServletRequest request) throws ActException {
        try {
            List<VoucherGenerateBO> list=voucherGenerateAPI.voucherId();
            int count=0;
            if(list!=null) {
                for (int i = 0; i < list.size(); i++) {
                    String path = "/voucher/vouchergenerate/" + list.get(i).getId();
                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setPath(path);
                    Object storageToken = request.getAttribute("storageToken");
                    fileInfo.setStorageToken(storageToken.toString());
                    List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
                    if(files!=null) {
                        count+=files.size();
                    }else{
                       count+=0;
                    }
                }
            }
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}