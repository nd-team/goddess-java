package com.bjike.goddess.bankrecords.service;

import com.alibaba.fastjson.JSON;
//import com.bjike.goddess.bankrecords.api.BankSummaryAPI;
import com.bjike.goddess.bankrecords.beanlist.Detail;
import com.bjike.goddess.bankrecords.bo.*;
import com.bjike.goddess.bankrecords.dto.BankAccountInfoDTO;
import com.bjike.goddess.bankrecords.dto.BankRecordDTO;
import com.bjike.goddess.bankrecords.dto.BankRecordDetailDTO;
import com.bjike.goddess.bankrecords.dto.BankSummaryDTO;
import com.bjike.goddess.bankrecords.entity.BankAccountInfo;
import com.bjike.goddess.bankrecords.entity.BankRecord;
import com.bjike.goddess.bankrecords.entity.BankRecordDetail;
import com.bjike.goddess.bankrecords.enums.GuideAddrStatus;
//import com.bjike.goddess.bankrecords.excel.BankRecordsAnaExcel;
import com.bjike.goddess.bankrecords.excel.BankRecordsAnalyzeExcel;
//import com.bjike.goddess.bankrecords.excel.BankRecordsCollectExcel;
import com.bjike.goddess.bankrecords.excel.BankSummaryExcel;
import com.bjike.goddess.bankrecords.to.BankRecordTO;
import com.bjike.goddess.bankrecords.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
//import com.bjike.goddess.financeinit.api.BaseParameterAPI;
//import com.bjike.goddess.fundrecords.api.FundRecordAPI;
//import com.bjike.goddess.fundrecords.bo.FundRecordBO;
//import com.bjike.goddess.fundrecords.bo.MonthCollectBO;
//import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * 银行流水业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 05:35 ]
 * @Description: [ 银行流水业务实现 ]12
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "bankrecordsSerCache")
@Service
public class BankRecordSerImpl extends ServiceImpl<BankRecord, BankRecordDTO> implements BankRecordSer {

    @Autowired
    private BankRecordDetailSer bankRecordDetailSer;
    @Autowired
    private BankAccountInfoSer bankAccountInfoSer;
//    @Autowired
//    private FundRecordAPI fundRecordAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
//    @Autowired
//    private BaseParameterAPI baseParameterAPI;
//    @Autowired
//    private BankSummaryAPI bankSummaryAPI;

    @Autowired
    private BankSummarySer bankSummarySer;

    @Override
    public Long countTo(BankRecordDTO dto) throws SerException {
////        String token = RpcTransmit.getUserToken();
////        String systemId = userAPI.currentSysNO();
////        RpcTransmit.transmitUserToken(token);
//        StringBuffer sb = new StringBuffer();
//        sb.append("select count(id) from bankrecords_bankrecord where 1=1");
//        if (dto.getPropertyid().equals("基本户")) {
//            sb.append(" and accountId in (select id from bankrecords_bankaccountinfo where type='基本户') ");
//        } else {
//            sb.append(" and accountId in (select id from bankrecords_bankaccountinfo where type='一般户') ");
//        }
//        if (dto.getAccountId().equals("0")) {
//
//        } else {
//            sb.append(" and accountId=' " + dto.getAccountId() + " ' ");
//        }
//        if (dto.getStartDate() != null) {
//            String s = baseParameterAPI.findDoudap();//账套会计期间启用日期
//            LocalDate beginDateTime = LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));//这是把
//            LocalDate beginDateTime1 = LocalDate.parse(dto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//            if (beginDateTime1.until(beginDateTime, ChronoUnit.WEEKS) > 0) {
//                sb.append(" and recordDate>=' " + dto.getStartDate() + " ' ");
//            } else {
//                throw new SerException("不能低于账套会计期间启用日期");
//            }
//        }
//        if (dto.getEndDate() != null) {
//            sb.append(" and recordDate<=' " + dto.getEndDate() + " ' ");
//        }
//        List<Object> list = super.findBySql(sb.toString());
        return null;
    }


    @Override
    public List<BankRecordBO> bankRecordcall(BankRecordDTO dto) throws SerException {
//        String token = RpcTransmit.getUserToken();
//        String systemId = userAPI.currentSysNO();
//        RpcTransmit.transmitUserToken(token);
        int page = dto.getPage();
        int limit = dto.getLimit();
        int start = (page - 1) * limit;
        int end = page * limit;

        StringBuffer sb = new StringBuffer();

        sb.append("select * from bankrecords_bankrecord where 1=1");
//        if (dto.getPropertyid().equals("基本户")) {
//            sb.append(" and accountId in (select id from bankrecords_bankaccountinfo where type='基本户') ");
//        } else {
//            sb.append(" and accountId in (select id from bankrecords_bankaccountinfo where type='一般户') ");
//        }
//        if (dto.getAccountId().equals("0")) {
//
//        } else {
//            sb.append(" and accountId=' " + dto.getAccountId() + " ' ");
//        }
//        if (dto.getStartDate() != null) {
//            String s = baseParameterAPI.findDoudap();//账套会计期间启用日期
//            LocalDate beginDateTime = LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));//这是把它转为时间类型
//            LocalDate beginDateTime1 = LocalDate.parse(dto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//            if (beginDateTime1.until(beginDateTime, ChronoUnit.WEEKS) > 0) {
//                sb.append(" and recordDate>=' " + dto.getStartDate() + " ' ");
//            } else {
//                throw new SerException("不能低于账套会计期间启用日期");
//            }
//        }
        if (dto.getEndDate() != null) {
            sb.append(" and recordDate<=' " + dto.getEndDate() + " ' ");
        }
        sb.append(" ORDER BY recordDate desc limit  " + start + "  , " + end + "  ");
        return BeanTransform.copyProperties(super.findBySql(sb.toString()), BankRecordBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ExcelTitleBO> check(List<InputStream> inputStreams) throws SerException {

        if (inputStreams != null && !inputStreams.isEmpty()) {

            String fileName = getFileInfo(inputStreams.get(0));
            String suffixName = fileName.substring(fileName.lastIndexOf("."));

            //判断文件后缀名
            if (suffixName.equals(".xls") || suffixName.equals(".XLS") ||
                    suffixName.equals(".xlsx") || suffixName.equals(".XLSX")) {
                //转换字节流
                Object obj = (Object) inputStreams.get(1);
                InputStream inputStream = new ByteArrayInputStream((byte[]) obj);
                List<ExcelTitleBO> list = new ArrayList<ExcelTitleBO>();
                try {
                    //不同格式的Excel需要导入不同的包,(".xlsx"后缀为OFFICE2007以上的版本)
                    if (suffixName.equals(".xls") || suffixName.equals(".XLS")) {
                        HSSFWorkbook wb = new HSSFWorkbook(inputStream);
                        HSSFSheet sheet = wb.getSheetAt(0);
                        int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();
                        for (int i = 0; i < cellNum; i++) {
                            ExcelTitleBO titleBO = new ExcelTitleBO();
                            titleBO.setValue(sheet.getRow(0).getCell(i).getStringCellValue());
                            list.add(titleBO);
                        }
                    } else if (suffixName.equals(".xlsx") || suffixName.equals(".XLSX")) {
                        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
                        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
                        int cellNum = xssfSheet.getRow(0).getPhysicalNumberOfCells();
                        for (int i = 0; i < cellNum; i++) {
                            ExcelTitleBO titleBO = new ExcelTitleBO();
                            titleBO.setValue(xssfSheet.getRow(0).getCell(i).getStringCellValue());
                            list.add(titleBO);
                        }
                    }
                    return list;
                } catch (IOException e) {
                    throw new SerException("Excel读取失败，请检查文件是否损坏或联系管理员!");
                }
            } else {
                throw new SerException("请上传'.xls'或者'.xlsx'格式的Excel文件!");
            }

        } else {
            throw new SerException("文件内容不能为空!");
        }
    }


    @Override
    @Transactional
    public void upload(BankRecordTO to) throws SerException {
        try {
            List<InputStream> inputStreams = to.getInputStreams();
            if (inputStreams != null && !inputStreams.isEmpty()) {

                String fileName = getFileInfo(inputStreams.get(0));
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                //判断文件后缀名
                if (suffixName.equalsIgnoreCase(".xls") || suffixName.equalsIgnoreCase(".xlsx")) {
                    //转换字节流
                    Object obj = (Object) inputStreams.get(1);
                    InputStream inputStream = new ByteArrayInputStream((byte[]) obj);

                    //不同格式的Excel需要导入不同的包,(".xlsx"后缀为OFFICE2007以上的版本)
                    if (suffixName.equals(".xls") || suffixName.equals(".XLS")) {
                        HSSFWorkbook wb = new HSSFWorkbook(inputStream);
                        HSSFSheet sheet = wb.getSheetAt(0);
                        insertModels(sheet, to);
                    } else if (suffixName.equals(".xlsx") || suffixName.equals(".XLSX")) {
                        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
                        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
                        insertModels(xssfSheet, to);
                    }
                } else {
                    throw new SerException("请上传'.xls'或者'.xlsx'格式的Excel文件!");
                }


            } else {
                throw new SerException("上传的Excel不能为空!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long count(BankRecordDTO dto) throws SerException {

        String sql = "SELECT count(m.c) FROM (\n" +
                "  SELECT 1 as c\n" +
                "  FROM bankrecords_bankrecord b LEFT JOIN bankrecords_bankrecorddetail d ON b.id = d.bankRecord_id\n" +
                "  WHERE 1 = 1 ";
        if (dto.getAccountId().equals("0")) {
            sql += " and b.accountId in (select id from bankrecords_bankaccountinfo where type='基本户') ";
        }
        if (!StringUtils.isEmpty(dto.getAccountId()) && !dto.getAccountId().equals("0")) {
            sql += "AND b.accountId = '" + dto.getAccountId() + "' ";
        }
        if (!StringUtils.isEmpty(dto.getStartDate()) && !StringUtils.isEmpty(dto.getEndDate())) {
            sql += "       AND b.id IN\n" +
                    "        (SELECT bankRecord_id\n" +
                    "         FROM bankrecords_bankrecorddetail\n" +
                    "         WHERE title = '交易日' AND date_format(val, '%Y-%m-%d') BETWEEN '" + dto.getStartDate() + "' AND '" + dto.getEndDate() + "'\n" +
                    "        )\n";
        }
        if (!StringUtils.isEmpty(dto.getMoney())) {
            sql += "        AND b.id IN\n" +
                    "            (SELECT bankRecord_id\n" +
                    "             FROM bankrecords_bankrecorddetail\n" +
                    "             WHERE\n" +
                    "               (title = '余额' AND val = " + dto.getMoney() + ") OR (title = '贷方金额' AND val = " + dto.getMoney() + ") OR (title = '借方金额' AND val = " + dto.getMoney() + ")\n" +
                    "            )\n";
        }
        if (!StringUtils.isEmpty(dto.getSummary())) {
            sql += "        AND b.id IN\n" +
                    "            (SELECT bankRecord_id\n" +
                    "             FROM bankrecords_bankrecorddetail\n" +
                    "             WHERE\n" +
                    "               title = '摘要' AND val = '" + dto.getSummary() + "'\n" +
                    "            )\n";
        }
        if (!StringUtils.isEmpty(dto.getPayName())) {
            sql += "        AND b.id IN\n" +
                    "            (SELECT bankRecord_id\n" +
                    "             FROM bankrecords_bankrecorddetail\n" +
                    "             WHERE\n" +
                    "               title = '收/付方名称' AND val = '" + dto.getPayName() + "'\n" +
                    "            )\n";
        }
        if (!StringUtils.isEmpty(dto.getPayAccount())) {
            sql += "        AND b.id IN\n" +
                    "            (SELECT bankRecord_id\n" +
                    "             FROM bankrecords_bankrecorddetail\n" +
                    "             WHERE\n" +
                    "               title = '收/付方帐号' AND val = '" + dto.getPayAccount() + "'\n" +
                    "            )\n";
        }
        sql += "  GROUP BY b.id )m";
        List<Object> list = super.findBySql(sql);
        return Long.parseLong(String.valueOf(list.get(0)));
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<BankRecordBO> pageList(BankRecordDTO dto) throws SerException {
//        dto.getSorts().add("createTime=desc");
//        dto.getConditions().add(Restrict.eq("accountId", dto.getAccountId()));
//        List<BankRecordBO> boList = BeanTransform.copyProperties(super.findByPage(dto), BankRecordBO.class);
//修改：改为sql语句查询

        String token = RpcTransmit.getUserToken();
//
//String systemId = userAPI.currentSysNO();
        RpcTransmit.transmitUserToken(token);
        int page = dto.getPage();
        int limit = dto.getLimit();
        int start = (page - 1) * limit;
        int end = page * limit;
        String sql = "SELECT b.id as id, d.bankRecord_id as bankRecord_id, d.id as detailId, d.title as title, d.titleIndex as titleIndex, d.val as val FROM bankrecords_bankrecord b LEFT JOIN bankrecords_bankrecorddetail d ON b.id = d.bankRecord_id " +
                "WHERE " +
                "b.id in (select n.id from (" +
                "(SELECT b.id as id FROM bankrecords_bankrecord b LEFT JOIN bankrecords_bankrecorddetail d ON b.id = d.bankRecord_id " +
                "WHERE ";
        if (dto.getAccountId().equals("0")) {
            sql += " b.accountId in (select id from bankrecords_bankaccountinfo where type='基本户') ";
        } else {
            sql += " b.accountId='" + dto.getAccountId() + "' ";
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            RpcTransmit.transmitUserToken(token);
            //String s=baseParameterAPI.findDoudap();//没有任何值
            String s="2013-01-30";
            if(s!=null){
                LocalDate beginDateTime = LocalDate.parse(s);//账套会计期间启用日期这是把它转为时间类型
            if (LocalDate.parse(dto.getStartDate()).isAfter(beginDateTime)) {
                sql += " AND b.id in (" +

                        "SELECT bankRecord_id as id FROM bankrecords_bankrecorddetail" +
                        " WHERE title = '交易日' AND date_format(val, '%Y-%m-%d') BETWEEN '" + dto.getStartDate() + "'  AND '" + dto.getEndDate() + "' " +
                        ") ";
            }
            } else {
                throw new SerException("不能低于账套会计期间启用日期");
            }
        }
//        if (!StringUtils.isEmpty(dto.getMoney())) {
//            sql += "\tand b.id in (\n" +
//                    "\tSELECT bankRecord_id as id FROM bankrecords_bankrecorddetail \n" +
//                    "\tWHERE (title = '余额' AND val = '" + dto.getMoney() + "') OR (title = '贷方金额' AND val = '" + dto.getMoney() + "') OR (title = '借方金额' AND val = '" + dto.getMoney() + "')\n" +
//                    "\t) \n";
//        }
//        if (!StringUtils.isEmpty(dto.getSummary())) {
//            sql += "\tAND b.id IN\n" +
//                    "            (SELECT bankRecord_id\n" +
//                    "             FROM bankrecords_bankrecorddetail\n" +
//                    "             WHERE\n" +
//                    "               title = '摘要' AND val = '" + dto.getSummary() + "'\n" +
//                    "            )\n";
//        }
//        if (!StringUtils.isEmpty(dto.getPayName())) {
//            sql += "        AND b.id IN\n" +
//                    "            (SELECT bankRecord_id\n" +
//                    "             FROM bankrecords_bankrecorddetail\n" +
//                    "             WHERE\n" +
//                    "               title = '收/付方名称' AND val = '" + dto.getPayName() + "'\n" +
//                    "            )\n";
//        }
//        if (!StringUtils.isEmpty(dto.getPayAccount())) {
//            sql += "        AND b.id IN\n" +
//                    "            (SELECT bankRecord_id\n" +
//                    "             FROM bankrecords_bankrecorddetail\n" +
//                    "             WHERE\n" +
//                    "               title = '收/付方帐号' AND val = '" + dto.getPayAccount() + "'\n" +
//                    "            )\n";
//        }
        sql += "GROUP BY b.id limit " + start + ", " + end + ") n" +
                ")" +
                ") ";

        List<Object> list = super.findBySql(sql);

        Set set = new HashSet();
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
//            set.add(obj[0]);
        }

        List<BankRecordBO> bos = new ArrayList<>();
        for (Object object : set) {
            BankRecordBO bankRecordBO = new BankRecordBO();
            bankRecordBO.setId(String.valueOf(object));

            List<Detail> details = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                Object[] obj2 = (Object[]) list.get(j);
                if (obj2[1].equals(bankRecordBO.getId())) {
                    Detail detail = new Detail();
                    detail.setId(String.valueOf(obj2[2]));
                    detail.setTitle(String.valueOf(obj2[3]));
                    detail.setTitleIndex(Integer.parseInt(String.valueOf(obj2[4])));
                    detail.setVal(String.valueOf(obj2[5]));
                    details.add(detail);
                }

            }

            details = format(BeanTransform.copyProperties(details, BankRecordDetail.class));
            bankRecordBO.setDetailList(details);

            bos.add(bankRecordBO);

        }
//
//        if (boList != null && !boList.isEmpty()) {
//            for (BankRecordBO bo : boList) {
//                setFormat(bo);
//            }
//        }
        return bos;
    }

    public BankRecordBO setFormat(BankRecordBO bo) throws SerException {
        setBoInfo(bo);

        BankRecordDetailDTO detailDTO = new BankRecordDetailDTO();
        detailDTO.getConditions().add(Restrict.eq("bankRecord.id", bo.getId()));
        List<BankRecordDetail> detailList = bankRecordDetailSer.findByCis(detailDTO);
        if (detailList != null && !detailList.isEmpty()) {
            bo.setDetailList(format(detailList));
        }
        return bo;
    }

    public List<Detail> format(List<BankRecordDetail> detailList) throws SerException {
        List<Detail> list = BeanTransform.copyProperties(detailList, Detail.class);
        //按表头下标排序，还原Excel字段排列格式
        Collections.sort(list, new Comparator<Detail>() {
            @Override
            public int compare(Detail bo1, Detail bo2) {
                return bo1.getTitleIndex().compareTo(bo2.getTitleIndex());
            }
        });
        return list;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        BankRecord model = super.findById(id);
        if (model != null) {
            super.remove(model);
        } else {
            throw new SerException("非法Id,银行流水对象不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public BankRecordBO find(String id) throws SerException {
        BankRecord model = super.findById(id);
        BankRecordBO bo = null;
        if (model != null) {
            bo = BeanTransform.copyProperties(model, BankRecordBO.class);
            setFormat(bo);
        } else {
            throw new SerException("查询对象不能为空!");
        }
        return bo;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<BankRecordCollectBO> collect(Integer year, Integer month, String[] accountIds) throws SerException {
        //查询accountIds各账户的总支出、总收入
        StringBuilder sql = new StringBuilder("SELECT");
        sql.append(" bankrecord.accountId, info.bank AS bank, sum(bankrecord.creditorCost) AS creditorCost, sum(bankrecord.debtorCost) AS debtorCost");
        sql.append(" FROM bankrecords_bankrecord bankrecord LEFT JOIN bankrecords_bankaccountinfo info ON bankrecord.accountId = info.id");
        sql.append(" WHERE 0 = 0 ");
        sql.append(" AND bankrecord.recordYear = " + year);
        sql.append(" AND bankrecord.recordMonth = " + month);

        if (accountIds != null && accountIds.length > 0) {
            sql.append(" AND bankrecord.accountId IN ( ");
            for (int i = 0; i < accountIds.length; i++) {
                if (i != accountIds.length - 1) {
                    sql.append("'" + accountIds[i] + "'" + ",");
                } else {
                    sql.append("'" + accountIds[i] + "'");
                }
            }
            sql.append(" ) ");
        }
        sql.append(" GROUP BY bankrecord.accountId ");
        String[] fields = new String[]{"accountId", "bank", "creditorCost", "debtorCost"};

        List<BankRecordCollectBO> boList = super.findBySql(sql.toString(), BankRecordCollectBO.class, fields);
        if (!CollectionUtils.isEmpty(boList)) {
            //查询账户year年month月余额
            for (BankRecordCollectBO bo : boList) {
                List<BankRecord> list = findBalance(year, month, bo.getAccountId());
                if (!CollectionUtils.isEmpty(list)) {
                    bo.setYear(year);
                    bo.setMonth(month);
                    bo.setBalance(list.get(0).getBalance());
                    bo.setId(list.get(0).getId());
                    bo.setMakeMoney(bo.getCreditorCost() - bo.getDebtorCost());
                    bo.setMakeMoney(new BigDecimal(bo.getMakeMoney()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
            }
            double debtorCost = boList.stream().filter(p -> p.getDebtorCost() != null).mapToDouble(BankRecordCollectBO::getDebtorCost).sum();
            double creditorCost = boList.stream().filter(p -> p.getCreditorCost() != null).mapToDouble(BankRecordCollectBO::getCreditorCost).sum();
            double balance = boList.stream().filter(p -> p.getBalance() != null).mapToDouble(BankRecordCollectBO::getBalance).sum();
            BankRecordCollectBO totalBO = new BankRecordCollectBO(debtorCost, creditorCost, balance, year, month, "合计");
            totalBO.setMakeMoney(creditorCost - debtorCost);
            boList.add(totalBO);
            return boList;
        } else {
            return null;
        }
    }


    public List<BankRecord> findBalance(Integer year, Integer month, String accountId) throws SerException {
        StringBuilder sql = new StringBuilder("select id,balance from bankrecords_bankrecord bankrecord  where 0 = 0");
        sql.append(" and bankrecord.recordYear = " + year);
        sql.append(" and bankrecord.recordMonth = " + month);
        sql.append(" and bankrecord.accountId = '" + accountId + "'");
        sql.append(" ORDER BY bankrecord.recordDate desc");
        sql.append(" limit 1");
        String[] balanceField = new String[]{"id", "balance"};
        return super.findBySql(sql.toString(), BankRecord.class, balanceField);
    }

    public List<BankRecord> findBalanceByTime(String startTime, String endTime) throws SerException {
        StringBuilder sql = new StringBuilder("select balance from bankrecords_bankrecord bankrecord  where 0 = 0");
        sql.append(" and bankrecord.recordDate between '" + startTime + "' and '" + endTime + "' ");
        sql.append(" ORDER BY bankrecord.recordDate desc");
        String[] balanceField = new String[]{"balance"};
        return super.findBySql(sql.toString(), BankRecord.class, balanceField);
    }

    public List<BankRecordCollectBO> findByMonth(Integer year, Integer month, String accountId) throws SerException {
        StringBuilder sql = new StringBuilder("SELECT");
        sql.append(" info.bank AS bank, bankrecord.creditorCost, bankrecord.debtorCost, bankrecord.balance");
        sql.append(" FROM bankrecords_bankrecord bankrecord LEFT JOIN bankrecords_bankaccountinfo info ON bankrecord.accountId = info.id");
        sql.append(" WHERE 0 = 0 ");
        sql.append(" and bankrecord.recordYear = " + year);
        sql.append(" and bankrecord.recordMonth = " + month);
        sql.append(" and bankrecord.accountId = '" + accountId + "'");
        sql.append(" ORDER BY bankrecord.recordDate desc");
        sql.append(" limit 1");
        String[] balanceField = new String[]{"bank", "creditorCost", "debtorCost", "balance"};
        return super.findBySql(sql.toString(), BankRecordCollectBO.class, balanceField);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<BankRecordAnalyzeBO> analyze(Integer year, Integer month, String[] accountIds) throws SerException {
        List<BankRecordAnalyzeBO> boList = new ArrayList<BankRecordAnalyzeBO>();
        //未选择时，按所有账户分组查询
        if (accountIds == null || accountIds.length == 0) {
            StringBuilder accountSql = new StringBuilder(" select DISTINCT accountId from bankrecords_bankrecord ");
            String[] accountIdStr = new String[]{"accountId"};
            List<BankRecordBO> accountIdList = super.findBySql(accountSql.toString(), BankRecordBO.class, accountIdStr);
            if (!CollectionUtils.isEmpty(accountIdList)) {
                List<String> strTemps = new ArrayList<String>();
                for (BankRecordBO bo : accountIdList) {
                    strTemps.add(bo.getAccountId());
                }
                accountIds = (String[]) strTemps.toArray(new String[strTemps.size()]);
            } else {
                return null;
            }
        }
        for (int i = 0; i < accountIds.length; i++) {
            BankRecordAnalyzeBO bo = new BankRecordAnalyzeBO();
            bo.setYear(year);
            bo.setMonth(month);
            //查询accountIds账户year年month月最后的银行流水记录
            List<BankRecordCollectBO> cuuretnBO = findByMonth(year, month, accountIds[i]);
            if (!CollectionUtils.isEmpty(cuuretnBO)) {
                bo.setBank(cuuretnBO.get(0).getBank());
                bo.setCurrentBalance(cuuretnBO.get(0).getBalance());
                bo.setCurrentCreditorCost(cuuretnBO.get(0).getCreditorCost());
                bo.setCurrentDebtorCost(cuuretnBO.get(0).getDebtorCost());
            }
            //查询accountIds账户year年(month-1)月最后的银行流水记录
            Integer lastMonth = month == 1 ? 12 : (month - 1);
            Integer lastYear = month == 1 ? (year - 1) : year;
            List<BankRecordCollectBO> lastBO = findByMonth(lastYear, lastMonth, accountIds[i]);
            if (!CollectionUtils.isEmpty(lastBO)) {
                bo.setLastBalance(lastBO.get(0).getBalance());
                bo.setLastCreditorCost(lastBO.get(0).getCreditorCost());
                bo.setLastDebtorCost(lastBO.get(0).getDebtorCost());
            } else {
                bo.setLastBalance(0.0);
                bo.setLastCreditorCost(0.0);
                bo.setLastDebtorCost(0.0);
            }
            if (!CollectionUtils.isEmpty(cuuretnBO)) {
                double lastCreditorCost = bo.getLastCreditorCost();
                double lastDebtorCost = bo.getLastDebtorCost();
                //计算差额
                double creditorSubtract = bo.getCurrentCreditorCost() - lastCreditorCost;
                double debtorSubtract = bo.getCurrentDebtorCost() - lastDebtorCost;
                bo.setCreditorSubtract(creditorSubtract);
                bo.setDebtorSubtract(debtorSubtract);
                //计算增长率
                if (lastCreditorCost == 0) {
                    if (creditorSubtract == 0) {
                        bo.setCreditorGrow(0.0);
                    } else {
                        bo.setCreditorGrow(1.0);
                    }
                } else {

                    bo.setCreditorGrow(creditorSubtract / lastCreditorCost);
                    bo.setCreditorGrow(new BigDecimal(bo.getCreditorGrow()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
                if (lastDebtorCost == 0) {
                    if (debtorSubtract == 0) {
                        bo.setDebtorGrow(0.0);
                    } else {
                        bo.setDebtorGrow(1.0);
                    }
                } else {
                    bo.setDebtorGrow(debtorSubtract / lastDebtorCost);
                    bo.setDebtorGrow(new BigDecimal(bo.getDebtorGrow()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
                //计算比率
                BankRecordDTO allDTO = new BankRecordDTO();//查询总流水
                allDTO.getConditions().add(Restrict.eq("accountId", accountIds[i]));
                List<BankRecord> allList = super.findByCis(allDTO);

                double allDebtorCost = allList.stream().filter(p -> p.getDebtorCost() != null).mapToDouble(BankRecord::getDebtorCost).sum();
                double allCreditorCost = allList.stream().filter(p -> p.getCreditorCost() != null).mapToDouble(BankRecord::getCreditorCost).sum();

                Double debtorRateTemp = bo.getCurrentDebtorCost() / allDebtorCost;
                Double creditorRateTemp = bo.getCurrentCreditorCost() / allCreditorCost;

                DecimalFormat format = new DecimalFormat("#.00");
                String creditorRate = null;
                String debtorRate = null;
                if (debtorRateTemp.doubleValue() == 0.0) {
                    debtorRate = "0.00%";
                } else {
                    if (allDebtorCost == 0) {

                    } else {
                        debtorRate = format.format(debtorRateTemp * 100) + "%";
                    }
                }
                // bo.setDebtorRate(debtorRate);

                if (creditorRateTemp.doubleValue() == 0.0) {
                    creditorRate = "0.00%";
                } else {
                    if (allCreditorCost == 0.0) {

                    } else {
                        creditorRate = format.format(creditorRateTemp * 100) + "%";
                    }
                }
                //bo.setCreditorRate(creditorRate);
                boList.add(bo);
            }
        }
        return boList;
    }

    /**
     * 银行分析
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<BankRecordAnalyzeBO> analyzeTo(String startDate, String endDate, String[] accountIds) throws SerException {
        String token = RpcTransmit.getUserToken();
        // RpcTransmit.transmitUserToken(token);
//        String token = RpcTransmit.getUserToken();
        String systemId = userAPI.currentSysNO();
        RpcTransmit.transmitUserToken(token);
        if (startDate != null && endDate != null) {
            //String s = baseParameterAPI.findDoudap();
            String s="2013-12-30";
            if (s != null) {
                LocalDate beginDateTime = LocalDate.parse(s);//账套会计期间启用日期这是把它转为时间类型
                if (LocalDate.parse(startDate).isAfter(beginDateTime)) {//这里是对比账套会计期间启用日期

                    BankRecordAnalyzeBO bankRecordAnalyzeBO = new BankRecordAnalyzeBO();
                    BankSummaryDTO bankSummaryDTO = new BankSummaryDTO();
                    bankSummaryDTO.setStartDate(startDate);
                    bankSummaryDTO.setEndDate(endDate);
                    String sql = null;
                    String pin = null;
                    Integer lastMonth = 0;
                    Integer lastYear = 0;
                    List<BankRecordAnalyzeBO> brblist = new ArrayList<>();
                    List<BankSummaryBO> lastMonthList = new ArrayList<>();
                    List<BankSummaryBO> list =bankSummarySer.backfilterQuery(bankSummaryDTO,accountIds);
                    for (int i = 0; i < list.size(); i++) {
//                        list.get(i).getIncomeCreditAmount();//本月收入
//                        list.get(i).getExpenseDebitAmount();//本月支出
//                        list.get(i).getYear();//年
//                        list.get(i).getMonth();//月
//                        list.get(i).getTheDateOf();//年月
//                        list.get(i).getBankName();//银行名称
//                        list.get(i).getAmountOfThisMonth();//差额
                        lastMonth = list.get(i).getMonth() == 1 ? 12 : (list.get(i).getMonth() - 1);
                        lastYear = list.get(i).getMonth() == 1 ? (list.get(i).getYear() - 1) : list.get(i).getYear();


                        sql = "select ifnull(sum(debtorCost), 0) as expenseDebitAmount,ifnull(sum(creditorCost), 0) as incomeCreditAmount from bankrecords_bankrecord  where recordYear='" + lastYear + "' and recordMonth='" + lastMonth + "' and accountId='"+list.get(i).getAccountId()+"' ";
                        lastMonthList = super.findBySql(sql, BankSummaryBO.class, new String[]{"expenseDebitAmount", "incomeCreditAmount"});
                        if (org.apache.commons.lang3.StringUtils.isBlank(lastMonthList.get(0).getId())) {
                            lastMonthList.get(0).setIncomeCreditAmount(0.0);//上个月的收入
                            lastMonthList.get(0).setExpenseDebitAmount(0.0);//上个月的支出

                        }
                        bankRecordAnalyzeBO.setBank(list.get(i).getBankName());//银行名称
                        bankRecordAnalyzeBO.setCurrentCreditorCost(list.get(i).getExpenseDebitAmount());//本月支出
                        bankRecordAnalyzeBO.setLastDebtorCost(lastMonthList.get(0).getExpenseDebitAmount());//上个月支出
                        bankRecordAnalyzeBO.setCurrentDebtorCost(list.get(i).getIncomeCreditAmount());//本月收入
                        bankRecordAnalyzeBO.setLastCreditorCost(lastMonthList.get(0).getIncomeCreditAmount());//上个月收入
                        bankRecordAnalyzeBO.setCreditorSubtract(list.get(i).getIncomeCreditAmount() - lastMonthList.get(0).getIncomeCreditAmount());//收入差额
                        bankRecordAnalyzeBO.setDebtorSubtract(list.get(i).getExpenseDebitAmount() - lastMonthList.get(0).getExpenseDebitAmount());//支出差额
                        bankRecordAnalyzeBO.setCreditorRate(list.get(i).getIncomeCreditAmount() / (list.get(i).getIncomeCreditAmount() + list.get(i).getExpenseDebitAmount()));//收入比例
                        bankRecordAnalyzeBO.setDebtorRate(list.get(i).getExpenseDebitAmount() / (list.get(i).getIncomeCreditAmount() + list.get(i).getExpenseDebitAmount()));//支出比例额
                        bankRecordAnalyzeBO.setTheDateOf(list.get(i).getTheDateOf());//日期;
                        if(lastMonthList.get(0).getIncomeCreditAmount()==0.0) {
                            bankRecordAnalyzeBO.setCreditorGrow(0.0);
                        }else {
                            bankRecordAnalyzeBO.setCreditorGrow((list.get(i).getIncomeCreditAmount() - lastMonthList.get(0).getIncomeCreditAmount()) / lastMonthList.get(0).getIncomeCreditAmount());//收入增长率

                        }
                        if(lastMonthList.get(0).getIncomeCreditAmount()==0.0) {
                            bankRecordAnalyzeBO.setDebtorGrow(0.0);
                        }else {
                            bankRecordAnalyzeBO.setDebtorGrow((list.get(i).getExpenseDebitAmount() - lastMonthList.get(0).getExpenseDebitAmount()) / lastMonthList.get(0).getExpenseDebitAmount());//支出增长率

                        }
                        brblist.add(bankRecordAnalyzeBO);

                    }
                    return brblist;
                } else {
                    throw new SerException("不能低于账套会计间启用日期");
                }
            } else {
                throw new SerException("开始和结束不能空");
            }
        }
        return null;
    }

    @Override
    public BankRecordCompareBO compare(Integer year, Integer month) throws SerException {
        BankRecordCompareBO bo = new BankRecordCompareBO();
        bo.setYear(year);
        bo.setMonth(month);
        bo.setBankBalance(0.0);
        bo.setBankBalance(0.0);
        bo.setBalanceSubtract(0.0);
//        MonthCollectBO fundBO = fundRecordAPI.month(year,month);

        String sql = " select sum(balance) as balance from bankrecords_bankrecord where" +
                " recordYear = " + year + " and recordMonth = " + month;
        List<BankRecord> list = super.findBySql(sql, BankRecord.class, new String[]{"balance"});

        if (list != null && !list.isEmpty()) {
            if (list.get(0).getBalance() != null) {
                bo.setBankBalance(list.get(0).getBalance());
            }
//            if (fundBO != null) {
//                BigDecimal bigDecimal = new BigDecimal(fundBO.getCurrentBalance());
//
//                bo.setFundBalance(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//                bo.setBalanceSubtract(bo.getBankBalance() - fundBO.getCurrentBalance());
//            }
        }
        return bo;
    }

    /**
     * 新对比
     */
    @Override
    public BankRecordCompareBO compareTo(Integer years, Integer month) throws SerException {
//        String datetime = years + "-" + month;
////        String token = RpcTransmit.getUserToken();
////        RpcTransmit.transmitUserToken(token);
//        if (years != null && month != null) {
//            MonthCollectBO fundBO  = fundRecordAPI.month(years,month);
//            BankRecordCompareBO bankRecordCompareBO = new BankRecordCompareBO();
//            String sql = "select ANY_VALUE(sum(balance)) as balance,date_format(recordDate,'%y-%m') as recordDate from bankrecords_bankrecord where recordDate year='"+years+"' and month='"+month+"' group by recordDate ";
//            List<BankRecordBO> list = super.findBySql(sql, BankRecordBO.class, new String[]{"balance", "recordDate"});
//            if (list != null && !list.isEmpty()) {
//                bankRecordCompareBO.setFundBalance(list.get(0).getBalance() - bo.getAmount());
//                bankRecordCompareBO.setBankBalance(list.get(0).getBalance());
//                bankRecordCompareBO.setBalanceSubtract(bo.getAmount());
//                bankRecordCompareBO.setYears(list.get(0).getRecordDate());
//            }
//            return bankRecordCompareBO;
//        } else {
//            throw new SerException("不能空");
        return null;

    }


    @Override
    public List<BankRecordBO> findByCondition(Integer year, Integer month, String number) throws SerException {

        if (!StringUtils.isEmpty(number)) {
            BankAccountInfoDTO infoDTO = new BankAccountInfoDTO();
            infoDTO.getConditions().add(Restrict.eq("number", number));
            BankAccountInfo accountInfo = bankAccountInfoSer.findOne(infoDTO);
            if (!StringUtils.isEmpty(accountInfo)) {
                BankRecordDTO dto = new BankRecordDTO();
                dto.getSorts().add("createTime=desc");
                dto.getConditions().add(Restrict.eq("accountId", accountInfo.getId()));
                dto.getConditions().add(Restrict.eq("year", year));
                dto.getConditions().add(Restrict.eq("month", month));
                List<BankRecordBO> boList = BeanTransform.copyProperties(super.findByCis(dto), BankRecordBO.class);
                if (boList != null && !boList.isEmpty()) {
                    for (BankRecordBO bo : boList) {
                        setFormat(bo);
                    }
                }
                return boList;
            } else {
                throw new SerException("账号不存在!");
            }

        } else {
            throw new SerException("账号不能为空");
        }
    }

    @Override
    public List<BankRecordCollectBO> collectByCondition(Integer year, Integer month, String number) throws SerException {
        BankRecordDTO dto = new BankRecordDTO();
        String bank = "";
        if (!StringUtils.isEmpty(number)) {

            BankAccountInfoDTO infoDTO = new BankAccountInfoDTO();
            infoDTO.getConditions().add(Restrict.eq("number", number));
            BankAccountInfo info = bankAccountInfoSer.findOne(infoDTO);
            if (info != null) {
                bank = info.getBank();
                dto.getConditions().add(Restrict.eq("accountId", info.getId()));
            } else {
                throw new SerException("账户名称不存在!");
            }

        }
        dto.getConditions().add(Restrict.eq("year", year));
        dto.getConditions().add(Restrict.eq("month", month));
        return collectCombine(dto, bank, year, month);
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = to.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case DELETE:
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
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case ANALYZE:
                flag = guideSeeIdentity();
                break;
            case COMPARE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（部门级别）
     */
    private Boolean guideAddIdentity() throws SerException {
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

    public void insertModels(HSSFSheet sheet, BankRecordTO to) throws SerException {
        int rowNum = sheet.getLastRowNum();
        if (rowNum == 0) {
            throw new SerException("导入的Excel银行流水记录不能为空!");
        }
        int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();

        Boolean judegeFormat = false;

        for (int i = 1; i <= rowNum; i++) {
            BankRecord model = new BankRecord();
            //校验时间格式
            judgeDate(model, to, sheet, i);

            model.setAccountId(to.getAccountId());
//            model.setCreditorCost(sheet.getRow(i).getCell(to.getCreditorCostIndex()).getNumericCellValue());
//            model.setDebtorCost(sheet.getRow(i).getCell(to.getDebtorCostIndex()).getNumericCellValue());
//            model.setBalance(sheet.getRow(i).getCell(to.getBalanceIndex()).getNumericCellValue());

            for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                if ("交易日".equals(sheet.getRow(0).getCell(j).getStringCellValue())) {
                    model.setRecordDate(sheet.getRow(i).getCell(j).getStringCellValue());
                }
                if ("借方金额".equals(sheet.getRow(0).getCell(j).getStringCellValue())) {
                    model.setDebtorCost(org.apache.commons.lang3.StringUtils.isBlank(sheet.getRow(i).getCell(j).getStringCellValue()) ? 0.0 :
                            Double.parseDouble(sheet.getRow(i).getCell(j).getStringCellValue()));
                }
                if ("贷方金额".equals(sheet.getRow(0).getCell(j).getStringCellValue())) {
                    model.setCreditorCost(org.apache.commons.lang3.StringUtils.isBlank(sheet.getRow(i).getCell(j).getStringCellValue()) ? 0.0 :
                            Double.parseDouble(sheet.getRow(i).getCell(j).getStringCellValue()));
                }
                if ("余额".equals(sheet.getRow(0).getCell(j).getStringCellValue())) {
                    model.setBalance(org.apache.commons.lang3.StringUtils.isBlank(sheet.getRow(i).getCell(j).getStringCellValue()) ? 0.0 :
                            Double.parseDouble(sheet.getRow(i).getCell(j).getStringCellValue()));
                }
            }


            List<BankRecordDetail> detailList = new ArrayList<BankRecordDetail>();
            for (int j = 0; j < cellNum; j++) {
                detailList.add(saveDetail(model, sheet, i, j));
            }
            if (!CollectionUtils.isEmpty(detailList)) {

                if (!judegeFormat) {
                    //判断该账号是否已经存在银行流水记录，如果存在检查本次导入的Excel数据格式是否对应已存在的Excel数据格式,只需要检查第一行可以确定
                    BankRecordDTO dto = new BankRecordDTO();
                    dto.getConditions().add(Restrict.eq("accountId", to.getAccountId()));
                    dto.setLimit(1);
                    dto.getSorts().add("createTime=asc");
                    List<BankRecordBO> boList = BeanTransform.copyProperties(super.findByPage(dto), BankRecordBO.class);
                    if (!CollectionUtils.isEmpty(boList) && !boList.get(0).getId().equals(model.getId())) {
                        BankRecordDetailDTO detailDTO = new BankRecordDetailDTO();
                        detailDTO.getConditions().add(Restrict.eq("bankRecord.id", boList.get(0).getId()));
                        List<BankRecordDetail> details = bankRecordDetailSer.findByCis(detailDTO);

                        List<Detail> formats = format(details);
                        if (formats.size() == detailList.size()) {
                            for (int k = 0; k < formats.size(); k++) {
                                if (!formats.get(k).getTitle().equals(detailList.get(k).getTitle())) {
                                    throw new SerException("导入的Excel数据内容格式该账户已导入的数据不一致,请检查导入的Excel!");
                                }
                            }
                        } else {
                            throw new SerException("导入的Excel数据内容格式该账户已导入的数据不一致,请检查导入的Excel!");
                        }
                    }
                    judegeFormat = true;
                }
                model.setDetailList(detailList);
            } else {
                throw new SerException("导入的Excel银行流水记录不能为空!");
            }
            super.save(model);
        }
    }

    public void insertModels(XSSFSheet sheet, BankRecordTO to) throws SerException {
        int rowNum = sheet.getLastRowNum();
        if (rowNum == 0) {
            throw new SerException("导入的Excel银行流水记录不能为空!");
        }
        int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();

        Boolean judegeFormat = false;

        for (int i = 1; i <= rowNum; i++) {
            BankRecord model = new BankRecord();
            //校验时间格式
//            judgeDate(model, to, sheet, i);

            model.setAccountId(to.getAccountId());//让前端传一个账户id过来
//            model.setCreditorCost(sheet.getRow(i).getCell(to.getCreditorCostIndex()).getNumericCellValue());
//            model.setDebtorCost(sheet.getRow(i).getCell(to.getDebtorCostIndex()).getNumericCellValue());
//            model.setBalance(sheet.getRow(i).getCell(to.getBalanceIndex()).getNumericCellValue());


            for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                System.out.println("hang:" + sheet.getRow(0).getCell(j).getStringCellValue());
                if ("交易日".equals(sheet.getRow(0).getCell(j).getStringCellValue())) {


                    Date date = sheet.getRow(i).getCell(j).getDateCellValue();
                    Instant instant = date.toInstant();
                    ZoneId zoneId = ZoneId.systemDefault();
                    LocalDate localDate1 = instant.atZone(zoneId).toLocalDate();
                    model.setRecordDate(localDate1.toString());
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(model.getRecordDate())) {
                        LocalDate localDate = LocalDate.parse(model.getRecordDate());
                        model.setYear(localDate.getYear());
                        model.setMonth(localDate.getMonthValue());
                    }

                }
                if ("借方金额".equals(sheet.getRow(0).getCell(j).getStringCellValue())) {
                    model.setDebtorCost(sheet.getRow(i).getCell(j).getNumericCellValue());
                }
                if ("贷方金额".equals(sheet.getRow(0).getCell(j).getStringCellValue())) {
                    model.setCreditorCost(sheet.getRow(i).getCell(j).getNumericCellValue());
                }
                if ("余额".equals(sheet.getRow(0).getCell(j).getStringCellValue())) {
                    model.setBalance(sheet.getRow(i).getCell(j).getNumericCellValue());
                }
            }


            List<BankRecordDetail> detailList = new ArrayList<BankRecordDetail>();
            for (int j = 0; j < cellNum; j++) {
                detailList.add(saveDetail(model, sheet, i, j));
            }
            if (!CollectionUtils.isEmpty(detailList)) {

               /* if (!judegeFormat) {
                    //判断该账号是否已经存在银行流水记录，如果存在检查本次导入的Excel数据格式是否对应已存在的Excel数据格式,只需要检查第一行可以确定
                    BankRecordDTO dto = new BankRecordDTO();
                    dto.getConditions().add(Restrict.eq("accountId", to.getAccountId()));
                    dto.setLimit(1);
                    dto.getSorts().add("createTime=asc");
                    List<BankRecordBO> boList = BeanTransform.copyProperties(super.findByPage(dto), BankRecordBO.class);
                    if (!CollectionUtils.isEmpty(boList) && !boList.get(0).getId().equals(model.getId())) {
                        BankRecordDetailDTO detailDTO = new BankRecordDetailDTO();
                        detailDTO.getConditions().add(Restrict.eq("bankRecord.id", boList.get(0).getId()));
                        List<BankRecordDetail> details = bankRecordDetailSer.findByCis(detailDTO);

                        List<Detail> formats = format(details);
                        if (formats.size() == detailList.size()) {
                            for (int k = 0; k < formats.size(); k++) {
                                if (!formats.get(k).getTitle().equals(detailList.get(k).getTitle())) {
                                    throw new SerException("导入的Excel数据内容格式该账户已导入的数据不一致,请检查导入的Excel!");
                                }
                            }
                        } else {
                            throw new SerException("导入的Excel数据内容格式该账户已导入的数据不一致,请检查导入的Excel!");
                        }
                    }
                    judegeFormat = true;
                }*/
                model.setDetailList(detailList);
            } else {
                throw new SerException("导入的Excel银行流水记录不能为空!");
            }
            super.save(model);
        }
    }

    //校验日期
    public void judgeDate(BankRecord model, BankRecordTO to, HSSFSheet sheet, Integer row) throws SerException {
        //校验Excel时间格式，由于不同银行导出的时间格式都不一致，都不符合则抛出异常
        // ----判断是否日期格式
        switch (sheet.getRow(row).getCell(to.getRecordDateIndex()).getCellTypeEnum()) {
            case STRING:
                try {
                    LocalDateTime localDateTime = DateUtil.parseDateTime(sheet.getRow(row).getCell(to.getRecordDateIndex()).getStringCellValue());
                    setProperty(model, sheet, row, localDateTime);
                } catch (Exception e2) {
                    try {
                        LocalDate localDate = DateUtil.parseDate(sheet.getRow(row).getCell(to.getRecordDateIndex()).getStringCellValue());
                        setProperty(model, sheet, row, localDate.atStartOfDay());
                    } catch (Exception e3) {
                        StringBuilder sb = new StringBuilder(sheet.getRow(row).getCell(to.getRecordDateIndex()).getStringCellValue());
                        sb.insert(4, "-");
                        sb.insert(7, "-");
                        try {
                            LocalDateTime localDateTime = DateUtil.parseDateTime(sb.toString());
                            setProperty(model, sheet, row, localDateTime);
                        } catch (Exception e4) {
                            try {
                                LocalDate localDate = DateUtil.parseDate(sb.toString());
                                setProperty(model, sheet, row, localDate.atStartOfDay());
                            } catch (Exception e5) {
                                throw new SerException("第" + (row + 1) + "行" + (to.getRecordDateIndex() + 1) + "列的日期格式不符合\"yyyy-MM-dd\"或\"yyyy-MM-dd HH:mm:ss\"中任一格式!");
                            }
                        }
                    }
                }
                break;
            default:
                try {
                    LocalDateTime localDateTime = DateUtil.changeLocaDateTime(sheet.getRow(row).getCell(to.getRecordDateIndex()).getDateCellValue());
                    setProperty(model, sheet, row, localDateTime);
                } catch (Exception e1) {
                    throw new SerException("第" + (row + 1) + "行" + (to.getRecordDateIndex() + 1) + "列的日期格式不符合");
                }
        }
    }

    //校验日期
    public void judgeDate(BankRecord model, BankRecordTO to, XSSFSheet sheet, Integer row) throws SerException {
        //校验Excel时间格式，由于不同银行导出的时间格式都不一致，都不符合则抛出异常
        // ----判断是否日期格式
        switch (sheet.getRow(row).getCell(to.getRecordDateIndex()).getCellTypeEnum()) {
            case STRING:
                try {
                    LocalDateTime localDateTime = DateUtil.parseDateTime(sheet.getRow(row).getCell(to.getRecordDateIndex()).getStringCellValue());
                    setProperty(model, sheet, row, localDateTime);
                } catch (Exception e2) {
                    try {
                        LocalDate localDate = DateUtil.parseDate(sheet.getRow(row).getCell(to.getRecordDateIndex()).getStringCellValue());
                        setProperty(model, sheet, row, localDate.atStartOfDay());
                    } catch (Exception e3) {
                        StringBuilder sb = new StringBuilder(sheet.getRow(row).getCell(to.getRecordDateIndex()).getStringCellValue());
                        sb.insert(4, "-");
                        sb.insert(7, "-");
                        try {
                            LocalDateTime localDateTime = DateUtil.parseDateTime(sb.toString());
                            setProperty(model, sheet, row, localDateTime);
                        } catch (Exception e4) {
                            try {
                                LocalDate localDate = DateUtil.parseDate(sb.toString());
                                setProperty(model, sheet, row, localDate.atStartOfDay());
                            } catch (Exception e5) {
                                throw new SerException("第" + (row + 1) + "行" + (to.getRecordDateIndex() + 1) + "列的日期格式不符合\"yyyy-MM-dd\"或\"yyyy-MM-dd HH:mm:ss\"中任一格式!");
                            }
                        }
                    }
                }
                break;
            default:
                try {
                    LocalDateTime localDateTime = DateUtil.changeLocaDateTime(sheet.getRow(row).getCell(to.getRecordDateIndex()).getDateCellValue());
                    setProperty(model, sheet, row, localDateTime);
                } catch (Exception e1) {
                    throw new SerException("第" + (row + 1) + "行" + (to.getRecordDateIndex() + 1) + "列的日期格式不符合");
                }
        }
    }

    public void setProperty(BankRecord model, HSSFSheet sheet, Integer row, LocalDateTime localDateTime) {
        model.setRecordDate(localDateTime.toString());
        model.setYear(localDateTime.getYear());
        model.setMonth(localDateTime.getMonthValue());
    }

    public void setProperty(BankRecord model, XSSFSheet sheet, Integer row, LocalDateTime localDateTime) {
        model.setRecordDate(localDateTime.toString());
        model.setYear(localDateTime.getYear());
        model.setMonth(localDateTime.getMonthValue());
    }

    public BankRecordDetail saveDetail(BankRecord model, HSSFSheet sheet, Integer row, Integer cell) throws SerException {

        BankRecordDetail detail = new BankRecordDetail();
        detail.setBankRecord(model);
        detail.setTitleIndex(cell);
        detail.setTitle(sheet.getRow(0).getCell(cell).getStringCellValue());
        //如果为数字格式，需要手动转为String
        String cellStr = "";
        Double cellInt = null;
        try {
            cellStr = sheet.getRow(row).getCell(cell).getStringCellValue();
        } catch (Exception e) {
            try {
                cellInt = sheet.getRow(row).getCell(cell).getNumericCellValue();
            } catch (Exception e1) {
                throw new SerException(e.getMessage());
            }
        }
        if (cellInt != null) {
            detail.setVal(cellInt.toString());
        } else {
            detail.setVal(cellStr);
        }
        return detail;
    }

    public BankRecordDetail saveDetail(BankRecord model, XSSFSheet sheet, Integer row, Integer cell) throws SerException {
        BankRecordDetail detail = new BankRecordDetail();
        detail.setBankRecord(model);
        detail.setTitleIndex(cell);
        detail.setTitle(sheet.getRow(0).getCell(cell).getStringCellValue());
        //如果为数字格式，需要手动转为String
        String cellStr = "";
        Double cellInt = null;
        try {
            cellStr = sheet.getRow(row).getCell(cell).getStringCellValue();
        } catch (Exception e) {
            try {
                cellInt = sheet.getRow(row).getCell(cell).getNumericCellValue();
            } catch (Exception e1) {
                throw new SerException(e.getMessage());
            }
        }
        if (cellInt != null) {
            detail.setVal(cellInt.toString());
        } else {
            detail.setVal(cellStr);
        }
        return detail;
    }

    public void setBoInfo(BankRecordBO bo) throws SerException {
        BankAccountInfo info = bankAccountInfoSer.findById(bo.getAccountId());
        if (info != null) {
            bo.setBank(info.getBank());
            bo.setBankAddress(info.getBankAddress());
            bo.setCardNumber(info.getCardNumber());
            bo.setNumber(info.getNumber());
            bo.setCompany(info.getCompany());
            bo.setName(info.getName());
            bo.setType(info.getType());
        }
    }

    /**
     * 通过流获取文件信息
     *
     * @param obj
     * @return
     */
    private String getFileInfo(Object obj) {
        String json_info = new String((byte[]) obj);
        if (!StringUtils.isEmpty(json_info)) {
            json_info = json_info.replaceAll("\\\\", "");
            json_info = json_info.substring(1, json_info.length() - 1);
//            FileInfo fileInfo = JSON.parseObject(json_info, FileInfo.class);
//            return fileInfo.getFileName();
        }
        return null;
    }

    @Override
    public Double balanceByMonth(String startTime, String endTime) throws SerException {

        Double totalBalance = 0.0;
        List<BankRecord> list = findBalanceByTime(startTime, endTime);
        totalBalance = list.stream().filter(p -> p.getBalance() != null).mapToDouble(p -> p.getBalance()).sum();
        return totalBalance;
    }

    @Override
    public Boolean sonPermission() throws SerException {
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

//    @Override
//    public byte[] collectExcel(Integer year, Integer month, String[] accountName) throws SerException {
//
//        List<BankRecordsCollectExcel> excelList = new ArrayList<BankRecordsCollectExcel>();
//        List<BankRecordCollectBO> boList = collect(year, month, accountName);
//        if (!CollectionUtils.isEmpty(boList)) {
//            for (BankRecordCollectBO bo : boList) {
//                BankRecordsCollectExcel excel = new BankRecordsCollectExcel();
//                BeanUtils.copyProperties(bo, excel);
//                excelList.add(excel);
//            }
//        } else {
//            excelList.add(new BankRecordsCollectExcel());
//        }
//
//        Excel excel = new Excel(0, 2);
//        byte[] bytes = ExcelUtil.clazzToExcel(excelList, excel);
//        return bytes;
//    }
//

    @Override
    public byte[] collectExcel(String startTime,String endTime,String[] accountIds) throws SerException {
        BankSummaryDTO bankSummaryDTO=new BankSummaryDTO();
        bankSummaryDTO.setStartDate(startTime);
        bankSummaryDTO.setEndDate(endTime);
        List<BankSummaryExcel> excelList = new ArrayList<BankSummaryExcel>();

        List<BankSummaryBO> boList =bankSummarySer.backfilterQuery(bankSummaryDTO,accountIds);

        if (!CollectionUtils.isEmpty(boList)) {
            for (BankSummaryBO bo : boList) {
                BankSummaryExcel excel = new BankSummaryExcel();
                BeanUtils.copyProperties(bo, excel);
                excelList.add(excel);
            }
        } else {
            excelList.add(new BankSummaryExcel());
        }

        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(excelList, excel);
        return bytes;
    }


    public List<BankRecordCollectBO> collectCombine(BankRecordDTO dto, String bank, Integer year, Integer month) throws SerException {
        List<BankRecordCollectBO> boList = BeanTransform.copyProperties(super.findByCis(dto), BankRecordCollectBO.class);
        if (boList != null && !boList.isEmpty()) {
            Double debtorCost = 0.0;
            Double creditorCost = 0.0;
            Double balance = 0.0;
            for (BankRecordCollectBO bo : boList) {
                bo.setBank(bank);
                if (bo.getDebtorCost() != null) {
                    debtorCost = debtorCost + bo.getDebtorCost();
                }
                if (bo.getCreditorCost() != null) {
                    creditorCost = creditorCost + bo.getCreditorCost();
                }
                if (bo.getBalance() != null) {
                    balance = balance + bo.getBalance();
                }
            }
            BankRecordCollectBO bo = new BankRecordCollectBO(debtorCost, creditorCost, balance, year, month, "合计");
            boList.add(bo);
        }
        return boList;
    }

//    @Override
//    public byte[] analyzeExcel(Integer year, Integer month, String[] accountIds) throws SerException {
//        List<BankRecordsAnalyzeExcel> excelList = new ArrayList<BankRecordsAnalyzeExcel>();
//        List<BankRecordAnalyzeBO> boList = analyze(year, month, accountIds);
//        if (!CollectionUtils.isEmpty(boList)) {
//            for (BankRecordAnalyzeBO bo : boList) {
//                BankRecordsAnalyzeExcel excel = new BankRecordsAnalyzeExcel();
//                BeanUtils.copyProperties(bo, excel);
//                excelList.add(excel);
//            }
//        } else {
//            excelList.add(new BankRecordsAnalyzeExcel());
//        }
//
//        Excel excel = new Excel(0, 2);
//        byte[] bytes = ExcelUtil.clazzToExcel(excelList, excel);
//        return bytes;
//    }
    @Override
    public byte[] analyzeExcel(String startDate,String endDate, String[] accountIds) throws SerException {
        List<BankRecordsAnalyzeExcel> excelList = new ArrayList<BankRecordsAnalyzeExcel>();
        List<BankRecordAnalyzeBO> boList=analyzeTo(startDate, endDate, accountIds);
        if (!CollectionUtils.isEmpty(boList)) {
            for (BankRecordAnalyzeBO bo : boList) {
                BankRecordsAnalyzeExcel excel = new BankRecordsAnalyzeExcel();
                BeanUtils.copyProperties(bo, excel);
                excelList.add(excel);
            }
        } else {
            excelList.add(new BankRecordsAnalyzeExcel());
        }

        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(excelList, excel);
        return bytes;
    }



    /**
     * 银行流水导出
     *
     * @param accountIds
     * @return
     */
    @Override
    public byte[] bankRecordExcel(String accountIds) throws SerException {
        String sql="select val from bankrecords_bankrecorddetail where bankRecord_id in (select id from bankrecords_bankrecord where accountId='"+accountIds+"') AND title='交易日'";//交易日
        String sql1="select val from bankrecords_bankrecorddetail where bankRecord_id in (select id from bankrecords_bankrecord where accountId='"+accountIds+"') AND title='交易时间'";//交易时间
        String sql2="select val from bankrecords_bankrecorddetail where bankRecord_id in (select id from bankrecords_bankrecord where accountId='"+accountIds+"') AND title='起息日'";//起息日

        String sql3="select val from bankrecords_bankrecorddetail where bankRecord_id in (select id from bankrecords_bankrecord where accountId='"+accountIds+"') AND title='交易类型'";//交易类型
        String sql4="select val from bankrecords_bankrecorddetail where bankRecord_id in (select id from bankrecords_bankrecord where accountId='"+accountIds+"') AND title='借方金额'";//借方金额
        String sql5="select val from bankrecords_bankrecorddetail where bankRecord_id in (select id from bankrecords_bankrecord where accountId='"+accountIds+"') AND title='贷方金额'";//贷方金额
        String sql6="select val from bankrecords_bankrecorddetail where bankRecord_id in (select id from bankrecords_bankrecord where accountId='"+accountIds+"') AND title='余额'";//借方金额
        String sql7="select val from bankrecords_bankrecorddetail where bankRecord_id in (select id from bankrecords_bankrecord where accountId='"+accountIds+"') AND title='摘要'";//贷方金额
        String [] s=new String[]{"val"};
        List<BankRecordDetail> list=super.findBySql(sql,BankRecordDetail.class,s);
        List<BankRecordDetail> list1=super.findBySql(sql1,BankRecordDetail.class,s);
        List<BankRecordDetail> list2=super.findBySql(sql2,BankRecordDetail.class,s);
        List<BankRecordDetail> list3=super.findBySql(sql3,BankRecordDetail.class,s);
        List<BankRecordDetail> list4=super.findBySql(sql4,BankRecordDetail.class,s);
        List<BankRecordDetail> list5=super.findBySql(sql5,BankRecordDetail.class,s);
        List<BankRecordDetail> list6=super.findBySql(sql6,BankRecordDetail.class,s);
        List<BankRecordDetail> list7=super.findBySql(sql7,BankRecordDetail.class,s);
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("银行流水");
        sheet.setDefaultColumnWidth(20);
        XSSFRow row = sheet.createRow(0);//创建行从0开始
        XSSFCell cell = row.createCell(0);//创建列从0开始
        row.createCell(0).setCellValue("交易日");
        row.createCell(1).setCellValue("交易时间");
        row.createCell(2).setCellValue("起息日");
        row.createCell(3).setCellValue("交易类型");
        row.createCell(4).setCellValue("借方金额");
        row.createCell(5).setCellValue("贷方金额");
        row.createCell(6).setCellValue("余额");
        row.createCell(7).setCellValue("摘要");
        for (int i=0;i<list.size();i++){
           row=sheet.createRow(i+1);
           row.createCell(0).setCellValue(list.get(i).getVal());
            row.createCell(1).setCellValue(list1.get(i).getVal());
            row.createCell(2).setCellValue(list2.get(i).getVal());
            row.createCell(3).setCellValue(list3.get(i).getVal());
            row.createCell(4).setCellValue(list4.get(i).getVal());
            row.createCell(5).setCellValue(list5.get(i).getVal());
            row.createCell(6).setCellValue(list6.get(i).getVal());
            row.createCell(7).setCellValue(list7.get(i).getVal());
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return os.toByteArray();
    }

}