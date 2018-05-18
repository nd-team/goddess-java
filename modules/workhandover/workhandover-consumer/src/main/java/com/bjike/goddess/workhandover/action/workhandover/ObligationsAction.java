package com.bjike.goddess.workhandover.action.workhandover;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workhandover.api.ObligationsAPI;
import com.bjike.goddess.workhandover.bo.ObligationsBO;
import com.bjike.goddess.workhandover.dto.ObligationsDTO;
import com.bjike.goddess.workhandover.to.ObligationsTO;
import com.bjike.goddess.workhandover.vo.ObligationsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 工作交接责任义务
 *
 * @Author: [ chenyang ]
 * @Date: [ 2017-11-13 06:26 ]
 * @Description: [ 工作交接时间规范 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("obligations")
public class ObligationsAction {

    @Autowired
    ObligationsAPI obligationsAPI;

    /**
     * 工作交接责任义务列表
     *
     * @param dto
     * @param request
     * @return
     * @throws ActException
     */
    @GetMapping("v1/list")
    public Result list(ObligationsDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ObligationsVO> vo = BeanTransform.copyProperties ( obligationsAPI.findObligations ( dto ), ObligationsVO.class, request );
            return ActResult.initialize ( vo );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }

    }

    /**
     * 添加工作交接责任义务
     *
     * @param to
     * @param request
     * @return
     * @throws ActException
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ObligationsTO to, BindingResult request) throws ActException {
        try {
            ObligationsBO bo = obligationsAPI.insertObligations ( to );
            return ActResult.initialize ( bo );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }

    }

    /**
     * 编辑工作交接责任义务
     *
     * @param to
     * @param result
     * @return
     * @throws ActException
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ObligationsTO to, BindingResult result) throws ActException {
        try {
            ObligationsBO bo = obligationsAPI.editObligations ( to );
            return ActResult.initialize ( bo );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 删除工作交接责任义务
     *
     * @param id
     * @return
     * @throws ActException
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            obligationsAPI.removeObligations ( id );
            return new ActResult ( "删除成功" );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

}