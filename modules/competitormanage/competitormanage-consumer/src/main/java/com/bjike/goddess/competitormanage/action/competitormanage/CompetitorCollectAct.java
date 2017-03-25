package com.bjike.goddess.competitormanage.action.competitormanage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.competitormanage.api.CompetitorCollectAPI;
import com.bjike.goddess.competitormanage.dto.CompetitorCollectDTO;
import com.bjike.goddess.competitormanage.to.CompetitorCollectTO;
import com.bjike.goddess.competitormanage.vo.CollectionTotalVO;
import com.bjike.goddess.competitormanage.vo.CompetitorCollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("competitormanage/competitorcollect")
public class CompetitorCollectAct {

    @Autowired
    private CompetitorCollectAPI competitorCollectAPI;

    /**
     * 新增竞争对手汇总
     *
     * @param to 竞争对手汇总信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(CompetitorCollectTO to, BindingResult bindingResult) throws ActException {
        try {
            CompetitorCollectVO vo = BeanTransform.copyProperties(competitorCollectAPI.save(to), CompetitorCollectVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑竞争对手汇总
     *
     * @param to 竞争对手汇总信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(CompetitorCollectTO to, BindingResult bindingResult) throws ActException {
        try {
            CompetitorCollectVO vo = BeanTransform.copyProperties(competitorCollectAPI.edit(to), CompetitorCollectVO.class);
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
    @GetMapping("v1/freeze/{id}")
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
    @GetMapping("v1/breakFreeze/{id}")
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
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            competitorCollectAPI.delete(id);
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
    public Result pageList(CompetitorCollectDTO dto) throws ActException {
        try {
            List<CompetitorCollectVO> voList = BeanTransform.copyProperties(competitorCollectAPI.pageList(dto), CompetitorCollectVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询
     *
     * @version v1
     */
    @GetMapping("v1/collectionTotal")
    public Result collectionTotal() throws ActException {
        try {
            List<CollectionTotalVO> voList = BeanTransform.copyProperties(competitorCollectAPI.collectionTotal(), CollectionTotalVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}