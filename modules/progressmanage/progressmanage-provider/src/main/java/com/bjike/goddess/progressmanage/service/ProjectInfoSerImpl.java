package com.bjike.goddess.progressmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.progressmanage.bo.ProjectInfoBO;
import com.bjike.goddess.progressmanage.bo.ProjectListForNodeBO;
import com.bjike.goddess.progressmanage.dto.ProjectInfoDTO;
import com.bjike.goddess.progressmanage.entity.ProgressTable;
import com.bjike.goddess.progressmanage.entity.ProjectInfo;
import com.bjike.goddess.progressmanage.to.ProjectInfoTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:41 ]
 * @Description: [ 项目信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "progressmanageSerCache")
@Service
public class ProjectInfoSerImpl extends ServiceImpl<ProjectInfo, ProjectInfoDTO> implements ProjectInfoSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ProgressTableSer progressTableSer;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectInfoBO insertModel(ProjectInfoTO to) throws SerException {

        if (isExistOutProject(to) != null) {
            throw new SerException("该合同外部项目名称已存在，请检查数据!");
        }
        if (isExistInProject(to) != null) {
            throw new SerException("该内部项目名称已存在，请检查数据!");
        }


        UserBO userBO = userAPI.currentUser();
        ProjectInfo model = BeanTransform.copyProperties(to, ProjectInfo.class, true);
        model.setCreateUser(userBO.getEmployeeNumber());
        super.save(model);

        ProgressTable progressTable = new ProgressTable();
        progressTable.setCreateUser(userBO.getEmployeeNumber());
        progressTable.setTabName(to.getTableName());
        progressTable.setProject(model);
        progressTableSer.save(progressTable);

        return BeanTransform.copyProperties(model, ProjectInfoBO.class);
    }

    public ProjectInfo isExistOutProject(ProjectInfoTO to) throws SerException {
        StringBuilder sql = new StringBuilder("select id from progressmanage_projectinfo where 0 = 0");

        sql.append(" and outProject ='" + to.getOutProject() + "'");

        List<ProjectInfo> list = super.findBySql(sql.toString(), ProjectInfo.class, new String[]{"id"});

        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public ProjectInfo isExistInProject(ProjectInfoTO to) throws SerException {
        StringBuilder sql = new StringBuilder("select id from progressmanage_projectinfo where 0 = 0");

        sql.append(" and inProject ='" + to.getInProject() + "'");

        List<ProjectInfo> list = super.findBySql(sql.toString(), ProjectInfo.class, new String[]{"id"});

        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectInfoBO updateModel(ProjectInfoTO to) throws SerException {
        ProjectInfo model = super.findById(to.getId());
        if (model != null) {

            ProjectInfo judgeOut = isExistOutProject(to);
            ProjectInfo judegeIn = isExistInProject(to);

            if (judgeOut == null || (judgeOut != null && to.getId().equals(judgeOut.getId()))) {

            } else {
                throw new SerException("该合同外部项目名称已存在，请检查数据!");
            }
            if (judegeIn == null || (judegeIn != null && to.getId().equals(judegeIn.getId()))) {

            } else {
                throw new SerException("该内部项目名称已存在，请检查数据!");
            }

            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);

            ProgressTable progressTable = model.getProgressTable();
            if (!to.getTableName().equals(progressTable.getTabName())) {
                progressTable.setTabName(to.getTableName());
                progressTableSer.update(progressTable);
            }

            return BeanTransform.copyProperties(model, ProjectInfoBO.class);
        } else {
            throw new SerException("非法Id,编辑对象不存在!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        ProjectInfo model = super.findById(id);
        if (model != null) {
            model.setStatus(Status.DELETE);
            super.save(model);
        } else {
            throw new SerException("非法Id,删除对象不存在!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectInfoBO> pageList(ProjectInfoDTO dto) throws SerException {
        dto.getConditions().add(Restrict.ne("status", Status.DELETE));
        dto.getSorts().add("createTime=desc");
        List<ProjectInfoBO> boList = BeanTransform.copyProperties(super.findByPage(dto), ProjectInfoBO.class);

        for (ProjectInfoBO bo : boList) {
            DepartmentDetailBO detailBO = departmentDetailAPI.getById(bo.getGroupId());
            if (detailBO != null) {
                bo.setGroup(detailBO.getDepartment());
            }
        }
        return boList;
    }

    @Override
    public List<ProjectListForNodeBO> projects() throws SerException {
        ProjectInfoDTO dto = new ProjectInfoDTO();
        dto.getConditions().add(Restrict.ne("status", Status.DELETE));
        dto.getConditions().add(Restrict.ne("status", Status.CONGEAL));
        dto.getSorts().add("createTime=desc");
        dto.getSorts().add("area=desc");

        List<ProjectInfo> list = super.findByCis(dto);

        List<ProjectListForNodeBO> boList = BeanTransform.copyProperties(list, ProjectListForNodeBO.class);

        for (ProjectListForNodeBO bo : boList) {
            DepartmentDetailBO detailBO = departmentDetailAPI.getById(bo.getGroupId());
            if (detailBO != null) {
                bo.setGroup(detailBO.getDepartment());
            }
        }
        return boList;
    }
}