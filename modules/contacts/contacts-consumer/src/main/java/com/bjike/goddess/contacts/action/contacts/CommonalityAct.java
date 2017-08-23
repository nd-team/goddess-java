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
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.dto.CommonalityDTO;
import com.bjike.goddess.contacts.excel.CommonalityExcel;
import com.bjike.goddess.contacts.to.CommonalityTO;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import com.bjike.goddess.contacts.vo.CommonalityVO;
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
 * 公共邮箱管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:45 ]
 * @Description: [ 公共邮箱管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("commonality")
public class CommonalityAct extends BaseFileAction{

    @Autowired
    private CommonalityAPI commonalityAPI;

    /**
     * 保存
     *
     * @param to 公共邮箱管理传输对象
     * @return class CommonalityVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CommonalityTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.save(to), CommonalityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 公共邮箱管理传输对象
     * @return class CommonalityVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) CommonalityTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.update(to), CommonalityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param to 公共邮箱管理传输对象
     * @return class CommonalityVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(CommonalityTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.delete(to), CommonalityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param to 公共邮箱管理传输对象
     * @return class CommonalityVO
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(CommonalityTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.congeal(to), CommonalityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param to 公共邮箱管理传输对象
     * @return class CommonalityVO
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(CommonalityTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.thaw(to), CommonalityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未冻结的公共邮箱
     *
     * @return class CommonalityVO
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.findThaw(), CommonalityVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表查询
     *
     * @param dto 公共邮箱管理数据传输对象
     * @return class CommonalityVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(CommonalityDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.maps(dto), CommonalityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据部门ID查询公共邮箱
     *
     * @param department 部门ID
     * @return class CommonalityVO
     * @version v1
     */
    @GetMapping("v1/findByDepartment")
    public Result findByDepartment(String department,HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.findByDepartment(department), CommonalityVO.class,request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id获取公共邮箱数据
     *
     * @param id 公共邮箱数据id
     * @return class CommonalityVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.getById(id), CommonalityVO.class));
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
            return ActResult.initialize(commonalityAPI.getTotal());
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

            Boolean isHasPermission = commonalityAPI.guidePermission(guidePermissionTO);
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
            List<CommonalityExcel> tos = ExcelUtil.excelToClazz(is, CommonalityExcel.class, excel);
            List<CommonalityTO> tocs = new ArrayList<>();
            for (CommonalityExcel str : tos) {
                CommonalityTO commonalityTO = BeanTransform.copyProperties(str, CommonalityTO.class);
                tocs.add(commonalityTO);
            }
            //注意序列化
            commonalityAPI.importExcel(tocs);
            return new ActResult("导入成功");
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
            String fileName = "公共邮箱管理导入模板.xlsx";
            super.writeOutFile(response, commonalityAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }



}