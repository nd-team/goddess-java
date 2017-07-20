package com.bjike.goddess.materialcheck.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialcheck.bo.MaterialInventoryBO;
import com.bjike.goddess.materialcheck.dto.MaterialInventoryDTO;
import com.bjike.goddess.materialcheck.entity.MaterialInventory;
import com.bjike.goddess.materialcheck.excel.SonPermissionObject;
import com.bjike.goddess.materialcheck.to.GuidePermissionTO;
import com.bjike.goddess.materialcheck.to.MaterialInventoryTO;
import com.bjike.goddess.materialcheck.type.GuideAddrStatus;
import com.bjike.goddess.materialcheck.type.InventoryType;
import com.bjike.goddess.materialinstock.api.MaterialInStockAPI;
import com.bjike.goddess.materialinstock.bo.AttributeBO;
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.type.MaterialState;
import com.bjike.goddess.materialinstock.type.UseState;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 物资盘点业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-08 04:00 ]
 * @Description: [ 物资盘点业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialcheckSerCache")
@Service
public class MaterialInventorySerImpl extends ServiceImpl<MaterialInventory, MaterialInventoryDTO> implements MaterialInventorySer {

    @Autowired
    private MaterialInStockAPI materialInStockAPI;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private MaterialAnalyzeSer materialAnalyzeSer;

    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是本部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(财务模块)
     *
     * @throws SerException
     */
    private void checkModPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是财务模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(总经办)
     *
     * @throws SerException
     */
    private void checkPonsPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.arrCusPermission("3");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是总经办岗位人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对财务模块审核权限（模块级别）
     */
    private Boolean guideMondIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对总经办审核权限（岗位级别）
     */
    private Boolean guidePosinIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.arrCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        {
            List<SonPermissionObject> list = new ArrayList<>();
            String userToken = RpcTransmit.getUserToken();
            Boolean flagInvenDai = guideIdentity();
            RpcTransmit.transmitUserToken(userToken);
            Boolean flagInvenMondDai = guideMondIdentity();
            RpcTransmit.transmitUserToken(userToken);
            Boolean flagInvenPosinDai = guidePosinIdentity();
            RpcTransmit.transmitUserToken(userToken);

            SonPermissionObject obj = new SonPermissionObject();

            obj = new SonPermissionObject();
            obj.setName("materialanalyzedaily");
            obj.setDescribesion("物质盘点日盘");
            if (flagInvenDai || flagInvenMondDai || flagInvenPosinDai) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);

            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("materialanalyzeweek");
            obj.setDescribesion("物质盘点周盘");
            if (flagInvenDai || flagInvenMondDai || flagInvenPosinDai) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);

            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("materialanalyzeannual");
            obj.setDescribesion("物质盘点年盘");
            if (flagInvenDai || flagInvenMondDai || flagInvenPosinDai) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            Boolean flagAnalyDai = materialAnalyzeSer.sonPermission();
            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("materialAnalyzedaily");
            obj.setDescribesion("物质分析日盘");
            if (flagAnalyDai) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            Boolean flagAnalyWeek = materialAnalyzeSer.sonPermission();
            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("materialAnalyzeweek");
            obj.setDescribesion("物质分析周盘");
            if (flagAnalyWeek) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            Boolean flagAnalyAnnu = materialAnalyzeSer.sonPermission();
            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("materialAnalyzeannual");
            obj.setDescribesion("物质分析年盘");
            if (flagAnalyAnnu) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            return list;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case CMODUL:
                flag = guideMondIdentity();
                break;
            case MANAGES:
                flag = guidePosinIdentity();
                break;
            case ADUIT:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }


    /**
     * 分页查询物资盘点
     *
     * @param dto 物资盘点dto
     * @return class MaterialInventoryBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MaterialInventoryBO> list(MaterialInventoryDTO dto) throws SerException {
        checkPermission();
        List<MaterialInventory> list = super.findByPage(dto);
        List<MaterialInventoryBO> boList = BeanTransform.copyProperties(list, MaterialInventoryBO.class);
        return boList;
    }

    /**
     * 保存物资盘点
     *
     * @param to 物资盘点to
     * @return class MaterialInventoryBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialInventoryBO save(MaterialInventoryTO to) throws SerException {
        checkPermission();
        MaterialInventory entity = BeanTransform.copyProperties(to, MaterialInventory.class, true);
        entity = super.save(entity);
        MaterialInventoryBO bo = BeanTransform.copyProperties(entity, MaterialInventoryBO.class);
        return bo;
    }

    /**
     * 根据id删除物资盘点
     *
     * @param id 物资盘点唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }

    /**
     * 更新物资盘点
     *
     * @param to 物资盘点to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialInventoryTO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialInventory model = super.findById(to.getId());
            if (model != null) {
                updateMaterialInventory(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新物资盘点
     *
     * @param to
     * @param model
     */
    private void updateMaterialInventory(MaterialInventoryTO to, MaterialInventory model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 经办人确认情况
     *
     * @param id             物资盘点唯一标识
     * @param operatorStatus 经办人确认情况
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void operatorConfirm(String id, String operatorStatus) throws SerException {
        checkPermission();
        MaterialInventory model = super.findById(id);
        model.setOperatorConfirm(operatorStatus);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 账务模块核实
     *
     * @param id            物资盘点唯一标识
     * @param accountStatus 账务模块核实情况
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void accountModuleConfirm(String id, String accountStatus) throws SerException {
        checkModPermission();
        MaterialInventory model = super.findById(id);
        model.setAccountModuleConfirm(accountStatus);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 总经办审核
     *
     * @param id        物资盘点唯一标识
     * @param zjbStatus 总经办审核意见
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void zjbConfirm(String id, String zjbStatus) throws SerException {
        checkPonsPermission();
        MaterialInventory model = super.findById(id);
        model.setZjbOpinion(zjbStatus);
        super.update(model);
    }

    /**
     * 物资盘点
     *
     * @param inventoryType 物资盘点类型
     * @return class MaterialInventoryBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MaterialInventoryBO> materialInventory(InventoryType inventoryType) throws SerException {
        checkPermission();
        List<MaterialInventoryBO> inventoryBOList = new ArrayList<>(0);
        //查询到所有类型的存储地区,项目组,物品类型,物资名称
        List<AttributeBO> attributeBOList = materialInStockAPI.findAllKindsType();
        if (attributeBOList.isEmpty()) {
            return Collections.emptyList();
        }
        //依次遍历计算每条记录
        for (AttributeBO bo : attributeBOList) {
            List<MaterialInStockBO> boList = materialInStockAPI.findByAttribute(bo);
            String area = bo.getStorageArea();//地区
            String projectGroup = bo.getProjectGroup();//项目组
            String type = bo.getMaterialType();        //物资类型
            String deviceName = bo.getMaterialName();  //设备名称
            String unit = bo.getUnit();                //单位
            Integer accountNo = boList.size();         //账目数
            Double total = boList.stream().mapToDouble(c -> c.getUnitPrice()).sum();//计算总额
            Integer stockNo = Math.toIntExact(boList.stream().filter(c -> c.getUseState() == UseState.INSTOCK).count());//计算库存数
            Integer receiveNo = Math.toIntExact(boList.stream().filter(c -> c.getUseState() == UseState.RECEIVE).count());//计算领用数
            Integer repairNo = Math.toIntExact(boList.stream().filter(c -> c.getMaterialState() == MaterialState.REPAIRING).count());//计算维修数
            Integer transferNo = Math.toIntExact(boList.stream().filter(c -> c.getUseState() == UseState.TRANSFER).count());//计算调动数
            Integer scrapNo = Math.toIntExact(boList.stream().filter(c -> c.getMaterialState() == MaterialState.SCRAP).count());//计算报废数
            Integer inventoryLossNo = accountNo - stockNo - receiveNo - repairNo - transferNo - scrapNo;
            inventoryLossNo = (inventoryLossNo > 0) ? inventoryLossNo : 0;//计算盘亏数
            Double inventoryLossTotal = (accountNo == 0) ? 0 : (total * inventoryLossNo / accountNo);//计算盘亏总额
            Integer inventorySurplusNo = accountNo - stockNo - receiveNo - repairNo - transferNo - scrapNo;
            inventorySurplusNo = (inventorySurplusNo < 0) ? -inventorySurplusNo : 0;   //计算盘盈数
            Double inventorySurplusTotal = (accountNo == 0) ? 0 : (total * inventorySurplusNo / accountNo);//计算盘盈总额

            //逐个设置属性
            MaterialInventoryBO materialInventoryBO = new MaterialInventoryBO();
            materialInventoryBO.setArea(area);
            materialInventoryBO.setProjectGroup(projectGroup);
            materialInventoryBO.setType(type);
            materialInventoryBO.setDeviceName(deviceName);
            materialInventoryBO.setUnit(unit);
            materialInventoryBO.setAccountNo(accountNo);
            materialInventoryBO.setTotal(total);
            materialInventoryBO.setStockNo(stockNo);
            materialInventoryBO.setReceiveNo(receiveNo);
            materialInventoryBO.setRepairNo(repairNo);
            materialInventoryBO.setTransferNo(transferNo);
            materialInventoryBO.setScrapNo(scrapNo);
            materialInventoryBO.setInventoryLossNo(inventoryLossNo);
            materialInventoryBO.setInventoryLossTotal(inventoryLossTotal);
            materialInventoryBO.setInventorySurplusNo(inventorySurplusNo);
            materialInventoryBO.setInventorySurplusTotal(inventorySurplusTotal);
            materialInventoryBO.setInventoryType(inventoryType);

            inventoryBOList.add(materialInventoryBO);
        }

        //如果不为空,则拷贝数据并且保存
        if (!inventoryBOList.isEmpty()) {
            List<MaterialInventory> inventoryList = BeanTransform.copyProperties(inventoryBOList, MaterialInventory.class, true);
            super.save(inventoryList);
        }

        return inventoryBOList;
    }

}