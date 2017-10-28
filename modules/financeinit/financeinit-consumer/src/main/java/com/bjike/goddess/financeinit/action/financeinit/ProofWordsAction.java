package com.bjike.goddess.financeinit.action.financeinit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.ProofWordsAPI;
import com.bjike.goddess.financeinit.bo.ProofWordsBO;
import com.bjike.goddess.financeinit.dto.ProofWordsDTO;
import com.bjike.goddess.financeinit.entity.ProofWords;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.to.ProofWordsTO;
import com.bjike.goddess.financeinit.vo.ProofWordsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 凭证字
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 03:09 ]
 * @Description: [ 凭证字 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("proofwords")
public class ProofWordsAction {
    @Autowired
    private ProofWordsAPI proofWordsAPI;

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

            Boolean isHasPermission = proofWordsAPI.guidePermission(guidePermissionTO);
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
     * @param proofWordsDTO 凭证字dto
     * @des 获取所有凭证字总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProofWordsDTO proofWordsDTO) throws ActException {
        try {
            Long count = proofWordsAPI.countProof(proofWordsDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个凭证字
     *
     * @param id 凭证字id
     * @return class ProofWordsVO
     * @des 根据id获取凭证字
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            ProofWordsVO proofWordsVO = BeanTransform.copyProperties(
                    proofWordsAPI.getOneById(id), ProofWordsVO.class);
            return ActResult.initialize(proofWordsVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 凭证字列表
     *
     * @param proofWordsDTO 凭证字dto
     * @return class ProofWordsVO
     * @des 获取所有凭证字
     * @version v1
     */
    @GetMapping("v1/listAccount")
    public Result findListAccount(ProofWordsDTO proofWordsDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<ProofWordsVO> accountVOList = BeanTransform.copyProperties(
                    proofWordsAPI.listProof(proofWordsDTO), ProofWordsVO.class, request);
            return ActResult.initialize(accountVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加凭证字
     *
     * @param proofWordsTO 凭证字数据to
     * @return class ProofWordsVO
     * @des 添加凭证字
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addAccount(@Validated(value= ADD.class) ProofWordsTO proofWordsTO, BindingResult bindingResult) throws ActException {
        try {
            ProofWordsBO proofWordsBO = proofWordsAPI.addProof(proofWordsTO);
            return ActResult.initialize(BeanTransform.copyProperties(proofWordsBO, ProofWordsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}