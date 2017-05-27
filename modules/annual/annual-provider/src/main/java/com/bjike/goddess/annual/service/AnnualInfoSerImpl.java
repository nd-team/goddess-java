package com.bjike.goddess.annual.service;

import com.bjike.goddess.annual.bo.AnnualArrangementStandardBO;
import com.bjike.goddess.annual.bo.AnnualInfoBO;
import com.bjike.goddess.annual.bo.AnnualStandardBO;
import com.bjike.goddess.annual.dto.AnnualInfoDTO;
import com.bjike.goddess.annual.entity.AnnualInfo;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.ArrangementAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.vo.EntryBasicInfoVO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * 年假信息业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 03:30 ]
 * @Description: [ 年假信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "annualSerCache")
@Service
public class AnnualInfoSerImpl extends ServiceImpl<AnnualInfo, AnnualInfoDTO> implements AnnualInfoSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private ArrangementAPI arrangementAPI;
    @Autowired
    private AnnualStandardSer annualStandardSer;
    @Autowired
    private AnnualArrangementStandardSer annualArrangementStandardSer;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private EntryBasicInfoAPI entryBasicInfoAPI;

    private static final String header = "%d年1月1日至%d年12月30日一年内您的病假天数已超过规定天数，根据公司制度您今年将不享受带薪年假。标准：";
    private static final String standard = "职员工龄%d-%d年，请病假累计%d个月以上的,";
    private static final String foot = "以上情况将不享受带薪年假。";
    private static final String title = "关于年假通知";

    @Override
    public List<AnnualInfoBO> findByUsername(String username) throws SerException {
        AnnualInfoDTO dto = new AnnualInfoDTO();
        dto.getConditions().add(Restrict.eq("username", username));
        List<AnnualInfo> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, AnnualInfoBO.class);
    }

    @Override
    public List<AnnualInfoBO> findByUsers(String... username) throws SerException {
        AnnualInfoDTO dto = new AnnualInfoDTO();
        dto.getConditions().add(Restrict.in("username", username));
        List<AnnualInfo> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, AnnualInfoBO.class);
    }

    /**
     * 计算工龄
     *
     * @param entity        年假信息实体数据
     * @param now           当前日期
     * @param arrangementId 层级ID
     * @return
     * @throws SerException
     */
    private String countSeniority(AnnualInfo entity, LocalDate now, String arrangementId) throws SerException {
        Long months = entity.getEntryTime().until(now, ChronoUnit.MONTHS), year = months / 12, month = months % 12;
        AnnualStandardBO standardBO = annualStandardSer.findBySeniority(year.intValue());
        if (null != standardBO) {
            AnnualArrangementStandardBO arrangementStandardBO = annualArrangementStandardSer.findByArrangementStandard(standardBO.getId(), arrangementId);
            //@TODO 等请假模块出来做病假判断
            entity.setSurplus(arrangementStandardBO.getDays().doubleValue());
            entity.setAnnual(arrangementStandardBO.getDays());
        } else {
            entity.setSurplus(0d);
            entity.setAnnual(0);
        }
        StringBuilder seniority = new StringBuilder(0);
        if (0 != year)
            seniority.append(year).append("年");
        if (0 != month)
            seniority.append(month).append("个月");
        return seniority.toString();
    }

    /**
     * 发送年假提醒邮件
     *
     * @param receivers 接受人ID
     * @param year      年份
     * @throws SerException
     */
    private void sendEmail(String[] receivers, Integer year) throws SerException {
        List<AnnualStandardBO> standardBOs = annualStandardSer.findThaw();
        StringBuilder content = new StringBuilder(0);
        content.append(String.format(this.header, year, year));
        for (AnnualStandardBO standard : standardBOs)
            content.append(String.format(this.standard, standard.getStartCycle(), standard.getEndCycle(), standard.getAstrict()));
        content.append(this.foot);
        MessageTO message = new MessageTO(title, content.toString());
        message.setMsgType(MsgType.SYS);
        message.setRangeType(RangeType.SPECIFIED);
        message.setSendType(SendType.EMAIL);
        message.setReceivers(receivers);
        messageAPI.send(message);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void generate() throws SerException {
        LocalDate now = LocalDate.now();//定时器为每年1月1日生成数据
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<UserBO> userBOs = userAPI.findByCis(userDTO);
        AnnualInfoDTO annualInfoDTO = new AnnualInfoDTO();
        annualInfoDTO.getConditions().add(Restrict.eq("year", now.getYear() - 1));//获取去年数据
        List<AnnualInfo> saveList = new ArrayList<>(0), updateList = super.findByCis(annualInfoDTO);
        List<String> receivers = new ArrayList<>(0);
        for (UserBO userBO : userBOs) {
            PositionDetailUserBO detailBO = positionDetailUserAPI.findOneByUser(userBO.getId());
            PositionDetailBO position;
            int annual = 0;
            AnnualInfo entity = new AnnualInfo();
            entity.setYear(now.getYear());
            entity.setUsername(userBO.getUsername());
            List<EntryBasicInfoVO> entryBasicInfo = entryBasicInfoAPI.getByEmpNumber(userBO.getEmployeeNumber());
            if (null != entryBasicInfo && entryBasicInfo.size() > 0) {//没有成功获取入职日期则发送邮件及结束此次循环
                try {
                    entity.setEntryTime(LocalDate.parse(entryBasicInfo.get(0).getEntryTime()));
                } catch (Exception e) {
                    receivers.add(userBO.getId());
                    continue;
                }
            } else {
                receivers.add(userBO.getId());
                continue;
            }
            for (String id : detailBO.getPositionIds().split(",")) {
                PositionDetailBO positionDetail = positionDetailAPI.findBOById(id);
                entity.setSeniority(this.countSeniority(entity, now, positionDetail.getArrangementId()));
                if (entity.getAnnual() > annual) {//计算层级年假后比当前存储年假大则使用计算年假
                    annual = entity.getAnnual();
                    entity.setArea(positionDetail.getArea());
                    entity.setDepartment(positionDetail.getDepartmentName());
                    entity.setPosition(positionDetail.getPosition());
                    entity.setArrangement(positionDetail.getArrangementName());
                } else
                    entity.setAnnual(annual);
            }
            entity.isAlready(Boolean.FALSE);
            if (entity.getAnnual() == 0)
                receivers.add(userBO.getId());
            else
                saveList.add(entity);
        }
        if (receivers.size() != 0)
            this.sendEmail(receivers.toArray(new String[0]), now.getYear() - 1);//传入年份为去年
        for (AnnualInfo entity : updateList)
            entity.setSurplus(0d);
        if (saveList.size() != 0)
            super.save(saveList);
        if (updateList.size() != 0)
            super.update(updateList);
    }

    @Override
    public List<AnnualInfoBO> maps(AnnualInfoDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), AnnualInfoBO.class);
    }

    @Override
    public AnnualInfoBO getById(String id) throws SerException {
        AnnualInfo entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return BeanTransform.copyProperties(entity, AnnualInfoBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        AnnualInfoDTO dto = new AnnualInfoDTO();
        return super.count(dto);
    }
}