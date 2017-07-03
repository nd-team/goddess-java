package com.bjike.goddess.bidding.action.bidding;

import com.bjike.goddess.bidding.api.TenderInfoAPI;
import com.bjike.goddess.bidding.bo.TenderInfoBO;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.dto.TenderInfoDTO;
import com.bjike.goddess.bidding.to.BiddingDeleteFileTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.bidding.to.TenderInfoTO;
import com.bjike.goddess.bidding.vo.TenderInfoVO;
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
 * 标书资料
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:05:05.315 ]
 * @Description: [ 标书资料 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("tenderinfo")
public class TenderInfoAction extends BaseFileAction{
    @Autowired
    private TenderInfoAPI tenderInfoAPI;
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

            Boolean isHasPermission = tenderInfoAPI.guidePermission(guidePermissionTO);
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
     * 标书资料列表总条数
     *
     * @param tenderInfoDTO 标书资料dto
     * @des 获取所有标书资料
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(TenderInfoDTO tenderInfoDTO) throws ActException {
        try {
            Long count = tenderInfoAPI.countTenderInfo(tenderInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个标书资料
     *
     * @param id
     * @return class TenderInfoVO
     * @des 获取一个标书资料
     * @version v1
     */
    @GetMapping("v1/info/{id}")
    public Result info(@PathVariable String id) throws ActException {
        try {
            TenderInfoBO tenderInfoBO = tenderInfoAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(tenderInfoBO, TenderInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 标书资料列表
     *
     * @param tenderInfoDTO 标书资料dto
     * @return class TenderInfoVO
     * @des 获取所有标书资料
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(TenderInfoDTO tenderInfoDTO, HttpServletRequest request) throws ActException {
        try {
            List<TenderInfoVO> tenderInfoVOS = BeanTransform.copyProperties(
                    tenderInfoAPI.findListTenderInfo(tenderInfoDTO), TenderInfoVO.class, request);
            return ActResult.initialize(tenderInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加标书资料
     *
     * @param tenderInfoTO 标书资料数据to
     * @return class TenderInfoVO
     * @des 添加标书资料
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) TenderInfoTO tenderInfoTO, BindingResult bindingResult) throws ActException {
        try {
            TenderInfoBO tenderInfoBO = tenderInfoAPI.insertTenderInfo(tenderInfoTO);
            return ActResult.initialize(BeanTransform.copyProperties(tenderInfoBO, TenderInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑标书资料
     *
     * @param tenderInfoTO 标书资料数据to
     * @return class TenderInfoVO
     * @des 编辑标书资料
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) TenderInfoTO tenderInfoTO, BindingResult bindingResult) throws ActException {
        try {
            TenderInfoBO tenderInfoBO = tenderInfoAPI.editTenderInfo(tenderInfoTO);
            return ActResult.initialize(BeanTransform.copyProperties(tenderInfoBO, TenderInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除标书资料
     *
     * @param id 用户id
     * @des 根据用户id删除标书资料记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteTenderInfo(@PathVariable String id) throws ActException {
        try {
            tenderInfoAPI.removeTenderInfo(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @des 标书资料
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
     * @param id 标书资料id
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
     * @param dto 标书资料
     * @des 导出标书资料
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(TenderInfoDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "标书资料.xlsx";
            super.writeOutFile(response, tenderInfoAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

}