package com.bjike.goddess.materialinstock.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialinstock.api.MaterialInStockAPI;
import com.bjike.goddess.materialinstock.bo.StockWarningBO;
import com.bjike.goddess.materialinstock.dto.StockWarningDTO;
import com.bjike.goddess.materialinstock.entity.StockWarning;
import com.bjike.goddess.materialinstock.excel.SonPermissionObject;
import com.bjike.goddess.materialinstock.to.GuidePermissionTO;
import com.bjike.goddess.materialinstock.to.StockWarningTO;
import com.bjike.goddess.materialinstock.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 库存预警业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 05:00 ]
 * @Description: [ 库存预警业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialinstockSerCache")
@Service
public class StockWarningSerImpl extends ServiceImpl<StockWarning, StockWarningDTO> implements StockWarningSer {



    @Autowired
    private UserAPI userAPI;

    @Autowired
    private MaterialInStockSer materialInStockSer;

    @Autowired
    private CusPermissionSer cusPermissionSer;

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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagStockWarning = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("stockwarning");
        obj.setDescribesion("库存预警");
        if (flagStockWarning) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagMaterInSt = materialInStockSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("materialAnalyzeannual");
        obj.setDescribesion("库存入库");
        if (flagMaterInSt) {
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
     * 分页查询库存预警
     *
     * @return class StockWarningBO
     * @throws SerException
     */
    @Override
    public List<StockWarningBO> list(StockWarningDTO dto) throws SerException {
        checkPermission();
        List<StockWarning> list = super.findByPage(dto);
        List<StockWarningBO> listBO = BeanTransform.copyProperties(list, StockWarningBO.class);
        return listBO;
    }

    /**
     * 保存库存预警
     *
     * @param to 库存预警to
     * @return class StockWarningBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public StockWarningBO save(StockWarningTO to) throws SerException {
        checkPermission();
        StockWarning entity = BeanTransform.copyProperties(to, StockWarning.class, true);
        entity = super.save(entity);
        StockWarningBO bo = BeanTransform.copyProperties(entity, StockWarningBO.class);
        return bo;
    }

    /**
     * 根据id删除库存预警
     *
     * @param id 库存预警唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }

    /**
     * 更新库存预警
     *
     * @param to 库存预警to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(StockWarningTO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())){
            StockWarning model = super.findById(to.getId());
            if (model != null) {
                updateStockWarning(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新库存预警
     *
     * @param to 库存预警to
     * @param model 库存预警
     * @throws SerException
     */
    private void updateStockWarning(StockWarningTO to, StockWarning model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

}