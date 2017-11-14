package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.OverWorkCountSetAPI;
import com.bjike.goddess.attendance.bo.OverWorkCountSetBO;
import com.bjike.goddess.attendance.dto.OverWorkCountSetDTO;
import com.bjike.goddess.attendance.to.OverWorkCountSetTO;
import com.bjike.goddess.attendance.vo.OverWorkCountSetVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 加班汇总通报设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:16 ]
 * @Description: [ 加班汇总通报设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("overworkcountset")
public class OverWorkCountSetAction {
    @Autowired
    private OverWorkCountSetAPI overWorkCountSetAPI;

    /**
     * 列表
     *
     * @param dto 加班汇总通报设置数据传输
     * @return class OverWorkCountSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(OverWorkCountSetDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<OverWorkCountSetBO> list = overWorkCountSetAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, OverWorkCountSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 加班汇总通报设置传输对象
     * @return class OverWorkCountSetVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) OverWorkCountSetTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            OverWorkCountSetBO bo = overWorkCountSetAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, OverWorkCountSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 加班汇总通报设置id
     * @return class OverWorkCountSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/over/work/count/set/{id}")
    public Result OverWorkCountSet(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            OverWorkCountSetBO bo = overWorkCountSetAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, OverWorkCountSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 加班汇总通报设置传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) OverWorkCountSetTO to, BindingResult result) throws ActException {
        try {
            overWorkCountSetAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 加班汇总通报设置id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            overWorkCountSetAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 加班汇总通报设置数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(OverWorkCountSetDTO dto) throws ActException {
        try {
            return ActResult.initialize(overWorkCountSetAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 定时检测发送
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/send")
    public Result send() throws ActException {
        try {
            overWorkCountSetAPI.send();
            return new ActResult("aa");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}