package com.bjike.goddess.supplier.action.supplier;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.api.CooperationSituationAPI;
import com.bjike.goddess.supplier.to.ContactSituationTO;
import com.bjike.goddess.supplier.vo.CooperationSituationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 合作情况
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T11:05:37.659 ]
 * @Description: [ 合作情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("supplier/cooperationsituation")
public class CooperationSituationAction {

    @Autowired
    private CooperationSituationAPI cooperationSituationAPI;

    /**
     * 根据供应商基本信息ID查询合作情况
     *
     * @param info_id 供应商基本信息ID
     * @version v1
     * @return class CooperationSituationVO
     */
    @GetMapping("v1/findByInformation/{info_id}")
    public Result findByInformation(@PathVariable String info_id) throws ActException {
        try {
            return ActResult.initialize(
                    BeanTransform.copyProperties(
                            cooperationSituationAPI.findByInformation(info_id)
                            , CooperationSituationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存供应商合作情况数据
     *
     * @param to 供应商合作情况传输对象
     * @version v1
     * @return class CooperationSituationVO
     */
    @PostMapping("v1/save")
    public Result save(@Validated ContactSituationTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(cooperationSituationAPI.save(to), CooperationSituationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改供应商合作情况数据
     *
     * @param to 供应商合作情况传输对象
     * @version v1
     * @return class CooperationSituationVO
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated ContactSituationTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(cooperationSituationAPI.update(to), CooperationSituationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除供应商合作情况数据
     *
     * @param id 供应商合作情况id
     * @version v1
     * @return class CooperationSituationVO
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(cooperationSituationAPI.delete(id), CooperationSituationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}