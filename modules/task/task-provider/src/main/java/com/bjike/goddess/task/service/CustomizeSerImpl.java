package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.task.bo.CustomizeBO;
import com.bjike.goddess.task.dto.CustomizeDTO;
import com.bjike.goddess.task.entity.Customize;
import com.bjike.goddess.task.enums.NoticeType;
import com.bjike.goddess.task.enums.SummaryType;
import com.bjike.goddess.task.enums.TimeType;
import com.bjike.goddess.task.quartz.TaskSession;
import com.bjike.goddess.task.to.CustomizeTO;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-25 17:05]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class CustomizeSerImpl extends ServiceImpl<Customize, CustomizeDTO> implements CustomizeSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ScheduleSer scheduleSer;


    @Override
    public List<CustomizeBO> list(CustomizeDTO dto) throws SerException {
        return BeanTransform.copyProperties(super.findByPage(dto), CustomizeBO.class);
    }

    @Override
    public Long count(CustomizeDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public void add(CustomizeTO to) throws SerException {
        String nickname = userAPI.currentUser().getNickname();
        validated(to);
        Customize customize = BeanTransform.copyProperties(to, Customize.class, "tables", "fields");
        customize.setTablesId(StringUtils.join(to.getTables(), ","));
        customize.setFields(StringUtils.join(to.getFields(), ","));
        customize.setUser(nickname);
        customize.setLastTime(LocalDateTime.now());
        super.save(customize);
        if (customize.getEnable()) {
            TaskSession.put(customize.getId(), customize);
        }
    }

    @Override
    public void enable(String id, boolean enable) throws SerException {
        Customize customize = super.findById(id);
        if (null != customize) {
            if (enable) {
                Customize c = TaskSession.get(customize.getId());
                if (null == c) {
                    TaskSession.put(customize.getId(), customize);
                }
            } else {
                TaskSession.remove(customize.getId());
            }
            customize.setEnable(enable);
            super.update(customize);
        } else {
            throw new SerException("找不到该记录");
        }

    }

    private void validated(CustomizeTO to) throws SerException {
        if (!to.getSummaryType().equals(SummaryType.ALL)) {
            if (StringUtils.isBlank(to.getSummaryTarget())) {
                throw new SerException("请指定汇总部门或者人员");
            }
        }
        if (!to.getNoticeType().equals(NoticeType.ALL.ALL)) {
            if (StringUtils.isBlank(to.getNoticeTarget())) {
                throw new SerException("请指定提醒部门或者人员");
            }
        }
    }

    @Override
    public void executeTask() throws SerException {
        List<Customize> customizes = initTask();
        for (Customize customize : customizes) {
            if (isInvoking(customize)) { //是否可调用
                //查询调用
                try {
                    scheduleSer.customizeCollect(customize);
                }catch (Exception e) {  //异常也更新时间避免循环错误调用
                    e.printStackTrace();
                    customize.setLastTime(LocalDateTime.now());
                    super.update(customize);
                    TaskSession.remove(customize.getId());
                }
                customize.setLastTime(LocalDateTime.now());
                TaskSession.put(customize.getId(), customize);
                super.update(customize);
            }
        }
    }


    /**
     * 查询任务
     *
     * @return
     * @throws SerException
     */
    private boolean first = true;

    private List<Customize> initTask() throws SerException {
        List<Customize> customizes = new ArrayList<>();
        if (null != TaskSession.sessions()) {
            Map<String, Customize> map = TaskSession.sessions().asMap();
            for (Map.Entry<String, Customize> entry : map.entrySet()) {
                customizes.add(entry.getValue());
            }
        } else if (first) { //仅查询一次
            first = false;
            CustomizeDTO dto = new CustomizeDTO();
            dto.getConditions().add(Restrict.eq("enable", true));
            customizes = super.findByCis(dto);
            for (Customize customize : customizes) {
                TaskSession.put(customize.getId(), customize);
            }
        }
        return customizes;
    }

    /**
     * 是否可调用
     *
     * @param customize
     * @return
     */
    private boolean isInvoking(Customize customize) {
        TimeType type = customize.getTimeType();
        String timeVal = customize.getTimeVal();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime last = customize.getLastTime();
        if (!type.equals(TimeType.EVERYDAY)) { //每隔时间段
            int second = 0;
            if (type.equals(TimeType.MINUTE)) {
                second = Integer.parseInt(timeVal) * 60;
            } else if (type.equals(TimeType.HOUR)) {
                second = Integer.parseInt(timeVal) * 60 * 60;
            }
            if (second < ChronoUnit.SECONDS.between(last, now)) {
                return true;
            }
        } else { //每天
            String time = DateUtil.dateToString(LocalDateTime.now());
            time = StringUtils.substringAfter(time, " ");
            time = StringUtils.substringBeforeLast(time, ":");
            if (time.equals(timeVal)) {
                return true;
            }
        }
        return false;
    }

}
