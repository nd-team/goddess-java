package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.businessproject.api.BusinessContractAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.marketdevelopment.bo.PlanYearBusinessBO;
import com.bjike.goddess.marketdevelopment.bo.PlanYearSubjectBO;
import com.bjike.goddess.marketdevelopment.bo.PlanYearSubjectUpdateBO;
import com.bjike.goddess.marketdevelopment.bo.PlanYearmapsBO;
import com.bjike.goddess.marketdevelopment.dto.*;
import com.bjike.goddess.marketdevelopment.entity.BusinessCourse;
import com.bjike.goddess.marketdevelopment.entity.PlanYear;
import com.bjike.goddess.marketdevelopment.entity.PlanYearBusiness;
import com.bjike.goddess.marketdevelopment.entity.PlanYearSubject;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.enums.Status;
import com.bjike.goddess.marketdevelopment.excel.PlanYearmapsExportExcel;
import com.bjike.goddess.marketdevelopment.excel.PlanYearmapsImportExcel;
import com.bjike.goddess.marketdevelopment.to.*;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 年计划(科目方向)业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-08 03:34 ]
 * @Description: [ 年计划(科目方向)业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class PlanYearSubjectSerImpl extends ServiceImpl<PlanYearSubject, PlanYearSubjectDTO> implements PlanYearSubjectSer {
    @Autowired
    private PlanYearSer planYearSer;
    @Autowired
    private PlanYearBusinessSer planYearBusinessSer;
    @Autowired
    private PlanYearSubjectSer planYearSubjectSer;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private BusinessContractAPI businessContractAPI;
    @Autowired
    private BusinessCourseSer businessCourseSer;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MarPermissionSer marPermissionSer;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    private static final String planManage = "plan-manage";

    private static final String planCheck = "plan-check";


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
    public List<PlanYearmapsBO> maps(PlanYearSubjectDTO dto) throws SerException {
        BeanTransform.copyProperties(dto, PlanYearDTO.class);
        PlanYearDTO planYearDTO = BeanTransform.copyProperties(dto, PlanYearDTO.class);
        planYearDTO.getSorts().add("year=desc");
        planYearDTO.getSorts().add("modifyTime=desc");
        List<PlanYear> planYears = planYearSer.findByPage(planYearDTO);
        List<PlanYearmapsBO> bos = new ArrayList<>(0);
        if (null != planYears && planYears.size() > 0) {
            for (PlanYear planYear : planYears) {
                PlanYearmapsBO bo = BeanTransform.copyProperties(planYear, PlanYearmapsBO.class, false);

                PlanYearBusinessDTO planYearBusinessDTO = new PlanYearBusinessDTO();
                planYearBusinessDTO.getConditions().add(Restrict.eq("yearId", planYear.getId()));
                List<PlanYearBusiness> planYearBusinesses = planYearBusinessSer.findByCis(planYearBusinessDTO);
                List<PlanYearBusinessBO> planYearBusinessBOs = new ArrayList<>(0);
                if (null != planYearBusinesses && planYearBusinesses.size() > 0) {
                    for (PlanYearBusiness planYearBusiness : planYearBusinesses) {
                        PlanYearBusinessBO planYearBusinessBO = BeanTransform.copyProperties(planYearBusiness, PlanYearBusinessBO.class, false);

                        PlanYearSubjectDTO planYearSubjectDTO = new PlanYearSubjectDTO();
                        planYearSubjectDTO.getConditions().add(Restrict.eq("businessDataId", planYearBusiness.getId()));
                        List<PlanYearSubject> planYearSubjects = planYearSubjectSer.findByCis(planYearSubjectDTO);
                        List<PlanYearSubjectBO> planYearSubjectBOs = new ArrayList<>(0);
                        if (null != planYearSubjects && planYearSubjects.size() > 0) {
                            for (PlanYearSubject planYearSubject : planYearSubjects) {
                                PlanYearSubjectBO planYearSubjectBO = BeanTransform.copyProperties(planYearSubject, PlanYearSubjectBO.class, false);
                                planYearSubjectBOs.add(planYearSubjectBO);
                            }
                        }
                        planYearBusinessBO.setPlanYearSubjectVOs(planYearSubjectBOs);
                        planYearBusinessBOs.add(planYearBusinessBO);
                    }
                }
                bo.setPlanYearBusinessVOs(planYearBusinessBOs);
                bos.add(bo);
            }
        }
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void save(PlanYearmapsTO to) throws SerException {
        String planYearId = "";
        PlanYearDTO planYearDTO = new PlanYearDTO();
        planYearDTO.getConditions().add(Restrict.eq("year", to.getYear()));
        PlanYear planYear = planYearSer.findOne(planYearDTO);
        if (null == planYear) {
            planYear = new PlanYear();
            BeanTransform.copyProperties(to, planYear);
            planYear.setYearDiferenceMoney(planYear.getYearTargetMoney() - planYear.getYearActualMoney());
            planYear = planYearSer.save(planYear);
            planYearId = planYear.getId();
        } else {
            BeanUtils.copyProperties(to, planYear, "id", "createTime");
            planYear.setYearDiferenceMoney(planYear.getYearTargetMoney() - planYear.getYearActualMoney());
            planYearSer.update(planYear);
            planYearId = planYear.getId();
        }

        String planYearBusinessId = "";
        List<PlanYearBusinessTO> planYearBusinessTOs = to.getPlanYearBusinessTOs();
        if (null != planYearBusinessTOs && planYearBusinessTOs.size() > 0) {
            for (PlanYearBusinessTO planYearBusinessTO : planYearBusinessTOs) {
                PlanYearBusiness planYearBusiness = BeanTransform.copyProperties(planYearBusinessTO, PlanYearBusiness.class, true);
                PlanYearBusinessDTO planYearBusinessDTO = new PlanYearBusinessDTO();
                planYearBusinessDTO.getConditions().add(Restrict.eq("yearId", planYearId));
                planYearBusinessDTO.getConditions().add(Restrict.eq("businessType", planYearBusiness.getBusinessType()));
                PlanYearBusiness planYearBusiness1 = planYearBusinessSer.findOne(planYearBusinessDTO);
                if (null == planYearBusiness1) {
                    planYearBusiness.setYearId(planYearId);
                    planYearBusiness = planYearBusinessSer.save(planYearBusiness);
                    planYearBusinessId = planYearBusiness.getId();
                } else {
                    BeanUtils.copyProperties(to, planYearBusiness1, "id", "createTime");
                    planYearBusinessSer.update(planYearBusiness1);
                    planYearBusinessId = planYearBusiness1.getId();
                }

                List<PlanYearSubjectTO> planYearSubjectTOs = planYearBusinessTO.getPlanYearSubjectTOs();
                if (null != planYearSubjectTOs && planYearSubjectTOs.size() > 0) {
                    for (PlanYearSubjectTO planYearSubjectTO : planYearSubjectTOs) {
                        PlanYearSubject planYearSubject = BeanTransform.copyProperties(planYearSubjectTO, PlanYearSubject.class, true);
                        PlanYearSubjectDTO planYearSubjectDTO = new PlanYearSubjectDTO();
                        planYearSubjectDTO.getConditions().add(Restrict.eq("businessDataId", planYearBusinessId));
                        planYearSubjectDTO.getConditions().add(Restrict.eq("subject", planYearSubject.getSubject()));
                        PlanYearSubject planYearSubject1 = super.findOne(planYearSubjectDTO);
                        if (null == planYearSubject1) {
                            planYearSubject.setDiffMoney(planYearSubjectTO.getPlanMoney() - planYearSubjectTO.getActualMoney());
                            planYearSubject.setBusinessDataId(planYearBusinessId);
                            super.save(planYearSubject);
                        } else {
                            BeanUtils.copyProperties(planYearSubjectTO, planYearSubject1, "id", "createTime");
                            planYearSubject1.setDiffMoney(planYearSubject1.getPlanMoney() - planYearSubject1.getActualMoney());
                            super.update(planYearSubject1);
                        }
                    }
                }
            }
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void update(PlanYearSubjectUpdateTO to) throws SerException {
        PlanYearSubject entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        BeanUtils.copyProperties(to, entity, "id", "createTime");
        entity.setDiffMoney(entity.getPlanMoney() - entity.getActualMoney());

        PlanYearBusiness planYearBusiness = planYearBusinessSer.findById(to.getBusinessDataId());
        if (null == planYearBusiness) {
            throw new SerException("目标数据对象不能为空");
        }
        BeanUtils.copyProperties(to, planYearBusiness, "id", "createTime");
        planYearBusiness.setPlanMoney(to.getVariousPlanMoney());
        planYearBusiness.setActualMoney(to.getVariousActualMoney());

        PlanYear planYear = planYearSer.findById(to.getYearId());
        if (null == planYear) {
            throw new SerException("目标数据对象不能为空");
        }
        BeanUtils.copyProperties(to, planYear, "id", "createTime");
        planYear.setYearDiferenceMoney(planYear.getYearTargetMoney() - planYear.getYearActualMoney());

        super.update(entity);
        planYearBusinessSer.update(planYearBusiness);
        planYearSer.update(planYear);
    }

    @Override
    public void delete(String id) throws SerException {
        PlanYearBusiness planYearBusiness = planYearBusinessSer.findById(id);
        if (null == planYearBusiness) {
            throw new SerException("目标数据对象不能为空");
        }
        planYearBusinessSer.remove(planYearBusiness);
        PlanYearSubjectDTO planYearSubjectDTO = new PlanYearSubjectDTO();
        List<PlanYearSubject> planYearSubjects = super.findByCis(planYearSubjectDTO);
        if (null != planYearSubjects && planYearSubjects.size() > 0) {
            super.remove(planYearSubjects);
        }
    }

    @Override
    public PlanYearSubjectUpdateBO getById(String id) throws SerException {
        PlanYearSubject entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        PlanYearSubjectUpdateBO bo = new PlanYearSubjectUpdateBO();
        BeanTransform.copyProperties(entity, bo);

        PlanYearBusiness planYearBusiness = planYearBusinessSer.findById(entity.getBusinessDataId());
        BeanTransform.copyProperties(planYearBusiness, bo);
        bo.setVariousActualMoney(planYearBusiness.getActualMoney());
        bo.setVariousPlanMoney(planYearBusiness.getPlanMoney());

        PlanYear planYear = planYearSer.findById(planYearBusiness.getYearId());
        BeanTransform.copyProperties(planYear, bo);
        bo.setId(entity.getId());
        return bo;
    }

    @Override
    public Long getTotal(PlanYearTypeDTO dto) throws SerException {
        PlanYearDTO planYearDTO = BeanTransform.copyProperties(dto, PlanYearDTO.class);
        return planYearSer.count(planYearDTO);
    }

    @Override
    public void congeal(String id) throws SerException {
        PlanYear planYear = planYearSer.findById(id);
        if (null == planYear) {
            throw new SerException("目标数据对象不能为空");
        }
        planYear.setStatus(Status.CONGEAL);
        planYear.setModifyTime(LocalDateTime.now());
        planYearSer.update(planYear);
    }

    @Override
    public void thaw(String id) throws SerException {
        PlanYear planYear = planYearSer.findById(id);
        if (null == planYear) {
            throw new SerException("目标数据对象不能为空");
        }
        planYear.setStatus(Status.THAW);
        planYear.setModifyTime(LocalDateTime.now());
        planYearSer.update(planYear);
    }

    @Override
    public void importExcel(List<PlanYearmapsImportExcel> tos) throws SerException {
        for (PlanYearmapsImportExcel planYearmapsImportExcel : tos) {
            PlanYearmapsTO to = BeanTransform.copyProperties(planYearmapsImportExcel, PlanYearmapsTO.class);
            List<PlanYearBusinessTO> planYearBusinessTOs = new ArrayList<>(0);
            PlanYearBusinessTO planYearBusinessTO = BeanTransform.copyProperties(planYearmapsImportExcel, PlanYearBusinessTO.class);

            List<PlanYearSubjectTO> planYearSubjectTOs = new ArrayList<>(0);
            PlanYearSubjectTO planYearSubjectTO = BeanTransform.copyProperties(planYearmapsImportExcel, PlanYearSubjectTO.class);

            planYearSubjectTOs.add(planYearSubjectTO);
            planYearBusinessTO.setPlanYearSubjectTOs(planYearSubjectTOs);
            planYearBusinessTOs.add(planYearBusinessTO);
            to.setPlanYearBusinessTOs(planYearBusinessTOs);
            save(to);
        }
    }

    @Override
    public byte[] exportTempExcel() throws SerException {
        List<PlanYearmapsExportExcel> planYearmapsExportExcels = new ArrayList<>(0);
        PlanYearmapsExportExcel planYearmapsExportExcel = new PlanYearmapsExportExcel();
        planYearmapsExportExcels.add(planYearmapsExportExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(planYearmapsExportExcels, excel);
        return bytes;
    }

    @Override
    public byte[] export() throws SerException {
//        List<PlanYear> planYears = planYearSer.findAll();

        List<Integer> businessSize = new ArrayList<>(0);
        List<Integer> subjectSize = new ArrayList<>(0);
        List<PlanYearmapsExportExcel> planYearmapsExportExcels = new ArrayList<>(0);
//        if (null != planYears && planYears.size() > 0) {
//            for (PlanYear planYear : planYears) {
//                PlanYearmapsExportExcel planYearmapsExportExcel = BeanTransform.copyProperties(planYear, PlanYearmapsExportExcel.class);
//                PlanYearBusinessDTO planYearBusinessDTO = new PlanYearBusinessDTO();
//                planYearBusinessDTO.getConditions().add(Restrict.eq("yearId", planYear.getId()));
//                List<PlanYearBusiness> planYearBusinesses = planYearBusinessSer.findByCis(planYearBusinessDTO);
//                if (null != planYearBusinesses && planYearBusinesses.size() > 0) {
//                    for (PlanYearBusiness planYearBusiness : planYearBusinesses) {
//                        businessSize.add(planYearBusinesses.size());
//                        BeanTransform.copyProperties(planYearBusiness, planYearmapsExportExcel);
//                        planYearmapsExportExcel.setVariousActualMoney(planYearBusiness.getActualMoney());
//                        planYearmapsExportExcel.setVariousPlanMoney(planYearBusiness.getPlanMoney());
//                        PlanYearSubjectDTO planYearSubjectDTO = new PlanYearSubjectDTO();
//                        planYearSubjectDTO.getConditions().add(Restrict.eq("businessDataId", planYearBusiness.getId()));
//                        List<PlanYearSubject> planYearSubjects = super.findByCis(planYearSubjectDTO);
//                        if (null != planYearSubjects && planYearSubjects.size() > 0) {
//                            for (PlanYearSubject planYearSubject : planYearSubjects) {
//                                subjectSize.add(planYearSubjects.size());
//                                BeanTransform.copyProperties(planYearSubject, planYearmapsExportExcel);
//                                planYearmapsExportExcels.add(planYearmapsExportExcel);
//                            }
//                        }
//                    }
//                }
//            }
//        }
        PlanYearSubjectDTO planYearSubjectDTO2 = new PlanYearSubjectDTO();
        planYearSubjectDTO2.getSorts().add("businessDataId=asc");
        List<PlanYearSubject> planYearSubjects = super.findByCis(planYearSubjectDTO2);
        String busiId = "";
        String yearId = "";
        PlanYearBusinessDTO planYearBusinessDTO3 = new PlanYearBusinessDTO();
        planYearBusinessDTO3.getSorts().add("yearId=asc");
        List<PlanYearBusiness> planYearBusinessList = planYearBusinessSer.findByCis(planYearBusinessDTO3);
        if (null != planYearBusinessList && planYearBusinessList.size() > 0) {
            for (PlanYearBusiness planYearBusiness : planYearBusinessList) {
                if (StringUtils.isBlank(yearId) || !yearId.equals(planYearBusiness.getYearId())) {
                    yearId = planYearBusiness.getYearId();
                    PlanYearBusinessDTO planYearBusinessDTO1 = new PlanYearBusinessDTO();
                    planYearBusinessDTO1.getConditions().add(Restrict.eq("yearId", planYearBusiness.getYearId()));
//                            planYearBusinessDTO1.getSorts().add("id=asc");
                    List<PlanYearBusiness> planYearBusinesses1 = planYearBusinessSer.findByCis(planYearBusinessDTO1);
                    int sum = 0;
                    if (null != planYearBusinesses1 && planYearBusinesses1.size() > 0) {
                        for (PlanYearBusiness planYearBusiness1 : planYearBusinesses1) {
                            PlanYearSubjectDTO planYearSubjectDTO = new PlanYearSubjectDTO();
                            planYearSubjectDTO.getConditions().add(Restrict.eq("businessDataId", planYearBusiness1.getId()));
                            List<PlanYearSubject> planYearSubjects1 = planYearSubjectSer.findByCis(planYearSubjectDTO);
                            sum += planYearSubjects1.size();
                        }
                    }
                    businessSize.add(sum);
                }
            }
        }

        if (null != planYearSubjects && planYearSubjects.size() > 0) {
            for (PlanYearSubject planYearSubject : planYearSubjects) {
                PlanYearmapsExportExcel planYearmapsExportExcel = BeanTransform.copyProperties(planYearSubject, PlanYearmapsExportExcel.class);
                PlanYearBusinessDTO planYearBusinessDTO = new PlanYearBusinessDTO();
                planYearBusinessDTO.getConditions().add(Restrict.eq("id", planYearSubject.getBusinessDataId()));
                planYearBusinessDTO.getSorts().add("yearId=asc");
                List<PlanYearBusiness> planYearBusinesses = planYearBusinessSer.findByCis(planYearBusinessDTO);

                if (StringUtils.isBlank(busiId) || !busiId.equals(planYearSubject.getBusinessDataId())) {
                    busiId = planYearSubject.getBusinessDataId();
                    PlanYearSubjectDTO planYearSubjectDTO = new PlanYearSubjectDTO();
                    planYearSubjectDTO.getConditions().add(Restrict.eq("businessDataId", busiId));
                    List<PlanYearSubject> planYearSubjects1 = super.findByCis(planYearSubjectDTO);
                    subjectSize.add(planYearSubjects1.size());
                }

                if (null != planYearBusinesses && planYearBusinesses.size() > 0) {
                    for (PlanYearBusiness planYearBusiness : planYearBusinesses) {
                        BeanTransform.copyProperties(planYearBusiness, planYearmapsExportExcel);
                        planYearmapsExportExcel.setVariousPlanMoney(planYearBusiness.getPlanMoney());
                        planYearmapsExportExcel.setVariousActualMoney(planYearBusiness.getActualMoney());

//                        if (StringUtils.isBlank(yearId) || !yearId.equals(planYearBusiness.getYearId())) {
//                            yearId = planYearBusiness.getYearId();
//                            PlanYearBusinessDTO planYearBusinessDTO1 = new PlanYearBusinessDTO();
//                            planYearBusinessDTO1.getConditions().add(Restrict.eq("yearId", planYearBusiness.getYearId()));
////                            planYearBusinessDTO1.getSorts().add("id=asc");
//                            List<PlanYearBusiness> planYearBusinesses1 = planYearBusinessSer.findByCis(planYearBusinessDTO1);
//                            int sum = 0;
//                            if (null != planYearBusinesses1 && planYearBusinesses1.size() > 0) {
//                                for (PlanYearBusiness planYearBusiness1 : planYearBusinesses1) {
//                                    PlanYearSubjectDTO planYearSubjectDTO = new PlanYearSubjectDTO();
//                                    planYearSubjectDTO.getConditions().add(Restrict.eq("businessDataId", planYearBusiness1.getId()));
//                                    List<PlanYearSubject> planYearSubjects1 = planYearSubjectSer.findByCis(planYearSubjectDTO);
//                                    sum += planYearSubjects1.size();
//                                }
//                            }
//                            businessSize.add(sum);
//                        }

                        PlanYearDTO planYearDTO = new PlanYearDTO();
                        planYearDTO.getConditions().add(Restrict.eq("id", planYearBusiness.getYearId()));
                        List<PlanYear> planYears = planYearSer.findByCis(planYearDTO);
                        if (null != planYears && planYears.size() > 0) {
//                            rowSize = planYears.size();
                            for (PlanYear planYear : planYears) {
                                BeanTransform.copyProperties(planYear, planYearmapsExportExcel);
                                planYearmapsExportExcels.add(planYearmapsExportExcel);
                            }
                        }
                    }
                }
            }
        }
        List<PlanYear> planYear1 = planYearSer.findAll();
        if (null == planYear1 || planYear1.size() < 1) {
            return null;
        }

        //导出
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(planYearmapsExportExcels, excel);
        XSSFWorkbook wb = null;
        ByteArrayOutputStream os = null;
        try {
            InputStream is = new ByteArrayInputStream(bytes);
            wb = new XSSFWorkbook(is);
            XSSFSheet sheet;
            sheet = wb.getSheetAt(0);
            //主表行数
            int rowSize = planYear1.size();
            List<Field> fields = ClazzUtils.getFields(PlanYearmapsExportExcel.class); //获得列表对象属性
            List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);

            int index = 0;
            int lastRow = 0;
            int minIndex = 0;
            int minLastRow = 0;
            for (int j = 0; j < rowSize; j++) {

                int x = 0;
                //List<int> maxList所有子表的长度
                if (null != businessSize && businessSize.size() > 0) {
                    int mergeRowCount = businessSize.get(j);
                    //5
                    if (mergeRowCount != 1) {

                        int firstRow = index;
                        lastRow = firstRow + mergeRowCount - 1;
                        if (firstRow == 0) {
                            firstRow += 1;
                            lastRow += 1;
                        }
                        //合并主表共33列
                        for (int i = 0; i < 6; i++) {
                            //1,5
                            sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, i, i));
                        }
                        index = ++lastRow;

                        os = new ByteArrayOutputStream();
                        wb.write(os);
                    }else {
                        minIndex += 1;
                    }
                }
            }
            for (int j = 0; j < subjectSize.size(); j++) {
                if (null != subjectSize && subjectSize.size() > 0) {
                    int mergeRowCount = subjectSize.get(j);
                    //5
                    if (mergeRowCount != 1) {

                        int firstRow = minIndex;
                        minLastRow = firstRow + mergeRowCount - 1;
                        if (firstRow == 0) {
                            firstRow += 1;
                            minLastRow += 1;
                        }
                        //合并主表共33列
                        for (int i = 6; i < 10; i++) {
                            //1,5
                            sheet.addMergedRegion(new CellRangeAddress(firstRow, minLastRow, i, i));
                        }
                        minIndex = ++minLastRow;

                        os = new ByteArrayOutputStream();
                        wb.write(os);
                    }else {
                        minIndex += 1;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return os.toByteArray();
    }

    @Override
    public Double findMoney(String year) throws SerException {
//        if(moduleAPI.isCheck("businessproject")){
        Double money = businessContractAPI.findMoney(year);
        return money;
//        }

    }

    @Override
    public Double moneyByType(String businessType, String year) throws SerException {
//        if(moduleAPI.isCheck("businessproject")){
        Double money = businessContractAPI.moneyByType(businessType, year);
        return money;
//        }
    }

    @Override
    public Double countYearProportion(Double workWeight, Double proportion) throws SerException {
        Double sum = 0d;
        if (null != workWeight && null != proportion) {
            sum = workWeight * proportion / 100;
        }
        return sum;
    }

    @Override
    public Integer findDeveBusiness(String course) throws SerException {
        BusinessCourseDTO businessCourseDTO = new BusinessCourseDTO();
        businessCourseDTO.getConditions().add(Restrict.eq("course", course));
        List<BusinessCourse> businessCourses = businessCourseSer.findByCis(businessCourseDTO);
        int sum = businessCourses.size();
        return sum;
    }
}