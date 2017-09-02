package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.shareholdersmanage.api.ChangeEquityTypeAPI;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.ShareOpenAccountBO;
import com.bjike.goddess.shareholdersmanage.dto.ShareOpenAccountDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.entity.ShareOpenAccount;
import com.bjike.goddess.shareholdersmanage.excel.ShareOpenAccountExport;
import com.bjike.goddess.shareholdersmanage.excel.SonPermissionObject;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordDetailTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOpenAccountBTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOpenAccountTO;
import com.bjike.goddess.shareholdersmanage.type.Gender;
import com.bjike.goddess.shareholdersmanage.type.GuideAddrStatus;
import com.bjike.goddess.shareholdersmanage.type.ShareholderStatus;
import com.bjike.goddess.shareholdersmanage.type.TypeName;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 股东开户业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-18 02:41 ]
 * @Description: [ 股东开户业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class ShareOpenAccountSerImpl extends ServiceImpl<ShareOpenAccount, ShareOpenAccountDTO> implements ShareOpenAccountSer {
    @Autowired
    private EquityTransactRecordSer equityTransactRecordSer;
    @Autowired
    private EquityTransactRecordDetailSer equityTransactRecordDetailSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ChangeEquityTypeSer changeEquityTypeSer;
    @Autowired
    private EquityCoalescSer equityCoalescSer;
    @Autowired
    private EquityGiftSer equityGiftSer;
    @Autowired
    private EquityInheritanceSer equityInheritanceSer;
    @Autowired
    private EquityTransferSer equityTransferSer;
    @Autowired
    private FairChangeSer fairChangeSer;
    @Autowired
    private FreezeEquitySer freezeEquitySer;
    @Autowired
    private LogoutEquitySer logoutEquitySer;
    @Autowired
    private LogoutShareSer logoutShareSer;
    @Autowired
    private NewEquitySer newEquitySer;
    @Autowired
    private PledgeEquitySer pledgeEquitySer;
    @Autowired
    private ShareChangeSer shareChangeSer;
    @Autowired
    private ShareOutBonusDetailSer shareOutBonusDetailSer;
    @Autowired
    private ShareOutBonusManageSer shareOutBonusManageSer;

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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagOpen = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("shareopenaccount");
        obj.setDescribesion("股东开户");
        if (flagOpen) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagShareChange = shareChangeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("sharechange");
        obj.setDescribesion("股东变更");
        if (flagShareChange) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagLogShare = logoutShareSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("logoutshare");
        obj.setDescribesion("注销股东");
        if (flagLogShare) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagEquRecord = equityTransactRecordSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("equitytransactrecord");
        obj.setDescribesion("股权交易记录");
        if (flagEquRecord) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);



        RpcTransmit.transmitUserToken(userToken);
        Boolean flagNewEquity = newEquitySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("newequity");
        obj.setDescribesion("新增股权");
        if (flagNewEquity) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagEquTranfer = equityTransferSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("equitytransfer");
        obj.setDescribesion("股权转让");
        if (flagEquTranfer) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagLogEquity = logoutEquitySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("logoutequity");
        obj.setDescribesion("注销股权");
        if (flagLogEquity) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagEquitChaType = changeEquityTypeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("changeequitytype");
        obj.setDescribesion("变更股权类型");
        if (flagEquitChaType) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagInheritan = equityInheritanceSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("equityinheritance");
        obj.setDescribesion("股权继承");
        if (flagInheritan) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGift = equityGiftSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("equitygift");
        obj.setDescribesion("股权赠与");
        if (flagGift) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagEquityGift = equityGiftSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("equitycoalesc");
        obj.setDescribesion("股权合并");
        if (flagEquityGift) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagPledge = pledgeEquitySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("pledgeequity");
        obj.setDescribesion("质押股权");
        if (flagPledge) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagFreeze = freezeEquitySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("freezeequity");
        obj.setDescribesion("冻结股权");
        if (flagFreeze) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagFairChange = fairChangeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("fairchange");
        obj.setDescribesion("公允值变动");
        if (flagFairChange) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagOutBonus = shareOutBonusManageSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("shareoutbonusmanage");
        obj.setDescribesion("分红管理");
        if (flagOutBonus) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagOutCase = shareOutBonusManageSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("shareoutbonuscase");
        obj.setDescribesion("分红情况查询");
        if (flagOutCase) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagOutRoster = shareOutBonusManageSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("shareoutroster");
        obj.setDescribesion("股东名册查询");
        if (flagOutRoster) {
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
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public Long countShareOpen(ShareOpenAccountDTO shareOpenAccountDTO) throws SerException {
        Long count = super.count(shareOpenAccountDTO);
        return count;
    }

    @Override
    public ShareOpenAccountBO getOne(String id) throws SerException {
        ShareOpenAccount shareOpenAccount = super.findById(id);
        return BeanTransform.copyProperties(shareOpenAccount, ShareOpenAccountBO.class);
    }

    @Override
    public List<ShareOpenAccountBO> findList(ShareOpenAccountDTO shareOpenAccountDTO) throws SerException {
        checkPermission();
        searchCondi(shareOpenAccountDTO);
        shareOpenAccountDTO.getConditions().add(Restrict.eq("shareholderStatus", ShareholderStatus.NORMAL));
        shareOpenAccountDTO.getSorts().add("createTime=desc");
        List<ShareOpenAccount> shareOpenAccounts = super.findByCis(shareOpenAccountDTO);
        return BeanTransform.copyProperties(shareOpenAccounts, ShareOpenAccountBO.class);
    }

    /**
     * 根据条件查询数据
     *
     * @param shareOpenAccountDTO
     * @throws SerException
     */
    public void searchCondi(ShareOpenAccountDTO shareOpenAccountDTO) throws SerException {
        if (StringUtils.isNotBlank(shareOpenAccountDTO.getStartDate()) && StringUtils.isBlank(shareOpenAccountDTO.getEndDate())) {
            throw new SerException("请输入结束时间");
        } else if (StringUtils.isNotBlank(shareOpenAccountDTO.getEndDate()) && StringUtils.isBlank(shareOpenAccountDTO.getStartDate())) {
            throw new SerException("请输入开始时间");
        } else if (StringUtils.isNotBlank(shareOpenAccountDTO.getEndDate()) && StringUtils.isNotBlank(shareOpenAccountDTO.getStartDate())) {
            shareOpenAccountDTO.getConditions().add(Restrict.between("openDate", new String[]{shareOpenAccountDTO.getStartDate(), shareOpenAccountDTO.getEndDate()}));
        }
        if (StringUtils.isNotBlank(shareOpenAccountDTO.getArea())) {
            shareOpenAccountDTO.getConditions().add(Restrict.eq("area", shareOpenAccountDTO.getArea()));
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ShareOpenAccountBO save(ShareOpenAccountTO shareOpenAccountTO) throws SerException {
        checkPermission();
        ShareOpenAccount shareOpenAccount = BeanTransform.copyProperties(shareOpenAccountTO, ShareOpenAccount.class, true);
        Double amount = shareOpenAccountTO.getHoldNum() * shareOpenAccountTO.getPerSharePrice();//出资额
        Double totalAmount = totalAmount(shareOpenAccountTO.getEquityType()) + amount;//总出资额
        Double percentage = 0d; //占股比例
        if (totalAmount != 0) {
            percentage = (amount / totalAmount) * 100;
        }
        shareOpenAccount.setCreateTime(LocalDateTime.now());
        shareOpenAccount.setAmount(amount);
        shareOpenAccount.setPercentage(percentage);//占股比例
        shareOpenAccount.setShareholderStatus(ShareholderStatus.NORMAL);
        shareOpenAccount = super.save(shareOpenAccount);
        updateList();//重新修改该股权类型的所有的占股比例
        //添加到交易记录表中
        EquityTransactRecord equityTransactRecord = BeanTransform.copyProperties(shareOpenAccountTO, EquityTransactRecord.class, true);
        equityTransactRecord.setCreateTime(LocalDateTime.now());
        equityTransactRecord.setAmount(amount);
        equityTransactRecord.setPercentage(percentage);
        equityTransactRecord.setShareholderStatus(ShareholderStatus.NORMAL);
        equityTransactRecordSer.save(equityTransactRecord);
        equityTransactRecordSer.updateTransList();
        //添加到交易记录明细表中
        EquityTransactRecordDetailTO equityTransactRecordDetailTO = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTO.setShareholderName(shareOpenAccount.getShareholderName());
        equityTransactRecordDetailTO.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTO.setHoldNum(shareOpenAccount.getHoldNum());
        equityTransactRecordDetailTO.setPerSharePrice(shareOpenAccount.getPerSharePrice());
        equityTransactRecordDetailTO.setAmount(shareOpenAccount.getAmount());
        equityTransactRecordDetailTO.setTransactType("股东开户");
        equityTransactRecordDetailTO.setTransactId(shareOpenAccount.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTO);
        return BeanTransform.copyProperties(shareOpenAccount, ShareOpenAccountBO.class);
    }

    /**
     * 重新修改股东开户该类型的所有的占股比例
     *
     * @return
     * @throws SerException
     */
    public void updateList() throws SerException {
        for (String equityType : findEquityType()) {
            ShareOpenAccountDTO shareOpenAccountDTO = new ShareOpenAccountDTO();
            shareOpenAccountDTO.getConditions().add(Restrict.eq("equityType", equityType));
            List<ShareOpenAccount> shareOpenAccountList = super.findByCis(shareOpenAccountDTO);
            Double totalAmount = 0d;
            List<ShareOpenAccount> list = new ArrayList<>();
            totalAmount = shareOpenAccountList.stream().mapToDouble(s -> s.getAmount()).sum();//总出资额
            for (ShareOpenAccount shareOpenAccount : shareOpenAccountList) {
                if (totalAmount != 0) {
                    shareOpenAccount.setPercentage((shareOpenAccount.getAmount() / totalAmount) * 100);
                    shareOpenAccount.setModifyTime(LocalDateTime.now());
                } else {
                    shareOpenAccount.setPercentage(0d);
                    shareOpenAccount.setModifyTime(LocalDateTime.now());
                }
                list.add(shareOpenAccount);
            }
            super.update(list);
        }
    }

    /**
     * 查询该股权类型的金额总和
     *
     * @return
     * @throws SerException
     */
    public Double totalAmount(String equityType) throws SerException {
        ShareOpenAccountDTO shareOpenAccountDTO = new ShareOpenAccountDTO();
        shareOpenAccountDTO.getConditions().add(Restrict.eq("equityType", equityType));
        List<ShareOpenAccount> shareOpenAccounts = super.findByCis(shareOpenAccountDTO);
        Double totalAmount = 0d;
        if (shareOpenAccounts != null && shareOpenAccounts.size() > 0) {
            totalAmount = shareOpenAccounts.stream().mapToDouble(s -> s.getAmount()).sum();//总出资额
        }
        return totalAmount;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ShareOpenAccountBO edit(ShareOpenAccountBTO shareOpenAccountBTO) throws SerException {
        checkPermission();
        ShareOpenAccount shareOpenAccount = super.findById(shareOpenAccountBTO.getId());
        if (shareOpenAccount == null) {
            throw new SerException("此数据不存在");
        }
        LocalDateTime date = shareOpenAccount.getCreateTime();
        Integer holdNum = shareOpenAccount.getHoldNum();//持股数量
        Double perSharePrice = shareOpenAccount.getPerSharePrice();//每股价格
        String capitalWay = shareOpenAccount.getCapitalWay();//出资方式
        String equityType = shareOpenAccount.getEquityType();//股权类型
        Double percentage = shareOpenAccount.getPercentage();//股权比例
        Double amount = shareOpenAccount.getAmount();//出资额
        ShareholderStatus shareholderStatus = shareOpenAccount.getShareholderStatus();//股东状态
        shareOpenAccount = BeanTransform.copyProperties(shareOpenAccountBTO, ShareOpenAccount.class, true, "remark");
        shareOpenAccount.setModifyTime(LocalDateTime.now());
        shareOpenAccount.setCreateTime(date);
        shareOpenAccount.setHoldNum(holdNum);
        shareOpenAccount.setPerSharePrice(perSharePrice);
        shareOpenAccount.setCapitalWay(capitalWay);
        shareOpenAccount.setEquityType(equityType);
        shareOpenAccount.setPercentage(percentage);
        shareOpenAccount.setShareholderStatus(shareholderStatus);
        shareOpenAccount.setAmount(amount);
        super.update(shareOpenAccount);
        return BeanTransform.copyProperties(shareOpenAccount, ShareOpenAccountBO.class);
    }

    @Override
    public List<String> findEquityType() throws SerException {
        List<ShareOpenAccount> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (ShareOpenAccount model : list) {
            String equityType = model.getEquityType();
            if (StringUtils.isNotBlank(model.getEquityType())) {
                set.add(equityType);
            }
        }
        return new ArrayList<>(set);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        ShareOpenAccountBO shareOpenAccountBO = this.getOne(id);
        equityTransactRecordSer.deleteByName(shareOpenAccountBO.getShareholderName());
        equityTransactRecordDetailSer.deleteByName(shareOpenAccountBO.getShareholderName());
        super.remove(id);
    }

    @Override
    public List<ShareOpenAccountBO> summationShareOpen(ShareOpenAccountDTO shareOpenAccountDTO) throws SerException {
       checkPermission();
        searchCondi(shareOpenAccountDTO);
        List<ShareOpenAccountBO> shareOpenAccountBOS = new ArrayList<>();
        List<ShareOpenAccount> shareOpenAccounts = super.findByCis(shareOpenAccountDTO);
        if (shareOpenAccounts != null && shareOpenAccounts.size() > 0) {
            shareOpenAccountBOS = BeanTransform.copyProperties(shareOpenAccounts, ShareOpenAccountBO.class);
            Integer totalHoldNum = shareOpenAccounts.stream().mapToInt(s -> s.getHoldNum()).sum();//总持股数量
            Double totalAmount = shareOpenAccounts.stream().mapToDouble(s -> s.getAmount()).sum();//总出资额
            ShareOpenAccountBO shareOpenAccountBO = new ShareOpenAccountBO();
            shareOpenAccountBO.setArea("合计");
            shareOpenAccountBO.setHoldNum(totalHoldNum);
            shareOpenAccountBO.setAmount(totalAmount);
            shareOpenAccountBOS.add(shareOpenAccountBO);
        }
        return shareOpenAccountBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ShareOpenAccountBO importExcel(List<ShareOpenAccountTO> shareOpenAccountTOS) throws SerException {
        if (shareOpenAccountTOS != null && shareOpenAccountTOS.size() > 0) {
            for (ShareOpenAccountTO shareOpenAccountTO : shareOpenAccountTOS) {
                save(shareOpenAccountTO);
            }
        }

        ShareOpenAccountBO shareOpenAccountBO = BeanTransform.copyProperties(new ShareOpenAccount(), ShareOpenAccountBO.class);
        return shareOpenAccountBO;
    }

    @Override
    public byte[] exportExcel(ShareOpenAccountDTO shareOpenAccountDTO) throws SerException {
        checkPermission();
        searchCondi(shareOpenAccountDTO);
        shareOpenAccountDTO.getConditions().add(Restrict.eq("shareholderStatus", ShareholderStatus.NORMAL));
        List<ShareOpenAccount> list = super.findByCis(shareOpenAccountDTO);
        List<ShareOpenAccountExport> shareOpenAccountExports = new ArrayList<>();
        list.stream().forEach(str -> {
            ShareOpenAccountExport excel = BeanTransform.copyProperties(str, ShareOpenAccountExport.class, "typeName", "gender", "shareholderStatus");
            excel.setTypeName(TypeName.exportStrConvert(str.getTypeName()));
            excel.setGender(Gender.exportStrConvert(str.getGender()));
            excel.setShareholderStatus(ShareholderStatus.exportStrConvert(str.getShareholderStatus()));
            shareOpenAccountExports.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(shareOpenAccountExports, excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<ShareOpenAccountExport> shareOpenAccountExports = new ArrayList<>();
        ShareOpenAccountExport excel = new ShareOpenAccountExport();
        excel.setArea("上海");
        excel.setOpenDate("2017-08-22");
        excel.setTypeName("法人");
        excel.setShareholderName("李四");
        excel.setGender("男");
        excel.setDocumentType("身份证");
        excel.setCertifiID("492093482309532903923");
        excel.setContactAddress("广州市,天河区");
        excel.setContactNum("1387289833");
        excel.setHoldNum(10);
        excel.setPerSharePrice(100d);
        excel.setAmount(1000d);
        excel.setPercentage(20d);
        excel.setCapitalWay("购股");
        excel.setEquityType("a");
        excel.setShareholderStatus("正常");
        excel.setRemark("");
        shareOpenAccountExports.add(excel);

        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(shareOpenAccountExports, exce);
        return bytes;
    }

    @Override
    public ShareOpenAccountBO findByName(String shareholderName) throws SerException {
        ShareOpenAccountDTO shareOpenAccountDTO = new ShareOpenAccountDTO();
        shareOpenAccountDTO.getConditions().add(Restrict.eq("shareholderName", shareholderName));
        ShareOpenAccount shareOpenAccount = super.findOne(shareOpenAccountDTO);
        return BeanTransform.copyProperties(shareOpenAccount, ShareOpenAccountBO.class);
    }

    @Override
    public List<String> findShareholderName() throws SerException {
        ShareOpenAccountDTO shareOpenAccountDTO = new ShareOpenAccountDTO();
        shareOpenAccountDTO.getConditions().add(Restrict.eq("shareholderStatus", ShareholderStatus.NORMAL));
        List<ShareOpenAccount> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (ShareOpenAccount model : list) {
            String shareholderName = model.getShareholderName();
            if (StringUtils.isNotBlank(model.getShareholderName())) {
                set.add(shareholderName);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findArea() throws SerException {
        List<ShareOpenAccount> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (ShareOpenAccount model : list) {
            String area = model.getArea();
            if (StringUtils.isNotBlank(model.getArea())) {
                set.add(area);
            }
        }
        return new ArrayList<>(set);
    }
}