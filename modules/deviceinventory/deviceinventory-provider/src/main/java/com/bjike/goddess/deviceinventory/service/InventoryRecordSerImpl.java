package com.bjike.goddess.deviceinventory.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.deviceinventory.bo.InventoryRecordBO;
import com.bjike.goddess.deviceinventory.dto.InventoryRecordDTO;
import com.bjike.goddess.deviceinventory.entity.InventoryRecord;
import com.bjike.goddess.deviceinventory.enums.GuideAddrStatus;
import com.bjike.goddess.deviceinventory.excel.InventoryRecordExcel;
import com.bjike.goddess.deviceinventory.to.GuidePermissionTO;
import com.bjike.goddess.deviceinventory.to.InventoryRecordTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 盘点记录业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-19 09:33 ]
 * @Description: [ 盘点记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "deviceinventorySerCache")
@Service
public class InventoryRecordSerImpl extends ServiceImpl<InventoryRecord, InventoryRecordDTO> implements InventoryRecordSer {
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

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
                throw new SerException("您不是相应部门的人员，不可以操作");
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
     * 核对查看权限（部门级别）
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
     * 核对添加修改删除审核权限（岗位级别）
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
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void save(InventoryRecordTO to) throws SerException {
        InventoryRecord inventoryRecord = BeanTransform.copyProperties(to, InventoryRecord.class, true);
        super.save(inventoryRecord);
    }

    @Override
    public List<InventoryRecordBO> list(InventoryRecordDTO dto) throws SerException {
        checkSeeIdentity();
        List<InventoryRecord> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, InventoryRecordBO.class);
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
        InventoryRecordDTO dto = new InventoryRecordDTO();
        dto.getConditions().add(Restrict.between("inventoryTime", time));
        List<InventoryRecord> list = super.findByCis(dto);
        if (list==null||list.isEmpty()){
            throw new SerException("该时间段没有数据");
        }
        List<InventoryRecordExcel> toList=BeanTransform.copyProperties(list,InventoryRecordExcel.class,true);
        Excel excel=new Excel(0,2);
        byte[] bytes= ExcelUtil.clazzToExcel(toList,excel);
        return bytes;
    }

    @Override
    public List<InventoryRecordBO> areaCount() throws SerException {
        checkSeeIdentity();
        List<InventoryRecord> list = super.findAll();
        List<InventoryRecordBO> boList = new ArrayList<InventoryRecordBO>();
        int quantitySum = 0;
        int inventoryNumSum = 0;
        int profitLossSum = 0;
        double profitLossCountSum = 0.00;
        for (String area : findAllAreas()) {
            for (InventoryRecord i : list) {
                if (area.equals(i.getStorageArea())) {
                    quantitySum += i.getQuantity();
                    inventoryNumSum += i.getInventoryNum();
                    profitLossSum += i.getProfitLoss();
                    profitLossCountSum += i.getProfitLossCount();
                    InventoryRecordBO bo = BeanTransform.copyProperties(i, InventoryRecordBO.class);
                    boList.add(bo);
                }
            }
            if (quantitySum != 0) {
                InventoryRecordBO bo = new InventoryRecordBO();
                bo.setQuantitySum(quantitySum);
                bo.setInventoryNumSum(inventoryNumSum);
                bo.setProfitLossSum(profitLossSum);
                bo.setProfitLossCountSum(profitLossCountSum);
                boList.add(bo);
                quantitySum = 0;
                inventoryNumSum = 0;
                profitLossSum = 0;
                profitLossCountSum = 0.00;  //置为0
            }
        }
        return boList;
    }

    @Override
    public List<InventoryRecordBO> projectGroupCount() throws SerException {
        checkSeeIdentity();
        List<InventoryRecord> list = super.findAll();
        List<InventoryRecordBO> boList = new ArrayList<InventoryRecordBO>();
        int quantitySum = 0;
        int inventoryNumSum = 0;
        int profitLossSum = 0;
        double profitLossCountSum = 0.00;
        for (String projectGroup : findAllProjectGroups()) {
            for (InventoryRecord i : list) {
                if (projectGroup.equals(i.getProjectGroup())) {
                    quantitySum += i.getQuantity();
                    inventoryNumSum += i.getInventoryNum();
                    profitLossSum += i.getProfitLoss();
                    profitLossCountSum += i.getProfitLossCount();
                    InventoryRecordBO bo = BeanTransform.copyProperties(i, InventoryRecordBO.class);
                    boList.add(bo);
                }
            }
            if (quantitySum != 0) {
                InventoryRecordBO bo = new InventoryRecordBO();
                bo.setQuantitySum(quantitySum);
                bo.setInventoryNumSum(inventoryNumSum);
                bo.setProfitLossSum(profitLossSum);
                bo.setProfitLossCountSum(profitLossCountSum);
                boList.add(bo);
                quantitySum = 0;
                inventoryNumSum = 0;
                profitLossSum = 0;
                profitLossCountSum = 0.00;  //置为0
            }
        }
        return boList;
    }

    @Override
    public List<InventoryRecordBO> materialNameCount() throws SerException {
        checkSeeIdentity();
        List<InventoryRecord> list = super.findAll();
        List<InventoryRecordBO> boList = new ArrayList<InventoryRecordBO>();
        int quantitySum = 0;
        int inventoryNumSum = 0;
        int profitLossSum = 0;
        double profitLossCountSum = 0.00;
        for (String materialName : findAllMaterialNames()) {
            for (InventoryRecord i : list) {
                if (materialName.equals(i.getMaterialName())) {
                    quantitySum += i.getQuantity();
                    inventoryNumSum += i.getInventoryNum();
                    profitLossSum += i.getProfitLoss();
                    profitLossCountSum += i.getProfitLossCount();
                    InventoryRecordBO bo = BeanTransform.copyProperties(i, InventoryRecordBO.class);
                    boList.add(bo);
                }
            }
            if (quantitySum != 0) {
                InventoryRecordBO bo = new InventoryRecordBO();
                bo.setQuantitySum(quantitySum);
                bo.setInventoryNumSum(inventoryNumSum);
                bo.setProfitLossSum(profitLossSum);
                bo.setProfitLossCountSum(profitLossCountSum);
                boList.add(bo);
                quantitySum = 0;
                inventoryNumSum = 0;
                profitLossSum = 0;
                profitLossCountSum = 0.00;  //置为0
            }
        }
        return boList;
    }

    @Override
    public List<InventoryRecordBO> stateCount() throws SerException {
        checkSeeIdentity();
        List<InventoryRecord> list = super.findAll();
        List<InventoryRecordBO> boList = new ArrayList<InventoryRecordBO>();
        int quantitySum = 0;
        int inventoryNumSum = 0;
        int profitLossSum = 0;
        double profitLossCountSum = 0.00;
        for (String state : findAllStates()) {
            for (InventoryRecord i : list) {
                if (state.equals(i.getState())) {
                    quantitySum += i.getQuantity();
                    inventoryNumSum += i.getInventoryNum();
                    profitLossSum += i.getProfitLoss();
                    profitLossCountSum += i.getProfitLossCount();
                    InventoryRecordBO bo = BeanTransform.copyProperties(i, InventoryRecordBO.class);
                    boList.add(bo);
                }
            }
            if (quantitySum != 0) {
                InventoryRecordBO bo = new InventoryRecordBO();
                bo.setQuantitySum(quantitySum);
                bo.setInventoryNumSum(inventoryNumSum);
                bo.setProfitLossSum(profitLossSum);
                bo.setProfitLossCountSum(profitLossCountSum);
                boList.add(bo);
                quantitySum = 0;
                inventoryNumSum = 0;
                profitLossSum = 0;
                profitLossCountSum = 0.00;  //置为0
            }
        }
        return boList;
    }

    @Override
    public Long count(InventoryRecordDTO dto) throws SerException{
        return super.count(dto);
    }

    /**
     * 查找所有地区
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllAreas() throws SerException {
        List<InventoryRecord> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (InventoryRecord i : list) {
            set.add(i.getStorageArea());
        }
        return set;
    }

    /**
     * 查找所有部门
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllProjectGroups() throws SerException {
        List<InventoryRecord> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (InventoryRecord i : list) {
            set.add(i.getProjectGroup());
        }
        return set;
    }

    /**
     * 查找所有物资名称
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllMaterialNames() throws SerException {
        List<InventoryRecord> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (InventoryRecord i : list) {
            set.add(i.getMaterialName());
        }
        return set;
    }

    /**
     * 查找所有状态
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllStates() throws SerException {
        List<InventoryRecord> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (InventoryRecord i : list) {
            set.add(i.getState());
        }
        return set;
    }
}