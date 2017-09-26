package com.bjike.goddess.royalty.action.royalty;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.royalty.api.IndexLibraryAPI;
import com.bjike.goddess.royalty.bo.IndexLibraryBO;
import com.bjike.goddess.royalty.dto.IndexLibraryDTO;
import com.bjike.goddess.royalty.excel.SonPermissionObject;
import com.bjike.goddess.royalty.to.GuidePermissionTO;
import com.bjike.goddess.royalty.to.IndexLibraryTO;
import com.bjike.goddess.royalty.to.RoyaltyCollectTO;
import com.bjike.goddess.royalty.vo.IndexLibraryVO;
import com.bjike.goddess.royalty.vo.RoyaltyCollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理提成管理汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 11:23 ]
 * @Description: [ 管理提成管理汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("royaltycollect")
public class RoyaltyCollectAction {
    @Autowired
    private IndexLibraryAPI indexLibraryAPI;


    /**
     * 管理提成管理日汇总
     *
     * @param to to
     * @return class RoyaltyCollectVO
     * @des 管理提成管理日汇总
     * @version v1
     */
    @GetMapping("v1/dayCollect")
    public Result dayCollect(RoyaltyCollectTO to) throws ActException {
        try {
            List<RoyaltyCollectVO> royaltyCollectVOS = BeanTransform.copyProperties(indexLibraryAPI.dayRoyalty(to),RoyaltyCollectVO.class);
            return ActResult.initialize(royaltyCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 管理提成管理周汇总
     *
     * @param to to
     * @return class RoyaltyCollectVO
     * @des 管理提成管理周汇总
     * @version v1
     */
    @GetMapping("v1/weekCollect")
    public Result weekCollect(RoyaltyCollectTO to) throws ActException {
        try {
            List<RoyaltyCollectVO> royaltyCollectVOS = BeanTransform.copyProperties(indexLibraryAPI.weekRoyalty(to),RoyaltyCollectVO.class);
            return ActResult.initialize(royaltyCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 管理提成管理月汇总
     *
     * @param to to
     * @return class RoyaltyCollectVO
     * @des 管理提成管理月汇总
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect(RoyaltyCollectTO to) throws ActException {
        try {
            List<RoyaltyCollectVO> royaltyCollectVOS = BeanTransform.copyProperties(indexLibraryAPI.monthRoyalty(to),RoyaltyCollectVO.class);
            return ActResult.initialize(royaltyCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 管理提成管理累计汇总
     *
     * @param to to
     * @return class RoyaltyCollectVO
     * @des 管理提成管理累计汇总
     * @version v1
     */
    @GetMapping("v1/totalCollect")
    public Result totalCollect(RoyaltyCollectTO to) throws ActException {
        try {
            List<RoyaltyCollectVO> royaltyCollectVOS = BeanTransform.copyProperties(indexLibraryAPI.totalRoyalty(to),RoyaltyCollectVO.class);
            return ActResult.initialize(royaltyCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}