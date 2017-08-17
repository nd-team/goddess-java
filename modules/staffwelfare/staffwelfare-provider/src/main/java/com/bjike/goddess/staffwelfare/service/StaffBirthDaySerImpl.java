package com.bjike.goddess.staffwelfare.service;

import com.bjike.goddess.archive.api.StaffRecordsAPI;
import com.bjike.goddess.archive.bo.StaffRecordsBO;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.dimission.api.DimissionInfoAPI;
import com.bjike.goddess.dimission.entity.DimissionInfo;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.entity.EntryBasicInfo;
import com.bjike.goddess.staffwelfare.bo.StaffBirthDayBO;
import com.bjike.goddess.staffwelfare.dto.StaffBirthDayDTO;
import com.bjike.goddess.staffwelfare.entity.StaffBirthDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haikuang on 17-8-17.
 */
@CacheConfig(cacheNames = "staffwelfaremanageSerCache")
@Service
public class StaffBirthDaySerImpl extends ServiceImpl<StaffBirthDay, StaffBirthDayDTO> implements StaffBirthDaySer {
    @Autowired
    private StaffRecordsAPI staffRecordsAPI;

    @Autowired
    private DimissionInfoAPI dimissionInfoAPI;

    @Autowired
    private EntryBasicInfoAPI entryBasicInfoAPI;

    @Autowired
    private ModuleAPI moduleAPI;

    @Override
    public List<StaffBirthDayBO> findBirthDay(StaffBirthDayDTO dto) throws SerException {
        List<StaffBirthDayBO> boList = new ArrayList<>(0);
        if (moduleAPI.isCheck("archive")) {
            List<StaffRecordsBO> staffRecordsBOS = staffRecordsAPI.findByMonth(dto.getMonth());
            if (staffRecordsBOS != null && !staffRecordsBOS.isEmpty()) {
                if (moduleAPI.isCheck("dimission")) {
                    for (StaffRecordsBO staffRecordsBO : staffRecordsBOS) {
                        List<DimissionInfo> infoList = dimissionInfoAPI.findByName(staffRecordsBO.getUsername());
                        if (infoList == null && infoList.isEmpty()) {
                            for (StaffBirthDayBO staffBirthDayBO : boList) {
                                List<EntryBasicInfoBO> entryBasicInfo = entryBasicInfoAPI.getByEmpNumber(staffRecordsBO.getSerialNumber());
                                if (entryBasicInfo != null && !entryBasicInfo.isEmpty()) {
                                    staffBirthDayBO.setArea(entryBasicInfo.get(0).getArea());
                                    staffBirthDayBO.setDepartment(entryBasicInfo.get(0).getDepartment());
                                    staffBirthDayBO.setUserName(entryBasicInfo.get(0).getName());
                                    staffBirthDayBO.setMonth(dto.getMonth());
                                } else {
                                    throw new SerException("公司不存在该员工");
                                }
                            }
                        } else {
                            for (StaffBirthDayBO staffBirthDayBO : boList) {
                                for(DimissionInfo dimissionInfo : infoList) {
                                    List<EntryBasicInfoBO> entryBasicInfo = entryBasicInfoAPI.getByEmpNumber(staffRecordsBO.getSerialNumber());
                                    if (entryBasicInfo != null && !entryBasicInfo.isEmpty()) {
                                        staffBirthDayBO.setArea(entryBasicInfo.get(0).getArea());
                                        staffBirthDayBO.setDepartment(entryBasicInfo.get(0).getDepartment());
                                        staffBirthDayBO.setUserName(entryBasicInfo.get(0).getName());
                                        staffBirthDayBO.setIfDimission(true);
                                        staffBirthDayBO.setDimissionDate(dimissionInfo.getDimissionDate().toString().replace("T"," "));
                                        staffBirthDayBO.setMonth(dto.getMonth());
                                    } else {
                                        throw new SerException("公司不存在该员工");
                                    }
                                }
                            }
                        }
                    }

                }
            } else {
                for (StaffRecordsBO staffRecordsBO : staffRecordsBOS) {
                    for (StaffBirthDayBO staffBirthDayBO : boList) {
                        List<EntryBasicInfoBO> entryBasicInfo = entryBasicInfoAPI.getByEmpNumber(staffRecordsBO.getSerialNumber());
                        if (entryBasicInfo != null && !entryBasicInfo.isEmpty()) {
                            staffBirthDayBO.setArea(entryBasicInfo.get(0).getArea());
                            staffBirthDayBO.setDepartment(entryBasicInfo.get(0).getDepartment());
                            staffBirthDayBO.setUserName(entryBasicInfo.get(0).getName());
                            staffBirthDayBO.setMonth(dto.getMonth());
                        } else {
                            throw new SerException("公司不存在该员工");
                        }
                    }
                }
            }
        }
        return boList;
    }
}
