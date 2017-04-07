package com.bjike.goddess.festival.action.festival;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.festival.api.NoticeThingAPI;
import com.bjike.goddess.festival.dto.NoticeThingDTO;
import com.bjike.goddess.festival.vo.NoticeThingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 注意事项
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:19 ]
 * @Description: [ 注意事项 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("festival/noticething")
public class NoticeThingAction {

    @Autowired
    private NoticeThingAPI noticeThingAPI;

    /**
     * 查看注意事项
     *
     * @param noticeThingDTO 注意事项dto
     * @des 查看注意事项
     * @return  class NoticeThingVO
     * @version v1
     */
    @GetMapping("v1/getNoticeThingDetail")
    public Result getNoticeThingDetail (@Validated(NoticeThingDTO.TESTFindDetail.class) NoticeThingDTO noticeThingDTO, BindingResult bindingResult) throws ActException {
        try {
            List<NoticeThingVO> noticeThingVOS = BeanTransform.copyProperties(
                    noticeThingAPI.getNoticeThing(noticeThingDTO.getHolidayProgrammeId()), NoticeThingVO.class, true);
            return ActResult.initialize(noticeThingVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}