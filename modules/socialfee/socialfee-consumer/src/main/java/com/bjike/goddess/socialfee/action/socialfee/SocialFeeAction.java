package com.bjike.goddess.socialfee.action.socialfee;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.socialfee.to.GuidePermissionTO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.socialfee.api.SocialFeeAPI;
import com.bjike.goddess.socialfee.bo.SocialFeeBO;
import com.bjike.goddess.socialfee.bo.VoucherDataBO;
import com.bjike.goddess.socialfee.dto.SocialFeeDTO;
import com.bjike.goddess.socialfee.excle.SocialFeeExcel;
import com.bjike.goddess.socialfee.excle.SonPermissionObject;
import com.bjike.goddess.socialfee.to.SocialFeeTO;
import com.bjike.goddess.socialfee.to.VoucherDataTO;
import com.bjike.goddess.socialfee.vo.SocialFeeVO;
import com.bjike.goddess.socialfee.vo.VoucherDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 社会缴费
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-19 03:25 ]
 * @Description: [ 社会缴费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("socialfee")
public class SocialFeeAction extends BaseFileAction {

    @Autowired
    private SocialFeeAPI socialFeeAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

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

            List<SonPermissionObject> hasPermissionList = socialFeeAPI.sonPermission();
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

            Boolean isHasPermission = socialFeeAPI.guidePermission(guidePermissionTO);
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
     * 社会缴费列表总条数
     *
     * @param socialFeeDTO 社会缴费信息dto
     * @des 获取所有社会缴费信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SocialFeeDTO socialFeeDTO) throws ActException {
        try {
            Long count = socialFeeAPI.countSocialFee(socialFeeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 社会缴费列表
     *
     * @param socialFeeDTO 社会缴费信息dto
     * @return class SocialFeeVO
     * @des 获取所有社会缴费信息
     * @version v1
     */
    @GetMapping("v1/listSocialFee")
    public Result findListSocialFee(SocialFeeDTO socialFeeDTO, BindingResult bindingResult) throws ActException {
        try {
            List<SocialFeeVO> socialFeeVOList = BeanTransform.copyProperties(
                    socialFeeAPI.listSocialFee(socialFeeDTO), SocialFeeVO.class, true);
            return ActResult.initialize(socialFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加社会缴费
     *
     * @param socialFeeTO 社会缴费基本信息数据to
     * @return class SocialFeeVO
     * @des 添加社会缴费
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addSocialFee(@Validated(SocialFeeTO.TestAdd.class) SocialFeeTO socialFeeTO, BindingResult bindingResult) throws ActException {
        try {
            SocialFeeBO socialFeeBO1 = socialFeeAPI.addSocialFee(socialFeeTO);
            return ActResult.initialize(BeanTransform.copyProperties(socialFeeBO1, SocialFeeVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑社会缴费
     *
     * @param socialFeeTO 社会缴费基本信息数据bo
     * @return class SocialFeeVO
     * @des 添加社会缴费
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editSocialFee(@Validated(SocialFeeTO.TestAdd.class) SocialFeeTO socialFeeTO) throws ActException {
        try {
            SocialFeeBO socialFeeBO1 = socialFeeAPI.editSocialFee(socialFeeTO);
            return ActResult.initialize(BeanTransform.copyProperties(socialFeeBO1, SocialFeeVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除社会缴费信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteSocialFee(@PathVariable String id) throws ActException {
        try {
            socialFeeAPI.deleteSocialFee(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }


    /**
     * 一个社会缴费
     *
     * @param id 社会缴费信息id
     * @return class SocialFeeVO
     * @des 根据id查询社会缴费
     * @version v1
     */
    @GetMapping("v1/listSocialFee/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            SocialFeeVO socialFeeVOList = BeanTransform.copyProperties(
                    socialFeeAPI.getOneById(id), SocialFeeVO.class, true);
            return ActResult.initialize(socialFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param socialFeeDTO 社会缴费信息dto
     * @return class SocialFeeVO
     * @des 汇总获取所有社会缴费信息
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(SocialFeeDTO socialFeeDTO, BindingResult bindingResult) throws ActException {
        try {
            List<SocialFeeVO> socialFeeVOList = BeanTransform.copyProperties(
                    socialFeeAPI.collect(socialFeeDTO), SocialFeeVO.class, true);
            return ActResult.initialize(socialFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 所有纳税人名称
     *
     * @des 获取所有纳税人名称
     * @version v1
     */
    @GetMapping("v1/listPayfeer")
    public Result listPayfeer() throws ActException {
        try {
            List<String> list = socialFeeAPI.listPayFeer();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 所有姓名
     *
     * @des 获取所有姓名
     * @version v1
     */
    @GetMapping("v1/listEmpName")
    public Result listEmpName() throws ActException {
        try {
            List<String> list = socialFeeAPI.listEmpName();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    //@LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<SocialFeeExcel> tos = ExcelUtil.excelToClazz(is, SocialFeeExcel.class, excel);
            List<SocialFeeTO> tocs = new ArrayList<>();
            for (SocialFeeExcel str : tos) {
                SocialFeeTO socialFeeTO = BeanTransform.copyProperties(str, SocialFeeTO.class);
                tocs.add(socialFeeTO);
            }
            //注意序列化
            socialFeeAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出
     *
     * @param socialFeeDTO 社会缴费信息dto
     * @return class SocialFeeVO
     * @des 汇总获取所有社会缴费信息
     * @version v1
     */
//    @GetMapping("v1/export")
//    public Result export(SocialFeeDTO socialFeeDTO, BindingResult bindingResult) throws ActException {
//        try {
//            String exportAddr = socialFeeAPI.export(socialFeeDTO);
//            return ActResult.initialize(exportAddr);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }


    /**
     * 生成记账凭证
     *
     * @param ids 列表id数组
     * @return class VoucherDataVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/vGenerate")
    public Result vGenerate(String[] ids) throws ActException {
        try {

            VoucherDataBO voucherDataBO = socialFeeAPI.vGenerate(ids);
            return ActResult.initialize(BeanTransform.copyProperties(voucherDataBO, VoucherDataVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 提交记账凭证
     *
     * @param voucherDataTO voucherDataTO数据
     * @return class VoucherDataVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/voucher")
    public Result voucher(@Validated(SocialFeeTO.TestVoucher.class) VoucherDataTO voucherDataTO) throws ActException {
        try {

            VoucherDataBO voucherDataBO = socialFeeAPI.voucher(voucherDataTO);
            return ActResult.initialize(BeanTransform.copyProperties(voucherDataBO, VoucherDataVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @param dto 社会缴纳
     * @des 导出社会缴费记录
     * @version v1
     */
    //@LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(SocialFeeDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "社会缴费记录.xlsx";
            super.writeOutFile(response, socialFeeAPI.exportExcel(dto), fileName);
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
            String fileName = "社会缴费记录.xlsx";
            super.writeOutFile(response, socialFeeAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

}