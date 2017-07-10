package com.bjike.goddess.progressmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.progressmanage.bo.ProgressTableBO;
import com.bjike.goddess.progressmanage.bo.ProjectInfoBO;
import com.bjike.goddess.progressmanage.bo.TableListForHeadBO;
import com.bjike.goddess.progressmanage.dto.ProgressTableDTO;
import com.bjike.goddess.progressmanage.entity.ProgressTable;
import com.bjike.goddess.progressmanage.entity.ProjectInfo;
import com.bjike.goddess.progressmanage.to.ProgressTableTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 进度表业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:46 ]
 * @Description: [ 进度表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "progressmanageSerCache")
@Service
public class ProgressTableSerImpl extends ServiceImpl<ProgressTable, ProgressTableDTO> implements ProgressTableSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ProjectInfoSer projectInfoSer;

    @Override
    @Transactional(rollbackFor = SerException.class)

    public ProgressTableBO insertModel(ProgressTableTO to) throws SerException {

        UserBO userBO = userAPI.currentUser();
        ProjectInfo projectInfo = projectInfoSer.findById(to.getProjectId());
        if (projectInfo != null) {
            if (isExist(to) == null) {
                ProgressTable model = BeanTransform.copyProperties(to, ProgressTable.class, true);
                model.setCreateUser(userBO.getUsername());
                model.setProject(projectInfo);
                super.save(model);
                return BeanTransform.copyProperties(model, ProgressTableBO.class);
            } else {
                throw new SerException("该项目已经存在该进度表,请检查数据!");
            }
        } else {
            throw new SerException("非法项目Id,项目对象不能为空!");
        }
    }

    public ProgressTable isExist(ProgressTableTO to) throws SerException {

        ProgressTableDTO dto = new ProgressTableDTO();
        dto.getConditions().add(Restrict.eq("tabName", to.getTabName()));
        dto.getConditions().add(Restrict.eq("project.id", to.getProjectId()));
        dto.setLimit(1);
        return super.findOne(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProgressTableBO updateModel(ProgressTableTO to) throws SerException {
        UserBO userBO = userAPI.currentUser();
        ProjectInfo projectInfo = projectInfoSer.findById(to.getProjectId());
        if (projectInfo != null) {
            ProgressTable model = super.findById(to.getId());
            if (model != null) {
                if (isExist(to) == null || (isExist(to) != null && to.getId().equals(model.getId()))) {
                    BeanTransform.copyProperties(to, model, true);
                    model.setModifyTime(LocalDateTime.now());
                    model.setUpdateUser(userBO.getUsername());
                    model.setProject(projectInfo);
                    super.update(model);
                    return BeanTransform.copyProperties(model, ProgressTableBO.class);
                } else {
                    throw new SerException("该项目已经存在该进度表,请检查数据!");
                }
            } else {
                throw new SerException("非法Id,编辑对象不存在!");
            }
        } else {
            throw new SerException("非法项目Id,项目对象不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        ProgressTable model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.DELETE) {
                model.setStatus(Status.DELETE);
                super.save(model);
            } else {
                //由于考虑数据完整性,删除状态数据并不删除,如有需要删除可制作定时器一段时间后删除对应状态及关联的数据
                throw new SerException("非法Id,删除对象不存在!");
            }

        } else {
            throw new SerException("非法Id,删除对象不存在!");
        }
    }

    @Override
    public List<TableListForHeadBO> tables(String projectId) throws SerException {

        ProgressTableDTO dto = new ProgressTableDTO();
        dto.getConditions().add(Restrict.ne("status", Status.DELETE));
        dto.getConditions().add(Restrict.ne("status", Status.CONGEAL));
        dto.getConditions().add(Restrict.eq("project.id", projectId));
        dto.getSorts().add("createTime=desc");

        return BeanTransform.copyProperties(super.findByPage(dto), TableListForHeadBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {
        ProgressTable model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.CONGEAL) {
                model.setStatus(Status.CONGEAL);
                super.update(model);
            } else {
                throw new SerException("已经冻结,无需重复操作!");
            }

        } else {
            throw new SerException("非法Id,冻结对象不存在!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void unfreeze(String id) throws SerException {
        ProgressTable model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.THAW) {
                model.setStatus(Status.THAW);
                super.update(model);
            } else {
                throw new SerException("已经解冻,无需重复操作!");
            }

        } else {
            throw new SerException("非法Id,删除对象不存在!");
        }
    }

    @Override
    public List<ProgressTableBO> pageList(ProgressTableDTO dto) throws SerException {
        dto.getConditions().add(Restrict.ne("status", Status.DELETE));
        dto.getConditions().add(Restrict.eq("project.id", dto.getProjectId()));
        dto.getSorts().add("createTime=desc");

        List<ProgressTable> list = super.findByPage(dto);
        List<ProgressTableBO> boList = null;
        if (!CollectionUtils.isEmpty(list)) {
            boList = new ArrayList<ProgressTableBO>();
            for (ProgressTable bo : list) {
                ProgressTableBO vo = BeanTransform.copyProperties(bo, ProgressTableBO.class);
                vo.setProjectInfoBO(BeanTransform.copyProperties(bo.getProject(), ProjectInfoBO.class));
                boList.add(vo);
            }
        }
        return boList;
    }
}