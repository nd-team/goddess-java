package com.bjike.goddess.bankrecords.service;

import com.bjike.goddess.bankrecords.bo.BankAccountInfoBO;
import com.bjike.goddess.bankrecords.bo.BankRecordBO;
import com.bjike.goddess.bankrecords.dto.BankAccountInfoDTO;
import com.bjike.goddess.bankrecords.dto.BankRecordDTO;
import com.bjike.goddess.bankrecords.entity.BankAccountInfo;
import com.bjike.goddess.bankrecords.entity.BankRecord;
import com.bjike.goddess.bankrecords.enums.GuideAddrStatus;
import com.bjike.goddess.bankrecords.to.BankAccountInfoTO;
import com.bjike.goddess.bankrecords.to.GuidePermissionTO;
import com.bjike.goddess.bankrecords.to.SonPermissionObject;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.AccountAPI;
import com.bjike.goddess.financeinit.bo.AccountBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 账号信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 03:58 ]
 * @Description: [ 账号信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "bankrecordsSerCache")
@Service
public class BankAccountInfoSerImpl extends ServiceImpl<BankAccountInfo, BankAccountInfoDTO> implements BankAccountInfoSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private BankRecordSer bankRecordSer;
    @Autowired
    private AccountAPI accountAPI;

    public List<BankAccountInfoBO> findAlltoPage(BankAccountInfoDTO dto) throws SerException {
        int page = dto.getPage();
        int limit = dto.getLimit();
        int start = (page - 1) * limit;
        int end = page * limit;
        //  String token = RpcTransmit.getUserToken();
        //AccountDTO accountDTO = new AccountDTO();
        BankAccountInfo bankAccountInfo = new BankAccountInfo();
        //RpcTransmit.transmitUserToken(token);
        //String systemId = userAPI.currentSysNO();
        //accountDTO.setSystemId(systemId);
        // List<AccountBO> list = accountAPI.listAccount(accountDTO);

        String sql = "select ANY_VALUE(name) as name,ANY_VALUE(account) as account,ANY_VALUE(bankAddr)as bankAddr,ANY_VALUE(secondSubject)as secondSubject,ANY_VALUE(remark) as remark from financeinit_account where secondSubject='一般户' or secondSubject='基本户' group by account limit " + start + " , " + end + "  ";
        String s[] = new String[]{"name", "account", "bankAddr", "secondSubject", "remark"};
        List<AccountBO> list = super.findBySql(sql, AccountBO.class, s);
        List<BankAccountInfo> olds = super.findAll();
        if (list != null && !list.isEmpty()) {
            for (AccountBO bo : list) {
                boolean bool = false;
                for (BankAccountInfo bai : olds) {
                    if (bo.getAccount().equals(bai.getNumber())) {
                        bool = true;
                    }
                }
                if (!bool) {
                    bankAccountInfo.setName(bo.getName());
                    bankAccountInfo.setNumber(bo.getAccount());
                    bankAccountInfo.setBank(bo.getBankAddr().substring(0, 4));
                    bankAccountInfo.setBankAddress(bo.getBankAddr());
                    bankAccountInfo.setType(bo.getSecondSubject());
                    bankAccountInfo.setRemark(bo.getRemark());
                    bankAccountInfo.setCompany(" ");//没有的值避免报错就存null
                    bankAccountInfo.setCardNumber(" ");//没有的值避免报错就存null
                    super.save(bankAccountInfo);//先把前部分的把账号来源给加进银行账户信息
                }
            }
        }
        return BeanTransform.copyProperties(super.findAll(), BankAccountInfoBO.class);
    }

    @Override
    public List<BankAccountInfoBO> listPage(BankAccountInfoDTO dto) throws SerException {
        int page = dto.getPage();
        int limit = dto.getLimit();
        int start = (page - 1) * limit;
        int end = page * limit;
        String sql = null;
        if (dto.getName()!= null) {
            sql = "select bank,bankAddress,name,number,type,remark from bankrecords_bankaccountinfo where name='" +dto.getName()+ "' limit  " + start + "  ,  " + end + "  ";
        } else {
            sql = "select bank,bankAddress,name,number,type,remark from bankrecords_bankaccountinfo limit  " + start + "  ,  " + end + "  ";
        }
        String[] s = new String[]{"bank", "bankAddress", "name", "number", "type", "remark"};
        List<BankAccountInfoBO> bos= super.findBySql(sql, BankAccountInfoBO.class, s);
        return bos;

    }
    @Override
    public List<BankAccountInfoBO> listAccount() throws SerException {
        String sql = "select number from bankrecords_bankaccountinfo";
        String[] field = new String[]{"number"};
        return super.findBySql(sql, BankAccountInfoBO.class, field);

    }




    @Override
    @Transactional(rollbackFor = SerException.class)
    public BankAccountInfoBO insertModel(BankAccountInfoTO to) throws SerException {
        BankAccountInfo model = BeanTransform.copyProperties(to, BankAccountInfo.class, true);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, BankAccountInfoBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public BankAccountInfoBO updateModel(BankAccountInfoTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            BankAccountInfo model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("无法更新记账凭证查询的数据!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, BankAccountInfoBO.class);
    }

    @Override
    public List<BankAccountInfoBO> pageList(BankAccountInfoDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), BankAccountInfoBO.class);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();

        String userToken = RpcTransmit.getUserToken();
        Boolean flagAddSign = guideSeeIdentity();
        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("bankaccountinfo");
        obj.setDescribesion("银行账户信息");
        if (flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = bankRecordSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("bankrecord");
        obj.setDescribesion("银行流水");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagCollectDis = bankRecordSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("collect");
        obj.setDescribesion("汇总分析");
        if (flagCollectDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
    }

    /**
     * 导航栏核对查看权限（岗位级别）
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

    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = to.getGuideAddrStatus();
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
            case DELETE:
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
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case ANALYZE:
                flag = guideSeeIdentity();
                break;
            case COMPARE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    public void delete(String id) throws SerException {
        BankAccountInfo model = super.findById(id);
        if (model != null) {
            BankRecordDTO bankRecordDTO = new BankRecordDTO();
            bankRecordDTO.getConditions().add(Restrict.eq("accountId", id));
            List<BankRecord> bankRecordList = bankRecordSer.findByCis(bankRecordDTO);
            if (CollectionUtils.isEmpty(bankRecordList)) {
                super.remove(id);
            } else {
                throw new SerException("该账户信息存在银行流水关联，请检查数据!");
            }
        } else {
            throw new SerException("非法Id,账户信息对象不能为空!");
        }
    }

    /**
     * 导航栏核对添加修改删除审核权限（部门级别）
     */
    private Boolean guideAddIdentity() throws SerException {
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
}