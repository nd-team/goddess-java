package com.bjike.goddess.contractware.action.contractware;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.api.CarRentalAgreementAPI;
import com.bjike.goddess.contractware.bo.CarRentalAgreementBO;
import com.bjike.goddess.contractware.dto.CarRentalAgreementDTO;
import com.bjike.goddess.contractware.to.CarRentalAgreementTO;
import com.bjike.goddess.contractware.to.ContractwareDeleteFileTO;
import com.bjike.goddess.contractware.vo.CarRentalAgreementVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * 租车协议
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:43 ]
 * @Description: [ 租车协议 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("carrentalagreement")
public class CarRentalAgreementAction extends BaseFileAction{
    @Autowired
    private CarRentalAgreementAPI carRentalAgreementAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 租车协议列表总条数
     *
     * @param carRentalAgreementDTO 租车协议dto
     * @des 获取所有租车协议总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CarRentalAgreementDTO carRentalAgreementDTO) throws ActException {
        try {
            Long count = carRentalAgreementAPI.countCarRentalAgreement(carRentalAgreementDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个租车协议
     *
     * @param id
     * @return class CarRentalAgreementVO
     * @des 获取一个租车协议
     * @version v1
     */
    @GetMapping("v1/car/{id}")
    public Result car(@PathVariable String id) throws ActException {
        try {
            CarRentalAgreementBO carRentalAgreementBO = carRentalAgreementAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(carRentalAgreementBO, CarRentalAgreementVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 租车协议列表
     *
     * @param carRentalAgreementDTO 租车协议dto
     * @return class CarRentalAgreementVO
     * @des 获取所有租车协议
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CarRentalAgreementDTO carRentalAgreementDTO, HttpServletRequest request) throws ActException {
        try {
            List<CarRentalAgreementVO> carRentalAgreementVOS = BeanTransform.copyProperties
                    (carRentalAgreementAPI.findListCarRentalAgreement(carRentalAgreementDTO), CarRentalAgreementVO.class, request);
            return ActResult.initialize(carRentalAgreementVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加租车协议
     *
     * @param carRentalAgreementTO 租车协议数据to
     * @return class CarRentalAgreementVO
     * @des 添加租车协议
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) CarRentalAgreementTO carRentalAgreementTO, BindingResult bindingResult) throws ActException {
        try {
            CarRentalAgreementBO carRentalAgreementBO = carRentalAgreementAPI.insertCarRentalAgreement(carRentalAgreementTO);
            return ActResult.initialize(carRentalAgreementBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑租车协议
     *
     * @param carRentalAgreementTO 租车协议数据to
     * @return class CarRentalAgreementVO
     * @des 编辑租车协议
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) CarRentalAgreementTO carRentalAgreementTO, BindingResult bindingResult) throws ActException {
        try {
            CarRentalAgreementBO carRentalAgreementBO = carRentalAgreementAPI.editCarRentalAgreement(carRentalAgreementTO);
            return ActResult.initialize(carRentalAgreementBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除租车协议
     *
     * @param id 用户id
     * @des 根据用户id删除租车协议记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            carRentalAgreementAPI.removeCarRentalAgreement(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @des 合同保管
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String paths = "/" + id;
            List<InputStream> inputStreams = getInputStreams(request, paths);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id 合同保管id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /bidding/id/....
            String path = "/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件下载
     *
     * @param path 文件信息路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {


            //该文件的路径
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("download success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 删除文件或文件夹
     *
     * @param contractwareDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(ContractwareDeleteFileTO.TestDEL.class) ContractwareDeleteFileTO contractwareDeleteFileTO, HttpServletRequest request) throws SerException {
        if(null != contractwareDeleteFileTO.getPaths() && contractwareDeleteFileTO.getPaths().length>=0 ){
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(),contractwareDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

}