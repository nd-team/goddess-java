package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.ComputerAssistBO;
import com.bjike.goddess.assistance.dto.ComputerAssistDTO;
import com.bjike.goddess.assistance.entity.ComputerAssist;
import com.bjike.goddess.assistance.to.ComputerAssistTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 电脑补助业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:20 ]
 * @Description: [ 电脑补助业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class ComputerAssistSerImpl extends ServiceImpl<ComputerAssist, ComputerAssistDTO> implements ComputerAssistSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private EntryBasicInfoAPI entryBasicInfoAPI;

    @Override
    public Long countComputerAssist(ComputerAssistDTO computerAssistDTO) throws SerException {
        if (StringUtils.isNotBlank(computerAssistDTO.getEmpName())) {
            computerAssistDTO.getConditions().add(Restrict.like("empName", computerAssistDTO.getEmpName()));
        }
        computerAssistDTO.getSorts().add("createTime=desc");
        Long count = super.count(computerAssistDTO);
        return count;
    }

    @Override
    public List<ComputerAssistBO> listComputerAssist(ComputerAssistDTO computerAssistDTO) throws SerException {
        if (StringUtils.isNotBlank(computerAssistDTO.getEmpName())) {
            computerAssistDTO.getConditions().add(Restrict.like("empName", computerAssistDTO.getEmpName()));
        }
        computerAssistDTO.getSorts().add("createTime=desc");
        List<ComputerAssist> list = super.findByCis(computerAssistDTO, true);

        return BeanTransform.copyProperties(list, ComputerAssistBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ComputerAssistBO addComputerAssist(ComputerAssistTO computerAssistTO) throws SerException {
        ComputerAssist computerAssist = BeanTransform.copyProperties(computerAssistTO, ComputerAssist.class, true);
        if (Double.isNaN(computerAssistTO.getAssistDays())) {
            throw new SerException("补助天数不能为非数字");
        }

        //补助金额补助金额（100元/月） 后台计算公式(100×（补助天数/补助计薪周期相减总天数）)
        if (StringUtils.isNotBlank(computerAssistTO.getSalaryStartTime())
                || StringUtils.isNotBlank(computerAssistTO.getSalaryEndTime())) {
            throw new SerException("计薪开始时间和结束时间不能为空");
        }
        //设置钱
        Long day = computerAssist.getSalaryEndTime().toEpochDay() - computerAssist.getSalaryStartTime().toEpochDay();
        Double money = computerAssistTO.getAssistDays() * (computerAssistTO.getAssistDays() / day);
        computerAssist.setAssistMoney(money);
        computerAssist.setCreateTime(LocalDateTime.now());
        super.save(computerAssist);
        return BeanTransform.copyProperties(computerAssist, ComputerAssistBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public ComputerAssistBO editComputerAssist(ComputerAssistTO computerAssistTO) throws SerException {
        if (Double.isNaN(computerAssistTO.getAssistDays())) {
            throw new SerException("补助天数不能为非数字");
        }
        ComputerAssist computerAssist = BeanTransform.copyProperties(computerAssistTO, ComputerAssist.class, true);
        ComputerAssist rs = super.findById(computerAssistTO.getId());

        rs.setEmpName(computerAssist.getEmpName());
        rs.setRemark(computerAssist.getRemark());

        //补助金额补助金额（100元/月） 后台计算公式(100×（补助天数/补助计薪周期相减总天数）)
        if (StringUtils.isNotBlank(computerAssistTO.getSalaryStartTime())
                || StringUtils.isNotBlank(computerAssistTO.getSalaryEndTime())) {
            throw new SerException("计薪开始时间和结束时间不能为空");
        }
        //设置钱
        Long day = computerAssist.getSalaryEndTime().toEpochDay() - computerAssist.getSalaryStartTime().toEpochDay();
        Double money = computerAssistTO.getAssistDays() * (computerAssistTO.getAssistDays() / day);
        rs.setAssistMoney(money);

        rs.setModifyTime(LocalDateTime.now());
        super.update(rs);
        return BeanTransform.copyProperties(rs, ComputerAssistBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteComputerAssist(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public List<ComputerAssistBO> collectByArea(ComputerAssistDTO computerAssistDTO) throws SerException {
        //地区不为空
        String[] fields = new String[]{"empName", "projectGroup", "area", "assistStartTime", "assistEndTime",
                "salaryStartTime", "salaryEndTime", "assistDays", "assistMoney"};
        String sql = "";
        List<ComputerAssist> list = new ArrayList<>();
        if (StringUtils.isNotBlank(computerAssistDTO.getArea())) {
            sql = "select empName,projectGroup,area,assistStartTime,assistEndTime, " +
                    " salaryStartTime,salaryEndTime,assistDays,assistMoney " +
                    " from assistance_computerassist where area ='" + computerAssistDTO.getArea() + "'";
            list = super.findBySql(sql, ComputerAssist.class, fields);
        } else {
            fields = new String[]{"area", "assistMoney"};
            sql = "select area ,count(assistMoney) as  assistMoney  from assistance_computerassist group by area ";
            list = super.findBySql(sql, ComputerAssist.class, fields);
        }
        return BeanTransform.copyProperties(list, ComputerAssistBO.class);
    }

    @Override
    public List<ComputerAssistBO> collectByProGroup(ComputerAssistDTO computerAssistDTO) throws SerException {
        //项目组不为空
        String[] fields = new String[]{"empName", "projectGroup", "area", "assistStartTime", "assistEndTime",
                "salaryStartTime", "salaryEndTime", "assistDays", "assistMoney"};
        String sql = "";
        List<ComputerAssist> list = new ArrayList<>();
        if (StringUtils.isNotBlank(computerAssistDTO.getArea())) {
            sql = "select empName,projectGroup,area,assistStartTime,assistEndTime, " +
                    " salaryStartTime,salaryEndTime,assistDays,assistMoney " +
                    " from assistance_computerassist where projectGroup ='" + computerAssistDTO.getProjectGroup() + "'";
            list = super.findBySql(sql, ComputerAssist.class, fields);
        } else {
            fields = new String[]{"projectGroup", "assistMoney"};
            sql = "select projectGroup , count(assistMoney) as  assistMoney  from assistance_computerassist group by projectGroup ";
            list = super.findBySql(sql, ComputerAssist.class, fields);
        }
        return BeanTransform.copyProperties(list, ComputerAssistBO.class);
    }

    @Override
    public List<String> listAllUser() throws SerException {
        List<UserBO> userBOList = userAPI.findAllUser();
        List<String> list = userBOList.stream().map(UserBO::getUsername).collect(Collectors.toList());
        return list;
    }

    @Override
    public EntryBasicInfoBO getUserByName(ComputerAssistDTO computerAssistDTO) throws SerException {
        if (StringUtils.isBlank(computerAssistDTO.getEmpName())) {
            throw new SerException("员工姓名不能为空");
        }
//            此處錯誤,注釋人:黎貴欽
//        EntryBasicInfoVO entryBasicInfoBO = entryBasicInfoAPI.getEntryBasicInfoByName( computerAssistDTO.getEmpName() );
        return null;
    }
}