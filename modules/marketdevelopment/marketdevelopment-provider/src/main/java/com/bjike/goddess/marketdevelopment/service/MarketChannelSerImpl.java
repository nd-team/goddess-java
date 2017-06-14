package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.marketdevelopment.bo.MarketChannelBO;
import com.bjike.goddess.marketdevelopment.bo.MarketChannelExcelBO;
import com.bjike.goddess.marketdevelopment.bo.MarketResearchExcelBO;
import com.bjike.goddess.marketdevelopment.dto.MarketChannelDTO;
import com.bjike.goddess.marketdevelopment.entity.MarketChannel;
import com.bjike.goddess.marketdevelopment.entity.MarketResearch;
import com.bjike.goddess.marketdevelopment.to.CollectTO;
import com.bjike.goddess.marketdevelopment.to.MarketChannelTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 市场挖掘业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:15 ]
 * @Description: [ 市场挖掘业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class MarketChannelSerImpl extends ServiceImpl<MarketChannel, MarketChannelDTO> implements MarketChannelSer {

    @Autowired
    private MarPermissionSer marPermissionSer;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    private static final String channelManage = "channel-manage";

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketChannelBO save(MarketChannelTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(channelManage))
            throw new SerException("您的帐号没有权限");
        MarketChannel entity = BeanTransform.copyProperties(to, MarketChannel.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, MarketChannelBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketChannelBO update(MarketChannelTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(channelManage))
            throw new SerException("您的帐号没有权限");
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                MarketChannel entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, MarketChannelBO.class);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketChannelBO delete(MarketChannelTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(channelManage))
            throw new SerException("您的帐号没有权限");
        MarketChannel entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, MarketChannelBO.class);
    }

    @Override
    public List<MarketChannelBO> findByType(String type) throws SerException {
        MarketChannelDTO dto = new MarketChannelDTO();
        dto.getConditions().add(Restrict.eq("type", type));
        List<MarketChannel> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, MarketChannelBO.class);
    }

    @Override
    public List<MarketChannelBO> findByCourse(String course) throws SerException {
        MarketChannelDTO dto = new MarketChannelDTO();
        dto.getConditions().add(Restrict.eq("course", course));
        List<MarketChannel> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, MarketChannelBO.class);
    }

    @Override
    public List<MarketChannelBO> findByCourseType(String type, String course) throws SerException {
        MarketChannelDTO dto = new MarketChannelDTO();
        dto.getConditions().add(Restrict.eq("course", course));
        dto.getConditions().add(Restrict.eq("type", type));
        List<MarketChannel> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, MarketChannelBO.class);
    }

    @Override
    public List<MarketChannel> findByPage(MarketChannelDTO dto) throws SerException {
        if (!marPermissionSer.getMarPermission(channelManage))
            throw new SerException("您的帐号没有权限");
        return super.findByPage(dto);
    }

    @Override
    public byte[] exportExcel(CollectTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(channelManage))
            throw new SerException("您的帐号没有权限");
        MarketChannelDTO dto = new MarketChannelDTO();
        if (StringUtils.isNotBlank(to.getType()))
            dto.getConditions().add(Restrict.eq("type", to.getType()));
        dto.getSorts().add("createTime=desc");
        List<MarketChannel> list = super.findByCis(dto);
        List<MarketChannelExcelBO> boList = BeanTransform.copyProperties(list, MarketChannelExcelBO.class);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(boList, excel);
        return bytes;
    }
}