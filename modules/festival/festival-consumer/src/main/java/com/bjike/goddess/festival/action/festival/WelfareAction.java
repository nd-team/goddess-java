package com.bjike.goddess.festival.action.festival;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.festival.api.WelfareAPI;
import com.bjike.goddess.festival.dto.WelfareDTO;
import com.bjike.goddess.festival.vo.WelfareVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 节假日礼品福利
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:17 ]
 * @Description: [ 节假日礼品福利 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("festival/welfare")
public class WelfareAction {

    @Autowired
    private WelfareAPI welfareAPI;

    /**
     * 查看节假日礼品福利
     *
     * @param welfareDTO 节假日礼品福利dto
     * @des 查看节假日礼品福利
     * @return  class WelfareVO
     * @version v1
     */
    @GetMapping("v1/getWelfareDetail")
    public Result getWelfareDetail (@Validated(WelfareDTO.TESTFindDetail.class) WelfareDTO welfareDTO, BindingResult bindingResult) throws ActException {
        try {
            List<WelfareVO> welfareVOS = BeanTransform.copyProperties(
                    welfareAPI.getWelfare(welfareDTO.getHolidayProgrammeId()), WelfareVO.class, true);
            return ActResult.initialize(welfareVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}