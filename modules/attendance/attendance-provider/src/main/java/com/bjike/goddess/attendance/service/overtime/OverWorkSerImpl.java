package com.bjike.goddess.attendance.service.overtime;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.attendance.api.VacateAPI;
import com.bjike.goddess.attendance.bo.VacateBO;
import com.bjike.goddess.attendance.bo.overtime.*;
import com.bjike.goddess.attendance.dto.VacateConDTO;
import com.bjike.goddess.attendance.dto.overtime.*;
import com.bjike.goddess.attendance.entity.overtime.OverWork;
import com.bjike.goddess.attendance.enums.AuditStatus;
import com.bjike.goddess.attendance.enums.CountType;
import com.bjike.goddess.attendance.enums.GuideAddrStatus;
import com.bjike.goddess.attendance.enums.OverTimesType;
import com.bjike.goddess.attendance.excel.OverWorkExportExcel;
import com.bjike.goddess.attendance.excel.OverWorkImportExcel;
import com.bjike.goddess.attendance.service.CusPermissionSer;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.OverWorkAuditTO;
import com.bjike.goddess.attendance.to.OverWorkTO;
import com.bjike.goddess.attendance.vo.OverLongAndRelaxDayVO;
import com.bjike.goddess.attendance.vo.OverWorkTimesVO;
import com.bjike.goddess.attendance.vo.PositionAndDepartVO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.PositionUserDetailAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 加班业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attendanceSerCache")
@Service
public class OverWorkSerImpl extends ServiceImpl<OverWork, OverWorkDTO> implements OverWorkSer {

    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private PositionUserDetailAPI positionUserDetailAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private VacateAPI vacateAPI;
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
            flag = cusPermissionSer.getCusPermission("5");
            if (!flag) {
                throw new SerException("您不是相应岗位的人员，不可以操作");
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
            flag = cusPermissionSer.busCusPermission("5");
            if (!flag) {
                throw new SerException("您不是相应岗位的人员，不可以操作");
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
            flag = cusPermissionSer.getCusPermission("5");
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
            flag = cusPermissionSer.busCusPermission("5");
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
            case AUDIT:
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
    public Long countOverWork(OverWorkDTO overWorkDTO) throws SerException {
        if (StringUtils.isNotBlank(overWorkDTO.getOverWorker())) {
            overWorkDTO.getConditions().add(Restrict.eq("overWorker", overWorkDTO.getOverWorker()));
        }
        if (null != overWorkDTO.getAuditStatus()) {
            overWorkDTO.getConditions().add(Restrict.eq("auditStatus", overWorkDTO.getAuditStatus().getCode()));
        }
        Long count = super.count(overWorkDTO);

        return count;
    }

    @Override
    public OverWorkBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        OverWork overWork = super.findById(id);
        OverWorkBO overWorkBO = BeanTransform.copyProperties(overWork, OverWorkBO.class);
        return overWorkBO;
    }

    @Override
    public List<OverWorkBO> listOverWork(OverWorkDTO overWorkDTO) throws SerException {
        if (StringUtils.isNotBlank(overWorkDTO.getOverWorker())) {
            overWorkDTO.getConditions().add(Restrict.eq("overWorker", overWorkDTO.getOverWorker()));
        }
        if (null != overWorkDTO.getAuditStatus()) {
            overWorkDTO.getConditions().add(Restrict.eq("auditStatus", overWorkDTO.getAuditStatus().getCode()));
        }
        overWorkDTO.getSorts().add("createTime=desc");
        List<OverWork> list = super.findByCis(overWorkDTO, true);
        List<OverWorkBO> boList = BeanTransform.copyProperties(list, OverWorkBO.class);

        return boList;
    }


    private Logger log = Logger.getLogger(OverWorkSerImpl.class);

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OverWorkBO addOverWork(OverWorkTO overWorkTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        OverWork overWork = new OverWork();
        BeanTransform.copyProperties(overWorkTO, overWork, "overStartTime", "overEndTime");
        overWork.setOverStartTime(DateUtil.parseDateTime(overWorkTO.getOverStartTime()));
        overWork.setOverEndTime(DateUtil.parseDateTime(overWorkTO.getOverEndTime()));
        overWork.setAuditStatus(AuditStatus.NONE);
        overWork.setEntryer(userBO.getUsername());
        super.save(overWork);

        //如果是项目经理下发的任务，则不用审核，将审核状态改为已通过
        RpcTransmit.transmitUserToken(userToken);
        log.info("当前用户 ：" + JSON.toJSONString(userBO));
        //获取录入人的主职位
        Map<String, String> positMap = positionUserDetailAPI.departPosition(userBO.getUsername());
        log.info("当前用户2 ：" + JSON.toJSONString(positMap));


        RpcTransmit.transmitUserToken(userToken);
        if (null != positMap) {
            String position = "";
            for (Map.Entry str : positMap.entrySet()) {
                position = (String) str.getValue();
            }
            if (position.contains("项目经理")) {
                log.info("当前用户3 ：" + position);
                OverWork temp = super.findById(overWork.getId());
                temp.setAuditStatus(AuditStatus.AGREE);
                super.update(temp);
            }
        }
        return BeanTransform.copyProperties(overWork, OverWorkBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteOverWork(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        OverWork overWork = super.findById(id);
        if (null != overWork) {
            super.remove(id);
        }
    }

    @Override
    public List<AreaBO> areaList() throws SerException {
        List<AreaBO> area = BeanTransform.copyProperties(departmentDetailAPI.findArea(), AreaBO.class);
        return area;
    }

    @Override
    public List<String> peopleList() throws SerException {
        List<UserBO> userBOS = positionDetailUserAPI.findUserList();
        List<String> userList = new ArrayList<>();
        if (userBOS != null && userBOS.size() > 0) {
            userList = userBOS.stream().filter(str -> StringUtils.isNotBlank(str.getUsername()))
                    .map(UserBO::getUsername).collect(Collectors.toList());
        }
        return userList;
    }

    @Override
    public PositionAndDepartVO getPositAndDepart(String overWorker) throws SerException {
        PositionAndDepartVO vo = new PositionAndDepartVO();
        Map<String, String> positMap = positionUserDetailAPI.departPosition(overWorker);
        if (positMap != null) {
            for (Map.Entry str : positMap.entrySet()) {
                vo.setDepart((String) str.getKey());
                vo.setPosition((String) str.getValue());
            }
        }
        return vo;
    }

    @Override
    public OverLongAndRelaxDayVO caculateTime(OverLongAndRelaxdayDTO overDTO) throws SerException {
        //加班时长：
        //若有午休： 加班时长= 结束时间 - 开始时间 -1.5午休
        //若没有午休：  加班时长= 结束时间 - 开始时间
        Double overLong = 0d;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(overDTO.getStartTime(), formatter);
        LocalDateTime end = LocalDateTime.parse(overDTO.getEndTime(), formatter);
        Long hour = end.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - start.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        overLong = new BigDecimal((double) hour / 1000 / 60 / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        if (overDTO.getNoonBreakOr().equals(true)) {
            overLong = overLong - 1.5d;
        }
        //计算可休天数= 加班时长/8
        Double relaxDay = overLong / 8;

        OverLongAndRelaxDayVO vo = new OverLongAndRelaxDayVO();
        vo.setOverLong(overLong);
        vo.setRelaxDay(relaxDay);

        return vo;
    }

    @Override
    public Long countAudit(OverWorkDTO overWorkDTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        if (StringUtils.isNotBlank(overWorkDTO.getOverWorker())) {
            overWorkDTO.getConditions().add(Restrict.eq("overWorker", overWorkDTO.getOverWorker()));
        }
        if (null != overWorkDTO.getAuditStatus()) {
            overWorkDTO.getConditions().add(Restrict.eq("auditStatus", overWorkDTO.getAuditStatus().getCode()));
        }
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        if (!"admin".equals(userBO.getUsername())) {
            overWorkDTO.getConditions().add(Restrict.eq("charger", userBO.getUsername()));
        }

        Long count = super.count(overWorkDTO);

        return count;
    }

    @Override
    public List<OverWorkBO> listAudit(OverWorkDTO overWorkDTO) throws SerException {
        checkSeeIdentity();
        String userToken = RpcTransmit.getUserToken();
        if (StringUtils.isNotBlank(overWorkDTO.getOverWorker())) {
            overWorkDTO.getConditions().add(Restrict.eq("overWorker", overWorkDTO.getOverWorker()));
        }
        if (null != overWorkDTO.getAuditStatus()) {
            overWorkDTO.getConditions().add(Restrict.eq("auditStatus", overWorkDTO.getAuditStatus().getCode()));
        }

        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        if (!"admin".equals(userBO.getUsername())) {
            overWorkDTO.getConditions().add(Restrict.eq("charger", userBO.getUsername()));
        }

        overWorkDTO.getSorts().add("createTime=desc");
        List<OverWork> list = super.findByCis(overWorkDTO, true);
        List<OverWorkBO> boList = BeanTransform.copyProperties(list, OverWorkBO.class);

        return boList;
    }

    @Override
    public OverWorkBO auditOverWork(OverWorkAuditTO auditTO) throws SerException {
        checkAddIdentity();
        String userToken = RpcTransmit.getUserToken();
        if (StringUtils.isBlank(auditTO.getId())) {
            throw new SerException("id不能为空");
        }
        if (null == auditTO.getAuditStatus()) {
            throw new SerException("审核状态AuditStatus不能为空");
        }
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        OverWork overWork = super.findById(auditTO.getId());
        if (!userBO.getUsername().equals(overWork.getCharger())) {
            throw new SerException("您不是审核人，不能审核");
        }
        if (null != overWork) {
            overWork.setAuditAdvice(auditTO.getAuditAdvice());
            overWork.setAuditStatus(auditTO.getAuditStatus());
            overWork.setAuditTime(LocalDateTime.now());
            overWork.setModifyTime(LocalDateTime.now());
            super.update(overWork);
        }
        return BeanTransform.copyProperties(overWork, OverWorkBO.class);
    }

    @Override
    public Long countRestDay(OverWorkRestDayDTO overWorkRestDayDTO) throws SerException {
        //查询员工编号
        //查询员工姓名
        //查询剩余加班天数
        //查询核算截至时间
        //若传加班人员条件,只能看但前加班人员的截至当前月的剩余加班数
        Long count = 0L;
        if (StringUtils.isNotBlank(overWorkRestDayDTO.getOverWorker())) {
            count = 1L;
        } else {
            List<String> userList = this.peopleList();
            if (userList != null && userList.size() > 0) {
                count = (long) userList.size();
            }
        }
        return count;
    }

    @Override
    public List<OverWorkRestDayBO> listRestDay(OverWorkRestDayDTO overWorkRestDayDTO) throws SerException {
        List<OverWorkRestDayBO> returnList = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        //查询员工编号
        //查询员工姓名
        //查询剩余加班天数
        //查询核算截至时间
        List<String> subUserList = new ArrayList<>();
        List<String> userList = new ArrayList<>();
        if (StringUtils.isBlank(overWorkRestDayDTO.getOverWorker())) {
            userList = this.peopleList();
        } else {
            userList.add(overWorkRestDayDTO.getOverWorker());
        }
        if (null != userList) {
            //分页处理
            Integer page = overWorkRestDayDTO.getPage();
            Integer limmit = overWorkRestDayDTO.getLimit();
            int startLine = page * limmit;
            int endLine = page * limmit + limmit;
            if (userList.size() - 1 >= endLine) {
                subUserList = userList.subList(startLine, endLine);
            } else if (userList.size() - 1 < endLine) {
                subUserList = userList.subList(startLine, userList.size());
            }
            if (subUserList != null && subUserList.size() > 0) {
                for (String userName : subUserList) {
                    //查加班开始时间在前三个月到本月的21号的累积
                    LocalDateTime now = LocalDateTime.now();
                    if (StringUtils.isNotBlank(overWorkRestDayDTO.getCheckEndTime())) {
                        now = DateUtil.parseDateTime(overWorkRestDayDTO.getCheckEndTime() + "-20");
                    }
                    LocalDateTime thirdMonthAgo = now.minusMonths(3);

                    LocalDateTime nowDate = LocalDateTime.of(now.getYear(), now.getMonthValue(), 21, 23, 59, 59);
                    LocalDateTime thirdAgoMonth = LocalDateTime.of(thirdMonthAgo.getYear(), thirdMonthAgo.getMonthValue(), 20, 00, 00, 01);

                    OverWorkDTO dto = new OverWorkDTO();
                    dto.getConditions().add(Restrict.between("overStartTime", new LocalDateTime[]{thirdAgoMonth, nowDate}));
                    dto.getConditions().add(Restrict.eq("overWorker", userName));
                    List<OverWork> listOver = super.findByCis(dto);
                    //计算所有加班天数
                    Double allOverDay = listOver == null ? 0d : listOver.stream().mapToDouble(OverWork::getRelaxDay).sum();
                    //查询上月20到本月21号所有请假天数
                    LocalDateTime lastMonDate = now.minusMonths(1).minusDays(2).plusSeconds(2);
                    VacateConDTO vacateConDTO = new VacateConDTO();
                    vacateConDTO.setEmpName(userName);
                    vacateConDTO.setStartTime(String.valueOf(lastMonDate));
                    vacateConDTO.setEndTime(String.valueOf(nowDate));
                    List<VacateBO> vacateBOList = vacateAPI.findByCon(vacateConDTO);
                    Double vacateDay = vacateBOList == null ? 0d : vacateBOList.stream().mapToDouble(VacateBO::getTime).sum();

                    //计算剩余加班天数
                    Double restOverDay = allOverDay - vacateDay;

                    //查询员工编号
                    RpcTransmit.transmitUserToken(userToken);
                    UserBO userBO = userAPI.findByUsername(userName);
                    RpcTransmit.transmitUserToken(userToken);

                    OverWorkRestDayBO overWorkRestDayBO = new OverWorkRestDayBO();
                    overWorkRestDayBO.setEmpNum(userBO != null ? userBO.getEmployeeNumber() : "");
                    overWorkRestDayBO.setEmpName(userName);
                    overWorkRestDayBO.setRestDay(String.valueOf(restOverDay));
                    overWorkRestDayBO.setCheckEndTime(nowDate + "");
                    returnList.add(overWorkRestDayBO);

                }
            }
        }
        return returnList;
    }

    @Override
    public Double overDay(String name, LocalDate endTime) throws SerException {
        LocalDate s = null;
        if (endTime.getDayOfMonth() > 21) {
            s = endTime.minusMonths(2);
        } else {
            s = endTime.minusMonths(3);
        }
        LocalDateTime start = LocalDateTime.of(s.getYear(), s.getMonthValue(), 20, 00, 00, 01);
        LocalDateTime end = LocalDateTime.of(endTime.getYear(), endTime.getMonthValue(), endTime.getDayOfMonth(), 00, 00, 01);
        OverWorkDTO dto = new OverWorkDTO();
        dto.getConditions().add(Restrict.between("overStartTime", new LocalDateTime[]{start, end}));
        dto.getConditions().add(Restrict.eq("overWorker", name));
        List<OverWork> listOver = super.findByCis(dto);
        //计算所有加班天数
        Double allOverDay = listOver == null ? 0d : listOver.stream().mapToDouble(OverWork::getRelaxDay).sum();
        return allOverDay;
    }

    @Override
    public List<OverWorkBO> myListOverWork(PhoneMyOverWorkDTO phoneMyOverWorkDTO) throws SerException {
        OverWorkDTO dto = BeanTransform.copyProperties(phoneMyOverWorkDTO, OverWorkDTO.class, "serialVersionUID");
        dto.getConditions().add(Restrict.eq("overWorker", phoneMyOverWorkDTO.getOverWorker()));

        dto.getSorts().add("createTime=desc");
        List<OverWork> list = super.findByCis(dto, true);
        List<OverWorkBO> bo = BeanTransform.copyProperties(list, OverWorkBO.class);

        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq("username",phoneMyOverWorkDTO.getOverWorker()));
        List<UserBO> listUserBO = userAPI.findByCis( userDTO );
        if( listUserBO != null && listUserBO.size()>0 ){
            for( OverWorkBO u : bo ){
                u.setPic( listUserBO != null && listUserBO.size()>0 ? listUserBO.get(0).getHeadSculpture():"" );

            }
        }

        return bo;
    }

    @Override
    public List<OverWorkBO> myEntryList(PhoneMyEntryOverWorkDTO phoneMyEntryOverWorkDTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        OverWorkDTO dto = BeanTransform.copyProperties(phoneMyEntryOverWorkDTO, OverWorkDTO.class, "serialVersionUID");
        Map<String, String> positMap = positionUserDetailAPI.departPosition(userBO.getUsername());
        RpcTransmit.transmitUserToken(userToken);
        Boolean isManage = false;
        if (null != positMap) {
            String position = "";
            for (Map.Entry str : positMap.entrySet()) {
                position = (String) str.getValue();
            }
            if (position.contains("项目经理") || position.contains("模块负责人")) {
                isManage = true;
            }
        }
        if (!isManage) {
            //说明是不是项目经理或模块负责人。就显示录入人
            dto.getConditions().add(Restrict.eq("entryer", phoneMyEntryOverWorkDTO.getEntryer()));
        }
        //是项目经理就查所有，不传条件
        dto.getSorts().add("createTime=desc");
        List<OverWork> list = super.findByCis(dto, true);
        List<OverWorkBO> bo = BeanTransform.copyProperties(list, OverWorkBO.class);
        if( bo != null && bo.size()>0 ) {
            List<String> overerList = bo.stream().filter(str->StringUtils.isNotBlank(str.getOverWorker())).map(OverWorkBO::getOverWorker).collect(Collectors.toList());
            UserDTO userDTO = new UserDTO();
            userDTO.getConditions().add(Restrict.in("username",overerList));
            List<UserBO> listUserBO = userAPI.findByCis( userDTO );
            if( listUserBO != null && listUserBO.size()>0 ){
                for( OverWorkBO u : bo ){
                    if (StringUtils.isNotBlank(u.getOverWorker())) {
                        Optional<UserBO> temp = listUserBO.stream().findFirst().filter(str->u.getOverWorker().equals(str.getUsername()));
                        if( temp.isPresent() ){
                            u.setPic( temp.get().getHeadSculpture() );
                        }
                    }
                }
            }
        }
        return bo;
    }

    @Override
    public List<OverWorkBO> myAuditList(PhoneMyEntryOverWorkDTO phoneMyEntryOverWorkDTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        OverWorkDTO dto = BeanTransform.copyProperties(phoneMyEntryOverWorkDTO, OverWorkDTO.class, "serialVersionUID");

        RpcTransmit.transmitUserToken(userToken);
        Map<String, String> positMap = positionUserDetailAPI.departPosition(userBO.getUsername());
        RpcTransmit.transmitUserToken(userToken);
        Boolean isManage = false;
        if (null != positMap) {
            String position = "";
            for (Map.Entry str : positMap.entrySet()) {
                position = (String) str.getValue();
            }
            if (position.contains("项目经理")) {
                isManage = true;
            }
        }

        if (!isManage) {
            //说明是不是项目经理
            dto.getConditions().add(Restrict.eq("charger", phoneMyEntryOverWorkDTO.getEntryer()));
        }
        //是项目经理就查所有，不传条件

        dto.getConditions().add(Restrict.eq("auditStatus", AuditStatus.NONE));
        dto.getSorts().add("createTime=desc");
        List<OverWork> list = super.findByCis(dto, true);
        List<OverWorkBO> bo = BeanTransform.copyProperties(list, OverWorkBO.class);
        if( bo != null && bo.size()>0 ) {
            List<String> overerList = bo.stream().filter(str->StringUtils.isNotBlank(str.getOverWorker())).map(OverWorkBO::getOverWorker).collect(Collectors.toList());
            UserDTO userDTO = new UserDTO();
            userDTO.getConditions().add(Restrict.in("username",overerList));
            List<UserBO> listUserBO = userAPI.findByCis( userDTO );
            if( listUserBO != null && listUserBO.size()>0 ){
                for( OverWorkBO u : bo ){
                    if (StringUtils.isNotBlank(u.getOverWorker())) {
                        Optional<UserBO> temp = listUserBO.stream().findFirst().filter(str->u.getOverWorker().equals(str.getUsername()));
                        if( temp.isPresent() ){
                            u.setPic( temp.get().getHeadSculpture() );
                        }
                    }
                }
            }
        }
        return bo;
    }



    @Override
    public OverWorkBO getPhoneOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();

        RpcTransmit.transmitUserToken(userToken);
        Map<String, String> positMap = positionUserDetailAPI.departPosition(userBO.getUsername());
        RpcTransmit.transmitUserToken(userToken);
        Boolean isManage = false;
        if (null != positMap) {
            String position = "";
            for (Map.Entry str : positMap.entrySet()) {
                position = (String) str.getValue();
            }
            if ( position.contains("项目经理")) {
                isManage = true;
            }
        }

        OverWork overWork = super.findById(id);
        OverWorkBO bo = BeanTransform.copyProperties(overWork, OverWorkBO.class);
        if (isManage && AuditStatus.NONE.equals(overWork.getAuditStatus())) {
            bo.setHasAuditIs(true);
        } else {
            bo.setHasAuditIs(false);
        }
        if( StringUtils.isNotBlank(bo.getOverWorker())){
            UserBO overer = userAPI.findByUsername( bo.getOverWorker());
            bo.setPic( overer != null ?overer.getHeadSculpture():"");
        }

        return bo;
    }


    @Override
    public List<OverWorkMailBO> outWorkCountMail(OverWorkDTO dto) throws SerException {
        List<OverWork> list = all(dto);
        Set<String> departs = list.stream().map(OverWork::getDepart).collect(Collectors.toSet());
        List<OverWorkMailBO> bos = new ArrayList<>();
        double outWorkTimeSum = 0;
        double normalTimeSum = 0;
        int numSum = 0;   //总合计
        for (String depart : departs) {
            double outWorkTimeDepart = 0;
            double normalTimeDepart = 0;
            int numDepart = 0;   //部门合计
            OverWorkMailBO bo = new OverWorkMailBO();
            bo.setDepart(depart);
            List<OutWorkDBO> sons = new ArrayList<>();
            Set<String> names = list.stream().filter(overWork -> depart.equals(overWork.getDepart())).map(OverWork::getOverWorker).collect(Collectors.toSet());
            for (String name : names) {
                double normalTime = list.stream().filter(overWork -> "普通加班".equals(overWork.getOverType()) && name.equals(overWork.getOverWorker())).mapToDouble(OverWork::getOverLong).sum();
                double outWorkTime = list.stream().filter(overWork -> name.equals(overWork.getOverWorker())).mapToDouble(OverWork::getOverLong).sum();
                normalTimeDepart += normalTime;
                outWorkTimeDepart += outWorkTime;
                normalTimeSum += normalTime;
                outWorkTimeSum += outWorkTime;
                OutWorkDBO dbo = new OutWorkDBO(name, outWorkTime, normalTime);
                sons.add(dbo);
                numDepart++;
                numSum++;
            }
            bo.setSons(sons);
            bos.add(bo);
            OverWorkMailBO count = new OverWorkMailBO();
            count.setDepart("合计");
            OutWorkDBO dbo = new OutWorkDBO(numDepart + "", outWorkTimeDepart, normalTimeDepart);
            List<OutWorkDBO> sons1 = new ArrayList<>();
            sons1.add(dbo);
            count.setSons(sons1);
            bos.add(count);
        }
        OutWorkDBO dbo = new OutWorkDBO(numSum + "", outWorkTimeSum, normalTimeSum);
        OverWorkMailBO count = new OverWorkMailBO();
        count.setDepart("总合计");
        List<OutWorkDBO> sons = new ArrayList<>();
        sons.add(dbo);
        count.setSons(sons);
        bos.add(count);
        return bos;
    }

    @Override
    public OverWorkCountBO outWorkCount(OverWorkDTO dto) throws SerException {
        String time = dto.getStartTime() + "-" + dto.getEndTime();
        List<OverWork> list = all(dto);
        Set<String> areas = list.stream().map(OverWork::getArea).collect(Collectors.toSet());
        List<OutWorkABO> abos = new ArrayList<>();
        double outWorkTimeSum = 0;
        double normalTimeSum = 0;
        int numSum = 0;   //总合计
        for (String area : areas) {
            OutWorkABO abo = new OutWorkABO();
            abo.setArea(area);
            List<OutWorkBBO> bbos = new ArrayList<>();
            Set<String> departs = list.stream().filter(overWork -> area.equals(overWork.getArea())).map(OverWork::getDepart).collect(Collectors.toSet());
            for (String depart : departs) {
                double outWorkTimeDepart = 0;
                double normalTimeDepart = 0;
                int numDepart = 0;   //部门合计
                OutWorkBBO bbo = new OutWorkBBO();
                bbo.setDepart(depart);
                List<OutWorkCBO> cbos = new ArrayList<>();
                Set<String> positions = list.stream().filter(overWork -> area.equals(overWork.getArea()) && depart.equals(overWork.getDepart())).map(OverWork::getPosition).collect(Collectors.toSet());
                for (String position : positions) {
                    OutWorkCBO cbo = new OutWorkCBO();
                    List<OutWorkDBO> dbos = new ArrayList<>();
                    Set<String> names = list.stream().filter(overWork -> area.equals(overWork.getArea()) && depart.equals(overWork.getDepart()) && position.equals(overWork.getPosition())).map(OverWork::getOverWorker).collect(Collectors.toSet());
                    for (String name : names) {
                        double normalTime = list.stream().filter(overWork -> "普通加班".equals(overWork.getOverType()) && name.equals(overWork.getOverWorker())).mapToDouble(OverWork::getOverLong).sum();
                        double outWorkTime = list.stream().filter(overWork -> name.equals(overWork.getOverWorker())).mapToDouble(OverWork::getOverLong).sum();
                        normalTimeDepart += normalTime;
                        outWorkTimeDepart += outWorkTime;
                        normalTimeSum += normalTime;
                        outWorkTimeSum += outWorkTime;
                        OutWorkDBO dbo = new OutWorkDBO(name, outWorkTime, normalTime);
                        dbos.add(dbo);
                        numDepart++;
                        numSum++;
                    }
                    cbo.setSons(dbos);
                    cbos.add(cbo);
                }
                bbo.setSons(cbos);
                bbos.add(bbo);
                OutWorkBBO departCount = count(outWorkTimeDepart, normalTimeDepart, numDepart, null);
                bbos.add(departCount);
            }
            abo.setSons(bbos);
            abos.add(abo);
        }
        OutWorkBBO sum = count(outWorkTimeSum, normalTimeSum, numSum, "总合计");
        OutWorkABO abo = new OutWorkABO();
        List<OutWorkBBO> bbos = new ArrayList<>();
        bbos.add(sum);
        abo.setSons(bbos);
        abos.add(abo);
        OverWorkCountBO countBO = new OverWorkCountBO();
        countBO.setTime(time);
        countBO.setSons(abos);
        return countBO;
    }

    private OutWorkBBO count(double outWorkTimeDepart, double normalTimeDepart, int num, String type) throws SerException {
        OutWorkBBO bo = new OutWorkBBO();
        if ("总合计".equals(type)) {
            bo.setDepart("总合计");
        } else {
            bo.setDepart("合计");
        }
        List<OutWorkCBO> cbos = new ArrayList<>();
        OutWorkCBO cbo = new OutWorkCBO();
        List<OutWorkDBO> dbos = new ArrayList<>();
        OutWorkDBO count = new OutWorkDBO();
        count.setName(num + "人");
        count.setOutWorkTime(outWorkTimeDepart);
        count.setNormalTime(normalTimeDepart);
        dbos.add(count);
        cbo.setSons(dbos);
        bo.setSons(cbos);
        return bo;
    }

    private List<OverWork> all(OverWorkDTO dto) throws SerException {
        CountType countType = dto.getCountType();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT id FROM attendance_overwork" +
                "        WHERE ");
        if (CountType.DEPART.equals(countType)) {
            String[] departs = dto.getDeparts();
            if (null == departs) {
                throw new SerException("部门汇总必须选择部门");
            }
            String[] departTemps = new String[departs.length];
            int i = 0;
            for (String s : departs) {
                departTemps[i] = "'" + s + "'";
                i++;
            }
            String departStr = StringUtils.join(departTemps, ",");
            sb.append("depart in (" + departStr + ") AND ");
        }
        sb.append("('" + dto.getStartTime() + "' BETWEEN DATE_FORMAT(overStartTime, '%Y-%m-%d') AND DATE_FORMAT(overEndTime, '%Y-%m-%d'))" +
                "   AND ('" + dto.getEndTime() + "' BETWEEN DATE_FORMAT(overStartTime, '%Y-%m-%d') AND DATE_FORMAT(overEndTime, '%Y-%m-%d'))");
        List<OverWork> list = super.findBySql(sb.toString(), OverWork.class, new String[]{"id"});
        List<OverWork> overWorks = new ArrayList<>();
        if (null != list) {
            List<String> ids = list.stream().map(OverWork::getId).collect(Collectors.toList());
            for (String s : ids) {
                overWorks.add(super.findById(s));
            }
        }
        return overWorks;
    }

    @Override
    public OverWorkTimesVO userOverTimeCollect(OverTimesDTO overTimesDTO) throws SerException {
        OverWorkTimesVO overWorkTimesVO = new OverWorkTimesVO();

        String userName = overTimesDTO.getOverWorker();
        OverTimesType overTimesType = overTimesDTO.getOverTimesType();
        if (StringUtils.isBlank(userName)) {
            throw new SerException("加班人员不能为空");
        }
        if (null == overTimesType) {
            throw new SerException("汇总时间类型不能为空");
        }

        LocalDate timeBegan = LocalDate.now();
        LocalDate timeEnd = LocalDate.now();
        switch (overTimesDTO.getOverTimesType()) {
            case WEEK:
                if (StringUtils.isBlank("" + overTimesDTO.getYear()) || StringUtils.isBlank("" + overTimesDTO.getMonth()) || StringUtils.isBlank("" + overTimesDTO.getWeek())) {
                    throw new SerException("年份或月份或周数不能为空");
                }
                LocalDate[] dateDuring = DateUtil.getWeekTimes(overTimesDTO.getYear(), overTimesDTO.getMonth(), overTimesDTO.getWeek());
                timeBegan = dateDuring[0];
                timeEnd = dateDuring[1];
                break;
            case QUART:
                if (StringUtils.isBlank("" + overTimesDTO.getYear()) || overTimesDTO.getQuart() > 4 || overTimesDTO.getQuart() < 1) {
                    throw new SerException("年份(year)或季度（quart）不能为空,且year大于1900,quart在1～4之间");
                }
                switch (overTimesDTO.getQuart()) {
                    case 1:
                        timeBegan = DateUtil.parseDate(overTimesDTO.getYear() + "-01-01");
                        timeEnd = DateUtil.parseDate(overTimesDTO.getYear() + "-03-31");
                        break;
                    case 2:
                        timeBegan = DateUtil.parseDate(overTimesDTO.getYear() + "-04-01");
                        timeEnd = DateUtil.parseDate(overTimesDTO.getYear() + "-06-30");
                        break;
                    case 3:
                        timeBegan = DateUtil.parseDate(overTimesDTO.getYear() + "-07-01");
                        timeEnd = DateUtil.parseDate(overTimesDTO.getYear() + "-09-31");
                        break;
                    case 4:
                        timeBegan = DateUtil.parseDate(overTimesDTO.getYear() + "-10-01");
                        timeEnd = DateUtil.parseDate(overTimesDTO.getYear() + "-12-31");
                        break;
                    default:
                        break;
                }
                break;
            default:
                return null;
        }
        if (overTimesType.equals(OverTimesType.WEEK)) {
            while (timeBegan.isBefore(timeEnd) || timeBegan.isEqual(timeEnd)) {
                DayOfWeek week = timeBegan.getDayOfWeek();
                LocalDateTime start = LocalDateTime.of(timeBegan.getYear(), timeBegan.getMonth(), timeBegan.getDayOfMonth(), 0, 0, 1);
                LocalDateTime end = LocalDateTime.of(timeBegan.getYear(), timeBegan.getMonth(), timeBegan.getDayOfMonth(), 12, 59, 59);

                OverWorkDTO overWorkDTO = new OverWorkDTO();
                overWorkDTO.getConditions().add(Restrict.eq("overWorker", userName));
                overWorkDTO.getConditions().add(Restrict.lt_eq("overStartTime", start));
                overWorkDTO.getConditions().add(Restrict.lt_eq("overEndTime", end));
                Long count = super.count(overWorkDTO);

                switch (week) {
                    case MONDAY:
                        overWorkTimesVO.setFirst("" + count);
                        break;
                    case TUESDAY:
                        overWorkTimesVO.setSecond("" + count);
                        break;
                    case WEDNESDAY:
                        overWorkTimesVO.setThird("" + count);
                        break;
                    case THURSDAY:
                        overWorkTimesVO.setFour("" + count);
                        break;
                    case FRIDAY:
                        overWorkTimesVO.setFive("" + count);
                        break;
                    case SATURDAY:
                        overWorkTimesVO.setSix("" + count);
                        break;
                    case SUNDAY:
                        overWorkTimesVO.setSeven("" + count);
                        break;
                    default:
                        break;

                }
                timeBegan = timeBegan.plusDays(1);
            }
            overWorkTimesVO.setFirst(StringUtils.isBlank(overWorkTimesVO.getFirst()) ? 0 + "" : overWorkTimesVO.getFirst());
            overWorkTimesVO.setSecond(StringUtils.isBlank(overWorkTimesVO.getSecond()) ? 0 + "" : overWorkTimesVO.getSecond());
            overWorkTimesVO.setThird(StringUtils.isBlank(overWorkTimesVO.getThird()) ? 0 + "" : overWorkTimesVO.getThird());
            overWorkTimesVO.setFour(StringUtils.isBlank(overWorkTimesVO.getFour()) ? 0 + "" : overWorkTimesVO.getFour());
            overWorkTimesVO.setFive(StringUtils.isBlank(overWorkTimesVO.getFive()) ? 0 + "" : overWorkTimesVO.getFive());
            overWorkTimesVO.setSix(StringUtils.isBlank(overWorkTimesVO.getSix()) ? 0 + "" : overWorkTimesVO.getSix());
            overWorkTimesVO.setSeven(StringUtils.isBlank(overWorkTimesVO.getSeven()) ? 0 + "" : overWorkTimesVO.getSeven());

        } else if (overTimesType.equals(OverTimesType.QUART)) {
            while (timeEnd.getYear() == timeBegan.getYear() && timeEnd.getMonthValue() - timeBegan.getMonthValue() <= 2 && timeEnd.getMonthValue() - timeBegan.getMonthValue() >= 0) {
                int monthMinus = timeEnd.getMonthValue() - timeBegan.getMonthValue();
                LocalDateTime start = LocalDateTime.of(timeBegan.getYear(), timeBegan.getMonth(), timeBegan.getDayOfMonth(), 0, 0, 1);
                LocalDateTime end = start.with(TemporalAdjusters.lastDayOfMonth());

                OverWorkDTO overWorkDTO = new OverWorkDTO();
                overWorkDTO.getConditions().add(Restrict.eq("overWorker", userName));
                overWorkDTO.getConditions().add(Restrict.lt_eq("overStartTime", start));
                overWorkDTO.getConditions().add(Restrict.lt_eq("overEndTime", end));
                Long count = super.count(overWorkDTO);

                switch (monthMinus) {
                    case 2:
                        overWorkTimesVO.setFirstMonth(start + "月-" + count);
                        break;
                    case 1:
                        overWorkTimesVO.setSecndMonth(start + "月-" + count);
                        break;
                    case 0:
                        overWorkTimesVO.setThirdMonth(start + "月-" + count);
                        break;
                    default:
                        break;

                }

                timeBegan = timeBegan.plusMonths(1);
            }
            overWorkTimesVO.setFirstMonth(StringUtils.isBlank(overWorkTimesVO.getFirstMonth()) ? 0 + "" : overWorkTimesVO.getFirstMonth());
            overWorkTimesVO.setSecndMonth(StringUtils.isBlank(overWorkTimesVO.getSecndMonth()) ? 0 + "" : overWorkTimesVO.getSecndMonth());
            overWorkTimesVO.setThirdMonth(StringUtils.isBlank(overWorkTimesVO.getThirdMonth()) ? 0 + "" : overWorkTimesVO.getThirdMonth());
        }

        overWorkTimesVO.setOverTimesType(overTimesType);
        overWorkTimesVO.setUserName(userName);


        return overWorkTimesVO;
    }

    @Override
    public byte[] exportExcel(OverWorkDTO dto) throws SerException {
        List<OverWork> overWorks = super.findByCis(dto);
        List<OverWorkBO> overWorkBOs = BeanTransform.copyProperties(overWorks, OverWorkBO.class, false);
        List<OverWorkExportExcel> overWorkExportExcels = BeanTransform.copyProperties(overWorkBOs, OverWorkExportExcel.class, "noonBreakOr", "auditStatus");
        List<AuditStatus> auditStatuses = overWorkBOs.stream().map(OverWorkBO::getAuditStatus).collect(Collectors.toList());
        List<Boolean> noonBreakOrs = overWorkBOs.stream().map(OverWorkBO::getNoonBreakOr).collect(Collectors.toList());

        if (null != overWorkExportExcels && overWorkExportExcels.size() > 0 && auditStatuses.size() == noonBreakOrs.size() && overWorkExportExcels.size() == noonBreakOrs.size()) {
            for (int i = 0; i < overWorkBOs.size(); i++) {
                overWorkExportExcels.get(i).setAuditStatus(transFormAuditStatus(auditStatuses.get(i)));
                overWorkExportExcels.get(i).setNoonBreakOr(transFormNoonBreakOr(noonBreakOrs.get(i)));
            }
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(overWorkExportExcels, excel);
        return bytes;
    }

    @Override
    public byte[] templateExcel() throws SerException {
        OverWorkImportExcel overWorkImportExcel = new OverWorkImportExcel();
        List<OverWorkImportExcel> overWorkImportExcels = new ArrayList<>(0);
        overWorkImportExcels.add(overWorkImportExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(overWorkImportExcels, excel);
        return bytes;
    }

    @Transactional
    @Override
    public void upload(List<OverWorkImportExcel> tos) throws SerException {
        List<OverWorkBO> overWorkBOs = BeanTransform.copyProperties(tos, OverWorkBO.class, "noonBreakOr", "auditStatus");
        List<String> auditStatus = tos.stream().map(OverWorkImportExcel::getAuditStatus).collect(Collectors.toList());
        List<String> noonBreakOrs = tos.stream().map(OverWorkImportExcel::getNoonBreakOr).collect(Collectors.toList());
//        if(overWorkBOs.size() == auditStatus.size() && overWorkBOs.size() == noonBreakOrs.size()){
        for (int i = 0; i < overWorkBOs.size(); i++) {
            overWorkBOs.get(i).setAuditStatus(transFormString(auditStatus.get(i)));
            overWorkBOs.get(i).setNoonBreakOr(transFormNoonBreakOr(noonBreakOrs.get(i)));
        }
        List<OverWork> overWorks = BeanTransform.copyProperties(overWorkBOs, OverWork.class, true);
        super.save(overWorks);
    }

    private String transFormNoonBreakOr(Boolean tar) throws SerException {
        String str = "";
        if (tar) {
            str = "是";
        } else {
            str = "否";
        }
        return str;
    }

    private Boolean transFormNoonBreakOr(String string) throws SerException {
        Boolean tar = false;
        if ("是".equals(string)) {
            tar = true;
        }
        return tar;
    }

    private String transFormAuditStatus(AuditStatus auditStatus) throws SerException {
        String string = "";
        switch (auditStatus) {
            case NONE:
                string = "未处理";
                break;
            case AGREE:
                string = "通过";
                break;
            case REJECT:
                string = "不通过";
                break;
            default:
                string = "";
                break;
        }
        return string;
    }

    private AuditStatus transFormString(String string) throws SerException {
        AuditStatus auditStatus = null;
        switch (string) {
            case "未处理":
                auditStatus = AuditStatus.NONE;
                break;
            case "通过":
                auditStatus = AuditStatus.AGREE;
                break;
            case "不通过":
                auditStatus = AuditStatus.REJECT;
                break;
            default:
                auditStatus = AuditStatus.NONE;
                break;
        }
        return auditStatus;
    }


}