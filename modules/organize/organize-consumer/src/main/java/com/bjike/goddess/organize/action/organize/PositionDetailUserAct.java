package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.dto.PositionDetailUserDTO;
import com.bjike.goddess.organize.to.PositionDetailUserTO;
import com.bjike.goddess.organize.vo.PositionDetailUserVO;
import com.bjike.goddess.organize.vo.UserPositionVO;
import com.bjike.goddess.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户职位
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:33 ]
 * @Description: [ 用户职位 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("positiondetailuser")
public class PositionDetailUserAct {

    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    private PositionDetailUserVO assemble(PositionDetailUserVO vo) {
        String[] names = vo.getPosition().split(","), ids = vo.getPositionIds().split(",");
        vo.setPositionVo(new ArrayList<>(0));
        for (int i = 0, lent = ids.length; lent > i; i++) {
            UserPositionVO positionVO = new UserPositionVO();
            positionVO.setId(ids[i]);
            positionVO.setPosition(names[i]);
            vo.getPositionVo().add(positionVO);
        }
        return vo;
    }

    private List<PositionDetailUserVO> assemble(List<PositionDetailUserVO> vos) {
        for (PositionDetailUserVO vo : vos) {
            this.assemble(vo);
        }
        return vos;
    }

    /**
     * 保存
     *
     * @param to 用户职位传输对象
     * @return class PositionDetailUserVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) PositionDetailUserTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailUserAPI.save(to), PositionDetailUserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 用户职位传输对象
     * @return class PositionDetailUserVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) PositionDetailUserTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailUserAPI.update(to), PositionDetailUserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 用户职位数据id
     * @return class PositionDetailUserVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailUserAPI.delete(id), PositionDetailUserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据用户id查询职位详细数据
     *
     * @param id 用户id
     * @return class PositionDetailUserVO
     * @version v1
     */
    @GetMapping("v1/findPositionByUser/{id}")
    public Result findPositionByUser(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailUserAPI.findPositionByUser(id), PositionDetailUserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取用户职位数据
     *
     * @param id 用户id
     * @return class PositionDetailUserVO
     * @version v1
     */
    @GetMapping("v1/findOneByUser/{id}")
    public Result findOneByUser(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            List<PositionDetailUserVO> vos = BeanTransform.copyProperties(positionDetailUserAPI.findOneByUser(id), PositionDetailUserVO.class, request);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 用户职位数据传输对象
     * @return class PositionDetailUserVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(PositionDetailUserDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<PositionDetailUserVO> vos = BeanTransform.copyProperties(positionDetailUserAPI.maps(dto), PositionDetailUserVO.class, request);
            return ActResult.initialize(vos);
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
            return ActResult.initialize(positionDetailUserAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据ID查询用户职位
     *
     * @param id 用户职位数据id
     * @return class PositionDetailUserVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            PositionDetailUserVO vo = BeanTransform.copyProperties(positionDetailUserAPI.findById(id), PositionDetailUserVO.class, request);
            return ActResult.initialize(this.assemble(vo));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取用户列表
     *
     * @return class UserVO
     * @version v1
     */
    @GetMapping("v1/findUserList")
    public Result findUserList(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailUserAPI.findUserList(), UserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}