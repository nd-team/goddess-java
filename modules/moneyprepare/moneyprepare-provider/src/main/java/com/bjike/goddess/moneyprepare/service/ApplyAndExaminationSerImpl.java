package com.bjike.goddess.moneyprepare.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyprepare.api.FundPrepareAPI;
import com.bjike.goddess.moneyprepare.bo.ApplyAndExaminationBO;
import com.bjike.goddess.moneyprepare.bo.FundPrepareApplyBO;
import com.bjike.goddess.moneyprepare.bo.FundPrepareBO;
import com.bjike.goddess.moneyprepare.dto.ApplyAndExaminationDTO;
import com.bjike.goddess.moneyprepare.dto.FundPrepareDTO;
import com.bjike.goddess.moneyprepare.entity.ApplyAndExamination;
import com.bjike.goddess.moneyprepare.enums.GuideAddrStatus;
import com.bjike.goddess.moneyprepare.excel.SonPermissionObject;
import com.bjike.goddess.moneyprepare.to.ApplyAndExaminationTO;
import com.bjike.goddess.moneyprepare.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * 申请和审批业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 04:16 ]
 * @Description: [ 申请和审批业务实现 ]applyAndExamination
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneyprepareSerCache")
@Service
public class ApplyAndExaminationSerImpl extends ServiceImpl<ApplyAndExamination, ApplyAndExaminationDTO> implements ApplyAndExaminationSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private FundPrepareAPI fundPrepareAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private FundPrepareSer fundPrepareSer;
    @Autowired
    private ProportionSer proportionSer;

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
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("applyandexamination");
        obj.setDescribesion("申请和审批");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = fundPrepareSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("fundprepare");
        obj.setDescribesion("资金准备");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = proportionSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("proportion");
        obj.setDescribesion("比例分配");
        if (flagSeeCate) {
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
    public Long countApplyAndExamination(ApplyAndExaminationDTO applyAndExaminationDTO) throws SerException {
        searchCondition(applyAndExaminationDTO);
        Long count = super.count(applyAndExaminationDTO);
        return count;
    }

    @Override
    public ApplyAndExaminationBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }

        ApplyAndExamination applyAndExamination = super.findById(id);
        return BeanTransform.copyProperties(applyAndExamination, ApplyAndExaminationBO.class);
    }

    @Override
    public List<ApplyAndExaminationBO> listApplyAndExamination(ApplyAndExaminationDTO applyAndExaminationDTO) throws SerException {
//        checkSeeIdentity();

        searchCondition(applyAndExaminationDTO);
        List<ApplyAndExamination> list = super.findByPage(applyAndExaminationDTO);
        List<ApplyAndExaminationBO> applyAndExaminationBOS = new ArrayList<>();
        list.stream().forEach(str -> {
            ApplyAndExaminationBO bo = BeanTransform.copyProperties(str, ApplyAndExaminationBO.class);
            applyAndExaminationBOS.add(bo);
        });
        return applyAndExaminationBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyAndExaminationBO addApplyAndExamination(ApplyAndExaminationTO applyAndExaminationTO) throws SerException {
//        checkAddIdentity();
        //DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //LocalDate date = LocalDate.parse(applyAndExaminationTO.getStartProjectTime(),format);


        String string = applyAndExaminationTO.getTableName();
        String time = getFirstDay(string);
        if (null == time || "".equals(time)) {
            throw new SerException("资金准备表的名称应包含有日期，格式为yyyy年MM月");
        }
//        try {
//            DateUtil.parseDate(applyAndExaminationTO.getTableName());
//        } catch (Exception e) {
//            throw new SerException("输入的日期格式不对,格式为yyyy-MM-dd");
//        }
        ApplyAndExamination applyAndExamination = new ApplyAndExamination();
        applyAndExamination.setTableName(applyAndExaminationTO.getTableName());
        applyAndExamination.setApply(userAPI.currentUser().getUsername());
        applyAndExamination.setApplyTime(LocalDateTime.now());
        applyAndExamination.setRemark(applyAndExaminationTO.getRemark());
        applyAndExamination.setCreateTime(LocalDateTime.now());
        if (isExist(applyAndExamination)) {
            throw new SerException("该月的资金准备表已存在");
        }
        super.save(applyAndExamination);
        ApplyAndExaminationBO applyAndExaminationBO = BeanTransform.copyProperties(applyAndExamination, ApplyAndExaminationBO.class);
        return applyAndExaminationBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyAndExaminationBO editApplyAndExamination(ApplyAndExaminationTO applyAndExaminationTO) throws SerException {
//        checkAddIdentity();

        ApplyAndExamination temp = super.findById(applyAndExaminationTO.getId());

        String string = applyAndExaminationTO.getTableName();
        String time = getFirstDay(string);
        if (null == time || "".equals(time)) {
            throw new SerException("资金准备表的名称应包含有日期，格式为yyyy-MM-dd");
        }

        ApplyAndExamination applyAndExamination = BeanTransform.copyProperties(applyAndExaminationTO, ApplyAndExamination.class, true);
        BeanUtils.copyProperties(applyAndExamination, temp, "id", "createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        ApplyAndExaminationBO applyAndExaminationBO = BeanTransform.copyProperties(temp, ApplyAndExaminationBO.class);
        return applyAndExaminationBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteApplyAndExamination(String id) throws SerException {
//        checkAddIdentity();

        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyAndExaminationBO auditApplyAndExamination(ApplyAndExaminationTO applyAndExaminationTO) throws SerException {
        if (StringUtils.isBlank(applyAndExaminationTO.getId())) {
            throw new SerException("id不能为空");
        }
        String userToken = RpcTransmit.getUserToken();
//        checkAddIdentity();
        RpcTransmit.transmitUserToken(userToken);

        ApplyAndExamination temp = super.findById(applyAndExaminationTO.getId());

        temp.setExaminationOpinion(applyAndExaminationTO.getExaminationOpinion());
        temp.setRemark(applyAndExaminationTO.getRemark());
        temp.setExamination(userAPI.currentUser().getUsername());

        temp.setExaminationStatus(true);
        temp.setExaminationTime(LocalDateTime.now());
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        ApplyAndExaminationBO applyAndExaminationBO = BeanTransform.copyProperties(temp, ApplyAndExaminationBO.class);
        return applyAndExaminationBO;
    }

    @Override
    public List<FundPrepareApplyBO> record(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }

        ApplyAndExamination applyAndExamination = super.findById(id);
        String string = applyAndExamination.getTableName();
        String times = getFirstDay(string);
        if (null == times || "".equals(times)) {
            throw new SerException("资金准备表的名称应包含有日期，格式为yyyy-MM-dd");
        }

        LocalDate time = LocalDate.parse(times);
        //前一个月
        LocalDate time1 = LocalDate.parse(times).minusMonths(1);
        //前两个月
        LocalDate time2 = LocalDate.parse(times).minusMonths(2);
        //前三个月
        LocalDate time3 = LocalDate.parse(times).minusMonths(3);

        //根据月份查询当月的资金准备表
        FundPrepareDTO fundPrepareDTO = new FundPrepareDTO();
        fundPrepareDTO.setTime(time.toString());
        List<FundPrepareBO> list = fundPrepareAPI.listFundPrepare(fundPrepareDTO);

        //前一个月的资金准备表
        FundPrepareDTO fundPrepareDTO1 = new FundPrepareDTO();
        fundPrepareDTO1.setTime(time1.toString());
        List<FundPrepareBO> list1 = fundPrepareAPI.listFundPrepare(fundPrepareDTO1);

        //前两个月的资金准备表
        FundPrepareDTO fundPrepareDTO2 = new FundPrepareDTO();
        fundPrepareDTO2.setTime(time2.toString());
        List<FundPrepareBO> list2 = fundPrepareAPI.listFundPrepare(fundPrepareDTO2);

        //前三个月的资金准备表
        FundPrepareDTO fundPrepareDTO3 = new FundPrepareDTO();
        fundPrepareDTO3.setTime(time3.toString());
        List<FundPrepareBO> list3 = fundPrepareAPI.listFundPrepare(fundPrepareDTO3);

        List<FundPrepareApplyBO> fundPrepareApplyBOList = new ArrayList<>();
        if (null != list && list.size() > 0) {
            for (FundPrepareBO bo : list) {
                FundPrepareApplyBO fundPrepareApply = new FundPrepareApplyBO();
                fundPrepareApply.setFirstSubject(bo.getFirstSubject());
                fundPrepareApply.setSecondSubject(bo.getSecondSubject());
                fundPrepareApply.setFund(bo.getFund());
                fundPrepareApply.setId(bo.getId());
                fundPrepareApplyBOList.add(fundPrepareApply);
            }
        }
        if (null != fundPrepareApplyBOList && fundPrepareApplyBOList.size() > 0) {
            for (FundPrepareApplyBO fundPrepareApplyBO : fundPrepareApplyBOList) {
                if (null != list1 && list1.size() > 0) {
                    for (FundPrepareBO bo1 : list1) {
                        if (fundPrepareApplyBO.getSecondSubject().equals(bo1.getSecondSubject())) {
                            fundPrepareApplyBO.setFund1(bo1.getFund());
                        }
                    }
                }
                if (null != list2 && list2.size() > 0) {
                    for (FundPrepareBO bo2 : list2) {
                        if (fundPrepareApplyBO.getSecondSubject().equals(bo2.getSecondSubject())) {
                            fundPrepareApplyBO.setFund2(bo2.getFund());
                        }
                    }
                }
                if (null != list3 && list3.size() > 0) {
                    for (FundPrepareBO bo3 : list3) {
                        if (fundPrepareApplyBO.getSecondSubject().equals(bo3.getSecondSubject())) {
                            fundPrepareApplyBO.setFund3(bo3.getFund());
                        }
                    }
                }
            }
        }

        return fundPrepareApplyBOList;
    }


    public static void main(String[] args) {
        String bb = "2017-05-10";
        LocalDate xx = LocalDate.parse(bb, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate d = LocalDate.now();
        d.getMonth();
        System.out.println(d.getMonth().getValue());
        System.out.println(d.minusMonths(1));
        System.out.println(d.minusMonths(2));
        System.out.println(d.minusMonths(9));
        System.out.println(d.getYear());
        System.out.println(d.getMonthValue());

        StringBuilder sb = new StringBuilder();
        sb.append(d.getYear());
        sb.append("-");
        if (d.getMonthValue() < 10) {
            sb.append("0" + d.getMonth().getValue() + "-01");
        } else {
            sb.append(d.getMonth().getValue() + "-01");
        }
        System.out.println(sb);
        System.out.println(xx.getMonthValue() + "------------");

        String a = "2017-04-13";
        System.out.println(a.lastIndexOf("-"));
        System.out.println(a.subSequence(0, a.lastIndexOf("-") + 1));
        int count = LocalDate.parse(a).getMonthValue();

        LocalDate aa = LocalDate.now().minusMonths(-1);
        System.out.println(LocalDate.parse(a).getMonthValue() + "");

        LocalDate localDate = LocalDate.now();
        Period period = Period.between(localDate, localDate.plus(2, ChronoUnit.DAYS));
        System.out.println(period.getDays());
        for (int i = count; i > 0; i--) {
            System.out.println();
        }
        System.out.println(LocalDate.parse(a).getDayOfYear());
    }

    public void searchCondition(ApplyAndExaminationDTO applyAndExaminationDTO) throws SerException {
        /**
         * 名称
         */
        if (StringUtils.isNotBlank(applyAndExaminationDTO.getTableName())) {
            applyAndExaminationDTO.getConditions().add(Restrict.eq("tableName", applyAndExaminationDTO.getTableName()));
        }
        /**
         * 申请人
         */
        if (StringUtils.isNotBlank(applyAndExaminationDTO.getApply())) {
            applyAndExaminationDTO.getConditions().add(Restrict.like("apply", applyAndExaminationDTO.getApply()));
        }
        /**
         * 申请时间
         */
        if (applyAndExaminationDTO.getApplyTime() != null) {
            applyAndExaminationDTO.getConditions().add(Restrict.eq("applyTime", applyAndExaminationDTO.getApplyTime()));
        }
        /**
         * 审批人
         */
        if (StringUtils.isNotBlank(applyAndExaminationDTO.getExamination())) {
            applyAndExaminationDTO.getConditions().add(Restrict.like("examination", applyAndExaminationDTO.getExamination()));
        }
        /**
         * 审批时间
         */
        if (applyAndExaminationDTO.getExaminationTime() != null) {
            applyAndExaminationDTO.getConditions().add(Restrict.like("examinationTime", applyAndExaminationDTO.getExaminationTime()));
        }
        /**
         * 审批意见
         */
        if (StringUtils.isNotBlank(applyAndExaminationDTO.getExaminationOpinion())) {
            applyAndExaminationDTO.getConditions().add(Restrict.like("examinationOpinion", applyAndExaminationDTO.getExaminationOpinion()));
        }
        /**
         * 备注
         */
        if (StringUtils.isNotBlank(applyAndExaminationDTO.getRemark())) {
            applyAndExaminationDTO.getConditions().add(Restrict.eq("remark", applyAndExaminationDTO.getRemark()));
        }
    }

    private String transformation(int first, int last, String string, String tag) throws SerException {
        String newString = "";
        String time = "";
        if (!"-".equals(tag)) {
            tag = "-";
        }
        if (first != last && 3 == (last - first)) {
            //直接获取
            newString = string.substring(first - 4, first) + tag + string.substring(first + 1, last);
            //time=2017-07-01
            time = newString + "-01";
        }
        if (first != last && 2 == (last - first)) {
            newString = string.substring(first - 4, first);
            String month = "-0" + string.substring(first + 1, first + 2);
            time = newString + month + "-01";
        }
        return time;
    }

    private String transformation1(String string) throws SerException {
        String time = "";
        int first = string.indexOf("年");
        int last = string.indexOf("月");
        String tag = "-";
        if (-1 != first && -1 != last && last > first) {
            string.replaceAll("年", "-");
            string.replaceAll("月", "-");
            time = transformation(first, last, string, tag);
        }
        return time;
    }

    //将字符串中包含的日期转换为当月1号
    private String getFirstDay(String string) throws SerException {
        String time = "";
        String tag = "-";
        int first = string.indexOf("-");
        int last = string.lastIndexOf("-");
        if (-1 == first && -1 == last) {
            first = string.indexOf("/");
            last = string.lastIndexOf("/");
            if (-1 == first && -1 == last) {
                //判断中文日期：2017年8月
                time = transformation1(string);
                return time;
            }
            tag = "/";
            time = transformation(first, last, string, tag);
            return time;
        }
        time = transformation(first, last, string, tag);
        return time;
    }

    //判断当月资金准备表是否已存在
    private Boolean isExist(ApplyAndExamination applyAndExamination) throws SerException {
        Boolean tar = false;
        String time = getFirstDay(applyAndExamination.getTableName());
        List<ApplyAndExamination> applyAndExaminations = super.findAll();
        if (null != applyAndExaminations && applyAndExaminations.size() > 0) {
            for (ApplyAndExamination entity : applyAndExaminations) {
                if (getFirstDay(entity.getTableName()).equals(time)) {
                    tar = true;
                }
            }
        }
        return tar;
    }

}