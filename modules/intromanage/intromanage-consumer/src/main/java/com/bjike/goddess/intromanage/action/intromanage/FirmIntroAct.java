package com.bjike.goddess.intromanage.action.intromanage;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.business.api.BusinessRegisterAPI;
import com.bjike.goddess.business.bo.RegisterNaTypeCaBO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.api.FirmIntroAPI;
import com.bjike.goddess.intromanage.bo.FirmIntroBO;
import com.bjike.goddess.intromanage.dto.FirmIntroDTO;
import com.bjike.goddess.intromanage.to.FirmDisplayFieldTO;
import com.bjike.goddess.intromanage.to.FirmIntroTO;
import com.bjike.goddess.intromanage.to.GuidePermissionTO;
import com.bjike.goddess.intromanage.to.SiginManageDeleteFileTO;
import com.bjike.goddess.intromanage.vo.*;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
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
 * 公司简介
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:20 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("firmintro")
public class FirmIntroAct extends BaseFileAction{

    @Autowired
    private FirmIntroAPI firmIntroAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private BusinessRegisterAPI businessRegisterAPI;

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

            Boolean isHasPermission = firmIntroAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询公司简介
     *
     * @param id 公司简介唯一标识
     * @return class FirmIntroVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/firmintro/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            FirmIntroBO bo = firmIntroAPI.findById(id);
            //查询荣誉与资质
            List<HonorAndQualityVO> honorAndQualitieVOs = BeanTransform.copyProperties(bo.getHonorAndQualityBOS(), HonorAndQualityVO.class);
            //查询主业介绍
            List<MainBusinessIntroVO> mainBusinessIntroVOS = BeanTransform.copyProperties(bo.getMainBusinessIntroBOS(), MainBusinessIntroVO.class);
            //查询成功案例
            List<SuccessStoriesVO> successStoriesVOS = BeanTransform.copyProperties(bo.getSuccessStoriesBOS(), SuccessStoriesVO.class);
            //查询客户及合作伙伴
            List<CustomerAndPartnerVO> customerAndPartnerVOS = BeanTransform.copyProperties(bo.getCustomerAndPartnerBOS(), CustomerAndPartnerVO.class);
            //查询通讯途径
            List<CommunicationPathVO> communicationPathVOS = BeanTransform.copyProperties(bo.getCommunicationPathBOS(), CommunicationPathVO.class);

            FirmIntroVO vo = BeanTransform.copyProperties(bo, FirmIntroVO.class, request);
            vo.setHonorAndQualityVOS(honorAndQualitieVOs);
            vo.setMainBusinessIntroVOS(mainBusinessIntroVOS);
            vo.setSuccessStoriesVOS(successStoriesVOS);
            vo.setCustomerAndPartnerVOS(customerAndPartnerVOS);
            vo.setCommunicationPathVOS(communicationPathVOS);

            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 公司简介dto
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/count")
    public Result count(@Validated FirmIntroDTO dto, BindingResult result) throws ActException {
        try {
            Long count = firmIntroAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询公司简介
     *
     * @param dto 公司简介dto
     * @return class FirmIntroVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result list(@Validated FirmIntroDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<FirmIntroBO> boList = firmIntroAPI.list(dto);
            List<FirmIntroVO> voList = BeanTransform.copyProperties(boList, FirmIntroVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加公司简介
     *
     * @param to 公司简介to
     * @return class FirmIntroVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) FirmIntroTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            FirmIntroBO bo = firmIntroAPI.save(to);
            FirmIntroVO vo = BeanTransform.copyProperties(bo, FirmIntroVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除公司简介
     *
     * @param id 公司简介唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            firmIntroAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑公司简介
     *
     * @param to 公司简介to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) FirmIntroTO to, BindingResult result) throws ActException {
        try {
            firmIntroAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 设置公司简介显示的字段
     *
     * @param username 用户姓名数组
     * @param to       公司简介显示字段to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/setFirmDisplayField")
    public Result setFirmDisplayField(String[] username, @Validated(value = {ADD.class}) FirmDisplayFieldTO to, BindingResult result) throws ActException {
        try {
            firmIntroAPI.setFirmDisplayField(username, to);
            return new ActResult("setFirmDisplayField success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有的用户
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/findAllUser")
    public Result findAllUser() throws ActException {
        try {
            List<String> userName = new ArrayList<>();
            userName = firmIntroAPI.findallMonUser();
            return ActResult.initialize(userName);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 公司名称公司性质公司资金下拉值
     *
     * @throws ActException
     * @return class RegisterNaTypeCaVO
     * @version v1
     */
    @GetMapping("v1/findNaTyCa")
    public Result findNaTyCa() throws ActException {
        try {
            List<RegisterNaTypeCaBO> nameTypeCa = new ArrayList<>();
            if (moduleAPI.isCheck("business")) {
                nameTypeCa = businessRegisterAPI.findRegiNaTyCa();
            }
            return ActResult.initialize(BeanTransform.copyProperties(nameTypeCa, RegisterNaTypeCaVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地址下拉值
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findBussiness/address")
    public Result findBussinessAdd() throws ActException {
        try {
            List<String> address = new ArrayList<>();
            if (moduleAPI.isCheck("business")) {
                address = businessRegisterAPI.findAddress();
            }
            return ActResult.initialize(address);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 上传附件
     *
     * @des 审核项目签订与立项
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String paths = "/businessproject/siginmanage/" + id;
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
     * @param id 签订与立项id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /businessproject/id/....
            String path = "/businessproject/siginmanage/" + id;
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
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(SiginManageDeleteFileTO.TestDEL.class) SiginManageDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

    /**
     * 冻结
     *
     * @param id id
     * @des 根据id冻结公司简介
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            firmIntroAPI.congealFirmin(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 解冻
     *
     * @param id id
     * @des 根据id解冻公司简介
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id ) throws ActException {
        try {
            firmIntroAPI.thawFirmin(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @des 导出公司简介
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport( HttpServletResponse response) throws ActException {
        try {
            String fileName = "公司简介.xlsx";
            super.writeOutFile(response, firmIntroAPI.exportExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * excel模板下载
     *
     * @des 下载模板公司简介
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "公司简介模板.xlsx";
            super.writeOutFile(response, firmIntroAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }
}