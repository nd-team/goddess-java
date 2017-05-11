package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.lendreimbursement.bo.FinoddinforBO;
import com.bjike.goddess.lendreimbursement.dto.FinoddinforDTO;
import com.bjike.goddess.lendreimbursement.entity.Finoddinfor;
import com.bjike.goddess.lendreimbursement.to.FinoddinforTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 报销单号管理业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-12 09:19 ]
 * @Description: [ 报销单号管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "lendreimbursementSerCache")
@Service
public class FinoddinforSerImpl extends ServiceImpl<Finoddinfor, FinoddinforDTO> implements FinoddinforSer {

    @Override
    public Long countFinoddinfor(FinoddinforDTO finoddinforDTO) throws SerException {
        finoddinforDTO.getConditions().add(Restrict.eq(STATUS,Status.THAW));
        Long count = super.count(finoddinforDTO);
        return count;
    }

    @Override
    public FinoddinforBO getOneById(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能呢为空");
        }
        Finoddinfor finoddinfor = super.findById(id);
        return BeanTransform.copyProperties(finoddinfor, FinoddinforBO.class );
    }


    @Override
    public List<FinoddinforBO> listFinoddinfor(FinoddinforDTO finoddinforDTO) throws SerException {
        finoddinforDTO.getSorts().add("runNum=desc");
        finoddinforDTO.getConditions().add(Restrict.eq(STATUS,Status.THAW));

        List<Finoddinfor> list = super.findByCis(finoddinforDTO,true);

        return BeanTransform.copyProperties(list, FinoddinforBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FinoddinforBO addFinoddinfor(FinoddinforTO finoddinforTO) throws SerException {
//        Finoddinfor infor = BeanTransform.copyProperties(finoddinforTO,Finoddinfor.class,true);
//        infor.setCreateTime(LocalDateTime.now());
//        super.save( infor );

        //临时容器
        List<Finoddinfor> models = new ArrayList<>(0);
        Finoddinfor finOddinfor = null;

        FinoddinforDTO dto = new FinoddinforDTO();
        dto.getSorts().add("runNum=asc");
        List<Finoddinfor> oddinfors = super.findByCis(dto);

        // 单号头的临时容器
        String runNum = finoddinforTO.getRunNum();
        for (int i = finoddinforTO.getStartNum(), end = finoddinforTO.getEndNum(); i <= end; i++) {
            StringBuilder runNumAdd = new StringBuilder(0);
            runNumAdd.append(finoddinforTO.getRunNum());
            if (i < 10) {
                runNumAdd.append("00");
                runNumAdd.append(i);
                finoddinforTO.setRunNum(runNumAdd.toString());
            } else if(10 <=i && i <=99) {
                runNumAdd.append("0");
                runNumAdd.append(i);
                finoddinforTO.setRunNum(runNumAdd.toString());
            }else {
                runNumAdd.append(i);
                finoddinforTO.setRunNum(runNumAdd.toString());
            }
            for(Finoddinfor oddinfor:oddinfors){
                if(oddinfor.getRunNum().equals(runNumAdd.toString())){
                    throw new SerException("你填写的单号数据库已存在,请重新确认再输入!") ;
                }
            }
            finOddinfor = new Finoddinfor( finoddinforTO.getRunNum()  );
            finOddinfor.setRunNum(finoddinforTO.getRunNum());
            finOddinfor.setStatus(Status.THAW);
            finOddinfor.setCreateTime(LocalDateTime.now());
            models.add(finOddinfor);
            finoddinforTO.setRunNum(runNum);
        }
        super.save(models);
        return BeanTransform.copyProperties(finoddinforTO, FinoddinforBO.class);
    }



    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteFinoddinfor(String id) throws SerException {
        if (StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        super.remove( id );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealFinoddinfor(String id) throws SerException {
        Finoddinfor finoddinfor = super.findById( id );
        finoddinfor.setStatus(Status.CONGEAL);
        finoddinfor.setModifyTime(LocalDateTime.now());
        super.update( finoddinfor );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawFinoddinfor(String id) throws SerException {
        Finoddinfor finoddinfor = super.findById( id );
        finoddinfor.setStatus(Status.THAW);
        finoddinfor.setModifyTime(LocalDateTime.now());
        super.update( finoddinfor );
    }

    @Override
    public String getMinRunNum() throws SerException {
        String[] fields = new String[]{"runNum"};
        List<Finoddinfor> finoddinforList =
                super.findBySql("select min(runNum)  from lendreimbursement_finoddinfor where status=0 ",
                        Finoddinfor.class, fields);

        List<String> strs = finoddinforList.stream().map(Finoddinfor::getRunNum)
                .filter(name -> (name != null || !"".equals(name.trim()))).distinct().collect(Collectors.toList());

        String minNum = strs!=null && strs.size()>0 ? strs.get(0):null;
        return minNum;
    }
}