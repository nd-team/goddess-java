package com.bjike.goddess.staffing.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.staffing.bo.CurrentDayBO;
import com.bjike.goddess.staffing.bo.LastDayBO;
import com.bjike.goddess.staffing.bo.TaskSituationBO;
import com.bjike.goddess.staffing.dto.CountDTO;
import com.bjike.goddess.staffing.entity.Count;
import com.bjike.goddess.staffing.to.CountTO;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 操作汇总业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-09 02:00 ]
 * @Description: [ 操作汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffingSerCache")
@Service
public class CountSerImpl extends ServiceImpl<Count, CountDTO> implements CountSer {
    @Autowired
    private UserAPI userAPI;

    private static final String module = "人员编制";

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void add(CountTO to) throws SerException {
        String token = RpcTransmit.getUserToken();
        String name = userAPI.currentUser().getUsername();
        Count count = BeanTransform.copyProperties(to, Count.class, true);
        count.setUser(name);
        count.setTime(LocalDateTime.now());
        super.save(count);
        RpcTransmit.transmitUserToken(token);
    }

    @Override
    public List<TaskSituationBO> countSituation(CountDTO dto) throws SerException {
        //todo:从任务分配中查找
        String time = dto.getTime();
        LocalDate current = null;
        if (StringUtils.isBlank(time)) {
            current = LocalDate.now();    //默认为当天
        } else {
            current = DateUtil.parseDate(time);
        }
        LocalDate last = current.minusDays(1);
        dto.getConditions().add(Restrict.between("time", new LocalDate[]{last, last}));
        List<Count> list = super.findByCis(dto);
        Set<String> users = list.stream().map(count -> count.getUser()).collect(Collectors.toSet());
        List<TaskSituationBO> bos = new ArrayList<>();
        for (String user : users) {
            String lastContent = null;
            StringBuilder sb = new StringBuilder();
            List<Count> counts = list.stream().filter(count -> user.equals(count.getUser())).collect(Collectors.toList());
            double actulWork = counts.size();
            Set<String> navigations = counts.stream().map(count -> count.getNavigation()).collect(Collectors.toSet());
            int i = 0;
            int num = 0;
            for (String navigation : navigations) {
                Set<String> functions = counts.stream().filter(count -> navigation.equals(count.getNavigation())).map(count -> count.getFunction()).collect(Collectors.toSet());
                for (String function : functions) {
                    num++;
                    if (i == navigations.size() - 1) {
                        sb.append(navigation + "-" + function);
                    } else {
                        sb.append(navigation + "-" + function + ",");
                    }
                }
                i++;
            }
            lastContent = sb.toString();
            LastDayBO lastDayBO = new LastDayBO();
            lastDayBO.setTime(DateUtil.dateToString(last)+"工作完成情况");
            lastDayBO.setContent(lastContent);
            lastDayBO.setActualTask(Double.valueOf(num));
            lastDayBO.setActualWork(actulWork);
            List<LastDayBO> lasts=new ArrayList<>();
            lasts.add(lastDayBO);
            CurrentDayBO currentDayBO=new CurrentDayBO();
            currentDayBO.setTime(DateUtil.dateToString(current)+"工作计划");
            List<CurrentDayBO> currents=new ArrayList<>();
            currents.add(currentDayBO);
            TaskSituationBO taskSituationBO = new TaskSituationBO();
            taskSituationBO.setUser(user);
            taskSituationBO.setType(module);
            taskSituationBO.setTaskType("行政任务");
            taskSituationBO.setLastDay(lasts);
            taskSituationBO.setCurrentDay(currents);
            bos.add(taskSituationBO);
        }
        return bos;
    }
}