package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.SenioritySubsidiesBO;
import com.bjike.goddess.assistance.dto.SenioritySubsidiesDTO;
import com.bjike.goddess.assistance.dto.SenioritySubsidiesStandardDTO;
import com.bjike.goddess.assistance.entity.SenioritySubsidies;
import com.bjike.goddess.assistance.entity.SenioritySubsidiesStandard;
import com.bjike.goddess.assistance.enums.GuideAddrStatus;
import com.bjike.goddess.assistance.enums.SubsidiesStatus;
import com.bjike.goddess.assistance.excel.SenioritySubsidiesImport;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.SenioritySubsidiesTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 工龄补助业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 11:34 ]
 * @Description: [ 工龄补助业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class SenioritySubsidiesSerImpl extends ServiceImpl<SenioritySubsidies, SenioritySubsidiesDTO> implements SenioritySubsidiesSer {
    @Autowired
    private SenioritySubsidiesStandardSer senioritySubsidiesStandardSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 核对添加删除修改查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对审核权限（岗位级别）
     */
    private void checkAduitIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加删除修改查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对审核权限（岗位级别）
     */
    private Boolean guideAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    private Boolean allTrueIdentity() throws SerException {
        return true;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAuditIdentity();
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
                flag = guideSeeIdentity();
                break;
            case EDIT:
                flag = guideSeeIdentity();
                break;
            case DELETE:
                flag = guideSeeIdentity();
                break;
            case MANAGEAUDIT:
                flag = guideAuditIdentity();
                break;
            case CONFIRM:
                flag = allTrueIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countSenSub(SenioritySubsidiesDTO senioritySubsidiesDTO) throws SerException {
        searchCondition(senioritySubsidiesDTO);
        Long count = super.count(senioritySubsidiesDTO);
        return count;
    }

    @Override
    public SenioritySubsidiesBO getOneById(String id) throws SerException {
        SenioritySubsidies senioritySubsidies = super.findById(id);
        return BeanTransform.copyProperties(senioritySubsidies, SenioritySubsidiesBO.class);
    }

    @Override
    public List<SenioritySubsidiesBO> listSenSub(SenioritySubsidiesDTO senioritySubsidiesDTO) throws SerException {
        checkSeeIdentity();
        searchCondition(senioritySubsidiesDTO);
        List<SenioritySubsidies> senioritySubsidiesList = super.findByPage(senioritySubsidiesDTO);
        return BeanTransform.copyProperties(senioritySubsidiesList, SenioritySubsidiesBO.class);
    }

    public void searchCondition(SenioritySubsidiesDTO senioritySubsidiesDTO) throws SerException {
        if (StringUtils.isNotBlank(senioritySubsidiesDTO.getName())) {
            senioritySubsidiesDTO.getConditions().add(Restrict.eq("name", senioritySubsidiesDTO.getName()));
        }
    }

    @Override
    public void saveSen(SenioritySubsidiesTO senioritySubsidiesTO) throws SerException {
        SenioritySubsidies senioritySubsidies = BeanTransform.copyProperties(senioritySubsidiesTO, SenioritySubsidies.class, true);
        senioritySubsidies.setCreateTime(LocalDateTime.now());
        super.save(senioritySubsidies);
    }

    @Override
    public void editSen(SenioritySubsidiesTO senioritySubsidiesTO) throws SerException {
        SenioritySubsidies senioritySubsidies = super.findById(senioritySubsidiesTO.getId());
        BeanTransform.copyProperties(senioritySubsidiesTO, senioritySubsidies, true);
        senioritySubsidies.setModifyTime(LocalDateTime.now());
        super.update(senioritySubsidies);

    }

    @Override
    public void checkDate() throws SerException {
        List<SenioritySubsidies> list = super.findAll();
        List<SenioritySubsidies> senioritySubsidiesList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (SenioritySubsidies senioritySubsidies : list) {
                LocalDate time = senioritySubsidies.getEntryDate();
                LocalDate date = LocalDate.now();
                int monthDiff = (date.getYear() - time.getYear()) * 12 + date.getMonthValue() - time.getMonthValue();
                if (monthDiff >= 13) {
                    String year = getYear(monthDiff);
                    SenioritySubsidiesStandardDTO dto = new SenioritySubsidiesStandardDTO();
                    dto.getConditions().add(Restrict.eq("yearNum", year));
                    SenioritySubsidiesStandard senioritySubsidiesStandard = senioritySubsidiesStandardSer.findOne(dto);
                    if (senioritySubsidiesStandard != null) {
                        senioritySubsidies.setGainGrant(senioritySubsidiesStandard.getPerMonthGrant());
                        senioritySubsidies.setSubsidiesStatus(SubsidiesStatus.INSUBSIDIES);
                    }
                }
                senioritySubsidies.setModifyTime(LocalDateTime.now());
                senioritySubsidies.setCompanyLength(monthDiff);
                senioritySubsidiesList.add(senioritySubsidies);
            }
            super.update(senioritySubsidiesList);
        }
    }

    public String getYear(Integer month) throws SerException {
        String year = "1年";
        if (month <= 24 && month >= 13) {
            year = "1年";
        } else if (month <= 36 && month >= 25) {
            year = "2年";
        } else if (month <= 48 && month >= 37) {
            year = "3年";
        } else if (month <= 60 && month >= 49) {
            year = "4年";
        } else if (month <= 60 && month >= 49) {
            year = "4年";
        } else if (month <= 72 && month >= 61) {
            year = "5年";
        } else if (month <= 84 && month >= 73) {
            year = "6年";
        } else if (month <= 96 && month >= 85) {
            year = "7年";
        } else if (month <= 108 && month >= 97) {
            year = "8年";
        } else if (month <= 120 && month >= 109) {
            year = "9年";
        } else if (month <= 132 && month >= 121) {
            year = "10年";
        }
        return year;
    }

    @Override
    public byte[] exportExcel() throws SerException {
        checkSeeIdentity();
        List<SenioritySubsidies> list = super.findAll();
        List<SenioritySubsidiesImport> senioritySubsidiesImports = new ArrayList<>();
        list.stream().forEach(str -> {
            SenioritySubsidiesImport excel = BeanTransform.copyProperties(str, SenioritySubsidiesImport.class);
            senioritySubsidiesImports.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(senioritySubsidiesImports, excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<SenioritySubsidiesImport> senioritySubsidiesImports = new ArrayList<>();
        SenioritySubsidiesImport excel = new SenioritySubsidiesImport();
        excel.setArea("广州");
        excel.setName("张三");
        excel.setEmpNo("9658426");
        excel.setDepartment("研发部");
        excel.setJobs("工程师");
        excel.setEntryDate("2017-09-12");
        excel.setStartIssueDate("2017-12-12");
        excel.setCompanyLength(13);
        excel.setGainGrant(100d);
        excel.setSubsidiesStatus("在补助");
        excel.setStaffStatus("在职");
        senioritySubsidiesImports.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(senioritySubsidiesImports, exce);
        return bytes;
    }

    @Override
    public void importExcel(List<SenioritySubsidiesTO> senioritySubsidiesTOS) throws SerException {
        checkSeeIdentity();
        List<SenioritySubsidies> senioritySubsidies = BeanTransform.copyProperties(senioritySubsidiesTOS, SenioritySubsidies.class, true);
        senioritySubsidies.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(senioritySubsidies);
    }

    @Override
    public SenioritySubsidiesBO findAge(String employeeName) throws SerException {
        SenioritySubsidiesDTO dto = new SenioritySubsidiesDTO();
        dto.getConditions().add(Restrict.eq("name",employeeName));
        SenioritySubsidies model = super.findOne(dto);
        SenioritySubsidiesBO senioritySubsidiesBO = BeanTransform.copyProperties(model,SenioritySubsidiesBO.class,false);
        return senioritySubsidiesBO;
    }
}