package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.EquityCoalescBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordDetailBO;
import com.bjike.goddess.shareholdersmanage.bo.ProportioAnmountBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityCoalescDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityCoalesc;
import com.bjike.goddess.shareholdersmanage.entity.EquityGift;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.to.EquityCoalescTO;
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
 * 股权合并业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:24 ]
 * @Description: [ 股权合并业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class EquityCoalescSerImpl extends ServiceImpl<EquityCoalesc, EquityCoalescDTO> implements EquityCoalescSer {
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
    public Long countCoalesc(EquityCoalescDTO equityCoalescDTO) throws SerException {
        Long count = super.count(equityCoalescDTO);
        return count;
    }

    @Override
    public EquityCoalescBO getOne(String id) throws SerException {
        EquityCoalesc equityGift = super.findById(id);
        return BeanTransform.copyProperties(equityGift, EquityCoalescBO.class);
    }



    @Override
    public List<EquityCoalescBO> findList(EquityCoalescDTO equityCoalescDTO) throws SerException {
        checkPermission();
        searchCondi(equityCoalescDTO);
        equityCoalescDTO.getSorts().add("createTime=desc");
        List<EquityCoalesc> equityGifts = super.findByPage(equityCoalescDTO);
        return BeanTransform.copyProperties(equityGifts, EquityCoalescBO.class);
    }
    /**
     * 根据条件查询数据
     *
     * @param equityCoalescDTO
     * @throws SerException
     */
    public void searchCondi(EquityCoalescDTO equityCoalescDTO) throws SerException {
        if (StringUtils.isNotBlank(equityCoalescDTO.getArea())) {
            equityCoalescDTO.getConditions().add(Restrict.eq("area", equityCoalescDTO.getArea()));
        }
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public EquityCoalescBO save(EquityCoalescTO equityCoalescTO) throws SerException {
        checkPermission();
        //添加本条数据
        EquityCoalesc equityCoalesc = BeanTransform.copyProperties(equityCoalescTO,EquityCoalesc.class,true);
        equityCoalesc.setCreateTime(LocalDateTime.now());
        equityCoalesc = super.save(equityCoalesc);
        EquityTransactRecordBO equityTransactRecordBOBC = equityTransactRecordSer.getByName(equityCoalescTO.getBeCombined());//被合并方交易记录信息
        EquityTransactRecordBO equityTransactRecordBOC = equityTransactRecordSer.getByName(equityCoalescTO.getCombined());//合并方交易记录信息
        //添加交易记录明细数据
        //被合并方
        EquityTransactRecordDetailTO equityTransactRecordDetailTOBC = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTOBC.setShareholderName(equityCoalescTO.getBeCombined());
        equityTransactRecordDetailTOBC.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTOBC.setPerSharePrice(equityTransactRecordBOBC.getPerSharePrice());
        equityTransactRecordDetailTOBC.setHoldNum(-equityTransactRecordBOBC.getHoldNum());
        equityTransactRecordDetailTOBC.setAmount(-equityTransactRecordBOBC.getAmount());
        equityTransactRecordDetailTOBC.setTransactType("股权合并");
        equityTransactRecordDetailTOBC.setTransactId(equityCoalesc.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTOBC);
        //合并方
        EquityTransactRecordDetailTO equityTransactRecordDetailTOC = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTOC.setShareholderName(equityCoalescTO.getCombined());
        equityTransactRecordDetailTOC.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTOC.setPerSharePrice(equityTransactRecordBOBC.getPerSharePrice());
        equityTransactRecordDetailTOC.setHoldNum(equityTransactRecordBOBC.getHoldNum());
        equityTransactRecordDetailTOC.setAmount(equityTransactRecordBOBC.getAmount());
        equityTransactRecordDetailTOC.setTransactType("股权合并");
        equityTransactRecordDetailTOC.setTransactId(equityCoalesc.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTOC);
        //编辑交易记录
        //合并方
        EquityTransactRecordTO equityTransactRecordTOC = new EquityTransactRecordTO();
        equityTransactRecordTOC.setHoldNum(equityTransactRecordBOC.getHoldNum()+equityTransactRecordBOBC.getHoldNum());
        equityTransactRecordTOC.setAmount(equityTransactRecordBOC.getAmount()+equityTransactRecordBOBC.getAmount());
        equityTransactRecordTOC.setId(equityTransactRecordBOC.getId());
        equityTransactRecordSer.updateTrans(equityTransactRecordTOC);
        //被合并方
        EquityTransactRecordTO equityTransactRecordTOBC = new EquityTransactRecordTO();
        equityTransactRecordTOBC.setHoldNum(0);
        equityTransactRecordTOBC.setAmount(0d);
        equityTransactRecordTOBC.setId(equityTransactRecordBOBC.getId());
        equityTransactRecordSer.updateTrans(equityTransactRecordTOBC);
        //重新设置所有股东的占股比例
        equityTransactRecordSer.updateTransList();
        return BeanTransform.copyProperties(equityCoalesc,EquityCoalescBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public EquityCoalescBO edit(EquityCoalescTO equityCoalescTO) throws SerException {
       checkPermission();
        //判断合并方和被合并方时候被修改
        EquityCoalesc equityCoalesc = super.findById(equityCoalescTO.getId());
        String combined = equityCoalesc.getCombined();//合并方
        String beCombined = equityCoalesc.getBeCombined();//被合并方
        if(!combined.equals(equityCoalescTO.getCombined()) || beCombined.equals(equityCoalescTO.getBeCombined())){
            //恢复交易记录数据
            EquityTransactRecordBO equityTransactRecordBOC = equityTransactRecordSer.getByName(combined);//修改之前的合并方信息交易记录
            EquityTransactRecordDetailBO equityTransactRecordDetailBOC = equityTransactRecordDetailSer.getByNameId(combined, equityCoalescTO.getId());//修改之前的合并方信息交易记录明细
            EquityTransactRecordBO equityTransactRecordBOBC = equityTransactRecordSer.getByName(beCombined);//修改之前的被合并方信息交易记录
            EquityTransactRecordDetailBO equityTransactRecordDetailBOBC = equityTransactRecordDetailSer.getByNameId(beCombined, equityCoalescTO.getId());//修改之前的被合并方信息交易记录明细
            equityTransactRecordSer.reinstate(equityTransactRecordBOC, equityTransactRecordDetailBOC);//恢复合并方信息
            equityTransactRecordSer.reinstate(equityTransactRecordBOBC, equityTransactRecordDetailBOBC);//恢复被合并方信息
            //编辑交易明细记录
            EquityTransactRecordBO equityTransactRecordBOAC = equityTransactRecordSer.getByName(equityCoalescTO.getCombined());//修改之后的合并方信息交易记录
            EquityTransactRecordBO equityTransactRecordBOABC = equityTransactRecordSer.getByName(equityCoalescTO.getBeCombined());//修改之后的被合并方信息交易记录
            //合并方
            EquityTransactRecordDetailTO equityTransactRecordDetailTOAC = new EquityTransactRecordDetailTO();
            equityTransactRecordDetailTOAC.setId(equityTransactRecordDetailBOC.getId());
            equityTransactRecordDetailTOAC.setShareholderName(equityTransactRecordBOAC.getShareholderName());
            equityTransactRecordDetailTOAC.setHoldNum(equityTransactRecordBOABC.getHoldNum());
            equityTransactRecordDetailTOAC.setTransactDate(LocalDate.now().toString());
            equityTransactRecordDetailTOAC.setPerSharePrice(equityTransactRecordBOABC.getPerSharePrice());
            equityTransactRecordDetailTOAC.setAmount(equityTransactRecordBOABC.getAmount());
            equityTransactRecordDetailSer.edit(equityTransactRecordDetailTOAC);
            //被合并方
            EquityTransactRecordDetailTO equityTransactRecordDetailTOABC = new EquityTransactRecordDetailTO();
            equityTransactRecordDetailTOABC.setId(equityTransactRecordDetailBOBC.getId());
            equityTransactRecordDetailTOABC.setShareholderName(equityTransactRecordBOABC.getShareholderName());
            equityTransactRecordDetailTOABC.setHoldNum(-equityTransactRecordBOABC.getHoldNum());
            equityTransactRecordDetailTOABC.setTransactDate(LocalDate.now().toString());
            equityTransactRecordDetailTOABC.setPerSharePrice(equityTransactRecordBOABC.getPerSharePrice());
            equityTransactRecordDetailTOABC.setAmount(-equityTransactRecordBOABC.getAmount());
            equityTransactRecordDetailSer.edit(equityTransactRecordDetailTOABC);
            //编辑交易记录信息
            //合并方
            EquityTransactRecordTO equityTransactRecordTOC = new EquityTransactRecordTO();
            equityTransactRecordTOC.setId(equityTransactRecordBOAC.getId());
            equityTransactRecordTOC.setHoldNum(equityTransactRecordBOAC.getHoldNum() + equityTransactRecordBOABC.getHoldNum());
            equityTransactRecordTOC.setAmount(equityTransactRecordBOAC.getAmount() + equityTransactRecordBOABC.getAmount());
            equityTransactRecordSer.updateTrans(equityTransactRecordTOC);
            //被合并方
            EquityTransactRecordTO equityTransactRecordTOBC = new EquityTransactRecordTO();
            equityTransactRecordTOBC.setId(equityTransactRecordBOABC.getId());
            equityTransactRecordTOBC.setHoldNum(0);
            equityTransactRecordTOBC.setAmount(0d);
            equityTransactRecordSer.updateTrans(equityTransactRecordTOBC);
            //重新设置所有占股比例
            equityTransactRecordSer.updateTransList();
        }

        //编辑本条记录
        LocalDateTime date = equityCoalesc.getCreateTime();
        equityCoalesc = BeanTransform.copyProperties(equityCoalescTO,EquityCoalesc.class,true);
        equityCoalesc.setCreateTime(date);
        equityCoalesc.setModifyTime(LocalDateTime.now());
        super.update(equityCoalesc);
        return BeanTransform.copyProperties(equityCoalesc,EquityCoalescBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
       checkPermission();
        //恢复交易数据
        EquityCoalesc equityCoalesc = super.findById(id);
        String combined = equityCoalesc.getCombined();//合并方
        String beCombined = equityCoalesc.getBeCombined();//被合并方
        EquityTransactRecordBO equityTransactRecordBOC = equityTransactRecordSer.getByName(combined);//修改之前的合并方信息交易记录
        EquityTransactRecordDetailBO equityTransactRecordDetailBOC = equityTransactRecordDetailSer.getByNameId(combined, id);//修改之前的合并方信息交易记录明细
        EquityTransactRecordBO equityTransactRecordBOBC = equityTransactRecordSer.getByName(beCombined);//修改之前的被合并方信息交易记录
        EquityTransactRecordDetailBO equityTransactRecordDetailBOBC = equityTransactRecordDetailSer.getByNameId(beCombined, id);//修改之前的被合并方信息交易记录明细
        equityTransactRecordSer.reinstate(equityTransactRecordBOC, equityTransactRecordDetailBOC);//恢复合并方信息
        equityTransactRecordSer.reinstate(equityTransactRecordBOBC, equityTransactRecordDetailBOBC);//恢复被合并方信息
        //重新设置所有占股比例
        equityTransactRecordSer.updateTransList();
        //删除交易明细记录
        equityTransactRecordDetailSer.deleteByTransactId(id);
        //删除本条数据
        super.remove(id);
    }

    @Override
    public ProportioAnmountBO proAnmount(String beCombined) throws SerException {
        EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordSer.getByName(beCombined);
        ProportioAnmountBO proportioAnmountBO = new ProportioAnmountBO();
        proportioAnmountBO.setPercentage(equityTransactRecordBO.getPercentage());
        proportioAnmountBO.setAmount(equityTransactRecordBO.getAmount());
        return proportioAnmountBO;
    }
}