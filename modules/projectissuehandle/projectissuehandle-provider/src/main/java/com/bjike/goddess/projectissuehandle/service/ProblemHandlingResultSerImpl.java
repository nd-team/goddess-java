package com.bjike.goddess.projectissuehandle.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.projectissuehandle.bo.ProblemHandlingResultBO;
import com.bjike.goddess.projectissuehandle.dto.ProblemHandlingResultDTO;
import com.bjike.goddess.projectissuehandle.entity.ProblemHandlingResult;
import com.bjike.goddess.projectissuehandle.enums.GuideAddrStatus;
import com.bjike.goddess.projectissuehandle.enums.ProblemProcessingResult;
import com.bjike.goddess.projectissuehandle.excel.ProblemHandlingResultExport;
import com.bjike.goddess.projectissuehandle.to.GuidePermissionTO;
import com.bjike.goddess.projectissuehandle.to.ProblemHandlingResultTO;
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
 * 确认问题处理结果业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:30 ]
 * @Description: [ 确认问题处理结果业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectissuehandleSerCache")
@Service
public class ProblemHandlingResultSerImpl extends ServiceImpl<ProblemHandlingResult, ProblemHandlingResultDTO> implements ProblemHandlingResultSer {
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
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = proPermissionSer.getProPermission("1");
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
            flag = proPermissionSer.busProPermission("2");
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

    /**
     * 时间格式（年月日）
     */
    private void checkDate(ProblemHandlingResultTO problemHandlingResultTO) throws SerException {
        try {
            DateUtil.parseDate(problemHandlingResultTO.getProblemAcceptTime());
            DateUtil.parseDate(problemHandlingResultTO.getProblemOccurrenceTime());
            DateUtil.parseDate(problemHandlingResultTO.getProblemSolveTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式不对");
        }
    }

    @Override
    public Long countProblemHandlingResult(ProblemHandlingResultDTO problemHandlingResultDTO) throws SerException {
        if (StringUtils.isNotBlank(problemHandlingResultDTO.getInternalProjectName())) {
            problemHandlingResultDTO.getConditions().add(Restrict.like("internalProjectName", problemHandlingResultDTO.getInternalProjectName()));
        }
        if (StringUtils.isNotBlank(problemHandlingResultDTO.getProjectType())) {
            problemHandlingResultDTO.getConditions().add(Restrict.like("projectType", problemHandlingResultDTO.getProjectType()));
        }
        if (StringUtils.isNotBlank(problemHandlingResultDTO.getProblemObject())) {
            problemHandlingResultDTO.getConditions().add(Restrict.like("problemObject", problemHandlingResultDTO.getProblemObject()));
        }
        Long counts = super.count(problemHandlingResultDTO);
        return counts;
    }

    @Override
    public ProblemHandlingResultBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        ProblemHandlingResult problemHandlingResult = super.findById(id);
        return BeanTransform.copyProperties(problemHandlingResult, ProblemHandlingResultBO.class);
    }

    @Override
    public List<ProblemHandlingResultBO> findListProblemHandlingResult(ProblemHandlingResultDTO problemHandlingResultDTO) throws SerException {
        checkSeeIdentity();
        problemHandlingResultDTO.getSorts().add("createTime=desc");
        List<ProblemHandlingResult> list = super.findByCis(problemHandlingResultDTO, true);
//        List<ProblemHandlingResultBO> problemHandlingResultBOS = new ArrayList<>();
//        list.stream().forEach(str ->{
//            ProblemAcceptBO bo = BeanTransform.copyProperties(str.getProblemAccept(),ProblemAcceptBO.class);
//            ProblemHandlingResultBO problemHandlingResultBO = BeanTransform.copyProperties(str,ProblemHandlingResultBO.class);
//            problemHandlingResultBO.setProblemAcceptBO(bo);
//            problemHandlingResultBOS.add(problemHandlingResultBO);
//        });
//
//        List<ProblemHandlingResultBO> boList = BeanTransform.copyProperties(problemHandlingResultBOS, ProblemHandlingResultBO.class);
        List<ProblemHandlingResultBO> boList = BeanTransform.copyProperties(list, ProblemHandlingResultBO.class);

//        for (int i = 0; i < list.size(); i++) {
//            ProblemHandlingResult temp = list.get(i);
//            ProblemAcceptBO pbo = BeanTransform.copyProperties(temp.getProblemAccept(), ProblemAcceptBO.class);
//            boList.get(i).setProblemAcceptBO(pbo);
//        }

        return boList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemHandlingResultBO insertProblemHandlingResult(ProblemHandlingResultTO problemHandlingResultTO) throws SerException {

        checkAddIdentity();
        checkDate(problemHandlingResultTO);
        ProblemHandlingResult problemHandlingResult = BeanTransform.copyProperties(problemHandlingResultTO, ProblemHandlingResult.class, true, "problemRelevantDepartment");
        problemHandlingResult.setProblemRelevantDepartment(StringUtils.join(problemHandlingResultTO.getProblemRelevantDepartment(), ","));
        problemHandlingResult.setCreateTime(LocalDateTime.now());
        super.save(problemHandlingResult);


        ProblemHandlingResultBO bo = BeanTransform.copyProperties(problemHandlingResult, ProblemHandlingResultBO.class);
        return bo;


//        String projectNum = problemHandlingResultTO.getProjectNum();
//        ProblemAcceptDTO dto = new ProblemAcceptDTO();
//        dto.getConditions().add(Restrict.eq("projectNum",projectNum));
//        ProblemAccept problemAccept = problemAcceptSer.findOne(dto);
//        problemHandlingResult.setProblemAccept(problemAccept);

//        ProblemAcceptDTO dto = new ProblemAcceptDTO();
//        dto.getConditions().add(Restrict.eq("projectNum", ProblemAccept.class));
//        List<ProblemAccept> problemAccepts = problemAcceptSer.findByCis(dto);
        //List<ProblemHandlingResult> problemHandlingResult = super.findByCis();
        //String a = super.findByMaxField("projectNum",ProblemAccept.class);
//        for (int i = 0 ;i < problemAccepts.size();i++) {
//            for (int j = 0 ; j <problemHandlingResults.size();j++){
//                if (problemAccepts != null && problemAccepts.size() > 0) {
//                    ProblemAccept p = problemAccepts.get(0);
//                    problemAccepts.add(p);
//                    }
//                }

//        ProblemAccept p = problemAccepts.get(0);
//        ProblemHandlingResult problemHandlingResult = new ProblemHandlingResult();
//        problemHandlingResult.setYear(p.getYear());
//        problemHandlingResult.setArea(p.getArea());
//        problemHandlingResult.setExternalContractProjectName(p.getExternalContractProjectName());
//        problemHandlingResult.setInternalProjectName(p.getInternalProjectName());
//        problemHandlingResult.setProjectType(p.getProjectType());
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemHandlingResultBO editProblemHandlingResult(ProblemHandlingResultTO problemHandlingResultTO) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(problemHandlingResultTO.getId())) {
            throw new SerException("id不能为空");
        }
        ProblemHandlingResult problemHandlingResult = super.findById(problemHandlingResultTO.getId());
        checkDate(problemHandlingResultTO);
        BeanTransform.copyProperties(problemHandlingResultTO, problemHandlingResult, true, "problemRelevantDepartment");
        problemHandlingResult.setProblemRelevantDepartment(StringUtils.join(problemHandlingResultTO.getProblemRelevantDepartment(), ","));

        problemHandlingResult.setModifyTime(LocalDateTime.now());
        super.update(problemHandlingResult);
        return BeanTransform.copyProperties(problemHandlingResult, ProblemHandlingResultBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeProblemHandlingResult(String id) throws SerException {
        checkAddIdentity();
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

    }


    @Override
    public List<ProblemHandlingResultBO> searchProblemHandlingResult(ProblemHandlingResultDTO problemHandlingResultDTO) throws SerException {
        /**
         * 内部项目名称
         */
        if (StringUtils.isNotBlank(problemHandlingResultDTO.getInternalProjectName())) {
            problemHandlingResultDTO.getConditions().add(Restrict.like("internalProjectName", problemHandlingResultDTO.getInternalProjectName()));
        }
        /**
         * 工程类型
         */
        if (StringUtils.isNotBlank(problemHandlingResultDTO.getProjectType())) {
            problemHandlingResultDTO.getConditions().add(Restrict.like("projectType", problemHandlingResultDTO.getProjectType()));
        }
        /**
         * 问题对象
         */
        if (StringUtils.isNotBlank(problemHandlingResultDTO.getProblemObject())) {
            problemHandlingResultDTO.getConditions().add(Restrict.like("problemObject", problemHandlingResultDTO.getProblemObject()));
        }
        /*if(problemHandlingResultDTO.getProblemObject()!=null){
            problemHandlingResultDTO.getConditions().add(Restrict.eq("problemObject", problemHandlingResultDTO.getProblemObject()));
        }*/


        List<ProblemHandlingResult> problemHandlingResultList = super.findByCis(problemHandlingResultDTO);

        List<ProblemHandlingResultBO> problemHandlingResultBOList = BeanTransform.copyProperties(problemHandlingResultList, ProblemHandlingResultBO.class);
        return problemHandlingResultBOList;
    }


    @Override
    public byte[] exportExcel(ProblemHandlingResultDTO dto) throws SerException {
        if (null != dto.getName()) {
            dto.getConditions().add(Restrict.in("internalProjectName", dto.getName()));
        }
        if (null != dto.getType()) {
            dto.getConditions().add(Restrict.in("projectType", dto.getType()));
        }
        if (null != dto.getObject()) {
            dto.getConditions().add(Restrict.in("problemObject", dto.getObject()));
        }
        List<ProblemHandlingResult> list = super.findByCis(dto);

        List<ProblemHandlingResultExport> problemHandlingResultExports = new ArrayList<>();
        list.stream().forEach(str -> {
            ProblemHandlingResultExport export = BeanTransform.copyProperties(str, ProblemHandlingResultExport.class, "problemProcessingResult");
            export.setProblemProcessingResult(ProblemProcessingResult.exportStrConvert(str.getProblemProcessingResult()));
            problemHandlingResultExports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(problemHandlingResultExports, excel);
        return bytes;
    }

    @Override
    public List<String> getName() throws SerException {
        String[] fields = new String[]{"internalProjectName"};
        List<ProblemHandlingResultBO> problemHandlingResultBOS = super.findBySql("select distinct internalProjectName from projectissuehandle_problemhandlingresult group by internalProjectName order by internalProjectName asc ", ProblemHandlingResultBO.class, fields);

        List<String> collectList = problemHandlingResultBOS.stream().map(ProblemHandlingResultBO::getInternalProjectName)
                .filter(internalProjectName -> (internalProjectName != null || !"".equals(internalProjectName.trim()))).distinct().collect(Collectors.toList());

        return collectList;
    }

    @Override
    public List<String> getType() throws SerException {
        String[] fields = new String[]{"projectType"};
        List<ProblemHandlingResultBO> problemHandlingResultBOS = super.findBySql("select distinct projectType from projectissuehandle_problemhandlingresult group by projectType order by projectType asc ", ProblemHandlingResultBO.class, fields);

        List<String> collectList = problemHandlingResultBOS.stream().map(ProblemHandlingResultBO::getProjectType)
                .filter(projectType -> (projectType != null || !"".equals(projectType.trim()))).distinct().collect(Collectors.toList());


        return collectList;
    }

    @Override
    public List<String> getObject() throws SerException {
        String[] fields = new String[]{"problemObject"};
        List<ProblemHandlingResultBO> problemHandlingResultBOS = super.findBySql("select distinct problemObject from projectissuehandle_problemhandlingresult group by problemObject order by problemObject asc ", ProblemHandlingResultBO.class, fields);

        List<String> collectList = problemHandlingResultBOS.stream().map(ProblemHandlingResultBO::getProblemObject)
                .filter(problemObject -> (problemObject != null || !"".equals(problemObject.trim()))).distinct().collect(Collectors.toList());


        return collectList;
    }


}

