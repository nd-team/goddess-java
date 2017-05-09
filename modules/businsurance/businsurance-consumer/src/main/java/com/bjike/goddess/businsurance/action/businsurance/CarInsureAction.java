package com.bjike.goddess.businsurance.action.businsurance;

import com.bjike.goddess.businsurance.api.CarInsureAPI;
import com.bjike.goddess.businsurance.bo.CarInsureBO;
import com.bjike.goddess.businsurance.dto.CarInsureDTO;
import com.bjike.goddess.businsurance.to.CarInsureTO;
import com.bjike.goddess.businsurance.vo.CarInsureVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
public class CarInsureAction {



    @Autowired
    private CarInsureAPI carInsureAPI;

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
     * 一个方案查看详细
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
     * @param carInsureDTO 车险信息基本信息数据carInsureDTO
     * @des 上传附件
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile")
    public Result uploadFile(CarInsureDTO carInsureDTO, BindingResult bindingResult) throws ActException {
//        try {
//            CarInsureBO carInsureBO1 = carInsureAPI.uploadFile(carInsureDTO);
//            return ActResult.initialize(BeanTransform.copyProperties(carInsureBO1, CarInsureVO.class, true));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
        return new ActResult(null);
    }


    /**
     * 查看附件
     *
     * @param id 车险信息基本信息数据id
     * @des 查看附件
     * @version v1
     */
    @GetMapping("v1/getFile/{id}")
    public Result getFile(@PathVariable String id , BindingResult bindingResult) throws ActException {
//        try {
//            CarInsureBO carInsureBO1 = carInsureAPI.addCarInsure(carInsureTO);
//            return ActResult.initialize(BeanTransform.copyProperties(carInsureBO1, CarInsureVO.class, true));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
        return new ActResult(null);
    }


}