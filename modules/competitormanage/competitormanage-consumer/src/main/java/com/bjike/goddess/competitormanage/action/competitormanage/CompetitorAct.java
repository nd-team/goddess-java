package com.bjike.goddess.competitormanage.action.competitormanage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.competitormanage.api.CompetitorAPI;
import com.bjike.goddess.competitormanage.dto.CompetitorDTO;
import com.bjike.goddess.competitormanage.to.CompetitorTO;
import com.bjike.goddess.competitormanage.vo.CompetitorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("competitormanage/competitor")
public class CompetitorAct {
    @Autowired
    private CompetitorAPI competitorAPI;

    /**
     * 新增竞争对手
     *
     * @param to 竞争对手信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(CompetitorTO to, BindingResult bindingResult) throws ActException {
        try {
            CompetitorVO voList = BeanTransform.copyProperties(competitorAPI.saveCompetitor(to), CompetitorVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑竞争对手
     *
     * @param to 竞争对手信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(CompetitorTO to, BindingResult bindingResult) throws ActException {
        try {
            CompetitorVO vo = BeanTransform.copyProperties(competitorAPI.editCompetitor(to), CompetitorVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 竞争对手组织结构信息
     *
     * @param to 竞争对手组织结构信息
     * @version v1
     */
    @PostMapping("v1/editOrganization")
    public Result editOrganization(CompetitorTO to, BindingResult bindingResult) throws ActException {
        try {
            CompetitorVO vo = BeanTransform.copyProperties(competitorAPI.editOrganization(to), CompetitorVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除竞争对手
     *
     * @param id 竞争对手ID
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            competitorAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(CompetitorDTO dto) throws ActException {
        try {
            List<CompetitorVO> voList = BeanTransform.copyProperties(competitorAPI.pageList(dto), CompetitorVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}