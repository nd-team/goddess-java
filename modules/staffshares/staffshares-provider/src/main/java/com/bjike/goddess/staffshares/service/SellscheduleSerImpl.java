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
import com.bjike.goddess.staffshares.entity.Sellschedule;
import com.bjike.goddess.staffshares.to.SellscheduleTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    @Override
    public void sell(SellscheduleTO to) throws SerException {
        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("ｉｄ不能为空");
        }

        Buyschedule entity = buyscheduleSer.findById(to.getId());
        if (null == entity) {
            throw new SerException("出售数据不能为空");
        }

        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        Sellschedule sellschedule = new Sellschedule();
        sellschedule.setSellName(userBO.getUsername());
        sellschedule.setCode(entity.getCode());
        sellschedule.setName(entity.getName());
        sellschedule.setSellNum(to.getSellNum());
        sellschedule.setSellPrice(to.getTotalSellPrice() / to.getSellNum());
        sellschedule.setTotalSellPrice(to.getTotalSellPrice());
        sellschedule.setSellTime(LocalDateTime.now());
        sellschedule.setNumber(entity.getPurchaseNum() - to.getSellNum());
//        sellschedule.setBuyName();
//        sellschedule.setPurchaseNum;
//        sellschedule.setBuyTime;
        super.save(sellschedule);
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
        Long count = super.count(sellscheduleDTO);
        return count;
    }

    @Override
    public List<SellscheduleCollectBO> collect() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        String name = userBO.getUsername();

        List<SellscheduleCollectBO> buyscheduleCollectBOs = new ArrayList<>(0);
        Boolean tar = authority(userBO);
        String file[] = new String[]{"sellName", "sellNum", "totalSellPrice"};
        String sql = getSql(tar, userBO);
        List<Buyschedule> list = super.findBySql(sql, Buyschedule.class, file);
        BeanTransform.copyProperties(list, buyscheduleCollectBOs);
        return buyscheduleCollectBOs;
    }
    public static void main(String[] args){
        LocalDateTime time = LocalDateTime.now();
        System.out.print(time.toString());
    }

    @Override
    public List<TransactionBO> transaction() throws SerException {
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
    private Boolean authority(UserBO userBO) throws SerException{
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
        if (0 <= sellscheduleDTO.getSellNum()) {
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
        if (0 <= sellscheduleDTO.getNumber()) {
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
        if (0 <= sellscheduleDTO.getPurchaseNum()) {
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