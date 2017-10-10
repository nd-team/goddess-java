package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.DayReportBO;
import com.bjike.goddess.attendance.dto.DayReportDTO;
import com.bjike.goddess.attendance.entity.DayReport;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.taskallotment.api.TaskNodeAPI;
import com.bjike.goddess.taskallotment.bo.DayBO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 日报业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 10:28 ]
 * @Description: [ 日报业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attendanceSerCache")
@Service
public class DayReportSerImpl extends ServiceImpl<DayReport, DayReportDTO> implements DayReportSer {
    @Autowired
    private TaskNodeAPI taskNodeAPI;
    @Autowired
    private UserAPI userAPI;

    @Override
    public List<DayReportBO> list(DayReportDTO dto) throws SerException {
        String[] names = dto.getNames();
        //todo:权限
        if (null == names) {
            names = new String[]{userAPI.currentUser().getUsername()};
        }
        String time = DateUtil.dateToString(LocalDate.now());
        List<DayBO> list = taskNodeAPI.dayReport(time, names);
        return BeanTransform.copyProperties(list, DayReportBO.class);
    }
}