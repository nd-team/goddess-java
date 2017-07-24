
package com.bjike.goddess.accommodation.action.accommodation;

import com.bjike.goddess.accommodation.api.RentalAPI;
import com.bjike.goddess.accommodation.bo.RentalBO;
import com.bjike.goddess.accommodation.dto.RentalDTO;
import com.bjike.goddess.accommodation.to.GuidePermissionTO;
import com.bjike.goddess.accommodation.to.RentalDeleteFileTO;
import com.bjike.goddess.accommodation.to.RentalTO;
import com.bjike.goddess.accommodation.vo.CollectVO;
import com.bjike.goddess.accommodation.vo.RentalVO;
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
 * 租房信息业务
 *
 * @Author: [xiazhili]
 * @Date: [17-3-14]
 * @Description: [租房信息业务]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */

@RestController
@RequestMapping("rental")
public class RentalAct extends BaseFileAction {
    @Autowired
    private RentalAPI rentalAPI;
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

            Boolean isHasPermission = rentalAPI.guidePermission(guidePermissionTO);
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
     * 租房信息列表总条数
     *
     * @param rentalDTO 租房信息记录dto
     * @des 获取所有租房信息
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(RentalDTO rentalDTO) throws ActException {
        try {
            Long count = rentalAPI.count(rentalDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个租房信息
     *
     * @param id
     * @return class RentalVO
     * @des 获取一个租房信息
     * @version v1
     */
    @GetMapping("v1/rental/{id}")
    public Result rental(@PathVariable String id) throws ActException {
        try {
            RentalBO bo = rentalAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RentalVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 租房信息列表
     *
     * @param rentalDTO 租房信息dto
     * @return class RentalVO
     * @des 获取租房信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(RentalDTO rentalDTO, HttpServletRequest request) throws ActException {
        try {
            List<RentalVO> rentalVOS = BeanTransform.copyProperties(
                    rentalAPI.findListRental(rentalDTO), RentalVO.class, request);
            return ActResult.initialize(rentalVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加租房信息
     *
     * @param rentalTO 租房信息to
     * @return class RentalVO
     * @des 添加租房信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) RentalTO rentalTO, BindingResult bindingResult) throws ActException {
        try {
            RentalBO rentalBO = rentalAPI.insertRental(rentalTO);
            return ActResult.initialize(rentalBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑租房信息
     *
     * @param rentalTO 租房信息数据bo
     * @return class RentalVO
     * @des 编辑租房信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) RentalTO rentalTO, BindingResult bindingResult) throws ActException {
        try {
            RentalBO rentalBO = rentalAPI.editRental(rentalTO);
            return ActResult.initialize(rentalBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据用户id删除租房信息记录
     *
     * @param id 用户id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            rentalAPI.removeRental(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param areas 地区
     * @return class CollectVO
     * @des 根据地区汇总
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(String[] areas) throws ActException {
        try {
            List<CollectVO> collectVOS = BeanTransform.copyProperties(rentalAPI.collect(areas), CollectVO.class);
            return ActResult.initialize(collectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @des 房租信息
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
     * @param id 房租信息id
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
     * @param rentalDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(RentalDeleteFileTO.TestDEL.class) RentalDeleteFileTO rentalDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != rentalDeleteFileTO.getPaths() && rentalDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), rentalDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

    /**
     * 导出excel
     *
     * @param dto 租房信息
     * @des 导出租房信息
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(RentalDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "租房信息.xlsx";
            super.writeOutFile(response, rentalAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 获取地区
     *
     * @des 获取地区集合
     * @version v1
     */
    @GetMapping("v1/areas")
    public Result areas() throws ActException {
        try {
            List<String> areasList = rentalAPI.getArea();
            return ActResult.initialize(areasList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}


