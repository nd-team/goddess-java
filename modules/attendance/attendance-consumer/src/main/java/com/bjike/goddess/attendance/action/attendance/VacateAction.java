package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.VacateAPI;
import com.bjike.goddess.attendance.bo.DayReportCountBO;
import com.bjike.goddess.attendance.bo.VacateBO;
import com.bjike.goddess.attendance.bo.VacateCountBO;
import com.bjike.goddess.attendance.dto.DayReportDTO;
import com.bjike.goddess.attendance.dto.VacateDTO;
import com.bjike.goddess.attendance.to.DeleteFileTO;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.VacateTO;
import com.bjike.goddess.attendance.vo.DayReportCountVO;
import com.bjike.goddess.attendance.vo.SonPermissionObject;
import com.bjike.goddess.attendance.vo.VacateCountVO;
import com.bjike.goddess.attendance.vo.VacateVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionUserDetailAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.enums.FileType;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.user.api.UserAPI;
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
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 请假管理
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 05:15 ]
 * @Description: [ 请假管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("vacate")
public class VacateAction extends BaseFileAction {
    @Autowired
    private VacateAPI vacateAPI;
    @Autowired
    private PositionUserDetailAPI positionUserDetailAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
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

            List<SonPermissionObject> hasPermissionList = vacateAPI.sonPermission();
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

            Boolean isHasPermission = vacateAPI.guidePermission(guidePermissionTO);
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
     * 获取请假天数
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/get/time")
    public Result getTime(@Validated(VacateTO.GETTIME.class) VacateTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(vacateAPI.getTime(to));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) VacateTO to, BindingResult result) throws ActException {
        try {
            vacateAPI.save(to);
            return new ActResult("添加成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 补录
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/fill")
    public Result fill(@Validated(ADD.class) VacateTO to, BindingResult result) throws ActException {
        try {
            vacateAPI.fill(to);
            return new ActResult("补录成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @return class VacateVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(VacateDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<VacateBO> list = vacateAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, VacateVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id id
     * @return class VacateVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/vacate/{id}")
    public Result vacate(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            VacateBO list = vacateAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(list, VacateVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            vacateAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总条数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result vacate(VacateDTO dto) throws ActException {
        try {
            return ActResult.initialize(vacateAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取请假人的主送人
     *
     * @param name 请假人
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/mains/{name}")
    public Result mains(@PathVariable String name) throws ActException {
        try {
            return ActResult.initialize(positionUserDetailAPI.findMains(name));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取请假人的抄送人
     *
     * @param name 请假人
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/carbons/{name}")
    public Result carbons(@PathVariable String name) throws ActException {
        try {
            return ActResult.initialize(positionUserDetailAPI.findCarbons(name));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核列表
     *
     * @return class VacateVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/audit/list")
    public Result auditList(VacateDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<VacateBO> auditList = vacateAPI.auditList(dto);
            return ActResult.initialize(BeanTransform.copyProperties(auditList, VacateVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核列表总条数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/audit/list/count")
    public Result auditList(VacateDTO dto) throws ActException {
        try {
            return ActResult.initialize(vacateAPI.auditListCount(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/audit")
    public Result audit(@Validated(EDIT.class) VacateTO to, BindingResult result) throws ActException {
        try {
            vacateAPI.audit(to);
            return new ActResult("审核成功");
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
            String path = "/attendance/vacate" + id;
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
     * @param id uuid
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/attendance/vacate" + id;
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
     * 移动端图片
     *
     * @param id uuid
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/phone/photo/{id}")
    public Result photosPhone(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(photos(id, request));
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

    /**
     * 获取uuid
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/uuid")
    public Result uuid() throws ActException {
        try {
            return ActResult.initialize(UUID.randomUUID().toString());
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取当前用户
     *
     * @return class UserVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/user")
    public Result user(HttpServletRequest request) throws ActException {
        try {
            UserBO bo = userAPI.currentUser();
            return ActResult.initialize(BeanTransform.copyProperties(bo, UserVO.class, request));
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有地区
     *
     * @return class AreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areas")
    public Result areas(HttpServletRequest request) throws ActException {
        try {
            List<AreaBO> list = departmentDetailAPI.findArea();
            return ActResult.initialize(BeanTransform.copyProperties(list, AreaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有部门
     *
     * @return class DepartmentDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/departs")
    public Result departs(HttpServletRequest request) throws ActException {
        try {
            List<DepartmentDetailBO> list = departmentDetailAPI.findStatus();
            return ActResult.initialize(BeanTransform.copyProperties(list, DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有岗位
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/positions")
    public Result positions() throws ActException {
        try {
            List<String> list = positionDetailAPI.positionNames();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    //    /**
//     * 图片附件
//     *
//     * @param id uuid
//     * @throws SerException
//     */
    private List<FileVO> photos(String id, HttpServletRequest request) throws SerException {
        List<FileVO> list = new ArrayList<>();
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/attendance/vacate" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            //只返回图片
            if (null != files) {
                list = files.stream().filter(fileVO -> FileType.BMP.equals(fileVO.getFileType()) || FileType.PNG.equals(fileVO.getFileType()) || FileType.GIF.equals(fileVO.getFileType()) || FileType.JPG.equals(fileVO.getFileType()) || FileType.JPEG.equals(fileVO.getFileType())).collect(Collectors.toList());
            }
            return list;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    /**
     * 移动端列表
     *
     * @return class VacateVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list/phone")
    public Result listPhone(VacateDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<VacateBO> list = vacateAPI.listPhone(dto);
            for (VacateBO bo : list) {
                List<FileVO> photos = photos(bo.getUuid(), request);
                bo.setPhotos(photos.stream().map(FileVO::getPath).collect(Collectors.toList()));
            }
            return ActResult.initialize(BeanTransform.copyProperties(list, VacateVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找(移动端)
     *
     * @param id id
     * @return class VacateVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/vacate/phone/{id}")
    public Result findByIDPhone(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            VacateBO list = vacateAPI.findByIDPhone(id);
            List<FileVO> photos = photos(list.getUuid(), request);
            list.setPhotos(photos.stream().map(FileVO::getPath).collect(Collectors.toList()));
            return ActResult.initialize(BeanTransform.copyProperties(list, VacateVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 移动端审核列表
     *
     * @return class VacateVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/audit/list/phone")
    public Result auditListPhone(VacateDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<VacateBO> auditList = vacateAPI.auditListPhone(dto);
            for (VacateBO bo : auditList) {
                List<FileVO> photos = photos(bo.getUuid(), request);
                bo.setPhotos(photos.stream().map(FileVO::getPath).collect(Collectors.toList()));
            }
            return ActResult.initialize(BeanTransform.copyProperties(auditList, VacateVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 检测某人是否有审核权限（移动端）
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/check/permission")
    public Result checkPermission() throws ActException {
        try {
            String name=userAPI.currentUser().getUsername();
            boolean flag = false;
            if ("admin".equalsIgnoreCase(name)){
                flag = true;
            }
            PositionDetailBO detailBO = positionUserDetailAPI.getPosition(name);
            if (null != detailBO) {
                if ("管理层".equals(detailBO.getArrangementName()) || "决策层".equals(detailBO.getArrangementName())) {
                    flag = true;
                }
            }
            return ActResult.initialize(flag);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 请假汇总
     *
     * @param dto dto
     * @return class VacateCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/vacate/count")
    public Result vacateCount(@Validated({VacateDTO.COUNT.class}) VacateDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            VacateCountBO list = vacateAPI.vacateCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, VacateCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}