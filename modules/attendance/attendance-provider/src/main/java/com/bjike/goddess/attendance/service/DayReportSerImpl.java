package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.DayReportBO;
import com.bjike.goddess.attendance.bo.DayReportCountBO;
import com.bjike.goddess.attendance.dto.DayReportDTO;
import com.bjike.goddess.attendance.entity.DayReport;
import com.bjike.goddess.attendance.enums.CountType;
import com.bjike.goddess.attendance.enums.GuideAddrStatus;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.taskallotment.api.TaskNodeAPI;
import com.bjike.goddess.taskallotment.bo.DayReport.DaysBO;
import com.bjike.goddess.taskallotment.bo.DayReport.DayReportMailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
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
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case SEE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public List<DayReportBO> list(DayReportDTO dto) throws SerException {
        String[] names = dto.getNames();
        if (null == names) {
            names = new String[]{userAPI.currentUser().getUsername()};
        }
        String time = dto.getTime();
        if (null == time) {
            time = DateUtil.dateToString(LocalDate.now());
        }
        List<DaysBO> list = taskNodeAPI.dayReport(time, names);
        return BeanTransform.copyProperties(list, DayReportBO.class);
    }

    @Override
    public DayReportCountBO dayCount(DayReportDTO dto) throws SerException {
        if (CountType.DEPART.equals(dto.getCountType())) {
            String[] departs = dto.getDepartIds();
            if (null == departs) {
                throw new SerException("部门汇总必须选择部门");
            }
        }else if(CountType.PERSONAL.equals(dto.getCountType())){
            String[] personals = dto.getNames();
            if (null == personals) {
                throw new SerException("个人汇总必须选择姓名");
            }
        }
        return BeanTransform.copyProperties(taskNodeAPI.dayCount(dto.getStartTime(), dto.getEndTime(), dto.getDepartIds(),dto.getNames()), DayReportCountBO.class);
    }

    @Override
    public List<DayReportMailBO> dayCountMail(DayReportDTO dto) throws SerException {
        return taskNodeAPI.dayCountMail(dto.getStartTime(), dto.getEndTime(), dto.getDepartIds());
    }
}