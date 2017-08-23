package com.bjike.goddess.bidding.action.bidding;

import com.bjike.goddess.bidding.api.BiddingAnswerQuestionsAPI;
import com.bjike.goddess.bidding.bo.BiddingAnswerQuestionsBO;
import com.bjike.goddess.bidding.dto.BiddingAnswerQuestionsDTO;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.to.BiddingAnswerQuestionsTO;
import com.bjike.goddess.bidding.to.BiddingDeleteFileTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.bidding.vo.BiddingAnswerQuestionsVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
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
import java.util.List;

/**
 * 投标答疑问题记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:54:11.904 ]
 * @Description: [ 投标答疑问题记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("biddinganswerquestions")
public class BiddingAnswerQuestionsAction extends BaseFileAction{
    @Autowired
    private BiddingAnswerQuestionsAPI biddingAnswerQuestionsAPI;
    @Autowired
    private FileAPI fileAPI;
    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = biddingAnswerQuestionsAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 投标答疑问题记录列表总条数
     *
     * @param biddingAnswerQuestionsDTO 投标答疑问题记录dto
     * @des 获取所有投标答疑问题记录
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BiddingAnswerQuestionsDTO biddingAnswerQuestionsDTO) throws ActException {
        try {
            Long count = biddingAnswerQuestionsAPI.countBiddingAnswerQuestions(biddingAnswerQuestionsDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个投标答疑问题记录
     *
     * @param id
     * @return class BiddingAnswerQuestionsVO
     * @des 获取一个投标答疑问题记录
     * @version v1
     */
    @GetMapping("v1/answer/{id}")
    public Result answer(@PathVariable String id) throws ActException {
        try {
            BiddingAnswerQuestionsBO biddingAnswerQuestionsBO = biddingAnswerQuestionsAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(biddingAnswerQuestionsBO, BiddingAnswerQuestionsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 投标答疑问题列表
     *
     * @param biddingAnswerQuestionsDTO 投标答疑问题记录dto
     * @return class BiddingAnswerQuestionsVO
     * @des 获取所有投标答疑问题记录
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BiddingAnswerQuestionsDTO biddingAnswerQuestionsDTO, HttpServletRequest request) throws ActException {
        try {
            List<BiddingAnswerQuestionsVO> biddingAnswerQuestionsVOS = BeanTransform.copyProperties(
                    biddingAnswerQuestionsAPI.findListBiddingAnswerQuestions(biddingAnswerQuestionsDTO), BiddingAnswerQuestionsVO.class,request);
            return ActResult.initialize(biddingAnswerQuestionsVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加投标答疑问题记录
     *
     * @param biddingAnswerQuestionsTO 投标答疑问题记录to
     * @return class BiddingAnswerQuestionsVO
     * @des 添加投标答疑问题记录
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) BiddingAnswerQuestionsTO biddingAnswerQuestionsTO, BindingResult bindingResult) throws ActException {
        try {
            BiddingAnswerQuestionsBO biddingAnswerQuestionsBO = biddingAnswerQuestionsAPI.insertBiddingAnswerQuestions(biddingAnswerQuestionsTO);
            return ActResult.initialize(BeanTransform.copyProperties(biddingAnswerQuestionsBO, BiddingAnswerQuestionsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑投标答疑问题记录
     *
     * @param biddingAnswerQuestionsTO 投标答疑问题记录数据to
     * @return class BiddingAnswerQuestionsVO
     * @des 添加投标答疑问题记录
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) BiddingAnswerQuestionsTO biddingAnswerQuestionsTO, BindingResult bindingResult) throws ActException {
        try {
            BiddingAnswerQuestionsBO biddingAnswerQuestionsBO = biddingAnswerQuestionsAPI.editBiddingAnswerQuestions(biddingAnswerQuestionsTO);
            return ActResult.initialize(BeanTransform.copyProperties(biddingAnswerQuestionsBO, BiddingAnswerQuestionsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除投标答疑问题记录
     *
     * @param id 用户id
     * @des 根据用户id删除投标答疑问题记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            biddingAnswerQuestionsAPI.removeBiddingAnswerQuestions(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @des 投标答疑问题记录
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
     * @param id 投标答疑问题记录id
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
     * @param biddingDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(BiddingDeleteFileTO.TestDEL.class) BiddingDeleteFileTO biddingDeleteFileTO, HttpServletRequest request) throws SerException {
        if(null != biddingDeleteFileTO.getPaths() && biddingDeleteFileTO.getPaths().length>=0 ){
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(),biddingDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }
    /**
     * 导出excel
     *
     * @param dto 投标答疑问题记录
     * @des 导出投标答疑问题记录
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(BiddingAnswerQuestionsDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "投标答疑问题记录.xlsx";
            super.writeOutFile(response, biddingAnswerQuestionsAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


}