package com.bjike.goddess.task.action.collect;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.dto.EmailDTO;
import com.bjike.goddess.organize.vo.ActResultOrgan;
import com.bjike.goddess.task.api.CustomizeAPI;
import com.bjike.goddess.task.api.ScheduleAPI;
import com.bjike.goddess.task.bo.CustomizeBO;
import com.bjike.goddess.task.bo.CustomizeSonBO;
import com.bjike.goddess.task.dto.CollectDTO;
import com.bjike.goddess.task.dto.CustomizeDTO;
import com.bjike.goddess.task.to.CustomizeTO;
import com.bjike.goddess.task.to.TaskNodeExcel;
import com.bjike.goddess.taskallotment.api.ProjectAPI;
import com.bjike.goddess.taskallotment.vo.ProjectVO;
import com.bjike.goddess.taskallotment.vo.TableVO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义汇总
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-25 17:06]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@DefaultProperties
@RestController
@RequestMapping("customize")
public class CustomizeAct {

    @Autowired
    private CustomizeAPI customizeAPI;
    @Autowired
    private ScheduleAPI scheduleAPI;
    @Autowired
    private ProjectAPI projectAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    /**
     * 汇总
     *
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/collect")
    public Result collect(@Validated(CollectDTO.COUNT.class) CollectDTO dto, BindingResult rs) throws ActException {
        try {
            String result = scheduleAPI.collect(dto);
            return ActResult.initialize(result);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @return class CustomizeBO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CustomizeDTO dto) throws ActException {
        try {
            List<CustomizeBO> customizeBOS = customizeAPI.list(dto);
            return ActResult.initialize(customizeBOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id id
     * @return class CustomizeBO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/customize/{id}")
    public Result customize(@PathVariable String id) throws ActException {
        try {
            CustomizeBO customizeBOS = customizeAPI.one(id);
            List<CustomizeSonBO> sons=customizeBOS.getSons();
            for (CustomizeSonBO son:sons){
                son.setTypes(son.getType().split("、"));
            }
            return ActResult.initialize(customizeBOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 数据量
     *
     * @return {name:'data',type:'int',defaultValue:'',description:'数据量.'}
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CustomizeDTO dto) throws ActException {
        try {
            return ActResult.initialize(customizeAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @PostMapping("v1/add")
    public Result count(@Validated({ADD.class}) CustomizeTO to, BindingResult rs) throws ActException {
        try {
            customizeAPI.add(to);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) CustomizeTO to, BindingResult rs) throws ActException {
        try {
            customizeAPI.edit(to);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result count(@PathVariable String id) throws ActException {
        try {
            customizeAPI.delete(id);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看详情
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/detail/{id}")
    public Result detail(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(customizeAPI.detail(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 字段对应的值
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/values")
    public Result values(@Validated({CustomizeTO.VALUE.class}) CustomizeTO to, BindingResult rs) throws ActException {
        try {
            return ActResult.initialize(scheduleAPI.values(to));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 设置通报
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/notice")
    public Result notice(@Validated({CustomizeTO.NOTICE.class}) CustomizeTO to, BindingResult rs) throws ActException {
        try {
            customizeAPI.notice(to);
            return ActResult.initialize("设置通报成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有项目
     *
     * @return class ProjectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/projects")
    public Result projects(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(projectAPI.starts(), ProjectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目id获取任务表
     *
     * @param projectId 项目id
     * @return class TableVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/tables")
    public Result tables(String projectId, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(projectAPI.tableByProjectId(projectId), TableVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 所有汇总字段
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/fileds")
    public Result fileds(@Validated(CustomizeTO.FIELD.class) CustomizeTO to, BindingResult result) throws ActException {
        try {
            List<String> list = new ArrayList<>();
            List<Field> fields = ClazzUtils.getFields(TaskNodeExcel.class);
            List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);
            for (int i = 0; i < headers.size(); i++) {
                list.add(headers.get(i).name());
            }
            list.addAll(projectAPI.fileds(to.getTablesId()));
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


//    /**
//     * 打开关闭
//     *
//     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
//     * @version v1
//     */
//    @PutMapping("v1/edit/{enable}/{id}")
//    public Result count(@PathVariable Boolean enable, @PathVariable String id) throws ActException {
//        try {
//            customizeAPI.enable(id, enable);
//            return ActResult.initialize(true);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 获取用户列表
     *
     * @return class UserVO
     * @throws ActException
     * @version v1
     * @version v1
     */
    @GetMapping("v1/users")
    public Result users(HttpServletRequest request) throws ActException {
        try {
            List<UserBO> list = positionDetailUserAPI.findUserListInOrgan();
            return ActResult.initialize(BeanTransform.copyProperties(list, UserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据姓名数组获取邮箱
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/emails")
    public Result emails(@Validated(EmailDTO.EMAIL.class) EmailDTO dto, BindingResult result) throws ActException {
//        try {
//            String[] names = dto.getNames();
//            List<String> list = internalContactsAPI.getEmails(names);
//            return ActResult.initialize(list);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
        List<String> list = new ArrayList<>(0);
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        String[] names = dto.getNames();
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < names.length; i++) {
                if (i == names.length - 1) {
//                byte[] b=names[i].getBytes("iso-8859-1");
                    sb.append(names[i]);
                } else {
//                byte[] b=names[i].getBytes("iso-8859-1");
                    sb.append(names[i]);
                }
            }
            HttpPost httpPost = new HttpPost("https://contacts.issp.bjike.com:8080/internalcontacts/v1/getEmail?names=" + sb.toString() + "");//线上
//        HttpPost httpPost = new HttpPost("http://localhost:51310/internalcontacts/v1/getEmail?names="+sb.toString()+"");//线下测试
            httpPost.setHeader("userToken", RpcContext.getContext().getAttachment("userToken"));
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("names", sb.toString()));

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps);
            entity.setContentEncoding("utf-8");
            httpPost.setEntity(entity);
//            httpPost.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
//        httpclient.execute(httpPost);
//        httpclient.getConnectionManager().shutdown();

            ActResultOrgan resultOrgan = new ActResultOrgan();
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            resultOrgan = JSON.parseObject(EntityUtils.toString(response.getEntity()), ActResultOrgan.class);
            list = (List<String>) (resultOrgan.getData());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ActResult.initialize(list);
    }

    /**
     * 获取所有公邮
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw(HttpServletRequest request) throws ActException {
//        try {
//            List<CommonalityBO> list = commonalityAPI.findThaw();
//            return ActResult.initialize(BeanTransform.copyProperties(list, CommonalityVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }

        List<String> list = new ArrayList<>(0);
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://contacts.issp.bjike.com:8080/commonality/v1/getEmails");//线上
//        HttpGet httpGet = new HttpGet("http://localhost:51310/commonality/v1/getEmails");//线下测试
        httpGet.setHeader("userToken", RpcContext.getContext().getAttachment("userToken"));

        ActResultOrgan resultOrgan = new ActResultOrgan();
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            resultOrgan = JSON.parseObject(EntityUtils.toString(response.getEntity()), ActResultOrgan.class);
            list = (List<String>) (resultOrgan.getData());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ActResult.initialize(list);

    }
}
