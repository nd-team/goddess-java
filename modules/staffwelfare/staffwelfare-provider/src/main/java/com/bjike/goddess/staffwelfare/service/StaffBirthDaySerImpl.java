package com.bjike.goddess.staffwelfare.service;

import com.bjike.goddess.archive.api.StaffRecordsAPI;
import com.bjike.goddess.archive.bo.StaffRecordsBO;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.dimission.api.DimissionInfoAPI;
import com.bjike.goddess.dimission.entity.DimissionInfo;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.entity.EntryBasicInfo;
import com.bjike.goddess.staffwelfare.bo.StaffBirthDayBO;
import com.bjike.goddess.staffwelfare.dto.StaffBirthDayDTO;
import com.bjike.goddess.staffwelfare.entity.StaffBirthDay;
import com.bjike.goddess.staffwelfare.enums.GuideAddrStatus;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import scala.Array;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

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
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
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

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
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
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
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
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
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
    public List<StaffBirthDayBO> findBirthDay(StaffBirthDayDTO dto) throws SerException {
        List<StaffBirthDayBO> boList = new ArrayList<>(0);
        if (moduleAPI.isCheck("archive")) {
            List<StaffRecordsBO> staffRecordsBOS = staffRecordsAPI.findByMonth(dto.getMonth());
            List<StaffBirthDayBO> bos = new ArrayList<>(staffRecordsBOS.size());
            if (staffRecordsBOS != null && staffRecordsBOS.size() > 0) {
                if (moduleAPI.isCheck("dimission")) {
                    for (StaffRecordsBO staffRecordsBO : staffRecordsBOS) {
                        List<DimissionInfo> infoList = dimissionInfoAPI.findByName(staffRecordsBO.getUsername());
                        if (infoList != null && infoList.size() > 0) {
                            for (DimissionInfo dimissionInfo : infoList) {
                                List<EntryBasicInfoBO> entryBasicInfo = entryBasicInfoAPI.getByEmpNumber(staffRecordsBO.getSerialNumber());
                                StaffBirthDayBO staffBirthDayBO = new StaffBirthDayBO();
                                if (entryBasicInfo != null && !entryBasicInfo.isEmpty()) {
                                    staffBirthDayBO.setArea(entryBasicInfo.get(0).getArea());
                                    staffBirthDayBO.setDepartment(entryBasicInfo.get(0).getDepartment());
                                    staffBirthDayBO.setUserName(entryBasicInfo.get(0).getName());
                                    staffBirthDayBO.setIfDimission(true);
                                    staffBirthDayBO.setDimissionDate(dimissionInfo.getDimissionDate().toString().replace("T", " "));
                                    staffBirthDayBO.setMonth(dto.getMonth());
                                } else {
                                    throw new SerException("公司不存在该员工");
                                }
                                boList.add(staffBirthDayBO);
                            }
                        } else {
                            StaffBirthDayBO staffBirthDayBO = new StaffBirthDayBO();
                            List<EntryBasicInfoBO> entryBasicInfo = entryBasicInfoAPI.getByEmpNumber(staffRecordsBO.getSerialNumber());
                            if (entryBasicInfo != null && !entryBasicInfo.isEmpty()) {
                                staffBirthDayBO.setArea(entryBasicInfo.get(0).getArea());
                                staffBirthDayBO.setDepartment(entryBasicInfo.get(0).getDepartment());
                                staffBirthDayBO.setUserName(entryBasicInfo.get(0).getName());
                                staffBirthDayBO.setMonth(dto.getMonth());
                            } else {
                                throw new SerException("公司不存在该员工");
                            }
                            boList.add(staffBirthDayBO);
                        }
                    }

                }
            } else {
                for (StaffRecordsBO staffRecordsBO : staffRecordsBOS) {
                    List<EntryBasicInfoBO> entryBasicInfo = entryBasicInfoAPI.getByEmpNumber(staffRecordsBO.getSerialNumber());
                    StaffBirthDayBO staffBirthDayBO = new StaffBirthDayBO();
                    if (entryBasicInfo != null && entryBasicInfo.size() > 0) {
                        staffBirthDayBO.setArea(entryBasicInfo.get(0).getArea());
                        staffBirthDayBO.setDepartment(entryBasicInfo.get(0).getDepartment());
                        staffBirthDayBO.setUserName(entryBasicInfo.get(0).getName());
                        staffBirthDayBO.setMonth(dto.getMonth());
                    } else {
                        throw new SerException("公司不存在该员工");
                    }
                    boList.add(staffBirthDayBO);
                }
            }
        }
        return boList;
    }
}