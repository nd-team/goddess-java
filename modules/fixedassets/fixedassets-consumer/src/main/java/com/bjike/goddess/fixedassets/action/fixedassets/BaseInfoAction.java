package com.bjike.goddess.fixedassets.action.fixedassets;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.api.AccountanCourseAPI;
import com.bjike.goddess.fixedassets.api.BaseInfoAPI;
import com.bjike.goddess.fixedassets.bo.BaseInfoBO;
import com.bjike.goddess.fixedassets.dto.BaseInfoDTO;
import com.bjike.goddess.fixedassets.excel.BaseInfoExcel;
import com.bjike.goddess.fixedassets.to.BaseInfoTO;
import com.bjike.goddess.fixedassets.to.SiginManageDeleteFileTO;
import com.bjike.goddess.fixedassets.vo.BaseInfoDetailVO;
import com.bjike.goddess.fixedassets.vo.BaseInfoVO;
import com.bjike.goddess.fixedassets.vo.SummationVO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基本信息
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-23 11:41 ]
 * @Description: [ 基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("baseinfo")
public class BaseInfoAction extends BaseFileAction {
    @Autowired
    private BaseInfoAPI baseInfoAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private AccountanCourseAPI accountanCourseAPI;

    /**
     * 列表总条数
     *
     * @param baseInfoDTO 基本信息dto
     * @des 获取所有基本信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BaseInfoDTO baseInfoDTO) throws ActException {
        try {
            Long count = baseInfoAPI.countBaseInfo(baseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个基本信息
     *
     * @param id 基本信息id
     * @return class BaseInfoVO
     * @des 根据id获取基本信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            BaseInfoVO baseInfoVO = BeanTransform.copyProperties(
                    baseInfoAPI.getById(id), BaseInfoVO.class);
            return ActResult.initialize(baseInfoVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 基本信息列表
     *
     * @param baseInfoDTO 基本信息dto
     * @return class BaseInfoVO
     * @des 获取所有基本信息
     * @version v1
     */
    @GetMapping("v1/listAccount")
    public Result findListAccount(BaseInfoDTO baseInfoDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<BaseInfoVO> accountVOList = BeanTransform.copyProperties(
                    baseInfoAPI.list(baseInfoDTO), BaseInfoVO.class, request);
            return ActResult.initialize(accountVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加基本信息
     *
     * @param baseInfoTO 基本信息数据to
     * @return class BaseInfoVO
     * @des 添加基本信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addAccount(@Validated BaseInfoTO baseInfoTO, BindingResult bindingResult) throws ActException {
        try {
            BaseInfoBO baseInfoBO = baseInfoAPI.save(baseInfoTO);
            return ActResult.initialize(BeanTransform.copyProperties(baseInfoBO, BaseInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑基本信息
     *
     * @param baseInfoTO 基本信息数据bo
     * @return class BaseInfoVO
     * @des 添加账户来源
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editAccount(@Validated BaseInfoTO baseInfoTO, BindingResult bindingResult) throws ActException {
        try {
            BaseInfoBO baseInfoBO = baseInfoAPI.edit(baseInfoTO);
            return ActResult.initialize(BeanTransform.copyProperties(baseInfoBO, BaseInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除基本信息
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteAccount(@PathVariable String id) throws ActException {
        try {
            baseInfoAPI.deleteBaseInfo(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @des 导出基本信息
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "基本信息.xlsx";
            super.writeOutFile(response, baseInfoAPI.exportExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
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
            List<BaseInfoExcel> tos = ExcelUtil.excelToClazz(is, BaseInfoExcel.class, excel);
            List<BaseInfoTO> tocs = new ArrayList<>();
            for (BaseInfoExcel str : tos) {
                BaseInfoTO baseInfoTO = BeanTransform.copyProperties(str, BaseInfoTO.class, "bookDate");
                baseInfoTO.setBookDate(DateUtil.dateToString(str.getBookDate()));
                tocs.add(baseInfoTO);
            }
            //注意序列化
            baseInfoAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * excel模板下载
     *
     * @des 下载市场活动申请模板
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "市场活动申请模板.xlsx";
            super.writeOutFile(response, baseInfoAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/" + id;
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件下载
     *
     * @param path 文件路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            FileInfo fileInfo = new FileInfo();
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            fileInfo.setPath(path);
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("download success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 删除文件或文件夹
     *
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(SiginManageDeleteFileTO.TestDEL.class) SiginManageDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

    /**
     * 基本信息汇总表
     *
     * @param year  年份
     * @param month 月份
     * @return class SummationVO
     * @des 基本信息汇总表
     * @version v1
     */
    @GetMapping("v1/summation")
    public Result summation(@RequestParam Integer year, @RequestParam Integer month, HttpServletRequest request) throws ActException {
        try {
            List<SummationVO> summationVOList = BeanTransform.copyProperties(
                    baseInfoAPI.summation(year, month), SummationVO.class, request);
            return ActResult.initialize(summationVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 基本信息明细表
     *
     * @param year  年份
     * @param month 月份
     * @return class BaseInfoDetailVO
     * @des 基本信息汇总表
     * @version v1
     */
    @GetMapping("v1/baseInfo/detail")
    public Result baseInfoDetail(@RequestParam Integer year, @RequestParam Integer month, HttpServletRequest request) throws ActException {
        try {
            List<BaseInfoDetailVO> baseInfoDetailVOList = BeanTransform.copyProperties(
                    baseInfoAPI.baseInfoDetail(year, month), BaseInfoDetailVO.class, request);
            return ActResult.initialize(baseInfoDetailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有用户
     *
     * @version v1
     */
    @GetMapping("v1/allGetPerson")
    public Result allGetPerson() throws ActException {
        try {
            List<String> getPerson = new ArrayList<>();
            getPerson = baseInfoAPI.findallUser();
            return ActResult.initialize(getPerson);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 所有部门下拉值
     *
     * @version v1
     */
    @GetMapping("v1/allOrageDepartment")
    public Result allOrageDepartment() throws ActException {
        try {
            List<String> list = new ArrayList<>(0);
            List<OpinionBO> opinionBOList = departmentDetailAPI.findThawOpinion();
            if (!CollectionUtils.isEmpty(opinionBOList)) {
                list = opinionBOList.stream().map(OpinionBO::getValue).distinct().collect(Collectors.toList());
            }
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 固定资产会计科目下拉值
     *
     * @version v1
     */
    @GetMapping("v1/fixedAssetAccountCourse")
    public Result fixedAssetAccountCourse() throws ActException {
        try {
            List<String> list = accountanCourseAPI.findByFixedAssets();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取累计折旧科目
     *
     * @version v1
     */
    @GetMapping("v1/totalDepreciationAccount")
    public Result totalDepreciationAccount() throws ActException {
        try {
            String name = accountanCourseAPI.findByCourseName("累计折旧");
            return ActResult.initialize(name);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取减值准备科目
     *
     * @version v1
     */
    @GetMapping("v1/impairmentLoss")
    public Result impairmentLossAccount() throws ActException {
        try {
            String name = accountanCourseAPI.findByCourseName("减值准备");
            return ActResult.initialize(name);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取减值准备对方科目
     *
     * @version v1
     */
    @GetMapping("v1/impairmentLoss/each")
    public Result impairmentLossEach() throws ActException {
        try {
            String name = accountanCourseAPI.findByCourseName("营业外支出");
            return ActResult.initialize(name);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取折旧费用科目
     *
     * @version v1
     */
    @GetMapping("v1/depreciationExpense")
    public Result depreciationExpense() throws ActException {
        try {
            List<String> names = accountanCourseAPI.findDepreciationAccount();
            return ActResult.initialize(names);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取税金科目
     *
     * @version v1
     */
    @GetMapping("v1/taxSubjct")
    public Result taxSubjct() throws ActException {
        try {
            String name = accountanCourseAPI.findtaxSubject();
            return ActResult.initialize(name);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}