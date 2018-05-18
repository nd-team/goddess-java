package com.bjike.goddess.fundrecords.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.api.AccountAPI;
import com.bjike.goddess.financeinit.api.BaseParameterAPI;
import com.bjike.goddess.financeinit.bo.AccountBO;
import com.bjike.goddess.financeinit.bo.BaseParameterBO;
import com.bjike.goddess.financeinit.dto.AccountDTO;
import com.bjike.goddess.financeinit.dto.BaseParameterDTO;
import com.bjike.goddess.financeinit.entity.Account;
import com.bjike.goddess.fundrecords.api.FundRecordAPI;
import com.bjike.goddess.fundrecords.bo.FundRecordBO;
import com.bjike.goddess.fundrecords.bo.FundRecordCollectBO;
import com.bjike.goddess.fundrecords.dto.CapitalFlowRecordCollectDTO;
import com.bjike.goddess.fundrecords.dto.CapitalFlowRecordDTO;
import com.bjike.goddess.fundrecords.dto.FundRecordDTO;
import com.bjike.goddess.fundrecords.entity.FundRecord;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import com.bjike.goddess.voucher.entity.VoucherGenerate;
import com.bjike.goddess.voucher.excel.SubjectIntroExport;
import org.apache.commons.lang3.StringUtils;
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
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Author: [xiexiaoting]
 * @Date: [2018-04-03 14:06]
 * @Description: [ 资金流水记录]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "capitalFlowRecordSerCache")
@Service
public class CapitalFlowRecordSerImpl extends ServiceImpl<FundRecord, FundRecordDTO> implements CapitalFlowRecordSer {


    @Autowired
    private ModuleAPI moduleAPI;

    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;

    @Autowired
    private FundRecordSerImpl fundRecordSerImpl;

    @Autowired
    private AccountAPI accountAPI;

    @Autowired
    private BaseParameterAPI baseParameterAPI;

    @Autowired
    private FundRecordAPI fundRecordAPI;


    /*
    * 获取资金流水记录列表
    * */
    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<FundRecordBO> listRecord(FundRecordDTO dto) throws SerException {
        String token = RpcTransmit.getUserToken();
        List<FundRecord> fundRecordList = new ArrayList<>();

        if (!dto.isLastest()) {
            //todo 查询条件
            List<FundRecord> fundList = super.findByCis(dto, true);

            long result = super.count((new FundRecordDTO()));
            if (result != 0) {  //本模块有数据,直接返回
                return BeanTransform.copyProperties(fundList, FundRecordBO.class);
            }
        }

        List<VoucherGenerateBO> voucherList = new ArrayList<>();   // 记账凭证
        BigDecimal amount = new BigDecimal(0.0);
        RpcTransmit.transmitUserToken(token);
        List<AccountBO> boList = accountAPI.listAccount(new AccountDTO());
        if (boList != null) {
            for (AccountBO bo : boList) {
                amount = amount.add(new BigDecimal(bo.getAmount() == null ? 0.0 : bo.getAmount()));
            }
        }

        RpcTransmit.transmitUserToken(token);


        long count = voucherGenerateAPI.findByCourseName().size();
        Long calculation = (count/1000)+1;
        for (int i = 1; i <= calculation; i ++) {
            VoucherGenerateDTO dto1 = new VoucherGenerateDTO();
            dto1.setPage(i);
            dto1.setLimit(1000);

            List<VoucherGenerateBO> voucherList1 = voucherGenerateAPI.findByCourseName();
            voucherList.addAll(voucherList1);
        }

        if (voucherList != null && voucherList.size() > 0) {
            FundRecord fundRecord2 = new FundRecord();
            LocalDate localDate = LocalDate.parse(voucherList.get(0).getVoucherDate());
            localDate = localDate.minusDays(1);
            fundRecord2.setRecordDate(localDate); // 日期可改
            fundRecord2.setArea(null);
            fundRecord2.setProject(null);
            fundRecord2.setProjectGroup(null);
            fundRecord2.setDigest(null);
            fundRecord2.setIncome(null);
            fundRecord2.setExpenditure(null);
            fundRecord2.setAmount(amount.doubleValue());
            fundRecordList.add(fundRecord2);

            for (VoucherGenerateBO voucherBO : voucherList) {
                FundRecord fundRecord = new FundRecord();
                fundRecord.setRecordDate(LocalDate.parse(voucherBO.getVoucherDate())); // 日期可改
                fundRecord.setArea(voucherBO.getArea());
                fundRecord.setProject(voucherBO.getProjectName());
                fundRecord.setProjectGroup(voucherBO.getProjectGroup());
                fundRecord.setDigest(voucherBO.getSumary());
                fundRecord.setIncome(voucherBO.getBorrowMoney());
                fundRecord.setExpenditure(voucherBO.getLoanMoney());
                fundRecord.setAmount(amount.doubleValue() + voucherBO.getBorrowMoney() - voucherBO.getLoanMoney());
                StringBuilder str = new StringBuilder(voucherBO.getSource());
                str.append("-" + voucherBO.getFirstSubject());
                if (StringUtils.isNotBlank(voucherBO.getSecondSubject())) {
                    str.append("-" + voucherBO.getSecondSubject());
                }
                if (StringUtils.isNotBlank(voucherBO.getThirdSubject())) {
                    str.append("-" + voucherBO.getThirdSubject());
                }
                fundRecord.setDataSource(str.toString());

                fundRecordList.add(fundRecord);
                amount = new BigDecimal(fundRecord.getAmount());

            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        save(fundRecordList);
                    } catch (SerException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
        return BeanTransform.copyProperties(fundRecordList, FundRecordBO.class);
    }

    /**
     * 保存资金流水
     *
     * @param list
     * @throws SerException
     */
    private void save(List<FundRecord> list) throws SerException {
        /*for (FundRecord entity : list) {
            FundRecordDTO dto = new FundRecordDTO();
            dto.getConditions().add(Restrict.eq("area", entity.getArea()));
            dto.getConditions().add(Restrict.eq("dataSource", entity.getDataSource()));
            dto.getConditions().add(Restrict.eq("amount", entity.getAmount()));
            dto.getConditions().add(Restrict.eq("area", entity.getArea()));
            dto.getConditions().add(Restrict.eq("area", entity.getArea()));
            dto.getConditions().add(Restrict.eq("area", entity.getArea()));
            dto.getConditions().add(Restrict.eq("area", entity.getArea()));
            dto.setProject(entity.getProject());
            //todo
            FundRecord old = super.findOne(dto);
            if (old == null) {
                super.save(entity);
            }
        }
*/
        String sql = "select id from fundrecords_fundrecord";
        String[] arr = new String[] {"id"};
        List<FundRecord> bos = super.findBySql(sql, FundRecord.class, arr);
        int len = bos.size();
        if (len > 1000) {
            int index = 0;
            for (int i = 0; i < len - 1000; i = i + 1000) {
                List<FundRecord> bos1 = bos.subList(i, i + 999);
                super.remove(bos1);
                index = i;
            }
            List<FundRecord> bos1 = bos.subList(index + 1000, len);
            super.remove(bos1);

        } else {
            super.remove(bos);
        }

//        super.save(list);
        int len1 = list.size();
        if (len1 > 1000) {
            int index = 0;
            for (int i = 0; i < len1 - 1000; i = i + 1000) {
                List<FundRecord> bos1 = list.subList(i, i + 999);
                super.save(bos1);
                index = i;
            }
            List<FundRecord> bos1 = list.subList(index + 1000, len1);
            super.save(bos1);
        } else {
            super.save(list);
        }
        System.out.println("更新数据结束");

    }

    /*
    * 删除资金流水记录
    * */
    @Override
    public void deleteFundRecord() throws SerException {
        String delSql = "delete from fundrecords_fundrecord";
        super.executeSql(delSql);
    }


    /*
   * 全局搜索分页查询资金流水记录
   * */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<FundRecordBO> searchByCondition(CapitalFlowRecordDTO dto, FundRecordDTO fundRecordDTO) throws SerException {
//        FundRecordDTO dto1 = new FundRecordDTO();
//        dto1.setLastest(true);
//        this.listRecord(dto1);

        int startRow = dto.getPage() * dto.getLimit();
        int endRow = (dto.getPage() + 1) * dto.getLimit();
        List<FundRecordBO> fundRecordBOList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("select recordDate as recordDate,area as area,project as project,projectGroup as projectGroup,digest as digest,income as income,expenditure as expenditure,amount as amount,dataSource as dataSource from fundrecords_fundrecord where 1=1");

        if (StringUtils.isNotBlank(dto.getStartTime()) && StringUtils.isNotBlank(dto.getEndTime())) {
            sb.append(" and ( cast ( recordDate as date ) between '" + dto.getStartTime() + "' and '" + dto.getEndTime() + "') ");
        }
        if (StringUtils.isNotBlank(dto.getArea())) {
            sb.append(" and area = '" + dto.getArea() + "'");
        }
        if (StringUtils.isNotBlank(dto.getProject())) {
            sb.append(" and project = '" + dto.getProject() + "'");
        }
        if (StringUtils.isNotBlank(dto.getProjectGroup())) {
            sb.append(" and projectGroup = '" + dto.getProjectGroup() + "'");
        }

        sb.append(" ORDER BY recordDate limit " + startRow + ", " + endRow + " ");
        String[] fileds = new String[]{"recordDate", "area", "project", "projectGroup", "digest", "income", "expenditure", "amount", "dataSource"};
        List<FundRecordBO> list = super.findBySql(sb.toString(), FundRecordBO.class, fileds);

//        return fundRecordSerImpl.sortPageList(list,fundRecordDTO);
        return list;
    }

    /*
    * 查询资金流水记录总数
    * */
    @Override
    public Long countRecord(FundRecordDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getDataSource())) {
            dto.getConditions().add(Restrict.like("dataSource", dto.getDataSource()));
        }
        Long count = super.count(dto);
        return count;
    }

    /*
    * 处理数据同步记账凭证和资金流水删除地区字段相应数据
    * */
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteArea(String id) throws SerException {
//        if(StringUtils.isNotBlank(id) ){
//            throw new SerException("未选中删除的数据!");
//        }
        VoucherGenerateBO generate = voucherGenerateAPI.getById(id);
        StringBuffer sql = new StringBuffer();
        sql.append("  delete from fundrecords_fundrecord WHERE area='" + generate.getArea() + "' AND project='" + generate.getProjectName() + "'");
        super.executeSql(sql.toString());

    }

    /**
     * 资金流水记录获取第一条数据
     *
     * @throws SerException
     */
    @Override
    public void generateData() throws SerException {

        List<FundRecord> fundList = new ArrayList<>();
        List<VoucherGenerateBO> voucherList = new ArrayList<>();
        VoucherGenerate voucherGenerate = new VoucherGenerate();
        List<AccountBO> accountBOList = new ArrayList<>();
        Account account = new Account();

        double bor = 0d;
        double loa = 0d;
        double count = 0d;
        for (VoucherGenerateBO vouList : voucherList) {
            for (int i = 0; i <= voucherList.size(); i++) {
                bor = vouList.getBorrowMoney();
                bor++;
            }
            for (int j = 0; j <= voucherList.size(); j++) {
                loa = vouList.getLoanMoney();
                loa++;
            }
        }
        for (AccountBO acount : accountBOList) {
            for (int i = 0; i <= accountBOList.size(); i++) {
                count = acount.getAmount();
                count++;
            }
        }

        FundRecord fundRecord = new FundRecord();
        fundRecord.setIncome(bor);
        fundRecord.setExpenditure(loa);
        fundRecord.setAmount(count + bor - loa);
        fundRecord.setDigest(String.valueOf(account.getAmount())); // 转为String类型
        fundRecord.setDataSource(" 第一条数据为空 ");
        fundRecord.setArea(" 第一条数据为空 ");
        fundRecord.setProject(" 第一条数据为空 ");
        fundRecord.setProjectGroup(" 第一条数据为空 ");
        fundRecord.setVoucherGenerateId(" 第一条数据为空 ");

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime befordate = account.getCreateTime();
        String date = df.format(befordate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance(); // 获得日历
        try {
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DAY_OF_MONTH, -1);  // 计算前一天日期
        String getdate = sdf.format(calendar.getTime()); // 获得前一天日期
        LocalDate time = LocalDate.parse(getdate, df);  // 转为LocalDate 类型
        fundRecord.setRecordDate(time);  // 日期为开账日期前一天

        fundList.add(fundRecord);
    }


    @Override
    public void deleteVoucherGenerate(String id) throws SerException {
        voucherGenerateAPI.deleteVoucherGenerate(id);
    }

    @Override
    public void listRecordTask() throws SerException {
        FundRecordDTO dto = new FundRecordDTO();
        dto.setLastest(true);
        this.listRecord(dto);
    }


    /**
     * 获取开账时间财务初始化基本参数的账套会计期间启用日期xiexiaoting
     * @return baseBOList
     * @throws SerException
     */
    @Override
    public List<BaseParameterBO> listBaseDate(BaseParameterDTO dto)throws SerException{
        List<BaseParameterBO> baseBOList = baseParameterAPI.listBasicPara(new BaseParameterDTO());
        return BeanTransform.copyProperties(baseBOList,BaseParameterBO.class);
    }

    @Override
    public byte[] exportExcel(CapitalFlowRecordCollectDTO dto) throws SerException {
        Map<Integer, List<Integer>> integerListMap = new HashMap<>();
        if(dto.getType().equals("1") && dto.getType()!=null){  // 日期
            List<FundRecordCollectBO> list=fundRecordAPI.monthSumma(dto.getStartTime(),dto.getEndTime()) ;


            Excel excel = new Excel(0, 2);
            byte[] bytes = ExcelUtil.clazzToExcel(list, excel);
            XSSFWorkbook wb = null;
            ByteArrayOutputStream os = null;
            try {
                InputStream is = new ByteArrayInputStream(bytes);
                wb = new XSSFWorkbook(is);
                XSSFSheet sheet;
                sheet = wb.getSheetAt(0);
                //主表行数
                int rowSize = list.size();
                List<Field> fields = ClazzUtils.getFields(SubjectIntroExport.class); //获得列表对象属性
                List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);

                int index = 0;
                int lastRow = 0;
                for (int j = 0; j < rowSize; j++) {

                    int x = 0;
                    //List<int> maxList所有子表的长度
                    if (null != integerListMap.get(j) && integerListMap.get(j).size() > 0) {
//                    PositionWorkDetails mainPwd = list.get(j);
                        int mergeRowCount = integerListMap.get(j).get(j);
                        //5
                        if (mergeRowCount != 1) {
                            int firstRow = index;
                            lastRow = firstRow + mergeRowCount - 1;
                            //合并主表共33列
                            for (int i = 1; i < 3; i++) {
                                //1,5
                                sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, i, i));
                                x = 3;
                            }
                            //合并模快
                            Map<Integer, List<Integer>> mergeMLen = integerListMap;
                            //获取规划模块合并长度数据
                            List<Integer> ghMergeLen = mergeMLen.get(j);
                            if (null != ghMergeLen && ghMergeLen.size() > 0) {
                                int mfirstRow = firstRow;
                                int mMergeRowCount = 0;
                                for (Integer mi : ghMergeLen) {
                                    if (mi != 1) {
                                        int mlastRow = (firstRow - 1) + mMergeRowCount + mi;
                                        for (int z = x; z < x + 19; z++) {
                                            sheet.addMergedRegion(new CellRangeAddress(mfirstRow, mlastRow, z, z));
                                        }
                                        x += 19;
                                        mfirstRow = mlastRow + 1;
                                        mMergeRowCount = mMergeRowCount + mi;
                                    }
                                }
                            }

                            lastRow--;
                        }
                    }
                    lastRow++;
                    index = lastRow + 1;
                }


                os = new ByteArrayOutputStream();
                wb.write(os);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if(dto.getType().equals("2") && dto.getType()!=null){  // 地区
            fundRecordAPI.areaSumma(dto.getStartTime(),dto.getEndTime(),dto.getArea());
        }
        if(dto.getType().equals("3") && dto.getType()!=null){ // 项目组汇总
            fundRecordAPI.projectSumma(dto.getStartTime(),dto.getEndTime(),dto.getProjectGroup());
        }
        if(dto.getType().equals("4") && dto.getType()!=null){  // 项目名称汇总
            fundRecordAPI.projectNameSumma(dto.getStartTime(),dto.getEndTime(),dto.getProject());
        }

        //导出


        return new byte[0];
    }


}
