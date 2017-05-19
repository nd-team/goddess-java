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
import com.bjike.goddess.competitormanage.dto.CompetitorDTO;
import com.bjike.goddess.competitormanage.to.CompetitorTO;
import com.bjike.goddess.competitormanage.vo.CompetitorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 竞争对手信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-21 04:49 ]
 * @Description: [ 竞争对手信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("competitor")
public class CompetitorAct {
    @Autowired
    private CompetitorAPI competitorAPI;

    /**
     * 新增竞争对手
     *
     * @param to 竞争对手信息
     * @return class CompetitorVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) CompetitorTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CompetitorVO voList = BeanTransform.copyProperties(competitorAPI.saveCompetitor(to), CompetitorVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑竞争对手
     *
     * @param to 竞争对手信息
     * @return class CompetitorVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) CompetitorTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CompetitorVO vo = BeanTransform.copyProperties(competitorAPI.editCompetitor(to), CompetitorVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 竞争对手组织结构信息
     *
     * @param to 竞争对手组织结构信息
     * @return class CompetitorVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/organization")
    public Result editOrganization(@Validated({CompetitorTO.Organization.class}) CompetitorTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CompetitorVO vo = BeanTransform.copyProperties(competitorAPI.editOrganization(to), CompetitorVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除竞争对手
     *
     * @param id 竞争对手Id
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            competitorAPI.delete(id);
            return new ActResult();
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
    public Result count(CompetitorDTO dto) throws ActException {
        try {
            Long count = competitorAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询竞争对手记录
     *
     * @param id 竞争对手Id
     * @return class CompetitorVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result findByid(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            CompetitorVO vo = BeanTransform.copyProperties(competitorAPI.findById(id), CompetitorVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 查询条件或分页条件
     * @return class CompetitorVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(CompetitorDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<CompetitorVO> voList = BeanTransform.copyProperties(competitorAPI.pageList(dto), CompetitorVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}