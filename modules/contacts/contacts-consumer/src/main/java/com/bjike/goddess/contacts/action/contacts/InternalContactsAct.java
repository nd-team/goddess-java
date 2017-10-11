package com.bjike.goddess.contacts.action.contacts;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.contacts.bo.MobileInternalContactsBO;
import com.bjike.goddess.contacts.bo.NameAndIdBO;
import com.bjike.goddess.contacts.dto.InternalContactsDTO;
import com.bjike.goddess.contacts.excel.InternalContactsExcel;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import com.bjike.goddess.contacts.to.InternalContactsTO;
import com.bjike.goddess.contacts.vo.InternalContactsVO;
import com.bjike.goddess.contacts.vo.MobileInternalContactsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 内部通讯录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:08 ]
 * @Description: [ 内部通讯录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("internalcontacts")
public class InternalContactsAct extends BaseFileAction {

    @Autowired
    private InternalContactsAPI internalContactsAPI;

    /**
     * 保存
     *
     * @param to 内部通讯录传输对象
     * @return class InternalContactsVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) InternalContactsTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(internalContactsAPI.save(to), InternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 内部通讯录传输对象
     * @return class InternalContactsVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) InternalContactsTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(internalContactsAPI.update(to), InternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param to 内部通讯录传输对象
     * @return class InternalContactsVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(InternalContactsTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(internalContactsAPI.delete(to), InternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询邮箱不为空数据
     *
     * @return class InternalContactsVO
     * @version v1
     */
    @GetMapping("v1/findEmail")
    public Result findEmailNotNull(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(internalContactsAPI.findEmailNotNull(), InternalContactsVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据用户ID查询通讯录
     *
     * @param user_id 用户ID
     * @return class InternalContactsVO
     * @version v1
     */
    @GetMapping("v1/findByUser")
    public Result findByUser(String user_id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(internalContactsAPI.findByUser(user_id), InternalContactsVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表数据
     *
     * @param dto 内部通讯录数据传输对象
     * @return class InternalContactsVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(InternalContactsDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(internalContactsAPI.maps(dto), InternalContactsVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id获取内部通讯录数据
     *
     * @param id 内部通讯录数据id
     * @return class InternalContactsVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(internalContactsAPI.getById(id), InternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(internalContactsAPI.getTotal());
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

            Boolean isHasPermission = internalContactsAPI.guidePermission(guidePermissionTO);
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
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<InternalContactsExcel> tos = ExcelUtil.excelToClazz(is, InternalContactsExcel.class, excel);
            List<InternalContactsTO> tocs = new ArrayList<>();
            for (InternalContactsExcel str : tos) {
                InternalContactsTO internalContactsTO = BeanTransform.copyProperties(str, InternalContactsTO.class);
                tocs.add(internalContactsTO);
            }
            //注意序列化
            internalContactsAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 检测
     *
     * @des 自动删除离职员工通讯录
     * @version v1
     */
    @GetMapping("v1/checkDimissionInfo")
    public Result checkDimissionInfo() throws ActException {
        try {
            internalContactsAPI.checkDimissionInfo();
            return ActResult.initialize("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获得入职员工的id和姓名
     *
     * @return class NameAndIdBO
     * @des 获得入职员工的姓名
     * @version v1
     */
    @GetMapping("v1/getUserName")
    public Result getUserName() throws ActException {
        try {
            List<NameAndIdBO> list = internalContactsAPI.getUserName();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * excel模板下载
     *
     * @des 下载模板商务通讯录
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "内部通讯录导入模板.xlsx";
            super.writeOutFile(response, internalContactsAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 根据姓名获取邮箱
     *
     * @version v1
     */
    @PostMapping("v1/getEmail")
    public Result getEmail(String[] names, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(internalContactsAPI.getEmails(names));
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 移动端获取列表
     *
     * @return class MobileInternalContactsVO
     * @version v1
     */
    @GetMapping("v1/mobile/list")
    public Result mobileList(InternalContactsDTO dto) throws ActException {
        try {
            List<MobileInternalContactsBO> mobileInternalContactsBOs = internalContactsAPI.mobileList(dto);
            return ActResult.initialize(BeanTransform.copyProperties(mobileInternalContactsBOs, MobileInternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 移动端总条数
     *
     * @version v1
     */
    @GetMapping("v1/mobile/total")
    public Result getMobileTotal(InternalContactsDTO dto) throws ActException {
        try {
            return ActResult.initialize(internalContactsAPI.getMobileTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取移动端数据
     *
     * @return class MobileInternalContactsVO
     * @version v1
     */
    @GetMapping("v1/mobile/findByID/{id}")
    public Result findByMobileID(@PathVariable String id) throws ActException {
        try {
            MobileInternalContactsBO bo = internalContactsAPI.findByMobileID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, MobileInternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 导入Excel
//     *
//     * @param request 注入HttpServletRequest对象
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/test")
//    public Result test(HttpServletRequest request) throws ActException {
//        try {
//            List<InputStream> inputStreams = super.getInputStreams(request);
//            InputStream is = inputStreams.get(1);
//            Excel excel = new Excel(0, 1);
//            List<InternalContactsExcel> tos = ExcelUtil.excelToClazz(is, InternalContactsExcel.class, excel);
//            List<InternalContactsTO> tocs = new ArrayList<>();
//            for (InternalContactsExcel str : tos) {
//                InternalContactsTO internalContactsTO = BeanTransform.copyProperties(str, InternalContactsTO.class);
//                tocs.add(internalContactsTO);
//            }
//            //注意序列化
//            internalContactsAPI.test(tocs);
//            return new ActResult("导入成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

}