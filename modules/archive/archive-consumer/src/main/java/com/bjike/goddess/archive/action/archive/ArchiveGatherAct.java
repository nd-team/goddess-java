package com.bjike.goddess.archive.action.archive;

import com.bjike.goddess.archive.api.ArchiveGatherAPI;
import com.bjike.goddess.archive.dto.ArchiveGatherDTO;
import com.bjike.goddess.archive.to.ArchiveGatherTO;
import com.bjike.goddess.archive.vo.ArchiveGatherVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.file.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.storage.api.FileAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 档案收集
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:22 ]
 * @Description: [ 档案收集 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("archivegather")
public class ArchiveGatherAct extends BaseFileAction {

    @Autowired
    private ArchiveGatherAPI archiveGatherAPI;
    @Autowired
    private FileAPI fileAPI;


    /**
     * 保存
     *
     * @param to 档案收集传输对象
     * @return class ArchiveGatherVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ArchiveGatherTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveGatherAPI.save(to), ArchiveGatherVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 档案收集传输对象
     * @return class ArchiveGatherVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) ArchiveGatherTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveGatherAPI.update(to), ArchiveGatherVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 档案收集数据id
     * @return class ArchiveGatherVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveGatherAPI.delete(id), ArchiveGatherVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 档案收集数据传输对象
     * @return class ArchiveGatherVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(ArchiveGatherDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveGatherAPI.maps(dto), ArchiveGatherVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @param request  上传请求
     * @param username 员工姓名(创建对应文件夹使用)
     * @return class Result
     * @version v1
     */
    @PostMapping("v1/uploadEnclosure")
    public Result uploadEnclosure(HttpServletRequest request, String username) throws ActException {
        try {

            String path = "/" + username;
            fileAPI.upload(this.getInputStreams(request, path));
            return new ActResult("上传成功");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }
}