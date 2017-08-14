package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.staffshares.bo.SellscheduleBO;
import com.bjike.goddess.staffshares.bo.SellscheduleCollectBO;
import com.bjike.goddess.staffshares.bo.TransactionBO;
import com.bjike.goddess.staffshares.dto.SellscheduleDTO;
import com.bjike.goddess.staffshares.entity.Buyschedule;
import com.bjike.goddess.staffshares.entity.Details;
import com.bjike.goddess.staffshares.entity.Sellschedule;
import com.bjike.goddess.staffshares.enums.GuideAddrStatus;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import com.bjike.goddess.staffshares.to.SellscheduleTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 出售记录表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:15 ]
 * @Description: [ 出售记录表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffsharesSerCache")
@Service
public class SellscheduleSerImpl extends ServiceImpl<Sellschedule, SellscheduleDTO> implements SellscheduleSer {
    @Autowired
    private BuyscheduleSer buyscheduleSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private DetailsSer detailsSer;
    @Autowired
    private SellscheduleSer sellscheduleSer;

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
    public void sell(SellscheduleTO to) throws SerException {
        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("id不能为空");
        }

        Buyschedule buyschedule = buyscheduleSer.findById(to.getId());
        if (null == buyschedule) {
            throw new SerException("出售数据不能为空");
        }
        if (to.getSellNum() > buyschedule.getPurchaseNum()) {
            throw new SerException("出售股数不能超过购买股数");
        }

        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        //增加交易详情
//        DetailsDTO detailsDTO = new DetailsDTO();
//        detailsDTO.getConditions().add(Restrict.eq("code", buyschedule.getCode()));
//        detailsDTO.getConditions().add(Restrict.eq("publisher", buyschedule.getShareholder()));
//        List<Details> detailses = detailsSer.findByCis(detailsDTO);
//        if (CollectionUtils.isEmpty(detailses)) {
        //增加一条交易详情
        Details details = new Details();
        details.setCode(buyschedule.getCode());
        details.setName(buyschedule.getName());
        details.setPublisher(buyschedule.getShareholder());
        details.setNumber(to.getSellNum());
        details.setPrice(to.getTotalSellPrice() / to.getSellNum());
        details.setTime(LocalDate.now());
        details.setSharesNum(to.getSellNum());
        detailsSer.save(details);
//        }else {
//            Details details = detailses.get(0);
//            details.setSharesNum(details.getSharesNum() + to.getSellNum());
//        }

//        Sellschedule sellschedule = new Sellschedule();
//        sellschedule.setSellName(userBO.getUsername());
//        sellschedule.setCode(buyschedule.getCode());
//        sellschedule.setName(buyschedule.getName());
//        sellschedule.setSellNum(to.getSellNum());
//        sellschedule.setSellPrice(to.getTotalSellPrice() / to.getSellNum());
//        sellschedule.setTotalSellPrice(to.getTotalSellPrice());
//        sellschedule.setSellTime(LocalDateTime.now());
//        sellschedule.setNumber(buyschedule.getPurchaseNum() - to.getSellNum());
////        sellschedule.setBuyName();
////        sellschedule.setPurchaseNum;
////        sellschedule.setBuyTime;
//        super.save(sellschedule);
    }

    @Override
    public List<SellscheduleBO> maps(SellscheduleDTO dto) throws SerException {
//        checkSeeIdentity();

        searchCondition(dto);
        List<Sellschedule> list = super.findByPage(dto);
        List<SellscheduleBO> sellscheduleBOList = new ArrayList<>(0);

        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        Boolean tar = authority(userBO);
        if (!CollectionUtils.isEmpty(list)) {
            if (tar) {
                sellscheduleBOList = BeanTransform.copyProperties(list, SellscheduleBO.class, false);
            } else {
                for (Sellschedule sellschedule : list) {
                    if (sellschedule.getSellName().equals(userBO.getUsername())) {
                        SellscheduleBO sellscheduleBO = BeanTransform.copyProperties(sellschedule, SellscheduleBO.class, false);
                        sellscheduleBOList.add(sellscheduleBO);
                    }
                }
            }
        }
        return sellscheduleBOList;
    }

    @Override
    public SellscheduleBO getById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        Sellschedule sellschedule = super.findById(id);
        return BeanTransform.copyProperties(sellschedule, SellscheduleBO.class);
    }

    @Override
    public Long getTotal(SellscheduleDTO sellscheduleDTO) throws SerException {
        searchCondition(sellscheduleDTO);
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        Long count = 0l;
        if (authority(userBO)) {
            count = super.count(sellscheduleDTO);
        } else {
            sellscheduleDTO.getConditions().add(Restrict.eq("sellName", userBO.getUsername()));
            count = super.count(sellscheduleDTO);
        }
        return count;
    }

    @Override
    public List<SellscheduleCollectBO> collect() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        String name = userBO.getUsername();

        Boolean tar = authority(userBO);
        String file[] = new String[]{"sellName", "sellNum", "totalSellPrice"};
        String sql = getSql(tar, userBO);
        List<Sellschedule> list = super.findBySql(sql, Sellschedule.class, file);
        List<SellscheduleCollectBO> buyscheduleCollectBOs = BeanTransform.copyProperties(list, SellscheduleCollectBO.class);
        return buyscheduleCollectBOs;
    }

    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.now();
        System.out.print(time.toString());
    }

    @Override
    public List<TransactionBO> transaction() throws SerException {
        TransactionBO transactionBO = new TransactionBO();
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        List<String> stringList = new ArrayList<>(0);
        List<String> stringList2 = new ArrayList<>(0);
        if (authority(userBO)) {
            List<Buyschedule> buyschedules = buyscheduleSer.findAll();
            List<Sellschedule> sellschedules = sellscheduleSer.findAll();
            if (!CollectionUtils.isEmpty(buyschedules)) {
                for (Buyschedule buyschedule : buyschedules) {
                    stringList.add(buyschedule.getShareholder());
                }

            }
            if (!CollectionUtils.isEmpty(sellschedules)) {
                for
            }
        }

        return null;
    }

    private String getSql(Boolean tar, UserBO userBO) throws SerException {
        String file[] = new String[]{};
        StringBuilder sql = new StringBuilder("select sellName, ");
        sql.append(" sum(sellNum) as sellNum, ");
        sql.append(" sum(totalSellPrice) as totalSellPrice ");
        sql.append(" from staffshares_sellschedule ");
        if (!tar) {
            sql.append(" where sellName = '" + userBO.getUsername() + "' ");
        }
        sql.append(" group by sellName; ");
        return sql.toString();
    }

    //判断是否有权限
    private Boolean authority(UserBO userBO) throws SerException {
        List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId());
        Boolean tar = false;
        if (!CollectionUtils.isEmpty(positionDetailBOs)) {
            for (PositionDetailBO positionDetailBO : positionDetailBOs) {
                if ("财务运营部".equals(positionDetailBO.getDepartmentName()) || "规划模块".equals(positionDetailBO.getDepartmentName()) || "协调管理中心（总经办）".equals(positionDetailBO.getDepartmentName())) {
                    //拥有全部权限
                    tar = true;
                }
            }
        }
        if ("admin".equals(userBO.getUsername())) {
            tar = true;
        }
        return tar;
    }

    public void searchCondition(SellscheduleDTO sellscheduleDTO) throws SerException {
        /**
         * 出售人
         */
        if (StringUtils.isNotBlank(sellscheduleDTO.getSellName())) {
            sellscheduleDTO.getConditions().add(Restrict.eq("sellName", sellscheduleDTO.getSellName()));
        }
        /**
         * 方案代码
         */
        if (StringUtils.isNotBlank(sellscheduleDTO.getCode())) {
            sellscheduleDTO.getConditions().add(Restrict.eq("code", sellscheduleDTO.getCode()));
        }
        /**
         * 方案名称
         */
        if (StringUtils.isNotBlank(sellscheduleDTO.getName())) {
            sellscheduleDTO.getConditions().add(Restrict.eq("name", sellscheduleDTO.getName()));
        }
        /**
         * 出售股数
         */
        if (sellscheduleDTO.getSellNum() != null) {
            sellscheduleDTO.getConditions().add(Restrict.eq("sellNum", sellscheduleDTO.getSellNum()));
        }
        /**
         * 出售价格
         */
        if (null != sellscheduleDTO.getSellPrice()) {
            sellscheduleDTO.getConditions().add(Restrict.eq("sellPrice", sellscheduleDTO.getSellPrice()));
        }
        /**
         * 出售金额
         */
        if (null != sellscheduleDTO.getTotalSellPrice()) {
            sellscheduleDTO.getConditions().add(Restrict.eq("totalSellPrice", sellscheduleDTO.getTotalSellPrice()));
        }
        /**
         * 出售时间
         */
        if (StringUtils.isNotBlank(sellscheduleDTO.getSellTime())) {
            sellscheduleDTO.getConditions().add(Restrict.eq("sellTime", sellscheduleDTO.getSellTime()));
        }
        /**
         * 剩余出售量
         */
        if (null != sellscheduleDTO.getNumber()) {
            sellscheduleDTO.getConditions().add(Restrict.eq("number", sellscheduleDTO.getNumber()));
        }
        /**
         * 购买人
         */
        if (StringUtils.isNotBlank(sellscheduleDTO.getBuyName())) {
            sellscheduleDTO.getConditions().add(Restrict.eq("buyName", sellscheduleDTO.getBuyName()));
        }
        /**
         * 购买股数
         */
        if (null != sellscheduleDTO.getPurchaseNum()) {
            sellscheduleDTO.getConditions().add(Restrict.eq("purchaseNum", sellscheduleDTO.getPurchaseNum()));
        }
        /**
         * 购买时间
         */
        if (StringUtils.isNotBlank(sellscheduleDTO.getBuyTime())) {
            sellscheduleDTO.getConditions().add(Restrict.eq("buyTime", sellscheduleDTO.getBuyTime()));
        }
    }
}