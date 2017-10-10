package com.bjike.goddess.dispatchcar.action.dispatchcar;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.CheckChangeCarAPI;
import com.bjike.goddess.dispatchcar.bo.CheckChangeCarBO;
import com.bjike.goddess.dispatchcar.dto.CheckChangeCarDTO;
import com.bjike.goddess.dispatchcar.to.CheckChangeCarTO;
import com.bjike.goddess.dispatchcar.to.CorrectMistakeTO;
import com.bjike.goddess.dispatchcar.vo.CheckChangeCarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
* 出车核对修改记录
* @Author: [ Jason ]
* @Date: [  2017-09-25 11:24 ]
* @Description:	[ 出车核对修改记录 ]
* @Version: [ v1.0.0 ]
* @Copy: [ com.bjike ]
*/
@RestController
@RequestMapping("checkchangecar")
public class CheckChangeCarAction {
    @Autowired
    private CheckChangeCarAPI checkChangeCarAPI;


    /**
     * 出车核对修改记录列表
     * @param dto 查询条件
     * @return class CheckChangeCarVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findList(CheckChangeCarDTO dto) throws ActException{
        try {
            List<CheckChangeCarBO> boList = checkChangeCarAPI.list(dto);
            List<CheckChangeCarVO> voList = BeanTransform.copyProperties(boList,CheckChangeCarVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 问题解决
     * @param to 出车
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/modify")
    public Result modify(@Validated(ADD.class) CorrectMistakeTO to) throws ActException{
        try {
            checkChangeCarAPI.modify(to);
            return new ActResult("问题解决成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

 }