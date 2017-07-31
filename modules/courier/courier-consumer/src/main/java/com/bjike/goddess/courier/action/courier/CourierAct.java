package com.bjike.goddess.courier.action.courier;

import com.bjike.goddess.accommodation.api.RentalAPI;
import com.bjike.goddess.checkhost.api.DormitoryInfoAPI;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.courier.api.CourierAPI;
import com.bjike.goddess.courier.api.CourierCompanyAPI;
import com.bjike.goddess.courier.bo.CourierBO;
import com.bjike.goddess.courier.bo.CourierCountBO;
import com.bjike.goddess.courier.dto.CourierDTO;
import com.bjike.goddess.courier.to.CourierTO;
import com.bjike.goddess.courier.to.DeleteFileTO;
import com.bjike.goddess.courier.to.GuidePermissionTO;
import com.bjike.goddess.courier.vo.CourierCountVO;
import com.bjike.goddess.courier.vo.CourierVO;
import com.bjike.goddess.courier.vo.SonPermissionObject;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

/**
 * 快递收发
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-28 10:25 ]
 * @Description: [ 快递收发 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("courier")
public class CourierAct extends BaseFileAction {

    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private CourierAPI courierAPI;
    @Autowired
    private DormitoryInfoAPI dormitoryInfoAPI;
    @Autowired
    private RentalAPI rentalAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private CourierCompanyAPI courierCompanyAPI;
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

            List<SonPermissionObject> hasPermissionList = courierAPI.sonPermission();
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

            Boolean isHasPermission = courierAPI.guidePermission(guidePermissionTO);
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
            String path = "/courier/courier/" + id;
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
            String path = "/courier/courier/" + id;
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
     * 添加
     *
     * @param to      快递收发信息
     * @param request 请求对象
     * @return class CourierVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) CourierTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            CourierBO bo = courierAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, CourierVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to      收发快递信息
     * @param request 请求对象
     * @return class CourierVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) CourierTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            CourierBO bo = courierAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, CourierVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param dto     快递收发分页信息
     * @param request 请求对象
     * @return class CourierVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CourierDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<CourierBO> list = courierAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, CourierVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      快递收发id
     * @param request 请求对象
     * @return class CourierVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/courier/{id}")
    public Result courier(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            CourierBO bo = courierAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, CourierVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 快递收发id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            courierAPI.delete(id);
            return new ActResult("删除成功！");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 日汇总
     *
     * @param dto dto
     * @return class CourierCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/dayCount")
    public Result dayCount(@Validated(CourierDTO.DAY.class) CourierDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<CourierCountBO> list = courierAPI.dayCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, CourierCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 周汇总
     *
     * @param dto dto
     * @return class CourierCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/weekCount")
    public Result weekCount(@Validated(CourierDTO.WEEK.class) CourierDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<CourierCountBO> list = courierAPI.weekCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, CourierCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 月汇总
     *
     * @param dto dto
     * @return class CourierCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/monthCount")
    public Result monthCount(@Validated(CourierDTO.MONTH.class) CourierDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<CourierCountBO> list = courierAPI.monthCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, CourierCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 年汇总
     *
     * @param dto dto
     * @return class CourierCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/yearCount")
    public Result yearCount(@Validated(CourierDTO.YEAR.class) CourierDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<CourierCountBO> list = courierAPI.yearCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, CourierCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 通过项目组查找员工姓名
//     *
//     * @param request   请求对象
//     * @param groupName 项目组
//     * @return class OtherVO
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/findNameByGroup/{groupName}")
//    public Result findNameByGroup(HttpServletRequest request, @PathVariable String[] groupName) throws ActException {
//        try {
//            List<String> list = courierAPI.findNameByGroup(groupName);
//            List<OtherBO> boList = new ArrayList<OtherBO>(0);
//            for (String s : list) {
//                OtherBO otherBO = new OtherBO();
//                otherBO.setName(s);
//                boList.add(otherBO);
//            }
//            return ActResult.initialize(BeanTransform.copyProperties(boList, OtherVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 通过部门查找员工姓名
//     *
//     * @param request        请求对象
//     * @param departmentName 部门名
//     * @return class OtherVO
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/findNameByDepartment/{departmentName}")
//    public Result findNameByDepartment(HttpServletRequest request, @PathVariable String[] departmentName) throws ActException {
//        try {
//            List<String> list = courierAPI.findNameByDepartment(departmentName);
//            List<OtherBO> boList = new ArrayList<OtherBO>(0);
//            for (String s : list) {
//                OtherBO otherBO = new OtherBO();
//                otherBO.setName(s);
//                boList.add(otherBO);
//            }
//            return ActResult.initialize(BeanTransform.copyProperties(boList, OtherVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 查找所有的员工名
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findAllNames")
    public Result findAllNames() throws ActException {
        try {
            Set<String> set = new HashSet<>();
            List<UserBO> list = userAPI.findAllUser();
            for (UserBO userBO : list) {
                if (Status.THAW.equals(userBO.getStatus())) {
                    set.add(userBO.getUsername());
                }
            }
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有部门
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findDepartments")
    public Result findDepartments() throws ActException {
        try {
            Set<String> set = new HashSet<>();
            List<DepartmentDetailBO> list = departmentDetailAPI.findStatus();
            for (DepartmentDetailBO departmentDetailBO : list) {
                set.add(departmentDetailBO.getDepartment());
            }
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有的快递公司
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allCompany")
    public Result allCompany() throws ActException {
        try {
            Set<String> set = courierCompanyAPI.allCompany();
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找上条记录的快递费总额
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/lastCourierSum")
    public Result lastCourierSum() throws ActException {
        try {
            return ActResult.initialize(courierAPI.lastCourierSum());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有的收件地和寄件地
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findAllAreas")
    public Result findAllAreas() throws ActException {
        try {
            Set<String> dormitoryAddress = dormitoryInfoAPI.allAddress();
            Set<String> address = rentalAPI.allAddress();
            Set<String> set = new HashSet<>();
            set.addAll(dormitoryAddress);
            set.addAll(address);
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有年份
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allYear")
    public Result allYear() throws ActException {
        try {
            Set<Integer> set = courierAPI.allYear();
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有月份
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allMonth")
    public Result allMonth() throws ActException {
        try {
            Set<Integer> set = new HashSet<>();
            for (int i=1;i<=12;i++){
                set.add(i);
            }
            return ActResult.initialize(set);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总记录数
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CourierDTO dto) throws ActException {
        try {
            return ActResult.initialize(courierAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据寄、收件地获取负责人联系方式
     *
     * @param dormitoryAddress 寄、收件地址
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findContact/{dormitoryAddress}")
    public Result findContact(@PathVariable String dormitoryAddress) throws ActException {
        try {
            return ActResult.initialize(dormitoryInfoAPI.findContact(dormitoryAddress));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取当前月有几周
     *
     * @param year  年份
     * @param month 月份
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findWeek/{year}/{month}")
    public Result findWeek(@PathVariable Integer year, @PathVariable Integer month) throws ActException {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= weekNum; i++) {
                list.add(i);
            }
            return ActResult.initialize(list);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }
}