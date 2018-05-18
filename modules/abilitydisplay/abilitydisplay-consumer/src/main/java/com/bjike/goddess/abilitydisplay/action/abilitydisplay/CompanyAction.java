package com.bjike.goddess.abilitydisplay.action.abilitydisplay;

import com.bjike.goddess.abilitydisplay.api.CompanyAPI;
import com.bjike.goddess.abilitydisplay.bo.CompanyBO;
import com.bjike.goddess.abilitydisplay.entity.Company;
import com.bjike.goddess.abilitydisplay.entity.MyPage;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.WanyJackson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 公司
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 03:38 ]
 * @Description: [ 公司 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */

@RestController
@RequestMapping("company")
public class CompanyAction {

    @Autowired
    private CompanyAPI companyAPI;

    /**
     * 测试
     *
     * @version v1
     */
    @GetMapping("test")
    public Result test() {
        return new ActResult("success", companyAPI.test());
    }

    /**
     * 列表
     *
     * @param pageNum pageNum
     * @return class MyPage
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(Integer pageNum) throws SerException {
        MyPage page = companyAPI.getList(pageNum);
        return new ActResult("success", page);
    }

    /**
     * 添加
     *
     * @param data data
     * @version v1
     */

    @PostMapping("v1/add")
    public Result add(String data, HttpServletRequest request) throws IOException, SerException {
        System.out.println( data );
        companyAPI.add(WanyJackson.superman(data, CompanyBO.class));
        return new ActResult("success");
    }

    /**
     * 删除
     *
     * @param id id
     * @version v1
     */
    @DeleteMapping("v1/del")
    public Result del(String id) {
        System.out.println(id);
        companyAPI.del(id);
        return new ActResult();
    }

    /**
     * 编辑
     *
     * @param id id
     * @version v1
     */
    @GetMapping("v1/edi")
    public Result edi(String id) throws SerException {
        return new ActResult("success", companyAPI.edi(id));
    }

    /**
     * 更新
     *
     * @param data data
     * @version v1
     */
    @PutMapping("v1/update")
    public Result update(String data) throws IOException, SerException {
        System.out.println(data);
        CompanyBO companyBO = WanyJackson.superman(data, CompanyBO.class);
        companyAPI.update(companyBO);
        return new ActResult("success");
    }

    @GetMapping("demo")
    public ModelAndView demo(){
        return new ModelAndView("demo");
    }

}