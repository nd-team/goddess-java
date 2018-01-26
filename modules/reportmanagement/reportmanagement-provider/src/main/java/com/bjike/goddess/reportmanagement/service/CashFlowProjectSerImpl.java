package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.reportmanagement.bo.*;
import com.bjike.goddess.reportmanagement.dto.*;
import com.bjike.goddess.reportmanagement.entity.*;
import com.bjike.goddess.reportmanagement.enums.ProjectType;
import com.bjike.goddess.reportmanagement.excel.CashFlowExport;
import com.bjike.goddess.reportmanagement.to.CashRateListTO;
import com.bjike.goddess.reportmanagement.to.CashRateTO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.SubjectCollectBO;
import com.bjike.goddess.voucher.dto.SubjectCollectDTO;
import com.bjike.goddess.voucher.entity.SubjectCollect;
import com.bjike.goddess.voucher.excel.AccountInfoExport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 现金流量项目表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 03:06 ]
 * @Description: [ 现金流量项目表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class CashFlowProjectSerImpl extends ServiceImpl<CashFlowProject, CashFlowProjectDTO> implements CashFlowProjectSer {
    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;
    @Autowired
    private CashRateSer cashRateSer;
    @Autowired
    private ProjectSer projectSer;
    @Autowired
    private CashFlowSer cashFlowSer;
    @Autowired
    private CashFormulaSer cashFormulaSer;
    @Autowired
    private CashFlowDataSer cashFlowDataSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<CashFlowProjectBO> list(CashFlowProjectDTO dto) throws SerException {
        List<CashFlowProjectBO> bos = new ArrayList<>(0);
        if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
            dto.setStartTime(DateUtil.dateToString(DateUtil.getStartMonth()));
            dto.setEndTime(DateUtil.dateToString(DateUtil.getEndMonth()));
        }
        //判断是否已有存储
        CashFlowDTO cashFlowDTO = new CashFlowDTO();
        cashFlowDTO.getConditions().add(Restrict.eq("startTime", dto.getStartTime()));
        cashFlowDTO.getConditions().add(Restrict.eq("endTime", dto.getEndTime()));
        cashFlowDTO.getSorts().add("num=asc");
//        cashFlowDTO.getSorts().add("seqNum=asc");
//        List<CashFlow> cashFlows = cashFlowSer.findByCis(cashFlowDTO,true);
        List<CashFlow> cashFlows = cashFlowSer.findByCis(cashFlowDTO);
        if (null != cashFlows && cashFlows.size() > 0) {
            for (CashFlow cashFlow : cashFlows) {
                CashFlowProjectBO cashFlowProjectBO = BeanTransform.copyProperties(cashFlow, CashFlowProjectBO.class);
                cashFlowProjectBO.setId(cashFlow.getProjectId());
                bos.add(cashFlowProjectBO);
            }
            return convertCashFlowProject(bos);
        }


        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.getSorts().add("projectType=asc");
        projectDTO.getSorts().add("createTime=asc");
//        List<Project> projects = projectSer.findByCis(projectDTO,true);
        List<Project> projects = projectSer.findByCis(projectDTO);
        Boolean b1 = true;
        Boolean b2 = true;
        Boolean b3 = true;
        Boolean b4 = true;
        Boolean b5 = true;
        int num = 1;
        if (null != projects && projects.size() > 0) {
            for (Project project : projects) {
                if (ProjectType.MANAGEMENT.equals(project.getProjectType()) && b1) {
                    CashFlowProjectBO bo = new CashFlowProjectBO();
                    bo.setProjectName("一、经营活动产生的现金流量");
                    bo.setNum(num);
                    bo.setStartTime(dto.getStartTime());
                    bo.setEndTime(dto.getEndTime());
                    bos.add(bo);
                    num++;
                    b1 = false;
                } else if (ProjectType.INVESTMENT.equals(project.getProjectType()) && b2) {
                    CashFlowProjectBO bo = new CashFlowProjectBO();
                    bo.setProjectName("二、投资活动产生的现金流量");
                    bo.setNum(num);
                    bo.setStartTime(dto.getStartTime());
                    bo.setEndTime(dto.getEndTime());
                    bos.add(bo);
                    num++;
                    b2 = false;
                } else if (ProjectType.FINANCING.equals(project.getProjectType()) && b3) {
                    CashFlowProjectBO bo = new CashFlowProjectBO();
                    bo.setProjectName("三、筹资活动产生的现金流量");
                    bo.setNum(num);
                    bo.setStartTime(dto.getStartTime());
                    bo.setEndTime(dto.getEndTime());
                    bos.add(bo);
                    num++;
                    b3 = false;
                }
                CashFlowProjectBO bo = new CashFlowProjectBO();
                bo.setProjectName(project.getProjectName());
                bo.setId(project.getId());

                if (ProjectType.MANAGEMENT.equals(project.getProjectType())) {
                    if ("现金流入小计".equals(project.getProjectName())) {
                        project.setProjectName("一、现金流入小计");
                    }
                    if ("现金流出小计".equals(project.getProjectName())) {
                        project.setProjectName("一、现金流出小计");
                    }
                } else if (ProjectType.INVESTMENT.equals(project.getProjectType())) {
                    if ("现金流入小计".equals(project.getProjectName())) {
                        project.setProjectName("二、现金流入小计");
                    }
                    if ("现金流出小计".equals(project.getProjectName())) {
                        project.setProjectName("二、现金流出小计");
                    }
                } else if (ProjectType.FINANCING.equals(project.getProjectType())) {
                    if ("现金流入小计".equals(project.getProjectName())) {
                        project.setProjectName("三、现金流入小计");
                    }
                    if ("现金流出小计".equals(project.getProjectName())) {
                        project.setProjectName("三、现金流出小计");
                    }
                }
                bo.setMoney(findCashByProject(project.getProjectName(), dto));
                bo.setStartTime(dto.getStartTime());
                bo.setEndTime(dto.getEndTime());
                bo.setNum(num++);
                bos.add(bo);
            }
//            //判断是否存储
//            Boolean tar = false;
//            String startTime = findFirstDay(dto.getStartTime());
//            String startTime1 = findFirstDay(dto.getEndTime());
//            String endTime = findLastDay(dto.getEndTime());
//            if (findResult(dto.getEndTime(), DateUtil.dateToString(LocalDate.now())) && startTime == dto.getStartTime()
//                    && endTime == findLastDay(dto.getEndTime()) && startTime == startTime1) {
//                tar = true;
//            }

            int seqNum = 1;
            for (CashFlowProjectBO bo : bos) {
                CashFlow cashFlow = new CashFlow();
                cashFlow.setProjectName(bo.getProjectName());
                cashFlow.setProjectId(bo.getId());
                cashFlow.setNum(bo.getNum());
                cashFlow.setSeqNum(seqNum++);
                cashFlow.setStartTime(bo.getStartTime());
                cashFlow.setEndTime(bo.getEndTime());
                cashFlow.setMoney(bo.getMoney());
                cashFlowSer.save(cashFlow);
            }
        }

//        boolean first = false;
//        for (CashFlowProjectBO bo : bos) {
//            if ("一、现金流入小计".equals(bo.getProjectName())) {
//                first = true;
//            }
//        }
//        if (!first) {
//
//
//        }
        return convertCashFlowProject(bos);
    }

    private List<CashFlowProjectBO> convertCashFlowProject(List<CashFlowProjectBO> list) {
        List<CashFlowProjectBO> bos = new ArrayList<>();
        CashFlowProjectBO bo1 = new CashFlowProjectBO();
        CashFlowProjectBO bo2 = new CashFlowProjectBO();
        CashFlowProjectBO bo3 = new CashFlowProjectBO();
        CashFlowProjectBO bo4 = new CashFlowProjectBO();
        CashFlowProjectBO bo5 = new CashFlowProjectBO();
        CashFlowProjectBO bo6 = new CashFlowProjectBO();
        CashFlowProjectBO bo7 = new CashFlowProjectBO();
        CashFlowProjectBO bo8 = new CashFlowProjectBO();
        CashFlowProjectBO bo9 = new CashFlowProjectBO();
        CashFlowProjectBO bo10 = new CashFlowProjectBO();
        CashFlowProjectBO bo11 = new CashFlowProjectBO();
        CashFlowProjectBO bo12 = new CashFlowProjectBO();
        CashFlowProjectBO bo13 = new CashFlowProjectBO();
        CashFlowProjectBO bo14 = new CashFlowProjectBO();
        CashFlowProjectBO bo15 = new CashFlowProjectBO();
        CashFlowProjectBO bo16 = new CashFlowProjectBO();
        CashFlowProjectBO bo17 = new CashFlowProjectBO();
        CashFlowProjectBO bo18 = new CashFlowProjectBO();
        CashFlowProjectBO bo19 = new CashFlowProjectBO();
        CashFlowProjectBO bo20 = new CashFlowProjectBO();
        CashFlowProjectBO bo21 = new CashFlowProjectBO();
        CashFlowProjectBO bo22 = new CashFlowProjectBO();
        CashFlowProjectBO bo23 = new CashFlowProjectBO();
        CashFlowProjectBO bo24 = new CashFlowProjectBO();
        CashFlowProjectBO bo25 = new CashFlowProjectBO();
        CashFlowProjectBO bo26 = new CashFlowProjectBO();
        CashFlowProjectBO bo27 = new CashFlowProjectBO();
        CashFlowProjectBO bo28 = new CashFlowProjectBO();
        CashFlowProjectBO bo29 = new CashFlowProjectBO();
        CashFlowProjectBO bo30 = new CashFlowProjectBO();
        CashFlowProjectBO bo31 = new CashFlowProjectBO();
        CashFlowProjectBO bo32 = new CashFlowProjectBO();
        CashFlowProjectBO bo33 = new CashFlowProjectBO();
        CashFlowProjectBO bo34 = new CashFlowProjectBO();
        for (CashFlowProjectBO cashFlowProjectBO : list) {
            if ("一、经营活动产生的现金流量".equals(cashFlowProjectBO.getProjectName())) {
                bo1 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("销售商品、提供劳务收到的现金".equals(cashFlowProjectBO.getProjectName())) {
                bo2 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("收到的税费返还".equals(cashFlowProjectBO.getProjectName())) {
                bo3 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("收到的其他与经营活动有关的现金".equals(cashFlowProjectBO.getProjectName())) {
                bo4 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("一、现金流入小计".equals(cashFlowProjectBO.getProjectName())) {
                bo5 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("购买商品接受劳务支付的现金".equals(cashFlowProjectBO.getProjectName())) {
                bo6 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("支付给职工以及为职工支付的现金".equals(cashFlowProjectBO.getProjectName())) {
                bo7 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("支付的各项税费".equals(cashFlowProjectBO.getProjectName())) {
                bo8 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("支付的其他与经营活动有关的现金".equals(cashFlowProjectBO.getProjectName())) {
                bo9 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("一、现金流出小计".equals(cashFlowProjectBO.getProjectName())) {
                bo10 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("经营活动产生的现金流量净额".equals(cashFlowProjectBO.getProjectName())) {
                bo11 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("二、投资活动产生的现金流量".equals(cashFlowProjectBO.getProjectName())) {
                bo12 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("收回投资所收到的现金".equals(cashFlowProjectBO.getProjectName())) {
                bo13 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("取得投资收益所收到的现金".equals(cashFlowProjectBO.getProjectName())) {
                bo14 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("处置固定资产、无形资产和其他长期资产所收回的现金净额".equals(cashFlowProjectBO.getProjectName())) {
                bo15 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("收到的其他与投资活动有关的现金".equals(cashFlowProjectBO.getProjectName())) {
                bo16 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("二、现金流入小计".equals(cashFlowProjectBO.getProjectName())) {
                bo17 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("购建固定资产、无形资产和其他长期资产所支付的现金".equals(cashFlowProjectBO.getProjectName())) {
                bo18 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("投资所支付的现金".equals(cashFlowProjectBO.getProjectName())) {
                bo19 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("支付的其他与投资活动有关的现金".equals(cashFlowProjectBO.getProjectName())) {
                bo20 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("二、现金流出小计".equals(cashFlowProjectBO.getProjectName())) {
                bo21 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("投资活动产生的现金流量净额".equals(cashFlowProjectBO.getProjectName())) {
                bo22 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("三、筹资活动产生的现金流量".equals(cashFlowProjectBO.getProjectName())) {
                bo23 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("吸收投资所收到的现金".equals(cashFlowProjectBO.getProjectName())) {
                bo24 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("取得借款所收到的现金".equals(cashFlowProjectBO.getProjectName())) {
                bo25 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("收到的其他与筹资活动有关的现金".equals(cashFlowProjectBO.getProjectName())) {
                bo26 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("三、现金流入小计".equals(cashFlowProjectBO.getProjectName())) {
                bo27 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("偿还债务所支付的现金".equals(cashFlowProjectBO.getProjectName())) {
                bo28 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("分配股利、利润和偿付利息所支付的现金".equals(cashFlowProjectBO.getProjectName())) {
                bo29 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("支付的其他与筹资活动有关的现金".equals(cashFlowProjectBO.getProjectName())) {
                bo30 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("三、现金流出小计".equals(cashFlowProjectBO.getProjectName())) {
                bo31 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("筹资活动产生的现金流量净额".equals(cashFlowProjectBO.getProjectName())) {
                bo32 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("四、汇率变动对现金的影响".equals(cashFlowProjectBO.getProjectName())) {
                bo33 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
            if ("五、现金及现金等价物净增加额".equals(cashFlowProjectBO.getProjectName())) {
                bo34 = new CashFlowProjectBO(cashFlowProjectBO.getProjectName(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
            }
        }
        if (null != bo1.getProjectName()) {
            bos.add(bo1);
        }
        if (null != bo2.getProjectName()) {
            bos.add(bo2);
        }
        if (null != bo3.getProjectName()) {
            bos.add(bo3);
        }
        if (null != bo4.getProjectName()) {
            bos.add(bo4);
        }
        if (null != bo5.getProjectName()) {
            bos.add(bo5);
        }
        if (null != bo6.getProjectName()) {
            bos.add(bo6);
        }
        if (null != bo7.getProjectName()) {
            bos.add(bo7);
        }
        if (null != bo8.getProjectName()) {
            bos.add(bo8);
        }
        if (null != bo9.getProjectName()) {
            bos.add(bo9);
        }
        if (null != bo10.getProjectName()) {
            bos.add(bo10);
        }
        if (null != bo11.getProjectName()) {
            bos.add(bo11);
        }
        if (null != bo12.getProjectName()) {
            bos.add(bo12);
        }
        if (null != bo13.getProjectName()) {
            bos.add(bo13);
        }
        if (null != bo14.getProjectName()) {
            bos.add(bo14);
        }
        if (null != bo15.getProjectName()) {
            bos.add(bo15);
        }
        if (null != bo16.getProjectName()) {
            bos.add(bo16);
        }
        if (null != bo17.getProjectName()) {
            bos.add(bo17);
        }
        if (null != bo18.getProjectName()) {
            bos.add(bo18);
        }

        if (null != bo19.getProjectName()) {
            bos.add(bo19);
        }
        if (null != bo20.getProjectName()) {
            bos.add(bo20);
        }

        if (null != bo21.getProjectName()) {
            bos.add(bo21);
        }

        if (null != bo22.getProjectName()) {
            bos.add(bo22);
        }
        if (null != bo23.getProjectName()) {
            bos.add(bo23);
        }
        if (null != bo24.getProjectName()) {
            bos.add(bo24);
        }
        if (null != bo25.getProjectName()) {
            bos.add(bo25);
        }
        if (null != bo26.getProjectName()) {
            bos.add(bo26);
        }
        if (null != bo27.getProjectName()) {
            bos.add(bo27);
        }
        if (null != bo28.getProjectName()) {
            bos.add(bo28);
        }
        if (null != bo29.getProjectName()) {
            bos.add(bo29);
        }
        if (null != bo30.getProjectName()) {
            bos.add(bo30);
        }
        if (null != bo31.getProjectName()) {
            bos.add(bo31);
        }
        if (null != bo32.getProjectName()) {
            bos.add(bo32);
        }
        if (null != bo33.getProjectName()) {
            bos.add(bo33);
        }

        if (null != bo34.getProjectName()) {
            bos.add(bo34);
        }


        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CashFormulaBO findFormula(String id) throws SerException {
//        CashFlow cashFlow = cashFlowSer.findById(id);
//        if (cashFlow == null) {
//            throw new SerException("该实体不存在");
//        }
//        String projectId = cashFlow.getProjectId();
        CashFormulaDTO cashFormulaDTO = new CashFormulaDTO();
        cashFormulaDTO.getConditions().add(Restrict.eq("projectId", id));
        CashFormula cashFormula = cashFormulaSer.findOne(cashFormulaDTO);
        CashFormulaBO bo = BeanTransform.copyProperties(cashFormula, CashFormulaBO.class);
        return bo;
    }

    @Override
    public Long findTotal(CashFlowProjectDTO dto) throws SerException {
        ProjectDTO projectDTO = new ProjectDTO();
        return projectSer.count(projectDTO) + 3;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReturnCashBO findMoney(CashFlowProjectDTO dto) throws SerException {
        if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
            dto.setStartTime(DateUtil.dateToString(DateUtil.getStartMonth()));
            dto.setEndTime(DateUtil.dateToString(DateUtil.getEndMonth()));
        }
        CashFlowDTO cashFlowDTO = new CashFlowDTO();
        cashFlowDTO.getConditions().add(Restrict.eq("projectId", dto.getProjectId()));
        cashFlowDTO.getConditions().add(Restrict.eq("startTime", dto.getStartTime()));
        cashFlowDTO.getConditions().add(Restrict.eq("endTime", dto.getEndTime()));
        CashFlow cashFlow = cashFlowSer.findOne(cashFlowDTO);
        if (null != cashFlow) {
            ReturnCashBO bo = BeanTransform.copyProperties(cashFlow, ReturnCashBO.class);
            return bo;
        }
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void editMoney(CashFlowProjectDTO dto) throws SerException {
        CashFlowDTO cashFlowDTO = new CashFlowDTO();
        cashFlowDTO.getConditions().add(Restrict.eq("projectId", dto.getProjectId()));
        cashFlowDTO.getConditions().add(Restrict.eq("startTime", dto.getStartTime()));
        cashFlowDTO.getConditions().add(Restrict.eq("endTime", dto.getEndTime()));
        CashFlow cashFlow = cashFlowSer.findOne(cashFlowDTO);
        cashFlow.setMoney(dto.getMoney());
        cashFlow.setModifyTime(LocalDateTime.now());
        cashFlowSer.update(cashFlow);
    }

    @Override
    public List<CashRateBO> findRate(String id) throws SerException {
        List<CashRateBO> bos = new ArrayList<>(0);
        CashRateDTO cashRateDTO = new CashRateDTO();
        cashRateDTO.getConditions().add(Restrict.eq("projectId", id));
        List<CashRate> cashRates = cashRateSer.findByCis(cashRateDTO);
        if (null != cashRates && cashRates.size() > 0) {
            for (CashRate cashRate : cashRates) {
                String formula = cashRate.getFormula() + "(1+" + cashRate.getRate().toString() + "%)中的" + cashRate.getRate().toString() + "%改为:";
                CashRateBO bo = new CashRateBO();
                BeanTransform.copyProperties(cashRate, bo);
                bo.setFormula(formula);
                bos.add(bo);
            }
        } else {
            throw new SerException("公式中无可以修改的比率");
        }
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void editRate(CashRateListTO tos) throws SerException {
        try {
            if (null == tos.getCashRateTOs() || tos.getCashRateTOs().size() < 1) {
                throw new SerException("传入数据不能为空");
            }
            for (CashRateTO to : tos.getCashRateTOs()) {
                CashRate cashRate = cashRateSer.findById(to.getId());
                cashRate.setRate(to.getRate());
                cashRate.setModifyTime(LocalDateTime.now());
                cashRateSer.update(cashRate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据科目查询金额
    public Double findCashByProject(String projectName, CashFlowProjectDTO dto) throws SerException {
        Double cash = 0d;
        if ("销售商品、提供劳务收到的现金".equals(projectName)) {
            cash = findCash(dto);
            return cash;
        } else if ("收到的税费返还".equals(projectName)) {
            cash = findCash1(dto);
            return cash;
        } else if ("收到的其他与经营活动有关的现金".equals(projectName)) {
            cash = findCash2(dto);
            return cash;
        } else if ("一、现金流入小计".equals(projectName)) {
            cash = findCash3(dto);
            return cash;
        } else if ("购买商品接受劳务支付的现金".equals(projectName)) {
            cash = findCash4(dto);
            return cash;
        } else if ("支付给职工以及为职工支付的现金".equals(projectName)) {
            cash = findCash5(dto);
            return cash;
        } else if ("支付的各项税费".equals(projectName)) {
            cash = findCash6(dto);
            return cash;
        } else if ("支付的其他与经营活动有关的现金".equals(projectName)) {
            cash = findCash7(dto);
            return cash;
        } else if ("一、现金流出小计".equals(projectName)) {
            cash = findCash8(dto);
            return cash;
        } else if ("经营活动产生的现金流量净额".equals(projectName)) {
            cash = findCash9(dto);
            return cash;
        } else if ("收回投资所收到的现金".equals(projectName)) {
            cash = findCash10(dto);
            return cash;
        } else if ("取得投资收益所收到的现金".equals(projectName)) {
            cash = findCash11(dto);
            return cash;
        } else if ("处置固定资产、无形资产和其他长期资产所收回的现金净额".equals(projectName)) {
            cash = findCash12(dto);
            return cash;
        } else if ("收到的其他与投资活动有关的现金".equals(projectName)) {
            cash = findCash13(dto);
            return cash;
        } else if ("二、现金流入小计".equals(projectName)) {
            cash = findCash14(dto);
            return cash;
        } else if ("购建固定资产、无形资产和其他长期资产所支付的现金".equals(projectName)) {
            cash = findCash15(dto);
            return cash;
        } else if ("投资所支付的现金".equals(projectName)) {
            cash = findCash16(dto);
            return cash;
        } else if ("支付的其他与投资活动有关的现金".equals(projectName)) {
            cash = findCash17(dto);
            return cash;
        } else if ("二、现金流出小计".equals(projectName)) {
            cash = findCash18(dto);
            return cash;
        } else if ("投资活动产生的现金流量净额".equals(projectName)) {
            cash = findCash19(dto);
            return cash;
        } else if ("吸收投资所收到的现金".equals(projectName)) {
            cash = findCash20(dto);
            return cash;
        } else if ("借款所收到的现金".equals(projectName)) {
            cash = findCash21(dto);
            return cash;
        } else if ("收到的其他与筹资活动有关的现金".equals(projectName)) {
            cash = findCash22(dto);
            return cash;
        } else if ("三、现金流入小计".equals(projectName)) {
            cash = findCash23(dto);
            return cash;
        } else if ("偿还债务所支付的现金".equals(projectName)) {
            cash = findCash24(dto);
            return cash;
        } else if ("分配股利、利润或偿付利息所支付的现金".equals(projectName)) {
            cash = findCash25(dto);
            return cash;
        } else if ("支付的其他与筹资活动有关的现金".equals(projectName)) {
            cash = findCash26(dto);
            return cash;
        } else if ("三、现金流出小计".equals(projectName)) {
            cash = findCash27(dto);
            return cash;
        } else if ("筹资活动产生的现金流量净额".equals(projectName)) {
            cash = findCash28(dto);
            return cash;
        } else if ("四、汇率变动对现金的影响".equals(projectName)) {
            cash = findCash29(dto);
            return cash;
        } else if ("五、现金及现金等价物净增加额".equals(projectName)) {
            cash = findCash30(dto);
            return cash;
        }
        return cash;
    }

    //查询销售商品、提供劳务收到的现金
    private Double findCash(CashFlowProjectDTO dto) throws SerException {
        //利润表中主营业务收入(本年累计数)*(1+6%)   比率可更改
        Double cash1 = 0d;
        SubjectCollectBO subjectCollectBO1 = voucherGenerateAPI.findCurrentAndYear("营业收入", dto.getStartTime(), dto.getEndTime());
        if (null != subjectCollectBO1) {
            cash1 = subjectCollectBO1.getYearAmount();
            cash1 = cash1 == null ? 0d : cash1;
        }
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.getConditions().add(Restrict.eq("projectName", "销售商品、提供劳务收到的现金"));
        Project project = projectSer.findOne(projectDTO);
        String projectId = "";
        if (null != project) {
            projectId = project.getId();
        }
        CashRateDTO cashRateDTO = new CashRateDTO();
        cashRateDTO.getConditions().add(Restrict.eq("projectId", projectId));
        cashRateDTO.getConditions().add(Restrict.eq("formula", "利润表中主营业务收入*"));
        CashRate cashRate = cashRateSer.findOne(cashRateDTO);
        if (null == cashRate) {
            cashRate = new CashRate();
            cashRate.setFormula("利润表中主营业务收入*");
            cashRate.setProjectId(projectId);
            cashRate.setRate(6d);
            cashRateSer.save(cashRate);
        }
        cash1 = cash1 * (1 + cashRate.getRate() / 100);

        //其他业务收入(记账凭证~贷方-借方(本年累计))（剔除税金）/(1+X%)
        Double cash2 = 0d;
        SubjectCollectDTO subjectCollectDTO = new SubjectCollectDTO();
        subjectCollectDTO.setFirstSubject("其他业务收入");
        cash2 = voucherGenerateAPI.getCurrent(subjectCollectDTO, dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), false);
        cash2 = cash2 == null ? 0d : cash2;
        CashRateDTO cashRateDTO1 = new CashRateDTO();
        cashRateDTO1.getConditions().add(Restrict.eq("projectId", projectId));
        cashRateDTO1.getConditions().add(Restrict.eq("formula", "其他业务收入/"));
        CashRate cashRate1 = cashRateSer.findOne(cashRateDTO1);
        if (null == cashRate1) {
            cashRate1 = new CashRate();
            cashRate1.setFormula("其他业务收入/");
            cashRate1.setProjectId(projectId);
            cashRate1.setRate(0d);
            cashRateSer.save(cashRate1);
        }
        cash2 = cash2 / (1 + cashRate1.getRate() / 100);
        //应收票据期初余额-应收票据期末余额
        Double cash3 = 0d;
        SubjectCollectDTO subjectCollectDTO1 = new SubjectCollectDTO();
        subjectCollectDTO1.setFirstSubject("应收票据");
        SubjectCollectBO subjectCollectBO = voucherGenerateAPI.getSum(subjectCollectDTO1,dto.getStartTime(), dto.getEndTime(), true);
        if (null != subjectCollectBO) {
            cash3 = subjectCollectBO.getBeginAmount() - subjectCollectBO.getEndAmount();
        }

        //应收账款期初余额-应收账款期末余额
        Double cash4 = 0d;
        cash4 = findAssetNum("应收账款", dto.getEndTime(), true) - findAssetNum("应收账款", dto.getEndTime(), false);

        //预收账款期末余额-预收账款期初余额
        Double cash5 = 0d;
        cash5 = findAssetNum("预收账款", dto.getEndTime(), false) - findAssetNum("预收账款", dto.getEndTime(), true);

        //计提的应收账款坏账准备期末余额
        Double cash6 = 0d;
        cash6 = findAssetNum("应收账款坏账准备", dto.getEndTime(), false);

        //存入公式
        ProjectDTO proDTO = new ProjectDTO();
        proDTO.getConditions().add(Restrict.eq("projectName", "查询销售商品、提供劳务收到的现金"));
        proDTO.getConditions().add(Restrict.eq("projectType", ProjectType.MANAGEMENT));
        Project project1 = projectSer.findOne(proDTO);
        if (null != project1) {
            CashFormulaDTO cashFormulaDTO = new CashFormulaDTO();
            cashFormulaDTO.getConditions().add(Restrict.eq("projectId", project1.getId()));
            cashFormulaDTO.getConditions().add(Restrict.eq("projectName", project1.getProjectName()));
            CashFormula cashFormula = cashFormulaSer.findOne(cashFormulaDTO);
            if (null == cashFormula) {
                cashFormula = new CashFormula();
                cashFormula.setProjectId(project1.getId());
                cashFormula.setProjectName(project1.getProjectName());

                CashRateDTO cashRateDTO2 = new CashRateDTO();
                cashRateDTO2.getConditions().add(Restrict.eq("projectId", project1.getId()));
//                cashRateDTO2.getConditions().add(Restrict.eq("formula", "利润表中主营业务收入*"));
                List<CashRate> cashRates = cashRateSer.findByCis(cashRateDTO2);
                if (null != cashRates && cashRates.size() > 0) {
                    String rate1 = cashRates.stream().filter(obj -> "利润表中主营业务收入*".equals(obj.getFormula())).map(CashRate::getRate).collect(Collectors.toList()).get(0).toString();
                    String rate2 = cashRates.stream().filter(obj -> "其他业务收入/".equals(obj.getFormula())).map(CashRate::getRate).collect(Collectors.toList()).get(0).toString();

                    String formula = "利润表中主营业务收入×(1+" + rate1 + "%)+其他业务收入/(1+" + rate2 + "%)+(应收票据期初余额-应收票据期末余额)+(应收账款期初余额-应收账款期末余额)+(预收账款期末余额-预收账款期初余额)-计提的应收账款坏账准备期末余额";
                    cashFormula.setForm(formula);
                    cashFormulaSer.save(cashFormula);
                }
            }
        }

        //利润表中主营业务收入(本年累计数)×(1+6%)+其他业务收入(记账凭证~贷方-借方(本年累计))（剔除税金）/(1+X%)+(应收票据期初余额-应收票据期末余额)+(应收账款期初余额-应收账款期末余额)+(预收账款期末余额-预收账款期初余额)-计提的应收账款坏账准备期末余额
        return cash1 + cash2 + cash3 + cash4 + cash5 - cash6;
    }

    //收到的税费返还
    private Double findCash1(CashFlowProjectDTO dto) throws SerException {
        //（应收补贴款期初余额－应收补贴款期末余额）
        Double cash1 = 0d;
        cash1 = findAssetNum("应收补贴款", dto.getEndTime(), true) - findAssetNum("应收补贴款", dto.getEndTime(), false);

        //补贴收入(记账凭证~贷方-借方(本年累计))
        Double cash2 = 0d;
        SubjectCollectDTO subjectCollectDTO = new SubjectCollectDTO();
        subjectCollectDTO.setFirstSubject("补贴收入");
        cash2 = voucherGenerateAPI.getCurrent(subjectCollectDTO, dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), false);

        //所得税本期贷方发生额累计数
        Double cash3 = 0d;
        SubjectCollectDTO subjectCollectDTO1 = new SubjectCollectDTO();
        subjectCollectDTO1.setFirstSubject("所得税");
        cash3 = voucherGenerateAPI.getCurrent(subjectCollectDTO1, dto.getStartTime(), dto.getEndTime(), false);

        //存入公式
        saveFormula("收到的税费返还", ProjectType.MANAGEMENT, "（应收补贴款期初余额－应收补贴款期末余额）＋补贴收入＋所得税本期贷方发生额累计数");

        return cash1 + cash2 + cash3;
    }

    //收到的其他与经营活动有关的现金
    private Double findCash2(CashFlowProjectDTO dto) throws SerException {

        //补充资料中“经营活动产生的现金流量净额”－{（1+3）－(10+12+13+18) }   注意：里面的数字指的是行次
        Double cash1 = 0d;
        SubjectCollectBO subjectCollectBO = voucherGenerateAPI.findCurrentAndYear("净利润", dto.getStartTime(), dto.getEndTime());
        if (null != subjectCollectBO) {
            cash1 = subjectCollectBO.getYearAmount();
        }

        Double cash2 = 0d;
        cash2 = findCash(dto) + findCash1(dto);

        Double cash3 = 0d;
        cash3 = findCash4(dto) + findCash5(dto) + findCash6(dto) + findCash7(dto);

        //存入公式
        saveFormula("收到的其他与经营活动有关的现金", ProjectType.MANAGEMENT, "补充资料中“经营活动产生的现金流量净额”－{（销售商品、提供劳务收到的现金+收到的税费返还）－( 购买商品、接受劳务支付的现金+支付给职工以及为职工支付的现金+ 支付的各项税费+支付的其他与经营活动有关的现金) }");

        return cash1 - cash2 + cash3;
    }

    //现金流入小计
    private Double findCash3(CashFlowProjectDTO dto) throws SerException {
        //存入公式
        saveFormula("一、现金流入小计", ProjectType.MANAGEMENT, "销售商品、提供劳务收到的现金 + 收到的税费返还 + 收到的其他与经营活动有关的现金");

        return findCash(dto) + findCash1(dto) + findCash2(dto);
    }

    //购买商品、接受劳务支付的现金
    private Double findCash4(CashFlowProjectDTO dto) throws SerException {

        //利润表中主营业务成本
        Double cash1 = 0d;
        SubjectCollectBO subjectCollectBO1 = voucherGenerateAPI.findCurrentAndYear("营业收入", dto.getStartTime(), dto.getEndTime());
        if (null != subjectCollectBO1) {
            cash1 = subjectCollectBO1.getYearAmount();
        }

        //（存货期末余额－存货期初余额）
        Double cash2 = 0d;
        cash2 = findAssetNum("存货", dto.getEndTime(), false) - findAssetNum("存货", dto.getEndTime(), true);

        //〔利润表中主营业务成本＋（存货期末余额－存货期初余额）〕×（1＋17%）
        Double cash3 = 0d;
        CashRate cashRate = findCashRate("购买商品、接受劳务支付的现金", "〔利润表中主营业务成本＋（存货期末余额－存货期初余额）〕*", 17d);
        Double rate = 0d;
        if (null != cashRate) {
            rate = cashRate.getRate();
        }
        cash3 = (cash1 + cash2) * (1 + rate / 100);

        //其他业务支出(记账凭证~借方-贷方(本年累计))（剔除税金）/(1+X%)
        Double cash4 = 0d;
        SubjectCollectBO subjectCollectBO2 = voucherGenerateAPI.findCurrentAndYear("其他业务支出", dto.getStartTime(), dto.getEndTime());
        if (null != subjectCollectBO2) {
            cash4 = subjectCollectBO2.getYearAmount();
        }
        CashRate cashRate1 = findCashRate("购买商品、接受劳务支付的现金", "其他业务支出/", 0d);
        Double rate1 = 0d;
        if (null != cashRate1) {
            rate1 = cashRate.getRate();
        }
        cash4 = cash4 / (1 + rate1 / 100);

        //（应付票据期初余额－应付票据期末余额）
        Double cash5 = 0d;
        cash5 = findAssetNum("应付票据", dto.getEndTime(), true) - findAssetNum("应付票据", dto.getEndTime(), false);

        //（应付账款期初余额－应付账款期末余额）
        Double cash6 = 0d;
        cash6 = findAssetNum("应付账款", dto.getEndTime(), true) - findAssetNum("应付账款", dto.getEndTime(), false);

        //（预付账款期末余额－预付账款期初余额）
        Double cash7 = 0d;
        cash7 = findAssetNum("预付账款", dto.getEndTime(), false) - findAssetNum("预付账款", dto.getEndTime(), true);


        //存入公式
        ProjectDTO proDTO = new ProjectDTO();
        proDTO.getConditions().add(Restrict.eq("projectName", "购买商品、接受劳务支付的现金"));
        proDTO.getConditions().add(Restrict.eq("projectType", ProjectType.MANAGEMENT));
        Project project1 = projectSer.findOne(proDTO);
        if (null != project1) {
            CashFormulaDTO cashFormulaDTO = new CashFormulaDTO();
            cashFormulaDTO.getConditions().add(Restrict.eq("projectId", project1.getId()));
            cashFormulaDTO.getConditions().add(Restrict.eq("projectName", project1.getProjectName()));
            CashFormula cashFormula = cashFormulaSer.findOne(cashFormulaDTO);
            if (null == cashFormula) {
                cashFormula = new CashFormula();
                cashFormula.setProjectId(project1.getId());
                cashFormula.setProjectName(project1.getProjectName());

                CashRateDTO cashRateDTO2 = new CashRateDTO();
                cashRateDTO2.getConditions().add(Restrict.eq("projectId", project1.getId()));
                List<CashRate> cashRates = cashRateSer.findByCis(cashRateDTO2);
                if (null != cashRates && cashRates.size() > 0) {
                    String rate3 = cashRates.stream().filter(obj -> "〔利润表中主营业务成本＋（存货期末余额－存货期初余额）〕*".equals(obj.getFormula())).map(CashRate::getRate).collect(Collectors.toList()).get(0).toString();
                    String rate4 = cashRates.stream().filter(obj -> "其他业务支出/".equals(obj.getFormula())).map(CashRate::getRate).collect(Collectors.toList()).get(0).toString();

                    String formula = "〔利润表中主营业务成本＋（存货期末余额－存货期初余额）〕×（1＋" + rate3 + "%）＋其他业务支出/(1+" + rate4 + "%)＋（应付票据期初余额－应付票据期末余额）＋（应付账款期初余额－应付账款期末余额）＋（预付账款期末余额－预付账款期初余额）";
                    cashFormula.setForm(formula);
                    cashFormulaSer.save(cashFormula);
                }
            }
        }


        return cash3 + cash4 + cash5 + cash6 + cash7;
    }

    // 支付给职工以及为职工支付的现金
    private Double findCash5(CashFlowProjectDTO dto) throws SerException {
        //“应付工资”科目本期借方发生额累计数
        Double cash1 = 0d;
        SubjectCollectBO subjectCollectBO1 = voucherGenerateAPI.findCurrentAndYear("应付工资", dto.getStartTime(), dto.getEndTime());
        if (null != subjectCollectBO1) {
            cash1 = subjectCollectBO1.getCurrentAmount();
        }

        //“应付福利费”科目本期借方发生额累计数
        Double cash2 = 0d;
        SubjectCollectBO subjectCollectBO2 = voucherGenerateAPI.findCurrentAndYear("应付福利费", dto.getStartTime(), dto.getEndTime());
        if (null != subjectCollectBO2) {
            cash2 = subjectCollectBO2.getCurrentAmount();
        }

        //管理费用中“社保费”
        Double cash3 = 0d;
        cash3 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("社保费"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        //住房公积金
        Double cash4 = 0d;
        cash4 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("住房公积金"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        //残保金
        Double cash5 = 0d;
        cash5 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("残保金"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        //工会经费
        Double cash6 = 0d;
        cash6 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("工会经费"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        //成本及制造费用明细表中的“劳动保护费”
        Double cash7 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("劳动保护费"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        //职工保险
        Double cash8 = 0d;
        cash8 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("职工保险"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        //职工培训费
        Double cash9 = 0d;
        cash9 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("职工培训费"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        //存入公式
        saveFormula("支付给职工以及为职工支付的现金", ProjectType.MANAGEMENT, "“应付工资”科目本期借方发生额累计数＋“应付福利费”科目本期借方发生额累计数＋管理费用中“社保费”、“住房公积金”、“残保金”、“工会经费”＋成本及制造费用明细表中的“劳动保护费”+职工保险+职工培训费等");

        return cash1 + cash2 + cash3 + cash4 + cash5 + cash6 + cash7 + cash8 + cash9;
    }

    // 支付的各项税费
    private Double findCash6(CashFlowProjectDTO dto) throws SerException {
        //“应交税金”各明细账户本期借方发生额累计数
        Double cash1 = 0d;
        SubjectCollectBO subjectCollectBO1 = voucherGenerateAPI.findCurrentAndYear("应交税金", dto.getStartTime(), dto.getEndTime());
        if (null != subjectCollectBO1) {
            cash1 = subjectCollectBO1.getCurrentAmount();
        }

        //“其他应交款”各明细账户借方数
        Double cash2 = 0d;
        SubjectCollectBO subjectCollectBO2 = voucherGenerateAPI.findCurrentAndYear("其他应交款", dto.getStartTime(), dto.getEndTime());
        if (null != subjectCollectBO2) {
            cash2 = subjectCollectBO2.getCurrentAmount();
        }

        //“管理费用”中“税金”本期借方发生额累计数
        Double cash3 = 0d;
        SubjectCollectBO subjectCollectBO3 = voucherGenerateAPI.findCurrentAndYear("税金", dto.getStartTime(), dto.getEndTime());
        if (null != subjectCollectBO3) {
            cash3 = subjectCollectBO3.getCurrentAmount();
        }

        //“其他业务支出”中有关税金项目
        Double cash4 = 0d;
        // TODO: 17-11-21

        //存入公式
        saveFormula("支付的各项税费", ProjectType.MANAGEMENT, "“应交税金”各明细账户本期借方发生额累计数＋“其他应交款”各明细账户借方数＋“管理费用”中“税金”本期借方发生额累计数＋“其他业务支出”中有关税金项目 ");

        return cash1 + cash2 + cash3 + cash4;
    }

    //支付的其他与经营活动有关的现金
    private Double findCash7(CashFlowProjectDTO dto) throws SerException {
        //营业外支出（剔除固定资产处置损失）(本年累计)
        Double cash1 = 0d;
        cash1 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("营业外支出"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("固定资产处置损失"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        //管理费用(剔除工资、福利费、劳动保险金、待业保险金、住房公积金、养老保险、医疗保险、折旧、坏账准备或坏账损失、列入的各项税金等)(本年累计)
        Double cash2 = 0d;
        cash2 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("管理费用"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("工资"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("福利费"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("劳动保险金"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("待业保险金"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("住房公积金"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("养老保险"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("医疗保险"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("折旧"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("坏账准备或坏账损失"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("列入的各项税金"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        //营业费用
        Double cash3 = 0d;
        cash3 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("营业费用"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        //成本及制造费用(剔除工资、福利费、劳动保险金、待业保险金、住房公积金、养老保险、医疗保险等)(本年累计)
        Double cash4 = 0d;
        cash4 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("成本及制造费用"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("工资"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("福利费"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("劳动保险金"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("待业保险金"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("住房公积金"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("养老保险"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true) -
                voucherGenerateAPI.getCurrent(new SubjectCollectDTO("医疗保险"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        //其他应收款本期借方发生额
        Double cash5 = 0d;
        cash5 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("其他应收款"), dto.getStartTime(), dto.getEndTime(), true);

        //其他应付中有关税金项目
        Double cash6 = 0d;
        // TODO: 17-11-21

        //存入公式
        saveFormula("支付的其他与经营活动有关的现金", ProjectType.MANAGEMENT, "营业外支出（剔除固定资产处置损失）(本年累计)＋管理费用(剔除工资、福利费、劳动保险金、待业保险金、住房公积金、养老保险、医疗保险、折旧、坏账准备或坏账损失、列入的各项税金等)(本年累计)＋营业费用、成本及制造费用(剔除工资、福利费、劳动保险金、待业保险金、住房公积金、养老保险、医疗保险等)(本年累计)＋其他应收款本期借方发生额＋其他应付中有关税金项目（备注：剔除的数说明不能出现二次）");

        return cash1 + cash2 + cash3 + cash4 + cash5 + cash6;
    }

    //现金流出小计
    private Double findCash8(CashFlowProjectDTO dto) throws SerException {
        //存入公式
        saveFormula("一、现金流出小计", ProjectType.MANAGEMENT, "购买商品、接受劳务支付的现金 + 支付给职工以及为职工支付的现金 + 支付的各项税费 + 支付的其他与经营活动有关的现金");

        return findCash4(dto) + findCash5(dto) + findCash6(dto) + findCash7(dto);
    }

    //经营活动产生的现金流量净额
    private Double findCash9(CashFlowProjectDTO dto) throws SerException {
        //存入公式
        saveFormula("经营活动产生的现金流量净额", ProjectType.MANAGEMENT, "现金流入小计 - 现金流出小计");
        return findCash3(dto) - findCash8(dto);
    }

    //收回投资所收到的现金
    private Double findCash10(CashFlowProjectDTO dto) throws SerException {
        //(短期投资期初数－短期投资期末数）
        Double cash1 = 0d;
        cash1 = findAssetNum("短期投资", dto.getEndTime(), true) - findAssetNum("短期投资", dto.getEndTime(), false);

        //（长期股权投资期初数－长期股权投资期末数）
        Double cash2 = 0d;
        cash2 = findAssetNum("长期股权投资", dto.getEndTime(), true) - findAssetNum("长期股权投资", dto.getEndTime(), false);

        //（长期债权投资期初数－长期债权投资期末数）
        Double cash3 = 0d;
        cash3 = findAssetNum("长期债权投资", dto.getEndTime(), true) - findAssetNum("长期债权投资", dto.getEndTime(), false);

        //存入公式
        saveFormula("收回投资所收到的现金", ProjectType.INVESTMENT, "（短期投资期初数－短期投资期末数）＋（长期股权投资期初数－长期股权投资期末数）＋（长期债权投资期初数－长期债权投资期末数）");

        return cash1 + cash2 + cash3;
    }

    //取得投资收益所收到的现金
    private Double findCash11(CashFlowProjectDTO dto) throws SerException {
        //利润表投资收益
        Double cash1 = 0d;
        SubjectCollectBO subjectCollectBO1 = voucherGenerateAPI.findCurrentAndYear("投资收益", dto.getStartTime(), dto.getEndTime());
        if (null != subjectCollectBO1) {
            cash1 = subjectCollectBO1.getCurrentAmount();
        }

        //（应收利息期末数－应收利息期初数）
        Double cash2 = 0d;
        cash2 = findAssetNum("应收利息", dto.getEndTime(), false) - findAssetNum("应收利息", dto.getEndTime(), true);

        //（应收股利期末数－应收股利期初数）
        Double cash3 = 0d;
        cash3 = findAssetNum("应收股利", dto.getEndTime(), false) - findAssetNum("应收股利", dto.getEndTime(), true);

        //存入公式
        saveFormula("取得投资收益所收到的现金", ProjectType.INVESTMENT, "利润表投资收益－（应收利息期末数－应收利息期初数）－（应收股利期末数－应收股利期初数）");

        return cash1 - cash2 - cash3;
    }

    //处置固定资产、无形资产和其他长期资产所收回的现金净额
    private Double findCash12(CashFlowProjectDTO dto) throws SerException {
        //“固定资产清理”的贷方余额(记账凭证)(贷方-借方本年累计)
        Double cash1 = 0d;
        cash1 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("固定资产清理"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), false);

        //(无形资产期末数－无形资产期初数）
        Double cash2 = 0d;
        cash2 = findAssetNum("无形资产", dto.getEndTime(), false) - findAssetNum("无形资产", dto.getEndTime(), true);

        //（其他长期资产期末数－其他长期资产期初数）
        Double cash3 = 0d;
        cash3 = findAssetNum("其他长期资产", dto.getEndTime(), false) - findAssetNum("其他长期资产", dto.getEndTime(), true);

        //存入公式
        saveFormula("处置固定资产、无形资产和其他长期资产所收回的现金净额", ProjectType.INVESTMENT, "“固定资产清理”的贷方余额＋（无形资产期末数－无形资产期初数）＋（其他长期资产期末数－其他长期资产期初数）");

        return cash1 + cash2 + cash3;
    }

    //收到的其他与投资活动有关的现金
    private Double findCash13(CashFlowProjectDTO dto) throws SerException {

        //存入公式
        saveFormula("收到的其他与投资活动有关的现金", ProjectType.INVESTMENT, "如收回融资租赁设备本金等。");

        //如收回融资租赁设备本金等
        return 0d;
    }

    //现金流入小计
    private Double findCash14(CashFlowProjectDTO dto) throws SerException {
        //存入公式
        saveFormula("二、现金流入小计", ProjectType.INVESTMENT, " 收回投资所收到的现金 +  取得投资收益所收到的现金 + 处置固定资产、无形资产和其他长期资产所收回的现金净额 + 收到的其他与投资活动有关的现金");

        return findCash10(dto) + findCash11(dto) + findCash12(dto) + findCash13(dto);
    }

    // 购建固定资产、无形资产和其他长期资产所支付的现金
    private Double findCash15(CashFlowProjectDTO dto) throws SerException {
        //（在建工程期末数－在建工程期初数）
        Double cash1 = 0d;
        cash1 = findAssetNum("在建工程", dto.getEndTime(), false) - findAssetNum("在建工程", dto.getEndTime(), true);

        //（固定资产期末数－固定资产期初数）
        Double cash2 = 0d;
        cash2 = findAssetNum("固定资产", dto.getEndTime(), false) - findAssetNum("固定资产", dto.getEndTime(), true);

        //无形资产期末数－无形资产期初数）
        Double cash3 = 0d;
        cash3 = findAssetNum("无形资产", dto.getEndTime(), false) - findAssetNum("无形资产", dto.getEndTime(), true);

        //（其他长期资产期末数－其他长期资产期初数）
        Double cash4 = 0d;
        cash4 = findAssetNum("其他长期资产", dto.getEndTime(), false) - findAssetNum("其他长期资产", dto.getEndTime(), true);

        //存入公式
        saveFormula("购建固定资产、无形资产和其他长期资产所支付的现金", ProjectType.INVESTMENT, " （在建工程期末数－在建工程期初数）＋（固定资产期末数－固定资产期初数）＋（无形资产期末数－无形资产期初数）＋（其他长期资产期末数－其他长期资产期初数）     （备注：如期末数小于期初数，则在处置固定资产、无形资产和其他长期资产所收回的现金净额项目中核算，这里不用核算）");

        if (cash1 < 0 || cash2 < 0 || cash3 < 0 || cash4 < 0) {
            return 0d;
        }

        return cash1 + cash2 + cash3 + cash4;
    }

    //投资所支付的现金
    private Double findCash16(CashFlowProjectDTO dto) throws SerException {
        //（短期投资期末数－短期投资期初数）
        Double cash1 = 0d;
        cash1 = findAssetNum("短期投资", dto.getEndTime(), false) - findAssetNum("短期投资", dto.getEndTime(), true);

        //（长期股权投资期末数－长期股权投资期初数）（剔除投资收益或损失）
        Double cash2 = 0d;
        cash2 = findAssetNum("长期股权投资", dto.getEndTime(), false) - findAssetNum("长期股权投资", dto.getEndTime(), true);

        Double cash3 = 0d;
        cash3 = findAssetNum("投资收益或损失", dto.getEndTime(), false) - findAssetNum("投资收益或损失", dto.getEndTime(), true);

        //（长期债权投资期末数－长期债权投资期初数）
        Double cash4 = 0d;
        cash4 = findAssetNum("长期债权投资", dto.getEndTime(), false) - findAssetNum("长期债权投资", dto.getEndTime(), true);

        //存入公式
        saveFormula("投资所支付的现金", ProjectType.INVESTMENT, " （短期投资期末数－短期投资期初数）＋（长期股权投资期末数－长期股权投资期初数）（剔除投资收益或损失）＋（长期债权投资期末数－长期债权投资期初数）(投资收益来源与利润表)（备注：如期末数小于期初数，则在收回投资所收到的现金项目中核算，这里不用核算）");

        //（备注：如期末数小于期初数，则在收回投资所收到的现金项目中核算，这里不用核算）
        if (cash1 < 0 || cash2 < 0 || cash3 < 0 || cash4 < 0) {
            return 0d;
        }
        return cash1 + (cash2 - cash3) + cash4;

    }

    //支付的其他与投资活动有关的现金
    private Double findCash17(CashFlowProjectDTO dto) throws SerException {
        //存入公式
        saveFormula("支付的其他与投资活动有关的现金", ProjectType.INVESTMENT, "如投资未按期到位罚款");

        //如投资未按期到位罚款。
        return 0d;
    }

    //现金流出小计
    private Double findCash18(CashFlowProjectDTO dto) throws SerException {
        //存入公式
        saveFormula("二、现金流出小计", ProjectType.INVESTMENT, " 购建固定资产、无形资产和其他长期资产所支付的现金 + 投资所支付的现金 + 支付的其他与投资活动有关的现金");
        return findCash15(dto) + findCash16(dto) + findCash17(dto);
    }

    //投资活动产生的现金流量净额
    private Double findCash19(CashFlowProjectDTO dto) throws SerException {
        //存入公式
        saveFormula("投资活动产生的现金流量净额", ProjectType.INVESTMENT, "现金流入小计 - 现金流出小计");

        return findCash18(dto) - findCash14(dto);
    }

    //吸收投资所收到的现金
    private Double findCash20(CashFlowProjectDTO dto) throws SerException {
        //（实收资本期末数－实收资本期初数）
        Double cash1 = 0d;
        cash1 = findAssetNum("实收资本", dto.getEndTime(), false) - findAssetNum("实收资本", dto.getEndTime(), true);

        //（应付债券期末数－应付债券期初数）
        Double cash2 = 0d;
        cash2 = findAssetNum("应付债券", dto.getEndTime(), false) - findAssetNum("应付债券", dto.getEndTime(), true);

        //存入公式
        saveFormula("吸收投资所收到的现金", ProjectType.FINANCING, "（实收资本期末数－实收资本期初数）＋（应付债券期末数－应付债券期初数）");

        return cash1 + cash2;
    }

    //借款所收到的现金
    private Double findCash21(CashFlowProjectDTO dto) throws SerException {
        //（短期借款期末数－短期借款期初数）
        Double cash1 = 0d;
        cash1 = findAssetNum("短期借款", dto.getEndTime(), false) - findAssetNum("短期借款", dto.getEndTime(), true);

        //（长期借款期末数－长期借款期初数）
        Double cash2 = 0d;
        cash2 = findAssetNum("长期借款", dto.getEndTime(), false) - findAssetNum("长期借款", dto.getEndTime(), true);

        //存入公式
        saveFormula("借款所收到的现金", ProjectType.FINANCING, "（短期借款期末数－短期借款期初数）＋（长期借款期末数－长期借款期初数）");

        return cash1 + cash2;
    }

    // 收到的其他与筹资活动有关的现金
    private Double findCash22(CashFlowProjectDTO dto) throws SerException {
        //存入公式
        saveFormula("收到的其他与筹资活动有关的现金", ProjectType.FINANCING, " 如投资人未按期缴纳股权的罚款现金收入等。");

        //  如投资人未按期缴纳股权的罚款现金收入等。
        return 0d;
    }

    // 现金流入小计
    private Double findCash23(CashFlowProjectDTO dto) throws SerException {
        //存入公式
        saveFormula("三、现金流入小计", ProjectType.FINANCING, "吸收投资所收到的现金 + 借款所收到的现金 + 收到的其他与筹资活动有关的现金");

        return findCash20(dto) + findCash21(dto) + findCash22(dto);
    }

    //偿还债务所支付的现金
    private Double findCash24(CashFlowProjectDTO dto) throws SerException {
        //（短期借款期初数－短期借款期末数）
        Double cash1 = 0d;
        cash1 = findAssetNum("短期借款", dto.getEndTime(), true) - findAssetNum("短期借款", dto.getEndTime(), false);

        //（长期借款期初数－长期借款期末数）
        Double cash2 = 0d;
        cash2 = findAssetNum("长期借款", dto.getEndTime(), true) - findAssetNum("长期借款", dto.getEndTime(), false);

        //（应付债券期初数－应付债券期末数）
        Double cash3 = 0d;
        cash3 = findAssetNum("应付债券", dto.getEndTime(), true) - findAssetNum("应付债券", dto.getEndTime(), false);

        //存入公式
        saveFormula("偿还债务所支付的现金", ProjectType.FINANCING, "（短期借款期初数－短期借款期末数）＋（长期借款期初数－长期借款期末数）＋（应付债券期初数－应付债券期末数）");

        return cash1 + cash2 + cash3;
    }

    //分配股利、利润或偿付利息所支付的现金
    private Double findCash25(CashFlowProjectDTO dto) throws SerException {
        //存入公式
        saveFormula("分配股利、利润或偿付利息所支付的现金", ProjectType.FINANCING, "应付股利借方发生额＋利息支出（财务费用下二级科目：财务费用~利息支出＋长期借款利息＋在建工程利息＋应付债券利息~预提费用中“计提利息”贷方余额－票据贴现利息支出");

        return 0d;
    }

    //支付的其他与筹资活动有关的现金
    private Double findCash26(CashFlowProjectDTO dto) throws SerException {
        //存入公式
        saveFormula("支付的其他与筹资活动有关的现金", ProjectType.FINANCING, "  如发生筹资费用所支付的现金、融资租赁所支付的现金、减少注册资本所支付的现金（收购本公司股票，退还联营单位的联营投资等）、企业以分期付款方式购建固定资产，除首期付款支付的现金以外的其他各期所支付的现金等。");

        return 0d;
    }

    //现金流出小计
    private Double findCash27(CashFlowProjectDTO dto) throws SerException {
        //存入公式
        saveFormula("三、现金流出小计", ProjectType.FINANCING, "偿还债务所支付的现金 + 分配股利、利润或偿付利息所支付的现金 + 支付的其他与筹资活动有关的现金");
        return findCash24(dto) + findCash25(dto) + findCash26(dto);
    }

    //筹资活动产生的现金流量净额
    private Double findCash28(CashFlowProjectDTO dto) throws SerException {
        //存入公式
        saveFormula("筹资活动产生的现金流量净额", ProjectType.FINANCING, "现金流入小计 - 现金流出小计");
        return findCash27(dto) - findCash23(dto);
    }

    //四、汇率变动对现金的影响
    private Double findCash29(CashFlowProjectDTO dto) throws SerException {
        //存入公式
//        saveFormula("四、汇率变动对现金的影响", ProjectType.RATE, " ");
        return 0d;
    }

    //五、现金及现金等价物净增加额
    private Double findCash30(CashFlowProjectDTO dto) throws SerException {
        //存入公式
        saveFormula("五、现金及现金等价物净增加额", ProjectType.CASH, "经营活动产生的现金流量净额 + 投资活动产生的现金流量净额 + 筹资活动产生的现金流量净额 + 汇率变动对现金的影响 ");
        return findCash9(dto) + findCash19(dto) + findCash28(dto) + findCash29(dto);
    }

    //获取资产负债表中的期初数和期末数,tar为true,获取期初数
    private Double findAssetNum(String firstSubject, String endTime, Boolean tar) throws SerException {
        SubjectCollectDTO subjectCollectDTO = new SubjectCollectDTO();
        subjectCollectDTO.setFirstSubject(firstSubject);
        SubjectCollectBO subjectCollectBO = voucherGenerateAPI.getSum(subjectCollectDTO, endTime,endTime, true);
        if (null != subjectCollectBO) {
            if (tar) {
                return subjectCollectBO.getBeginAmount();
            } else {
                return subjectCollectBO.getEndAmount();
            }
        }
        return 0d;
    }

    //获取比率表
    private CashRate findCashRate(String projectName, String formula, Double rate) throws SerException {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.getConditions().add(Restrict.eq("projectName", projectName));
        Project project = projectSer.findOne(projectDTO);
        if (null == project) {
            return null;
        }
        String projectId = project.getId();
        CashRateDTO cashRateDTO = new CashRateDTO();
        cashRateDTO.getConditions().add(Restrict.eq("projectId", projectId));
        cashRateDTO.getConditions().add(Restrict.eq("formula", formula));
        CashRate cashRate = cashRateSer.findOne(cashRateDTO);
        if (null == cashRate) {
            cashRate = new CashRate();
            cashRate.setFormula(formula);
            cashRate.setProjectId(projectId);
            cashRate.setRate(rate);
            cashRateSer.save(cashRate);
        }
        return cashRate;
    }

    //得到某个月的第一天
    private String findFirstDay(String time) throws SerException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(time);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(Calendar.DAY_OF_MONTH, 1);
            date = c.getTime();
            return sdf.format(date);
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    //得到某一个月的最后一天
    private String findLastDay(String time) throws SerException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(time);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DAY_OF_MONTH, -1);
            date = c.getTime();
            return sdf.format(date);
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    private Boolean findResult(String time1, String time2) throws SerException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(time1);
            Date date2 = sdf.parse(time2);
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(date1);
            c2.setTime(date2);
            int result = c1.get(Calendar.DAY_OF_MONTH) - c2.get(Calendar.DAY_OF_MONTH);
            if (result <= 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }

    }


    public static void main(String[] fd) {
        String start = DateUtil.dateToString(DateUtil.getEndMonth());
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse("2017-11-12");
            Date date2 = sdf.parse("2017-11-21");
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(date1);
            c2.setTime(date2);
            int rr = c1.get(Calendar.DAY_OF_MONTH) - c2.get(Calendar.DAY_OF_MONTH);
            System.out.println(rr);

        } catch (Exception e) {

        }
    }

    //存入公式
    private void saveFormula(String projectName, ProjectType projectType, String formula) throws SerException {
        //存入公式
        ProjectDTO proDTO = new ProjectDTO();
        proDTO.getConditions().add(Restrict.eq("projectName", projectName));
        proDTO.getConditions().add(Restrict.eq("projectType", projectType));
        Project project1 = projectSer.findOne(proDTO);
        if (null != project1) {
            CashFormulaDTO cashFormulaDTO = new CashFormulaDTO();
            cashFormulaDTO.getConditions().add(Restrict.eq("projectId", project1.getId()));
            cashFormulaDTO.getConditions().add(Restrict.eq("projectName", project1.getProjectName()));
            CashFormula cashFormula = cashFormulaSer.findOne(cashFormulaDTO);
            if (null == cashFormula) {
                cashFormula = new CashFormula();
                cashFormula.setProjectId(project1.getId());
                cashFormula.setProjectName(project1.getProjectName());

                cashFormula.setForm(formula);
                cashFormulaSer.save(cashFormula);
            }
        }
    }

    @Override
    public byte[] export(CashFlowProjectDTO dto) throws SerException {
        List<CashFlowProjectBO> bos1 = this.list(dto);
        CashFlowDataDTO dto2 = new CashFlowDataDTO();
        dto2.setStartTime(dto.getStartTime());
        dto2.setEndTime(dto.getEndTime());
        List<CashFlowDataBO> bos2 = cashFlowDataSer.list(dto2);
        List<CashFlowExportBO> list = convertCashFlow(bos1, bos2);

        List<CashFlowExport> exports = new ArrayList<>();
        list.stream().forEach(str -> {
            CashFlowExport export = BeanTransform.copyProperties(str, CashFlowExport.class);
            exports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports, excel);
        return bytes;
    }

    List<CashFlowExportBO> convertCashFlow (List<CashFlowProjectBO> bos1, List<CashFlowDataBO> bos2) {
        List<CashFlowExportBO> list = new ArrayList<>();
        for (CashFlowProjectBO cashFlowProjectBO : bos1) {
            CashFlowExportBO cashFlowExportBO = new CashFlowExportBO();
            cashFlowExportBO.setProject(cashFlowProjectBO.getProjectName());
            cashFlowExportBO.setProjectAsset(cashFlowProjectBO.getNum());
            cashFlowExportBO.setProjectMoney(cashFlowProjectBO.getMoney());
            for (CashFlowDataBO cashFlowDataBO : bos2) {
                if ("一、经营活动产生的现金流量".equals(cashFlowProjectBO.getProjectName())) {
                    if ("1、将净利润调节为经营活动现金流量".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("销售商品、提供劳务收到的现金".equals(cashFlowProjectBO.getProjectName())) {
                    if ("净利润".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("收到的税费返还".equals(cashFlowProjectBO.getProjectName())) {
                    if ("加：计提的资产减值准备".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("收到的其他与经营活动有关的现金".equals(cashFlowProjectBO.getProjectName())) {
                    if ("固定资产折旧".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("一、现金流入小计".equals(cashFlowProjectBO.getProjectName())) {
                    if ("无形资产摊销".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("购买商品接受劳务支付的现金".equals(cashFlowProjectBO.getProjectName())) {
                    if ("长期待摊费用摊销".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("支付给职工以及为职工支付的现金".equals(cashFlowProjectBO.getProjectName())) {
                    if ("待摊费用减少（减：增加）".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("支付的各项税费".equals(cashFlowProjectBO.getProjectName())) {
                    if ("预提费用增加（减：减少）".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("支付的其他与经营活动有关的现金".equals(cashFlowProjectBO.getProjectName())) {
                    if ("处置固定资产、无形资产和其他长期资产的损失（减：收益）".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("一、现金流出小计".equals(cashFlowProjectBO.getProjectName())) {
                    if ("固定资产报废损失".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("经营活动产生的现金流量净额".equals(cashFlowProjectBO.getProjectName())) {
                    if ("财务费用".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("二、投资活动产生的现金流量".equals(cashFlowProjectBO.getProjectName())) {
                    if ("投资损失（减：收益）".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("收回投资所收到的现金".equals(cashFlowProjectBO.getProjectName())) {
                    if ("递延税款贷项（减：借项）".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("取得投资收益所收到的现金".equals(cashFlowProjectBO.getProjectName())) {
                    if ("存货的减少（减：增加）".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("处置固定资产、无形资产和其他长期资产所收回的现金净额".equals(cashFlowProjectBO.getProjectName())) {
                    if ("经营性应收项目的减少（减：增加）".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("收到的其他与投资活动有关的现金".equals(cashFlowProjectBO.getProjectName())) {
                    if ("经营性应付项目的增加（减：减少）".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("二、现金流入小计".equals(cashFlowProjectBO.getProjectName())) {
                    if ("其他".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("购建固定资产、无形资产和其他长期资产所支付的现金".equals(cashFlowProjectBO.getProjectName())) {
                    if ("经营活动产生的现金流量净额".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("投资所支付的现金".equals(cashFlowProjectBO.getProjectName())) {
//                    if ("1、将净利润调节为经营活动现金流量".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(null);
                        cashFlowExportBO.setProjectDataAsset(null);
                        cashFlowExportBO.setProjectDataMoney(null);
//                        break;
//                    }
                }
                if ("支付的其他与投资活动有关的现金".equals(cashFlowProjectBO.getProjectName())) {
                    cashFlowExportBO.setProjectData(null);
                    cashFlowExportBO.setProjectDataAsset(null);
                    cashFlowExportBO.setProjectDataMoney(null);
                }
                if ("二、现金流出小计".equals(cashFlowProjectBO.getProjectName())) {
                    cashFlowExportBO.setProjectData(null);
                    cashFlowExportBO.setProjectDataAsset(null);
                    cashFlowExportBO.setProjectDataMoney(null);
                }
                if ("投资活动产生的现金流量净额".equals(cashFlowProjectBO.getProjectName())) {
                    if ("2、不涉及现金收支的投资和筹资活动：".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("三、筹资活动产生的现金流量".equals(cashFlowProjectBO.getProjectName())) {
                    if ("债务转为资本".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("吸收投资所收到的现金".equals(cashFlowProjectBO.getProjectName())) {
                    if ("一年内到期的可转换公司债券".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("取得借款所收到的现金".equals(cashFlowProjectBO.getProjectName())) {
                    if ("融资租入固定资产".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("收到的其他与筹资活动有关的现金".equals(cashFlowProjectBO.getProjectName())) {
                    cashFlowExportBO.setProjectData(null);
                    cashFlowExportBO.setProjectDataAsset(null);
                    cashFlowExportBO.setProjectDataMoney(null);
                }
                if ("三、现金流入小计".equals(cashFlowProjectBO.getProjectName())) {
//                    if ("1、将净利润调节为经营活动现金流量".equals(cashFlowDataBO.getData())) {
                    cashFlowExportBO.setProjectData(null);
                    cashFlowExportBO.setProjectDataAsset(null);
                    cashFlowExportBO.setProjectDataMoney(null);
//                        break;
//                    }
                }
                if ("偿还债务所支付的现金".equals(cashFlowProjectBO.getProjectName())) {
                    cashFlowExportBO.setProjectData(null);
                    cashFlowExportBO.setProjectDataAsset(null);
                    cashFlowExportBO.setProjectDataMoney(null);
                }
                if ("分配股利、利润和偿付利息所支付的现金".equals(cashFlowProjectBO.getProjectName())) {
                    if ("3、现金及现金等价物净增加情况".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("支付的其他与筹资活动有关的现金".equals(cashFlowProjectBO.getProjectName())) {
                    if ("现金的期未余额".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("三、现金流出小计".equals(cashFlowProjectBO.getProjectName())) {
                    if ("减：现金的期初余额".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("筹资活动产生的现金流量净额".equals(cashFlowProjectBO.getProjectName())) {
                    if ("加：现金等价物的期未余额".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                    }
                }
                if ("四、汇率变动对现金的影响".equals(cashFlowProjectBO.getProjectName())) {
                    if ("减：现金等价物的期初余额".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }
                if ("五、现金及现金等价物净增加额".equals(cashFlowProjectBO.getProjectName())) {
                    if ("现金及现金等价物净增加额".equals(cashFlowDataBO.getData())) {
                        cashFlowExportBO.setProjectData(cashFlowDataBO.getData());
                        cashFlowExportBO.setProjectDataAsset(cashFlowDataBO.getNum());
                        cashFlowExportBO.setProjectDataMoney(cashFlowDataBO.getMoney());
                        break;
                    }
                }

            }
            list.add(cashFlowExportBO);

        }
        return list;
    }

}