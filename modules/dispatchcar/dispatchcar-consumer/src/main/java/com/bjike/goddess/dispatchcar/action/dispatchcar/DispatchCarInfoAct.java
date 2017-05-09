package com.bjike.goddess.dispatchcar.action.dispatchcar;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.dispatchcar.dto.DispatchCarInfoDTO;
import com.bjike.goddess.dispatchcar.to.DispatchCarInfoEditTO;
import com.bjike.goddess.dispatchcar.to.DispatchCarInfoTO;
import com.bjike.goddess.dispatchcar.vo.AuditDetailVO;
import com.bjike.goddess.dispatchcar.vo.DispatchCarInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 出车记录
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-12 05:26 ]
 * @Description: [ 出车记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("dispatchcarinfo")
public class DispatchCarInfoAct {

    @Autowired
    private DispatchCarInfoAPI dispatchCarInfoAPI;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DispatchCarInfoDTO dto) throws ActException {
        try {
            Long count = dispatchCarInfoAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id出车记录
     *
     * @param id 出车记录id
     * @return class DispatchCarInfoVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            DispatchCarInfoVO vo = BeanTransform.copyProperties(dispatchCarInfoAPI.findById(id), DispatchCarInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增出车记录
     *
     * @param editTO 出车记录
     * @return class DispatchCarInfoVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) DispatchCarInfoEditTO editTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            DispatchCarInfoTO to = BeanTransform.copyProperties(editTO,DispatchCarInfoTO.class);
            DispatchCarInfoVO vo = BeanTransform.copyProperties(dispatchCarInfoAPI.addModel(to), DispatchCarInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑出车记录
     *
     * @param editTO 出车记录
     * @return class DispatchCarInfoVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) DispatchCarInfoTO editTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            DispatchCarInfoTO to = BeanTransform.copyProperties(editTO,DispatchCarInfoTO.class);
            DispatchCarInfoVO vo = BeanTransform.copyProperties(dispatchCarInfoAPI.editModel(to), DispatchCarInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结出车记录
     *
     * @param id 出车记录id
     * @version v1
     */
    @PatchMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            dispatchCarInfoAPI.freeze(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻出车记录
     *
     * @param id 出车记录id
     * @version v1
     */
    @PatchMapping("v1/unfreeze/{id}")
    public Result breakFreeze(@PathVariable String id) throws ActException {
        try {
            dispatchCarInfoAPI.breakFreeze(id);
            return new ActResult("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @param request 附件内容
     * @param id      出车id
     * @version v1
     */
    @PostMapping("v1/upload/{id}")
    public Result fileUpload(HttpServletRequest request, BindingResult bindingResult, @PathVariable String id) throws ActException {
        //// TODO: 17-5-6  
        return new ActResult("success");
    }

    /**
     * 查看附件
     *
     * @param id 出车记录id
     * @version v1
     */
    @GetMapping("v1/files/{id}")
    public Result findFiles(@PathVariable String id) throws ActException {
        //// TODO: 17-4-14 查看附件
        return ActResult.initialize("success!");
    }

    /**
     * 审核详情
     *
     * @param id 出车记录id
     * @return class AuditDetailVO
     * @version v1
     */
    @GetMapping("v1/audit/{id}")
    public Result findAudit(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AuditDetailVO vo = BeanTransform.copyProperties(dispatchCarInfoAPI.findAudit(id), AuditDetailVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class DispatchCarInfoVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(DispatchCarInfoDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<DispatchCarInfoVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.pageList(dto), DispatchCarInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}