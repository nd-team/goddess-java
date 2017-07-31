package com.bjike.goddess.materialsummary.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.materialsummary.bo.SendEmailBO;
import com.bjike.goddess.materialsummary.dto.SendEmailDTO;
import com.bjike.goddess.materialsummary.entity.SendEmail;
import com.bjike.goddess.materialsummary.to.SendEmailTO;
import com.bjike.goddess.materialsummary.type.CollectSendUnit;
import com.bjike.goddess.materialsummary.type.ModuleType;
import com.bjike.goddess.materialsummary.type.SummaryType;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 物质购买发送邮件业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-28 08:17 ]
 * @Description: [ 物质购买发送邮件业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class SendEmailSerImpl extends ServiceImpl<SendEmail, SendEmailDTO> implements SendEmailSer {
    @Autowired
    private UserAPI userAPI;

    @Override
    public Long counts(SendEmailDTO SendEmailDTO) throws SerException {
        Long count = super.count(SendEmailDTO);
        return count;
    }

    @Override
    public SendEmailBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        SendEmail buySendEmail = super.findById(id);
        return BeanTransform.copyProperties(buySendEmail, SendEmailBO.class);
    }

    @Override
    public List<SendEmailBO> listCollectEmail(SendEmailDTO SendEmailDTO) throws SerException {
        SendEmailDTO.getSorts().add("createTime=desc");
        List<SendEmail> list = super.findByPage(SendEmailDTO);
        return BeanTransform.copyProperties(list, SendEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SendEmailBO addCollectEmail(SendEmailTO SendEmailTO) throws SerException {

        if (SendEmailTO.getSendNum() < 0) {
            throw new SerException("发送间隔不能小于0");
        }
        if (SendEmailTO.getSendNum() < 30 && SendEmailTO.getCollectSendUnit().equals(CollectSendUnit.MINUTE)) {
            throw new SerException("发送间隔单位为分钟的间隔数不能小于30分钟");
        }

        if (SendEmailTO.getSendNum() > SendEmailTO.getSendNum().longValue() && SendEmailTO.getSendNum() < (SendEmailTO.getSendNum().longValue() + 1)) {
            throw new SerException("发送间隔不能为小数");
        }

        List<String> sendObjectList = SendEmailTO.getSendObject();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱书写不正确");
                }
                emails.append(emailStr + ",");
            }
        }
        SendEmail sendEmail = BeanTransform.copyProperties(SendEmailTO, SendEmail.class, true);
        sendEmail.setCreateTime(LocalDateTime.now());
        sendEmail.setStatus(Status.THAW);
        sendEmail.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        if (null == sendEmail.getCollectSendUnit()) {
            throw new SerException("发送单位不能为空");
        }
        String unit = sendUnitConverse(sendEmail.getCollectSendUnit().getCode());
        sendEmail.setSendNumAndUnit(sendEmail.getSendNum() + unit);

        //设置汇总条件


        //设置发送对象
        sendEmail.setSendObject(String.valueOf(emails));
        //设置上次发送时间
        sendEmail.setLastSendTime(LocalDateTime.now());


        super.save(sendEmail);

        return BeanTransform.copyProperties(sendEmail, SendEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SendEmailBO editCollectEmail(SendEmailTO buySendEmailTO) throws SerException {
        if (buySendEmailTO.getSendNum() < 0) {
            throw new SerException("发送间隔不能小于0");
        }

        if (buySendEmailTO.getSendNum() < 30 && buySendEmailTO.getCollectSendUnit().equals(CollectSendUnit.MINUTE)) {
            throw new SerException("发送间隔单位为分钟的间隔数不能小于30分钟");
        }

        if (buySendEmailTO.getSendNum() > buySendEmailTO.getSendNum().longValue() && buySendEmailTO.getSendNum() < (buySendEmailTO.getSendNum().longValue() + 1)) {
            throw new SerException("发送间隔不能为小数");
        }

        List<String> sendObjectList = buySendEmailTO.getSendObject();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱书写不正确");
                }
                emails.append(emailStr + ",");
            }
        }
        SendEmail temp = super.findById(buySendEmailTO.getId());
        SendEmail buySendEmail = BeanTransform.copyProperties(buySendEmailTO, SendEmail.class, true);

        BeanUtils.copyProperties(buySendEmail, temp, "id", "createTime", "createPersion", "lastSendTime", "status");
        temp.setModifyTime(LocalDateTime.now());
        temp.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        String unit = sendUnitConverse(buySendEmail.getCollectSendUnit().getCode());
        temp.setSendNumAndUnit(buySendEmail.getSendNum() + unit);

        //设置汇总条件


        //设置发送对象
        temp.setSendObject(String.valueOf(emails));

        super.update(temp);
        return BeanTransform.copyProperties(temp, SendEmailBO.class);

    }

    @Override
    public void deleteCollectEmail(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public void congealCollectEmail(String id) throws SerException {
        SendEmail buySendEmail = super.findById(id);
        buySendEmail.setStatus(Status.CONGEAL);
        super.update(buySendEmail);
    }

    @Override
    public void thawCollectEmail(String id) throws SerException {
        SendEmail buySendEmail = super.findById(id);
        buySendEmail.setStatus(Status.THAW);
        super.update(buySendEmail);
    }

    /**
     * 发送间隔单位转换
     */
    private String sendUnitConverse(int code) {
        String unit = "";
        switch (code) {
            case 0:
                unit = "分钟";
                break;
            case 1:
                unit = "小时";
                break;
            case 2:
                unit = "天";
                break;
            case 3:
                unit = "周";
                break;
            case 4:
                unit = "月";
                break;
            case 5:
                unit = "季度";
                break;
            case 6:
                unit = "年";
                break;
            default:
                unit = "";
                break;
        }
        return unit;
    }

    @Override
    public List<SummaryType> summaryType(ModuleType moduleType) throws SerException {
        List<SummaryType> typeList = Arrays.asList();
        if(moduleType.equals(ModuleType.MATERIALBUY)){
            typeList = Arrays.asList(SummaryType.MATCLASSIFBUY,SummaryType.DEPARTAREABUY,SummaryType.PERSONMATBUY);
        }else if(moduleType.equals(ModuleType.MATERIALSTOR)){
            typeList = Arrays.asList(SummaryType.STOCKSOURCE,SummaryType.AREASTOCK);
        }else if(moduleType.equals(ModuleType.MATERIALCHECK)){
            typeList = Arrays.asList(SummaryType.AREAMATCHECK,SummaryType.DEPARMATCHECK);
        }else if(moduleType.equals(ModuleType.MATERIALMAINTEN)){
            typeList = Arrays.asList(SummaryType.MAINTENSTATUS,SummaryType.WARRANTYSTATUS);
        }
        return typeList;
    }
}