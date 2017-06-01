package com.bjike.goddess.account.service;

import com.bjike.goddess.account.bo.AccountCollectBO;
import com.bjike.goddess.account.bo.AccountInfoBO;
import com.bjike.goddess.account.dto.AccountInfoDTO;
import com.bjike.goddess.account.entity.AccountInfo;
import com.bjike.goddess.account.to.AccountInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private CusPermissionSer cusPermissionSer;
    @Override
    public Long countAccountInfo(AccountInfoDTO accountInfoDTO) throws SerException {
        Long count = super.count(accountInfoDTO);
        return count;
    }

    @Override
    public AccountInfoBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        AccountInfo accountInfo = super.findById(id);
        return BeanTransform.copyProperties(accountInfo,AccountInfoBO.class);
    }
    @Override
    public List<AccountInfoBO> findListAccountInfo(AccountInfoDTO accountInfoDTO) throws SerException {
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您的帐号没有权限");
        }
        List<AccountInfo> accountInfos = super.findByCis(accountInfoDTO,true);
        return BeanTransform.copyProperties(accountInfos,AccountInfoBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public AccountInfoBO insertAccountInfo(AccountInfoTO accountInfoTO) throws SerException {
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您不是财务人员，没有权限");
        }
        AccountInfo accountInfo = BeanTransform.copyProperties(accountInfoTO,AccountInfo.class,true);
        accountInfo.setCreateTime(LocalDateTime.now());
        super.save(accountInfo);
        return BeanTransform.copyProperties(accountInfo,AccountInfoBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public AccountInfoBO editAccountInfo(AccountInfoTO accountInfoTO) throws SerException {
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您不是财务人员，没有权限");
        }
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

        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您不是财务人员，没有权限");
        }
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public List<AccountCollectBO> collectAccountInfo(String [] areas) throws SerException {
        if(areas == null || areas.length <= 0){
            throw new SerException("汇总失败，请选择地区");
        }
        String[] areasTemp = new String[areas.length];
        for(int i = 0;i<areas.length;i++){
            areasTemp[i] = "'"+areas[i]+"'";
        }
        String areaStr = StringUtils.join(areasTemp,",");
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT area,projectName AS projectName,projectGroup AS projectGroup, ");
        sb.append(" firstSubject AS firstSubject,secondSubject AS secondSubject,thirdSubject AS thirdSubject ");
        sb.append(" FROM account_accountinfo a WHERE area in(%s)GROUP BY ");
        sb.append(" firstSubject,secondSubject,thirdSubject,projectName,projectGroup,area ORDER BY area; ");
        String sql = sb.toString();
        sql = String.format(sql,areaStr);
        String [] fields = new String[]{"area","projectName","projectGroup","firstSubject","secondSubject","thirdSubject"};
        List<AccountCollectBO> accountCollectBOS = super.findBySql(sql,AccountCollectBO.class,fields);
        return accountCollectBOS;
    }

    @Override
    public List<String> getArea() throws SerException {
        String [] fields = new String[]{"area"};
        List<AccountCollectBO> accountCollectBOS = super.findBySql("select distinct area from account_accountinfo group by area order by area asc ",AccountCollectBO.class,fields);

        List<String> areaList = accountCollectBOS.stream().map(AccountCollectBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return areaList;
    }

}