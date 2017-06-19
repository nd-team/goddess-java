package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.marketdevelopment.bo.MarketMeasureBO;
import com.bjike.goddess.marketdevelopment.bo.MarketMeasureCollectBO;
import com.bjike.goddess.marketdevelopment.bo.MarketMeasureExcelBO;
import com.bjike.goddess.marketdevelopment.dto.MarketMeasureDTO;
import com.bjike.goddess.marketdevelopment.entity.MarketMeasure;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.to.CollectTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.MarketMeasureTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private UserAPI userAPI;

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


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = marPermissionSer.getMarPermission(marketCheck);
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = marPermissionSer.getMarPermission(marketManage);
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public List<MarketMeasureCollectBO> collect() throws SerException {
        MarketMeasureDTO dto = new MarketMeasureDTO();
        dto.getSorts().add("type=desc");
        List<MarketMeasure> list = super.findByCis(dto);
        List<MarketMeasureCollectBO> collectBOs = new ArrayList<>(0);
        String type = "";
        int number = 1;
        for (MarketMeasure entity : list) {
            if (!type.equals(entity.getType())) {
                type = entity.getType();
                MarketMeasureCollectBO bo = new MarketMeasureCollectBO();
                List<MarketMeasure> count = list.stream()
                        .filter(m -> m.getType().equals(entity.getType()))
                        .collect(Collectors.toList());
                bo.setNumber(number++);
                bo.setType(type);
                bo.setCapital(count.stream().mapToDouble(MarketMeasure::getCapital).sum());
                bo.setStaff(count.stream().mapToDouble(MarketMeasure::getStaff).sum());
                bo.setEquipment(count.stream().mapToDouble(MarketMeasure::getEquipment).sum());
                bo.setCar(count.stream().mapToDouble(MarketMeasure::getCar).sum());
                bo.setStaffCost(count.stream().mapToDouble(MarketMeasure::getStaffCost).sum());
                bo.setCarCost(count.stream().mapToDouble(MarketMeasure::getCarCost).sum());
                bo.setEquipmentCost(count.stream().mapToDouble(MarketMeasure::getEquipmentCost).sum());
                collectBOs.add(bo);
            }
        }
        MarketMeasureCollectBO bo = new MarketMeasureCollectBO();
        bo.setType("总计");
        bo.setCapital(list.stream().mapToDouble(MarketMeasure::getCapital).sum());
        bo.setStaff(list.stream().mapToDouble(MarketMeasure::getStaff).sum());
        bo.setEquipment(list.stream().mapToDouble(MarketMeasure::getEquipment).sum());
        bo.setCar(list.stream().mapToDouble(MarketMeasure::getCar).sum());
        bo.setStaffCost(list.stream().mapToDouble(MarketMeasure::getStaffCost).sum());
        bo.setCarCost(list.stream().mapToDouble(MarketMeasure::getCarCost).sum());
        bo.setEquipmentCost(list.stream().mapToDouble(MarketMeasure::getEquipmentCost).sum());
        collectBOs.add(bo);
        return collectBOs;
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
            flag = marPermissionSer.getMarPermission(marketCheck);
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
            flag = marPermissionSer.getMarPermission(marketManage);
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
}