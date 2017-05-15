package com.bjike.goddess.assemble.action.module;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.assemble.bo.ModuleBO;
import com.bjike.goddess.assemble.dto.ModuleDTO;
import com.bjike.goddess.assemble.to.ModuleTO;
import com.bjike.goddess.assemble.vo.ModuleVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 模块功能
 * @Author: [liguiqin]
 * @Date: [2017-03-03 10:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("module")
public class ModuleAct {

    @Autowired
    private ModuleAPI moduleAPI;

    /**
     * 获取列表
     * @param moduleVO 模块
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ModuleDTO moduleDTO, HttpServletRequest request) throws ActException{
        ActResult actResult = new ActResult();
        try {
            actResult.setData(BeanTransform.copyProperties(moduleAPI.list(moduleDTO), ModuleVO.class,request));
        } catch (SerException e) {
            throw new ActException(e.getMessage(),e.getCause());
        }
        return actResult;
    }
}
