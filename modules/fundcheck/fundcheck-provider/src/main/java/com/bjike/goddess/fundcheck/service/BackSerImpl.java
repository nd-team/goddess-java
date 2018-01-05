package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.fundcheck.bo.BackBO;
import com.bjike.goddess.fundcheck.dto.BackDTO;
import com.bjike.goddess.fundcheck.entity.*;
import com.bjike.goddess.fundcheck.enums.GuideAddrStatus;
import com.bjike.goddess.fundcheck.excel.BackTemplateExcel;
import com.bjike.goddess.fundcheck.excel.SonPermissionObject;
import com.bjike.goddess.fundcheck.to.BackTO;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.receivable.api.ReceivableSubsidiaryAPI;
import com.bjike.goddess.receivable.bo.ReceivableSubsidiaryBO;
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
 * 回款业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:48 ]
 * @Description: [ 回款业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "fundcheckSerCache")
@Service
public class BackSerImpl extends ServiceImpl<Back, BackDTO> implements BackSer {
    @Autowired
    private ReceivableSubsidiaryAPI receivableSubsidiaryAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private AccountBalanceSer accountBalanceSer;
    @Autowired
    private AccountIncomeSer accountIncomeSer;
    @Autowired
    private AccountSpendSer accountSpendSer;
    @Autowired
    private BeginBalanceSer beginBalanceSer;
    @Autowired
    private OperatExpensesSer operatExpensesSer;
    @Autowired
    private OtherIncomeSer otherIncomeSer;
    @Autowired
    private OtherSpendSer otherSpendSer;
    @Autowired
    private PayStockSer payStockSer;
    @Autowired
    private StockMoneySer stockMoneySer;
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
                throw new SerException("您不是相应部门的人员，不可以查看");
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
     * 导航栏核对查看权限（部门级别）
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
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("back");
        obj.setDescribesion("回款");
        if (flagSeeInfo || flagAddInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeBalance = accountBalanceSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("accountbalance");
        obj.setDescribesion("账上余额");
        if (flagSeeBalance) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeIncome = accountIncomeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("accountincome");
        obj.setDescribesion("账务收入");
        if (flagSeeIncome) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeSpend = accountSpendSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("accountspend");
        obj.setDescribesion("账务支出");
        if (flagSeeSpend) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        Boolean flagSeeBegin = beginBalanceSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("beginbalance");
        obj.setDescribesion("期初余额");
        if (flagSeeBegin) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeOperat = operatExpensesSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("operatexpenses");
        obj.setDescribesion("营业费用");
        if (flagSeeOperat) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeOtherIncome = otherIncomeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("otherincome");
        obj.setDescribesion("其他收入");
        if (flagSeeOtherIncome) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeOtherSpend = otherSpendSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("otherspend");
        obj.setDescribesion("其他支出");
        if (flagSeeOtherSpend) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeePayStock = payStockSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("paystock");
        obj.setDescribesion("支付给股东");
        if (flagSeePayStock) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeStockMoney = stockMoneySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("stockmoney");
        obj.setDescribesion("收到股东款");
        if (flagSeeStockMoney) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }


    @Override
    public Long count(BackDTO backDTO) throws SerException {
        Long count = super.count(backDTO);
        return count;
    }

    @Override
    public BackBO getOne(String id) throws SerException {
        Back back = super.findById(id);
        return BeanTransform.copyProperties(back, BackBO.class);
    }

    @Override
    public List<BackBO> findListBack(BackDTO backDTO) throws SerException {
        checkSeeIdentity();
        backDTO.getSorts().add("createTime=desc");
        List<Back> backs = super.findByCis(backDTO);
        List<BackBO> backBOS = BeanTransform.copyProperties(backs,BackBO.class);
        return backBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BackBO insert(BackTO backTO) throws SerException {
        checkAddIdentity();
        Back back = BeanTransform.copyProperties(backTO,Back.class,true);
        back.setCreateTime(LocalDateTime.now());
        super.save(back);
        return BeanTransform.copyProperties(back,BackBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BackBO edit(BackTO backTO) throws SerException {
        checkAddIdentity();
        Back back = super.findById(backTO.getId());
        BeanTransform.copyProperties(backTO,back,true);
        back.setModifyTime(LocalDateTime.now());
        super.update(back);
        return BeanTransform.copyProperties(back,BackBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);

    }

    @Override
    public List<BackBO> backinfo(String startTime, String endTime) throws SerException {
        List<ReceivableSubsidiaryBO> receivableSubsidiaryBOS = receivableSubsidiaryAPI.receivable(startTime, endTime);
        List<Back> backs = new ArrayList<>();
        for (ReceivableSubsidiaryBO r : receivableSubsidiaryBOS) {
            Back entity = new Back();
            entity.setDate(DateUtil.parseDate(r.getAccountTime()));
            entity.setArea(r.getArea());
            entity.setAccountMoney(r.getAccountMoney());
            entity.setInnerName(r.getInnerName());
            entity.setTaxes(r.getTaxes());
            backs.add(entity);
        }
        List<BackBO> backBOS = BeanTransform.copyProperties(backs,BackBO.class);
        return backBOS;
    }
    @Override
    public BaseBO importExcel(List<BackTO> backTOS) throws SerException {
        List<Back> backs = BeanTransform.copyProperties(backTOS, Back.class, true);
        super.save(backs);

        BackBO bo = BeanTransform.copyProperties(new Back(), BackBO.class);
        return bo;
    }
    @Override
    public byte[] templateExport() throws SerException {
        List<BackTemplateExcel> backTemplateExcels = new ArrayList<>();

        BackTemplateExcel excel = new BackTemplateExcel();
        excel.setDate(LocalDate.now());
        excel.setArea("test");
        excel.setInnerName("test");
        excel.setAccountMoney(10.0d);
        excel.setTaxes(10.0d);
        backTemplateExcels.add(excel);


        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(backTemplateExcels, exce);
        return bytes;
    }

}