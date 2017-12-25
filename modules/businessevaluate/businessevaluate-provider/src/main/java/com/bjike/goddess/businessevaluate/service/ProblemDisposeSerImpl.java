package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.ProblemDisposeBO;
import com.bjike.goddess.businessevaluate.dto.ProblemDisposeDTO;
import com.bjike.goddess.businessevaluate.entity.EvaluateProjectInfo;
import com.bjike.goddess.businessevaluate.entity.ProblemDispose;
import com.bjike.goddess.businessevaluate.enums.GuideAddrStatus;
import com.bjike.goddess.businessevaluate.to.GuidePermissionTO;
import com.bjike.goddess.businessevaluate.to.ProblemDisposeTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目问题受理和处理业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 02:11 ]
 * @Description: [ 项目问题受理和处理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessevaluateSerCache")
@Service
public class ProblemDisposeSerImpl extends ServiceImpl<ProblemDispose, ProblemDisposeDTO> implements ProblemDisposeSer {

    @Autowired
    private EvaluateProjectInfoSer evaluateProjectInfoSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProblemDisposeBO insertModel(ProblemDisposeTO to) throws SerException {

        ProblemDispose model = BeanTransform.copyProperties(to, ProblemDispose.class, true);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, ProblemDisposeBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProblemDisposeBO updateModel(ProblemDisposeTO to) throws SerException {

        updateModelInfo(to);
        return BeanTransform.copyProperties(to, ProblemDisposeBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProblemDisposeBO> pageList(ProblemDisposeDTO dto) throws SerException {

        dto.getSorts().add("createTime=desc");
        List<ProblemDispose> list = super.findByPage(dto);
        List<ProblemDisposeBO> boList = BeanTransform.copyProperties(list, ProblemDisposeBO.class);
        //设置项目信息
        if (boList != null && !boList.isEmpty()) {
            for (ProblemDisposeBO bo : boList) {
                EvaluateProjectInfo info = evaluateProjectInfoSer.findById(bo.getProjectInfoId());
                if (info != null) {
                    bo.setArea(info.getArea());
                    bo.setProject(info.getProject());
                    bo.setStartTime(info.getStartTime().toString());
                    bo.setEndTime(info.getEndTime().toString());
                }
            }
        }
        return boList;
    }

    @Override
    public Boolean sonPermission() throws SerException {
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
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = to.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = sonPermission();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case QUALITATIVE:
                flag = guideAddIdentity();
                break;
            case QUANTIFY:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（部门级别）
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

    /**
     * 更新数据（编辑、审核）
     *
     * @param to 问题处理
     */
    public void updateModelInfo(ProblemDisposeTO to) throws SerException {

        if (!StringUtils.isEmpty(to.getId())) {
            ProblemDispose model = super.findById(to.getId());
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