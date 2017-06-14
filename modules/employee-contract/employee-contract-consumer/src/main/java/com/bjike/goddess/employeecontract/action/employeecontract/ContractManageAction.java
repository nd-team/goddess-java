package com.bjike.goddess.employeecontract.action.employeecontract;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.employeecontract.api.ContractManageAPI;
import com.bjike.goddess.employeecontract.dto.ContractManageDTO;
import com.bjike.goddess.employeecontract.to.ContractChangeTO;
import com.bjike.goddess.employeecontract.to.ContractInfoTO;
import com.bjike.goddess.employeecontract.to.ContractManageTO;
import com.bjike.goddess.employeecontract.to.ContractPersonalTO;
import com.bjike.goddess.employeecontract.vo.ContractChangeVO;
import com.bjike.goddess.employeecontract.vo.ContractInfoVO;
import com.bjike.goddess.employeecontract.vo.ContractManageVO;
import com.bjike.goddess.employeecontract.vo.ContractPersonalVO;
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
 * 合同管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 04:04 ]
 * @Description: [ 合同管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contractmanage")
public class ContractManageAction extends BaseFileAction {

    @Autowired
    private ContractManageAPI contractManageAPI;

    @Autowired
    private FileAPI fileAPI;

    /**
     * 数据录入
     *
     * @param to 合同管理传输对象
     * @return class ContractManageVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ContractManageTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractManageAPI.save(to), ContractManageVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改详细信息(查看详细编辑)
     *
     * @param to 合同管理传输对象
     * @return class ContractManageVO
     * @version v1
     */
    @PutMapping("v1/updateDetail/{id}")
    public Result updateDetail(@Validated(EDIT.class) ContractManageTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractManageAPI.updateDetail(to), ContractManageVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改合同管理
     *
     * @param to 合同信息传输对象
     * @return class ContractInfoVO
     * @version v1
     */
    @PutMapping("v1/info/update/{id}")
    public Result updateInfo(@Validated(EDIT.class) ContractInfoTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractManageAPI.updateInfo(to), ContractInfoVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改员工合同信息详细
     *
     * @param to 人员合同信息传输对象
     * @return class ContractPersonalVO
     * @version v1
     */
    @PutMapping("v1/personal/update/{id}")
    public Result updatePersonal(@Validated(EDIT.class) ContractPersonalTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractManageAPI.updatePersonal(to), ContractPersonalVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 确认解除合同
     *
     * @param id 合同管理数据id
     * @return class ContractInfoVO
     * @version v1
     */
    @PutMapping("v1/affirm/{id}")
    public Result affirm(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractManageAPI.affirm(id), ContractInfoVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 合同管理数据id
     * @return class ContractManageVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractManageAPI.delete(id), ContractManageVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取传输数据
     *
     * @param id 合同管理数据id
     * @return class ContractManageVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractManageAPI.getById(id), ContractManageVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 员工合同信息详细列表
     *
     * @param dto 合同管理数据传输对象
     * @return class ContractPersonalVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/personal/maps")
    public Result personalMaps(ContractManageDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractManageAPI.personalMaps(dto), ContractPersonalVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 合同管理列表
     *
     * @param dto 合同管理数据传输对象
     * @return class ContractInfoVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/info/maps")
    public Result infoMaps(ContractManageDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractManageAPI.infoMaps(dto), ContractInfoVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取员工合同信息详细列表总条数
     *
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/personal/getTotal")
    public Result getPersonalTotal() throws ActException {
        try {
            return ActResult.initialize(contractManageAPI.getPersonalTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取合同管理列表总条数
     *
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/info/getTotal")
    public Result getInfoTotal() throws ActException {
        try {
            return ActResult.initialize(contractManageAPI.getInfoTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未解除合同信息
     *
     * @return class ContractManageVO
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractManageAPI.findStatus(), ContractManageVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存合同变更信息
     *
     * @param to 合同变更详细传输对象
     * @return class ContractChangeVO
     * @version v1
     */
    @PostMapping("v1/saveChange/{id}")
    public Result saveChange(@Validated(ADD.class) ContractChangeTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractManageAPI.saveChange(to), ContractChangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @param id 合同管理数据id
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadEnclosure/{id}")
    public Result uploadEnclosure(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String path = "/employeecontract/contractmanage/" + id;
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id 合同管理数据id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String path = "/employeecontract/contractmanage/" + id;
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
     * @param paths 多文件信息路径
     * @version v1
     */
    @PostMapping("v1/deleteFile")
    public Result delFile(@RequestParam String[] paths, HttpServletRequest request) throws ActException {
        try {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), paths);
            return new ActResult("delFile success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}