package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.FilesDataAPI;
import com.bjike.goddess.marketdevelopment.bo.FilesDataBO;
import com.bjike.goddess.marketdevelopment.vo.FilesDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 阶段表头数据
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:30 ]
 * @Description: [ 阶段表头数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("filesdata")
public class FilesDataAction {
    @Autowired
    private FilesDataAPI filesDataAPI;

    /**
     * 查询表头的数据
     *
     * @return class FilesDataVO
     * @version v1
     */
    @GetMapping("v1/findFiles")
    public Result findFiles() throws ActException {
        try {
            FilesDataBO bo = filesDataAPI.findFiles();
            return ActResult.initialize(BeanTransform.copyProperties(bo, FilesDataVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}