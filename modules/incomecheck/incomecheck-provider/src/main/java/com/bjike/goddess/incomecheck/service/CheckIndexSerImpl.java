package com.bjike.goddess.incomecheck.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.incomecheck.api.CheckIndexAPI;
import com.bjike.goddess.incomecheck.bo.CheckIndexBO;
import com.bjike.goddess.incomecheck.dto.CheckIndexDTO;
import com.bjike.goddess.incomecheck.entity.CheckIndex;
import com.bjike.goddess.incomecheck.enums.GuideAddrStatus;
import com.bjike.goddess.incomecheck.to.CheckIndexTO;
import com.bjike.goddess.incomecheck.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 指标设置业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-29 09:49 ]
 * @Description: [ 指标设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "incomecheckSerCache")
@Service
public class CheckIndexSerImpl extends ServiceImpl<CheckIndex, CheckIndexDTO> implements CheckIndexSer {

    @Autowired
    private CheckIndexAPI checkIndexAPI;
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
    public Long countCheckIndex(CheckIndexDTO checkIndexDTO) throws SerException {
        Long count = super.count(checkIndexDTO);
        return count;
    }

    @Override
    public CheckIndexBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        CheckIndex checkIndex = super.findById(id);
        return BeanTransform.copyProperties(checkIndex, CheckIndexBO.class);
    }

    @Override
    public List<CheckIndexBO> listCheckIndex(CheckIndexDTO checkIndexDTO) throws SerException {
        checkSeeIdentity();
        checkIndexDTO.getSorts().add("createTime=desc");
        List<CheckIndex> list = super.findByCis(checkIndexDTO, true);

        return BeanTransform.copyProperties(list, CheckIndexBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CheckIndexBO addCheckIndex(CheckIndexTO checkIndexTO) throws SerException {
        checkAddIdentity();
        if (checkIndexTO.getIncomeRate() == null) {
            throw new SerException("收入比例不能为空");
        }
        if (checkIndexTO.getCompleteRate() == null) {
            throw new SerException("完工比例不能为空");
        }
        CheckIndexDTO dto = new CheckIndexDTO();
        CheckIndex ci = super.findOne(dto);
        if (ci != null && StringUtils.isNotBlank(ci.getId())) {
            throw new SerException("已经存在指标，不能再添加");
        }
        CheckIndex checkIndex = BeanTransform.copyProperties(checkIndexTO, CheckIndex.class, true);

        checkIndex.setCreateTime(LocalDateTime.now());
        super.save(checkIndex);
        return BeanTransform.copyProperties(checkIndex, CheckIndexBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CheckIndexBO editCheckIndex(CheckIndexTO checkIndexTO) throws SerException {
        checkAddIdentity();
        if (checkIndexTO.getIncomeRate() == null) {
            throw new SerException("收入比例不能为空");
        }
        if (checkIndexTO.getCompleteRate() == null) {
            throw new SerException("完工比例不能为空");
        }

        CheckIndex checkIndex = BeanTransform.copyProperties(checkIndexTO, CheckIndex.class, true);

        CheckIndex temp = super.findById(checkIndexTO.getId());

        BeanUtils.copyProperties(checkIndex, temp, "id", "createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
        return BeanTransform.copyProperties(temp, CheckIndexBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCheckIndex(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }


}