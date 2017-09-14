package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.*;
import com.bjike.goddess.shareholdersmanage.dto.EquityGiftDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityGift;
import com.bjike.goddess.shareholdersmanage.to.EquityGiftTO;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordDetailTO;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordTO;
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
import java.util.List;

/**
 * 股权赠与业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:08 ]
 * @Description: [ 股权赠与业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class EquityGiftSerImpl extends ServiceImpl<EquityGift, EquityGiftDTO> implements EquityGiftSer {
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
    public Long countGift(EquityGiftDTO equityGiftDTO) throws SerException {
        Long count = super.count(equityGiftDTO);
        return count;
    }

    @Override
    public EquityGiftBO getOne(String id) throws SerException {
        EquityGift equityGift = super.findById(id);
        return BeanTransform.copyProperties(equityGift, EquityGiftBO.class);
    }

    @Override
    public List<EquityGiftBO> findList(EquityGiftDTO equityGiftDTO) throws SerException {
        checkPermission();
        searchCondi(equityGiftDTO);
        equityGiftDTO.getSorts().add("createTime=desc");
        List<EquityGift> equityGifts = super.findByPage(equityGiftDTO);
        return BeanTransform.copyProperties(equityGifts, EquityGiftBO.class);
    }
    /**
     * 根据条件查询数据
     *
     * @param equityGiftDTO
     * @throws SerException
     */
    public void searchCondi(EquityGiftDTO equityGiftDTO) throws SerException {
        if (StringUtils.isNotBlank(equityGiftDTO.getArea())) {
            equityGiftDTO.getConditions().add(Restrict.eq("area", equityGiftDTO.getArea()));
        }
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public EquityGiftBO save(EquityGiftTO equityGiftTO) throws SerException {
       checkPermission();
        //添加本条数据
        EquityGift equityGift = BeanTransform.copyProperties(equityGiftTO,EquityGift.class,true);
        equityGift.setCreateTime(LocalDateTime.now());
        equityGift = super.save(equityGift);
        EquityTransactRecordBO equityTransactRecordBOR = equityTransactRecordSer.getByName(equityGiftTO.getDonor());//赠与人交易记录信息
        EquityTransactRecordBO equityTransactRecordBOE = equityTransactRecordSer.getByName(equityGiftTO.getDonee());//受赠人交易记录信息
        //添加赠与人交易记录明细数据
        EquityTransactRecordDetailTO equityTransactRecordDetailTOR = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTOR.setShareholderName(equityGiftTO.getDonor());
        equityTransactRecordDetailTOR.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTOR.setPerSharePrice(equityTransactRecordBOR.getPerSharePrice());
        equityTransactRecordDetailTOR.setHoldNum(-equityTransactRecordBOR.getHoldNum());
        equityTransactRecordDetailTOR.setAmount(-equityTransactRecordBOR.getAmount());
        equityTransactRecordDetailTOR.setTransactType("股权赠与");
        equityTransactRecordDetailTOR.setTransactId(equityGift.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTOR);

        //添加受赠人交易记录明细数据
        EquityTransactRecordDetailTO equityTransactRecordDetailTOE = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTOE.setShareholderName(equityGiftTO.getDonee());
        equityTransactRecordDetailTOE.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTOE.setPerSharePrice(equityTransactRecordBOR.getPerSharePrice());
        equityTransactRecordDetailTOE.setHoldNum(equityTransactRecordBOR.getHoldNum());
        equityTransactRecordDetailTOE.setAmount(equityTransactRecordBOR.getAmount());
        equityTransactRecordDetailTOE.setTransactType("股权赠与");
        equityTransactRecordDetailTOE.setTransactId(equityGift.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTOE);

        //修改受赠人交易记录数据
        EquityTransactRecordTO equityTransactRecordTOE = new EquityTransactRecordTO();
        equityTransactRecordTOE.setHoldNum(equityTransactRecordBOE.getHoldNum()+equityTransactRecordBOR.getHoldNum());
        equityTransactRecordTOE.setAmount(equityTransactRecordBOE.getAmount()+equityTransactRecordBOR.getAmount());
        equityTransactRecordTOE.setId(equityTransactRecordBOE.getId());
        equityTransactRecordSer.updateTrans(equityTransactRecordTOE);
        //修改赠与人交易记录数据
        EquityTransactRecordTO equityTransactRecordTOR = new EquityTransactRecordTO();
        equityTransactRecordTOR.setHoldNum(0);
        equityTransactRecordTOR.setAmount(0d);
        equityTransactRecordTOR.setId(equityTransactRecordBOR.getId());
        equityTransactRecordSer.updateTrans(equityTransactRecordTOR);

        equityTransactRecordSer.updateTransList();
        return BeanTransform.copyProperties(equityGift,EquityGiftBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public EquityGiftBO edit(EquityGiftTO equityGiftTO) throws SerException {
        checkPermission();
        //判断赠与人和受赠人是否被修改
        EquityGift equityGift = super.findById(equityGiftTO.getId());
        String donor = equityGift.getDonor();//赠与人
        String donee = equityGift.getDonee();//受赠人
        if(!donor.equals(equityGiftTO.getDonor()) || !donee.equals(equityGiftTO.getDonee())){
            //恢复交易记录数据
            EquityTransactRecordBO equityTransactRecordBOR = equityTransactRecordSer.getByName(donor);//修改之前的赠与人信息交易记录
            EquityTransactRecordDetailBO equityTransactRecordDetailBOR = equityTransactRecordDetailSer.getByNameId(donor, equityGiftTO.getId());//修改之前的赠与人信息交易记录明细
            EquityTransactRecordBO equityTransactRecordBOBE = equityTransactRecordSer.getByName(donee);//修改之前的受赠人信息交易记录
            EquityTransactRecordDetailBO equityTransactRecordDetailBOBE = equityTransactRecordDetailSer.getByNameId(donee, equityGiftTO.getId());//修改之前的受赠人信息交易记录明细
            //恢复信息
            equityTransactRecordSer.reinstate(equityTransactRecordBOR, equityTransactRecordDetailBOR);//恢复赠与人信息
            equityTransactRecordSer.reinstate(equityTransactRecordBOBE, equityTransactRecordDetailBOBE);//恢复受赠人信息
            //编辑交易记录明细数据
            EquityTransactRecordBO equityTransactRecordBOAR = equityTransactRecordSer.getByName(equityGiftTO.getDonor());//修改之后的赠与人信息交易记录
            EquityTransactRecordBO equityTransactRecordBOAE = equityTransactRecordSer.getByName(equityGiftTO.getDonee());//修改之后的受赠人信息交易记录
            //赠与人
            EquityTransactRecordDetailTO equityTransactRecordDetailTOBR = new EquityTransactRecordDetailTO();
            equityTransactRecordDetailTOBR.setId(equityTransactRecordDetailBOR.getId());
            equityTransactRecordDetailTOBR.setShareholderName(equityTransactRecordBOAR.getShareholderName());
            equityTransactRecordDetailTOBR.setHoldNum(-equityTransactRecordBOAR.getHoldNum());
            equityTransactRecordDetailTOBR.setTransactDate(LocalDate.now().toString());
            equityTransactRecordDetailTOBR.setPerSharePrice(equityTransactRecordBOAR.getPerSharePrice());
            equityTransactRecordDetailTOBR.setAmount(-equityTransactRecordBOAR.getAmount());
            equityTransactRecordDetailSer.edit(equityTransactRecordDetailTOBR);
            //受赠人
            EquityTransactRecordDetailTO equityTransactRecordDetailTOR = new EquityTransactRecordDetailTO();
            equityTransactRecordDetailTOR.setId(equityTransactRecordDetailBOBE.getId());
            equityTransactRecordDetailTOR.setShareholderName(equityTransactRecordBOAE.getShareholderName());
            equityTransactRecordDetailTOR.setHoldNum(equityTransactRecordBOAR.getHoldNum());
            equityTransactRecordDetailTOR.setTransactDate(LocalDate.now().toString());
            equityTransactRecordDetailTOR.setPerSharePrice(equityTransactRecordBOAR.getPerSharePrice());
            equityTransactRecordDetailTOR.setAmount(equityTransactRecordBOAR.getAmount());
            equityTransactRecordDetailSer.edit(equityTransactRecordDetailTOR);

            //编辑被修改过后的交易记录数据
            //受赠人
            EquityTransactRecordTO equityTransactRecordTOE = new EquityTransactRecordTO();
            equityTransactRecordTOE.setId(equityTransactRecordBOAE.getId());
            equityTransactRecordTOE.setHoldNum(equityTransactRecordBOAE.getHoldNum() + equityTransactRecordBOAR.getHoldNum());
            equityTransactRecordTOE.setAmount(equityTransactRecordBOAE.getAmount() + equityTransactRecordBOAR.getAmount());
            equityTransactRecordSer.updateTrans(equityTransactRecordTOE);

            //赠与人
            EquityTransactRecordTO equityTransactRecordTOR = new EquityTransactRecordTO();
            equityTransactRecordTOR.setId(equityTransactRecordBOAR.getId());
            equityTransactRecordTOR.setHoldNum(0);
            equityTransactRecordTOR.setAmount(0d);
            equityTransactRecordSer.updateTrans(equityTransactRecordTOR);
            //重新设置所有占股比例
            equityTransactRecordSer.updateTransList();
        }
        //修改本条数据
        LocalDateTime date = equityGift.getCreateTime();
        equityGift = BeanTransform.copyProperties(equityGiftTO,EquityGift.class,true);
        equityGift.setCreateTime(date);
        equityGift.setModifyTime(LocalDateTime.now());
        super.update(equityGift);
        return BeanTransform.copyProperties(equityGift,EquityGiftBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        checkPermission();
        //恢复数据
        EquityGift equityGift = super.findById(id);
        String donor = equityGift.getDonor();//赠与人
        String donee = equityGift.getDonee();//受赠人
        EquityTransactRecordBO equityTransactRecordBOR = equityTransactRecordSer.getByName(donor);//修改之前的赠与人信息交易记录
        EquityTransactRecordDetailBO equityTransactRecordDetailBOR = equityTransactRecordDetailSer.getByNameId(donor, id);//修改之前的赠与人信息交易记录明细
        EquityTransactRecordBO equityTransactRecordBOBE = equityTransactRecordSer.getByName(donee);//修改之前的受赠人信息交易记录
        EquityTransactRecordDetailBO equityTransactRecordDetailBOBE = equityTransactRecordDetailSer.getByNameId(donee, id);//修改之前的受赠人信息交易记录明细
        equityTransactRecordSer.reinstate(equityTransactRecordBOR, equityTransactRecordDetailBOR);//恢复赠与人信息
        equityTransactRecordSer.reinstate(equityTransactRecordBOBE, equityTransactRecordDetailBOBE);//恢复受赠人信息
        //重新设置所有占股比例
        equityTransactRecordSer.updateTransList();
        //删除明细数据
        equityTransactRecordDetailSer.deleteByTransactId(id);
        //删除本条数据
        super.remove(id);
    }
}