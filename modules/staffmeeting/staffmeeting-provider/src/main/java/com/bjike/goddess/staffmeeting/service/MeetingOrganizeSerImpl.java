package com.bjike.goddess.staffmeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.api.MeetingLayAPI;
import com.bjike.goddess.staffmeeting.bo.MeetingLayBO;
import com.bjike.goddess.staffmeeting.bo.MeetingOrganizeBO;
import com.bjike.goddess.staffmeeting.dto.MeetingOrganizeDTO;
import com.bjike.goddess.staffmeeting.entity.MeetingOrganize;
import com.bjike.goddess.staffmeeting.entity.MeetingSummary;
import com.bjike.goddess.staffmeeting.to.MeetingOrganizeTO;
import com.bjike.goddess.staffmeeting.util.ChineseCharToEn;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工代表大会组织内容业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 10:45 ]
 * @Description: [ 员工代表大会组织内容业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffmeetingSerCache")
@Service
public class MeetingOrganizeSerImpl extends ServiceImpl<MeetingOrganize, MeetingOrganizeDTO> implements MeetingOrganizeSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MeetingLayAPI meetingLayAPI;
    @Autowired
    private MeetingSummarySer meetingSummarySer;

    public String getNumber(MeetingOrganize model) throws SerException {
        StringBuilder number = new StringBuilder("YGDB-");
        MeetingLayBO meetingLayBO = meetingLayAPI.findById(model.getLayId());
        //获取层面名称首字母大写
        String layName = ChineseCharToEn.getAllFirstLetter(meetingLayBO.getName());
        number.append(layName);
        number.append("-");
        number.append(LocalDate.now().toString().replace("-", ""));
        number.append("-");
        //查询最新的编号
        MeetingOrganizeDTO dto = new MeetingOrganizeDTO();
        dto.getSorts().add("createTime=desc");
        dto.setLimit(1);
        List<MeetingOrganize> list = super.findByPage(dto);
        if (list != null && !list.isEmpty()) {
            String lastNumStr = list.get(0).getMeetingNum();
            //截取最新编号
            Integer lastNum = Integer.parseInt(lastNumStr.substring(lastNumStr.lastIndexOf("-") + 1));
            if (lastNum < 9) {
                number.append("000");
            } else if (lastNum < 99) {
                number.append("00");
            } else if (lastNum < 999) {
                number.append("0");
            } else if (lastNum < 9999) {

            } else {
                throw new SerException("编号溢出，请联系管理员");
            }
            number.append(++lastNum);
        } else {
            number.append("0001");
        }
        return number.toString();
    }


    @Override
    public MeetingOrganizeBO insertModel(MeetingOrganizeTO to) throws SerException {
        MeetingOrganize model = BeanTransform.copyProperties(to, MeetingOrganize.class, true);
        model.setStatus(Status.THAW);
        model.setOrganizer(userAPI.currentUser().getUsername());
        model.setMeetingNum(getNumber(model));
        super.save(model);
        //生成纪要
        insertSummary(model);

        return BeanTransform.copyProperties(model, MeetingOrganizeBO.class);
    }

    public void insertSummary(MeetingOrganize model) throws SerException {
        MeetingSummary summary = new MeetingSummary();
        summary.setMeetingNum(model.getMeetingNum());
        summary.setActualTime(model.getPlanTime());
        summary.setActualPlace(model.getPlanPlace());
        meetingSummarySer.save(summary);
    }

    @Override
    public MeetingOrganizeBO updateModel(MeetingOrganizeTO to) throws SerException {
        //编辑修改层面不考虑编号变化，编号只于新增是初始化，如果编号不正确，请先考虑是否修改过层面
        MeetingOrganize model = super.findById(to.getId());
        if (model != null) {
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
        return BeanTransform.copyProperties(model, MeetingOrganizeBO.class);
    }

    @Override
    public void freeze(String id) throws SerException {
        MeetingOrganize model = super.findById(id);
        if (model != null) {
            model.setStatus(Status.CONGEAL);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
    }

    @Override
    public List<MeetingOrganizeBO> pageList(MeetingOrganizeDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        return BeanTransform.copyProperties(super.findByPage(dto), MeetingOrganizeBO.class);
    }
}