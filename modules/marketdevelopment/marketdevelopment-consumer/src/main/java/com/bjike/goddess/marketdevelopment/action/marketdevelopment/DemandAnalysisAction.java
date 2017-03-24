package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.DemandAnalysisAPI;
import com.bjike.goddess.marketdevelopment.to.DemandAnalysisTO;
import com.bjike.goddess.marketdevelopment.vo.DemandAnalysisVO;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("marketdevelopment/demandanalysis")
public class DemandAnalysisAction {

    @Autowired
    private DemandAnalysisAPI demandAnalysisAPI;

    /**
     * 保存市场需求分析数据
     *
     * @param to 市场需求分析传输对象
     * @return class DemandAnalysisVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(DemandAnalysisTO to) throws ActException {
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
    public Result update(DemandAnalysisTO to) throws ActException {
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
    public Result findByType(@PathVariable String type) throws ActException {
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
    public Result findByCourse(@PathVariable String course) throws ActException {
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
    public Result findByCourseType(@PathVariable String type, @PathVariable String course) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(demandAnalysisAPI.findByCourseType(type, course), DemandAnalysisVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}