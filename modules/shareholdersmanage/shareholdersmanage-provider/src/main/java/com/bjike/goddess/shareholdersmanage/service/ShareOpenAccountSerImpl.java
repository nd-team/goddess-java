package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.ShareOpenAccountBO;
import com.bjike.goddess.shareholdersmanage.dto.ShareOpenAccountDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.entity.ShareOpenAccount;
import com.bjike.goddess.shareholdersmanage.excel.ShareOpenAccountExport;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordDetailTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOpenAccountBTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOpenAccountTO;
import com.bjike.goddess.shareholdersmanage.type.Gender;
import com.bjike.goddess.shareholdersmanage.type.ShareholderStatus;
import com.bjike.goddess.shareholdersmanage.type.TypeName;
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
        ShareOpenAccount shareOpenAccount = super.findById(shareOpenAccountBTO.getId());
        if (shareOpenAccount == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime date = shareOpenAccount.getCreateTime();
        Integer holdNum = shareOpenAccount.getHoldNum();//持股数量
        Double perSharePrice = shareOpenAccount.getPerSharePrice();//每股价格
        String capitalWay = shareOpenAccount.getCapitalWay();//出资方式
        String equityType = shareOpenAccount.getEquityType();//股权类型
        Double percentage = shareOpenAccount.getPercentage();//股权比例
        shareOpenAccount = BeanTransform.copyProperties(shareOpenAccountBTO, ShareOpenAccount.class, true, "remark");
        shareOpenAccount.setModifyTime(LocalDateTime.now());
        shareOpenAccount.setCreateTime(date);
        shareOpenAccount.setHoldNum(holdNum);
        shareOpenAccount.setPerSharePrice(perSharePrice);
        shareOpenAccount.setCapitalWay(capitalWay);
        shareOpenAccount.setEquityType(equityType);
        shareOpenAccount.setPercentage(percentage);
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
}