package com.bjike.goddess.socialfee.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.socialfee.bo.SocialFeeBO;
import com.bjike.goddess.socialfee.bo.VoucherDataBO;
import com.bjike.goddess.socialfee.dto.SocialFeeDTO;
import com.bjike.goddess.socialfee.dto.SocialFeeVoucherDTO;
import com.bjike.goddess.socialfee.entity.SocialFee;
import com.bjike.goddess.socialfee.entity.SocialFeeVoucher;
import com.bjike.goddess.socialfee.to.SocialFeeTO;
import com.bjike.goddess.socialfee.to.VoucherDataTO;
import com.bjike.goddess.socialfee.utils.DownloadExeclUtil;
import com.bjike.goddess.socialfee.utils.FileUtils;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import com.bjike.goddess.voucher.to.VoucherGenerateTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 社会缴费业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-19 03:25 ]
 * @Description: [ 社会缴费业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "socialfeeSerCache")
@Service
public class SocialFeeSerImpl extends ServiceImpl<SocialFee, SocialFeeDTO> implements SocialFeeSer {

    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;
    @Autowired
    private SocialFeeVoucherSer socialFeeVoucherSer;

    public static final String[] EXCELHEAD = {"缴费所属年","缴费所属月","缴费所属时期","纳税人名称","单位社保号",
            "姓名","身份证明号码","证件号","个人社保号","基本养老保险计费工资","基本养老保险单位","基本养老保险个人",
            "工伤保险计费工资","工伤保险单位","工伤保险个人","失业保险计费工资","失业保险单位","失业保险个人",
            "社会医疗保险计费工资","社会医疗保险单位","社会医疗保险个人","重大疾病医疗补助计费工资","重大疾病医疗补助单位",
            "重大疾病医疗补助个人","生育保险计费工资","生育保险单位","生育保险个人","单位合计","个人合计","应缴金额"};

    @Override
    public Long countSocialFee(SocialFeeDTO socialFeeDTO) throws SerException {
        if (StringUtils.isNotBlank(socialFeeDTO.getPayTime())) {
            socialFeeDTO.getConditions().add(Restrict.eq("payTime", socialFeeDTO.getPayTime()));
        }
        if (StringUtils.isNotBlank(socialFeeDTO.getPayFeer())) {
            socialFeeDTO.getConditions().add(Restrict.eq("payFeer", socialFeeDTO.getPayFeer()));
        }
        if (StringUtils.isNotBlank(socialFeeDTO.getEmpName())) {
            socialFeeDTO.getConditions().add(Restrict.eq("empName", socialFeeDTO.getEmpName()));
        }
        if (StringUtils.isNotBlank(socialFeeDTO.getWorkSocalNum())) {
            socialFeeDTO.getConditions().add(Restrict.eq("workSocalNum", socialFeeDTO.getWorkSocalNum()));
        }
        Long count = super.count(socialFeeDTO);
        return count;
    }

    @Override
    public List<SocialFeeBO> listSocialFee(SocialFeeDTO socialFeeDTO) throws SerException {
        socialFeeDTO.getSorts().add("payTime=asc");
        if (StringUtils.isNotBlank(socialFeeDTO.getPayTime())) {
            socialFeeDTO.getConditions().add(Restrict.eq("payTime", socialFeeDTO.getPayTime()));
        }
        if (StringUtils.isNotBlank(socialFeeDTO.getPayFeer())) {
            socialFeeDTO.getConditions().add(Restrict.eq("payFeer", socialFeeDTO.getPayFeer()));
        }
        if (StringUtils.isNotBlank(socialFeeDTO.getEmpName())) {
            socialFeeDTO.getConditions().add(Restrict.eq("empName", socialFeeDTO.getEmpName()));
        }
        if (StringUtils.isNotBlank(socialFeeDTO.getWorkSocalNum())) {
            socialFeeDTO.getConditions().add(Restrict.eq("workSocalNum", socialFeeDTO.getWorkSocalNum()));
        }
        List<SocialFee> list = super.findByCis(socialFeeDTO, true);

        return BeanTransform.copyProperties(list, SocialFeeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SocialFeeBO addSocialFee(SocialFeeTO socialFeeTO) throws SerException {
        if (StringUtils.isBlank(socialFeeTO.getPayTimeYear())) {
            throw new SerException("年份不能为空");
        }
        if (StringUtils.isBlank(socialFeeTO.getPayTimeMonth())) {
            throw new SerException("月份不能为空");
        }
        sumMoney(socialFeeTO);
        SocialFee socialFee = BeanTransform.copyProperties(socialFeeTO, SocialFee.class, true);
        socialFee.setPayTime(socialFeeTO.getPayTimeYear() + socialFeeTO.getPayTimeMonth());
        socialFee.setCreateTime(LocalDateTime.now());
        super.save(socialFee);
        return BeanTransform.copyProperties(socialFee, SocialFeeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SocialFeeBO editSocialFee(SocialFeeTO socialFeeTO) throws SerException {
        if (StringUtils.isBlank(socialFeeTO.getPayTimeYear())) {
            throw new SerException("年份不能为空");
        }
        if (StringUtils.isBlank(socialFeeTO.getPayTimeMonth())) {
            throw new SerException("月份不能为空");
        }
        sumMoney(socialFeeTO);
        SocialFee socialFee = BeanTransform.copyProperties(socialFeeTO, SocialFee.class, true);
        SocialFee temp = super.findById(socialFeeTO.getId());

        BeanUtils.copyProperties(socialFee, temp, "id", "createTime");
        temp.setPayTime(socialFeeTO.getPayTimeYear() + socialFeeTO.getPayTimeMonth());

        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
        return BeanTransform.copyProperties(socialFee, SocialFeeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteSocialFee(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public SocialFeeBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        SocialFee socialFee = super.findById(id);
        return BeanTransform.copyProperties(socialFee, SocialFeeBO.class);
    }

    @Override
    public List<String> listPayFeer() throws SerException {
        String[] field = new String[]{"payFeer"};
        String sql = " select payFeer ,1 from socialfee_socialfee group by payFeer ";
        List<SocialFee> list = super.findBySql(sql, SocialFee.class, field);
        List<String> payFeerList = list.stream().map(SocialFee::getPayFeer).collect(Collectors.toList());
        return payFeerList;
    }

    @Override
    public List<String> listEmpName() throws SerException {
        String[] field = new String[]{"empName"};
        String sql = " select empName ,1 from socialfee_socialfee group by empName ";
        List<SocialFee> list = super.findBySql(sql, SocialFee.class, field);
        List<String> empNameList = list.stream().map(SocialFee::getEmpName).collect(Collectors.toList());
        return empNameList;
    }

    @Override
    public List<SocialFeeBO> collect(SocialFeeDTO socialFeeDTO) throws SerException {
//        汇总表头：（缴费所属时期/纳税人名称/姓名/应缴金额）
        List<SocialFeeBO> list = new ArrayList<>();
        String start = socialFeeDTO.getStartTime();
        String end = socialFeeDTO.getEndTime();
        String company = socialFeeDTO.getPayFeer();
        String emp = socialFeeDTO.getEmpName();

        Boolean timeFlag = false;
        String[] field = new String[]{"totalMoney"};
        StringBuffer sql = new StringBuffer("");
        String sqlAppend = "";
        String startYearMonth = "";
        String endYearMonth = "";
        if (StringUtils.isNotBlank(start) && StringUtils.isNotBlank(end)) {
            String startYear = LocalDate.parse(start).getYear() + "";
            int startMonthValue = LocalDate.parse(start).getMonthValue();
            String startMonth = startMonthValue < 10 ? "0" + startMonthValue : startMonthValue + "";
            startYearMonth = startYear + startMonth;

            String endYear = LocalDate.parse(end).getYear() + "";
            int endMonthValue = LocalDate.parse(end).getMonthValue();
            String endMonth = endMonthValue < 10 ? "0" + endMonthValue : endMonthValue + "";
            endYearMonth = endYear + endMonth;
            sqlAppend = " and payTime between '" + startYearMonth + "' and '" + endYearMonth + "' ";
            timeFlag = true;
        }
        String payTimeDur = startYearMonth + "-" + endYearMonth;
        if (StringUtils.isBlank(company) && StringUtils.isBlank(emp)) {
            if (timeFlag) {
                sql.append(" select sum(totalMoney) as totalMoney ,1 from socialfee_socialfee where 1=1 ");
                sql.append(sqlAppend);
                list = super.findBySql(sql.toString(), SocialFeeBO.class, field);
                list.stream().forEach(str -> {
                    str.setPayTime(payTimeDur);
                    str.setEmpName("");
                    str.setPayFeer("");
                });
            }
        } else if (StringUtils.isBlank(company) && StringUtils.isNotBlank(emp)) {
            //根据时间段和公司查数据
            ctCom(socialFeeDTO, timeFlag, list, sqlAppend, payTimeDur);
        } else if (StringUtils.isNotBlank(company) && StringUtils.isBlank(emp)) {
            //根据时间段和个人查数据
            ctEmp(socialFeeDTO, timeFlag, list, sqlAppend, payTimeDur);
        } else if (StringUtils.isNotBlank(company) && StringUtils.isNotBlank(emp)) {
            //根据时间段和公司和个人查数据
            ctComAndEmp(socialFeeDTO, timeFlag, list, sqlAppend, payTimeDur);
        }

        return list;
    }

    //根据时间段和公司查数据
    private List<SocialFeeBO> ctCom(SocialFeeDTO socialFeeDTO, Boolean timeFlag, List<SocialFeeBO> list, String sqlAppend, String payTimeDur) throws SerException {
        String company = socialFeeDTO.getPayFeer();
        String emp = socialFeeDTO.getEmpName();

        String[] field = new String[]{"totalMoney"};
        StringBuffer sql = new StringBuffer("");
        sql.append(" select sum(totalMoney) as totalMoney ,1 from socialfee_socialfee where 1=1 ");
        sql.append(" and empName = '" + emp + "' ");
        if (timeFlag) {
            sql.append(sqlAppend);
            list = super.findBySql(sql.toString(), SocialFeeBO.class, field);
            list.stream().forEach(str -> {
                str.setPayTime(payTimeDur);
                str.setEmpName(emp);
                str.setPayFeer("");
            });
        } else {
            list = super.findBySql(sql.toString(), SocialFeeBO.class, field);
            list.stream().forEach(str -> {
                str.setPayTime("");
                str.setEmpName(emp);
                str.setPayFeer("");
            });
        }

        return list;
    }

    //根据时间段和个人查数据
    private List<SocialFeeBO> ctEmp(SocialFeeDTO socialFeeDTO, Boolean timeFlag, List<SocialFeeBO> list, String sqlAppend, String payTimeDur) throws SerException {
        String company = socialFeeDTO.getPayFeer();
        String emp = socialFeeDTO.getEmpName();

        String[] field = new String[]{"totalMoney"};
        StringBuffer sql = new StringBuffer("");
        sql.append(" select sum(totalMoney) as totalMoney ,1 from socialfee_socialfee where 1=1 ");
        sql.append(" and payFeer = '" + company + "' ");
        if (timeFlag) {
            sql.append(sqlAppend);
            list = super.findBySql(sql.toString(), SocialFeeBO.class, field);
            list.stream().forEach(str -> {
                str.setPayTime(payTimeDur);
                str.setEmpName("");
                str.setPayFeer(company);
            });
        } else {
            list = super.findBySql(sql.toString(), SocialFeeBO.class, field);
            list.stream().forEach(str -> {
                str.setPayTime("");
                str.setEmpName("");
                str.setPayFeer(company);
            });
        }

        return list;
    }

    //根据时间段和公司和个人查数据
    private List<SocialFeeBO> ctComAndEmp(SocialFeeDTO socialFeeDTO, Boolean timeFlag, List<SocialFeeBO> list, String sqlAppend, String payTimeDur) throws SerException {
        String company = socialFeeDTO.getPayFeer();
        String emp = socialFeeDTO.getEmpName();

        String[] field = new String[]{"totalMoney"};
        StringBuffer sql = new StringBuffer("");
        sql.append(" select sum(totalMoney) as totalMoney ,1 from socialfee_socialfee where 1=1 ");
        sql.append(" and payFeer = '" + company + "' ");
        sql.append(" and empName = '" + emp + "' ");
        if (timeFlag) {
            sql.append(sqlAppend);
            list = super.findBySql(sql.toString(), SocialFeeBO.class, field);
            list.stream().forEach(str -> {
                str.setPayTime(payTimeDur);
                str.setEmpName(emp);
                str.setPayFeer(company);
            });
        } else {
            list = super.findBySql(sql.toString(), SocialFeeBO.class, field);
            list.stream().forEach(str -> {
                str.setPayTime("");
                str.setEmpName(emp);
                str.setPayFeer(company);
            });
        }
        return list;
    }

    private void sumMoney(SocialFeeTO socialFeeTO) throws SerException {
        Double sumWork = socialFeeTO.getBaseWork() + socialFeeTO.getInjuryWork()
                + socialFeeTO.getSocialMediWork() + socialFeeTO.getIllWork()
                + socialFeeTO.getPregnantWork() + socialFeeTO.getUnemployWork();
        Double sumEmp = socialFeeTO.getBaseEmp() + socialFeeTO.getInjuryEmp()
                + socialFeeTO.getSocialMediEmp() + socialFeeTO.getIllEmp()
                + socialFeeTO.getPregnantEmp() + socialFeeTO.getUnemployEmp();
        socialFeeTO.setWorkMoney(sumWork);
        socialFeeTO.setEmpMoney(sumEmp);
        socialFeeTO.setEmpMoney(sumWork + sumEmp);

    }


    @Override
    public VoucherDataBO vGenerate(String[] ids) throws SerException {
        StringBuffer sb= new StringBuffer("");
        for(String id : ids ){
            sb.append( "'"+id+"'," );
        }
//       "id","payTimeYear","payTimeMonth","payTime","totalMoney"
        SocialFeeDTO socialFeeDTO = new SocialFeeDTO();
        socialFeeDTO.getConditions().add(Restrict.in("id", ids ));
        List<SocialFee> socialFees =  super.findByCis( socialFeeDTO );
        if( socialFees == null ){
            throw  new SerException("您没有选择数据，请选择");
        }
        String time = socialFees.get(0).getPayTime();
        Boolean bo = socialFees.stream().allMatch(str-> time.equals(str));
        if( !bo ){
            throw new SerException("您选择的数据的缴费所属时期不一致，请重新选择");
        }

        Double totalMoney = socialFees.stream().mapToDouble(SocialFee::getTotalMoney).sum();
        int year = Integer.parseInt(socialFees.get(0).getPayTimeYear());
        int month = Integer.parseInt(socialFees.get(0).getPayTimeMonth());
        LocalDate vDate = LocalDate.of( year , month ,1);

        List<VoucherDataBO> list = new ArrayList<>();
        VoucherDataBO voucherDataBO = new VoucherDataBO();
        voucherDataBO.setVoucherDate( String.valueOf(vDate) );
        voucherDataBO.setSumary("缴纳社保");
        voucherDataBO.setSubjects(Arrays.asList("应付职工薪酬-社会保险","银行存款"));
        voucherDataBO.setBorrowMoneys(Arrays.asList(totalMoney,0d));
        voucherDataBO.setLoanMoneys(Arrays.asList(0d,totalMoney));
        voucherDataBO.setAreas("");
        voucherDataBO.setProjects("");
        voucherDataBO.setGroups("");

        return voucherDataBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherDataBO voucher(VoucherDataTO voucherDataTO) throws SerException {
        String sumarys = voucherDataTO.getSumary();
        List<String> subjects = voucherDataTO.getSubjects();
        List<Double> borrows = voucherDataTO.getBorrowMoneys();
        List<Double> loans = voucherDataTO.getLoanMoneys();
        String areas = voucherDataTO.getAreas();
        String projects = voucherDataTO.getProjects();
        String groups = voucherDataTO.getGroups();

        if (StringUtils.isBlank(voucherDataTO.getVoucherDate())) {

            throw new SerException("日期不能为空");
        }

        if (StringUtils.isBlank(sumarys)) {
            throw new SerException("摘要不能为空");
        }
        if (StringUtils.isBlank(areas)) {
            throw new SerException("地区不能为空");
        }
        if (StringUtils.isBlank(projects)) {
            throw new SerException("项目不能为空");
        }
        if (StringUtils.isBlank(groups)) {
            throw new SerException("项目组不能为空");
        }
        List<String> firstSub = new ArrayList<>();
        List<String> secSub = new ArrayList<>();
        List<String> thirdSub = new ArrayList<>();

        for (String str : subjects) {
            if (StringUtils.isBlank(str)) {
                throw new SerException("科目不能为空");
            }
            String[] subs = str.split("-");
            if (subs.length == 1) {
                firstSub.add(subs[0]);
            }
            if (subs.length == 2) {
                secSub.add(subs[1]);
            }
            if (subs.length >= 3) {
                thirdSub.add(subs[2]);
            }
        }
        for (Double str : borrows) {
            if (str == null) {
                throw new SerException("借方金额不能为空");
            }
        }
        for (Double str : loans) {
            if (str == null) {
                throw new SerException("贷方金额不能为空");
            }
        }

        //看是否已经生成过记账凭证 payTimeYear:201704
        LocalDate time = LocalDate.parse( voucherDataTO.getVoucherDate());
        SocialFeeVoucherDTO socialFeeVoucherDTO = new SocialFeeVoucherDTO();
        socialFeeVoucherDTO.getConditions().add(Restrict.eq("payTimeYear", time.getYear()+(time.getMonthValue() <10 ? "0"+time.getMonthValue(): time.getMonthValue()+"")));
        List<SocialFeeVoucher> sfvList = socialFeeVoucherSer.findByCis( socialFeeVoucherDTO );
        if( sfvList != null && sfvList.size()>0 ){
            //说明该年月已经生成过记账凭证，则只能删除再添加
            String[] voucherIds = sfvList.get(0).getVoucherId().split(",");
            for(String id: voucherIds){
                voucherGenerateAPI.deleteVoucherGenerate( id );
            }
        }
        //说明该年月没有生成过记账凭证，则只能添加
        VoucherGenerateTO vgTO = new VoucherGenerateTO();
        vgTO.setVoucherWord("记");
        vgTO.setVoucherDate(voucherDataTO.getVoucherDate());
        vgTO.setSumary(sumarys);
        vgTO.setArea(areas);
        vgTO.setProjectName(projects);
        vgTO.setProjectGroup(groups);
        vgTO.setTicketNum(0d);
        for (int i = 0; i < borrows.size(); i++) {

            vgTO.setFirstSubjects(firstSub);
            vgTO.setSecondSubjects(secSub);
            vgTO.setThirdSubjects(thirdSub);
            vgTO.setBorrowMoneys(borrows);
            vgTO.setLoanMoneys(loans);

        }
        List<VoucherGenerateBO> vgBO = voucherGenerateAPI.addVoucherGenerate(vgTO);
        if( vgBO != null && vgBO.size()>0 ){
            String ids ="";
            for(VoucherGenerateBO vg: vgBO){
                ids +=vg.getId()+",";
            }
            SocialFeeVoucher sfv = new SocialFeeVoucher();
            sfv.setPayTimeYear( time.getYear()+(time.getMonthValue() <10 ? "0"+time.getMonthValue(): time.getMonthValue()+""));
            sfv.setVoucherId( StringUtils.substringBeforeLast(ids,",") );
            sfv.setCreateTime( LocalDateTime.now());
            socialFeeVoucherSer.save( sfv );
        }
        return BeanTransform.copyProperties(voucherDataTO,VoucherDataBO.class);
    }

    @Override
    public String export(SocialFeeDTO socialFeeDTO) throws SerException {
        //查询
        if( StringUtils.isNotBlank(socialFeeDTO.getPayFeer())){
            socialFeeDTO.getConditions().add(Restrict.eq("payFeer",socialFeeDTO.getPayFeer()));
        }if( StringUtils.isNotBlank(socialFeeDTO.getEmpName())){
            socialFeeDTO.getConditions().add(Restrict.eq("empName",socialFeeDTO.getEmpName()));
        }if( StringUtils.isNotBlank(socialFeeDTO.getStartTime()) && StringUtils.isNotBlank(socialFeeDTO.getEndTime())){
            String startYear = LocalDate.parse(socialFeeDTO.getStartTime()).getYear() + "";
            int startMonthValue = LocalDate.parse(socialFeeDTO.getStartTime()).getMonthValue();
            String startMonth = startMonthValue < 10 ? "0" + startMonthValue : startMonthValue + "";
            String startYearMonth = startYear + startMonth;

            String endYear = LocalDate.parse(socialFeeDTO.getEndTime()).getYear() + "";
            int endMonthValue = LocalDate.parse(socialFeeDTO.getEndTime()).getMonthValue();
            String endMonth = endMonthValue < 10 ? "0" + endMonthValue : endMonthValue + "";
            String endYearMonth = endYear + endMonth;
            socialFeeDTO.getConditions().add(Restrict.between("payTime",Arrays.asList("'"+startYearMonth+"'","'"+endYearMonth+"'")));
        }
        List<SocialFee> list =  super.findByCis( socialFeeDTO );

        String tempString = "社会缴费";
        XSSFWorkbook wb = new XSSFWorkbook();//创建一个Excel文件
        XSSFSheet sheet = wb.createSheet(tempString);//创建报销明细工作薄
        sheet.setDefaultRowHeight((short) 300);//设置默认行高
        // 设置execl工作簿中的列名
        String[] excelHeader = EXCELHEAD;//Excel表头
        XSSFCellStyle style = DownloadExeclUtil.titleStyle(wb, IndexedColors.PALE_BLUE.getIndex());  // 设置标题样式
        XSSFRow row = sheet.createRow(0);//创建第一行
        row.setHeight((short) 400);//设置第一行单元格的高度
        for (int i = 0, length = excelHeader.length; i < length; i++) {
            XSSFCell cell = row.createCell(i);
            if (i == 0 || i == 4 || i == 8) {
                sheet.setColumnWidth(i, 2000); //设置单元格的宽
            } else if (i == 18) {
                sheet.setColumnWidth(i, 5000);
            } else {
                sheet.setColumnWidth(i, 4000);
            }
            cell.setCellValue(excelHeader[i]);//设置单元格的值
            cell.setCellStyle(style);   //设置样式
        }


        createRowDetail(list, row, sheet);//填充数据

        String savePath = new StringBuilder(FileUtils.getFilesRePath("function/socialfee/Socialfee/export/")).append(LocalDate.now().toString()).toString();
        return FileUtils.getDownloadPath(FileUtils.saveWBFile(wb, savePath, tempString + ".xlsx"));

    }


    /**
     * @param list  报销记录集合
     * @param row
     * @param sheet Excel表单
     * @description 创建数据行
     */
    public void createRowDetail(List<SocialFee> list, XSSFRow row, XSSFSheet sheet) {
//        public static final String[] EXCELHEAD = {"缴费所属年","缴费所属月","缴费所属时期","纳税人名称","单位社保号",
//                "姓名","身份证明号码","证件号","个人社保号","基本养老保险计费工资","基本养老保险单位","基本养老保险个人",
//                "工伤保险计费工资","工伤保险单位","工伤保险个人","失业保险计费工资","失业保险单位","失业保险个人",
//                "社会医疗保险计费工资","社会医疗保险单位","社会医疗保险个人","重大疾病医疗补助计费工资","重大疾病医疗补助单位",
//                "重大疾病医疗补助个人","生育保险计费工资","生育保险单位","生育保险个人","单位合计","个人合计","应缴金额"};

        int index = 0;
        for (SocialFee socialFee : list) {
            ++index;
            row = sheet.createRow(index);    // 每循环一次创建一行
            int callIndex = 0;
            row.createCell(callIndex++).setCellValue(index);//设置行的索引
            row.createCell(callIndex++).setCellValue(socialFee.getPayTimeYear());//单据编号
            row.createCell(callIndex++).setCellValue(socialFee.getPayTimeMonth());//报销提交时间
            row.createCell(callIndex++).setCellValue(socialFee.getPayTime());//申请报销人
            row.createCell(callIndex++).setCellValue(socialFee.getPayFeer());//获取地区
            row.createCell(callIndex++).setCellValue(socialFee.getWorkSocalNum());//负责人
            row.createCell(callIndex++).setCellValue(socialFee.getEmpName());//项目名称
            row.createCell(callIndex++).setCellValue(socialFee.getCardNum());//单据数量
            row.createCell(callIndex++).setCellValue(socialFee.getIdentityNum());//单据编号
            row.createCell(callIndex++).setCellValue(socialFee.getEmpSocalNum());//获取总金额
            row.createCell(callIndex++).setCellValue(socialFee.getBaseSalary());//获取参与人
            row.createCell(callIndex++).setCellValue(socialFee.getBaseWork());//获取收票人
            row.createCell(callIndex++).setCellValue(socialFee.getBaseEmp() );//获取售票时间
            row.createCell(callIndex++).setCellValue(socialFee.getInjurySalary());//获取参与人
            row.createCell(callIndex++).setCellValue(socialFee.getInjuryWork());//获取收票人
            row.createCell(callIndex++).setCellValue(socialFee.getInjuryEmp() );//获取售票时间
            row.createCell(callIndex++).setCellValue(socialFee.getUnemploySalary());//获取参与人
            row.createCell(callIndex++).setCellValue(socialFee.getUnemployWork());//获取收票人
            row.createCell(callIndex++).setCellValue(socialFee.getUnemployEmp() );//获取售票时间
            row.createCell(callIndex++).setCellValue(socialFee.getSocialMediSalary());//获取参与人
            row.createCell(callIndex++).setCellValue(socialFee.getSocialMediWork());//获取收票人
            row.createCell(callIndex++).setCellValue(socialFee.getSocialMediEmp() );//获取售票时间
            row.createCell(callIndex++).setCellValue(socialFee.getIllSalary());//获取参与人
            row.createCell(callIndex++).setCellValue(socialFee.getIllWork());//获取收票人
            row.createCell(callIndex++).setCellValue(socialFee.getIllEmp() );//获取售票时间
            row.createCell(callIndex++).setCellValue(socialFee.getPregnantSalary());//获取参与人
            row.createCell(callIndex++).setCellValue(socialFee.getPregnantWork());//获取收票人
            row.createCell(callIndex++).setCellValue(socialFee.getPregnantEmp() );//获取售票时间
            row.createCell(callIndex++).setCellValue(socialFee.getWorkMoney());//获取参与人
            row.createCell(callIndex++).setCellValue(socialFee.getEmpMoney());//获取收票人
            row.createCell(callIndex++).setCellValue(socialFee.getTotalMoney() );//获取售票时间

        }
    }
}