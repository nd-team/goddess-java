package com.bjike.goddess.projectissuehandle.action.projectissuehandle;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectissuehandle.api.CollectAPI;
import com.bjike.goddess.projectissuehandle.vo.CollectVO;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 汇总项目执行中的问题受理及处理结果
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-27 10:49 ]
 * @Description: [ 汇总项目执行中的问题受理及处理结果 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("collect")
public class CollectAction {
    @Autowired
    private CollectAPI collectAPI;

    /**
     * 汇总项目执行中的问题受理及处理结果
     *
     * @param areas 地区
     * @return class CollectVO
     * @des 项目执行中的问题受理及处理结果
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(@NotBlank String[] areas) throws ActException {
        try {
            List<CollectVO> collectVOS = BeanTransform.copyProperties(
                    collectAPI.collect(areas), CollectVO.class, true);
            return ActResult.initialize(collectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}