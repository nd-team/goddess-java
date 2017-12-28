package com.bjike.goddess.businesscommission.action.businesscommission;

import com.bjike.goddess.assemble.api.ModuleAPI;
//import com.bjike.goddess.businesscommission.api.CommissionQuotaAPI;
import com.bjike.goddess.businesscommission.bo.CommissionQuotaBO;
import com.bjike.goddess.businesscommission.dto.CommissionQuotaDTO;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import com.bjike.goddess.businesscommission.to.CommissionQuotaDeleteFileTO;
//import com.bjike.goddess.businesscommission.to.CommissionQuotaTO;
//import com.bjike.goddess.businesscommission.to.GuidePermissionTO;
//import com.bjike.goddess.businesscommission.vo.CommissionQuotaVO;
//import com.bjike.goddess.businessproject.api.BaseInfoManageAPI;
//import com.bjike.goddess.common.api.exception.ActException;
//import com.bjike.goddess.common.api.exception.SerException;
//import com.bjike.goddess.common.api.restful.Result;
//import com.bjike.goddess.common.consumer.action.BaseFileAction;
//import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
//import com.bjike.goddess.common.consumer.restful.ActResult;
//import com.bjike.goddess.common.utils.bean.BeanTransform;
//import com.bjike.goddess.contractcommunicat.api.ProjectContractAPI;
//import com.bjike.goddess.market.api.MarketInfoAPI;
//import com.bjike.goddess.marketactivitymanage.api.MarketServeApplyAPI;
//import com.bjike.goddess.organize.api.DepartmentDetailAPI;
//import com.bjike.goddess.organize.bo.AreaBO;
//import com.bjike.goddess.projectissuehandle.api.ProblemHandlingResultAPI;
//import com.bjike.goddess.storage.api.FileAPI;
//import com.bjike.goddess.storage.to.FileInfo;
//import com.bjike.goddess.storage.vo.FileVO;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;

/**
 * 业务提成定额表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-29 04:49 ]
 * @Description: [ 业务提成定额表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("commissionquota")
public class CommissionQuotaAction extends BaseFileAction {
//    @Autowired
//    private CommissionQuotaAPI commissionQuotaAPI;
//    @Autowired
//    private FileAPI fileAPI;
//    @Autowired
//    private ModuleAPI moduleAPI;
//    @Autowired
//    private DepartmentDetailAPI departmentDetailAPI;
//    @Autowired
//    private BaseInfoManageAPI baseInfoManageAPI;
//    @Autowired
//    private MarketInfoAPI marketInfoAPI;
//    @Autowired
//    private ProjectContractAPI projectContractAPI;
//    @Autowired
//    private ProblemHandlingResultAPI problemHandlingResultAPI;
//    @Autowired
//    private MarketServeApplyAPI marketServeApplyAPI;
//
//    /**
//     * 列表总条数
//     *
//     * @param commissionQuotaDTO 业务提成定额dto
//     * @des 获取所有业务提成定额总条数
//     * @version v1
//     */
//    @GetMapping("v1/count")
//    public Result count(CommissionQuotaDTO commissionQuotaDTO) throws ActException {
//        try {
//            Long count = commissionQuotaAPI.countCommissionQuota(commissionQuotaDTO);
//            return ActResult.initialize(count);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//
//    /**
//     * 一个业务提成定额
//     *
//     * @param id 项目业务提成定额id
//     * @return class CommissionQuotaVO
//     * @des 根据id获取项目业务提成定额
//     * @version v1
//     */
//    @GetMapping("v1/getOneById/{id}")
//    public Result getOneById(@PathVariable String id) throws ActException {
//        try {
//            CommissionQuotaVO projectCarryVO = BeanTransform.copyProperties(
//                    commissionQuotaAPI.getOneById(id), CommissionQuotaVO.class);
//            return ActResult.initialize(projectCarryVO);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 项目业务提成定额列表
//     *
//     * @param commissionQuotaDTO 项目业务提成定额信息dto
//     * @return class CommissionQuotaVO
//     * @des 获取所有项目业务提成定额信息
//     * @version v1
//     */
//    @GetMapping("v1/list")
//    public Result findListCommissionQuota(CommissionQuotaDTO commissionQuotaDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {
//            //List<CommissionQuotaBO> list = commissionQuotaAPI.listCommissionQuota(commissionQuotaDTO);
//            List<CommissionQuotaBO> list = commissionQuotaAPI.listCommissionQuota(commissionQuotaDTO);
//            List<CommissionQuotaVO> commissionQuotaVOList = new ArrayList<>();
//            list.stream().forEach(str -> {
//                CommissionQuotaVO vo = BeanTransform.copyProperties(str, CommissionQuotaVO.class);
//                commissionQuotaVOList.add(vo);
//            });
//
//            return ActResult.initialize(commissionQuotaVOList);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//
//    /**
//     * 添加项目业务提成定额
//     *
//     * @param commissionQuotaTO 项目业务提成定额基本信息数据to
//     * @return class CommissionQuotaVO
//     * @des 添加项目业务提成定额
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/add")
//    public Result addCommissionQuota(@Validated(CommissionQuotaTO.TestAdd.class) CommissionQuotaTO commissionQuotaTO, BindingResult bindingResult) throws ActException {
//        try {
//            CommissionQuotaBO commissionQuotaBO1 = commissionQuotaAPI.addCommissionQuota(commissionQuotaTO);
//            return ActResult.initialize(BeanTransform.copyProperties(commissionQuotaBO1, CommissionQuotaVO.class));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 编辑项目业务提成定额
//     *
//     * @param commissionQuotaTO 项目业务提成定额基本信息数据bo
//     * @return class CommissionQuotaVO
//     * @des 添加项目业务提成定额
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/edit")
//    public Result editCommissionQuota(@Validated(CommissionQuotaTO.TestAdd.class) CommissionQuotaTO commissionQuotaTO, BindingResult bindingResult) throws ActException {
//        try {
//            CommissionQuotaBO commissionQuotaBO1 = commissionQuotaAPI.editCommissionQuota(commissionQuotaTO);
//            return ActResult.initialize(BeanTransform.copyProperties(commissionQuotaBO1, CommissionQuotaVO.class));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 删除
//     *
//     * @param id id
//     * @des 根据id删除项目业务提成定额信息记录
//     * @version v1
//     */
//    @LoginAuth
//    @DeleteMapping("v1/delete/{id}")
//    public Result deleteCommissionQuota(@PathVariable String id) throws ActException {
//        try {
//            commissionQuotaAPI.deleteCommissionQuota(id);
//            return new ActResult("delete success!");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 上传附件
//     *
//     * @des 审核项目业务提成定额
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/uploadFile/{id}")
//    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
//        try {
//            //跟前端约定好 ，文件路径是列表id
//            // /id/....
//            String paths = "/" + id;
//            List<InputStream> inputStreams = getInputStreams(request, paths);
//            fileAPI.upload(inputStreams);
//            return new ActResult("upload success");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 文件附件列表
//     *
//     * @param id 业务提成定额id
//     * @return class FileVO
//     * @version v1
//     */
//    @GetMapping("v1/listFile/{id}")
//    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
//        try {
//            //跟前端约定好 ，文件路径是列表id
//            // /businessproject/id/....
//            String path = "/" + id;
//            FileInfo fileInfo = new FileInfo();
//            fileInfo.setPath(path);
//            Object storageToken = request.getAttribute("storageToken");
//            fileInfo.setStorageToken(storageToken.toString());
//            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
//            return ActResult.initialize(files);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 文件下载
//     *
//     * @param path 文件信息路径
//     * @version v1
//     */
//    @GetMapping("v1/downloadFile")
//    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
//        try {
//            //该文件的路径
//            Object storageToken = request.getAttribute("storageToken");
//            FileInfo fileInfo = new FileInfo();
//            fileInfo.setPath(path);
//            fileInfo.setStorageToken(storageToken.toString());
//            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
//            byte[] buffer = fileAPI.download(fileInfo);
//            writeOutFile(response, buffer, filename);
//            return new ActResult("download success");
//        } catch (Exception e) {
//            throw new ActException(e.getMessage());
//        }
//
//    }
//
//    /**
//     * 删除文件或文件夹
//     *
//     * @param commissionQuotaDeleteFileTO 多文件信息路径
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/deleteFile")
//    public Result delFile(@Validated(CommissionQuotaDeleteFileTO.TestDEL.class) CommissionQuotaDeleteFileTO commissionQuotaDeleteFileTO, HttpServletRequest request) throws SerException {
//        if (null != commissionQuotaDeleteFileTO.getPaths() && commissionQuotaDeleteFileTO.getPaths().length >= 0) {
//            Object storageToken = request.getAttribute("storageToken");
//            fileAPI.delFile(storageToken.toString(), commissionQuotaDeleteFileTO.getPaths());
//        }
//        return new ActResult("delFile success");
//    }
//
//
//    /**
//     * 导出excel
//     *
//     * @param dto 项目业务提成定额
//     * @des 导出项目业务提成定额
//     * @version v1
//     */
//    @LoginAuth
//    @GetMapping("v1/export")
//    public Result exportReport(CommissionQuotaDTO dto, HttpServletResponse response) throws ActException {
//        try {
//            String fileName = "项目业务提成定额.xlsx";
//            super.writeOutFile(response, commissionQuotaAPI.exportExcel(dto), fileName);
//            return new ActResult("导出成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        } catch (IOException e1) {
//            throw new ActException(e1.getMessage());
//        }
//    }
//
//
//    /**
//     * 功能导航权限
//     *
//     * @param guidePermissionTO 导航类型数据
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/guidePermission")
//    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {
//
//            Boolean isHasPermission = commissionQuotaAPI.guidePermission(guidePermissionTO);
//            if (!isHasPermission) {
//                //int code, String msg
//                return new ActResult(0, "没有权限", false);
//            } else {
//                return new ActResult(0, "有权限", true);
//            }
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
////    /**
////     * 获得所有的信息提供人
////     *
////     * @des 获得所有的信息提供人
////     * @version v1
////     */
////    @GetMapping("v1/listInformationProvide")
////    public Result listInformationProvide() throws ActException {
////        try {
////            List<String> list = commissionQuotaAPI.listInformationProvide();
////
////            return ActResult.initialize(list);
////        } catch (SerException e) {
////            throw new ActException(e.getMessage());
////        }
////    }
//
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
//
//    /**
//     * 获得所有的地区或项目名称或实际业务提成总额
//     *
//     * @des 获得所有的地区, 项目名称, 实际业务提成总额
//     * @version v1
//     */
//    @GetMapping("v1/listAreas")
//    public Result listAreas() throws ActException {
//        try {
//            List<CommissionQuotaBO> listCommissionQuotaBO = commissionQuotaAPI.listAreas();
//            List<CommissionQuotaVO> list = BeanTransform.copyProperties(listCommissionQuotaBO, CommissionQuotaVO.class);
//            return ActResult.initialize(list);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
////    /**
////     * 获得所有的地区
////     *
////     * @des 获得所有的地区
////     * @version v1
////     */
////    @GetMapping("v1/area")
////    public Result getArea() throws ActException {
////        try {
////            List<String> list = commissionQuotaAPI.getArea();
////            return ActResult.initialize(list);
////        } catch (SerException e) {
////            throw new ActException(e.getMessage());
////        }
////    }
//
//    /**
//     * 获得所有的地区
//     *
//     * @des 获得所有的地区
//     * @version v1
//     */
//    @GetMapping("v1/area")
//    public Result getArea() throws ActException {
//        try {
//            List<String> stringList = new ArrayList<>(0);
//            if (moduleAPI.isCheck("organize")) {
//                List<AreaBO> areaBOs = departmentDetailAPI.findArea();
//                for (AreaBO areaBO : areaBOs) {
//                    stringList.add(areaBO.getArea());
//                }
//            }
//            return ActResult.initialize(stringList);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
////    /**
////     * 获得所有的项目名称
////     *
////     * @des 获得所有的项目名称
////     * @version v1
////     */
////    @GetMapping("v1/project/name")
////    public Result getName() throws ActException {
////        try {
////            List<String> list = commissionQuotaAPI.getName();
////            return ActResult.initialize(list);
////        } catch (SerException e) {
////            throw new ActException(e.getMessage());
////        }
////    }
//
//    /**
//     * 获得所有的项目名称
//     *
//     * @des 获得所有的项目名称
//     * @version v1
//     */
//    @GetMapping("v1/project/name")
//    public Result getName() throws ActException {
//        try {
//            if (moduleAPI.isCheck("businessproject")) {
//                Set<String> set = baseInfoManageAPI.allInnerProjects();
//                return ActResult.initialize(set);
//            } else {
//                return ActResult.initialize(null);
//            }
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 获得所有的实际业务提成总额
//     *
//     * @des 获得所有的实际业务提成总额
//     * @version v1
//     */
//    @GetMapping("v1/actualAmount")
//    public Result getActualAmount() throws ActException {
//        try {
//            List<Double> list = commissionQuotaAPI.getActualAmount();
//            return ActResult.initialize(list);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

}