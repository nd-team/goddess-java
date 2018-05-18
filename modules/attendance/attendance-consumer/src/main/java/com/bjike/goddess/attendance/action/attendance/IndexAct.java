package com.bjike.goddess.attendance.action.attendance;

import com.alibaba.fastjson.JSONObject;
import com.bjike.goddess.attendance.api.FinanceAttendanceAPI;
import com.bjike.goddess.attendance.dto.FinanceAttendanceDTO;
import com.bjike.goddess.attendance.entity.FinanceAttendance;
import com.bjike.goddess.attendance.entity.PageUtils;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.WanyJackson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: [chenjunhao]
 * @Date: [2018-01-26 11:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("index")
public class IndexAct {

    @Autowired
    private FinanceAttendanceAPI financeAttendanceAPI;

    /**
     * 跳转到本项目
     * @return
     */
    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    /**
     * 跳转到指定路径
     * @return
     */
    @GetMapping("list")
    public ModelAndView list(){
        return new ModelAndView("modules/user");
    }

    /**
     * 请求数据列
     * @return
     */
    @RequestMapping("datalist")
    public Result datalist(String page,String limit,String name) throws SerException{
        JSONObject jsonObject = new JSONObject();
        PageUtils pageUtils = financeAttendanceAPI.findAll(page,limit,name==null?"":name);
        jsonObject.put("page",pageUtils);

        return new ActResult("",jsonObject) ;
    }

    /**
     * 保存数据列
     * @return
     */
    @PostMapping("save")
    public Result save(String data) throws SerException, IOException {
        FinanceAttendanceDTO dto = WanyJackson.superman(data,FinanceAttendanceDTO.class);

        financeAttendanceAPI.save(dto);

        return new ActResult("") ;
    }


    /**
     * 删除数据列
     * @return
     */
    @PostMapping("delete")
    public Result delete( @RequestBody String[] ids) throws SerException, IOException {
        financeAttendanceAPI.delete(ids);
        return new ActResult("") ;
    }


    /**
     *  根据iD获取对象
     * @param  id
     * @return
     * @throws SerException
     * @throws IOException
     */
    @GetMapping("info/{id}")
    public Result info ( @PathVariable String id) throws SerException, IOException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("user",financeAttendanceAPI.findById(id));

        return new ActResult("",jsonObject) ;
    }


    /**
     * 更新数据列
     * @return
     */
    @PostMapping("update")
    public Result update(String data) throws SerException, IOException {
        FinanceAttendanceDTO dto = WanyJackson.superman(data,FinanceAttendanceDTO.class);
        financeAttendanceAPI.Update(dto);
        return new ActResult("") ;
    }




}
