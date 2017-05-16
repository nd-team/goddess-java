package com.bjike.goddess.rotation.action.rotation;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rotation.api.CoverRotationOpinionAPI;
import com.bjike.goddess.rotation.dto.CoverRotationOpinionDTO;
import com.bjike.goddess.rotation.vo.CoverRotationOpinionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 岗位轮换自荐意见
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 04:04 ]
 * @Description: [ 岗位轮换自荐意见 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("coverrotationopinion")
public class CoverRotationOpinionAct {

    @Autowired
    private CoverRotationOpinionAPI coverRotationOpinionAPI;

    /**
     * 根据岗位轮换自荐id查询意见
     *
     * @param id  岗位轮换自荐数据id
     * @param dto 岗位轮换自荐意见数据传输对象
     * @return class CoverRotationOpinionVO
     * @version v1
     */
    @GetMapping("v1/findByCover/{id}")
    public Result findByCover(@PathVariable String id, CoverRotationOpinionDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(coverRotationOpinionAPI.findByCover(id, dto), CoverRotationOpinionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @param id 岗位轮换自荐数据id
     * @return
     * @version v1
     */
    @GetMapping("v1/getTotal/{id}")
    public Result getTotal(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(coverRotationOpinionAPI.getTotal(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}