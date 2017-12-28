package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.marketdevelopment.bo.PlanDayBO;
import com.bjike.goddess.marketdevelopment.dto.BusinessCourseDTO;
import com.bjike.goddess.marketdevelopment.dto.PlanDayDTO;
import com.bjike.goddess.marketdevelopment.entity.BusinessCourse;
import com.bjike.goddess.marketdevelopment.entity.PlanDay;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.enums.Status;
import com.bjike.goddess.marketdevelopment.excel.PlanDayExportExcel;
import com.bjike.goddess.marketdevelopment.excel.PlanDayImportExcel;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.PlanDayTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bjike.goddess.marketdevelopment.bo.MarkProblemAcceBO;

/**
 * 日计划业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-29 03:55 ]
 * @Description: [ 日计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class PlanDaySerImpl extends ServiceImpl<PlanDay, PlanDayDTO> implements PlanDaySer {

    @Autowired
    private BusinessCourseSer businessCourseSer;
    @Autowired
    private ModuleAPI moduleAPI;
//    @Autowired
//    private ProblemAcceptAPI problemAcceptAPI;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MarPermissionSer marPermissionSer;
//    @Autowired
//    private BusinessContractAPI businessContractAPI;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    private static final String planManage = "plan-manage";

    private static final String planCheck = "plan-check";


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = marPermissionSer.getMarPermission(marketCheck);
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = marPermissionSer.getMarPermission(marketManage);
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
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
            flag = marPermissionSer.getMarPermission(marketCheck);
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
            flag = marPermissionSer.getMarPermission(marketManage);
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
    public List<PlanDayBO> maps(PlanDayDTO dto) throws SerException {
        dto.getSorts().add("serialNumber=asc");
        List<PlanDay> planDays = super.findByPage(dto);
        List<PlanDayBO> planDayBOs = BeanTransform.copyProperties(planDays, PlanDayBO.class, false);
        return planDayBOs;
    }

    @Override
    public void save(PlanDayTO to) throws SerException {
        List<PlanDay> planDays = super.findAll();
        int num = planDays.size();
        PlanDay entity = BeanTransform.copyProperties(to, PlanDay.class, true);
        entity.setSerialNumber(String.valueOf(num));
        super.save(entity);
    }

    @Override
    public void update(PlanDayTO to) throws SerException {
        PlanDay entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        BeanTransform.copyProperties(to, entity);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void congeal(PlanDayTO to) throws SerException {
        PlanDay entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        entity.setStatus(Status.CONGEAL);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void thaw(PlanDayTO to) throws SerException {
        PlanDay entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        entity.setStatus(Status.THAW);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void delete(PlanDayTO to) throws SerException {
        PlanDay entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        super.remove(entity);
    }

    @Override
    public PlanDayBO getById(String id) throws SerException {
        PlanDay entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        PlanDayBO bo = BeanTransform.copyProperties(entity, PlanDayBO.class, false);
        return bo;
    }

    @Override
    public Long getTotal(PlanDayDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public byte[] templateExcel() throws SerException {
        PlanDayImportExcel planDayImportExcel = new PlanDayImportExcel();
        List<PlanDayImportExcel> planDayImportExcels = new ArrayList<>(0);
        planDayImportExcels.add(planDayImportExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(planDayImportExcels, excel);
        return bytes;
    }

    @Override
    public byte[] exportExcel(PlanDayDTO dto) throws SerException {
        dto.getSorts().add("serialNumber=asc");
        List<PlanDay> planDays = super.findByCis(dto);
        List<PlanDayExportExcel> planDayExportExcels = BeanTransform.copyProperties(planDays, PlanDayExportExcel.class);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(planDayExportExcels, excel);
        return bytes;
    }

    @Override
    public void upload(List<PlanDayImportExcel> tos) throws SerException {
        for (PlanDayImportExcel excel : tos) {
            PlanDayTO to = BeanTransform.copyProperties(excel, PlanDayTO.class, false);
            save(to);
        }
    }

    @Override
    public List<String> findBusinessType() throws SerException {
        List<String> list = new ArrayList<>(0);
        BusinessCourseDTO businessCourseDTO = new BusinessCourseDTO();
        businessCourseDTO.getSorts().add("businessNum=asc");
        List<BusinessCourse> businessCourses = businessCourseSer.findByCis(businessCourseDTO);
        if (null != businessCourses && businessCourses.size() > 0) {
            list = businessCourses.stream().map(BusinessCourse::getBusinessType).distinct().collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public List<String> findBusinessSub() throws SerException {
        List<String> list = new ArrayList<>(0);
        BusinessCourseDTO businessCourseDTO = new BusinessCourseDTO();
        businessCourseDTO.getSorts().add("businessNum=asc");
        businessCourseDTO.getSorts().add("subjectNum=asc");
        List<BusinessCourse> businessCourses = businessCourseSer.findByCis(businessCourseDTO);
        if (null != businessCourses && businessCourses.size() > 0) {
            list = businessCourses.stream().map(BusinessCourse::getCourse).distinct().collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public List<String> findInterCode() throws SerException {
        List<String> list = new ArrayList<>(0);
//        if (moduleAPI.isCheck("projectissuehandle")) {
//            list = problemAcceptAPI.getProjectNum();
//        }
        return list;
    }

//    @Override
//    public MarkProblemAcceBO findProblemAcce(String interCode) throws SerException {
//        if (moduleAPI.isCheck("projectissuehandle")) {
//            ProblemAcceBO problemAcceBO = problemAcceptAPI.findProblemAcce(interCode);
//            if (null != problemAcceBO) {
//                MarkProblemAcceBO bo = new MarkProblemAcceBO();
//                BeanTransform.copyProperties(problemAcceBO, bo, "problemTypes");
//                bo.setProblemTypes(ProblemTypes.exportStrConvert(problemAcceBO.getProblemTypes()));
//                return bo;
//            }
//        }
//        return null;
//    }

//    @Override
//    public List<String> findMarkCode() throws SerException {
//        List<String> list = businessContractAPI.findMarkNum();
//        return list;
//    }

//    @Override
//    public String findInnerProject(String marketNum) throws SerException {
//        return businessContractAPI.findProjectName(marketNum);
//    }
}