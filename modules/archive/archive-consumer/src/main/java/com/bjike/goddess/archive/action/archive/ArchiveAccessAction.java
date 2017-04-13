package com.bjike.goddess.archive.action.archive;

import com.bjike.goddess.archive.api.ArchiveAccessAPI;
import com.bjike.goddess.archive.dto.ArchiveAccessDTO;
import com.bjike.goddess.archive.service.ArchiveAccessSer;
import com.bjike.goddess.archive.to.AccessAuditTO;
import com.bjike.goddess.archive.to.ArchiveAccessTO;
import com.bjike.goddess.archive.vo.ArchiveAccessVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 档案调阅
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:03 ]
 * @Description: [ 档案调阅 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("archiveaccess")
public class ArchiveAccessAction {

    @Autowired
    private ArchiveAccessAPI archiveAccessAPI;

    /**
     * 保存
     *
     * @param to 档案调阅传输对象
     * @return class ArchiveAccessVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(ArchiveAccessTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveAccessAPI.save(to), ArchiveAccessVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 档案调阅传输对象
     * @return class ArchiveAccessVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(ArchiveAccessTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveAccessAPI.update(to), ArchiveAccessVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 档案调阅数据id
     * @return class ArchiveAccessVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveAccessAPI.delete(id), ArchiveAccessVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param to 档案调阅传输对象
     * @return class ArchiveAccessVO
     * @version v1
     */
    @PutMapping("v1/audit/{id}")
    public Result audit(AccessAuditTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveAccessAPI.audit(to), ArchiveAccessVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 档案调阅数据传输对象
     * @return class ArchiveAccessVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(ArchiveAccessDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveAccessAPI.maps(dto), ArchiveAccessVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}