package com.bjike.goddess.driverinfo.action.driverinfo;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.driverinfo.api.DriverInfoAPI;
import com.bjike.goddess.driverinfo.bo.DriverInfoBO;
import com.bjike.goddess.driverinfo.dto.DriverInfoDTO;
import com.bjike.goddess.driverinfo.to.DriverInfoTO;
import com.bjike.goddess.driverinfo.vo.DriverInfoVO;
import com.bjike.goddess.storage.api.FileAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 租车协议车辆信息管理控制器
 *
 * @Author: [Jason]
 * @Date: [17-3-8 上午9:41]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("driverinfo")
public class DriverInfoAct extends BaseFileAction {

    @Autowired
    private DriverInfoAPI driverInfoAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 新增司机信息
     *
     * @param to 司机信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) DriverInfoTO to, BindingResult bindingResult) throws ActException {

        try {
            DriverInfoVO vo = BeanTransform.copyProperties(driverInfoAPI.saveDriverInfo(to), DriverInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑司机信息
     *
     * @param to 司机信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated({EDIT.class})DriverInfoTO to, BindingResult bindingResult) throws ActException {

        try {
            DriverInfoVO vo = BeanTransform.copyProperties(driverInfoAPI.updateDriverInfo(to), DriverInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核司机信息
     *
     * @param to 司机信息
     * @version v1
     */
    @PostMapping("v1/audit")
    public Result audit(DriverInfoTO to, BindingResult bindingResult) throws ActException {

        try {
            DriverInfoVO vo = BeanTransform.copyProperties(driverInfoAPI.updateAudit(to), DriverInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除对应id司机信息
     *
     * @param id 司机信息id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            driverInfoAPI.remove(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结对应id司机信息状态
     *
     * @param id 司机信息id
     * @version v1
     */
    @GetMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            driverInfoAPI.updateStatus(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件证件照片
     *
     * @param name 司机姓名
     * @version v1
     */
    @PostMapping("v1/uploadImage")
    public Result uploadImage(String name, HttpServletRequest request) throws ActException {
        try {
            String path = "driverinfo";
            fileAPI.upload(this.getInputStreams(request, path.toString()));
            return new ActResult("上传成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * EXCEL数据导入
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @PostMapping("v1/uploadExcel")
    public Result uploadExcel(HttpServletRequest request) {
        // TODO: 17-3-20
        return null;
    }

    /**
     * EXCEL数据下载
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @PostMapping("v1/downloadExcel")
    public Result downloadExcel(HttpServletRequest request) {
        // TODO: 17-3-20
        return null;
    }

    /**
     * 分页查询
     *
     * @param dto  分页查询条件
     * @param type 记录类型
     * @version v1
     */
    @GetMapping("v1/pageList/{type}")
    public Result pageList(DriverInfoDTO dto, @PathVariable String type) throws ActException {

        try {
            List<DriverInfoBO> boList = driverInfoAPI.pageList(dto, type);
            List<DriverInfoVO> voList = BeanTransform.copyProperties(boList, DriverInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }
}
