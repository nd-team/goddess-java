package com.bjike.goddess.oilcardmanage.action.oilcardmanage;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.oilcardmanage.api.OilCardBasicAPI;
import com.bjike.goddess.oilcardmanage.bo.OilCardBasicBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardBasicDTO;
import com.bjike.goddess.oilcardmanage.excel.SonPermissionObject;
import com.bjike.goddess.oilcardmanage.to.GuidePermissionTO;
import com.bjike.goddess.oilcardmanage.to.OilCardBasicTO;
import com.bjike.goddess.oilcardmanage.vo.OilCardBasicVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 油卡信息
 *
 * @Author: [Jason]
 * @Date: [17-3-11 上午10:51]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("basic")
public class OilCardBasicAct {

    @Autowired
    private OilCardBasicAPI oilCardBasicAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;





    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = oilCardBasicAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

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

            Boolean isHasPermission = oilCardBasicAPI.guidePermission(guidePermissionTO);
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
     * @param to 油卡基础信息
     * @return class OilCardBasicVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) OilCardBasicTO to, BindingResult bindingResult) throws ActException {

        try {
            OilCardBasicVO vo = BeanTransform.copyProperties(oilCardBasicAPI.saveOilCarBasic(to), OilCardBasicVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 油卡基础信息
     * @return class OilCardBasicVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) OilCardBasicTO to, BindingResult bindingResult) throws ActException {
        try {
            OilCardBasicVO vo = BeanTransform.copyProperties(oilCardBasicAPI.updateOilCardBasic(to), OilCardBasicVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 油卡信息记录ID
     * @version v1
     */
    @PutMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {

        try {
            oilCardBasicAPI.freezeOilCardBasic(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id id
     * @version v1
     */
    @PutMapping("v1/unfreeze/{id}")
    public Result breakFreeze(@PathVariable String id) throws ActException {

        try {
            oilCardBasicAPI.breakFreeze(id);
            return new ActResult("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 油卡信息记录ID
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {

        try {
            oilCardBasicAPI.deleteOilCardBasic(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 分页查询信息
     * @return class OilCardBasicVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(OilCardBasicDTO dto) throws ActException {
        try {
            List<OilCardBasicVO> voList = BeanTransform.copyProperties(oilCardBasicAPI.pageList(dto), OilCardBasicVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据Id查询油卡基础信息
     *
     * @param id id
     * @return class OilCardBasicVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result pageList(@PathVariable  String id) throws ActException {
        try {
            OilCardBasicVO vo = BeanTransform.copyProperties(oilCardBasicAPI.find(id), OilCardBasicVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(OilCardBasicDTO dto) throws ActException {
        try {
            if (dto.getStatus() != null) {
                dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
            }
            Long count = oilCardBasicAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有未冻结且闲置的油卡
     * @return class OilCardBasicVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/oilcard")
    public Result findOilCard() throws ActException{
        try {
            List<OilCardBasicBO> list = oilCardBasicAPI.findOilCard();
            List<OilCardBasicVO> voList = BeanTransform.copyProperties(list,OilCardBasicVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

}
