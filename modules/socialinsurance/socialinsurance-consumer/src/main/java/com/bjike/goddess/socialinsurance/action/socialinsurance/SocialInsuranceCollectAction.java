package com.bjike.goddess.socialinsurance.action.socialinsurance;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.socialinsurance.api.SocialInsuranceAPI;
import com.bjike.goddess.socialinsurance.api.SocialInsuranceCollectAPI;
import com.bjike.goddess.socialinsurance.bo.SICollectEchartBO;
import com.bjike.goddess.socialinsurance.bo.SocialInsuranceCollectBO;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceCollectDTO;
import com.bjike.goddess.socialinsurance.to.GuidePermissionTO;
import com.bjike.goddess.socialinsurance.vo.SICollectEchartVO;
import com.bjike.goddess.socialinsurance.vo.SocialInsuranceCollectVO;
import com.bjike.goddess.user.to.DepartmentTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = socialInsuranceCollectAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
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
    @GetMapping("v1/personal")
    public Result personalCollect(@Validated(SocialInsuranceCollectDTO.TestGet.class) SocialInsuranceCollectDTO dto, BindingResult bindingResult) throws ActException {
        try {
            dto.setStartDate(dto.getStartDate() + "-01");
            dto.setEndDate(dto.getEndDate() + "-31");
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
    @GetMapping("v1/department")
    public Result departmentCollect(@Validated(SocialInsuranceCollectDTO.TestGet.class) SocialInsuranceCollectDTO dto, BindingResult bindingResult) throws ActException {
        try {
            dto.setStartDate(dto.getStartDate() + "-01");
            dto.setEndDate(dto.getEndDate() + "-31");
            List<SocialInsuranceCollectBO> bos = socialInsuranceCollectAPI.departmentCollect(dto);
            List<SocialInsuranceCollectVO> vos = BeanTransform.copyProperties(bos, SocialInsuranceCollectVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 社保地区汇总
     *
     * @param dto dto
     * @return class SocialInsuranceCollectVO
     * @version v1
     */
    @GetMapping("v1/area")
    public Result areaCollect(@Validated(SocialInsuranceCollectDTO.TestGet.class) SocialInsuranceCollectDTO dto, BindingResult bindingResult) throws ActException {
        try {
            dto.setStartDate(dto.getStartDate() + "-01");
            dto.setEndDate(dto.getEndDate() + "-31");
            List<SocialInsuranceCollectBO> bos = socialInsuranceCollectAPI.areaCollect(dto);
            List<SocialInsuranceCollectVO> vos = BeanTransform.copyProperties(bos, SocialInsuranceCollectVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 社保个人汇总柱状图展示
     *
     * @param dto dto
     * @return class SICollectEchartVO
     * @version v1
     */
    @GetMapping("v1/echart/bar/personal")
    public Result personalCollectEchart(@Validated(SocialInsuranceCollectDTO.TestGet.class) SocialInsuranceCollectDTO dto, BindingResult bindingResult) throws ActException {
        try {
            dto.setStartDate(dto.getStartDate() + "-01");
            dto.setEndDate(dto.getEndDate() + "-31");
            SICollectEchartBO bo = socialInsuranceCollectAPI.personalCollectEchart(dto);
            SICollectEchartVO vo = BeanTransform.copyProperties(bo, SICollectEchartVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 社保部门汇总柱状图展示
     *
     * @param dto dto
     * @return class SICollectEchartVO
     * @version v1
     */
    @GetMapping("v1/echart/bar/department")
    public Result departmentCollectEchart(@Validated(SocialInsuranceCollectDTO.TestGet.class) SocialInsuranceCollectDTO dto, BindingResult bindingResult) throws ActException {
        try {
            dto.setStartDate(dto.getStartDate() + "-01");
            dto.setEndDate(dto.getEndDate() + "-31");
            SICollectEchartBO bo = socialInsuranceCollectAPI.departmentCollectEchart(dto);
            SICollectEchartVO vo = BeanTransform.copyProperties(bo, SICollectEchartVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 社保地区汇总柱状图展示
     *
     * @param dto dto
     * @return class SICollectEchartVO
     * @version v1
     */
    @GetMapping("v1/echart/bar/area")
    public Result areaCollectEchart(@Validated(SocialInsuranceCollectDTO.TestGet.class) SocialInsuranceCollectDTO dto, BindingResult bindingResult) throws ActException {
        try {
            dto.setStartDate(dto.getStartDate() + "-01");
            dto.setEndDate(dto.getEndDate() + "-31");
            SICollectEchartBO bo = socialInsuranceCollectAPI.areaCollectEchart(dto);
            SICollectEchartVO vo = BeanTransform.copyProperties(bo, SICollectEchartVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 社保个人汇总饼型图展示
     *
     * @param dto dto
     * @return class SICollectEchartVO
     * @version v1
     */
    @GetMapping("v1/echart/pie/personal")
    public Result personalCollectPieEchart(@Validated(SocialInsuranceCollectDTO.TestGet.class) SocialInsuranceCollectDTO dto, BindingResult bindingResult) throws ActException {
        try {
            dto.setStartDate(dto.getStartDate() + "-01");
            dto.setEndDate(dto.getEndDate() + "-31");
            SICollectEchartBO bo = socialInsuranceCollectAPI.personalCollectPieEchart(dto);
            SICollectEchartVO vo = BeanTransform.copyProperties(bo, SICollectEchartVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 社保部门汇总饼型图展示
     *
     * @param dto dto
     * @return class SICollectEchartVO
     * @version v1
     */
    @GetMapping("v1/echart/pie/department")
    public Result departmentCollectPieEchart(@Validated(SocialInsuranceCollectDTO.TestGet.class) SocialInsuranceCollectDTO dto, BindingResult bindingResult) throws ActException {
        try {
            dto.setStartDate(dto.getStartDate() + "-01");
            dto.setEndDate(dto.getEndDate() + "-31");
            SICollectEchartBO bo = socialInsuranceCollectAPI.departmentCollectPieEchart(dto);
            SICollectEchartVO vo = BeanTransform.copyProperties(bo, SICollectEchartVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 社保地区汇总饼型图展示
     *
     * @param dto dto
     * @return class SICollectEchartVO
     * @version v1
     */
    @GetMapping("v1/echart/pie/area")
    public Result areaCollectPieEchart(@Validated(SocialInsuranceCollectDTO.TestGet.class) SocialInsuranceCollectDTO dto, BindingResult bindingResult) throws ActException {
        try {
            dto.setStartDate(dto.getStartDate() + "-01");
            dto.setEndDate(dto.getEndDate() + "-31");
            SICollectEchartBO bo = socialInsuranceCollectAPI.areaCollectPieEchart(dto);
            SICollectEchartVO vo = BeanTransform.copyProperties(bo, SICollectEchartVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
