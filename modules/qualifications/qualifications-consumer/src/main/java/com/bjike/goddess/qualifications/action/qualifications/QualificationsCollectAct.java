package com.bjike.goddess.qualifications.action.qualifications;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.file.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.api.QualificationsCollectAPI;
import com.bjike.goddess.qualifications.bo.QualificationsCollectBO;
import com.bjike.goddess.qualifications.dto.QualificationsCollectDTO;
import com.bjike.goddess.qualifications.to.QualificationsCollectFilterTO;
import com.bjike.goddess.qualifications.to.QualificationsCollectTO;
import com.bjike.goddess.qualifications.vo.QualificationsCollectVO;
import com.bjike.goddess.storage.api.FileAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 资质办理进度汇总
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-20 10:39 ]
 * @Description: [ 资质办理进度汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("qualificationscollect")
public class QualificationsCollectAct extends BaseFileAction {

    @Autowired
    private QualificationsCollectAPI qualificationsCollectAPI;
    @Autowired
    private FileAPI fileAPI;


    /**
     * 保存
     *
     * @param to 资质办理进度汇总传输对象
     * @return class QualificationsCollectVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) QualificationsCollectTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsCollectAPI.save(to), QualificationsCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 资质办理进度汇总传输对象
     * @return class QualificationsCollectVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) QualificationsCollectTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsCollectAPI.update(to), QualificationsCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 资质办理进度汇总数据id
     * @return class QualificationsCollectVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsCollectAPI.delete(id), QualificationsCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据条件查询
     *
     * @param to 资质办理进度汇总查询条件传输对象
     * @return class QualificationsCollectVO
     * @version v1
     */
    @GetMapping("v1/findByFilter")
    public Result findByFilter(QualificationsCollectFilterTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsCollectAPI.findByFilter(to), QualificationsCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 资质办理进度汇总数据传输对象
     * @return class QualificationsCollectVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(QualificationsCollectDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsCollectAPI.maps(dto), QualificationsCollectVO.class));
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
            return ActResult.initialize(qualificationsCollectAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取数据
     *
     * @param id 数据id
     * @return class QualificationsCollectVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsCollectAPI.getById(id), QualificationsCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @param request 上传请求
     * @param id      资质办理进度汇总数据id
     * @version v1
     */
    @PostMapping("v1/uploadEnclosure/{id}")
    public Result uploadEnclosure(HttpServletRequest request, @PathVariable String id) throws ActException {
        try {
            QualificationsCollectBO collectBO = qualificationsCollectAPI.getById(id);
            if (null == collectBO)
                throw new SerException("数据id错误");
            StringBuilder path = new StringBuilder(0);
            if (StringUtils.isNotBlank(collectBO.getCertificate()))
                path.append("/").append(collectBO.getCertificate());
            if (StringUtils.isNotBlank(collectBO.getCertificate()))
                path.append("/").append(collectBO.getCompany());

            fileAPI.upload(this.getInputStreams(request, path.toString()));
            return new ActResult("上传成功");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

}