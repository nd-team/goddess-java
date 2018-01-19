package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialbuy.bo.AreaBuyStatusDayCollectBO;
import com.bjike.goddess.materialbuy.bo.MaterialBuyBO;
import com.bjike.goddess.materialbuy.bo.MaterialBuySummaryBO;
import com.bjike.goddess.materialbuy.bo.OptionBO;
import com.bjike.goddess.materialbuy.dto.MaterialBuyDTO;
import com.bjike.goddess.materialbuy.entity.MaterialBuy;
import com.bjike.goddess.materialbuy.to.GuidePermissionTO;
import com.bjike.goddess.materialbuy.to.MaterialBuyTO;
import com.bjike.goddess.materialbuy.vo.SonPermissionObject;

import java.time.LocalDate;
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
public interface MaterialBuySer extends Ser<MaterialBuy, MaterialBuyDTO> {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

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
     * @param maps 文件名，byte 文件字节
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

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(MaterialBuyDTO dto) throws SerException;

    /**
     * 物质汇总所需接口
     * 查找所有类型
     *
     * @return
     * @throws SerException
     */
    List<String> findDevType(String[] intervalTime) throws SerException;

    /**
     * 物质汇总所需接口
     * 根据设备类型查找所有地区
     *
     * @return
     * @throws SerException
     */
    List<String> findAreaByType(String devType, String[] intervalTime) throws SerException;

    /**
     * 物质汇总所需接口
     * 根据设备类型和地区查找所有部门
     *
     * @return
     * @throws SerException
     */
    List<String> findDeparByTyAre(String devType,String area, String[] intervalTime) throws SerException;

    /**
     * 物质汇总所需接口
     * 根据设备类型和地区和部门查找所有信息
     *
     * @return
     * @throws SerException
     */
    List<MaterialBuyBO> findByTyAndAr(String devType, String area,String department, String[] intervalTime) throws SerException;

    /**
     * 物质汇总所需接口
     * 查找所有地区
     *
     * @return
     * @throws SerException
     */
    List<String> findArea(String[] intervalTime) throws SerException;

    /**
     * 物质汇总所需接口
     * 根据地区查找所有部门
     *
     * @return
     * @throws SerException
     */
    List<String> findDepByArea(String area,String[] intervalTime) throws SerException;

    /**
     * 物质汇总所需接口
     * 根据地区,部门查找所有类型
     *
     * @return
     * @throws SerException
     */
    List<String> findDevByAreaDev(String area,String projectTeam,String[] intervalTime) throws SerException;

    /**
     * 物质汇总所需接口
     * 根据地区,部门查找所有信息
     *
     * @return
     * @throws SerException
     */
    List<MaterialBuyBO> findByTeamAnArea(String area,String projectTeam,String devType,String[] intervalTime) throws SerException;

    /**
     * 物质汇总所需接口
     * 查找所有申购人
     *
     * @return
     * @throws SerException
     */
    List<String> findRequis(String[] intervalTime) throws SerException;

    /**
     * 物质汇总所需接口
     * 根据申购人查找类型
     *
     * @return
     * @throws SerException
     */
    List<String> findByRequis(String requisitioner, String[] intervalTime) throws SerException;
    /**
     * 物质汇总所需接口
     * 根据申购人和类型查找所有信息
     *
     * @return
     * @throws SerException
     */
    List<MaterialBuyBO> findByRequisType(String requisitioner,String devType, String[] intervalTime) throws SerException;

    /**
     * 获得申购日期
     */
    default List<String> findSubscribeDate() throws SerException {
        return null;
    }

    /**
     * 获得申购人
     */
    default List<String> findRequisitioner() throws SerException {
        return null;
    }

    /**
     * 导出excel
     * chenjunhao
     *
     * @param dto
     * @return
     * @throws SerException
     */
    byte[] exportExcel(MaterialBuyDTO dto) throws SerException;


    /**
     * 物质购买汇总
     *
     * @return class AreaBuyStatusDayCollectBO
     * @throws SerException
     */
    List<MaterialBuySummaryBO> materialBuySum(MaterialBuyDTO dto) throws SerException;

    /**
     * 图形化汇总
     * @param year
     * @param month
     * @return
     * @throws SerException
     */
    OptionBO GUI(String year, String month) throws SerException;

    /**
     * 图形化周汇总
     * @param year
     * @param month
     * @return
     * @throws SerException
     */
    OptionBO GuiByWeek(String year,String month,String week) throws SerException;

}