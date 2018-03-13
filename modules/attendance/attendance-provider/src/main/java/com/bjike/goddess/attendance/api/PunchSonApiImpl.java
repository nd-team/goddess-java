package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.CaseCountBO;
import com.bjike.goddess.attendance.bo.PunchBO;
import com.bjike.goddess.attendance.bo.PunchPhoneBO;
import com.bjike.goddess.attendance.bo.PunchSonBO;
import com.bjike.goddess.attendance.dto.PunchDTO;
import com.bjike.goddess.attendance.dto.overtime.OverTimesDTO;
import com.bjike.goddess.attendance.excel.PunchImportExcel;
import com.bjike.goddess.attendance.service.PunchSonSer;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.PunchSonTO;
import com.bjike.goddess.attendance.vo.OverWorkTimesVO;
import com.bjike.goddess.attendance.vo.PunchSonVO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 打卡子表业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:26 ]
 * @Description: [ 打卡子表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("punchSonApiImpl")
public class PunchSonApiImpl implements PunchSonAPI {
    @Autowired
    private PunchSonSer punchSonSer;

    @Override
    public PunchSonBO save(PunchSonTO to) throws SerException {
        return punchSonSer.save(to);
    }

    @Override
    public List<String> string(Double longitude, Double latitude, String area) throws SerException {
        return punchSonSer.string(longitude, latitude, area);
    }

    @Override
    public List<PunchBO> list(PunchDTO dto) throws SerException {
        return punchSonSer.list(dto);
    }

    @Override
    public Long count(PunchDTO dto) throws SerException {
        return punchSonSer.count(dto);
    }

    @Override
    public List<PunchPhoneBO> phoneList(PunchDTO dto) throws SerException {
        return punchSonSer.phoneList(dto);
    }

    @Override
    public List<CaseCountBO> caseCount(PunchDTO dto) throws SerException {
        return punchSonSer.caseCount(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return punchSonSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return punchSonSer.guidePermission(guidePermissionTO);
    }

    @Override
    public OverWorkTimesVO userOverTimeCollect(OverTimesDTO overTimesDTO) throws SerException {
        return punchSonSer.userOverTimeCollect(overTimesDTO);
    }

    @Override
    public byte[] exportExcel(PunchDTO dto) throws SerException {
        return punchSonSer.exportExcel(dto);
    }

    @Override
    public byte[] templateExcel() throws SerException {
        return punchSonSer.templateExcel();
    }

    @Override
    public void upload(List<PunchImportExcel> tos) throws SerException {
        punchSonSer.upload(tos);
    }

    @Override
    public Long currentUserLateCount() throws SerException {
        return punchSonSer.currentUserLateCount();
    }

    @Override
    public Boolean isPunch(PunchSonTO to) throws SerException {
        return punchSonSer.isPunch(to);
    }

    @Override
    public List<PunchSonBO> getPunchSon(String date) throws SerException {
        return punchSonSer.getPunchSon(date);
    }
}