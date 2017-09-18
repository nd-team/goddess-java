package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.managepromotion.bo.*;
import com.bjike.goddess.managepromotion.dto.EmployeePromotedDTO;
import com.bjike.goddess.managepromotion.entity.EmployeePromoted;
import com.bjike.goddess.managepromotion.entity.OverviewSkillLevel;
import com.bjike.goddess.managepromotion.enums.GuideAddrStatus;
import com.bjike.goddess.managepromotion.to.*;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 员工已晋升情况业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:20 ]
 * @Description: [ 员工已晋升情况业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managepromotionSerCache")
@Service
public class EmployeePromotedSerImpl extends ServiceImpl<EmployeePromoted, EmployeePromotedDTO> implements EmployeePromotedSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private OverviewSkillLevelSer overviewSkillLevelSer;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

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
            case DELETE:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countEmployeePromoted(EmployeePromotedDTO employeePromotedDTO) throws SerException {
        Long count = super.count(employeePromotedDTO);
        return count;
    }

    @Override
    public EmployeePromotedBO getOne(String id) throws SerException {
        EmployeePromoted employeePromoted = super.findById(id);
        return BeanTransform.copyProperties(employeePromoted, EmployeePromotedBO.class);
    }

    @Override
    public List<EmployeePromotedBO> findListEmployeePromoted(EmployeePromotedDTO employeePromotedDTO) throws SerException {
        checkSeeIdentity();
        seach(employeePromotedDTO);
        employeePromotedDTO.getSorts().add("createTime=desc");
        List<EmployeePromoted> employeePromoteds = super.findByPage(employeePromotedDTO);
        List<EmployeePromotedBO> employeePromotedBOS = BeanTransform.copyProperties(employeePromoteds, EmployeePromotedBO.class);
        return employeePromotedBOS;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EmployeePromotedBO insertEmployeePromoted(EmployeePromotedTO employeePromotedTO) throws SerException {
        checkAddIdentity();
        EmployeePromoted employeePromoted = BeanTransform.copyProperties(employeePromotedTO, EmployeePromoted.class, true);
        employeePromoted.setModifyTime(LocalDateTime.now());
        super.save(employeePromoted);
        return BeanTransform.copyProperties(employeePromoted, EmployeePromotedBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EmployeePromotedBO editEmployeePromoted(EmployeePromotedTO employeePromotedTO) throws SerException {
        checkAddIdentity();
        EmployeePromoted employeePromoted = super.findById(employeePromotedTO.getId());
        BeanTransform.copyProperties(employeePromotedTO, employeePromoted, true);
        employeePromoted.setModifyTime(LocalDateTime.now());
        super.update(employeePromoted);

        return BeanTransform.copyProperties(employeePromoted, EmployeePromotedBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeEmployeePromoted(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    public void seach(EmployeePromotedDTO employeePromotedDTO) throws SerException {
        /**
         * 姓名
         */

        if (StringUtils.isNotBlank(employeePromotedDTO.getName())) {
            employeePromotedDTO.getConditions().add(Restrict.eq("name", employeePromotedDTO.getName()));
        }
        /**
         * 时间
         */
        String start = employeePromotedDTO.getStartTimes();
        String end = employeePromotedDTO.getEndTimes();
        if (StringUtils.isNotBlank(start) && StringUtils.isNotBlank(end)) {
            String[] condi = new String[]{start, end};
            employeePromotedDTO.getConditions().add(Restrict.between("times", condi));
        }
        /**
         * 状态
         */
        if (StringUtils.isNotBlank(employeePromotedDTO.getStatus())) {
            employeePromotedDTO.getConditions().add(Restrict.eq("status", employeePromotedDTO.getStatus()));
        }
    }

    @Override
    public List<CollectBO> collect(CollectTO to) throws SerException {
        EmployeePromotedDTO dto = new EmployeePromotedDTO();
        if (null != to.getName()) {
            dto.getConditions().add(Restrict.in("name", to.getName()));
        }
        String start = to.getStartTime();
        String end = to.getEndTime();
        if (StringUtils.isNotBlank(start) && StringUtils.isNotBlank(end)) {
            String[] condi = new String[]{start, end};
            dto.getConditions().add(Restrict.between("times", condi));
        }
        if (null != to.getStatus()) {
            dto.getConditions().add(Restrict.eq("status", to.getStatus()));
        }
        return promotCollect(dto);
    }

    public List<CollectBO> promotCollect(EmployeePromotedDTO dto) throws SerException {
        List<EmployeePromoted> employeePromoteds = super.findByCis(dto);
        List<CollectBO> collectBOS = new ArrayList<>();
        for (EmployeePromoted model : employeePromoteds) {
            CollectBO bo = new CollectBO();
            bo.setDepartment(model.getDepartment());
            bo.setProjectGroup(model.getProjectGroup());
            bo.setName(model.getName());
            bo.setJobs(model.getJobs());
            bo.setChannel(model.getChannel());
            bo.setTimes(DateUtil.dateToString(model.getTimes()));
            bo.setPromotionBefore(model.getPromotionBefore());
            bo.setPromotionAfter(model.getPromotionAfter());
            bo.setExtent(model.getExtent());
            bo.setTotalRange(model.getTotalRange());
            bo.setStatus(model.getStatus());
            collectBOS.add(bo);

        }
        //幅度
        Integer extent = 0;
        //总幅度
        Integer totalRange = 0;
        if (employeePromoteds != null) {
            extent = employeePromoteds.stream().filter(p -> p.getExtent() != null).mapToInt(p -> p.getExtent()).sum();
            totalRange = employeePromoteds.stream().filter(p -> p.getTotalRange() != null).mapToInt(p -> p.getTotalRange()).sum();

            CollectBO totalBo = new CollectBO("合计幅度", extent, totalRange);
            collectBOS.add(totalBo);
        } else {
            extent = employeePromoteds.stream().filter(p -> p.getExtent() != null).mapToInt(p -> p.getExtent()).sum();
            totalRange = employeePromoteds.stream().filter(p -> p.getTotalRange() != null).mapToInt(p -> p.getTotalRange()).sum();

            CollectBO totalBo = new CollectBO("合计幅度", extent, totalRange);
            collectBOS.add(totalBo);
        }
        return collectBOS;
    }

    @Override
    public List<String> getName() throws SerException {
        String[] fields = new String[]{"name"};
        List<EmployeePromotedBO> boList = super.findBySql("select name from managepromotion_employeepromoted group by name order by name asc ", EmployeePromotedBO.class, fields);

        List<String> nameList = boList.stream().map(EmployeePromotedBO::getName)
                .filter(name -> (name != null || !"".equals(name.trim()))).distinct().collect(Collectors.toList());


        return nameList;
    }

    @Override
    public List<String> getStatus() throws SerException {
        String[] fields = new String[]{"status"};
        List<EmployeePromotedBO> boList = super.findBySql("select status from managepromotion_employeepromoted group by status order by status asc ", EmployeePromotedBO.class, fields);

        List<String> statusList = boList.stream().map(EmployeePromotedBO::getStatus)
                .filter(status -> (status != null || !"".equals(status.trim()))).distinct().collect(Collectors.toList());


        return statusList;
    }

    @Override
    public List<SkillPromotionDetailCollectBO> detailWeekCollect(SkillPromotionDetailCollectTO to) throws SerException {
        List<SkillPromotionDetailCollectBO> boList = new ArrayList<>();
        LocalDate[] time = null;
        Integer year = to.getYear();
        Integer month = to.getMonth();
        Integer week = to.getWeek();
        if (year != null && month != null && week != null) {
            time = DateUtil.getWeekTimes(year, month, week);
        } else {
            String dateString = DateUtil.dateToString(LocalDate.now());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
            time = DateUtil.getWeekTimes(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), weekOfMonth);
        }
        LocalDate start = time[0];
        LocalDate end = time[1];
        String sql = " SELECT projectGroup AS projectGroup,name AS name,jobs AS jobs, " +
                " channel AS channel,times AS times,sum(promotionBefore)AS promotionBefore, " +
                " sum(promotionAfter) AS promotionAfter,sum(promotionAfter-promotionBefore)as extent " +
                " FROM managepromotion_employeepromoted WHERE times between '"+start+"' and '"+end+"' " +
                " GROUP BY projectGroup,name,jobs,channel,times ";
        String[] fields = new String[]{"projectGroup","name","jobs","channel","times","promotionBefore","promotionAfter","extent"};
        List<SkillPromotionDetailCollectBO> skillPromotionDetailCollectBOS = super.findBySql(sql,SkillPromotionDetailCollectTO.class,fields);
        for (SkillPromotionDetailCollectBO collectBO:skillPromotionDetailCollectBOS){
            collectBO.setCycle(start+"-"+end);
            boList.add(collectBO);
        }
        return boList;
    }


    @Override
    public List<SkillPromotionDetailCollectBO> detailMonthCollect(SkillPromotionDetailCollectTO to) throws SerException {
        return null;
    }

    @Override
    public List<SkillPromotionDetailCollectBO> detailTotalCollect(SkillPromotionDetailCollectTO to) throws SerException {
        return null;
    }

    @Override
    public List<ProfessionalSkillCollectBO> dayProfessionalCollect(ProfessionalSkillTO to) throws SerException {
        LocalDate time = null;
        if (to.getTime() != null) {
            time = DateUtil.parseDate(to.getTime());
        } else {
            time =LocalDate.now();
        }
        List<ProfessionalSkillCollectBO> boList = new ArrayList<>();
        String sql = "SELECT  area AS area ,department AS  department,major AS  major,grade AS grade,count(*) as count " +
                " FROM managepromotion_overviewskilllevel WHERE acquisitionTime='" + time + "' GROUP BY area,department,major,grade ";
        String[] fields = new String[]{"area", "department", "major", "grade", "people"};
        List<ProfessionalSkillCollectBO> professionalSkillBOS = super.findBySql(sql, ProfessionalSkillCollectBO.class, fields);

        for (ProfessionalSkillCollectBO collectBO : professionalSkillBOS) {
            Integer peopleCount = 0;
            peopleCount = departmentDetailAPI.departmentTotalPeople(collectBO.getDepartment());
            collectBO.setPromotionPeople(collectBO.getPeople() - peopleCount);
            boList.add(collectBO);
        }
        return boList;
    }

    @Override
    public List<ProfessionalSkillCollectBO> weekProfessionalCollect(ProfessionalSkillTO to) throws SerException {
        LocalDate[] time = null;
        Integer year = to.getYear();
        Integer month = to.getMonth();
        Integer week = to.getWeek();
        if (year != null && month != null && week != null) {
            time = DateUtil.getWeekTimes(year, month, week);
        } else {
            String dateString = DateUtil.dateToString(LocalDate.now());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
            time = DateUtil.getWeekTimes(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), weekOfMonth);
        }
        LocalDate start = time[0];
        LocalDate end = time[1];
        List<ProfessionalSkillCollectBO> boList = new ArrayList<>();
        String sql = "SELECT  area AS area ,department AS  department,major AS  major,grade AS grade,count(*) as count " +
                " FROM managepromotion_overviewskilllevel WHERE acquisitionTime between '" + start + "' and '" + end + "' GROUP BY area,department,major,grade ";
        String[] fields = new String[]{"area", "department", "major", "grade", "people"};
        List<ProfessionalSkillCollectBO> professionalSkillBOS = super.findBySql(sql, ProfessionalSkillCollectBO.class, fields);

        for (ProfessionalSkillCollectBO collectBO : professionalSkillBOS) {
            Integer peopleCount = 0;
            peopleCount = departmentDetailAPI.departmentTotalPeople(collectBO.getDepartment());
            collectBO.setPromotionPeople(collectBO.getPeople() - peopleCount);
            boList.add(collectBO);
        }
        return boList;
    }


    @Override
    public List<ProfessionalSkillCollectBO> monthProfessionalCollect(ProfessionalSkillTO to) throws SerException {
        Integer year = 0;
        Integer month = 0;
        if (to.getYear() != null && to.getMonth() != null) {
            year = to.getYear();
            month = to.getMonth();
        } else {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }

        List<ProfessionalSkillCollectBO> boList = new ArrayList<>();
        String sql = "SELECT  area AS area ,department AS  department,major AS  major,grade AS grade,count(*) as count " +
                " FROM managepromotion_overviewskilllevel WHERE year(acquisitionTime) = '" + year + "' and month(acquisitionTime) = '" + month + "' GROUP BY area,department,major,grade ";
        String[] fields = new String[]{"area", "department", "major", "grade", "people"};
        List<ProfessionalSkillCollectBO> professionalSkillBOS = super.findBySql(sql, ProfessionalSkillCollectBO.class, fields);

        for (ProfessionalSkillCollectBO collectBO : professionalSkillBOS) {
            Integer peopleCount = 0;
            peopleCount = departmentDetailAPI.departmentTotalPeople(collectBO.getDepartment());
            collectBO.setPromotionPeople(collectBO.getPeople() - peopleCount);
            boList.add(collectBO);
        }
        return boList;
    }

    @Override
    public List<ProfessionalSkillCollectBO> totalProfessionalCollect(ProfessionalSkillTO to) throws SerException {
        LocalDate end = null;
        if (to.getTime() != null) {
            end = DateUtil.parseDate(to.getTime());
        } else {
            end = LocalDate.now();
        }
        List<ProfessionalSkillCollectBO> boList = new ArrayList<>();

        String sql = "SELECT  area AS area ,department AS  department,major AS  major,grade AS grade,count(*) as count " +
                " FROM managepromotion_overviewskilllevel where acquisitionTime <= '" + end + "' GROUP BY area,department,major,grade ";
        String[] fields = new String[]{"area", "department", "major", "grade", "people"};
        List<ProfessionalSkillCollectBO> professionalSkillBOS = super.findBySql(sql, ProfessionalSkillCollectBO.class, fields);

        for (ProfessionalSkillCollectBO collectBO : professionalSkillBOS) {
            Integer peopleCount = 0;
            peopleCount = departmentDetailAPI.departmentTotalPeople(collectBO.getDepartment());
            collectBO.setPromotionPeople(collectBO.getPeople() - peopleCount);
            boList.add(collectBO);
        }
        return boList;
    }


    //获取所有地区
    private List<String> professionalAreas() throws SerException {
        String[] fields = new String[]{"area"};
        String sql = " SELECT area AS  area FROM managepromotion_overviewskilllevel GROUP BY area ";
        List<OverviewSkillLevel> overviewSkillLevels = super.findBySql(sql, OverviewSkillLevel.class, fields);
        List<String> areas = overviewSkillLevels.stream().map(OverviewSkillLevel::getArea).collect(Collectors.toList());
        return areas;
    }

    //获取所有项目组/部门
    private List<String> professionaldDepartments() throws SerException {
        String[] fields = new String[]{"department"};
        String sql = " SELECT department AS  department FROM managepromotion_overviewskilllevel GROUP BY department ";
        List<OverviewSkillLevel> overviewSkillLevels = super.findBySql(sql, OverviewSkillLevel.class, fields);
        List<String> departments = overviewSkillLevels.stream().map(OverviewSkillLevel::getDepartment).collect(Collectors.toList());
        return departments;
    }


    @Override
    public List<StaffSkillCollectBO> dayStaffCollect(StaffSkillCollectTO to) throws SerException {
        LocalDate time = null;
        if (to.getTime() != null) {
            time = DateUtil.parseDate(to.getTime());
        } else {
            time = LocalDate.now();
        }
        List<StaffSkillCollectBO> boList = new ArrayList<>();
        String sql = " SELECT area AS area ,department AS  department,name as name, " +
                " count(*) as skillNum,sum(promotedNumber) AS promotedNumber " +
                " FROM managepromotion_overviewskilllevel where acquisitionTime = '" + time + "' " +
                " GROUP BY area,department,name ";
        String[] fields = new String[]{"area", "department", "name", "skillNum", "promotedNumber"};
        boList = super.findBySql(sql, StaffSkillCollectBO.class, fields);
        return boList;
    }

    @Override
    public List<StaffSkillCollectBO> weekStaffCollect(StaffSkillCollectTO to) throws SerException {
        LocalDate[] time = null;
        Integer year = to.getYear();
        Integer month = to.getMonth();
        Integer week = to.getWeek();
        if (year != null && month != null && week != null) {
            time = DateUtil.getWeekTimes(year, month, week);
        } else {
            String dateString = DateUtil.dateToString(LocalDate.now());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
            time = DateUtil.getWeekTimes(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), weekOfMonth);
        }
        LocalDate start = time[0];
        LocalDate end = time[1];
        List<StaffSkillCollectBO> boList = new ArrayList<>();
        String sql = " SELECT area AS area ,department AS  department,name as name, " +
                " count(*) as skillNum,sum(promotedNumber) AS promotedNumber " +
                " FROM managepromotion_overviewskilllevel WHERE acquisitionTime between '" + start + "' and '" + end + "' GROUP BY area,department,name ";
        String[] fields = new String[]{"area", "department", "name", "skillNum", "promotedNumber"};
        boList = super.findBySql(sql, StaffSkillCollectBO.class, fields);
        return boList;
    }

    @Override
    public List<StaffSkillCollectBO> monthStaffCollect(StaffSkillCollectTO to) throws SerException {
        Integer year = 0;
        Integer month = 0;
        if (to.getYear() != null && to.getMonth() != null) {
            year = to.getYear();
            month = to.getMonth();
        } else {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        List<StaffSkillCollectBO> boList = new ArrayList<>();
        String sql = " SELECT area AS area ,department AS  department,name as name, " +
                " count(*) as skillNum,sum(promotedNumber) AS promotedNumber " +
                " FROM managepromotion_overviewskilllevel WHERE year(acquisitionTime) = '" + year + "' and month(acquisitionTime) " +
                " = '" + month + "' GROUP BY area,department,name ";
        String[] fields = new String[]{"area", "department", "name", "skillNum", "promotedNumber"};
        boList = super.findBySql(sql, StaffSkillCollectBO.class, fields);
        return boList;
    }

    @Override
    public List<StaffSkillCollectBO> totalStaffCollect(StaffSkillCollectTO to) throws SerException {
        LocalDate end = null;
        if (to.getTime() != null) {
            end = DateUtil.parseDate(to.getTime());
        } else {
            end = LocalDate.now();
        }
        List<StaffSkillCollectBO> boList = new ArrayList<>();
        String sql = " SELECT area AS area ,department AS  department,name as name, " +
                " count(*) as skillNum,sum(promotedNumber) AS promotedNumber " +
                " FROM managepromotion_overviewskilllevel WHERE acquisitionTime <= '"+end+"' GROUP BY area,department,name ";
        String[] fields = new String[]{"area", "department", "name", "skillNum", "promotedNumber"};
        boList = super.findBySql(sql, StaffSkillCollectBO.class, fields);
        return boList;
    }

}