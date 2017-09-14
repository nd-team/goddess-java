package com.bjike.goddess.shareholdersmanage.action.shareholdersmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.api.EquityTransactRecordAPI;
import com.bjike.goddess.shareholdersmanage.api.ShareOutBonusDetailAPI;
import com.bjike.goddess.shareholdersmanage.api.ShareOutBonusManageAPI;
import com.bjike.goddess.shareholdersmanage.bo.*;
import com.bjike.goddess.shareholdersmanage.dto.ShareOutBonusDetailDTO;
import com.bjike.goddess.shareholdersmanage.dto.ShareOutBonusManageDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.entity.ShareOutBonusDetail;
import com.bjike.goddess.shareholdersmanage.entity.ShareOutBonusManage;
import com.bjike.goddess.shareholdersmanage.service.EquityTransactRecordSer;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOutBonusDetailTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOutBonusManageTO;
import com.bjike.goddess.shareholdersmanage.vo.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 分红管理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:47 ]
 * @Description: [ 分红管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("shareoutbonusmanage")
public class ShareOutBonusManageAction extends BaseFileAction{
    @Autowired
    private ShareOutBonusDetailAPI shareOutBonusDetailAPI;
    @Autowired
    private ShareOutBonusManageAPI shareOutBonusManageAPI;
    @Autowired
    private EquityTransactRecordAPI equityTransactRecordAPI;
    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = shareOutBonusManageAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param shareOutBonusManageDTO 分红管理dto
     * @des 获取所有分红管理总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ShareOutBonusManageDTO shareOutBonusManageDTO) throws ActException {
        try {
            Long count = shareOutBonusManageAPI.countOutBonus(shareOutBonusManageDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个分红管理
     *
     * @param id 分红管理id
     * @return class ShareOutBonusManageVO
     * @des 根据id获取分红管理
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            ShareOutBonusManageVO shareOutBonusManageVO = BeanTransform.copyProperties(
                    shareOutBonusManageAPI.getOne(id), ShareOutBonusManageVO.class);
            return ActResult.initialize(shareOutBonusManageVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个分红明细
     *
     * @param id 分红明细id
     * @return class ShareOutBonusManageVO
     * @des 根据id获取分红明细
     * @version v1
     */
    @GetMapping("v1/getDetailById/{id}")
    public Result getDetailById(@PathVariable String id) throws ActException {
        try {
            ShareOutBonusDetailVO shareOutBonusDetailVO = BeanTransform.copyProperties(
                    shareOutBonusDetailAPI.getOne(id), ShareOutBonusDetailVO.class);
            return ActResult.initialize(shareOutBonusDetailVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分红管理列表
     *
     * @param shareOutBonusManageDTO 分红管理dto
     * @return class ShareOutBonusManageVO
     * @des 获取所有分红管理
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findEquityTrfer(ShareOutBonusManageDTO shareOutBonusManageDTO, HttpServletRequest request) throws ActException {
        try {
            List<ShareOutBonusManageVO> shareOutBonusManageVOS = BeanTransform.copyProperties(
                    shareOutBonusManageAPI.findList(shareOutBonusManageDTO), ShareOutBonusManageVO.class, request);
            return ActResult.initialize(shareOutBonusManageVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加分红管理
     *
     * @param shareOutBonusManageTO 分红管理to
     * @return class ShareOutBonusManageVO
     * @des 添加分红管理
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addEqurfer(@Validated({ADD.class}) ShareOutBonusManageTO shareOutBonusManageTO, BindingResult result) throws ActException {
        try {
            ShareOutBonusManageBO shareOutBonusManageBO = shareOutBonusManageAPI.save(shareOutBonusManageTO);
            return ActResult.initialize(BeanTransform.copyProperties(shareOutBonusManageBO, ShareOutBonusManageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑分红管理
     *
     * @param shareOutBonusManageTO 分红管理数据bo
     * @return class ShareOutBonusManageVO
     * @des 编辑分红管理
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editShareOpen(@Validated({EDIT.class}) ShareOutBonusManageTO shareOutBonusManageTO, BindingResult result) throws ActException {
        try {
            ShareOutBonusManageBO shareOutBonusManageBO = shareOutBonusManageAPI.edit(shareOutBonusManageTO);
            return ActResult.initialize(BeanTransform.copyProperties(shareOutBonusManageBO, ShareOutBonusManageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除分红管理
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteShareChange(@PathVariable String id) throws ActException {
        try {
            shareOutBonusManageAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 设置分红明细
     *
     * @param shareOutBonusDetailTO 分红明细to
     * @return class ShareOutBonusDetailVO
     * @des 设置分红明细
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add/detail")
    public Result addDetail(@Validated({ADD.class}) ShareOutBonusDetailTO shareOutBonusDetailTO, BindingResult result) throws ActException {
        try {
            ShareOutBonusDetailBO shareOutBonusDetailBO = shareOutBonusDetailAPI.save(shareOutBonusDetailTO);
            return ActResult.initialize(BeanTransform.copyProperties(shareOutBonusDetailBO, ShareOutBonusDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分红明细列表
     *
     * @param ShareOutBonusManageId 分红管理id
     * @return class ShareOutBonusDetailVO
     * @des 获取所有分红管理
     * @version v1
     */
    @GetMapping("v1/list/detail")
    public Result findDetailList(@RequestParam String ShareOutBonusManageId, HttpServletRequest request) throws ActException {
        try {
            List<ShareOutBonusDetailVO> shareOutBonusDetailVOS = BeanTransform.copyProperties(
                    shareOutBonusDetailAPI.findListBySharId(ShareOutBonusManageId), ShareOutBonusDetailVO.class, request);
            return ActResult.initialize(shareOutBonusDetailVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 编辑分红明细
     *
     * @param shareOutBonusDetailTO 分红明细数据bo
     * @return class ShareOutBonusDetailVO
     * @des 编辑分红明细
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit/detail")
    public Result editDetail(@Validated({EDIT.class}) ShareOutBonusDetailTO shareOutBonusDetailTO, BindingResult result) throws ActException {
        try {
            ShareOutBonusDetailBO shareOutBonusDetailBO = shareOutBonusDetailAPI.edit(shareOutBonusDetailTO);
            return ActResult.initialize(BeanTransform.copyProperties(shareOutBonusDetailBO, ShareOutBonusDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 删除分红明细数据
     *
     * @param id id
     * @des 根据id删除分红明细
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/deleteDetail/{id}")
    public Result deleteDetail(@PathVariable String id) throws ActException {
        try {
            shareOutBonusDetailAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 计算分红额
     * @param shareOutBonusManageId 分红管理id
     * @param shareOutBonusPropor 分红比例
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/comput/amount")
    public Result computAmount(@RequestParam String shareOutBonusManageId,@RequestParam Double shareOutBonusPropor) throws ActException {
        try {
            Double amount = shareOutBonusDetailAPI.computAmount(shareOutBonusManageId,shareOutBonusPropor);
            return ActResult.initialize(amount);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 计算所得税
     * @param shareOutBonusAmount 分红额
     * @param incomeTaxPropor 所得税比例
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/comput/income")
    public Result computAmount(@RequestParam Double shareOutBonusAmount,@RequestParam Double incomeTaxPropor) throws ActException {
        try {
            Double incomeTax = shareOutBonusDetailAPI.computIncomeTax(shareOutBonusAmount,incomeTaxPropor);
            return ActResult.initialize(incomeTax);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 分红情况查询列表
     *
     * @param shareOutBonusManageDTO 分红管理dto
     * @return class ShareOutBonusCaseVO
     * @des 分红情况查询列表
     * @version v1
     */
    @GetMapping("v1/caseList")
    public Result findCaseList(ShareOutBonusManageDTO shareOutBonusManageDTO, HttpServletRequest request) throws ActException {
        try {
            List<ShareOutBonusCaseVO> shareOutBonusCaseVOList = BeanTransform.copyProperties(
                    shareOutBonusManageAPI.findShareCase(shareOutBonusManageDTO), ShareOutBonusCaseVO.class, request);
            return ActResult.initialize(shareOutBonusCaseVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分红情况查询汇总
     *
     * @param shareOutBonusManageDTO 分红管理dto
     * @return class ShareOutBonusCaseVO
     * @des 分红情况查询列表
     * @version v1
     */
    @GetMapping("v1/summCase")
    public Result summCase(ShareOutBonusManageDTO shareOutBonusManageDTO, HttpServletRequest request) throws ActException {
        try {
            List<ShareOutBonusCaseVO> shareOutBonusCaseVOList = BeanTransform.copyProperties(
                    shareOutBonusManageAPI.summShareCase(shareOutBonusManageDTO), ShareOutBonusCaseVO.class, request);
            return ActResult.initialize(shareOutBonusCaseVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 股东花名册列表
     *
     * @param shareOutBonusManageDTO 分红管理dto
     * @return class ShareRosterVO
     * @des 分红情况查询列表
     * @version v1
     */
    @GetMapping("v1/rosterList")
    public Result findRosterList(ShareOutBonusManageDTO shareOutBonusManageDTO, HttpServletRequest request) throws ActException {
        try {
            List<ShareRosterBO> shareRosterBOList = BeanTransform.copyProperties(
                    shareOutBonusManageAPI.findShareRoster(shareOutBonusManageDTO), ShareRosterVO.class, request);
            return ActResult.initialize(shareRosterBOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 股东花名册分红明细列表
     *
     * @param shareholderName 股东名称
     * @return class ShareRosterDetailVO
     * @des 分红情况查询列表
     * @version v1
     */
    @GetMapping("v1/rosterDetailList")
    public Result findRosterDetailList(@RequestParam String shareholderName, HttpServletRequest request) throws ActException {
        try {
            List<ShareRosterDetailVO> shareRosterDetailVOS = BeanTransform.copyProperties(
                    shareOutBonusManageAPI.findShareRosterDetail(shareholderName), ShareRosterDetailVO.class, request);
            return ActResult.initialize(shareRosterDetailVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 股东花名册汇总
     *
     * @param shareOutBonusManageDTO 分红管理dto
     * @return class ShareRosterVO
     * @des 分红情况查询列表
     * @version v1
     */
    @GetMapping("v1/summRoster")
    public Result summRoster(ShareOutBonusManageDTO shareOutBonusManageDTO, HttpServletRequest request) throws ActException {
        try {
            List<ShareRosterVO> shareRosterVOS = BeanTransform.copyProperties(
                    shareOutBonusManageAPI.summShareRoster(shareOutBonusManageDTO), ShareRosterVO.class, request);
            return ActResult.initialize(shareRosterVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 导出Excel
     *
     * @param shareOutBonusManageDTO 分红管理dto
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/exportExcel")
    public Result exportExcel(ShareOutBonusManageDTO shareOutBonusManageDTO, HttpServletResponse response) throws ActException {
        try {
            String fileName = "股东花名册.xlsx";
            super.writeOutFile(response, shareOutBonusManageAPI.exportExcel(shareOutBonusManageDTO), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }
    /**
     * 添加时根据类型获取股东名称
     *
     * @param id 分类管理id
     * @des 根据类型获取股东名称
     * @version v1
     */
    @GetMapping("v1/findShareholderNameBy/type")
    public Result getLinkDate(@RequestParam String id) throws ActException {
        try {
            List<String> shareholderNames = new ArrayList<>();
            ShareOutBonusManageBO shareOutBonusManageBO = shareOutBonusManageAPI.getOne(id);
            shareholderNames = equityTransactRecordAPI.getNameByEquityType(shareOutBonusManageBO.getEquityType());
            return ActResult.initialize(shareholderNames);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 编辑时根据类型获取股东名称
     *
     * @param id 分类明细id
     * @des 根据类型获取股东名称
     * @version v1
     */
    @GetMapping("v1/editName/type")
    public Result getEditNameByType(@RequestParam String id) throws ActException {
        try {
            List<String> shareholderNames = new ArrayList<>();
            ShareOutBonusDetailBO shareOutBonusDetailBO = shareOutBonusDetailAPI.getOne(id);
            EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordAPI.getByName(shareOutBonusDetailBO.getShareholderName());
            shareholderNames = equityTransactRecordAPI.getNameByEquityType(equityTransactRecordBO.getEquityType());
            return ActResult.initialize(shareholderNames);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}