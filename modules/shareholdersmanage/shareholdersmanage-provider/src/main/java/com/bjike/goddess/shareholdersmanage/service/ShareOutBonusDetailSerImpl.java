package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.ShareOutBonusDetailBO;
import com.bjike.goddess.shareholdersmanage.bo.ShareOutBonusManageBO;
import com.bjike.goddess.shareholdersmanage.dto.ShareOutBonusDetailDTO;
import com.bjike.goddess.shareholdersmanage.entity.ShareOutBonusDetail;
import com.bjike.goddess.shareholdersmanage.entity.ShareOutBonusManage;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOutBonusDetailTO;
import com.bjike.goddess.shareholdersmanage.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 分红明细业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:55 ]
 * @Description: [ 分红明细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class ShareOutBonusDetailSerImpl extends ServiceImpl<ShareOutBonusDetail, ShareOutBonusDetailDTO> implements ShareOutBonusDetailSer {
   @Autowired
   private ShareOutBonusManageSer shareOutBonusManageSer;
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
    public Long countOutBonus(ShareOutBonusDetailDTO shareOutBonusDetailDTO) throws SerException {
        Long count = super.count(shareOutBonusDetailDTO);
        return count;
    }

    @Override
    public ShareOutBonusDetailBO getOne(String id) throws SerException {
        ShareOutBonusDetail shareOutBonusDetail = super.findById(id);
        return BeanTransform.copyProperties(shareOutBonusDetail, ShareOutBonusDetailBO.class);
    }

    @Override
    public List<ShareOutBonusDetailBO> findListBySharId(String shareOutBonusManageId) throws SerException {
        checkPermission();
        ShareOutBonusDetailDTO shareOutBonusDetailDTO = new ShareOutBonusDetailDTO();
        shareOutBonusDetailDTO.getConditions().add(Restrict.eq("shareOutBonusManageId",shareOutBonusManageId));
        List<ShareOutBonusDetail> shareOutBonusDetails = super.findByCis(shareOutBonusDetailDTO);
        return BeanTransform.copyProperties(shareOutBonusDetails,ShareOutBonusDetailBO.class);
    }

    @Override
    public ShareOutBonusDetailBO save(ShareOutBonusDetailTO shareOutBonusDetailTO) throws SerException {
       checkPermission();
        ShareOutBonusDetail shareOutBonusDetail = BeanTransform.copyProperties(shareOutBonusDetailTO,ShareOutBonusDetail.class,true);
        ShareOutBonusManageBO shareOutBonusManageBO = shareOutBonusManageSer.getOne(shareOutBonusDetailTO.getShareOutBonusManageId());
        shareOutBonusDetail.setCreateTime(LocalDateTime.now());
        shareOutBonusDetail.setTotalShareOutBonus(shareOutBonusManageBO.getTotalShareOutBonus());
        super.save(shareOutBonusDetail);
        //重新设置分红管理的共缴税
        ShareOutBonusManage shareOutBonusManage = shareOutBonusManageSer.findById(shareOutBonusDetailTO.getShareOutBonusManageId());
        List<ShareOutBonusDetailBO> shareOutBonusDetailBOS = findListBySharId(shareOutBonusDetailTO.getShareOutBonusManageId());
        Double totalIncomeTax = shareOutBonusDetailBOS.stream().mapToDouble(s->s.getIncomeTax()).sum();
        shareOutBonusManage.setTotalIncomeTax(totalIncomeTax);
        shareOutBonusManage.setModifyTime(LocalDateTime.now());
        shareOutBonusManageSer.update(shareOutBonusManage);
        return BeanTransform.copyProperties(shareOutBonusDetail,ShareOutBonusDetailBO.class);
    }

    @Override
    public ShareOutBonusDetailBO edit(ShareOutBonusDetailTO shareOutBonusDetailTO) throws SerException {
        checkPermission();
        ShareOutBonusDetail shareOutBonusDetail = super.findById(shareOutBonusDetailTO.getId());
        Double totalShareOutBonus = shareOutBonusDetail.getTotalShareOutBonus();
        LocalDateTime date = shareOutBonusDetail.getCreateTime();
        shareOutBonusDetail = BeanTransform.copyProperties(shareOutBonusDetailTO,ShareOutBonusDetail.class,true);
        shareOutBonusDetail.setTotalShareOutBonus(totalShareOutBonus);
        shareOutBonusDetail.setCreateTime(date);
        shareOutBonusDetail.setModifyTime(LocalDateTime.now());
        super.update(shareOutBonusDetail);
        //重新设置分红管理的共缴税
        ShareOutBonusManage shareOutBonusManage = shareOutBonusManageSer.findById(shareOutBonusDetailTO.getShareOutBonusManageId());
        List<ShareOutBonusDetailBO> shareOutBonusDetailBOS = findListBySharId(shareOutBonusDetailTO.getShareOutBonusManageId());
        Double totalIncomeTax = shareOutBonusDetailBOS.stream().mapToDouble(s->s.getIncomeTax()).sum();
        shareOutBonusManage.setTotalIncomeTax(totalIncomeTax);
        shareOutBonusManage.setModifyTime(LocalDateTime.now());
        shareOutBonusManageSer.update(shareOutBonusManage);
        return BeanTransform.copyProperties(shareOutBonusDetail,ShareOutBonusDetailBO.class);
    }

    @Override
    public void delete(String id) throws SerException {
       checkPermission();
        ShareOutBonusDetail shareOutBonusDetail = super.findById(id);
        super.remove(id);
        ShareOutBonusManage shareOutBonusManage = shareOutBonusManageSer.findById(shareOutBonusDetail.getShareOutBonusManageId());
        List<ShareOutBonusDetailBO> shareOutBonusDetailBOS = findListBySharId(shareOutBonusDetail.getShareOutBonusManageId());
        Double totalIncomeTax = shareOutBonusDetailBOS.stream().mapToDouble(s->s.getIncomeTax()).sum();
        shareOutBonusManage.setTotalIncomeTax(totalIncomeTax);
        shareOutBonusManage.setModifyTime(LocalDateTime.now());
        shareOutBonusManageSer.update(shareOutBonusManage);
    }

    @Override
    public void deleteByShareId(String ShareOutBonusManageId) throws SerException {
        ShareOutBonusDetailDTO shareOutBonusDetailDTO = new ShareOutBonusDetailDTO();
        shareOutBonusDetailDTO.getConditions().add(Restrict.eq("ShareOutBonusManageId",ShareOutBonusManageId));
        List<ShareOutBonusDetail> shareOutBonusDetails = super.findByCis(shareOutBonusDetailDTO);
        super.remove(shareOutBonusDetails);
    }

    @Override
    public Double computAmount(String ShareOutBonusManageId, Double shareOutBonusPropor) throws SerException{
        ShareOutBonusManageBO shareOutBonusManageBO = shareOutBonusManageSer.getOne(ShareOutBonusManageId);
        Double shareOutBonusAmount = shareOutBonusManageBO.getTotalShareOutBonus()*(shareOutBonusPropor/100);
        return shareOutBonusAmount;
    }

    @Override
    public Double computIncomeTax(Double shareOutBonusAmount, Double incomeTaxPropor) throws SerException {
        Double incomeTax = shareOutBonusAmount*(incomeTaxPropor/100);
        return incomeTax;
    }
}