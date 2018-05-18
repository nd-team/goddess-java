package com.bjike.goddess.festival.action.festival;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.festival.api.HolidayProgrammeAPI;
import com.bjike.goddess.festival.bo.HolidayProgrammeBO;
import com.bjike.goddess.festival.dto.HolidayProgrammeDTO;
import com.bjike.goddess.festival.excel.SonPermissionObject;
import com.bjike.goddess.festival.to.GuidePermissionTO;
import com.bjike.goddess.festival.to.HolidayProgrammeTO;
import com.bjike.goddess.festival.vo.*;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 法定节假日放假方案
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:03 ]
 * @Description: [ 法定节假日放假方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("holidayprogramme")
public class HolidayProgrammeAction {

    @Autowired
    private HolidayProgrammeAPI holidayProgrammeAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private ModuleAPI moduleAPI;

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

            List<SonPermissionObject> hasPermissionList = holidayProgrammeAPI.sonPermission();
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

            Boolean isHasPermission = holidayProgrammeAPI.guidePermission(guidePermissionTO);
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
     * 法定节假日放假方案列表总条数
     *
     * @param holidayProgrammeDTO 法定节假日放假方案信息dto
     * @des 获取所有法定节假日放假方案信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(HolidayProgrammeDTO holidayProgrammeDTO) throws ActException {
        try {
            Long count = holidayProgrammeAPI.countHolidayProgramme(holidayProgrammeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个放假方案
     *
     * @param id 放假方案id
     * @return class HolidayProgrammeVO
     * @des 根据id获取一个放假方案
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            HolidayProgrammeBO holidayProgrammeBO = holidayProgrammeAPI.getOneById(id);
            HolidayProgrammeVO holidayProgrammeVO = BeanTransform.copyProperties(
                    holidayProgrammeBO, HolidayProgrammeVO.class);
            //节假日工作安排数组
            List<HolidayWorkPlanVO> holidayListBO = BeanTransform.copyProperties(holidayProgrammeBO.getHolidayWorkPlanBOList(), HolidayWorkPlanVO.class);
            //各地区紧急联系人数
            List<AreaRelationerVO> areaListBO = BeanTransform.copyProperties(holidayProgrammeBO.getAreaRelationerBOList(), AreaRelationerVO.class);
            //节假日福利数组
            List<WelfareVO> welfareListBO = BeanTransform.copyProperties(holidayProgrammeBO.getWelfareBOList(), WelfareVO.class);
            //注意事项数组
            List<NoticeThingVO> noticeListBO = BeanTransform.copyProperties(holidayProgrammeBO.getNoticeThingBOList(), NoticeThingVO.class);

            holidayProgrammeVO.setHolidayWorkPlanVOList(holidayListBO);
            holidayProgrammeVO.setAreaRelationerVOList(areaListBO);
            holidayProgrammeVO.setWelfareVOList(welfareListBO);
            holidayProgrammeVO.setNoticeThingVOList(noticeListBO);
            return ActResult.initialize(holidayProgrammeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 法定节假日放假方案列表
     *
     * @param holidayProgrammeDTO 法定节假日放假方案信息dto
     * @return class HolidayProgrammeVO
     * @des 获取所有法定节假日放假方案信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListHolidayProgramme(HolidayProgrammeDTO holidayProgrammeDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<HolidayProgrammeVO> holidayProgrammeVOList = BeanTransform.copyProperties(
                    holidayProgrammeAPI.listHolidayProgramme(holidayProgrammeDTO), HolidayProgrammeVO.class, request);
            return ActResult.initialize(holidayProgrammeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加法定节假日放假方案
     *
     * @param holidayProgrammeTO 法定节假日放假方案基本信息数据to
     * @return class HolidayProgrammeVO
     * @des 添加法定节假日放假方案
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addHolidayProgramme(@Validated({HolidayProgrammeTO.TESTAddAndEdit.class}) HolidayProgrammeTO holidayProgrammeTO, BindingResult bindingResult) throws ActException {
        try {
            HolidayProgrammeBO holidayProgrammeBO1 = holidayProgrammeAPI.addHolidayProgramme(holidayProgrammeTO);
            return ActResult.initialize(BeanTransform.copyProperties(holidayProgrammeBO1, HolidayProgrammeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑法定节假日放假方案
     *
     * @param holidayProgrammeTO 法定节假日放假方案基本信息数据bo
     * @return class HolidayProgrammeVO
     * @des 添加法定节假日放假方案
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editHolidayProgramme(@Validated({HolidayProgrammeTO.TESTAddAndEdit.class}) HolidayProgrammeTO holidayProgrammeTO) throws ActException {
        try {
            HolidayProgrammeBO holidayProgrammeBO1 = holidayProgrammeAPI.editHolidayProgramme(holidayProgrammeTO);
            return ActResult.initialize(BeanTransform.copyProperties(holidayProgrammeBO1, HolidayProgrammeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除法定节假日放假方案信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteHolidayProgramme(@PathVariable String id) throws ActException {
        try {
            holidayProgrammeAPI.deleteHolidayProgramme(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 获取所有的项目组
     *
     * @return class DepartmentDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findDepart")
    public Result findDepart(HttpServletRequest request) throws ActException {
        try {
            String userToken = request.getHeader(RpcCommon.USER_TOKEN);

            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, userToken);
                List<DepartmentDetailVO> list = BeanTransform.copyProperties(
                        departmentDetailAPI.findStatus(), DepartmentDetailVO.class, request);
                return ActResult.initialize(list);
            }
            return null;
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}