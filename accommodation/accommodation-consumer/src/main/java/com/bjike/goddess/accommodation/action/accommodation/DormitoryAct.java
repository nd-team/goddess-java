package com.bjike.goddess.accommodation.action.accommodation;

import com.bjike.goddess.accommodation.api.DormitoryAPI;
import com.bjike.goddess.accommodation.bo.DormitoryBO;
import com.bjike.goddess.accommodation.dto.DormitoryDTO;
import com.bjike.goddess.accommodation.to.DormitoryTO;
import com.bjike.goddess.accommodation.vo.DormitoryVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 宿舍信息业务
 *
 * @Author: [xiazhili]
 * @Date: [17-3-14]
 * @Description: [宿舍信息业务]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */


@RestController
@RequestMapping("dormitoryAct")
public class DormitoryAct {
    @Autowired
    private DormitoryAPI dormitoryAPI;
    /**
     * 获取宿舍信息
     * @param dormitoryDTO 宿舍信息dto
     * @version v1
     */
    @GetMapping("v1/listDormitory")
    public Result findListDormitory(DormitoryDTO dormitoryDTO) throws ActException {
        try {
            List<DormitoryVO> dormitoryVOS = BeanTransform.copyProperties(
                    dormitoryAPI.listDormitory(dormitoryDTO),DormitoryVO.class,true);
            return ActResult.initialize( dormitoryVOS );
        } catch (SerException e) {
            throw  new ActException( e.getMessage());
        }
    }

    /**
     * 添加宿舍信息
     * @param dormitoryTO 宿舍信息to
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addDormitory (@Validated DormitoryTO dormitoryTO) throws ActException {
        try {
            DormitoryBO dormitoryBO = dormitoryAPI.insertDormitory(dormitoryTO);
            return ActResult.initialize( dormitoryBO);
        } catch (SerException e) {
            throw  new ActException( e.getMessage() );
        }
    }
    /**
     * 编辑宿舍信息
     * @param dormitoryTO 宿舍信息数据bo
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editDormitory (@Validated DormitoryTO dormitoryTO ) throws ActException {
        try {
            DormitoryBO dormitoryBO = dormitoryAPI.editDormitory(dormitoryTO);
            return ActResult.initialize( dormitoryBO);
        } catch (SerException e) {
            throw  new ActException( e.getMessage() );
        }
    }


    /**
     * 删除宿舍记录
     * @des 根据用户id删除宿舍信息记录
     * @param id 用户id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteDormitory(@PathVariable String id) throws ActException {
        try {
            dormitoryAPI.removeDormitory(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw  new ActException( e.getMessage());
        }
    }
    /**
     * 导入数据
     * @version v1
     */
    @PostMapping("v1/importFile")
    public Result importFile() throws ActException {
        try {
            dormitoryAPI.importFile();
            return new ActResult("importFile success!");
        } catch (SerException e) {
            throw  new ActException( e.getMessage());
        }
    }
    /**
     * 导出明细
     * @version v1
     */
    @PostMapping("v1/exportExcel")
    public Result exportExcel(String area)throws ActException {
        String  excel = null;
        try {
            excel = dormitoryAPI.exportExcel(area);
            return new ActResult(excel);
        } catch (SerException e) {
            throw  new ActException( e.getMessage() );
        }

    }
}
