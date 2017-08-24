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
import com.bjike.goddess.moneyside.api.InvestTransferAPI;
import com.bjike.goddess.moneyside.bo.InvestFormBO;
import com.bjike.goddess.moneyside.bo.InvestTransferBO;
import com.bjike.goddess.moneyside.dto.InvestFormDTO;
import com.bjike.goddess.moneyside.dto.InvestTransferDTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import com.bjike.goddess.moneyside.to.InvestFormTO;
import com.bjike.goddess.moneyside.to.InvestTransferTO;
import com.bjike.goddess.moneyside.to.MoneySideDeleteFileTO;
import com.bjike.goddess.moneyside.vo.InvestFormVO;
import com.bjike.goddess.moneyside.vo.InvestTransferVO;
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
import java.util.List;

/**
 * 投资转让
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 03:09 ]
 * @Description: [ 投资转让 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("investtransfer")
public class InvestTransferAction extends BaseFileAction{
    @Autowired
    private InvestTransferAPI investTransferAPI;
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

            Boolean isHasPermission = investTransferAPI.guidePermission(guidePermissionTO);
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
     * 投资转让列表总条数
     *
     * @param investTransferDTO 投资转让dto
     * @des 获取所有投资转让总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(InvestTransferDTO investTransferDTO) throws ActException {
        try {
            Long count = investTransferAPI.countInvestTransfer(investTransferDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个投资转让
     *
     * @param id
     * @return class InvestTransferVO
     * @des 获取一个投资转让
     * @version v1
     */
    @GetMapping("v1/form/{id}")
    public Result form(@PathVariable String id) throws ActException {
        try {
            InvestTransferBO investTransferBO = investTransferAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(investTransferBO, InvestTransferVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 投资转让列表
     *
     * @param investTransferDTO 投资转让dto
     * @return class InvestTransferVO
     * @des 获取所有投资转让
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(InvestTransferDTO investTransferDTO, HttpServletRequest request) throws ActException {
        try {
            List<InvestTransferVO> investTransferVOS = BeanTransform.copyProperties
                    (investTransferAPI.findListInvestTransfer(investTransferDTO), InvestTransferVO.class, request);
            return ActResult.initialize(investTransferVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加投资转让
     *
     * @param investTransferTO 投资转让数据to
     * @return class InvestTransferVO
     * @des 添加投资转让
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) InvestTransferTO investTransferTO, BindingResult bindingResult) throws ActException {
        try {
            InvestTransferBO investTransferBO = investTransferAPI.insertInvestTransfer(investTransferTO);
            return ActResult.initialize(investTransferBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑投资转让
     *
     * @param investTransferTO 投资转让数据to
     * @return class InvestTransferVO
     * @des 编辑投资转让
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) InvestTransferTO investTransferTO, BindingResult bindingResult) throws ActException {
        try {
            InvestTransferBO investTransferBO = investTransferAPI.editInvestTransfer(investTransferTO);
            return ActResult.initialize(investTransferBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除投资转让
     *
     * @param id 用户id
     * @des 根据用户id删除投资转让记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            investTransferAPI.removeInvestTransfer(id);
            return new ActResult("delete success");
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
            String paths = "/moneyside/equityinvest/" + id;
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
            // /moneyside/id/....
            String path = "/moneyside/equityinvest/" + id;
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


    /**
     * 查询所有投资转让人
     * @return class UserVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/find/user")
    public Result findUser() throws ActException{
        try {
            List<UserBO> bos = investTransferAPI.findUserListInOrgan();
            List<UserVO> vos = BeanTransform.copyProperties(bos,UserVO.class);
            return ActResult.initialize(vos);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


}