package com.bjike.goddess.oilcardmanage.action.oilcardmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.oilcardmanage.api.OilCardRechargeAPI;
import com.bjike.goddess.oilcardmanage.dto.OilCardRechargeDTO;
import com.bjike.goddess.oilcardmanage.excel.OilCardRechargeSetExcel;
import com.bjike.goddess.oilcardmanage.to.CompetitorDeleteFileTO;
import com.bjike.goddess.oilcardmanage.to.ExportOilcardRechargeTO;
import com.bjike.goddess.oilcardmanage.to.GuidePermissionTO;
import com.bjike.goddess.oilcardmanage.to.OilCardRechargeTO;
import com.bjike.goddess.oilcardmanage.vo.OilCardRechargeVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 油卡充值
 *
 * @Author: [Jason]
 * @Date: [17-3-15 上午11:08]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("recharge")
public class OilCardRechargeAct extends BaseFileAction {

    @Autowired
    private OilCardRechargeAPI oilCardRechargeAPI;
    @Autowired
    private FileAPI fileAPI;

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

            Boolean isHasPermission = oilCardRechargeAPI.guidePermission(guidePermissionTO);
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
     * 新增
     *
     * @param to 充卡充值信息
     * @return class OilCardRechargeVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) OilCardRechargeTO to, BindingResult bindingResult) throws ActException {

        try {
            OilCardRechargeVO vo = BeanTransform.copyProperties(oilCardRechargeAPI.saveOilCardRecharge(to), OilCardRechargeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 油卡充值信息
     * @return class OilCardRechargeVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) OilCardRechargeTO to, BindingResult bindingResult) throws ActException {

        try {
            OilCardRechargeVO vo = BeanTransform.copyProperties(oilCardRechargeAPI.updateOilCardRecharge(to), OilCardRechargeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 分页查询信息
     * @return class OilCardRechargeVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result pageList(OilCardRechargeDTO dto) throws ActException {

        try {
            List<OilCardRechargeVO> vo = BeanTransform.copyProperties(oilCardRechargeAPI.pageList(dto), OilCardRechargeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param oilCardBasicId 油卡id
     * @param startTime      开始时间
     * @param endTime        结束时间
     * @return class OilCardRechargeVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/collect")
    public Result collect(@RequestParam String oilCardBasicId, @RequestParam String startTime, @RequestParam String endTime) throws ActException {

        try {
            List<OilCardRechargeVO> vo = BeanTransform.copyProperties(oilCardRechargeAPI.collect(oilCardBasicId, startTime, endTime), OilCardRechargeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据Id查询油卡基础信息
     *
     * @param id id
     * @return class OilCardRechargeVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result pageList(@PathVariable String id) throws ActException {
        try {
            OilCardRechargeVO vo = BeanTransform.copyProperties(oilCardRechargeAPI.findBy(id), OilCardRechargeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询纪要总记录数
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(OilCardRechargeDTO dto) throws ActException {
        try {
            Long count = oilCardRechargeAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传截图附件
     *
     * @param id      id
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/upload/{id}")
    public Result upload(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/oilcardmanage/recharge/" + id;
            List<InputStream> inputStreams = super.getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            oilCardRechargeAPI.updateScreen(id);
            return new ActResult("上传成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传充值后截图附件
     *
     * @param id      id
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadScreen/{id}")
    public Result uploadScreen(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/oilcardmanage/recharge/" + id;
            List<InputStream> inputStreams = super.getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            oilCardRechargeAPI.updatePrepaid(id);
            return new ActResult("上传成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 文件附件列表
     *
     * @param id 签订与立项id
     * @return class FileVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/files/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/oilcardmanage/recharge/" + id;
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
    @LoginAuth
    @GetMapping("v1/download")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
            String filename = org.apache.commons.lang3.StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("下载成功");
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
    @PostMapping("v1/delfile")
    public Result delFile(@Validated(CompetitorDeleteFileTO.TestDEL.class) CompetitorDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("删除成功");
    }


    /**
     * 删除充值记录
     *
     * @param id
     * @throws ActException、
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            oilCardRechargeAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/leadExcel")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<OilCardRechargeSetExcel> tos = ExcelUtil.excelToClazz(is, OilCardRechargeSetExcel.class, excel);
            List<OilCardRechargeTO> toList = BeanTransform.copyProperties(tos, OilCardRechargeTO.class);
            oilCardRechargeAPI.leadExcel(toList);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
//
    /**
     * 导出Excel
     *
     * @param to 导出条件
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcel(ExportOilcardRechargeTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "油卡充值记录.xlsx";
            super.writeOutFile(response, oilCardRechargeAPI.exportExcel(to), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * excel模板下载
     *
     * @des 下载模板油卡充值记录
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "油卡充值记录模板.xlsx";
            super.writeOutFile(response, oilCardRechargeAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 更新信息
     * @param id
     * @param balance
     * @param pettyCash
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/update/information")
    public Result updateInformation(String id,Double balance,Double pettyCash) throws ActException{
        try {
            oilCardRechargeAPI.updateInformation(id,balance,pettyCash);
            return new ActResult("更新信息成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }

    }


    /**
     * 充值信息更新
     * @param id
     * @param ifRecharge
     * @param pettyCash
     * @param rechargeMoney
     * @param rechargeDate
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/update/recharge")
    public Result updateRecharge(String id,Boolean ifRecharge,Double pettyCash,Double rechargeMoney,String rechargeDate) throws ActException{
        try {
            oilCardRechargeAPI.updateRecharge(id,ifRecharge,pettyCash,rechargeMoney,rechargeDate);
            return new ActResult("充值信息更新成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 通报充值
     * @param id
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/notice/recharge")
    public Result noticeRecharge(String id) throws ActException{
        try {
            oilCardRechargeAPI.noticeRecharge(id);
            return new ActResult("通报充值成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }



}
