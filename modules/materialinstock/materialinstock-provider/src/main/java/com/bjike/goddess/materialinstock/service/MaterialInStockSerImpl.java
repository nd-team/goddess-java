package com.bjike.goddess.materialinstock.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialinstock.bo.AttributeBO;
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.dto.MaterialInStockDTO;
import com.bjike.goddess.materialinstock.entity.MaterialInStock;
import com.bjike.goddess.materialinstock.to.GuidePermissionTO;
import com.bjike.goddess.materialinstock.to.MaterialInStockTO;
import com.bjike.goddess.materialinstock.type.GuideAddrStatus;
import com.bjike.goddess.materialinstock.type.MaterialState;
import com.bjike.goddess.materialinstock.type.UseState;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 物资入库业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 04:55 ]
 * @Description: [ 物资入库业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialinstockSerCache")
@Service
public class MaterialInStockSerImpl extends ServiceImpl<MaterialInStock, MaterialInStockDTO> implements MaterialInStockSer {

    @Autowired
    private UserAPI userAPI;
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


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
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
            case UPLOAD:
                flag = guideIdentity();
                break;
            case DOWNLOAD:
                flag = guideIdentity();
                break;
            case SEEFILE:
                flag = guideIdentity();
                break;
            case SEE:
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
     * 分页查询物资入库
     *
     * @return class MaterialInStockBO
     * @throws SerException
     */
    @Override
    public List<MaterialInStockBO> list(MaterialInStockDTO dto) throws SerException {
        checkPermission();
        List<MaterialInStock> list = super.findByPage(dto);
        List<MaterialInStockBO> listBO = BeanTransform.copyProperties(list, MaterialInStockBO.class);
        return listBO;
    }

    @Override
    public List<MaterialInStock> findByCis(MaterialInStockDTO dto) throws SerException {
        return super.findByCis(dto);
    }

    /**
     * 根据物资状态和物资使用状态查询物资入库
     *
     * @param materialState 物资状态
     * @param useState      物资使用状态
     * @param dto           物资入库dto
     * @return
     * @throws SerException
     */
    @Override
    public List<MaterialInStockBO> findByState(MaterialState materialState, UseState useState, MaterialInStockDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("materialState", materialState));
        dto.getConditions().add(Restrict.eq("useState", useState));
        List<MaterialInStock> list = super.findByPage(dto);
        List<MaterialInStockBO> listBO = BeanTransform.copyProperties(list, MaterialInStockBO.class);
        return listBO;
    }

    /**
     * 根据物资编号查询物资入库
     *
     * @param materialCoding 物资编号
     * @return class MaterialInStockBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialInStockBO findByMaterialCoding(String materialCoding) throws SerException {
        MaterialInStockDTO dto = new MaterialInStockDTO();
        dto.getConditions().add(Restrict.eq("stockEncoding", materialCoding));
        MaterialInStock model = super.findOne(dto);
        return BeanTransform.copyProperties(model, MaterialInStockBO.class);
    }

    /**
     * 更新物资使用状态
     *
     * @param materialNum 物资编号集合
     * @param useState    使用状态
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void updateUseState(String[] materialNum, UseState useState) throws SerException {
        List<MaterialInStock> list = getMaterialInStocks(materialNum);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        for (MaterialInStock model : list) {
            model.setUseState(useState);
        }
        super.update(list);
    }

    /**
     * 根据物资编号查询物资
     *
     * @param materialNum 物资编号
     * @return 物资入库集合
     * @throws SerException
     */
    @Override
    public List<MaterialInStock> getMaterialInStocks(String[] materialNum) throws SerException {
        MaterialInStockDTO dto = new MaterialInStockDTO();
        dto.getConditions().add(Restrict.in("stockEncoding", materialNum));//入库编码
        return super.findByCis(dto);
    }

    /**
     * 保存物资入库
     *
     * @param to 物资入库to
     * @return class MaterialInStockBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialInStockBO save(MaterialInStockTO to) throws SerException {
        checkPermission();
        MaterialInStock entity = BeanTransform.copyProperties(to, MaterialInStock.class, true);
        String stockEncoding = appendStockEncoding(entity);
        entity.setStockEncoding(stockEncoding);
        entity.setUseState(UseState.INSTOCK);//将使用状态设置为在库
        entity.setQuantity(1);               //设置数量
        entity = super.save(entity);
        MaterialInStockBO bo = BeanTransform.copyProperties(entity, MaterialInStockBO.class);
        return bo;
    }

    /**
     * 计算物资入库编码
     *
     * @param entity 物资入库实体
     * @return 物资入库编码
     */
    private String appendStockEncoding(MaterialInStock entity) {
        String area = entity.getStorageArea();//入库地区
        String projectGroup = entity.getProjectGroup();//项目组
        String materialType = entity.getMaterialType();//物资类型
        String materialName = entity.getMaterialName();//物资名称
        StringBuilder sb = new StringBuilder();
        sb.append(area).append("-").append(projectGroup).append("-").append(materialType).append("-")
                .append(materialName).append("-").append(LocalDateTime.now());
        return sb.toString();
    }

    /**
     * 根据id删除物资入库
     *
     * @param id 物资入库唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新物资入库
     *
     * @param to 物资入库to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialInStockTO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialInStock model = super.findById(to.getId());
            if (model != null) {
                updateMaterialInStock(to, model);
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
     * @param to    物资入库to
     * @param model 物资入库
     * @throws SerException
     */
    private void updateMaterialInStock(MaterialInStockTO to, MaterialInStock model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 查询存储地区,项目组,物品类型,物资名称所有不同的类型
     *
     * @return
     * @throws SerException
     */
    @Override
    public List<AttributeBO> findAllKindsType() throws SerException {
        List<MaterialInStock> list = super.findAll();
        Set<MaterialInStock> set = new HashSet<>(0);
        set.addAll(list);//这里会自动去除重复的对象
        return getAttributeBos(set);
    }

    /**
     * 获取非重复对象的属性
     *
     * @param set 去除重复的对象
     * @return
     */
    private List<AttributeBO> getAttributeBos(Set<MaterialInStock> set) {
        if (CollectionUtils.isEmpty(set)) {
            return Collections.emptyList();
        }
        List<MaterialInStock> list = new ArrayList<>(set);
        List<AttributeBO> boList = BeanTransform.copyProperties(list, AttributeBO.class);
        return boList;
    }

    /**
     * 根据属性查找物资入库
     *
     * @param bo 属性bo
     * @return class MaterialInStock
     * @throws SerException
     */
    @Override
    public List<MaterialInStockBO> findByAttribute(AttributeBO bo) throws SerException {
        MaterialInStockDTO dto = new MaterialInStockDTO();
        dto.getConditions().add(Restrict.eq("storageArea", bo.getStorageArea())); //存储地区
        dto.getConditions().add(Restrict.eq("projectGroup", bo.getProjectGroup()));//项目组
        dto.getConditions().add(Restrict.eq("materialType", bo.getMaterialType())); //物资类型
        dto.getConditions().add(Restrict.eq("materialName", bo.getMaterialName()));//物资名称
        List<MaterialInStock> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, MaterialInStockBO.class);
    }

    @Override
    //cjh
    public Set<String> allstockEncoding() throws SerException {
        Set<String> set = new HashSet<String>();
        List<MaterialInStock> list = super.findAll();
        for (MaterialInStock m : list) {
            set.add(m.getStockEncoding());
        }
        return set;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void updateLijuntao(MaterialInStockTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialInStock model = super.findById(to.getId());
            if (model != null) {
                updateMaterialInStock(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    public List<String> findAddAllDetails() throws SerException {
        List<DepartmentDetailBO> departmentDetailBOS = departmentDetailAPI.findStatus();
        if (org.apache.commons.collections4.CollectionUtils.isEmpty(departmentDetailBOS)) {
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
    public List<String> findallUser() throws SerException {
        List<UserBO> userBOS = positionDetailUserAPI.findUserList();
        if (org.apache.commons.collections4.CollectionUtils.isEmpty(userBOS)) {
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