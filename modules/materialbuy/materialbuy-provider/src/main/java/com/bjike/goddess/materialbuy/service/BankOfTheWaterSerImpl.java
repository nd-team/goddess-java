package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.materialbuy.bo.BankOfTheWaterBO;
import com.bjike.goddess.materialbuy.bo.ExcelTitleBO;
import com.bjike.goddess.materialbuy.dto.BankOfTheWaterDTO;
import com.bjike.goddess.materialbuy.entity.BankOfTheWater;
import com.bjike.goddess.materialbuy.to.BankOfTheWaterTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.channels.NonWritableChannelException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 银行流水业务实现
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-04 09:39 ]
 * @Description: [ 银行流水业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialbuySerCache")
@Service
public class BankOfTheWaterSerImpl extends ServiceImpl<BankOfTheWater, BankOfTheWaterDTO> implements BankOfTheWaterSer {
    @Override
    public List<ExcelTitleBO> check(List<InputStream> inputStreams) throws SerException {
        return null;
    }

    @Override
    public void upload(BankOfTheWaterTO to) throws SerException {

    }

    @Override
    public List<BankOfTheWaterBO> listPath(BankOfTheWaterDTO dto) throws SerException {
        dto.getSorts().add("tradingDay=desc");
        dto.getSorts().add("theBankAccount=desc");
        return BeanTransform.copyProperties(super.findByCis(dto),BankOfTheWaterBO.class);
    }

    @Override
    public List<BankOfTheWaterBO> bankSummary(BankOfTheWaterDTO dto) throws SerException {
            if (dto.getTheStartTime().until(dto.getAccountingDate(), ChronoUnit.WEEKS) > 0) {
                   dto.getConditions().add(Restrict.lt("theStartTime",dto.getTheStartTime()));
                   dto.getConditions().add(Restrict.gt("theEndOfrThe",dto.getTheEndOfrThe()));
                    List<BankOfTheWater> list= super.findByCis(dto);
                     if(list==null){
                         throw new SerException("没有任何数据");
                     }else{
                         for (int i=0;i<list.size();i++){
                             list.get(i).getTradingDay();
                         }
                     }
            } else {
                throw new SerException("不能低于账套会计期间启用日期");
            }
    return  null;
    }

    @Override
    public List<BankOfTheWaterBO> banksGlobally() throws SerException {
       return BeanTransform.copyProperties(super.findAll(),BankOfTheWaterBO.class);
    }


}