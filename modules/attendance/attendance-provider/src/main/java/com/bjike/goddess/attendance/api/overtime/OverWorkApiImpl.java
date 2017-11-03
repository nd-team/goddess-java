package com.bjike.goddess.attendance.api.overtime;

import com.bjike.goddess.attendance.bo.overtime.AreaBO;
import com.bjike.goddess.attendance.bo.overtime.OverWorkBO;
import com.bjike.goddess.attendance.bo.overtime.OverWorkRestDayBO;
import com.bjike.goddess.attendance.dto.overtime.*;
import com.bjike.goddess.attendance.service.overtime.OverWorkSer;
import com.bjike.goddess.attendance.to.overtime.OverWorkAuditTO;
import com.bjike.goddess.attendance.to.overtime.OverWorkTO;
import com.bjike.goddess.attendance.vo.overtime.OverLongAndRelaxDayVO;
import com.bjike.goddess.attendance.vo.overtime.PositionAndDepartVO;
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


    @Override
    public Long countOverWork(OverWorkDTO overWorkDTO) throws SerException {
        return overWorkSer.countOverWork( overWorkDTO );
    }

    @Override
    public OverWorkBO getOneById(String id) throws SerException {
        return overWorkSer.getOneById(id);
    }

    @Override
    public List<OverWorkBO> listOverWork(OverWorkDTO overWorkDTO) throws SerException {
        return overWorkSer.listOverWork( overWorkDTO );
    }

    @Override
    public OverWorkBO addOverWork(OverWorkTO overWorkTO) throws SerException {
        return overWorkSer.addOverWork( overWorkTO );
    }

    @Override
    public void deleteOverWork(String id) throws SerException {
        overWorkSer.deleteOverWork( id );
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
        return overWorkSer.getPositAndDepart( overWorker );
    }

    @Override
    public OverLongAndRelaxDayVO caculateTime(OverLongAndRelaxdayDTO overLongAndRelaxdayDTO) throws SerException {
        return overWorkSer.caculateTime( overLongAndRelaxdayDTO );
    }

    @Override
    public Long countAudit(OverWorkDTO overWorkDTO) throws SerException {
        return overWorkSer.countAudit( overWorkDTO);
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
        return overWorkSer.myListOverWork( phoneMyOverWorkDTO );
    }

    @Override
    public List<OverWorkBO> myEntryList(PhoneMyEntryOverWorkDTO phoneMyEntryOverWorkDTO) throws SerException {
        return overWorkSer.myEntryList( phoneMyEntryOverWorkDTO );
    }

    @Override
    public List<OverWorkBO> myAuditList(PhoneMyEntryOverWorkDTO phoneMyEntryOverWorkDTO) throws SerException {
        return overWorkSer.myAuditList( phoneMyEntryOverWorkDTO ) ;
    }

    @Override
    public OverWorkBO getPhoneOneById(String id) throws SerException {
        return overWorkSer.getPhoneOneById( id ) ;
    }
}