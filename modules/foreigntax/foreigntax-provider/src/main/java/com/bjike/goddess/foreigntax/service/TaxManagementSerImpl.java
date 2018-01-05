package com.bjike.goddess.foreigntax.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.foreigntax.bo.TaxManagementBO;
import com.bjike.goddess.foreigntax.bo.VoucherDataBO;
import com.bjike.goddess.foreigntax.dto.TaxManagementDTO;
import com.bjike.goddess.foreigntax.dto.TaxVoucherDTO;
import com.bjike.goddess.foreigntax.entity.TaxManagement;
import com.bjike.goddess.foreigntax.entity.TaxVoucher;
import com.bjike.goddess.foreigntax.enums.GuideAddrStatus;
import com.bjike.goddess.foreigntax.excel.SonPermissionObject;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.TaxManagementTO;
import com.bjike.goddess.foreigntax.to.VoucherDataTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import com.bjike.goddess.voucher.entity.VoucherGenerate;
import com.bjike.goddess.voucher.to.VoucherGenerateTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 税金管理业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:40 ]
 * @Description: [ 税金管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "foreigntaxSerCache")
@Service
public class TaxManagementSerImpl extends ServiceImpl<TaxManagement, TaxManagementDTO> implements TaxManagementSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private AccountInfoManagementSer accountInfoManagementSer;
    @Autowired
    private TaxVoucherSer taxVoucherSer;
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
                throw new SerException("您不是相应部门的人员，不可以查看");
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
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("taxmanagement");
        obj.setDescribesion("税金管理");
        if (flagSeeInfo || flagAddInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeAnswer = accountInfoManagementSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("accountinfomanagement");
        obj.setDescribesion("外账资料管理");
        if (flagSeeAnswer) {
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
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
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
            case COLLECT:
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
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    public Long count(TaxManagementDTO dto) throws SerException {
        Long counts = super.count(dto);
        return counts;
    }

    @Override
    public TaxManagementBO getOne(String id) throws SerException {
        TaxManagement taxManagement = super.findById(id);
        return BeanTransform.copyProperties(taxManagement, TaxManagementBO.class);
    }

    @Override
    public List<TaxManagementBO> list(TaxManagementDTO dto) throws SerException {
        checkSeeIdentity();
        viewTaxManagement(dto);
        List<TaxManagement> taxManagements = super.findByCis(dto, true);
        List<TaxManagementBO> taxManagementBOS = BeanTransform.copyProperties(taxManagements, TaxManagementBO.class);
        return taxManagementBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TaxManagementBO insert(TaxManagementTO to) throws SerException {
        checkAddIdentity();
        TaxManagement taxManagement = BeanTransform.copyProperties(to, TaxManagement.class, true);
        taxManagement.setCreateTime(LocalDateTime.now());
        super.save(taxManagement);
        TaxManagementBO bo = BeanTransform.copyProperties(taxManagement, TaxManagementBO.class);
        return bo;
    }

    @Override
    public void taxTime() throws SerException {
        Integer year = LocalDate.now().getYear();
        Integer month = LocalDate.now().getMonthValue();
        LocalDate taxStart = LocalDate.of(year,month,1);
        LocalDate taxEnd = LocalDate.of(year,month,DateUtil.getDayByDate(year,month));
        TaxManagement taxManagement = new TaxManagement();
        taxManagement.setTaxStart(taxStart);
        taxManagement.setTaxStart(taxEnd);
        super.update(taxManagement);
    }
    @Override
    public Map<String,String> getDead(String taxEnd)throws SerException{
        Map<String,String> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(taxEnd);
            Calendar cal = Calendar.getInstance();

            cal.setTime(date);
            cal.add(Calendar.DATE, 15);
            String deadlineFor = sdf.format(cal.getTime());//发票审核时间
            map.put("deadlineFor", deadlineFor);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TaxManagementBO edit(TaxManagementTO to) throws SerException {
        checkAddIdentity();
        TaxManagement taxManagement = super.findById(to.getId());
        LocalDateTime createTime = taxManagement.getCreateTime();
        taxManagement = BeanTransform.copyProperties(to, TaxManagement.class, true);
        taxManagement.setCreateTime(createTime);
        taxManagement.setModifyTime(LocalDateTime.now());
        super.update(taxManagement);
        return BeanTransform.copyProperties(taxManagement, TaxManagementBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    public void viewTaxManagement(TaxManagementDTO dto) throws SerException {
        //公司
        if (StringUtils.isNotBlank(dto.getCompany())) {
            dto.getConditions().add(Restrict.like("company", dto.getCompany()));
        }
        //税种
        if (StringUtils.isNotBlank(dto.getTaxType())) {
            dto.getConditions().add(Restrict.eq("taxType", dto.getTaxType()));
        }
        //税款所属期起
        if (StringUtils.isNotBlank(dto.getTaxStart())) {
            dto.getConditions().add(Restrict.eq("taxStart", dto.getTaxStart()));
        }
        //税款所属期止
        if (StringUtils.isNotBlank(dto.getTaxEnd())) {
            dto.getConditions().add(Restrict.eq("taxEnd", dto.getTaxEnd()));
        }
    }

    @Override
    public List<TaxManagementBO> collect(TaxManagementDTO dto) throws SerException {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT company AS company,landTaxMark AS landTaxMark,taxStart AS taxStart, ");
        sb.append(" taxEnd AS taxEnd,taxType AS taxType,sum(rate) AS rate,sum(tax) AS tax, ");
        sb.append(" deadlineFor AS deadlineFor,deadlineDate AS deadlineDate,paymentStatus AS paymentStatus ");
        sb.append(" FROM foreigntax_taxmanagement where 1=1 ");
        if (StringUtils.isNotBlank(dto.getCompany())) {
            sb.append(" and company = '" + dto.getCompany() + "'");
        }
        if (StringUtils.isNotBlank(dto.getTaxType())) {
            sb.append(" and taxType = '" + dto.getTaxType() + "'");
        }
        if (StringUtils.isNotBlank(dto.getTaxStart()) && StringUtils.isNotBlank(dto.getTaxEnd())) {
            sb.append(" and taxStart = '" + dto.getTaxStart() + "' and taxEnd = '" + dto.getTaxEnd() + "'");
        }
        sb.append(" GROUP BY company,landTaxMark,taxStart,taxEnd,taxType,deadlineFor,deadlineDate,paymentStatus ");
        String sql = sb.toString();
        String[] fields = new String[]{"company", "landTaxMark", "taxStart", "taxEnd", "taxType",
                "rate", "tax", "deadlineFor", "deadlineDate", "paymentStatus"};
        List<TaxManagementBO> taxManagementBOS = super.findBySql(sql, TaxManagementBO.class, fields);
        return taxManagementBOS;

    }

    @Override
    public List<String> getCompany() throws SerException {
        String[] fields = new String[]{"company"};
        List<TaxManagementBO> taxManagementBOS = super.findBySql("select distinct company from foreigntax_taxmanagement group by company order by company asc ", TaxManagementBO.class, fields);

        List<String> companyList = taxManagementBOS.stream().map(TaxManagementBO::getCompany)
                .filter(company -> (StringUtils.isNotBlank(company))).distinct().collect(Collectors.toList());


        return companyList;
    }

    @Override
    public List<String> getTaxType() throws SerException {
        String[] fields = new String[]{"taxType"};
        List<TaxManagementBO> taxManagementBOS = super.findBySql("select distinct taxType from foreigntax_taxmanagement group by taxType order by taxType asc ", TaxManagementBO.class, fields);

        List<String> taxTypeList = taxManagementBOS.stream().map(TaxManagementBO::getTaxType)
                .filter(taxType -> (StringUtils.isNotBlank(taxType))).distinct().collect(Collectors.toList());


        return taxTypeList;
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
        TaxManagementDTO dto = new TaxManagementDTO();
        dto.getConditions().add(Restrict.in("id",ids));
        List<TaxManagement> taxManagements = super.findByCis(dto);
        if(null == taxManagements){
            throw new SerException("您没有选择数据，请选择");
        }

        Double totalMoney = taxManagements.stream().mapToDouble(TaxManagement::getTax).sum();
        VoucherDataBO voucherDataBO = new VoucherDataBO();
        for(TaxManagement taxManagement :taxManagements){
            if(taxManagement.getLandTaxMark().equals("增值税")){
                voucherDataBO.setVoucherDate(DateUtil.dateToString(taxManagement.getTaxStart()));
                voucherDataBO.setSumary("增值税");
                voucherDataBO.setSubjects(Arrays.asList("应交税金-未交增值税", "银行存款"));
                voucherDataBO.setBorrowMoneys(Arrays.asList(totalMoney, 0d));
                voucherDataBO.setLoanMoneys(Arrays.asList(0d, totalMoney));
                voucherDataBO.setAreas("");
                voucherDataBO.setProjects("");
                voucherDataBO.setGroups("");
            }else if(taxManagement.getLandTaxMark().equals("城市建设维护税")){
                voucherDataBO.setVoucherDate(DateUtil.dateToString(taxManagement.getTaxStart()));
                voucherDataBO.setSumary("城市建设维护税");
                voucherDataBO.setSubjects(Arrays.asList("应交城建税", "银行存款"));
                voucherDataBO.setBorrowMoneys(Arrays.asList(totalMoney, 0d));
                voucherDataBO.setLoanMoneys(Arrays.asList(0d, totalMoney));
                voucherDataBO.setAreas("");
                voucherDataBO.setProjects("");
                voucherDataBO.setGroups("");
            }else if(taxManagement.getLandTaxMark().equals("教育费附加")){
                voucherDataBO.setVoucherDate(DateUtil.dateToString(taxManagement.getTaxStart()));
                voucherDataBO.setSumary("教育费附加");
                voucherDataBO.setSubjects(Arrays.asList("应交教育费附加", "银行存款"));
                voucherDataBO.setBorrowMoneys(Arrays.asList(totalMoney, 0d));
                voucherDataBO.setLoanMoneys(Arrays.asList(0d, totalMoney));
                voucherDataBO.setAreas("");
                voucherDataBO.setProjects("");
                voucherDataBO.setGroups("");
            }else if(taxManagement.getLandTaxMark().equals("地方教育附加")){
                voucherDataBO.setVoucherDate(DateUtil.dateToString(taxManagement.getTaxStart()));
                voucherDataBO.setSumary("地方教育附加");
                voucherDataBO.setSubjects(Arrays.asList("应交地方教育附加", "银行存款"));
                voucherDataBO.setBorrowMoneys(Arrays.asList(totalMoney, 0d));
                voucherDataBO.setLoanMoneys(Arrays.asList(0d, totalMoney));
                voucherDataBO.setAreas("");
                voucherDataBO.setProjects("");
                voucherDataBO.setGroups("");
            }else if(taxManagement.getLandTaxMark().equals("企业所得税")){
                voucherDataBO.setVoucherDate(DateUtil.dateToString(taxManagement.getTaxStart()));
                voucherDataBO.setSumary("企业所得税");
                voucherDataBO.setSubjects(Arrays.asList("应交企业所得税", "银行存款"));
                voucherDataBO.setBorrowMoneys(Arrays.asList(totalMoney, 0d));
                voucherDataBO.setLoanMoneys(Arrays.asList(0d, totalMoney));
                voucherDataBO.setAreas("");
                voucherDataBO.setProjects("");
                voucherDataBO.setGroups("");
            }else {
                throw new SerException("摘要要与国地税标志相匹配，暂时国地税标志只有(增值税,城市建设维护税,教育费附加,地方教育附加,企业所得税)");
            }
        }
        return voucherDataBO;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherDataBO generate(VoucherDataTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();

        String sumarys = to.getSumary();
        List<String> subjects = to.getSubjects();
        List<Double> borrows = to.getBorrowMoneys();
        List<Double> loans = to.getLoanMoneys();
        String areas = to.getAreas();
        String projects = to.getProjects();
        String groups = to.getGroups();

        if (StringUtils.isBlank(to.getVoucherDate())) {
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
        //看是否已经生成记账凭证
        LocalDate time = DateUtil.parseDate(to.getVoucherDate());
        TaxVoucherDTO dto = new TaxVoucherDTO();
        dto.getConditions().add(Restrict.eq("voucherDate",time));
        List<TaxVoucher> taxVouchers = taxVoucherSer.findByCis(dto);
        if(taxVouchers != null && taxVouchers.size()>0){
            //说明该年月日已经生成过记账凭证，则只能删除再添加
            String[] voucherIds = taxVouchers.get(0).getVoucherId().split(",");
            for (String id : voucherIds) {
                RpcTransmit.transmitUserToken(userToken);
                voucherGenerateAPI.deleteVoucherGenerate(id);
            }
            taxVoucherSer.remove(taxVouchers);
        }
        //说明没有生成记账凭证，则继续添加
        VoucherGenerateTO vTO = new VoucherGenerateTO();
        vTO.setVoucherWord("记");
        vTO.setVoucherDate(to.getVoucherDate());
        vTO.setSumary(sumarys);
        vTO.setArea(areas);
        vTO.setProjectName(projects);
        vTO.setProjectGroup(groups);
        vTO.setTicketNum(0d);
        RpcTransmit.transmitUserToken(userToken);
        vTO.setTicketer(userAPI.currentUser().getUsername());

        for (int i = 0; i < borrows.size(); i++) {

            vTO.setFirstSubjects(firstSub);
            vTO.setSecondSubjects(secSub);
            vTO.setThirdSubjects(thirdSub);
            vTO.setBorrowMoneys(borrows);
            vTO.setLoanMoneys(loans);

        }

        RpcTransmit.transmitUserToken(userToken);
        List<VoucherGenerateBO> vgBO = voucherGenerateAPI.addVoucherGenerate(vTO);
        if (vgBO != null && vgBO.size() > 0) {
            String ids = "";
            for (VoucherGenerateBO vg : vgBO) {
                ids += vg.getId() + ",";
            }
            TaxVoucher sfv = new TaxVoucher();
            sfv.setVoucherDate(time);
            sfv.setVoucherId(StringUtils.substringBeforeLast(ids, ","));
            sfv.setCreateTime(LocalDateTime.now());
            sfv.setTotalMoney(borrows.stream().mapToDouble(Double::doubleValue).sum());
            taxVoucherSer.save(sfv);
        }
        VoucherDataBO bo = BeanTransform.copyProperties(to, VoucherDataBO.class);
        return bo;
    }


    @Override
    public List<TaxManagementBO> listByCompany(String company, String monthStart, String monthEnd) throws SerException {
        TaxManagementDTO dto = new TaxManagementDTO();
//        List<String> con = Arrays.asList(monthStart,monthEnd);
        String[] con = new String[]{monthStart, monthEnd};
        if (StringUtils.isNotBlank(company)) {
            dto.getConditions().add(Restrict.eq("company", company));
            dto.getConditions().add(Restrict.between("month", con));
        }
        List<TaxManagement> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, TaxManagementBO.class);
    }
}