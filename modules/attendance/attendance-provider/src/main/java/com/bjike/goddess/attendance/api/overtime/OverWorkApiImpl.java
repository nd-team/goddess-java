package com.bjike.goddess.attendance.api.overtime;

import com.bjike.goddess.attendance.bo.overtime.AreaBO;
import com.bjike.goddess.attendance.bo.overtime.OverWorkBO;
import com.bjike.goddess.attendance.bo.overtime.OverWorkCountBO;
import com.bjike.goddess.attendance.bo.overtime.OverWorkRestDayBO;
import com.bjike.goddess.attendance.dto.overtime.*;
import com.bjike.goddess.attendance.excel.OverWorkImportExcel;
import com.bjike.goddess.attendance.excel.SonPermissionObject;
import com.bjike.goddess.attendance.service.CusPermissionSer;
import com.bjike.goddess.attendance.service.overtime.OverWorkSer;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.OverWorkAuditTO;
import com.bjike.goddess.attendance.to.OverWorkTO;
import com.bjike.goddess.attendance.vo.OverLongAndRelaxDayVO;
import com.bjike.goddess.attendance.vo.OverWorkTimesVO;
import com.bjike.goddess.attendance.vo.PositionAndDepartVO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 加班业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("overWorkApiImpl")
public class OverWorkApiImpl implements OverWorkAPI {

    @Autowired
    private OverWorkSer overWorkSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;


    @Override
    public Long countOverWork(OverWorkDTO overWorkDTO) throws SerException {
        return overWorkSer.countOverWork(overWorkDTO);
    }

    @Override
    public OverWorkBO getOneById(String id) throws SerException {
        return overWorkSer.getOneById(id);
    }

    @Override
    public List<OverWorkBO> listOverWork(OverWorkDTO overWorkDTO) throws SerException {
        return overWorkSer.listOverWork(overWorkDTO);
    }

    @Override
    public OverWorkBO addOverWork(OverWorkTO overWorkTO) throws SerException {
        return overWorkSer.addOverWork(overWorkTO);
    }

    @Override
    public void deleteOverWork(String id) throws SerException {
        overWorkSer.deleteOverWork(id);
    }

    @Override
    public List<AreaBO> areaList() throws SerException {
        return overWorkSer.areaList();
    }

    @Override
    public List<String> peopleList() throws SerException {
        return overWorkSer.peopleList();
    }

    @Override
    public PositionAndDepartVO getPositAndDepart(String overWorker) throws SerException {
        return overWorkSer.getPositAndDepart(overWorker);
    }

    @Override
    public OverLongAndRelaxDayVO caculateTime(OverLongAndRelaxdayDTO overLongAndRelaxdayDTO) throws SerException {
        return overWorkSer.caculateTime(overLongAndRelaxdayDTO);
    }

    @Override
    public Long countAudit(OverWorkDTO overWorkDTO) throws SerException {
        return overWorkSer.countAudit(overWorkDTO);
    }

    @Override
    public List<OverWorkBO> listAudit(OverWorkDTO overWorkDTO) throws SerException {
        return overWorkSer.listAudit(overWorkDTO);
    }

    @Override
    public OverWorkBO auditOverWork(OverWorkAuditTO auditTO) throws SerException {
        return overWorkSer.auditOverWork(auditTO);
    }

    @Override
    public Long countRestDay(OverWorkRestDayDTO overWorkRestDayDTO) throws SerException {
        return overWorkSer.countRestDay(overWorkRestDayDTO);
    }

    @Override
    public List<OverWorkRestDayBO> listRestDay(OverWorkRestDayDTO overWorkRestDayDTO) throws SerException {
        return overWorkSer.listRestDay(overWorkRestDayDTO);
    }

    @Override
    public List<OverWorkBO> myListOverWork(PhoneMyOverWorkDTO phoneMyOverWorkDTO) throws SerException {
        return overWorkSer.myListOverWork(phoneMyOverWorkDTO);
    }

    @Override
    public List<OverWorkBO> myEntryList(PhoneMyEntryOverWorkDTO phoneMyEntryOverWorkDTO) throws SerException {
        return overWorkSer.myEntryList(phoneMyEntryOverWorkDTO);
    }

    @Override
    public List<OverWorkBO> myAuditList(PhoneMyEntryOverWorkDTO phoneMyEntryOverWorkDTO) throws SerException {
        return overWorkSer.myAuditList(phoneMyEntryOverWorkDTO);
    }

    @Override
    public OverWorkBO getPhoneOneById(String id) throws SerException {
        return overWorkSer.getPhoneOneById(id);
    }

    @Override
    public OverWorkCountBO outWorkCount(OverWorkDTO dto) throws SerException {
        return overWorkSer.outWorkCount(dto);

    }

    @Override
    public Boolean sonPermission() throws SerException {
        return overWorkSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return overWorkSer.guidePermission(guidePermissionTO);
    }

    @Override
    public OverWorkTimesVO userOverTimeCollect(OverTimesDTO overTimesDTO ) throws SerException {
        return overWorkSer.userOverTimeCollect( overTimesDTO ) ;
    }

    @Override
    public byte[] exportExcel(OverWorkDTO dto) throws SerException {
        return overWorkSer.exportExcel(dto) ;
    }

    @Override
    public byte[] templateExcel() throws SerException {
        return overWorkSer.templateExcel();
    }

    @Override
    public void upload(List<OverWorkImportExcel> tos) throws SerException {
        overWorkSer.upload(tos);
    }

    @Override
    public Long currentUserCount() throws SerException {
        return overWorkSer.currentUserCount();
    }

    @Override
    public Boolean getDepartment(String idFlag) throws SerException {
        return overWorkSer.getDepartment(idFlag);
    }

    /**
     * 考勤项目经理权限添加
     * @param guidePermissionTO
     * @return
     * @throws SerException
     */
    @Override
    public Boolean guideCusPermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return overWorkSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<SonPermissionObject> theSonPermission() throws SerException {
        return overWorkSer.theSonPermission();
    }

}