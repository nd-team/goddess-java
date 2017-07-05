package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.marketdevelopment.bo.MonthPlanBO;
import com.bjike.goddess.marketdevelopment.bo.WeekPlanBO;
import com.bjike.goddess.marketdevelopment.bo.WeekPlanExcelBO;
import com.bjike.goddess.marketdevelopment.dto.WeekPlanDTO;
import com.bjike.goddess.marketdevelopment.entity.WeekPlan;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.to.CollectTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.WeekPlanTO;
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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 周计划业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 06:49 ]
 * @Description: [ 周计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class WeekPlanSerImpl extends ServiceImpl<WeekPlan, WeekPlanDTO> implements WeekPlanSer {

    @Autowired
    private MonthPlanSer monthPlanSer;
    @Autowired
    private MarPermissionSer marPermissionSer;
    @Autowired
    private UserAPI userAPI;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    private static final String planManage = "plan-manage";

    private static final String planCheck = "plan-check";

    private WeekPlanBO transformBO(WeekPlan entity) {
        WeekPlanBO bo = BeanTransform.copyProperties(entity, WeekPlanBO.class);
        bo.setCourse(entity.getMonth().getYear().getCourse());
        bo.setMonthId(entity.getMonth().getId());
        bo.setMonthTotal(entity.getMonth().getTotal());
        bo.setYear(entity.getMonth().getYear().getYear());
        bo.setMonthValue(entity.getMonth().getMonth().getCode());
        bo.setType(entity.getMonth().getMonth().getValueString());
        bo.setCycle(entity.getStartCycle().toString() + "至" + entity.getEndCycle().toString());
        return bo;
    }

    private List<WeekPlanBO> transformBOList(List<WeekPlan> list) {
        List<WeekPlanBO> bos = new ArrayList<>(list.size());
        for (WeekPlan entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WeekPlanBO save(WeekPlanTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(planManage))
//            throw new SerException("您的帐号没有权限");
        WeekPlan entity = BeanTransform.copyProperties(to, WeekPlan.class, true);
        entity.setMonth(monthPlanSer.findById(to.getMonthId()));
        entity.setTotal(entity.getActivity() + entity.getVisit() + entity.getContact() + entity.getKnow() + entity.getInquire());
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WeekPlanBO update(WeekPlanTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(planManage))
//            throw new SerException("您的帐号没有权限");
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                WeekPlan entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return this.transformBO(entity);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WeekPlanBO delete(WeekPlanTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(planManage))
//            throw new SerException("您的帐号没有权限");
        WeekPlan entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<WeekPlanBO> findByMonth(String monthId) throws SerException {
        WeekPlanDTO dto = new WeekPlanDTO();
        dto.getConditions().add(Restrict.eq("month.id", monthId));
        List<WeekPlan> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public List<WeekPlanBO> findByDate(String startDate, String endDate) throws SerException {
        LocalDate start = LocalDate.parse(startDate), end = LocalDate.parse(endDate);
        WeekPlanDTO dto = new WeekPlanDTO();
        dto.getConditions().add(Restrict.gt_eq("startCycle", startDate));
        dto.getConditions().add(Restrict.lt_eq("startCycle", endDate));
        dto.getConditions().add(Restrict.gt_eq("endCycle", startDate));
        dto.getConditions().add(Restrict.lt_eq("endCycle", endDate));
        List<WeekPlan> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public WeekPlanBO getById(String id) throws SerException {
//        if (!isPermission(planCheck))
//            throw new SerException("您的帐号没有权限");
        try {
            return this.transformBO(super.findById(id));
        } catch (SerException e) {
            throw new SerException("数据对象不能为空");
        }
    }

    @Override
    public List<WeekPlanBO> maps(WeekPlanDTO dto) throws SerException {
//        if (!isPermission(planCheck))
//            throw new SerException("您的帐号没有权限");
        dto.getSorts().add("startCycle=desc");
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public List<WeekPlanBO> findByMonthIds(String... ids) throws SerException {
        if (ids == null && ids.length == 0)
            return new ArrayList<>(0);
        WeekPlanDTO dto = new WeekPlanDTO();
        dto.getConditions().add(Restrict.in("month.id", ids));
        dto.getSorts().add("startCycle=desc");
        return this.transformBOList(super.findByCis(dto));
    }

    @Override
    public List<WeekPlanBO> findByType(String type) throws SerException {
        if (StringUtils.isBlank(type))
            return new ArrayList<>(0);
        List<MonthPlanBO> monthPlanBOs = monthPlanSer.findByType(type);
        if (monthPlanBOs.size() == 0)
            return new ArrayList<>(0);
        List<WeekPlanBO> weekPlanBOs = this.findByMonthIds(monthPlanBOs.stream()
                .map(MonthPlanBO::getId)
                .collect(Collectors.toList())
                .toArray(new String[0]));
        return weekPlanBOs;
    }

    @Override
    public byte[] exportExcel(CollectTO to) throws SerException {
//        if (!isPermission(planCheck))
//            throw new SerException("您的帐号没有权限");
        List<WeekPlanBO> list;
        if (StringUtils.isBlank(to.getType())) {
            list = this.transformBOList(super.findAll().stream()
                    .sorted(Comparator.comparing(WeekPlan::getStartCycle).reversed())
                    .collect(Collectors.toList()));
        } else {
            list = this.findByType(to.getType());
        }
        List<WeekPlanExcelBO> boList = new ArrayList<>(0);
        for (WeekPlanBO bo : list) {
            WeekPlanExcelBO excelBO = new WeekPlanExcelBO();
            BeanTransform.copyProperties(bo, excelBO, true);
            boList.add(excelBO);
        }
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

    public Boolean isPermission(String planCheck) throws SerException{

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

}