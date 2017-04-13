package com.bjike.goddess.archive.action.archive;

import com.bjike.goddess.archive.api.ArchiveDetailAPI;
import com.bjike.goddess.archive.dto.ArchiveDetailDTO;
import com.bjike.goddess.archive.to.ArchiveDetailTO;
import com.bjike.goddess.archive.vo.ArchiveDetailVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class ArchiveDetailAction {

    @Autowired
    private ArchiveDetailAPI archiveDetailAPI;

    /**
     * 保存
     *
     * @param to 档案明细传输对象
     * @return class ArchiveDetailVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(ArchiveDetailTO to) throws ActException {
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
    public Result update(ArchiveDetailTO to) throws ActException {
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
    public Result delete(String id) throws ActException {
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

}