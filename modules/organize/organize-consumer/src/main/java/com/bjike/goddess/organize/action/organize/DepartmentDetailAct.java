package com.bjike.goddess.organize.action.organize;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
//import com.bjike.goddess.businessproject.api.BaseInfoManageAPI;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
import com.bjike.goddess.organize.to.DepartmentDetailTO;
import com.bjike.goddess.organize.vo.ActResultOrgan;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.organize.vo.OpinionVO;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 部门详细操作
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:54]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("departmentDetail")
public class DepartmentDetailAct {

    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

    /**
     * 保存部门项目组详细信息
     *
     * @param to 部门项目组传输对象
     * @return class DepartmentDetailVO
     * @version v1
     */
    @CacheEvict(value = "listCache", allEntries = true)
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) DepartmentDetailTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            DepartmentDetailVO vo = BeanTransform.copyProperties(departmentDetailAPI.save(to), DepartmentDetailVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改部门项目组详细信息
     *
     * @param to 部门项目组传输对象
     * @return class DepartmentDetailVO
     * @version v1
     */
    @CacheEvict(value = "listCache", allEntries = true)
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) DepartmentDetailTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            DepartmentDetailVO vo = BeanTransform.copyProperties(departmentDetailAPI.update(to), DepartmentDetailVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未冻结部门项目组详细信息
     *
     * @return class DepartmentDetailVO
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.findStatus(), DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 部门项目组数据id
     * @return class DepartmentDetailVO
     * @version v1
     */
    @CacheEvict(value = "listCache", key = "#id", allEntries = true, condition = "#id != ''")
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.delete(id), DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param dto 部门项目组数据传输
     * @return class DepartmentDetailVO
     * @version v1
     */
    @Cacheable(value = {"listCache"}, key = "#dto.toString()")
    @GetMapping("v1/maps")
    public Result maps(DepartmentDetailDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.view(dto), DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(departmentDetailAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据ID查询部门项目组
     *
     * @param id 部门详细数据id
     * @return class DepartmentDetailVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.getById(id), DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 部门详细数据id
     * @return class DepartmentDetailVO
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.congeal(id), DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id 部门详细数据id
     * @return class DepartmentDetailVO
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.thaw(id), DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据地区查询部门项目组
     *
     * @param area 地区
     * @return class DepartmentDetailVO
     * @version v1
     */
    @GetMapping("v1/findByArea")
    public Result findByArea(String area, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.findByArea(area), DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询地区
     *
     * @return class AreaVO
     * @version v1
     */
    @GetMapping("v1/findArea")
    public Result findArea(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.findArea(), AreaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未冻结部门选项
     *
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("v1/findThawOpinion")
    public Result findThawOpinion() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.findThawOpinion(), OpinionVO.class));  
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有内部项目名称
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allInnerProjects")
    public Result allInnerProjects() throws ActException {
//        try {
//            Set<String> set = new HashSet<>();
//            if (moduleAPI.isCheck("businessproject")) {
////                set = baseInfoManageAPI.allInnerProjects();
//            }
//            return ActResult.initialize(set);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
        List<Object> list = new ArrayList<>(0);
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet("https://businessproject.issp.bjike.com:8080/baseinfomanage/v1/allInnerProjects");//线上
        HttpGet httpGet = new HttpGet("http://localhost:51204/baseinfomanage/v1/allInnerProjects");//线下测试
        httpGet.setHeader("userToken", RpcContext.getContext().getAttachment("userToken"));

        ActResultOrgan resultOrgan = new ActResultOrgan();

        List<String> list1 =new ArrayList<>();
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            resultOrgan = JSON.parseObject(EntityUtils.toString(response.getEntity()), ActResultOrgan.class);
            list.add(resultOrgan.getData());
            list1 =  (List<String>)resultOrgan.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ActResult.initialize(list1);
    }

    /**
     * 真实编号
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/number")
    public Result number(DepartmentDetailTO to) throws ActException {
        try {
            return ActResult.initialize(departmentDetailAPI.number(to));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    @GetMapping("v1/departmentTotalPeople")
    public Result departmentTotalPeople(String dep) throws ActException {
        try {
            return ActResult.initialize(departmentDetailAPI.departmentTotalPeople(dep));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
