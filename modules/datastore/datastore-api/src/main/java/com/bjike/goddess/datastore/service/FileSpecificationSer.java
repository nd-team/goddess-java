package com.bjike.goddess.datastore.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.datastore.bo.FileSpecificationBO;
import com.bjike.goddess.datastore.entity.FileSpecification;
import com.bjike.goddess.datastore.dto.FileSpecificationDTO;
import com.bjike.goddess.datastore.to.FileSpecificationTO;

import java.util.List;

/**
 * 数据存储文件规范业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 05:12 ]
 * @Description: [ 数据存储文件规范业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FileSpecificationSer extends Ser<FileSpecification, FileSpecificationDTO> {
    /**
     * 数据存储文件规范列表总条数
     */
    default Long countFileSpecification(FileSpecificationDTO fileSpecificationDTO) throws SerException {
        return null;
    }

    /**
     * 数据存储文件规范
     *
     * @param fileSpecificationDTO 数据存储文件规范dto
     * @return class FileSpecificationBO
     * @throws SerException
     */
    default List<FileSpecificationBO> findListFileSpecification(FileSpecificationDTO fileSpecificationDTO) throws SerException {
        return null;
    }

    /**
     * 添加数据存储文件规范
     *
     * @param fileSpecificationTO 数据存储文件规范数据to
     * @return class FileSpecificationBO
     * @throws SerException
     */
    default FileSpecificationBO insertFileSpecification(FileSpecificationTO fileSpecificationTO) throws SerException {
        return null;
    }

    /**
     * 编辑数据存储文件规范
     *
     * @param fileSpecificationTO 数据存储文件规范数据to
     * @return class FileSpecificationBO
     * @throws SerException
     */
    default FileSpecificationBO editFileSpecification(FileSpecificationTO fileSpecificationTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除数据存储文件规范
     *
     * @param id
     * @throws SerException
     */
    default void removeFileSpecification(String id) throws SerException {

    }


}