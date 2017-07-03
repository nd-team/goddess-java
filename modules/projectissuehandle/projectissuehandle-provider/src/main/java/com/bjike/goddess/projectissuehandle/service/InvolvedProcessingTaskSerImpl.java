package com.bjike.goddess.projectissuehandle.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.projectissuehandle.bo.InvolvedProcessingTaskBO;
import com.bjike.goddess.projectissuehandle.dto.InvolvedProcessingTaskDTO;
import com.bjike.goddess.projectissuehandle.entity.InvolvedProcessingTask;
import com.bjike.goddess.projectissuehandle.enums.GuideAddrStatus;
import com.bjike.goddess.projectissuehandle.excel.InvolvedProcessingTaskExport;
import com.bjike.goddess.projectissuehandle.to.GuidePermissionTO;
import com.bjike.goddess.projectissuehandle.to.InvolvedProcessingTaskTO;
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
import java.util.stream.Collectors;

/**
 * 参与处理人员的任务分配业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 05:02 ]
 * @Description: [ 参与处理人员的任务分配业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectissuehandleSerCache")
@Service
public class InvolvedProcessingTaskSerImpl extends ServiceImpl<InvolvedProcessingTask, InvolvedProcessingTaskDTO> implements InvolvedProcessingTaskSer {
    @Autowired
    private ProPermissionSer proPermissionSer;
    @Autowired
    private UserAPI userAPI;

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
            flag = proPermissionSer.getProPermission("1");
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
            flag = proPermissionSer.busProPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }
    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException{
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser( );
        RpcTransmit.transmitUserToken( userToken );
        String userName = userBO.getUsername();
        if( !"admin".equals( userName.toLowerCase())){
            flag = proPermissionSer.getProPermission("1");
        }else{
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException{
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser( );
        RpcTransmit.transmitUserToken( userToken );
        String userName = userBO.getUsername();
        if( !"admin".equals( userName.toLowerCase())){
            flag = proPermissionSer.busProPermission("2");
        }else{
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken( userToken );
        Boolean flagAdd = guideAddIdentity();
        if( flagSee || flagAdd ){
            return true;
        }else{
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
    public Long countInvolvedProcessingTask(InvolvedProcessingTaskDTO involvedProcessingTaskDTO) throws SerException {
        Long counts = super.count(involvedProcessingTaskDTO);
        return counts;
    }
    @Override
    public InvolvedProcessingTaskBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        InvolvedProcessingTask involvedProcessingTask = super.findById(id);
        return BeanTransform.copyProperties(involvedProcessingTask,InvolvedProcessingTaskBO.class);
    }

    @Override
    public List<InvolvedProcessingTaskBO> findListInvolvedProcessingTask(InvolvedProcessingTaskDTO involvedProcessingTaskDTO) throws SerException {
        checkSeeIdentity();
        involvedProcessingTaskDTO.getSorts().add("createTime=desc");
        List<InvolvedProcessingTask> involvedProcessingTasks = super.findByCis(involvedProcessingTaskDTO, true);
        List<InvolvedProcessingTaskBO> involvedProcessingTaskBOS = BeanTransform.copyProperties(involvedProcessingTasks, InvolvedProcessingTaskBO.class);
        return involvedProcessingTaskBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InvolvedProcessingTaskBO insertInvolvedProcessingTask(InvolvedProcessingTaskTO involvedProcessingTaskTO) throws SerException {
        checkAddIdentity();
        InvolvedProcessingTask involvedProcessingTask = BeanTransform.copyProperties(involvedProcessingTaskTO, InvolvedProcessingTask.class, true);
        involvedProcessingTask.setCreateTime(LocalDateTime.now());
        super.save(involvedProcessingTask);
        return BeanTransform.copyProperties(involvedProcessingTask, InvolvedProcessingTaskBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InvolvedProcessingTaskBO editInvolvedProcessingTask(InvolvedProcessingTaskTO involvedProcessingTaskTO) throws SerException {
        checkAddIdentity();
        if(StringUtils.isBlank(involvedProcessingTaskTO.getId())){
            throw new SerException("id不能为空");
        }
        InvolvedProcessingTask involvedProcessingTask = super.findById(involvedProcessingTaskTO.getId());
        BeanTransform.copyProperties(involvedProcessingTaskTO, involvedProcessingTask, true);
        involvedProcessingTask.setModifyTime(LocalDateTime.now());
        super.update(involvedProcessingTask);
        return BeanTransform.copyProperties(involvedProcessingTaskTO, InvolvedProcessingTaskBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeInvolvedProcessingTask(String id) throws SerException {

        checkAddIdentity();
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<InvolvedProcessingTaskBO> searchInvolvedProcessingTask(InvolvedProcessingTaskDTO involvedProcessingTaskDTO) throws SerException {
        /**
         * 内部项目名称
         */
        if(StringUtils.isNotBlank(involvedProcessingTaskDTO.getInternalProjectName())){
            involvedProcessingTaskDTO.getConditions().add(Restrict.like("internalProjectName",involvedProcessingTaskDTO.getInternalProjectName()));
        }
        /**
         * 处理人员
         */
        if(StringUtils.isNotBlank(involvedProcessingTaskDTO.getHandler())){
            involvedProcessingTaskDTO.getConditions().add(Restrict.like("handler",involvedProcessingTaskDTO.getHandler()));
        }
        List<InvolvedProcessingTask> involvedProcessingTasks = super.findByCis(involvedProcessingTaskDTO,true);
        List<InvolvedProcessingTaskBO> involvedProcessingTaskBOS = BeanTransform.copyProperties(involvedProcessingTasks,InvolvedProcessingTaskBO.class);
        return involvedProcessingTaskBOS;
    }

    @Override
    public byte[] exportExcel(InvolvedProcessingTaskDTO dto) throws SerException {
        if (null != dto.getName()) {
            dto.getConditions().add(Restrict.in("internalProjectName", dto.getName()));
        }
        if (null != dto.getHandlers()) {
            dto.getConditions().add(Restrict.in("handler", dto.getHandlers()));
        }
        List<InvolvedProcessingTask> list = super.findByCis(dto);

        List<InvolvedProcessingTaskExport> involvedProcessingTaskExports = new ArrayList<>();
        list.stream().forEach(str -> {
            InvolvedProcessingTaskExport export = BeanTransform.copyProperties(str, InvolvedProcessingTaskExport.class);
            involvedProcessingTaskExports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(involvedProcessingTaskExports, excel);
        return bytes;
    }
    @Override
    public List<String> getName() throws SerException {
        String[] fields = new String[]{"internalProjectName"};
        List<InvolvedProcessingTaskBO> involvedProcessingTaskBOS = super.findBySql("select distinct internalProjectName from projectissuehandle_involvedprocessingtask group by internalProjectName order by internalProjectName asc ", InvolvedProcessingTaskBO.class, fields);

        List<String> collectList = involvedProcessingTaskBOS.stream().map(InvolvedProcessingTaskBO::getInternalProjectName)
                .filter(internalProjectName -> (internalProjectName != null || !"".equals(internalProjectName.trim()))).distinct().collect(Collectors.toList());


        return collectList;
    }
    @Override
    public List<String> getHandler() throws SerException {
        String[] fields = new String[]{"handler"};
        List<InvolvedProcessingTaskBO> involvedProcessingTaskBOS = super.findBySql("select distinct handler from projectissuehandle_involvedprocessingtask group by handler order by handler asc ", InvolvedProcessingTaskBO.class, fields);

        List<String> collectList = involvedProcessingTaskBOS.stream().map(InvolvedProcessingTaskBO::getHandler)
                .filter(handler -> (handler != null || !"".equals(handler.trim()))).distinct().collect(Collectors.toList());


        return collectList;
    }
}