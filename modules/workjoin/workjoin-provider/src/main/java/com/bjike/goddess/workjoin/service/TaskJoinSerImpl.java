package com.bjike.goddess.workjoin.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionInstructionAPI;
import com.bjike.goddess.organize.bo.PositionInstructionBO;
import com.bjike.goddess.organize.dto.PositionInstructionDTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.workjoin.bo.TaskJoinBO;
import com.bjike.goddess.workjoin.dto.TaskJoinDTO;
import com.bjike.goddess.workjoin.entity.TaskJoin;
import com.bjike.goddess.workjoin.enums.GuideAddrStatus;
import com.bjike.goddess.workjoin.to.GuidePermissionTO;
import com.bjike.goddess.workjoin.to.TaskJoinTO;
import net.sf.ehcache.store.compound.LegacyCopyStrategyAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 任务交接业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:55 ]
 * @Description: [ 任务交接业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "workjoinSerCache")
@Service
public class TaskJoinSerImpl extends ServiceImpl<TaskJoin, TaskJoinDTO> implements TaskJoinSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private PositionInstructionAPI positionInstructionAPI;

    @Autowired
    private ModuleAPI moduleAPI;

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
        return flag;
    }
    @Override
    public Long countTaskJoin(TaskJoinDTO taskJoinDTO) throws SerException {
        Long count = super.count(taskJoinDTO);
        return count;
    }

    @Override
    public TaskJoinBO getOne(String id) throws SerException {
        TaskJoin taskJoin = super.findById(id);
        return BeanTransform.copyProperties(taskJoin,TaskJoinBO.class);
    }

    @Override
    public List<TaskJoinBO> findListTaskJoin(TaskJoinDTO taskJoinDTO) throws SerException {
        checkSeeIdentity();
        taskJoinDTO.getSorts().add("createTime=desc");
        List<TaskJoin> taskJoins = super.findByPage(taskJoinDTO);
        List<TaskJoinBO> taskJoinBOS = BeanTransform.copyProperties(taskJoins,TaskJoinBO.class);
        return taskJoinBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public TaskJoinBO insertTaskJoin(TaskJoinTO taskJoinTO) throws SerException {
        checkAddIdentity();
        TaskJoin taskJoin = BeanTransform.copyProperties(taskJoinTO,TaskJoin.class,true);
        taskJoin.setCreateTime(LocalDateTime.now());
        super.save(taskJoin);
        return BeanTransform.copyProperties(taskJoin,TaskJoinBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public TaskJoinBO editTaskJoin(TaskJoinTO taskJoinTO) throws SerException {
        checkAddIdentity();
        TaskJoin taskJoin = super.findById(taskJoinTO.getId());
        LocalDateTime creatTime = taskJoin.getCreateTime();
        taskJoin = BeanTransform.copyProperties(taskJoinTO,TaskJoin.class,true);
        taskJoin.setCreateTime(creatTime);
        taskJoin.setModifyTime(LocalDateTime.now());
        super.update(taskJoin);
        return BeanTransform.copyProperties(taskJoinTO,TaskJoinBO.class,true);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeTaskJoin(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public List<PositionInstructionBO> findPosition() throws SerException {
        List<PositionInstructionBO> boList = new ArrayList<>(0);
        if(moduleAPI.isCheck("organize")) {
            String userToken = RpcTransmit.getUserToken();
            RpcTransmit.transmitUserToken(userToken);
            PositionInstructionDTO dto = new PositionInstructionDTO();
            boList = positionInstructionAPI.findPage(dto);
        }
        return boList;
    }
}