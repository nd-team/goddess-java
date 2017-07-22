package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.managepromotion.bo.CollectBO;
import com.bjike.goddess.managepromotion.bo.EmployeePromotedBO;
import com.bjike.goddess.managepromotion.dto.EmployeePromotedDTO;
import com.bjike.goddess.managepromotion.entity.EmployeePromoted;
import com.bjike.goddess.managepromotion.enums.GuideAddrStatus;
import com.bjike.goddess.managepromotion.to.CollectTO;
import com.bjike.goddess.managepromotion.to.EmployeePromotedTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
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
 * 员工已晋升情况业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:20 ]
 * @Description: [ 员工已晋升情况业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managepromotionSerCache")
@Service
public class EmployeePromotedSerImpl extends ServiceImpl<EmployeePromoted, EmployeePromotedDTO> implements EmployeePromotedSer {

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
            case DELETE:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countEmployeePromoted(EmployeePromotedDTO employeePromotedDTO) throws SerException {
        Long count = super.count(employeePromotedDTO);
        return count;
    }

    @Override
    public EmployeePromotedBO getOne(String id) throws SerException {
        EmployeePromoted employeePromoted = super.findById(id);
        return BeanTransform.copyProperties(employeePromoted, EmployeePromotedBO.class);
    }

    @Override
    public List<EmployeePromotedBO> findListEmployeePromoted(EmployeePromotedDTO employeePromotedDTO) throws SerException {
        checkSeeIdentity();
        seach(employeePromotedDTO);
        employeePromotedDTO.getSorts().add("createTime=desc");
        List<EmployeePromoted> employeePromoteds = super.findByPage(employeePromotedDTO);
        List<EmployeePromotedBO> employeePromotedBOS = BeanTransform.copyProperties(employeePromoteds, EmployeePromotedBO.class);
        return employeePromotedBOS;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EmployeePromotedBO insertEmployeePromoted(EmployeePromotedTO employeePromotedTO) throws SerException {
        checkAddIdentity();
        EmployeePromoted employeePromoted = BeanTransform.copyProperties(employeePromotedTO, EmployeePromoted.class, true);
        employeePromoted.setModifyTime(LocalDateTime.now());
        super.save(employeePromoted);
        return BeanTransform.copyProperties(employeePromoted, EmployeePromotedBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EmployeePromotedBO editEmployeePromoted(EmployeePromotedTO employeePromotedTO) throws SerException {
        checkAddIdentity();
        EmployeePromoted employeePromoted = super.findById(employeePromotedTO.getId());
        BeanTransform.copyProperties(employeePromotedTO, employeePromoted, true);
        employeePromoted.setModifyTime(LocalDateTime.now());
        super.update(employeePromoted);

        return BeanTransform.copyProperties(employeePromoted, EmployeePromotedBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeEmployeePromoted(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    public void seach(EmployeePromotedDTO employeePromotedDTO) throws SerException {
        /**
         * 姓名
         */

        if (StringUtils.isNotBlank(employeePromotedDTO.getName())) {
            employeePromotedDTO.getConditions().add(Restrict.eq("name", employeePromotedDTO.getName()));
        }
        /**
         * 时间
         */
        String start = employeePromotedDTO.getStartTimes();
        String end = employeePromotedDTO.getEndTimes();
        if (StringUtils.isNotBlank(start) && StringUtils.isNotBlank(end)) {
            String[] condi = new String[]{start, end};
            employeePromotedDTO.getConditions().add(Restrict.between("times", condi));
        }
        /**
         * 状态
         */
        if (StringUtils.isNotBlank(employeePromotedDTO.getStatus())) {
            employeePromotedDTO.getConditions().add(Restrict.eq("status", employeePromotedDTO.getStatus()));
        }
    }

    @Override
    public List<CollectBO> collect(CollectTO to) throws SerException {
        EmployeePromotedDTO dto = new EmployeePromotedDTO();
        if (null != to.getName()) {
            dto.getConditions().add(Restrict.in("name", to.getName()));
        }
        String start = to.getStartTime();
        String end = to.getEndTime();
        if (StringUtils.isNotBlank(start) && StringUtils.isNotBlank(end)) {
            String[] condi = new String[]{start, end};
            dto.getConditions().add(Restrict.between("times", condi));
        }
        if (null != to.getStatus()) {
            dto.getConditions().add(Restrict.eq("status", to.getStatus()));
        }
        return promotCollect(dto);
    }

    public List<CollectBO> promotCollect(EmployeePromotedDTO dto) throws SerException {
        List<EmployeePromoted> employeePromoteds = super.findByCis(dto);
        List<CollectBO> collectBOS = new ArrayList<>();
        for (EmployeePromoted model : employeePromoteds) {
            CollectBO bo = new CollectBO();
            bo.setDepartment(model.getDepartment());
            bo.setProjectGroup(model.getProjectGroup());
            bo.setName(model.getName());
            bo.setJobs(model.getJobs());
            bo.setChannel(model.getChannel());
            bo.setTimes(DateUtil.dateToString(model.getTimes()));
            bo.setPromotionBefore(model.getPromotionBefore());
            bo.setPromotionAfter(model.getPromotionAfter());
            bo.setExtent(model.getExtent());
            bo.setTotalRange(model.getTotalRange());
            bo.setStatus(model.getStatus());
            collectBOS.add(bo);

        }
        //幅度
        Integer extent = 0;
        //总幅度
        Integer totalRange = 0;
        if (employeePromoteds != null) {
            extent = employeePromoteds.stream().filter(p -> p.getExtent() != null).mapToInt(p -> p.getExtent()).sum();
            totalRange = employeePromoteds.stream().filter(p -> p.getTotalRange() != null).mapToInt(p -> p.getTotalRange()).sum();

            CollectBO totalBo = new CollectBO("合计幅度", extent, totalRange);
            collectBOS.add(totalBo);
        } else {
            extent = employeePromoteds.stream().filter(p -> p.getExtent() != null).mapToInt(p -> p.getExtent()).sum();
            totalRange = employeePromoteds.stream().filter(p -> p.getTotalRange() != null).mapToInt(p -> p.getTotalRange()).sum();

            CollectBO totalBo = new CollectBO("合计幅度", extent, totalRange);
            collectBOS.add(totalBo);
        }
        return collectBOS;
    }

    @Override
    public List<String> getName() throws SerException {
        String[] fields = new String[]{"name"};
        List<EmployeePromotedBO> boList = super.findBySql("select name from managepromotion_employeepromoted group by name order by name asc ", EmployeePromotedBO.class, fields);

        List<String> nameList = boList.stream().map(EmployeePromotedBO::getName)
                .filter(name -> (name != null || !"".equals(name.trim()))).distinct().collect(Collectors.toList());


        return nameList;
    }

    @Override
    public List<String> getStatus() throws SerException {
        String[] fields = new String[]{"status"};
        List<EmployeePromotedBO> boList = super.findBySql("select status from managepromotion_employeepromoted group by status order by status asc ", EmployeePromotedBO.class, fields);

        List<String> statusList = boList.stream().map(EmployeePromotedBO::getStatus)
                .filter(status -> (status != null || !"".equals(status.trim()))).distinct().collect(Collectors.toList());


        return statusList;
    }

}