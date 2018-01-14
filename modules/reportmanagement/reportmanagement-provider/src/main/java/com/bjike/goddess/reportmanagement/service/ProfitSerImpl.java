package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.organize.api.ModuleTypeAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.reportmanagement.bo.*;
import com.bjike.goddess.reportmanagement.dto.*;
import com.bjike.goddess.reportmanagement.entity.Profit;
import com.bjike.goddess.reportmanagement.entity.ProfitFormula;
import com.bjike.goddess.reportmanagement.enums.Form;
import com.bjike.goddess.reportmanagement.enums.GuideAddrStatus;
import com.bjike.goddess.reportmanagement.enums.ProfitType;
import com.bjike.goddess.reportmanagement.enums.Type;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.to.ProfitFormulaTO;
import com.bjike.goddess.reportmanagement.to.ProfitTO;
import com.bjike.goddess.reportmanagement.utils.Utils;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.SubjectCollectBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<ProfitBO> list(ProfitDTO dto) throws SerException {
//        checkSeeIdentity();
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
//                List<FormulaBO> formulaBOs = formulaSer.findByFid(profit.getId(), formulaDTO);
                SubjectCollectBO subjectCollectBO = voucherGenerateAPI.findCurrentAndYear(profit.getProject(), startTime, endTime);
                if (subjectCollectBO != null) {
//                    FormulaBO formulaBO = formulaBOs.get(formulaBOs.size() - 1);
                    ProfitBO bo = BeanTransform.copyProperties(profit, ProfitBO.class);
                    bo.setCurrentMonthAmount(subjectCollectBO.getCurrentAmount());
                    bo.setCurrentYearAmount(subjectCollectBO.getYearAmount());

                    if (ProfitType.AINCOME.equals(profit.getProfitType()) && b8) {
                        ProfitBO bo1 = new ProfitBO();
                        bo1.setProject("一、营业收入");
                        SubjectCollectBO subjectCollectBO1 = voucherGenerateAPI.findCurrentAndYear("营业收入", startTime, endTime);
                        if (null != subjectCollectBO1) {
                            bo1.setCurrentMonthAmount(subjectCollectBO1.getCurrentAmount());
                            bo1.setCurrentYearAmount(subjectCollectBO1.getYearAmount());
                        }
                        bo1.setNum(num);
                        num++;
                        boList.add(bo1);
                        b8 = false;
                    } else if (ProfitType.BPROFIT.equals(profit.getProfitType())) {
                        if (b2) {
                            ProfitBO twoBO = new ProfitBO();
                            twoBO.setProject("二、营业利润");
                            SubjectCollectBO subjectCollectBO2 = voucherGenerateAPI.findCurrentAndYear("营业利润", startTime, endTime);
                            if (null != subjectCollectBO2) {
                                twoBO.setCurrentMonthAmount(subjectCollectBO2.getCurrentAmount());
                                twoBO.setCurrentYearAmount(subjectCollectBO2.getYearAmount());
                            }
//                            twoBO.setCurrentMonthAmount(incomeMonth);
//                            twoBO.setCurrentYearAmount(incomeYear);
                            twoBO.setNum(num);
                            num++;
                            boList.add(twoBO);
                            b2 = false;
                        }
                    } else if (ProfitType.CSUM.equals(profit.getProfitType())) {
                        if (b5) {
                            ProfitBO twoBO = new ProfitBO();
                            twoBO.setProject("三、利润总额");
                            SubjectCollectBO subjectCollectBO3 = voucherGenerateAPI.findCurrentAndYear("利润总额", startTime, endTime);
                            if (null != subjectCollectBO3) {
                                twoBO.setCurrentMonthAmount(subjectCollectBO3.getCurrentAmount());
                                twoBO.setCurrentYearAmount(subjectCollectBO3.getYearAmount());
                            }
//                            twoBO.setCurrentMonthAmount(incomeMonth);
//                            twoBO.setCurrentYearAmount(incomeYear);
                            twoBO.setNum(num);
                            num++;
                            boList.add(twoBO);
                            b5 = false;
                        }
                    }
                    bo.setNum(num);
                    num++;
                    boList.add(bo);
                }
// else {
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
            }
            ProfitBO lastBO = new ProfitBO();
            lastBO.setProject("四、净利润");
            SubjectCollectBO subjectCollectBO4 = voucherGenerateAPI.findCurrentAndYear("净利润", startTime, endTime);
            if (null != subjectCollectBO4) {
                lastBO.setCurrentMonthAmount(subjectCollectBO4.getCurrentAmount());
                lastBO.setCurrentYearAmount(subjectCollectBO4.getYearAmount());
            }
//            lastBO.setCurrentMonthAmount(incomeMonth);
//            lastBO.setCurrentYearAmount(incomeYear);
            lastBO.setNum(num);
            num++;
            boList.add(lastBO);
        }
        return boList;
    }

    @Override
    public List<ProfitLevelBO> levelAnalyze(ProfitDTO dto) throws SerException {
        checkSeeIdentity();
        if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
            dto.setStartTime(DateUtil.dateToString(DateUtil.getStartMonth()));
            dto.setEndTime(DateUtil.dateToString(DateUtil.getEndMonth()));
        }
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
//        String[] projectNames = dto.getProjectNames();
        FormulaDTO formulaDTO = new FormulaDTO();
        BeanUtils.copyProperties(dto, formulaDTO);
        dto.getSorts().add("profitType=ASC");
        List<Profit> list = super.findByCis(dto);
        List<ProfitLevelBO> boList = new ArrayList<>();
        boolean b = true;
        boolean b1 = true;
        boolean b2 = true;
        boolean b3 = true;
        boolean b4 = true;
        boolean b5 = true;
        boolean b6 = true;
        boolean b7 = true;
        double startSum = 0;
        double endSum = 0;
        if ((list != null) && (!list.isEmpty())) {
            for (Profit p : list) {
                List<FormulaBO> starts = formulaSer.profitAnalyze(p.getId(), startTime, dto);
                List<FormulaBO> ends = formulaSer.profitAnalyze(p.getId(), endTime, dto);
                double start = 0;
                double end = 0;
                if ((starts != null) && (!starts.isEmpty())) {
                    start = starts.get(starts.size() - 1).getCurrent();
                }
                if ((ends != null) && (!ends.isEmpty())) {
                    end = ends.get(ends.size() - 1).getCurrent();
                }
                double change = start - end;
                if (change < 0) {
                    change = -change;
                }
                ProfitLevelBO bo = new ProfitLevelBO();
                bo.setProject(p.getProject());
                bo.setStart(start);
                bo.setEnd(end);
                bo.setChange(change);
                if (end == 0) {
                    bo.setChangeScale("0%");
                } else {
                    bo.setChangeScale(String.format("%.2f", (change / end) * 100) + "%");
                }
                if (p == list.get(0)) {
                    bo.setProject("一、" + p.getProject());
                }
                if (ProfitType.AINCOME.equals(p.getProfitType())) {
                    bo.setProfitType(p.getProfitType());
                    if (Type.ADD.equals(p.getType())) {
                        startSum += bo.getStart();
                        endSum += bo.getEnd();
                        if (b) {
                            bo.setProject("加：" + p.getProject());
                            b = false;
                        }
                    } else if (Type.REMOVE.equals(p.getType())) {
                        startSum -= bo.getStart();
                        endSum -= bo.getEnd();
                        if (b1) {
                            bo.setProject("减：" + p.getProject());
                            b1 = false;
                        }
                    }
                } else if (ProfitType.BPROFIT.equals(p.getProfitType())) {
                    bo.setProfitType(p.getProfitType());
                    if (b2) {
                        ProfitLevelBO twoBO = new ProfitLevelBO();
                        twoBO.setProject("二、营业利润");
                        twoBO.setStart(startSum);
                        twoBO.setEnd(endSum);
                        twoBO.setProfitType(p.getProfitType());
                        double changeSum = startSum - endSum;
                        if (changeSum < 0) {
                            changeSum = -changeSum;
                        }
                        if (endSum == 0) {
                            bo.setChangeScale("0%");
                        } else {
                            bo.setChangeScale(String.format("%.2f", (changeSum / endSum) * 100) + "%");
                        }
                        boList.add(twoBO);
                        b2 = false;
                    }
                    if (Type.ADD.equals(p.getType())) {
                        startSum += bo.getStart();
                        endSum += bo.getEnd();
                        if (b3) {
                            bo.setProject("加：" + p.getProject());
                            b3 = false;
                        }
                    } else if (Type.REMOVE.equals(p.getType())) {
                        startSum -= bo.getStart();
                        endSum -= bo.getEnd();
                        if (b4) {
                            bo.setProject("减：" + p.getProject());
                            b4 = false;
                        }
                    }
                } else if (ProfitType.CSUM.equals(p.getProfitType())) {
                    bo.setProfitType(p.getProfitType());
                    if (b5) {
                        ProfitLevelBO twoBO = new ProfitLevelBO();
                        twoBO.setProject("三、利润总额");
                        twoBO.setStart(startSum);
                        twoBO.setEnd(endSum);
                        twoBO.setProfitType(p.getProfitType());
                        double changeSum = startSum - endSum;
                        if (changeSum < 0) {
                            changeSum = -changeSum;
                        }
                        if (endSum == 0) {
                            bo.setChangeScale("0%");
                        } else {
                            bo.setChangeScale(String.format("%.2f", (changeSum / endSum) * 100) + "%");
                        }
                        boList.add(twoBO);
                        b5 = false;
                    }
                    if (Type.ADD.equals(p.getType())) {
                        startSum += bo.getStart();
                        endSum += bo.getEnd();
                        if (b6) {
                            bo.setProject("加：" + p.getProject());
                            b6 = false;
                        }
                    } else if (Type.REMOVE.equals(p.getType())) {
                        startSum -= bo.getStart();
                        endSum -= bo.getEnd();
                        if (b7) {
                            bo.setProject("减：" + p.getProject());
                            b7 = false;
                        }
                    }
                }
                boList.add(bo);
            }
            ProfitLevelBO lastBO = new ProfitLevelBO();
            lastBO.setProject("四、净利润");
            lastBO.setStart(startSum);
            lastBO.setEnd(endSum);
            lastBO.setProfitType(ProfitType.DNETPROFIT);
            double changeSum = startSum - endSum;
            if (changeSum < 0) {
                changeSum = -changeSum;
            }
            if (endSum == 0) {
                lastBO.setChangeScale("0%");
            } else {
                lastBO.setChangeScale(String.format("%.2f", (changeSum / endSum) * 100) + "%");
            }
            boList.add(lastBO);
        }
        boList.stream().forEach(obj -> {
            obj.setStartTime(dto.getStartTime());
            obj.setEndTime(dto.getEndTime());
        });
        return boList;
    }

    @Override
    public List<ProfitVerticalBO> verticalAnalyze(ProfitDTO dto) throws SerException {
        if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
            dto.setStartTime(DateUtil.dateToString(DateUtil.getStartMonth()));
            dto.setEndTime(DateUtil.dateToString(DateUtil.getEndMonth()));
        }
        List<ProfitLevelBO> levelBOs = levelAnalyze(dto);
        double incomeStart = 0;
        double incomeEnd = 0;
        List<ProfitVerticalBO> boList = new ArrayList<>();
        if ((levelBOs != null) && (!levelBOs.isEmpty())) {
            for (ProfitLevelBO level : levelBOs) {
                ProfitVerticalBO verticalBO = new ProfitVerticalBO();
                verticalBO.setProject(level.getProject());
                if (ProfitType.AINCOME.equals(level.getProfitType())) {
                    if (level.getProject().contains("、")) {
                        incomeStart = level.getStart();
                        incomeEnd = level.getEnd();
                        verticalBO.setStartTime("100%");
                        verticalBO.setEndTime("100%");
                        boList.add(verticalBO);
                    } else {
                        String start = String.format("%.2f", (level.getStart() / incomeStart) * 100);
                        String end = String.format("%.2f", (level.getEnd() / incomeEnd) * 100);
                        double change = Double.parseDouble(start) - Double.parseDouble(end);
                        verticalBO.setStartTime(start + "%");
                        verticalBO.setEndTime(end + "%");
                        verticalBO.setChange(change);
                        boList.add(verticalBO);
                    }
                } else if (ProfitType.BPROFIT.equals(level.getProfitType())) {
                    if (level.getProject().contains("、")) {
                        incomeStart = level.getStart();
                        incomeEnd = level.getEnd();
                        verticalBO.setStartTime("100%");
                        verticalBO.setEndTime("100%");
                        boList.add(verticalBO);
                    } else {
                        String start = String.format("%.2f", (level.getStart() / incomeStart) * 100);
                        String end = String.format("%.2f", (level.getEnd() / incomeEnd) * 100);
                        double change = Double.parseDouble(start) - Double.parseDouble(end);
                        verticalBO.setStartTime(start + "%");
                        verticalBO.setEndTime(end + "%");
                        verticalBO.setChange(change);
                        boList.add(verticalBO);
                    }
                } else if (ProfitType.CSUM.equals(level.getProfitType())) {
                    if (level.getProject().contains("、")) {
                        incomeStart = level.getStart();
                        incomeEnd = level.getEnd();
                        verticalBO.setStartTime("100%");
                        verticalBO.setEndTime("100%");
                        boList.add(verticalBO);
                    } else {
                        String start = String.format("%.2f", (level.getStart() / incomeStart) * 100);
                        String end = String.format("%.2f", (level.getEnd() / incomeEnd) * 100);
                        double change = Double.parseDouble(start) - Double.parseDouble(end);
                        verticalBO.setStartTime(start + "%");
                        verticalBO.setEndTime(end + "%");
                        verticalBO.setChange(change);
                        boList.add(verticalBO);
                    }
                } else if (ProfitType.DNETPROFIT.equals(level.getProfitType())) {
                    if (level.getProject().contains("、")) {
                        incomeStart = level.getStart();
                        incomeEnd = level.getEnd();
                        verticalBO.setStartTime("100%");
                        verticalBO.setEndTime("100%");
                        boList.add(verticalBO);
                    }
                }
            }
        }
        boList.stream().forEach(obj -> {
            obj.setStartTime(dto.getStartTime());
            obj.setEndTime(dto.getEndTime());
        });
        return boList;
    }

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
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        FormulaDTO formulaDTO = new FormulaDTO();
        BeanUtils.copyProperties(dto, formulaDTO);
        List<FormulaBO> list = formulaSer.findByFid(id, formulaDTO);
        List<DetailBO> boList = new ArrayList<>();
        if ((list != null) && (!list.isEmpty())) {
            FormulaBO last = list.get(list.size() - 1);
            double begin = last.getBegin();
            double current = last.getCurrent();
            Form form = last.getForm();
            double currentSum = 0;
            String project = findByID(id).getProject();
            String term = startTime + "~" + endTime;
            DetailBO currentBO = new DetailBO();
            currentBO.setProject(project);
            currentBO.setTerm(term);
            currentBO.setState("本期合计");
            currentBO.setForm(form);
            if (Form.DEBIT.equals(form)) {
                currentSum = begin + current;
                currentBO.setDebit(current);
            } else if (Form.CREDIT.equals(form)) {
                currentSum = begin - current;
                currentBO.setCredit(current);
            }
            currentBO.setRemain(currentSum);
            double year = currentSum;
            DetailBO beginBO = new DetailBO();
            beginBO.setProject(project);
            beginBO.setTerm(term);
            beginBO.setState("期初余额");
            beginBO.setForm(form);
            beginBO.setRemain(begin);
            boList.add(beginBO);
            boList.add(currentBO);
            DetailBO yearBO = new DetailBO();
            yearBO.setTerm(term);
            yearBO.setState("本年累计");
            yearBO.setForm(form);
            yearBO.setRemain(year);
            boList.add(yearBO);
        }
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

    //判断是否是账务模块
    private Boolean isAccountingModule() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String moduleId = moduleTypeAPI.findModuleId("账务模块");
        return positionDetailUserAPI.checkAsUserModule(userBO.getId(), moduleId);
    }
}