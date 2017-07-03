package com.bjike.goddess.bankrecords.service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.bankrecords.beanlist.Detail;
import com.bjike.goddess.bankrecords.bo.*;
import com.bjike.goddess.bankrecords.dto.BankAccountInfoDTO;
import com.bjike.goddess.bankrecords.dto.BankRecordDTO;
import com.bjike.goddess.bankrecords.dto.BankRecordDetailDTO;
import com.bjike.goddess.bankrecords.entity.BankAccountInfo;
import com.bjike.goddess.bankrecords.entity.BankRecord;
import com.bjike.goddess.bankrecords.entity.BankRecordDetail;
import com.bjike.goddess.bankrecords.to.BankRecordTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.fundrecords.api.FundRecordAPI;
import com.bjike.goddess.fundrecords.bo.MonthCollectBO;
import com.bjike.goddess.storage.to.FileInfo;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    @Autowired
    private FundRecordAPI fundRecordAPI;

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
    @Transactional(rollbackFor = SerException.class)
    public void upload(BankRecordTO to) throws SerException {
        try {
            List<InputStream> inputStreams = to.getInputStreams();
            if (inputStreams != null && !inputStreams.isEmpty()) {

                String fileName = getFileInfo(inputStreams.get(0));
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                //判断文件后缀名
                if (suffixName.equals(".xls") || suffixName.equals(".XLS") ||
                        suffixName.equals(".xlsx") || suffixName.equals(".XLSX")) {
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
    @Transactional(rollbackFor = SerException.class)
    public List<BankRecordBO> pageList(BankRecordDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        dto.getConditions().add(Restrict.eq("accountId", dto.getAccountId()));
        List<BankRecordBO> boList = BeanTransform.copyProperties(super.findByPage(dto), BankRecordBO.class);
        if (boList != null && !boList.isEmpty()) {
            for (BankRecordBO bo : boList) {
                setFormat(bo);
            }
        }
        return boList;
    }

    public BankRecordBO setFormat(BankRecordBO bo) throws SerException {
        setBoInfo(bo);

        BankRecordDetailDTO detailDTO = new BankRecordDetailDTO();
        detailDTO.getConditions().add(Restrict.eq("bankRecordId", bo.getId()));
        List<BankRecordDetail> detailList = bankRecordDetailSer.findByCis(detailDTO);
        if (detailList != null && !detailList.isEmpty()) {
            List<Detail> list = BeanTransform.copyProperties(detailList, Detail.class);
            //按表头下标排序，还原Excel字段排列格式
            Collections.sort(list, new Comparator<Detail>() {
                @Override
                public int compare(Detail bo1, Detail bo2) {
                    return bo1.getTitleIndex().compareTo(bo2.getTitleIndex());
                }
            });
            bo.setDetailList(list);
        }
        return bo;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        BankRecord model = super.findById(id);
        if (model != null) {
            super.remove(model);
            BankRecordDetailDTO detailDTO = new BankRecordDetailDTO();
            detailDTO.getConditions().add(Restrict.eq("bankRecordId", model.getId()));
            List<BankRecordDetail> detailList = bankRecordDetailSer.findByCis(detailDTO);
            if (detailList != null && !detailList.isEmpty()) {
                bankRecordDetailSer.remove(detailList);
            }
        } else {
            throw new SerException("删除对象不能为空!");
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
    public List<BankRecordCollectBO> collect(Integer year, Integer month, String accountName) throws SerException {
        BankRecordDTO dto = new BankRecordDTO();
        String bank = "";
        if (!StringUtils.isEmpty(accountName)) {

            BankAccountInfoDTO infoDTO = new BankAccountInfoDTO();
            infoDTO.getConditions().add(Restrict.eq("name", accountName));
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

    @Override
    @Transactional(rollbackFor = SerException.class)
    public BankRecordAnalyzeBO analyze(Integer year, Integer month, String accountName) throws SerException {
        BankRecordDTO currentDTO = new BankRecordDTO();
        BankRecordDTO lastDTO = new BankRecordDTO();
        BankRecordDTO allDTO = new BankRecordDTO();
        String bank = "";
        if (!StringUtils.isEmpty(accountName)) {

            BankAccountInfoDTO infoDTO = new BankAccountInfoDTO();
            infoDTO.getConditions().add(Restrict.eq("name", accountName));
            BankAccountInfo info = bankAccountInfoSer.findOne(infoDTO);
            if (info != null) {
                bank = info.getBank();
                currentDTO.getConditions().add(Restrict.eq("accountId", info.getId()));
                lastDTO.getConditions().add(Restrict.eq("accountId", info.getId()));
            } else {
                throw new SerException("账户名称不存在!");
            }
        }
        currentDTO.getConditions().add(Restrict.eq("year", year));
        currentDTO.getConditions().add(Restrict.eq("month", month));
        lastDTO.getConditions().add(Restrict.eq("year", year));
        lastDTO.getConditions().add(Restrict.eq("month", month - 1));

        List<BankRecord> currentList = super.findByCis(currentDTO);
        List<BankRecord> lastList = super.findByCis(lastDTO);
        List<BankRecord> allList = super.findByCis(allDTO);

        Double allDebtorCost = allList.stream().filter(p -> p.getDebtorCost() != null).mapToDouble(BankRecord::getDebtorCost).sum();
        Double allCreditorCost = allList.stream().filter(p -> p.getCreditorCost() != null).mapToDouble(BankRecord::getCreditorCost).sum();
        Double currentDebtorCost = currentList.stream().filter(p -> p.getDebtorCost() != null).mapToDouble(BankRecord::getDebtorCost).sum();
        Double currentCreditorCost = currentList.stream().filter(p -> p.getCreditorCost() != null).mapToDouble(BankRecord::getCreditorCost).sum();
        Double currentBalance = currentList.stream().filter(p -> p.getBalance() != null).mapToDouble(BankRecord::getBalance).sum();
        Double lastDebtorCost = lastList.stream().filter(p -> p.getDebtorCost() != null).mapToDouble(BankRecord::getDebtorCost).sum();
        Double lastCreditorCost = lastList.stream().filter(p -> p.getCreditorCost() != null).mapToDouble(BankRecord::getCreditorCost).sum();
        Double lastBalance = lastList.stream().filter(p -> p.getBalance() != null).mapToDouble(BankRecord::getBalance).sum();

        Double creditorSubtract = currentCreditorCost - lastCreditorCost;
        Double debtorSubtract = currentDebtorCost - lastDebtorCost;

        Double creditorGrow = null;
        Double debtorGrow = null;

        if (lastCreditorCost != 0) {
            creditorGrow = creditorSubtract / lastCreditorCost;
        }
        if (lastDebtorCost != 0) {
            debtorGrow = debtorSubtract / lastDebtorCost;
        }

        DecimalFormat format = new DecimalFormat("#.00");

        String creditorRate = null;
        String debtorRate = null;
        if (allDebtorCost != null && allDebtorCost != 0) {
            creditorRate = format.format(creditorSubtract / allDebtorCost) + "%";
            debtorRate = format.format(debtorSubtract / allCreditorCost) + "%";
        }
        BankRecordAnalyzeBO bo = new BankRecordAnalyzeBO(bank, year, month,
                currentDebtorCost, currentCreditorCost, currentBalance,
                lastDebtorCost, lastCreditorCost, lastBalance, creditorSubtract, debtorSubtract,
                creditorRate, debtorRate, creditorGrow, debtorGrow
        );
        return bo;
    }

    @Override
    public BankRecordCompareBO compare(Integer year, Integer month) throws SerException {
        BankRecordCompareBO bo = new BankRecordCompareBO();
        bo.setYear(year);
        bo.setMonth(month);
        bo.setBankBalance(0.0);
        bo.setBankBalance(0.0);
        bo.setBalanceSubtract(0.0);
        MonthCollectBO fundBO = fundRecordAPI.month(year, month);

        String sql = " select sum(balance) as balance from bankrecords_bankrecord where" +
                " recordYear = " + year + " and recordMonth = " + month;
        List<BankRecord> list = super.findBySql(sql, BankRecord.class, new String[]{"balance"});

        if (list != null && !list.isEmpty()) {
            if (list.get(0).getBalance() != null) {
                bo.setBankBalance(list.get(0).getBalance());
            }
            if (fundBO != null) {
                bo.setFundBalance(fundBO.getCurrentBalance());
                bo.setBalanceSubtract(bo.getBankBalance() - fundBO.getCurrentBalance());
            }
        }
        return bo;
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
                dto.getConditions().add(Restrict.eq("accountId", dto.getAccountId()));
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

    public void insertModels(HSSFSheet sheet, BankRecordTO to) throws SerException {
        int rowNum = sheet.getLastRowNum();
        int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();
        for (int i = 1; i <= rowNum; i++) {
            BankRecord model = new BankRecord();
            //校验时间格式
            judgeDate(model, to, sheet, i);

            model.setAccountId(to.getAccountId());
            model.setCreditorCost(sheet.getRow(i).getCell(to.getCreditorCostIndex()).getNumericCellValue());
            model.setDebtorCost(sheet.getRow(i).getCell(to.getDebtorCostIndex()).getNumericCellValue());
            model.setBalance(sheet.getRow(i).getCell(to.getBalanceIndex()).getNumericCellValue());
            super.save(model);
            for (int j = 0; j < cellNum; j++) {
                saveDetail(model, sheet, i, j);
            }
        }

    }

    public void insertModels(XSSFSheet sheet, BankRecordTO to) throws SerException {
        int rowNum = sheet.getLastRowNum();
        int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();
        for (int i = 1; i <= rowNum; i++) {

            BankRecord model = new BankRecord();
            //校验时间格式
            judgeDate(model, to, sheet, i);

            model.setAccountId(to.getAccountId());
            model.setCreditorCost(sheet.getRow(i).getCell(to.getCreditorCostIndex()).getNumericCellValue());
            model.setDebtorCost(sheet.getRow(i).getCell(to.getDebtorCostIndex()).getNumericCellValue());
            model.setBalance(sheet.getRow(i).getCell(to.getBalanceIndex()).getNumericCellValue());
            super.save(model);
            for (int j = 0; j < cellNum; j++) {
                saveDetail(model, sheet, i, j);
            }
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

    public void saveDetail(BankRecord model, HSSFSheet sheet, Integer row, Integer cell) throws SerException {

        BankRecordDetail detail = new BankRecordDetail();
        detail.setBankRecordId(model.getId());
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
        bankRecordDetailSer.save(detail);

    }

    public void saveDetail(BankRecord model, XSSFSheet sheet, Integer row, Integer cell) throws SerException {
        BankRecordDetail detail = new BankRecordDetail();
        detail.setBankRecordId(model.getId());
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
        bankRecordDetailSer.save(detail);
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
            FileInfo fileInfo = JSON.parseObject(json_info, FileInfo.class);
            return fileInfo.getFileName();
        }
        return null;
    }
}