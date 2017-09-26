package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.service.TaskSer;
import com.bjike.goddess.task.to.TaskTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-19 11:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("taskApiImpl")
public class TaskApiImpl implements TaskAPI {
    @Autowired
    private TaskSer taskSer;

    @Override
    public void issued(TaskTO to) throws SerException {
        taskSer.issued(to);
    }
}
