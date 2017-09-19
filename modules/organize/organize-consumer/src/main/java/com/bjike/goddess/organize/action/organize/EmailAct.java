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
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.EmailAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.dto.EmailDTO;
import com.bjike.goddess.organize.to.EmailTO;
import com.bjike.goddess.organize.vo.ActResultOrgan;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.organize.vo.EmailVO;
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
import java.util.List;

/**
 * 邮件发送
 *
 * @Author: [chenjunhao]
 * @Date: [17-3-8 上午9:54]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("email")
public class EmailAct {
    @Autowired
    private EmailAPI emailAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

    /**
     * 添加
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) EmailTO to, BindingResult result) throws ActException {
        try {
            emailAPI.add(to);
            return new ActResult("添加成功");
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
    public Result edit(@Validated(EDIT.class) EmailTO to, BindingResult result) throws ActException {
        try {
            emailAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            emailAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto dto
     * @return class EmailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(EmailDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(emailAPI.list(dto), EmailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id id
     * @return class EmailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/one/{id}")
    public Result one(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(emailAPI.one(id), EmailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总条数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result one(EmailDTO dto) throws ActException {
        try {
            return ActResult.initialize(emailAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有部门
     *
     * @return class DepartmentDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/depart")
    public Result depart(HttpServletRequest request) throws ActException {
        try {
            List<DepartmentDetailBO> list = departmentDetailAPI.findStatus();
            return ActResult.initialize(BeanTransform.copyProperties(list, DepartmentDetailVO.class, request));
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
    @GetMapping("v1/emails")
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
        String[] names=dto.getNames();
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<names.length;i++){
            if (i==names.length-1){
                sb.append(names[i]);
            }else {
                sb.append(names[i]+",");
            }
        }
//                HttpGet httpGet = new HttpGet("https://contacts.issp.bjike.com:8080/internalcontacts/v1/getEmail?names="+sb.toString()+"");//线上
        HttpGet httpGet = new HttpGet("http://localhost:51310/internalcontacts/v1/getEmail?names="+sb.toString()+"");//线下测试
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
//        HttpGet httpGet = new HttpGet("https://contacts.issp.bjike.com:8080/commonality/v1/getEmails");//线上
        HttpGet httpGet = new HttpGet("http://localhost:51310/commonality/v1/getEmails");//线下测试
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

    @GetMapping("v1/send")
    public Result send(HttpServletRequest request) throws ActException {
        try {
            emailAPI.send();
            return new ActResult("");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
