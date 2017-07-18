package com.bjike.goddess.materialreceive.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.materialinstock.api.MaterialInStockAPI;
import com.bjike.goddess.materialinstock.type.MaterialState;
import com.bjike.goddess.materialinstock.type.UseState;
import com.bjike.goddess.materialreceive.bo.MaterialReceiveBO;
import com.bjike.goddess.materialreceive.dto.MaterialReceiveDTO;
import com.bjike.goddess.materialreceive.entity.MaterialReceive;
import com.bjike.goddess.materialreceive.to.GuidePermissionTO;
import com.bjike.goddess.materialreceive.to.MaterialReceiveTO;
import com.bjike.goddess.materialreceive.to.MaterialReturnTO;
import com.bjike.goddess.materialreceive.type.AuditState;
import com.bjike.goddess.materialreceive.type.GuideAddrStatus;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
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

/**
 * 物资领用归还登记业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:41 ]
 * @Description: [ 物资领用归还登记业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialreceiveSerCache")
@Service
public class MaterialReceiveSerImpl extends ServiceImpl<MaterialReceive, MaterialReceiveDTO> implements MaterialReceiveSer {

    @Autowired
    private MaterialInStockAPI materialInStockAPI;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
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
     * 检查权限(福利模块)
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
            throw new SerException("您不是福利模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(岗位)
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
            throw new SerException("您不是项目经理,没有该操作权限");
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
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagMond = guideMondIdentity();
        Boolean flagPosin = guidePosinIdentity();
        if (flagSee || flagMond || flagPosin) {
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
            case AUDIT:
                flag = guideMondIdentity();
                break;
            case RECEIVE:
                flag = guidePosinIdentity();
                break;
            case BREA:
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
     * 分页查询物资领用归还登记
     *
     * @return class MaterialReceiveBO
     * @throws SerException
     */
    @Override
    public List<MaterialReceiveBO> list(MaterialReceiveDTO dto) throws SerException {
        checkPermission();
        List<MaterialReceive> list = super.findByPage(dto);
        List<MaterialReceiveBO> listBO = BeanTransform.copyProperties(list, MaterialReceiveBO.class);
        return listBO;
    }

    /**
     * 保存物资领用归还登记
     *
     * @param to 物资领用归还登记to
     * @return class MaterialReceiveBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialReceiveBO save(MaterialReceiveTO to) throws SerException {
        checkPermission();
        Integer quantity = setReceive(to);//设置领用数量和领用编号
        return saveModel(to, quantity);
    }

    /**
     * 保存物资领用归还登记
     *
     * @param to
     * @param quantity
     * @return
     * @throws SerException
     */
    private MaterialReceiveBO saveModel(MaterialReceiveTO to, Integer quantity) throws SerException {
        MaterialReceive entity = BeanTransform.copyProperties(to, MaterialReceive.class, true);
        entity.setAuditState(AuditState.UNAUDITED);
        entity.setQuantity(quantity);//设置领用数量
        entity = super.save(entity);
        MaterialReceiveBO bo = BeanTransform.copyProperties(entity, MaterialReceiveBO.class);
        return bo;
    }

    /**
     * 设置领用编号
     *
     * @param to
     * @return
     * @throws SerException
     */
    private Integer setReceive(MaterialReceiveTO to) throws SerException {
        String[] materialNum = checkMaterialNum(to);//校验领用物资编号是否为空
        Integer quantity = setMaterialNo(to, materialNum);//设置领用编号
        materialInStockAPI.updateUseState(materialNum, UseState.RECEIVE);    //更新物资使用状态
        return quantity;
    }

    /**
     * 设置物资领用归还登记中的物资编号
     *
     * @param to          物资领用归还登记to
     * @param materialNum 物资编号
     * @return
     */
    private Integer setMaterialNo(MaterialReceiveTO to, String[] materialNum) {
        Integer quantity = to.getMaterialNum().length;//领用数量
        StringBuilder materialNo = new StringBuilder();
        for (int i = 0; i < quantity; i++) {
            if (i < (quantity - 1)) {
                materialNo.append(materialNum[i]).append(",");
            } else {
                materialNo.append(materialNum[i]);
            }
        }
        to.setMaterialNo(materialNo.toString());//设置领用编号
        return quantity;
    }

    /**
     * 校验领用物资是否为空
     *
     * @param to
     * @return
     * @throws SerException
     */
    private String[] checkMaterialNum(MaterialReceiveTO to) throws SerException {
        String[] materialNum = to.getMaterialNum();//获取入库编码
        Boolean materialNumNotEmpty = (materialNum != null) && (materialNum.length > 0);
        if (!materialNumNotEmpty) {
            throw new SerException("物资入库编码为空,无法领用.");
        }
        return materialNum;
    }

    /**
     * 根据id删除物资领用归还登记
     *
     * @param id 物资领用归还登记唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }

    /**
     * 更新物资领用归还登记
     *
     * @param to 物资领用归还登记to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialReceiveTO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialReceive model = super.findById(to.getId());
            if (model != null) {
                updateUseState(model, UseState.INSTOCK);//更新物资使用状态
                setReceive(to);
                updateMaterialReceive(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新物资入库状态
     *
     * @param model    物资入库
     * @param useState 使用状态
     * @throws SerException
     */
    private void updateUseState(MaterialReceive model, UseState useState) throws SerException {
        String materialNo = model.getMaterialNo();
        String[] materialNum = materialNo.split(",");
        materialInStockAPI.updateUseState(materialNum, useState);
    }

    /**
     * 更新物资领用归还登记
     *
     * @param to    物资领用归还登记to
     * @param model 物资领用归还登记
     * @throws SerException
     */
    private void updateMaterialReceive(MaterialReceiveTO to, MaterialReceive model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 审核
     *
     * @param id           物资领用归还登记唯一标识
     * @param auditState   审核状态
     * @param auditOpinion 审核意见
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void audit(String id, AuditState auditState, String auditOpinion) throws SerException {
        checkPonsPermission();
        if (StringUtils.isNotBlank(id)) {
            MaterialReceive model = super.findById(id);
            model.setAuditState(auditState);
            model.setAuditOpinion(auditOpinion);
            super.update(model);
        } else {
            throw new SerException("更新id不能为空");
        }
    }

    /**
     * 领用成功
     *
     * @param to 物资领用归还登记to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void receiveOver(MaterialReceiveTO to) throws SerException {
        checkModPermission();
        if (StringUtils.isNotBlank(to.getId())) {
            MaterialReceive model = super.findById(to.getId());
            model.setModel(to.getModel());
            model.setOldStorageArea(to.getOldStorageArea());
            model.setOldPrincipal(to.getOldPrincipal());
            model.setHandler(to.getHandler());
            super.update(model);
        } else {
            throw new SerException("更新id不能为空");
        }

    }

    /**
     * 物资归还
     *
     * @param to 物资归还to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void materialReturn(MaterialReturnTO to) throws SerException {
        checkModPermission();
        setUseStateToInStock(to);//更新使用状态为在库
        String id = to.getId();
        if (StringUtils.isBlank(id)) {
            throw new SerException("物资归还id为空,无法执行归还操作");
        }
        MaterialReceive model = super.findById(id);
        Boolean ifReturn = to.getIfReturn();//是否归还
        MaterialState materialState = to.getMaterialState();//物资状态
        String returnTime = to.getReturnTime();//归还时间
        AuditState auditCase = to.getAuditCase();//审核情况
        model.setIfReturn(ifReturn);
        model.setMaterialState(materialState);
        model.setReturnTime(DateUtil.parseDateTime(returnTime));
        model.setAuditCase(auditCase);
        super.update(model);
    }

    /**
     * 更新用户状态为在库
     *
     * @param to 物资领用归还登记to
     * @throws SerException
     */
    private void setUseStateToInStock(MaterialReturnTO to) throws SerException {
        String[] materialNum = to.getMaterialNum();//获取归还的物资编号
        materialInStockAPI.updateUseState(materialNum, UseState.INSTOCK);
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
}