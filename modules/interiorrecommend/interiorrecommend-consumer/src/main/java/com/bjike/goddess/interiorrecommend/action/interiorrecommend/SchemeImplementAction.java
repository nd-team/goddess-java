package com.bjike.goddess.interiorrecommend.action.interiorrecommend;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.api.SchemeImplementAPI;
import com.bjike.goddess.interiorrecommend.bo.SchemeImplementBO;
import com.bjike.goddess.interiorrecommend.dto.SchemeImplementDTO;
import com.bjike.goddess.interiorrecommend.service.SchemeImplementSer;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.SchemeImplementTO;
import com.bjike.goddess.interiorrecommend.vo.SchemeImplementVO;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.vo.EntryBasicInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
* 内部推荐方案实施
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-12 11:29 ]
* @Description:	[ 内部推荐方案实施 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("schemeimplement")
public class SchemeImplementAction {
    @Autowired
    private SchemeImplementAPI schemeImplementAPI;

//    /**
//     * 功能导航权限
//     *
//     * @param guidePermissionTO 导航类型数据
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/guidePermission")
//    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {
//
//            Boolean isHasPermission = schemeImplementAPI.guidePermission(guidePermissionTO);
//            if (!isHasPermission) {
//                //int code, String msg
//                return new ActResult(0, "没有权限", false);
//            } else {
//                return new ActResult(0, "有权限", true);
//            }
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }


    /**
     * 我要推荐
     * @param to 推荐方案实施内容
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) SchemeImplementTO to,BindingResult bindingResult) throws ActException{
        try {
            schemeImplementAPI.add(to);
            return new ActResult("添加成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除
     * @param id 推荐id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete")
    public Result delete(@RequestParam String id) throws ActException{
        try {
            schemeImplementAPI.delete(id);
            return new ActResult("删除成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     * @param to 推荐方案实施内容
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/modify")
    public Result modify(@Validated(EDIT.class) SchemeImplementTO to,BindingResult bindingResult) throws ActException{
        try {
            schemeImplementAPI.modify(to);
            return new ActResult("修改成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     * @param dto 查询条件
     * @return class SchemeImplementVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(SchemeImplementDTO dto) throws ActException{
        try {
            List<SchemeImplementBO> boList = schemeImplementAPI.pageList(dto);
            List<SchemeImplementVO> voList = BeanTransform.copyProperties(boList,SchemeImplementVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表总条数
     * @param dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SchemeImplementDTO dto) throws ActException{
        try {
            Long count = schemeImplementAPI.count(dto);
            return ActResult.initialize(count);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id查询单条数据
     * @param id 内部推荐实施id
     * @return class SchemeImplementVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/one")
    public Result findOne(@RequestParam String id) throws ActException{
        try {
            SchemeImplementBO schemeImplementBO = schemeImplementAPI.findOne(id);
            SchemeImplementVO vo =  BeanTransform.copyProperties(schemeImplementBO,SchemeImplementVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据方案名称和岗位获取推荐奖金额
     * @param type 方案名称
     * @param recommendPosition 岗位
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/award")
    public Result findAward(@RequestParam String type,@RequestParam String recommendPosition) throws ActException{
        try {
            Integer award = schemeImplementAPI.findAward(type,recommendPosition);
            return ActResult.initialize(award);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据方案名称查找推荐岗位
     * @param type 方案名称
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/position")
    public Result findPosition(@RequestParam String type) throws ActException{
        try {
            String position  = schemeImplementAPI.findPosition(type);
            return ActResult.initialize(position);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取所有入职员工姓名
     * @return class EntryBasicInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/entry")
    public Result findEntry() throws ActException{
        try {
            List<EntryBasicInfoBO> boList = schemeImplementAPI.findEntry();
            List<EntryBasicInfoVO> voList = BeanTransform.copyProperties(boList,EntryBasicInfoVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

 }