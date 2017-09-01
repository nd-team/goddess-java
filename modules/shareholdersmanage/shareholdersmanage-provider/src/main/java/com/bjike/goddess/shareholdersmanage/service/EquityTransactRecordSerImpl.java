package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordDetailBO;
import com.bjike.goddess.shareholdersmanage.bo.ShareAndTypeBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityTransactRecordDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.type.GuideAddrStatus;
import com.bjike.goddess.shareholdersmanage.type.ShareholderStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 股权交易记录业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:05 ]
 * @Description: [ 股权交易记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class EquityTransactRecordSerImpl extends ServiceImpl<EquityTransactRecord, EquityTransactRecordDTO> implements EquityTransactRecordSer {
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是财务部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
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
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case SEE:
                flag = guideIdentity();
                break;
            case COLLECT:
                flag = guideIdentity();
                break;
            case UPLOAD:
                flag = guideIdentity();
                break;
            case DOWNLOAD:
                flag = guideIdentity();
                break;
            case IMPORT:
                flag = guideIdentity();
                break;
            case EXPORT:
                flag = guideIdentity();
                break;
            case SEEFILE:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public Long countTrans(EquityTransactRecordDTO equityTransactRecordDTO) throws SerException {
        Long count = super.count(equityTransactRecordDTO);
        return count;
    }

    @Override
    public EquityTransactRecordBO getOne(String id) throws SerException {
        EquityTransactRecord equityTransactRecord = super.findById(id);
        return BeanTransform.copyProperties(equityTransactRecord, EquityTransactRecordBO.class);
    }

    @Override
    public List<EquityTransactRecordBO> findList(EquityTransactRecordDTO equityTransactRecordDTO) throws SerException {
        checkPermission();
        equityTransactRecordDTO.getSorts().add("modifyTime=desc");
        List<EquityTransactRecord> equityTransactRecords = super.findByCis(equityTransactRecordDTO);
        return BeanTransform.copyProperties(equityTransactRecords, EquityTransactRecordBO.class);
    }

    @Override
    public Double transTotalAmount(String equityType) throws SerException {
        EquityTransactRecordDTO equityTransactRecordDTO = new EquityTransactRecordDTO();
        equityTransactRecordDTO.getConditions().add(Restrict.eq("equityType", equityType));
        List<EquityTransactRecord> equityTransactRecords = super.findByCis(equityTransactRecordDTO);
        Double totalAmount = 0d;
        if (equityTransactRecords != null && equityTransactRecords.size() > 0) {
            totalAmount = equityTransactRecords.stream().mapToDouble(s -> s.getAmount()).sum();//总出资额
        }
        return totalAmount;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void updateTrans(EquityTransactRecordTO equityTransactRecordTO) throws SerException {
        EquityTransactRecord equityTransactRecord = super.findById(equityTransactRecordTO.getId());
        if (equityTransactRecord == null) {
            throw new SerException("数据不存在");
        }
        BeanTransform.copyProperties(equityTransactRecordTO, equityTransactRecord, true);
        equityTransactRecord.setModifyTime(LocalDateTime.now());
        super.update(equityTransactRecord);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void updateTransList() throws SerException {
        for (String equityType : findEquityType()){
            EquityTransactRecordDTO equityTransactRecordDTO = new EquityTransactRecordDTO();
            equityTransactRecordDTO.getConditions().add(Restrict.eq("equityType", equityType));
            List<EquityTransactRecord> equityTransactRecords = super.findByCis(equityTransactRecordDTO);
            Double totalAmount = 0d;
            List<EquityTransactRecord> list = new ArrayList<>();
            totalAmount = equityTransactRecords.stream().mapToDouble(s -> s.getAmount()).sum();//总出资额
            for (EquityTransactRecord equityTransactRecord : equityTransactRecords){
                if (totalAmount != 0) {
                    equityTransactRecord.setPercentage((equityTransactRecord.getAmount() / totalAmount)*100);
                    equityTransactRecord.setModifyTime(LocalDateTime.now());
                } else {
                    equityTransactRecord.setPercentage(0d);
                    equityTransactRecord.setModifyTime(LocalDateTime.now());
                }
                list.add(equityTransactRecord);
            }
            super.update(list);
        }
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteTransact(String id) throws SerException {
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteByName(String shareholderName) throws SerException {
        EquityTransactRecordDTO equityTransactRecordDTO = new EquityTransactRecordDTO();
        equityTransactRecordDTO.getConditions().add(Restrict.eq("shareholderName",shareholderName));
        List<EquityTransactRecord> equityTransactRecords = super.findByCis(equityTransactRecordDTO);
        super.remove(equityTransactRecords);
    }

    @Override
    public EquityTransactRecordBO getByName(String shareholderName) throws SerException {
        EquityTransactRecordDTO equityTransactRecordDTO = new EquityTransactRecordDTO();
        equityTransactRecordDTO.getConditions().add(Restrict.eq("shareholderName",shareholderName));
        EquityTransactRecord equityTransactRecord = super.findOne(equityTransactRecordDTO);
        return BeanTransform.copyProperties(equityTransactRecord,EquityTransactRecordBO.class);
    }

    @Override
    public List<EquityTransactRecordBO> summationTrans() throws SerException {
       checkPermission();
        List<EquityTransactRecordBO> equityTransactRecordBOS = new ArrayList<>();
        List<EquityTransactRecord> equityTransactRecords = super.findAll();
        equityTransactRecordBOS = BeanTransform.copyProperties(equityTransactRecords, EquityTransactRecordBO.class);
        EquityTransactRecordBO equityTransactRecordBO = new EquityTransactRecordBO();
        Integer holdNum = equityTransactRecordBOS.stream().mapToInt(e-> e.getHoldNum()).sum();
        Double totalAmount = equityTransactRecordBOS.stream().mapToDouble(e->e.getAmount()).sum();
        equityTransactRecordBO.setShareholderName("合计");
        equityTransactRecordBO.setHoldNum(holdNum);
        equityTransactRecordBO.setAmount(totalAmount);
        equityTransactRecordBOS.add(equityTransactRecordBO);
        return equityTransactRecordBOS;
    }
    @Override
    public List<String> findEquityType() throws SerException {
        List<EquityTransactRecord> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (EquityTransactRecord model : list) {
            String equityType = model.getEquityType();
            if (StringUtils.isNotBlank(model.getEquityType())) {
                set.add(equityType);
            }
        }
        return new ArrayList<>(set);
    }

    public void reinstate(EquityTransactRecordBO equityTransactRecordBO,EquityTransactRecordDetailBO equityTransactRecordDetailBO)throws SerException{
        EquityTransactRecordTO equityTransactRecordTO = new EquityTransactRecordTO();
        equityTransactRecordTO.setId(equityTransactRecordBO.getId());
        equityTransactRecordTO.setHoldNum(equityTransactRecordBO.getHoldNum()-equityTransactRecordDetailBO.getHoldNum());
        equityTransactRecordTO.setAmount(equityTransactRecordBO.getAmount()-equityTransactRecordDetailBO.getAmount());
        updateTrans(equityTransactRecordTO);
    }

    @Override
    public List<EquityTransactRecordBO> getByEquityType(String equityType) throws SerException {
        EquityTransactRecordDTO equityTransactRecordDTO = new EquityTransactRecordDTO();
        equityTransactRecordDTO.getConditions().add(Restrict.eq("equityType",equityType));
        List<EquityTransactRecord> equityTransactRecords = super.findByCis(equityTransactRecordDTO);
        return BeanTransform.copyProperties(equityTransactRecords,EquityTransactRecordBO.class);
    }

    @Override
    public List<String> getNameByEquityType(String equityType) throws SerException {
        EquityTransactRecordDTO equityTransactRecordDTO = new EquityTransactRecordDTO();
        equityTransactRecordDTO.getConditions().add(Restrict.eq("equityType",equityType));
        equityTransactRecordDTO.getConditions().add(Restrict.eq("shareholderStatus", ShareholderStatus.NORMAL));
        List<EquityTransactRecord> list = super.findByCis(equityTransactRecordDTO);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (EquityTransactRecord model : list) {
            String name = model.getShareholderName();
            if (StringUtils.isNotBlank(model.getShareholderName())) {
                set.add(name);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<ShareAndTypeBO> getNameAndType() throws SerException {
        EquityTransactRecordDTO equityTransactRecordDTO = new EquityTransactRecordDTO();
        equityTransactRecordDTO.getConditions().add(Restrict.eq("shareholderStatus", ShareholderStatus.NORMAL));
        List<EquityTransactRecord> list = super.findAll();
        return BeanTransform.copyProperties(list,ShareAndTypeBO.class);
    }
}