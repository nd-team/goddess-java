package com.bjike.goddess.businessevaluate.service.interiorevaluate;

import com.bjike.goddess.businessevaluate.bo.interiorevaluate.ProjectPrincipalEvaluateBO;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.ProjectPrincipalEvaluateDTO;
import com.bjike.goddess.businessevaluate.entity.EvaluateProjectInfo;
import com.bjike.goddess.businessevaluate.entity.interiorevaluate.ProjectPrincipalEvaluate;
import com.bjike.goddess.businessevaluate.service.EvaluateProjectInfoSer;
import com.bjike.goddess.businessevaluate.to.interiorevaluate.ProjectPrincipalEvaluateTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商务负责人评价业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 01:55 ]
 * @Description: [ 商务负责人评价业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessevaluateSerCache")
@Service
public class ProjectPrincipalEvaluateSerImpl extends ServiceImpl<ProjectPrincipalEvaluate, ProjectPrincipalEvaluateDTO> implements ProjectPrincipalEvaluateSer {

    @Autowired
    private EvaluateProjectInfoSer evaluateProjectInfoSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectPrincipalEvaluateBO insertModel(ProjectPrincipalEvaluateTO to) throws SerException {
        ProjectPrincipalEvaluate model = BeanTransform.copyProperties(to, ProjectPrincipalEvaluate.class);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, ProjectPrincipalEvaluateBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectPrincipalEvaluateBO editModel(ProjectPrincipalEvaluateTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            ProjectPrincipalEvaluate model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(to, ProjectPrincipalEvaluateBO.class);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectPrincipalEvaluateBO> pageList(ProjectPrincipalEvaluateDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<ProjectPrincipalEvaluate> list = super.findByPage(dto);
        List<ProjectPrincipalEvaluateBO> boList = BeanTransform.copyProperties(list, ProjectPrincipalEvaluateBO.class);
        //设置项目信息
        if (boList != null && !boList.isEmpty()) {
            for (ProjectPrincipalEvaluateBO bo : boList) {
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
}