package com.bjike.goddess.account.service;

import com.bjike.goddess.account.bo.AccountInfoBO;
import com.bjike.goddess.account.to.AccountInfoTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.account.dto.AccountInfoDTO;
import com.bjike.goddess.account.entity.AccountInfo;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.SplittableRandom;

/**
 * 明细账信息业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-06 11:25 ]
 * @Description: [ 明细账信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "accountSerCache")
@Service
public class AccountInfoSerImpl extends ServiceImpl<AccountInfo, AccountInfoDTO> implements AccountInfoSer {

    @Autowired
    private AccountInfoSer accountInfoSer;
    @Cacheable
    @Override
    public List<AccountInfoBO> findListAccountInfo(AccountInfoDTO accountInfoDTO) throws SerException {
        List<AccountInfo> accountInfos = super.findByCis(accountInfoDTO,true);
        return BeanTransform.copyProperties(accountInfos,AccountInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AccountInfoBO insertAccountInfo(AccountInfoTO accountInfoTO) throws SerException {
        AccountInfo accountInfo = BeanTransform.copyProperties(accountInfoTO,AccountInfo.class,true);
        accountInfo.setCreateTime(LocalDateTime.now());
        super.save(accountInfo);
        return BeanTransform.copyProperties(accountInfo,AccountInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AccountInfoBO editAccountInfo(AccountInfoTO accountInfoTO) throws SerException {
        if(!StringUtils.isEmpty(accountInfoTO.getId())){
            AccountInfo accountInfo = super.findById(accountInfoTO.getId());
            BeanTransform.copyProperties(accountInfoTO,accountInfo,true);
            accountInfo.setModifyTime(LocalDateTime.now());
            super.update(accountInfo);
        }else{
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(accountInfoTO,AccountInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeAccountInfo(String id) throws SerException {
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<AccountInfoBO> collectAccountInfo(String area,String projectName,String projectGroup) throws SerException{
        AccountInfoDTO dto = new AccountInfoDTO();
        dto.getSorts().add("createTime=desc");
        String sql = "select distinct area ,1 from account_accountinfo info where 0 = 0 ";
        if(!StringUtils.isEmpty(area)){
            dto.getConditions().add(Restrict.eq("area",area));
            sql = sql + "and info.area = '" + area + "'";
        }
        if(!StringUtils.isEmpty(projectName)){
            dto.getConditions().add(Restrict.eq("projectName",projectName));
            sql = sql + "and info.projectName = '" + projectName + "'";
        }
        if(!StringUtils.isEmpty(projectGroup)){
            dto.getConditions().add(Restrict.eq("projectGroup",projectGroup));
            sql = sql + "and info.projectGroup = '" + projectGroup + "'";
        }
        //查询地区
        List<AccountInfo> areaList = accountInfoSer.findBySql(sql,AccountInfo.class,new String[]{"area"});
        //查询项目名称
        List<AccountInfo> proNameList = accountInfoSer.findBySql(sql,AccountInfo.class,new String[]{"projectName"});
        //查询项目组
        List<AccountInfo> proGroupList = accountInfoSer.findBySql(sql,AccountInfo.class,new String[]{"projectGroup"});
        //查询符合条件的信息
        List<AccountInfo> infoList = accountInfoSer.findByCis(dto);
        List<AccountInfoBO> boList = BeanTransform.copyProperties(areaList,AccountInfoBO.class);

        if (boList != null && !boList.isEmpty()) {

            if (infoList != null && !infoList.isEmpty()) {
                for (AccountInfoBO bo : boList) {
                    Double debitAmount = 0.0;
                    Double creditAmount = 0.0;

                    for (AccountInfo info : infoList) {

                        if (bo.getArea().equals(info.getArea())) {
                            //设置借方金额,贷方金额
                            debitAmount = debitAmount + info.getDebitAmount();
                            creditAmount = creditAmount + info.getCreditAmount();
                        }
                    }
                    bo.setDebitAmount(debitAmount);
                    bo.setCreditAmount(creditAmount);
                }
            }
            Double debitAmount = boList.stream().filter(p -> null != p.getDebitAmount()).mapToDouble(p -> p.getDebitAmount()).sum();
            Double creditAmount = boList.stream().filter(p -> null != p.getCreditAmount()).mapToDouble(p -> p.getCreditAmount()).sum();
            AccountInfoBO accountInfoBO = new AccountInfoBO("合计",null,null,debitAmount,creditAmount);
            boList.add(accountInfoBO);
        }
        return boList;
    }

}