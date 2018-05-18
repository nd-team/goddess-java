package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.WorkOGBO;
import com.bjike.goddess.recruit.entity.WorkOG;
import com.bjike.goddess.recruit.service.WorkOGSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作对赌业务接口实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 02:33 ]
 * @Description: [ 工作对赌业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("workOGApiImpl")
public class WorkOGApiImpl implements WorkOGAPI {

    @Autowired
    private WorkOGSer workOGSer;

    @Override
    public void addWork(WorkOG workOG) throws SerException {
        workOGSer.add(workOG);
    }

    @Override
    public void del(String id) throws SerException {
        workOGSer.del(id);
    }

    @Override
    public void update(WorkOG workOG) throws SerException {
        workOGSer.update(workOG);
    }

    @Override
    public WorkOGBO getWorkOG(String id) throws SerException {
        return BeanTransform.wanycopyProperties(workOGSer.getWorkOG(id), WorkOGBO.class);
    }

    @Override
    public List<WorkOGBO> getRemind(String name) throws SerException {
        return workOGSer.getRemind(name);
    }

    @Override
    public List<WorkOGBO> getWorkMsg(String name) throws SerException {
        return workOGSer.getWorkMsg(name);
    }

    @Override
    public List<WorkOGBO> getWorkScore(String name) throws SerException {
        return workOGSer.getWorkScore(name);
    }

    @Override
    public WorkOG findByModular(String modular) {
        return workOGSer.findByModular(modular);
    }

    @Override
    public WorkOGBO findByTime(String time) {
        return workOGSer.findByTime(time);
    }
}