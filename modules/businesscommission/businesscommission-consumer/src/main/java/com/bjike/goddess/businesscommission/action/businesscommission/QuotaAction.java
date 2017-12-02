package com.bjike.goddess.businesscommission.action.businesscommission;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.businesscommission.api.QuotaAPI;
import com.bjike.goddess.businesscommission.bo.QuotaBO;
import com.bjike.goddess.businesscommission.bo.QuotaCollectBO;
import com.bjike.goddess.businesscommission.dto.QuotaDTO;
import com.bjike.goddess.businesscommission.to.GuidePermissionTO;
import com.bjike.goddess.businesscommission.to.QuotaDeleteFileTO;
import com.bjike.goddess.businesscommission.to.QuotaTO;
import com.bjike.goddess.businesscommission.vo.QuotaCollectVO;
import com.bjike.goddess.businesscommission.vo.QuotaVO;
import com.bjike.goddess.businessproject.api.SiginManageAPI;
import com.bjike.goddess.businessproject.bo.SiginManageBO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectroyalty.vo.WeightalsVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
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

/**
 * 业务提成权重分配表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-26 11:38 ]
 * @Description: [ 业务提成权重分配表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("quota")
public class QuotaAction extends BaseFileAction {
    @Autowired
    private QuotaAPI quotaAPI;
    @Autowired
    private SiginManageAPI siginManageAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 列表总条数
     *
     * @param dto 业务提成权重分配dto
     * @des 获取所有业务提成权重分配总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(QuotaDTO dto) throws ActException {
        try {
            Long count = quotaAPI.countQuota(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 一个业务提成权重分配
     *
     * @param id 项目业务提成权重分配id
     * @return class QuotaVO
     * @des 根据id获取项目业务提成权重分配
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            QuotaVO projectCarryVO = BeanTransform.copyProperties(
                    quotaAPI.getOneById(id), QuotaVO.class);
            return ActResult.initialize(projectCarryVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目业务提成权重分配列表
     *
     * @param quotaDTO 项目业务提成权重分配信息dto
     * @return class QuotaVO
     * @des 获取所有项目业务提成权重分配信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListQuota(QuotaDTO quotaDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            //List<QuotaBO> list = quotaAPI.listQuota(quotaDTO);
            List<QuotaBO> list = quotaAPI.listQuota(quotaDTO);
            List<QuotaVO> quotaVOList = new ArrayList<>();
            if(null != list && list.size() > 0) {
                list.stream().forEach(str -> {
                    QuotaVO vo = BeanTransform.copyProperties(str, QuotaVO.class);
                    quotaVOList.add(vo);
                });
            }
            return ActResult.initialize(quotaVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加项目业务提成权重分配
     *
     * @param to 项目业务提成权重分配基本信息数据to
     * @des 添加项目业务提成权重分配
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addQuota(@Validated(QuotaTO.TestAdd.class) QuotaTO to, BindingResult bindingResult) throws ActException {
        try {
            quotaAPI.addQuota(to);
            return ActResult.initialize("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目业务提成权重分配
     *
     * @param to 项目业务提成权重分配基本信息数据bo
     * @des 添加项目业务提成权重分配
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit/{id}")
    public Result editQuota(@Validated(QuotaTO.TestAdd.class) QuotaTO to, BindingResult bindingResult) throws ActException {
        try {
            quotaAPI.editQuota(to);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目业务提成权重分配信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteQuota(@PathVariable String id) throws ActException {
        try {
            quotaAPI.deleteQuota(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @des 审核项目业务提成权重分配
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String paths = "/" + id;
            List<InputStream> inputStreams = getInputStreams(request, paths);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id 业务提成权重分配id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /businessproject/id/....
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
     * @param path 文件信息路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
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
     * @param quotaDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(QuotaDeleteFileTO.TestDEL.class) QuotaDeleteFileTO quotaDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != quotaDeleteFileTO.getPaths() && quotaDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), quotaDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }


    /**
     * 导出excel
     *
     * @param dto 项目业务提成权重分配
     * @des 导出项目业务提成权重分配
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(QuotaDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "项目业务提成权重分配.xlsx";
            super.writeOutFile(response, quotaAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
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

            Boolean isHasPermission = quotaAPI.guidePermission(guidePermissionTO);
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

//    /**
//     * 获得所有的信息提供人
//     *
//     * @des 获得所有的信息提供人
//     * @version v1
//     */
//    @GetMapping("v1/listInformationProvide")
//    public Result listInformationProvide() throws ActException {
//        try {
//            if (moduleAPI.isCheck("market")) {
//                List<String> list = marketInfoAPI.getMarketInfoCollecting();
//                return ActResult.initialize(list);
//            } else {
//                return ActResult.initialize(null);
//            }
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 获取所有的业务揽接人
//     *
//     * @version v1
//     */
//    @GetMapping("v1/listBusinessContracting")
//    public Result listBusinessContracting() throws ActException {
//        try {
//            if (moduleAPI.isCheck("marketactivitymanage")) {
//                List<String> list = marketServeApplyAPI.getServePrincipal();
//                return ActResult.initialize(list);
//            } else {
//                return ActResult.initialize(null);
//            }
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//
//    /**
//     * 获得所有的业务洽谈人
//     *
//     * @des 获得所有的业务洽谈人
//     * @version v1
//     */
//    @GetMapping("v1/listBusinessNegotiation")
//    public Result listBusinessNegotiation() throws ActException {
//        try {
//            if (moduleAPI.isCheck("contractcommunicat")) {
//                List<String> list = projectContractAPI.getCommunicateUser();
//                return ActResult.initialize(list);
//            } else {
//                return ActResult.initialize(null);
//            }
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 获得所有的业务维护人
//     *
//     * @des 获得所有的业务维护人
//     * @version v1
//     */
//    @GetMapping("v1/listMaintenance")
//    public Result listMaintenance() throws ActException {
//        try {
//            if (moduleAPI.isCheck("projectissuehandle")) {
//                List<String> list = problemHandlingResultAPI.getProblemHandler();
//                return ActResult.initialize(list);
//            } else {
//                return ActResult.initialize(null);
//            }
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }


    /**
     * 内部项目名称
     *
     * @version v1
     */
    @GetMapping("v1/find/projectName")
    public Result findProjectName() throws ActException {
        try {
            List<String> list = new ArrayList<>(0);
            if (moduleAPI.isCheck("businessproject")) {
                List<String> projectNames = siginManageAPI.listInnerProject();
                list.addAll(projectNames);
//                return ActResult.initialize(projectNames);
            }
            //获取比例表中的内部项目名称
            List<String> projectNames = quotaAPI.listInnerProject();
            list.addAll(projectNames);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据内部项目名称获取地区部门立项时间是否立项
     *
     * @return class WeightalsVO
     * @version v1
     */
    @GetMapping("v1/findByProject")
    public Result findByProject(String projectName) throws ActException {
        try {
            SiginManageBO siginManageBO = siginManageAPI.findByProject(projectName);
            if (null != siginManageBO) {
                WeightalsVO weightalsVO = new WeightalsVO();
                weightalsVO.setArea(siginManageBO.getArea());
                weightalsVO.setDepartment(siginManageBO.getProjectGroup());
                weightalsVO.setBuildTime(siginManageBO.getCreateTime());
                if ("已立项".equals(siginManageBO.getMakeProject())) {
                    weightalsVO.setBuild(true);
                } else {
                    weightalsVO.setBuild(false);
                }
                return ActResult.initialize(weightalsVO);
            }
            return ActResult.initialize(null);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目名称获取是否已完工
     *
     * @version v1
     */
    @GetMapping("v1/findCompleteStatus")
    public Result findCompleteStatus(String projectName) throws ActException {
        try {
            return ActResult.initialize(siginManageAPI.findCompleteStatus(projectName));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 业务提成管理日汇总
     *
     * @return class QuotaCollectVO
     * @version v1
     */
    @GetMapping("v1/dayCollect")
    public Result dayCollect(String day) throws ActException {
        try {
            List<QuotaCollectBO> quotaBOs = quotaAPI.dayCollect(day);
            return ActResult.initialize(BeanTransform.copyProperties(quotaBOs, QuotaCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 业务提成管理周汇总
     *
     * @return class QuotaCollectVO
     * @version v1
     */
    @GetMapping("v1/weekCollect")
    public Result weekCollect(Integer year, Integer month, Integer week) throws ActException {
        try {
            List<QuotaCollectBO> quotaBOs = quotaAPI.weekCollect(year, month, week);
            return ActResult.initialize(BeanTransform.copyProperties(quotaBOs, QuotaCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 业务提成管理月汇总
     *
     * @return class QuotaCollectVO
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect(String month) throws ActException {
        try {
            List<QuotaCollectBO> quotaBOs = quotaAPI.monthCollect(month);
            return ActResult.initialize(BeanTransform.copyProperties(quotaBOs, QuotaCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 业务提成管理累计汇总
     *
     * @return class QuotaCollectVO
     * @version v1
     */
    @GetMapping("v1/totalCollect")
    public Result totalCollect() throws ActException {
        try {
            List<QuotaCollectBO> quotaBOs = quotaAPI.totalCollect();
            return ActResult.initialize(BeanTransform.copyProperties(quotaBOs, QuotaCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}