package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.marketdevelopment.bo.DayPlanBO;
import com.bjike.goddess.marketdevelopment.bo.DayPlanExcelBO;
import com.bjike.goddess.marketdevelopment.dto.DayPlanDTO;
import com.bjike.goddess.marketdevelopment.entity.DayPlan;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.to.CollectTO;
import com.bjike.goddess.marketdevelopment.to.DayPlanTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 天计划业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:08 ]
 * @Description: [ 天计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class DayPlanSerImpl extends ServiceImpl<DayPlan, DayPlanDTO> implements DayPlanSer {

    @Autowired
    private MarPermissionSer marPermissionSer;
    @Autowired
    private UserAPI userAPI;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    private static final String planManage = "plan-manage";

    private static final String planCheck = "plan-check";


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
            flag = marPermissionSer.getMarPermission(planCheck);
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
            flag = marPermissionSer.getMarPermission(planManage);
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

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DayPlanBO save(DayPlanTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(planManage))
//            throw new SerException("您的帐号没有权限");
        DayPlan entity = BeanTransform.copyProperties(to, DayPlan.class, true);
        entity.setSerialNumber(this.createNumber(entity.getTime()));
        super.save(entity);
        return BeanTransform.copyProperties(entity, DayPlanBO.class);
    }

    private String createNumber(LocalDate time) throws SerException {
        String day = time.toString().replaceAll("-", "");
        List<DayPlanBO> list = this.findByDate(time.toString());
        if (null == list)
            list = new ArrayList<>(0);
        StringBuilder serial = new StringBuilder();
        serial.append(day).append("-").append(list.size() + 1);
        return serial.toString();
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DayPlanBO update(DayPlanTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(planManage))
//            throw new SerException("您的帐号没有权限");
        DayPlan entity = super.findById(to.getId());
        if (null != entity) {
            BeanTransform.copyProperties(to, entity, true);
            entity.setTime(LocalDate.parse(to.getTime()));
            entity.setSerialNumber(this.createNumber(entity.getTime()));
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return BeanTransform.copyProperties(entity, DayPlanBO.class);
        } else {
            throw new SerException("数据对象不能为空");
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DayPlanBO delete(DayPlanTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(planManage))
//            throw new SerException("您的帐号没有权限");
        DayPlan entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, DayPlanBO.class);
    }

    @Override
    public List<DayPlanBO> findByDate(String start, String end) throws SerException {
        LocalDate startDate = LocalDate.parse(start), endDate = LocalDate.parse(end);
        DayPlanDTO dto = new DayPlanDTO();
        dto.getConditions().add(Restrict.gt_eq("time", startDate));
        dto.getConditions().add(Restrict.lt_eq("time", endDate));
        List<DayPlan> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, DayPlanBO.class);
    }

    @Override
    public List<DayPlanBO> findByDate(String date) throws SerException {
        LocalDate time = LocalDate.parse(date);
        DayPlanDTO dto = new DayPlanDTO();
        dto.getConditions().add(Restrict.eq("time", time));
        List<DayPlan> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, DayPlanBO.class);
    }

    @Override
    public List<DayPlanBO> maps(DayPlanDTO dto) throws SerException {
//        if (!marPermissionSer.getMarPermission(planCheck))
//            throw new SerException("您的帐号没有权限");
        dto.getSorts().add("time=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), DayPlanBO.class);
    }

    @Override
    public byte[] exportExcel(CollectTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(planCheck))
//            throw new SerException("您的帐号没有权限");
        DayPlanDTO dto = new DayPlanDTO();
        if (StringUtils.isNotBlank(to.getType()))
            dto.getConditions().add(Restrict.eq("type", to.getType()));
        if (StringUtils.isNotBlank(to.getStart()) && StringUtils.isNotBlank(to.getEnd())) {
            LocalDate[] time = {LocalDate.parse(to.getStart()), LocalDate.parse(to.getEnd())};
            dto.getConditions().add(Restrict.between("time", time));
        }
        dto.getSorts().add("time=desc");
        List<DayPlan> list = super.findByCis(dto);
        List<DayPlanExcelBO> boList = BeanTransform.copyProperties(list, DayPlanExcelBO.class);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(boList, excel);
        return bytes;
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = marPermissionSer.getMarPermission(planCheck);
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = marPermissionSer.getMarPermission(planManage);
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }
}