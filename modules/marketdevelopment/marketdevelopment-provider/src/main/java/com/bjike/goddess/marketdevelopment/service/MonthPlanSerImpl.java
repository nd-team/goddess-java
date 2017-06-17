package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.marketdevelopment.bo.MonthPlanBO;
import com.bjike.goddess.marketdevelopment.bo.MonthPlanChoiceBO;
import com.bjike.goddess.marketdevelopment.bo.MonthPlanExcelBO;
import com.bjike.goddess.marketdevelopment.bo.YearPlanBO;
import com.bjike.goddess.marketdevelopment.dto.MonthPlanDTO;
import com.bjike.goddess.marketdevelopment.entity.MonthPlan;
import com.bjike.goddess.marketdevelopment.entity.YearPlan;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.to.CollectTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.MonthPlanTO;
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
 * 月计划业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 06:41 ]
 * @Description: [ 月计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class MonthPlanSerImpl extends ServiceImpl<MonthPlan, MonthPlanDTO> implements MonthPlanSer {

    @Autowired
    private YearPlanSer yearPlanSer;

    @Autowired
    private WeekPlanSer weekPlanSer;

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private MarPermissionSer marPermissionSer;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    private static final String planManage = "plan-manage";

    private static final String planCheck = "plan-check";

    /**
     * 转换月计划传输对象
     *
     * @param entity 月计划实体
     * @returnz
     */
    private MonthPlanBO transformBO(MonthPlan entity) {
        MonthPlanBO bo = BeanTransform.copyProperties(entity, MonthPlanBO.class);
        bo.setYearId(entity.getYear().getId());
        bo.setYearNumber(entity.getYear().getYear());
        bo.setMonthValue(entity.getMonth().getValueString());
        bo.setType(entity.getYear().getType());
        bo.setWorkloadWeight(entity.getYear().getWorkloadWeight());
        bo.setCourse(entity.getYear().getCourse());
        bo.setDevelopment(entity.getYear().getDevelopment());
        bo.setBusinessAccounted(entity.getYear().getBusinessAccounted());
        bo.setYearCourseAccounted(entity.getYear().getCourseAccounted());
        return bo;
    }

    /**
     * 转换月计划传输对象集合
     *
     * @param list 月计划实体集合
     * @return
     */
    private List<MonthPlanBO> transformBOList(List<MonthPlan> list) {
        List<MonthPlanBO> bos = new ArrayList<>(list.size());
        for (MonthPlan entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MonthPlanBO save(MonthPlanTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(planManage))
            throw new SerException("您的帐号没有权限");
        MonthPlan entity = BeanTransform.copyProperties(to, MonthPlan.class);
        entity.setYear(yearPlanSer.findById(to.getYearId()));
        if (entity.getYear() == null)
            throw new SerException("年计划数据为空");
        entity.setTotal(to.getQuota() + entity.getQuota() * entity.getAccounted());
        super.save(entity);
        return this.transformBO(entity);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public MonthPlanBO update(MonthPlanTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(planManage))
            throw new SerException("您的帐号没有权限");
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                MonthPlan entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setYear(yearPlanSer.findById(to.getYearId()));
                entity.setTotal(to.getQuota() + entity.getQuota() * entity.getAccounted() / 100);
                super.update(entity);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return this.transformBO(entity);
            } catch (SerException e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MonthPlanBO delete(MonthPlanTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(planManage))
            throw new SerException("您的帐号没有权限");
        MonthPlan entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        if (weekPlanSer.findByMonth(entity.getId()).size() != 0)
            throw new SerException("存在依赖关系无法删除");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<MonthPlanBO> findByYearID(String yearId) throws SerException {
        MonthPlanDTO dto = new MonthPlanDTO();
        dto.getConditions().add(Restrict.eq("year.id", yearId));
        List<MonthPlan> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public List<MonthPlanBO> findByYear(Integer year) throws SerException {
        String[] fields = {"id", "accounted", "courseAccounted", "leastQuota", "quota", "total", "yearId"};
        StringBuilder sql = new StringBuilder(" SELECT m.id,m.accounted,m.courseAccounted,m.leastQuota,m.quota,m.total,m.year_id FROM marketdevelopment_month_plan AS m ");
        sql.append(" LEFT JOIN (SELECT id FROM marketdevelopment_year_plan WHERE year = ");
        sql.append(year);
        sql.append(") AS y ON y.id = m.year_id");
        List<MonthPlanBO> bos = super.findBySql(sql.toString(), MonthPlanBO.class, fields);
        for (MonthPlanBO bo : bos) {
            YearPlan yearPlan = yearPlanSer.findById(bo.getYearId());
            bo.setYearId(yearPlan.getId());
            bo.setYearNumber(yearPlan.getYear());
            bo.setType(yearPlan.getType());
            bo.setWorkloadWeight(yearPlan.getWorkloadWeight());
            bo.setCourse(yearPlan.getCourse());
            bo.setDevelopment(yearPlan.getDevelopment());
            bo.setBusinessAccounted(yearPlan.getBusinessAccounted());
            bo.setYearCourseAccounted(yearPlan.getCourseAccounted());
            bo.setMonth(super.findById(bo.getId()).getMonth());
        }
        return bos;
    }

    @Override
    public MonthPlanBO getById(String id) throws SerException {
        if (!marPermissionSer.getMarPermission(planCheck))
            throw new SerException("您的帐号没有权限");
        try {
            return this.transformBO(super.findById(id));
        } catch (SerException e) {
            throw new SerException("数据对象不能为空");
        }
    }

    @Override
    public List<MonthPlanBO> maps(MonthPlanDTO dto) throws SerException {
        if (!marPermissionSer.getMarPermission(planCheck))
            throw new SerException("您的帐号没有权限");
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public List<MonthPlanChoiceBO> getChoice() throws SerException {
        List<MonthPlanBO> list = this.findByYear(LocalDate.now().getYear()).stream()
                .sorted(Comparator.comparing(MonthPlanBO::getYearNumber)
                        .thenComparing(MonthPlanBO::getMonth))
                .collect(Collectors.toList());
        List<MonthPlanChoiceBO> choiceBOs = new ArrayList<>(0);
        String format = "%d年%s 任务量:%f";
        for (MonthPlanBO bo : list) {
            MonthPlanChoiceBO choice = new MonthPlanChoiceBO();
            choice.setId(bo.getId());
            choice.setShowValue(String.format(format, bo.getYearNumber(), bo.getMonth().getValueString(), bo.getTotal()));
            choiceBOs.add(choice);
        }
        return choiceBOs;
    }

    @Override
    public List<MonthPlanBO> findByYearIds(String... ids) throws SerException {
        MonthPlanDTO dto = new MonthPlanDTO();
        dto.getConditions().add(Restrict.in("year.id", ids));
        List<MonthPlan> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public List<MonthPlanBO> findByType(String type) throws SerException {
        if (StringUtils.isBlank(type))
            return new ArrayList<>(0);
        List<YearPlanBO> yearPlanBOs = yearPlanSer.findByType(type);
        if (yearPlanBOs == null || yearPlanBOs.size() == 0)
            return new ArrayList<>(0);
        List<MonthPlanBO> boList = this.findByYearIds(yearPlanBOs.stream()
                .map(YearPlanBO::getId).collect(Collectors.toList())
                .toArray(new String[0]));
        return boList;
    }

    @Override
    public byte[] exportExcel(CollectTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(planCheck))
            throw new SerException("您的帐号没有权限");
        List<MonthPlanBO> list;
        if (StringUtils.isBlank(to.getType())) {
            list = this.transformBOList(super.findAll());
        } else {
            list = this.findByType(to.getType());
        }
        list = list.stream()
                .sorted(Comparator.comparing(MonthPlanBO::getYearNumber).thenComparing(MonthPlanBO::getMonth))
                .collect(Collectors.toList());
        List<MonthPlanExcelBO> boList = new ArrayList<>(0);
        for (MonthPlanBO bo : list) {
            MonthPlanExcelBO excelBO = new MonthPlanExcelBO();
            BeanTransform.copyProperties(bo, excelBO, true);
            excelBO.setMonthString(bo.getMonth().getValueString());
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
            flag = marPermissionSer.getMarPermission("1");
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
            flag = marPermissionSer.getMarPermission("2");
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