package com.bjike.goddess.deviceinventory.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.deviceinventory.api.InventoryRecordAPI;
import com.bjike.goddess.deviceinventory.bo.InventoryBO;
import com.bjike.goddess.deviceinventory.dto.InventoryDTO;
import com.bjike.goddess.deviceinventory.entity.Inventory;
import com.bjike.goddess.deviceinventory.enums.GuideAddrStatus;
import com.bjike.goddess.deviceinventory.excel.InventoryExcel;
import com.bjike.goddess.deviceinventory.to.GuidePermissionTO;
import com.bjike.goddess.deviceinventory.to.InventoryRecordTO;
import com.bjike.goddess.deviceinventory.to.InventoryTO;
import com.bjike.goddess.deviceinventory.vo.SonPermissionObject;
import com.bjike.goddess.materialinstock.api.MaterialInStockAPI;
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.dto.MaterialInStockDTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 盘点业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-19 08:54 ]
 * @Description: [ 盘点业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "deviceinventorySerCache")
@Service
public class InventorySerImpl extends ServiceImpl<Inventory, InventoryDTO> implements InventorySer {
    @Autowired
    private MaterialInStockAPI materialInStockAPI;
    @Autowired
    private InventoryRecordAPI inventoryRecordAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private InventoryRecordSer inventoryRecordSer;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("inventory");
        obj.setDescribesion("盘点");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = inventoryRecordSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("inventoryrecord");
        obj.setDescribesion("盘点记录");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case INVENTORY:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    public InventoryBO findByID(String id) throws SerException {
        Inventory inventory = super.findById(id);
        if (inventory == null) {
            throw new SerException("该对象不存在");
        }
        InventoryBO bo = BeanTransform.copyProperties(inventory, InventoryBO.class);
        if (bo.getInventoryNum() != null) {
            int a = bo.getInventoryNum() - bo.getQuantity();
            bo.setProfitLoss(a);
            bo.setProfitLossCount(a * bo.getUnitPrice());
        }
        return bo;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<InventoryBO> list(InventoryDTO dto) throws SerException {
        checkSeeIdentity();
        String[] stockEncodings = dto.getStockEncodings();
        MaterialInStockDTO materialInStockDTO = new MaterialInStockDTO();
        materialInStockDTO.getConditions().add(Restrict.in("stockEncoding", stockEncodings));
        List<MaterialInStockBO> list = materialInStockAPI.findBOByCis(materialInStockDTO);
        List<Inventory> inventorys = super.findAll();
        if (list != null) {
            for (MaterialInStockBO v : list) {
                if (inventorys.size() == 0) {
                    Inventory inventory = new Inventory();
                    BeanUtils.copyProperties(v, inventory);
                    inventory.setPurchaseDate(DateUtil.parseDate(v.getPurchaseDate()));
                    inventory.setMaterialinstockId(v.getId());
                    super.save(inventory);
                } else {
                    boolean b1 = true;
                    for (Inventory p : inventorys) {
                        if (p.getMaterialinstockId().equals(v.getId())) {
                            p.setStockEncoding(v.getStockEncoding());
                            p.setStorageArea(v.getStorageArea());
                            p.setProjectGroup(v.getProjectGroup());
                            p.setMaterialName(v.getMaterialName());
                            p.setUnit(v.getUnit());
                            p.setUnitPrice(v.getUnitPrice());
                            p.setPurchaseDate(DateUtil.parseDate(v.getPurchaseDate()));
                            p.setWarrantyExpire(v.getWarrantyExpire());
                            super.update(p);
                            b1 = false;
                        }
                    }
                    if (b1) {
                        Inventory inventory = new Inventory();
                        BeanUtils.copyProperties(v, inventory);
                        inventory.setPurchaseDate(DateUtil.parseDate(v.getPurchaseDate()));
                        inventory.setMaterialinstockId(v.getId());
                        super.save(inventory);
                    }
                }
            }
        }
        for (Inventory p : super.findAll()) {
            MaterialInStockBO v = materialInStockAPI.findById(p.getMaterialinstockId());
            if (v == null) {
                super.remove(p.getId());
            }
        }
        List<Inventory> list1 = super.findByCis(dto, true);
        List<InventoryBO> boList = new ArrayList<InventoryBO>();
        for (Inventory i : list1) {
            InventoryBO bo = BeanTransform.copyProperties(i, InventoryBO.class);
            if (bo.getInventoryNum() != null) {
                int a = bo.getInventoryNum() - bo.getQuantity();
                bo.setProfitLoss(a);
                bo.setProfitLossCount(a * bo.getUnitPrice());
            }
            boList.add(bo);
        }
        return boList;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void inventory(InventoryTO to) throws SerException {
        checkAddIdentity();
        Inventory inventory = super.findById(to.getId());
        if (inventory == null) {
            throw new SerException("该对象不存在");
        }
        Boolean isInventory=inventory.getIsInventory();
        if (isInventory!=null&&isInventory){
            throw new SerException("您已盘点过该记录");
        }
        inventory.setInventoryTime(DateUtil.parseDate(to.getInventoryTime()));
        inventory.setQuantity(to.getQuantity());
        inventory.setInventoryNum(to.getInventoryNum());
        inventory.setDifferThat(to.getDifferThat());
        inventory.setResponsible(to.getResponsible());
        inventory.setState(to.getState());
        inventory.setInventoryMan(to.getInventoryMan());
        inventory.setNote(to.getNote());
        inventory.setModifyTime(LocalDateTime.now());
        inventory.setIsInventory(true);
        super.update(inventory);
        InventoryRecordTO recordTO = BeanTransform.copyProperties(inventory, InventoryRecordTO.class);
        int a = recordTO.getInventoryNum() - recordTO.getQuantity();
        recordTO.setProfitLoss(a);
        recordTO.setProfitLossCount(a * recordTO.getUnitPrice());
        inventoryRecordAPI.save(recordTO);
    }

    @Override
    public byte[] export(String startTime, String endTime) throws SerException {
//        checkSeeIdentity();
        LocalDate s = null;
        LocalDate e = null;
        try {
            s = DateUtil.parseDate(startTime);
            e = DateUtil.parseDate(endTime);
        } catch (Exception ex) {
            throw new SerException("日期格式错误");
        }
        LocalDate[] time = new LocalDate[]{s, e};
        InventoryDTO dto = new InventoryDTO();
        dto.getConditions().add(Restrict.between("inventoryTime", time));
        List<Inventory> list = super.findByCis(dto);
        if (list == null || list.isEmpty()) {
            throw new SerException("该时间段没有数据");
        }
        List<InventoryBO> boList = new ArrayList<InventoryBO>();
        for (Inventory i : list) {
            InventoryBO bo = BeanTransform.copyProperties(i, InventoryBO.class);
            if (bo.getInventoryNum() != null) {
                int a = bo.getInventoryNum() - bo.getQuantity();
                bo.setProfitLoss(a);
                bo.setProfitLossCount(a * bo.getUnitPrice());
            }
            boList.add(bo);
        }
        List<InventoryExcel> toList = BeanTransform.copyProperties(boList, InventoryExcel.class, true);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    public Long count(InventoryDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public Set<String> allstockEncoding() throws SerException {
        return materialInStockAPI.allstockEncoding();
    }
}