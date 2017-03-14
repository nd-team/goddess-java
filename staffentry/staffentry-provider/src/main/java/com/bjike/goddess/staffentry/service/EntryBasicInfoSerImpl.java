package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.bo.EntryRegisterBO;
import com.bjike.goddess.staffentry.dto.EntryBasicInfoDTO;
import com.bjike.goddess.staffentry.entity.EntryBasicInfo;
import com.bjike.goddess.staffentry.to.EntryBasicInfoTO;
import com.bjike.goddess.user.utils.email.Email;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 入职基本信息业务实现
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 11:57]
 * @Description: [入职基本信息业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "entryBasicInfoSerCache")
@Service
public class EntryBasicInfoSerImpl extends ServiceImpl<EntryBasicInfo, EntryBasicInfoDTO> implements EntryBasicInfoSer {

    @Cacheable
    @Override
    public List<EntryBasicInfo> listEntryBasicInfo(EntryBasicInfoDTO entryBasicInfoDTO) throws SerException {

        //TODO: tanghaixiang 2017-03-10 未做根据 entryBasicInfodtO 分页查询所有
        List<EntryBasicInfo> entryBasicInfos = super.findByPage(entryBasicInfoDTO);
        return entryBasicInfos;
    }

    @Cacheable
    @Override
    public EntryBasicInfo getEntryBasicInfo(String id) throws SerException {
        EntryBasicInfo entryBasicInfo = super.findById(id);

        return entryBasicInfo;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EntryBasicInfoBO insertEntryBasicInfo(EntryBasicInfoTO entryBasicInfoTO) throws SerException {
        EntryBasicInfo entryBasicInfo = BeanTransform.copyProperties(entryBasicInfoTO, EntryBasicInfo.class, true);
        try {
            entryBasicInfo.setCreateTime(LocalDateTime.now());
            super.save(entryBasicInfo);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
        return BeanTransform.copyProperties(entryBasicInfo, EntryRegisterBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EntryBasicInfoBO editEntryBasicInfo(EntryBasicInfoTO entryBasicInfoTO) throws SerException {

        EntryBasicInfo entryBasicInfo = BeanTransform.copyProperties(entryBasicInfoTO, EntryBasicInfo.class, true);
        try {
            entryBasicInfo.setModifyTime(LocalDateTime.now());
            super.update(entryBasicInfo);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

        return BeanTransform.copyProperties(entryBasicInfo, EntryRegisterBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeEntryBasicInfo(String id) throws SerException {
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EntryBasicInfoBO sendEntryBasicInfo(EntryBasicInfoTO entryBasicInfoTO) throws SerException {
        EntryBasicInfo entryBasicInfo = new EntryBasicInfo();
        if (entryBasicInfoTO != null) {
            if (StringUtils.isNotBlank(entryBasicInfoTO.getId()) && StringUtils.isNotBlank(entryBasicInfoTO.getEmails())) {
                entryBasicInfo = super.findById(entryBasicInfoTO.getId());

                List<String> emails = new ArrayList<>(0);
                //TODO: tanghaixiang 2017-03-10 未做邮件发送邮箱
                //emails.add(internals.get(0).getEmail());
                StringBuffer content = new StringBuffer("");
                content.append(" 员工编号：" + entryBasicInfoTO.getEmployeeID())
                        .append(" 姓名:" + entryBasicInfoTO.getName())
                        .append(" 专业:" + entryBasicInfoTO.getProfession())
                        .append(" 联系电话:" + entryBasicInfoTO.getPhone())
                        .append(" 邮箱账号:" + entryBasicInfoTO.getEmail())
                        .append(" 入职时间:" + entryBasicInfoTO.getEntryTime())
                        .append(" 入职地区:" + entryBasicInfoTO.getArea())
                        .append(" 入职部门:" + entryBasicInfoTO.getDepartment())
                        .append(" 入职项目组:" + entryBasicInfoTO.getProjectGroup())
                        .append(" 入职岗位:" + entryBasicInfoTO.getPosition())
                ;
                Email email = new Email("入职通告", content.toString());
//                email.initEmailInfo("培训信息通知邮件内容", emails );

                try {
//                    EmailUtil.SendMail(email);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                /**
                 * 修改邮件发送对象
                 */
               EntryBasicInfo entryBasicInfo1 =  super.findById( entryBasicInfoTO.getId() );
               entryBasicInfo1.setModifyTime( LocalDateTime.now() );
               entryBasicInfo1.setEmailInfo( true );
                try {
                    super.update( entryBasicInfo1 );
                } catch (SerException e) {
                    throw   new SerException(e.getMessage());
                }
            }
        }
        return BeanTransform.copyProperties(entryBasicInfo, EntryRegisterBO.class);
    }

    @Cacheable
    @Override
    public  List<EntryBasicInfoBO> collectEntryBasicInfo(EntryBasicInfoDTO entryBasicInfoDTO) throws SerException {
        if (entryBasicInfoDTO == null) {
            throw new SerException("您好!查询条件为空,无法进行查询!");
        }

        if ((entryBasicInfoDTO.getPostNames() == null) || (entryBasicInfoDTO.getPostNames().length == 0)) {
            throw new SerException("您好!职位列表为空,无法进行查询!");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] positions = entryBasicInfoDTO.getPostNames();              //职位列表
        String startDateString = entryBasicInfoDTO.getStartDate();          //开始日期字符串
        String endDateString = entryBasicInfoDTO.getEndDate();              //结束日期字符串
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);
        List<EntryBasicInfoBO> list = new ArrayList<>(0);

        //TODO: tanghaixiang 2017-03-10 未做汇总
        for (String position : positions) {
            EntryBasicInfoDTO dto = new EntryBasicInfoDTO();
//            dto.getCriterions().add(Restrictions.and(Restrictions.eq("position", position), Restrictions.between("entryTime", startDate, endDate)));
//            int count = super.selectBySearch(dto).size();                  //获取入职人数
            EntryBasicInfoBO entryBasicInfoBO = new EntryBasicInfoBO();
            entryBasicInfoBO.setEntryTime(startDate + "~" + endDate);        //设置时间范围
            entryBasicInfoBO.setPosition(position);                          //设置职位
//            entryBasicInfoBO.setEntryCount(count);                           //设置入职人数
            list.add(entryBasicInfoBO);
        }

        return list;
    }
}
