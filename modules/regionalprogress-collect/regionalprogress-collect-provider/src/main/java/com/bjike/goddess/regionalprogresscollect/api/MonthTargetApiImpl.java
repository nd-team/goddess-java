package com.bjike.goddess.regionalprogresscollect.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.regionalprogresscollect.bo.MonthTargetBO;
import com.bjike.goddess.regionalprogresscollect.dto.MonthTargetDTO;
import com.bjike.goddess.regionalprogresscollect.service.MonthTargetSer;
import com.bjike.goddess.regionalprogresscollect.to.MonthTargetTO;
import com.bjike.goddess.regionalprogresscollect.to.StandardTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 月指标业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 03:13 ]
 * @Description: [ 月指标业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("monthTargetApiImpl")
public class MonthTargetApiImpl implements MonthTargetAPI {

    @Autowired
    private MonthTargetSer monthTargetSer;

    @Override
    public MonthTargetBO save(MonthTargetTO to) throws SerException {
        return monthTargetSer.save(to);
    }

    @Override
    public MonthTargetBO update(MonthTargetTO to) throws SerException {
        return monthTargetSer.update(to);
    }

    @Override
    public MonthTargetBO delete(String id) throws SerException {
        return monthTargetSer.delete(id);
    }

    @Override
    public MonthTargetBO getById(String id) throws SerException {
        return monthTargetSer.getById(id);
    }

    @Override
    public MonthTargetBO updateStandard(StandardTO to) throws SerException {
        return monthTargetSer.updateStandard(to);
    }

    @Override
    public List<MonthTargetBO> maps(MonthTargetDTO dto) throws SerException {
        return monthTargetSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        return monthTargetSer.getTotal();
    }
}