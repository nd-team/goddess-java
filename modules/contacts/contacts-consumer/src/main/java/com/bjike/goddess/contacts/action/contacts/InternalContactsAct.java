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
import com.bjike.goddess.contacts.api.CommerceContactsAPI;
import com.bjike.goddess.contacts.api.ExternalContactsAPI;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.contacts.bo.*;
import com.bjike.goddess.contacts.dto.InternalContactsDTO;
import com.bjike.goddess.contacts.dto.SearchDTO;
import com.bjike.goddess.contacts.excel.InternalContactsExcel;
import com.bjike.goddess.contacts.excel.InternalContactsTestExcel;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import com.bjike.goddess.contacts.to.InternalContactsTO;
import com.bjike.goddess.contacts.vo.InternalContactsVO;
import com.bjike.goddess.contacts.vo.MobileCommerceContactsVO;
import com.bjike.goddess.contacts.vo.MobileExternalContactsVO;
import com.bjike.goddess.contacts.vo.MobileInternalContactsVO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.DepartmentPeopleBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private ExternalContactsAPI externalContactsAPI;
    @Autowired
    private CommerceContactsAPI commerceContactsAPI;

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
     * 根据姓名获取地区员工编号职位部门
     *
     * @version v1
     */
    @PostMapping("v1/getByName")
    public Result getByName(@RequestParam String name, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(internalContactsAPI.getByName(name));
        } catch (Exception e) {
            throw new ActException(e.getMessage());
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
     * 移动端获取根据姓名获取所有电话号码
     *
     * @return class PhoneNumberBO
     * @version v1
     */
    @GetMapping("v1/mobile/tel")
    public Result mobileGetTel() throws ActException {
        try {
            List<PhoneNumberBO> phoneNumberBOS = internalContactsAPI.mobileGetTel();
            return ActResult.initialize(phoneNumberBOS);
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

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/test")
    public Result test(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<InternalContactsTestExcel> tos = ExcelUtil.excelToClazz(is, InternalContactsTestExcel.class, excel);
            List<InternalContactsTO> tocs = new ArrayList<>();
            for (InternalContactsTestExcel str : tos) {
                InternalContactsTO internalContactsTO = BeanTransform.copyProperties(str, InternalContactsTO.class);
                internalContactsTO.setName(internalContactsTO.getUserId());
                tocs.add(internalContactsTO);
            }
            //注意序列化
            internalContactsAPI.test(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 发送邮件
     *
     * @version v1
     */
    @GetMapping("v1/checkEmail")
    public Result checkEmail() throws ActException {
        try {
            internalContactsAPI.checkEmail();
            return ActResult.initialize("成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 移动端从组织结构中获取所有部门下的人数
     *
     * @return class DepartmentPeopleBO
     * @version v1
     */
    @GetMapping("v1/mobile/people")
    public Result peopleByDepartment(HttpServletRequest request) throws ActException {
        try {
//            String userToken = request.getHeader(RpcCommon.USER_TOKEN).toString();
//            RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, userToken);
            List<DepartmentPeopleBO> list = departmentDetailAPI.peopleByDepartment();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 移动端从获取部门下的信息
     *
     * @param dep 部门
     * @return class MobileInternalContactsBO
     * @version v1
     */
    @GetMapping("v1/mobile/info")
    public Result mobileInfoByDepartment(String dep) throws ActException {
        try {
            List<MobileInternalContactsBO> list = internalContactsAPI.mobileInfoByDepartment(dep);
            return ActResult.initialize(list);
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

    /**
     * 获取所有年份
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/year")
    public Result year() throws ActException {
        try {
            List<Integer> list = new ArrayList<>();
            Integer year = LocalDate.now().getYear();
            for (int i = year - 5; i < year + 5; i++) {
                list.add(i);
            }
            return ActResult.initialize(list);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 手机端全局搜索
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/mobileSearch")
    public Result mobileSearch(SearchDTO dto) throws ActException {
        try {
            List<MobileSearchBO> mobileSearchBOS = internalContactsAPI.mobileSearch(dto);
            return ActResult.initialize(mobileSearchBOS);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 手机端根据id查询全局信息
     *
     * @version v1
     */
    @GetMapping("v1/mobileFindById/{id}")
    public Result mobileFindById(@PathVariable String id) throws ActException {
        try {
            MobileInternalContactsBO bo = internalContactsAPI.findByMobileID(id);
            if (null != bo) {
                return ActResult.initialize(BeanTransform.copyProperties(bo, MobileInternalContactsVO.class));
            }

            MobileExternalContactsBO bo1 = externalContactsAPI.findByMobileID(id);
            if (null != bo1) {
                return ActResult.initialize(BeanTransform.copyProperties(bo1, MobileExternalContactsVO.class));
            }
            MobileCommerceContactsBO bo2 = commerceContactsAPI.findByMobileID(id);
            if (null != bo2) {
                return ActResult.initialize(BeanTransform.copyProperties(bo2, MobileCommerceContactsVO.class));
            }
            return ActResult.initialize(null);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }


}