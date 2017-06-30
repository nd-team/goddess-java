package com.bjike.goddess.courier.action.courier;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.courier.api.CourierAPI;
import com.bjike.goddess.courier.bo.CourierBO;
import com.bjike.goddess.courier.bo.CourierCountBO;
import com.bjike.goddess.courier.bo.OtherBO;
import com.bjike.goddess.courier.dto.CourierDTO;
import com.bjike.goddess.courier.to.CourierTO;
import com.bjike.goddess.courier.vo.CourierCountVO;
import com.bjike.goddess.courier.vo.CourierVO;
import com.bjike.goddess.courier.vo.OtherVO;
import com.bjike.goddess.storage.api.FileAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 快递收发
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-28 10:25 ]
 * @Description: [ 快递收发 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("courier")
public class CourierAct extends BaseFileAction {

    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private CourierAPI courierAPI;

    /**
     * 上传文件
     *
     * @param request 请求对象
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload(HttpServletRequest request) throws ActException {
        try {
            String path = "/upload";
            List<InputStream> inputStream = this.getInputStreams(request, path);
            fileAPI.upload(inputStream);
            return new ActResult("上传成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to      快递收发信息
     * @param request 请求对象
     * @return class CourierVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) CourierTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            CourierBO bo = courierAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, CourierVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to      收发快递信息
     * @param request 请求对象
     * @return class CourierVO
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) CourierTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            CourierBO bo = courierAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, CourierVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param dto     快递收发分页信息
     * @param request 请求对象
     * @return class CourierVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CourierDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<CourierBO> list = courierAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, CourierVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      快递收发id
     * @param request 请求对象
     * @return class CourierVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/courier/{id}")
    public Result courier(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            CourierBO bo = courierAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, CourierVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 快递收发id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            courierAPI.delete(id);
            return new ActResult("删除成功！");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 日汇总
     *
     * @param request        请求对象
     * @param arrival        是否汇总地区
     * @param courierCompany 是否汇总快递公司
     * @param department     是否汇总部门
     * @param sendTime       汇总的日期
     * @return class CourierCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/dayCount/{arrival}/{courierCompany}/{department}/{sendTime}")
    public Result dayCount(HttpServletRequest request, @PathVariable boolean arrival, @PathVariable boolean courierCompany, @PathVariable boolean department, @PathVariable String sendTime) throws ActException {
        try {
            List<CourierCountBO> list = courierAPI.dayCount(arrival, sendTime, courierCompany, department);
            return ActResult.initialize(BeanTransform.copyProperties(list, CourierCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 周汇总
     *
     * @param request        请求对象
     * @param arrival        是否汇总地区
     * @param courierCompany 是否汇总快递公司
     * @param department     是否汇总部门
     * @param lastWeek       汇总的是否为上一周
     * @return class CourierCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/weekCount/{arrival}/{courierCompany}/{department}/{lastWeek}")
    public Result weekCount(HttpServletRequest request, @PathVariable boolean arrival, @PathVariable boolean courierCompany, @PathVariable boolean department, @PathVariable boolean lastWeek) throws ActException {
        try {
            List<CourierCountBO> list = courierAPI.weekCount(arrival, courierCompany, department, lastWeek);
            return ActResult.initialize(BeanTransform.copyProperties(list, CourierCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 月汇总
     *
     * @param request        请求对象
     * @param arrival        是否汇总地区
     * @param courierCompany 是否汇总快递公司
     * @param department     是否汇总部门
     * @param month          汇总的月份
     * @return class CourierCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/monthCount/{arrival}/{courierCompany}/{department}/{month}")
    public Result monthCount(HttpServletRequest request, @PathVariable boolean arrival, @PathVariable boolean courierCompany, @PathVariable boolean department, @PathVariable Integer month) throws ActException {
        try {
            List<CourierCountBO> list = courierAPI.monthCount(arrival, courierCompany, department, month);
            return ActResult.initialize(BeanTransform.copyProperties(list, CourierCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 年汇总
     *
     * @param request        请求对象
     * @param arrival        是否汇总地区
     * @param courierCompany 是否汇总快递公司
     * @param department     是否汇总部门
     * @param year           汇总的年份
     * @return class CourierCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/yearCount/{arrival}/{courierCompany}/{department}/{year}")
    public Result yearCount(HttpServletRequest request, @PathVariable boolean arrival, @PathVariable boolean courierCompany, @PathVariable boolean department, @PathVariable Integer year) throws ActException {
        try {
            List<CourierCountBO> list = courierAPI.yearCount(arrival, courierCompany, department, year);
            return ActResult.initialize(BeanTransform.copyProperties(list, CourierCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过项目组查找员工姓名
     *
     * @param request   请求对象
     * @param groupName 项目组
     * @return class OtherVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findNameByGroup/{groupName}")
    public Result findNameByGroup(HttpServletRequest request, @PathVariable String[] groupName) throws ActException {
        try {
            List<String> list = courierAPI.findNameByGroup(groupName);
            List<OtherBO> boList = new ArrayList<OtherBO>(0);
            for (String s : list) {
                OtherBO otherBO = new OtherBO();
                otherBO.setName(s);
                boList.add(otherBO);
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, OtherVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过部门查找员工姓名
     *
     * @param request        请求对象
     * @param departmentName 部门名
     * @return class OtherVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findNameByDepartment/{departmentName}")
    public Result findNameByDepartment(HttpServletRequest request, @PathVariable String[] departmentName) throws ActException {
        try {
            List<String> list = courierAPI.findNameByDepartment(departmentName);
            List<OtherBO> boList = new ArrayList<OtherBO>(0);
            for (String s : list) {
                OtherBO otherBO = new OtherBO();
                otherBO.setName(s);
                boList.add(otherBO);
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, OtherVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有的员工名
     *
     * @param request 请求对象
     * @return class OtherVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findAllNames")
    public Result findAllNames(HttpServletRequest request) throws ActException {
        try {
            List<String> list = courierAPI.findAllNames();
            List<OtherBO> boList = new ArrayList<OtherBO>(0);
            for (String s : list) {
                OtherBO otherBO = new OtherBO();
                otherBO.setName(s);
                boList.add(otherBO);
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, OtherVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有的收件地和寄件地
     *
     * @param request 请求对象
     * @return class OtherVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findAllAreas")
    public Result findAllAreas(HttpServletRequest request) throws ActException {
        try {
            Set<String> set = courierAPI.findAllAreas();
            List<OtherBO> boList = new ArrayList<OtherBO>(0);
            for (String s : set) {
                OtherBO otherBO = new OtherBO();
                otherBO.setArea(s);
                boList.add(otherBO);
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, OtherVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有的快递公司
     *
     * @param request 请求对象
     * @return class OtherVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findAllCompanys")
    public Result findAllCompanys(HttpServletRequest request) throws ActException {
        try {
            List<String> list = courierAPI.findAllCompanys();
            List<OtherBO> boList = new ArrayList<OtherBO>(0);
            for (String s : list) {
                OtherBO otherBO = new OtherBO();
                otherBO.setCompany(s);
                boList.add(otherBO);
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, OtherVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    @GetMapping("v1/findRemainSum")
//    public  Result findRemainSum(HttpServletRequest request) throws ActException{
//        try {
//            OtherBO otherBO=new OtherBO();
//            otherBO.setRemain(courierAPI.findRemainSum());
//            return ActResult.initialize(BeanTransform.copyProperties(otherBO, OtherVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
}