package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.EquityInheritanceBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordDetailBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityInheritanceDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityInheritance;
import com.bjike.goddess.shareholdersmanage.to.EquityInheritanceTO;
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
 * 股权继承业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:06 ]
 * @Description: [ 股权继承业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class EquityInheritanceSerImpl extends ServiceImpl<EquityInheritance, EquityInheritanceDTO> implements EquityInheritanceSer {
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
    public Long countInheritance(EquityInheritanceDTO equityInheritanceDTO) throws SerException {
        Long count = super.count(equityInheritanceDTO);
        return count;
    }

    @Override
    public EquityInheritanceBO getOne(String id) throws SerException {
        EquityInheritance equityInheritance = super.findById(id);
        return BeanTransform.copyProperties(equityInheritance, EquityInheritanceBO.class);
    }

    @Override
    public List<EquityInheritanceBO> findList(EquityInheritanceDTO equityInheritanceDTO) throws SerException {
        checkPermission();
        searchCondi(equityInheritanceDTO);
        equityInheritanceDTO.getSorts().add("createTime=desc");
        List<EquityInheritance> equityInheritanceList = super.findByCis(equityInheritanceDTO);
        return BeanTransform.copyProperties(equityInheritanceList, EquityInheritanceBO.class);
    }
    /**
     * 根据条件查询数据
     *
     * @param equityInheritanceDTO
     * @throws SerException
     */
    public void searchCondi(EquityInheritanceDTO equityInheritanceDTO) throws SerException {
        if (StringUtils.isNotBlank(equityInheritanceDTO.getArea())) {
            equityInheritanceDTO.getConditions().add(Restrict.eq("area", equityInheritanceDTO.getArea()));
        }
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public EquityInheritanceBO save(EquityInheritanceTO equityInheritanceTO) throws SerException {
       checkPermission();
        //添加本条数据
        EquityInheritance equityInheritance = BeanTransform.copyProperties(equityInheritanceTO,EquityInheritance.class,true);
        equityInheritance.setCreateTime(LocalDateTime.now());
        equityInheritance = super.save(equityInheritance);
        EquityTransactRecordBO equityTransactRecordBOH = equityTransactRecordSer.getByName(equityInheritanceTO.getHeir());//继承人交易记录信息
        EquityTransactRecordBO equityTransactRecordBOBH = equityTransactRecordSer.getByName(equityInheritanceTO.getBeHeir());//被继承人交易记录信息
        //添加交易记录明细数据
        //继承人
        EquityTransactRecordDetailTO equityTransactRecordDetailTOH = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTOH.setShareholderName(equityInheritanceTO.getHeir());
        equityTransactRecordDetailTOH.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTOH.setPerSharePrice(equityTransactRecordBOBH.getPerSharePrice());
        equityTransactRecordDetailTOH.setHoldNum(equityTransactRecordBOBH.getHoldNum());
        equityTransactRecordDetailTOH.setAmount(equityTransactRecordBOBH.getAmount());
        equityTransactRecordDetailTOH.setTransactType("股权继承");
        equityTransactRecordDetailTOH.setTransactId(equityInheritance.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTOH);

        //被继承人
        EquityTransactRecordDetailTO equityTransactRecordDetailTOBH = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTOBH.setShareholderName(equityInheritanceTO.getBeHeir());
        equityTransactRecordDetailTOBH.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTOBH.setPerSharePrice(equityTransactRecordBOBH.getPerSharePrice());
        equityTransactRecordDetailTOBH.setHoldNum(-equityTransactRecordBOBH.getHoldNum());
        equityTransactRecordDetailTOBH.setAmount(-equityTransactRecordBOBH.getAmount());
        equityTransactRecordDetailTOBH.setTransactType("股权继承");
        equityTransactRecordDetailTOBH.setTransactId(equityInheritance.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTOBH);
        //修改交易记录数据
        //继承人
        EquityTransactRecordTO equityTransactRecordTOH = new EquityTransactRecordTO();
        equityTransactRecordTOH.setHoldNum(equityTransactRecordBOH.getHoldNum()+equityTransactRecordBOBH.getHoldNum());
        equityTransactRecordTOH.setAmount(equityTransactRecordBOH.getAmount()+equityTransactRecordBOBH.getAmount());
        equityTransactRecordTOH.setId(equityTransactRecordBOH.getId());
        equityTransactRecordSer.updateTrans(equityTransactRecordTOH);

        //被继承人
        EquityTransactRecordTO equityTransactRecordTOBH = new EquityTransactRecordTO();
        equityTransactRecordTOBH.setHoldNum(0);
        equityTransactRecordTOBH.setAmount(0d);
        equityTransactRecordTOBH.setId(equityTransactRecordBOBH.getId());
        equityTransactRecordSer.updateTrans(equityTransactRecordTOBH);
        equityTransactRecordSer.updateTransList();
        return BeanTransform.copyProperties(equityInheritance,EquityInheritanceBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public EquityInheritanceBO edit(EquityInheritanceTO equityInheritanceTO) throws SerException {
       checkPermission();
        //判断继承人和被继承人是否被修改
        EquityInheritance equityInheritance = super.findById(equityInheritanceTO.getId());
        String heir = equityInheritance.getHeir();//继承人
        String beHeir = equityInheritance.getBeHeir();//被继承人
        if(heir.equals(equityInheritanceTO.getHeir()) || beHeir.equals(equityInheritanceTO.getBeHeir())){
            //恢复交易记录数据
            EquityTransactRecordBO equityTransactRecordBOH = equityTransactRecordSer.getByName(heir);//修改之前的继承人信息交易记录
            EquityTransactRecordDetailBO equityTransactRecordDetailBOH = equityTransactRecordDetailSer.getByNameId(heir, equityInheritanceTO.getId());//修改之前的继承人信息交易记录明细
            EquityTransactRecordBO equityTransactRecordBOBH = equityTransactRecordSer.getByName(beHeir);//修改之前的被继承人信息交易记录
            EquityTransactRecordDetailBO equityTransactRecordDetailBOBH = equityTransactRecordDetailSer.getByNameId(beHeir, equityInheritanceTO.getId());//修改之前的被继承人信息交易记录明细
            //恢复信息
            equityTransactRecordSer.reinstate(equityTransactRecordBOH, equityTransactRecordDetailBOH);//恢复变更前股东信息
            equityTransactRecordSer.reinstate(equityTransactRecordBOBH, equityTransactRecordDetailBOBH);//恢复变更后股东信息

            //修改交易记录明细数据
            EquityTransactRecordBO equityTransactRecordBOAH = equityTransactRecordSer.getByName(equityInheritanceTO.getHeir());//修改之后的继承人信息交易记录
            EquityTransactRecordBO equityTransactRecordBOABH = equityTransactRecordSer.getByName(equityInheritanceTO.getBeHeir());//修改之后的被继承人信息交易记录
            //继承人
            EquityTransactRecordDetailTO equityTransactRecordDetailTOH = new EquityTransactRecordDetailTO();
            equityTransactRecordDetailTOH.setId(equityTransactRecordDetailBOH.getId());
            equityTransactRecordDetailTOH.setShareholderName(equityTransactRecordBOAH.getShareholderName());
            equityTransactRecordDetailTOH.setHoldNum(equityTransactRecordBOABH.getHoldNum());
            equityTransactRecordDetailTOH.setTransactDate(LocalDate.now().toString());
            equityTransactRecordDetailTOH.setPerSharePrice(equityTransactRecordBOABH.getPerSharePrice());
            equityTransactRecordDetailTOH.setAmount(equityTransactRecordBOABH.getAmount());
            equityTransactRecordDetailSer.edit(equityTransactRecordDetailTOH);

            //被继承人
            EquityTransactRecordDetailTO equityTransactRecordDetailTOBH = new EquityTransactRecordDetailTO();
            equityTransactRecordDetailTOBH.setId(equityTransactRecordDetailBOBH.getId());
            equityTransactRecordDetailTOBH.setShareholderName(equityTransactRecordBOABH.getShareholderName());
            equityTransactRecordDetailTOBH.setHoldNum(-equityTransactRecordBOABH.getHoldNum());
            equityTransactRecordDetailTOBH.setTransactDate(LocalDate.now().toString());
            equityTransactRecordDetailTOBH.setPerSharePrice(equityTransactRecordBOABH.getPerSharePrice());
            equityTransactRecordDetailTOBH.setAmount(-equityTransactRecordBOABH.getAmount());
            equityTransactRecordDetailSer.edit(equityTransactRecordDetailTOBH);
            //修改被修改交易记录数据
            //继承人
            EquityTransactRecordTO equityTransactRecordTOH = new EquityTransactRecordTO();
            equityTransactRecordTOH.setId(equityTransactRecordBOAH.getId());
            equityTransactRecordTOH.setHoldNum(equityTransactRecordBOAH.getHoldNum() + equityTransactRecordBOABH.getHoldNum());
            equityTransactRecordTOH.setAmount(equityTransactRecordBOAH.getAmount() + equityTransactRecordBOABH.getAmount());
            equityTransactRecordSer.updateTrans(equityTransactRecordTOH);

            //被继承人
            EquityTransactRecordTO equityTransactRecordTOBH = new EquityTransactRecordTO();
            equityTransactRecordTOBH.setId(equityTransactRecordBOABH.getId());
            equityTransactRecordTOBH.setHoldNum(0);
            equityTransactRecordTOBH.setAmount(0d);
            equityTransactRecordSer.updateTrans(equityTransactRecordTOBH);

            equityTransactRecordSer.updateTransList();
        }
        //修改本条数据
        LocalDateTime date = equityInheritance.getCreateTime();
        equityInheritance = BeanTransform.copyProperties(equityInheritanceTO,EquityInheritance.class,true);
        equityInheritance.setCreateTime(date);
        equityInheritance.setModifyTime(LocalDateTime.now());
        super.update(equityInheritance);
        return BeanTransform.copyProperties(equityInheritance,EquityInheritanceBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        checkPermission();
        //恢复交易记录数据
        EquityInheritance equityInheritance = super.findById(id);
        String heir = equityInheritance.getHeir();//继承人
        String beHeir = equityInheritance.getBeHeir();//被继承人
        EquityTransactRecordBO equityTransactRecordBOH = equityTransactRecordSer.getByName(heir);//继承人信息交易记录
        EquityTransactRecordDetailBO equityTransactRecordDetailBOH = equityTransactRecordDetailSer.getByNameId(heir,id);//继承人信息交易记录明细
        EquityTransactRecordBO equityTransactRecordBOBH = equityTransactRecordSer.getByName(beHeir);//被继承人信息交易记录
        EquityTransactRecordDetailBO equityTransactRecordDetailBOBH = equityTransactRecordDetailSer.getByNameId(beHeir,id);//被继承人信息交易记录明细
        equityTransactRecordSer.reinstate(equityTransactRecordBOH,equityTransactRecordDetailBOH);
        equityTransactRecordSer.reinstate(equityTransactRecordBOBH,equityTransactRecordDetailBOBH);
        equityTransactRecordSer.updateTransList();
        //删除交易记录明细数据
        equityTransactRecordDetailSer.deleteByTransactId(id);
        //删除本条数据
        super.remove(id);
    }
}