package com.bjike.goddess.staffwelfaremanage.action.staffwelfaremanage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffwelfaremanage.api.PersonalFestivalWishAPI;
import com.bjike.goddess.staffwelfaremanage.dto.PersonalFestivalWishDTO;
import com.bjike.goddess.staffwelfaremanage.vo.PersonalFestivalWishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 节日祝福语
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 03:08 ]
 * @Description: [ 节日祝福语 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("personalfestivalwish")
public class PersonalFestivalWishAct {

    @Autowired
    private PersonalFestivalWishAPI personalFestivalWishAPI;

    /**
     * 分页查询个人节日祝福语
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(PersonalFestivalWishDTO dto) throws ActException {
        try {
            List<PersonalFestivalWishVO> voList = BeanTransform.copyProperties(personalFestivalWishAPI.pageList(dto), PersonalFestivalWishVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }
}