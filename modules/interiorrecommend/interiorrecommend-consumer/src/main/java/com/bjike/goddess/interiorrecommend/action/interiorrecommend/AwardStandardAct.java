package com.bjike.goddess.interiorrecommend.action.interiorrecommend;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.api.AwardStandardAPI;
import com.bjike.goddess.interiorrecommend.dto.AwardStandardDTO;
import com.bjike.goddess.interiorrecommend.to.AwardStandardTO;
import com.bjike.goddess.interiorrecommend.vo.AwardStandardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 推荐奖励要求标准
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 11:39 ]
 * @Description: [ 推荐奖励要求标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("awardstandard")
public class AwardStandardAct {

    @Autowired
    private AwardStandardAPI awardStandardAPI;

    /**
     * 新增
     *
     * @param to 推荐奖励要求标准
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) AwardStandardTO to, BindingResult bindingResult) throws ActException {
        try {
            AwardStandardVO vo = BeanTransform.copyProperties(awardStandardAPI.addModel(to), AwardStandardVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 推荐奖励要求标准
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) AwardStandardTO to, BindingResult bindingResult) throws ActException {
        try {
            AwardStandardVO vo = BeanTransform.copyProperties(awardStandardAPI.editModel(to), AwardStandardVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 推荐奖励要求标准id
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            awardStandardAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 分页条件
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/pageList")
    public Result pageList(AwardStandardDTO dto) throws ActException {
        try {
            List<AwardStandardVO> voList = BeanTransform.copyProperties(awardStandardAPI.pageList(dto), AwardStandardVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}