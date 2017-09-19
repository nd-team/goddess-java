package com.bjike.goddess.organize.action.organize;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.WorkRangeAPI;
import com.bjike.goddess.organize.bo.WorkRangeFlatBO;
import com.bjike.goddess.organize.dto.WorkRangeDTO;
import com.bjike.goddess.organize.to.DepartmentWorkRangeTO;
import com.bjike.goddess.organize.to.WorkRangeFlatTO;
import com.bjike.goddess.organize.to.WorkRangeTO;
import com.bjike.goddess.organize.vo.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 工作范围信息设置操作
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("workRange")
public class WorkRangeAct {

    @Autowired
    private WorkRangeAPI workRangeAPI;


    /**
     * 查询部门工作范围信息详细列表
     *
     * @param departmentId 部门ID
     * @param dto          部门工作范围数据传输
     * @return class WorkRangeVO
     * @version v1
     */
    @GetMapping("v1/findDepartmentWorkRangeView")
    public Result findDepartmentWorkRangeView(String departmentId, WorkRangeDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findDepartmentWorkRangeView(departmentId, dto), WorkRangeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据部门详细ID查询工作范围信息
     *
     * @param departmentId 部门详细ID
     * @return class WorkRangeVO
     * @version v1
     */
    @GetMapping("v1/findByDepartment")
    public Result findByDepartment(String departmentId, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findByDepartment(departmentId), WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据工作范围ID查询部门详细信息
     *
     * @param rangeId 工作范围ID
     * @return class DepartmentDetailVO
     * @version v1
     */
    @GetMapping("v1/findByRange")
    public Result findByRange(String rangeId, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findByRange(rangeId), DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 部门详细增加工作范围
     *
     * @param to 部门工作范围传输对象
     * @return class WorkRangeVO
     * @version v1
     */
    @PostMapping("v1/departmentAddRange")
    public Result departmentAddRange(DepartmentWorkRangeTO to, HttpServletRequest request) throws ActException {
        try {
            workRangeAPI.departmentAddRange(to);
            return new ActResult("添加成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据方向查询工作范围
     *
     * @param direction 方向
     * @return class WorkRangeVO
     * @version v1
     */
    @GetMapping("v1/findByDirection")
    public Result findByDirection(String direction, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findByDirection(direction), WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据方向和科目查询工作范围
     *
     * @param direction 方向
     * @param project   科目
     * @return class WorkRangeVO
     * @version v1
     */
    @GetMapping("v1/findByDirectionProject")
    public Result findByDirectionProject(String direction, String project, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findByDirectionProject(direction, project), WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据科目查询工作范围
     *
     * @param project 科目
     * @return class WorkRangeVO
     * @version v1
     */
    @GetMapping("v1/findByProject")
    public Result findByProject(String project, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findByProject(project), WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存工作范围信息
     *
     * @param to 工作范围传输对象
     * @return class WorkRangeVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) WorkRangeTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.save(to), WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改工作范围信息
     *
     * @param to 工作范围传输对象
     * @return class WorkRangeVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) WorkRangeTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.update(to), WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 工作范围数据id
     * @return class WorkRangeVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.delete(id), WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param dto 工作范围数据传输
     * @return class WorkRangeVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(WorkRangeDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.maps(dto), WorkRangeVO.class, request));
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
            return ActResult.initialize(workRangeAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据ID查询工作范围
     *
     * @param id 工作范围数据id
     * @return class WorkRangeVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findById(id), WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询方向
     *
     * @version v1
     */
//    @GetMapping("v1/findDirection")
//    public Result findDirection() throws ActException {
//        try {
//            List<String> list = new ArrayList<>(0);
//            if (moduleAPI.isCheck("marketdevelopment")) {
//                List<BusinessTypeBO> businessTypeBOs = businessTypeAPI.findThaw();
//                for (BusinessTypeBO businessTypeBO : businessTypeBOs) {
//                    list.add(businessTypeBO.getType());
//                }
//            }
//            return ActResult.initialize(list);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
    @GetMapping("v1/findDirection")
    public Result findDirection() throws ActException {
        List<String> list = new ArrayList<>(0);
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://marketdevelopment.issp.bjike.com:8080/businesstype/v1/findDirection");//线上
//        HttpGet httpGet = new HttpGet("http://localhost:51306/businesstype/v1/findDirection");//线下测试
        httpGet.setHeader("userToken", RpcContext.getContext().getAttachment("userToken"));

        ActResultOrgan resultOrgan = new ActResultOrgan();
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            resultOrgan = JSON.parseObject(EntityUtils.toString(response.getEntity()), ActResultOrgan.class);
//            list.add(resultOrgan.getData());
            list = (List<String>) (resultOrgan.getData());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ActResult.initialize(list);
    }


    /**
     * 查询科目
     *
     * @version v1
     */
//    @GetMapping("v1/findProject")
//    public Result findProject() throws ActException {
//        try {
//            List<String> stringList = new ArrayList<>(0);
//            if (moduleAPI.isCheck("marketdevelopment")) {
//                List<BusinessCourseBO> businessCourseBOList = businessCourseAPI.findThaw();
//                for (BusinessCourseBO businessCourseBO : businessCourseBOList) {
//                    stringList.add(businessCourseBO.getCourse());
//                }
//            }
//            if (moduleAPI.isCheck("market")) {
//                List<String> stringList1 = marketInfoAPI.getProjectName();
//                for (String str : stringList) {
//                    stringList.add(str);
//                }
//            }
//            return ActResult.initialize(stringList);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
    @GetMapping("v1/findProject")
    public Result findProject() throws ActException {
        List<String> stringList = new ArrayList<>(0);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://market.issp.bjike.com:8080/marketinfo/v1/getProjectName/name");//线上
//        HttpGet httpGet = new HttpGet("http://localhost:51604/marketinfo/v1/getProjectName/name");//线下测试
        httpGet.setHeader("userToken", RpcContext.getContext().getAttachment("userToken"));
        ActResultOrgan resultOrgan = new ActResultOrgan();
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            resultOrgan = JSON.parseObject(EntityUtils.toString(response.getEntity()), ActResultOrgan.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stringList = (List<String>) (resultOrgan.getData());

        CloseableHttpClient httpClient1 = HttpClients.createDefault();
        HttpGet httpGet1 = new HttpGet("https://marketdevelopment.issp.bjike.com:8080/businesscourse/v1/getProjectName");//线上
//        HttpGet httpGet1 = new HttpGet("http://localhost:51306/businesscourse/v1/getProjectName");//线下测试
        httpGet1.setHeader("userToken", RpcContext.getContext().getAttachment("userToken"));
        ActResultOrgan resultOrgan1 = new ActResultOrgan();
        try {
            CloseableHttpResponse response1 = httpClient1.execute(httpGet1);
            resultOrgan1 = JSON.parseObject(EntityUtils.toString(response1.getEntity()), ActResultOrgan.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> list = (List<String>) (resultOrgan1.getData());
        Set<String> set = new HashSet<>(0);
        if (null != stringList && !stringList.isEmpty()) {
            stringList.addAll(list);
            set.addAll(stringList);
        }
        return ActResult.initialize(set);
    }

    /**
     * 查询科目测试
     *
     * @version v1
     */
    @GetMapping("v1/findProjectTest")
    public Result findProjectTest() throws ActException {
        List<String> stringList = new ArrayList<>(0);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://market.issp.bjike.com:8080/marketinfo/v1/getProjectName/name");//线上
//        HttpGet httpGet = new HttpGet("http://localhost:51604/marketinfo/v1/getProjectName/name");//线下测试
        httpGet.setHeader("userToken", RpcContext.getContext().getAttachment("userToken"));
//                HttpPost httpPost = new HttpPost("");
//                List<BasicNameValuePair> data = new ArrayList<>();
//                data.add(new BasicNameValuePair("username","lake"));
//                httpPost.setEntity(new UrlEncodedFormEntity(data, Consts.UTF_8));
        ActResultOrgan resultOrgan = new ActResultOrgan();
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            resultOrgan = JSON.parseObject(EntityUtils.toString(response.getEntity()), ActResultOrgan.class);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ActResult.initialize(resultOrgan.getData());
    }

    /**
     * 查询分类
     *
     * @version v1
     */
    @GetMapping("v1/findClassify")
    public Result findClassify(HttpServletRequest request) throws ActException {
        List<String> list = new ArrayList<>(0);
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://marketdevelopment.issp.bjike.com:8080/businesstype/v1/findDirection");//线上
//        HttpGet httpGet = new HttpGet("http://localhost:51306/businesstype/v1/findDirection");//线下测试
        httpGet.setHeader("userToken", RpcContext.getContext().getAttachment("userToken"));

        ActResultOrgan resultOrgan = new ActResultOrgan();
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            resultOrgan = JSON.parseObject(EntityUtils.toString(response.getEntity()), ActResultOrgan.class);
            list = (List<String>) (resultOrgan.getData());

        } catch (IOException e) {
            e.printStackTrace();
        }
        CloseableHttpClient closeableHttpClient1 = HttpClients.createDefault();
        HttpGet httpGet1 = new HttpGet("https://market.issp.bjike.com:8080/marketinfo/v1/findMarket/getTechnologyCategory");//线上
//        HttpGet httpGet1 = new HttpGet("http://localhost:51604/marketinfo/v1/findMarket/getTechnologyCategory");//线下测试
        httpGet1.setHeader("userToken", RpcContext.getContext().getAttachment("userToken"));
        ActResultOrgan resultOrgan1 = new ActResultOrgan();
        Set<String> set = new HashSet<>(0);
        try {
            CloseableHttpResponse response1 = closeableHttpClient1.execute(httpGet1);
            resultOrgan1 = JSON.parseObject(EntityUtils.toString(response1.getEntity()), ActResultOrgan.class);
            List<String> list1 = (List<String>) (resultOrgan1.getData());
            if (null != list1 && !list1.isEmpty()) {
                list.addAll(list1);
            }
            if (null != list && !list.isEmpty()) {
                set.addAll(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ActResult.initialize(set);


//        try {
//            List<String> list = new ArrayList<>(0);
//            if (moduleAPI.isCheck("marketdevelopment")) {
//                List<BusinessTypeBO> businessTypeBOs = businessTypeAPI.findThaw();
//                for (BusinessTypeBO businessTypeBO : businessTypeBOs) {
//                    list.add(businessTypeBO.getType());
//                }
//            }
//            if (moduleAPI.isCheck("market")) {
//                List<String> stringList = marketInfoAPI.getTechnologyCategory();
//                for (String str : stringList) {
//                    list.add(str);
//                }
//            }
//            return ActResult.initialize(list);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
    }

    /**
     * 关闭
     *
     * @param id 部门工作范围数据id
     * @return class WorkRangeVO
     * @version v1
     */
    @PatchMapping("v1/close/{id}")
    public Result close(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.close(id), WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 开启
     *
     * @param id 部门工作范围数据id
     * @return class WorkRangeVO
     * @version v1
     */
    @PatchMapping("v1/open/{id}")
    public Result open(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.open(id), WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取工作范围选项
     *
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("v1/findThawOpinion")
    public Result findThawOpinion() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workRangeAPI.findThawOpinion(), OpinionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 平台列表
     *
     * @return class WorkRangeFlatBO
     * @version v1
     */
    @GetMapping("v1/flat/list")
    public Result getFlatList(WorkRangeDTO dto) throws ActException {
        try {
            List<WorkRangeFlatBO> workRangeFlatBOs = workRangeAPI.getFlatList(dto);
            List<WorkRangeFlatVO> list = BeanTransform.copyProperties(workRangeFlatBOs, WorkRangeFlatVO.class);
            return ActResult.initialize(workRangeFlatBOs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 平台添加
     *
     * @version v1
     */
    @PostMapping("v1/flat/add")
    public Result flatAdd(@Validated(ADD.class) WorkRangeFlatTO to, BindingResult result) throws ActException {
        try {
            workRangeAPI.flatAdd(to);
            return ActResult.initialize("ADD SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据平台业务方向分类获取平台对象
     *
     * @return class WorkRangeFlatBO
     * @version v1
     */
    @GetMapping("v1/flat/find/{direction}")
    public Result findByFlatDirection(@PathVariable String direction) throws ActException {
        try {
            List<WorkRangeFlatBO> workRangeFlatBOs = workRangeAPI.findByFlatDirection(direction);
            return ActResult.initialize(workRangeFlatBOs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 平台编辑
     *
     * @version v1
     */
    @PutMapping("v1/flat/update/{directionEdit}")
    public Result flatUpdate(@Validated(EDIT.class) WorkRangeFlatTO to, BindingResult result) throws ActException {
        try {
            workRangeAPI.flatUpdate(to);
            return ActResult.initialize("EDIT SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 平台删除
     *
     * @version v1
     */
    @DeleteMapping("v1/flat/delete/{direction}")
    public Result faltDelete(@PathVariable String direction) throws ActException {
        try {
            workRangeAPI.faltDelete(direction);
            return ActResult.initialize("DELETE SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 平台关闭
     *
     * @param direction 业务方向分类
     * @version v1
     */
    @PatchMapping("v1/flat/close/{direction}")
    public Result flatClose(@PathVariable String direction) throws ActException {
        try {
            workRangeAPI.flatClose(direction);
            return ActResult.initialize("CLOSE SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 平台重启
     *
     * @param direction 业务方向分类
     * @version v1
     */
    @PatchMapping("v1/flat/open/{direction}")
    public Result open(@PathVariable String direction) throws ActException {
        try {
            workRangeAPI.flatOpen(direction);
            return ActResult.initialize("OPEN SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取平台总条数
     *
     * @version v1
     */
    @GetMapping("v1/flat/total")
    public Result getFlatTotal() throws ActException {
        try {
            return ActResult.initialize(workRangeAPI.getFlatTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
