package com.bjike.goddess.socialinsurance.action.socialinsurance;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.socialinsurance.api.SocialInsuranceAPI;
import com.bjike.goddess.socialinsurance.api.SocialInsuranceCollectAPI;
import com.bjike.goddess.socialinsurance.bo.SocialInsuranceCollectBO;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceCollectDTO;
import com.bjike.goddess.socialinsurance.vo.SocialInsuranceCollectVO;
import com.bjike.goddess.user.to.DepartmentTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 社会保险汇总
 * @Author: [caiwenxian]
 * @Date: [2017-12-21 17:20]
 * @Description: [ 社会保险汇总 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@RestController
@RequestMapping("socialinsurancecollect")
public class SocialInsuranceCollectAction {

    @Autowired
    SocialInsuranceCollectAPI socialInsuranceCollectAPI;

    /**
     * 社保个人汇总
     *
     * @param dto dto
     * @return class SocialInsuranceCollectVO
     * @version v1
     */
    @PutMapping("v1/personal")
    public Result personalCollect(SocialInsuranceCollectDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<SocialInsuranceCollectBO> bos = socialInsuranceCollectAPI.personalCollect(dto);
            List<SocialInsuranceCollectVO> vos = BeanTransform.copyProperties(bos, SocialInsuranceCollectVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 社保部门汇总
     *
     * @param dto dto
     * @return class SocialInsuranceCollectVO
     * @version v1
     */
    @PutMapping("v1/department")
    public Result departmentCollect(SocialInsuranceCollectDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<SocialInsuranceCollectBO> bos = socialInsuranceCollectAPI.departmentCollect(dto);
            List<SocialInsuranceCollectVO> vos = BeanTransform.copyProperties(bos, SocialInsuranceCollectVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 社保个人汇总
     *
     * @param dto dto
     * @return class SocialInsuranceCollectVO
     * @version v1
     */
    @PutMapping("v1/area")
    public Result areaCollect(SocialInsuranceCollectDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<SocialInsuranceCollectBO> bos = socialInsuranceCollectAPI.areaCollect(dto);
            List<SocialInsuranceCollectVO> vos = BeanTransform.copyProperties(bos, SocialInsuranceCollectVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
