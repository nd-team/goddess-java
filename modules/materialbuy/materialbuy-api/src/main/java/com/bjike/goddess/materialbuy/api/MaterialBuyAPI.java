package com.bjike.goddess.materialbuy.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialbuy.bo.AreaBuyStatusDayCollectBO;
import com.bjike.goddess.materialbuy.bo.MaterialBuyBO;
import com.bjike.goddess.materialbuy.dto.MaterialBuyDTO;
import com.bjike.goddess.materialbuy.to.MaterialBuyTO;

import java.util.List;
import java.util.Map;

/**
 * 物资购买业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialBuyAPI {

    /**
     * 根据id查询物资购买
     *
     * @param id 物资购买唯一标识
     * @return class MaterialBuyBO
     * @throws SerException
     */
    MaterialBuyBO findById(String id) throws SerException;

    /**
     * 分页查询物资购买
     *
     * @return class MaterialBuyBO
     * @throws SerException
     */
    List<MaterialBuyBO> list(MaterialBuyDTO dto) throws SerException;

    /**
     * 保存物资购买
     *
     * @param to 物资购买to
     * @return class MaterialBuyBO
     * @throws SerException
     */
    MaterialBuyBO save(MaterialBuyTO to) throws SerException;

    /**
     * 根据id删除物资购买
     *
     * @param id 物资购买唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新物资购买
     *
     * @param to 物资购买to
     * @throws SerException
     */
    void update(MaterialBuyTO to) throws SerException;

    /**
     * 文件上传
     *
     * @param maps  文件名，byte 文件字节
     * @param path 上传路径
     */
    void upload(Map<String, byte[]> maps, String path) throws SerException;

    /**
     * 查看详情
     *
     * @param id 物资购买唯一标识
     * @return class MaterialBuyBO
     * @throws SerException
     */
    MaterialBuyBO checkDetail(String id) throws SerException;

    /**
     * 地区负责人审核
     *
     * @param to 物资购买to
     * @throws SerException
     */
    void areaPrincipalAudit(MaterialBuyTO to) throws SerException;

    /**
     * 查询等待付款
     *
     * @param dto 物资购买dto
     * @return class MaterialBuyBO
     * @throws SerException
     */
    List<MaterialBuyBO> findWaitPay(MaterialBuyDTO dto) throws SerException;

    /**
     * 地区购买情况汇总
     *
     * @return class AreaBuyStatusDayCollectBO
     * @throws SerException
     */
    List<AreaBuyStatusDayCollectBO> areaBuyStatusDaySum() throws SerException;

}