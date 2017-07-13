package com.bjike.goddess.progressmanage.action.progressmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.progressmanage.api.ProgressTableAPI;
import com.bjike.goddess.progressmanage.api.ProjectInfoAPI;
import com.bjike.goddess.progressmanage.api.TableHeadAPI;
import com.bjike.goddess.progressmanage.dto.TableHeadDTO;
import com.bjike.goddess.progressmanage.to.TableHeadTO;
import com.bjike.goddess.progressmanage.vo.ProjectListForNodeVO;
import com.bjike.goddess.progressmanage.vo.TableHeadVO;
import com.bjike.goddess.progressmanage.vo.TableListForHeadVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 进度表表头
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:08 ]
 * @Description: [ 进度表表头 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("tablehead")
public class TableHeadAct {

    @Autowired
    private ProgressTableAPI progressTableAPI;
    @Autowired
    private TableHeadAPI tableHeadAPI;
    @Autowired
    private ProjectInfoAPI projectInfoAPI;


    /**
     * 项目下拉列表
     *
     * @return class ProjectListForNodeVO
     * @version v1
     */
    @GetMapping("v1/projects")
    public Result projects(HttpServletRequest request) throws ActException {

        try {
            List<ProjectListForNodeVO> voList = BeanTransform.copyProperties(projectInfoAPI.projects(), ProjectListForNodeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 进度表下拉列表
     *
     * @return class ProjectListForNodeVO
     * @version v1
     */
    @GetMapping("v1/tables/{projectId}")
    public Result projects(@PathVariable String projectId, HttpServletRequest request) throws ActException {

        try {
            List<TableListForHeadVO> voList = BeanTransform.copyProperties(progressTableAPI.tables(projectId), TableListForHeadVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 新增
     *
     * @param to 表头
     * @return class TableHeadVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) TableHeadTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            TableHeadVO vo = BeanTransform.copyProperties(tableHeadAPI.add(to), TableHeadVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 表头
     * @return class TableHeadVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) TableHeadTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            TableHeadVO vo = BeanTransform.copyProperties(tableHeadAPI.edit(to), TableHeadVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 表头id
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            tableHeadAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @return class TableHeadVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(@Validated({TableHeadDTO.Headvalidate.class}) TableHeadDTO dto, HttpServletRequest request) throws ActException {

        try {
            List<TableHeadVO> voList = BeanTransform.copyProperties(tableHeadAPI.pageList(dto), TableHeadVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}