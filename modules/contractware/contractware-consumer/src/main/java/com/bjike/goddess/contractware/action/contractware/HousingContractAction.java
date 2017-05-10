package com.bjike.goddess.contractware.action.contractware;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.api.HousingContractAPI;
import com.bjike.goddess.contractware.bo.HousingContractBO;
import com.bjike.goddess.contractware.dto.HousingContractDTO;
import com.bjike.goddess.contractware.to.HousingContractTO;
import com.bjike.goddess.contractware.vo.HousingContractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 房屋合同
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:42 ]
 * @Description: [ 房屋合同 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("housingcontract")
public class HousingContractAction {
    @Autowired
    private HousingContractAPI housingContractAPI;

    /**
     * 房屋合同列表总条数
     *
     * @param housingContractDTO 房屋合同dto
     * @des 获取所有房屋合同总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(HousingContractDTO housingContractDTO) throws ActException {
        try {
            Long count = housingContractAPI.countHousingContract(housingContractDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个房屋合同
     *
     * @param id
     * @return class HousingContractVO
     * @des 获取一个房屋合同
     * @version v1
     */
    @GetMapping("v1/housing/{id}")
    public Result housing(@PathVariable String id) throws ActException {
        try {
            HousingContractBO housingContractBO = housingContractAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(housingContractBO, HousingContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 房屋合同列表
     *
     * @param housingContractDTO 房屋合同dto
     * @return class HousingContractVO
     * @des 获取所有房屋合同
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(HousingContractDTO housingContractDTO, HttpServletRequest request) throws ActException {
        try {
            List<HousingContractVO> housingContractVOS = BeanTransform.copyProperties
                    (housingContractAPI.findListHousingContract(housingContractDTO), HousingContractVO.class, request);
            return ActResult.initialize(housingContractVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加房屋合同
     *
     * @param housingContractTO 房屋合同数据to
     * @return class HousingContractVO
     * @des 添加房屋合同
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) HousingContractTO housingContractTO, BindingResult bindingResult) throws ActException {
        try {
            HousingContractBO housingContractBO = housingContractAPI.insertHousingContract(housingContractTO);
            return ActResult.initialize(housingContractBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑房屋合同
     *
     * @param housingContractTO 房屋合同数据to
     * @return class HousingContractVO
     * @des 编辑房屋合同
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) HousingContractTO housingContractTO, BindingResult bindingResult) throws ActException {
        try {
            HousingContractBO housingContractBO = housingContractAPI.editHousingContract(housingContractTO);
            return ActResult.initialize(housingContractBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除房屋合同
     *
     * @param id 用户id
     * @des 根据用户id删除房屋合同记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            housingContractAPI.removeHousingContract(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 上传附件
     *
     * @version v1
     */


}