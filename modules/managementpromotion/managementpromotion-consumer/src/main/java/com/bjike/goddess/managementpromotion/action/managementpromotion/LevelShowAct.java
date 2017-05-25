package com.bjike.goddess.managementpromotion.action.managementpromotion;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managementpromotion.api.LevelShowAPI;
import com.bjike.goddess.managementpromotion.dto.LevelShowDTO;
import com.bjike.goddess.managementpromotion.vo.LevelShowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 管理等级情况慨览
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:53 ]
 * @Description: [ 管理等级情况慨览 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("levelshow")
public class LevelShowAct {
    @Autowired
    private LevelShowAPI levelShowAPI;

    /**
     * 管理分类等级设计列表总条数
     *
     * @param dto 管理分类等级设计dto
     * @des 获取所有管理分类等级设计总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(LevelShowDTO dto) throws ActException {
        try {
            Long count = levelShowAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 管理分类等级设计列表
     *
     * @param dto 管理分类等级设计信息dto
     * @return class LevelShowVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(LevelShowDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<LevelShowVO> VOS = BeanTransform.copyProperties
                    (levelShowAPI.find(dto), LevelShowVO.class, request);
            return ActResult.initialize(VOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}