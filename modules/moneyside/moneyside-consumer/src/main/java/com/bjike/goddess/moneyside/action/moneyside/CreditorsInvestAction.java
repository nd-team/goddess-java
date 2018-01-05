package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.CreditorsInvestAPI;
import com.bjike.goddess.moneyside.bo.CreditorsInvestBO;
import com.bjike.goddess.moneyside.dto.CreditorsInvestDTO;
import com.bjike.goddess.moneyside.to.CreditorsInvestTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import com.bjike.goddess.moneyside.to.MoneySideDeleteFileTO;
import com.bjike.goddess.moneyside.vo.CreditorsInvestVO;
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
import java.io.InputStream;
import java.util.List;

/**
 * 投资条件-债权投资
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:33 ]
 * @Description: [ 投资条件-债权投资 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("creditorsinvest")
public class CreditorsInvestAction extends BaseFileAction {
    @Autowired
    private CreditorsInvestAPI creditorsInvestAPI;
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

            Boolean isHasPermission = creditorsInvestAPI.guidePermission(guidePermissionTO);
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
     * 债权投资列表总条数
     *
     * @param creditorsInvestDTO 债权投资dto
     * @des 获取所有债权投资总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CreditorsInvestDTO creditorsInvestDTO) throws ActException {
        try {
            Long count = creditorsInvestAPI.countCreditorsInvest(creditorsInvestDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个债权投资
     *
     * @param id
     * @return class CreditorsInvestVO
     * @des 获取一个债权投资
     * @version v1
     */
    @GetMapping("v1/creditors/{id}")
    public Result creditors(@PathVariable String id) throws ActException {
        try {
            CreditorsInvestBO creditorsInvestBO = creditorsInvestAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(creditorsInvestBO, CreditorsInvestVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 债权投资列表
     *
     * @param creditorsInvestDTO 债权投资dto
     * @return class CreditorsInvestVO
     * @des 获取所有债权投资
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CreditorsInvestDTO creditorsInvestDTO, HttpServletRequest request) throws ActException {
        try {
            List<CreditorsInvestVO> creditorsInvestVOS = BeanTransform.copyProperties
                    (creditorsInvestAPI.findListCreditorsInvest(creditorsInvestDTO), CreditorsInvestVO.class, request);
            return ActResult.initialize(creditorsInvestVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加债权投资
     *
     * @param creditorsInvestTO 债权投资数据to
     * @return class CreditorsInvestVO
     * @des 添加债权投资
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) CreditorsInvestTO creditorsInvestTO, BindingResult bindingResult) throws ActException {
        try {
            CreditorsInvestBO creditorsInvestBO = creditorsInvestAPI.insertCreditorsInvest(creditorsInvestTO);
            return ActResult.initialize(creditorsInvestBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑债权投资
     *
     * @param creditorsInvestTO 债权投资数据to
     * @return class CreditorsInvestVO
     * @des 编辑债权投资
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) CreditorsInvestTO creditorsInvestTO, BindingResult bindingResult) throws ActException {
        try {
            CreditorsInvestBO creditorsInvestBO = creditorsInvestAPI.editCreditorsInvest(creditorsInvestTO);
            return ActResult.initialize(creditorsInvestBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除债权投资
     *
     * @param id 用户id
     * @des 根据用户id删除债权投资记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            creditorsInvestAPI.removeCreditorsInvest(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @des 债权投资
     * @version v1
     */
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String paths = "/" + id;
            List<InputStream> inputStreams = super.getInputStreams(request, paths);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success!");
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
            // /moneyside/id/....
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
     * @param moneySideDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(MoneySideDeleteFileTO.TestDEL.class) MoneySideDeleteFileTO moneySideDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != moneySideDeleteFileTO.getPaths() && moneySideDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), moneySideDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }


}