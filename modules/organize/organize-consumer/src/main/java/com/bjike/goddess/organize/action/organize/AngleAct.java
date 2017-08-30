package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.AngleAPI;
import com.bjike.goddess.organize.bo.AngleBO;
import com.bjike.goddess.organize.dto.AngleDTO;
import com.bjike.goddess.organize.to.AngleTO;
import com.bjike.goddess.organize.vo.AngleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 角度操作
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("angle")
public class AngleAct {

    @Autowired
    private AngleAPI angleAPI;

    /**
     * 保存角度信息
     *
     * @param to 角度传输对象
     * @return class AngleVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) AngleTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(angleAPI.saveAsTo(to), AngleVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改角度信息
     *
     * @param to 角度传输对象
     * @return class AngleVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) AngleTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            angleAPI.updateAsTo(to);
            return ActResult.initialize(BeanTransform.copyProperties(to, AngleVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取未冻结角度数据
     *
     * @return class AngleVO
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus(HttpServletRequest request) throws ActException {
        try {
            List<AngleBO> bos = angleAPI.findStatus();
            List<AngleVO> vos = BeanTransform.copyProperties(bos, AngleVO.class, request);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 关闭
     *
     * @param id 角度数据id
     * @return class AngleVO
     * @version v1
     */
    @PutMapping("v1/close/{id}")
    public Result close(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(angleAPI.close(id), AngleVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 开启
     *
     * @param id 角度数据id
     * @return class AngleVO
     * @version v1
     */
    @PutMapping("v1/open/{id}")
    public Result open(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(angleAPI.open(id), AngleVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 角度数据id
     * @return class AngleVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(angleAPI.delete(id), AngleVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param dto 角度数据传输
     * @return class AngleVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(AngleDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(angleAPI.maps(dto), AngleVO.class, request));
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
            return ActResult.initialize(angleAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据ID查询角度
     *
     * @param id 角度数据id
     * @return class AngleVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(angleAPI.findById(id), AngleVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
