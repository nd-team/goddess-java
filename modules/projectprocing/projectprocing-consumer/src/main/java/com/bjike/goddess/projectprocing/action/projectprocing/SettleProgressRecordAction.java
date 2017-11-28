package com.bjike.goddess.projectprocing.action.projectprocing;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.api.SettleProgressRecordAPI;
import com.bjike.goddess.projectprocing.bo.SettleProgressRecordBO;
import com.bjike.goddess.projectprocing.dto.SettleProgressRecordDTO;
import com.bjike.goddess.projectprocing.to.SettleProgressRecordTO;
import com.bjike.goddess.projectprocing.vo.SettleProgressRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 结算进度调整记录&结算问题汇总
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:19 ]
 * @Description: [ 结算进度调整记录&结算问题汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("settleprogressrecord")
public class SettleProgressRecordAction {
    @Autowired
    private SettleProgressRecordAPI settleProgressRecordAPI;
    /**
     * 结算进度调整记录列表总条数
     *
     * @param settleProgressRecordDTO 结算进度调整记录dto
     * @des 获取所有结算进度调整记录总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SettleProgressRecordDTO settleProgressRecordDTO) throws ActException {
        try {
            Long count = settleProgressRecordAPI.countManage(settleProgressRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个结算进度调整记录
     *
     * @param id 结算进度调整记录id
     * @return class SettleProgressRecordVO
     * @des 根据id获取结算进度调整记录
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            SettleProgressRecordVO settleProgressRecordVO = BeanTransform.copyProperties(
                    settleProgressRecordAPI.getOneById(id), SettleProgressRecordVO.class);
            return ActResult.initialize(settleProgressRecordVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 结算进度调整记录列表
     *
     * @param settleProgressRecordDTO 结算进度调整记录dto
     * @return class SettleProgressRecordVO
     * @des 获取所有结算进度调整记录
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListProjectAcceptance(SettleProgressRecordDTO settleProgressRecordDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<SettleProgressRecordVO> projectAcceptanceVOList = BeanTransform.copyProperties(
                    settleProgressRecordAPI.listManage(settleProgressRecordDTO), SettleProgressRecordVO.class, request);
            return ActResult.initialize(projectAcceptanceVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加结算进度调整记录
     *
     * @param settleProgressRecordTO 结算进度调整记录数据to
     * @return class SettleProgressRecordVO
     * @des 添加结算进度调整记录
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addProjectAcceptance(@Validated(ADD.class) SettleProgressRecordTO settleProgressRecordTO, BindingResult bindingResult) throws ActException {
        try {
            SettleProgressRecordBO projectAcceptanceBO1 = settleProgressRecordAPI.addManage(settleProgressRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(projectAcceptanceBO1, SettleProgressRecordVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑结算进度调整记录
     *
     * @param settleProgressRecordTO 结算进度调整记录数据bo
     * @return class SettleProgressRecordVO
     * @des 编辑项目验收情况
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editProjectAcceptance(@Validated(EDIT.class) SettleProgressRecordTO settleProgressRecordTO) throws ActException {
        try {
            SettleProgressRecordBO projectAcceptanceBO1 = settleProgressRecordAPI.editManage(settleProgressRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(projectAcceptanceBO1, SettleProgressRecordVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除结算进度调整
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProjectAcceptance(@PathVariable String id) throws ActException {
        try {
            settleProgressRecordAPI.deleteManage(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }
    /**
     * 核对分析
     *
     * @param id                 id
     * @param moneyModule        资金模块
     * @param moneyModuleOpinion 资金模块意见
     * @des 核对分析
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/checkAnalysis")
    public Result checkAnalysis(@RequestParam String id,@RequestParam String moneyModule,@RequestParam String moneyModuleOpinion) throws ActException {
        try {
           settleProgressRecordAPI.checkAnalysis(id,moneyModule,moneyModuleOpinion);
            return new ActResult("checkAnalysis success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 确认
     *
     * @param id             id
     * @param generalManager 总经理
     * @param approvalExam   审批意见
     * @param confirm        是否确认
     * @des 确认
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/confirm")
    public Result confirm(@RequestParam String id,@RequestParam String generalManager,@RequestParam String approvalExam, Boolean confirm) throws ActException {
        try {
            settleProgressRecordAPI.confirm(id,generalManager,approvalExam,confirm);
            return new ActResult("confirm success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}