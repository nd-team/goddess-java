package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.bo.RecommendSchemeBO;
import com.bjike.goddess.interiorrecommend.dto.RecommendSchemeDTO;
import com.bjike.goddess.interiorrecommend.entity.RecommendScheme;
import com.bjike.goddess.interiorrecommend.enums.GuideAddrStatus;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.RecommendSchemeTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 推荐方案业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 10:31 ]
 * @Description: [ 推荐方案业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "interiorrecommendSerCache")
@Service
public class RecommendSchemeSerImpl extends ServiceImpl<RecommendScheme, RecommendSchemeDTO> implements RecommendSchemeSer {

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private UserDetailAPI userDetailAPI;

    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

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
     * 核对是否有审核权限（部门级别）
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
     * 核对添加修改删除权限（岗位级别）
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

    /**
     * 综合资源部负责人审核权限
     */
    private Boolean guideSynthesize() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 运营商务部负责人审核权限
     */
    private Boolean guideOperation() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 总经办审核权限
     */
    private Boolean guideManager() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("5");
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
            case SYNTHESIZEAUDIT:
                flag = guideSynthesize();
                break;
            case MANAGEADUIT:
                flag = guideManager();
                break;
            case OPERATIONAUDIT:
                flag = guideOperation();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
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
    public RecommendSchemeBO insertModel(RecommendSchemeTO to) throws SerException {
        RecommendScheme model = BeanTransform.copyProperties(to, RecommendScheme.class, true);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, RecommendSchemeBO.class);
    }

    @Override
    public RecommendSchemeBO updateModel(RecommendSchemeTO to) throws SerException {
        setUpdate(to);
        return BeanTransform.copyProperties(to, RecommendSchemeBO.class);
    }

    @Override
    public List<RecommendSchemeBO> pageList(RecommendSchemeDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<RecommendScheme> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, RecommendSchemeBO.class);
    }

    @Override
    public void resourcesAudit(String id, String resourcesSuggest, Boolean resourcesAudit) throws SerException {
        //判断是否为综合部负责人
        if (guideSynthesize() == true) {
            String userBO = RpcTransmit.getUserToken();
            String userId = userAPI.currentUser().getId();
            RecommendScheme model = super.findById(id);
            if (model != null) {
                //todo 尚未明确组织结构,需要判断当前用户是否为综合资源部负责人
                model.setResourcesSuggest(resourcesSuggest);
                model.setResourcesAudit(resourcesAudit);
                super.update(model);
            } else {
                throw new SerException("非法Id,推荐方案对象不能为空!");
            }
        } else {
            throw new SerException("你没有审核权限");
        }
    }

    @Override
    public void operateAudit(String id, String operateSuggest, Boolean operateAudit) throws SerException {
        //判断是否为运营部负责人
        if (guideOperation() == true) {
            String userBO = RpcTransmit.getUserToken();
            String userId = userAPI.currentUser().getId();
                RecommendScheme model = super.findById(id);
                if (model != null) {
                    //todo 尚未明确组织结构，需要判断当前用户是否为运营商务部负责人zzzzzz
                    model.setOperateSuggest(operateSuggest);
                    model.setOperateAudit(operateAudit);
                    super.update(model);
                } else {
                    throw new SerException("非法Id,推荐方案对象不能为空!");
                }
            } else {
                throw new SerException("你没有审核权限");
            }
    }

    @Override
    public void generalAudit(String id, String generalSuggest, Boolean generalAudit) throws SerException {
        //判断为是否为总经办
        if (guideManager() == true) {
            String userBO = RpcTransmit.getUserToken();
            String userId = userAPI.currentUser().getId();
                RecommendScheme model = super.findById(id);
                if (model != null) {
                    //todo 尚未明确组织结构，需要判断当前用户是否为总经办
                    model.setGeneralSuggest(generalSuggest);
                    model.setGeneralAudit(generalAudit);
                    super.update(model);
                } else {
                    throw new SerException("非法Id,推荐方案对象不能为空!");
                }
            } else {
                throw new SerException("你没有审核权限");
            }
    }


    /**
     * 更新数据（编辑、审核）
     *
     * @param to 推荐方案
     */
    public void setUpdate(RecommendSchemeTO to) throws SerException {

        if (!StringUtils.isEmpty(to.getId())) {
            RecommendScheme model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }
}