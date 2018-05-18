package com.bjike.goddess.attendance.service.overtime;

import com.bjike.goddess.attendance.bo.overtime.ExtralOverWorkBO;
import com.bjike.goddess.attendance.dto.ExtralOverWorkSonDTO;
import com.bjike.goddess.attendance.dto.overtime.ExtralOverWorkDTO;
import com.bjike.goddess.attendance.dto.overtime.ExtralOverWorkDayDTO;
import com.bjike.goddess.attendance.entity.ExtralOverWorkSon;
import com.bjike.goddess.attendance.entity.overtime.ExtralOverWork;
import com.bjike.goddess.attendance.service.ExtralOverWorkSonSer;
import com.bjike.goddess.attendance.to.ExtralOverWorkTO;
import com.bjike.goddess.attendance.vo.ExtralOverWorkDayVO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 补班设置业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-12 04:42 ]
 * @Description: [ 补班设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attendanceSerCache")
@Service
public class ExtralOverWorkSerImpl extends ServiceImpl<ExtralOverWork, ExtralOverWorkDTO> implements ExtralOverWorkSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ExtralOverWorkSonSer extralOverWorkSonSer;


    @Override
    public Long countExtralOverWork(ExtralOverWorkDTO extralOverWorkDTO) throws SerException {
        Long count = super.count(extralOverWorkDTO);
        return count;
    }

    @Override
    public ExtralOverWorkBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        ExtralOverWork extralOverWork = super.findById(id);
        return BeanTransform.copyProperties(extralOverWork, ExtralOverWorkBO.class);
    }

    @Override
    public List<ExtralOverWorkBO> listExtralOverWork(ExtralOverWorkDTO extralOverWorkDTO) throws SerException {
        extralOverWorkDTO.getSorts().add("createTime=desc");
        List<ExtralOverWork> list = super.findByCis(extralOverWorkDTO, true);
        List<ExtralOverWorkBO> boList = BeanTransform.copyProperties(list, ExtralOverWorkBO.class);
        return boList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExtralOverWorkBO addExtralOverWork(ExtralOverWorkTO extralOverWorkTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        ExtralOverWork temp = BeanTransform.copyProperties(extralOverWorkTO, ExtralOverWork.class, true);
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        temp.setCreator(userBO.getUsername());
        temp.setCreateTime(LocalDateTime.now());
        temp.setModifyTime(LocalDateTime.now());
        super.save(temp);
        getTime(temp);
        return BeanTransform.copyProperties(extralOverWorkTO, ExtralOverWorkBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExtralOverWorkBO editExtralOverWork(ExtralOverWorkTO extralOverWorkTO) throws SerException {
        if (StringUtils.isBlank(extralOverWorkTO.getId())) {
            throw new SerException("id不能为空");
        }
        ExtralOverWork temp = super.findById(extralOverWorkTO.getId());

        if( null == temp ){
            throw new SerException("不存在该对象");
        }
        BeanTransform.copyProperties( extralOverWorkTO , temp,"createTime","creator","id","overStartTime","overEndTime");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime overStartTime = LocalDateTime.parse(extralOverWorkTO.getOverStartTime(), formatter );
        LocalDateTime overEndTime = LocalDateTime.parse(extralOverWorkTO.getOverEndTime(), formatter );
        temp.setModifyTime( LocalDateTime.now());
        temp.setOverStartTime( overStartTime );
        temp.setOverEndTime( overEndTime );
        super.update( temp );
        return BeanTransform.copyProperties(temp, ExtralOverWorkBO.class);
    }



    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteExtralOverWork(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        ExtralOverWork temp = super.findById(id);
        if (null == temp) {
            throw new SerException("该数据不存在，请刷新数据");
        }
        ExtralOverWorkSonDTO sonDTO = new ExtralOverWorkSonDTO();
        sonDTO.getConditions().add(Restrict.eq("fatherId", id));
        List<ExtralOverWorkSon> sons = extralOverWorkSonSer.findByCis(sonDTO);
        extralOverWorkSonSer.remove(sons);
        super.remove(id);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ExtralOverWorkDayVO caculateTime(ExtralOverWorkDayDTO overDTO) throws SerException {
        ExtralOverWorkDayVO vo = new ExtralOverWorkDayVO();
        vo.setOverDay(getTime1(overDTO));
        return vo;
    }

    private double getTime(ExtralOverWork entity) throws SerException {
        double time = 0;
        LocalDateTime startTime = entity.getOverStartTime();
        LocalDateTime endTime = entity.getOverEndTime();
        String s = DateUtil.dateToString(startTime).substring(0, DateUtil.dateToString(startTime).indexOf(" "));
        String e = DateUtil.dateToString(endTime).substring(0, DateUtil.dateToString(endTime).indexOf(" "));
        LocalDate starts = DateUtil.parseDate(s);
        LocalDate end = DateUtil.parseDate(e);
        long misDay = DateUtil.misDay(end, starts);
        if (entity.getLunchBreak()) {
            if (misDay == 0) {  //结束和开始都为当天
                time += saveSon(entity.getId(), startTime, DateUtil.parseDateTime(s + " 12:00:00"));
                time += saveSon(entity.getId(), DateUtil.parseDateTime(s + " 13:30:00"), endTime);
            } else {   //不是都为当天
                if (DateUtil.mis(DateUtil.parseDateTime(s + " 12:00:00"), startTime) >= 0) {   //第一天情况
                    time += saveSon(entity.getId(), startTime, DateUtil.parseDateTime(s + " 12:00:00"));
                    time += saveSon(entity.getId(), DateUtil.parseDateTime(s + " 13:30:00"), DateUtil.parseDateTime(s + " 18:00:00"));
                } else {   //小于早上下班时间
                    time += saveSon(entity.getId(), startTime, DateUtil.parseDateTime(s + " 18:00:00"));
                }
                for (long i = 1; i < misDay; i++) {
                    LocalDate start=starts.plusDays(i);
                    String s1 = DateUtil.dateToString(start);
                    LocalDateTime zGo = DateUtil.parseDateTime(s1 + " 08:30:00");   //早上上班
                    LocalDateTime zOut = DateUtil.parseDateTime(s1 + " 12:00:00");   //早上下班时间
                    LocalDateTime xGo = DateUtil.parseDateTime(s1 + " 13:30:00");   //下午上班时间
                    LocalDateTime xOut = DateUtil.parseDateTime(s1 + " 18:00:00");   //下午下班时间
                    time += saveSon(entity.getId(), zGo, zOut);
                    time += saveSon(entity.getId(), xGo, xOut);
                }
                if (DateUtil.mis(endTime, DateUtil.parseDateTime(e + " 13:30:00")) >= 0) {   //最后一天情况
                    time += saveSon(entity.getId(), DateUtil.parseDateTime(e + " 08:30:00"), DateUtil.parseDateTime(e + " 12:00:00"));
                    time += saveSon(entity.getId(), DateUtil.parseDateTime(e + " 13:30:00"), endTime);
                } else {
                    time += saveSon(entity.getId(), DateUtil.parseDateTime(e + " 08:30:00"), endTime);
                }
            }
        } else {   //不午休的情况
            if (misDay == 0) {  //结束和开始都为当天
                time += saveSon(entity.getId(), startTime, endTime);
            } else {
                time += saveSon(entity.getId(), startTime, DateUtil.parseDateTime(s + " 18:00:00"));  //第一天情况
                for (long i = 1; i < misDay; i++) {
                    LocalDate start=starts.plusDays(i);
                    String s1 = DateUtil.dateToString(start);
                    LocalDateTime go = DateUtil.parseDateTime(s1 + " 08:30:00");   //早上上班
                    LocalDateTime out = DateUtil.parseDateTime(s1 + " 18:00:00");   //下午下班时间
                    time += saveSon(entity.getId(), go, out);
//                    time++;   //加一天
                }
                time += saveSon(entity.getId(), DateUtil.parseDateTime(e + " 08:30:00"), endTime);  //最后一天情况
            }
        }
        return time;
    }

    @Transactional(rollbackFor = SerException.class)
    public Double saveSon(String fatherId, LocalDateTime start, LocalDateTime end) throws SerException {
        double time = 0;
        ExtralOverWorkSon son = new ExtralOverWorkSon();
        son.setFatherId(fatherId);
        son.setStartTime(start);
        son.setEndTime(end);
        double d = Double.parseDouble(DateUtil.mis(end, start) + "") / 8;
        time = new BigDecimal(d).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        extralOverWorkSonSer.save(son);
        return time;
    }

    private double getTime1(ExtralOverWorkDayDTO overDTO) throws SerException {
        double time = 0;
        LocalDateTime startTime = DateUtil.parseDateTime(overDTO.getStartTime());
        LocalDateTime endTime = DateUtil.parseDateTime(overDTO.getEndTime());
        String s = DateUtil.dateToString(startTime).substring(0, DateUtil.dateToString(startTime).indexOf(" "));
        String e = DateUtil.dateToString(endTime).substring(0, DateUtil.dateToString(endTime).indexOf(" "));
        LocalDate starts = DateUtil.parseDate(s);
        LocalDate end = DateUtil.parseDate(e);
        long misDay = DateUtil.misDay(end, starts);
        if (overDTO.getLunchBreak()) {
            if (misDay == 0) {  //结束和开始都为当天
                time += get(startTime, DateUtil.parseDateTime(s + " 12:00:00"));
                time += get(DateUtil.parseDateTime(s + " 13:30:00"), endTime);
            } else {   //不是都为当天
                if (DateUtil.mis(DateUtil.parseDateTime(s + " 12:00:00"), startTime) >= 0) {   //第一天情况
                    time += get(startTime, DateUtil.parseDateTime(s + " 12:00:00"));
                    time += get(DateUtil.parseDateTime(s + " 13:30:00"), DateUtil.parseDateTime(s + " 18:00:00"));
                } else {   //小于早上下班时间
                    time += get(startTime, DateUtil.parseDateTime(s + " 18:00:00"));
                }
                for (long i = 1; i < misDay; i++) {
                    LocalDate start=starts.plusDays(i);
                    String s1 = DateUtil.dateToString(start);
                    LocalDateTime zGo = DateUtil.parseDateTime(s1 + " 08:30:00");   //早上上班
                    LocalDateTime zOut = DateUtil.parseDateTime(s1 + " 12:00:00");   //早上下班时间
                    LocalDateTime xGo = DateUtil.parseDateTime(s1 + " 13:30:00");   //下午上班时间
                    LocalDateTime xOut = DateUtil.parseDateTime(s1 + " 18:00:00");   //下午下班时间
                    time += get(zGo, zOut);
                    time += get(xGo, xOut);
                }
                if (DateUtil.mis(endTime, DateUtil.parseDateTime(e + " 13:30:00")) >= 0) {   //最后一天情况
                    time += get(DateUtil.parseDateTime(e + " 08:30:00"), DateUtil.parseDateTime(e + " 12:00:00"));
                    time += get(DateUtil.parseDateTime(e + " 13:30:00"), endTime);
                } else {
                    time += get(DateUtil.parseDateTime(e + " 08:30:00"), endTime);
                }
            }
        } else {   //不午休的情况
            if (misDay == 0) {  //结束和开始都为当天
                time += get(startTime, endTime);
            } else {
                time += get(startTime, DateUtil.parseDateTime(s + " 18:00:00"));  //第一天情况
                for (long i = 1; i < misDay; i++) {
                    LocalDate start=starts.plusDays(i);
                    String s1 = DateUtil.dateToString(start);
                    LocalDateTime go = DateUtil.parseDateTime(s1 + " 08:30:00");   //早上上班
                    LocalDateTime out = DateUtil.parseDateTime(s1 + " 18:00:00");   //下午下班时间
                    time += get(go, out);
//                    time++;   //加一天
                }
                time += get(DateUtil.parseDateTime(e + " 08:30:00"), endTime);  //最后一天情况
            }
        }
        return time;
    }

    private Double get(LocalDateTime start, LocalDateTime end) throws SerException {
        double time = 0;
        double d = Double.parseDouble(DateUtil.mis(end, start) + "") / (8 * 3600000);
        time = new BigDecimal(d).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        return time;
    }
}