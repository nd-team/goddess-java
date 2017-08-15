package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.staffshares.bo.CompanySchemeBO;
import com.bjike.goddess.staffshares.bo.DividendsBO;
import com.bjike.goddess.staffshares.bo.DividendsConditionsBO;
import com.bjike.goddess.staffshares.bo.DividendsDetailBO;
import com.bjike.goddess.staffshares.dto.DividendsDTO;
import com.bjike.goddess.staffshares.entity.*;
import com.bjike.goddess.staffshares.enums.GuideAddrStatus;
import com.bjike.goddess.staffshares.enums.Status;
import com.bjike.goddess.staffshares.to.DividendsTO;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 干股分红表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 11:14 ]
 * @Description: [ 干股分红表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffsharesSerCache")
@Service
public class DividendsSerImpl extends ServiceImpl<Dividends, DividendsDTO> implements DividendsSer {
    @Autowired
    private SchemeSer schemeSer;
    @Autowired
    private CompanysSchemeSer companysSchemeSer;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private BuyscheduleSer buyscheduleSer;
    @Autowired
    private SellscheduleSer sellscheduleSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
                throw new SerException("您不是相应部门的人员，不可以操作");
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
     * 核对查看权限（部门级别）
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
     * 核对添加修改删除审核权限（岗位级别）
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
    public List<CompanySchemeBO> detail() throws SerException {
        List<Scheme> schemeList = schemeSer.findAll().stream().filter(obj -> obj.getStatus().equals(Status.ISSUED)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(schemeList)) {
            for (Scheme scheme : schemeList) {
                CompanysScheme companySchemeBO = new CompanysScheme();
                companySchemeBO.setCode(scheme.getCode());
                companySchemeBO.setName(scheme.getName());
                companySchemeBO.setPublisher(scheme.getPublisher());
                companySchemeBO.setNumber(scheme.getNumber());
                companySchemeBO.setPrice(scheme.getPrice());
                companySchemeBO.setTime(scheme.getTime());
                companySchemeBO.setQuantityNum(scheme.getNumber() - scheme.getSharesNum());
                companySchemeBO.setSharesNum(scheme.getSharesNum());
                companySchemeBO.setMoney((scheme.getNumber() - scheme.getSharesNum()) * scheme.getPrice());
                if (companySchemeBO.getSharesNum() > 0) {
                    companySchemeBO.setStatus("发行中");
                } else {
                    companySchemeBO.setStatus("结束");
                }
                companysSchemeSer.save(companySchemeBO);
            }
        }
        List<CompanySchemeBO> companySchemeBOs = BeanTransform.copyProperties(companysSchemeSer.findAll(), CompanySchemeBO.class, false);
        return companySchemeBOs;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void dividends(DividendsTO to) throws SerException {
        CompanysScheme companysScheme = companysSchemeSer.findById(to.getId());
        if (companysScheme == null) {
            throw new SerException("数据对象为空");
        }
        String time = getDate(to.getTime());

        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        if (!cheakFinance(userBO) && !"admin".equals(userBO.getUsername())) {
            throw new SerException("当前用户不是财务部人员，没有权限");
        }
        Dividends entity = company(to, companysScheme);

        //持股天数
        int days = 0;

        //查询所有的持股人的购买记录
        List<Buyschedule> buyschedules = buyscheduleSer.findAll();
        if (!CollectionUtils.isEmpty(buyschedules)) {
            //得到方案代码一样的购买记录
            buyschedules = buyschedules.stream().filter(obj -> obj.getCode().equals(entity.getCode())).collect(Collectors.toList());
        }
        //查询所有的出售记录
        List<Sellschedule> sellschedules = sellscheduleSer.findAll();
        if (!CollectionUtils.isEmpty(sellschedules)) {
            sellschedules = sellschedules.stream().filter(obj -> obj.getCode().equals(entity.getCode())).collect(Collectors.toList());
        }

        //有购买没卖
        List<Buyschedule> buyschedules1 = new ArrayList<>(0);
        //有购买有卖出
        List<Buyschedule> buyschedules2 = new ArrayList<>(0);
        //持股天数计算１，有购买没卖，交易时间
        if (!CollectionUtils.isEmpty(buyschedules) && !CollectionUtils.isEmpty(sellschedules)) {
            for (Buyschedule buyschedule : buyschedules) {
                if (!sellschedules.contains(buyschedule.getShareholder())) {
                    //有购买没卖的
                    buyschedules1.add(buyschedule);
                } else {
                    buyschedules2.add(buyschedule);
                }
            }
        }
        buyNotSell(buyschedules1, companysScheme, to, time);
        //持股天数计算２：有购买有卖出,卖出后股数不为0
        buyAndSell(sellschedules, to, companysScheme, time);
    }

    @Override
    public List<DividendsBO> maps(DividendsDTO dto) throws SerException {
//        checkSeeIdentity();
        List<Dividends> list = super.findByPage(dto);
        if (!CollectionUtils.isEmpty(list)) {
            list = list.stream().filter(obj -> !obj.getShareholder().equals("公司")).collect(Collectors.toList());
            List<DividendsBO> schemeBOList = BeanTransform.copyProperties(list, DividendsBO.class, false);
            return schemeBOList;
        }
        return null;
    }

    @Override
    public DividendsBO getById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        Dividends dividends = super.findById(id);
        return BeanTransform.copyProperties(dividends, DividendsBO.class);
    }


    @Override
    public Long getTotal(DividendsDTO dto) throws SerException {
        searchCondition(dto);
        Long count = super.count(dto);
        dto.getConditions().add(Restrict.eq("shareholder", "公司"));
        Long count1 = super.count(dto);
        return count - count1;
    }

    @Override
    public void confirm(DividendsTO to) throws SerException {
        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("id不能为空");
        }
        Dividends entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标对象不能为空");
        }
        entity.setSituation(to.getSituation());
        entity.setRemark(to.getRemark());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<DividendsDetailBO> list(DividendsDTO dto) throws SerException {
//        checkSeeIdentity();

        searchCondition(dto);
        List<Dividends> list = super.findByPage(dto);
        if (!CollectionUtils.isEmpty(list)) {
            List<DividendsDetailBO> dividendsDetailBOs = BeanTransform.copyProperties(list.stream().filter(obj -> obj.getNum() > 0 && !obj.getShareholder().equals("公司")).collect(Collectors.toList()), DividendsDetailBO.class, false, "taxProfit", "time", "remark", "status", "earnings");
            for (DividendsDetailBO dividendsDetailBO : dividendsDetailBOs) {
                dividendsDetailBO.setEarnings(dividendsDetailBO.getTotalEarnings());
                dividendsDetailBO.setStatus("持股中");
            }
            return dividendsDetailBOs;
        }
        return null;
    }

    @Override
    public DividendsDetailBO find(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        Dividends dividends = super.findById(id);
        if (null == dividends && dividends.getNum() <= 0) {
            throw new SerException("目标数据不存在");
        }
        DividendsDetailBO dividendsDetailBO = BeanTransform.copyProperties(dividends, DividendsDetailBO.class, false, "taxProfit", "time", "remark", "status", "earnings");
        dividendsDetailBO.setEarnings(dividendsDetailBO.getTotalEarnings());
        return dividendsDetailBO;
    }

    @Override
    public Long getCount(DividendsDTO dto) throws SerException {
        searchCondition(dto);
        Long count = super.count(dto);
        dto.getConditions().add(Restrict.eq("num", "0"));
        dto.getConditions().add(Restrict.eq("shareholder", "公司"));
        Long count1 = super.count(dto);
        return count - count1;
    }

    @Override
    public List<DividendsDetailBO> collect() throws SerException {
        String[] fields = new String[]{"code", "name", "shareholder", "num", "totalEquity", "totalEarnings", "earnings"};
        StringBuilder sql = new StringBuilder("select code, name, shareholder, ");
        sql.append(" sum(num) as num, ");
        sql.append(" sum(totalEquity) as totalEquity, ");
        sql.append(" sum(totalEarnings) as totalEarnings, ");
        sql.append(" sum(earnings) as earnings ");
        sql.append(" from staffshares_dividends ");
        sql.append(" group by code, name, shareholder; ");
        List<DividendsDetailBO> dividendsDetailBOs = super.findBySql(sql.toString(), DividendsDetailBO.class, fields);
        if (!CollectionUtils.isEmpty(dividendsDetailBOs)) {
            for (DividendsDetailBO dividendsDetailBO : dividendsDetailBOs) {
                dividendsDetailBO.setEarnings(dividendsDetailBO.getTotalEarnings());
            }
        }
        if (!CollectionUtils.isEmpty(dividendsDetailBOs)) {
            dividendsDetailBOs = dividendsDetailBOs.stream().filter(obj -> !obj.getShareholder().equals("公司")).collect(Collectors.toList());
        }
        return dividendsDetailBOs;
    }

    @Override
    public List<DividendsConditionsBO> detailList() throws SerException {
        StringBuilder sql = new StringBuilder("select sum(number) as number, ");
        sql.append(" sum(totalEquity) as totalEquity ");
        sql.append(" from staffshares_scheme ");
        sql.append(" where status = '4' ");
        String[] fields = new String[]{"number", "totalEquity"};
        List<Scheme> schemeList = schemeSer.findBySql(sql.toString(), Scheme.class, fields);
        DividendsConditionsBO dividendsConditionsBO = new DividendsConditionsBO();
        if (!CollectionUtils.isEmpty(schemeList)) {
            Scheme scheme = schemeList.get(0);
            dividendsConditionsBO.setTotalNum(scheme.getNumber());
            dividendsConditionsBO.setIssuance(scheme.getTotalEquity());
        }
        String[] fields1 = new String[]{"shareholderNum", "num", "totalEquity", "totalEarnings"};
        StringBuilder sql1 = new StringBuilder("select distinct count(shareholder) as shareholderNum, ");
        sql1.append(" sum(num) as num, ");
        sql1.append(" sum(totalEquity) as totalEquity, ");
        sql1.append(" sum(totalEarnings) as totalEarnings ");
        sql1.append(" from staffshares_dividends");
        List<DividendsConditionsBO> dividendsConditionsBOs = super.findBySql(sql1.toString(), DividendsConditionsBO.class, fields1);
        if (!CollectionUtils.isEmpty(dividendsConditionsBOs)) {
            DividendsConditionsBO bo = dividendsConditionsBOs.get(0);
            dividendsConditionsBO.setShareholderNum(bo.getShareholderNum());
            dividendsConditionsBO.setNum(bo.getNum());
            dividendsConditionsBO.setTotalEquity(bo.getTotalEquity());
            dividendsConditionsBO.setTotalEarnings(bo.getTotalEarnings());
            DecimalFormat df = new DecimalFormat("#.##");
            double d = dividendsConditionsBO.getNum() / dividendsConditionsBO.getTotalNum() * 100;
            String ratio = df.format(d) + "%";
            dividendsConditionsBO.setRatio(ratio);
        }
        List<DividendsConditionsBO> dividendsConditionsBOList = new ArrayList<DividendsConditionsBO>();
        dividendsConditionsBOList.add(dividendsConditionsBO);
        return dividendsConditionsBOList;
    }

    private Boolean cheakFinance(UserBO userBO) throws SerException {
        Boolean tar = false;
        List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId()).stream().filter(str -> str.getStatus().equals(com.bjike.goddess.common.api.type.Status.THAW)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(positionDetailBOs)) {
            for (PositionDetailBO positionDetailBO : positionDetailBOs) {
                if ("财务运营部".equals(positionDetailBO.getDepartmentName()) && "admin".equals(userBO.getUsername())) {
                    tar = true;
                }
            }
        }
        return tar;
    }

    private void searchCondition(DividendsDTO dto) throws SerException {
        /**
         * 方案代码
         */
        if (StringUtils.isNotBlank(dto.getCode())) {
            dto.getConditions().add(Restrict.eq("code", dto.getCode()));
        }
        /**
         * 方案名称
         */
        if (StringUtils.isNotBlank(dto.getName())) {
            dto.getConditions().add(Restrict.eq("name", dto.getName()));
        }/**
         * 税后利润
         */
        if (null != dto.getTaxProfit()) {
            dto.getConditions().add(Restrict.like("taxProfit", dto.getTaxProfit()));
        }
        /**
         * 持股数
         */
        if (null != dto.getNum()) {
            dto.getConditions().add(Restrict.like("num", dto.getNum()));
        }
        /**
         * 总股本
         */
        if (null != dto.getTotalEquity()) {
            dto.getConditions().add(Restrict.eq("totalEquity", dto.getTotalEquity()));
        }
        /**
         * 每股收益
         */
        if (null != dto.getEarnings()) {
            dto.getConditions().add(Restrict.eq("earnings", dto.getEarnings()));
        }/**
         * 总收益/分红
         */
        if (null != dto.getTotalEarnings()) {
            dto.getConditions().add(Restrict.like("totalEarnings", dto.getTotalEarnings()));
        }
        /**
         * 购入时间
         */
        if (StringUtils.isNotBlank(dto.getBuyTime())) {
            dto.getConditions().add(Restrict.like("buyTime", dto.getBuyTime()));
        }
        /**
         * 持股时长
         */
        if (0 < dto.getDuration()) {
            dto.getConditions().add(Restrict.eq("duration", dto.getDuration()));
        }/**
         * 分红发放时间
         */
        if (StringUtils.isNotBlank(dto.getDividendTime())) {
            dto.getConditions().add(Restrict.like("dividendTime", dto.getDividendTime()));
        }
        /**
         * 本次红利收益时间段
         */
        if (StringUtils.isNotBlank(dto.getTime())) {
            dto.getConditions().add(Restrict.like("time", dto.getTime()));
        }

        /**
         * 备注
         */
        if (StringUtils.isNotBlank(dto.getRemark())) {
            dto.getConditions().add(Restrict.eq("remark", dto.getRemark()));
        }/**
         * 持股人确认情况
         */
        if (null != dto.getSituation()) {
            dto.getConditions().add(Restrict.like("situation", dto.getSituation()));
        }
        /**
         * 持股人
         */
        if (StringUtils.isNotBlank(dto.getShareholder())) {
            dto.getConditions().add(Restrict.like("shareholder", dto.getShareholder()));
        }
    }

    //计算两个日期之间的天数
    public long getdaySpace(String date1, String date2) throws SerException {

        try {
            Date a1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
            Date b1 = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
            //获取相减后天数
            long day = (a1.getTime() - b1.getTime()) / (24 * 60 * 60 * 1000);
            if (day < 0) {
                day = -day;
            }
            return day;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    public static void main(String[] args) throws SerException {
//        try {
//            System.out.print(getdaySpace("2017-01-01", "2017-09-01"));
//        } catch (SerException e) {
//            throw null;
//        }
//        new DividendsSerImpl().getDate("2017-01-01~2018-02-01");
        System.out.print(LocalDateTime.now().toString().substring(0, LocalDateTime.now().toString().indexOf("T")));
    }

    private String getDate(String time) throws SerException {
//        String time = to.getTime();
        if (-1 == time.indexOf("~")) {
            throw new SerException("时间段格式为yyyy-MM-dd~yyyy-MM-dd");
        } else {
            String start = time.substring(0, time.indexOf("~"));
            String end = time.substring(time.indexOf("~") + 1, time.length());
            checkDate(start, end);
            if (isOrder(start, end)) {
                throw new SerException("时间段第一个日期要先于第二个日期");
            }
            return time;
        }
    }

    /**
     * 时间格式（年月日）
     */
    private void checkDate(String start, String end) throws SerException {
        try {
            DateUtil.parseDate(start);
            DateUtil.parseDate(end);
        } catch (Exception e) {
            throw new SerException("时间段格式为yyyy-MM-dd~yyyy-MM-dd");
        }
    }

    //判断两个日期的先后,date1<date2,返回true，否则返回false
    public Boolean isOrder(String date1, String date2) throws SerException {
        Boolean tar = false;
        try {
            Date a1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
            Date b1 = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
            //获取相减后天数
            long day = (a1.getTime() - b1.getTime()) / (24 * 60 * 60 * 1000);
            if (day > 0) {
                tar = true;
            }
            return tar;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    //公司分红
    private Dividends company(DividendsTO to, CompanysScheme companysScheme) throws SerException {
        Dividends entity = new Dividends();
        BeanTransform.copyProperties(to, entity, true);
        entity.setShareholder("公司");
        entity.setCode(companysScheme.getCode());
        entity.setName(companysScheme.getName());
        entity.setTaxProfit(to.getTaxProfit());
        entity.setNum(companysScheme.getNumber());
        entity.setTotalEquity(companysScheme.getNumber() * companysScheme.getPrice());
        entity.setEarnings(entity.getTaxProfit() / entity.getTotalEquity());
        entity.setTotalEarnings(entity.getEarnings() * entity.getNum());
//        entity.setBuyTime();
//        entity.setDuration();
        entity.setDividendTime(LocalDate.parse(to.getDividendTime()));
        entity.setTime(to.getTime());
//        entity.setRemark(to.getRemark());
        entity.setSituation(false);
        super.save(entity);
        return entity;
    }

    //持股时长
    private int getDays(Dividends entity, String time) throws SerException {
        int days = 0;
        //购买时间先与分红时间段
        //购买时间
        String buyTime = entity.getBuyTime().toString().substring(0, entity.getBuyTime().toString().indexOf("T"));
        //分红时间段第一个日期
        String start = time.substring(0, time.indexOf("~"));
        //分红时间段第二个日期
        String end = time.substring(time.indexOf("~") + 1, time.length());
        if (isOrder(buyTime, start)) {
            days = (int) getdaySpace(start, end);

        }//购买时间在分红时间段之间
        else if (isOrder(start, buyTime) && isOrder(buyTime, end)) {
            days = (int) getdaySpace(buyTime, end);
        }//购买时间在分红时间段之后
        else if (isOrder(end, buyTime)) {
            days = 0;
        }
        return days;
    }

    //有购买没卖持股人分红
    private void buyNotSell(List<Buyschedule> buyschedules, CompanysScheme companysScheme, DividendsTO to, String time) throws SerException {
        if (!CollectionUtils.isEmpty(buyschedules)) {
            for (Buyschedule buyschedule : buyschedules) {
                Dividends entity1 = new Dividends();
                entity1.setShareholder(buyschedule.getShareholder());
                entity1.setCode(buyschedule.getCode());
                entity1.setName(buyschedule.getName());
                entity1.setTaxProfit(to.getTaxProfit());
                entity1.setNum(buyschedule.getPurchaseNum());
                entity1.setTotalEquity(entity1.getNum() * companysScheme.getPrice());
                entity1.setEarnings(entity1.getTaxProfit() / entity1.getTotalEquity());
                entity1.setTotalEarnings(entity1.getNum() * entity1.getEarnings());
                entity1.setBuyTime(buyschedule.getTime());
                entity1.setDuration(getDays(entity1, time));
                entity1.setDividendTime(LocalDate.parse(to.getDividendTime()));
                entity1.setTime(to.getTime());
//                entity1.setRemark();
                entity1.setSituation(false);
                super.save(entity1);
            }
        }
    }

    //有购买有卖出，但卖出有剩股数
    private void buyAndSell(List<Sellschedule> sellschedules, DividendsTO to, CompanysScheme companysScheme, String time) throws SerException {
        sellschedules = sellschedules.stream().filter(obj -> obj.getNumber() > 0).collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(sellschedules)) {
            for (Sellschedule sellschedule : sellschedules) {
                Dividends entity2 = new Dividends();

                entity2.setShareholder(sellschedule.getSellName());
                entity2.setCode(sellschedule.getCode());
                entity2.setName(sellschedule.getName());
                entity2.setTaxProfit(to.getTaxProfit());
                entity2.setNum(sellschedule.getNumber());
                entity2.setTotalEquity(entity2.getNum() * companysScheme.getPrice());
                entity2.setEarnings(entity2.getTaxProfit() / entity2.getTotalEquity());
                entity2.setTotalEarnings(entity2.getNum() * entity2.getEarnings());
                entity2.setBuyTime(sellschedule.getBuyTime());
                entity2.setDuration(getDays(entity2, time));
                entity2.setDividendTime(LocalDate.parse(to.getDividendTime()));
                entity2.setTime(to.getTime());
//                entity2.setRemark();
                entity2.setSituation(false);
                super.save(entity2);
            }
        }

    }
}