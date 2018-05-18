package com.bjike.goddess.organize.action.organize;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.HierarchyAPI;
import com.bjike.goddess.organize.dto.HierarchyDTO;
import com.bjike.goddess.organize.to.HierarchyTO;
import com.bjike.goddess.organize.util.PageUtils;
import com.bjike.goddess.organize.vo.HierarchyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 体系操作
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:30]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("hierarchy")
public class HierarchyAct {

    @Autowired
    private HierarchyAPI hierarchyAPI;

    /**
     * 保存体系信息
     *
     * @param to 体系传输对象
     * @return class HierarchyVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) HierarchyTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            HierarchyVO vo = BeanTransform.copyProperties(hierarchyAPI.save(to), HierarchyVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改体系信息
     *
     * @param to 体系传输对象
     * @return class HierarchyVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) HierarchyTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(hierarchyAPI.update(to), HierarchyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取正常状态的体系信息
     *
     * @return class HierarchyVO
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(hierarchyAPI.findStatus(), HierarchyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 体系数据id
     * @return class HierarchyVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(hierarchyAPI.delete(id), HierarchyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param dto 体系数据传输
     * @return class HierarchyVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(HierarchyDTO dto, HttpServletRequest request) throws ActException {
        try {
//            PageUtils pageUtils = new PageUtils(BeanTransform.copyProperties(hierarchyAPI.maps(dto), HierarchyVO.class),
//                    hierarchyAPI.getTotal().intValue(), dto.getLimit(),dto.getPage() );

            return ActResult.initialize(BeanTransform.copyProperties(hierarchyAPI.maps(dto), HierarchyVO.class, request));
//            return ActResult.initialize(pageUtils);
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
            return ActResult.initialize(hierarchyAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据ID查询体系
     *
     * @param id
     * @return class HierarchyVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(hierarchyAPI.findById(id), HierarchyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 体系数据id
     * @return class HierarchyVO
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(hierarchyAPI.congeal(id), HierarchyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id 体系数据id
     * @return class HierarchyVO
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(hierarchyAPI.thaw(id), HierarchyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新api
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
    @GetMapping("nav")
    public String list(){
        return "{\"msg\":\"success\",\"menuList\":[{\"menuId\":1,\"parentId\":0,\"parentName\":null,\"name\":\"组织结构\",\"url\":null,\"perms\":null,\"type\":0,\"icon\":\"fa fa-cog\",\"orderNum\":0,\"open\":null," +
                "\"list\":[" +
                "{\"menuId\":2,\"parentId\":1,\"parentName\":null,\"name\":\"体系\",\"url\":\"hierarchy\",\"perms\":null,\"type\":1,\"icon\":\"fa fa-user\",\"orderNum\":1,\"open\":null,\"list\":null}," +
                "{\"menuId\":31,\"parentId\":1,\"parentName\":null,\"name\":\"部门\",\"url\":\"departmentDetail\",\"perms\":null,\"type\":1,\"icon\":\"fa fa-file-code-o\",\"orderNum\":1,\"open\":null,\"list\":null}," +
                "{\"menuId\":3,\"parentId\":1,\"parentName\":null,\"name\":\"层级\",\"url\":\"modules/sys/role.html\",\"perms\":null,\"type\":1,\"icon\":\"fa fa-user-secret\",\"orderNum\":2,\"open\":null,\"list\":null}," +
                "{\"menuId\":4,\"parentId\":1,\"parentName\":null,\"name\":\"岗位\",\"url\":\"modules/sys/menu.html\",\"perms\":null,\"type\":1,\"icon\":\"fa fa-th-list\",\"orderNum\":3,\"open\":null,\"list\":null}," +
                "{\"menuId\":5,\"parentId\":1,\"parentName\":null,\"name\":\"组织构成\",\"url\":\"druid/sql.html\",\"perms\":null,\"type\":1,\"icon\":\"fa fa-bug\",\"orderNum\":4,\"open\":null,\"list\":null}" +
                "]}],\"code\":0}";
    }

    @GetMapping("hierarchy")
    public ModelAndView hierarchy(){
        return new ModelAndView("modules/hierarchy");
    }

    @GetMapping("departmentDetail")
    public ModelAndView departmentDetail(){
        return new ModelAndView("modules/departmentDetail");
    }
    /**
     * 列表
     *
     * @return class HierarchyVO
     * @version v1
     */
    @GetMapping("v1/datalist")
    public Result datalist(HierarchyDTO dto, HttpServletRequest request) throws ActException {
        try {
            PageUtils pageUtils = new PageUtils(BeanTransform.copyProperties(hierarchyAPI.maps(dto), HierarchyVO.class),
                    hierarchyAPI.getTotal().intValue(), dto.getLimit(),dto.getPage() );
            return ActResult.initialize(pageUtils);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param ids 体系数据id
     * @return class HierarchyVO
     * @version v1
     */
    @PostMapping("v1/delete")
    public Result deleteIds(String[] ids, HttpServletRequest request) throws ActException {
        try {

//            String[] arrIds = ids.split(",");
            for (int i=0;i<ids.length;i++){
                hierarchyAPI.delete(ids[i]);
            }
            return ActResult.initialize("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 冻结
     *
     * @param ids 体系数据id
     * @return class HierarchyVO
     * @version v1
     */
    @PostMapping("v1/freeze")
    public Result freeze(String[] ids, HttpServletRequest request) throws ActException {
        try {

            for (int i=0;i<ids.length;i++){
                hierarchyAPI.congeal(ids[i]);
            }
            return ActResult.initialize("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 启用
     *
     * @param ids 体系数据id
     * @return class HierarchyVO
     * @version v1
     */
    @PostMapping("v1/enable")
    public Result enable(String[] ids, HttpServletRequest request) throws ActException {
        try {

            for (int i=0;i<ids.length;i++){
                hierarchyAPI.thaw(ids[i]);
            }
            return ActResult.initialize("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }









}
