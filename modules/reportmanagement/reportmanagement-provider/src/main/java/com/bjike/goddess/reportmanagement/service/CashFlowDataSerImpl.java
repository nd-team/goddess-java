package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.reportmanagement.bo.CashFlowDataBO;
import com.bjike.goddess.reportmanagement.bo.CashFlowProjectBO;
import com.bjike.goddess.reportmanagement.bo.CashFormulaDataBO;
import com.bjike.goddess.reportmanagement.bo.ReturnCashDataBO;
import com.bjike.goddess.reportmanagement.dto.CashFlowDataDTO;
import com.bjike.goddess.reportmanagement.dto.CashFlowDatumDTO;
import com.bjike.goddess.reportmanagement.dto.CashFormulaDataDTO;
import com.bjike.goddess.reportmanagement.dto.DataDTO;
import com.bjike.goddess.reportmanagement.entity.*;
import com.bjike.goddess.reportmanagement.enums.DataType;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.SubjectCollectBO;
import com.bjike.goddess.voucher.dto.SubjectCollectDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 现金流量资料表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 03:02 ]
 * @Description: [ 现金流量资料表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class CashFlowDataSerImpl extends ServiceImpl<CashFlowData, CashFlowDataDTO> implements CashFlowDataSer {

    @Autowired
    private CashFlowDatumSer cashFlowDatumSer;
    @Autowired
    private DataSer dataSer;
    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;
    @Autowired
    private CashFormulaDataSer cashFormulaDataSer;
    @Autowired
    private CashFlowDataSer cashFlowDataSer;
    @Autowired
    private CashFormulaSer cashFormulaSer;

    @Override
    public List<CashFlowDataBO> list(CashFlowDataDTO dto) throws SerException {
        List<CashFlowDataBO> bos = new ArrayList<>(0);
        if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
            dto.setStartTime(DateUtil.dateToString(DateUtil.getStartMonth()));
            dto.setEndTime(DateUtil.dateToString(DateUtil.getEndMonth()));
        }
        //判断是否已有存储
        CashFlowDatumDTO cashFlowDatumDTO = new CashFlowDatumDTO();
        cashFlowDatumDTO.getConditions().add(Restrict.eq("startTime", dto.getStartTime()));
        cashFlowDatumDTO.getConditions().add(Restrict.eq("endTime", dto.getEndTime()));
        cashFlowDatumDTO.getSorts().add("seqNum=asc");
        List<CashFlowDatum> cashFlowDatumList = cashFlowDatumSer.findByCis(cashFlowDatumDTO);
        if (null != cashFlowDatumList && cashFlowDatumList.size() > 0) {
            for (CashFlowDatum cashFlowDatum : cashFlowDatumList) {
                CashFlowDataBO cashFlowDataBO = BeanTransform.copyProperties(cashFlowDatum, CashFlowDataBO.class);
                cashFlowDataBO.setId(cashFlowDatum.getDataId());
                bos.add(cashFlowDataBO);
            }
            return convertCashFlowData(bos);
        }

        DataDTO dataDTO = new DataDTO();
        dataDTO.getSorts().add("dataType=asc");
        dataDTO.getSorts().add("createTime=asc");
        List<Data> datas = dataSer.findByCis(dataDTO);
        Boolean b1 = true;
        Boolean b2 = true;
        Boolean b3 = true;
        Boolean b4 = true;
        Boolean b5 = true;
        int num = 1;
        if (null != datas && datas.size() > 0) {
            for (Data data : datas) {
                if (DataType.PROFIT.equals(data.getDataType()) && b1) {
                    CashFlowDataBO bo = new CashFlowDataBO();
                    bo.setData("1、将净利润调节为经营活动现金流量");
                    bo.setNum(num);
                    bo.setStartTime(dto.getStartTime());
                    bo.setEndTime(dto.getEndTime());
                    bos.add(bo);
                    num ++;
                    b1 = false;
                } else if (DataType.NOTINVOLVE.equals(data.getDataType()) && b2) {
                    CashFlowDataBO bo = new CashFlowDataBO();
                    bo.setData("2、不涉及现金收支的投资和筹资活动");
                    bo.setNum(num);
                    bo.setStartTime(dto.getStartTime());
                    bo.setEndTime(dto.getEndTime());
                    bos.add(bo);
                    num ++;
                    b2 = false;
                } else if (DataType.CASH.equals(data.getDataType()) && b3) {
                    CashFlowDataBO bo = new CashFlowDataBO();
                    bo.setData("3、现金及现金等价物净增加情况");
                    bo.setNum(num);
                    bo.setStartTime(dto.getStartTime());
                    bo.setEndTime(dto.getEndTime());
                    bos.add(bo);
                    num ++;
                    b3 = false;
                }
                CashFlowDataBO bo = new CashFlowDataBO();
                bo.setData(data.getData());
                bo.setId(data.getId());

                bo.setMoney(findCashByData(data.getData(), dto));
                bo.setStartTime(dto.getStartTime());
                bo.setEndTime(dto.getEndTime());
                bo.setNum(num++);
                bos.add(bo);
            }
            int seqNum = 1;
            for (CashFlowDataBO bo : bos) {
                CashFlowDatum cashFlowDatum = new CashFlowDatum();
                cashFlowDatum.setData(bo.getData());
                cashFlowDatum.setDataId(bo.getId());
                cashFlowDatum.setNum(bo.getNum());
                cashFlowDatum.setSeqNum(seqNum++);
                cashFlowDatum.setStartTime(bo.getStartTime());
                cashFlowDatum.setEndTime(bo.getEndTime());
                cashFlowDatum.setMoney(bo.getMoney());
                cashFlowDatumSer.save(cashFlowDatum);
            }
        }
        return convertCashFlowData(bos);
    }

    private List<CashFlowDataBO> convertCashFlowData(List<CashFlowDataBO> list) {
//        for (int i = 0; i < bos.size(); i ++) {
//            if ("其他".equals(bos.get(i).getData())) {
//                CashFlowDataBO bo1 = new CashFlowDataBO("", null, null);
//                bos.add(i + 1, bo1);
//                bos.add(i + 2, bo1);
//                bos.add(i + 3, bo1);
//            }
//            if ("融资租入固定资产".equals(bos.get(i).getData())) {
//                CashFlowDataBO bo1 = new CashFlowDataBO("", null, null);
//                bos.add(i + 1, bo1);
//                bos.add(i + 2, bo1);
//                bos.add(i + 3, bo1);
//            }


        List<CashFlowDataBO> bos = new ArrayList<>();
        CashFlowDataBO bo1 = new CashFlowDataBO();
        CashFlowDataBO bo2 = new CashFlowDataBO();
        CashFlowDataBO bo3 = new CashFlowDataBO();
        CashFlowDataBO bo4 = new CashFlowDataBO();
        CashFlowDataBO bo5 = new CashFlowDataBO();
        CashFlowDataBO bo6 = new CashFlowDataBO();
        CashFlowDataBO bo7 = new CashFlowDataBO();
        CashFlowDataBO bo8 = new CashFlowDataBO();
        CashFlowDataBO bo9 = new CashFlowDataBO();
        CashFlowDataBO bo10 = new CashFlowDataBO();
        CashFlowDataBO bo11 = new CashFlowDataBO();
        CashFlowDataBO bo12 = new CashFlowDataBO();
        CashFlowDataBO bo13 = new CashFlowDataBO();
        CashFlowDataBO bo14 = new CashFlowDataBO();
        CashFlowDataBO bo15 = new CashFlowDataBO();
        CashFlowDataBO bo16 = new CashFlowDataBO();
        CashFlowDataBO bo17 = new CashFlowDataBO();
        CashFlowDataBO bo18 = new CashFlowDataBO();
        CashFlowDataBO bo19 = new CashFlowDataBO();
        CashFlowDataBO bo20 = new CashFlowDataBO();
        CashFlowDataBO bo21 = new CashFlowDataBO();
        CashFlowDataBO bo22 = new CashFlowDataBO();
        CashFlowDataBO bo23 = new CashFlowDataBO();
        CashFlowDataBO bo24 = new CashFlowDataBO();
        CashFlowDataBO bo25 = new CashFlowDataBO();
        CashFlowDataBO bo26 = new CashFlowDataBO();
        CashFlowDataBO bo27 = new CashFlowDataBO();
        CashFlowDataBO bo28 = new CashFlowDataBO();
        CashFlowDataBO bo29 = new CashFlowDataBO();
        CashFlowDataBO bo30 = new CashFlowDataBO();
        CashFlowDataBO bo31 = new CashFlowDataBO();
        CashFlowDataBO bo32 = new CashFlowDataBO();
        CashFlowDataBO bo33 = new CashFlowDataBO();
        CashFlowDataBO bo34 = new CashFlowDataBO();
            for (CashFlowDataBO cashFlowProjectBO : list) {
                if ("1、将净利润调节为经营活动现金流量".equals(cashFlowProjectBO.getData())) {
                    bo1 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("净利润".equals(cashFlowProjectBO.getData())) {
                    bo2 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("加：计提的资产减值准备".equals(cashFlowProjectBO.getData())) {
                    bo3 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("固定资产折旧".equals(cashFlowProjectBO.getData())) {
                    bo4 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("无形资产摊销".equals(cashFlowProjectBO.getData())) {
                    bo5 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("长期待摊费用摊销".equals(cashFlowProjectBO.getData())) {
                    bo6 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("待摊费用减少（减：增加）".equals(cashFlowProjectBO.getData())) {
                    bo7 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("预提费用增加（减：减少）".equals(cashFlowProjectBO.getData())) {
                    bo8 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("处置固定资产、无形资产和其他长期资产的损失（减：收益）".equals(cashFlowProjectBO.getData())) {
                    bo9 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("固定资产报废损失".equals(cashFlowProjectBO.getData())) {
                    bo10 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("财务费用".equals(cashFlowProjectBO.getData())) {
                    bo11 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("投资损失（减：收益）".equals(cashFlowProjectBO.getData())) {
                    bo12 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("递延税款贷项（减：借项）".equals(cashFlowProjectBO.getData())) {
                    bo13 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("存货的减少（减：增加）".equals(cashFlowProjectBO.getData())) {
                    bo14 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("经营性应收项目的减少（减：增加）".equals(cashFlowProjectBO.getData())) {
                    bo15 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("经营性应付项目的增加（减：减少）".equals(cashFlowProjectBO.getData())) {
                    bo16 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("其他".equals(cashFlowProjectBO.getData())) {
                    bo17 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("经营活动产生的现金流量净额".equals(cashFlowProjectBO.getData())) {
                    bo18 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                    bo19 = new CashFlowDataBO(null, null, null, null);
                    bo20 = new CashFlowDataBO(null, null, null, null);
                    bo21 = new CashFlowDataBO(null, null, null, null);
                }
                if ("2、不涉及现金收支的投资和筹资活动".equals(cashFlowProjectBO.getData())) {
                    bo22 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("债务转为资本".equals(cashFlowProjectBO.getData())) {
                    bo23 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("一年内到期的可转换公司债券".equals(cashFlowProjectBO.getData())) {
                    bo24 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("融资租入固定资产".equals(cashFlowProjectBO.getData())) {
                    bo25 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                    bo26 = new CashFlowDataBO(null, null, null, null);
                    bo27 = new CashFlowDataBO(null, null, null, null);
                    bo28 = new CashFlowDataBO(null, null, null, null);
                }
                if ("3、现金及现金等价物净增加情况".equals(cashFlowProjectBO.getData())) {
                    bo29 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("现金的期未余额".equals(cashFlowProjectBO.getData())) {
                    bo30 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("减：现金的期初余额".equals(cashFlowProjectBO.getData())) {
                    bo31 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("加：现金等价物的期未余额".equals(cashFlowProjectBO.getData())) {
                    bo32 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("减：现金等价物的期初余额".equals(cashFlowProjectBO.getData())) {
                    bo33 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
                }
                if ("现金及现金等价物净增加额".equals(cashFlowProjectBO.getData())) {
                    bo34 = new CashFlowDataBO(cashFlowProjectBO.getData(), cashFlowProjectBO.getNum(), cashFlowProjectBO.getMoney(), cashFlowProjectBO.getId());
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
                bos.add(bo31);
                bos.add(bo32);
                bos.add(bo33);
                bos.add(bo34);

        return bos;
    }

    @Override
    public CashFormulaDataBO findFormula(String dataId) throws SerException {
        CashFormulaDataDTO cashFormulaDataDTO = new CashFormulaDataDTO();
        cashFormulaDataDTO.getConditions().add(Restrict.eq("dataId", dataId));
        CashFormulaData cashFormulaData = cashFormulaDataSer.findOne(cashFormulaDataDTO);
        CashFormulaDataBO bo = BeanTransform.copyProperties(cashFormulaData, CashFormulaDataBO.class);
        return bo;
    }

    @Override
    public Long findTotal(CashFlowDataDTO dto) throws SerException {
        DataDTO projectDTO = new DataDTO();
        return dataSer.count(projectDTO) + 3;
    }

    @Override
    public ReturnCashDataBO findMoney(CashFlowDataDTO dto) throws SerException {
        if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
            dto.setStartTime(DateUtil.dateToString(DateUtil.getStartMonth()));
            dto.setEndTime(DateUtil.dateToString(DateUtil.getEndMonth()));
        }
        CashFlowDatumDTO cashFlowDataDTO = new CashFlowDatumDTO();
        cashFlowDataDTO.getConditions().add(Restrict.eq("dataId", dto.getDataId()));
        cashFlowDataDTO.getConditions().add(Restrict.eq("startTime", dto.getStartTime()));
        cashFlowDataDTO.getConditions().add(Restrict.eq("endTime", dto.getEndTime()));
        CashFlowDatum cashFlowDatum = cashFlowDatumSer.findOne(cashFlowDataDTO);
//        CashFlowData cashFlowData = cashFlowDataSer.findOne(cashFlowDataDTO);
//        String sql = "select endTime, id, money, startTime, data from reportmanagement_cashflowdata where dataId = '" + dto.getDataId() + "' and startTime = '" + dto.getStartTime()
//                + "' and endTime = '" + dto.getEndTime() + "'";
//        String[] fields = new String[]{"endTime", "id", "money", "startTime", "data"};
//        List<CashFlowData> cashFlowData = cashFlowDataSer.findBySql(sql, CashFlowData.class, fields);
//        if (cashFlowData == null || cashFlowData.size() == 0) {
//            return null;
//        }
//        if (null != cashFlowData.get(0)) {
//            ReturnCashDataBO bo = BeanTransform.copyProperties(cashFlowData.get(0), ReturnCashDataBO.class);
//            return bo;
//        }
        if (null != cashFlowDatum) {
            ReturnCashDataBO bo = BeanTransform.copyProperties(cashFlowDatum, ReturnCashDataBO.class);
            return bo;
        }
        return null;
    }

    @Override
    public void editMoney(CashFlowDataDTO dto) throws SerException {
        CashFlowDatumDTO cashFlowDataDTO = new CashFlowDatumDTO();
        cashFlowDataDTO.getConditions().add(Restrict.eq("dataId", dto.getDataId()));
        cashFlowDataDTO.getConditions().add(Restrict.eq("startTime", dto.getStartTime()));
        cashFlowDataDTO.getConditions().add(Restrict.eq("endTime", dto.getEndTime()));
        CashFlowDatum cashFlowData = cashFlowDatumSer.findOne(cashFlowDataDTO);
            if(cashFlowData != null) {
            cashFlowData.setMoney(dto.getMoney());
            cashFlowData.setModifyTime(LocalDateTime.now());
            cashFlowDatumSer.update(cashFlowData);
        }
    }

    //根据科目查询金额
    private Double findCashByData(String projectName, CashFlowDataDTO dto) throws SerException {
        Double cash = 0d;
        if ("净利润".equals(projectName)) {
            cash = findCash(dto);
            return cash;
        } else if ("加：计提的资产减值准备".equals(projectName)) {
            cash = findCash1(dto);
            return cash;
        } else if ("固定资产折旧".equals(projectName)) {
            cash = findCash2(dto);
            return cash;
        } else if ("无形资产摊销".equals(projectName)) {
            cash = findCash3(dto);
            return cash;
        } else if ("长期待摊费用摊销".equals(projectName)) {
            cash = findCash4(dto);
            return cash;
        } else if ("待摊费用减少（减：增加）".equals(projectName)) {
            cash = findCash5(dto);
            return cash;
        } else if ("预提费用增加（减：减少）".equals(projectName)) {
            cash = findCash6(dto);
            return cash;
        } else if ("处置固定资产、无形资产和其他长期资产的损失（减：收益）".equals(projectName)) {
            cash = findCash7(dto);
            return cash;
        } else if ("固定资产报废损失".equals(projectName)) {
            cash = findCash8(dto);
            return cash;
        } else if ("财务费用".equals(projectName)) {
            cash = findCash9(dto);
            return cash;
        } else if ("投资损失（减：收益）".equals(projectName)) {
            cash = findCash10(dto);
            return cash;
        } else if ("递延税款贷项（减：借项）".equals(projectName)) {
            cash = findCash11(dto);
            return cash;
        } else if ("存货的减少（减：增加）".equals(projectName)) {
            cash = findCash12(dto);
            return cash;
        } else if ("经营性应收项目的减少（减：增加）".equals(projectName)) {
            cash = findCash13(dto);
            return cash;
        } else if ("经营性应付项目的增加（减：减少）".equals(projectName)) {
            cash = findCash14(dto);
            return cash;
        } else if ("其他".equals(projectName)) {
            cash = findCash15(dto);
            return cash;
        } else if ("经营活动产生的现金流量净额".equals(projectName)) {
            cash = findCash16(dto);
            return cash;
        } else if (" 债务转为资本".equals(projectName)) {
            cash = findCash17(dto);
            return cash;
        } else if ("一年内到期的可转换公司债券".equals(projectName)) {
            cash = findCash18(dto);
            return cash;
        } else if ("融资租入固定资产".equals(projectName)) {
            cash = findCash19(dto);
            return cash;
        } else if ("现金的期末余额".equals(projectName)) {
            cash = findCash20(dto);
            return cash;
        } else if ("减：现金的期初余额".equals(projectName)) {
            cash = findCash21(dto);
            return cash;
        } else if (" 加：现金等价物的期末余额".equals(projectName)) {
            cash = findCash22(dto);
            return cash;
        } else if ("减：现金等价物的期初余额".equals(projectName)) {
            cash = findCash23(dto);
            return cash;
        } else if ("现金及现金等价物净增加额".equals(projectName)) {
            cash = findCash24(dto);
            return cash;
        }
        return cash;
    }

    //净利润
    private Double findCash(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("净利润", DataType.PROFIT, "该项目根据利润表净利润数填列");

        //该项目根据利润表净利润数填列(本年累计)
        SubjectCollectBO subjectCollectBO = voucherGenerateAPI.findCurrentAndYear("净利润", dto.getStartTime(), dto.getEndTime());
        if (null != subjectCollectBO) {
            return subjectCollectBO.getYearAmount();
        }
        return 0d;
    }

    //加：计提的资产减值准备
    private Double findCash1(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("加：计提的资产减值准备", DataType.PROFIT, "本期计提的各项资产减值准备发生额累计数");

        //本期计提的各项资产减值准备发生额累计数(记账凭证)
        Double cash1 = 0d;
        cash1 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("各项资产减值准备"), dto.getStartTime(), dto.getEndTime(), true);
        return cash1;
    }

    //固定资产折旧
    private Double findCash2(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("固定资产折旧", DataType.PROFIT, "制造费用中折旧＋管理费用中折旧 或：累计折旧期末数－累计折旧期初数");

        //累计折旧期末数－累计折旧期初数
        Double cash1 = 0d;
        cash1 = findAssetNum("累计折旧", dto.getEndTime(), false) - findAssetNum("累计折旧", dto.getEndTime(), true);
        return cash1;
    }

    //无形资产摊销
    private Double findCash3(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("无形资产摊销", DataType.PROFIT, "无形资产（期初数－期末数）");

        //无形资产（期初数－期末数）
        Double cash1 = 0d;
        cash1 = findAssetNum("无形资产", dto.getEndTime(), true) - findAssetNum("无形资产", dto.getEndTime(), false);
        return cash1;
    }

    //长期待摊费用摊销
    private Double findCash4(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("长期待摊费用摊销", DataType.PROFIT, "长期待摊费用（期初数－期末数）");

        //长期待摊费用（期初数－期末数）
        Double cash1 = 0d;
        cash1 = findAssetNum("长期待摊费用", dto.getEndTime(), true) - findAssetNum("长期待摊费用", dto.getEndTime(), false);
        return cash1;
    }

    //待摊费用减少（减：增加）
    private Double findCash5(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("待摊费用减少（减：增加）", DataType.PROFIT, "待摊费用期初数－待摊费用期末数");

        //待摊费用期初数－待摊费用期末数
        Double cash1 = 0d;
        cash1 = findAssetNum("待摊费用", dto.getEndTime(), true) - findAssetNum("待摊费用", dto.getEndTime(), false);
        return cash1;
    }

    //预提费用增加（减：减少）
    private Double findCash6(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("预提费用增加（减：减少）", DataType.PROFIT, "预提费用期末数－预提费用期初数");

        //预提费用期末数－预提费用期初数
        Double cash1 = 0d;
        cash1 = findAssetNum("预提费用", dto.getEndTime(), false) - findAssetNum("预提费用", dto.getEndTime(), true);
        return cash1;
    }

    //处置固定资产、无形资产和其他长期资产的损失（减：收益）
    private Double findCash7(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("处置固定资产、无形资产和其他长期资产的损失（减：收益）", DataType.PROFIT, "根据固定资产清理及营业外支出（或收入）明细账分析填列：“营业外支出——处理固定资产损失”、“其他业务支出——处理无形资产支出”等账户的借方发生额(借方-贷方本年累计)，减去“营业外收入——处理固定资产收益”、“其他业务收入——处理无形资产收入”账户的贷方发生额计算填列");

        //营业外支出——处理固定资产损失”的借方发生额(借方-贷方本年累计)
        Double cash1 = 0d;
        cash1 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("处理固定资产损失"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        //“其他业务支出——处理无形资产支出”等账户的借方发生额(借方-贷方本年累计)
        Double cash2 = 0d;
        cash2 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("处理无形资产支出"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        //“营业外收入——处理固定资产收益”账户的贷方发生额
        Double cash3 = 0d;
        cash3 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("处理固定资产收益"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), false);

        //其他业务收入——处理无形资产收入”账户的贷方发生额计算填列
        Double cash4 = 0d;
        cash4 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("处理无形资产收入"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), false);

        return cash1 + cash2 - cash3 - cash4;
    }

    //固定资产报废损失
    private Double findCash8(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("固定资产报废损失", DataType.PROFIT, " 根据固定资产清理及营业外支出明细账分析填列：固定资产报废损失=营业外支出中固定资产盘亏损失和固定资产报废净损失(科目方向)+营业外收入中固定资产盘盈收益和固定资产报废净收益");

        //营业外支出中固定资产盘亏损失
        Double cash1 = 0d;
        cash1 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("固定资产盘亏损失"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        //固定资产报废净损失
        Double cash2 = 0d;
        cash2 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("固定资产报废净损失"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        //营业外收入中固定资产盘盈收益
        Double cash3 = 0d;
        cash3 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("固定资产盘盈收益"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        //固定资产报废净收益
        Double cash4 = 0d;
        cash4 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("固定资产报废净收益"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        return cash1 + cash2 + cash3 + cash4;
    }

    //财务费用
    private Double findCash9(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("财务费用", DataType.PROFIT, "利息支出~应收票据的贴现利息");

        //利息支出~应收票据的贴现利息(记账凭证,本年累计)
        Double cash1 = 0d;
        Double cash2 = 0d;
        cash1 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("利息支出"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);
        cash2 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("应收票据的贴现利息"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        return cash1 - cash2;
    }

    //投资损失（减：收益）
    private Double findCash10(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("投资损失（减：收益）", DataType.PROFIT, "投资收益");

        //投资收益（借方余额正号填列，贷方余额负号填列）(记账凭证,本年累计)
        Double cash1 = 0d;
        cash1 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("投资收益"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        return cash1;
    }

    //递延税款贷项（减：借项）
    private Double findCash11(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("递延税款贷项（减：借项）", DataType.PROFIT, "递延税款（期末数－期初数）");

        //递延税款（期末数－期初数）(记账凭证,本年累计)
        Double cash1 = 0d;
        cash1 = findAssetNum("递延税款", dto.getEndTime(), false) - findAssetNum("递延税款", dto.getEndTime(), true);

        return cash1;
    }

    //存货的减少（减：增加）
    private Double findCash12(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("存货的减少（减：增加）", DataType.PROFIT, "存货（期初数－期末数）");

        //存货（期初数－期末数）(记账凭证,本年累计)
        Double cash1 = 0d;
        cash1 = findAssetNum("存货", dto.getEndTime(), true) - findAssetNum("存货", dto.getEndTime(), false);

        return cash1;
    }

    //经营性应收项目的减少（减：增加）
    private Double findCash13(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("经营性应收项目的减少（减：增加）", DataType.PROFIT, "应收账款（期初数－期末数）＋应收票据（期初数－期末数）＋预付账款（期初数－期末数）＋其他应收款（期初数－期末数）＋待摊费用（期初数－期末数）－坏账准备期末余额");

        //应收账款（期初数－期末数）
        Double cash1 = 0d;
        cash1 = findAssetNum("应收账款", dto.getEndTime(), true) - findAssetNum("应收账款", dto.getEndTime(), false);

        //应收票据（期初数－期末数）
        Double cash2 = 0d;
        cash2 = findAssetNum("应收票据", dto.getEndTime(), true) - findAssetNum("应收票据", dto.getEndTime(), false);

        //预付账款（期初数－期末数）
        Double cash3 = 0d;
        cash3 = findAssetNum("预付账款", dto.getEndTime(), true) - findAssetNum("预付账款", dto.getEndTime(), false);

        //其他应收款（期初数－期末数）
        Double cash4 = 0d;
        cash4 = findAssetNum("其他应收款", dto.getEndTime(), true) - findAssetNum("其他应收款", dto.getEndTime(), false);

        //待摊费用（期初数－期末数）
        Double cash5 = 0d;
        cash5 = findAssetNum("待摊费用", dto.getEndTime(), true) - findAssetNum("待摊费用", dto.getEndTime(), false);

        //坏账准备期末余额
        Double cash6 = 0d;
        cash6 = voucherGenerateAPI.getCurrent(new SubjectCollectDTO("坏账准备"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);

        return cash1 + cash2 + cash3 + cash4 + cash5 - cash6;
    }

    // 经营性应付项目的增加（减：减少）
    private Double findCash14(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("经营性应付项目的增加（减：减少）", DataType.PROFIT, "应付账款（期末数－期初数）＋预收账款（期末数－期初数）＋应付票据（期末数－期初数）＋应付工资（期末数－期初数）＋应付福利费（期末数－期初数）＋应交税金（期末数－期初数）＋其他应交款（期末数－期初数）");

        //应付账款（期末数－期初数）
        Double cash1 = 0d;
        cash1 = findAssetNum("应付账款", dto.getEndTime(), false) - findAssetNum("应付账款", dto.getEndTime(), true);

        //预收账款（期末数－期初数）
        Double cash2 = 0d;
        cash2 = findAssetNum("预收账款", dto.getEndTime(), false) - findAssetNum("预收账款", dto.getEndTime(), true);

        //应付票据（期末数－期初数）
        Double cash3 = 0d;
        cash3 = findAssetNum("应付票据", dto.getEndTime(), false) - findAssetNum("应付票据", dto.getEndTime(), true);

        //应付工资（期末数－期初数）
        Double cash4 = 0d;
        cash4 = findAssetNum("应付工资", dto.getEndTime(), false) - findAssetNum("应付工资", dto.getEndTime(), true);

        //应付福利费（期末数－期初数）
        Double cash5 = 0d;
        cash5 = findAssetNum("应付福利费", dto.getEndTime(), false) - findAssetNum("应付福利费", dto.getEndTime(), true);

        //应交税金（期末数－期初数）
        Double cash6 = 0d;
        cash6 = findAssetNum("应交税金", dto.getEndTime(), false) - findAssetNum("应交税金", dto.getEndTime(), true);

        //其他应交款（期末数－期初数）
        Double cash7 = 0d;
        cash7 = findAssetNum("其他应交款", dto.getEndTime(), false) - findAssetNum("其他应交款", dto.getEndTime(), true);

        return cash1 + cash2 + cash3 + cash4 + cash5 + cash6 + cash7;
    }

    //其他
    private Double findCash15(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("其他", DataType.PROFIT, "但不是经营性活动的现金流，同时，又不符合现流补充资料的各项资料的");

        return 0d;
    }

    //经营活动产生的现金流量净额
    private Double findCash16(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("经营活动产生的现金流量净额", DataType.PROFIT, " 净利润 + 其他");

        return findCash15(dto) + findCash(dto);
    }

    //债务转为资本
    private Double findCash17(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("债务转为资本", DataType.NOTINVOLVE, " ");

        return 0d;
    }

    //一年内到期的可转换公司债券
    private Double findCash18(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("一年内到期的可转换公司债券", DataType.NOTINVOLVE, " ");

        return 0d;
    }

    //融资租入固定资产
    private Double findCash19(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("融资租入固定资产", DataType.NOTINVOLVE, " ");

        return 0d;
    }

    //现金的期末余额
    private Double findCash20(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("现金的期末余额", DataType.CASH, "资产负债表“货币资金”期末余额");

        return voucherGenerateAPI.getCurrent(new SubjectCollectDTO("货币资金"), dto.getStartTime().substring(0, 4) + "-01-01", dto.getEndTime(), true);
    }

    //减：现金的期初余额
    private Double findCash21(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("减：现金的期初余额", DataType.CASH, "资产负债表“货币资金”期初余额");

        //资产负债表“货币资金”期初余额
        return findAssetNum("货币资金", dto.getEndTime(), true);
    }

    //加：现金等价物的期末余额
    private Double findCash22(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("加：现金等价物的期末余额", DataType.CASH, "资产负债表“交易性金融资产”期末余额");

        //资产负债表“交易性金融资产”期末余额
        return findAssetNum("交易性金融资产", dto.getEndTime(), false);
    }

    //减：现金等价物的期初余额
    private Double findCash23(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("减：现金等价物的期初余额", DataType.CASH, "资产负债表“交易性金融资产”期初余额");

        //资产负债表“交易性金融资产”期初余额
        return findAssetNum("交易性金融资产", dto.getEndTime(), true);
    }

    //现金及现金等价物净增加额
    private Double findCash24(CashFlowDataDTO dto) throws SerException {
        //保存公式
        saveFormula("现金及现金等价物净增加额", DataType.CASH, "现金的期末余额 - 减：现金的期初余额");

        return findCash20(dto) - findCash21(dto);
    }


    //获取资产负债表中的期初数和期末数,tar为true,获取期初数
    private Double findAssetNum(String firstSubject, String endTime, Boolean tar) throws SerException {
        SubjectCollectDTO subjectCollectDTO = new SubjectCollectDTO();
        subjectCollectDTO.setFirstSubject(firstSubject);
//        SubjectCollectBO subjectCollectBO = voucherGenerateAPI.getSum(subjectCollectDTO, endTime,endTime, true);
        SubjectCollectBO subjectCollectBO = voucherGenerateAPI.getSum(subjectCollectDTO, endTime,endTime, false);
        if (null != subjectCollectBO) {
            if (tar) {
                return subjectCollectBO.getBeginAmount();
            } else {
                return subjectCollectBO.getEndAmount();
            }
        }
        return 0d;
    }

    //存入公式
    private void saveFormula(String data, DataType dataType, String formula) throws SerException {
        //存入公式
        DataDTO dataDTO = new DataDTO();
        dataDTO.getConditions().add(Restrict.eq("data", data));
        dataDTO.getConditions().add(Restrict.eq("dataType", dataType));
        Data data1 = dataSer.findOne(dataDTO);
        if (null != data1) {
            CashFormulaDataDTO cashFormulaDataDTO = new CashFormulaDataDTO();
            cashFormulaDataDTO.getConditions().add(Restrict.eq("dataId", data1.getId()));
            cashFormulaDataDTO.getConditions().add(Restrict.eq("data", data1.getData()));
            CashFormulaData cashFormulaData = cashFormulaDataSer.findOne(cashFormulaDataDTO);
            if (null == cashFormulaData) {
                cashFormulaData = new CashFormulaData();
                cashFormulaData.setDataId(data1.getId());
                cashFormulaData.setData(data1.getData());

                cashFormulaData.setForm(formula);
                cashFormulaDataSer.save(cashFormulaData);
            }
        }
    }

}