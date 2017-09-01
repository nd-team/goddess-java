package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordDetailBO;
import com.bjike.goddess.shareholdersmanage.bo.PledgeEquityBO;
import com.bjike.goddess.shareholdersmanage.dto.PledgeEquityDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.entity.PledgeEquity;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordDetailTO;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.PledgeEquityTO;
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
 * 质押股权业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:32 ]
 * @Description: [ 质押股权业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class PledgeEquitySerImpl extends ServiceImpl<PledgeEquity, PledgeEquityDTO> implements PledgeEquitySer {
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
    public Long countPledge(PledgeEquityDTO pledgeEquityDTO) throws SerException {
        Long count = super.count(pledgeEquityDTO);
        return count;
    }

    @Override
    public PledgeEquityBO getOne(String id) throws SerException {
        PledgeEquity pledgeEquity = super.findById(id);
        return BeanTransform.copyProperties(pledgeEquity, PledgeEquityBO.class);
    }

    @Override
    public List<PledgeEquityBO> findList(PledgeEquityDTO pledgeEquityDTO) throws SerException {
        checkPermission();
        searchCondi(pledgeEquityDTO);
        pledgeEquityDTO.getSorts().add("createTime=desc");
        List<PledgeEquity> pledgeEquityList = super.findByCis(pledgeEquityDTO);
        return BeanTransform.copyProperties(pledgeEquityList, PledgeEquityBO.class);
    }
    /**
     * 根据条件查询数据
     *
     * @param pledgeEquityDTO
     * @throws SerException
     */
    public void searchCondi(PledgeEquityDTO pledgeEquityDTO) throws SerException {
        if (StringUtils.isNotBlank(pledgeEquityDTO.getArea())) {
            pledgeEquityDTO.getConditions().add(Restrict.eq("area", pledgeEquityDTO.getArea()));
        }
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public PledgeEquityBO save(PledgeEquityTO pledgeEquityTO) throws SerException {
        checkPermission();
        //判断输入的出质人股数是否大于此股东所拥有的股数
        EquityTransactRecordBO equityTransactRecordBOI = equityTransactRecordSer.getByName(pledgeEquityTO.getInvestor());
        if (equityTransactRecordBOI.getHoldNum() < pledgeEquityTO.getHoldNum()) {
            throw new SerException(pledgeEquityTO.getHoldNum()+"没有那么多股权数量");
        }
        //添加本条数据
        PledgeEquity pledgeEquity = BeanTransform.copyProperties(pledgeEquityTO,PledgeEquity.class,true);
        pledgeEquity.setCreateTime(LocalDateTime.now());
        pledgeEquity = super.save(pledgeEquity);
        EquityTransactRecordBO equityTransactRecordBOC = equityTransactRecordSer.getByName(pledgeEquityTO.getCreditor());//债权人交易记录信息
        //添加交易记录明细
        //出质人
        EquityTransactRecordDetailTO equityTransactRecordDetailTOI = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTOI.setShareholderName(equityTransactRecordBOI.getShareholderName());
        equityTransactRecordDetailTOI.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTOI.setPerSharePrice(equityTransactRecordBOI.getPerSharePrice());
        equityTransactRecordDetailTOI.setHoldNum(-pledgeEquityTO.getHoldNum());
        equityTransactRecordDetailTOI.setAmount(-pledgeEquityTO.getAmount());
        equityTransactRecordDetailTOI.setTransactType("质押股权");
        equityTransactRecordDetailTOI.setTransactId(pledgeEquity.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTOI);
        //债权人
        EquityTransactRecordDetailTO equityTransactRecordDetailTOC = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTOC.setShareholderName(equityTransactRecordBOC.getShareholderName());
        equityTransactRecordDetailTOC.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTOC.setPerSharePrice(equityTransactRecordBOI.getPerSharePrice());
        equityTransactRecordDetailTOC.setHoldNum(pledgeEquityTO.getHoldNum());
        equityTransactRecordDetailTOC.setAmount(pledgeEquityTO.getAmount());
        equityTransactRecordDetailTOC.setTransactType("质押股权");
        equityTransactRecordDetailTOC.setTransactId(pledgeEquity.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTOC);
        //编辑交易记录
        //出质人
        EquityTransactRecordTO equityTransactRecordTOI = new EquityTransactRecordTO();
        equityTransactRecordTOI.setHoldNum(equityTransactRecordBOI.getHoldNum() - pledgeEquityTO.getHoldNum());
        equityTransactRecordTOI.setAmount(equityTransactRecordBOI.getAmount() - pledgeEquityTO.getAmount());
        equityTransactRecordTOI.setId(equityTransactRecordBOI.getId());
        equityTransactRecordSer.updateTrans(equityTransactRecordTOI);
        //债权人
        EquityTransactRecordTO equityTransactRecordTOC = new EquityTransactRecordTO();
        equityTransactRecordTOC.setHoldNum(equityTransactRecordBOC.getHoldNum() + pledgeEquityTO.getHoldNum());
        equityTransactRecordTOC.setAmount(equityTransactRecordBOC.getAmount() + pledgeEquityTO.getAmount());
        equityTransactRecordTOC.setId(equityTransactRecordBOC.getId());
        equityTransactRecordSer.updateTrans(equityTransactRecordTOC);
        //重新设置交易记录中所有的股东占股比例
        equityTransactRecordSer.updateTransList();
        return BeanTransform.copyProperties(pledgeEquity,PledgeEquityBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public PledgeEquityBO edit(PledgeEquityTO pledgeEquityTO) throws SerException {
        checkPermission();
        PledgeEquity pledgeEquity = super.findById(pledgeEquityTO.getId());
        String investor = pledgeEquity.getInvestor();//出质人
        String creditor = pledgeEquity.getCreditor();//债权人
        Integer holdNum = pledgeEquity.getHoldNum();//出质股数
        //判断出质人债权人出质股数是否被修改
        if(!investor.equals(pledgeEquityTO.getInvestor()) || !creditor.equals(pledgeEquityTO.getCreditor()) || holdNum!=pledgeEquityTO.getHoldNum()){
            //恢复出质人债权人交易记录信息
            EquityTransactRecordBO equityTransactRecordBOI = equityTransactRecordSer.getByName(investor);//修改之前的出质人信息交易记录
            EquityTransactRecordDetailBO equityTransactRecordDetailBOI = equityTransactRecordDetailSer.getByNameId(investor,pledgeEquityTO.getId());//修改之前的出质人信息交易记录明细
            EquityTransactRecordBO equityTransactRecordBOC = equityTransactRecordSer.getByName(creditor);//修改之前的债权人信息交易记录
            EquityTransactRecordDetailBO equityTransactRecordDetailBOC = equityTransactRecordDetailSer.getByNameId(creditor,pledgeEquityTO.getId());//修改之前的债权人信息交易记录明细
            equityTransactRecordSer.reinstate(equityTransactRecordBOI,equityTransactRecordDetailBOI);//转让人
            equityTransactRecordSer.reinstate(equityTransactRecordBOC,equityTransactRecordDetailBOC);//受让人
            //判断出质人是否有足够多的股权数量
            EquityTransactRecordBO equityTransactRecordBOAI = equityTransactRecordSer.getByName(pledgeEquityTO.getInvestor());
            if (equityTransactRecordBOAI.getHoldNum() < pledgeEquityTO.getHoldNum()) {
                throw new SerException(pledgeEquityTO.getInvestor()+"没有那么多股权数量");
            }
            //修改出质人交易记录明细信息
            EquityTransactRecordDetailTO equityTransactRecordDetailTOI = new EquityTransactRecordDetailTO();
            equityTransactRecordDetailTOI.setId(equityTransactRecordDetailBOI.getId());
            equityTransactRecordDetailTOI.setShareholderName(pledgeEquityTO.getInvestor());
            equityTransactRecordDetailTOI.setHoldNum(-pledgeEquityTO.getHoldNum());
            equityTransactRecordDetailTOI.setTransactDate(LocalDate.now().toString());
            equityTransactRecordDetailTOI.setPerSharePrice(equityTransactRecordBOAI.getPerSharePrice());
            equityTransactRecordDetailTOI.setAmount(-pledgeEquityTO.getAmount());
            equityTransactRecordDetailSer.edit(equityTransactRecordDetailTOI);
            //修改债权人交易记录明细信息
            EquityTransactRecordBO equityTransactRecordBOAC = equityTransactRecordSer.getByName(pledgeEquityTO.getCreditor());
            EquityTransactRecordDetailTO equityTransactRecordDetailTOC = new EquityTransactRecordDetailTO();
            equityTransactRecordDetailTOC.setId(equityTransactRecordDetailBOC.getId());
            equityTransactRecordDetailTOC.setShareholderName(pledgeEquityTO.getCreditor());
            equityTransactRecordDetailTOC.setHoldNum(pledgeEquityTO.getHoldNum());
            equityTransactRecordDetailTOC.setTransactDate(LocalDate.now().toString());
            equityTransactRecordDetailTOC.setPerSharePrice(equityTransactRecordBOAC.getPerSharePrice());
            equityTransactRecordDetailTOC.setAmount(pledgeEquityTO.getAmount());
            equityTransactRecordDetailSer.edit(equityTransactRecordDetailTOC);

            //修改修改后出质人交易记录信息
            EquityTransactRecordTO equityTransactRecordTOAI = new EquityTransactRecordTO();
            equityTransactRecordTOAI.setHoldNum(equityTransactRecordBOAI.getHoldNum() - pledgeEquityTO.getHoldNum());
            equityTransactRecordTOAI.setAmount(equityTransactRecordBOAI.getAmount() - pledgeEquityTO.getAmount());
            equityTransactRecordTOAI.setId(equityTransactRecordBOAI.getId());
            equityTransactRecordSer.updateTrans(equityTransactRecordTOAI);
            //修改修改后债权人交易记录信息
            EquityTransactRecordTO equityTransactRecordTOC = new EquityTransactRecordTO();
            equityTransactRecordTOC.setHoldNum(equityTransactRecordBOAC.getHoldNum() + pledgeEquityTO.getHoldNum());
            equityTransactRecordTOC.setAmount(equityTransactRecordBOAC.getAmount() + pledgeEquityTO.getAmount());
            equityTransactRecordTOC.setId(equityTransactRecordBOAC.getId());
            equityTransactRecordSer.updateTrans(equityTransactRecordTOC);
            //重新设置所有股东占股比例
            equityTransactRecordSer.updateTransList();
        }
        //修改本条信息
        LocalDateTime date = pledgeEquity.getCreateTime();
        pledgeEquity = BeanTransform.copyProperties(pledgeEquityTO,PledgeEquity.class,true);
        pledgeEquity.setCreateTime(date);
        pledgeEquity.setModifyTime(LocalDateTime.now());
        super.update(pledgeEquity);
        return BeanTransform.copyProperties(pledgeEquity,PledgeEquityBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        checkPermission();
        //恢复交易记录数据
        PledgeEquity pledgeEquity = super.findById(id);
        String investor = pledgeEquity.getInvestor();//出质人
        String creditor = pledgeEquity.getCreditor();//债权人
        EquityTransactRecordBO equityTransactRecordBOI = equityTransactRecordSer.getByName(investor);//出质人信息交易记录
        EquityTransactRecordDetailBO equityTransactRecordDetailBOI = equityTransactRecordDetailSer.getByNameId(investor,id);//出质人信息交易记录明细
        EquityTransactRecordBO equityTransactRecordBOC = equityTransactRecordSer.getByName(creditor);//债权人信息交易记录
        EquityTransactRecordDetailBO equityTransactRecordDetailBOC = equityTransactRecordDetailSer.getByNameId(creditor,id);//债权人信息交易记录明细
        equityTransactRecordSer.reinstate(equityTransactRecordBOI,equityTransactRecordDetailBOI);//转让人
        equityTransactRecordSer.reinstate(equityTransactRecordBOC,equityTransactRecordDetailBOC);//受让人
        //删除交易明细数据
        equityTransactRecordDetailSer.deleteByTransactId(id);
        //删除本条数据
        super.remove(id);
    }
}