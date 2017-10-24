package com.bjike.goddess.receivable.action.receivable;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.receivable.api.BackProgressAPI;
import com.bjike.goddess.receivable.bo.BackProgressBO;
import com.bjike.goddess.receivable.bo.CollectDetailBO;
import com.bjike.goddess.receivable.dto.BackProgressDTO;
import com.bjike.goddess.receivable.excel.BackProgressExcel;
import com.bjike.goddess.receivable.to.BackProgressCollectTO;
import com.bjike.goddess.receivable.to.BackProgressTO;
import com.bjike.goddess.receivable.to.GuidePermissionTO;
import com.bjike.goddess.receivable.to.YearCollectTO;
import com.bjike.goddess.receivable.vo.BackProgressCollectVO;
import com.bjike.goddess.receivable.vo.BackProgressVO;
import com.bjike.goddess.receivable.vo.CollectDetailVO;
import com.bjike.goddess.receivable.vo.CollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 回款进度
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 02:52 ]
 * @Description: [ 回款进度 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("backprogress")
public class BackProgressAction extends BaseFileAction {
    @Autowired
    private BackProgressAPI backProgressAPI;

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

            Boolean isHasPermission = backProgressAPI.guidePermission(guidePermissionTO);
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
     * 回款进度列表总条数
     *
     * @param dto 回款进度dto
     * @des 获取所有回款进度总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BackProgressDTO dto) throws ActException {
        try {
            Long count = backProgressAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个回款进度
     *
     * @param id
     * @return class BackProgressVO
     * @des 获取一个回款进度
     * @version v1
     */
    @GetMapping("v1/back/{id}")
    public Result back(@PathVariable String id) throws ActException {
        try {
            BackProgressBO bo = backProgressAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BackProgressVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 回款进度列表
     *
     * @param dto
     * @return class BackProgressVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BackProgressDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<BackProgressVO> backProgressVOS = BeanTransform.copyProperties(backProgressAPI.list(dto), BackProgressVO.class, request);
            return ActResult.initialize(backProgressVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑回款进度
     *
     * @param to
     * @param bindingResult
     * @return class BackProgressVO
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(BackProgressTO.TestEdit.class) BackProgressTO to, BindingResult bindingResult) throws ActException {
        try {
            BackProgressBO backProgressBO = backProgressAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(backProgressBO, BackProgressVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除回款进度
     *
     * @param id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            backProgressAPI.delete(id);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<BackProgressExcel> tos = ExcelUtil.excelToClazz(is, BackProgressExcel.class, excel);
            List<BackProgressTO> tocs = new ArrayList<>();
            for (BackProgressExcel str : tos) {
                BackProgressTO backProgressTO = BeanTransform.copyProperties(str, BackProgressTO.class, "applyClearing", "influenceClearing");
                if (str.getApplyClearing().equals("是")) {
                    backProgressTO.setApplyClearing(true);
                } else {
                    backProgressTO.setApplyClearing(false);
                }
                if (str.getInfluenceClearing().equals("是")) {
                    backProgressTO.setInfluenceClearing(true);
                } else {
                    backProgressTO.setInfluenceClearing(false);
                }
                tocs.add(backProgressTO);
            }
            //注意序列化
            backProgressAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * excel模板下载
     *
     * @des 下载模板回款进度
     * @version v1
     */
    @GetMapping("v1/templateExcel")
    public Result templateExcel(HttpServletResponse response) throws ActException {
        try {
            String fileName = "回款进度导入模板.xlsx";
            super.writeOutFile(response, backProgressAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 条件汇总
     *
     * @param to
     * @return class BackProgressCollectVO
     * @des 根据地区, 外包单位, 类型, 专业, 开发票, 预收账款时间, 运营商名称, 派工情况, 实际完工状态 汇总
     * @version v1
     */
    @GetMapping("v1/backCollect")
    public Result backCollect(BackProgressCollectTO to) throws SerException {
        try {
            List<BackProgressCollectVO> collectVOS = BeanTransform.copyProperties(backProgressAPI.backProgressCollect(to), BackProgressCollectVO.class);
            return ActResult.initialize(collectVOS);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
    }

    /**
     * 获取所有地区
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areas")
    public Result areas() throws ActException {
        try {
            Set<String> areas = backProgressAPI.areas();
            return ActResult.initialize(areas);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有外包单位
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/contractors")
    public Result contractors() throws ActException {
        try {
            Set<String> contractors = backProgressAPI.contractors();
            return ActResult.initialize(contractors);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有类型
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/types")
    public Result types() throws ActException {
        try {
            Set<String> types = backProgressAPI.types();
            return ActResult.initialize(types);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有专业
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/majors")
    public Result majors() throws ActException {
        try {
            Set<String> majors = backProgressAPI.majors();
            return ActResult.initialize(majors);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有运营商名称
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/operatorNames")
    public Result operatorNames() throws ActException {
        try {
            Set<String> operatorNames = backProgressAPI.operatorNames();
            return ActResult.initialize(operatorNames);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有派工情况
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/taskCases")
    public Result taskCases() throws ActException {
        try {
            Set<String> taskCases = backProgressAPI.taskCases();
            return ActResult.initialize(taskCases);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有实际完工状态
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/completeStatus")
    public Result completeStatus() throws ActException {
        try {
            Set<String> completeStatus = backProgressAPI.completeStatus();
            return ActResult.initialize(completeStatus);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总合计
     *
     * @param to
     * @return class CollectDetailVO
     * @dec 汇总一年的金额合计
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(YearCollectTO to) throws ActException {
        try {
            List<CollectDetailVO> collectDetailVOS = BeanTransform.copyProperties(backProgressAPI.yearCollect(to), CollectDetailVO.class);
            return ActResult.initialize(collectDetailVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}