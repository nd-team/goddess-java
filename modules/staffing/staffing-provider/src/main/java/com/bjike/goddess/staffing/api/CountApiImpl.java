package com.bjike.goddess.staffing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffing.bo.CountBO;
import com.bjike.goddess.staffing.bo.TaskSituationBO;
import com.bjike.goddess.staffing.dto.CountDTO;
import com.bjike.goddess.staffing.service.CountSer;
import com.bjike.goddess.staffing.to.CountTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作汇总业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-09 02:00 ]
 * @Description: [ 操作汇总业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("countApiImpl")
public class CountApiImpl implements CountAPI {
    @Autowired
    private CountSer countSer;

    @Override
    public void add(CountTO to) throws SerException {
        countSer.add(to);
    }

    @Override
    public List<TaskSituationBO> countSituation(CountDTO dto) throws SerException {
        return countSer.countSituation(dto);
    }
}