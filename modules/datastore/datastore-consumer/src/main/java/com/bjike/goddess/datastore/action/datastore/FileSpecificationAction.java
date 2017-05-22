package com.bjike.goddess.datastore.action.datastore;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.datastore.api.AccountPwdSpecificationAPI;
import com.bjike.goddess.datastore.api.FileSpecificationAPI;
import com.bjike.goddess.datastore.bo.AccountPwdSpecificationBO;
import com.bjike.goddess.datastore.bo.FileSpecificationBO;
import com.bjike.goddess.datastore.dto.AccountPwdSpecificationDTO;
import com.bjike.goddess.datastore.dto.FileSpecificationDTO;
import com.bjike.goddess.datastore.to.AccountPwdSpecificationTO;
import com.bjike.goddess.datastore.to.FileSpecificationTO;
import com.bjike.goddess.datastore.vo.AccountPwdSpecificationVO;
import com.bjike.goddess.datastore.vo.FileSpecificationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 数据存储文件规范
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 06:14 ]
 * @Description: [ 数据存储文件规范 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("filespecification")
public class FileSpecificationAction {
    @Autowired
    private FileSpecificationAPI fileSpecificationAPI;

    /**
     * 数据存储文件规范列表总条数
     *
     * @param fileSpecificationDTO 数据存储文件规范dto
     * @des 获取所有数据存储文件规范总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FileSpecificationDTO fileSpecificationDTO) throws ActException {
        try {
            Long count = fileSpecificationAPI.countFileSpecification(fileSpecificationDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个数据存储文件规范
     *
     * @param id
     * @return class FileSpecificationVO
     * @des 获取一个数据存储文件规范
     * @version v1
     */
    @GetMapping("v1/file/{id}")
    public Result file(@PathVariable String id) throws ActException {
        try {
            FileSpecificationBO fileSpecificationBO = fileSpecificationAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(fileSpecificationBO, FileSpecificationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 数据存储文件规范列表
     *
     * @param fileSpecificationDTO 数据存储文件规范dto
     * @return class FileSpecificationVO
     * @des 获取所有数据存储文件规范
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FileSpecificationDTO fileSpecificationDTO, HttpServletRequest request) throws ActException {
        try {
            List<FileSpecificationVO> fileSpecificationVOS = BeanTransform.copyProperties
                    (fileSpecificationAPI.findListFileSpecification(fileSpecificationDTO),FileSpecificationVO.class,request);
            return ActResult.initialize(fileSpecificationVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加数据存储文件规范
     *
     * @param fileSpecificationTO 数据存储文件规范数据to
     * @return class FileSpecificationVO
     * @des 添加数据存储文件规范
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(FileSpecificationTO fileSpecificationTO, BindingResult bindingResult) throws ActException {
        try {
            FileSpecificationBO fileSpecificationBO = fileSpecificationAPI.insertFileSpecification(fileSpecificationTO);
            return ActResult.initialize(fileSpecificationBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑数据存储文件规范
     *
     * @param fileSpecificationTO 数据存储文件规范数据to
     * @return class FileSpecificationVO
     * @des 编辑数据存储文件规范
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(FileSpecificationTO fileSpecificationTO, BindingResult bindingResult) throws ActException {
        try {
            FileSpecificationBO fileSpecificationBO = fileSpecificationAPI.editFileSpecification(fileSpecificationTO);
            return ActResult.initialize(fileSpecificationBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除数据存储文件规范
     *
     * @param id 用户id
     * @des 根据用户id删除数据存储文件规范记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            fileSpecificationAPI.removeFileSpecification(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}