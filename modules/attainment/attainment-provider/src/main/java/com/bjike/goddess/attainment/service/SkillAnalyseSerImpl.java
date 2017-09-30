package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.AttainmentTypeBO;
import com.bjike.goddess.attainment.bo.SkillAnalyseBO;
import com.bjike.goddess.attainment.dto.SkillAnalyseDTO;
import com.bjike.goddess.attainment.entity.SkillAnalyse;
import com.bjike.goddess.attainment.enums.GuideAddrStatus;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.attainment.to.SkillAnalyseTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
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
 * 技能分析表业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:45 ]
 * @Description: [ 技能分析表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class SkillAnalyseSerImpl extends ServiceImpl<SkillAnalyse, SkillAnalyseDTO> implements SkillAnalyseSer {



    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SkillAnalyseBO save(SkillAnalyseTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        SkillAnalyse entity = BeanTransform.copyProperties(to, SkillAnalyse.class, true);
        entity.setWriter(user.getUsername());
        entity.setWriterTime(LocalDateTime.now());
        super.save(entity);
        return BeanTransform.copyProperties(entity, SkillAnalyseBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SkillAnalyseBO update(SkillAnalyseTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                UserBO user = userAPI.currentUser();
                SkillAnalyse entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                if (!user.getUsername().equals(entity.getWriter())) {
                    entity.setWriter(user.getUsername());
                    entity.setWriterTime(LocalDateTime.now());
                }
                entity.setRemark(to.getRemark());
                super.update(entity);
                return BeanTransform.copyProperties(entity, SkillAnalyseBO.class);
            } catch (SerException e) {
                throw new SerException("数据对象不能为空");
            }

        } else
            throw new SerException("数据ID不能为空");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SkillAnalyseBO delete(String id) throws SerException {
        SkillAnalyse entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, SkillAnalyseBO.class);
    }

    @Override
    public List<SkillAnalyseBO> maps(SkillAnalyseDTO dto) throws SerException {
        dto.getSorts().add("writerTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), SkillAnalyseBO.class);
    }

    @Override
    public SkillAnalyseBO getById(String id) throws SerException {
        SkillAnalyse entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return BeanTransform.copyProperties(entity, SkillAnalyseBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        SkillAnalyseDTO dto = new SkillAnalyseDTO();
        return super.count(dto);
    }

    @Override
    public List<String> getUser() throws SerException {
        List<UserBO> userBOs = positionDetailUserAPI.findUserListInOrgan();
        List<String> list = new ArrayList<>();
        if(null != userBOs && userBOs.size() > 0 ){
            for(UserBO bo : userBOs){
                list.add(bo.getUsername());
            }
        }
        return list;
    }

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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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
}