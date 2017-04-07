package com.bjike.goddess.festival.action.festival;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.festival.api.GiftStandardAPI;
import com.bjike.goddess.festival.bo.GiftStandardBO;
import com.bjike.goddess.festival.dto.GiftStandardDTO;
import com.bjike.goddess.festival.to.GiftStandardTO;
import com.bjike.goddess.festival.vo.GiftStandardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 节假日礼品标准
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:02 ]
 * @Description: [ 节假日礼品标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("festival/giftstandard")
public class GiftStandardAction {

    @Autowired
    private GiftStandardAPI giftStandardAPI;

    /**
     *  节假日礼品标准列表总条数
     *
     * @param giftStandardDTO  节假日礼品标准信息dto
     * @des 获取所有节假日礼品标准信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(GiftStandardDTO giftStandardDTO) throws ActException {
        try {
            Long count = giftStandardAPI.countGiftStandard(giftStandardDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 节假日礼品标准列表
     *
     * @param giftStandardDTO 节假日礼品标准信息dto
     * @des 获取所有节假日礼品标准信息
     * @return  class GiftStandardVO
     * @version v1
     */
    @GetMapping("v1/listGiftStandard")
    public Result findListGiftStandard(GiftStandardDTO giftStandardDTO, BindingResult bindingResult) throws ActException {
        try {
            List<GiftStandardVO> giftStandardVOList = BeanTransform.copyProperties(
                    giftStandardAPI.listGiftStandard(giftStandardDTO), GiftStandardVO.class, true);
            return ActResult.initialize(giftStandardVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加节假日礼品标准
     *
     * @param giftStandardTO 节假日礼品标准基本信息数据to
     * @des 添加节假日礼品标准
     * @return  class GiftStandardVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addGiftStandard(@Validated({GiftStandardTO.TESTAddAndEdit.class}) GiftStandardTO giftStandardTO, BindingResult bindingResult) throws ActException {
        try {
            GiftStandardBO giftStandardBO1 = giftStandardAPI.addGiftStandard(giftStandardTO);
            return ActResult.initialize(BeanTransform.copyProperties(giftStandardBO1,GiftStandardVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑节假日礼品标准
     *
     * @param giftStandardTO 节假日礼品标准基本信息数据bo
     * @des 添加节假日礼品标准
     * @return  class GiftStandardVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result editGiftStandard(@Validated({GiftStandardTO.TESTAddAndEdit.class}) GiftStandardTO giftStandardTO) throws ActException {
        try {
            GiftStandardBO giftStandardBO1 = giftStandardAPI.editGiftStandard(giftStandardTO);
            return ActResult.initialize(BeanTransform.copyProperties(giftStandardBO1,GiftStandardVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除节假日礼品标准信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteGiftStandard(@PathVariable String id) throws ActException {
        try {
            giftStandardAPI.deleteGiftStandard(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 获取所有礼品类型
     *
     * @des 获取所有礼品
     * @return  class GiftStandardVO
     * @version v1
     */
    @GetMapping("v1/getGift")
    public Result getGift ( BindingResult bindingResult) throws ActException {
        try {
            List<String> giftStandardVOList = giftStandardAPI.getGiftByFestivalName();
            return ActResult.initialize(giftStandardVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}