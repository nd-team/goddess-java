package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.DayPlanAPI;
import com.bjike.goddess.marketdevelopment.dto.DayPlanDTO;
import com.bjike.goddess.marketdevelopment.to.CollectTO;
import com.bjike.goddess.marketdevelopment.to.DayPlanTO;
import com.bjike.goddess.marketdevelopment.vo.DayPlanVO;
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
 * 天计划
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:08 ]
 * @Description: [ 天计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("dayplan")
public class DayPlanAct extends BaseFileAction {

    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private DayPlanAPI dayPlanAPI;


    /**
     * 列表
     *
     * @param dto 天计划数据传输对象
     * @return class DayPlanVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(DayPlanDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dayPlanAPI.maps(dto), DayPlanVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存天计划数据
     *
     * @param to 天计划传输对象
     * @return class DayPlanVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) DayPlanTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dayPlanAPI.save(to), DayPlanVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改天计划数据
     *
     * @param to 天计划传输对象
     * @return class DayPlanVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) DayPlanTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dayPlanAPI.update(to), DayPlanVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除天计划数据
     *
     * @param to 天计划传输对象
     * @return class DayPlanVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(DayPlanTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dayPlanAPI.delete(to), DayPlanVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据时间范围查询天计划数据
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return class DayPlanVO
     * @version v1
     */
    @GetMapping("v1/findByDateCycle")
    public Result findByDate(String start, String end, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dayPlanAPI.findByDate(start, end), DayPlanVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据时间查询天计划数据
     *
     * @param date 时间
     * @return class DayPlanVO
     * @version v1
     */
    @GetMapping("v1/findByDate")
    public Result findByDate(String date, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dayPlanAPI.findByDate(date), DayPlanVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取天计划数据
     *
     * @param id 天计划数据id
     * @return class DayPlanVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dayPlanAPI.getById(id), DayPlanVO.class, request));
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
            return ActResult.initialize(dayPlanAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 上传附件
     *
     * @param id 天计划数据id
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadEnclosure/{id}")
    public Result uploadEnclosure(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String path = "/marketdevelopment/dayplan/" + id;
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
     * @param id 天计划数据id
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String path = "/marketdevelopment/dayplan/" + id;
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

    /**
     * 导出
     *
     * @param to 导出查询条件传输对象
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcel(CollectTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "天计划.xlsx";
            super.writeOutFile(response, dayPlanAPI.exportExcel(to), fileName);
            return new ActResult("导出成功");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }
}