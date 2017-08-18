package com.bjike.goddess.rotation.action.rotation;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.ArrangementAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.vo.OpinionVO;
import com.bjike.goddess.rotation.api.CoverRotationAPI;
import com.bjike.goddess.rotation.dto.CoverRotationDTO;
import com.bjike.goddess.rotation.excel.SonPermissionObject;
import com.bjike.goddess.rotation.to.CoverRotationOpinionTO;
import com.bjike.goddess.rotation.to.CoverRotationTO;
import com.bjike.goddess.rotation.to.GuidePermissionTO;
import com.bjike.goddess.rotation.vo.CoverRotationOpinionVO;
import com.bjike.goddess.rotation.vo.CoverRotationVO;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 岗位轮换自荐
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:18 ]
 * @Description: [ 岗位轮换自荐 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("coverrotation")
public class CoverRotationAct {

    @Autowired
    private CoverRotationAPI coverRotationAPI;
    @Autowired
    private ArrangementAPI arrangementAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    /**
     * 保存
     *
     * @param to 岗位轮换自荐传输对象
     * @return class CoverRotationVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CoverRotationTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(coverRotationAPI.save(to), CoverRotationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 岗位轮换自荐传输对象
     * @return class CoverRotationVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(ADD.class) CoverRotationTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(coverRotationAPI.update(to), CoverRotationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 岗位轮换自荐数据id
     * @return class CoverRotationVO
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(coverRotationAPI.delete(id), CoverRotationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询岗位轮换自荐数据
     *
     * @param id 岗位轮换自荐数据id
     * @return class CoverRotationVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(coverRotationAPI.getById(id), CoverRotationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 意见
     *
     * @param to 岗位轮换自荐意见传输对象
     * @return class CoverRotationOpinionVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/opinion/{id}")
    public Result opinion(@Validated(ADD.class) CoverRotationOpinionTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            to.setCoverId(to.getId());
            to.setId(null);
            return ActResult.initialize(BeanTransform.copyProperties(coverRotationAPI.opinion(to), CoverRotationOpinionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办意见
     *
     * @param to 岗位轮换自荐传输对象
     * @return class CoverRotationVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/general/opinion/{id}")
    public Result generalOpinion(@Validated(EDIT.class) CoverRotationTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(coverRotationAPI.generalOpinion(to), CoverRotationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * ArrangementAPI
     * 列表
     *
     * @param dto 岗位轮换自荐数据传输对象
     * @return class CoverRotationVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(CoverRotationDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(coverRotationAPI.maps(dto), CoverRotationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(coverRotationAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 得到岗位轮换等级
     *
     * @version v1
     */
    @GetMapping("v/findThawOpinion")
    public Result findThawOpinion() throws ActException {
        try {
            if (moduleAPI.isCheck("organize")) {
                return ActResult.initialize(BeanTransform.copyProperties(arrangementAPI.findThawOpinion(), OpinionVO.class));
            } else {
                return ActResult.initialize(null);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 岗位轮换姓名
//     *
//     * @return class FindNameVO
//     * @version v1
//     */
//    @GetMapping("v1/getName")
//    public Result getName() throws ActException {
//        try {
//            return ActResult.initialize(BeanTransform.copyProperties(coverRotationAPI.getName(), FindNameVO.class));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 岗位轮换姓名
     *
     * @version v1
     */
    @GetMapping("v1/getName")
    public Result getName() throws ActException {
        try {
            List<String> list = new ArrayList<>(0);
            if (moduleAPI.isCheck("organize")) {
                List<UserBO> userBOs = positionDetailUserAPI.findUserListInOrgan();
                if (!CollectionUtils.isEmpty(userBOs)) {
                    list = userBOs.stream().map(UserBO::getUsername).distinct().collect(Collectors.toList());
                }
            }
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            List<SonPermissionObject> hasPermissionList = coverRotationAPI.sonPermission();
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

            Boolean isHasPermission = coverRotationAPI.guidePermission(guidePermissionTO);
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

}