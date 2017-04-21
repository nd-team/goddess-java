package com.bjike.goddess.socialfee.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.socialfee.bo.SocialFeeBO;
import com.bjike.goddess.socialfee.bo.VoucherDataBO;
import com.bjike.goddess.socialfee.dto.SocialFeeDTO;
import com.bjike.goddess.socialfee.to.SocialFeeTO;
import com.bjike.goddess.socialfee.to.VoucherDataTO;

import java.util.List;

/**
 * 社会缴费业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-19 03:25 ]
 * @Description: [ 社会缴费业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SocialFeeAPI {

    /**
     * 社会缴费列表总条数
     */
    default Long countSocialFee(SocialFeeDTO socialFeeDTO) throws SerException {
        return null;
    }

    /**
     * 社会缴费列表
     *
     * @return class SocialFeeBO
     */
    default List<SocialFeeBO> listSocialFee(SocialFeeDTO socialFeeDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param socialFeeTO 社会缴费信息
     * @return class SocialFeeBO
     */
    default SocialFeeBO addSocialFee(SocialFeeTO socialFeeTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param socialFeeTO 社会缴费信息
     * @return class SocialFeeBO
     */
    default SocialFeeBO editSocialFee(SocialFeeTO socialFeeTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteSocialFee(String id) throws SerException {
        return;
    }

    ;

    /**
     * 根据id查询
     *
     * @return class SocialFeeBO
     */
    default SocialFeeBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 汇总
     *
     * @return class SocialFeeBO
     */
    default List<SocialFeeBO> collect(SocialFeeDTO socialFeeDTO) throws SerException {
        return null;
    }


    /**
     * 获取所有纳税人名称
     *
     * @return
     * @throws SerException
     */
    default List<String> listPayFeer() throws SerException {
        return null;
    }

    /**
     * 获取所有姓名
     *
     * @return
     * @throws SerException
     */
    default List<String> listEmpName() throws SerException {
        return null;
    }

    /**
     * 生成记账凭证
     * @param ids
     * @return class VoucherDataBO
     * @throws SerException
     */
    default VoucherDataBO vGenerate(String[] ids) throws SerException{return null;}

    /**
     * 提交记账凭证
     *
     * @return class VoucherDataBO
     * @throws SerException
     */
    default VoucherDataBO voucher(VoucherDataTO voucherDataTO) throws SerException {
        return null;
    }

    /**
     * 导出
     * @param socialFeeDTO
     * @return
     * @throws SerException
     */
    default String export (SocialFeeDTO socialFeeDTO) throws  SerException{return null;}



}