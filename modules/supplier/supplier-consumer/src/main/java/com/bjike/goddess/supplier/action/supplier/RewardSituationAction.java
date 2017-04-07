package com.bjike.goddess.supplier.action.supplier;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.api.RewardSituationAPI;
import com.bjike.goddess.supplier.to.RewardSituationTO;
import com.bjike.goddess.supplier.vo.RewardSituationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 获奖情况
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:53:15.058 ]
 * @Description: [ 获奖情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("supplier/rewardsituation")
public class RewardSituationAction {


    @Autowired
    private RewardSituationAPI rewardSituationAPI;

    /**
     * 根据供应商基本信息ID查询获奖情况
     *
     * @param info_id 供应商基本信息ID
     * @version v1
     * @return class RewardSituationVO
     */
    @GetMapping("v1/findByInformation/{info_id}")
    public Result findByInformation(@PathVariable String info_id) throws ActException {
        try {
            return ActResult.initialize(
                    BeanTransform.copyProperties(
                            rewardSituationAPI.findByInformation(info_id)
                            , RewardSituationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存供应商获奖情况数据
     *
     * @param to 供应商获奖情况传输对象
     * @version v1
     * @return class RewardSituationVO
     */
    @PostMapping("v1/save")
    public Result save(@Validated RewardSituationTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(rewardSituationAPI.save(to), RewardSituationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改供应商获奖情况数据
     *
     * @param to 供应商获奖情况传输对象
     * @version v1
     * @return class RewardSituationVO
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated RewardSituationTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(rewardSituationAPI.update(to), RewardSituationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除供应商获奖情况数据
     *
     * @param id 供应商获奖情况id
     * @version v1
     * @return class RewardSituationVO
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(rewardSituationAPI.delete(id), RewardSituationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}