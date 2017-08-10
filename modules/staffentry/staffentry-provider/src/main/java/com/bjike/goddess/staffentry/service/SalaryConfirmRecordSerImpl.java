package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.staffentry.bo.SalaryConfirmRecordBO;
import com.bjike.goddess.staffentry.dto.SalaryConfirmRecordDTO;
import com.bjike.goddess.staffentry.entity.SalaryConfirmRecord;
import com.bjike.goddess.staffentry.enums.GuideAddrStatus;
import com.bjike.goddess.staffentry.to.GuidePermissionTO;
import com.bjike.goddess.staffentry.to.SalaryConfirmRecordTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 薪资确认业务实现
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 17:26]
 * @Description: [薪资确认业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "staffentrySerCache")
@Service
public class SalaryConfirmRecordSerImpl extends ServiceImpl<SalaryConfirmRecord, SalaryConfirmRecordDTO> implements SalaryConfirmRecordSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 检测模块
     * @param idFlag
     * @throws SerException
     */
    private Boolean checkMoudleIdentity(String idFlag) throws SerException{

        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        Boolean flag = false;
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.moudleCusPermission(idFlag);
//            if( !flag){
//                throw new SerException("你不是相应模块的人员，不能进行操作");
//            }
        }
        return flag;
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = checkMoudleIdentity("4");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = checkMoudleIdentity("5");
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagAdd ) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = checkMoudleIdentity("4");
                break;
            case ADD:
                flag = checkMoudleIdentity("5");
                break;
            case EDIT:
                flag = checkMoudleIdentity("5");
                break;
            case DELETE:
                flag = checkMoudleIdentity("5");
                break;
            case COLLECT:
                flag = checkMoudleIdentity("4");
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }


    @Override
    public Long countSalaryConfirmRecord(SalaryConfirmRecordDTO salaryConfirmRecordDTO) throws SerException {
        Long count = super.count( salaryConfirmRecordDTO);
        return count;
    }

    @Override
    public SalaryConfirmRecordBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        SalaryConfirmRecord  salaryConfirmRecord = super.findById(id);
        String dates =  salaryConfirmRecord.getEntryTime().toString();
        SalaryConfirmRecordBO bo = BeanTransform.copyProperties( salaryConfirmRecord , SalaryConfirmRecordBO.class,"entryTime");
        bo.setEntryTime(  dates);
        return bo;
    }

    @Override
    public List<SalaryConfirmRecord> listSalaryConfirmRecord(SalaryConfirmRecordDTO salaryConfirmRecordDTO) throws SerException {

        List<SalaryConfirmRecord> salaryConfirmRecords = super.findByPage( salaryConfirmRecordDTO );
        return salaryConfirmRecords;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SalaryConfirmRecordBO insertSalaryConfirmRecord(SalaryConfirmRecordTO salaryConfirmRecordTO) throws SerException {

        SalaryConfirmRecord salaryConfirmRecord = BeanTransform.copyProperties( salaryConfirmRecordTO , SalaryConfirmRecord.class ,true);
        salaryConfirmRecord.setCreateTime( LocalDateTime.now() );
        try {
            super.save( salaryConfirmRecord );
        } catch (SerException e) {
            throw  new SerException( e.getMessage() );
        }
        return BeanTransform.copyProperties( salaryConfirmRecord ,SalaryConfirmRecordBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SalaryConfirmRecordBO editSalaryConfirmRecord(SalaryConfirmRecordTO salaryConfirmRecordTO) throws SerException {

        if(StringUtils.isBlank(salaryConfirmRecordTO.getId())){
            throw new SerException("id不能为空");
        }
        SalaryConfirmRecord temp = super.findById(salaryConfirmRecordTO.getId());
        SalaryConfirmRecord salaryConfirmRecord = BeanTransform.copyProperties( salaryConfirmRecordTO , SalaryConfirmRecord.class ,true);
        BeanTransform.copyProperties( salaryConfirmRecord ,temp,"id","createTime","entryTime");
        temp.setEntryTime(salaryConfirmRecord.getEntryTime());
        temp.setModifyTime( LocalDateTime.now() );
        super.update( temp );

        return BeanTransform.copyProperties( temp ,SalaryConfirmRecordBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeSalaryConfirmRecord(String id) throws SerException {

        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        try {
            super.remove( id );
        } catch (SerException e) {
            throw  new SerException(e.getMessage() );
        }

    }
}
