package com.bjike.goddess.bidding.action.bidding;

import com.bjike.goddess.bidding.api.BiddingInfoAPI;
import com.bjike.goddess.bidding.bo.BiddingInfoBO;
import com.bjike.goddess.bidding.bo.InfoBO;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.excel.SonPermissionObject;
import com.bjike.goddess.bidding.to.BiddingDeleteFileTO;
import com.bjike.goddess.bidding.to.BiddingInfoTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.bidding.to.SearchTO;
import com.bjike.goddess.bidding.vo.BiddingInfoCollectVO;
import com.bjike.goddess.bidding.vo.BiddingInfoVO;
import com.bjike.goddess.bidding.vo.InfoVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.idem.Info;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 招标信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:48:29.980 ]
 * @Description: [ 招标信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("biddinginfo")
public class BiddingInfoAction extends BaseFileAction {
    @Autowired
    private BiddingInfoAPI biddingInfoAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    private static Logger logger = Logger.getLogger(BiddingInfoAction.class);

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

            List<SonPermissionObject> hasPermissionList = biddingInfoAPI.sonPermission();
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

            Boolean isHasPermission = biddingInfoAPI.guidePermission(guidePermissionTO);
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
     * 招标信息列表总条数
     *
     * @param biddingInfoDTO 招标信息dto
     * @des 获取所有招标信息
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BiddingInfoDTO biddingInfoDTO) throws ActException {
        try {
            Long count = biddingInfoAPI.countBiddingInfo(biddingInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个招标信息
     *
     * @param id
     * @return class BiddingInfoVO
     * @des 获取一个招标信息
     * @version v1
     */
    @GetMapping("v1/info/{id}")
    public Result info(@PathVariable String id) throws ActException {
        try {
            BiddingInfoBO biddingInfoBO = biddingInfoAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(biddingInfoBO, BiddingInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 招标信息列表
     *
     * @param biddingInfoDTO 招标信息dto
     * @return class BiddingInfoVO
     * @des 获取所有招标信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BiddingInfoDTO biddingInfoDTO, HttpServletRequest request) throws ActException {
        try {
            List<BiddingInfoVO> biddingInfoVOS = BeanTransform.copyProperties(
                    biddingInfoAPI.findListBiddingInfo(biddingInfoDTO), BiddingInfoVO.class, request);
            return ActResult.initialize(biddingInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加招标信息
     *
     * @param biddingInfoTO 招标信息to
     * @return class BiddingInfoVO
     * @des 添加招标信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) BiddingInfoTO biddingInfoTO, BindingResult bindingResult) throws ActException {
        try {
            BiddingInfoBO biddingInfoBO = biddingInfoAPI.insertBiddingInfo(biddingInfoTO);
            return ActResult.initialize(BeanTransform.copyProperties(biddingInfoBO, BiddingInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑招标信息
     *
     * @param biddingInfoTO 招标信息数据to
     * @return class BiddingInfoVO
     * @des 编辑招标信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) BiddingInfoTO biddingInfoTO, BindingResult bindingResult) throws ActException {
        try {
            BiddingInfoBO biddingInfoBO = biddingInfoAPI.editBiddingInfo(biddingInfoTO);
            return ActResult.initialize(BeanTransform.copyProperties(biddingInfoBO, BiddingInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除招标信息
     *
     * @param id 用户id
     * @des 根据用户id删除招标信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            biddingInfoAPI.removeBiddingInfo(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总招标信息
     *
     * @param cities 地市
     * @return class BiddingInfoCollectVO
     * @des 汇总招标信息
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(@RequestParam String[] cities) throws ActException {
        try {
            List<BiddingInfoCollectVO> biddingInfoCollectVOS = BeanTransform.copyProperties(
                    biddingInfoAPI.collectBiddingInfo(cities), BiddingInfoCollectVO.class);
            return ActResult.initialize(biddingInfoCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取地区
     *
     * @des 获取地区集合
     * @version v1
     */
    @GetMapping("v1/cities")
    public Result cities() throws ActException {
        try {
            List<String> citiesList = biddingInfoAPI.getBiddingInfoCities();
            return ActResult.initialize(citiesList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取招标信息
     *
     * @param biddingNumber 编号
     * @return class BiddingInfoVO
     * @des 根据编号获取招标信息
     * @version v1
     */
    @GetMapping("v1/getBiddingNum")
    public Result getBidding(String biddingNumber) throws ActException {
        try {
//            logger.info("获取招标信息开始:" + biddingNumber);
            BiddingInfoBO biddingInfoBO = biddingInfoAPI.getBidding(biddingNumber);
//            logger.info("获取招标信息结果:" + biddingNumber);
            return ActResult.initialize(BeanTransform.copyProperties(biddingInfoBO, BiddingInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取项目名称
     *
     * @des 获取项目名称集合
     * @version v1
     */
    @GetMapping("v1/projectName")
    public Result projectName() throws ActException {
        try {
            List<String> projectNameList = biddingInfoAPI.getProjectName();
            return ActResult.initialize(projectNameList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取编号
     *
     * @des 获取编号集合
     * @version v1
     */
    @GetMapping("v1/biddingNumber")
    public Result biddingNumber() throws ActException {
        try {
            List<String> biddingNumberList = biddingInfoAPI.getTenderNumber();
            return ActResult.initialize(biddingNumberList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @des 招标信息
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
     * @param id 招标信息id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /bidding/id/....
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
     * @param biddingDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(BiddingDeleteFileTO.TestDEL.class) BiddingDeleteFileTO biddingDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != biddingDeleteFileTO.getPaths() && biddingDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), biddingDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

    /**
     * 导出excel
     *
     * @param dto 招投信息
     * @des 导出招投信息
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(BiddingInfoDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "招投信息.xlsx";
            super.writeOutFile(response, biddingInfoAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 年份
     *
     * @version v1
     */
    @GetMapping("v1/year")
    public Result yearList() throws ActException {
        try {
            //获取所有年
            List<String> yearList = new ArrayList<>();
            int year = LocalDate.now().getYear();

            for (int i = year - 5; i <= year + 5; i++) {
                yearList.add(String.valueOf(i));
            }
            return ActResult.initialize(yearList);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 中国移动采购与招标网获取网址内的信息
     *
     * @param to
     * @return class InfoVO
     * @version v1
     */
    @PostMapping("v1/info")
    public Result info(SearchTO to) throws ActException {
        try {
            List<InfoVO> infoVOS = BeanTransform.copyProperties(biddingInfoAPI.info(to),InfoVO.class);
            return ActResult.initialize(infoVOS);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 工信部招标网获取信息
     *
     * @param to
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/txzbInfo")
    public Result txzbInfo(SearchTO to) throws ActException {
        try {
            List<InfoVO> infoVOS = BeanTransform.copyProperties(biddingInfoAPI.txzbInfo(to),InfoVO.class);
            return ActResult.initialize(infoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 中央政府采购网获取信息
     *
     * @param to
     * @return class InfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/zycgInfo")
    public Result zycgInfo(SearchTO to) throws ActException {
        try {
            List<InfoVO> infoVOS = BeanTransform.copyProperties(biddingInfoAPI.zycgInfo(to),InfoVO.class);
            return ActResult.initialize(infoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 中国警务招标网获取信息
     *
     * @param to
     * @return class InfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/caigouInfo")
    public Result caigouInfo(SearchTO to) throws ActException {
        try {
            List<InfoVO> infoVOS = BeanTransform.copyProperties(biddingInfoAPI.caigouInfo(to),InfoVO.class);
            return ActResult.initialize(infoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 中国电力工程招标网获取信息
     *
     * @param to
     * @return class InfoVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/toobiaoInfo")
    public Result toobiaoInfo(SearchTO to) throws ActException {
        try {
            List<InfoVO> infoVOS = BeanTransform.copyProperties(biddingInfoAPI.toobiaoInfo(to),InfoVO.class);
            return ActResult.initialize(infoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
//    /**
//     * 中国学校招标网获取信息
//     *
//     * @param to
//     * @throws SerException
//     * @version v1
//     */
//    @GetMapping("v1/schoolbidInfo")
//    public Result schoolbidInfo(SearchTO to) throws ActException {
//        try {
//            return ActResult.initialize(biddingInfoAPI.schoolbidInfo(to));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
}