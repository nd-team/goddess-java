package com.bjike.goddess.attendance.service.overtime;

import com.bjike.goddess.attendance.api.VacateAPI;
import com.bjike.goddess.attendance.bo.VacateBO;
import com.bjike.goddess.attendance.bo.overtime.AreaBO;
import com.bjike.goddess.attendance.bo.overtime.OverWorkBO;
import com.bjike.goddess.attendance.bo.overtime.OverWorkRestDayBO;
import com.bjike.goddess.attendance.dto.VacateConDTO;
import com.bjike.goddess.attendance.dto.overtime.OverLongAndRelaxdayDTO;
import com.bjike.goddess.attendance.dto.overtime.OverWorkRestDayDTO;
import com.bjike.goddess.attendance.entity.Vacate;
import com.bjike.goddess.attendance.enums.AuditStatus;
import com.bjike.goddess.attendance.to.overtime.OverWorkAuditTO;
import com.bjike.goddess.attendance.to.overtime.OverWorkTO;
import com.bjike.goddess.attendance.vo.overtime.OverLongAndRelaxDayVO;
import com.bjike.goddess.attendance.vo.overtime.PositionAndDepartVO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.attendance.dto.overtime.OverWorkDTO;
import com.bjike.goddess.attendance.entity.overtime.OverWork;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.PositionUserDetailAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private UserAPI userAPI;
    @Autowired
    private VacateAPI vacateAPI;


    @Override
    public Long countOverWork(OverWorkDTO overWorkDTO) throws SerException {
        if (StringUtils.isNotBlank(overWorkDTO.getOverWorker())){
            overWorkDTO.getConditions().add(Restrict.eq("overWorker", overWorkDTO.getOverWorker()));
        }
        if( null !=overWorkDTO.getAuditStatus() ){
            overWorkDTO.getConditions().add(Restrict.eq("auditStatus", overWorkDTO.getAuditStatus().getCode()));
        }
        Long count = super.count(overWorkDTO);

        return count;
    }

    @Override
    public OverWorkBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        OverWork overWork = super.findById( id );
        OverWorkBO overWorkBO = BeanTransform.copyProperties(overWork, OverWorkBO.class);
        return overWorkBO;
    }

    @Override
    public List<OverWorkBO> listOverWork(OverWorkDTO overWorkDTO) throws SerException {
        if (StringUtils.isNotBlank(overWorkDTO.getOverWorker())){
            overWorkDTO.getConditions().add(Restrict.eq("overWorker", overWorkDTO.getOverWorker()));
        }
        if( null !=overWorkDTO.getAuditStatus() ){
            overWorkDTO.getConditions().add(Restrict.eq("auditStatus", overWorkDTO.getAuditStatus().getCode()));
        }
        overWorkDTO.getSorts().add("createTime=desc");
        List<OverWork> list = super.findByCis( overWorkDTO ,true );
        List<OverWorkBO> boList = BeanTransform.copyProperties( list, OverWorkBO.class);

        return boList;
    }



    @Transactional(rollbackFor = SerException.class)
    @Override
    public OverWorkBO addOverWork(OverWorkTO overWorkTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        OverWork overWork = new OverWork();
        BeanTransform.copyProperties(overWorkTO,overWork,"overStartTime","overEndTime");
        overWork.setOverStartTime( DateUtil.parseDateTime(overWorkTO.getOverStartTime()) );
        overWork.setOverEndTime( DateUtil.parseDateTime(overWorkTO.getOverEndTime()) );
        overWork.setAuditStatus(AuditStatus.NONE);
        super.save( overWork );
        //如果是项目经理下发的任务，则不用审核，将审核状态改为已通过
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        Map<String,String> positMap = positionUserDetailAPI.departPosition(userBO.getUsername());
        RpcTransmit.transmitUserToken(userToken);
        if(null != positMap) {
            String position = "";
            for (Map.Entry str : positMap.entrySet()) {
                position = (String) str.getValue();
            }
            if (position.contains("项目经理")) {
                OverWork temp = super.findById(overWork.getId());
                temp.setAuditStatus(AuditStatus.AGREE);
                super.update(temp);
            }
        }
        return BeanTransform.copyProperties( overWork, OverWorkBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteOverWork(String id) throws SerException {
        if (StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        OverWork overWork = super.findById( id );
        if( null!= overWork){
            super.remove( id );
        }
    }

    @Override
    public List<AreaBO> areaList() throws SerException {
        List<AreaBO> area = BeanTransform.copyProperties(departmentDetailAPI.findArea(),AreaBO.class);
        return area;
    }

    @Override
    public List<String> peopleList() throws SerException {
        List<UserBO> userBOS = positionDetailUserAPI.findUserList();
        List<String> userList = new ArrayList<>();
        if( userBOS != null && userBOS.size()>0 ){
            userList = userBOS.stream().filter(str->StringUtils.isNotBlank( str.getUsername()))
                    .map(UserBO::getUsername).collect(Collectors.toList());
        }
        return userList;
    }

    @Override
    public PositionAndDepartVO getPositAndDepart(String overWorker) throws SerException {
        PositionAndDepartVO vo = new PositionAndDepartVO();
        Map<String,String> positMap = positionUserDetailAPI.departPosition(overWorker);
        if ( positMap != null ){
            for( Map.Entry str : positMap.entrySet() ){
                vo.setDepart( (String)str.getKey() );
                vo.setPosition( (String)str.getValue() );
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
        LocalDateTime start = LocalDateTime.parse( overDTO.getStartTime(), formatter );
        LocalDateTime end = LocalDateTime.parse( overDTO.getEndTime(), formatter );
        Long hour = end.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()-start.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        overLong = new BigDecimal((double) hour/1000/60/60).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        if ( overDTO.getNoonBreakOr().equals(true) ){
            overLong = overLong-1.5d;
        }
        //计算可休天数= 加班时长/8
        Double relaxDay = overLong/8;

        OverLongAndRelaxDayVO vo = new OverLongAndRelaxDayVO();
        vo.setOverLong( overLong );
        vo.setRelaxDay( relaxDay );

        return vo;
    }

    @Override
    public Long countAudit(OverWorkDTO overWorkDTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        if (StringUtils.isNotBlank(overWorkDTO.getOverWorker())){
            overWorkDTO.getConditions().add(Restrict.eq("overWorker", overWorkDTO.getOverWorker()));
        }
        if( null !=overWorkDTO.getAuditStatus() ){
            overWorkDTO.getConditions().add(Restrict.eq("auditStatus", overWorkDTO.getAuditStatus().getCode()));
        }
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        if( !"admin".equals(userBO.getUsername())){
            overWorkDTO.getConditions().add(Restrict.eq("charger", userBO.getUsername()));
        }

        Long count = super.count(overWorkDTO);

        return count;
    }

    @Override
    public List<OverWorkBO> listAudit(OverWorkDTO overWorkDTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        if (StringUtils.isNotBlank(overWorkDTO.getOverWorker())){
            overWorkDTO.getConditions().add(Restrict.eq("overWorker", overWorkDTO.getOverWorker()));
        }
        if( null !=overWorkDTO.getAuditStatus() ){
            overWorkDTO.getConditions().add(Restrict.eq("auditStatus", overWorkDTO.getAuditStatus().getCode()));
        }

        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        if( !"admin".equals(userBO.getUsername())){
            overWorkDTO.getConditions().add(Restrict.eq("charger", userBO.getUsername()));
        }

        overWorkDTO.getSorts().add("createTime=desc");
        List<OverWork> list = super.findByCis( overWorkDTO ,true );
        List<OverWorkBO> boList = BeanTransform.copyProperties( list, OverWorkBO.class);

        return boList;
    }

    @Override
    public OverWorkBO auditOverWork(OverWorkAuditTO auditTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        if( StringUtils.isBlank( auditTO.getId())){
            throw new SerException("id不能为空");
        }
        if( null == auditTO.getAuditStatus() ){
            throw new SerException("审核状态AuditStatus不能为空");
        }
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        OverWork overWork = super.findById( auditTO.getId() );
        if( !userBO.getUsername().equals( overWork.getCharger())){
            throw new SerException("您不是审核人，不能审核");
        }
        if( null != overWork  ){
            overWork.setAuditAdvice( auditTO.getAuditAdvice() );
            overWork.setAuditStatus( auditTO.getAuditStatus() );
            overWork.setModifyTime( LocalDateTime.now() );
            super.update( overWork );
        }
        return BeanTransform.copyProperties( overWork , OverWorkBO.class);
    }

    @Override
    public Long countRestDay(OverWorkRestDayDTO overWorkRestDayDTO) throws SerException {
        //查询员工编号
        //查询员工姓名
        //查询剩余加班天数
        //查询核算截至时间
        //若传加班人员条件,只能看但前加班人员的截至当前月的剩余加班数
        Long count = 0L;
        if( StringUtils.isNotBlank(overWorkRestDayDTO.getOverWorker())){
            count = 1L;
        }else {
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
        if( StringUtils.isBlank(overWorkRestDayDTO.getOverWorker())){
            userList = this.peopleList();
        }else{
            userList.add(overWorkRestDayDTO.getOverWorker());
        }
        if( null != userList ) {
            //分页处理
            Integer page = overWorkRestDayDTO.getPage();
            Integer limmit = overWorkRestDayDTO.getLimit();
            int startLine = page * limmit ;
            int endLine = page * limmit + limmit;
            if( userList.size()-1 >= endLine ){
                subUserList = userList.subList(startLine, endLine);
            }else if( userList.size()-1< endLine ){
                subUserList = userList.subList(startLine, userList.size());
            }
            if( subUserList != null && subUserList.size()>0 ) {
                for (String userName : subUserList) {
                    //查加班开始时间在前三个月到本月的21号的累积
                    LocalDateTime now = LocalDateTime.now();
                    if ( StringUtils.isNotBlank(overWorkRestDayDTO.getCheckEndTime())) {
                        now = DateUtil.parseDateTime(  overWorkRestDayDTO.getCheckEndTime()+"-20");
                    }
                    LocalDateTime thirdMonthAgo = now.minusMonths(3);

                    LocalDateTime nowDate = LocalDateTime.of(now.getYear(),now.getMonthValue(),21,23,59,59);
                    LocalDateTime thirdAgoMonth = LocalDateTime.of( thirdMonthAgo.getYear(),thirdMonthAgo.getMonthValue(),20,00,00,01);

                    OverWorkDTO dto = new OverWorkDTO();
                    dto.getConditions().add(Restrict.between("overStartTime",new LocalDateTime[]{thirdAgoMonth,nowDate}));
                    dto.getConditions().add(Restrict.eq("overWorker", userName));
                    List<OverWork> listOver = super.findByCis(dto );
                    //计算所有加班天数
                    Double allOverDay = listOver==null ? 0d:listOver.stream().mapToDouble(OverWork::getRelaxDay).sum();
                    //查询上月20到本月21号所有请假天数
                    LocalDateTime lastMonDate = now.minusMonths(1).minusDays(2).plusSeconds(2);
                    VacateConDTO vacateConDTO = new VacateConDTO();
                    vacateConDTO.setEmpName( userName );
                    vacateConDTO.setStartTime( String.valueOf(lastMonDate));
                    vacateConDTO.setEndTime( String.valueOf( nowDate ));
                    List<VacateBO> vacateBOList = vacateAPI.findByCon( vacateConDTO );
                    Double vacateDay = vacateBOList==null ? 0d : vacateBOList.stream().mapToDouble(VacateBO::getTime).sum();

                    //计算剩余加班天数
                    Double restOverDay = allOverDay - vacateDay;

                    //查询员工编号
                    RpcTransmit.transmitUserToken(userToken);
                    UserBO userBO = userAPI.findByUsername( userName );
                    RpcTransmit.transmitUserToken(userToken);

                    OverWorkRestDayBO overWorkRestDayBO = new OverWorkRestDayBO();
                    overWorkRestDayBO.setEmpNum( userBO!= null ? userBO.getEmployeeNumber():"");
                    overWorkRestDayBO.setEmpName( userName );
                    overWorkRestDayBO.setRestDay( String.valueOf(restOverDay) );
                    overWorkRestDayBO.setCheckEndTime( nowDate+"" );
                    returnList.add( overWorkRestDayBO );

                }
            }
        }
        return returnList;
    }



}