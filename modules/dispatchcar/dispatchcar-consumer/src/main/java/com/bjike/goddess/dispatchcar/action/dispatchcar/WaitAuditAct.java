package com.bjike.goddess.dispatchcar.action.dispatchcar;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.dispatchcar.dto.DispatchCarInfoDTO;
import com.bjike.goddess.dispatchcar.enums.FindType;
import com.bjike.goddess.dispatchcar.vo.DispatchCarInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 等待审核
 *
 * @Author: [Jason]
 * @Date: [17-4-14 上午10:43]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("waitaudit")
public class WaitAuditAct {

    @Autowired
    private DispatchCarInfoAPI dispatchCarInfoAPI;


    /**
     * 等待审核页面分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(DispatchCarInfoDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("findType", FindType.WAITAUDIT));
            List<DispatchCarInfoVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.pageList(dto), DispatchCarInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看附件
     *
     * @param id 出车记录id
     * @version v1
     */
    @GetMapping("v1/findFiles/{id}")
    public Result findFiles(@PathVariable String id) throws ActException {
        //// TODO: 17-4-14 查看附件
        return ActResult.initialize("success!");
    }

    /**
     * 根据id查询出车记录
     *
     * @param id 出车记录id
     * @version v1
     */
    @GetMapping("v1/findDetail/{id}")
    public Result findDetail(@PathVariable String id) throws ActException {
        try {
            DispatchCarInfoVO vo = BeanTransform.copyProperties(dispatchCarInfoAPI.findDetail(id), DispatchCarInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资金核对意见
     *
     * @param id             出车记录id
     * @param fundModuleSugg 意见
     * @version v1
     */
    @GetMapping("v1/fundSugg")
    public Result fundSugg(String id, String fundModuleSugg) throws ActException {
        try {
            dispatchCarInfoAPI.fundSugg(id, fundModuleSugg);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 预算核对意见
     *
     * @param id 出车记录id
     * @param budgetModuleSugg 出车记录id
     * @version v1
     */
    @GetMapping("v1/budgetSugg")
    public Result budgetSugg(String id, String budgetModuleSugg) throws ActException {
        try {
            dispatchCarInfoAPI.budgetSugg(id, budgetModuleSugg);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目负责人或任务下发人审核
     *
     * @param id 出车记录id
     * @param principalSugg 出车记录id
     * @param auditResult 审核记过
     * @version v1
     */
    @GetMapping("v1/principalSugg")
    public Result principalSugg(String id, String principalSugg, Boolean auditResult) throws ActException {
        try {
            dispatchCarInfoAPI.principalSugg(id, principalSugg,auditResult);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
