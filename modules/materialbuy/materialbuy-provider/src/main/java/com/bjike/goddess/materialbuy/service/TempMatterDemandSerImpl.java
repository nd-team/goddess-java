package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialbuy.bo.TempMatterDemandBO;
import com.bjike.goddess.materialbuy.dto.TempMatterDemandDTO;
import com.bjike.goddess.materialbuy.entity.TempMatterDemand;
import com.bjike.goddess.materialbuy.enums.GuideAddrStatus;
import com.bjike.goddess.materialbuy.to.GuidePermissionTO;
import com.bjike.goddess.materialbuy.to.TempMatterDemandTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 临时物资需求业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:08 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialbuySerCache")
@Service
public class TempMatterDemandSerImpl extends ServiceImpl<TempMatterDemand, TempMatterDemandDTO> implements TempMatterDemandSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
                flag = guideSeeIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public List<TempMatterDemandBO> list(TempMatterDemandDTO dto) throws SerException {
        return null;
    }

    /**
     * 分页查询临时物资需求
     *
     * @return class TempMatterDemandBO
     * @throws SerException
     */
//    @Override
//    public List<TempMatterDemandBO> list(TempMatterDemandDTO dto) throws SerException {
//        checkSeeIdentity();
//        List<TempMatterDemand> list = super.findByPage(dto);
//        List<TempMatterDemandBO> listBO = BeanTransform.copyProperties(list, TempMatterDemandBO.class);
//        if (listBO != null) {
//            Comparator<TempMatterDemandBO> comparator = new Comparator<TempMatterDemandBO>() {
//                @Override
//                public int compare(TempMatterDemandBO o1, TempMatterDemandBO o2) {
//                    return o2.getCreateTime().compareTo(o1.getCreateTime());
//                }
//            };
//            listBO = Ordering.from(comparator).sortedCopy(listBO);
//        }
//        return listBO;
//    }

    /**
     * 保存临时物资需求
     *
     * @param to 临时物资需求to
     * @return class TempMatterDemandBO
     * @throws SerException
     */
    @Override
    public TempMatterDemandBO save(TempMatterDemandTO to) throws SerException {
        checkAddIdentity();
        TempMatterDemand entity = BeanTransform.copyProperties(to, TempMatterDemand.class, true);
        entity = super.save(entity);
        TempMatterDemandBO bo = BeanTransform.copyProperties(entity, TempMatterDemandBO.class);
        return bo;
    }

    /**
     * 根据id删除临时物资需求
     *
     * @param id 临时物资需求唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    /**
     * 更新临时物资需求
     *
     * @param to 临时物资需求to
     * @throws SerException
     */
    @Override
    public void update(TempMatterDemandTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotEmpty(to.getId())) {
            TempMatterDemand model = super.findById(to.getId());
            if (model != null) {
                updateTempMatterDemand(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新临时物资需求
     *
     * @param to    临时物资需求to
     * @param model 临时物资需求
     * @throws SerException
     */
    private void updateTempMatterDemand(TempMatterDemandTO to, TempMatterDemand model) throws SerException {
        BeanUtils.copyProperties(to, model);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 审核
     *
     * @param to 临时物资需求to
     * @throws SerException
     */
    @Override
    public void audit(TempMatterDemandTO to) throws SerException {
        update(to);
    }

    /**
     * 查看详情
     *
     * @param id 临时物资需求唯一标识
     * @return class TempMatterDemandBO
     * @throws SerException
     */
    @Override
    public TempMatterDemandBO checkDetail(String id) throws SerException {
        TempMatterDemand model = super.findById(id);
        return BeanTransform.copyProperties(model, TempMatterDemandBO.class);
    }

    @Override
    public Long count(TempMatterDemandDTO dto) throws SerException {
        return super.count(dto);
    }
}