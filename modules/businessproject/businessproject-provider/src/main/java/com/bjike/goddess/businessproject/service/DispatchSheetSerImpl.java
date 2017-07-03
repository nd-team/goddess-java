package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.DispatchSheetBO;
import com.bjike.goddess.businessproject.dto.DispatchSheetDTO;
import com.bjike.goddess.businessproject.entity.DispatchSheet;
import com.bjike.goddess.businessproject.enums.GuideAddrStatus;
import com.bjike.goddess.businessproject.excel.ContractCategoryExcel;
import com.bjike.goddess.businessproject.excel.DispatchSheetExcel;
import com.bjike.goddess.businessproject.to.DispatchSheetTO;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 商务项目派工单信息管理业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 10:06 ]
 * @Description: [ 商务项目派工单信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectSerCache")
@Service
public class DispatchSheetSerImpl extends ServiceImpl<DispatchSheet, DispatchSheetDTO> implements DispatchSheetSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
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
    private void checkAddIdentity() throws SerException{
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
        if( flagSee || flagAdd ){
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

    /**
     * 日期格式(年月日)
     */
    private void checkDate(DispatchSheetTO dispatchSheetTO) throws SerException {
        try {
            DateUtil.parseDate(dispatchSheetTO.getSiginTime());
            DateUtil.parseDate(dispatchSheetTO.getStartProjectTime());
            DateUtil.parseDate(dispatchSheetTO.getEndProjectTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式有误");
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
    public Long countDispatchSheet(DispatchSheetDTO dispatchSheetDTO) throws SerException {
        searchCondition(dispatchSheetDTO);

        Long count = super.count(dispatchSheetDTO);
        return count;
    }

    @Override
    public DispatchSheetBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        DispatchSheet dispatchSheet = super.findById(id);
        return BeanTransform.copyProperties(dispatchSheet, DispatchSheetBO.class);
    }

    @Override
    public List<DispatchSheetBO> listDispatchSheet(DispatchSheetDTO dispatchSheetDTO) throws SerException {
        checkSeeIdentity();

        searchCondition(dispatchSheetDTO);
        List<DispatchSheet> list = super.findByPage(dispatchSheetDTO);
        List<DispatchSheetBO> dispatchSheetBOList = BeanTransform.copyProperties(list, DispatchSheetBO.class);
        return dispatchSheetBOList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DispatchSheetBO addDispatchSheet(DispatchSheetTO dispatchSheetTO) throws SerException {
        checkAddIdentity();
        checkDate(dispatchSheetTO);
        DispatchSheet dispatchSheet = BeanTransform.copyProperties(dispatchSheetTO, DispatchSheet.class, true);
        dispatchSheet.setCreateTime(LocalDateTime.now());
        super.save(dispatchSheet);

        DispatchSheetBO dispatchSheetBO = BeanTransform.copyProperties(dispatchSheet, DispatchSheetBO.class);

        return dispatchSheetBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DispatchSheetBO editDispatchSheet(DispatchSheetTO dispatchSheetTO) throws SerException {
        checkAddIdentity();

        DispatchSheet temp = super.findById(dispatchSheetTO.getId());

        checkDate(dispatchSheetTO);
        DispatchSheet dispatchSheet = BeanTransform.copyProperties(dispatchSheetTO, DispatchSheet.class, true);
        BeanUtils.copyProperties(dispatchSheet, temp, "id", "createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        DispatchSheetBO dispatchSheetBO = BeanTransform.copyProperties(temp, DispatchSheetBO.class);

        return dispatchSheetBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteDispatchSheet(String id) throws SerException {
        checkAddIdentity();

        super.remove(id);
    }


    public void searchCondition(DispatchSheetDTO dispatchSheetDTO) throws SerException {
        /**
         * 内部项目名称
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getInnerProject())) {
            dispatchSheetDTO.getConditions().add(Restrict.like("innerProject", dispatchSheetDTO.getInnerProject()));
        }/**
         * 合同外部项目编号
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getOutProjectNum())) {
            dispatchSheetDTO.getConditions().add(Restrict.like("outProjectNum", dispatchSheetDTO.getOutProjectNum()));
        }/**
         * 对应销售合同编号
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getSaleContractNum())) {
            dispatchSheetDTO.getConditions().add(Restrict.like("saleContractNum", dispatchSheetDTO.getSaleContractNum()));
        }
        /**
         * 业务类型
         */
        if (dispatchSheetDTO.getBusinessType() != null) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("businessType", dispatchSheetDTO.getBusinessType()));
        }
        /**
         * 业务方向科目
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getBusinessSubject())) {
            dispatchSheetDTO.getConditions().add(Restrict.like("businessSubject", dispatchSheetDTO.getBusinessSubject()));
        }
        /**
         * 合作方式
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getBusinessCooperate())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("businessCooperate", dispatchSheetDTO.getBusinessCooperate()));
        }
        /**
         * 总包单位名称
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getMajorCompany())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("majorCompany", dispatchSheetDTO.getMajorCompany()));
        }
        /**
         * 分包单位名称
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getSubCompany())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("subCompany", dispatchSheetDTO.getSubCompany()));
        }
        /**
         * 地区
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getArea())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("area", dispatchSheetDTO.getArea()));
        }
        /**
         * 派工单名称
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getDispatchProject())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("dispatchProject", dispatchSheetDTO.getDispatchProject()));
        }
        /**
         * 派工单编号
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getDispatchNum())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("dispatchNum", dispatchSheetDTO.getDispatchNum()));
        }

    }

    @Override
    public List<String> listArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<DispatchSheetBO> dispatchSheetBOList = super.findBySql("select area from businessproject_dispatchsheet order by area asc ", DispatchSheetBO.class, fields);

        List<String> areaList = dispatchSheetBOList.stream().map(DispatchSheetBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return areaList;
    }

    @Override
    public List<String> listDispatchName() throws SerException {
        String[] fields = new String[]{"dispatchProject"};
        List<DispatchSheetBO> dispatchSheetBOList = super.findBySql("select dispatchProject from businessproject_dispatchsheet order by area asc ", DispatchSheetBO.class, fields);

        List<String> dispatchProjectList = dispatchSheetBOList.stream().map(DispatchSheetBO::getDispatchProject)
                .filter(str -> (str != null || !"".equals(str.trim()))).distinct().collect(Collectors.toList());


        return dispatchProjectList;
    }

    @Override
    public Set<String> allInnerProjects() throws SerException {
        List<DispatchSheet> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (DispatchSheet b : list) {
            set.add(b.getInnerProject());
        }
        return set;
    }

    @Override
    public List<String> allDispatchNum() throws SerException {
        List<DispatchSheet> list = super.findAll();
        List<String> nums = new ArrayList<>();
        for (DispatchSheet b : list) {
            nums.add(b.getDispatchNum());
        }
        return nums;
    }

    @Override
    public List<DispatchSheetBO> getInfoByDispatchNum(String dispatchNum) throws SerException {
        DispatchSheetDTO dto = new DispatchSheetDTO();
        if( StringUtils.isNotBlank(dispatchNum)){
            dto.getConditions().add(Restrict.eq("dispatchNum",dispatchNum));
        }
        List<DispatchSheet> list =  super.findByCis( dto );
        return BeanTransform.copyProperties( list , DispatchSheetBO.class);
    }

    @Override
    public byte[] exportExcel(DispatchSheetDTO dto) throws SerException {
        String[] innerProjects = dto.getInnerProjects();
        List<DispatchSheetExcel> toList = new ArrayList<DispatchSheetExcel>();
        if ((innerProjects != null) && (innerProjects.length > 0)) {
            List<DispatchSheet> list = super.findByCis(dto);
            for (String s : innerProjects) {
                if (StringUtils.isNotBlank(s)) {
                    for (DispatchSheet b : list) {
                        if (s.equals(b.getInnerProject())) {
                            DispatchSheetExcel excel = new DispatchSheetExcel();
                            BeanUtils.copyProperties(b, excel);
                            toList.add(excel);
                        }
                    }
                }
            }
        } else {
            List<DispatchSheet> list = super.findByCis(dto);
            for (DispatchSheet b : list) {
                DispatchSheetExcel excel = new DispatchSheetExcel();
                BeanUtils.copyProperties(b, excel);
                toList.add(excel);
            }
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void leadExcel(List<DispatchSheetTO> toList) throws SerException {
        checkAddIdentity();
        for (DispatchSheetTO to : toList) {
            DispatchSheet baseInfoManage = new DispatchSheet();
            BeanUtils.copyProperties(to, baseInfoManage);
            baseInfoManage.setSiginTime(DateUtil.parseDate(to.getSiginTime()));
            baseInfoManage.setStartProjectTime(DateUtil.parseDate(to.getStartProjectTime()));
            baseInfoManage.setEndProjectTime(DateUtil.parseDate(to.getEndProjectTime()));
            String completeProject = baseInfoManage.getCompleteProject();
            String fileCondition = baseInfoManage.getFileCondition();
            String innerProjectNum = baseInfoManage.getInnerProjectNum();
            if ((!"已完工".equals(completeProject)) && (!"未完工".equals(completeProject))) {
                throw new SerException("是否完工只能为未完工或已完工");
            }
            if ((!"未归档".equals(fileCondition)) && (!"已归档".equals(fileCondition))) {
                throw new SerException("合同是否已归档只能为未归档或已归档");
            }
            if (innerProjectNum == null || StringUtils.isBlank(innerProjectNum)) {
                baseInfoManage.setInnerProjectNum("无");
            }
            super.save(baseInfoManage);
        }
    }

    @Override
    public byte[] templateExcel() throws SerException {
        List<DispatchSheetExcel> toList = new ArrayList<DispatchSheetExcel>();
        DispatchSheetExcel baseInfoManageLeadExcel = new DispatchSheetExcel();
        toList.add(baseInfoManageLeadExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }
}