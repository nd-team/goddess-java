package com.bjike.goddess.businessevaluate.action.marketresponse;

import com.bjike.goddess.businessevaluate.api.MarketSesponseAPI;
import com.bjike.goddess.businessevaluate.dto.MarketSesponseDTO;
import com.bjike.goddess.businessevaluate.to.MarketSesponseTO;
import com.bjike.goddess.businessevaluate.vo.MarketSesponseVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 市场反应和创新能力
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 11:33 ]
 * @Description: [ 市场反应和创新能力 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessevaluate/marketsesponse")
public class MarketSesponseAct {

    @Autowired
    private MarketSesponseAPI marketSesponseAPI;

    /**
     * 新增市场反应和创新能力
     *
     * @param to 市场反应和创新能力
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(MarketSesponseTO to, BindingResult bindingResult) throws ActException {
        try {
            MarketSesponseVO vo = BeanTransform.copyProperties(marketSesponseAPI.addModel(to), MarketSesponseVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑市场反应和创新能力
     *
     * @param to 市场反应和创新能力
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(MarketSesponseTO to, BindingResult bindingResult) throws ActException {
        try {
            MarketSesponseVO vo = BeanTransform.copyProperties(marketSesponseAPI.editModel(to), MarketSesponseVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除市场反应和创新能力
     *
     * @param id 市场反应和创新能力ID
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            marketSesponseAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询项目基本信息
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(MarketSesponseDTO dto) throws ActException {
        try {
            List<MarketSesponseVO> voList = BeanTransform.copyProperties(marketSesponseAPI.pageList(dto), MarketSesponseVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}