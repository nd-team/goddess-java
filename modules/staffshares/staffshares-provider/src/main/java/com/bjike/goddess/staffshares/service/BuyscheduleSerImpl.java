package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.staffshares.bo.BuyscheduleBO;
import com.bjike.goddess.staffshares.bo.BuyscheduleCollectBO;
import com.bjike.goddess.staffshares.dto.BuyscheduleDTO;
import com.bjike.goddess.staffshares.entity.Buyschedule;
import com.bjike.goddess.staffshares.to.BuyscheduleTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买入记录表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:09 ]
 * @Description: [ 买入记录表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffsharesSerCache")
@Service
public class BuyscheduleSerImpl extends ServiceImpl<Buyschedule, BuyscheduleDTO> implements BuyscheduleSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    @Override
    public List<BuyscheduleBO> maps(BuyscheduleDTO dto) throws SerException {
//        checkSeeIdentity();

        searchCondition(dto);
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        String name = userBO.getUsername();
        List<BuyscheduleBO> buyscheduleBOList = new ArrayList<>(0);
        List<Buyschedule> list = super.findByPage(dto);
        if (!CollectionUtils.isEmpty(list)) {
            Boolean tar = authority(userBO);
            if (tar) {
                buyscheduleBOList = BeanTransform.copyProperties(list, BuyscheduleBO.class, false);
            } else {
                list.stream().filter(str -> str.getShareholder().equals(userBO.getUsername())).collect(Collectors.toList());
                buyscheduleBOList = BeanTransform.copyProperties(list, BuyscheduleBO.class, false);
            }
        }
        return buyscheduleBOList;
    }

    @Override
    public BuyscheduleBO getById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        Buyschedule buyschedule = super.findById(id);
        return BeanTransform.copyProperties(buyschedule, BuyscheduleBO.class);
    }

    @Override
    public Long getTotal(BuyscheduleDTO buyscheduleDTO) throws SerException {
        searchCondition(buyscheduleDTO);
        Long count = super.count(buyscheduleDTO);
        return count;
    }

    @Override
    public void sell(BuyscheduleTO buyscheduleTO) throws SerException {
//        if (StringUtils.isBlank(buyscheduleTO.getId())) {
//            throw new SerException("ｉｄ不能为空");
//        }
//
//        Buyschedule entity = super.findById(buyscheduleTO.getId());
//        if (null == entity) {
//            throw new SerException("出售数据不能为空");
//        }
//
//        String userToken = RpcTransmit.getUserToken();
//        UserBO userBO = userAPI.currentUser();
//        RpcTransmit.transmitUserToken(userToken);
//
//        Sellschedule sellschedule = new Sellschedule();
//        sellschedule.setSellName(userBO.getUsername());
//        sellschedule.setCode(entity.getCode());
//        sellschedule.setName(entity.getName());
//        sellschedule.setSellNum(buyscheduleTO.gets);
//        sellschedule.setSellPrice;
//        sellschedule.setTotalSellPrice;
//        sellschedule.setSellTime;
//        sellschedule.setNumber;
//        sellschedule.setBuyName;
//        sellschedule.setPurchaseNum;
//        sellschedule.setBuyTime;
    }

    @Override
    public List<BuyscheduleCollectBO> collect() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        String name = userBO.getUsername();

        List<BuyscheduleCollectBO> buyscheduleCollectBOs = new ArrayList<>(0);
        Boolean tar = authority(userBO);
        String file[] = new String[]{"shareholder", "purchaseNum", "totalBuyPrice", "totalPrice"};
        String sql = getSql(tar, userBO);
        List<Buyschedule> list = super.findBySql(sql, Buyschedule.class, file);
        BeanTransform.copyProperties(list, buyscheduleCollectBOs);
        return buyscheduleCollectBOs;
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
        return tar;
    }

    private String getSql(Boolean tar, UserBO userBO) throws SerException {
        String file[] = new String[]{};
        StringBuilder sql = new StringBuilder("select shareholder, ");
        sql.append(" sum(purchaseNum) as purchaseNum, ");
        sql.append(" sum(totalBuyPrice) as totalBuyPrice, ");
        sql.append(" sum(totalPrice) as totalPrice ");
        sql.append(" from staffshares_buyschedule ");
        if (!tar) {
            sql.append(" where shareholder = '" + userBO.getUsername() + "' ");
        }
        sql.append(" group by shareholder; ");
        return sql.toString();
    }

    public void searchCondition(BuyscheduleDTO buyscheduleDTO) throws SerException {
        /**
         * 持股人
         */
        if (StringUtils.isNotBlank(buyscheduleDTO.getShareholder())) {
            buyscheduleDTO.getConditions().add(Restrict.eq("shareholder", buyscheduleDTO.getShareholder()));
        }
        /**
         * 方案代码
         */
        if (StringUtils.isNotBlank(buyscheduleDTO.getCode())) {
            buyscheduleDTO.getConditions().add(Restrict.eq("code", buyscheduleDTO.getCode()));
        }
        /**
         * 方案名称
         */
        if (StringUtils.isNotBlank(buyscheduleDTO.getName())) {
            buyscheduleDTO.getConditions().add(Restrict.eq("name", buyscheduleDTO.getName()));
        }
        /**
         * 购入股数
         */
        if (0 <= buyscheduleDTO.getPurchaseNum()) {
            buyscheduleDTO.getConditions().add(Restrict.eq("purchaseNum", buyscheduleDTO.getPurchaseNum()));
        }
        /**
         * 买入价格
         */
        if (null != buyscheduleDTO.getBuyPrice()) {
            buyscheduleDTO.getConditions().add(Restrict.eq("buyPrice", buyscheduleDTO.getBuyPrice()));
        }
        /**
         * 发行价格
         */
        if (null != buyscheduleDTO.getPrice()) {
            buyscheduleDTO.getConditions().add(Restrict.eq("price", buyscheduleDTO.getPrice()));
        }
        /**
         * 买入金额
         */
        if (null != buyscheduleDTO.getTotalBuyPrice()) {
            buyscheduleDTO.getConditions().add(Restrict.eq("totalBuyPrice", buyscheduleDTO.getTotalBuyPrice()));
        }
    }
}