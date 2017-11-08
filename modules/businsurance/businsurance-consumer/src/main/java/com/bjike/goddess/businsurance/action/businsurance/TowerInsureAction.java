package com.bjike.goddess.businsurance.action.businsurance;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.businsurance.api.TowerInsureAPI;
import com.bjike.goddess.businsurance.bo.TowerInsureBO;
import com.bjike.goddess.businsurance.dto.TowerInsureDTO;
import com.bjike.goddess.businsurance.excel.SonPermissionObject;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.businsurance.to.SiginManageDeleteFileTO;
import com.bjike.goddess.businsurance.to.TowerInsureTO;
import com.bjike.goddess.businsurance.vo.TowerInsureVO;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 塔工意外险信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 09:30 ]
 * @Description: [ 塔工意外险信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("towerinsure")
public class TowerInsureAction extends BaseFileAction {


    @Autowired
    private TowerInsureAPI towerInsureAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private ModuleAPI moduleAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
//            if (moduleAPI.isCheck("organize")) {
                SonPermissionObject obj = new SonPermissionObject();
                obj.setName("cuspermission");
                obj.setDescribesion("设置");
                Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
                if (!isHasPermission) {
                    //int code, String msg
                    obj.setFlag(false);
                } else {
                    obj.setFlag(true);
                }
                list.add(obj);
//            }
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = towerInsureAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

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

            Boolean isHasPermission = towerInsureAPI.guidePermission(guidePermissionTO);
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
     * 塔工意外险列表总条数
     *
     * @param towerInsureDTO 塔工意外险信息dto
     * @des 获取所有塔工意外险信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(TowerInsureDTO towerInsureDTO) throws ActException {
        try {
            Long count = towerInsureAPI.countTowerInsure(towerInsureDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 塔工意外险列表
     *
     * @param towerInsureDTO 塔工意外险信息dto
     * @return class TowerInsureVO
     * @des 获取所有塔工意外险信息
     * @version v1
     */
    @GetMapping("v1/listTowerInsure")
    public Result findList(TowerInsureDTO towerInsureDTO, BindingResult bindingResult) throws ActException {
        try {
            List<TowerInsureVO> towerInsureVOList = BeanTransform.copyProperties(
                    towerInsureAPI.listTowerInsure(towerInsureDTO), TowerInsureVO.class, true);
            return ActResult.initialize(towerInsureVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加塔工意外险
     *
     * @param towerInsureTO 塔工意外险基本信息数据to
     * @return class TowerInsureVO
     * @des 添加塔工意外险
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(TowerInsureTO.TestAddAndEdit.class) TowerInsureTO towerInsureTO, BindingResult bindingResult) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.addTowerInsure(towerInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑塔工意外险
     *
     * @param towerInsureTO 塔工意外险基本信息数据bo
     * @return class TowerInsureVO
     * @des 编辑塔工意外险
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(TowerInsureTO.TestAddAndEdit.class) TowerInsureTO towerInsureTO) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.editTowerInsure(towerInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除塔工意外险信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            towerInsureAPI.deleteTowerInsure(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 编辑转账授权信息
     *
     * @param towerInsureTO 塔工意外险基本信息数据bo
     * @return class TowerInsureVO
     * @des 编辑转账授权信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editAccount")
    public Result editAccount(@Validated TowerInsureTO towerInsureTO) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.editAccount(towerInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑要约内容
     *
     * @param towerInsureTO 塔工意外险基本信息数据bo
     * @return class TowerInsureVO
     * @des 编辑要约内容
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editContext")
    public Result editContext(@Validated TowerInsureTO towerInsureTO) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.editContext(towerInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑投保人基本信息
     *
     * @param towerInsureTO 塔工意外险基本信息数据bo
     * @return class TowerInsureVO
     * @des 编辑投保人基本信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/applicant")
    public Result applicant(@Validated TowerInsureTO towerInsureTO) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.applicant(towerInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑被投保人基本信息
     *
     * @param towerInsureTO 塔工意外险基本信息数据bo
     * @return class TowerInsureVO
     * @des 编辑被投保人基本信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editInsurePerson")
    public Result editInsurePerson(@Validated TowerInsureTO towerInsureTO) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.editInsurePerson(towerInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑受益人基本信息
     *
     * @param towerInsureTO 塔工意外险基本信息数据bo
     * @return class TowerInsureVO
     * @des 编辑受益人基本信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editBenefit")
    public Result editBenefit(@Validated TowerInsureTO towerInsureTO) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.editBenefit(towerInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑销售机构信息
     *
     * @param towerInsureTO 塔工意外险基本信息数据bo
     * @return class TowerInsureVO
     * @des 编辑销售机构信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editSaleOrgan")
    public Result editSaleOrgan(@Validated TowerInsureTO towerInsureTO) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.editSaleOrgan(towerInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 一个塔工意外险
     *
     * @param id id
     * @return class TowerInsureVO
     * @des 根据id查看详细
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.getTowerInsure(id);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 上传附件
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/businsurance/towerInsure/" + id;
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
     * @param id id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/businsurance/towerInsure/" + id;
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
     * @param path 文件路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            FileInfo fileInfo = new FileInfo();
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            fileInfo.setPath(path);
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
     * 获取所有用户
     *
     * @return class UserVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/users")
    public Result users(HttpServletRequest request) throws ActException {
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                List<UserBO> list = positionDetailUserAPI.findUserListInOrgan();
                return ActResult.initialize(BeanTransform.copyProperties(list, UserVO.class, request));
            }
            return null;
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }
}