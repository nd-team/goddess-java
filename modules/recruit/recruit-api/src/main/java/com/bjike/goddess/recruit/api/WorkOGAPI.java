package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.WorkOGBO;
import com.bjike.goddess.recruit.entity.RemindMsg;
import com.bjike.goddess.recruit.entity.WorkOG;

import java.util.List;

/**
 * 工作对赌业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 02:33 ]
 * @Description: [ 工作对赌业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WorkOGAPI {


    void addWork(WorkOG workOG) throws SerException;

    void del(String id) throws SerException;

    void update(WorkOG workOG) throws SerException;

    WorkOGBO getWorkOG(String id) throws SerException;

    List<WorkOGBO> getRemind(String name) throws SerException;

    List<WorkOGBO> getWorkMsg(String name) throws SerException;

    List<WorkOGBO> getWorkScore(String name) throws SerException;

    WorkOG findByModular(String modular);
}