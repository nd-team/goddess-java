package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ResumeInfoBO;
import com.bjike.goddess.archive.bo.ResumeInfoDataBO;
import com.bjike.goddess.archive.dto.ResumeInfoDTO;
import com.bjike.goddess.archive.dto.StaffRecordsDTO;
import com.bjike.goddess.archive.entity.ResumeInfo;
import com.bjike.goddess.archive.entity.StaffRecords;
import com.bjike.goddess.archive.enums.GuideAddrStatus;
import com.bjike.goddess.archive.excel.ResumeInfoExportExcel;
import com.bjike.goddess.archive.excel.ResumeInfoImportExcel;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.to.ResumeInfoTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 人员简历信息业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-14 11:30 ]
 * @Description: [ 人员简历信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class ResumeInfoSerImpl extends ServiceImpl<ResumeInfo, ResumeInfoDTO> implements ResumeInfoSer {

    @Autowired
    private StaffRecordsSer staffRecordsSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private RotainCusPermissionSer cusPermissionSer;

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
            flag = cusPermissionSer.getRotainCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
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
            flag = cusPermissionSer.getRotainCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }


    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getRotainCusPermission("2");
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

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getRotainCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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
    public ResumeInfoDataBO findDataByName(String name) throws SerException {
        StaffRecordsDTO staffRecordsDTO = new StaffRecordsDTO();
        staffRecordsDTO.getConditions().add(Restrict.eq("username", name));
        List<StaffRecords> staffRecordses = staffRecordsSer.findByCis(staffRecordsDTO);
        if (null != staffRecordses && staffRecordses.size() > 0) {
            StaffRecords staffRecords = staffRecordses.get(0);
            ResumeInfoDataBO resumeInfoDataBO = new ResumeInfoDataBO();
            BeanUtils.copyProperties(staffRecords, resumeInfoDataBO);
            return resumeInfoDataBO;
        }
        return null;
    }

    @Override
    public void add(ResumeInfoTO to) throws SerException {
        ResumeInfo entity = new ResumeInfo();
        BeanUtils.copyProperties(to, entity);
        entity.setStatus(Status.THAW);
        super.save(entity);
    }

    @Override
    public void edit(ResumeInfoTO to) throws SerException {
        ResumeInfo entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据对象为空");
        }
        BeanUtils.copyProperties(to, entity, "createTime", "id");
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public ResumeInfoBO findEntity(String id) throws SerException {
        ResumeInfo entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象为空");
        }
        ResumeInfoBO bo = BeanTransform.copyProperties(entity, ResumeInfoBO.class, false);
        return bo;
    }

    @Override
    public void delete(String id) throws SerException {
        ResumeInfo entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象为空");
        }
        super.remove(entity);
    }

    @Override
    public byte[] exportExcel(ResumeInfoDTO dto) throws SerException {
        dto = findData(dto);
        searchCondition(dto);
        List<ResumeInfo> resumeInfos = super.findByCis(dto);
        ResumeInfoExportExcel resumeInfoExportExcel = new ResumeInfoExportExcel();
        List<ResumeInfoExportExcel> resumeInfoExportExcels = new ArrayList<>(0);
        Excel excel = new Excel(0, 2);
        List<ResumeInfoBO> bos = BeanTransform.copyProperties(resumeInfos, ResumeInfoBO.class, false);
        resumeInfoExportExcels = transExcel(bos, resumeInfoExportExcels);
        byte[] bytes = ExcelUtil.clazzToExcel(resumeInfoExportExcels, excel);
        return bytes;
    }

    @Override
    public void freeze(String id) throws SerException {
        ResumeInfo entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象为空");
        }
        entity.setStatus(Status.CONGEAL);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void thaw(String id) throws SerException {
        ResumeInfo entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象为空");
        }
        entity.setStatus(Status.THAW);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void upload(List<ResumeInfoTO> toList) throws SerException {
        for (ResumeInfoTO to : toList) {
            this.add(to);
        }
    }

    @Override
    public List<ResumeInfoBO> maps(ResumeInfoDTO dto) throws SerException {
        dto = findData(dto);
        searchCondition(dto);
        List<ResumeInfo> resumeInfos = super.findByCis(dto);
        List<ResumeInfoBO> bos = BeanTransform.copyProperties(resumeInfos, ResumeInfoBO.class, false);
        return bos;
    }

    @Override
    public Long getTotal(ResumeInfoDTO dto) throws SerException {
        dto = findData(dto);
        searchCondition(dto);
        return super.count(dto);
    }

    @Override
    public byte[] templateExcel() throws SerException {
        ResumeInfoImportExcel resumeInfoImportExcel = new ResumeInfoImportExcel();
        List<ResumeInfoImportExcel> resumeInfoImportExcels = new ArrayList<>(0);
        resumeInfoImportExcels.add(resumeInfoImportExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(resumeInfoImportExcels, excel);
        return bytes;
    }

    @Override
    public List<String> findUserName() throws SerException {
        List<ResumeInfo> resumeInfos = super.findAll();
        if (null != resumeInfos && resumeInfos.size() > 0) {
            List<String> list = resumeInfos.stream().map(ResumeInfo::getUsername).collect(Collectors.toList());
            return list;
        }
        return null;
    }

    private List<ResumeInfoExportExcel> transExcel(List<ResumeInfoBO> bos, List<ResumeInfoExportExcel> resumeInfoExportExcels) throws SerException {
        if (null != bos && bos.size() > 0) {
            BeanUtils.copyProperties(bos, resumeInfoExportExcels, "status");
            List<Status> statuses = bos.stream().map(ResumeInfoBO::getStatus).collect(Collectors.toList());
            if (statuses.size() == resumeInfoExportExcels.size() && statuses.size() > 0) {
                for (int i = 0; i < statuses.size(); i++) {
                    resumeInfoExportExcels.get(i).setStatus(tarnsStatus(statuses.get(i)));
                }
            }
        }
        return resumeInfoExportExcels;
    }

    private String tarnsStatus(Status status) throws SerException {
        String str = "";
        switch (status) {
            case THAW:
                str = "解冻";
                break;
            case CONGEAL:
                str = "冻结";
                break;
            case DELETE:
                str = "删除";
                break;
            case NOACTIVE:
                str = "未激活";
                break;
            case UNREVIEW:
                str = "未审核";
                break;
        }
        return str;
    }

    private void searchCondition(ResumeInfoDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getUsername())) {
            dto.getConditions().add(Restrict.eq("username", dto.getUsername()));
        }
        if (StringUtils.isNotBlank(dto.getProject())) {
            dto.getConditions().add(Restrict.eq("project", dto.getProject()));
        }
    }

    /**
     * 是否有权限查看所有人的信息(岗位级别)
     */
    private Boolean guideSeePositionIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.guideSeePositionIdentity();
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 根据岗位查看所有信息或个人信息
     */
    private ResumeInfoDTO findData(ResumeInfoDTO dto) throws SerException {
        if (!guideSeePositionIdentity()) {
            dto = new ResumeInfoDTO();
            String userToken = RpcTransmit.getUserToken();
            UserBO userBO = userAPI.currentUser();
            RpcTransmit.transmitUserToken(userToken);
            dto.getConditions().add(Restrict.eq("username", userBO.getUsername()));
        }
        return dto;
    }
}