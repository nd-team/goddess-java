package com.bjike.goddess.royalty.action.royalty;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.royalty.api.IndexLibraryAPI;
import com.bjike.goddess.royalty.bo.IndexLibraryBO;
import com.bjike.goddess.royalty.dto.IndexLibraryDTO;
import com.bjike.goddess.royalty.entity.IndexLibrary;
import com.bjike.goddess.royalty.entity.SystemBet;
import com.bjike.goddess.royalty.excel.SonPermissionObject;
import com.bjike.goddess.royalty.to.GuidePermissionTO;
import com.bjike.goddess.royalty.to.IndexLibraryTO;
import com.bjike.goddess.royalty.vo.IndexLibraryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 指标库
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 11:23 ]
 * @Description: [ 指标库 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("indexlibrary")
public class IndexLibraryAction {
    @Autowired
    private IndexLibraryAPI indexLibraryAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result i() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = indexLibraryAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = indexLibraryAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 指标库列表总条数
     *
     * @param dto 指标库dto
     * @des 获取所有指标库
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(IndexLibraryDTO dto) throws ActException {
        try {
            Long count = indexLibraryAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个指标库
     *
     * @param id
     * @return class IndexLibraryVO
     * @des 获取一个指标库
     * @version v1
     */
    @GetMapping("v1/back/{id}")
    public Result back(@PathVariable String id) throws ActException {
        try {
            IndexLibraryBO indexLibraryBO = indexLibraryAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(indexLibraryBO, IndexLibraryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 指标库列表
     *
     * @param indexLibraryDTO 指标库dto
     * @return class IndexLibraryVO
     * @des 获取所有指标库
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(IndexLibraryDTO indexLibraryDTO, HttpServletRequest request) throws ActException {
        try {
            List<IndexLibraryVO> backVOS = BeanTransform.copyProperties(
                    indexLibraryAPI.list(indexLibraryDTO), IndexLibraryVO.class, request);
            return ActResult.initialize(backVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加指标库
     *
     * @param indexLibraryTO 指标库to
     * @return class IndexLibraryVO
     * @des 添加指标库
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(IndexLibraryTO.TestAdd.class) IndexLibraryTO indexLibraryTO, BindingResult bindingResult) throws ActException {
        try {
            IndexLibraryBO indexLibraryBO = indexLibraryAPI.insert(indexLibraryTO);
            return ActResult.initialize(BeanTransform.copyProperties(indexLibraryBO, IndexLibraryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑指标库
     *
     * @param indexLibraryTO 指标库数据to
     * @return class IndexLibraryVO
     * @des 编辑指标库
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(IndexLibraryTO.TestEdit.class) IndexLibraryTO indexLibraryTO, BindingResult bindingResult) throws ActException {
        try {
            IndexLibraryBO indexLibraryBO = indexLibraryAPI.edit(indexLibraryTO);
            return ActResult.initialize(BeanTransform.copyProperties(indexLibraryBO, IndexLibraryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除指标库
     *
     * @param id 用户id
     * @des 根据用户id删除指标库记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            indexLibraryAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取指标编号
     *
     * @des 获取指标编号集合
     * @version v1
     */
    @GetMapping("v1/indexNum")
    public Result indexNum() throws ActException {
        try {
            List<String> indexNumList = indexLibraryAPI.getIndexNum();
            return ActResult.initialize(indexNumList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取指标名称
     *
     * @des 获取指标名称集合
     * @version v1
     */
    @GetMapping("v1/indexName")
    public Result indexName() throws ActException {
        try {
            List<String> indexNameList = indexLibraryAPI.getIndexName();
            return ActResult.initialize(indexNameList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取指标库
     *
     * @param indexNum 指标编号
     * @return class IndexLibraryVO
     * @des 根据指标编号获取指标库
     * @version v1
     */
    @GetMapping("v1/getIndexLibrary")
    public Result getIndexLibrary(String indexNum) throws ActException {
        try {
            IndexLibraryBO indexLibraryBO = indexLibraryAPI.getIndexLibrary(indexNum);
            return ActResult.initialize(BeanTransform.copyProperties(indexLibraryBO, IndexLibraryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}