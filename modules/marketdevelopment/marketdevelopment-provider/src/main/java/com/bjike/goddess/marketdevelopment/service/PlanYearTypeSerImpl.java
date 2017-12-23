package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.marketdevelopment.bo.PlanYearBO;
import com.bjike.goddess.marketdevelopment.bo.PlanYearType1BO;
import com.bjike.goddess.marketdevelopment.bo.PlanYearUpdateBO;
import com.bjike.goddess.marketdevelopment.dto.PlanYearDTO;
import com.bjike.goddess.marketdevelopment.dto.PlanYearTypeDTO;
import com.bjike.goddess.marketdevelopment.entity.PlanYear;
import com.bjike.goddess.marketdevelopment.entity.PlanYearType;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.enums.MoneyType;
import com.bjike.goddess.marketdevelopment.enums.MonthType;
import com.bjike.goddess.marketdevelopment.enums.Status;
import com.bjike.goddess.marketdevelopment.excel.PlanYearExportExcel;
import com.bjike.goddess.marketdevelopment.excel.PlanYearImportExcel;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.PlanYearTO;
import com.bjike.goddess.marketdevelopment.to.PlanYearTypeTO;
import com.bjike.goddess.marketdevelopment.to.PlanYearUpdateTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.util.stream.Collectors;

/**
 * 年计划业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 05:32 ]
 * @Description: [ 年计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class PlanYearTypeSerImpl extends ServiceImpl<PlanYearType, PlanYearTypeDTO> implements PlanYearTypeSer {
    @Autowired
    private PlanYearSer planYearSer;

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
    public List<PlanYearBO> maps(PlanYearTypeDTO dto) throws SerException {
        List<PlanYearBO> bos = new ArrayList<>(0);
        PlanYearDTO planYearDTO = BeanTransform.copyProperties(dto, PlanYearDTO.class, "serialVersionUID");
        planYearDTO.getSorts().add("year=desc");
        planYearDTO.getSorts().add("modifyTime=desc");
        List<PlanYear> planYears = planYearSer.findByPage(planYearDTO);
        if (null != planYears && planYears.size() > 0) {
            for (PlanYear planYear : planYears) {
                PlanYearBO bo = BeanTransform.copyProperties(planYear, PlanYearBO.class, false);
                List<PlanYearType1BO> planYearType1BOs = new ArrayList<>(0);

                for (MoneyType moneyType : MoneyType.values()) {
                    PlanYearTypeDTO planYearTypeDTO = new PlanYearTypeDTO();
                    planYearTypeDTO.getConditions().add(Restrict.eq("yearId", planYear.getId()));
                    planYearTypeDTO.getConditions().add(Restrict.eq("moneyType", moneyType));
                    List<PlanYearType> planYearTypes = super.findByCis(planYearTypeDTO);
                    if (null != planYearTypes && planYearTypes.size() > 0) {
                        PlanYearType1BO planYearType1BO = new PlanYearType1BO();
                        PlanYearUpdateBO bo1 = findBO(planYearTypes);
                        BeanTransform.copyProperties(bo1, planYearType1BO);
                        planYearType1BO.setMoneyType(planYearTypes.get(0).getMoneyType());
                        planYearType1BO.setTotal(planYearTypes.get(0).getTotal());
                        planYearType1BOs.add(planYearType1BO);
                    }
                }
                bo.setPlanYearTypeVOs(planYearType1BOs);
                bos.add(bo);
            }
        }
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void save(PlanYearTO to) throws SerException {
        String yearId = "";
        PlanYear planYear = BeanTransform.copyProperties(to, PlanYear.class, true);
        planYear.setYearDiferenceMoney(to.getYearTargetMoney() - to.getYearActualMoney());
        PlanYearDTO planYearDTO = new PlanYearDTO();
        planYearDTO.getConditions().add(Restrict.eq("year", to.getYear()));
        PlanYear planYear1 = planYearSer.findOne(planYearDTO);
        if (null == planYear1) {
            planYear = planYearSer.save(planYear);
            yearId = planYear.getId();
        } else {
            BeanTransform.copyProperties(to, planYear1, "id", "create");
            planYearSer.update(planYear1);
            yearId = planYear1.getId();
        }

        List<PlanYearTypeTO> planYearTypeTOs = to.getPlanYearTypeTOs();
        if (null != planYearTypeTOs && planYearTypeTOs.size() > 0) {
            if (planYearTypeTOs.size() > 3) {
                throw new SerException("类型有重复的数据,请重新输入");
            }
            for (PlanYearTypeTO planYearTypeTO : planYearTypeTOs) {
                //是否已添加
                PlanYearTypeDTO planYearTypeDTO = new PlanYearTypeDTO();
                planYearTypeDTO.getConditions().add(Restrict.eq("yearId", yearId));
                planYearTypeDTO.getConditions().add(Restrict.eq("moneyType", planYearTypeTO.getMoneyType()));
                List<PlanYearType> planYearTypes = super.findByCis(planYearTypeDTO);
                if (null != planYearTypes && planYearTypes.size() > 0) {
                    throw new SerException("该年份的类型数据已存在,不可再添加");
                }
            }
            int flag = 0;
            for (PlanYearTypeTO planYearTypeTO : planYearTypeTOs) {
                planYear.setId(yearId);
                createEntity(planYearTypeTO, planYear);
                if (MoneyType.AIM.equals(planYearTypeTO.getMoneyType())) {
                    flag++;
                }
                if (MoneyType.ATCUAL.equals(planYearTypeTO.getMoneyType())) {
                    flag++;
                }
            }
//            if (flag == 2) {
            PlanYearTypeDTO planYearTypeDTO = new PlanYearTypeDTO();
            planYearTypeDTO.getConditions().add(Restrict.eq("yearId", planYear.getId()));
            planYearTypeDTO.getConditions().add(Restrict.eq("moneyType", MoneyType.AIM));
            List<PlanYearType> planYearTypes = super.findByCis(planYearTypeDTO);
            PlanYearTypeDTO planYearTypeDTO1 = new PlanYearTypeDTO();
            planYearTypeDTO1.getConditions().add(Restrict.eq("yearId", planYear.getId()));
            planYearTypeDTO1.getConditions().add(Restrict.eq("moneyType", MoneyType.ATCUAL));
            List<PlanYearType> planYearTypes1 = super.findByCis(planYearTypeDTO1);

            List<Double> moneys = new ArrayList<>(0);
            if (null != planYearTypes && planYearTypes.size() > 0 && null != planYearTypes1 && planYearTypes1.size() > 0) {
                //计算每月的差异金额
                for (MonthType monthType : MonthType.values()) {
                    Double money = planYearTypes.stream().filter(obj -> monthType.equals(obj.getMonthType())).map(PlanYearType::getMoney).collect(Collectors.toList()).get(0) - planYearTypes1.stream().filter(obj -> monthType.equals(obj.getMonthType())).map(PlanYearType::getMoney).collect(Collectors.toList()).get(0);
                    moneys.add(money);
                }
                for (MonthType type : MonthType.values()) {
                    int i = 0;
                    PlanYearType planYearType = new PlanYearType();
                    planYearType.setYearId(planYear.getId());
                    planYearType.setMoneyType(MoneyType.DIFFERENCE);
                    planYearType.setMonthType(type);
                    planYearType.setMoney(moneys.get(i++));
                    planYearType.setTotal(String.valueOf(moneys.stream().mapToDouble(Double::doubleValue).sum()));
                    super.save(planYearType);
                }
            }
//            }
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void update(PlanYearUpdateTO to) throws SerException {
        PlanYear planYear = planYearSer.findById(to.getYearId());
        if (null == planYear) {
            throw new SerException("目标数据对象不能为空");
        }
        if (MoneyType.DIFFERENCE.equals(to.getMoneyType())) {
            throw new SerException("类型为差异金额的不可修改");
        }
        PlanYearType entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        //查询月份集合
        PlanYearTypeDTO planYearTypeDTO1 = new PlanYearTypeDTO();
        planYearTypeDTO1.getConditions().add(Restrict.eq("yearId", to.getYearId()));
        planYearTypeDTO1.getConditions().add(Restrict.eq("moneyType", entity.getMoneyType()));
        List<PlanYearType> planYearTypes1 = super.findByCis(planYearTypeDTO1);
        for (PlanYearType planYearType : planYearTypes1) {
            int i = 0;
            updateEntity(to, planYearType, i++);
        }

        BeanTransform.copyProperties(to, planYear, "id", "createTime");
        planYear.setModifyTime(LocalDateTime.now());
        planYear.setYearDiferenceMoney(planYear.getYearTargetMoney() - planYear.getYearActualMoney());
        planYearSer.update(planYear);

        //修改差异
        PlanYearTypeDTO planYearTypeDTO = new PlanYearTypeDTO();
        planYearTypeDTO.getConditions().add(Restrict.eq("yearId", to.getYearId()));
        planYearTypeDTO.getConditions().add(Restrict.eq("moneyType", MoneyType.DIFFERENCE));
        List<PlanYearType> planYearTypes = super.findByCis(planYearTypeDTO);
        if (null != planYearTypes && planYearTypes.size() > 0) {
            super.remove(planYearTypes);
        }
        editEntity(planYear);
    }

    private void updateEntity(PlanYearUpdateTO to, PlanYearType entity, int i) throws SerException {
        List<Double> moneys = new ArrayList<>(0);
        moneys.add(to.getJan());
        moneys.add(to.getFeb());
        moneys.add(to.getMar());
        moneys.add(to.getApr());
        moneys.add(to.getMay());
        moneys.add(to.getJun());
        moneys.add(to.getJul());
        moneys.add(to.getAug());
        moneys.add(to.getSep());
        moneys.add(to.getOct());
        moneys.add(to.getNov());
        moneys.add(to.getDec());

        for (MonthType monthType : MonthType.values()) {
            if (monthType.equals(entity.getMonthType())) {
                entity.setMoneyType(to.getMoneyType());
                entity.setMoney(moneys.get(i));
                entity.setTotal(String.valueOf(moneys.stream().mapToDouble(Double::doubleValue).sum()));
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
            }
        }
    }

    @Override
    public void delete(String id) throws SerException {
        PlanYearType planYearType = super.findById(id);
        if (null == planYearType) {
            throw new SerException("目标数据不能为空");
        }

        PlanYearTypeDTO planYearTypeDTO1 = new PlanYearTypeDTO();
        planYearTypeDTO1.getConditions().add(Restrict.eq("yearId", planYearType.getYearId()));
        planYearTypeDTO1.getConditions().add(Restrict.eq("moneyType", planYearType.getMoneyType()));
        List<PlanYearType> planYearTypes1 = super.findByCis(planYearTypeDTO1);
        if (null != planYearTypes1 && planYearTypes1.size() > 0) {
            super.remove(planYearTypes1);
        }

        //修改差异
        if (planYearType.getMoneyType() == MoneyType.AIM || planYearType.getMoneyType() == MoneyType.ATCUAL) {
            PlanYearTypeDTO planYearTypeDTO = new PlanYearTypeDTO();
            planYearTypeDTO.getConditions().add(Restrict.eq("yearId", planYearType.getYearId()));
            planYearTypeDTO.getConditions().add(Restrict.eq("moneyType", MoneyType.DIFFERENCE));
            List<PlanYearType> planYearTypes = super.findByCis(planYearTypeDTO);
            if (null != planYearTypes && planYearTypes.size() > 0) {
                super.remove(planYearTypes);
            }
        }
    }

    @Override
    public PlanYearUpdateBO getById(String id) throws SerException {
        PlanYearType planYearType = super.findById(id);
        if (null == planYearType) {
            throw new SerException("目标数据对象不能为空");
        }
        PlanYear planYear = planYearSer.findById(planYearType.getId());

        PlanYearTypeDTO planYearTypeDTO = new PlanYearTypeDTO();
        planYearTypeDTO.getConditions().add(Restrict.eq("moneyType", planYearType.getMoneyType()));
        planYearTypeDTO.getConditions().add(Restrict.eq("yearId", planYearType.getYearId()));

        List<PlanYearType> planYearTypes = super.findByCis(planYearTypeDTO);
        if (null != planYearTypes && planYearTypes.size() > 0) {
            PlanYearUpdateBO bo = findBO(planYearTypes);
            BeanTransform.copyProperties(planYearType, bo);
            BeanTransform.copyProperties(planYear, bo);
            return bo;
        }
        return null;
    }

    @Override
    public Long getTotal(PlanYearTypeDTO dto) throws SerException {
        PlanYearDTO planYearDTO = new PlanYearDTO();
        BeanTransform.copyProperties(dto, planYearDTO);
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
    public void importExcel(List<PlanYearImportExcel> tos) throws SerException {
        List<String> years = tos.stream().map(PlanYearImportExcel::getYear).distinct().collect(Collectors.toList());
        if (null != years && years.size() > 0) {
            for (int i = 0; i < years.size(); i++) {
                String year = years.get(i);
                List<PlanYearImportExcel> planYearImportExcels = tos.stream().filter(obj -> obj.getYear().equals(year)).collect(Collectors.toList());
                PlanYearTO to = BeanTransform.copyProperties(planYearImportExcels.get(0), PlanYearTO.class);
                List<PlanYearTypeTO> planYearTypeTOs = new ArrayList<>(0);
                for (PlanYearImportExcel planYearImportExcel : tos) {
                    PlanYearTypeTO planYearTypeTO = BeanTransform.copyProperties(planYearImportExcel, PlanYearTypeTO.class);
                    if (years.get(i).equals(planYearImportExcel.getYear())) {
                        planYearTypeTOs.add(planYearTypeTO);
                    }
                }
                to.setPlanYearTypeTOs(planYearTypeTOs);
                this.save(to);
            }
        }
    }

    @Override
    public byte[] exportTempExcel() throws SerException {
        List<PlanYearImportExcel> planYearImportExcels = new ArrayList<>(0);
        PlanYearImportExcel planYearImportExcel = new PlanYearImportExcel();
        planYearImportExcels.add(planYearImportExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(planYearImportExcels, excel);
        return bytes;
    }

    @Override
    public byte[] export() throws SerException {
        List<PlanYearExportExcel> planYearExportExcels = new ArrayList<>(0);
        List<Integer> sizeLength = new ArrayList<>(0);
        List<PlanYear> planYears = planYearSer.findAll();
        if (null != planYears && planYears.size() > 0) {
            for (PlanYear planYear : planYears) {
                int sum = 0;
                for (MoneyType moneyType : MoneyType.values()) {
                    PlanYearTypeDTO planYearTypeDTO = new PlanYearTypeDTO();
                    planYearTypeDTO.getConditions().add(Restrict.eq("yearId", planYear.getId()));
                    planYearTypeDTO.getConditions().add(Restrict.eq("moneyType", moneyType));
                    List<PlanYearType> planYearTypes = super.findByCis(planYearTypeDTO);
                    if (null != planYearTypes && planYearTypes.size() > 0) {
                        sum++;
                        PlanYearUpdateBO planYearUpdateBO = findBO(planYearTypes);
//                    for (PlanYearType planYearType : planYearTypes) {
                        PlanYearExportExcel planYearExportExcel = new PlanYearExportExcel();
                        BeanTransform.copyProperties(planYearUpdateBO, planYearExportExcel);
                        BeanTransform.copyProperties(planYear, planYearExportExcel);
                        planYearExportExcel.setMoneyType(moneyType);
                        planYearExportExcel.setTotal(planYearTypes.get(0).getTotal());
                        planYearExportExcels.add(planYearExportExcel);
                    }
                }
                sizeLength.add(sum);
            }
        }

        //导出
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(planYearExportExcels, excel);
        XSSFWorkbook wb = null;
        ByteArrayOutputStream os = null;
        try {
            InputStream is = new ByteArrayInputStream(bytes);
            wb = new XSSFWorkbook(is);
            XSSFSheet sheet;
            sheet = wb.getSheetAt(0);
            //主表行数
            int rowSize = planYears.size();
            List<Field> fields = ClazzUtils.getFields(PlanYearExportExcel.class); //获得列表对象属性
            List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);

            int index = 0;
            int lastRow = 0;
            for (int j = 0; j < rowSize; j++) {

                int x = 0;
                //List<int> maxList所有子表的长度
                if (null != sizeLength && sizeLength.size() > 0) {
                    PlanYearExportExcel mainPwd = planYearExportExcels.get(j);
                    int mergeRowCount = sizeLength.get(j);
                    //5
                    if (mergeRowCount > 1) {

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
                    } else {
                        index++;
                    }
                }
            }

            os = new ByteArrayOutputStream();
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return os.toByteArray();
    }


    private PlanYearUpdateBO findBO(List<PlanYearType> planYearTypes) throws SerException {
        PlanYearUpdateBO bo = new PlanYearUpdateBO();
        for (PlanYearType planYearType : planYearTypes) {
            if (MonthType.JAN.equals(planYearType.getMonthType())) {
                bo.setJan(planYearType.getMoney());
            }
            if (MonthType.FEB.equals(planYearType.getMonthType())) {
                bo.setFeb(planYearType.getMoney());
            }
            if (MonthType.MAR.equals(planYearType.getMonthType())) {
                bo.setMar(planYearType.getMoney());
            }
            if (MonthType.APR.equals(planYearType.getMonthType())) {
                bo.setApr(planYearType.getMoney());
            }
            if (MonthType.MAY.equals(planYearType.getMonthType())) {
                bo.setMay(planYearType.getMoney());
            }
            if (MonthType.JUN.equals(planYearType.getMonthType())) {
                bo.setJun(planYearType.getMoney());
            }
            if (MonthType.JUL.equals(planYearType.getMonthType())) {
                bo.setJul(planYearType.getMoney());
            }
            if (MonthType.AUG.equals(planYearType.getMonthType())) {
                bo.setAug(planYearType.getMoney());
            }
            if (MonthType.SEP.equals(planYearType.getMonthType())) {
                bo.setSep(planYearType.getMoney());
            }
            if (MonthType.OCT.equals(planYearType.getMonthType())) {
                bo.setOct(planYearType.getMoney());
            }
            if (MonthType.NOV.equals(planYearType.getMonthType())) {
                bo.setNov(planYearType.getMoney());
            }
            if (MonthType.DEC.equals(planYearType.getMonthType())) {
                bo.setDec(planYearType.getMoney());
                bo.setId(planYearType.getId());
            }
        }
        return bo;
    }

    private void createEntity(PlanYearTypeTO to, PlanYear planYear) throws SerException {
        List<Double> moneys = new ArrayList<>(0);
        moneys.add(to.getJan());
        moneys.add(to.getFeb());
        moneys.add(to.getMar());
        moneys.add(to.getApr());
        moneys.add(to.getMay());
        moneys.add(to.getJun());
        moneys.add(to.getJul());
        moneys.add(to.getAug());
        moneys.add(to.getSep());
        moneys.add(to.getOct());
        moneys.add(to.getNov());
        moneys.add(to.getDec());

        for (MonthType type : MonthType.values()) {
            int i = 0;
            PlanYearType planYearType = new PlanYearType();
            planYearType.setYearId(planYear.getId());
            planYearType.setMoneyType(to.getMoneyType());
            planYearType.setMonthType(type);
            planYearType.setMoney(moneys.get(i++));
            planYearType.setTotal(String.valueOf(moneys.stream().mapToDouble(Double::doubleValue).sum()));
            super.save(planYearType);
        }
    }

    /**
     * 修改差异
     */
    private void editEntity(PlanYear planYear) throws SerException {
        PlanYearTypeDTO planYearTypeDTO = new PlanYearTypeDTO();
        planYearTypeDTO.getConditions().add(Restrict.eq("yearId", planYear.getId()));
        planYearTypeDTO.getConditions().add(Restrict.eq("moneyType", MoneyType.AIM));
        List<PlanYearType> planYearTypes = super.findByCis(planYearTypeDTO);
        PlanYearTypeDTO planYearTypeDTO1 = new PlanYearTypeDTO();
        planYearTypeDTO1.getConditions().add(Restrict.eq("yearId", planYear.getId()));
        planYearTypeDTO1.getConditions().add(Restrict.eq("moneyType", MoneyType.ATCUAL));
        List<PlanYearType> planYearTypes1 = super.findByCis(planYearTypeDTO1);

        List<Double> moneys = new ArrayList<>(0);
        if (null != planYearTypes && planYearTypes.size() > 0 && null != planYearTypes1 && planYearTypes1.size() > 0) {
            //计算每月的差异金额
            for (MonthType monthType : MonthType.values()) {
                Double money = planYearTypes.stream().filter(obj -> monthType.equals(obj.getMonthType())).map(PlanYearType::getMoney).collect(Collectors.toList()).get(0) - planYearTypes1.stream().filter(obj -> monthType.equals(obj.getMonthType())).map(PlanYearType::getMoney).collect(Collectors.toList()).get(0);
                moneys.add(money);
            }
            for (MonthType type : MonthType.values()) {
                int i = 0;
                PlanYearType planYearType = new PlanYearType();
                planYearType.setYearId(planYear.getId());
                planYearType.setMoneyType(MoneyType.DIFFERENCE);
                planYearType.setMonthType(type);
                planYearType.setMoney(moneys.get(i++));
                planYearType.setTotal(String.valueOf(moneys.stream().mapToDouble(Double::doubleValue).sum()));
                super.save(planYearType);
            }
        }
    }
}