package com.bjike.goddess.archive.action.archive;

import com.bjike.goddess.archive.api.ArchiveDetailAPI;
import com.bjike.goddess.archive.dto.ArchiveDetailDTO;
import com.bjike.goddess.archive.to.ArchiveDetailTO;
import com.bjike.goddess.archive.vo.ArchiveDetailVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.storage.api.FileAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 档案明细
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:05 ]
 * @Description: [ 档案明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("archivedetail")
public class ArchiveDetailAct extends BaseFileAction {

    @Autowired
    private ArchiveDetailAPI archiveDetailAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 保存
     *
     * @param to 档案明细传输对象
     * @return class ArchiveDetailVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ArchiveDetailTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveDetailAPI.save(to), ArchiveDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 档案明细传输对象
     * @return class ArchiveDetailVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) ArchiveDetailTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveDetailAPI.update(to), ArchiveDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 档案明细数据id
     * @return class ArchiveDetailVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveDetailAPI.delete(id), ArchiveDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据姓名查询档案明细
     *
     * @param username 姓名
     * @return class ArchiveDetailVO
     * @version v1
     */
    @GetMapping("v1/findByUsername")
    public Result findByUsername(String username) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveDetailAPI.findByUsername(username), ArchiveDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 档案明细数据传输对象
     * @return class ArchiveDetailVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(ArchiveDetailDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveDetailAPI.maps(dto), ArchiveDetailVO.class));
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

    /**
     * 根据id获取档案明细数据
     *
     * @param id 档案明细数据id
     * @return class ArchiveDetailVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveDetailAPI.getById(id), ArchiveDetailVO.class, request));
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
            return ActResult.initialize(archiveDetailAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}