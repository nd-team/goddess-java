package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordDetailBO;
import com.bjike.goddess.shareholdersmanage.bo.ShareOpenAccountBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityTransactRecordDetailDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecordDetail;
import com.bjike.goddess.shareholdersmanage.entity.ShareOpenAccount;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordDetailTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 股权交易记录详情业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:11 ]
 * @Description: [ 股权交易记录详情业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class EquityTransactRecordDetailSerImpl extends ServiceImpl<EquityTransactRecordDetail, EquityTransactRecordDetailDTO> implements EquityTransactRecordDetailSer {
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
    public Long countTranDetail(EquityTransactRecordDetailDTO equityTransactRecordDetailDTO) throws SerException {
        Long count = super.count(equityTransactRecordDetailDTO);
        return count;
    }

    @Override
    public EquityTransactRecordDetailBO getOne(String id) throws SerException {
        EquityTransactRecordDetail equityTransactRecordDetail = super.findById(id);
        return BeanTransform.copyProperties(equityTransactRecordDetail, EquityTransactRecordDetailBO.class);
    }

    @Override
    public List<EquityTransactRecordDetailBO> findByName(String shareholderName) throws SerException {
        checkPermission();
        List<EquityTransactRecordDetailBO> equityTransactRecordDetailBOS = new ArrayList<>();
        EquityTransactRecordDetailDTO equityTransactRecordDetailDTO = new EquityTransactRecordDetailDTO();
        equityTransactRecordDetailDTO.getConditions().add(Restrict.eq("shareholderName",shareholderName));
        List<EquityTransactRecordDetail> equityTransactRecordDetails = super.findByPage(equityTransactRecordDetailDTO);
        equityTransactRecordDetailBOS = BeanTransform.copyProperties(equityTransactRecordDetails, EquityTransactRecordDetailBO.class);
        EquityTransactRecordDetailBO equityTransactRecordDetailBO1 = new EquityTransactRecordDetailBO();
        Double totalAmount = equityTransactRecordDetailBOS.stream().mapToDouble(e->e.getAmount()).sum();
        equityTransactRecordDetailBO1.setTransactDate("合计");
        equityTransactRecordDetailBO1.setAmount(totalAmount);
        equityTransactRecordDetailBOS.add(equityTransactRecordDetailBO1);
        return equityTransactRecordDetailBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void save(EquityTransactRecordDetailTO equityTransactRecordDetailTO) throws SerException {
        EquityTransactRecordDetail equityTransactRecordDetail = BeanTransform.copyProperties(equityTransactRecordDetailTO,EquityTransactRecordDetail.class,true);
        equityTransactRecordDetail.setCreateTime(LocalDateTime.now());
        super.update(equityTransactRecordDetail);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void edit(EquityTransactRecordDetailTO equityTransactRecordDetailTO) throws SerException {
        EquityTransactRecordDetail equityTransactRecordDetail = super.findById(equityTransactRecordDetailTO.getId());
        if (equityTransactRecordDetail == null) {
            throw new SerException("此数据不存在");
        }
        BeanTransform.copyProperties(equityTransactRecordDetailTO, equityTransactRecordDetail, true);
        equityTransactRecordDetail.setModifyTime(LocalDateTime.now());
        super.update(equityTransactRecordDetail);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        super.remove(id);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteByName(String shareholderName) throws SerException {
        EquityTransactRecordDetailDTO equityTransactRecordDetailDTODTO = new EquityTransactRecordDetailDTO();
        equityTransactRecordDetailDTODTO.getConditions().add(Restrict.eq("shareholderName",shareholderName));
        List<EquityTransactRecordDetail> equityTransactRecordDetails = super.findByCis(equityTransactRecordDetailDTODTO);
        super.remove(equityTransactRecordDetails);
    }

    @Override
    public List<EquityTransactRecordDetailBO> getByName(String shareholderName) throws SerException {
        EquityTransactRecordDetailDTO equityTransactRecordDetailDTO = new EquityTransactRecordDetailDTO();
        equityTransactRecordDetailDTO.getConditions().add(Restrict.eq("shareholderName",shareholderName));
        List<EquityTransactRecordDetail> equityTransactRecordDetails = super.findByCis(equityTransactRecordDetailDTO);
        return BeanTransform.copyProperties(equityTransactRecordDetails,EquityTransactRecordDetailBO.class);
    }

    @Override
    public EquityTransactRecordDetailBO getByNameId(String shareholderName, String transactId) throws SerException {
        EquityTransactRecordDetailDTO equityTransactRecordDetailDTO = new EquityTransactRecordDetailDTO();
        equityTransactRecordDetailDTO.getConditions().add(Restrict.eq("shareholderName",shareholderName));
        equityTransactRecordDetailDTO.getConditions().add(Restrict.eq("transactId",transactId));
        EquityTransactRecordDetail equityTransactRecordDetail = super.findOne(equityTransactRecordDetailDTO);
        return BeanTransform.copyProperties(equityTransactRecordDetail,EquityTransactRecordDetailBO.class);
    }

    @Override
    public List<EquityTransactRecordDetailBO> getByTransactId(String transactId) throws SerException {
        EquityTransactRecordDetailDTO equityTransactRecordDetailDTO = new EquityTransactRecordDetailDTO();
        equityTransactRecordDetailDTO.getConditions().add(Restrict.eq("transactId",transactId));
        List<EquityTransactRecordDetail> equityTransactRecordDetails = super.findByCis(equityTransactRecordDetailDTO);
        return BeanTransform.copyProperties(equityTransactRecordDetails,EquityTransactRecordDetailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteByTransactId(String transactId) throws SerException {
        EquityTransactRecordDetailDTO equityTransactRecordDetailDTO = new EquityTransactRecordDetailDTO();
        equityTransactRecordDetailDTO.getConditions().add(Restrict.eq("transactId",transactId));
        List<EquityTransactRecordDetail> equityTransactRecordDetails = super.findByCis(equityTransactRecordDetailDTO);
        if(equityTransactRecordDetails!=null && equityTransactRecordDetails.size()>0){
            super.remove(equityTransactRecordDetails);
        }
    }
}