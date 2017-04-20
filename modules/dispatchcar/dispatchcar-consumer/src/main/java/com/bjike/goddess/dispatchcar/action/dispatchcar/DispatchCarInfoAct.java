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
import com.bjike.goddess.dispatchcar.to.DispatchCarInfoTO;
import com.bjike.goddess.dispatchcar.vo.DispatchCarInfoVO;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 新增出车记录
     *
     * @param to 出车记录
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) DispatchCarInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            DispatchCarInfoVO vo = BeanTransform.copyProperties(dispatchCarInfoAPI.addModel(to), DispatchCarInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑出车记录
     *
     * @param to 出车记录
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) DispatchCarInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            DispatchCarInfoVO vo = BeanTransform.copyProperties(dispatchCarInfoAPI.editModel(to), DispatchCarInfoVO.class);
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
    @GetMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            dispatchCarInfoAPI.freeze(id);
            return new ActResult();
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
    @GetMapping("v1/breakFreeze/{id}")
    public Result breakFreeze(@PathVariable String id) throws ActException {
        try {
            dispatchCarInfoAPI.breakFreeze(id);
            return new ActResult();
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
    @GetMapping("v1/fileUpload/{id}")
    public Result fileUpload(HttpServletRequest request, BindingResult bindingResult, @PathVariable String id) throws ActException {
        try {
            //文件上传
            try {
                List<MultipartFile> multipartFiles = this.getMultipartFile(request);
                Map<String, byte[]> map = new HashMap<>(multipartFiles.size());
                for (MultipartFile multipartFile : multipartFiles) {
                    byte[] bytes = IOUtils.toByteArray(multipartFile.getInputStream());
                    map.put(multipartFile.getOriginalFilename(), bytes);
                }
                DispatchCarInfoTO to = new DispatchCarInfoTO();
                to.setId(id);
                to.setMap(map);
                dispatchCarInfoAPI.fileUpload(to);
            } catch (IOException e) {
                throw new ActException(e.getMessage());
            }
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    private List<MultipartFile> getMultipartFile(HttpServletRequest request) throws SerException {
        if (null != request && !ServletFileUpload.isMultipartContent(request)) {
            throw new SerException("上传表单不是multipart/form-data类型");
        }
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request; // 转换成多部分request
        return multiRequest.getFiles("file");
    }

    /**
     * 查看附件
     *
     * @param id 出车记录id
     * @version v1
     */
    @GetMapping("v1/findFiles/{id}")
    public Result findFiles(@PathVariable String id) throws ActException {
            //// TODO: 17-4-14 查看附件
            return ActResult.initialize("success!");
    }

    /**
     * 根据id查询出车记录
     *
     * @param id 出车记录id
     * @version v1
     */
    @GetMapping("v1/findDetail/{id}")
    public Result findDetail(@PathVariable String id) throws ActException {
        try {
            DispatchCarInfoVO vo = BeanTransform.copyProperties(dispatchCarInfoAPI.findDetail(id), DispatchCarInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核详情
     *
     * @param id 出车记录id
     * @version v1
     */
    @GetMapping("v1/findAudit/{id}")
    public Result findAudit(@PathVariable String id) throws ActException {
        try {
            List<DispatchCarInfoVO> vo = BeanTransform.copyProperties(dispatchCarInfoAPI.findAudit(id), DispatchCarInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 出车记录分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(DispatchCarInfoDTO dto) throws ActException {
        try {
            List<DispatchCarInfoVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.pageList(dto), DispatchCarInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}