package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.ChangeEquityTypeBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordDetailBO;
import com.bjike.goddess.shareholdersmanage.dto.ChangeEquityTypeDTO;
import com.bjike.goddess.shareholdersmanage.entity.ChangeEquityType;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecordDetail;
import com.bjike.goddess.shareholdersmanage.excel.SonPermissionObject;
import com.bjike.goddess.shareholdersmanage.to.ChangeEquityTypeTO;
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
 * 变更股权类型业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:42 ]
 * @Description: [ 变更股权类型业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class ChangeEquityTypeSerImpl extends ServiceImpl<ChangeEquityType, ChangeEquityTypeDTO> implements ChangeEquityTypeSer {
    @Autowired
    private EquityTransactRecordSer equityTransactRecordSer;
    @Autowired
    private EquityTransactRecordDetailSer equityTransactRecordDetailSer;
    @Autowired
    private FairChangeSer fairChangeSer;
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
    public Long countChangeType(ChangeEquityTypeDTO changeEquityTypeDTO) throws SerException {
        Long count = super.count(changeEquityTypeDTO);
        return count;
    }

    @Override
    public ChangeEquityTypeBO getOne(String id) throws SerException {
        ChangeEquityType changeEquityType = super.findById(id);
        return BeanTransform.copyProperties(changeEquityType, ChangeEquityTypeBO.class);
    }

    @Override
    public List<ChangeEquityTypeBO> findList(ChangeEquityTypeDTO changeEquityTypeDTO) throws SerException {
        checkPermission();
        changeEquityTypeDTO.getSorts().add("createTime=desc");
        List<ChangeEquityType> changeEquityTypes = super.findByPage(changeEquityTypeDTO);
        return BeanTransform.copyProperties(changeEquityTypes, ChangeEquityTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ChangeEquityTypeBO save(ChangeEquityTypeTO changeEquityTypeTO) throws SerException {
        checkPermission();
        //添加本条数据
        ChangeEquityType changeEquityType = BeanTransform.copyProperties(changeEquityTypeTO, ChangeEquityType.class, true);
        changeEquityType.setCreateTime(LocalDateTime.now());
        changeEquityType = super.save(changeEquityType);

        List<EquityTransactRecordBO> equityTransactRecordBOS = equityTransactRecordSer.getByEquityType(changeEquityTypeTO.getChangeBeforeType());
        if (equityTransactRecordBOS != null && equityTransactRecordBOS.size() > 0) {
            Double changeBeforePrice = fairChangeSer.findPriceByType(changeEquityTypeTO.getChangeBeforeType());//变更之前的股权类型每股价格
            Double changeAfterPrice = fairChangeSer.findPriceByType(changeEquityTypeTO.getChangeAfterType());//变更之后的股权类型每股价格
            Double shortfallPrice = changeAfterPrice - changeBeforePrice;//每股价格差
            List<EquityTransactRecord> list = new ArrayList<>();
            List<EquityTransactRecordDetail> detailList = new ArrayList<>();
            for (EquityTransactRecordBO equityTransactRecordBO : equityTransactRecordBOS) {
                //添加交易记录明细记录
                Double shortfallMoney = shortfallPrice * equityTransactRecordBO.getHoldNum();//差额
                EquityTransactRecordDetail equityTransactRecordDetail = new EquityTransactRecordDetail();
                equityTransactRecordDetail.setShareholderName(equityTransactRecordBO.getShareholderName());
                equityTransactRecordDetail.setTransactDate(LocalDate.now());
                equityTransactRecordDetail.setHoldNum(equityTransactRecordBO.getHoldNum());
                equityTransactRecordDetail.setPerSharePrice(shortfallPrice);
                equityTransactRecordDetail.setAmount(shortfallMoney);
                equityTransactRecordDetail.setTransactType("变更股权类型");
                equityTransactRecordDetail.setTransactId(changeEquityType.getId());
                detailList.add(equityTransactRecordDetail);
                //修改交易记录的股权类型
                EquityTransactRecord equityTransactRecord = equityTransactRecordSer.findById(equityTransactRecordBO.getId());
                equityTransactRecord.setPerSharePrice(changeAfterPrice);
                equityTransactRecord.setEquityType(changeEquityTypeTO.getChangeAfterType());
                equityTransactRecord.setAmount(changeAfterPrice * equityTransactRecordBO.getHoldNum());
                equityTransactRecord.setModifyTime(LocalDateTime.now());
                list.add(equityTransactRecord);
            }
            equityTransactRecordDetailSer.save(detailList);
            equityTransactRecordSer.update(list);
            //重新设置所有股东的占股比例
            equityTransactRecordSer.updateTransList();
        }
        return BeanTransform.copyProperties(changeEquityType, ChangeEquityTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ChangeEquityTypeBO edit(ChangeEquityTypeTO changeEquityTypeTO) throws SerException {
        checkPermission();
        ChangeEquityType changeEquityType = super.findById(changeEquityTypeTO.getId());
        String changeBeforeType = changeEquityType.getChangeBeforeType(); //修改之前的变更前股权类型
        String changeAfterType = changeEquityType.getChangeAfterType(); //修改之前的变更后股权类型
        //判断变更前股权类型和变更后股权类型是否被修改
        if (!changeBeforeType.equals(changeEquityTypeTO.getChangeBeforeType()) || !changeAfterType.equals(changeEquityTypeTO.getChangeAfterType())) {
            //恢复之前数据
            reinstateType(changeEquityTypeTO.getId(), changeBeforeType);
            equityTransactRecordDetailSer.deleteByTransactId(changeEquityTypeTO.getId());
            //重新进行修改交易记录数据和添加交易记录明细数据
            List<EquityTransactRecordBO> equityTransactRecordBOS = equityTransactRecordSer.getByEquityType(changeEquityTypeTO.getChangeBeforeType());
            if (equityTransactRecordBOS != null && equityTransactRecordBOS.size() > 0) {
                Double changeBeforePriceA = fairChangeSer.findPriceByType(changeEquityTypeTO.getChangeBeforeType());//修改之后变更之前的股权类型每股价格
                Double changeAfterPriceA = fairChangeSer.findPriceByType(changeEquityTypeTO.getChangeAfterType());//修改之后变更之后的股权类型每股价格
                Double shortfallPrice = changeAfterPriceA - changeBeforePriceA;//每股价格差
                List<EquityTransactRecord> list = new ArrayList<>();
                List<EquityTransactRecordDetail> detailList = new ArrayList<>();
                for (EquityTransactRecordBO equityTransactRecordBO : equityTransactRecordBOS) {
                    //添加交易记录明细记录
                    Double shortfallMoney = shortfallPrice * equityTransactRecordBO.getHoldNum();//差额
                    EquityTransactRecordDetail equityTransactRecordDetail = new EquityTransactRecordDetail();
                    equityTransactRecordDetail.setShareholderName(equityTransactRecordBO.getShareholderName());
                    equityTransactRecordDetail.setTransactDate(LocalDate.now());
                    equityTransactRecordDetail.setHoldNum(equityTransactRecordBO.getHoldNum());
                    equityTransactRecordDetail.setPerSharePrice(shortfallPrice);
                    equityTransactRecordDetail.setAmount(shortfallMoney);
                    equityTransactRecordDetail.setTransactType("变更股权类型");
                    equityTransactRecordDetail.setTransactId(changeEquityType.getId());
                    detailList.add(equityTransactRecordDetail);
                    //修改交易记录的股权类型
                    EquityTransactRecord equityTransactRecord = equityTransactRecordSer.findById(equityTransactRecordBO.getId());
                    equityTransactRecord.setPerSharePrice(changeAfterPriceA);
                    equityTransactRecord.setEquityType(changeEquityTypeTO.getChangeAfterType());
                    equityTransactRecord.setAmount(changeAfterPriceA * equityTransactRecordBO.getHoldNum());
                    equityTransactRecord.setModifyTime(LocalDateTime.now());
                    list.add(equityTransactRecord);
                }
                equityTransactRecordDetailSer.save(detailList);
                equityTransactRecordSer.update(list);
                //重新设置所有股东的占股比例
                equityTransactRecordSer.updateTransList();
            }
        }
        LocalDateTime date = changeEquityType.getCreateTime();
        changeEquityType = BeanTransform.copyProperties(changeEquityTypeTO, ChangeEquityType.class, true);
        changeEquityType.setCreateTime(date);
        changeEquityType.setModifyTime(LocalDateTime.now());
        super.update(changeEquityType);
        return BeanTransform.copyProperties(changeEquityType, ChangeEquityTypeBO.class);
    }

    /**
     * 交易记录表恢复所有的类型
     */
    public void reinstateType(String id, String changeBeforeType) throws SerException {
        List<EquityTransactRecordDetailBO> equityTransactRecordDetailBOS = equityTransactRecordDetailSer.getByTransactId(id);
        Double changeBeforePrice = fairChangeSer.findPriceByType(changeBeforeType);//变更之前的股权类型每股价格
        if (equityTransactRecordDetailBOS != null) {
            List<EquityTransactRecord> list = new ArrayList<>();
            for (EquityTransactRecordDetailBO detailBO : equityTransactRecordDetailBOS) {
                EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordSer.getByName(detailBO.getShareholderName());
                EquityTransactRecord equityTransactRecord = equityTransactRecordSer.findById(equityTransactRecordBO.getId());
                equityTransactRecord.setEquityType(changeBeforeType);
                equityTransactRecord.setPerSharePrice(changeBeforePrice);
                equityTransactRecord.setAmount(changeBeforePrice * detailBO.getHoldNum());
                equityTransactRecord.setModifyTime(LocalDateTime.now());
                list.add(equityTransactRecord);
            }
            equityTransactRecordSer.update(list);
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        checkPermission();
        ChangeEquityType changeEquityType = super.findById(id);
        //恢复所有数据
        reinstateType(id, changeEquityType.getChangeBeforeType());
        equityTransactRecordSer.updateTransList();
        equityTransactRecordDetailSer.deleteByTransactId(id);
        //删除本条记录
        super.remove(id);
        //重新设置所有股东的占股比例
        equityTransactRecordSer.updateTransList();
    }

}