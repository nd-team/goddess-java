package com.bjike.goddess.devicerepair.action.devicerepair;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.file.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.devicerepair.api.DeviceRepairAPI;
import com.bjike.goddess.devicerepair.bo.DeviceRepairBO;
import com.bjike.goddess.devicerepair.dto.DeviceRepairDTO;
import com.bjike.goddess.devicerepair.to.DeviceRepairTO;
import com.bjike.goddess.devicerepair.to.FetchDeviceTO;
import com.bjike.goddess.devicerepair.to.WelfareAuditTO;
import com.bjike.goddess.devicerepair.type.AuditState;
import com.bjike.goddess.devicerepair.vo.DeviceRepairVO;
import com.bjike.goddess.storage.api.FileAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

/**
 * 设备维修
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-03 02:59 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("devicerepair")
public class DeviceRepairAct extends BaseFileAction {

    @Autowired
    private DeviceRepairAPI deviceRepairAPI;

    @Autowired
    private FileAPI fileAPI;


    /**
     * 根据id查询设备维修
     *
     * @param id 设备维修唯一标识
     * @return class DeviceRepairVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/devicerepair/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            DeviceRepairBO bo = deviceRepairAPI.findById(id);
            DeviceRepairVO vo = BeanTransform.copyProperties(bo, DeviceRepairVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 设备维修dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated DeviceRepairDTO dto, BindingResult result) throws ActException {
        try {
            Long count = deviceRepairAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 设备维修dto
     * @return class DeviceRepairVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated DeviceRepairDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<DeviceRepairBO> boList = deviceRepairAPI.list(dto);
            List<DeviceRepairVO> voList = BeanTransform.copyProperties(boList, DeviceRepairVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加设备维修
     *
     * @param to 设备维修to信息
     * @return class DeviceRepairVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) DeviceRepairTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            DeviceRepairBO bo = deviceRepairAPI.save(to);
            DeviceRepairVO vo = BeanTransform.copyProperties(bo, DeviceRepairVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除设备维修
     *
     * @param id 设备维修唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            deviceRepairAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑设备维修
     *
     * @param to 设备维修to信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) DeviceRepairTO to, BindingResult result) throws ActException {
        try {
            deviceRepairAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 福利模块审核
     *
     * @param to 设备维修to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/welfareAudit")
    public Result welfareAudit(@Validated(WelfareAuditTO.WelfareAudit.class) WelfareAuditTO to, BindingResult result) throws ActException {
        try {
            deviceRepairAPI.welfareAudit(to);
            return new ActResult("welfareAudit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目经理审核
     *
     * @param id           设备维修唯一标识
     * @param pmAuditState 项目经理审核状态
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/pmAudit/{id}")
    public Result pmAudit(@PathVariable String id, @RequestParam(value = "pmAuditState") AuditState pmAuditState) throws ActException {
        try {
            deviceRepairAPI.pmAudit(id, pmAuditState);
            return new ActResult("pmAudit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 设备报废
     *
     * @param id          设备维修唯一标识
     * @param deviceIssue 设备出现的问题
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/deviceScrap/{id}")
    public Result deviceScrap(@PathVariable String id, @RequestParam(value = "deviceIssue") String deviceIssue) throws ActException {
        try {
            deviceRepairAPI.deviceScrap(id, deviceIssue);
            return new ActResult("deviceScrap success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 取回设备
     *
     * @param to 设备维修to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/fetchDevice")
    public Result fetchDevice(@Validated(FetchDeviceTO.FetchDevice.class) FetchDeviceTO to, BindingResult result) throws ActException {
        try {
            deviceRepairAPI.fetchDevice(to);
            return new ActResult("fetchDevice success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload(HttpServletRequest request) throws ActException {
        try {
            String path = "/upload/devicerepair";
            List<InputStream> inputStream = this.getInputStreams(request, path);
            fileAPI.upload(inputStream);
            return new ActResult("上传成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}