package com.bjike.goddess.driverinfo.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.driverinfo.bo.DriverInfoBO;
import com.bjike.goddess.driverinfo.dto.DriverInfoDTO;
import com.bjike.goddess.driverinfo.entity.DriverInfo;
import com.bjike.goddess.driverinfo.enums.DriverInfoType;
import com.bjike.goddess.driverinfo.to.DriverInfoTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 租车协议、车辆信息管理业务
 *
 * @Author: [Jason]
 * @Date: [17-3-8 上午9:43]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "driverInfoSerImplCache")
@Service
public class DriverInfoSerImpl extends ServiceImpl<DriverInfo, DriverInfoDTO> implements DriverInfoSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DriverInfoBO saveDriverInfo(DriverInfoTO to) throws SerException {

        DriverInfo model = BeanTransform.copyProperties(to, DriverInfo.class, true);
        model.setStatus(Status.THAW);
        model.setPassword("123456");//添加司机初始密码
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, DriverInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DriverInfoBO updateDriverInfo(DriverInfoTO to) throws SerException {

        updateModel(to);
        return BeanTransform.copyProperties(to, DriverInfoBO.class);
    }

    //审核相当于修改数据，只修改bo非空的值（审核及审核意见）
    @Transactional(rollbackFor = SerException.class)
    @Override
    public DriverInfoBO updateAudit(DriverInfoTO to) throws SerException {

        updateModel(to);
        return BeanTransform.copyProperties(to, DriverInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void updateStatus(String id) throws SerException {
        DriverInfo model = super.findById(id);
        model.setStatus(Status.CONGEAL);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    @Override
    public List<DriverInfoBO> pageList(DriverInfoDTO dto, String type) throws SerException {
        dto.getConditions().add(Restrict.eq("infoType", DriverInfoType.valueOf(type).getCode()));
        dto.getSorts().add("createTime=desc");
        List<DriverInfo> list = super.findByPage(dto);
        List<DriverInfoBO> pageList = BeanTransform.copyProperties(list, DriverInfoBO.class);
        return pageList;
    }

    @Override
    public void isNeedDriver() {
        // TODO: 17-3-20
    }

    @Override
    public DriverInfoBO findByDriver(String driverName) throws SerException {
        DriverInfoDTO dto = new DriverInfoDTO();
        dto.getConditions().add(Restrict.eq("driverName", driverName));
        return BeanTransform.copyProperties(super.findOne(dto), DriverInfoBO.class);
    }

    /**
     * 更新数据（编辑、审核）
     *
     * @param to
     * @throws SerException
     */
    public void updateModel(DriverInfoTO to) throws SerException {

        if (!StringUtils.isEmpty(to.getId())) {
            DriverInfo model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空!");
            }

        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 发送邮箱
     * @param subject
     * @param content
     * @param address
     */
    /*public void sendEmail(String subject,String content,List<String> address){
        Email email = new Email(EmailUtil.USERNAME, address);
        email.setSubject(subject);
        email.setContent(content);
        try {
            if (EmailUtil.validateEmail(email)) {
                EmailUtil.SendMail(email);
            }
        } catch (Exception e) {
            throw new CustomException("邮箱发送失败，请联系管理员!");
        }
    }*/
}
