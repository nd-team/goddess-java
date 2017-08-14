package com.bjike.goddess.staffwelfare.action.staffwelfare;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.staffwelfare.api.HeadPortraitHatAPI;
import com.bjike.goddess.staffwelfare.dto.HeadPortraitHatDTO;
import com.bjike.goddess.staffwelfare.excel.SonPermissionObject;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfare.to.HeadPortraitHatTO;
import com.bjike.goddess.staffwelfare.vo.HeadPortraitHatVO;
import com.bjike.goddess.user.api.UserAPI;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 头像帽
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 04:00 ]
 * @Description: [ 头像帽 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("headportraithat")
public class HeadPortraitHatAct {

    @Autowired
    private HeadPortraitHatAPI headPortraitHatAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = headPortraitHatAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = headPortraitHatAPI.guidePermission(guidePermissionTO);
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
     * 新增祝福语
     *
     * @param to 祝福语
     * @return class HeadPortraitHatVO
     * @version v1
     */
//    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) HeadPortraitHatTO to, HttpServletRequest request, BindingResult bindingResult) throws ActException {
        try {
            String token = RpcContext.getContext().getAttachment("userToken");

            //文件上传
            try {
                List<MultipartFile> multipartFiles = this.getMultipartFile(request);
                Map<String, byte[]> map = new HashMap<>(multipartFiles.size());
                for (MultipartFile multipartFile : multipartFiles) {
                    byte[] bytes = IOUtils.toByteArray(multipartFile.getInputStream());
                    map.put(multipartFile.getOriginalFilename(), bytes);
                }
                to.setMap(map);
            }catch (IOException e){
                throw new ActException(e.getMessage());
            }

            HeadPortraitHatVO vo = BeanTransform.copyProperties(headPortraitHatAPI.addModel(to), HeadPortraitHatVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑祝福语
     *
     * @param to 祝福语
     * @return class HeadPortraitHatVO
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class)HeadPortraitHatTO to, BindingResult bindingResult) throws ActException {
        try {
            HeadPortraitHatVO vo = BeanTransform.copyProperties(headPortraitHatAPI.editModel(to), HeadPortraitHatVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除祝福语
     *
     * @param id 祝福语id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            headPortraitHatAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 祝福语分页查询
     *
     * @param dto 分页条件
     * @return class HeadPortraitHatVO
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(HeadPortraitHatDTO dto) throws ActException {
        try {
            List<HeadPortraitHatVO> voList = BeanTransform.copyProperties(headPortraitHatAPI.pageList(dto), HeadPortraitHatVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    //上传文件
    private List<MultipartFile> getMultipartFile(HttpServletRequest request) throws SerException {
        if (null != request && !ServletFileUpload.isMultipartContent(request)) {
            throw new SerException("上传表单不是multipart/form-data类型");
        }
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request; // 转换成多部分request
        return multiRequest.getFiles("file");
    }
}