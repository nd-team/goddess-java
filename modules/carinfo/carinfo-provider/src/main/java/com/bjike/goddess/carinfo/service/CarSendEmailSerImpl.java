package com.bjike.goddess.carinfo.service;

import com.bjike.goddess.carinfo.bo.CarSendEmailBO;
import com.bjike.goddess.carinfo.to.CarSendEmailTO;
import com.bjike.goddess.carinfo.vo.CarSendEmailVO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.carinfo.dto.CarSendEmailDTO;
import com.bjike.goddess.carinfo.entity.CarSendEmail;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.contacts.bo.InternalContactsBO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.dto.PositionDetailDTO;
import com.bjike.goddess.organize.dto.PositionDetailUserDTO;
import com.bjike.goddess.organize.entity.DepartmentDetail;
import com.bjike.goddess.organize.entity.PositionDetail;
import com.bjike.goddess.organize.to.DepartmentDetailTO;
import com.bjike.goddess.organize.to.PositionDetailTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.dto.UserDetailDTO;
import com.bjike.goddess.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 发送邮件业务实现
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-07-25 09:50 ]
 * @Description: [ 发送邮件业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "carinfoSerCache")
@Service
public class CarSendEmailSerImpl extends ServiceImpl<CarSendEmail, CarSendEmailDTO> implements CarSendEmailSer {
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;

    @Autowired
    private MessageAPI messageAPI;

    // 定时器规则 "0 0 10 15 * ?" 每月15日上午10:00发送邮件
    @Override
    public void sendEmail() throws SerException {
        List<CarSendEmail> carSendEmails = super.findAll();
        List<String> receivers = new ArrayList<>();
        if (carSendEmails.size() > 0 && carSendEmails != null) {
            List<UserBO> userBO = positionDetailUserAPI.findByPosition(carSendEmails.get(0).getPositionNameId());
            if (userBO.size() > 0 && userBO != null) {
              List<User> users =   BeanTransform.copyProperties(userBO, User.class);
                for(User user : users){
                    InternalContactsBO contactsBO = internalContactsAPI.findByUser(user.getId());
                    if(contactsBO != null)
                    receivers.add(contactsBO.getEmail());
                }
            }
            String[] sendUsers = (String[]) receivers.toArray(new String[receivers.size()]);
            MessageTO messageTO = new MessageTO("司机新增提醒", "是否要新增招聘司机");
            messageTO.setSendType(SendType.EMAIL);
            messageTO.setMsgType(MsgType.SYS);//根据自己业务写
            messageTO.setSendType( SendType.EMAIL);//根据自己业务写
            messageTO.setRangeType( RangeType.SPECIFIED);//根据自己业务写
            messageTO.setSenderId("SYSTEM");
            messageTO.setSenderName("SYSTEM");
            messageTO.setReceivers(sendUsers);
            //本地测试方法，记得要开message模块的provider和consumer方法
//            String [] sendU = new String[1];
//            sendU[0] = "jiangzaixuan_aj@163.com";
//            messageTO.setReceivers(sendU);
            messageAPI.send(messageTO);
        }

    }

    @Override
    public List<DepartmentDetail> findDepartMent() throws SerException {
        List<DepartmentDetailBO> departmentBOS = departmentDetailAPI.findStatus();
        List<DepartmentDetailTO> departmentDetailTOS = BeanTransform.copyProperties(departmentBOS, DepartmentDetailTO.class);
        List<DepartmentDetail> departmentDetails = BeanTransform.copyProperties(departmentDetailTOS, DepartmentDetailTO.class);
        return departmentDetails;
    }

    @Override
    public List<PositionDetail> findPosition(String id) throws SerException {
        List<PositionDetailBO> positionBOS = positionDetailAPI.findByDepartment(id);
        List<PositionDetailTO> positionDetailTOS = BeanTransform.copyProperties(positionBOS, PositionDetailTO.class);
        List<PositionDetail> positionDetails = BeanTransform.copyProperties(positionDetailTOS, PositionDetail.class);
        return positionDetails;
    }

    @Override
    public CarSendEmailBO add(CarSendEmailTO to) throws SerException {
        CarSendEmail carSendEmail = BeanTransform.copyProperties(to, CarSendEmail.class);
        super.save(carSendEmail);
        to.setId(carSendEmail.getId());
        return BeanTransform.copyProperties(to, CarSendEmailBO.class);
    }

    @Override
    public List<CarSendEmailBO> list() throws SerException {
        List<CarSendEmail> carSendEmailTOS = super.findAll();
        return BeanTransform.copyProperties(carSendEmailTOS, CarSendEmailVO.class);
    }
}