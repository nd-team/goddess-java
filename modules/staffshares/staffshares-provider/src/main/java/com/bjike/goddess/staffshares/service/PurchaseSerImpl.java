package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.bonus.api.DisciplineRecordAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.staffentry.api.StaffEntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.LinkDateStaffEntryBO;
import com.bjike.goddess.staffshares.api.SchemeAPI;
import com.bjike.goddess.staffshares.bo.PurchaseBO;
import com.bjike.goddess.staffshares.bo.SchemeIssueBO;
import com.bjike.goddess.staffshares.dto.BuyscheduleDTO;
import com.bjike.goddess.staffshares.dto.DetailsDTO;
import com.bjike.goddess.staffshares.dto.PurchaseDTO;
import com.bjike.goddess.staffshares.dto.SellscheduleDTO;
import com.bjike.goddess.staffshares.entity.Buyschedule;
import com.bjike.goddess.staffshares.entity.Details;
import com.bjike.goddess.staffshares.entity.Purchase;
import com.bjike.goddess.staffshares.entity.Sellschedule;
import com.bjike.goddess.staffshares.enums.GuideAddrStatus;
import com.bjike.goddess.staffshares.enums.Status1;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import com.bjike.goddess.staffshares.to.PurchaseTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

/**
 * 干股申购表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 09:51 ]
 * @Description: [ 干股申购表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffsharesSerCache")
@Service
public class PurchaseSerImpl extends ServiceImpl<Purchase, PurchaseDTO> implements PurchaseSer {
    @Autowired
    private SchemeAPI schemeAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
        @Autowired
    private StaffEntryRegisterAPI staffEntryRegisterAPI;
    @Autowired
    private DisciplineRecordAPI disciplineRecordAPI;
    @Autowired
    private BuyscheduleSer buyscheduleSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private DetailsSer detailsSer;
    @Autowired
    private SellscheduleSer sellscheduleSer;
    @Autowired
    private ModuleAPI moduleAPI;
//    @Autowired
//    private AgeAssistAPI ageAssistAPI;

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
    public void buy(PurchaseTO to) throws SerException {
//        checkAddIdentity();
        if (to.getPurchaseNum() <= 0) {
            throw new SerException("购买股数不能为0");
        }
        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("id不能为空");
        }
        SchemeIssueBO schemeIssueBO = schemeAPI.getOne(to.getId());
        if (null == schemeIssueBO) {
            throw new SerException("查无此条交易数据");
        }
        if (to.getPurchaseNum() > schemeIssueBO.getSharesNum()) {
            throw new SerException("购买股数不能超过出售剩余的股数");
        }

        Purchase entity = new Purchase();
        entity.setStatus(Status1.SUBMIT);
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        entity.setName(userBO.getUsername());
        entity.setTime(LocalDateTime.now());
        entity.setCode(schemeIssueBO.getCode());
        entity.setIssueName(schemeIssueBO.getName());
        entity.setFacevalue(schemeIssueBO.getFacevalue());
        entity.setPrice(schemeIssueBO.getPrice());
        //根据用户名得到用户数据
        List<PositionDetailBO> positionDetailBOList = positionDetailUserAPI.getPositionDetail(userBO.getUsername());
        if (null != positionDetailBOList && positionDetailBOList.size() > 0) {
            PositionDetailBO positionDetailBO = positionDetailBOList.get(0);
            entity.setArea(positionDetailBO.getArea());
            // TODO: 17-8-5 每个持股方案有一个对应的项目，目前该项目从哪里拿不清楚，以后有的话得加上去
            entity.setProject("");
            entity.setDepartment(positionDetailBO.getDepartmentName());
            entity.setPosition(positionDetailBO.getPosition());

            LinkDateStaffEntryBO entryBO = staffEntryRegisterAPI.findByEmpNum(positionDetailBO.getSerialNumber());
            if (null != entryBO) {
                //获取第一条数据
//                EntryBasicInfoBO entryBasicInfoBO = entryBasicInfoBOs.get(0);
                String time = entryBO.getEntryDate();
                int months = getMonthSpace(time, LocalDate.now().toString());
                entity.setMonths(months);
            } else {
                throw new SerException("查无当前用户的入职信息");
            }

            int months = 0;
            if (moduleAPI.isCheck("assistance")) {
//                months = ageAssistAPI.getJobAge(userBO.getUsername()).intValue();
            }
            entity.setMonths(months);
            entity.setSellName(schemeIssueBO.getPublisher());
            entity.setPurchaseNum(to.getPurchaseNum());
            entity.setMoney(to.getPurchaseNum() * schemeIssueBO.getPrice());
            if (moduleAPI.isCheck("bonus")) {
                entity.setPenalty(disciplineRecordAPI.getPushNum(userBO.getUsername()));
                entity.setReward(disciplineRecordAPI.getRewardNum(userBO.getUsername()));
            } else {
                entity.setPenalty(0);
                entity.setReward(0);
            }
            // TODO: 17-8-7
            //各项晋升的次数
            entity.setPromotion(0);
            entity.setRemark(to.getRemark());
            super.save(entity);
        } else if ("admin".equals(userBO.getUsername())) {
            entity.setArea("admin");
            entity.setProject("admin");
            entity.setDepartment("admin");
            entity.setPosition("admin");
            entity.setMonths(0);
            entity.setSellName(schemeIssueBO.getPublisher());
            entity.setPurchaseNum(to.getPurchaseNum());
            entity.setMoney(to.getPurchaseNum() * schemeIssueBO.getPrice());
            entity.setPenalty(0);
            entity.setReward(0);
            // TODO: 17-8-7
            //各项晋升的次数
            entity.setPromotion(0);
            entity.setRemark(to.getRemark());
            super.save(entity);
        } else {
            throw new SerException("当前用户不是内部员工，无权购买");
        }
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void update(PurchaseTO to) throws SerException {
//        checkAddIdentity();
        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("id不能为空");
        }
        Purchase temp = super.findById(to.getId());
        if (temp == null) {
            throw new SerException("查无该条数据");
        }
        temp.setPurchaseNum(to.getPurchaseNum());

        String userToken = RpcTransmit.getUserToken();
        String name = userAPI.currentUser().getUsername();
        RpcTransmit.transmitUserToken(userToken);
        temp.setName(name);
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
//        checkAddIdentity();
        Purchase temp = super.findById(id);
        if (temp == null) {
            throw new SerException("查无该条数据");
        }
        if (StringUtils.isNotBlank(temp.getManager())) {
            throw new SerException("数据已提交最终审核，不可撤销");
        }
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void examine(PurchaseTO to) throws SerException {
//        checkAddIdentity();
        Purchase temp = super.findById(to.getId());

        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        String name = userBO.getUsername();
        RpcTransmit.transmitUserToken(userToken);

        List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId());
        if (!CollectionUtils.isEmpty(positionDetailBOs) || "admin".equals(userBO.getUsername())) {
            for (PositionDetailBO positionDetailBO : positionDetailBOs) {
                if ("财务运营部".equals(positionDetailBO.getDepartmentName()) && StringUtils.isBlank(temp.getFinancial())) {
                    temp.setFinancial(name);
                    temp.setOpinion(to.getOpinion());
                }
                if ("规划模块".equals(positionDetailBO.getDepartmentName()) && StringUtils.isBlank(temp.getPlanModule())) {
                    temp.setPlanModule(name);
                    temp.setOpinion1(to.getOpinion1());
                }
                if ("协调管理中心（总经办）".equals(positionDetailBO.getDepartmentName()) && StringUtils.isBlank(temp.getManager())) {
                    temp.setManager(name);
                    temp.setOpinion2(to.getOpinion2());
                }
            }
            if ("admin".equals(userBO.getUsername())) {
                temp.setFinancial(name);
                temp.setOpinion(to.getOpinion());
                temp.setPlanModule(name);
                temp.setOpinion1(to.getOpinion());
                temp.setManager(name);
                temp.setOpinion2(to.getOpinion());
            }
        } else {
            throw new SerException("当前用户不是内部员工");
        }
        if (to.getStatus().equals(Status1.ISSUED) && StringUtils.isNotBlank(temp.getManager())) {
            temp.setStatus(to.getStatus());
        } else {
            temp.setStatus(Status1.SUBMIT);
        }
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        //审核通过，则把数据放到交易记录表
        if (temp.getStatus().equals(Status1.ISSUED)) {
            Buyschedule buyschedule = new Buyschedule();
            buyschedule.setShareholder(temp.getName());
            buyschedule.setCode(temp.getCode());
            buyschedule.setName(temp.getIssueName());
            buyschedule.setPurchaseNum(temp.getPurchaseNum());
            buyschedule.setBuyPrice(temp.getMoney() / temp.getPurchaseNum());
            buyschedule.setPrice(temp.getPrice());
            buyschedule.setTotalBuyPrice(temp.getMoney());
            buyschedule.setTotalPrice(temp.getFacevalue());
            buyschedule.setSell(temp.getSellName());
            buyschedule.setTime(LocalDateTime.now());

            buyscheduleSer.save(buyschedule);
            //修改交易详情的数据
            DetailsDTO detailsDTO = new DetailsDTO();
            detailsDTO.getConditions().add(Restrict.eq("code", temp.getCode()));
            detailsDTO.getConditions().add(Restrict.eq("name", temp.getIssueName()));
            detailsDTO.getConditions().add(Restrict.eq("publisher", temp.getSellName()));
            List<Details> detailses = detailsSer.findByCis(detailsDTO);
            if (null != detailses && detailses.size() > 0) {
                Details details = detailses.get(0);
                if (details.getSharesNum() - to.getPurchaseNum() < 0) {
                    throw new SerException("购买股数不能超过出售的股数，审核无效");
                }
                //交易详情的出售剩余数量＝原交易详情出售剩余数量　－　购买数量
                details.setSharesNum(details.getSharesNum() - to.getPurchaseNum());
            } else {
//                throw new SerException("交易数据不存在");
//                增加新的交易详情数据
//                Details details = new Details();
//                details.setCode(temp.getCode());
//                details.setName(temp.getIssueName());
//                details.setPublisher(temp.getSellName());
//                details.setNumber();
//                details.setPrice();
//                details.setTime();
//                details.setSharesNum();
//                details.setFacevalue();
            }
            //增加出售记录
            Sellschedule sellschedule = new Sellschedule();
            sellschedule.setSellName(temp.getSellName());
            sellschedule.setCode(temp.getCode());
            sellschedule.setName(temp.getIssueName());
            sellschedule.setSellNum(temp.getPurchaseNum());
            sellschedule.setSellPrice(temp.getMoney() / temp.getPurchaseNum());
            sellschedule.setTotalSellPrice(temp.getMoney());
            sellschedule.setSellTime(LocalDateTime.now());

            //出售剩余数量　＝　出售人的所有购买数量　－　已出售的数量
            Long totalBuyNum = 0l;
            Long totalSellNum = 0l;
            BuyscheduleDTO buyscheduleDTO = new BuyscheduleDTO();
            buyscheduleDTO.getConditions().add(Restrict.eq("shareholder", temp.getSellName()));
            buyscheduleDTO.getConditions().add(Restrict.eq("code", temp.getCode()));
            List<Buyschedule> buyschedules = buyscheduleSer.findByCis(buyscheduleDTO);
            if (!CollectionUtils.isEmpty(buyschedules)) {
                for (Buyschedule buyschedule1 : buyschedules) {
                    totalBuyNum += buyschedule1.getPurchaseNum();
                }
                SellscheduleDTO sellscheduleDTO = new SellscheduleDTO();
                sellscheduleDTO.getConditions().add(Restrict.eq("sellName", temp.getSellName()));
                sellscheduleDTO.getConditions().add(Restrict.eq("code", temp.getCode()));
                List<Sellschedule> sellschedules = sellscheduleSer.findByCis(sellscheduleDTO);
                if (!CollectionUtils.isEmpty(sellschedules)) {
                    for (Sellschedule sellschedule1 : sellschedules) {
                        totalSellNum += sellschedule1.getSellNum();
                    }
                }
            }
            sellschedule.setNumber(totalBuyNum - totalSellNum);
            sellschedule.setBuyName(temp.getName());
            sellschedule.setPurchaseNum(temp.getPurchaseNum());
            sellschedule.setBuyTime(LocalDateTime.now());
            sellscheduleSer.save(sellschedule);
        }
    }

    @Override
    public List<PurchaseBO> list(PurchaseDTO dto) throws SerException {
//        checkSeeIdentity();
        searchCondition(dto);
        List<Purchase> list = super.findByPage(dto);
        List<PurchaseBO> purchaseBOList = BeanTransform.copyProperties(list, PurchaseBO.class, false);
//        if (null != purchaseBOList && purchaseBOList.size() > 0) {
//            for (PurchaseBO PurchaseBO : purchaseBOList) {
//                PurchaseBO.setData(LocalDate.now().toString());
//            }
//        }
        return purchaseBOList;
    }

    @Override
    public PurchaseBO getById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        Purchase purchase = super.findById(id);
        PurchaseBO purchaseBO = BeanTransform.copyProperties(purchase, PurchaseBO.class, false);
        return purchaseBO;
    }

    @Override
    public Long getTotal(PurchaseDTO dto) throws SerException {
        searchCondition(dto);
        Long count = super.count(dto);
        return count;
    }

    //计算两个日期之间的月数
    public int getMonthSpace(String date1, String date2) throws SerException {

        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(date1));
            c2.setTime(sdf.parse(date2));

            result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

            return result == 0 ? 1 : Math.abs(result);
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    public void searchCondition(PurchaseDTO dto) throws SerException {
        /**
         * 状态
         */
        if (null != dto.getStatus()) {
            dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
        }
        /**
         * 申购人
         */
        if (StringUtils.isNotBlank(dto.getName())) {
            dto.getConditions().add(Restrict.eq("name", dto.getName()));
        }/**
         * 申购时间
         */
        if (StringUtils.isNotBlank(dto.getTime())) {
            dto.getConditions().add(Restrict.like("time", dto.getTime()));
        }
        /**
         * 地区
         */
        if (StringUtils.isNotBlank(dto.getArea())) {
            dto.getConditions().add(Restrict.like("area", dto.getArea()));
        }
        /**
         * 项目
         */
        if (StringUtils.isNotBlank(dto.getProject())) {
            dto.getConditions().add(Restrict.eq("project", dto.getProject()));
        }
        /**
         * 部门
         */
        if (StringUtils.isNotBlank(dto.getDepartment())) {
            dto.getConditions().add(Restrict.eq("department", dto.getDepartment()));
        }
        /**
         * 岗位
         */
        if (StringUtils.isNotBlank(dto.getPosition())) {
            dto.getConditions().add(Restrict.like("position", dto.getPosition()));
        }
        /**
         * 在职时间（月数)
         */
        if (0 < dto.getMonths()) {
            dto.getConditions().add(Restrict.like("months", dto.getMonths()));
        }
        /**
         * 出售人
         */
        if (StringUtils.isNotBlank(dto.getSellName())) {
            dto.getConditions().add(Restrict.eq("sellName", dto.getSellName()));
        }
        /**
         * 购入股数
         */
        if (null != dto.getPurchaseNum()) {
            dto.getConditions().add(Restrict.eq("purchaseNum", dto.getPurchaseNum()));
        }/**
         * 应付额
         */
        if (null != dto.getMoney()) {
            dto.getConditions().add(Restrict.like("money", dto.getMoney()));
        }
        /**
         * 目前处罚记录次数
         */
        if (0 < dto.getPenalty()) {
            dto.getConditions().add(Restrict.like("penalty", dto.getPenalty()));
        }
        /**
         * 目前获得奖励次数
         */
        if (0 < dto.getReward()) {
            dto.getConditions().add(Restrict.eq("reward", dto.getReward()));
        }
        /**
         * 各项晋升的次数
         */
        if (0 < dto.getPromotion()) {
            dto.getConditions().add(Restrict.eq("promotion", dto.getPromotion()));
        }/**
         * 备注
         */
        if (StringUtils.isNotBlank(dto.getRemark())) {
            dto.getConditions().add(Restrict.like("remark", dto.getRemark()));
        }
    }


}