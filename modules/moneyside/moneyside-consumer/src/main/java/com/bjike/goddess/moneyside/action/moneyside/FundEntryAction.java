package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.FundEntryAPI;
import com.bjike.goddess.moneyside.bo.FundEntryBO;
import com.bjike.goddess.moneyside.dto.FundEntryDTO;
import com.bjike.goddess.moneyside.to.*;
import com.bjike.goddess.moneyside.vo.CollectVO;
import com.bjike.goddess.moneyside.vo.FundEntryVO;
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
import java.io.InputStream;
import java.util.List;

/**
 * 资金进入申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:05 ]
 * @Description: [ 资金进入申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("fundentry")
public class FundEntryAction extends BaseFileAction{
    @Autowired
    private FundEntryAPI fundEntryAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 资金进入申请列表总条数
     *
     * @param fundEntryDTO 资金进入申请dto
     * @des 获取所有资金进入申请总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FundEntryDTO fundEntryDTO) throws ActException {
        try {
            Long count = fundEntryAPI.countFundEntry(fundEntryDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个资金进入申请
     *
     * @param id
     * @return class FundEntryVO
     * @des 获取一个资金进入申请
     * @version v1
     */
    @GetMapping("v1/fund/{id}")
    public Result fund(@PathVariable String id) throws ActException {
        try {
            FundEntryBO fundEntryBO = fundEntryAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(fundEntryBO, FundEntryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资金进入申请列表
     *
     * @param fundEntryDTO 资金进入申请dto
     * @return class FundEntryVO
     * @des 获取所有资金进入申请
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FundEntryDTO fundEntryDTO, HttpServletRequest request) throws ActException {
        try {
            List<FundEntryVO> fundEntryVOS = BeanTransform.copyProperties
                    (fundEntryAPI.findListFundEntry(fundEntryDTO), FundEntryVO.class, request);
            return ActResult.initialize(fundEntryVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加资金进入申请
     *
     * @param fundEntryTO 资金进入申请数据to
     * @return class FundEntryVO
     * @des 添加资金进入申请
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(FundEntryTO.TestAdd.class) FundEntryTO fundEntryTO, BindingResult bindingResult) throws ActException {
        try {
            FundEntryBO fundEntryBO = fundEntryAPI.insertFundEntry(fundEntryTO);
            return ActResult.initialize(BeanTransform.copyProperties(fundEntryBO, FundEntryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑资金进入申请
     *
     * @param fundEntryTO 资金进入申请数据to
     * @return class FundEntryVO
     * @des 编辑资金进入申请
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(FundEntryTO.TestEdit.class) FundEntryTO fundEntryTO, BindingResult bindingResult) throws ActException {
        try {
            FundEntryBO fundEntryBO = fundEntryAPI.editFundEntry(fundEntryTO);
            return ActResult.initialize(BeanTransform.copyProperties(fundEntryBO, FundEntryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除资金进入申请
     *
     * @param id 用户id
     * @des 根据用户id删除资金进入申请记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            fundEntryAPI.removeFundEntry(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取投资人
     *
     * @des 获取投资人
     * @version v1
     */
    @GetMapping("v1/getInvestor")
    public Result getInvestor() throws ActException {
        try {
            List<String> list = fundEntryAPI.getInvestor();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param fundEntryTO 审核数据to
     * @return class FundEntryVO
     * @des 审核
     * @version v1
     */
    @PostMapping("v1/audit")
    public Result audit(@Validated(FundEntryTO.TestAudit.class) FundEntryTO fundEntryTO, BindingResult bindingResult) throws ActException {
        try {
            FundEntryBO fundEntryBO = fundEntryAPI.audit(fundEntryTO);
            return ActResult.initialize(BeanTransform.copyProperties(fundEntryBO,FundEntryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请人汇总
     *
     * @param to 汇总条件
     * @return class CollectVO
     * @version v1
     */
    @GetMapping("v1/investor")
    public Result investor(@Validated({ApplyPeopleTO.Collect.class}) ApplyPeopleTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CollectTO collectTO = BeanTransform.copyProperties(to, CollectTO.class);
            List<CollectVO> voList = BeanTransform.copyProperties(fundEntryAPI.collect(collectTO), CollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资金进入方式汇总
     *
     * @param to 汇总条件
     * @return class CollectVO
     * @version v1
     */
    @GetMapping("v1/accessCollect")
    public Result accessCollect(@Validated({AccessToFundTO.Collect.class}) AccessToFundTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CollectTO collectTO = BeanTransform.copyProperties(to, CollectTO.class);
            List<CollectVO> voList = BeanTransform.copyProperties(fundEntryAPI.collect(collectTO), CollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资金进入时间汇总
     *
     * @param to 汇总条件
     * @return class CollectVO
     * @version v1
     */
    @GetMapping("v1/timeCollect")
    public Result timeCollect(@Validated({DateTO.Collect.class}) DateTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CollectTO collectTO = BeanTransform.copyProperties(to, CollectTO.class);
            List<CollectVO> voList = BeanTransform.copyProperties(fundEntryAPI.collect(collectTO), CollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 上传附件
     *
     * @des 资金进入申请
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
     * @param id 资金进入申请id
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
     * @param fundFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(FundFileTO.TestDEL.class) FundFileTO fundFileTO, HttpServletRequest request) throws SerException {
        if(null != fundFileTO.getPaths() && fundFileTO.getPaths().length>=0 ){
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(),fundFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }


}