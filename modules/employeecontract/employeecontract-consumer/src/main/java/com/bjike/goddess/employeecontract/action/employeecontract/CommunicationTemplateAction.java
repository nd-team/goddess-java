package com.bjike.goddess.employeecontract.action.employeecontract;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.employeecontract.api.CommunicationTemplateAPI;
import com.bjike.goddess.employeecontract.bo.CommunicationTemplateBO;
import com.bjike.goddess.employeecontract.dto.CommunicationTemplateDTO;
import com.bjike.goddess.employeecontract.to.CommunicationTemplateTO;
import com.bjike.goddess.employeecontract.vo.CommunicationTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
* 各类交流沟通模板
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-11 04:28 ]
* @Description:	[ 各类交流沟通模板 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("communicationtemplate")
public class CommunicationTemplateAction {
    @Autowired
    private CommunicationTemplateAPI communicationTemplateAPI;

    /**
     * 列表
     * @param communicationTemplateDTO
     * @return class
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(CommunicationTemplateDTO communicationTemplateDTO) throws ActException{
        try {
            List<CommunicationTemplateBO> communicationTemplateBOS = communicationTemplateAPI.pageList(communicationTemplateDTO);
            List<CommunicationTemplateVO> communicationTemplateVOS = BeanTransform.copyProperties(communicationTemplateBOS,CommunicationTemplateVO.class);
            return ActResult.initialize(communicationTemplateVOS);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 添加
     * @param communicationTemplateTO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) CommunicationTemplateTO communicationTemplateTO) throws ActException{
        try {
            communicationTemplateAPI.add(communicationTemplateTO);
            return new ActResult("添加成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     * @param communicationTemplateTO
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/modify")
    public Result mofidy(@Validated(EDIT.class) CommunicationTemplateTO communicationTemplateTO) throws ActException{
        try {
            communicationTemplateAPI.modify(communicationTemplateTO);
            return new ActResult("修改成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表总条数
     * @param communicationTemplateDTO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CommunicationTemplateDTO communicationTemplateDTO) throws ActException{
        try {
            Long count = communicationTemplateAPI.count(communicationTemplateDTO);
            return ActResult.initialize(count);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询单条数据
     * @param id 数据的id
     * @return class CommunicationTemplateVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findOne")
    public Result findOne(@RequestParam String id) throws ActException{
        try {
            CommunicationTemplateBO bo = communicationTemplateAPI.findOne(id);
            CommunicationTemplateVO vo = BeanTransform.copyProperties(bo,CommunicationTemplateVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除
     * @param id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete")
    public Result delete(@RequestParam String id) throws ActException{
        try {
            communicationTemplateAPI.delete(id);
            return new ActResult("删除成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }
 }