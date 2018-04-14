package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.api.CompanyBasicInfoAPI;
import com.bjike.goddess.organize.api.ModuleTypeAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.reportmanagement.bo.*;
import com.bjike.goddess.reportmanagement.dto.*;
import com.bjike.goddess.reportmanagement.entity.Profit;
import com.bjike.goddess.reportmanagement.entity.ProfitData;
import com.bjike.goddess.reportmanagement.entity.ProfitFormula;
import com.bjike.goddess.reportmanagement.enums.Form;
import com.bjike.goddess.reportmanagement.enums.GuideAddrStatus;
import com.bjike.goddess.reportmanagement.enums.ProfitType;
import com.bjike.goddess.reportmanagement.enums.Type;
import com.bjike.goddess.reportmanagement.excel.ProfitExportExcel;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.to.ProfitFormulaTO;
import com.bjike.goddess.reportmanagement.to.ProfitTO;
import com.bjike.goddess.reportmanagement.utils.Utils;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.SubjectCollectBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.bjike.goddess.reportmanagement.utils.Static.num;

/**
 * 利润表业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-22 06:03 ]
 * @Description: [ 利润表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class ProfitSerImpl extends ServiceImpl<Profit, ProfitDTO> implements ProfitSer {
    @Autowired
    private FormulaSer formulaSer;
    @Autowired
    private AssetSer assetSer;
    @Autowired
    private DebtSer debtSer;
    @Autowired
    private ProfitIndicatorAdviceSer profitIndicatorAdviceSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private ModuleTypeAPI moduleTypeAPI;
    @Autowired
    private ProfitFormulaSer profitFormulaSer;
    //    @Autowired
//    private SubjectCollectAPI subjectCollectAPI;
    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;

    @Autowired
    private CompanyBasicInfoAPI companyBasicInfoAPI;

    @Autowired
    private ProfitDataSer profitDataSer;


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
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
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
    public ProfitBO save(ProfitTO to) throws SerException {
        checkAddIdentity();
        if (!isAccountingModule()) {
            throw new SerException("当前用户不是账务模块");
        }
        Profit entity = BeanTransform.copyProperties(to, Profit.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, ProfitBO.class);
    }

    //    @Override
    public List<ProfitBO> list1(ProfitDTO dto) throws SerException {
        checkSeeIdentity();
        if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
            dto.setStartTime(DateUtil.dateToString(LocalDate.now()));
            dto.setEndTime(DateUtil.dateToString(LocalDate.now()));
        }
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        FormulaDTO formulaDTO = new FormulaDTO();
        BeanUtils.copyProperties(dto, formulaDTO);
        LocalDate a = Utils.tranTime(endTime);
        String startYear = a.getYear() + "-01" + "-01";
        dto.getSorts().add("profitType=ASC");
        dto.getSorts().add("createTime=ASC");
        List<Profit> list = super.findByCis(dto);
        List<ProfitBO> boList = new ArrayList<ProfitBO>();
        boolean b = true;
        boolean b1 = true;
        boolean b2 = true;
        boolean b3 = true;
        boolean b4 = true;
        boolean b5 = true;
        boolean b6 = true;
        boolean b7 = true;
        boolean b8 = true;
        double incomeMonth = 0;
        double incomeYear = 0;
        int num = 1;
        if ((list != null) && (!list.isEmpty())) {
            for (Profit profit : list) {
                List<FormulaBO> formulaBOs = formulaSer.findByFid(profit.getId(), formulaDTO);
                if ((formulaBOs != null) && (!formulaBOs.isEmpty())) {
                    FormulaBO formulaBO = formulaBOs.get(formulaBOs.size() - 1);
                    ProfitBO bo = BeanTransform.copyProperties(profit, ProfitBO.class);
                    bo.setCurrentMonthAmount(formulaBO.getCurrent());
                    List<FormulaBO> startYearBOs = formulaSer.findByFid(profit.getId(), formulaDTO);
                    if ((startYearBOs != null) && (!startYearBOs.isEmpty())) {
                        bo.setCurrentYearAmount(startYearBOs.get(startYearBOs.size() - 1).getCurrent());
                    }
                    if (ProfitType.AINCOME.equals(profit.getProfitType()) && b8) {
                        ProfitBO bo1 = new ProfitBO();
                        bo1.setProject("一、营业收入");
                        num++;
                        b8 = false;
                    }
                    if (ProfitType.AINCOME.equals(profit.getProfitType())) {
                        if (Type.ADD.equals(profit.getType())) {
                            incomeMonth += bo.getCurrentMonthAmount();
                            incomeYear += bo.getCurrentYearAmount();
                            if (b) {
                                bo.setProject("加：" + profit.getProject());
                                b = false;
                            }
                        } else if (Type.REMOVE.equals(profit.getType())) {
                            incomeMonth -= bo.getCurrentMonthAmount();
                            incomeYear -= bo.getCurrentYearAmount();
                            if (b1) {
                                bo.setProject("减：" + profit.getProject());
                                b1 = false;
                            }
                        }
//                        if (profit == list.get(0)) {
//                            bo.setProject("一、" + profit.getProject());
//                        }
                    } else if (ProfitType.BPROFIT.equals(profit.getProfitType())) {
                        if (b2) {
                            ProfitBO twoBO = new ProfitBO();
                            twoBO.setProject("二、营业利润");
                            twoBO.setCurrentMonthAmount(incomeMonth);
                            twoBO.setCurrentYearAmount(incomeYear);
                            twoBO.setNum(num);
                            num++;
                            boList.add(twoBO);
                            b2 = false;
                        }
                        if (Type.ADD.equals(profit.getType())) {
                            incomeMonth += bo.getCurrentMonthAmount();
                            incomeYear += bo.getCurrentYearAmount();
                            if (b3) {
                                bo.setProject("加：" + profit.getProject());
                                b3 = false;
                            }
                        } else if (Type.REMOVE.equals(profit.getType())) {
                            incomeMonth -= bo.getCurrentMonthAmount();
                            incomeYear -= bo.getCurrentYearAmount();
                            if (b4) {
                                bo.setProject("减：" + profit.getProject());
                                b4 = false;
                            }
                        }
                    } else if (ProfitType.CSUM.equals(profit.getProfitType())) {
                        if (b5) {
                            ProfitBO twoBO = new ProfitBO();
                            twoBO.setProject("三、利润总额");
                            twoBO.setCurrentMonthAmount(incomeMonth);
                            twoBO.setCurrentYearAmount(incomeYear);
                            twoBO.setNum(num);
                            num++;
                            boList.add(twoBO);
                            b5 = false;
                        }
                        if (Type.ADD.equals(profit.getType())) {
                            incomeMonth += bo.getCurrentMonthAmount();
                            incomeYear += bo.getCurrentYearAmount();
                            if (b6) {
                                bo.setProject("加：" + profit.getProject());
                                b6 = false;
                            }
                        } else if (Type.REMOVE.equals(profit.getType())) {
                            incomeMonth -= bo.getCurrentMonthAmount();
                            incomeYear -= bo.getCurrentYearAmount();
                            if (b7) {
                                bo.setProject("减：" + profit.getProject());
                                b7 = false;
                            }
                        }
                    }
                    bo.setNum(num);
                    num++;
                    boList.add(bo);
                } else {
                    ProfitBO bo = BeanTransform.copyProperties(profit, ProfitBO.class);
                    bo.setNum(num);
                    num++;
                    boList.add(bo);
                }
            }
            ProfitBO lastBO = new ProfitBO();
            lastBO.setProject("四、净利润");
            lastBO.setCurrentMonthAmount(incomeMonth);
            lastBO.setCurrentYearAmount(incomeYear);
            lastBO.setNum(num);
            num++;
            boList.add(lastBO);
        }
        return boList;
    }

//    @Override
//    public List<ProfitBO> list(ProfitDTO dto) throws SerException {
//        checkSeeIdentity();
//        if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
//            dto.setStartTime(DateUtil.dateToString(LocalDate.now()));
//            dto.setEndTime(DateUtil.dateToString(LocalDate.now()));
//        }
//        String startTime = dto.getStartTime();
//        String endTime = dto.getEndTime();
//        FormulaDTO formulaDTO = new FormulaDTO();
//        BeanUtils.copyProperties(dto, formulaDTO);
//        LocalDate a = Utils.tranTime(endTime);
//        String startYear = a.getYear() + "-01" + "-01";
//        dto.getSorts().add("profitType=ASC");
//        dto.getSorts().add("createTime=ASC");
//        List<Profit> list = super.findByCis(dto);
//        List<ProfitBO> boList = new ArrayList<ProfitBO>();
//        boolean b = true;
//        boolean b1 = true;
//        boolean b2 = true;
//        boolean b3 = true;
//        boolean b4 = true;
//        boolean b5 = true;
//        boolean b6 = true;
//        boolean b7 = true;
//        boolean b8 = true;
//        double incomeMonth = 0;
//        double incomeYear = 0;
//        int num = 1;
//        if ((list != null) && (!list.isEmpty())) {
//            for (Profit profit : list) {
//                List<FormulaBO> formulaBOs = formulaSer.findByFid(profit.getId(), formulaDTO);
//                if ((formulaBOs != null) && (!formulaBOs.isEmpty())) {
//                    FormulaBO formulaBO = formulaBOs.get(formulaBOs.size() - 1);
//                    ProfitBO bo = BeanTransform.copyProperties(profit, ProfitBO.class);
//                    bo.setCurrentMonthAmount(formulaBO.getCurrent());
//                    List<FormulaBO> startYearBOs = formulaSer.findByFid(profit.getId(), formulaDTO);
//                    if ((startYearBOs != null) && (!startYearBOs.isEmpty())) {
//                        bo.setCurrentYearAmount(startYearBOs.get(startYearBOs.size() - 1).getCurrent());
//                    }
//                    if (ProfitType.AINCOME.equals(profit.getProfitType()) && b8) {
//                        ProfitBO bo1 = new ProfitBO();
//                        bo1.setProject("一、营业收入");
//                        bo1.setNum(num);
//                        num++;
//                        boList.add(bo1);
//                        b8 = false;
//                    }
//                    if (ProfitType.AINCOME.equals(profit.getProfitType())) {
//                        if (Type.ADD.equals(profit.getType())) {
//                            incomeMonth += bo.getCurrentMonthAmount();
//                            incomeYear += bo.getCurrentYearAmount();
//                            if (b) {
//                                bo.setProject("加：" + profit.getProject());
//                                b = false;
//                            }
//                        } else if (Type.REMOVE.equals(profit.getType())) {
//                            incomeMonth -= bo.getCurrentMonthAmount();
//                            incomeYear -= bo.getCurrentYearAmount();
//                            if (b1) {
//                                bo.setProject("减：" + profit.getProject());
//                                b1 = false;
//                            }
//                        }
//                    } else if (ProfitType.BPROFIT.equals(profit.getProfitType())) {
//                        if (b2) {
//                            ProfitBO twoBO = new ProfitBO();
//                            twoBO.setProject("二、营业利润");
//                            twoBO.setCurrentMonthAmount(incomeMonth);
//                            twoBO.setCurrentYearAmount(incomeYear);
//                            twoBO.setNum(num);
//                            num++;
//                            boList.add(twoBO);
//                            b2 = false;
//                        }
//                        if (Type.ADD.equals(profit.getType())) {
//                            incomeMonth += bo.getCurrentMonthAmount();
//                            incomeYear += bo.getCurrentYearAmount();
//                            if (b3) {
//                                bo.setProject("加：" + profit.getProject());
//                                b3 = false;
//                            }
//                        } else if (Type.REMOVE.equals(profit.getType())) {
//                            incomeMonth -= bo.getCurrentMonthAmount();
//                            incomeYear -= bo.getCurrentYearAmount();
//                            if (b4) {
//                                bo.setProject("减：" + profit.getProject());
//                                b4 = false;
//                            }
//                        }
//                    } else if (ProfitType.CSUM.equals(profit.getProfitType())) {
//                        if (b5) {
//                            ProfitBO twoBO = new ProfitBO();
//                            twoBO.setProject("三、利润总额");
//                            twoBO.setCurrentMonthAmount(incomeMonth);
//                            twoBO.setCurrentYearAmount(incomeYear);
//                            twoBO.setNum(num);
//                            num++;
//                            boList.add(twoBO);
//                            b5 = false;
//                        }
//                        if (Type.ADD.equals(profit.getType())) {
//                            incomeMonth += bo.getCurrentMonthAmount();
//                            incomeYear += bo.getCurrentYearAmount();
//                            if (b6) {
//                                bo.setProject("加：" + profit.getProject());
//                                b6 = false;
//                            }
//                        } else if (Type.REMOVE.equals(profit.getType())) {
//                            incomeMonth -= bo.getCurrentMonthAmount();
//                            incomeYear -= bo.getCurrentYearAmount();
//                            if (b7) {
//                                bo.setProject("减：" + profit.getProject());
//                                b7 = false;
//                            }
//                        }
//                    }
//                    bo.setNum(num);
//                    num++;
//                    boList.add(bo);
//                } else {
//                    ProfitBO bo = BeanTransform.copyProperties(profit, ProfitBO.class);
//
//                    if (ProfitType.AINCOME.equals(profit.getProfitType()) && b8) {
//                        ProfitBO bo1 = new ProfitBO();
//                        bo1.setProject("一、营业收入");
//                        bo1.setNum(num);
//                        boList.add(bo1);
//                        num++;
//                        b8 = false;
//                    }
//                    if (ProfitType.AINCOME.equals(profit.getProfitType())) {
//                        if (Type.ADD.equals(profit.getType())) {
//                            if (b) {
//                                bo.setProject("加：" + profit.getProject());
//                                b = false;
//                            }
//                        } else if (Type.REMOVE.equals(profit.getType())) {
//                            if (b1) {
//                                bo.setProject("减：" + profit.getProject());
//                                b1 = false;
//                            }
//                        }
//                    } else if (ProfitType.BPROFIT.equals(profit.getProfitType())) {
//                        if (b2) {
//                            ProfitBO twoBO = new ProfitBO();
//                            twoBO.setProject("二、营业利润");
//                            twoBO.setCurrentMonthAmount(incomeMonth);
//                            twoBO.setCurrentYearAmount(incomeYear);
//                            twoBO.setNum(num);
//                            num++;
//                            boList.add(twoBO);
//                            b2 = false;
//                        }
//                        if (Type.ADD.equals(profit.getType())) {
//                            if (b3) {
//                                bo.setProject("加：" + profit.getProject());
//                                b3 = false;
//                            }
//                        } else if (Type.REMOVE.equals(profit.getType())) {
//                            if (b4) {
//                                bo.setProject("减：" + profit.getProject());
//                                b4 = false;
//                            }
//                        }
//                    } else if (ProfitType.CSUM.equals(profit.getProfitType())) {
//                        if (b5) {
//                            ProfitBO twoBO = new ProfitBO();
//                            twoBO.setProject("三、利润总额");
//                            twoBO.setCurrentMonthAmount(incomeMonth);
//                            twoBO.setCurrentYearAmount(incomeYear);
//                            twoBO.setNum(num);
//                            num++;
//                            boList.add(twoBO);
//                            b5 = false;
//                        }
//                        if (Type.ADD.equals(profit.getType())) {
//                            if (b6) {
//                                bo.setProject("加：" + profit.getProject());
//                                b6 = false;
//                            }
//                        } else if (Type.REMOVE.equals(profit.getType())) {
//                            if (b7) {
//                                bo.setProject("减：" + profit.getProject());
//                                b7 = false;
//                            }
//                        }
//                    }
//                    bo.setNum(num);
//                    num++;
//                    boList.add(bo);
//                }
//            }
//            ProfitBO lastBO = new ProfitBO();
//            lastBO.setProject("四、净利润");
//            lastBO.setCurrentMonthAmount(incomeMonth);
//            lastBO.setCurrentYearAmount(incomeYear);
//            lastBO.setNum(num);
//            num++;
//            boList.add(lastBO);
//        }
//        return boList;
//    }

    /**
     * 查询使用的线程类
     *
     * @param
     * @version v1
     * @return class
     */
    class profitThread implements Runnable {

        Profit profit;

        String start;

        String end;

        List<ProfitBO> boList = new ArrayList<ProfitBO>();

        String token;


        public profitThread(Profit profit, String start, String end, List<ProfitBO> boList, String token) {
            this.profit = profit;
            this.start = start;
            this.end = end;
            this.boList = boList;
            this.token = token;
        }

        @Override
        public void run() {
            SubjectCollectBO subjectCollectBO = null;
            try {
                RpcTransmit.transmitUserToken(token);
                subjectCollectBO = voucherGenerateAPI.findCurrentAndYear(profit.getProject(), start, end, token);
            } catch (SerException e) {
                e.printStackTrace();
            }
            if (subjectCollectBO != null) {
                ProfitBO bo = BeanTransform.copyProperties(subjectCollectBO, ProfitBO.class, "project");
                bo.setProject(profit.getProject());
                bo.setProjectType(profit.getProjectType());
//                    bo.setProject1(profit.getProject());
                bo.setCurrentMonthAmount(subjectCollectBO.getCurrentAmount());
                bo.setCurrentYearAmount(subjectCollectBO.getYearAmount());
                bo.setNum(num);
                num++;
                num++;
                boList.add(bo);
            }
        }
    }


    @Override
    public List<ProfitBO> list(ProfitDTO dto) throws SerException {
        String token = RpcTransmit.getUserToken();
        Date time1 = new Date();
        List<ProfitBO> boList = new ArrayList<ProfitBO>();
//        checkSeeIdentity();
        if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
            dto.setStartTime(DateUtil.dateToString(LocalDate.now()));
            dto.setEndTime(DateUtil.dateToString(LocalDate.now()));
        }

        if (!dto.isLastest()) {
            //判断是否已有存储
            ProfitDataDTO profitDTO = new ProfitDataDTO();
            profitDTO.getConditions().add(Restrict.eq("startTime", dto.getStartTime()));
            profitDTO.getConditions().add(Restrict.eq("endTime", dto.getEndTime()));
            List<ProfitData> cashFlows = profitDataSer.findByCis(profitDTO);
            if (null != cashFlows && cashFlows.size() > 0) {
                int i = 1;
                for (ProfitData data : cashFlows) {
                    ProfitBO bo = new ProfitBO();
                    bo.setCurrentMonthAmount(data.getMonthMoney().doubleValue());
                    bo.setCurrentYearAmount(data.getYearMoney().doubleValue());
                    bo.setProject(data.getProject());
                    bo.setProjectType(data.getProjectType());
                    bo.setProfitType(data.getProfitType());
                    bo.setType(data.getType());
                    bo.setNum(i);
                    bo.setId(data.getProjectId());
                    boList.add(bo);
                    i ++;
                }
                return convertProfit(boList);
            }
        }

        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        FormulaDTO formulaDTO = new FormulaDTO();
        BeanUtils.copyProperties(dto, formulaDTO);
        LocalDate a = Utils.tranTime(endTime);
        String startYear = a.getYear() + "-01" + "-01";
        dto.getSorts().add("profitType=ASC");
        dto.getSorts().add("createTime=ASC");
        List<Profit> list = super.findByCis(dto);

        boolean b = true;
        boolean b1 = true;
        boolean b2 = true;
        boolean b3 = true;
        boolean b4 = true;
        boolean b5 = true;
        boolean b6 = true;
        boolean b7 = true;
        boolean b8 = true;
        double incomeMonth = 0;
        double incomeYear = 0;
        int num = 1;
        Double currentMonthAmount1 = 0.0;
        Double currentYearAmount1 = 0.0;
        if ((list != null) && (!list.isEmpty())) {
            for (Profit profit : list) {
                RpcTransmit.transmitUserToken(token);
                Thread thread = new Thread(new profitThread(profit, startTime, endTime, boList, token));
                thread.start();
                /*Date oldDate= new Date();
                SubjectCollectBO subjectCollectBO = voucherGenerateAPI.findCurrentAndYear(profit.getProject(), startTime, endTime);
                Date newDate = new Date();
                System.out.println("project: "+ profit.getProject() +"  date: " + (newDate.getSeconds() - oldDate.getSeconds()));
                if (subjectCollectBO != null) {
                    ProfitBO bo = BeanTransform.copyProperties(profit, ProfitBO.class, "project");
                    bo.setProject(profit.getProject());
                    bo.setProjectType(profit.getProjectType());
//                    bo.setProject1(profit.getProject());
                    bo.setCurrentMonthAmount(subjectCollectBO.getCurrentAmount());
                    bo.setCurrentYearAmount(subjectCollectBO.getYearAmount());
                    bo.setNum(num);
                    num++;
                    num++;
                    boList.add(bo);
                }*/
            }
            if (boList != null && list != null && boList.size() != list.size()) {
                try {
                    Thread.sleep(1000);
                    if (boList != null && list != null && boList.size() != list.size()) {
                        Thread.sleep(2000);
                        if (boList != null && list != null && boList.size() != list.size()) {
                            Thread.sleep(2000);
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Double monthAmount1 = 0.0;
            Double monthAmount2 = 0.0;
            Double monthAmount3 = 0.0;
            Double monthAmount4 = 0.0;
            Double monthAmount5 = 0.0;
            Double monthAmount6 = 0.0;
            Double monthAmount7 = 0.0;
            Double monthAmount8 = 0.0;
            Double yearAmount1 = 0.0;
            Double yearAmount2 = 0.0;
            Double yearAmount3 = 0.0;
            Double yearAmount4 = 0.0;
            Double yearAmount5 = 0.0;
            Double yearAmount6 = 0.0;
            Double yearAmount7 = 0.0;
            Double yearAmount8 = 0.0;

            Date oldDate = new Date();
            RpcTransmit.transmitUserToken(token);
            SubjectCollectBO subjectCollectBO1_1 = voucherGenerateAPI.findCurrentAndYear("营业收入", startTime, endTime, token);
            Date newDate = new Date();
            System.out.println("project: 营业收入" + "  date: " + (newDate.getSeconds() - oldDate.getSeconds()) + "  month：" + subjectCollectBO1_1.getCurrentAmount());
            if (null != subjectCollectBO1_1) {
                monthAmount1 = subjectCollectBO1_1.getCurrentAmount();
                yearAmount1 = subjectCollectBO1_1.getYearAmount();
            }

            for (ProfitBO bo : boList) {
                if ("营业成本".equals(bo.getProject()) || "营业税金及附加".equals(bo.getProject()) ||
                        "销售费用".equals(bo.getProject()) || "管理费用".equals(bo.getProject()) ||
                        "财务费用".equals(bo.getProject()) || "资产减值损益".equals(bo.getProject())) {
                    monthAmount2 = monthAmount2 - bo.getCurrentMonthAmount();
                    yearAmount2 = yearAmount2 - bo.getCurrentYearAmount();
                }
                if ("投资收益".equals(bo.getProject())) {
                    monthAmount2 = monthAmount2 + bo.getCurrentMonthAmount();
                    yearAmount2 = yearAmount2 + bo.getCurrentYearAmount();
                }
                if ("营业外支出".equals(bo.getProject())) {
                    //查出来的值可能为负值（负值代表支付，需要转为正值）
                    if (bo.getCurrentMonthAmount() != null) {
                        bo.setCurrentMonthAmount(Math.abs(bo.getCurrentMonthAmount()));
                    }
                    if (bo.getCurrentYearAmount() != null) {
                        bo.setCurrentYearAmount(Math.abs(bo.getCurrentYearAmount()));
                    }
                    monthAmount3 = monthAmount3 - bo.getCurrentMonthAmount();
                    yearAmount3 = yearAmount3 - bo.getCurrentYearAmount();
                }
                if ("营业外收入".equals(bo.getProject())) {
                    monthAmount3 = monthAmount3 + bo.getCurrentMonthAmount();
                    yearAmount3 = yearAmount3 + bo.getCurrentYearAmount();
                }
                if ("所得税".equals(bo.getProject())) {
                    monthAmount4 = monthAmount4 - bo.getCurrentMonthAmount();
                    yearAmount4 = yearAmount4 - bo.getCurrentYearAmount();
                }
                if ("年初未分配利润".equals(bo.getProject()) || "其他转入".equals(bo.getProject())) {
                    monthAmount5 = monthAmount5 + bo.getCurrentMonthAmount();
                    yearAmount5 = yearAmount5 + bo.getCurrentYearAmount();
                }
                if ("提取法定盈余公积".equals(bo.getProject()) || "提取法定公益金".equals(bo.getProject())
                        || "提取职工奖励及福利基金".equals(bo.getProject()) || "提取储备基金".equals(bo.getProject())
                        || "提取企业发展基金".equals(bo.getProject()) || "利润归还投资".equals(bo.getProject())) {
                    monthAmount6 = monthAmount6 - bo.getCurrentMonthAmount();
                    yearAmount6 = yearAmount6 - bo.getCurrentYearAmount();
                }
                if ("应付优先股股利".equals(bo.getProject()) || "提取任意盈余公积".equals(bo.getProject())
                        || "应付普通股股利".equals(bo.getProject()) || "转作资本（或股本）的普通股股利".equals(bo.getProject())) {
                    monthAmount7 = monthAmount7 - bo.getCurrentMonthAmount();
                    yearAmount7 = yearAmount7 - bo.getCurrentYearAmount();
                }
                //加上前缀
                bo.setProject(joingTogether(bo.getProject(), bo.getProjectType()));
            }

            ProfitBO lastBO1 = new ProfitBO();
            lastBO1.setProject("一、营业收入");
            lastBO1.setCurrentMonthAmount(monthAmount1);
            lastBO1.setCurrentYearAmount(yearAmount1);
            lastBO1.setNum(num);
            num++;
            boList.add(lastBO1);

            ProfitBO lastBO2 = new ProfitBO();
            lastBO2.setProject("二、营业利润");
            lastBO2.setCurrentMonthAmount(monthAmount1 + monthAmount2);
            lastBO2.setCurrentYearAmount(yearAmount1 + yearAmount2);
            lastBO2.setNum(num);
            num++;
            boList.add(lastBO2);

            ProfitBO lastBO3 = new ProfitBO();
            lastBO3.setProject("三、利润总额");
            lastBO3.setCurrentMonthAmount(monthAmount1 + monthAmount2 + monthAmount3);
            lastBO3.setCurrentYearAmount(yearAmount1 + yearAmount2 + yearAmount3);
            lastBO3.setNum(num);
            num++;
            boList.add(lastBO3);

            ProfitBO lastBO4 = new ProfitBO();
            lastBO4.setProject("四、净利润");
            lastBO4.setCurrentMonthAmount(monthAmount1 + monthAmount2 + monthAmount3 + monthAmount4);
            lastBO4.setCurrentYearAmount(yearAmount1 + yearAmount2 + yearAmount3 + yearAmount4);
            lastBO4.setNum(num);
            num++;
            boList.add(lastBO4);

            ProfitBO lastBO5 = new ProfitBO();
            lastBO5.setProject("五、可供分配的利润");
            lastBO5.setCurrentMonthAmount(monthAmount1 + monthAmount2 + monthAmount3 + monthAmount4 + monthAmount5);
            lastBO5.setCurrentYearAmount(yearAmount1 + yearAmount2 + yearAmount3 + yearAmount4 + yearAmount5);
            lastBO5.setNum(num);
            num++;
            boList.add(lastBO5);

            ProfitBO lastBO6 = new ProfitBO();
            lastBO6.setProject("六、可供投资者分配的利润");
            lastBO6.setCurrentMonthAmount(monthAmount1 + monthAmount2 + monthAmount3 + monthAmount4 + monthAmount5 + monthAmount6);
            lastBO6.setCurrentYearAmount(yearAmount1 + yearAmount2 + yearAmount3 + yearAmount4 + yearAmount5 + yearAmount6);
            lastBO6.setNum(num);
            num++;
            boList.add(lastBO6);

            ProfitBO lastBO7 = new ProfitBO();
            lastBO7.setProject("七、以前年度损益调整");
//            lastBO7.setCurrentMonthAmount(monthAmount1 + monthAmount2 + monthAmount3 + monthAmount4 + monthAmount5 + monthAmount6 + monthAmount7);
//            lastBO7.setCurrentYearAmount(yearAmount1 + yearAmount2 + yearAmount3 + yearAmount4 + yearAmount5 + yearAmount6 + yearAmount7);

            oldDate = new Date();
            RpcTransmit.transmitUserToken(token);
            SubjectCollectBO subjectCollectBO6 = voucherGenerateAPI.findCurrentAndYear("以前年度损益调整", startTime, endTime, token);
            newDate = new Date();
            System.out.println("project: 以前年度损益调整" + "  date: " + (newDate.getSeconds() - oldDate.getSeconds()));
            if (null != subjectCollectBO6) {
                lastBO7.setCurrentMonthAmount(subjectCollectBO6.getCurrentAmount());
                lastBO7.setCurrentYearAmount(subjectCollectBO6.getYearAmount());
            }
            lastBO7.setNum(num);
            num++;
            boList.add(lastBO7);


            /*ProfitBO twoBO1 = new ProfitBO();
            twoBO1.setProject("七、以前年度损益调整");
            SubjectCollectBO subjectCollectBO6 = voucherGenerateAPI.findCurrentAndYear("以前年度损益调整", startTime, endTime);
            if (null != subjectCollectBO6) {
                twoBO1.setCurrentMonthAmount(subjectCollectBO6.getCurrentAmount());
                twoBO1.setCurrentYearAmount(subjectCollectBO6.getYearAmount());
            }
            twoBO1.setNum(num);
            num++;
            boList.add(twoBO1);*/

            ProfitBO lastBO8 = new ProfitBO();
            lastBO8.setProject("八、未分配利润");
           /* SubjectCollectBO subjectCollectBO7 = voucherGenerateAPI.findCurrentAndYear("未分配利润", startTime, endTime);
            if (null != subjectCollectBO7) {
                lastBO8.setCurrentMonthAmount(subjectCollectBO7.getCurrentAmount());
                lastBO8.setCurrentYearAmount(subjectCollectBO7.getYearAmount());
            }*/
            lastBO8.setCurrentMonthAmount(monthAmount1 + monthAmount2 + monthAmount3 + monthAmount4 + monthAmount5 + monthAmount6 + monthAmount7
                    - (lastBO7.getCurrentMonthAmount() == null ? 0.0 : lastBO7.getCurrentMonthAmount()));
            lastBO8.setCurrentYearAmount(yearAmount1 + yearAmount2 + yearAmount3 + yearAmount4 + yearAmount5 + yearAmount6 + yearAmount7 -
                    (lastBO7.getCurrentYearAmount() == null ? 0.0 : lastBO7.getCurrentYearAmount()));
            lastBO8.setNum(num);
            num++;
            boList.add(lastBO8);
        }
        Date time2 = new Date();
        System.out.println("time：" + (time2.getSeconds() - time1.getSeconds()));

        //存储数据
        List<ProfitData> profitDataList = new ArrayList<>();
        for (ProfitBO bo : boList) {
            ProfitData data = new ProfitData();
            data.setProjectType(bo.getProjectType());
            data.setProject(bo.getProject());
            data.setProfitType(bo.getProfitType());
            data.setType(bo.getType());
            data.setNum(bo.getNum());
            data.setYearMoney(new BigDecimal(bo.getCurrentYearAmount()));
            data.setMonthMoney(new BigDecimal(bo.getCurrentMonthAmount()));
            data.setStartTime(LocalDate.parse(dto.getStartTime()));
            data.setEndTime(LocalDate.parse(dto.getEndTime()));
            data.setProjectId(bo.getId());
            data.setSystemId(null);
            profitDataList.add(data);
        }
        new Thread(new saveProfitDataThread(profitDataList)).start();

        return this.convertProfit(boList);
    }

    /**
     * 存储利润表的查询结果
     *
     */
    public class saveProfitDataThread implements Runnable {

        private List<ProfitData> list;

        public saveProfitDataThread(List<ProfitData> list) {
            this.list = list;
        }

        @Override
        public void run() {
            try {
                profitDataSer.save(list);
            } catch (SerException e) {
                e.printStackTrace();
            }
        }
    }

    List<ProfitBO> convertProfit(List<ProfitBO> list) {
        List<ProfitBO> bos = new ArrayList<>();
        ProfitBO bo1 = new ProfitBO();
        ProfitBO bo2 = new ProfitBO();
        ProfitBO bo3 = new ProfitBO();
        ProfitBO bo4 = new ProfitBO();
        ProfitBO bo5 = new ProfitBO();
        ProfitBO bo6 = new ProfitBO();
        ProfitBO bo7 = new ProfitBO();
        ProfitBO bo8 = new ProfitBO();
        ProfitBO bo9 = new ProfitBO();
        ProfitBO bo10 = new ProfitBO();
        ProfitBO bo11 = new ProfitBO();
        ProfitBO bo12 = new ProfitBO();
        ProfitBO bo13 = new ProfitBO();
        ProfitBO bo14 = new ProfitBO();
        ProfitBO bo15 = new ProfitBO();
        ProfitBO bo16 = new ProfitBO();
        ProfitBO bo17 = new ProfitBO();
        ProfitBO bo18 = new ProfitBO();
        ProfitBO bo19 = new ProfitBO();
        ProfitBO bo20 = new ProfitBO();
        ProfitBO bo21 = new ProfitBO();
        ProfitBO bo22 = new ProfitBO();
        ProfitBO bo23 = new ProfitBO();
        ProfitBO bo24 = new ProfitBO();
        ProfitBO bo25 = new ProfitBO();
        ProfitBO bo26 = new ProfitBO();
        ProfitBO bo27 = new ProfitBO();
        ProfitBO bo28 = new ProfitBO();
        ProfitBO bo29 = new ProfitBO();
        ProfitBO bo30 = new ProfitBO();

        for (ProfitBO bo : list) {
            if (null != bo.getCurrentMonthAmount()) {
                bo.setCurrentMonthAmount(new BigDecimal(bo.getCurrentMonthAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            if (null != bo.getCurrentYearAmount()) {
                bo.setCurrentYearAmount(new BigDecimal(bo.getCurrentYearAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            if ("一、营业收入".equals(bo.getProject())) {
                bo1 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("减：营业成本".equals(bo.getProject()) || "减:营业成本".equals(bo.getProject())) {
                bo2 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("营业税金及附加".equals(bo.getProject())) {
                bo3 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }

            if ("销售费用".equals(bo.getProject())) {
                bo4 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("管理费用".equals(bo.getProject())) {
                bo5 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("财务费用".equals(bo.getProject())) {
                bo6 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("资产减值损益".equals(bo.getProject())) {
                bo7 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("加：投资收益".equals(bo.getProject()) || "加:投资收益".equals(bo.getProject())) {
                bo8 = new ProfitBO(bo.getId(), bo.getProject() + "(亏损以“-”号填列)", bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }

            if ("二、营业利润".equals(bo.getProject())) {
                bo9 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("加：营业外收入".equals(bo.getProject()) || "加:营业外收入".equals(bo.getProject())) {
                bo10 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("减：营业外支出".equals(bo.getProject()) || "减:营业外支出".equals(bo.getProject())) {
                if (null != bo.getCurrentYearAmount()) {
                    bo.setCurrentYearAmount(Math.abs(bo.getCurrentYearAmount()));
                }
                if (null != bo.getCurrentMonthAmount()) {
                    bo.setCurrentMonthAmount(Math.abs(bo.getCurrentMonthAmount()));
                }
                bo11 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("三、利润总额".equals(bo.getProject())) {
                bo12 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("减：所得税".equals(bo.getProject()) || "减:所得税".equals(bo.getProject())) {
                bo13 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("四、净利润".equals(bo.getProject())) {
                bo14 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("加：年初未分配利润".equals(bo.getProject()) || "加:年初未分配利润".equals(bo.getProject())) {
                bo15 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("其他转入".equals(bo.getProject())) {
                bo16 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("五、可供分配的利润".equals(bo.getProject())) {
                bo17 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("减：提取法定盈余公积".equals(bo.getProject()) || "减:提取法定盈余公积".equals(bo.getProject())) {
                bo18 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }

            if ("提取法定公益金".equals(bo.getProject())) {
                bo19 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("提取职工奖励及福利基金".equals(bo.getProject())) {
                bo20 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("提取储备基金".equals(bo.getProject())) {
                bo21 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("提取企业发展基金".equals(bo.getProject())) {
                bo22 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("利润归还投资".equals(bo.getProject())) {
                bo23 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }

            if ("六、可供投资者分配的利润".equals(bo.getProject())) {
                bo24 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("减：应付优先股股利".equals(bo.getProject()) || "减:应付优先股股利".equals(bo.getProject())) {
                bo25 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("提取任意盈余公积".equals(bo.getProject())) {
                bo26 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("应付普通股股利".equals(bo.getProject())) {
                bo27 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("转作资本（或股本）的普通股股利".equals(bo.getProject()) || "转作资本(或股本)的普通股股利".equals(bo.getProject())) {
                bo28 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }

            if ("七、以前年度损益调整".equals(bo.getProject())) {
                bo29 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
            if ("八、未分配利润".equals(bo.getProject())) {
                bo30 = new ProfitBO(bo.getId(), bo.getProject(), bo.getNum(), bo.getCurrentMonthAmount(), bo.getCurrentYearAmount(), bo.getProjectType(), bo.getProfitType(), bo.getType());
            }
        }

        bos.add(bo1);
        bos.add(bo2);
        bos.add(bo3);
        bos.add(bo4);
        bos.add(bo5);
        bos.add(bo6);
        bos.add(bo7);
        bos.add(bo8);
        bos.add(bo9);
        bos.add(bo10);
        bos.add(bo11);
        bos.add(bo12);
        bos.add(bo13);
        bos.add(bo14);
        bos.add(bo15);
        bos.add(bo16);
        bos.add(bo17);
        bos.add(bo18);
        bos.add(bo19);
        bos.add(bo20);
        bos.add(bo21);
        bos.add(bo22);
        bos.add(bo23);
        bos.add(bo24);
        bos.add(bo25);
        bos.add(bo26);
        bos.add(bo27);
        bos.add(bo28);
        bos.add(bo29);
        bos.add(bo30);


        return bos;

    }


    public String joingTogether(String firstSubject, String projectType) throws SerException {
        return projectType == null ? firstSubject : (projectType + ":" + firstSubject);
    }

    @Override
    public List<ProfitLevelBO> levelAnalyze(ProfitDTO dto) throws SerException {
        String startTime = dto.getStartTime();
        String endDate = dto.getEndTime();
        List<ProfitLevelBO> profitLevelBOList = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("######0.00");
        if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
            dto.setStartTime(DateUtil.dateToString(LocalDate.now()));
            dto.setEndTime(DateUtil.dateToString(LocalDate.now()));
        }
        //开始值的数据
        String startValueStartDate = DateUtil.dateToString(LocalDate.of(DateUtil.parseDate(dto.getStartTime()).getYear(), DateUtil.parseDate(dto.getStartTime()).getMonthValue(), 1));
        String startValueEndDate = DateUtil.dateToString(LocalDate.of(DateUtil.parseDate(dto.getStartTime()).getYear(), DateUtil.parseDate(dto.getStartTime()).getMonthValue(), DateUtil.getDayByDate(DateUtil.parseDate(dto.getStartTime()).getYear(), DateUtil.parseDate(dto.getStartTime()).getMonthValue())));
        dto.setStartTime(startValueStartDate);
        dto.setEndTime(startValueEndDate);
        List<ProfitBO> profitBOS1 = list(dto);
        //结束值的数据
        String endValueStartDate = DateUtil.dateToString(LocalDate.of(DateUtil.parseDate(endDate).getYear(), DateUtil.parseDate(endDate).getMonthValue(), 1));
        String endValueEndDate = DateUtil.dateToString(LocalDate.of(DateUtil.parseDate(endDate).getYear(), DateUtil.parseDate(endDate).getMonthValue(), DateUtil.getDayByDate(DateUtil.parseDate(endDate).getYear(), DateUtil.parseDate(endDate).getMonthValue())));
        dto.setStartTime(endValueStartDate);
        dto.setEndTime(endValueEndDate);
        List<ProfitBO> profitBOS2 = list(dto);
        if (profitBOS1 != null && profitBOS1.size() > 0) {
            for (ProfitBO profitBO : profitBOS1) {
                for (ProfitBO profitBO1 : profitBOS2) {
                    if (profitBO.getProject().equals(profitBO1.getProject())) {
                        ProfitLevelBO profitLevelBO = new ProfitLevelBO();
                        profitLevelBO.setProject(profitBO.getProject());
                        profitLevelBO.setStart(profitBO.getCurrentMonthAmount());
                        profitLevelBO.setEnd(profitBO1.getCurrentMonthAmount());
                        profitLevelBO.setChange(profitBO1.getCurrentMonthAmount() - profitBO.getCurrentMonthAmount());
                        profitLevelBO.setChangeScale((profitBO.getCurrentMonthAmount() == 0 ? 0d : (Double.parseDouble(df.format((profitBO1.getCurrentMonthAmount() - profitBO.getCurrentMonthAmount()) / profitBO.getCurrentMonthAmount())))) + "%");
                        profitLevelBOList.add(profitLevelBO);
                    }
                }
            }
        }
        profitLevelBOList.stream().forEach(obj -> {
            obj.setStartTime(startTime);
            obj.setEndTime(endDate);
        });
        return profitLevelBOList;

    }
//    public List<ProfitLevelBO> levelAnalyze(ProfitDTO dto) throws SerException {
//        checkSeeIdentity();
//        if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
//            dto.setStartTime(DateUtil.dateToString(DateUtil.getStartMonth()));
//            dto.setEndTime(DateUtil.dateToString(DateUtil.getEndMonth()));
//        }
//        String startTime = dto.getStartTime();
//        String endTime = dto.getEndTime();
////        String[] projectNames = dto.getProjectNames();
//        FormulaDTO formulaDTO = new FormulaDTO();
//        BeanUtils.copyProperties(dto, formulaDTO);
//        dto.getSorts().add("profitType=ASC");
//        List<Profit> list = super.findByCis(dto);
//        List<ProfitLevelBO> boList = new ArrayList<>();
//        boolean b = true;
//        boolean b1 = true;
//        boolean b2 = true;
//        boolean b3 = true;
//        boolean b4 = true;
//        boolean b5 = true;
//        boolean b6 = true;
//        boolean b7 = true;
//        double startSum = 0;
//        double endSum = 0;
//        if ((list != null) && (!list.isEmpty())) {
//            for (Profit p : list) {
//                List<FormulaBO> starts = formulaSer.profitAnalyze(p.getId(), startTime, dto);
//                List<FormulaBO> ends = formulaSer.profitAnalyze(p.getId(), endTime, dto);
//                double start = 0;
//                double end = 0;
//                if ((starts != null) && (!starts.isEmpty())) {
//                    start = starts.get(starts.size() - 1).getCurrent();
//                }
//                if ((ends != null) && (!ends.isEmpty())) {
//                    end = ends.get(ends.size() - 1).getCurrent();
//                }
//                double change = start - end;
//                if (change < 0) {
//                    change = -change;
//                }
//                ProfitLevelBO bo = new ProfitLevelBO();
//                bo.setProject(p.getProject());
//                bo.setStart(start);
//                bo.setEnd(end);
//                bo.setChange(change);
//                if (end == 0) {
//                    bo.setChangeScale("0%");
//                } else {
//                    bo.setChangeScale(String.format("%.2f", (change / end) * 100) + "%");
//                }
//                if (p == list.get(0)) {
//                    bo.setProject("一、" + p.getProject());
//                }
//                if (ProfitType.AINCOME.equals(p.getProfitType())) {
//                    bo.setProfitType(p.getProfitType());
//                    if (Type.ADD.equals(p.getType())) {
//                        startSum += bo.getStart();
//                        endSum += bo.getEnd();
//                        if (b) {
//                            bo.setProject("加：" + p.getProject());
//                            b = false;
//                        }
//                    } else if (Type.REMOVE.equals(p.getType())) {
//                        startSum -= bo.getStart();
//                        endSum -= bo.getEnd();
//                        if (b1) {
//                            bo.setProject("减：" + p.getProject());
//                            b1 = false;
//                        }
//                    }
//                } else if (ProfitType.BPROFIT.equals(p.getProfitType())) {
//                    bo.setProfitType(p.getProfitType());
//                    if (b2) {
//                        ProfitLevelBO twoBO = new ProfitLevelBO();
//                        twoBO.setProject("二、营业利润");
//                        twoBO.setStart(startSum);
//                        twoBO.setEnd(endSum);
//                        twoBO.setProfitType(p.getProfitType());
//                        double changeSum = startSum - endSum;
//                        if (changeSum < 0) {
//                            changeSum = -changeSum;
//                        }
//                        if (endSum == 0) {
//                            bo.setChangeScale("0%");
//                        } else {
//                            bo.setChangeScale(String.format("%.2f", (changeSum / endSum) * 100) + "%");
//                        }
//                        boList.add(twoBO);
//                        b2 = false;
//                    }
//                    if (Type.ADD.equals(p.getType())) {
//                        startSum += bo.getStart();
//                        endSum += bo.getEnd();
//                        if (b3) {
//                            bo.setProject("加：" + p.getProject());
//                            b3 = false;
//                        }
//                    } else if (Type.REMOVE.equals(p.getType())) {
//                        startSum -= bo.getStart();
//                        endSum -= bo.getEnd();
//                        if (b4) {
//                            bo.setProject("减：" + p.getProject());
//                            b4 = false;
//                        }
//                    }
//                } else if (ProfitType.CSUM.equals(p.getProfitType())) {
//                    bo.setProfitType(p.getProfitType());
//                    if (b5) {
//                        ProfitLevelBO twoBO = new ProfitLevelBO();
//                        twoBO.setProject("三、利润总额");
//                        twoBO.setStart(startSum);
//                        twoBO.setEnd(endSum);
//                        twoBO.setProfitType(p.getProfitType());
//                        double changeSum = startSum - endSum;
//                        if (changeSum < 0) {
//                            changeSum = -changeSum;
//                        }
//                        if (endSum == 0) {
//                            bo.setChangeScale("0%");
//                        } else {
//                            bo.setChangeScale(String.format("%.2f", (changeSum / endSum) * 100) + "%");
//                        }
//                        boList.add(twoBO);
//                        b5 = false;
//                    }
//                    if (Type.ADD.equals(p.getType())) {
//                        startSum += bo.getStart();
//                        endSum += bo.getEnd();
//                        if (b6) {
//                            bo.setProject("加：" + p.getProject());
//                            b6 = false;
//                        }
//                    } else if (Type.REMOVE.equals(p.getType())) {
//                        startSum -= bo.getStart();
//                        endSum -= bo.getEnd();
//                        if (b7) {
//                            bo.setProject("减：" + p.getProject());
//                            b7 = false;
//                        }
//                    }
//                }
//                boList.add(bo);
//            }
//            ProfitLevelBO lastBO = new ProfitLevelBO();
//            lastBO.setProject("四、净利润");
//            lastBO.setStart(startSum);
//            lastBO.setEnd(endSum);
//            lastBO.setProfitType(ProfitType.DNETPROFIT);
//            double changeSum = startSum - endSum;
//            if (changeSum < 0) {
//                changeSum = -changeSum;
//            }
//            if (endSum == 0) {
//                lastBO.setChangeScale("0%");
//            } else {
//                lastBO.setChangeScale(String.format("%.2f", (changeSum / endSum) * 100) + "%");
//            }
//            boList.add(lastBO);
//        }
//        boList.stream().forEach(obj -> {
//            obj.setStartTime(dto.getStartTime());
//            obj.setEndTime(dto.getEndTime());
//        });
//        return boList;
//    }

    @Override
    public List<ProfitVerticalBO> verticalAnalyze(ProfitDTO dto) throws SerException {
        String endDate = dto.getEndTime();
        Double businessStartValue = 0d;
        Double businessEndValue = 0d;
        List<ProfitVerticalBO> profitVerticalBOS = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("######0.00");
        if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
            dto.setStartTime(DateUtil.dateToString(LocalDate.now()));
            dto.setEndTime(DateUtil.dateToString(LocalDate.now()));
        }
        //开始值的数据
        String startValueStartDate = DateUtil.dateToString(LocalDate.of(DateUtil.parseDate(dto.getStartTime()).getYear(), DateUtil.parseDate(dto.getStartTime()).getMonthValue(), 1));
        String startValueEndDate = DateUtil.dateToString(LocalDate.of(DateUtil.parseDate(dto.getStartTime()).getYear(), DateUtil.parseDate(dto.getStartTime()).getMonthValue(), DateUtil.getDayByDate(DateUtil.parseDate(dto.getStartTime()).getYear(), DateUtil.parseDate(dto.getStartTime()).getMonthValue())));
        dto.setStartTime(startValueStartDate);
        dto.setEndTime(startValueEndDate);
        List<ProfitBO> profitBOS1 = list(dto);
        //结束值的数据
        String endValueStartDate = DateUtil.dateToString(LocalDate.of(DateUtil.parseDate(endDate).getYear(), DateUtil.parseDate(endDate).getMonthValue(), 1));
        String endValueEndDate = DateUtil.dateToString(LocalDate.of(DateUtil.parseDate(endDate).getYear(), DateUtil.parseDate(endDate).getMonthValue(), DateUtil.getDayByDate(DateUtil.parseDate(endDate).getYear(), DateUtil.parseDate(endDate).getMonthValue())));
        List<ProfitBO> profitBOS2 = list(dto);
        dto.setStartTime(endValueStartDate);
        dto.setEndTime(endValueEndDate);
        if (profitBOS1 != null && profitBOS1.size() > 0) {
            List<ProfitBO> profitBOTis1 = profitBOS1.stream().filter(str -> "一、营业收入".equals(str.getProject())).collect(Collectors.toList());
            List<ProfitBO> profitBOTis2 = profitBOS2.stream().filter(str -> "一、营业收入".equals(str.getProject())).collect(Collectors.toList());
            if (null != profitBOTis1 && profitBOTis1.size() > 0) {
                businessStartValue = profitBOTis1.get(0).getCurrentMonthAmount();
                businessEndValue = profitBOTis2.get(0).getCurrentMonthAmount();
            }
            for (ProfitBO profitBO : profitBOS1) {
                for (ProfitBO profitBO1 : profitBOS2) {
                    if (profitBO.getProject().equals(profitBO1.getProject())) {
                        ProfitVerticalBO profitVerticalBO = new ProfitVerticalBO();
                        profitVerticalBO.setProject(profitBO.getProject());
                        profitVerticalBO.setStartTime(null == businessStartValue || 0 == businessStartValue ? 0 : Double.parseDouble(df.format(profitBO.getCurrentMonthAmount() / businessStartValue)));
                        profitVerticalBO.setEndTime(null == businessEndValue || 0 == businessEndValue ? 0 : Double.parseDouble(df.format(profitBO.getCurrentMonthAmount() / businessEndValue)));
                        profitVerticalBO.setChange(profitVerticalBO.getStartTime() - profitVerticalBO.getEndTime());
                        profitVerticalBOS.add(profitVerticalBO);
                    }
                }
            }
        }
        return profitVerticalBOS;
    }
//    @Override
//    public List<ProfitVerticalBO> verticalAnalyze(ProfitDTO dto) throws SerException {
//        if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
//            dto.setStartTime(DateUtil.dateToString(DateUtil.getStartMonth()));
//            dto.setEndTime(DateUtil.dateToString(DateUtil.getEndMonth()));
//        }
//        List<ProfitLevelBO> levelBOs = levelAnalyze(dto);
//        double incomeStart = 0;
//        double incomeEnd = 0;
//        List<ProfitVerticalBO> boList = new ArrayList<>();
//        if ((levelBOs != null) && (!levelBOs.isEmpty())) {
//            for (ProfitLevelBO level : levelBOs) {
//                ProfitVerticalBO verticalBO = new ProfitVerticalBO();
//                verticalBO.setProject(level.getProject());
//                if (ProfitType.AINCOME.equals(level.getProfitType())) {
//                    if (level.getProject().contains("、")) {
//                        incomeStart = level.getStart();
//                        incomeEnd = level.getEnd();
//                        verticalBO.setStartTime("100%");
//                        verticalBO.setEndTime("100%");
//                        boList.add(verticalBO);
//                    } else {
//                        String start = String.format("%.2f", (level.getStart() / incomeStart) * 100);
//                        String end = String.format("%.2f", (level.getEnd() / incomeEnd) * 100);
//                        double change = Double.parseDouble(start) - Double.parseDouble(end);
//                        verticalBO.setStartTime(start + "%");
//                        verticalBO.setEndTime(end + "%");
//                        verticalBO.setChange(change);
//                        boList.add(verticalBO);
//                    }
//                } else if (ProfitType.BPROFIT.equals(level.getProfitType())) {
//                    if (level.getProject().contains("、")) {
//                        incomeStart = level.getStart();
//                        incomeEnd = level.getEnd();
//                        verticalBO.setStartTime("100%");
//                        verticalBO.setEndTime("100%");
//                        boList.add(verticalBO);
//                    } else {
//                        String start = String.format("%.2f", (level.getStart() / incomeStart) * 100);
//                        String end = String.format("%.2f", (level.getEnd() / incomeEnd) * 100);
//                        double change = Double.parseDouble(start) - Double.parseDouble(end);
//                        verticalBO.setStartTime(start + "%");
//                        verticalBO.setEndTime(end + "%");
//                        verticalBO.setChange(change);
//                        boList.add(verticalBO);
//                    }
//                } else if (ProfitType.CSUM.equals(level.getProfitType())) {
//                    if (level.getProject().contains("、")) {
//                        incomeStart = level.getStart();
//                        incomeEnd = level.getEnd();
//                        verticalBO.setStartTime("100%");
//                        verticalBO.setEndTime("100%");
//                        boList.add(verticalBO);
//                    } else {
//                        String start = String.format("%.2f", (level.getStart() / incomeStart) * 100);
//                        String end = String.format("%.2f", (level.getEnd() / incomeEnd) * 100);
//                        double change = Double.parseDouble(start) - Double.parseDouble(end);
//                        verticalBO.setStartTime(start + "%");
//                        verticalBO.setEndTime(end + "%");
//                        verticalBO.setChange(change);
//                        boList.add(verticalBO);
//                    }
//                } else if (ProfitType.DNETPROFIT.equals(level.getProfitType())) {
//                    if (level.getProject().contains("、")) {
//                        incomeStart = level.getStart();
//                        incomeEnd = level.getEnd();
//                        verticalBO.setStartTime("100%");
//                        verticalBO.setEndTime("100%");
//                        boList.add(verticalBO);
//                    }
//                }
//            }
//        }
//        boList.stream().forEach(obj -> {
//            obj.setStartTime(dto.getStartTime());
//            obj.setEndTime(dto.getEndTime());
//        });
//        return boList;
//    }

    @Override
    public List<ProfitAnalyzeIndicatorBO> analyzeIndicator(ProfitDTO dto) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        List<ProfitBO> profits = list(dto);
        List<ProfitAnalyzeIndicatorBO> boList = new ArrayList<>();
        AssetDTO assetDTO = new AssetDTO();
        BeanUtils.copyProperties(dto, assetDTO, "sorts");
        DebtDTO debtDTO = new DebtDTO();
        BeanUtils.copyProperties(dto, debtDTO, "sorts");
        RpcTransmit.transmitUserToken(userToken);
        List<AssetBO> assetBOs = assetSer.list(assetDTO);
        RpcTransmit.transmitUserToken(userToken);
        List<DebtBO> debtBOs = debtSer.list(debtDTO);
        double income = 0;    //营业收入
        double expend = 0;   //营业成本
        double profit = 0;    //净利润
        double assetBegin = 0;    //期初资产
        double assetEnd = 0;   //期末资产
        double allBegin = 0;    //期初所有者权益
        double allEnd = 0;   //期末所有者权益
        if ((profits != null) && (!profits.isEmpty())) {
            if (null != profits.get(0).getCurrentMonthAmount()) {
                income = profits.get(0).getCurrentMonthAmount();
            }
            if (null != profits.get(1).getCurrentMonthAmount()) {
                expend = profits.get(1).getCurrentMonthAmount();
            }
            if (null != profits.get(profits.size() - 1).getCurrentMonthAmount()) {
                profit = profits.get(profits.size() - 1).getCurrentMonthAmount();
            }
        }
        if ((assetBOs != null) && (!assetBOs.isEmpty())) {
            if (null != assetBOs.get(assetBOs.size() - 1).getBeginAsset()) {
                assetBegin = assetBOs.get(assetBOs.size() - 1).getBeginAsset();
            }
            if (null != assetBOs.get(assetBOs.size() - 1).getEndAsset()) {
                assetEnd = assetBOs.get(assetBOs.size() - 1).getEndAsset();
            }
        }
        if ((debtBOs != null) && (!debtBOs.isEmpty())) {
            if (null != debtBOs.get(debtBOs.size() - 2).getBeginDebt()) {
                allBegin = debtBOs.get(debtBOs.size() - 2).getBeginDebt();
            }
            if (null != debtBOs.get(debtBOs.size() - 2).getEndDebt()) {
                allEnd = debtBOs.get(debtBOs.size() - 2).getEndDebt();
            }
        }
        ProfitAnalyzeIndicatorBO maoProfit = new ProfitAnalyzeIndicatorBO();
        maoProfit.setIndicator("销售毛利率");
        String mao = "0";
        String jing = "0";
        String asset = "0";
        String rd = "0";
        if (income != 0) {
            mao = String.format("%.2f", ((income - expend) / income) * 100);
            maoProfit.setIndicatorValue(mao + "%");
        }
        maoProfit.setState("表明一元销售收入扣除销售" +
                "成本后,有多少可用于抵减" +
                "各项费用而形成利润");
        boList.add(maoProfit);
        ProfitAnalyzeIndicatorBO jingProfit = new ProfitAnalyzeIndicatorBO();
        jingProfit.setIndicator("销售净利率");
        if (income != 0) {
            jing = String.format("%.2f", (profit / income) * 100);
            jingProfit.setIndicatorValue(jing + "%");
        }
        jingProfit.setState("反映每一元的销售收入实" +
                "现的净利润是多少,反映" +
                "企业一段时间经营的最终" +
                "获利水平,因此外部报表" +
                "使用者更重视销售净利率" +
                "的高低");
        boList.add(jingProfit);
        ProfitAnalyzeIndicatorBO assetProfit = new ProfitAnalyzeIndicatorBO();
        assetProfit.setIndicator("资产净利润率");
        double avgAsset = (assetBegin + assetEnd) / 2;
        if (avgAsset != 0) {
            asset = String.format("%.2f", (profit / avgAsset) * 100);
            assetProfit.setIndicatorValue(asset + "%");
        }
        assetProfit.setState("反映企业资产利用" +
                "效果的好坏及其获" +
                "利能力的高低");
        boList.add(assetProfit);
        ProfitAnalyzeIndicatorBO reward = new ProfitAnalyzeIndicatorBO();
        reward.setIndicator("净值报酬率");
        double avgAll = (allBegin + allEnd) / 2;
        if (avgAll != 0) {
            rd = String.format("%.2f", (profit / avgAll) * 100);
            reward.setIndicatorValue(rd + "%");
        }
        reward.setState("反映投资人投入资本获得的" +
                "报酬的高低,投资者可通过" +
                "计算该指标了解自己投入资" +
                "本的保险程度和获利水平");
        boList.add(reward);
        RpcTransmit.transmitUserToken(userToken);
        String advice = analyzeIndicatorAdvice(mao, jing, asset, rd);
        ProfitAnalyzeIndicatorBO adviceBO = new ProfitAnalyzeIndicatorBO();
        adviceBO.setIndicator("分析结果及建议");
        adviceBO.setState(advice);
        boList.add(adviceBO);
        return boList;
    }

    /**
     * 获取利润分析指标管理建议
     *
     * @param mao
     * @param jing
     * @param asset
     * @param rd
     * @return
     * @throws SerException
     */

    private String analyzeIndicatorAdvice(String mao, String jing, String asset, String rd) throws SerException {
        List<ProfitIndicatorAdviceBO> advices = profitIndicatorAdviceSer.list(new ProfitIndicatorAdviceDTO());
        String advice = null;
        if (advices != null && !advices.isEmpty()) {
            for (ProfitIndicatorAdviceBO r : advices) {
                boolean b1 = Double.parseDouble(mao) >= r.getGrossProfitMin() && Double.parseDouble(mao) <= r.getGrossProfitMax();
                boolean b2 = Double.parseDouble(jing) >= r.getNetProfitMin() && Double.parseDouble(jing) <= r.getNetProfitMax();
                boolean b3 = Double.parseDouble(asset) >= r.getAssetProfitMin() && Double.parseDouble(asset) <= r.getAssetProfitMax();
                boolean b4 = Double.parseDouble(rd) >= r.getRewardMin() && Double.parseDouble(rd) <= r.getRewardMax();
                if (b1 && b2 && b3 && b4) {
                    advice = r.getAdvice();
                }
            }
        }
        return advice;
    }

    @Override
    public List<DetailBO> findDetails(String id, ProfitDTO dto) throws SerException {
        checkSeeIdentity();
        List<DetailBO> boList = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("######0.00");
        Double beginningCreditAmount = 0d;//期初余额
        Double beginningDebitAmount = 0d;//期初借方余额
        Double beginningCrAmount = 0d;//期初贷方余额
        Double issueDebitAmount = 0d;//本期借方总额
        Double issueCreditAmount = 0d;//本期贷方总额
        Double issueTotalAmount = 0d;//本期合计余额
        Double endDebitAmount = 0d;//期末借方总额
        Double endCreditAmount = 0d;//期末贷方总额
        Double endTotalAmount = 0d;//本年累计额

        String startTime = dto.getStartTime() == null ? DateUtil.dateToString(LocalDate.now()) : dto.getStartTime();
        String endTime = dto.getEndTime() == null ? DateUtil.dateToString(LocalDate.now()) : dto.getEndTime();
        FormulaDTO formulaDTO = new FormulaDTO();
        BeanUtils.copyProperties(dto, formulaDTO);
//        List<FormulaBO> list = formulaSer.findByFid(id, formulaDTO);
        Profit profit = super.findById(id);
        Form form = formulaSer.FindWayByFid(id);
        if (form == null) {
            throw new SerException("请先添加对应项目计算公式");
        }

        //期初余额
        String term = startTime + "~" + endTime;
        DetailBO currentBO = new DetailBO();
        if (DateUtil.parseDate(startTime).getMonthValue() != 1) {
            String qcStartTime = DateUtil.dateToString(LocalDate.of(DateUtil.parseDate(startTime).getYear(), DateUtil.parseDate(startTime).getMonthValue() - 1, 1));
            String qcEndTime = DateUtil.dateToString(LocalDate.of(DateUtil.parseDate(startTime).getYear(), DateUtil.parseDate(startTime).getMonthValue() - 1, DateUtil.getDayByDate(DateUtil.parseDate(startTime).getYear(), DateUtil.parseDate(startTime).getMonthValue() - 1)));
            SubjectCollectBO subjectCollectBO = voucherGenerateAPI.findCurrent(2, profit.getProject(), qcStartTime, qcEndTime);
            if (subjectCollectBO != null) {
                beginningCreditAmount = subjectCollectBO.getCurrentAmount() == null ? 0 : subjectCollectBO.getCurrentAmount();
                beginningDebitAmount = subjectCollectBO.getIssueDebitAmount() == null ? 0 : subjectCollectBO.getIssueDebitAmount();
                beginningCrAmount = subjectCollectBO.getIssueCreditAmount() == null ? 0 : subjectCollectBO.getIssueCreditAmount();
            }
        }
        currentBO.setProject(profit.getProject());
        currentBO.setTerm(term);
        currentBO.setState("期初余额");
        currentBO.setForm(form);
        currentBO.setDebit(Double.parseDouble(df.format(beginningDebitAmount)));
        currentBO.setCredit(Double.parseDouble(df.format(beginningCrAmount)));
        currentBO.setRemain(Double.parseDouble(df.format(beginningCreditAmount)));
        boList.add(currentBO);

        //本期合计
        SubjectCollectBO subjectCollectBO1 = voucherGenerateAPI.findCurrent(2, profit.getProject(), startTime, endTime);
        if (subjectCollectBO1 != null) {
            issueTotalAmount = subjectCollectBO1.getCurrentAmount() == null ? 0 : subjectCollectBO1.getCurrentAmount();
            issueDebitAmount = subjectCollectBO1.getIssueDebitAmount() == null ? 0 : subjectCollectBO1.getIssueDebitAmount();
            issueCreditAmount = subjectCollectBO1.getIssueCreditAmount() == null ? 0 : subjectCollectBO1.getIssueCreditAmount();
        }

        DetailBO beginBO = new DetailBO();
        beginBO.setProject(profit.getProject());
        beginBO.setTerm(term);
        beginBO.setState("本期合计");
        beginBO.setForm(form);
        beginBO.setDebit(Double.parseDouble(df.format(issueDebitAmount)));
        beginBO.setCredit(Double.parseDouble(df.format(issueCreditAmount)));
        beginBO.setRemain(Double.parseDouble(df.format(issueTotalAmount)) + Double.parseDouble(df.format(beginningCreditAmount)));
        boList.add(beginBO);

        //本年累计
        String ljStartTime = DateUtil.parseDate(startTime).getYear() + "-01-01";
        SubjectCollectBO subjectCollectBO2 = voucherGenerateAPI.findCurrent(2, profit.getProject(), ljStartTime, endTime);
        if (subjectCollectBO2 != null) {
            endTotalAmount = subjectCollectBO2.getCurrentAmount() == null ? 0 : subjectCollectBO2.getCurrentAmount();
            endDebitAmount = subjectCollectBO2.getIssueDebitAmount() == null ? 0 : subjectCollectBO2.getIssueDebitAmount();
            endCreditAmount = subjectCollectBO2.getIssueCreditAmount() == null ? 0 : subjectCollectBO2.getIssueCreditAmount();
        }
        DetailBO yearBO = new DetailBO();
        yearBO.setTerm(term);
        yearBO.setState("本年累计");
        yearBO.setForm(form);
        yearBO.setDebit(Double.parseDouble(df.format(endDebitAmount)));
        yearBO.setCredit(Double.parseDouble(df.format(endCreditAmount)));
        yearBO.setRemain(Double.parseDouble(df.format(endTotalAmount)));
        boList.add(yearBO);

//        List<DetailBO> boList = new ArrayList<>();
//        if ((list != null) && (!list.isEmpty())) {
//            FormulaBO last = list.get(list.size() - 1);
//            double begin = last.getBegin();
//            double current = last.getCurrent();
//            Form form = last.getForm();
//            double currentSum = 0;
//            String project = findByID(id).getProject();
//            String term = startTime + "~" + endTime;
//            DetailBO currentBO = new DetailBO();
//            currentBO.setProject(project);
//            currentBO.setTerm(term);
//            currentBO.setState("本期合计");
//            currentBO.setForm(form);
//            if (Form.DEBIT.equals(form)) {
//                currentSum = begin + current;
//                currentBO.setDebit(current);
//            } else if (Form.CREDIT.equals(form)) {
//                currentSum = begin - current;
//                currentBO.setCredit(current);
//            }
//            currentBO.setRemain(currentSum);
//            double year = currentSum;
//            DetailBO beginBO = new DetailBO();
//            beginBO.setProject(project);
//            beginBO.setTerm(term);
//            beginBO.setState("期初余额");
//            beginBO.setForm(form);
//            beginBO.setRemain(begin);
//            boList.add(beginBO);
//            boList.add(currentBO);
//            DetailBO yearBO = new DetailBO();
//            yearBO.setTerm(term);
//            yearBO.setState("本年累计");
//            yearBO.setForm(form);
//            yearBO.setRemain(year);
//            boList.add(yearBO);
        return boList;
    }

    @Override
    public ProfitBO findByID(String id) throws SerException {
        Profit entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, ProfitBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void edit(ProfitTO to) throws SerException {
        checkAddIdentity();
        if (!isAccountingModule()) {
            throw new SerException("当前用户不是账务模块");
        }
        Profit entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, Profit.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        checkAddIdentity();
        if (!isAccountingModule()) {
            throw new SerException("当前用户不是账务模块");
        }
        Profit entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public Long count(ProfitDTO dto) throws SerException {
        return super.count(dto);
    }

    //zhuangkaiqin
    @Override
    public void addFormula(ProfitFormulaTO to) throws SerException {
        ProfitFormula profitFormula = new ProfitFormula();
        BeanUtils.copyProperties(to, profitFormula);
        //0代表是利润增减率分析中的,1代表是变动分析中的
        profitFormula.setType(0);
        profitFormulaSer.save(profitFormula);
    }

    @Override
    public void editFormula(ProfitFormulaTO to) throws SerException {
        ProfitFormula profitFormula = profitFormulaSer.findById(to.getId());
        if (null == profitFormula) {
            throw new SerException("目标数据不存在");
        }

        BeanUtils.copyProperties(to, profitFormula, "id", "createTime", "type");
        profitFormula.setModifyTime(LocalDateTime.now());
        profitFormulaSer.update(profitFormula);
    }

    @Override
    public void deleteFormula(String id) throws SerException {
        ProfitFormula profitFormula = profitFormulaSer.findById(id);
        if (null == profitFormula) {
            throw new SerException("目标数据不存在");
        }
        profitFormulaSer.remove(profitFormula);
    }

    @Override
    public ProfitFormulaBO findFormulaByID(String id) throws SerException {
        ProfitFormula profitFormula = profitFormulaSer.findById(id);
        if (null == profitFormula) {
            throw new SerException("目标数据对象不存在");
        }
        ProfitFormulaBO profitFormulaBO = BeanTransform.copyProperties(profitFormula, ProfitFormulaBO.class);
        return profitFormulaBO;
    }

    @Override
    public List<ProfitFormulaBO> decreaseRatioList(ProfitFormulaDTO profitFormulaDTO) throws SerException {
        profitFormulaDTO.getConditions().add(Restrict.eq("type", 0));
        profitFormulaDTO.getSorts().add("createTime=asc");
        List<ProfitFormula> profitFormulas = profitFormulaSer.findByCis(profitFormulaDTO);
        List<ProfitFormulaBO> profitFormulaBOs = BeanTransform.copyProperties(profitFormulas, ProfitFormulaBO.class);
        return profitFormulaBOs;
    }

    @Override
    public List<String> analysisChanges(ProfitDTO dto) throws SerException {
        // TODO: 17-10-30 以后会完善,目前只提供个接口
        List<String> list = new ArrayList<>(0);
        list.add("分析结果及建议");
        return list;
    }

    @Override
    public Long getFormulaTotal(ProfitFormulaDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("type", 0));
        return profitFormulaSer.count(dto);
    }

    @Override
    public List<ProfitFormulaBO> analysisChangesList(ProfitFormulaDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("type", 1));
        dto.getSorts().add("createTime=asc");
        List<ProfitFormula> profitFormulas = profitFormulaSer.findByCis(dto);
        List<ProfitFormulaBO> profitFormulaBOs = BeanTransform.copyProperties(profitFormulas, ProfitFormulaBO.class);
        return profitFormulaBOs;
    }

    @Override
    public void analysisChangesAdd(ProfitFormulaTO to) throws SerException {
        ProfitFormula profitFormula = new ProfitFormula();
        BeanUtils.copyProperties(to, profitFormula);
        profitFormula.setType(1);
        profitFormulaSer.save(profitFormula);
    }

    @Override
    public Long analysisChangesTotal(ProfitFormulaDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("type", 1));
        return profitFormulaSer.count(dto);
    }

    private String calculate(String[] operators, List<Double> eachSubject, Boolean percent) throws SerException {
        Double total = 0d;
        int i = operators.length;
        for (int j = 0; j < i; j++) {
            if (j != 0) {
                if ("+".equals(operators[j])) {
                    total = total + eachSubject.get(j);
                }
                if ("-".equals(operators[j])) {
                    total = total - eachSubject.get(j);
                }
                if ("*".equals(operators[j])) {
                    total = total * eachSubject.get(j);
                }
                if ("/".equals(operators[j])) {
                    total = total / eachSubject.get(j);
                }
            } else {
                total = eachSubject.get(0);
            }
        }
        if (percent) {
            if (total != 0d) {
                return (Double.valueOf(String.format("%.2f", total)) * 100) + "%";
            } else {
                return "0%";
            }
        } else {
            return String.valueOf(total);
        }
    }

    private Logger logger = Logger.getLogger(ProfitSerImpl.class);

    //判断是否是账务模块
    private Boolean isAccountingModule() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        RpcTransmit.transmitUserToken(userToken);
        if (!"admin".equals(userName.toLowerCase())) {
            String moduleId = moduleTypeAPI.findModuleId("账务模块");
            return positionDetailUserAPI.checkAsUserModule(userBO.getId(), moduleId);
        } else {
            return true;
        }
    }

    @Override
    public byte[] exportExcel(ProfitDTO dto) throws SerException {
        List<ProfitExportExcel> list = new ArrayList<>();
        List<ProfitBO> profitBOList = this.list(dto);
        for (ProfitBO profitBO : profitBOList) {
            ProfitExportExcel profitExportExcel = BeanTransform.copyProperties(profitBO, ProfitExportExcel.class);
            list.add(profitExportExcel);
        }

        Excel excel = new Excel(2, 3);
        byte[] bytes1 = ExcelUtil.clazzToExcel(list, excel);
        XSSFWorkbook wb = null;
        String comp = "";
        List<String> comps = companyBasicInfoAPI.findCompanyName();
        if (comps != null && comps.size() > 0) {
            comp = comps.get(0);
        }
        try {
            InputStream is = new ByteArrayInputStream(bytes1);
            wb = new XSSFWorkbook(is);// 创建一个工作execl文档
            XSSFCellStyle headerStyle = ExcelUtil.getStyle(wb, IndexedColors.WHITE.getIndex());
            headerStyle.setAlignment(HorizontalAlignment.CENTER); //水平布局：居中
            headerStyle.setWrapText(true);
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row = sheet.createRow(0);
            XSSFRow row1 = sheet.createRow(1);
            //标题
            for (int o = 0; o < 4; o++) {
                row.createCell(o).setCellValue("利润表");
            }
            CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 3);
            sheet.addMergedRegion(cra);//这个干嘛的
            //公司和单位
            row1.createCell(0).setCellValue("编制单位");
            row1.createCell(1).setCellValue(comp + "公司");
            row1.createCell(2).setCellValue("所属期:" + dto.getEndTime());
            row1.createCell(3).setCellValue("单位:元");

            row.getCell(0).setCellStyle(headerStyle);
            row1.getCell(2).setCellStyle(headerStyle);

        } catch (Exception e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return os.toByteArray();
    }

    public String test() throws SerException {

        profitDataSer.save(new ProfitData());
        return "success";
    }

    /**
     * 从记账凭证获取数据并存储到本模块（供定时任务使用）
     *
     * @param
     * @return class
     * @version v1
     */
    @Override
    public void profitTask() throws SerException {

        int year = 10;  //获取最近10年数据
        LocalDate now = LocalDate.now();
        LocalDate before = now.minusYears(year);
        now = now.minusYears(-1);
        while (!now.isEqual(before)) {

            for (int i = 1; i <= 12; i++) {

                LocalDate first = before.withMonth(i);
                if (now.getYear() - 1 == before.getYear() && first.getMonthValue() > before.getMonthValue()) {
                    continue;
                }

                System.out.println("时间段：" + first.with(TemporalAdjusters.firstDayOfMonth()) + " - " + first.with(TemporalAdjusters.lastDayOfMonth()) + "  " + new Date());
                LocalDate start = first.with(TemporalAdjusters.firstDayOfMonth());
                LocalDate end = first.with(TemporalAdjusters.lastDayOfMonth());

                ProfitDTO dto = new ProfitDTO();
                dto.setStartTime(start.toString());
                dto.setEndTime(end.toString());
                dto.setLastest(true);
                List<ProfitBO> bos = this.list(dto);
//                List<ProfitBO> bos = new ArrayList<>();
//                bos.add(new ProfitBO("1", null,null,0.0,0.0,null,null,null));
                if (bos == null || bos.size() < 1) {
                    continue;
                }
                List<ProfitData> profitDataList = new ArrayList<>();
                for (ProfitBO bo : bos) {
                    ProfitData profitData = new ProfitData();
                    profitData.setStartTime(start);
                    profitData.setEndTime(end);
                    profitData.setMonthMoney(new BigDecimal(bo.getCurrentMonthAmount()));
                    profitData.setYearMoney(new BigDecimal(bo.getCurrentYearAmount()));
                    profitData.setNum(bo.getNum());
                    profitData.setProject(bo.getProject());
                    profitData.setType(bo.getType());
                    profitData.setProfitType(bo.getProfitType());
                    profitData.setProjectType(bo.getProjectType());
                    profitData.setProjectId(bo.getId());
                    profitData.setSystemId(null);   //公司id，预留字段 todo
                    if (profitData == null) {
                        continue;
                    }
                    profitDataList.add(profitData);
                }
                //保存到本模块
                if (profitDataList != null && profitDataList.size() > 0) {
                    profitDataSer.save(profitDataList);
                }

            }

            year--;
            before = now.minusYears(year);
        }


    }

}