package com.bjike.goddess.competitormanage.action.competitormanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.competitormanage.api.CompetitorAPI;
import com.bjike.goddess.competitormanage.api.CompetitorCollectAPI;
import com.bjike.goddess.competitormanage.dto.CompetitorCollectDTO;
import com.bjike.goddess.competitormanage.to.CompetitorCollectTO;
import com.bjike.goddess.competitormanage.to.GuidePermissionTO;
import com.bjike.goddess.competitormanage.vo.CollectionTotalVO;
import com.bjike.goddess.competitormanage.vo.CompetitorCollectVO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 竞争对手汇总
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-23 11:27 ]
 * @Description: [ 竞争对手汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("collect")
public class CompetitorCollectAct {

    @Autowired
    private CompetitorCollectAPI competitorCollectAPI;
    @Autowired
    private PositionDetailUserAPI detailUserAPI;
    @Autowired
    private CompetitorAPI competitorAPI;


    /**
     * 地区列表
     *
     * @return class AreaBO
     * @version v1
     */
    @GetMapping("v1/areas")
    public Result areas(HttpServletRequest request) throws ActException {
        try {
            List<AreaBO> voList = BeanTransform.copyProperties(competitorAPI.areas(), AreaBO.class, request);
            return ActResult.initialize(voList);
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

            Boolean isHasPermission = competitorCollectAPI.guidePermission(guidePermissionTO);
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
     * 新增竞争对手汇总
     *
     * @param to 竞争对手汇总信息
     * @return class CompetitorCollectVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) CompetitorCollectTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CompetitorCollectVO vo = BeanTransform.copyProperties(competitorCollectAPI.save(to), CompetitorCollectVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 对象列表
     *
     * @return class UserBO
     * @version v1
     */
    @GetMapping("v1/users")
    public Result users(HttpServletRequest request) throws ActException {
        try {
            List<UserBO> voList = BeanTransform.copyProperties(detailUserAPI.findUserList(), UserBO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑竞争对手汇总
     *
     * @param to 竞争对手汇总信息
     * @return class CompetitorCollectVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) CompetitorCollectTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CompetitorCollectVO vo = BeanTransform.copyProperties(competitorCollectAPI.edit(to), CompetitorCollectVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结竞争对手汇总
     *
     * @param id 竞争对手ID
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            competitorCollectAPI.freeze(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻竞争对手汇总
     *
     * @param id 竞争对手ID
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/breakfreeze/{id}")
    public Result breakFreeze(@PathVariable String id) throws ActException {
        try {
            competitorCollectAPI.breakFreeze(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除竞争对手汇总
     *
     * @param id 竞争对手ID
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            competitorCollectAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 分页条件
     * @return class CompetitorCollectVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(CompetitorCollectDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<CompetitorCollectVO> voList = BeanTransform.copyProperties(competitorCollectAPI.pageList(dto), CompetitorCollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @return class CollectionTotalVO
     * @version v1
     */
    @GetMapping("v1/total")
    public Result collectionTotal(HttpServletRequest request) throws ActException {
        try {
            List<CollectionTotalVO> voList = BeanTransform.copyProperties(competitorCollectAPI.collectionTotal(), CollectionTotalVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询列表总条数
     *
     * @param dto 查询条件或分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CompetitorCollectDTO dto) throws ActException {
        try {
            Long count = competitorCollectAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询竞争对手记录
     *
     * @param id 竞争对手Id
     * @return class CompetitorCollectVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result findByid(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            CompetitorCollectVO vo = BeanTransform.copyProperties(competitorCollectAPI.findById(id), CompetitorCollectVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}