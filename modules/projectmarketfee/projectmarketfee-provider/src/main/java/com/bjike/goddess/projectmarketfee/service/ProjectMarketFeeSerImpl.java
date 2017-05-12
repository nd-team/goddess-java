package com.bjike.goddess.projectmarketfee.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.projectmarketfee.bo.ProjectMarketFeeBO;
import com.bjike.goddess.projectmarketfee.bo.ProjectMarketFeeCountBO;
import com.bjike.goddess.projectmarketfee.dto.ProjectMarketFeeDTO;
import com.bjike.goddess.projectmarketfee.entity.ProjectMarketFee;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import com.bjike.goddess.voucher.entity.VoucherGenerate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 项目前期的市场活动费业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-10 10:34 ]
 * @Description: [ 项目前期的市场活动费业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmarketfeeSerCache")
@Service
public class ProjectMarketFeeSerImpl extends ServiceImpl<ProjectMarketFee, ProjectMarketFeeDTO> implements ProjectMarketFeeSer {
    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;

    @Override
    public List<ProjectMarketFeeBO> list(ProjectMarketFeeDTO dto) throws SerException {
        List<VoucherGenerateBO> list = voucherGenerateAPI.allSales();
        List<ProjectMarketFee> projectMarketFees = super.findAll();
        if (list != null) {
            for (VoucherGenerateBO v : list) {
                if (projectMarketFees.size() == 0) {
                    ProjectMarketFee projectMarketFee = new ProjectMarketFee();
                    BeanUtils.copyProperties(v, projectMarketFee);
                    LocalDate date = DateUtil.parseDate(v.getVoucherDate());
                    Integer year = date.getYear();
                    Integer month = date.getMonthValue();
                    projectMarketFee.setYear(year);
                    projectMarketFee.setMonth(month);
                    projectMarketFee.setSaleId(v.getId());
                    super.save(projectMarketFee);
                } else {
                    boolean b1 = true;
                    for (ProjectMarketFee p : projectMarketFees) {
                        if (p.getSaleId().equals(v.getId())) {
                            LocalDateTime a = p.getCreateTime();
                            LocalDateTime b = p.getModifyTime();
                            String id = p.getId();
                            BeanUtils.copyProperties(v, p);
                            LocalDate date = DateUtil.parseDate(v.getVoucherDate());
                            Integer year = date.getYear();
                            Integer month = date.getMonthValue();
                            p.setId(id);
                            p.setYear(year);
                            p.setMonth(month);
                            p.setCreateTime(a);
                            p.setModifyTime(b);
                            p.setSaleId(v.getId());
                            super.update(p);
                            b1 = false;
                        }
                    }
                    if (b1) {
                        ProjectMarketFee projectMarketFee = new ProjectMarketFee();
                        BeanUtils.copyProperties(v, projectMarketFee);
                        projectMarketFee.setSaleId(v.getId());
                        LocalDate date = DateUtil.parseDate(v.getVoucherDate());
                        Integer year = date.getYear();
                        Integer month = date.getMonthValue();
                        projectMarketFee.setYear(year);
                        projectMarketFee.setMonth(month);
                        super.save(projectMarketFee);
                    }
                }
            }
        }
        for (ProjectMarketFee p : super.findAll()) {
            VoucherGenerate v = find(p.getSaleId());
            if ((v == null) || (!("销售费用".equals(v.getFirstSubject())))) {
                super.remove(p.getId());
            }
        }
        List<ProjectMarketFee> l = super.findByCis(dto, true);
        return BeanTransform.copyProperties(l, ProjectMarketFeeBO.class);
    }

    @Override
    public List<ProjectMarketFeeCountBO> firstSubjectCount(String startTime, String endTime) throws SerException {
        list(new ProjectMarketFeeDTO());
        LocalDate[] time = null;
        try {
            time = new LocalDate[]{DateUtil.parseDate(startTime), DateUtil.parseDate(endTime)};
        } catch (Exception e) {
            throw new SerException("日期格式错误");
        }
        ProjectMarketFeeDTO dto = new ProjectMarketFeeDTO();
        dto.getConditions().add(Restrict.between("voucherDate", time));
        List<ProjectMarketFee> all = super.findByCis(dto);
        Set<String> firstSubjects = allFirstSubjects();
        Set<String> secondSubjects = allSecondSubjects();
        Set<String> thirdSubjects = allThirdSubjects();
        Set<String> projectGroups = allProjectGroups();
        Double oneSum = 0.00;
        Double twoSum = 0.00;
        Double threeSum = 0.00;
        String times = startTime + "～" + endTime;
        List<ProjectMarketFeeCountBO> oneBos = new ArrayList<ProjectMarketFeeCountBO>();
        List<ProjectMarketFeeCountBO> twoBos = new ArrayList<ProjectMarketFeeCountBO>();
        List<ProjectMarketFeeCountBO> threeBos = new ArrayList<ProjectMarketFeeCountBO>();
        for (String firstSubject : firstSubjects) {
            for (String projectGroup : projectGroups) {
                for (ProjectMarketFee p : all) {
                    if (p.getFirstSubject().equals(firstSubject) && p.getProjectGroup().equals(projectGroup)) {
                        oneSum += p.getBorrowMoney();
                    }
                }
                if (oneSum != 0) {
                    ProjectMarketFeeCountBO bo = new ProjectMarketFeeCountBO();
                    bo.setFirstSubject(firstSubject);
                    bo.setProjectGroup(projectGroup);
                    bo.setVoucherDate(times);
                    bo.setBorrowMoney(oneSum);
                    oneBos.add(bo);
                    oneSum = 0.00;   //置为0
                }
            }
        }
        return oneBos;
    }

    @Override
    public List<ProjectMarketFeeCountBO> secondSubjectCount(String startTime, String endTime) throws SerException {
        list(new ProjectMarketFeeDTO());
        LocalDate[] time = null;
        try {
            time = new LocalDate[]{DateUtil.parseDate(startTime), DateUtil.parseDate(endTime)};
        } catch (Exception e) {
            throw new SerException("日期格式错误");
        }
        ProjectMarketFeeDTO dto = new ProjectMarketFeeDTO();
        dto.getConditions().add(Restrict.between("voucherDate", time));
        List<ProjectMarketFee> all = super.findByCis(dto);
        Set<String> firstSubjects = allFirstSubjects();
        Set<String> secondSubjects = allSecondSubjects();
        Set<String> thirdSubjects = allThirdSubjects();
        Set<String> projectGroups = allProjectGroups();
        Double oneSum = 0.00;
        Double twoSum = 0.00;
        Double threeSum = 0.00;
        String times = startTime + "～" + endTime;
        List<ProjectMarketFeeCountBO> oneBos = new ArrayList<ProjectMarketFeeCountBO>();
        List<ProjectMarketFeeCountBO> twoBos = new ArrayList<ProjectMarketFeeCountBO>();
        List<ProjectMarketFeeCountBO> threeBos = new ArrayList<ProjectMarketFeeCountBO>();
        for (String firstSubject : firstSubjects) {
            for (String secondSubject : secondSubjects) {
                for (String projectGroup : projectGroups) {
                    for (ProjectMarketFee p : all) {
                        if (p.getFirstSubject().equals(firstSubject) && p.getSecondSubject().equals(secondSubject) && p.getProjectGroup().equals(projectGroup)) {
                            twoSum += p.getBorrowMoney();
                        }
                    }
                    if (twoSum != 0) {
                        ProjectMarketFeeCountBO bo = new ProjectMarketFeeCountBO();
                        bo.setFirstSubject(firstSubject);
                        bo.setSecondSubject(secondSubject);
                        bo.setProjectGroup(projectGroup);
                        bo.setVoucherDate(times);
                        bo.setBorrowMoney(twoSum);
                        twoBos.add(bo);
                        twoSum = 0.00;   //置为0
                    }
                }
            }
        }
        return twoBos;
    }

    @Override
    public List<ProjectMarketFeeCountBO> thirdSubjectCount(String startTime, String endTime) throws SerException {
        list(new ProjectMarketFeeDTO());
        LocalDate[] time = null;
        try {
            time = new LocalDate[]{DateUtil.parseDate(startTime), DateUtil.parseDate(endTime)};
        } catch (Exception e) {
            throw new SerException("日期格式错误");
        }
        ProjectMarketFeeDTO dto = new ProjectMarketFeeDTO();
        dto.getConditions().add(Restrict.between("voucherDate", time));
        List<ProjectMarketFee> all = super.findByCis(dto);
        Set<String> firstSubjects = allFirstSubjects();
        Set<String> secondSubjects = allSecondSubjects();
        Set<String> thirdSubjects = allThirdSubjects();
        Set<String> projectGroups = allProjectGroups();
        Double oneSum = 0.00;
        Double twoSum = 0.00;
        Double threeSum = 0.00;
        String times = startTime + "～" + endTime;
        List<ProjectMarketFeeCountBO> oneBos = new ArrayList<ProjectMarketFeeCountBO>();
        List<ProjectMarketFeeCountBO> twoBos = new ArrayList<ProjectMarketFeeCountBO>();
        List<ProjectMarketFeeCountBO> threeBos = new ArrayList<ProjectMarketFeeCountBO>();
        for (String firstSubject : firstSubjects) {
            for (String secondSubject : secondSubjects) {
                for (String thirdSubject : thirdSubjects) {
                    for (String projectGroup : projectGroups) {
                        for (ProjectMarketFee p : all) {
                            if (p.getFirstSubject().equals(firstSubject) && p.getSecondSubject().equals(secondSubject) && p.getThirdSubject().equals(thirdSubject) && p.getProjectGroup().equals(projectGroup)) {
                                threeSum += p.getBorrowMoney();
                            }
                        }
                        if (threeSum != 0) {
                            ProjectMarketFeeCountBO bo = new ProjectMarketFeeCountBO();
                            bo.setFirstSubject(firstSubject);
                            bo.setSecondSubject(secondSubject);
                            bo.setThirdSubject(thirdSubject);
                            bo.setProjectGroup(projectGroup);
                            bo.setVoucherDate(times);
                            bo.setBorrowMoney(threeSum);
                            threeBos.add(bo);
                            threeSum = 0.00;   //置为0
                        }
                    }
                }
            }
        }
        return threeBos;
    }

    @Override
    public List<ProjectMarketFeeCountBO> areaCount(String startTime, String endTime) throws SerException {
        list(new ProjectMarketFeeDTO());
        LocalDate[] time = null;
        try {
            time = new LocalDate[]{DateUtil.parseDate(startTime), DateUtil.parseDate(endTime)};
        } catch (Exception e) {
            throw new SerException("日期格式错误");
        }
        ProjectMarketFeeDTO dto = new ProjectMarketFeeDTO();
        dto.getConditions().add(Restrict.between("voucherDate", time));
        List<ProjectMarketFee> all = super.findByCis(dto);
        Set<String> areas = allAreas();
        Set<String> firstSubjects = allFirstSubjects();
        Set<String> secondSubjects = allSecondSubjects();
        Set<String> thirdSubjects = allThirdSubjects();
        Double sum = 0.00;
        String times = startTime + "～" + endTime;
        List<ProjectMarketFeeCountBO> boList = new ArrayList<ProjectMarketFeeCountBO>();
        for (String firstSubject : firstSubjects) {
            for (String secondSubject : secondSubjects) {
                for (String thirdSubject : thirdSubjects) {
                    for (String area : areas) {
                        for (ProjectMarketFee p : all) {
                            if (p.getArea().equals(area) && p.getFirstSubject().equals(firstSubject) && p.getSecondSubject().equals(secondSubject) && p.getThirdSubject().equals(thirdSubject)) {
                                sum += p.getBorrowMoney();
                            }
                        }
                        if (sum != 0) {
                            ProjectMarketFeeCountBO bo = new ProjectMarketFeeCountBO();
                            bo.setFirstSubject(firstSubject);
                            bo.setSecondSubject(secondSubject);
                            bo.setThirdSubject(thirdSubject);
                            bo.setArea(area);
                            bo.setVoucherDate(times);
                            bo.setBorrowMoney(sum);
                            boList.add(bo);
                            sum = 0.00;   //置为0
                        }
                    }
                }
            }
        }
        return boList;
    }

    @Override
    public List<ProjectMarketFeeCountBO> projectGroupCount(String startTime, String endTime) throws SerException {
        list(new ProjectMarketFeeDTO());
        LocalDate[] time = null;
        try {
            time = new LocalDate[]{DateUtil.parseDate(startTime), DateUtil.parseDate(endTime)};
        } catch (Exception e) {
            throw new SerException("日期格式错误");
        }
        ProjectMarketFeeDTO dto = new ProjectMarketFeeDTO();
        dto.getConditions().add(Restrict.between("voucherDate", time));
        List<ProjectMarketFee> all = super.findByCis(dto);
        Set<String> areas = allAreas();
        Set<String> firstSubjects = allFirstSubjects();
        Set<String> secondSubjects = allSecondSubjects();
        Set<String> thirdSubjects = allThirdSubjects();
        Set<String> projectGroups = allProjectGroups();
        Double sum = 0.00;
        String times = startTime + "～" + endTime;
        List<ProjectMarketFeeCountBO> boList = new ArrayList<ProjectMarketFeeCountBO>();
        for (String firstSubject : firstSubjects) {
            for (String secondSubject : secondSubjects) {
                for (String thirdSubject : thirdSubjects) {
                    for (String projectGroup : projectGroups) {
                        for (String area : areas) {
                            for (ProjectMarketFee p : all) {
                                if (p.getArea().equals(area) && p.getFirstSubject().equals(firstSubject) && p.getSecondSubject().equals(secondSubject) && p.getThirdSubject().equals(thirdSubject) && p.getProjectGroup().equals(projectGroup)) {
                                    sum += p.getBorrowMoney();
                                }
                            }
                            if (sum != 0) {
                                ProjectMarketFeeCountBO bo = new ProjectMarketFeeCountBO();
                                bo.setFirstSubject(firstSubject);
                                bo.setSecondSubject(secondSubject);
                                bo.setThirdSubject(thirdSubject);
                                bo.setProjectGroup(projectGroup);
                                bo.setArea(area);
                                bo.setVoucherDate(times);
                                bo.setBorrowMoney(sum);
                                boList.add(bo);
                                sum = 0.00;   //置为0
                            }
                        }
                    }
                }
            }
        }
        return boList;
    }

    @Override
    public List<ProjectMarketFeeCountBO> projectNameCount(String startTime, String endTime) throws SerException {
        list(new ProjectMarketFeeDTO());
        LocalDate[] time = null;
        try {
            time = new LocalDate[]{DateUtil.parseDate(startTime), DateUtil.parseDate(endTime)};
        } catch (Exception e) {
            throw new SerException("日期格式错误");
        }
        ProjectMarketFeeDTO dto = new ProjectMarketFeeDTO();
        dto.getConditions().add(Restrict.between("voucherDate", time));
        List<ProjectMarketFee> all = super.findByCis(dto);
        Set<String> areas = allAreas();
        Set<String> firstSubjects = allFirstSubjects();
        Set<String> secondSubjects = allSecondSubjects();
        Set<String> thirdSubjects = allThirdSubjects();
        Set<String> projectGroups = allProjectGroups();
        Set<String> projectNames = allProjectNames();
        Double sum = 0.00;
        String times = startTime + "～" + endTime;
        List<ProjectMarketFeeCountBO> boList = new ArrayList<ProjectMarketFeeCountBO>();
        for (String firstSubject : firstSubjects) {
            for (String secondSubject : secondSubjects) {
                for (String thirdSubject : thirdSubjects) {
                    for (String projectGroup : projectGroups) {
                        for (String area : areas) {
                            for (String projectName : projectNames) {
                                for (ProjectMarketFee p : all) {
                                    if (p.getArea().equals(area) && p.getFirstSubject().equals(firstSubject) && p.getSecondSubject().equals(secondSubject) && p.getThirdSubject().equals(thirdSubject) && p.getProjectGroup().equals(projectGroup) && p.getProjectName().equals(projectName)) {
                                        sum += p.getBorrowMoney();
                                    }
                                }
                                if (sum != 0) {
                                    ProjectMarketFeeCountBO bo = new ProjectMarketFeeCountBO();
                                    bo.setFirstSubject(firstSubject);
                                    bo.setSecondSubject(secondSubject);
                                    bo.setThirdSubject(thirdSubject);
                                    bo.setProjectGroup(projectGroup);
                                    bo.setArea(area);
                                    bo.setProjectName(projectName);
                                    bo.setVoucherDate(times);
                                    bo.setBorrowMoney(sum);
                                    boList.add(bo);
                                    sum = 0.00;   //置为0
                                }
                            }
                        }
                    }
                }
            }
        }
        return boList;
    }

    @Override
    public ProjectMarketFeeCountBO count(String projectGroup, String area, Integer year, Integer month, String projectName) throws SerException {
        list(new ProjectMarketFeeDTO());
        ProjectMarketFeeDTO dto = new ProjectMarketFeeDTO();
        dto.getConditions().add(Restrict.eq("projectGroup", projectGroup));
        dto.getConditions().add(Restrict.eq("area", area));
        dto.getConditions().add(Restrict.eq("year", year));
        dto.getConditions().add(Restrict.eq("month", month));
        dto.getConditions().add(Restrict.eq("projectName", projectName));
        List<ProjectMarketFee> list = super.findByCis(dto);
        Double sum = 0.00;
        for (ProjectMarketFee p : list) {
            sum += p.getBorrowMoney();
        }
        ProjectMarketFeeCountBO bo = new ProjectMarketFeeCountBO();
        bo.setBorrowMoney(sum);
        return bo;
    }

    @Override
    public List<ProjectMarketFeeBO> findDetail(String firstSubject, String secondSubject, String thirdSubject, String area, String projectGroup, String projectName) throws SerException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id,firstSubject,secondSubject,thirdSubject,projectGroup,area,projectName,borrowMoney\n" +
                "from projectmarketfee_projectmarketfee");
        String[] fields = new String[]{"id", "firstSubject", "secondSubject", "thirdSubject", "projectGroup", "area", "projectName", "borrowMoney"};
        List<ProjectMarketFee> list = null;
        if ((!"null".equals(firstSubject)) && (!"null".equals(secondSubject)) && (!"null".equals(thirdSubject)) && (!"null".equals(area)) && (!"null".equals(projectGroup)) && (!"null".equals(projectName))) {
            String[] firstSubjects = new String[]{firstSubject};
            String[] secondSubjects = new String[]{secondSubject};
            String[] thirdSubjects = new String[]{thirdSubject};
            String[] areas = new String[]{area};
            String[] projectNames = new String[]{projectName};
            String[] projectGroups = new String[]{projectGroup};
            for (int i = 0; i < firstSubjects.length; i++) {
                sb.append(" where firstSubject='" + firstSubjects[i] + "' AND secondSubject='" + secondSubjects[i] + "' AND thirdSubject='" + thirdSubjects[i] + "'\n" +
                        "AND projectGroup='" + projectGroups[i] + "' AND area='" + areas[i] + "' AND projectName='" + projectNames[i] + "'");
                String sql = sb.toString();
                list = super.findBySql(sql, ProjectMarketFee.class, fields);
            }
            for (ProjectMarketFee p : list) {
                ProjectMarketFee p1 = super.findById(p.getId());
                p.setVoucherDate(p1.getVoucherDate());
            }
            return BeanTransform.copyProperties(list, ProjectMarketFeeBO.class);
        } else if ((!"null".equals(firstSubject)) && (!"null".equals(secondSubject)) && (!"null".equals(thirdSubject)) && (!"null".equals(area)) && (!"null".equals(projectGroup))) {
            String[] firstSubjects = new String[]{firstSubject};
            String[] secondSubjects = new String[]{secondSubject};
            String[] thirdSubjects = new String[]{thirdSubject};
            String[] areas = new String[]{area};
            String[] projectGroups = new String[]{projectGroup};
            for (int i = 0; i < firstSubjects.length; i++) {
                sb.append(" where firstSubject='" + firstSubjects[i] + "' AND secondSubject='" + secondSubjects[i] + "' AND thirdSubject='" + thirdSubjects[i] + "'\n" +
                        "AND projectGroup='" + projectGroups[i] + "' AND area='" + areas[i] + "'");
                String sql = sb.toString();
                list = super.findBySql(sql, ProjectMarketFee.class, fields);
            }
            for (ProjectMarketFee p : list) {
                ProjectMarketFee p1 = super.findById(p.getId());
                p.setVoucherDate(p1.getVoucherDate());
            }
            return BeanTransform.copyProperties(list, ProjectMarketFeeBO.class);
        } else if ((!"null".equals(firstSubject)) && (!"null".equals(secondSubject)) && (!"null".equals(thirdSubject)) && (!"null".equals(area))) {
            String[] firstSubjects = new String[]{firstSubject};
            String[] secondSubjects = new String[]{secondSubject};
            String[] thirdSubjects = new String[]{thirdSubject};
            String[] areas = new String[]{area};
            for (int i = 0; i < firstSubjects.length; i++) {
                sb.append(" where firstSubject='" + firstSubjects[i] + "' AND secondSubject='" + secondSubjects[i] + "' AND thirdSubject='" + thirdSubjects[i] + "'\n" +
                        "AND area='" + areas[i] + "'");
                String sql = sb.toString();
                list = super.findBySql(sql, ProjectMarketFee.class, fields);
            }
            for (ProjectMarketFee p : list) {
                ProjectMarketFee p1 = super.findById(p.getId());
                p.setVoucherDate(p1.getVoucherDate());
            }
            return BeanTransform.copyProperties(list, ProjectMarketFeeBO.class);
        } else if ((!"null".equals(firstSubject)) && (!"null".equals(secondSubject)) && (!"null".equals(thirdSubject)) && (!"null".equals(projectGroup))) {
            String[] firstSubjects = new String[]{firstSubject};
            String[] secondSubjects = new String[]{secondSubject};
            String[] thirdSubjects = new String[]{thirdSubject};
            String[] projectGroups = new String[]{projectGroup};
            for (int i = 0; i < firstSubjects.length; i++) {
                sb.append(" where firstSubject='" + firstSubjects[i] + "' AND secondSubject='" + secondSubjects[i] + "' AND thirdSubject='" + thirdSubjects[i] + "'\n" +
                        "AND projectGroup='" + projectGroups[i] + "'");
                String sql = sb.toString();
                list = super.findBySql(sql, ProjectMarketFee.class, fields);
            }
            for (ProjectMarketFee p : list) {
                ProjectMarketFee p1 = super.findById(p.getId());
                p.setVoucherDate(p1.getVoucherDate());
            }
            return BeanTransform.copyProperties(list, ProjectMarketFeeBO.class);
        } else if ((!"null".equals(firstSubject)) && (!"null".equals(secondSubject)) && (!"null".equals(projectGroup))) {
            String[] firstSubjects = new String[]{firstSubject};
            String[] secondSubjects = new String[]{secondSubject};
            String[] projectGroups = new String[]{projectGroup};
            for (int i = 0; i < firstSubjects.length; i++) {
                sb.append(" where firstSubject='" + firstSubjects[i] + "' AND secondSubject='" + secondSubjects[i] + "'\n" +
                        "AND projectGroup='" + projectGroups[i] + "'");
                String sql = sb.toString();
                list = super.findBySql(sql, ProjectMarketFee.class, fields);
            }
            for (ProjectMarketFee p : list) {
                ProjectMarketFee p1 = super.findById(p.getId());
                p.setVoucherDate(p1.getVoucherDate());
            }
            return BeanTransform.copyProperties(list, ProjectMarketFeeBO.class);
        } else if ((!"null".equals(firstSubject)) && (!"null".equals(projectGroup))) {
            String[] firstSubjects = new String[]{firstSubject};
            String[] projectGroups = new String[]{projectGroup};
            for (int i = 0; i < firstSubjects.length && i < projectGroups.length; i++) {
                sb.append(" where firstSubject='" + firstSubjects[i] + "' AND projectGroup='" + projectGroups[i] + "'");
                String sql = sb.toString();
                list = super.findBySql(sql, ProjectMarketFee.class, fields);
            }
            for (ProjectMarketFee p : list) {
                ProjectMarketFee p1 = super.findById(p.getId());
                p.setVoucherDate(p1.getVoucherDate());
            }
            return BeanTransform.copyProperties(list, ProjectMarketFeeBO.class);
        }
        return null;
    }

    /**
     * 查找所有一级科目
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allFirstSubjects() throws SerException {
        List<ProjectMarketFee> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (ProjectMarketFee p : list) {
            set.add(p.getFirstSubject());
        }
        return set;
    }

    /**
     * 查找所有二级科目
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allSecondSubjects() throws SerException {
        List<ProjectMarketFee> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (ProjectMarketFee p : list) {
            set.add(p.getSecondSubject());
        }
        return set;
    }

    /**
     * 查找所有三级科目
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allThirdSubjects() throws SerException {
        List<ProjectMarketFee> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (ProjectMarketFee p : list) {
            set.add(p.getThirdSubject());
        }
        return set;
    }

    /**
     * 查找所有地区
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allAreas() throws SerException {
        List<ProjectMarketFee> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (ProjectMarketFee p : list) {
            set.add(p.getArea());
        }
        return set;
    }

    /**
     * 查找所有项目组
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allProjectGroups() throws SerException {
        List<ProjectMarketFee> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (ProjectMarketFee p : list) {
            set.add(p.getProjectGroup());
        }
        return set;
    }

    /**
     * 查找所有项目名称
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allProjectNames() throws SerException {
        List<ProjectMarketFee> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (ProjectMarketFee p : list) {
            set.add(p.getProjectName());
        }
        return set;
    }

//    /**
//     * 查找所有年份
//     *
//     * @return class Integer
//     * @throws SerException
//     */
//    private Set<Integer> allYears() throws SerException {
//        List<ProjectMarketFee> list = super.findAll();
//        Set<Integer> set = new HashSet<Integer>();
//        for (ProjectMarketFee p : list) {
//            set.add(p.getYear());
//        }
//        return set;
//    }
//
//    /**
//     * 查找所有月份
//     *
//     * @return class Integer
//     * @throws SerException
//     */
//    private Set<Integer> allMonths() throws SerException {
//        List<ProjectMarketFee> list = super.findAll();
//        Set<Integer> set = new HashSet<Integer>();
//        for (ProjectMarketFee p : list) {
//            set.add(p.getMonth());
//        }
//        return set;
//    }

    /**
     * 通过销售费用id查找记账凭证信息
     *
     * @param id 记账凭证id
     * @return class VoucherGenerate
     * @throws SerException
     */
    private VoucherGenerate find(String id) throws SerException {
        List<VoucherGenerate> list = null;
        String[] s = new String[]{id};
        for (String i : s) {
            String sql = "SELECT firstSubject,secondSubject,thirdSubject,area,projectGroup,projectName,borrowMoney\n" +
                    "from voucher_vouchergenerate\n" +
                    "where id='" + i + "'";
            String[] fields = new String[]{"firstSubject", "secondSubject", "thirdSubject", "area", "projectGroup", "projectName", "borrowMoney"};
            list = super.findBySql(sql, VoucherGenerate.class, fields);
        }
        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }
}