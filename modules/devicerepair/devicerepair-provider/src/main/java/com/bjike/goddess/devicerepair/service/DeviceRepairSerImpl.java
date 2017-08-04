package com.bjike.goddess.devicerepair.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.devicerepair.bo.DeviceRepairBO;
import com.bjike.goddess.devicerepair.dto.DeviceRepairDTO;
import com.bjike.goddess.devicerepair.entity.DeviceRepair;
import com.bjike.goddess.devicerepair.excel.SonPermissionObject;
import com.bjike.goddess.devicerepair.to.DeviceRepairTO;
import com.bjike.goddess.devicerepair.to.FetchDeviceTO;
import com.bjike.goddess.devicerepair.to.GuidePermissionTO;
import com.bjike.goddess.devicerepair.to.WelfareAuditTO;
import com.bjike.goddess.devicerepair.type.AuditState;
import com.bjike.goddess.devicerepair.type.GuideAddrStatus;
import com.bjike.goddess.devicerepair.type.MaterialState;
import com.bjike.goddess.materialinstock.api.MaterialInStockAPI;
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.to.MaterialInStockTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static com.bjike.goddess.materialinstock.type.MaterialState.INTACT;
import static com.bjike.goddess.materialinstock.type.MaterialState.REPAIRING;

/**
 * 设备维修业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-03 02:59 ]
 * @Description: [ 设备维修业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "devicerepairSerCache")
@Service
public class DeviceRepairSerImpl extends ServiceImpl<DeviceRepair, DeviceRepairDTO> implements DeviceRepairSer {

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private MaterialInStockAPI materialInStockAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
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
     * 检查权限(模块)
     *
     * @throws SerException
     */
    private void checkAuditPermission() throws SerException {
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
            throw new SerException("您不是福利模块人员,没有该操作权限");
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
     * 核对福利模块审核权限（模块级别）
     */
    private Boolean guideAuditIdentity() throws SerException {
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


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagDeviceRep = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagDeviceAuditRep = guideAuditIdentity();
        RpcTransmit.transmitUserToken(userToken);
        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("devicerepair");
        obj.setDescribesion("设备维修");
        if (flagDeviceRep || flagDeviceAuditRep) {
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
            case UPLOAD:
                flag = guideIdentity();
                break;
            case DOWNLOAD:
                flag = guideIdentity();
                break;
            case SCRAP:
                flag = guideIdentity();
                break;
            case RETRAEVE:
                flag = guideIdentity();
                break;
            case AUDIT:
                flag = guideAuditIdentity();
                break;
            case SEEFILE:
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
     * 分页查询设备维修
     *
     * @param dto 设备维修dto
     * @return class DeviceRepairBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<DeviceRepairBO> list(DeviceRepairDTO dto) throws SerException {
        checkPermission();
        List<DeviceRepair> list = super.findByPage(dto);
        List<DeviceRepairBO> boList = BeanTransform.copyProperties(list, DeviceRepairBO.class);
        return boList;
    }

    /**
     * 保存设备维修
     *
     * @param to 设备维修to
     * @return class DeviceRepairBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public DeviceRepairBO save(DeviceRepairTO to) throws SerException {
        checkPermission();
        String materialCoding = to.getMaterialCoding();//获取物资编号
        MaterialInStockBO inStockBO = updateMaterialInStockBO(to);
        String materialName = inStockBO.getMaterialName();   //获取设备名称
        DeviceRepair entity = BeanTransform.copyProperties(to, DeviceRepair.class, true);
        entity.setDeviceName(materialName);               //设置设备名称
        entity.setWelfareAuditState(AuditState.UNAUDITED);//福利模块未审核
        entity.setPmAuditState(AuditState.UNAUDITED);     //项目经理未审核
        entity.setWhetherPayment(Boolean.FALSE);
        entity.setMaterialState(MaterialState.WAITING_REPAIR);//设置物资状态为待维修
        entity = super.save(entity);
        DeviceRepairBO bo = BeanTransform.copyProperties(entity, DeviceRepairBO.class);
        return bo;
    }

    /**
     * 更新物资入库
     *
     * @param to 物资入库to
     * @return class MaterialInStockBO
     * @throws SerException
     */
    private MaterialInStockBO updateMaterialInStockBO(DeviceRepairTO to) throws SerException {
        String materialCoding = to.getMaterialCoding();//获取物资编号
        MaterialInStockBO inStockBO = materialInStockAPI.findByMaterialCoding(materialCoding);
        if (inStockBO == null) {
            throw new SerException("该物资编号不存在,无法申请维修.");
        }
        MaterialInStockTO inStockTO = BeanTransform.copyProperties(inStockBO, MaterialInStockTO.class);
        inStockTO.setMaterialState(REPAIRING);//设置物资状态
        materialInStockAPI.updateLijuntao(inStockTO);
        return inStockBO;
    }

    /**
     * 根据id删除设备维修
     *
     * @param id 设备维修唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }

    /**
     * 更新设备维修
     *
     * @param to 设备维修to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(DeviceRepairTO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
            DeviceRepair model = super.findById(to.getId());
            if (model != null) {
                updateDeviceRepair(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新物资入库
     *
     * @param to 物资入库to
     * @throws SerException
     */
    private void updateInStock(DeviceRepairTO to) throws SerException {
        if (MaterialState.WAITING_REPAIR != to.getMaterialState()) {
            MaterialInStockBO inStockBO = materialInStockAPI.findByMaterialCoding(to.getMaterialCoding());
            MaterialInStockTO inStockTO = BeanTransform.copyProperties(inStockBO, MaterialInStockTO.class);
            inStockBO.setMaterialState(INTACT);
            materialInStockAPI.update(inStockTO);
        }
    }

    /**
     * 更新设备维修
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateDeviceRepair(DeviceRepairTO to, DeviceRepair model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 福利模块审核
     *
     * @param to 福利模块审核to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void welfareAudit(WelfareAuditTO to) throws SerException {
        checkAuditPermission();
        if (StringUtils.isNotBlank(to.getId())) {
            DeviceRepair model = super.findById(to.getId());
            if (model != null) {
                welfareAudit(to, model);
            } else {
                throw new SerException("福利模块审核对象不能为空.");
            }
        } else {
            throw new SerException("福利模块审核id不能为空.");
        }
    }

    /**
     * 项目经理审核状态
     *
     * @param id           设备维修唯一标识
     * @param pmAuditState 项目经理审核状态
     * @return class DeviceRepairBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void pmAudit(String id, AuditState pmAuditState) throws SerException {
        DeviceRepair model = super.findById(id);
        if (model != null) {
            model.setPmAuditState(pmAuditState);//项目经理审核状态
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("项目经理审核对象不能为空.");
        }
    }


    /**
     * 福利模块审核
     *
     * @param to    设备维修to
     * @param model 设备维修
     * @throws SerException
     */
    private void welfareAudit(WelfareAuditTO to, DeviceRepair model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 设备报废
     *
     * @param id          设备维修唯一标识
     * @param deviceIssue 设备出现的问题
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void deviceScrap(String id, String deviceIssue) throws SerException {
        checkPermission();
        DeviceRepair model = super.findById(id);
        if (model != null) {
            model.setMaterialState(MaterialState.SCRAPED);//设置状态为报废
            model.setDeviceIssue(deviceIssue);            //设备出现的问题
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("更新对象不能为空.");
        }
    }

    /**
     * 取回设备
     *
     * @param to 取回设备
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void fetchDevice(FetchDeviceTO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotBlank(to.getId())) {
            DeviceRepair model = super.findById(to.getId());
            if (model != null) {
                fetchDevice(to, model);
            } else {
                throw new SerException("更新对象不能为空.");
            }
        } else {
            throw new SerException("设备维修id不能为空.");
        }
    }

    /**
     * 取回设备
     *
     * @param to    取回设备to
     * @param model 设备维修
     */
    private void fetchDevice(FetchDeviceTO to, DeviceRepair model) throws SerException {
        checkPermission();
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }


    @Override
    public List<String> findAddAllDetails() throws SerException {
        List<DepartmentDetailBO> departmentDetailBOS = departmentDetailAPI.findStatus();
        if (CollectionUtils.isEmpty(departmentDetailBOS)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (DepartmentDetailBO departmentDetailBO : departmentDetailBOS){
            String details = departmentDetailBO.getDepartment();
            if (StringUtils.isNotBlank(departmentDetailBO.getDepartment())) {
                set.add(details);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findallMonUser() throws SerException {
        List<UserBO> userBOS = positionDetailUserAPI.findUserList();
        if (CollectionUtils.isEmpty(userBOS)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (UserBO userBO : userBOS){
            String userName = userBO.getUsername();
            if (StringUtils.isNotBlank(userBO.getUsername())) {
                set.add(userName);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<MaterialState> findDeviceStatus(String[] intervalTime) throws SerException {
        DeviceRepairDTO deviceRepairDTO = new DeviceRepairDTO();
        deviceRepairDTO.getConditions().add(Restrict.between("planOverRepairTime",intervalTime));
        List<DeviceRepair> deviceRepairs = super.findByCis(deviceRepairDTO);
        if (CollectionUtils.isEmpty(deviceRepairs)) {
            return Collections.emptyList();
        }
        Set<MaterialState> set = new HashSet<>();
        for (DeviceRepair deviceRepair : deviceRepairs){
            MaterialState status = deviceRepair.getMaterialState();
            if (deviceRepair.getMaterialState()!=null) {
                set.add(status);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findAreaByStatus(String[] intervalTime,MaterialState materialState) throws SerException {
        DeviceRepairDTO deviceRepairDTO = new DeviceRepairDTO();

        deviceRepairDTO.getConditions().add(Restrict.between("planOverRepairTime", intervalTime));
        deviceRepairDTO.getConditions().add(Restrict.eq("materialState", materialState.getValue()));
        List<DeviceRepair> deviceRepairs = super.findByCis(deviceRepairDTO);
        if (CollectionUtils.isEmpty(deviceRepairs)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (DeviceRepair deviceRepair : deviceRepairs) {
            String area = deviceRepair.getArea();
            if (StringUtils.isNotBlank(deviceRepair.getArea())) {
                set.add(area);
            }
        }
        return new ArrayList<>(set);
    }
    @Override
    public List<String> findProjectByStaAnAr(String[] intervalTime,MaterialState materialState,String area) throws SerException {
        DeviceRepairDTO deviceRepairDTO = new DeviceRepairDTO();
        deviceRepairDTO.getConditions().add(Restrict.between("planOverRepairTime", intervalTime));
        deviceRepairDTO.getConditions().add(Restrict.eq("materialState", materialState.getValue()));
        deviceRepairDTO.getConditions().add(Restrict.eq("area", area));
        List<DeviceRepair> deviceRepairs = super.findByCis(deviceRepairDTO);
        if (CollectionUtils.isEmpty(deviceRepairs)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (DeviceRepair deviceRepair : deviceRepairs) {
            String projectGroup = deviceRepair.getProjectGroup();
            if (StringUtils.isNotBlank(deviceRepair.getProjectGroup())) {
                set.add(projectGroup);
            }
        }
        return new ArrayList<>(set);
    }
    @Override
    public List<DeviceRepairBO> findByStaAnAr(String[] intervalTime, MaterialState materialState, String area,String projectGroup) throws SerException {
        DeviceRepairDTO deviceRepairDTO = new DeviceRepairDTO();
        deviceRepairDTO.getConditions().add(Restrict.between("planOverRepairTime", intervalTime));
        deviceRepairDTO.getConditions().add(Restrict.eq("materialState", materialState.getValue()));
        deviceRepairDTO.getConditions().add(Restrict.eq("area",area));
        deviceRepairDTO.getConditions().add(Restrict.eq("projectGroup",projectGroup));
        List<DeviceRepair> deviceRepairs = super.findByCis(deviceRepairDTO);
        return BeanTransform.copyProperties(deviceRepairs,DeviceRepairBO.class);
    }

    @Override
    public List<Boolean> findBool(String[] intervalTime) throws SerException {
        DeviceRepairDTO deviceRepairDTO = new DeviceRepairDTO();
        deviceRepairDTO.getConditions().add(Restrict.between("planOverRepairTime",intervalTime));
        List<DeviceRepair> deviceRepairs = super.findByCis(deviceRepairDTO);
        if (CollectionUtils.isEmpty(deviceRepairs)) {
            return Collections.emptyList();
        }
        Set<Boolean> set = new HashSet<>();
        for (DeviceRepair deviceRepair : deviceRepairs){
            Boolean whetherWarranty = deviceRepair.getWhetherWarranty();
            if (deviceRepair.getWhetherWarranty()!=null) {
                set.add(whetherWarranty);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findAreaByBool(String[] intervalTime, Boolean whetherWarranty) throws SerException {
        DeviceRepairDTO deviceRepairDTO = new DeviceRepairDTO();
        deviceRepairDTO.getConditions().add(Restrict.between("planOverRepairTime",intervalTime));
        deviceRepairDTO.getConditions().add(Restrict.eq("whetherWarranty",whetherWarranty?1:0));
        List<DeviceRepair> deviceRepairs = super.findByCis(deviceRepairDTO);
        if (CollectionUtils.isEmpty(deviceRepairs)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (DeviceRepair deviceRepair : deviceRepairs){
            String area = deviceRepair.getArea();
            if (StringUtils.isNotBlank(deviceRepair.getArea())) {
                set.add(area);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findProjByBoArea(String[] intervalTime, String area, Boolean whetherWarranty) throws SerException {
        DeviceRepairDTO deviceRepairDTO = new DeviceRepairDTO();
        deviceRepairDTO.getConditions().add(Restrict.between("planOverRepairTime",intervalTime));
        deviceRepairDTO.getConditions().add(Restrict.eq("whetherWarranty",whetherWarranty?1:0));
        deviceRepairDTO.getConditions().add(Restrict.eq("area",area));
        List<DeviceRepair> deviceRepairs = super.findByCis(deviceRepairDTO);
        if (CollectionUtils.isEmpty(deviceRepairs)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (DeviceRepair deviceRepair : deviceRepairs){
            String projectGroup = deviceRepair.getProjectGroup();
            if (StringUtils.isNotBlank(deviceRepair.getProjectGroup())) {
                set.add(projectGroup);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<DeviceRepairBO> findByBoAnArDep(String[] intervalTime, String area, Boolean whetherWarranty, String projectGroup) throws SerException {
        DeviceRepairDTO deviceRepairDTO = new DeviceRepairDTO();
        deviceRepairDTO.getConditions().add(Restrict.between("planOverRepairTime", intervalTime));
        deviceRepairDTO.getConditions().add(Restrict.eq("whetherWarranty", whetherWarranty?1:0));
        deviceRepairDTO.getConditions().add(Restrict.eq("area",area));
        deviceRepairDTO.getConditions().add(Restrict.eq("projectGroup",projectGroup));
        List<DeviceRepair> deviceRepairs = super.findByCis(deviceRepairDTO);
        return BeanTransform.copyProperties(deviceRepairs,DeviceRepairBO.class);
    }
}