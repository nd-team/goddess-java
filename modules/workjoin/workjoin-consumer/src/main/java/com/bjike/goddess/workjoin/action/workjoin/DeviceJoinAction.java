package com.bjike.goddess.workjoin.action.workjoin;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workjoin.api.DeviceJoinAPI;
import com.bjike.goddess.workjoin.bo.DeviceJoinBO;
import com.bjike.goddess.workjoin.dto.DeviceJoinDTO;
import com.bjike.goddess.workjoin.to.DeviceJoinTO;
import com.bjike.goddess.workjoin.vo.DeviceJoinVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 设备交接
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:57 ]
 * @Description: [ 设备交接 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("devicejoin")
public class DeviceJoinAction {
    @Autowired
    private DeviceJoinAPI deviceJoinAPI;

    /**
     * 设备交接列表总条数
     *
     * @param deviceJoinDTO 设备交接dto
     * @des 获取所有设备交接总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DeviceJoinDTO deviceJoinDTO) throws ActException {
        try {
            Long count = deviceJoinAPI.countDeviceJoin(deviceJoinDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个设备交接
     *
     * @param id
     * @return class DeviceJoinVO
     * @des 获取一个设备交接
     * @version v1
     */
    @GetMapping("v1/device/{id}")
    public Result device(@PathVariable String id) throws ActException {
        try {
            DeviceJoinBO deviceJoinBO = deviceJoinAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(deviceJoinBO, DeviceJoinVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 设备交接列表
     *
     * @param deviceJoinDTO 设备交接dto
     * @return class DeviceJoinVO
     * @des 获取所有设备交接
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(DeviceJoinDTO deviceJoinDTO, HttpServletRequest request) throws ActException {
        try {
            List<DeviceJoinVO> deviceJoinVOS = BeanTransform.copyProperties
                    (deviceJoinAPI.findListDeviceJoin(deviceJoinDTO), DeviceJoinVO.class, request);
            return ActResult.initialize(deviceJoinVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加设备交接
     *
     * @param deviceJoinTO 设备交接数据to
     * @return class DeviceJoinVO
     * @des 添加设备交接
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) DeviceJoinTO deviceJoinTO, BindingResult bindingResult) throws ActException {
        try {
            DeviceJoinBO deviceJoinBO = deviceJoinAPI.insertDeviceJoin(deviceJoinTO);
            return ActResult.initialize(deviceJoinBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑设备交接
     *
     * @param deviceJoinTO 设备交接数据to
     * @return class DeviceJoinVO
     * @des 编辑设备交接
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) DeviceJoinTO deviceJoinTO, BindingResult bindingResult) throws ActException {
        try {
            DeviceJoinBO deviceJoinBO = deviceJoinAPI.editDeviceJoin(deviceJoinTO);
            return ActResult.initialize(deviceJoinBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除设备交接
     *
     * @param id 用户id
     * @des 根据用户id删除设备交接记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            deviceJoinAPI.removeDeviceJoin(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传
     *
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload() throws ActException {
        try {
            deviceJoinAPI.upload();
            return new ActResult("upload success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 下载
     *
     * @version v1
     */
    @PostMapping("v1/download")
    public Result download() throws ActException {
        try {
            deviceJoinAPI.download();
            return new ActResult("download success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


}