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
import com.bjike.goddess.contacts.api.ExternalContactsAPI;
import com.bjike.goddess.contacts.bo.MobileExternalContactsBO;
import com.bjike.goddess.contacts.dto.ExternalContactsDTO;
import com.bjike.goddess.contacts.excel.ExternalContactsExcel;
import com.bjike.goddess.contacts.to.ExternalContactsTO;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import com.bjike.goddess.contacts.vo.ExternalContactsVO;
import com.bjike.goddess.contacts.vo.MobileExternalContactsVO;
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
 * 外部通讯录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:26 ]
 * @Description: [ 外部通讯录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("externalcontacts")
public class ExternalContactsAct extends BaseFileAction {

    @Autowired
    private ExternalContactsAPI externalContactsAPI;

    /**
     * 保存
     *
     * @param to 外部通讯录传输对象
     * @return class ExternalContactsVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ExternalContactsTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(externalContactsAPI.save(to), ExternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 外部通讯录传输对象
     * @return class ExternalContactsVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) ExternalContactsTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(externalContactsAPI.update(to), ExternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param to 外部通讯录传输对象
     * @return class ExternalContactsVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(ExternalContactsTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(externalContactsAPI.delete(to), ExternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据地区查询
     *
     * @param area 地区
     * @return class ExternalContactsVO
     * @version v1
     */
    @GetMapping("v1/findByArea")
    public Result findByArea(String area, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(externalContactsAPI.findByArea(area), ExternalContactsVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表查询
     *
     * @param dto 外部通讯录数据传输对象
     * @return class ExternalContactsVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(ExternalContactsDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(externalContactsAPI.maps(dto), ExternalContactsVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id获取外部通讯录数据
     *
     * @param id 外部通讯录数据id
     * @return class ExternalContactsVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(externalContactsAPI.getById(id), ExternalContactsVO.class));
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
            return ActResult.initialize(externalContactsAPI.getTotal());
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

            Boolean isHasPermission = externalContactsAPI.guidePermission(guidePermissionTO);
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
            List<ExternalContactsExcel> tos = ExcelUtil.excelToClazz(is, ExternalContactsExcel.class, excel);
            List<ExternalContactsTO> tocs = new ArrayList<>();
            for (ExternalContactsExcel str : tos) {
                ExternalContactsTO externalContactsTO = BeanTransform.copyProperties(str, ExternalContactsTO.class);
                tocs.add(externalContactsTO);
            }
            //注意序列化
            externalContactsAPI.importExcel(tocs);
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
            String fileName = "外部通讯录导入模板.xlsx";
            super.writeOutFile(response, externalContactsAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 移动端获取列表
     *
     * @return class MobileExternalContactsVO
     * @version v1
     */
    @GetMapping("v1/mobile/list")
    public Result mobileList(ExternalContactsDTO dto) throws ActException {
        try {
            List<MobileExternalContactsBO> mobileExternalContactsBOs = externalContactsAPI.mobileList(dto);
            return ActResult.initialize(BeanTransform.copyProperties(mobileExternalContactsBOs, MobileExternalContactsVO.class));
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
    public Result getMobileTotal(ExternalContactsDTO dto) throws ActException {
        try {
            return ActResult.initialize(externalContactsAPI.getMobileTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id 获取移动端数据
     *
     * @return calss MobileExternalContactsVO
     * @version v1
     */
    @GetMapping("v1/mobile/findByID/{id}")
    public Result findByMobileID(@PathVariable String id) throws ActException {
        try {
            MobileExternalContactsBO bo = externalContactsAPI.findByMobileID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, MobileExternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}