package com.bjike.goddess.interiorrecommend.action.interiorrecommend;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.api.AwardInfoAPI;
import com.bjike.goddess.interiorrecommend.api.RecommendInfoAPI;
import com.bjike.goddess.interiorrecommend.dto.AwardInfoDTO;
import com.bjike.goddess.interiorrecommend.to.AwardInfoTO;
import com.bjike.goddess.interiorrecommend.vo.AwardInfoVO;
import com.bjike.goddess.interiorrecommend.vo.AwardStandardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 推荐奖励信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 04:07 ]
 * @Description: [ 推荐奖励信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("awardinfo")
public class AwardInfoAct {

    @Autowired
    private AwardInfoAPI awardInfoAPI;
    @Autowired
    private RecommendInfoAPI recommendInfoAPI;

    /**
     * 推荐信息列表
     *
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/awardlist")
    public Result pageList() throws ActException {
        try {
            List<AwardStandardVO> voList = BeanTransform.copyProperties(recommendInfoAPI.awardlist(), AwardStandardVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 推荐奖励信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) AwardInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            AwardInfoVO vo = BeanTransform.copyProperties(awardInfoAPI.editModel(to), AwardInfoVO.class);
            return ActResult.initialize(vo);
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
    @GetMapping("v1/list")
    public Result pageList(AwardInfoDTO dto) throws ActException {
        try {
            List<AwardStandardVO> voList = BeanTransform.copyProperties(awardInfoAPI.pageList(dto), AwardStandardVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}