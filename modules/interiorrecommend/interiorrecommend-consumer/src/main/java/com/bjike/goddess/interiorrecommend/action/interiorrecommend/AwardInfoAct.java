package com.bjike.goddess.interiorrecommend.action.interiorrecommend;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.api.AwardInfoAPI;
import com.bjike.goddess.interiorrecommend.api.RecommendInfoAPI;
import com.bjike.goddess.interiorrecommend.bo.AwardInfoBO;
import com.bjike.goddess.interiorrecommend.bo.RecommendInfoBO;
import com.bjike.goddess.interiorrecommend.dto.AwardInfoDTO;
import com.bjike.goddess.interiorrecommend.dto.RecommendInfoDTO;
import com.bjike.goddess.interiorrecommend.entity.AwardInfo;
import com.bjike.goddess.interiorrecommend.excel.SonPermissionObject;
import com.bjike.goddess.interiorrecommend.to.AwardInfoTO;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.vo.AwardInfoVO;
import com.bjike.goddess.interiorrecommend.vo.AwardStandardVO;
import com.bjike.goddess.interiorrecommend.vo.RecommendInfoVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.entity.UserSetPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 推荐奖励信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 04:07 ]
 * @Description: [ 推荐奖励信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("awardinfo")
public class AwardInfoAct {

    @Autowired
    private AwardInfoAPI awardInfoAPI;
    @Autowired
    private RecommendInfoAPI recommendInfoAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = awardInfoAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = awardInfoAPI.guidePermission(guidePermissionTO);
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
     * 根据id来查询单个数据
     * @param id
     * @return class RecommendInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/info/{id}")
    public Result findRecommendInfo(@PathVariable  String id) throws ActException{
        try {
            RecommendInfoBO bo = awardInfoAPI.finOne(id);
            RecommendInfoVO vo = BeanTransform.copyProperties(bo,RecommendInfoVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 推荐奖励信息
     * @return class AwardInfoVO
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) AwardInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            AwardInfoVO vo = BeanTransform.copyProperties(awardInfoAPI.editModel(to), AwardInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核通过的推荐信息列表
     *
     * @param dto 分页条件
     * @return class RecommendInfoVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result pageList(RecommendInfoDTO dto) throws ActException {
        try {
            List<RecommendInfoVO> voList = BeanTransform.copyProperties(awardInfoAPI.pageList(dto), RecommendInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id来查询推荐奖励信息
     * @param id
     * @return class AwardInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/one/{id}")
    public Result findOne(@PathVariable String id) throws ActException{
        try {
            AwardInfoBO bo = awardInfoAPI.findOne(id);
            AwardInfoVO vo = BeanTransform.copyProperties(bo,AwardInfoVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表总条数
     * @param dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AwardInfoDTO dto) throws ActException{
        try{
            Long count = awardInfoAPI.count(dto);
            return ActResult.initialize(count);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }
}