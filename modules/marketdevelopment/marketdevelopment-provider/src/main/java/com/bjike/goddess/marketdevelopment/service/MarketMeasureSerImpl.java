package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.marketdevelopment.bo.MarketMeasureBO;
import com.bjike.goddess.marketdevelopment.bo.MarketMeasureExcelBO;
import com.bjike.goddess.marketdevelopment.bo.MarketResearchExcelBO;
import com.bjike.goddess.marketdevelopment.dto.MarketMeasureDTO;
import com.bjike.goddess.marketdevelopment.entity.MarketMeasure;
import com.bjike.goddess.marketdevelopment.entity.MarketResearch;
import com.bjike.goddess.marketdevelopment.to.CollectTO;
import com.bjike.goddess.marketdevelopment.to.MarketMeasureTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 市场测算业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:19 ]
 * @Description: [ 市场测算业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class MarketMeasureSerImpl extends ServiceImpl<MarketMeasure, MarketMeasureDTO> implements MarketMeasureSer {

    @Autowired
    private MarPermissionSer marPermissionSer;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketMeasureBO save(MarketMeasureTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(marketManage))
            throw new SerException("您的帐号没有权限");
        MarketMeasure entity = BeanTransform.copyProperties(to, MarketMeasure.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, MarketMeasureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketMeasureBO update(MarketMeasureTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(marketManage))
            throw new SerException("您的帐号没有权限");
        try {
            MarketMeasure entity = super.findById(to.getId());
            BeanTransform.copyProperties(to, entity, true);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return BeanTransform.copyProperties(entity, MarketMeasureBO.class);
        } catch (Exception e) {
            throw new SerException("数据对象不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketMeasureBO delete(MarketMeasureTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(marketManage))
            throw new SerException("您的帐号没有权限");
        MarketMeasure entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, MarketMeasureBO.class);
    }

    @Override
    public List<MarketMeasureBO> findByType(String type) throws SerException {
        MarketMeasureDTO dto = new MarketMeasureDTO();
        dto.getConditions().add(Restrict.eq("type", type));
        List<MarketMeasure> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, MarketMeasureBO.class);
    }

    @Override
    public List<MarketMeasureBO> findByCourse(String course) throws SerException {
        MarketMeasureDTO dto = new MarketMeasureDTO();
        dto.getConditions().add(Restrict.eq("course", course));
        List<MarketMeasure> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, MarketMeasureBO.class);
    }

    @Override
    public List<MarketMeasureBO> findByCourseType(String type, String course) throws SerException {
        MarketMeasureDTO dto = new MarketMeasureDTO();
        dto.getConditions().add(Restrict.eq("course", course));
        dto.getConditions().add(Restrict.eq("type", type));
        List<MarketMeasure> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, MarketMeasureBO.class);
    }

    @Override
    public List<MarketMeasure> findByPage(MarketMeasureDTO dto) throws SerException {
        if (!marPermissionSer.getMarPermission(marketCheck))
            throw new SerException("您的帐号没有权限");
        return super.findByPage(dto);
    }

    @Override
    public byte[] exportExcel(CollectTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(marketCheck))
            throw new SerException("您的帐号没有权限");
        MarketMeasureDTO dto = new MarketMeasureDTO();
        if (StringUtils.isNotBlank(to.getType()))
            dto.getConditions().add(Restrict.eq("type", to.getType()));
        dto.getSorts().add("createTime=desc");
        List<MarketMeasure> list = super.findByCis(dto);
        List<MarketMeasureExcelBO> boList = BeanTransform.copyProperties(list, MarketMeasureExcelBO.class);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(boList, excel);
        return bytes;
    }
}