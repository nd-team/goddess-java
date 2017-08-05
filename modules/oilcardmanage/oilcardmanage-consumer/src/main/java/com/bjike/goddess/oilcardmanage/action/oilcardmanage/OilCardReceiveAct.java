package com.bjike.goddess.oilcardmanage.action.oilcardmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.vo.CusPermissionOperateVO;
import com.bjike.goddess.oilcardmanage.api.OilCardReceiveAPI;
import com.bjike.goddess.oilcardmanage.bo.CusPermissionOperateBO;
import com.bjike.goddess.oilcardmanage.bo.OilCardBasicBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardReceiveDTO;
import com.bjike.goddess.oilcardmanage.enums.OilCardReceiveResult;
import com.bjike.goddess.oilcardmanage.to.GuidePermissionTO;
import com.bjike.goddess.oilcardmanage.to.OilCardReceiveTO;
import com.bjike.goddess.oilcardmanage.vo.OilCardBasicVO;
import com.bjike.goddess.oilcardmanage.vo.OilCardReceiveVO;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.vo.AreaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 油卡领用
 *
 * @Author: [Jason]
 * @Date: [17-3-14 下午5:07]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("receive")
public class OilCardReceiveAct {

    @Autowired
    private OilCardReceiveAPI oilCardReceiveAPI;

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

            Boolean isHasPermission = oilCardReceiveAPI.guidePermission(guidePermissionTO);
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
     * 新增
     *
     * @param to 卡领用信息
     * @return class OilCardReceiveVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) OilCardReceiveTO to, BindingResult bindingResult) throws ActException {
        try {
            OilCardReceiveVO vo = BeanTransform.copyProperties(oilCardReceiveAPI.saveOilCardReceive(to), OilCardReceiveVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 油卡领用信息
     * @return class OilCardReceiveVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) OilCardReceiveTO to, BindingResult bindingResult) throws ActException {
        try {
            OilCardReceiveVO vo = BeanTransform.copyProperties(oilCardReceiveAPI.updateOilCardReceive(to), OilCardReceiveVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param id                   id
     * @param auditSuggestion      审核意见
     * @param OilCardReceiveResult 审核结果
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/audit/{id}")
    public Result audit(@PathVariable String id, @RequestParam String auditSuggestion, @RequestParam OilCardReceiveResult OilCardReceiveResult) throws ActException {
        try {
            oilCardReceiveAPI.auditOilCardReceive(id, auditSuggestion, OilCardReceiveResult);
            return ActResult.initialize("审核成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 油卡领用Id
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            oilCardReceiveAPI.deleteOilCardReceive(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 归还油卡领用
     *
     * @param id id
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/return/{id}")
    public Result returnOilCard(@PathVariable String id) throws ActException {

        try {
            oilCardReceiveAPI.returnOilCardReceive(id);
            return new ActResult("领用成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 分页查询信息
     * @return class OilCardReceiveVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result pageList(OilCardReceiveDTO dto) throws ActException {

        try {
            List<OilCardReceiveVO> vo = BeanTransform.copyProperties(oilCardReceiveAPI.pageList(dto), OilCardReceiveVO.class);
            if(vo != null && vo.size()> 0 ){
                vo.stream().forEach(str -> {
                    str.setOilCardBasicVO( BeanTransform.copyProperties( str.getOilCardBasicVO() , OilCardBasicVO.class ));
                });
            }
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据Id查询油卡基础信息
     *
     * @param id id
     * @return class OilCardReceiveVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result pageList(String id) throws ActException {
        try {
            OilCardReceiveVO vo = BeanTransform.copyProperties(oilCardReceiveAPI.findById(id), OilCardReceiveVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询纪要总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(OilCardReceiveDTO dto) throws ActException {
        try {
            Long count = oilCardReceiveAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有未冻结的油卡
     * @return class OilCardBasicVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/oilcard")
    public Result findOilcard() throws ActException{
        try {
            List<OilCardBasicBO> boList = oilCardReceiveAPI.findOilCard();
            List<OilCardBasicVO> voList = BeanTransform.copyProperties(boList,OilCardBasicVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有地区
     * @return class AreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/area")
    public Result findArea() throws ActException{
        try {
           List<AreaBO> areaBOS =  oilCardReceiveAPI.findArea();
           List<AreaVO> areaVOS = BeanTransform.copyProperties(areaBOS,AreaVO.class);
           return ActResult.initialize(areaVOS);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有审核人
     * @return class CusPermissionOperateVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/operate")
    public Result findOperate() throws ActException{
        try {
            List<CusPermissionOperateBO> boList = oilCardReceiveAPI.findOperate();
            List<CusPermissionOperateVO> voList = BeanTransform.copyProperties(boList,CusPermissionOperateVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

}
