package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.DemandAnalysisAPI;
import com.bjike.goddess.marketdevelopment.dto.DemandAnalysisDTO;
import com.bjike.goddess.marketdevelopment.to.DemandAnalysisTO;
import com.bjike.goddess.marketdevelopment.vo.DemandAnalysisVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 市场需求分析
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:10 ]
 * @Description: [ 市场需求分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("demandanalysis")
public class DemandAnalysisAct {

    @Autowired
    private DemandAnalysisAPI demandAnalysisAPI;


    /**
     * 列表
     *
     * @param dto 市场需求分析数据传输对象
     * @return class DemandAnalysisVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(DemandAnalysisDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(demandAnalysisAPI.maps(dto), DemandAnalysisVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存市场需求分析数据
     *
     * @param to 市场需求分析传输对象
     * @return class DemandAnalysisVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) DemandAnalysisTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(demandAnalysisAPI.save(to), DemandAnalysisVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改市场需求分析数据
     *
     * @param to 市场需求分析传输对象
     * @return class DemandAnalysisVO
     * @version v1
     */
    @PutMapping("v1/udpate/{id}")
    public Result update(@Validated(EDIT.class) DemandAnalysisTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(demandAnalysisAPI.update(to), DemandAnalysisVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除市场需求分析数据
     *
     * @param to 市场需求分析传输对象
     * @return class DemandAnalysisVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(DemandAnalysisTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(demandAnalysisAPI.delete(to), DemandAnalysisVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据业务类型查询市场需求分析数据
     *
     * @param type 业务类型
     * @return class DemandAnalysisVO
     * @version v1
     */
    @GetMapping("v1/findByType")
    public Result findByType(String type) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(demandAnalysisAPI.findByType(type), DemandAnalysisVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据业务方向科目查询市场需求分析数据
     *
     * @param course 业务方向科目
     * @return class DemandAnalysisVO
     * @version v1
     */
    @GetMapping("v1/findByCourse")
    public Result findByCourse(String course) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(demandAnalysisAPI.findByCourse(course), DemandAnalysisVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据业务类型和方向科目查询市场需求分析数据
     *
     * @param type   业务类型
     * @param course 业务方向科目
     * @return class DemandAnalysisVO
     * @version v1
     */
    @GetMapping("v1/findByCourseType")
    public Result findByCourseType(String type, String course) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(demandAnalysisAPI.findByCourseType(type, course), DemandAnalysisVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id获取市场需求分析数据
     *
     * @param id 市场需求分析数据id
     * @return class DemandAnalysisVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(demandAnalysisAPI.getById(id), DemandAnalysisVO.class));
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
            return ActResult.initialize(demandAnalysisAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}