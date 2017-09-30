package com.bjike.goddess.dataprogress.action.dataprogress;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.dataprogress.api.DataProgressMainAPI;
import com.bjike.goddess.dataprogress.bo.DataProgressMainBO;
import com.bjike.goddess.dataprogress.dto.DataProgressChildDTO;
import com.bjike.goddess.dataprogress.dto.DataProgressMainDTO;
import com.bjike.goddess.dataprogress.to.DataProgressMainTO;
import com.bjike.goddess.dataprogress.vo.DataProgressMainVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 资料收集进度管理主表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-28 10:03 ]
 * @Description: [ 资料收集进度管理主表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("dataprogressmain")
public class DataProgressMainAction {
    @Autowired
    private DataProgressMainAPI dataProgressMainAPI;

    /**
     * 资料收集进度总条数
     *
     * @param dataProgressChildDTO
     * @throws ActException
     * @vesion v1
     */
    @GetMapping("v1/count")
    public Result count(DataProgressChildDTO dataProgressChildDTO) throws ActException {
        try {
            Long count = dataProgressMainAPI.count(dataProgressChildDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资料收集进度id
     *
     * @param id
     * @return class DataProgressMainVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/data/{id}")
    public Result data(@PathVariable String id) throws ActException {
        try {
            DataProgressMainBO bo = dataProgressMainAPI.getId(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, DataProgressMainVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资料收集进度列表
     *
     * @param dataProgressMainDTO
     * @return class DataProgressMainVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(DataProgressMainDTO dataProgressMainDTO, HttpServletRequest request) throws ActException {
        try {
            List<DataProgressMainBO> dataProgressMainBOS = BeanTransform.copyProperties(dataProgressMainAPI.list(dataProgressMainDTO), DataProgressMainVO.class, request);
            return ActResult.initialize(dataProgressMainBOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资料收集进度添加
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(DataProgressMainTO to, BindingResult result) throws ActException {
        try {
            dataProgressMainAPI.add(to);
            return ActResult.initialize("insert success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资料收集进度编辑
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(DataProgressMainTO to, BindingResult result) throws ActException {
        try {
            dataProgressMainAPI.edit(to);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 资料收集进度删除
     *
     * @param id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            dataProgressMainAPI.delete(id);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 年份
     *
     * @version v1
     */
    @GetMapping("v1/year")
    public Result yearList() throws ActException {
        try {
            //获取所有年
            List<Integer> yearList = new ArrayList<>();
            int year = LocalDate.now().getYear();

            for (int i = year - 5; i <= year + 5; i++) {
                yearList.add(i);
            }
            return ActResult.initialize(yearList);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取当前月有几周
     *
     * @param year  年份
     * @param month 月份
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findWeek/{year}/{month}")
    public Result findWeek(@PathVariable Integer year, @PathVariable Integer month) throws ActException {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= weekNum; i++) {
                list.add(i);
            }
            return ActResult.initialize(list);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 11 - 1);
        int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= weekNum; i++) {
            list.add(i);
        }
        System.out.println(list);
    }

}