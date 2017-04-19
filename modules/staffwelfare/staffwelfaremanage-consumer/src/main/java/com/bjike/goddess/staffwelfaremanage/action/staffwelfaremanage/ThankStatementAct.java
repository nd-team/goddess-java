package com.bjike.goddess.staffwelfaremanage.action.staffwelfaremanage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffwelfaremanage.api.ThankStatementAPI;
import com.bjike.goddess.staffwelfaremanage.dto.ThankStatementDTO;
import com.bjike.goddess.staffwelfaremanage.to.ThankStatementTO;
import com.bjike.goddess.staffwelfaremanage.vo.ThankStatementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 感谢语
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 09:14 ]
 * @Description: [ 感谢语 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("thankstatement")
public class ThankStatementAct {

    @Autowired
    private ThankStatementAPI thankStatementAPI;

    /**
     * 新增感谢语
     *
     * @param to 感谢语
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(ThankStatementTO to, BindingResult bindingResult) throws ActException {
        try {
            ThankStatementVO vo = BeanTransform.copyProperties(thankStatementAPI.addModel(to), ThankStatementVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑感谢语
     *
     * @param to 感谢语
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(ThankStatementTO to, BindingResult bindingResult) throws ActException {
        try {
            ThankStatementVO vo = BeanTransform.copyProperties(thankStatementAPI.editModel(to), ThankStatementVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结感谢语
     *
     * @param id 感谢语id
     * @version v1
     */
    @GetMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            thankStatementAPI.freeze(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻感谢语
     *
     * @param id 感谢语id
     * @version v1
     */
    @GetMapping("v1/breakFreeze/{id}")
    public Result breakFreeze(@PathVariable String id) throws ActException {
        try {
            thankStatementAPI.breakFreeze(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除感谢语
     *
     * @param id 感谢语id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            thankStatementAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 感谢语分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(ThankStatementDTO dto) throws ActException {
        try {
            List<ThankStatementVO> voList = BeanTransform.copyProperties(thankStatementAPI.pageList(dto), ThankStatementVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}