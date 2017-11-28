package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.assistance.api.AgeAssistAPI;
import com.bjike.goddess.bonus.api.DisciplineRecordAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.staffshares.api.SchemeAPI;
import com.bjike.goddess.staffshares.bo.DetailsBO;
import com.bjike.goddess.staffshares.bo.SchemeIssueBO;
import com.bjike.goddess.staffshares.dto.BuyscheduleDTO;
import com.bjike.goddess.staffshares.dto.DetailsDTO;
import com.bjike.goddess.staffshares.dto.SellscheduleDTO;
import com.bjike.goddess.staffshares.entity.Buyschedule;
import com.bjike.goddess.staffshares.entity.Details;
import com.bjike.goddess.staffshares.entity.Purchase;
import com.bjike.goddess.staffshares.entity.Sellschedule;
import com.bjike.goddess.staffshares.enums.GuideAddrStatus;
import com.bjike.goddess.staffshares.enums.Status1;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import com.bjike.goddess.staffshares.to.PurchaseTO;
import com.bjike.goddess.staffshares.to.SellscheduleTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

/**
 * 交易详情业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 08:54 ]
 * @Description: [ 交易详情业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffsharesSerCache")
@Service
public class DetailsSerImpl extends ServiceImpl<Details, DetailsDTO> implements DetailsSer {

    @Autowired
    private SchemeAPI schemeAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    //    @Autowired
//    private EntryBasicInfoAPI entryBasicInfoAPI;
    @Autowired
    private DisciplineRecordAPI disciplineRecordAPI;

    @Autowired
    private PurchaseSer purchaseSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private BuyscheduleSer buyscheduleSer;
    @Autowired
    private SellscheduleSer sellscheduleSer;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private AgeAssistAPI ageAssistAPI;


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
    public List<DetailsBO> listDetail(DetailsDTO dto) throws SerException {
        if (StringUtils.isBlank(dto.getId())) {
            throw new SerException("id不能为空");
        }
        //根据ｉｄ查询交易详情
        SchemeIssueBO schemeIssueBO = schemeAPI.getOne(dto.getId());
        if (schemeIssueBO != null) {
            DetailsDTO detailsDTO = new DetailsDTO();
            detailsDTO.getConditions().add(Restrict.eq("code", schemeIssueBO.getCode()));
            List<Details> detailses = super.findByCis(detailsDTO);
            if (CollectionUtils.isEmpty(detailses)) {
                //插入第一条数据
                Details details = new Details();
                details.setCode(schemeIssueBO.getCode());
                details.setName(schemeIssueBO.getName());
                details.setPublisher(schemeIssueBO.getPublisher());
                details.setNumber(schemeIssueBO.getNumber());
                details.setPrice(schemeIssueBO.getPrice());
                details.setTime(LocalDate.parse(schemeIssueBO.getTime()));
                details.setSharesNum(schemeIssueBO.getSharesNum());
                super.save(details);
                List<DetailsBO> detailsBOs = BeanTransform.copyProperties(details, DetailsBO.class, false);
                return detailsBOs;
            } else {
                //根据ｉｄ查询该条记录的详情
                detailsDTO.getConditions().add(Restrict.eq("code", schemeIssueBO.getCode()));
                List<Details> detailses1 = super.findByCis(detailsDTO);
                List<DetailsBO> detailsBOs = BeanTransform.copyProperties(detailses1, DetailsBO.class, false);
                return detailsBOs;
            }
        }
        return null;
    }

    @Override
    public DetailsBO getDetailById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        Details entity = super.findById(id);
        DetailsBO detailsBO = BeanTransform.copyProperties(entity, DetailsBO.class, false);
        return detailsBO;
    }

    @Override
    public Long getTotal(DetailsDTO detailsDTO) throws SerException {
        searchCondition(detailsDTO);
        Long count = super.count(detailsDTO);
        return count = 1l;
    }

    @Override
    public void buy(PurchaseTO to) throws SerException {
        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("交易详情id不能为空");
        }
        if (to.getPurchaseNum() <= 0) {
            throw new SerException("购入股数不能为0");
        }
        //根据ｉｄ查询交易详情
        Details entity = super.findById(to.getId());
        if (null != entity) {
            if (to.getPurchaseNum() > entity.getSharesNum()) {
                throw new SerException("购买股数不能超过出售的剩余股数");
            }
            Purchase purchase = new Purchase();
            purchase.setStatus(Status1.SUBMIT);
            String userToken = RpcTransmit.getUserToken();
            UserBO userBO = userAPI.currentUser();
            RpcTransmit.transmitUserToken(userToken);
            purchase.setName(userBO.getUsername());
            purchase.setTime(LocalDateTime.now());

            purchase.setCode(entity.getCode());
            purchase.setIssueName(entity.getName());
            purchase.setFacevalue(entity.getFacevalue());
            purchase.setPrice(entity.getPrice());
            //根据用户名得到用户数据
            List<PositionDetailBO> positionDetailBOList = positionDetailUserAPI.getPositionDetail(userBO.getUsername());
            if (null != positionDetailBOList && positionDetailBOList.size() > 0) {
                PositionDetailBO positionDetailBO = positionDetailBOList.get(0);
                purchase.setArea(positionDetailBO.getArea());
                // TODO: 17-8-5 每个持股方案有一个对应的项目
                purchase.setProject("");
                purchase.setDepartment(positionDetailBO.getDepartmentName());
                purchase.setPosition(positionDetailBO.getPosition());

//                List<EntryBasicInfoBO> entryBasicInfoBOs = entryBasicInfoAPI.getByEmpNumber(positionDetailBO.getSerialNumber());
//                if (null != entryBasicInfoBOs && entryBasicInfoBOs.size() > 0) {
                //获取第一条数据
//                    EntryBasicInfoBO entryBasicInfoBO = entryBasicInfoBOs.get(0);
//                    String time = entryBasicInfoBO.getEntryTime();
//                    int months = getMonthSpace(time, LocalDate.now().toString());
//                    purchase.setMonths(months);

                int months = 0;
                if (moduleAPI.isCheck("assistance")) {
                    months = ageAssistAPI.getJobAge(userBO.getUsername()).intValue();
                }
                purchase.setMonths(0);
                purchase.setSellName(entity.getPublisher());
                purchase.setPurchaseNum(to.getPurchaseNum());
                purchase.setMoney(to.getPurchaseNum() * entity.getPrice());
                if (moduleAPI.isCheck("bonus")) {
                    purchase.setPenalty(disciplineRecordAPI.getPushNum(userBO.getUsername()));
                    purchase.setReward(disciplineRecordAPI.getRewardNum(userBO.getUsername()));
                } else {
                    purchase.setPenalty(0);
                    purchase.setReward(0);
                }
                // TODO: 17-8-7
                //各项晋升的次数
                purchase.setPromotion(0);
                purchase.setRemark(to.getRemark());
                purchaseSer.save(purchase);
            } else if ("admin".equals(userBO.getUsername())) {
                purchase.setArea("admin");
                purchase.setProject("admin");
                purchase.setDepartment("admin");
                purchase.setPosition("admin");
                purchase.setMonths(0);
                purchase.setSellName(entity.getPublisher());
                purchase.setPurchaseNum(to.getPurchaseNum());
                purchase.setMoney(to.getPurchaseNum() * entity.getPrice());
                purchase.setPenalty(0);
                purchase.setReward(0);
                purchase.setPromotion(0);
                purchase.setRemark(to.getRemark());
                purchaseSer.save(purchase);
            } else {
                throw new SerException("当前用户不是内部员工，无法购买");
            }
        } else {
            throw new SerException("目标数据对象为空");
        }
    }

    @Override
    public void recovery(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id 不能为空");
        }
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId());
//        if (!CollectionUtils.isEmpty(positionDetailBOs)) {
//            for (PositionDetailBO positionDetailBO : positionDetailBOs) {
////                positionDetailBO.getDepartmentId().equals()
//            }
//        }

        Details entity = super.findById(id);
        entity.setSharesNum(0l);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void sell(SellscheduleTO to) throws SerException {
        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("id不能为空");
        }
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        Details entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据为空");
        }
//        if (entity.getPublisher().equals(userBO.getUsername())) {
//            throw new SerException("当前所选的数据中出售人不是当前用户");
//        }
        BuyscheduleDTO buyscheduleDTO = new BuyscheduleDTO();
        buyscheduleDTO.getConditions().add(Restrict.eq("shareholder", userBO.getUsername()));
        buyscheduleDTO.getConditions().add(Restrict.eq("code", entity.getCode()));
        List<Buyschedule> buyschedules = buyscheduleSer.findByCis(buyscheduleDTO);
        //当期用户的购买总股数；
        Long totalBuynum = 0l;
        //当前用户的出售总股数
        Long totalSellNum = 0l;
        if (CollectionUtils.isEmpty(buyschedules)) {
            throw new SerException("当前用户无购买记录");
        } else {
            //得到当前用户的所有购买股数
            for (Buyschedule buyschedule : buyschedules) {
                totalBuynum += buyschedule.getPurchaseNum();
            }
            SellscheduleDTO sellscheduleDTO = new SellscheduleDTO();
            sellscheduleDTO.getConditions().add(Restrict.eq("sellName", userBO.getUsername()));
            sellscheduleDTO.getConditions().add(Restrict.eq("code", entity.getCode()));
            List<Sellschedule> sellschedules = sellscheduleSer.findByCis(sellscheduleDTO);
            if (!CollectionUtils.isEmpty(sellschedules)) {
                for (Sellschedule sellschedule : sellschedules) {
                    totalSellNum += sellschedule.getSellNum();
                }
                totalBuynum = totalBuynum - totalSellNum;
            }
        }
        if (to.getSellNum() > totalBuynum) {
            throw new SerException("出售股数不能大于当前用户的购入股数");
        }

//        //增加当前用户的出售记录表
//        Sellschedule sellschedule = new Sellschedule();
//        sellschedule.setSellName(userBO.getUsername());
//        sellschedule.setCode(entity.getCode());
//        sellschedule.setName(entity.getCode());
//        sellschedule.setSellNum(to.getSellNum());
//        sellschedule.setSellPrice(to.getTotalSellPrice() / to.getSellNum());
//        sellschedule.setTotalSellPrice(to.getTotalSellPrice());
//        sellschedule.setSellTime(LocalDateTime.now());
//        sellschedule.setNumber(entity.getNumber() - to.getSellNum());
//        sellscheduleSer.save(sellschedule);
        //新增一条交易详情
        Details details = new Details();
        details.setCode(entity.getCode());
        details.setName(entity.getName());
        details.setPublisher(userBO.getUsername());
        details.setNumber(to.getSellNum());
        details.setPrice(to.getTotalSellPrice() / to.getSellNum());
        details.setTime(LocalDate.now());
        details.setSharesNum(to.getSellNum());
        super.save(details);
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


    public void searchCondition(DetailsDTO detailsDTO) throws SerException {
        /**
         * 方案代码
         */
        if (StringUtils.isNotBlank(detailsDTO.getCode())) {
            detailsDTO.getConditions().add(Restrict.eq("code", detailsDTO.getCode()));
        }/**
         * 方案名称
         */
        if (StringUtils.isNotBlank(detailsDTO.getName())) {
            detailsDTO.getConditions().add(Restrict.eq("name", detailsDTO.getName()));
        }/**
         * 出售/发行人
         */
        if (StringUtils.isNotBlank(detailsDTO.getPublisher())) {
            detailsDTO.getConditions().add(Restrict.like("publisher", detailsDTO.getPublisher()));
        }
        /**
         * 出售/发行数量
         */
        if (null != detailsDTO.getNumber()) {
            detailsDTO.getConditions().add(Restrict.like("number", detailsDTO.getNumber()));
        }
        /**
         * 出售/发行价格
         */
        if (null != detailsDTO.getPrice()) {
            detailsDTO.getConditions().add(Restrict.like("price", detailsDTO.getPrice()));
        }
        /**
         * 出售/发行时间
         */
        if (StringUtils.isNotBlank(detailsDTO.getTime())) {
            detailsDTO.getConditions().add(Restrict.like("time", detailsDTO.getTime()));
        }
        /**
         * 剩余出售量
         */
        if (null != detailsDTO.getSharesNum()) {
            detailsDTO.getConditions().add(Restrict.like("sharesNum", detailsDTO.getSharesNum()));
        }
    }
}