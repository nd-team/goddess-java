package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.FairChangeBO;
import com.bjike.goddess.shareholdersmanage.bo.FairTypeAndPerBO;
import com.bjike.goddess.shareholdersmanage.dto.FairChangeDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecordDetail;
import com.bjike.goddess.shareholdersmanage.entity.FairChange;
import com.bjike.goddess.shareholdersmanage.to.FairChangeTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 公允值变动业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-29 05:36 ]
 * @Description: [ 公允值变动业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class FairChangeSerImpl extends ServiceImpl<FairChange, FairChangeDTO> implements FairChangeSer {
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
    @Autowired
    private EquityTransactRecordSer equityTransactRecordSer;
    @Autowired
    private EquityTransactRecordDetailSer equityTransactRecordDetailSer;
    @Override
    public Long countFair(FairChangeDTO fairChangeDTO) throws SerException {
        Long count = super.count(fairChangeDTO);
        return count;
    }

    @Override
    public FairChangeBO getOne(String id) throws SerException {
        FairChange fairChange = super.findById(id);
        return BeanTransform.copyProperties(fairChange, FairChangeBO.class);
    }

    @Override
    public List<FairChangeBO> findList(FairChangeDTO fairChangeDTO) throws SerException {
        checkPermission();
        searchCondi(fairChangeDTO);
        fairChangeDTO.getSorts().add("createTime=desc");
        List<FairChange> equityGifts = super.findByCis(fairChangeDTO);
        return BeanTransform.copyProperties(equityGifts, FairChangeBO.class);
    }
    /**
     * 根据条件查询数据
     *
     * @param fairChangeDTO
     * @throws SerException
     */
    public void searchCondi(FairChangeDTO fairChangeDTO) throws SerException {
        if (StringUtils.isNotBlank(fairChangeDTO.getArea())) {
            fairChangeDTO.getConditions().add(Restrict.eq("area", fairChangeDTO.getArea()));
        }
    }
    @Override
    @Transactional(rollbackFor = SerException.class)
    public FairChangeBO save(FairChangeTO fairChangeTO) throws SerException {
        checkPermission();
        FairChange fairChange = BeanTransform.copyProperties(fairChangeTO,FairChange.class,true);
        fairChange.setCreateTime(LocalDateTime.now());
        super.save(fairChange);
        return BeanTransform.copyProperties(fairChange,FairChangeBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public FairChangeBO edit(FairChangeTO fairChangeTO) throws SerException {
        checkPermission();
        FairChange fairChange = super.findById(fairChangeTO.getId());
        Double perSharePrice = fairChange.getPerSharePrice();
        //判断每股价格是否被修改
        if(!perSharePrice.equals(fairChangeTO.getPerSharePrice())){
            Double shortfall = fairChangeTO.getPerSharePrice()-perSharePrice;//每股价格差
            List<EquityTransactRecordBO> equityTransactRecordBOS = equityTransactRecordSer.getByEquityType(fairChangeTO.getEquityType());
            if (equityTransactRecordBOS!=null && equityTransactRecordBOS.size()>0){
                List<EquityTransactRecordDetail> detailList = new ArrayList<>();
                List<EquityTransactRecord> list = new ArrayList<>();
                for (EquityTransactRecordBO equityTransactRecordBO : equityTransactRecordBOS){
                    Double shortfallMoney = shortfall*equityTransactRecordBO.getHoldNum();//差额
                    //添加交易明细
                    EquityTransactRecordDetail equityTransactRecordDetail = new EquityTransactRecordDetail();
                    equityTransactRecordDetail.setShareholderName(equityTransactRecordBO.getShareholderName());
                    equityTransactRecordDetail.setTransactDate(LocalDate.now());
                    equityTransactRecordDetail.setHoldNum(equityTransactRecordBO.getHoldNum());
                    equityTransactRecordDetail.setPerSharePrice(shortfall);
                    equityTransactRecordDetail.setAmount(shortfallMoney);
                    equityTransactRecordDetail.setTransactType("公允值变动");
                    equityTransactRecordDetail.setTransactId(fairChangeTO.getId());
                    equityTransactRecordDetail.setCreateTime(LocalDateTime.now());
                    detailList.add(equityTransactRecordDetail);
                    //编辑交易记录
                    EquityTransactRecord equityTransactRecord = equityTransactRecordSer.findById(equityTransactRecordBO.getId());
                    equityTransactRecord.setPerSharePrice(fairChangeTO.getPerSharePrice());
                    equityTransactRecord.setAmount(equityTransactRecordBO.getHoldNum()*fairChangeTO.getPerSharePrice());
                    equityTransactRecord.setModifyTime(LocalDateTime.now());
                    list.add(equityTransactRecord);
                }
                equityTransactRecordDetailSer.save(detailList);
                equityTransactRecordSer.update(list);
                //重新设置所有股东的占股比例
                equityTransactRecordSer.updateTransList();
            }
        }
        //编辑本条记录
        LocalDateTime date = fairChange.getCreateTime();
        fairChange = BeanTransform.copyProperties(fairChangeTO,FairChange.class,true);
        fairChange.setCreateTime(date);
        fairChange.setModifyTime(LocalDateTime.now());
        super.update(fairChange);
        return BeanTransform.copyProperties(fairChange,FairChangeBO.class);
    }

    @Override
    public List<FairTypeAndPerBO> typeAndPer() throws SerException {
        List<FairChange> fairChanges = super.findAll();
        return BeanTransform.copyProperties(fairChanges,FairTypeAndPerBO.class);
    }

    @Override
    public Double findPriceByType(String equityType) throws SerException {
        FairChangeDTO fairChangeDTO = new FairChangeDTO();
        fairChangeDTO.getConditions().add(Restrict.eq("equityType",equityType));
        Double perSharePrice = super.findOne(fairChangeDTO).getPerSharePrice();
        return perSharePrice;
    }
}