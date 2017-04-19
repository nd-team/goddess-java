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
 * 已付款记录
 *
 * @Author: [Jason]
 * @Date: [17-4-14 下午2:48]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("payed")
public class PayedAct {

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
            dto.getConditions().add(Restrict.eq("findType", FindType.PAYED));
            List<DispatchCarInfoVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.pageList(dto), DispatchCarInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核详情
     *
     * @param id 出车记录id
     * @version v1
     */
    @GetMapping("v1/findAudit/{id}")
    public Result findAudit(@PathVariable String id) throws ActException {
        try {
            List<DispatchCarInfoVO> vo = BeanTransform.copyProperties(dispatchCarInfoAPI.findAudit(id), DispatchCarInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
