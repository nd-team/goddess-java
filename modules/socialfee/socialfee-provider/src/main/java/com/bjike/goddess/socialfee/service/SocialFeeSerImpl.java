package com.bjike.goddess.socialfee.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.socialfee.to.GuidePermissionTO;
import com.bjike.goddess.socialfee.bo.SocialFeeBO;
import com.bjike.goddess.socialfee.bo.VoucherDataBO;
import com.bjike.goddess.socialfee.dto.SocialFeeDTO;
import com.bjike.goddess.socialfee.dto.SocialFeeVoucherDTO;
import com.bjike.goddess.socialfee.entity.SocialFee;
import com.bjike.goddess.socialfee.entity.SocialFeeVoucher;
import com.bjike.goddess.socialfee.excle.SocialFeeExcel;
import com.bjike.goddess.socialfee.excle.SonPermissionObject;
import com.bjike.goddess.socialfee.to.SocialFeeTO;
import com.bjike.goddess.socialfee.to.VoucherDataTO;
import com.bjike.goddess.socialfee.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import com.bjike.goddess.voucher.to.VoucherGenerateTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

//    public static final String[] EXCELHEAD = {"缴费所属年", "缴费所属月", "缴费所属时期", "纳税人名称", "单位社保号",
//            "姓名", "身份证明号码", "证件号", "个人社保号", "基本养老保险计费工资", "基本养老保险单位", "基本养老保险个人",
//            "工伤保险计费工资", "工伤保险单位", "工伤保险个人", "失业保险计费工资", "失业保险单位", "失业保险个人",
//            "社会医疗保险计费工资", "社会医疗保险单位", "社会医疗保险个人", "重大疾病医疗补助计费工资", "重大疾病医疗补助单位",
//            "重大疾病医疗补助个人", "生育保险计费工资", "生育保险单位", "生育保险个人", "单位合计", "个人合计", "应缴金额"};

    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        //财务模块权限
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是财务模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 导航检查权限
     *
     * @throws SerException
     */
    private Boolean guildPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSocial = guildPermission();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("socialfeeserimpl");
        obj.setDescribesion("社会缴费");
        if (flagSocial) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guildPermission();
                break;
            case ADD:
                flag = guildPermission();
                break;
            case EDIT:
                flag = guildPermission();
                break;
            case DELETE:
                flag = guildPermission();
                break;
            case IMPORT:
                flag = guildPermission();
                break;
            case EXPORT:
                flag = guildPermission();
                break;
            case SEE:
                flag = guildPermission();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

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
        checkPermission();
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
        checkPermission();
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

        socialFee.setTotalMoney(socialFee.getWorkMoney() + socialFee.getEmpMoney());
        super.save(socialFee);
        return BeanTransform.copyProperties(socialFee, SocialFeeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SocialFeeBO editSocialFee(SocialFeeTO socialFeeTO) throws SerException {
        checkPermission();
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
        temp.setTotalMoney(temp.getWorkMoney() + temp.getEmpMoney());
        super.update(temp);
        return BeanTransform.copyProperties(socialFee, SocialFeeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteSocialFee(String id) throws SerException {
        checkPermission();
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
        String sql = " select payFeer  from socialfee_socialfee group by payFeer ";
        List<SocialFee> list = super.findBySql(sql, SocialFee.class, field);
        List<String> payFeerList = list.stream().map(SocialFee::getPayFeer).collect(Collectors.toList());
        return payFeerList;
    }

    @Override
    public List<String> listEmpName() throws SerException {
        String[] field = new String[]{"empName"};
        String sql = " select empName  from socialfee_socialfee group by empName ";
        List<SocialFee> list = super.findBySql(sql, SocialFee.class, field);
        List<String> empNameList = list.stream().map(SocialFee::getEmpName).collect(Collectors.toList());
        return empNameList;
    }

    @Override
    public List<SocialFeeBO> collect(SocialFeeDTO socialFeeDTO) throws SerException {
//        汇总表头：（缴费所属时期/纳税人名称/姓名/应缴金额）
        checkPermission();
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
                sql.append(" select sum(totalMoney) as totalMoney  from socialfee_socialfee where 1=1 ");
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
            list = ctCom(socialFeeDTO, timeFlag, list, sqlAppend, payTimeDur);
        } else if (StringUtils.isNotBlank(company) && StringUtils.isBlank(emp)) {
            //根据时间段和个人查数据
            list = ctEmp(socialFeeDTO, timeFlag, list, sqlAppend, payTimeDur);
        } else if (StringUtils.isNotBlank(company) && StringUtils.isNotBlank(emp)) {
            //根据时间段和公司和个人查数据
            list = ctComAndEmp(socialFeeDTO, timeFlag, list, sqlAppend, payTimeDur);
        }

        return list;
    }

    //根据时间段和公司查数据
    private List<SocialFeeBO> ctCom(SocialFeeDTO socialFeeDTO, Boolean timeFlag, List<SocialFeeBO> list, String sqlAppend, String payTimeDur) throws SerException {
        String company = socialFeeDTO.getPayFeer();
        String emp = socialFeeDTO.getEmpName();

        String[] field = new String[]{"totalMoney"};
        StringBuffer sql = new StringBuffer("");
        sql.append(" select sum(totalMoney) as totalMoney  from socialfee_socialfee where 1=1 ");
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
        sql.append(" select sum(totalMoney) as totalMoney  from socialfee_socialfee where 1=1 ");
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
        sql.append(" select sum(totalMoney) as totalMoney  from socialfee_socialfee where 1=1 ");
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
        if (ids == null || ids.length <= 0) {
            throw new SerException("id数组不能为空");
        }
        StringBuffer sb = new StringBuffer("");
        for (String id : ids) {
            sb.append("'" + id + "',");
        }
//       "id","payTimeYear","payTimeMonth","payTime","totalMoney"
        SocialFeeDTO socialFeeDTO = new SocialFeeDTO();
        socialFeeDTO.getConditions().add(Restrict.in("id", ids));
        List<SocialFee> socialFees = super.findByCis(socialFeeDTO);
        if (socialFees == null) {
            throw new SerException("您没有选择数据，请选择");
        }
        String time = socialFees.get(0).getPayTime();
        Boolean bo = socialFees.stream().anyMatch(str -> !time.equals(str.getPayTime()));
        if (bo) {
            throw new SerException("您选择的数据的缴费所属时期不一致，请重新选择");
        }

        Double totalMoney = socialFees.stream().mapToDouble(SocialFee::getTotalMoney).sum();
        int year = Integer.parseInt(socialFees.get(0).getPayTimeYear());
        int month = Integer.parseInt(socialFees.get(0).getPayTimeMonth());
        LocalDate vDate = LocalDate.of(year, month, 1);

        List<VoucherDataBO> list = new ArrayList<>();
        VoucherDataBO voucherDataBO = new VoucherDataBO();
        voucherDataBO.setVoucherDate(String.valueOf(vDate));
        voucherDataBO.setSumary("缴纳社保");
        voucherDataBO.setSubjects(Arrays.asList("应付职工薪酬-社会保险", "银行存款"));
        voucherDataBO.setBorrowMoneys(Arrays.asList(totalMoney, 0d));
        voucherDataBO.setLoanMoneys(Arrays.asList(0d, totalMoney));
        voucherDataBO.setAreas("");
        voucherDataBO.setProjects("");
        voucherDataBO.setGroups("");

        return voucherDataBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherDataBO voucher(VoucherDataTO voucherDataTO) throws SerException {

        String userToken = RpcTransmit.getUserToken();

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
                secSub.add("无");
                thirdSub.add("无");
            }
            if (subs.length == 2) {
                firstSub.add(subs[0]);
                secSub.add(subs[1]);
                thirdSub.add("无");
            }
            if (subs.length >= 3) {
                firstSub.add(subs[0]);
                secSub.add(subs[1]);
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
        LocalDate time = LocalDate.parse(voucherDataTO.getVoucherDate());
        SocialFeeVoucherDTO socialFeeVoucherDTO = new SocialFeeVoucherDTO();
        socialFeeVoucherDTO.getConditions().add(Restrict.eq("payTimeYear", time.getYear() + (time.getMonthValue() < 10 ? "0" + time.getMonthValue() : time.getMonthValue() + "")));
        List<SocialFeeVoucher> sfvList = socialFeeVoucherSer.findByCis(socialFeeVoucherDTO);
        if (sfvList != null && sfvList.size() > 0) {
            //说明该年月已经生成过记账凭证，则只能删除再添加
            String[] voucherIds = sfvList.get(0).getVoucherId().split(",");
            for (String id : voucherIds) {
                RpcTransmit.transmitUserToken(userToken);
                voucherGenerateAPI.deleteVoucherGenerate(id);
            }
            socialFeeVoucherSer.remove(sfvList);
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

        RpcTransmit.transmitUserToken(userToken);
        List<VoucherGenerateBO> vgBO = voucherGenerateAPI.addVoucherGenerate(vgTO);
        if (vgBO != null && vgBO.size() > 0) {
            String ids = "";
            for (VoucherGenerateBO vg : vgBO) {
                ids += vg.getId() + ",";
            }
            SocialFeeVoucher sfv = new SocialFeeVoucher();
            sfv.setPayTimeYear(time.getYear() + (time.getMonthValue() < 10 ? "0" + time.getMonthValue() : time.getMonthValue() + ""));
            sfv.setVoucherId(StringUtils.substringBeforeLast(ids, ","));
            sfv.setCreateTime(LocalDateTime.now());
            sfv.setTotalMoney(borrows.stream().mapToDouble(Double::doubleValue).sum());
            socialFeeVoucherSer.save(sfv);
        }
        VoucherDataBO vbo = BeanTransform.copyProperties(voucherDataTO, VoucherDataBO.class);
        return vbo;
    }

//    @Override
//    public String export(SocialFeeDTO socialFeeDTO) throws SerException {
//        //查询
//        if( StringUtils.isNotBlank(socialFeeDTO.getPayFeer())){
//            socialFeeDTO.getConditions().add(Restrict.eq("payFeer",socialFeeDTO.getPayFeer()));
//        }if( StringUtils.isNotBlank(socialFeeDTO.getEmpName())){
//            socialFeeDTO.getConditions().add(Restrict.eq("empName",socialFeeDTO.getEmpName()));
//        }if( StringUtils.isNotBlank(socialFeeDTO.getStartTime()) && StringUtils.isNotBlank(socialFeeDTO.getEndTime())){
//            String startYear = LocalDate.parse(socialFeeDTO.getStartTime()).getYear() + "";
//            int startMonthValue = LocalDate.parse(socialFeeDTO.getStartTime()).getMonthValue();
//            String startMonth = startMonthValue < 10 ? "0" + startMonthValue : startMonthValue + "";
//            String startYearMonth = startYear + startMonth;
//
//            String endYear = LocalDate.parse(socialFeeDTO.getEndTime()).getYear() + "";
//            int endMonthValue = LocalDate.parse(socialFeeDTO.getEndTime()).getMonthValue();
//            String endMonth = endMonthValue < 10 ? "0" + endMonthValue : endMonthValue + "";
//            String endYearMonth = endYear + endMonth;
//            socialFeeDTO.getConditions().add(Restrict.between("payTime",Arrays.asList("'"+startYearMonth+"'","'"+endYearMonth+"'")));
//        }
//        List<SocialFee> list =  super.findByCis( socialFeeDTO );
//
//        String tempString = "社会缴费";
//        XSSFWorkbook wb = new XSSFWorkbook();//创建一个Excel文件
//        XSSFSheet sheet = wb.createSheet(tempString);//创建报销明细工作薄
//        sheet.setDefaultRowHeight((short) 300);//设置默认行高
//        // 设置execl工作簿中的列名
//        String[] excelHeader = EXCELHEAD;//Excel表头
//        XSSFCellStyle style = DownloadExeclUtil.titleStyle(wb, IndexedColors.PALE_BLUE.getIndex());  // 设置标题样式
//        XSSFRow row = sheet.createRow(0);//创建第一行
//        row.setHeight((short) 400);//设置第一行单元格的高度
//        for (int i = 0, length = excelHeader.length; i < length; i++) {
//            XSSFCell cell = row.createCell(i);
//            if (i == 0 || i == 4 || i == 8) {
//                sheet.setColumnWidth(i, 2000); //设置单元格的宽
//            } else if (i == 18) {
//                sheet.setColumnWidth(i, 5000);
//            } else {
//                sheet.setColumnWidth(i, 4000);
//            }
//            cell.setCellValue(excelHeader[i]);//设置单元格的值
//            cell.setCellStyle(style);   //设置样式
//        }
//
//
//        createRowDetail(list, row, sheet);//填充数据
//
//        String savePath = new StringBuilder(FileUtils.getFilesRePath("function/socialfee/Socialfee/export/")).append(LocalDate.now().toString()).toString();
//        return FileUtils.getDownloadPath(FileUtils.saveWBFile(wb, savePath, tempString + ".xlsx"));
//
//    }


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
            row.createCell(callIndex++).setCellValue(socialFee.getBaseEmp());//获取售票时间
            row.createCell(callIndex++).setCellValue(socialFee.getInjurySalary());//获取参与人
            row.createCell(callIndex++).setCellValue(socialFee.getInjuryWork());//获取收票人
            row.createCell(callIndex++).setCellValue(socialFee.getInjuryEmp());//获取售票时间
            row.createCell(callIndex++).setCellValue(socialFee.getUnemploySalary());//获取参与人
            row.createCell(callIndex++).setCellValue(socialFee.getUnemployWork());//获取收票人
            row.createCell(callIndex++).setCellValue(socialFee.getUnemployEmp());//获取售票时间
            row.createCell(callIndex++).setCellValue(socialFee.getSocialMediSalary());//获取参与人
            row.createCell(callIndex++).setCellValue(socialFee.getSocialMediWork());//获取收票人
            row.createCell(callIndex++).setCellValue(socialFee.getSocialMediEmp());//获取售票时间
            row.createCell(callIndex++).setCellValue(socialFee.getIllSalary());//获取参与人
            row.createCell(callIndex++).setCellValue(socialFee.getIllWork());//获取收票人
            row.createCell(callIndex++).setCellValue(socialFee.getIllEmp());//获取售票时间
            row.createCell(callIndex++).setCellValue(socialFee.getPregnantSalary());//获取参与人
            row.createCell(callIndex++).setCellValue(socialFee.getPregnantWork());//获取收票人
            row.createCell(callIndex++).setCellValue(socialFee.getPregnantEmp());//获取售票时间
            row.createCell(callIndex++).setCellValue(socialFee.getWorkMoney());//获取参与人
            row.createCell(callIndex++).setCellValue(socialFee.getEmpMoney());//获取收票人
            row.createCell(callIndex++).setCellValue(socialFee.getTotalMoney());//获取售票时间

        }
    }

    @Override
    public byte[] exportExcel(SocialFeeDTO socialFeeDTO) throws SerException {
        //查询
        checkPermission();
        if (StringUtils.isNotBlank(socialFeeDTO.getPayFeer())) {
            socialFeeDTO.getConditions().add(Restrict.eq("payFeer", socialFeeDTO.getPayFeer()));
        }
        if (StringUtils.isNotBlank(socialFeeDTO.getEmpName())) {
            socialFeeDTO.getConditions().add(Restrict.eq("empName", socialFeeDTO.getEmpName()));
        }
        if (StringUtils.isNotBlank(socialFeeDTO.getStartTime()) && StringUtils.isNotBlank(socialFeeDTO.getEndTime())) {
            String startYear = LocalDate.parse(socialFeeDTO.getStartTime()).getYear() + "";
            int startMonthValue = LocalDate.parse(socialFeeDTO.getStartTime()).getMonthValue();
            String startMonth = startMonthValue < 10 ? "0" + startMonthValue : startMonthValue + "";
            String startYearMonth = startYear + startMonth;

            String endYear = LocalDate.parse(socialFeeDTO.getEndTime()).getYear() + "";
            int endMonthValue = LocalDate.parse(socialFeeDTO.getEndTime()).getMonthValue();
            String endMonth = endMonthValue < 10 ? "0" + endMonthValue : endMonthValue + "";
            String endYearMonth = endYear + endMonth;
            socialFeeDTO.getConditions().add(Restrict.between("payTime", Arrays.asList("'" + startYearMonth + "'", "'" + endYearMonth + "'")));
        }
        List<SocialFee> list = super.findByCis(socialFeeDTO);
        List<SocialFeeExcel> socialFeeExcels = new ArrayList<>();
        list.stream().forEach(str -> {
            SocialFeeExcel excel = BeanTransform.copyProperties(str, SocialFeeExcel.class);
            socialFeeExcels.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(socialFeeExcels, excel);
        return bytes;
    }

    @Override
    public SocialFeeBO importExcel(List<SocialFeeTO> socialFeeTOS) throws SerException {
        checkPermission();
        List<SocialFee> socialFees = BeanTransform.copyProperties(socialFeeTOS, SocialFee.class, true);
        socialFees.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(socialFees);

        SocialFeeBO socialFeeBO = BeanTransform.copyProperties(new SocialFee(), SocialFeeBO.class);
        return socialFeeBO;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<SocialFeeExcel> socialFeeExcels = new ArrayList<>();

        SocialFeeExcel excel = new SocialFeeExcel();
        excel.setPayTimeYear("2017");
        excel.setPayTimeMonth("08");
        excel.setPayTime("201708");
        excel.setPayFeer("李峰");
        excel.setWorkSocalNum("2324232");
        excel.setEmpName("李芬");
        excel.setCardNum("431783928838493282");
        excel.setIdentityNum("12834783");
        excel.setEmpSocalNum("232123");
        excel.setBaseSalary(12d);
        excel.setBaseWork(23d);
        excel.setBaseEmp(12d);
        excel.setInjurySalary(9993d);
        excel.setInjuryWork(232d);
        excel.setInjuryEmp(1232d);
        excel.setUnemploySalary(23212d);
        excel.setUnemployWork(112d);
        excel.setUnemployEmp(112d);
        excel.setSocialMediSalary(112d);
        excel.setSocialMediWork(112d);
        excel.setSocialMediEmp(112d);
        excel.setIllSalary(112d);
        excel.setIllWork(112d);
        excel.setIllEmp(112d);
        excel.setPregnantSalary(112d);
        excel.setPregnantWork(112d);
        excel.setPregnantEmp(112d);
        excel.setWorkMoney(112d);
        excel.setEmpMoney(112d);
        excel.setTotalMoney(112d);

        socialFeeExcels.add(excel);

        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(socialFeeExcels, exce);
        return bytes;
    }
}