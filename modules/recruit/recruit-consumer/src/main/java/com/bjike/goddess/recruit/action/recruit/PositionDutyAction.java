package com.bjike.goddess.recruit.action.recruit;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.BusinessCourseAPI;
import com.bjike.goddess.marketdevelopment.bo.BusinessCourseBO;
import com.bjike.goddess.marketdevelopment.vo.BusinessCourseVO;
import com.bjike.goddess.marketdevelopment.vo.BusinessTypeChoiceVO;
import com.bjike.goddess.organize.api.HierarchyAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.HierarchyBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.vo.HierarchyVO;
import com.bjike.goddess.organize.vo.PositionDetailVO;
import com.bjike.goddess.recruit.api.PositionDutyAPI;
import com.bjike.goddess.recruit.bo.PositionDutyBO;
import com.bjike.goddess.recruit.dto.PositionDutyDTO;
import com.bjike.goddess.recruit.to.DeleteFileTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.PositionDutyTO;
import com.bjike.goddess.recruit.vo.PositionDutyVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司岗位分类岗位职责
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 09:31 ]
 * @Description: [ 公司岗位分类岗位职责 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("positionduty")
public class PositionDutyAction extends BaseFileAction {
    @Autowired
    private PositionDutyAPI positionDutyAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private HierarchyAPI hierarchyAPI;
    @Autowired
    private BusinessCourseAPI businessCourseAPI;
//    @Autowired
//    private BusinessTypeAPI businessTypeAPI;
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

            Boolean isHasPermission = positionDutyAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询公司岗位分类岗位职责
     *
     * @param id 公司岗位分类岗位职责唯一标识
     * @return class PositionDutyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/duty/{id}")
    public Result duty(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            PositionDutyBO bo = positionDutyAPI.getId(id);
            PositionDutyVO vo = BeanTransform.copyProperties(bo, PositionDutyVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 公司岗位分类岗位职责dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(PositionDutyDTO dto, BindingResult result) throws ActException {
        try {
            Long count = positionDutyAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 公司岗位分类岗位职责dto
     * @return class PositionDutyVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result list(PositionDutyDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            String userToken = RpcContext.getContext().getAttachment("userToken");
            List<PositionDutyBO> boList = positionDutyAPI.list(dto);
            List<PositionDutyVO> voList = BeanTransform.copyProperties(boList, PositionDutyVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加公司岗位分类岗位职责
     *
     * @param to 公司岗位分类岗位职责to信息
     * @return class PositionDutyVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) PositionDutyTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            PositionDutyBO bo = positionDutyAPI.save(to);
            PositionDutyVO vo = BeanTransform.copyProperties(bo, PositionDutyVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑公司岗位分类岗位职责
     *
     * @param to 公司岗位分类岗位职责to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) PositionDutyTO to, BindingResult result) throws ActException {
        try {
            PositionDutyBO bo = positionDutyAPI.update(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, PositionDutyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除公司岗位分类岗位职责
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/remove/{id}")
    public Result remove(@PathVariable String id) throws ActException {
        try {
            positionDutyAPI.remove(id);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻公司岗位分类岗位职责
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            positionDutyAPI.thaw(id);
            return ActResult.initialize("thaw success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结公司岗位分类岗位职责
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            positionDutyAPI.congeal(id);
            return ActResult.initialize("congeal success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询正常数据的业务方向科目数据
     *
     * @return class BusinessCourseVO
     * @version v1
     */
    @GetMapping("v1/subject")
    public Result subject(HttpServletRequest request) throws ActException {
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            List<BusinessCourseBO> businessCourseBOS = new ArrayList<>();
            if (moduleAPI.isCheck("marketdevelopment")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                businessCourseBOS = businessCourseAPI.findThaw();
            }
            return ActResult.initialize(BeanTransform.copyProperties(businessCourseBOS, BusinessCourseVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询正常数据的业务类型方向数据
     *
     * @return class BusinessTypeChoiceVO
     * @version v1
     */
    @GetMapping("v1/direction")
    public Result direction(HttpServletRequest request) throws ActException {
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
//            List<BusinessTypeBO> businessTypeBOS = new ArrayList<>();
            if (moduleAPI.isCheck("marketdevelopment")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
//                businessTypeBOS = businessTypeAPI.findThaw();
            }
            return ActResult.initialize(BeanTransform.copyProperties(null, BusinessTypeChoiceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取正常状态的体系信息
     *
     * @return class HierarchyVO
     * @version v1
     */
    @GetMapping("v1/hierarchy")
    public Result hierarchy(HttpServletRequest request) throws ActException {
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            List<HierarchyBO> hierarchyBOS = new ArrayList<>();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                hierarchyBOS = hierarchyAPI.findStatus();
            }
            return ActResult.initialize(BeanTransform.copyProperties(hierarchyBOS, HierarchyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询正常状态的岗位详细
     *
     * @return class PositionDetailVO
     * @version v1
     */
    @GetMapping("v1/position")
    public Result position(HttpServletRequest request) throws ActException {
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            List<PositionDetailBO> list = new ArrayList<>();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                list = positionDetailAPI.findStatus();
            }
            return ActResult.initialize(BeanTransform.copyProperties(list, PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询用户
     *
     * @return class UserVO
     * @version v1
     */
    @GetMapping("v1/user")
    public Result user(HttpServletRequest request) throws ActException {
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            List<UserBO> userBOS = new ArrayList<>();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                userBOS = positionDetailUserAPI.findUserListInOrgan();
            }
            return ActResult.initialize(BeanTransform.copyProperties(userBOS, UserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @des 招标信息
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
     * @param id 招标信息id
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
     * @param deleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(DeleteFileTO.TestDEL.class) DeleteFileTO deleteFileTO, HttpServletRequest request) throws SerException {
        if (null != deleteFileTO.getPaths() && deleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), deleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }


}