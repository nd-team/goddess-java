package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.FilesDataBO;
import com.bjike.goddess.marketdevelopment.dto.CustomerDTO;
import com.bjike.goddess.marketdevelopment.dto.FilesDataDTO;
import com.bjike.goddess.marketdevelopment.entity.Customer;
import com.bjike.goddess.marketdevelopment.entity.FilesData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 阶段表头数据业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:30 ]
 * @Description: [ 阶段表头数据业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class FilesDataSerImpl extends ServiceImpl<FilesData, FilesDataDTO> implements FilesDataSer {
    @Autowired
    private CustomerSer customerSer;

    @Override
    public FilesDataBO findFiles() throws SerException {
        FilesDataDTO dto = new FilesDataDTO();
        dto.getConditions().add(Restrict.eq("dateDataId", null));
        dto.getConditions().add(Restrict.ne("index", null));
        dto.getConditions().add(Restrict.ne("table", null));
        dto.getConditions().add(Restrict.eq("context", null));
        dto.getConditions().add(Restrict.eq("fatherId", null));
        dto.getSorts().add("index=asc");
        List<FilesData> filesDataList = super.findByCis(dto);
        if (null != filesDataList && filesDataList.size() > 0) {
            FilesDataBO bo = new FilesDataBO();
            bo.setTable("阶段");
            List<FilesDataBO> bos = new ArrayList<>(0);
            for (FilesData entity : filesDataList) {
                FilesDataBO filesDataBO = BeanTransform.copyProperties(entity, FilesDataBO.class, false);
                bos.add(filesDataBO);
                bo.getFilesDataVO1s().add(filesDataBO);
            }
//            bo.setFilesDataVO1s(bos);
            return bo;
        } else {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.getSorts().add("code=asc");
            List<Customer> customers = customerSer.findByCis(customerDTO);
            if (null != customers && customers.size() > 0) {
                int num = 1;
                FilesDataBO bo = new FilesDataBO();
                bo.setTable("阶段");
                for (Customer customer : customers) {
                    FilesData entity = new FilesData();
                    entity.setIndex(num++);
                    entity.setTable(customer.getStage() + customer.getCode());
                    entity = super.save(entity);
                    FilesDataBO filesDataBO = BeanTransform.copyProperties(entity, FilesDataBO.class, false);
                    bo.getFilesDataVO1s().add(filesDataBO);
                }
                return bo;
            } else {
                return null;
            }
        }
    }
}