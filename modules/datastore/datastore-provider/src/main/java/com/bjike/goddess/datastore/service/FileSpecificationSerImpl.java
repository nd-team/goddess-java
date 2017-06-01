package com.bjike.goddess.datastore.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.datastore.bo.FileSpecificationBO;
import com.bjike.goddess.datastore.dto.FileSpecificationDTO;
import com.bjike.goddess.datastore.entity.FileSpecification;
import com.bjike.goddess.datastore.to.FileSpecificationTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

/**
 * 数据存储文件规范业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 05:12 ]
 * @Description: [ 数据存储文件规范业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "datastoreSerCache")
@Service
public class FileSpecificationSerImpl extends ServiceImpl<FileSpecification, FileSpecificationDTO> implements FileSpecificationSer {

    @Override
    public Long countFileSpecification(FileSpecificationDTO fileSpecificationDTO) throws SerException {
        fileSpecificationDTO.getSorts().add("createTime=desc");
        Long counts = super.count(fileSpecificationDTO);
        return counts;
    }
    @Override
    public FileSpecificationBO getOne(String id) throws SerException {
        FileSpecification fileSpecification = super.findById(id);
        return BeanTransform.copyProperties(fileSpecification, FileSpecificationBO.class);
    }

    @Override
    public List<FileSpecificationBO> findListFileSpecification(FileSpecificationDTO fileSpecificationDTO) throws SerException {
        fileSpecificationDTO.getSorts().add("createTime=desc");
        List<FileSpecification> fileSpecifications = super.findByCis(fileSpecificationDTO, true);
        List<FileSpecificationBO> fileSpecificationBOS = BeanTransform.copyProperties(fileSpecifications,FileSpecificationBO.class);
        return fileSpecificationBOS;
    }

    @Override
    public FileSpecificationBO insertFileSpecification(FileSpecificationTO fileSpecificationTO) throws SerException {
        FileSpecification fileSpecification = BeanTransform.copyProperties(fileSpecificationTO,FileSpecification.class,true);
        fileSpecification.setCreateTime(LocalDateTime.now());
        super.save(fileSpecification);
        return BeanTransform.copyProperties(fileSpecification,FileSpecificationBO.class);
    }

    @Override
    public FileSpecificationBO editFileSpecification(FileSpecificationTO fileSpecificationTO) throws SerException {
        FileSpecification fileSpecification = super.findById(fileSpecificationTO.getId());
        BeanTransform.copyProperties(fileSpecificationTO,fileSpecification,true);
        fileSpecification.setModifyTime(LocalDateTime.now());
        super.update(fileSpecification);
        return BeanTransform.copyProperties(fileSpecification,FileSpecificationBO.class);
    }


    @Override
     public void removeFileSpecification(String id) throws SerException {
        super.remove(id);
    }
}