package com.bjike.goddess.outcarfare.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.outcarfare.bo.MoneyReadyBO;
import com.bjike.goddess.outcarfare.bo.MoneyReadyCountBO;
import com.bjike.goddess.outcarfare.dto.MoneyReadyDTO;
import com.bjike.goddess.outcarfare.entity.MoneyReady;
import com.bjike.goddess.outcarfare.enums.GuideAddrStatus;
import com.bjike.goddess.outcarfare.to.GuidePermissionTO;
import com.bjike.goddess.outcarfare.to.MoneyReadyTO;
import com.bjike.goddess.outcarfare.vo.SonPermissionObject;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 资金准备审核业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-05 02:41 ]
 * @Description: [ 资金准备审核业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "outcarfareSerCache")
@Service
public class MoneyReadySerImpl extends ServiceImpl<MoneyReady, MoneyReadyDTO> implements MoneyReadySer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private WaitPaySer waitPaySer;

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
        obj.setName("moneyready");
        obj.setDescribesion("资金审核准备");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = waitPaySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("waitpay");
        obj.setDescribesion("等待付款");
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
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case pay:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public MoneyReadyBO save(MoneyReadyTO to) throws SerException {
        checkAddIdentity();
        MoneyReady m = BeanTransform.copyProperties(to, MoneyReady.class, true);
        m.setReserve(m.getTotalReserve() * m.getProrate());
        m.setTime(DateUtil.getStartDayOfMonth(m.getYear(), m.getMonth()));
        m.setIsDel(false);
        super.save(m);
        return BeanTransform.copyProperties(m, MoneyReadyBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(MoneyReadyTO to) throws SerException {
        checkAddIdentity();
        MoneyReady moneyReady = super.findById(to.getId());
        LocalDateTime a = moneyReady.getCreateTime();
        moneyReady = BeanTransform.copyProperties(to, MoneyReady.class, true);
        moneyReady.setReserve(moneyReady.getTotalReserve() * moneyReady.getProrate());
        moneyReady.setTime(DateUtil.getStartDayOfMonth(moneyReady.getYear(), moneyReady.getMonth()));
        moneyReady.setIsDel(false);
        moneyReady.setCreateTime(a);
        moneyReady.setModifyTime(LocalDateTime.now());
        super.update(moneyReady);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        checkAddIdentity();
        MoneyReady moneyReady = super.findById(id);
        moneyReady.setIsDel(true);
        moneyReady.setDelTime(LocalDate.now());
        moneyReady.setModifyTime(LocalDateTime.now());
        super.update(moneyReady);
    }

    @Override
    public List<MoneyReadyBO> list(MoneyReadyDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        List<MoneyReady> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, MoneyReadyBO.class);
    }

    @Override
    public MoneyReadyBO findByID(String id) throws SerException {
        MoneyReady m = super.findById(id);
        return BeanTransform.copyProperties(m, MoneyReadyBO.class);
    }

    @Override
    public List<MoneyReadyBO> delList(MoneyReadyDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("delTime=asc");
        dto.getConditions().add(Restrict.eq("isDel", Boolean.FALSE));
        List<MoneyReady> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, MoneyReadyBO.class);
    }

    @Override
    public Long delCount(MoneyReadyDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("isDel", Boolean.FALSE));
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void quartz() throws SerException {
        MoneyReadyDTO dto = new MoneyReadyDTO();
        dto.getConditions().add(Restrict.eq("isDel", Boolean.FALSE));
        List<MoneyReady> list = super.findByCis(dto);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            long now = simpleDateFormat.parse(DateUtil.dateToString(LocalDate.now())).getTime();
            for (MoneyReady m : list) {
                LocalDate a = m.getDelTime().plusDays(30);
                long delTime = simpleDateFormat.parse(DateUtil.dateToString(a)).getTime();
                if (now - delTime >= 0) {
                    super.remove(m);
                }
            }
        } catch (ParseException e) {
            throw new SerException(e.getMessage());
        }
    }

    @Override
    public List<MoneyReadyCountBO> departCount(MoneyReadyDTO dto) throws SerException {
        checkSeeIdentity();
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        String[] groupTeams = dto.getGroupTeams();
        int startYear = Integer.valueOf(startTime.substring(0, 4));
        int startMonth = 0;
        int endMonth = 0;
        String a = String.valueOf(startTime.charAt(startTime.length() - 2));
        if ("0".equals(a)) {
            startMonth = Integer.valueOf(String.valueOf(startTime.charAt(startTime.length() - 1)));
        } else {
            String s = String.valueOf(startTime.charAt(startTime.length() - 2)) + String.valueOf(startTime.charAt(startTime.length() - 1));
            startMonth = Integer.valueOf(s);
        }
        int endYear = Integer.valueOf(endTime.substring(0, 4));
        String b = String.valueOf(endTime.charAt(endTime.length() - 2));
        if ("0".equals(b)) {
            endMonth = Integer.valueOf(String.valueOf(endTime.charAt(endTime.length() - 1)));
        } else {
            String s = String.valueOf(endTime.charAt(endTime.length() - 2)) + String.valueOf(endTime.charAt(endTime.length() - 1));
            endMonth = Integer.valueOf(s);
        }
        LocalDate start = DateUtil.getStartDayOfMonth(startYear, startMonth);
        LocalDate end = DateUtil.getEndDaYOfMonth(endYear, endMonth);
        String time = startTime + "至" + endTime;
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        dto.getConditions().add(Restrict.between("time", new LocalDate[]{start, end}));
        dto.getConditions().add(Restrict.in("groupTeam", groupTeams));
        List<MoneyReady> list = super.findByCis(dto);
        List<MoneyReadyCountBO> boList = new ArrayList<MoneyReadyCountBO>();
        double currentSum = list.stream().mapToDouble((MoneyReady m) -> m.getReserve()).sum();
        for (String groupTeam : groupTeams) {
            double currentMonthReserveSum = list.stream().filter(moneyReady -> groupTeam.equals(moneyReady.getGroupTeam())).mapToDouble((MoneyReady m) -> m.getReserve()).sum();
            MoneyReadyCountBO bo = new MoneyReadyCountBO();
            bo.setGroupTeam(groupTeam);
            bo.setTime(time);
            bo.setCurrentMonthReserveSum(currentMonthReserveSum);
            boList.add(bo);
        }
        MoneyReadyCountBO bo = new MoneyReadyCountBO();
        bo.setGroupTeam("合计");
        bo.setTime(time);
        bo.setCurrentMonthReserveSum(currentSum);
        boList.add(bo);
        List<MoneyReadyCountBO> lastBO = last(startYear, startMonth, groupTeams, "groupTeams");
        for (MoneyReadyCountBO current : boList) {
            for (MoneyReadyCountBO last : lastBO) {
                if (current.getGroupTeam().equals(last.getGroupTeam())) {
                    double lastMonthReserveSum = last.getLastMonthReserveSum();
                    current.setLastMonthReserveSum(lastMonthReserveSum);
                    double differences = current.getCurrentMonthReserveSum() - lastMonthReserveSum;
                    current.setDifferences(differences);
                    if (lastMonthReserveSum != 0) {
                        current.setGrowth(new BigDecimal(differences / lastMonthReserveSum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }
                }
            }
        }
        return boList;
    }

    @Override
    public List<MoneyReadyCountBO> areaCount(MoneyReadyDTO dto) throws SerException {
        checkSeeIdentity();
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        String[] areas = dto.getAreas();
        int startYear = Integer.valueOf(startTime.substring(0, 4));
        int startMonth = 0;
        int endMonth = 0;
        String a = String.valueOf(startTime.charAt(startTime.length() - 2));
        if ("0".equals(a)) {
            startMonth = Integer.valueOf(String.valueOf(startTime.charAt(startTime.length() - 1)));
        } else {
            String s = String.valueOf(startTime.charAt(startTime.length() - 2)) + String.valueOf(startTime.charAt(startTime.length() - 1));
            startMonth = Integer.valueOf(s);
        }
        int endYear = Integer.valueOf(endTime.substring(0, 4));
        String b = String.valueOf(endTime.charAt(endTime.length() - 2));
        if ("0".equals(b)) {
            endMonth = Integer.valueOf(String.valueOf(endTime.charAt(endTime.length() - 1)));
        } else {
            String s = String.valueOf(endTime.charAt(endTime.length() - 2)) + String.valueOf(endTime.charAt(endTime.length() - 1));
            endMonth = Integer.valueOf(s);
        }
        LocalDate start = DateUtil.getStartDayOfMonth(startYear, startMonth);
        LocalDate end = DateUtil.getEndDaYOfMonth(endYear, endMonth);
        String time = startTime + "至" + endTime;
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        dto.getConditions().add(Restrict.between("time", new LocalDate[]{start, end}));
        dto.getConditions().add(Restrict.in("area", areas));
        List<MoneyReady> list = super.findByCis(dto);
        List<MoneyReadyCountBO> boList = new ArrayList<MoneyReadyCountBO>();
        double currentSum = list.stream().mapToDouble((MoneyReady m) -> m.getReserve()).sum();
        for (String area : areas) {
            double currentMonthReserveSum = list.stream().filter(moneyReady -> area.equals(moneyReady.getArea())).mapToDouble((MoneyReady m) -> m.getReserve()).sum();
            MoneyReadyCountBO bo = new MoneyReadyCountBO();
            bo.setArea(area);
            bo.setTime(time);
            bo.setCurrentMonthReserveSum(currentMonthReserveSum);
            boList.add(bo);
        }
        MoneyReadyCountBO bo = new MoneyReadyCountBO();
        bo.setArea("合计");
        bo.setTime(time);
        bo.setCurrentMonthReserveSum(currentSum);
        boList.add(bo);
        List<MoneyReadyCountBO> lastBO = last(startYear, startMonth, areas, "areas");
        for (MoneyReadyCountBO current : boList) {
            for (MoneyReadyCountBO last : lastBO) {
                if (current.getArea().equals(last.getArea())) {
                    double lastMonthReserveSum = last.getLastMonthReserveSum();
                    current.setLastMonthReserveSum(lastMonthReserveSum);
                    double differences = current.getCurrentMonthReserveSum() - lastMonthReserveSum;
                    current.setDifferences(differences);
                    if (lastMonthReserveSum != 0) {
                        current.setGrowth(new BigDecimal(differences / lastMonthReserveSum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }
                }
            }
        }
        return boList;
    }

    /**
     * 上月汇总
     *
     * @param year
     * @param month
     * @param strings
     * @param type
     * @return
     * @throws SerException
     */
    private List<MoneyReadyCountBO> last(int year, int month, String[] strings, String type) throws SerException {
        MoneyReadyDTO dto = new MoneyReadyDTO();
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        List<MoneyReadyCountBO> boList = new ArrayList<>();
        if ("groupTeams".equals(type)) {
            dto.getConditions().add(Restrict.in("groupTeam", strings));
            if (month != 1) {
                dto.getConditions().add(Restrict.eq("year", year));
                dto.getConditions().add(Restrict.eq("month", month - 1));
                List<MoneyReady> list = super.findByCis(dto);
                double lastSum = list.stream().mapToDouble((MoneyReady m) -> m.getReserve()).sum();
                for (String s : strings) {
                    double lastMonthReserveSum = list.stream().filter(moneyReady -> s.equals(moneyReady.getGroupTeam())).mapToDouble((MoneyReady m) -> m.getReserve()).sum();
                    MoneyReadyCountBO bo = new MoneyReadyCountBO();
                    bo.setGroupTeam(s);
                    bo.setLastMonthReserveSum(lastMonthReserveSum);
                    boList.add(bo);
                }
                MoneyReadyCountBO bo = new MoneyReadyCountBO();
                bo.setGroupTeam("合计");
                bo.setLastMonthReserveSum(lastSum);
                boList.add(bo);
            } else {
                dto.getConditions().add(Restrict.eq("year", year - 1));
                dto.getConditions().add(Restrict.eq("month", 12));
                List<MoneyReady> list = super.findByCis(dto);
                double lastSum = list.stream().mapToDouble((MoneyReady m) -> m.getReserve()).sum();
                for (String s : strings) {
                    double lastMonthReserveSum = list.stream().filter(moneyReady -> s.equals(moneyReady.getGroupTeam())).mapToDouble((MoneyReady m) -> m.getReserve()).sum();
                    MoneyReadyCountBO bo = new MoneyReadyCountBO();
                    bo.setGroupTeam(s);
                    bo.setLastMonthReserveSum(lastMonthReserveSum);
                    boList.add(bo);
                }
                MoneyReadyCountBO bo = new MoneyReadyCountBO();
                bo.setGroupTeam("合计");
                bo.setLastMonthReserveSum(lastSum);
                boList.add(bo);
            }
        } else if ("areas".equals(type)) {
            dto.getConditions().add(Restrict.in("area", strings));
            if (month != 1) {
                dto.getConditions().add(Restrict.eq("year", year));
                dto.getConditions().add(Restrict.eq("month", month - 1));
                List<MoneyReady> list = super.findByCis(dto);
                double lastSum = list.stream().mapToDouble((MoneyReady m) -> m.getReserve()).sum();
                for (String s : strings) {
                    double lastMonthReserveSum = list.stream().filter(moneyReady -> s.equals(moneyReady.getArea())).mapToDouble((MoneyReady m) -> m.getReserve()).sum();
                    MoneyReadyCountBO bo = new MoneyReadyCountBO();
                    bo.setArea(s);
                    bo.setLastMonthReserveSum(lastMonthReserveSum);
                    boList.add(bo);
                }
                MoneyReadyCountBO bo = new MoneyReadyCountBO();
                bo.setArea("合计");
                bo.setLastMonthReserveSum(lastSum);
                boList.add(bo);
            } else {
                dto.getConditions().add(Restrict.eq("year", year - 1));
                dto.getConditions().add(Restrict.eq("month", 12));
                List<MoneyReady> list = super.findByCis(dto);
                double lastSum = list.stream().mapToDouble((MoneyReady m) -> m.getReserve()).sum();
                for (String s : strings) {
                    double lastMonthReserveSum = list.stream().filter(moneyReady -> s.equals(moneyReady.getArea())).mapToDouble((MoneyReady m) -> m.getReserve()).sum();
                    MoneyReadyCountBO bo = new MoneyReadyCountBO();
                    bo.setArea(s);
                    bo.setLastMonthReserveSum(lastMonthReserveSum);
                    boList.add(bo);
                }
                MoneyReadyCountBO bo = new MoneyReadyCountBO();
                bo.setArea("合计");
                bo.setLastMonthReserveSum(lastSum);
                boList.add(bo);
            }
        }
        return boList;
    }

    @Override
    public void reback(String id) throws SerException {
        checkAddIdentity();
        MoneyReady entity = super.findById(id);
        entity.setDelTime(null);
        entity.setIsDel(false);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<MoneyReadyBO> details(MoneyReadyDTO dto) throws SerException {
        checkSeeIdentity();
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        String[] groupTeams = dto.getGroupTeams();
        String[] areas = dto.getAreas();
        int startYear = Integer.valueOf(startTime.substring(0, 4));
        int startMonth = 0;
        int endMonth = 0;
        String a = String.valueOf(startTime.charAt(startTime.length() - 2));
        if ("0".equals(a)) {
            startMonth = Integer.valueOf(String.valueOf(startTime.charAt(startTime.length() - 1)));
        } else {
            String s = String.valueOf(startTime.charAt(startTime.length() - 2)) + String.valueOf(startTime.charAt(startTime.length() - 1));
            startMonth = Integer.valueOf(s);
        }
        int endYear = Integer.valueOf(endTime.substring(0, 4));
        String b = String.valueOf(endTime.charAt(endTime.length() - 2));
        if ("0".equals(b)) {
            endMonth = Integer.valueOf(String.valueOf(endTime.charAt(endTime.length() - 1)));
        } else {
            String s = String.valueOf(endTime.charAt(endTime.length() - 2)) + String.valueOf(endTime.charAt(endTime.length() - 1));
            endMonth = Integer.valueOf(s);
        }
        LocalDate start = DateUtil.getStartDayOfMonth(startYear, startMonth);
        LocalDate end = DateUtil.getEndDaYOfMonth(endYear, endMonth);
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        dto.getConditions().add(Restrict.between("time", new LocalDate[]{start, end}));
        List<MoneyReadyBO> boList = new LinkedList<>();
        if (null != areas) {
            dto.getSorts().add("area=asc");
            dto.getConditions().add(Restrict.in("area", areas));
            List<MoneyReady> list = super.findByCis(dto);
            if (!list.isEmpty()) {
                boList = BeanTransform.copyProperties(list, MoneyReadyBO.class);
            }
            for (String area : areas) {
                double totalReserve = list.stream().filter(moneyReady -> area.equals(moneyReady.getArea())).mapToDouble((MoneyReady m) -> m.getTotalReserve()).sum();
                double reserve = list.stream().filter(moneyReady -> area.equals(moneyReady.getArea())).mapToDouble((MoneyReady m) -> m.getReserve()).sum();
                ListIterator<MoneyReadyBO> iterator = boList.listIterator();
                boolean b1 = false;
                int num = 0;
                while (iterator.hasNext()) {
                    if (area.equals(iterator.next().getArea())) {
                        b1 = true;
                        num = iterator.nextIndex();
                    }
                }
                if (b1) {
                    MoneyReadyBO bo = new MoneyReadyBO();
                    bo.setArea(area);
                    bo.setCategory("合计");
                    bo.setTotalReserve(totalReserve);
                    bo.setReserve(reserve);
                    boList.add(num, bo);
                }
            }
        } else if (null != groupTeams) {
            dto.getSorts().add("groupTeam=asc");
            dto.getConditions().add(Restrict.in("groupTeam", groupTeams));
            List<MoneyReady> list = super.findByCis(dto);
            if (!list.isEmpty()) {
                boList = BeanTransform.copyProperties(list, MoneyReadyBO.class);
            }
            for (String groupTeam : groupTeams) {
                double totalReserve = list.stream().filter(moneyReady -> groupTeam.equals(moneyReady.getGroupTeam())).mapToDouble((MoneyReady m) -> m.getTotalReserve()).sum();
                double reserve = list.stream().filter(moneyReady -> groupTeam.equals(moneyReady.getGroupTeam())).mapToDouble((MoneyReady m) -> m.getReserve()).sum();
                ListIterator<MoneyReadyBO> iterator = boList.listIterator();
                boolean b1 = false;
                int num = 0;
                while (iterator.hasNext()) {
                    if (groupTeam.equals(iterator.next().getGroupTeam())) {
                        b1 = true;
                        num = iterator.nextIndex();
                    }
                }
                if (b1) {
                    MoneyReadyBO bo = new MoneyReadyBO();
                    bo.setGroupTeam(groupTeam);
                    bo.setCategory("合计");
                    bo.setTotalReserve(totalReserve);
                    bo.setReserve(reserve);
                    boList.add(num, bo);
                }
            }
        }
        return boList;
    }

    /**
     * 获取所有项目组
     *
     * @return class String
     * @throws SerException
     */
    @Override
    public Set<String> findAllGroupTeams() throws SerException {
        List<MoneyReady> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (MoneyReady m : list) {
            set.add(m.getGroupTeam());
        }
        return set;
    }

    @Override
    public Set<String> areas() throws SerException {
        List<MoneyReady> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (MoneyReady m : list) {
            set.add(m.getArea());
        }
        return set;
    }

    @Override
    public Long countSum(MoneyReadyDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        return super.count(dto);
    }
}