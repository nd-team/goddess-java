package com.bjike.goddess.moneyprepare.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.financeinit.api.AccountanCourseAPI;
import com.bjike.goddess.financeinit.api.CategoryAPI;
import com.bjike.goddess.financeinit.bo.AccountAddDateBO;
import com.bjike.goddess.moneyprepare.api.ProportionAPI;
import com.bjike.goddess.moneyprepare.bo.*;
import com.bjike.goddess.moneyprepare.dto.FundPrepareDTO;
import com.bjike.goddess.moneyprepare.dto.ProportionDTO;
import com.bjike.goddess.moneyprepare.entity.FundPrepare;
import com.bjike.goddess.moneyprepare.entity.Proportion;
import com.bjike.goddess.moneyprepare.enums.GuideAddrStatus;
import com.bjike.goddess.moneyprepare.to.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 资金准备业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 02:23 ]
 * @Description: [ 资金准备业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneyprepareSerCache")
@Service
public class FundPrepareSerImpl extends ServiceImpl<FundPrepare, FundPrepareDTO> implements FundPrepareSer {

    @Autowired
    private AccountanCourseAPI accountanCourseAPI;
    @Autowired
    private ProportionAPI proportionAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private CategoryAPI categoryAPI;

    @Override
    public Long countFundPrepare(FundPrepareDTO fundPrepareDTO) throws SerException {
        searchCondition(fundPrepareDTO);
        Long count = super.count(fundPrepareDTO);
        return count;
    }

    @Override
    public List<ProportionBO> listDetail(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }

        FundPrepare fundPrepare = super.findById(id);
        ProportionDTO proportionDTO = new ProportionDTO();
        //根据时间查询对应的分配比例
        proportionDTO.setTime(fundPrepare.getTime());
        List<ProportionBO> proportionBOs = proportionAPI.listProportion(proportionDTO);
        List<ProportionBO> boList = new ArrayList<>();
        if (null != proportionBOs && proportionBOs.size() > 0) {
            for (ProportionBO bo : proportionBOs) {
                bo.setEachFund(fundPrepare.getFund() * bo.getRatio());
                bo.setId(bo.getId());
                bo.setFundId(id);
                boList.add(bo);
            }
        } else {
            throw new SerException("当月的比例分配暂无,请先添加");
        }
        return boList;
//        return BeanTransform.copyProperties(fundPrepare, FundPrepareBO.class);
    }

    @Override
    public FundPrepareBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }

        FundPrepare fundPrepare = super.findById(id);
        return BeanTransform.copyProperties(fundPrepare, FundPrepareBO.class);
    }

    @Override
    public List<FundPrepareWeekBO> weekCollect() throws SerException {

        //对时间进行处理,转换为当月1号
        StringBuffer sb = new StringBuffer();
        String nowTime = LocalDate.now().toString();
//      LocalDate localDate =LocalDate.parse(fundPrepareTO.getTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        sb.append(nowTime.subSequence(0, nowTime.lastIndexOf("-") + 1) + "01");
        FundPrepareDTO fundPrepareDTO = new FundPrepareDTO();
        fundPrepareDTO.getConditions().add(Restrict.eq("time", sb.toString()));
        //查询当月的资金准备
        List<FundPrepare> fundPrepareList = super.findByCis(fundPrepareDTO);
        List<FundPrepareWeekBO> fundPrepareWeekBOs = new ArrayList<>();
        if (null != fundPrepareList && fundPrepareList.size() > 0) {
            for (FundPrepare fundPrepare : fundPrepareList) {
                FundPrepareWeekBO fundPrepareWeekBO = BeanTransform.copyProperties(fundPrepare, FundPrepareWeekBO.class, "time", "project", "id", "fourWeek", "thirdWeek", "secondWeek", "firstWeek");
                fundPrepareWeekBO.setFirstWeek(fundPrepare.getFund() / 4);
                fundPrepareWeekBO.setSecondWeek(fundPrepare.getFund() / 4);
                fundPrepareWeekBO.setThirdWeek(fundPrepare.getFund() / 4);
                fundPrepareWeekBO.setFourWeek(fundPrepare.getFund() / 4);
                fundPrepareWeekBO.setId(fundPrepare.getId());
                fundPrepareWeekBOs.add(fundPrepareWeekBO);
            }
        } else {
            throw new SerException("查无当月的资金准备");
        }
        return fundPrepareWeekBOs;
    }

    @Override
    public List<FundPrepareMonthBO> monthCollect() throws SerException {
        //对时间进行处理,转换为当月1号
        StringBuffer sb = new StringBuffer();
        String nowTime = LocalDate.now().toString();
//      LocalDate localDate =LocalDate.parse(fundPrepareTO.getTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        sb.append(nowTime.subSequence(0, nowTime.lastIndexOf("-") + 1) + "01");
        //当月1号
        LocalDate month = LocalDate.parse(sb.toString());
        //前一个月
        LocalDate month1 = LocalDate.parse(sb.toString()).minusMonths(1);
        //前两个月
        LocalDate month2 = LocalDate.parse(sb.toString()).minusMonths(2);
        //前三个月
        LocalDate month3 = LocalDate.parse(sb.toString()).minusMonths(3);

        //查询当月的资金准备
        FundPrepareDTO fundPrepareDTO = new FundPrepareDTO();
        fundPrepareDTO.getConditions().add(Restrict.eq("time", month.toString()));
        List<FundPrepare> list = super.findByCis(fundPrepareDTO);

        //查询前一个月的资金准备
        FundPrepareDTO fundPrepareDTO1 = new FundPrepareDTO();
        fundPrepareDTO1.getConditions().add(Restrict.eq("time", month1.toString()));
        List<FundPrepare> list1 = super.findByCis(fundPrepareDTO1);

        //查询前两个月的资金准备
        FundPrepareDTO fundPrepareDTO2 = new FundPrepareDTO();
        fundPrepareDTO2.getConditions().add(Restrict.eq("time", month2.toString()));
        List<FundPrepare> list2 = super.findByCis(fundPrepareDTO2);

        //查询前三个月的资金准备
        FundPrepareDTO fundPrepareDTO3 = new FundPrepareDTO();
        fundPrepareDTO3.getConditions().add(Restrict.eq("time", month3.toString()));
        List<FundPrepare> list3 = super.findByCis(fundPrepareDTO3);

        List<FundPrepareMonthBO> fundPrepareMonthBOList = new ArrayList<>();

        if (null != list && list.size() > 0) {
            for (FundPrepare bo : list) {
                FundPrepareMonthBO fundPrepareMonthBO = new FundPrepareMonthBO();
                fundPrepareMonthBO.setFirstSubject(bo.getFirstSubject());
                fundPrepareMonthBO.setSecondSubject(bo.getSecondSubject());
                fundPrepareMonthBO.setFund(bo.getFund());
                fundPrepareMonthBO.setId(bo.getId());
                fundPrepareMonthBOList.add(fundPrepareMonthBO);
            }
        }
        if (null != fundPrepareMonthBOList && fundPrepareMonthBOList.size() > 0) {
            for (FundPrepareMonthBO fundPrepareMonthBO : fundPrepareMonthBOList) {
                if (null != list1 && list1.size() > 0) {
                    for (FundPrepare bo1 : list1) {
                        if (fundPrepareMonthBO.getSecondSubject().equals(bo1.getSecondSubject())) {
                            fundPrepareMonthBO.setFund1(bo1.getFund());
                        }
                    }
                }
                if (null != list2 && list2.size() > 0) {
                    for (FundPrepare bo2 : list2) {
                        if (fundPrepareMonthBO.getSecondSubject().equals(bo2.getSecondSubject())) {
                            fundPrepareMonthBO.setFund2(bo2.getFund());
                        }
                    }
                }
                if (null != list3 && list3.size() > 0) {
                    for (FundPrepare bo3 : list3) {
                        if (fundPrepareMonthBO.getSecondSubject().equals(bo3.getSecondSubject())) {
                            fundPrepareMonthBO.setFund3(bo3.getFund());
                        }
                    }
                }
            }
        }
        return fundPrepareMonthBOList;
    }

    @Override
    public List<FundPrepareYearBO> yearCollect() throws SerException {
        //对时间进行处理,转换为当月1号
        StringBuffer sb = new StringBuffer();
        String nowTime = LocalDate.now().toString();
//      LocalDate localDate =LocalDate.parse(fundPrepareTO.getTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        sb.append(nowTime.subSequence(0, nowTime.lastIndexOf("-") + 1) + "01");
        //当月1号
        LocalDate month = LocalDate.parse(sb.toString());

        int numberMonth = month.getMonthValue();

        //今年资金准备
        List<FundPrepareBO> list = findYearFund(month.toString());
        //前一年资金准备
        List<FundPrepareBO> list1 = findYearFund(month.minusMonths(numberMonth).toString());
        //前两年资金准备
        List<FundPrepareBO> list2 = findYearFund(month.minusMonths(12 + numberMonth).toString());
        //前三年资金准备
        List<FundPrepareBO> list3 = findYearFund(month.minusMonths(24 + numberMonth).toString());

        List<FundPrepareYearBO> fundPrepareYearBOList = new ArrayList<>();

        if (null != list && list.size() > 0) {
            for (FundPrepareBO bo : list) {
                FundPrepareYearBO fundPrepareYearBO = new FundPrepareYearBO();
                fundPrepareYearBO.setFirstSubject(bo.getFirstSubject());
                fundPrepareYearBO.setSecondSubject(bo.getSecondSubject());
                fundPrepareYearBO.setFund(bo.getFund());
                fundPrepareYearBO.setId(bo.getId());
                fundPrepareYearBOList.add(fundPrepareYearBO);
            }
        }
        if (null != fundPrepareYearBOList && fundPrepareYearBOList.size() > 0) {
            for (FundPrepareYearBO fundPrepareYearBO : fundPrepareYearBOList) {
                if (null != list1 && list1.size() > 0) {
                    for (FundPrepareBO bo1 : list1) {
                        if (fundPrepareYearBO.getSecondSubject().equals(bo1.getSecondSubject())) {
                            fundPrepareYearBO.setFund1(bo1.getFund());
                        }
                    }
                }
                if (null != list2 && list2.size() > 0) {
                    for (FundPrepareBO bo2 : list2) {
                        if (fundPrepareYearBO.getSecondSubject().equals(bo2.getSecondSubject())) {
                            fundPrepareYearBO.setFund1(bo2.getFund());
                        }
                    }
                }
                if (null != list3 && list3.size() > 0) {
                    for (FundPrepareBO bo3 : list3) {
                        if (fundPrepareYearBO.getSecondSubject().equals(bo3.getSecondSubject())) {
                            fundPrepareYearBO.setFund1(bo3.getFund());
                        }
                    }
                }
            }
        }
        return fundPrepareYearBOList;
    }

    @Override
    public List<FundPrepareanAlysisBO> analysis() throws SerException {
        //对时间进行处理,转换为当月1号
        StringBuffer sb = new StringBuffer();
        String nowTime = LocalDate.now().toString();
//      LocalDate localDate =LocalDate.parse(fundPrepareTO.getTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        sb.append(nowTime.subSequence(0, nowTime.lastIndexOf("-") + 1) + "01");
        //当月1号
        LocalDate month = LocalDate.parse(sb.toString());
        //前一个月
        LocalDate month1 = LocalDate.parse(sb.toString()).minusMonths(1);

        //查询当月的资金准备
        FundPrepareDTO fundPrepareDTO = new FundPrepareDTO();
        fundPrepareDTO.getConditions().add(Restrict.eq("time", month.toString()));
        List<FundPrepare> list = super.findByCis(fundPrepareDTO);

        //查询前一个月的资金准备
        FundPrepareDTO fundPrepareDTO1 = new FundPrepareDTO();
        fundPrepareDTO1.getConditions().add(Restrict.eq("time", month1.toString()));
        List<FundPrepare> list1 = super.findByCis(fundPrepareDTO1);

        List<FundPrepareanAlysisBO> fundPrepareanAlysisBOList = new ArrayList<>();

        if (null != list && list.size() > 0) {
            for (FundPrepare bo : list) {
                FundPrepareanAlysisBO fundPrepareanAlysisBO = new FundPrepareanAlysisBO();
                fundPrepareanAlysisBO.setFirstSubject(bo.getFirstSubject());
                fundPrepareanAlysisBO.setSecondSubject(bo.getSecondSubject());
                fundPrepareanAlysisBO.setFund(bo.getFund());
                fundPrepareanAlysisBO.setId(bo.getId());
                fundPrepareanAlysisBOList.add(fundPrepareanAlysisBO);
            }
        }
        if (null != fundPrepareanAlysisBOList && fundPrepareanAlysisBOList.size() > 0) {
            for (FundPrepareanAlysisBO fundPrepareanAlysisBO : fundPrepareanAlysisBOList) {
                if (null != list1 && list1.size() > 0) {
                    for (FundPrepare bo1 : list1) {
                        if (fundPrepareanAlysisBO.getSecondSubject().equals(bo1.getSecondSubject())) {
                            fundPrepareanAlysisBO.setFund1(bo1.getFund());
                            fundPrepareanAlysisBO.setBalance(fundPrepareanAlysisBO.getFund() - fundPrepareanAlysisBO.getFund1());
                            fundPrepareanAlysisBO.setIncrease(fundPrepareanAlysisBO.getBalance() / fundPrepareanAlysisBO.getFund1());
                        }
                    }
                }
            }
        }
        return fundPrepareanAlysisBOList;
    }


    @Override
    public List<FundPrepareBO> listFundPrepare(FundPrepareDTO fundPrepareDTO) throws SerException {
//        checkSeeIdentity();

        searchCondition(fundPrepareDTO);
        fundPrepareDTO.getSorts().add("time=desc");
        List<FundPrepare> list = super.findByPage(fundPrepareDTO);
        List<FundPrepareBO> fundPrepareBOS = new ArrayList<>();
        list.stream().forEach(str -> {
            FundPrepareBO bo = BeanTransform.copyProperties(str, FundPrepareBO.class);
            fundPrepareBOS.add(bo);
        });
        return fundPrepareBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<FundPrepareBO> addFundPrepare(FundPrepareObjectTO fundPrepareObjectTO) throws SerException {
//        checkAddIdentity();
        List<FundPrepareBO> bos = new ArrayList<>();
        if (null != fundPrepareObjectTO.getFundPrepareTOList() && fundPrepareObjectTO.getFundPrepareTOList().size() > 0) {
            for (FundPrepareTO fundPrepareTO : fundPrepareObjectTO.getFundPrepareTOList()) {
                try {
                    DateUtil.parseDate(fundPrepareTO.getTime());
                } catch (Exception e) {
                    throw new SerException("输入的日期格式不对,格式为yyyy-MM-dd");
                }
                //对时间进行处理
                StringBuffer sb = new StringBuffer();
//                LocalDate localDate =LocalDate.parse(fundPrepareTO.getTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                sb.append(fundPrepareTO.getTime().subSequence(0, fundPrepareTO.getTime().lastIndexOf("-") + 1) + "01");
                fundPrepareTO.setTime(sb.toString());

                FundPrepare fundPrepare = BeanTransform.copyProperties(fundPrepareTO, FundPrepare.class);

                fundPrepare.setCreateTime(LocalDateTime.now());
                super.save(fundPrepare);
                if (isExist(fundPrepare)) {
                    throw new SerException("添加的数据重复,请检查后重新输入");
                }

                FundPrepareBO fundPrepareBO = BeanTransform.copyProperties(fundPrepare, FundPrepareBO.class);
                bos.add(fundPrepareBO);
            }
        }
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FundPrepareBO editFundPrepare(FundPrepareTO fundPrepareTO) throws SerException {
//        checkAddIdentity();

        FundPrepare temp = super.findById(fundPrepareTO.getId());

        try {
            DateUtil.parseDate(fundPrepareTO.getTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式不对,格式为yyyy-MM-dd");
        }
        //对时间进行处理
        StringBuffer sb = new StringBuffer();
//      LocalDate localDate =LocalDate.parse(fundPrepareTO.getTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        sb.append(fundPrepareTO.getTime().subSequence(0, fundPrepareTO.getTime().lastIndexOf("-") + 1) + "01");
        fundPrepareTO.setTime(sb.toString());

        FundPrepare fundPrepare = BeanTransform.copyProperties(fundPrepareTO, FundPrepare.class);
        BeanUtils.copyProperties(fundPrepare, temp, "createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        FundPrepareBO fundPrepareBO = BeanTransform.copyProperties(temp, FundPrepareBO.class);
        return fundPrepareBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteFundPrepare(String id) throws SerException {
//        checkAddIdentity();

        super.remove(id);
    }

    @Override
    public List<String> findFirstSubject() throws SerException {
        //从财务模块得到一级科目
        List<AccountAddDateBO> list = accountanCourseAPI.findFirstNameCode();
        if (null != list && list.size() > 0) {
            List<String> strings = list.stream().map(AccountAddDateBO::getAccountanName).distinct().collect(Collectors.toList());
            return strings;
        }
        return null;
    }

    @Override
    public List<String> findSecondSubject(String firstSubject) throws SerException {
        if (StringUtils.isBlank(firstSubject)) {
            throw new SerException("一级科目名称不能为空");
        }
        //从财务模块得到三级科目
        return accountanCourseAPI.findByFirstName(firstSubject);
    }


    public void searchCondition(FundPrepareDTO fundPrepareDTO) throws SerException {
        /**
         * 时间
         */
        if (StringUtils.isNotBlank(fundPrepareDTO.getTime())) {
            fundPrepareDTO.getConditions().add(Restrict.eq("time", fundPrepareDTO.getTime()));
        }

        /**
         * 一级科目
         */
        if (StringUtils.isNotBlank(fundPrepareDTO.getFirstSubject())) {
            fundPrepareDTO.getConditions().add(Restrict.like("firstSubject", fundPrepareDTO.getFirstSubject()));
        }
        /**
         * 二级科目
         */
        if (StringUtils.isNotBlank(fundPrepareDTO.getSecondSubject())) {
            fundPrepareDTO.getConditions().add(Restrict.like("secondSubject", fundPrepareDTO.getSecondSubject()));
        }
        /**
         * 准备金
         */
        if (null != fundPrepareDTO.getFund()) {
            fundPrepareDTO.getConditions().add(Restrict.like("fund", fundPrepareDTO.getFund()));
        }
    }


    //查询本年1月到本月的资金准备总数---month(yyyy-MM-dd)
    private List<FundPrepareBO> findYearFund(String month) throws SerException {
        LocalDate localDate = LocalDate.parse(month);
        //本年1月1号
        StringBuffer sb = new StringBuffer();
        sb.append(localDate.getYear() + "-01-01");
        String yearDay = sb.toString();

        String sql = "select sum(fund) AS fund,firstSubject as firstSubject,secondSubject as secondSubject FROM moneyprepare_fundprepare WHERE time BETWEEN '" + yearDay + " ' AND '" + month + "' GROUP BY firstSubject ,secondSubject  ORDER BY firstSubject,secondSubject,sum(fund)";

        String[] fields = new String[]{"fund", "firstSubject", "secondSubject"};
        List<FundPrepareBO> fundPrepareBOList = super.findBySql(sql, FundPrepareBO.class, fields);
        return fundPrepareBOList;

//        "select sum(fund),firstSubject,secondSubject FROM moneyprepare_fundprepare WHERE time BETWEEN \"2016-01-01\" AND \"2016-12-01\" GROUP BY firstSubject ,secondSubject  ORDER BY firstSubject,secondSubject,sum(fund)"
    }

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
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public List<ProjectCollectBO> projectCollect() throws SerException {
        //对时间进行处理,转换为当月1号
        StringBuffer sb = new StringBuffer();
        String nowTime = LocalDate.now().toString();
//      LocalDate localDate =LocalDate.parse(fundPrepareTO.getTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        sb.append(nowTime.subSequence(0, nowTime.lastIndexOf("-") + 1) + "01");
        //当月1号
        LocalDate month = LocalDate.parse(sb.toString());

        //查询当月的资金准备
        FundPrepareDTO fundPrepareDTO = new FundPrepareDTO();
        fundPrepareDTO.getConditions().add(Restrict.eq("time", month.toString()));
        List<FundPrepare> list = super.findByCis(fundPrepareDTO);

        //查询项目组的个数,按时间查询
        ProportionDTO proportionDTO = new ProportionDTO();
        proportionDTO.setTime(month.toString());
        List<ProportionBO> proportionBOs = proportionAPI.listProportion(proportionDTO);
        int num = proportionBOs.size();

        if (num == 0) {
            throw new SerException("该月份的比例分配暂无");
        }

        List<ProjectCollectBO> projectCollectBOList = new ArrayList<>();
        for (FundPrepare fundPrepare : list) {
            ProjectCollectBO projectCollectBO = new ProjectCollectBO();
            projectCollectBO.setFirstSubject(fundPrepare.getFirstSubject());
            projectCollectBO.setSecondSubject(fundPrepare.getSecondSubject());
            projectCollectBO.setFund(fundPrepare.getFund());
            projectCollectBOList.add(projectCollectBO);
        }
//        int i = 0;
//        Double[] strs = new Double[num];
        //得到项目名数组
//        String[] names = new String[num];

        List<Double[]> listString = new ArrayList<>();
        for (ProjectCollectBO projectCollectBO : projectCollectBOList) {
//            names[i] = bo.getProjectTeam();
            List<ProBO> proBOs = new ArrayList<>();
            for (ProportionBO bo : proportionBOs) {
                Double[] strings = new Double[num];
//                strings[i] = bo.getRatio() * projectCollectBO.getFund();
//
//                strs[i] = strings[i];
//                listString.add(strs);
                ProBO proBO = new ProBO();
                proBO.setName(bo.getProjectTeam());
                proBO.setFund(bo.getRatio() * projectCollectBO.getFund());
                proBOs.add(proBO);

            }
            projectCollectBO.setList(proBOs);
//            i += 1;
        }

        return projectCollectBOList;
    }

    @Override
    public List<ProportionBO> editProportion(ProportionObjectTO proportionObjectTO) throws SerException {
        List<ProportionTO> proportionTOList = proportionObjectTO.getProportionTOList();

        if (StringUtils.isBlank(proportionObjectTO.getFundId())) {
            throw new SerException("id不能为空");
        }

        FundPrepareBO fundPrepareBO = getOneById(proportionObjectTO.getFundId());
        Double totalFund = fundPrepareBO.getFund();

        List<ProportionBO> proportionBOs = new ArrayList<>();
        Double funds = 0d;
        if (null != proportionTOList && proportionTOList.size() > 0) {
            for (ProportionTO to : proportionTOList) {
                funds += to.getFund();
            }
            if (funds > totalFund) {
                throw new SerException("各个项目组资金总额不符合预先设定的值");
            }
            for (ProportionTO proportionTO : proportionTOList) {


                Proportion proportion = BeanTransform.copyProperties(proportionTO, Proportion.class, "id", "createTime", "fund");
//                BeanUtils.copyProperties(fundPrepare, temp, "id", "createTime");
                proportion.setModifyTime(LocalDateTime.now());
                ProportionBO proportionBO = proportionAPI.editProportion(proportionTO);

//                FundPrepareBO fundPrepareBO = BeanTransform.copyProperties(temp, FundPrepareBO.class);
                proportionBOs.add(proportionBO);
            }

        }
        return proportionBOs;
    }

    //判断当月的数据是否已存在
    private Boolean isExist(FundPrepare fundPrepare) throws SerException {
        Boolean tar = false;
        FundPrepareDTO dto = new FundPrepareDTO();
        dto.getConditions().add(Restrict.eq("time", fundPrepare.getTime()));
        dto.getConditions().add(Restrict.eq("firstSubject", fundPrepare.getFirstSubject()));
        dto.getConditions().add(Restrict.eq("secondSubject", fundPrepare.getSecondSubject()));
        List<FundPrepare> fundPrepares = super.findByCis(dto);
        if (fundPrepares.size() >= 2) {
            tar = true;
        }
        return tar;
    }
}