package com.bjike.goddess.checkfunds.service;


import com.bjike.goddess.checkfunds.bo.BankReconciliationBO;
import com.bjike.goddess.checkfunds.bo.RemainAdjustBO;
import com.bjike.goddess.checkfunds.dto.RemainAdjustDTO;
import com.bjike.goddess.checkfunds.entity.RemainAdjust;
import com.bjike.goddess.checkfunds.enums.GuideAddrStatus;
import com.bjike.goddess.checkfunds.to.GuidePermissionTO;
import com.bjike.goddess.checkfunds.to.RemainAdjustTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.entity.PositionDetail;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 余额调节业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:11 ]
 * @Description: [ 余额调节业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "checkfundsSerCache")
@Service
public class RemainAdjustSerImpl extends ServiceImpl<RemainAdjust, RemainAdjustDTO> implements RemainAdjustSer {
    @Autowired
    private BankReconciliationSer bankReconciliationSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
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
                flag = guideSeeIdentity();
                break;
            case HANDLE:
                flag = guideAddIdentity();
                break;
            case COMMIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case ADJUST:
                flag = guideAddIdentity();
                break;
            case DETAIL:
                flag = guideSeeIdentity();
                break;
            case DIFFER:
                flag = guideSeeIdentity();
                break;
            case CONFIRM:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void save(RemainAdjustTO to) throws SerException {
        RemainAdjust entity = BeanTransform.copyProperties(to, RemainAdjust.class, true);
        super.save(entity);
    }

    @Override
    public List<RemainAdjustBO> findByDTO(RemainAdjustDTO dto) throws SerException {
        List<RemainAdjust> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, RemainAdjustBO.class);
    }

    @Override
    public Long count(RemainAdjustDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<RemainAdjustBO> addFund(RemainAdjustTO to, String id) throws SerException {
        checkAddIdentity();
        BankReconciliationBO bankReconciliationBO = bankReconciliationSer.findByID(id);
        Integer year = bankReconciliationBO.getYear();
        Integer month = bankReconciliationBO.getMonth();
//        RemainAdjustDTO dto=new RemainAdjustDTO();
//        dto.getConditions().add(Restrict.eq("year",year));
//        dto.getConditions().add(Restrict.eq("month",month));
//        List<RemainAdjust> list=super.findByCis(dto);
//        boolean b = true;
//        for (RemainAdjust r : list) {
//            boolean b1 = r.getBankType() != null;
//            boolean b2 = StringUtils.isNotBlank(r.getBankType());
//            boolean b3 = r.getFundType() == null;
//            boolean b4 = StringUtils.isBlank(r.getFundType());
//            if (b1 && b2 && (b3 || b4) && b) {   //有记录且资金流水项目为空则编辑
//                RemainAdjust entity = super.findById(r.getId());
//                entity.setMoneyWaterProject(to.getMoneyWaterProject());
//                entity.setMoneyWaterSum(to.getMoneyWaterSum());
//                entity.setFundType("jia");
//                entity.setModifyTime(LocalDateTime.now());
//                super.update(entity);
//                b = false;   //设为false
//            }
//        }
//        if (b) {  //true则添加
        RemainAdjust entity = BeanTransform.copyProperties(to, RemainAdjust.class, true);
        entity.setFundType("jia");
        entity.setYear(year);
        entity.setMonth(month);
        super.save(entity);
//        }
        return bankReconciliationSer.boList(id);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<RemainAdjustBO> removeFund(RemainAdjustTO to, String id) throws SerException {
        checkAddIdentity();
        BankReconciliationBO bankReconciliationBO = bankReconciliationSer.findByID(id);
        Integer year = bankReconciliationBO.getYear();
        Integer month = bankReconciliationBO.getMonth();
//        RemainAdjustDTO dto=new RemainAdjustDTO();
//        dto.getConditions().add(Restrict.eq("year",year));
//        dto.getConditions().add(Restrict.eq("month",month));
//        List<RemainAdjust> list=super.findByCis(dto);
//        boolean b = true;
//        for (RemainAdjust r : list) {
//            boolean b1 = r.getBankType() != null;
//            boolean b2 = StringUtils.isNotBlank(r.getBankType());
//            boolean b3 = r.getFundType() == null;
//            boolean b4 = StringUtils.isBlank(r.getFundType());
//            if (b1 && b2 && (b3 || b4) && b) {   //有记录且资金流水项目为空则编辑
//                RemainAdjust entity = super.findById(r.getId());
//                entity.setMoneyWaterProject(to.getMoneyWaterProject());
//                entity.setMoneyWaterSum(to.getMoneyWaterSum());
//                entity.setFundType("jian");
//                entity.setModifyTime(LocalDateTime.now());
//                super.update(entity);
//                b = false;   //设为false
//            }
//        }
//        if (b) {  //true则添加
        RemainAdjust entity = BeanTransform.copyProperties(to, RemainAdjust.class, true);
        entity.setFundType("jian");
        entity.setYear(year);
        entity.setMonth(month);
        super.save(entity);
//        }
        return bankReconciliationSer.boList(id);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<RemainAdjustBO> addBank(RemainAdjustTO to, String id) throws SerException {
        checkAddIdentity();
        BankReconciliationBO bankReconciliationBO = bankReconciliationSer.findByID(id);
        Integer year = bankReconciliationBO.getYear();
        Integer month = bankReconciliationBO.getMonth();
//        RemainAdjustDTO dto=new RemainAdjustDTO();
//        dto.getConditions().add(Restrict.eq("year",year));
//        dto.getConditions().add(Restrict.eq("month",month));
//        List<RemainAdjust> list=super.findByCis(dto);
//        boolean b = true;
//        for (RemainAdjust r : list) {
//            boolean b1 = r.getFundType() != null;
//            boolean b2 = StringUtils.isNotBlank(r.getFundType());
//            boolean b3 = r.getBankType() == null;
//            boolean b4 = StringUtils.isBlank(r.getBankType());
//            if (b1 && b2 && (b3 || b4) && b) {   //有记录且资金流水项目为空则编辑
//                RemainAdjust entity = super.findById(r.getId());
//                entity.setBankWaterProject(to.getBankWaterProject());
//                entity.setBankWaterSum(to.getBankWaterSum());
//                entity.setBankType("jia");
//                entity.setModifyTime(LocalDateTime.now());
//                super.update(entity);
//                b = false;   //设为false
//            }
//        }
//        if (b) {  //true则添加
        RemainAdjust entity = BeanTransform.copyProperties(to, RemainAdjust.class, true);
        entity.setBankType("jia");
        entity.setYear(year);
        entity.setMonth(month);
        super.save(entity);
//        }
        return bankReconciliationSer.boList(id);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<RemainAdjustBO> removeBank(RemainAdjustTO to, String id) throws SerException {
        checkAddIdentity();
        BankReconciliationBO bankReconciliationBO = bankReconciliationSer.findByID(id);
        Integer year = bankReconciliationBO.getYear();
        Integer month = bankReconciliationBO.getMonth();
//        RemainAdjustDTO dto=new RemainAdjustDTO();
//        dto.getConditions().add(Restrict.eq("year",year));
//        dto.getConditions().add(Restrict.eq("month",month));
//        List<RemainAdjust> list=super.findByCis(dto);
//        boolean b = true;
//        for (RemainAdjust r : list) {
//            boolean b1 = r.getFundType() != null;
//            boolean b2 = StringUtils.isNotBlank(r.getFundType());
//            boolean b3 = r.getBankType() == null;
//            boolean b4 = StringUtils.isBlank(r.getBankType());
//            if (b1 && b2 && (b3 || b4) && b) {   //有记录且资金流水项目为空则编辑
//                RemainAdjust entity = super.findById(r.getId());
//                entity.setBankWaterProject(to.getBankWaterProject());
//                entity.setBankWaterSum(to.getBankWaterSum());
//                entity.setBankType("jian");
//                entity.setModifyTime(LocalDateTime.now());
//                super.update(entity);
//                b = false;   //设为false
//            }
//        }
//        if (b) {  //true则添加
        RemainAdjust entity = BeanTransform.copyProperties(to, RemainAdjust.class, true);
        entity.setBankType("jian");
        entity.setYear(year);
        entity.setMonth(month);
        super.save(entity);
//        }
        return bankReconciliationSer.boList(id);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    //前端拿最后一条记录的余额
    public void confirmAdjust(String id, Double fundBalance, Double bankBalance) throws SerException {
        checkAddIdentity();
        boolean b = fundBalance.equals(bankBalance);
        if (!b) {
            throw new SerException("两边余额不相等，不能确认调整");
        }
        bankReconciliationSer.confirmAdjust(id, bankBalance);
    }

    @Override
    public Long countNum(RemainAdjustDTO dto) throws SerException {
        return super.count(dto);
    }
}