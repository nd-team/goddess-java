package com.bjike.goddess.staffing.action.staffing;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffing.api.CountAPI;
import com.bjike.goddess.staffing.dto.CountDTO;
import com.bjike.goddess.staffing.vo.TaskSituationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 操作汇总
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-09 02:00 ]
 * @Description: [ 操作汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("count")
public class CountAction {
    @Autowired
    private CountAPI countAPI;

    /**
     * 查看某天的任务情况
     *
     * @param dto dto
     * @return class TaskSituationVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/situation")
    public Result countSituation(CountDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<TaskSituationVO> list = BeanTransform.copyProperties(
                    countAPI.countSituation(dto), TaskSituationVO.class, request);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}