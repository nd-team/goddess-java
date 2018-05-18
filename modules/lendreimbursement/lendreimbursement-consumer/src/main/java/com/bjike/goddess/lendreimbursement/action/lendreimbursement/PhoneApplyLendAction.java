package com.bjike.goddess.lendreimbursement.action.lendreimbursement;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.AccountanCourseAPI;
import com.bjike.goddess.financeinit.bo.AccountAddDateBO;
import com.bjike.goddess.lendreimbursement.api.ApplyLendAPI;
import com.bjike.goddess.lendreimbursement.api.LendAuditDetailAPI;
import com.bjike.goddess.lendreimbursement.bo.ApplyLendBO;
import com.bjike.goddess.lendreimbursement.bo.LendAuditDetailBO;
import com.bjike.goddess.lendreimbursement.dto.LendAuditDetailDTO;
import com.bjike.goddess.lendreimbursement.dto.PhoneApplyLendDTO;
import com.bjike.goddess.lendreimbursement.dto.PhoneApplyLendSelectDTO;
import com.bjike.goddess.lendreimbursement.enums.LendIndentityStatus;
import com.bjike.goddess.lendreimbursement.enums.LendPhoneShowStatus;
import com.bjike.goddess.lendreimbursement.to.*;
import com.bjike.goddess.lendreimbursement.vo.ApplyLendVO;
import com.bjike.goddess.lendreimbursement.vo.PhoneLendAuditprocingVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 手机端申请借款
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 申请借款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("phoneApplylend")
//@CrossOrigin(origins = {"*"})
public class PhoneApplyLendAction extends BaseFileAction {

    @Autowired
    private ApplyLendAPI applyLendAPI;
    @Autowired
    private LendAuditDetailAPI lendAuditDetailAPI;
    @Autowired
    private AccountanCourseAPI accountanCourseAPI;

    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private UserAPI userAPI;


    /**
     * 借款所有列表
     *
     * @param phoneApplyLendDTO 申请借款条件
     * @return class ApplyLendVO
     * @des 获取所有列表
     * @version v1
     */
    @GetMapping("v1/listAll")
    public Result listAll(@Validated(PhoneApplyLendDTO.TESTSELECT.class) PhoneApplyLendDTO phoneApplyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ApplyLendVO> applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.listAll(phoneApplyLendDTO), ApplyLendVO.class, true);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加借款申请
     *
     * @param phoneApplyLendTO 申请借款基本信息数据to
     * @return class ApplyLendVO
     * @des 添加申请借款
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addApplyLend(@Validated(PhoneApplyLendTO.TESTAddAndEdit.class) PhoneApplyLendTO phoneApplyLendTO, BindingResult bindingResult) throws ActException {
        try {
            ApplyLendTO applyLendTO = BeanTransform.copyProperties(phoneApplyLendTO, ApplyLendTO.class);
            ApplyLendBO applyLendBO1 = applyLendAPI.addApplyLend(applyLendTO);

            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加借款凭证图片
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            //上传图片-发票图片
            String path = "/applyLend/" + id + "/ticket";
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取一个借款的凭证图片
     *
     * @param id id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listCard/{id}")
    public Result listCard(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/applyLend/" + id + "/ticket";
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
     * 待审核的所有审核
     *
     * @param phoneApplyLendChargerTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 待审核的所有审核
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/waitPay/allAudit")
    public Result waitPayAllAudit(@Validated(PhoneApplyLendChargerTO.TESTChargeAudit.class) PhoneApplyLendChargerTO phoneApplyLendChargerTO, BindingResult bindingResult) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = new ApplyLendBO();
            LendIndentityStatus lendIndentityStatus = phoneApplyLendChargerTO.getLendIndentityStatus();
            if (LendIndentityStatus.CHARGER.equals(lendIndentityStatus)) {
                //负责人审核
                applyLendBO1 = editAuditBycharger(phoneApplyLendChargerTO);
            } else if (LendIndentityStatus.FINACER.equals(lendIndentityStatus)) {
                //财务审核
                applyLendBO1 = editAuditByOperate(phoneApplyLendChargerTO);
            } else if (LendIndentityStatus.MANAGER.equals(lendIndentityStatus)) {
                //总经办审核
                applyLendBO1 = editAuditByManager(phoneApplyLendChargerTO);
            }
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    private ApplyLendBO editAuditBycharger(PhoneApplyLendChargerTO phoneApplyLendChargerTO) throws SerException {
        ApplyLendTO applyLendTO = new ApplyLendTO();
        applyLendTO.setId(phoneApplyLendChargerTO.getId());
        applyLendTO.setChargerPass(phoneApplyLendChargerTO.getChargerPass());
        applyLendTO.setChargerOpinion(phoneApplyLendChargerTO.getChargerOpinion());
        ApplyLendBO applyLendBO1 = applyLendAPI.editChargerWaitAudit(applyLendTO);
        return applyLendBO1;
    }


    private ApplyLendBO editAuditByOperate(PhoneApplyLendChargerTO phoneApplyLendChargerTO) throws SerException {
        ApplyLendTO applyLendTO = new ApplyLendTO();
        applyLendTO.setId(phoneApplyLendChargerTO.getId());
        applyLendTO.setFincerPass(phoneApplyLendChargerTO.getChargerPass());
        applyLendTO.setFincerOpinion(phoneApplyLendChargerTO.getChargerOpinion());

        ApplyLendBO applyLendBO1 = applyLendAPI.editOperateWaitAudit(applyLendTO);
        return applyLendBO1;
    }


    private ApplyLendBO editAuditByManager(PhoneApplyLendChargerTO phoneApplyLendChargerTO) throws SerException {
        ApplyLendTO applyLendTO = new ApplyLendTO();
        applyLendTO.setId(phoneApplyLendChargerTO.getId());
        applyLendTO.setManagerPass(phoneApplyLendChargerTO.getChargerPass());
        applyLendTO.setManagerOpinion(phoneApplyLendChargerTO.getChargerOpinion());

        ApplyLendBO applyLendBO1 = applyLendAPI.editManageWaitAudit(applyLendTO);
        return applyLendBO1;
    }

    /**
     * 查看审核过程原因
     *
     * @param id 申请借款基本信息数据id
     * @return class PhoneLendAuditprocingVO
     * @des 总经办审核
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/audit/reason/{id}")
    public Result findReason(@PathVariable String id) throws ActException {
        try {
            //负责人审核
            //财务审核
            //总经办审核
            //财务申请冻结
            //负责人确认冻结
            List<PhoneLendAuditprocingVO> list = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ApplyLendBO applyLendBO1 = applyLendAPI.getOneById(id);
            LendAuditDetailDTO detailDTO = new LendAuditDetailDTO();
            detailDTO.setApplyLendId(applyLendBO1.getId());
            List<LendAuditDetailBO> listDetail = lendAuditDetailAPI.listLendAuditDetail(detailDTO);
            if (listDetail != null && listDetail.size() > 0) {
                for (LendAuditDetailBO str : listDetail) {
                    PhoneLendAuditprocingVO procingVO = new PhoneLendAuditprocingVO();
                    LocalDateTime time = LocalDateTime.parse(str.getCreateTime(), formatter);
                    procingVO.setAuditTime(time.getYear() + "-" + time.getMonthValue() + "-" + time.getDayOfMonth());
                    procingVO.setProcing((StringUtils.isBlank(str.getPosition()) ? "" : str.getPosition())
                            + "  审核人" + (StringUtils.isNotBlank(str.getAuditIdentity()) ? "(" + str.getAuditIdentity() + ")" : "")
                            + "：" + str.getAuditor() + " <br>是否通过："
                            + (StringUtils.isBlank(str.getPassOr()) ? "" : (str.getPassOr().equals("是") ? "审核通过" : "审核不通过"))
                            + "  <br>审核意见：" + (StringUtils.isBlank(str.getAuditSuggest()) ? "" : str.getAuditSuggest()));
                    list.add(procingVO);
                }
            }

            //付款
            if (StringUtils.isNotBlank(applyLendBO1.getPayer())) {
                PhoneLendAuditprocingVO procingVO = new PhoneLendAuditprocingVO();
                procingVO.setAuditTime(applyLendBO1.getPayDate());
                procingVO.setProcing("已付款 ：" + applyLendBO1.getPayer() + "通过" + applyLendBO1.getPayOrigin() + "付款" + applyLendBO1.getMoney() + "元");
                list.add(procingVO);
            }
            //确认收款
            if (StringUtils.isNotBlank(applyLendBO1.getReceivePay()) && "是".equals(applyLendBO1.getReceivePay())) {
                PhoneLendAuditprocingVO procingVO = new PhoneLendAuditprocingVO();
                procingVO.setAuditTime(applyLendBO1.getReceivePayTime());
                procingVO.setProcing("确认收款 ：申请人" + applyLendBO1.getLender() + "已确认收款");
                list.add(procingVO);
            }
            //还款
            if (StringUtils.isNotBlank(applyLendBO1.getReturnDate())) {
                PhoneLendAuditprocingVO procingVO = new PhoneLendAuditprocingVO();
                procingVO.setAuditTime(applyLendBO1.getReturnDate());
                //这里有三个金额，不知道是哪个
                procingVO.setProcing("还款 ：" + applyLendBO1.getReturnWays() + "还款" + applyLendBO1.getReturnMoney());
                list.add(procingVO);
            }
            //还款核对
            if (StringUtils.isNotBlank(applyLendBO1.getChecker())) {
                PhoneLendAuditprocingVO procingVO = new PhoneLendAuditprocingVO();
                procingVO.setAuditTime(applyLendBO1.getCheckDate());
                //这里有三个金额，不知道是哪个
                procingVO.setProcing("还款核对 ：" + applyLendBO1.getCheckcontent());
                list.add(procingVO);
            }
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


    /**
     * 付款
     *
     * @param phoneApplyLendPayTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 等待付款的付款
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editPay")
    public Result editPay(@Validated(PhoneApplyLendPayTO.TESTPAY.class) PhoneApplyLendPayTO phoneApplyLendPayTO, BindingResult bindingResult) throws ActException {
        ApplyLendTO applyLendTO = new ApplyLendTO();
        applyLendTO.setPayOrigin(phoneApplyLendPayTO.getPayOrigin());
        applyLendTO.setPayDate(phoneApplyLendPayTO.getPayDate());
        applyLendTO.setId(phoneApplyLendPayTO.getId());
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.editPayMoney(applyLendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 确认收款
     *
     * @param phoneApplyLendSurePayTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 确认收款
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editSureRecieve")
    public Result editSureRecieve(PhoneApplyLendSurePayTO phoneApplyLendSurePayTO) throws ActException {
        try {
            if (StringUtils.isBlank(phoneApplyLendSurePayTO.getId())) {
                throw new ActException("id不能为空");
            }
            ApplyLendTO applyLendTO = new ApplyLendTO();
            applyLendTO.setId(phoneApplyLendSurePayTO.getId());
            ApplyLendBO applyLendBO1 = applyLendAPI.editSureRecieveMoney(applyLendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 还款
     *
     * @param phoneLendReturnSendTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 借款记录还款
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/return/edit")
    public Result editReturn(@Validated(PhoneLendReturnSendTO.TESTRETURNSEND.class) PhoneLendReturnSendTO phoneLendReturnSendTO, BindingResult bindingResult) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.editPhoneReturn(phoneLendReturnSendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 寄件
     *
     * @param phoneLendSendTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 借款记录还款和寄件
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/send/edit")
    public Result editSend(@Validated(PhoneLendSendTO.TESTRETURNSEND.class) PhoneLendSendTO phoneLendSendTO, BindingResult bindingResult) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.editPhoneSend(phoneLendSendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加还款图片
     *
     * @param id 借款id
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/return/uploadFile/{id}")
    public Result uploadFileToReturn(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            //上传图片-发票图片
            String path = "/applyLend/" + id + "/returnPic";
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取一个还款图片
     *
     * @param id id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/return/listCard/{id}")
    public Result listCardToReturn(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/applyLend/" + id + "/returnPic";
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
     * 还款核对(这里把网页版的还款核对和帐务核对的收到单据合并在一起了)
     *
     * @param phoneLendReturnCheckTO 申请借款信息applyLendTO
     * @return class ApplyLendVO
     * @des 这里把网页版的还款核对和帐务核对的收到单据合并在一起了
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/check/checkReturn")
    public Result phoneCheckReturn(@Validated(PhoneLendReturnCheckTO.TESTRETURNCHECK.class) PhoneLendReturnCheckTO phoneLendReturnCheckTO, BindingResult bindingResult) throws ActException {
        try {
            ApplyLendVO applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.phoneCheckReturn(phoneLendReturnCheckTO), ApplyLendVO.class);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 手机版还款核对有误编辑
     *
     * @param phoneLendReturnSendTO 申请借款信息applyLendTO
     * @return class ApplyLendVO
     * @des 还款记录还款核对
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit/errorCheckReturn")
    public Result editErrorCheckReturn(@Validated(PhoneLendReturnSendTO.TESTRETURNSEND.class) PhoneLendReturnSendTO phoneLendReturnSendTO, BindingResult bindingResult) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.editPhoneReturn(phoneLendReturnSendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 详情(手机端权限个别按钮出现)
     *
     * @param id                  借款id
     * @param lendPhoneShowStatus 状态
     * @return class ApplyLendVO
     * @des 待审核(负责人财务总经办)和付款和确认收款和还款核对
     * @des 去还款按钮显示是当确认收款了(receivePay=是)且lendRetunStatus是NONE
     * @des 还款核对按钮显示是当确认收款了(receivePay=是)且lendRetunStatus是WAITRETURNCHECK
     * @version v1
     */
    @GetMapping("v1/info/lend/{id}/{lendPhoneShowStatus}")
    public Result findListApplyLend(@PathVariable String id, @PathVariable LendPhoneShowStatus lendPhoneShowStatus) throws ActException {
        try {
            String userToken = RpcContext.getContext().getAttachment(RpcCommon.USER_TOKEN);
            ApplyLendVO applyLendVO = BeanTransform.copyProperties(
                    applyLendAPI.getOneById(id), ApplyLendVO.class, true);
            RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, userToken);
            Boolean showRight = applyLendAPI.phoneShowRight(lendPhoneShowStatus, id);
            applyLendVO.setPhoneShowRight(showRight);
            return ActResult.initialize(applyLendVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 负责人审核有误编辑
     *
     * @param phoneApplyLendTO 申请借款基本信息数据to
     * @return class ApplyLendVO
     * @des 负责人和分析申请当有误编辑
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/error")
    public Result errorEdit(@Validated(PhoneApplyLendTO.TESTAddAndEdit.class) PhoneApplyLendTO phoneApplyLendTO, BindingResult bindingResult) throws ActException {
        try {
            ApplyLendTO applyLendTO = BeanTransform.copyProperties(phoneApplyLendTO, ApplyLendTO.class);
            ApplyLendBO applyLendBO1 = applyLendAPI.editApplyError(applyLendTO);

            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 手机端获取地区
     *
     * @return {name:'List<string>',type:'List<string>',defaultValue:'',description:'返回地区数组'}
     * @des 获取地区集合
     * @version v1
     */
    @GetMapping("v1/phone/getArea")
    public Result getArea() throws ActException {
        try {
            List<String> areaList = applyLendAPI.listPhoneArea();
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 手机端获取项目组
     *
     * @param phoneApplyLendSelectDTO 参数
     * @return {name:'List<string>',type:'List<string>',defaultValue:'',description:'返回地区数组'}
     * @des 获取项目组集合
     * @version v1
     */
    @GetMapping("v1/phone/getPGroupList")
    public Result getPGroupList(@Validated(PhoneApplyLendSelectDTO.TESTDepart.class) PhoneApplyLendSelectDTO phoneApplyLendSelectDTO, BindingResult bindingResult) throws ActException {
        try {
            List<String> areaList = applyLendAPI.listPhoneProjectGroup(phoneApplyLendSelectDTO);
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 手机端获取项目名
     *
     * @param phoneApplyLendSelectDTO 参数
     * @return {name:'List<string>',type:'List<string>',defaultValue:'',description:'返回地区数组'}
     * @des 获取项目名集合
     * @version v1
     */
    @GetMapping("v1/phone/getPNameList")
    public Result getPNameList(@Validated(PhoneApplyLendSelectDTO.TESTPNAME.class) PhoneApplyLendSelectDTO phoneApplyLendSelectDTO, BindingResult bindingResult) throws ActException {
        try {
            List<String> areaList = applyLendAPI.listPhoneProjectName(phoneApplyLendSelectDTO);
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取所有三级科目
     *
     * @des 根据一级科目的代码获取所有三级科目
     * @version v1
     */
//    @GetMapping("v1/listThirdSubject/{code}")
//    public Result listThirdByCode(@PathVariable String code) throws ActException {
//        try {
//            List<String> list = accountanCourseAPI.findThirdNameByCode(code);

//            return ActResult.initialize(list);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }


    /**
     * 手机版获取所有二级科目
     *
     * @des 根据三级科目和说明获取所有二级科目
     * @version v1
     */
//    @GetMapping("v1/listSecond/{code}")
//    public Result listSecond(@PathVariable String code) throws ActException {
//        try {
//            List<String> list = accountanCourseAPI.findSendNameByCode(code);
//            return ActResult.initialize(list);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 手机版获取所有一级科目和代码
     *
     * @des 手机版获取所有一级科目和代码
     * @version v1
     */
    @GetMapping("v1/listFirst")
    public Result listFirst(ReimburseRecordTO reimburseRecordTO) throws ActException {

        try {
            List<AccountAddDateBO> list = accountanCourseAPI.findFirstNameCode();

            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


}