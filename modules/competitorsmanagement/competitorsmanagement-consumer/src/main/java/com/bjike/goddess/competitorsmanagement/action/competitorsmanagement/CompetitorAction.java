package com.bjike.goddess.competitorsmanagement.action.competitorsmanagement;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.competitorsmanagement.api.CompetitorAPI;
import com.bjike.goddess.competitorsmanagement.bo.CompetitorBO;
import com.bjike.goddess.competitorsmanagement.bo.OrganizationSDBO;
import com.bjike.goddess.competitorsmanagement.dto.CompetitorDTO;
import com.bjike.goddess.competitorsmanagement.entity.OrganizationSD;
import com.bjike.goddess.competitorsmanagement.excel.CompetitorExcel;
import com.bjike.goddess.competitorsmanagement.excel.SonPermissionObject;
import com.bjike.goddess.competitorsmanagement.to.CompetitorTO;
import com.bjike.goddess.competitorsmanagement.vo.CompetitorVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.entity.File;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 竞争对手管理
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-13 09:45 ]
 * @Description: [ 竞争对手管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("competitor")
public class CompetitorAction extends BaseFileAction {

    @Autowired
    private CompetitorAPI competitorAPI;
    @Autowired
    private FileAPI fileAPI;
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
    public Result setButtonPermission() throws ActException {
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


    //取得竞争对手管理列表
    @GetMapping("v1/getlist")
    public Result getList() throws SerException {
        return new ActResult("success", BeanTransform.copyProperties(competitorAPI.getList(), CompetitorVO.class));
    }

    //添加单个竞争对手
    @PostMapping("v1/add")
    public Result add(CompetitorTO to) throws SerException {
        competitorAPI.add(to);
        return new ActResult("success");
    }

    //编辑单个竞争对手
    @GetMapping("v1/editor/{id}")
    public Result editor(@PathVariable String id) throws SerException {
        CompetitorVO vo = BeanTransform.copyProperties(competitorAPI.editor(id), CompetitorVO.class);
        return new ActResult("success", vo);
    }

    //更新单个竞争对手
    @PutMapping("v1/update")
    public Result upDate(CompetitorTO to) throws SerException {
        competitorAPI.upDate(to);
        return new ActResult("success");
    }

    //导出竞争对手excel
    @GetMapping("v1/export")
    public Result exportExcel(CompetitorDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "竞争对手管理.xlsx";
            super.writeOutFile(response, competitorAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    //导入竞争对手excel
    @PostMapping("v1/import")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<CompetitorExcel> tos = ExcelUtil.excelToClazz(is, CompetitorExcel.class, excel);
            List<CompetitorTO> tocs = new ArrayList<>();
            for (CompetitorExcel str : tos) {
                CompetitorTO siginManageTO = BeanTransform.copyProperties(str, CompetitorTO.class);
                tocs.add(siginManageTO);
            }
            //注意序列化
            competitorAPI.importExcel(tocs);
            return new ActResult("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (ActException e) {
            e.printStackTrace();
        }
        return new ActResult("i don't know why too");
    }

    //上传竞争对手附件
    @PostMapping("v1/fileUpload/{id}")
    public Result fileUpload(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String paths = "/competitorsmanagement/competitor/" + id;
            List<InputStream> inputStreams = getInputStreams(request, paths);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    //查看竞争对手附件列表
    @GetMapping("v1/fileList/{id}")
    public Result fileList(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String path = "/competitorsmanagement/competitor/" + id;
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

    //下载竞争对手附件
    @GetMapping("v1/fileDownload")
    public Result fileDownload(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    //根据 项目名称 地区 竞争对手名称 业务类型 搜索
    @GetMapping("v1/search/{condition}")
    public Result search(@PathVariable String condition) throws SerException {
        List<CompetitorVO> vos = BeanTransform.copyProperties(competitorAPI.search(condition), CompetitorVO.class);
        return new ActResult("success",vos);
    }

    //组织结构详情
    @GetMapping("v1/org")
    public Result organizationSD(CompetitorTO to) throws SerException {
        OrganizationSD sdbo = competitorAPI.organizationSD("64f9c059-dada-4bef-9813-dfdede68c3c4");
        return new ActResult("success",sdbo);
    }
}