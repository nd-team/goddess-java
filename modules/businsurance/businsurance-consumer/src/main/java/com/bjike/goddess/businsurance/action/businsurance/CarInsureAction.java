package com.bjike.goddess.businsurance.action.businsurance;

import com.bjike.goddess.businsurance.api.CarInsureAPI;
import com.bjike.goddess.businsurance.bo.CarInsureBO;
import com.bjike.goddess.businsurance.dto.CarInsureDTO;
import com.bjike.goddess.businsurance.to.CarInsureTO;
import com.bjike.goddess.businsurance.to.SiginManageDeleteFileTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.businsurance.vo.CarInsureVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
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
 * 车险信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 11:00 ]
 * @Description: [ 车险信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("carinsure")
public class CarInsureAction extends BaseFileAction{



    @Autowired
    private CarInsureAPI carInsureAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = carInsureAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 总条数
     *
     * @param carInsureDTO 车险信息信息dto
     * @des 获取所有车险信息信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CarInsureDTO carInsureDTO) throws ActException {
        try {
            Long count = carInsureAPI.countCarInsure(carInsureDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 车险信息列表
     *
     * @param carInsureDTO 车险信息信息dto
     * @return class CarInsureVO
     * @des 获取所有车险信息信息
     * @version v1
     */
    @GetMapping("v1/listCarInsure")
    public Result findList(CarInsureDTO carInsureDTO, BindingResult bindingResult) throws ActException {
        try {
            List<CarInsureVO> carInsureVOList = BeanTransform.copyProperties(
                    carInsureAPI.listCarInsure(carInsureDTO), CarInsureVO.class, true);
            return ActResult.initialize(carInsureVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param carInsureTO 车险信息基本信息数据to
     * @return class CarInsureVO
     * @des 添加车险信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(CarInsureTO.TestAdd.class) CarInsureTO carInsureTO, BindingResult bindingResult) throws ActException {
        try {
            CarInsureBO carInsureBO1 = carInsureAPI.addCarInsure(carInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(carInsureBO1, CarInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑
     *
     * @param carInsureTO 车险信息基本信息数据bo
     * @return class CarInsureVO
     * @des 编辑车险信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(CarInsureTO.TestAdd.class) CarInsureTO carInsureTO) throws ActException {
        try {
            CarInsureBO carInsureBO1 = carInsureAPI.editCarInsure(carInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(carInsureBO1, CarInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除车险信息信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            carInsureAPI.deleteCarInsure(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 编辑被保险机动车信息
     *
     * @param carInsureTO 车险信息基本信息数据bo
     * @return class CarInsureVO
     * @des 编辑被保险机动车信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editCar")
    public Result editCar(@Validated(CarInsureTO.TestEditCar.class) CarInsureTO carInsureTO) throws ActException {
        try {
            CarInsureBO carInsureBO1 = carInsureAPI.editCar(carInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(carInsureBO1, CarInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑要约内容
     *
     * @param carInsureTO 车险信息基本信息数据bo
     * @return class CarInsureVO
     * @des 编辑要约内容
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editContext")
    public Result editContext(@Validated CarInsureTO carInsureTO) throws ActException {
        try {
            CarInsureBO carInsureBO1 = carInsureAPI.editContext(carInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(carInsureBO1, CarInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑特别约定
     *
     * @param carInsureTO 车险信息基本信息数据bo
     * @return class CarInsureVO
     * @des 编辑特别约定
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editAppoint")
    public Result editAppoint(@Validated CarInsureTO carInsureTO) throws ActException {
        try {
            CarInsureBO carInsureBO1 = carInsureAPI.editAppoint(carInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(carInsureBO1, CarInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑销售机构信息
     *
     * @param carInsureTO 车险信息基本信息数据bo
     * @return class CarInsureVO
     * @des 编辑销售机构信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editSale")
    public Result editSale(@Validated CarInsureTO carInsureTO) throws ActException {
        try {
            CarInsureBO carInsureBO1 = carInsureAPI.editSale(carInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(carInsureBO1, CarInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }




    /**
     * 一个车险查看详细
     *
     * @param id id
     * @return class CarInsureVO
     * @des 根据id查看详细
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            CarInsureBO carInsureBO1 = carInsureAPI.getCarInsure(id);
            return ActResult.initialize(BeanTransform.copyProperties(carInsureBO1, CarInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 上传附件
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/businsurance/CarInsure/" + id;
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/businsurance/CarInsure/" + id;
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
     * @param path 文件路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            FileInfo fileInfo = new FileInfo();
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            fileInfo.setPath(path);
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
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(SiginManageDeleteFileTO.TestDEL.class) SiginManageDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

}