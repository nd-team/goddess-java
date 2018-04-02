package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.WorkOGBO;
import com.bjike.goddess.recruit.dao.WorkOGRep;
import com.bjike.goddess.recruit.dto.WorkOGDTO;
import com.bjike.goddess.recruit.entity.RemindMsg;
import com.bjike.goddess.recruit.entity.WorkOG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 工作对赌业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 02:33 ]
 * @Description: [ 工作对赌业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "recruitSerCache")
@Service
public class WorkOGSerImpl extends ServiceImpl<WorkOG, WorkOGDTO> implements WorkOGSer {

    @Autowired
    private WorkOGRep workOGRep;

    @Override
    public void add(WorkOG workOG) throws SerException {
        super.save(workOG);
    }

    @Transactional
    @Override
    public void del(String id) throws SerException {
//        super.remove(id);
        workOGRep.deleteByModular(id);
    }

    @Override
    public void upd(WorkOG workOG) throws SerException {
        super.update(workOG);
    }


    @Transactional
    @Override
    public WorkOGBO getWorkOG(String id) throws SerException {
        return BeanTransform.wanycopyProperties(workOGRep.findById(id), WorkOGBO.class);
    }

    //智能提醒
    @Override
    public List<WorkOGBO> getRemind(String name) throws SerException {
        String sql = "SELECT id, time,content FROM recruit_intelligentmsg WHERE user='" + name + "'";
        String[] fiedls = {"id", "time","nodeCON"};
        return super.findBySql(sql, WorkOGBO.class, fiedls);
    }

    //对赌消息
    @Transactional
    @Override
    public List<WorkOGBO> getWorkMsg(String name) throws SerException {
        return BeanTransform.wanycopyProperties(workOGRep.findByRatersOrScoringOB(name,name),WorkOGBO.class);
    }

    //评分消息
    @Override
    public List<WorkOGBO> getWorkIndex(String name) throws SerException {
        return null;
    }

    @Override
    public List<WorkOGBO> getWorkScore(String name) throws SerException {
        String sql = "SELECT id,time,acceptTime,raters,score,scoreContent FROM recruit_workog WHERE scoringOB = 'ca'";
        String[] fiedls = {"id","time","acceptTime","raters","score","scoreContent"};
        return findBySql(sql, WorkOGBO.class, fiedls);
    }

    @Override
    public WorkOG findByModular(String modular) {
        return workOGRep.findByModular(modular);
    }


    @Transactional
    @Override
    public WorkOGBO findByTime(String time) {
        return BeanTransform.wanycopyProperties(workOGRep.findByTime(time), WorkOGBO.class);
    }


}