package com.bjike.goddess.courier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.courier.bo.CourierBO;
import com.bjike.goddess.courier.bo.CourierCompanyBO;
import com.bjike.goddess.courier.bo.CourierCountBO;
import com.bjike.goddess.courier.dto.CourierDTO;
import com.bjike.goddess.courier.entity.Courier;
import com.bjike.goddess.courier.entity.CourierCompany;
import com.bjike.goddess.courier.to.CourierTO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.entity.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

/**
 * 快递收发业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-28 10:25 ]
 * @Description: [ 快递收发业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "courierSerCache")
@Service
public class CourierSerImpl extends ServiceImpl<Courier, CourierDTO> implements CourierSer {
    @Autowired
    private CourierCompanySer courierCompanySer;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public CourierBO save(CourierTO to) throws SerException {
        Courier courier = BeanTransform.copyProperties(to, Courier.class, true);
        String sendTime = courier.getSendTime();
        LocalDateTime localDateTime = DateUtil.parseDateTime(sendTime);
        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        courier.setYear(year);
        courier.setMonth(month);
        super.save(courier);
        if (!courier.getIsConfirm()) {
            //TODO:发邮件给寄件人
        }
        return BeanTransform.copyProperties(courier, CourierBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public CourierBO edit(CourierTO to) throws SerException {
        Courier courier = super.findById(to.getId());
        courier = BeanTransform.copyProperties(to, Courier.class, true);
        super.update(courier);
        if (!courier.getIsConfirm()) {
            //TODO:发邮件给寄件人
        }
        return BeanTransform.copyProperties(courier, CourierBO.class);
    }

    @Override
    public List<CourierBO> list(CourierDTO dto) throws SerException {
        List<Courier> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, CourierBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public CourierBO findByID(String id) throws SerException {
        Courier courier = super.findById(id);
        return BeanTransform.copyProperties(courier, CourierBO.class);
    }

    @Override
    public List<String> findNameByDepartment(String[] departmentName) throws SerException {
        List<UserDetail> list = null;
        List<String> l = new ArrayList<String>();
        for (String s : departmentName) {
            String sql = "select user_detail.realName from user_detail INNER JOIN user_department\n" +
                    "where user_detail.department_id=user_department.id\n" +
                    "and user_department.name='" + s + "'";
            list = super.findBySql(sql, UserDetail.class, new String[]{"realName"});
        }
        for (UserDetail u : list) {
            l.add(u.getRealName());
        }
        return l;
    }

    @Override
    public List<String> findNameByGroup(String[] groupName) throws SerException {
        List<UserDetail> list = null;
        List<String> l = new ArrayList<String>();
        for (String s : groupName) {
            String sql = "SELECT user_detail.realName FROM user_detail INNER JOIN user_group\n" +
                    "where user_detail.group_id=user_group.id\n" +
                    "and user_group.name='" + s + "'";
            list = super.findBySql(sql, UserDetail.class, new String[]{"realName"});
        }
        for (UserDetail u : list) {
            l.add(u.getRealName());
        }
        return l;
    }

    @Override
    public Double findRemainSum() throws SerException {
        List<String> l = new ArrayList<String>();
        List<Courier> couriers = null;
        String sql = "SELECT max(sendTime) from courier_courier";
        List<Courier> courierss = super.findBySql(sql, Courier.class, new String[]{"sendTime"});
        for (Courier courier : courierss) {
            l.add(courier.getSendTime());
        }
        for (String s : l) {
            String sql1 = "SELECT remainingSum from courier_courier\n" +
                    "where sendTime='" + s + "'";
            couriers = super.findBySql(sql1, Courier.class, new String[]{"remainingSum"});
        }
        return couriers.get(0).getFeeSum();
    }

    /**
     * 日汇总
     *
     * @param arrival        是否汇地区
     * @param sendTime       汇总的日期
     * @param courierCompany 是否汇快递公司
     * @param department     是否汇部门
     * @return class CourierCountVO
     * @throws SerException
     */
    @Override
    public List<CourierCountBO> dayCount(boolean arrival, String sendTime, boolean courierCompany, boolean department) throws SerException {
        List<CourierCountBO> boList = new ArrayList<CourierCountBO>(0);
        List<String> arrivals = findAllArrivals();
        List<String> departments = findAllDepartments();
        List<String> companys = findAllCompanys();
        List<Courier> list = null;
        Integer count = 0;
        double sum = 0;

        /**
         * 地区部门快递公司日汇总
         */
        if (arrival && department && courierCompany) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.like("sendTime", sendTime));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (String d : departments) {
                    for (String c : companys) {
                        for (Courier courier : list) {
                            if (a.equals(courier.getArrival()) && d.equals(courier.getDepartment()) && c.equals(courier.getCourierCompany())) {
                                count++;
                                sum += courier.getFeeSum();
                            }
                        }
                        CourierCountBO courierCountBO = new CourierCountBO();
                        courierCountBO.setArrival(a);
                        courierCountBO.setDepartment(d);
                        courierCountBO.setCourierCompany(c);
                        courierCountBO.setCountNum(count);
                        courierCountBO.setTotal(sum);
                        boList.add(courierCountBO);
                        count = 0;
                        sum = 0;     //置为0
                    }
                }
            }
            return boList;
        }

        /**
         * 地区快递公司日汇总
         */
        if (arrival && courierCompany) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.like("sendTime", sendTime));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (String c : companys) {
                    for (Courier courier : list) {
                        if (a.equals(courier.getArrival()) && c.equals(courier.getCourierCompany())) {
                            count++;
                            sum += courier.getFeeSum();
                        }
                    }
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setArrival(a);
                    courierCountBO.setCourierCompany(c);
                    courierCountBO.setCountNum(count);
                    courierCountBO.setTotal(sum);
                    boList.add(courierCountBO);
                    count = 0;
                    sum = 0;     //置为0
                }
            }
            return boList;
        }

        /**
         * 地区部门日汇总
         */
        if (arrival && department) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.like("sendTime", sendTime));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (String d : departments) {
                    for (Courier courier : list) {
                        if (a.equals(courier.getArrival()) && d.equals(courier.getDepartment())) {
                            count++;
                            sum += courier.getFeeSum();
                        }
                    }
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setArrival(a);
                    courierCountBO.setDepartment(d);
                    courierCountBO.setCountNum(count);
                    courierCountBO.setTotal(sum);
                    boList.add(courierCountBO);
                    count = 0;
                    sum = 0;     //置为0
                }
            }
            return boList;
        }

        /**
         * 部门快递公司日汇总
         */
        if (department && courierCompany) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.like("sendTime", sendTime));
            list = super.findByCis(dto);
            for (String d : departments) {
                for (String c : companys) {
                    for (Courier courier : list) {
                        if (d.equals(courier.getDepartment()) && c.equals(courier.getCourierCompany())) {
                            count++;
                            sum += courier.getFeeSum();
                        }
                    }
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setCourierCompany(c);
                    courierCountBO.setDepartment(d);
                    courierCountBO.setCountNum(count);
                    courierCountBO.setTotal(sum);
                    boList.add(courierCountBO);
                    count = 0;
                    sum = 0;     //置为0
                }
            }
            return boList;
        }

        /**
         * 地区日汇总
         */
        if (arrival) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.like("sendTime", sendTime));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (Courier courier : list) {
                    if (a.equals(courier.getArrival())) {
                        count++;
                        sum += courier.getFeeSum();
                    }
                }
                CourierCountBO courierCountBO = new CourierCountBO();
                courierCountBO.setArrival(a);
                courierCountBO.setCountNum(count);
                courierCountBO.setTotal(sum);
                boList.add(courierCountBO);
                count = 0;
                sum = 0;     //置为0
            }
            return boList;
        }

        /**
         * 部门日汇总
         */
        if (department) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.like("sendTime", sendTime));
            list = super.findByCis(dto);
            for (String d : departments) {
                for (Courier courier : list) {
                    if (d.equals(courier.getDepartment())) {
                        count++;
                        sum += courier.getFeeSum();
                    }
                }
                CourierCountBO courierCountBO = new CourierCountBO();
                courierCountBO.setDepartment(d);
                courierCountBO.setCountNum(count);
                courierCountBO.setTotal(sum);
                boList.add(courierCountBO);
                count = 0;
                sum = 0;     //置为0
            }
            return boList;
        }

        /**
         * 快递公司日汇总
         */
        if (courierCompany) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.like("sendTime", sendTime));
            list = super.findByCis(dto);
            for (String c : companys) {
                for (Courier courier : list) {
                    if (c.equals(courier.getCourierCompany())) {
                        count++;
                        sum += courier.getFeeSum();
                    }
                }
                CourierCountBO courierCountBO = new CourierCountBO();
                courierCountBO.setCourierCompany(c);
                courierCountBO.setCountNum(count);
                courierCountBO.setTotal(sum);
                boList.add(courierCountBO);
                count = 0;
                sum = 0;     //置为0
            }
            return boList;
        }

        /**
         * 日汇总
         */
        CourierDTO dto = new CourierDTO();
        dto.getConditions().add(Restrict.like("sendTime", sendTime));
        list = super.findByCis(dto);
        for (Courier courier : list) {
            count++;
            sum += courier.getFeeSum();
        }
        CourierCountBO courierCountBO = new CourierCountBO();
        courierCountBO.setCountNum(count);
        courierCountBO.setTotal(sum);
        boList.add(courierCountBO);
        return boList;
    }

    /**
     * 周汇总
     *
     * @param arrival        是否回地区
     * @param courierCompany 是否汇快递公司
     * @param department     是否汇部门
     * @return class CourierCountVO
     * @throws SerException
     */
    @Override
    public List<CourierCountBO> weekCount(boolean arrival, boolean courierCompany, boolean department, boolean lastWeek) throws SerException {
        LocalDateTime now = LocalDateTime.now();
        LocalDate startTime = null;
        LocalDate endTime = null;
        int day = now.getDayOfWeek().getValue();
        if (day == 1) {
            startTime = now.toLocalDate();
            endTime = now.plusDays(6).toLocalDate();
        } else if (day == 2) {
            startTime = now.minusDays(1).toLocalDate();
            endTime = now.plusDays(5).toLocalDate();
        } else if (day == 3) {
            startTime = now.minusDays(2).toLocalDate();
            endTime = now.plusDays(4).toLocalDate();
        } else if (day == 4) {
            startTime = now.minusDays(3).toLocalDate();
            endTime = now.plusDays(3).toLocalDate();
        } else if (day == 5) {
            startTime = now.minusDays(4).toLocalDate();
            endTime = now.plusDays(2).toLocalDate();
        } else if (day == 6) {
            startTime = now.minusDays(5).toLocalDate();
            endTime = now.plusDays(1).toLocalDate();
        } else {
            startTime = now.minusDays(6).toLocalDate();
            endTime = now.toLocalDate();
        }
        LocalDate[] time = new LocalDate[]{startTime, endTime};
        List<CourierCountBO> boList = new ArrayList<CourierCountBO>(0);
        List<String> arrivals = findAllArrivals();
        List<String> departments = findAllDepartments();
        List<String> companys = findAllCompanys();
        List<Courier> list = null;
        Integer count = 0;
        double sum = 0;

        /**
         * 地区部门快递公司上周汇总
         */
        if (arrival && department && courierCompany && lastWeek) {
            CourierDTO dto = new CourierDTO();
            startTime = startTime.minusDays(7);
            endTime = endTime.minusDays(7);
            time = new LocalDate[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("sendTime", time));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (String d : departments) {
                    for (String c : companys) {
                        for (Courier courier : list) {
                            if (a.equals(courier.getArrival()) && d.equals(courier.getDepartment()) && c.equals(courier.getCourierCompany())) {
                                count++;
                                sum += courier.getFeeSum();
                            }
                        }
                        CourierCountBO courierCountBO = new CourierCountBO();
                        courierCountBO.setArrival(a);
                        courierCountBO.setDepartment(d);
                        courierCountBO.setCourierCompany(c);
                        courierCountBO.setCountNum(count);
                        courierCountBO.setTotal(sum);
                        boList.add(courierCountBO);
                        count = 0;
                        sum = 0;     //置为0
                    }
                }
            }
            return boList;
        }

        /**
         * 地区快递公司上周汇总
         */
        if (arrival && courierCompany && lastWeek) {
            CourierDTO dto = new CourierDTO();
            startTime = startTime.minusDays(7);
            endTime = endTime.minusDays(7);
            time = new LocalDate[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("sendTime", time));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (String c : companys) {
                    for (Courier courier : list) {
                        if (a.equals(courier.getArrival()) && c.equals(courier.getCourierCompany())) {
                            count++;
                            sum += courier.getFeeSum();
                        }
                    }
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setArrival(a);
                    courierCountBO.setCourierCompany(c);
                    courierCountBO.setCountNum(count);
                    courierCountBO.setTotal(sum);
                    boList.add(courierCountBO);
                    count = 0;
                    sum = 0;     //置为0
                }
            }
            return boList;
        }

        /**
         * 地区部门上周汇总
         */
        if (arrival && department && lastWeek) {
            CourierDTO dto = new CourierDTO();
            startTime = startTime.minusDays(7);
            endTime = endTime.minusDays(7);
            time = new LocalDate[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("sendTime", time));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (String d : departments) {
                    for (Courier courier : list) {
                        if (a.equals(courier.getArrival()) && d.equals(courier.getDepartment())) {
                            count++;
                            sum += courier.getFeeSum();
                        }
                    }
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setArrival(a);
                    courierCountBO.setDepartment(d);
                    courierCountBO.setCountNum(count);
                    courierCountBO.setTotal(sum);
                    boList.add(courierCountBO);
                    count = 0;
                    sum = 0;     //置为0
                }
            }
            return boList;
        }

        /**
         * 部门快递公司上周汇总
         */
        if (department && courierCompany && lastWeek) {
            CourierDTO dto = new CourierDTO();
            startTime = startTime.minusDays(7);
            endTime = endTime.minusDays(7);
            time = new LocalDate[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("sendTime", time));
            list = super.findByCis(dto);
            for (String d : departments) {
                for (String c : companys) {
                    for (Courier courier : list) {
                        if (d.equals(courier.getDepartment()) && c.equals(courier.getCourierCompany())) {
                            count++;
                            sum += courier.getFeeSum();
                        }
                    }
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setCourierCompany(c);
                    courierCountBO.setDepartment(d);
                    courierCountBO.setCountNum(count);
                    courierCountBO.setTotal(sum);
                    boList.add(courierCountBO);
                    count = 0;
                    sum = 0;     //置为0
                }
            }
            return boList;
        }

        /**
         * 地区上周汇总
         */
        if (arrival && lastWeek) {
            CourierDTO dto = new CourierDTO();
            startTime = startTime.minusDays(7);
            endTime = endTime.minusDays(7);
            time = new LocalDate[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("sendTime", time));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (Courier courier : list) {
                    if (a.equals(courier.getArrival())) {
                        count++;
                        sum += courier.getFeeSum();
                    }
                }
                CourierCountBO courierCountBO = new CourierCountBO();
                courierCountBO.setArrival(a);
                courierCountBO.setCountNum(count);
                courierCountBO.setTotal(sum);
                boList.add(courierCountBO);
                count = 0;
                sum = 0;     //置为0
            }
            return boList;
        }

        /**
         * 部门上周汇总
         */
        if (department && lastWeek) {
            CourierDTO dto = new CourierDTO();
            startTime = startTime.minusDays(7);
            endTime = endTime.minusDays(7);
            time = new LocalDate[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("sendTime", time));
            list = super.findByCis(dto);
            for (String d : departments) {
                for (Courier courier : list) {
                    if (d.equals(courier.getDepartment())) {
                        count++;
                        sum += courier.getFeeSum();
                    }
                }
                CourierCountBO courierCountBO = new CourierCountBO();
                courierCountBO.setDepartment(d);
                courierCountBO.setCountNum(count);
                courierCountBO.setTotal(sum);
                boList.add(courierCountBO);
                count = 0;
                sum = 0;     //置为0
            }
            return boList;
        }

        /**
         * 快递公司上周汇总
         */
        if (courierCompany && lastWeek) {
            CourierDTO dto = new CourierDTO();
            startTime = startTime.minusDays(7);
            endTime = endTime.minusDays(7);
            time = new LocalDate[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("sendTime", time));
            list = super.findByCis(dto);
            for (String c : companys) {
                for (Courier courier : list) {
                    if (c.equals(courier.getCourierCompany())) {
                        count++;
                        sum += courier.getFeeSum();
                    }
                }
                CourierCountBO courierCountBO = new CourierCountBO();
                courierCountBO.setCourierCompany(c);
                courierCountBO.setCountNum(count);
                courierCountBO.setTotal(sum);
                boList.add(courierCountBO);
                count = 0;
                sum = 0;     //置为0
            }
            return boList;
        }

        /**
         * 上周汇总
         */
        if (lastWeek) {
            CourierDTO dto = new CourierDTO();
            startTime = startTime.minusDays(7);
            endTime = endTime.minusDays(7);
            time = new LocalDate[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("sendTime", time));
            list = super.findByCis(dto);
            for (Courier courier : list) {
                count++;
                sum += courier.getFeeSum();
            }
            CourierCountBO courierCountBO = new CourierCountBO();
            courierCountBO.setCountNum(count);
            courierCountBO.setTotal(sum);
            boList.add(courierCountBO);
            return boList;
        }

        /**
         * 地区部门快递公司周汇总
         */
        if (arrival && department && courierCompany) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.between("sendTime", time));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (String d : departments) {
                    for (String c : companys) {
                        for (Courier courier : list) {
                            if (a.equals(courier.getArrival()) && d.equals(courier.getDepartment()) && c.equals(courier.getCourierCompany())) {
                                count++;
                                sum += courier.getFeeSum();
                            }
                        }
                        CourierCountBO courierCountBO = new CourierCountBO();
                        courierCountBO.setArrival(a);
                        courierCountBO.setDepartment(d);
                        courierCountBO.setCourierCompany(c);
                        courierCountBO.setCountNum(count);
                        courierCountBO.setTotal(sum);
                        boList.add(courierCountBO);
                        count = 0;
                        sum = 0;     //置为0
                    }
                }
            }
            return boList;
        }

        /**
         * 地区快递公司周汇总
         */
        if (arrival && courierCompany) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.between("sendTime", time));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (String c : companys) {
                    for (Courier courier : list) {
                        if (a.equals(courier.getArrival()) && c.equals(courier.getCourierCompany())) {
                            count++;
                            sum += courier.getFeeSum();
                        }
                    }
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setArrival(a);
                    courierCountBO.setCourierCompany(c);
                    courierCountBO.setCountNum(count);
                    courierCountBO.setTotal(sum);
                    boList.add(courierCountBO);
                    count = 0;
                    sum = 0;     //置为0
                }
            }
            return boList;
        }

        /**
         * 地区部门周汇总
         */
        if (arrival && department) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.between("sendTime", time));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (String d : departments) {
                    for (Courier courier : list) {
                        if (a.equals(courier.getArrival()) && d.equals(courier.getDepartment())) {
                            count++;
                            sum += courier.getFeeSum();
                        }
                    }
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setArrival(a);
                    courierCountBO.setDepartment(d);
                    courierCountBO.setCountNum(count);
                    courierCountBO.setTotal(sum);
                    boList.add(courierCountBO);
                    count = 0;
                    sum = 0;     //置为0
                }
            }
            return boList;
        }

        /**
         * 部门快递公司周汇总
         */
        if (department && courierCompany) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.between("sendTime", time));
            list = super.findByCis(dto);
            for (String d : departments) {
                for (String c : companys) {
                    for (Courier courier : list) {
                        if (d.equals(courier.getDepartment()) && c.equals(courier.getCourierCompany())) {
                            count++;
                            sum += courier.getFeeSum();
                        }
                    }
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setCourierCompany(c);
                    courierCountBO.setDepartment(d);
                    courierCountBO.setCountNum(count);
                    courierCountBO.setTotal(sum);
                    boList.add(courierCountBO);
                    count = 0;
                    sum = 0;     //置为0
                }
            }
            return boList;
        }

        /**
         * 地区周汇总
         */
        if (arrival) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.between("sendTime", time));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (Courier courier : list) {
                    if (a.equals(courier.getArrival())) {
                        count++;
                        sum += courier.getFeeSum();
                    }
                }
                CourierCountBO courierCountBO = new CourierCountBO();
                courierCountBO.setArrival(a);
                courierCountBO.setCountNum(count);
                courierCountBO.setTotal(sum);
                boList.add(courierCountBO);
                count = 0;
                sum = 0;     //置为0
            }
            return boList;
        }

        /**
         * 部门周汇总
         */
        if (department) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.between("sendTime", time));
            list = super.findByCis(dto);
            for (String d : departments) {
                for (Courier courier : list) {
                    if (d.equals(courier.getDepartment())) {
                        count++;
                        sum += courier.getFeeSum();
                    }
                }
                CourierCountBO courierCountBO = new CourierCountBO();
                courierCountBO.setDepartment(d);
                courierCountBO.setCountNum(count);
                courierCountBO.setTotal(sum);
                boList.add(courierCountBO);
                count = 0;
                sum = 0;     //置为0
            }
            return boList;
        }

        /**
         * 快递公司周汇总
         */
        if (courierCompany) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.between("sendTime", time));
            list = super.findByCis(dto);
            for (String c : companys) {
                for (Courier courier : list) {
                    if (c.equals(courier.getCourierCompany())) {
                        count++;
                        sum += courier.getFeeSum();
                    }
                }
                CourierCountBO courierCountBO = new CourierCountBO();
                courierCountBO.setCourierCompany(c);
                courierCountBO.setCountNum(count);
                courierCountBO.setTotal(sum);
                boList.add(courierCountBO);
                count = 0;
                sum = 0;     //置为0
            }
            return boList;
        }

        /**
         * 周汇总
         */
        CourierDTO dto = new CourierDTO();
        dto.getConditions().add(Restrict.between("sendTime", time));
        list = super.findByCis(dto);
        for (Courier courier : list) {
            count++;
            sum += courier.getFeeSum();
        }
        CourierCountBO courierCountBO = new CourierCountBO();
        courierCountBO.setCountNum(count);
        courierCountBO.setTotal(sum);
        boList.add(courierCountBO);
        return boList;
    }

    /**
     * 月汇总
     *
     * @param arrival        是否汇地区
     * @param courierCompany 是否汇快递公司
     * @param department     是否汇部门
     * @param month          汇总的月份
     * @return class CourierCountVO
     * @throws SerException
     */
    @Override
    public List<CourierCountBO> monthCount(boolean arrival, boolean courierCompany, boolean department, Integer month) throws SerException {
        LocalDateTime now = LocalDateTime.now();
        Integer year = now.getYear();
        List<CourierCountBO> boList = new ArrayList<CourierCountBO>(0);
        List<String> arrivals = findAllArrivals();
        List<String> departments = findAllDepartments();
        List<String> companys = findAllCompanys();
        List<Courier> list = null;
        Integer count = 0;
        double sum = 0;

        /**
         * 地区部门快递公司月汇总
         */
        if (arrival && department && courierCompany) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.eq("year", year));
            dto.getConditions().add(Restrict.eq("month", month));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (String d : departments) {
                    for (String c : companys) {
                        for (Courier courier : list) {
                            if (a.equals(courier.getArrival()) && d.equals(courier.getDepartment()) && c.equals(courier.getCourierCompany())) {
                                count++;
                                sum += courier.getFeeSum();
                            }
                        }
                        CourierCountBO courierCountBO = new CourierCountBO();
                        courierCountBO.setArrival(a);
                        courierCountBO.setDepartment(d);
                        courierCountBO.setCourierCompany(c);
                        courierCountBO.setCountNum(count);
                        courierCountBO.setTotal(sum);
                        boList.add(courierCountBO);
                        count = 0;
                        sum = 0;     //置为0
                    }
                }
            }
            return boList;
        }

        /**
         * 地区快递公司月汇总
         */
        if (arrival && courierCompany) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.eq("year", year));
            dto.getConditions().add(Restrict.eq("month", month));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (String c : companys) {
                    for (Courier courier : list) {
                        if (a.equals(courier.getArrival()) && c.equals(courier.getCourierCompany())) {
                            count++;
                            sum += courier.getFeeSum();
                        }
                    }
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setArrival(a);
                    courierCountBO.setCourierCompany(c);
                    courierCountBO.setCountNum(count);
                    courierCountBO.setTotal(sum);
                    boList.add(courierCountBO);
                    count = 0;
                    sum = 0;     //置为0
                }
            }
            return boList;
        }

        /**
         * 地区部门月汇总
         */
        if (arrival && department) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.eq("year", year));
            dto.getConditions().add(Restrict.eq("month", month));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (String d : departments) {
                    for (Courier courier : list) {
                        if (a.equals(courier.getArrival()) && d.equals(courier.getDepartment())) {
                            count++;
                            sum += courier.getFeeSum();
                        }
                    }
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setArrival(a);
                    courierCountBO.setDepartment(d);
                    courierCountBO.setCountNum(count);
                    courierCountBO.setTotal(sum);
                    boList.add(courierCountBO);
                    count = 0;
                    sum = 0;     //置为0
                }
            }
            return boList;
        }

        /**
         * 部门快递公司月汇总
         */
        if (department && courierCompany) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.eq("year", year));
            dto.getConditions().add(Restrict.eq("month", month));
            list = super.findByCis(dto);
            for (String d : departments) {
                for (String c : companys) {
                    for (Courier courier : list) {
                        if (d.equals(courier.getDepartment()) && c.equals(courier.getCourierCompany())) {
                            count++;
                            sum += courier.getFeeSum();
                        }
                    }
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setCourierCompany(c);
                    courierCountBO.setDepartment(d);
                    courierCountBO.setCountNum(count);
                    courierCountBO.setTotal(sum);
                    boList.add(courierCountBO);
                    count = 0;
                    sum = 0;     //置为0
                }
            }
            return boList;
        }

        /**
         * 地区月汇总
         */
        if (arrival) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.eq("year", year));
            dto.getConditions().add(Restrict.eq("month", month));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (Courier courier : list) {
                    if (a.equals(courier.getArrival())) {
                        count++;
                        sum += courier.getFeeSum();
                    }
                }
                CourierCountBO courierCountBO = new CourierCountBO();
                courierCountBO.setArrival(a);
                courierCountBO.setCountNum(count);
                courierCountBO.setTotal(sum);
                boList.add(courierCountBO);
                count = 0;
                sum = 0;     //置为0
            }
            return boList;
        }

        /**
         * 部门月汇总
         */
        if (department) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.eq("year", year));
            dto.getConditions().add(Restrict.eq("month", month));
            list = super.findByCis(dto);
            for (String d : departments) {
                for (Courier courier : list) {
                    if (d.equals(courier.getDepartment())) {
                        count++;
                        sum += courier.getFeeSum();
                    }
                }
                CourierCountBO courierCountBO = new CourierCountBO();
                courierCountBO.setDepartment(d);
                courierCountBO.setCountNum(count);
                courierCountBO.setTotal(sum);
                boList.add(courierCountBO);
                count = 0;
                sum = 0;     //置为0
            }
            return boList;
        }

        /**
         * 快递公司月汇总
         */
        if (courierCompany) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.eq("year", year));
            dto.getConditions().add(Restrict.eq("month", month));
            list = super.findByCis(dto);
            for (String c : companys) {
                for (Courier courier : list) {
                    if (c.equals(courier.getCourierCompany())) {
                        count++;
                        sum += courier.getFeeSum();
                    }
                }
                CourierCountBO courierCountBO = new CourierCountBO();
                courierCountBO.setCourierCompany(c);
                courierCountBO.setCountNum(count);
                courierCountBO.setTotal(sum);
                boList.add(courierCountBO);
                count = 0;
                sum = 0;     //置为0
            }
            return boList;
        }

        /**
         * 月汇总
         */
        CourierDTO dto = new CourierDTO();
        dto.getConditions().add(Restrict.eq("year", year));
        dto.getConditions().add(Restrict.eq("month", month));
        list = super.findByCis(dto);
        for (Courier courier : list) {
            count++;
            sum += courier.getFeeSum();
        }
        CourierCountBO courierCountBO = new CourierCountBO();
        courierCountBO.setCountNum(count);
        courierCountBO.setTotal(sum);
        boList.add(courierCountBO);
        return boList;
    }

    /**
     * 年汇总
     *
     * @param arrival        是否汇地区
     * @param courierCompany 是否汇快递公司
     * @param department     是否汇部门
     * @param year           汇总的年份
     * @return class CourierCountVO
     * @throws SerException
     */
    @Override
    public List<CourierCountBO> yearCount(boolean arrival, boolean courierCompany, boolean department, Integer year) throws SerException {
        List<CourierCountBO> boList = new ArrayList<CourierCountBO>(0);
        List<String> arrivals = findAllArrivals();
        List<String> departments = findAllDepartments();
        List<String> companys = findAllCompanys();
        List<Courier> list = null;
        Integer count = 0;
        double sum = 0;

        /**
         * 地区部门快递公司年汇总
         */
        if (arrival && department && courierCompany) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.eq("year", year));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (String d : departments) {
                    for (String c : companys) {
                        for (Courier courier : list) {
                            if (a.equals(courier.getArrival()) && d.equals(courier.getDepartment()) && c.equals(courier.getCourierCompany())) {
                                count++;
                                sum += courier.getFeeSum();
                            }
                        }
                        CourierCountBO courierCountBO = new CourierCountBO();
                        courierCountBO.setArrival(a);
                        courierCountBO.setDepartment(d);
                        courierCountBO.setCourierCompany(c);
                        courierCountBO.setCountNum(count);
                        courierCountBO.setTotal(sum);
                        boList.add(courierCountBO);
                        count = 0;
                        sum = 0;     //置为0
                    }
                }
            }
            return boList;
        }

        /**
         * 地区快递公司年汇总
         */
        if (arrival && courierCompany) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.eq("year", year));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (String c : companys) {
                    for (Courier courier : list) {
                        if (a.equals(courier.getArrival()) && c.equals(courier.getCourierCompany())) {
                            count++;
                            sum += courier.getFeeSum();
                        }
                    }
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setArrival(a);
                    courierCountBO.setCourierCompany(c);
                    courierCountBO.setCountNum(count);
                    courierCountBO.setTotal(sum);
                    boList.add(courierCountBO);
                    count = 0;
                    sum = 0;     //置为0
                }
            }
            return boList;
        }

        /**
         * 地区部门年汇总
         */
        if (arrival && department) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.eq("year", year));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (String d : departments) {
                    for (Courier courier : list) {
                        if (a.equals(courier.getArrival()) && d.equals(courier.getDepartment())) {
                            count++;
                            sum += courier.getFeeSum();
                        }
                    }
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setArrival(a);
                    courierCountBO.setDepartment(d);
                    courierCountBO.setCountNum(count);
                    courierCountBO.setTotal(sum);
                    boList.add(courierCountBO);
                    count = 0;
                    sum = 0;     //置为0
                }
            }
            return boList;
        }

        /**
         * 部门快递公司年汇总
         */
        if (department && courierCompany) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.eq("year", year));
            list = super.findByCis(dto);
            for (String d : departments) {
                for (String c : companys) {
                    for (Courier courier : list) {
                        if (d.equals(courier.getDepartment()) && c.equals(courier.getCourierCompany())) {
                            count++;
                            sum += courier.getFeeSum();
                        }
                    }
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setCourierCompany(c);
                    courierCountBO.setDepartment(d);
                    courierCountBO.setCountNum(count);
                    courierCountBO.setTotal(sum);
                    boList.add(courierCountBO);
                    count = 0;
                    sum = 0;     //置为0
                }
            }
            return boList;
        }

        /**
         * 地区年汇总
         */
        if (arrival) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.eq("year", year));
            list = super.findByCis(dto);
            for (String a : arrivals) {
                for (Courier courier : list) {
                    if (a.equals(courier.getArrival())) {
                        count++;
                        sum += courier.getFeeSum();
                    }
                }
                CourierCountBO courierCountBO = new CourierCountBO();
                courierCountBO.setArrival(a);
                courierCountBO.setCountNum(count);
                courierCountBO.setTotal(sum);
                boList.add(courierCountBO);
                count = 0;
                sum = 0;     //置为0
            }
            return boList;
        }

        /**
         * 部门年汇总
         */
        if (department) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.eq("year", year));
            list = super.findByCis(dto);
            for (String d : departments) {
                for (Courier courier : list) {
                    if (d.equals(courier.getDepartment())) {
                        count++;
                        sum += courier.getFeeSum();
                    }
                }
                CourierCountBO courierCountBO = new CourierCountBO();
                courierCountBO.setDepartment(d);
                courierCountBO.setCountNum(count);
                courierCountBO.setTotal(sum);
                boList.add(courierCountBO);
                count = 0;
                sum = 0;     //置为0
            }
            return boList;
        }

        /**
         * 快递公司年汇总
         */
        if (courierCompany) {
            CourierDTO dto = new CourierDTO();
            dto.getConditions().add(Restrict.eq("year", year));
            list = super.findByCis(dto);
            for (String c : companys) {
                for (Courier courier : list) {
                    if (c.equals(courier.getCourierCompany())) {
                        count++;
                        sum += courier.getFeeSum();
                    }
                }
                CourierCountBO courierCountBO = new CourierCountBO();
                courierCountBO.setCourierCompany(c);
                courierCountBO.setCountNum(count);
                courierCountBO.setTotal(sum);
                boList.add(courierCountBO);
                count = 0;
                sum = 0;     //置为0
            }
            return boList;
        }

        /**
         * 年汇总
         */
        CourierDTO dto = new CourierDTO();
        dto.getConditions().add(Restrict.eq("year", year));
        list = super.findByCis(dto);
        for (Courier courier : list) {
            count++;
            sum += courier.getFeeSum();
        }
        CourierCountBO courierCountBO = new CourierCountBO();
        courierCountBO.setCountNum(count);
        courierCountBO.setTotal(sum);
        boList.add(courierCountBO);
        return boList;
    }

    /**
     * 查出所有地区
     *
     * @return Stirng
     * @throws SerException
     */
    private List<String> findAllArrivals() throws SerException {
        Set<String> set = new HashSet<String>();
        List<Courier> list = super.findAll();
        for (Courier courier : list) {
            set.add(courier.getArrival());
        }
        List<String> arrivalList = new ArrayList<>(set);
        return arrivalList;
    }

    /**
     * 查出所有部门
     *
     * @return String
     * @throws SerException
     */
    private List<String> findAllDepartments() throws SerException {
        Set<String> set = new HashSet<String>();
        List<Courier> list = super.findAll();
        for (Courier courier : list) {
            set.add(courier.getDepartment());
        }
        List<String> departments = new ArrayList<>(set);
        return departments;
    }

    /**
     * 查出所有快递公司
     *
     * @return String
     * @throws SerException
     */
    @Override
    public List<String> findAllCompanys() throws SerException {
        List<CourierCompany> list = courierCompanySer.findAll();
        Set<String> set = new HashSet<String>();
        List<String> l = new ArrayList<String>();
        for (CourierCompany c : list) {
            set.add(c.getCourierCompany());
        }
        for (String s : set) {
            l.add(s);
        }
        return l;
    }

    @Override
    public Set<String> findAllAreas() throws SerException {
        //TODO:查找所有寄件地和收件地
        return null;
    }

    @Override
    public List<String> findAllNames() throws SerException {
        //TODO:查找所有收件人
        return null;
    }
}