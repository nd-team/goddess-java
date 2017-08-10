package com.bjike.goddess.festival.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.festival.bo.CompanyFestivalTimeBO;
import com.bjike.goddess.festival.dto.CompanyFestivalTimeDTO;
import com.bjike.goddess.festival.entity.CompanyFestivalTime;
import com.bjike.goddess.festival.to.CompanyFestivalTimeTO;
import com.bjike.goddess.festival.to.GuidePermissionTO;
import com.bjike.goddess.festival.type.GuideAddrStatus;
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
import java.util.stream.Collectors;

/**
 * 公司放假时间安排业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 08:10 ]
 * @Description: [ 公司放假时间安排业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "festivalSerCache")
@Service
public class CompanyFestivalTimeSerImpl extends ServiceImpl<CompanyFestivalTime, CompanyFestivalTimeDTO> implements CompanyFestivalTimeSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是本部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }



    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee ) {
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
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case SEE:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countCompanyFestivalTime(CompanyFestivalTimeDTO companyFestivalTimeDTO) throws SerException {
        return super.count(companyFestivalTimeDTO);
    }

    @Override
    public CompanyFestivalTimeBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        CompanyFestivalTime companyFestivalTime = super.findById(id);
        return BeanTransform.copyProperties(companyFestivalTime, CompanyFestivalTimeBO.class);

    }

    @Override
    public List<CompanyFestivalTimeBO> listCompanyFestivalTime(CompanyFestivalTimeDTO companyFestivalTimeDTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        checkPermission();
        RpcTransmit.transmitUserToken(userToken);
        companyFestivalTimeDTO.getSorts().add("createTime=desc");
        List<CompanyFestivalTime> list = super.findByCis(companyFestivalTimeDTO);

        return BeanTransform.copyProperties(list, CompanyFestivalTimeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyFestivalTimeBO addCompanyFestivalTime(CompanyFestivalTimeTO companyFestivalTimeTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        checkPermission();
        RpcTransmit.transmitUserToken(userToken);

        CompanyFestivalTimeDTO dto = new CompanyFestivalTimeDTO();
        dto.getConditions().add(Restrict.eq("name", companyFestivalTimeTO.getName()));
        Long count = super.count(dto);
        if (count > 0) {
            throw new SerException("已存在一条该节日名称，节日名称不能相同,添加失败");
        }
        CompanyFestivalTime companyFestivalTime = BeanTransform.copyProperties(companyFestivalTimeTO, CompanyFestivalTime.class, true);
        companyFestivalTime.setCreateTime(LocalDateTime.now());
        super.save(companyFestivalTime);
        return BeanTransform.copyProperties(companyFestivalTime, CompanyFestivalTimeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyFestivalTimeBO editCompanyFestivalTime(CompanyFestivalTimeTO companyFestivalTimeTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        checkPermission();
        RpcTransmit.transmitUserToken(userToken);

        CompanyFestivalTimeDTO dto = new CompanyFestivalTimeDTO();
        dto.getConditions().add(Restrict.eq("name", companyFestivalTimeTO.getName()));
        Long count = super.count(dto);


        CompanyFestivalTime temp = super.findById(companyFestivalTimeTO.getId());
        if (!companyFestivalTimeTO.getName().equals(temp.getName()) && count > 0) {
            throw new SerException("已存在一条该节日名称，节日名称不能相同,编辑失败");
        }
        CompanyFestivalTime companyFestivalTime = BeanTransform.copyProperties(companyFestivalTimeTO, CompanyFestivalTime.class, true);

        BeanUtils.copyProperties(companyFestivalTime, temp, "id", "createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
        return BeanTransform.copyProperties(companyFestivalTime, CompanyFestivalTimeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCompanyFestivalTime(String id) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        checkPermission();
        RpcTransmit.transmitUserToken(userToken);

        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public List<String> listFestivalName() throws SerException {
        String[] fields = new String[]{"name"};
        List<CompanyFestivalTimeBO> companyFestivalTimeBOS = super.findBySql(
                "select name  from festival_companyfestivaltime group by name  order by name desc ,createTime desc ", CompanyFestivalTimeBO.class, fields);

        List<String> list = companyFestivalTimeBOS.stream().map(CompanyFestivalTimeBO::getName)
                .filter(name -> (name != null || !"".equals(name.trim()))).distinct().collect(Collectors.toList());


        return list;
    }

    @Override
    public Long countFestivalTimeByName(CompanyFestivalTimeDTO companyFestivalTimeDTO) throws SerException {
        if (StringUtils.isBlank(companyFestivalTimeDTO.getName())) {
            throw new SerException("节日名不能为空");
        }
        companyFestivalTimeDTO.getConditions().add(Restrict.eq("name", companyFestivalTimeDTO.getName()));
        Long count = super.count(companyFestivalTimeDTO);
        return count;
    }

    @Override
    public List<CompanyFestivalTimeBO> getCompanyFestivalTime(CompanyFestivalTimeDTO companyFestivalTimeDTO) throws SerException {
        if (StringUtils.isBlank(companyFestivalTimeDTO.getName())) {
            throw new SerException("节日名不能为空");
        }
        companyFestivalTimeDTO.getConditions().add(Restrict.eq("name", companyFestivalTimeDTO.getName()));
        List<CompanyFestivalTime> companyFestivalTime = super.findByCis(companyFestivalTimeDTO, true);
        List<CompanyFestivalTimeBO> cb = BeanTransform.copyProperties(companyFestivalTime, CompanyFestivalTimeBO.class);
        return cb;
    }
}