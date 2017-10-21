package com.bjike.goddess.lendreimbursement.action.lendreimbursement.lendreimshape;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.AccountAPI;
import com.bjike.goddess.financeinit.api.CategoryAPI;
import com.bjike.goddess.financeinit.bo.CategoryBO;
import com.bjike.goddess.financeinit.dto.CategoryDTO;
import com.bjike.goddess.lendreimbursement.api.ReimburseAuditLogAPI;
import com.bjike.goddess.lendreimbursement.api.ReimburseRecordAPI;
import com.bjike.goddess.lendreimbursement.bo.ReimburseRecordBO;
import com.bjike.goddess.lendreimbursement.dto.ReimburseRecordDTO;
import com.bjike.goddess.lendreimbursement.excel.SonPermissionObject;
import com.bjike.goddess.lendreimbursement.to.AccountVoucherTO;
import com.bjike.goddess.lendreimbursement.to.LendDeleteFileTO;
import com.bjike.goddess.lendreimbursement.to.LendGuidePermissionTO;
import com.bjike.goddess.lendreimbursement.to.ReimburseRecordTO;
import com.bjike.goddess.lendreimbursement.vo.AccountVoucherVO;
import com.bjike.goddess.lendreimbursement.vo.CollectReimerDataVO;
import com.bjike.goddess.lendreimbursement.vo.ReimburseAuditLogVO;
import com.bjike.goddess.lendreimbursement.vo.ReimburseRecordVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
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
import java.util.stream.Collectors;

/**
 * 报销记录图形
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销记录图形 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("reimburseshape")
public class ReimburseShapeAction extends BaseFileAction {

    @Autowired
    private ReimburseRecordAPI reimburseRecordAPI;
    @Autowired
    private ReimburseAuditLogAPI reimburseAuditLogAPI;
    @Autowired
    private CategoryAPI categoryAPI;
    @Autowired
    private AccountAPI accountAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;






}