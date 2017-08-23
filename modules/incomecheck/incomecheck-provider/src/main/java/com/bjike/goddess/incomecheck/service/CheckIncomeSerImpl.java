package com.bjike.goddess.incomecheck.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.incomecheck.bo.CheckIncomeBO;
import com.bjike.goddess.incomecheck.dto.CheckIncomeDTO;
import com.bjike.goddess.incomecheck.dto.CheckIndexDTO;
import com.bjike.goddess.incomecheck.entity.CheckIncome;
import com.bjike.goddess.incomecheck.entity.CheckIndex;
import com.bjike.goddess.incomecheck.enums.GuideAddrStatus;
import com.bjike.goddess.incomecheck.to.CheckIncomeTO;
import com.bjike.goddess.incomecheck.to.GuidePermissionTO;
import com.bjike.goddess.incomecheck.vo.SonPermissionObject;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 收入核算资金回笼业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-29 09:54 ]
 * @Description: [ 收入核算资金回笼业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "incomecheckSerCache")
@Service
public class CheckIncomeSerImpl extends ServiceImpl<CheckIncome, CheckIncomeDTO> implements CheckIncomeSer {

    @Autowired
    private CheckIndexSer checkIndexSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

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
        obj.setName("checkincome");
        obj.setDescribesion("收入核算资金回笼");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = checkIndexSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("checkindex");
        obj.setDescribesion("指标设置");
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
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case DIFFER:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    public Long countCheckIncome(CheckIncomeDTO checkIncomeDTO) throws SerException {
        Long count = super.count(checkIncomeDTO);
        return count;
    }

    @Override
    public CheckIncomeBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        CheckIncome checkIncome = super.findById(id);
        return BeanTransform.copyProperties(checkIncome, CheckIncomeBO.class);
    }

    @Override
    public List<CheckIncomeBO> listCheckIncome(CheckIncomeDTO checkIncomeDTO) throws SerException {
        checkSeeIdentity();
        checkIncomeDTO.getSorts().add("createTime=desc");
        List<CheckIncome> list = super.findByCis(checkIncomeDTO, true);

        return BeanTransform.copyProperties(list, CheckIncomeBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CheckIncomeBO addCheckIncome(CheckIncomeTO checkIncomeTO) throws SerException {
        checkAddIdentity();
        if (checkIncomeTO.getPlanIncome() == null) {
            throw new SerException("计划收入不能为空");
        }
        //TODO : 从回款明细的到帐金额拿数据->实际收入  ,任务分配拿实际任务量
        CheckIncome checkIncome = BeanTransform.copyProperties(checkIncomeTO, CheckIncome.class, true);

        checkIncome.setActualIncome(checkIncomeTO.getActualIncome() == null ? 0d : checkIncomeTO.getActualIncome());
        if (checkIncome.getPlanIncome().doubleValue() != 0) {
            checkIncome.setRate(checkIncome.getActualIncome() / checkIncome.getPlanIncome());
        }else {
            throw new SerException("计划收入为0，不能计算");
        }
        checkIncome.setBalance(checkIncome.getActualIncome() - checkIncome.getPlanIncome());
        checkIncome.setActualTask(checkIncomeTO.getActualTask() == null ? 0d : checkIncomeTO.getActualTask());
        if (checkIncome.getTargetTask().doubleValue() != 0) {
            checkIncome.setCompleteRate(checkIncome.getActualTask() / checkIncome.getTargetTask());
        }else {
            throw new SerException("目标任务量为0，不能计算");
        }
        checkIncome.setCreateTime(LocalDateTime.now());
        super.save(checkIncome);
        return BeanTransform.copyProperties(checkIncome, CheckIncomeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CheckIncomeBO editCheckIncome(CheckIncomeTO checkIncomeTO) throws SerException {
        checkAddIdentity();
        if (checkIncomeTO.getPlanIncome() == null) {
            throw new SerException("计划收入不能为空");
        }
        //TODO : 从回款明细的到帐金额拿数据->实际收入  ,任务分配拿实际任务量
        CheckIncome checkIncome = BeanTransform.copyProperties(checkIncomeTO, CheckIncome.class, true);
        CheckIncome temp = super.findById(checkIncomeTO.getId());

        BeanUtils.copyProperties(checkIncome, temp, "id", "createTime");
        temp.setActualIncome(checkIncome.getActualIncome() == null ? 0d : checkIncome.getActualIncome());
        if (temp.getPlanIncome().doubleValue() != 0) {
            temp.setRate(checkIncome.getActualIncome() / checkIncome.getPlanIncome());
        }else {
            throw new SerException("计划收入为0，不能计算");
        }
        temp.setBalance(checkIncome.getActualIncome() - checkIncome.getPlanIncome());
        temp.setActualTask(checkIncome.getActualTask() == null ? 0d : checkIncome.getActualTask());
        if (checkIncome.getTargetTask().doubleValue() != 0) {
            temp.setCompleteRate(checkIncome.getActualTask() / checkIncome.getTargetTask());
        }else {
            throw new SerException("目标任务量为0，不能计算");
        }

        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
        return BeanTransform.copyProperties(temp, CheckIncomeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCheckIncome(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public List<CheckIncomeBO> collectArea(CheckIncomeDTO checkIncomeDTO) throws SerException {
        checkSeeIdentity();
        String startTime = checkIncomeDTO.getStartTime();
        String endTime = checkIncomeDTO.getEndTime();

        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if (StringUtils.isNotBlank(startTime)) {
            start = LocalDate.parse(startTime);
        }
        if (StringUtils.isNotBlank(endTime)) {
            end = LocalDate.parse(endTime);
        }
        //如果没有选地区，汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"area", "planIncome", "actualIncome", "rate", "balance", "targetTask", "actualTask", "completeRate"};
        String sql = "";
        List<CheckIncomeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(checkIncomeDTO.getArea())) {
            sql = "select area ,  sum(planIncome) as planIncome , sum(actualIncome) as actualIncome ," +
                    "  (sum(actualIncome)/sum(planIncome)) as rate , (sum(actualIncome)-sum(planIncome)) as balance " +
                    " , sum(targetTask) as targetTask , sum(actualTask) as actualTask , (sum(actualTask)/sum(targetTask)) as completeRate from incomecheck_checkincome where 1= 1";
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                sql = sql + " and time between '" + start + "' and '" + end + "'  ";
            }
            sql = sql + " group by area  order by area desc ";
            list = super.findBySql(sql, CheckIncomeBO.class, field);
            list.stream().forEach(str -> {
                str.setTime(startTime + "到" + endTime);
            });
        } else {
            //如果有选地区，汇总表头：(地区/年份/月份/项目组/项目名称/类别/目标管理费/实际管理费/比例/差额)
            field = new String[]{"area", "time", "projectGroup", "projectName", "planIncome", "actualIncome", "rate", "balance", "targetTask", "actualTask", "completeRate"};
            sql = "select area , time , projectGroup ,projectName ,planIncome ,actualIncome ," +
                    "  (actualIncome/planIncome) as rate , (actualIncome-planIncome) as balance " +
                    " ,  targetTask , actualTask , (actualTask-targetTask) as completeRate from incomecheck_checkincome where 1=1 ";
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                sql = sql + " and time between '" + start + "' and '" + end + "'  ";
            }
            sql = sql + " and area='" + checkIncomeDTO.getArea() + "' order by area desc ";
            list = super.findBySql(sql, CheckIncomeBO.class, field);
        }


        return list;
    }

    @Override
    public List<CheckIncomeBO> collectGroup(CheckIncomeDTO checkIncomeDTO) throws SerException {
        checkSeeIdentity();
        String startTime = checkIncomeDTO.getStartTime();
        String endTime = checkIncomeDTO.getEndTime();

        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if (StringUtils.isNotBlank(startTime)) {
            start = LocalDate.parse(startTime);
        }
        if (StringUtils.isNotBlank(endTime)) {
            end = LocalDate.parse(endTime);
        }
        //如果没有选地区，汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"projectGroup", "planIncome", "actualIncome", "rate", "balance", "targetTask", "actualTask", "completeRate"};
        String sql = "";
        List<CheckIncomeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(checkIncomeDTO.getProjectGroup())) {
            sql = "select projectGroup ,  sum(planIncome) as planIncome , sum(actualIncome) as actualIncome ," +
                    "  (sum(actualIncome)/sum(planIncome)) as rate , (sum(actualIncome)-sum(planIncome)) as balance " +
                    " , sum(targetTask) as targetTask , sum(actualTask) as actualTask , (sum(actualTask)/sum(targetTask)) as completeRate from incomecheck_checkincome where 1= 1";
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                sql = sql + " and time between '" + start + "' and '" + end + "'  ";
            }
            sql = sql + " group by projectGroup  order by projectGroup desc ";
            list = super.findBySql(sql, CheckIncomeBO.class, field);
            list.stream().forEach(str -> {
                str.setTime(startTime + "到" + endTime);
            });
        } else {
            //如果有选地区，汇总表头：(地区/年份/月份/项目组/项目名称/类别/目标管理费/实际管理费/比例/差额)
            field = new String[]{"projectGroup", "time", "area", "projectName", "planIncome", "actualIncome", "rate", "balance", "targetTask", "actualTask", "completeRate"};
            sql = "select projectGroup , time , area ,projectName ,planIncome ,actualIncome ," +
                    "  (actualIncome/planIncome) as rate , (actualIncome-planIncome) as balance " +
                    " ,  targetTask , actualTask , (actualTask-targetTask) as completeRate from incomecheck_checkincome where 1=1 ";
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                sql = sql + " and time between '" + start + "' and '" + end + "'  ";
            }
            sql = sql + " and projectGroup='" + checkIncomeDTO.getProjectGroup() + "'  order by projectGroup desc ";
            list = super.findBySql(sql, CheckIncomeBO.class, field);
        }


        return list;
    }

    @Override
    public List<CheckIncomeBO> collectProject(CheckIncomeDTO checkIncomeDTO) throws SerException {
        checkSeeIdentity();
        String startTime = checkIncomeDTO.getStartTime();
        String endTime = checkIncomeDTO.getEndTime();

        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if (StringUtils.isNotBlank(startTime)) {
            start = LocalDate.parse(startTime);
        }
        if (StringUtils.isNotBlank(endTime)) {
            end = LocalDate.parse(endTime);
        }
        //如果没有选地区，汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"projectName", "planIncome", "actualIncome", "rate", "balance", "targetTask", "actualTask", "completeRate"};
        String sql = "";
        List<CheckIncomeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(checkIncomeDTO.getProjectName())) {
            sql = "select projectName ,  sum(planIncome) as planIncome , sum(actualIncome) as actualIncome ," +
                    "  (sum(actualIncome)/sum(planIncome)) as rate , (sum(actualIncome)-sum(planIncome)) as balance " +
                    " , sum(targetTask) as targetTask , sum(actualTask) as actualTask , (sum(actualTask)/sum(targetTask)) as completeRate from incomecheck_checkincome where 1= 1";
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                sql = sql + " and time between '" + start + "' and '" + end + "'  ";
            }
            sql = sql + " group by projectName  order by projectName desc ";
            list = super.findBySql(sql, CheckIncomeBO.class, field);
            list.stream().forEach(str -> {
                str.setTime(startTime + "到" + endTime);
            });
        } else {
            //如果有选地区，汇总表头：(地区/年份/月份/项目组/项目名称/类别/目标管理费/实际管理费/比例/差额)
            field = new String[]{"projectName", "time", "area", "projectName", "planIncome", "actualIncome", "rate", "balance", "targetTask", "actualTask", "completeRate"};
            sql = "select projectName , time , area ,projectGroup ,planIncome ,actualIncome ," +
                    "  (actualIncome/planIncome) as rate , (actualIncome-planIncome) as balance " +
                    " ,  targetTask , actualTask , (actualTask-targetTask) as completeRate from incomecheck_checkincome where 1=1 ";
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                sql = sql + " and time between '" + start + "' and '" + end + "'  ";
            }
            sql = sql + "  and projectName='" + checkIncomeDTO.getProjectName() + "'  order by projectName desc ";
            list = super.findBySql(sql, CheckIncomeBO.class, field);
        }


        return list;
    }

    private void caculateRate(String condition, String conditionValue, LocalDate start, LocalDate end, CheckIndex checkIndex, List<CheckIncomeBO> collectList) throws SerException {
        //condition area projectGroup project
        String[] field = new String[]{"area", "rate", "completeRate"};
        String sql = "";
        if ("area".equals(condition)) {
            sql = "select area , (sum(actualIncome)/sum(planIncome)) as rate , (sum(actualTask)/sum(targetTask)) as completeRate from incomecheck_checkincome " +
                    " where area = '" + conditionValue + "' and time between '" + start + "' and '" + end + "' group by area";
        }
        if ("projectGroup".equals(condition)) {
            field = new String[]{"projectGroup", "rate", "completeRate"};
            sql = "select projectGroup , (sum(actualIncome)/sum(planIncome)) as rate , (sum(actualTask)/sum(targetTask)) as completeRate from incomecheck_checkincome " +
                    " where projectGroup = '" + conditionValue + "' and time between '" + start + "' and '" + end + "' group by area";
        }
        if ("projectName".equals(condition)) {
            field = new String[]{"projectName", "rate", "completeRate"};
            sql = "select projectName , (sum(actualIncome)/sum(planIncome)) as rate , (sum(actualTask)/sum(targetTask)) as completeRate from incomecheck_checkincome " +
                    " where projectName = '" + conditionValue + "' and time between '" + start + "' and '" + end + "' group by area";
        }
        List<CheckIncomeBO> list = super.findBySql(sql, CheckIncomeBO.class, field);
        if (list != null && list.size() > 0) {
            CheckIncomeBO bo = list.get(0);
            bo.setTime(start.getYear() + "-" + start.getMonthValue());
            bo.setArea(conditionValue);
            if (checkIndex.getIncomeRate() < bo.getRate()) {
                bo.setWarnRate("提醒");
            }
            if (checkIndex.getCompleteRate() < bo.getCompleteRate()) {
                bo.setWarnRate("提醒");
            }
            collectList.add(bo);
        }

    }

    @Override
    public List<CheckIncomeBO> areaDiff(CheckIncomeDTO checkIncomeDTO) throws SerException {
        checkSeeIdentity();
        List<CheckIncomeBO> collectList = new ArrayList<>();
        if (StringUtils.isBlank(checkIncomeDTO.getArea())) {
            throw new SerException("地区不能为空");
        }
        if (StringUtils.isBlank(checkIncomeDTO.getStartTime()) && StringUtils.isBlank(checkIncomeDTO.getEndTime())) {
            throw new SerException("开始时间和结束时间不能为空");
        }
        String startTime = checkIncomeDTO.getStartTime();
        String endTime = checkIncomeDTO.getEndTime();
        LocalDate start = LocalDate.parse(startTime);
        LocalDate end = LocalDate.parse(endTime);
        String area = checkIncomeDTO.getArea();
        if (end.isBefore(start)) {
            throw new SerException("结束时间不能比开始时间小");
        }

        CheckIndexDTO checkIndexDTO = new CheckIndexDTO();
        CheckIndex checkIndex = checkIndexSer.findOne(checkIndexDTO);

        //获取选取的时间段内所有的年月
        int flag = 0;
        while (start.getYear() != end.getYear() || start.getMonthValue() != end.getMonthValue()) {
            if (flag == 0) {
                System.out.println(start);
                caculateRate("area", area, start, end, checkIndex, collectList);
            }
            start = start.plusMonths(1);
            if (flag != 3) {//!=3
                flag = 0;
            }
            if (flag != 3 && start.getMonthValue() == 12) {
                System.out.println(start);
                caculateRate("area", area, start, end, checkIndex, collectList);
                flag = 1;

                if (start.plusYears(1).getYear() <= end.getYear()) {
                    start = start.plusYears(1);
                    start = LocalDate.of(start.getYear(), 1, 1);
                    System.out.println(start);
                    caculateRate("area", area, start, end, checkIndex, collectList);
                    flag = 2;
                }
            }
            if (flag == 0 && start.getYear() == end.getYear() && start.getMonthValue() != 12) {
                if (start.getYear() == end.getYear() && start.plusMonths(1).getMonthValue() == end.getMonthValue()) {
                    System.out.println(start);
                    caculateRate("area", area, start, end, checkIndex, collectList);
                    System.out.println(start.plusMonths(1));
                    caculateRate("area", area, start, end, checkIndex, collectList);
                    flag = 3;
                } else if (start.getYear() == end.getYear() && start.getMonthValue() == end.getMonthValue()) {
                    System.out.println(start);
                    caculateRate("area", area, start, end, checkIndex, collectList);
                    flag = 3;
                }
            }
        }


        return collectList;
    }

    @Override
    public List<CheckIncomeBO> groupDiff(CheckIncomeDTO checkIncomeDTO) throws SerException {
        checkSeeIdentity();
        List<CheckIncomeBO> collectList = new ArrayList<>();
        if (StringUtils.isBlank(checkIncomeDTO.getArea())) {
            throw new SerException("项目组不能为空");
        }
        if (StringUtils.isBlank(checkIncomeDTO.getStartTime()) && StringUtils.isBlank(checkIncomeDTO.getEndTime())) {
            throw new SerException("开始时间和结束时间不能为空");
        }
        String startTime = checkIncomeDTO.getStartTime();
        String endTime = checkIncomeDTO.getEndTime();
        LocalDate start = LocalDate.parse(startTime);
        LocalDate end = LocalDate.parse(endTime);
        String group = checkIncomeDTO.getProjectGroup();
        if (end.isBefore(start)) {
            throw new SerException("结束时间不能比开始时间小");
        }

        CheckIndexDTO checkIndexDTO = new CheckIndexDTO();
        CheckIndex checkIndex = checkIndexSer.findOne(checkIndexDTO);

        //获取选取的时间段内所有的年月
        int flag = 0;
        while (start.getYear() != end.getYear() || start.getMonthValue() != end.getMonthValue()) {
            if (flag == 0) {
                System.out.println(start);
                caculateRate("projectGroup", group, start, end, checkIndex, collectList);
            }
            start = start.plusMonths(1);
            if (flag != 3) {//!=3
                flag = 0;
            }
            if (flag != 3 && start.getMonthValue() == 12) {
                System.out.println(start);
                caculateRate("projectGroup", group, start, end, checkIndex, collectList);
                flag = 1;

                if (start.plusYears(1).getYear() <= end.getYear()) {
                    start = start.plusYears(1);
                    start = LocalDate.of(start.getYear(), 1, 1);
                    System.out.println(start);
                    caculateRate("projectGroup", group, start, end, checkIndex, collectList);
                    flag = 2;
                }
            }
            if (flag == 0 && start.getYear() == end.getYear() && start.getMonthValue() != 12) {
                if (start.getYear() == end.getYear() && start.plusMonths(1).getMonthValue() == end.getMonthValue()) {
                    System.out.println(start);
                    caculateRate("projectGroup", group, start, end, checkIndex, collectList);
                    System.out.println(start.plusMonths(1));
                    caculateRate("projectGroup", group, start, end, checkIndex, collectList);
                    flag = 3;
                } else if (start.getYear() == end.getYear() && start.getMonthValue() == end.getMonthValue()) {
                    System.out.println(start);
                    caculateRate("projectGroup", group, start, end, checkIndex, collectList);
                    flag = 3;
                }
            }
        }


        return collectList;
    }

    @Override
    public List<CheckIncomeBO> projectDiff(CheckIncomeDTO checkIncomeDTO) throws SerException {
        checkSeeIdentity();
        List<CheckIncomeBO> collectList = new ArrayList<>();
        if (StringUtils.isBlank(checkIncomeDTO.getArea())) {
            throw new SerException("项目名称不能为空");
        }
        if (StringUtils.isBlank(checkIncomeDTO.getStartTime()) && StringUtils.isBlank(checkIncomeDTO.getEndTime())) {
            throw new SerException("开始时间和结束时间不能为空");
        }
        String startTime = checkIncomeDTO.getStartTime();
        String endTime = checkIncomeDTO.getEndTime();
        LocalDate start = LocalDate.parse(startTime);
        LocalDate end = LocalDate.parse(endTime);
        String pName = checkIncomeDTO.getProjectName();
        if (end.isBefore(start)) {
            throw new SerException("结束时间不能比开始时间小");
        }

        CheckIndexDTO checkIndexDTO = new CheckIndexDTO();
        CheckIndex checkIndex = checkIndexSer.findOne(checkIndexDTO);

        //获取选取的时间段内所有的年月
        int flag = 0;
        while (start.getYear() != end.getYear() || start.getMonthValue() != end.getMonthValue()) {
            if (flag == 0) {
                System.out.println(start);
                caculateRate("projectName", pName, start, end, checkIndex, collectList);
            }
            start = start.plusMonths(1);
            if (flag != 3) {//!=3
                flag = 0;
            }
            if (flag != 3 && start.getMonthValue() == 12) {
                System.out.println(start);
                caculateRate("projectName", pName, start, end, checkIndex, collectList);
                flag = 1;

                if (start.plusYears(1).getYear() <= end.getYear()) {
                    start = start.plusYears(1);
                    start = LocalDate.of(start.getYear(), 1, 1);
                    System.out.println(start);
                    caculateRate("projectName", pName, start, end, checkIndex, collectList);
                    flag = 2;
                }
            }
            if (flag == 0 && start.getYear() == end.getYear() && start.getMonthValue() != 12) {
                if (start.getYear() == end.getYear() && start.plusMonths(1).getMonthValue() == end.getMonthValue()) {
                    System.out.println(start);
                    caculateRate("projectName", pName, start, end, checkIndex, collectList);
                    System.out.println(start.plusMonths(1));
                    caculateRate("projectName", pName, start, end, checkIndex, collectList);
                    flag = 3;
                } else if (start.getYear() == end.getYear() && start.getMonthValue() == end.getMonthValue()) {
                    System.out.println(start);
                    caculateRate("projectName", pName, start, end, checkIndex, collectList);
                    flag = 3;
                }
            }
        }


        return collectList;
    }

    @Override
    public List<String> areaList() throws SerException {
        String[] field = new String[]{"area"};
        String sql = "select area  from incomecheck_checkincome group by area ";
        List<CheckIncome> checkIncomeList = super.findBySql(sql, CheckIncome.class, field);
        List<String> list = checkIncomeList.stream().map(CheckIncome::getArea).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<String> groupList() throws SerException {
        String[] field = new String[]{"projectGroup"};
        String sql = "select projectGroup  from incomecheck_checkincome group by projectGroup ";
        List<CheckIncome> checkIncomeList = super.findBySql(sql, CheckIncome.class, field);
        List<String> list = checkIncomeList.stream().map(CheckIncome::getProjectGroup).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<String> projectList() throws SerException {
        String[] field = new String[]{"projectName"};
        String sql = "select projectName  from incomecheck_checkincome group by projectName ";
        List<CheckIncome> checkIncomeList = super.findBySql(sql, CheckIncome.class, field);
        List<String> list = checkIncomeList.stream().map(CheckIncome::getProjectName).collect(Collectors.toList());
        return list;
    }
}