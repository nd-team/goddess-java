package com.bjike.goddess.contractware.action.contractware;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.api.HousingContractAPI;
import com.bjike.goddess.contractware.bo.HousingContractBO;
import com.bjike.goddess.contractware.dto.HousingContractDTO;
import com.bjike.goddess.contractware.to.ContractwareDeleteFileTO;
import com.bjike.goddess.contractware.to.HousingContractTO;
import com.bjike.goddess.contractware.vo.HousingContractVO;
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
 * 房屋合同
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:42 ]
 * @Description: [ 房屋合同 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("housingcontract")
public class HousingContractAction  extends BaseFileAction{
    @Autowired
    private HousingContractAPI housingContractAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 房屋合同列表总条数
     *
     * @param housingContractDTO 房屋合同dto
     * @des 获取所有房屋合同总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(HousingContractDTO housingContractDTO) throws ActException {
        try {
            Long count = housingContractAPI.countHousingContract(housingContractDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个房屋合同
     *
     * @param id
     * @return class HousingContractVO
     * @des 获取一个房屋合同
     * @version v1
     */
    @GetMapping("v1/housing/{id}")
    public Result housing(@PathVariable String id) throws ActException {
        try {
            HousingContractBO housingContractBO = housingContractAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(housingContractBO, HousingContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 房屋合同列表
     *
     * @param housingContractDTO 房屋合同dto
     * @return class HousingContractVO
     * @des 获取所有房屋合同
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(HousingContractDTO housingContractDTO, HttpServletRequest request) throws ActException {
        try {
            List<HousingContractVO> housingContractVOS = BeanTransform.copyProperties
                    (housingContractAPI.findListHousingContract(housingContractDTO), HousingContractVO.class, request);
            return ActResult.initialize(housingContractVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加房屋合同
     *
     * @param housingContractTO 房屋合同数据to
     * @return class HousingContractVO
     * @des 添加房屋合同
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) HousingContractTO housingContractTO, BindingResult bindingResult) throws ActException {
        try {
            HousingContractBO housingContractBO = housingContractAPI.insertHousingContract(housingContractTO);
            return ActResult.initialize(housingContractBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑房屋合同
     *
     * @param housingContractTO 房屋合同数据to
     * @return class HousingContractVO
     * @des 编辑房屋合同
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) HousingContractTO housingContractTO, BindingResult bindingResult) throws ActException {
        try {
            HousingContractBO housingContractBO = housingContractAPI.editHousingContract(housingContractTO);
            return ActResult.initialize(housingContractBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除房屋合同
     *
     * @param id 用户id
     * @des 根据用户id删除房屋合同记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            housingContractAPI.removeHousingContract(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 上传附件
     *
     * @des 合同保管
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
     * @param id 合同保管id
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
     * @param contractwareDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(ContractwareDeleteFileTO.TestDEL.class) ContractwareDeleteFileTO contractwareDeleteFileTO, HttpServletRequest request) throws SerException {
        if(null != contractwareDeleteFileTO.getPaths() && contractwareDeleteFileTO.getPaths().length>=0 ){
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(),contractwareDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }



}