package com.bjike.goddess.contacts.action.contacts;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.CommerceMemberAPI;
import com.bjike.goddess.contacts.dto.CommerceMemberDTO;
import com.bjike.goddess.contacts.to.CommerceMemberTO;
import com.bjike.goddess.contacts.vo.CommerceMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商务会员卡
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:38 ]
 * @Description: [ 商务会员卡 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contacts/commercemember")
public class CommerceMemberAction {

    @Autowired
    private CommerceMemberAPI commerceMemberAPI;

    /**
     * 保存
     *
     * @param to 商务会员卡传输对象
     * @return class CommerceMemberVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CommerceMemberTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commerceMemberAPI.save(to), CommerceMemberVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 商务会员卡传输对象
     * @return class CommerceMemberVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) CommerceMemberTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commerceMemberAPI.update(to), CommerceMemberVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param to 商务会员卡传输对象
     * @return class CommerceMemberVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(CommerceMemberTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commerceMemberAPI.delete(to), CommerceMemberVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表查询
     *
     * @param dto 商务会员卡数据传输对象
     * @return class CommerceMemberVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(CommerceMemberDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commerceMemberAPI.maps(dto), CommerceMemberVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}