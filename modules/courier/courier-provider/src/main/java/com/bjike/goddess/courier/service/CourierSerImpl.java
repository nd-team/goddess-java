package com.bjike.goddess.courier.service;

import com.bjike.goddess.announcement.api.AnnouncementAPI;
import com.bjike.goddess.announcement.to.AnnouncementTO;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.courier.bo.CourierBO;
import com.bjike.goddess.courier.bo.CourierCountBO;
import com.bjike.goddess.courier.dto.CourierDTO;
import com.bjike.goddess.courier.entity.Courier;
import com.bjike.goddess.courier.enums.GuideAddrStatus;
import com.bjike.goddess.courier.to.CourierTO;
import com.bjike.goddess.courier.to.GuidePermissionTO;
import com.bjike.goddess.courier.vo.SonPermissionObject;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private MessageAPI messageAPI;
    @Autowired
    private AnnouncementAPI announcementAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private CourierCompanySer courierCompanySer;
    @Autowired
    private ModuleAPI moduleAPI;

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
                throw new SerException("您不是相应部门的人员，不可以查看");
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
     * 导航栏核对查看权限（部门级别）
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
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("courier");
        obj.setDescribesion("快递收发");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = courierCompanySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("couriercompany");
        obj.setDescribesion("快递公司信息");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public CourierBO save(CourierTO to) throws SerException {
        checkAddIdentity();
        Courier courier = BeanTransform.copyProperties(to, Courier.class, true);
        LocalDate sendTime = courier.getSendTime();
        courier.setYear(sendTime.getYear());
        courier.setMonth(sendTime.getMonthValue());
        courier.setFeeSum(courier.getFreight() + courier.getSecure());
        courier.setRemainingSum(courier.getCourierSum() - courier.getFeeSum());
        courier.setCourierTel(courierCompanySer.findTel(to.getCourierCompany()));
        super.save(courier);
        if (courier.getIsConfirm() != null && !courier.getIsConfirm()) {
            String sendPerson = courier.getSendPerson();
            MessageTO messageTO = new MessageTO();
            messageTO.setTitle("您寄送到的物品与寄件清单不符合");
            messageTO.setContent("您寄送到的物品与寄件清单不符合,请进行核对");
            messageTO.setReceivers(new String[]{userAPI.findByUsername(sendPerson).getId()});
            messageAPI.send(messageTO);
        }
        return BeanTransform.copyProperties(courier, CourierBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public CourierBO edit(CourierTO to) throws SerException {
        checkAddIdentity();
        Courier courier = super.findById(to.getId());
        LocalDateTime a = courier.getCreateTime();
        courier = BeanTransform.copyProperties(to, Courier.class, true);
        LocalDate sendTime = courier.getSendTime();
        courier.setYear(sendTime.getYear());
        courier.setMonth(sendTime.getMonthValue());
        courier.setFeeSum(courier.getFreight() + courier.getSecure());
        courier.setRemainingSum(courier.getCourierSum() - courier.getFeeSum());
        courier.setCourierTel(courierCompanySer.findTel(to.getCourierCompany()));
        courier.setCreateTime(a);
        courier.setModifyTime(LocalDateTime.now());
        super.update(courier);
        if (courier.getIsConfirm() != null && !courier.getIsConfirm()) {
            String sendPerson = courier.getSendPerson();
            MessageTO messageTO = new MessageTO();
            messageTO.setTitle("您寄送到的物品与寄件清单不符合");
            messageTO.setContent("您寄送到的物品与寄件清单不符合,请进行核对");
            messageTO.setReceivers(new String[]{userAPI.findByUsername(sendPerson).getId()});
            messageAPI.send(messageTO);
        }
        return BeanTransform.copyProperties(courier, CourierBO.class);
    }

    @Override
    public List<CourierBO> list(CourierDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=asc");
        List<Courier> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, CourierBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public CourierBO findByID(String id) throws SerException {
        Courier courier = super.findById(id);
        return BeanTransform.copyProperties(courier, CourierBO.class);
    }

    @Override
    public List<CourierCountBO> dayCount(CourierDTO dto1) throws SerException {
        checkSeeIdentity();
        String sendTime = dto1.getSendTime();
        List<CourierCountBO> boList = new ArrayList<CourierCountBO>(0);
        List<String> arrivals = findAllArrivals();
        List<String> departments = findAllDepartments();
        List<String> companys = findAllCompanys();
        List<Courier> list = null;
        Integer count = 0;
        double sum = 0;
        int departmentCount = 0;
        double departmentSum = 0;

        CourierDTO dto = new CourierDTO();
        dto.getConditions().add(Restrict.eq("sendTime", sendTime));
        list = super.findByCis(dto);
        for (String a : arrivals) {
            for (String d : departments) {
                for (String c : companys) {
                    for (Courier courier : list) {
                        if (a.equals(courier.getArrival()) && d.equals(courier.getDepartment()) && c.equals(courier.getCourierCompany())) {
                            count++;
                            sum += courier.getFeeSum();
                            departmentCount++;
                            departmentSum += courier.getFeeSum();
                        }
//                        if (a.equals(courier.getArrival()) && d.equals(courier.getDepartment())) {
//                            departmentCount++;
//                            departmentSum += courier.getFeeSum();
//                        }
                    }
                    if ((count != 0) || (sum != 0)) {
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
                if ((departmentCount != 0) || (departmentSum != 0)) {
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setArrival(a);
                    courierCountBO.setDepartment("合计");
                    courierCountBO.setCountNum(departmentCount);
                    courierCountBO.setTotal(departmentSum);        //部门合计
                    boList.add(courierCountBO);
                    departmentCount = 0;
                    departmentSum = 0;
                }
            }
        }
        return boList;
    }

    @Override
    public List<CourierCountBO> weekCount(CourierDTO dto1) throws SerException {
        checkSeeIdentity();
        Integer year = dto1.getYear();
        Integer month = dto1.getMonth();
        Integer week = dto1.getWeek();
        LocalDate[] time = getTimes(year, month, week);
        List<CourierCountBO> boList = new ArrayList<CourierCountBO>(0);
        List<String> arrivals = findAllArrivals();
        List<String> departments = findAllDepartments();
        List<String> companys = findAllCompanys();
        List<Courier> list = null;
        Integer count = 0;
        double sum = 0;
        int departmentCount = 0;
        double departmentSum = 0;

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
                            departmentCount++;
                            departmentSum += courier.getFeeSum();
                        }
//                        if (a.equals(courier.getArrival()) && d.equals(courier.getDepartment())) {
//                            departmentCount++;
//                            departmentSum += courier.getFeeSum();
//                        }
                    }
                    if ((count != 0) || (sum != 0)) {
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
                if ((departmentCount != 0) || (departmentSum != 0)) {
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setArrival(a);
                    courierCountBO.setDepartment("合计");
                    courierCountBO.setCountNum(departmentCount);
                    courierCountBO.setTotal(departmentSum);        //部门合计
                    boList.add(courierCountBO);
                    departmentCount = 0;
                    departmentSum = 0;
                }
            }
        }
        return boList;
    }

    private LocalDate[] getTimes(int year, int month, int week) throws SerException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = dateFormat.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        String end = dateFormat.format(calendar.getTime());
        LocalDate e = DateUtil.parseDate(end);
        if (week == 1) {
            if (String.valueOf(month).length() == 1) {
                start = year + "-0" + month + "-01";
            } else {
                start = year + "-" + month + "-01";
            }
        }
        if (week == weekNum) {
            if (month != e.getMonthValue()) {
                e = DateUtil.parseDate(end);
                e = e.minusDays(e.getDayOfMonth());
            }
        }
        LocalDate s = DateUtil.parseDate(start);
        LocalDate[] time = new LocalDate[]{s, e};
        return time;
    }

    @Override
    public List<CourierCountBO> monthCount(CourierDTO dto1) throws SerException {
        checkSeeIdentity();
        Integer year = dto1.getYear();
        Integer month = dto1.getMonth();
        List<CourierCountBO> boList = new ArrayList<CourierCountBO>(0);
        List<String> arrivals = findAllArrivals();
        List<String> departments = findAllDepartments();
        List<String> companys = findAllCompanys();
        Integer count = 0;
        double sum = 0;
        int departmentCount = 0;
        double departmentSum = 0;

        List<Courier> list = super.findAll();
        for (String a : arrivals) {
            for (String d : departments) {
                for (String c : companys) {
                    for (Courier courier : list) {
                        if (a.equals(courier.getArrival()) && d.equals(courier.getDepartment()) && c.equals(courier.getCourierCompany()) && year.equals(courier.getYear()) && month.equals(courier.getMonth())) {
                            count++;
                            sum += courier.getFeeSum();
                            departmentCount++;
                            departmentSum += courier.getFeeSum();
                        }
//                        if (a.equals(courier.getArrival()) && d.equals(courier.getDepartment()) && year.equals(courier.getYear()) && month.equals(courier.getMonth())) {
//                            departmentCount++;
//                            departmentSum += courier.getFeeSum();
//                        }
                    }
                    if ((count != 0) || (sum != 0)) {
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
                if ((departmentCount != 0) || (departmentSum != 0)) {
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setArrival(a);
                    courierCountBO.setDepartment("合计");
                    courierCountBO.setCountNum(departmentCount);
                    courierCountBO.setTotal(departmentSum);        //部门合计
                    boList.add(courierCountBO);
                    departmentCount = 0;
                    departmentSum = 0;
                }
            }
        }
        return boList;

    }

    @Override
    public List<CourierCountBO> yearCount(CourierDTO dto1) throws SerException {
        checkSeeIdentity();
        Integer year = dto1.getYear();
        List<CourierCountBO> boList = new ArrayList<CourierCountBO>(0);
        List<String> arrivals = findAllArrivals();
        List<String> departments = findAllDepartments();
        List<String> companys = findAllCompanys();
        Integer count = 0;
        double sum = 0;
        int departmentCount = 0;
        double departmentSum = 0;

        List<Courier> list = super.findAll();
        for (String a : arrivals) {
            for (String d : departments) {
                for (String c : companys) {
                    for (Courier courier : list) {
                        if (a.equals(courier.getArrival()) && d.equals(courier.getDepartment()) && c.equals(courier.getCourierCompany()) && year.equals(courier.getYear())) {
                            count++;
                            sum += courier.getFeeSum();
                            departmentCount++;
                            departmentSum += courier.getFeeSum();
                        }
//                        if (a.equals(courier.getArrival()) && d.equals(courier.getDepartment()) && year.equals(courier.getYear())) {
//                            departmentCount++;
//                            departmentSum += courier.getFeeSum();
//                        }
                    }
                    if ((count != 0) || (sum != 0)) {
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
                if ((departmentCount != 0) || (departmentSum != 0)) {
                    CourierCountBO courierCountBO = new CourierCountBO();
                    courierCountBO.setArrival(a);
                    courierCountBO.setDepartment("合计");
                    courierCountBO.setCountNum(departmentCount);
                    courierCountBO.setTotal(departmentSum);        //部门合计
                    boList.add(courierCountBO);
                    departmentCount = 0;
                    departmentSum = 0;
                }
            }
        }
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
    private List<String> findAllCompanys() throws SerException {
        List<Courier> list = super.findAll();
        Set<String> set = new HashSet<String>();
        List<String> l = new ArrayList<String>();
        for (Courier c : list) {
            set.add(c.getCourierCompany());
        }
        for (String s : set) {
            l.add(s);
        }
        return l;
    }

    @Override
    //获取上条记录的快递费总额
    public Double lastCourierSum() throws SerException {
        checkSeeIdentity();
        CourierDTO dto = new CourierDTO();
        dto.getSorts().add("createTime=asc");
        List<Courier> list = super.findByCis(dto);
        if (list != null && !list.isEmpty()) {
            return list.get(list.size() - 1).getCourierSum();
        }
        return null;
    }

    @Override
    public Set<Integer> allYear() throws SerException {
        List<Courier> list = super.findAll();
        Set<Integer> set = new HashSet<>();
        for (Courier courier : list) {
            set.add(courier.getYear());
        }
        return set;
    }

    @Override
    public Set<Integer> allMonth() throws SerException {
        List<Courier> list = super.findAll();
        Set<Integer> set = new HashSet<>();
        for (Courier courier : list) {
            set.add(courier.getMonth());
        }
        return set;
    }

    @Override
    public Long count(CourierDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public void send() throws SerException {
        List<Courier> list = super.findAll();
        LocalDate now = LocalDate.now();
        for (Courier courier : list) {
            LocalDate receiptTime = courier.getReceiptTime();
            String sendPerson = courier.getSendPerson();
            String receiptPerson = courier.getReceiptPerson();
            if (now == receiptTime.minusDays(1)) {
                AnnouncementTO to = new AnnouncementTO();
                to.setTitle("您有一份快递即将到达");
                to.setAuthor("system");
                to.setPublishContent("您有一份快递即将达到，快递寄送到达后请上系统操作");
                String send = userAPI.findByUsername(sendPerson).getId();
                String receipt = userAPI.findByUsername(receiptPerson).getId();
                to.setRecipients(new String[]{send, receipt});
                to.setRequired(false);
                to.setSend(false);
                String token=RpcTransmit.getUserToken();
                if (moduleAPI.isCheck("courier")) {
                    RpcTransmit.transmitUserToken(token);
                    announcementAPI.addPerson(to);
                }
            }
        }
    }
}
