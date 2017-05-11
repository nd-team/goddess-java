package com.bjike.goddess.bankrecords.service;

import com.bjike.goddess.bankrecords.beanlist.Detail;
import com.bjike.goddess.bankrecords.bo.BankRecordAnalyzeBO;
import com.bjike.goddess.bankrecords.bo.BankRecordBO;
import com.bjike.goddess.bankrecords.bo.BankRecordCollectBO;
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
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.api.StorageUserAPI;
import com.bjike.goddess.storage.bo.FileBO;
import com.bjike.goddess.storage.constant.PathCommon;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
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
 * @Description: [ 银行流水业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "bankrecordsSerCache")
@Service
public class BankRecordSerImpl extends ServiceImpl<BankRecord, BankRecordDTO> implements BankRecordSer {

    @Autowired
    private StorageUserAPI storageUserAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private BankRecordDetailSer bankRecordDetailSer;
    @Autowired
    private BankAccountInfoSer bankAccountInfoSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<String> check(InputStream inputStream) throws SerException {

        /*if(inputStream!=null){
            HSSFWorkbook wb = null;
            HSSFSheet sheet = null;
            //上传成功后，不管后续业务是否成功都删除调文件
            try {
                wb = new HSSFWorkbook(inputStream);
                sheet = wb.getSheetAt(0);
            } catch (IOException e) {
                throw new SerException(e.getMessage());
            }
            List<String> list = new ArrayList<String>();
            int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();
            for (int i = 0; i < cellNum; i++) {
                list.add(sheet.getRow(0).getCell(i).getStringCellValue());
            }
            return list;
        }else {
            throw new SerException("找不到上传的文件!");
        }*/

        /*if (to.getBytes() != null && to.getBytes().length > 0) {
            String storageToken = RpcTransmit.getStorageToken();
            String path = "/bankRecord";

            File file = uploadExcel(to, path, storageToken);
            HSSFWorkbook wb = null;
            HSSFSheet sheet = null;
            //上传成功后，不管后续业务是否成功都删除调文件
            try {
                wb = new HSSFWorkbook(new FileInputStream(file));
                sheet = wb.getSheetAt(0);
            } catch (IOException e) {
                throw new SerException(e.getMessage());
            } finally {
                RpcTransmit.transmitStorageToken(storageToken);
                fileAPI.delFile(path);
            }
            List<String> list = new ArrayList<String>();
            int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();
            for (int i = 0; i < cellNum; i++) {
                list.add(sheet.getRow(0).getCell(i).getStringCellValue());
            }
            return list;

        } else {
            throw new SerException("请选择需要上传的附件!");
        }*/
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void upload(BankRecordTO to) throws SerException {
        /*String storageToken = RpcTransmit.getStorageToken();
        String path = "/bankRecord";

        File file = uploadExcel(to, path, storageToken);
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;

        XSSFWorkbook xssfWorkbook = null;
        XSSFSheet xssfSheet = null;

        String suffixName = file.getName().substring(file.getName().lastIndexOf("."));
        //不同格式的Excel需要导入不同的包
        if (suffixName.equals(".xls") || suffixName.equals(".XLS")) {
            try {
                wb = new HSSFWorkbook(new FileInputStream(file));
                sheet = wb.getSheetAt(0);
                insertModels(sheet, to, path, storageToken);
            } catch (Exception e) {
                RpcTransmit.transmitStorageToken(storageToken);
                fileAPI.delFile(path);
                throw new SerException(e.getMessage());
            }
        } else if (suffixName.equals(".xlsx") || suffixName.equals(".XLSX")) {
            try {
                xssfWorkbook = new XSSFWorkbook(new FileInputStream(file));
                xssfSheet = xssfWorkbook.getSheetAt(0);
                insertModels(xssfSheet, to, path, storageToken);
            } catch (Exception e) {
                RpcTransmit.transmitStorageToken(storageToken);
                fileAPI.delFile(path);
                throw new SerException(e.getMessage());
            }
        }*/

    }

    @Override
    public List<BankRecordBO> pageList(BankRecordDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<BankRecordBO> bolist = BeanTransform.copyProperties(super.findByPage(dto), BankRecordBO.class);
        if (bolist != null && !bolist.isEmpty()) {
            for (BankRecordBO bo : bolist) {
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
            }
        }
        return bolist;
    }

    @Override
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
    public BankRecordBO find(String id) throws SerException {
        BankRecord model = super.findById(id);
        BankRecordBO bo = null;
        if (model != null) {
            bo = BeanTransform.copyProperties(model, BankRecordBO.class);
            setBoInfo(bo);
        } else {
            throw new SerException("查询对象不能为空!");
        }
        return bo;
    }

    @Override
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
        if(allDebtorCost!=null){
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

    public File uploadExcel(BankRecordTO to, String path, String storageToken) throws SerException {

        FileBO fileBO = fileAPI.uploadSingle(to.getBytes(), path, to.getFileName());
        RpcTransmit.transmitStorageToken(storageToken);
        String filePath = fileAPI.getRealPath(path);
        File file = new File(filePath + PathCommon.SEPARATOR + fileBO.getName());

        if (!file.exists()) {
            throw new SerException("Excel导入失败，请联系研发人员!");
        }
        return file;
    }


    public void insertModels(HSSFSheet sheet, BankRecordTO to, String path, String storageToken) throws SerException {
        int rowNum = sheet.getLastRowNum();
        int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();
        try {
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
        } catch (SerException e) {
            //保存失败删除已经上传的附件
            RpcTransmit.transmitStorageToken(storageToken);
            fileAPI.delFile(path);
            throw new SerException(e.getMessage());
        }
    }

    public void insertModels(XSSFSheet sheet, BankRecordTO to, String path, String storageToken) throws SerException {
        int rowNum = sheet.getLastRowNum();
        int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();
        try {
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
        } catch (SerException e) {
            //保存失败删除已经上传的附件
            RpcTransmit.transmitStorageToken(storageToken);
            fileAPI.delFile(path);
            throw new SerException(e.getMessage());
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
}