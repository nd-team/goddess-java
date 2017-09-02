package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordDetailBO;
import com.bjike.goddess.shareholdersmanage.bo.FreezeEquityBO;
import com.bjike.goddess.shareholdersmanage.bo.FreezeEquityLinkDateBO;
import com.bjike.goddess.shareholdersmanage.dto.FreezeEquityDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecordDetail;
import com.bjike.goddess.shareholdersmanage.entity.FreezeEquity;
import com.bjike.goddess.shareholdersmanage.to.FreezeEquityTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 冻结股权业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:39 ]
 * @Description: [ 冻结股权业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class FreezeEquitySerImpl extends ServiceImpl<FreezeEquity, FreezeEquityDTO> implements FreezeEquitySer {
    @Autowired
    private EquityTransactRecordSer equityTransactRecordSer;
    @Autowired
    private EquityTransactRecordDetailSer equityTransactRecordDetailSer;
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
    public Long countFreeze(FreezeEquityDTO freezeEquityDTO) throws SerException {
        Long count = super.count(freezeEquityDTO);
        return count;
    }

    @Override
    public FreezeEquityBO getOne(String id) throws SerException {
        FreezeEquity freezeEquity = super.findById(id);
        return BeanTransform.copyProperties(freezeEquity, FreezeEquityBO.class);
    }

    @Override
    public List<FreezeEquityBO> findList(FreezeEquityDTO freezeEquityDTO) throws SerException {
        checkPermission();
        searchCondi(freezeEquityDTO);
        freezeEquityDTO.getSorts().add("createTime=desc");
        List<FreezeEquity> changeEquityTypes = super.findByCis(freezeEquityDTO);
        return BeanTransform.copyProperties(changeEquityTypes, FreezeEquityBO.class);
    }

    /**
     * 根据条件查询数据
     *
     * @param freezeEquityDTO
     * @throws SerException
     */
    public void searchCondi(FreezeEquityDTO freezeEquityDTO) throws SerException {
        if (StringUtils.isNotBlank(freezeEquityDTO.getArea())) {
            freezeEquityDTO.getConditions().add(Restrict.eq("area", freezeEquityDTO.getArea()));
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FreezeEquityBO save(FreezeEquityTO freezeEquityTO) throws SerException {
        checkPermission();
        //添加本条数据
        FreezeEquity freezeEquity = BeanTransform.copyProperties(freezeEquityTO, FreezeEquity.class, true);
        freezeEquity.setCreateTime(LocalDateTime.now());
        freezeEquity = super.save(freezeEquity);
        //添加交易明细记录
        List<EquityTransactRecordBO> equityTransactRecordBOS = equityTransactRecordSer.getByEquityType(freezeEquityTO.getEquityType());
        if (equityTransactRecordBOS != null && equityTransactRecordBOS.size() > 0) {
            List<EquityTransactRecordDetail> detailList = new ArrayList<>();
            List<EquityTransactRecord> list = new ArrayList<>();
            for (EquityTransactRecordBO equityTransactRecordBO : equityTransactRecordBOS) {
                //添加交易记录明细记录
                EquityTransactRecordDetail equityTransactRecordDetail = new EquityTransactRecordDetail();
                equityTransactRecordDetail.setShareholderName(equityTransactRecordBO.getShareholderName());
                equityTransactRecordDetail.setTransactDate(LocalDate.now());
                equityTransactRecordDetail.setHoldNum(-equityTransactRecordBO.getHoldNum());
                equityTransactRecordDetail.setPerSharePrice(equityTransactRecordBO.getPerSharePrice());
                equityTransactRecordDetail.setAmount(-equityTransactRecordBO.getAmount());
                equityTransactRecordDetail.setTransactType("冻结股权");
                equityTransactRecordDetail.setTransactId(freezeEquity.getId());
                detailList.add(equityTransactRecordDetail);

                //修改交易记录
                EquityTransactRecord equityTransactRecord = equityTransactRecordSer.findById(equityTransactRecordBO.getId());
                equityTransactRecord.setHoldNum(0);
                equityTransactRecord.setAmount(0d);
                equityTransactRecord.setModifyTime(LocalDateTime.now());
                list.add(equityTransactRecord);
            }
            equityTransactRecordDetailSer.save(detailList);
            equityTransactRecordSer.update(list);
            //重新设置所有股东的占股比例
            equityTransactRecordSer.updateTransList();
        }
        return BeanTransform.copyProperties(freezeEquity, FreezeEquityBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FreezeEquityBO edit(FreezeEquityTO freezeEquityTO) throws SerException {
        checkPermission();
        FreezeEquity freezeEquity = super.findById(freezeEquityTO.getId());
        String equityType = freezeEquity.getEquityType(); //修改之前的股权类型
        //判断股权类型是否被修改
        if (!equityType.equals(freezeEquityTO.getEquityType())) {
            List<EquityTransactRecordBO> equityTransactRecordBOS = equityTransactRecordSer.getByEquityType(equityType);
            if (equityTransactRecordBOS != null && equityTransactRecordBOS.size() > 0) {
                //恢复交易记录数据
                for (EquityTransactRecordBO recordBO : equityTransactRecordBOS) {
                    EquityTransactRecordDetailBO equityTransactRecordDetailBO = equityTransactRecordDetailSer.getByNameId(recordBO.getShareholderName(), freezeEquityTO.getId());
                    equityTransactRecordSer.reinstate(recordBO, equityTransactRecordDetailBO);
                }
                //删除交易明细记录
                equityTransactRecordDetailSer.deleteByTransactId(freezeEquityTO.getId());
            }
            List<EquityTransactRecordBO> equityTransactRecordBOSA = equityTransactRecordSer.getByEquityType(freezeEquityTO.getEquityType());
            if (equityTransactRecordBOSA != null && equityTransactRecordBOSA.size() > 0) {
                List<EquityTransactRecordDetail> detailList = new ArrayList<>();
                List<EquityTransactRecord> list = new ArrayList<>();
                for (EquityTransactRecordBO equityTransactRecordBO : equityTransactRecordBOSA) {
                    //添加交易记录明细记录
                    EquityTransactRecordDetail equityTransactRecordDetail = new EquityTransactRecordDetail();
                    equityTransactRecordDetail.setShareholderName(equityTransactRecordBO.getShareholderName());
                    equityTransactRecordDetail.setTransactDate(LocalDate.now());
                    equityTransactRecordDetail.setHoldNum(-equityTransactRecordBO.getHoldNum());
                    equityTransactRecordDetail.setPerSharePrice(equityTransactRecordBO.getPerSharePrice());
                    equityTransactRecordDetail.setAmount(-equityTransactRecordBO.getAmount());
                    equityTransactRecordDetail.setTransactType("冻结股权");
                    equityTransactRecordDetail.setTransactId(freezeEquity.getId());
                    detailList.add(equityTransactRecordDetail);

                    EquityTransactRecord equityTransactRecord = equityTransactRecordSer.findById(equityTransactRecordBO.getId());
                    equityTransactRecord.setHoldNum(0);
                    equityTransactRecord.setAmount(0d);
                    equityTransactRecord.setModifyTime(LocalDateTime.now());
                    list.add(equityTransactRecord);
                }
                equityTransactRecordDetailSer.save(detailList);
                equityTransactRecordSer.update(list);
                //重新设置所有股东的占股比例
                equityTransactRecordSer.updateTransList();
            }
        }
        //编辑本条数据
        LocalDateTime date = freezeEquity.getCreateTime();
        freezeEquity = BeanTransform.copyProperties(freezeEquityTO, FreezeEquity.class, true);
        freezeEquity.setCreateTime(date);
        freezeEquity.setModifyTime(LocalDateTime.now());
        super.update(freezeEquity);
        return BeanTransform.copyProperties(freezeEquity, FreezeEquityBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        checkPermission();
        FreezeEquity freezeEquity = super.findById(id);
        String equityType = freezeEquity.getEquityType(); //修改之前的股权类型
        List<EquityTransactRecordBO> equityTransactRecordBOS = equityTransactRecordSer.getByEquityType(equityType);
        if (equityTransactRecordBOS != null && equityTransactRecordBOS.size() > 0) {
            //恢复交易记录数据
            for (EquityTransactRecordBO recordBO : equityTransactRecordBOS) {
                EquityTransactRecordDetailBO equityTransactRecordDetailBO = equityTransactRecordDetailSer.getByNameId(recordBO.getShareholderName(), id);
                equityTransactRecordSer.reinstate(recordBO, equityTransactRecordDetailBO);
            }
        }
        //删除交易明细记录
        equityTransactRecordDetailSer.deleteByTransactId(id);
        super.remove(id);
    }

    @Override
    public FreezeEquityLinkDateBO totalNum(String equityType) throws SerException {
        List<EquityTransactRecordBO> equityTransactRecordBOS = equityTransactRecordSer.getByEquityType(equityType);
        FreezeEquityLinkDateBO freezeEquityLinkDateBO = new FreezeEquityLinkDateBO();
        if (equityTransactRecordBOS != null && equityTransactRecordBOS.size() > 0) {
            Integer totalHoldNum = equityTransactRecordBOS.stream().mapToInt(e -> e.getHoldNum()).sum();
            Double totalAmount = equityTransactRecordBOS.stream().mapToDouble(e -> e.getAmount()).sum();
            freezeEquityLinkDateBO.setTotalHoldNum(totalHoldNum);
            freezeEquityLinkDateBO.setTotalAmount(totalAmount);
        }
        return freezeEquityLinkDateBO;
    }
}