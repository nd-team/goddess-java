package com.bjike.goddess.datastore.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.datastore.bo.FileSpecificationBO;
import com.bjike.goddess.datastore.dto.FileSpecificationDTO;
import com.bjike.goddess.datastore.entity.FileSpecification;
import com.bjike.goddess.datastore.service.FileSpecificationSer;
import com.bjike.goddess.datastore.to.FileSpecificationTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据存储文件规范业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 05:12 ]
 * @Description: [ 数据存储文件规范业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("fileSpecificationApiImpl")
public class FileSpecificationApiImpl implements FileSpecificationAPI {
    @Autowired
    private FileSpecificationSer fileSpecificationSer;
    @Override
    public Long countFileSpecification(FileSpecificationDTO fileSpecificationDTO) throws SerException {
        return fileSpecificationSer.countFileSpecification(fileSpecificationDTO);
    }
    @Override
    public FileSpecificationBO getOne(String id) throws SerException {
        return fileSpecificationSer.getOne(id);
    }

    @Override
    public List<FileSpecificationBO> findListFileSpecification(FileSpecificationDTO fileSpecificationDTO) throws SerException {
        return fileSpecificationSer.findListFileSpecification(fileSpecificationDTO);
    }

    @Override
    public FileSpecificationBO insertFileSpecification(FileSpecificationTO fileSpecificationTO) throws SerException {
        return fileSpecificationSer.insertFileSpecification(fileSpecificationTO);
    }

    @Override
    public FileSpecificationBO editFileSpecification(FileSpecificationTO fileSpecificationTO) throws SerException {
        return fileSpecificationSer.editFileSpecification(fileSpecificationTO);
    }


    @Override
    public void removeFileSpecification(String id) throws SerException {
        fileSpecificationSer.removeFileSpecification(id);
    }

}