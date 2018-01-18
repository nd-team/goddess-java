package com.bjike.goddess.customerplatform.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customerplatform.bo.SiteBO;
import com.bjike.goddess.customerplatform.dto.SiteDTO;
import com.bjike.goddess.customerplatform.entity.Site;
import com.bjike.goddess.customerplatform.enums.GuideAddrStatus;
import com.bjike.goddess.customerplatform.to.GuidePermissionTO;
import com.bjike.goddess.customerplatform.to.SiteTO;
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

/**
 * 站点业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-30 11:29 ]
 * @Description: [ 站点业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerplatformSerCache")
@Service
public class SiteSerImpl extends ServiceImpl<Site, SiteDTO> implements SiteSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
        return flag;
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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void save(SiteTO to) throws SerException {
        Site entity = BeanTransform.copyProperties(to, Site.class, true);
        super.save(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void update(SiteTO to) throws SerException {
        Site entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        BeanUtils.copyProperties(to, entity, "id", "createTime");
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        Site entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        super.remove(id);
    }

    @Override
    public List<SiteBO> maps(SiteDTO dto) throws SerException {
        search(dto);
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        List<Site> sites = super.findByCis(dto,true);
        List<SiteBO> siteBOs = BeanTransform.copyProperties(sites, SiteBO.class, false);
        if (null != siteBOs && siteBOs.size() > 0) {
            for(SiteBO bo : siteBOs){
                // TODO: 18-1-5 判断是否是vip
                Boolean tar = false;
                if (!tar) {
                    bo.setPhone(transPhone(bo.getPhone()));
                }
            }
        }
        return siteBOs;
    }
    private List<SiteBO> search(SiteDTO dto) throws SerException{
        //站点名称
        if(StringUtils.isNotBlank(dto.getSiteName())){
            dto.getConditions().add(Restrict.like("siteName",dto.getSiteName()));
        }
        //站点类型
        if(StringUtils.isNotBlank(dto.getSiteType())){
            dto.getConditions().add(Restrict.like("siteType",dto.getSiteType()));
        }
        //省份
        if(StringUtils.isNotBlank(dto.getProvinces())){
            dto.getConditions().add(Restrict.eq("provinces",dto.getProvinces()));
        }
        //市
        if(StringUtils.isNotBlank(dto.getCity())){
            dto.getConditions().add(Restrict.eq("city",dto.getCity()));
        }
        //区
        if(StringUtils.isNotBlank(dto.getArea())){
            dto.getConditions().add(Restrict.eq("area",dto.getArea()));
        }
        //需求类型
        if(StringUtils.isNotBlank(dto.getDemandType())){
            dto.getConditions().add(Restrict.like("demandType",dto.getDemandType()));
        }
        if(StringUtils.isNotBlank(dto.getStartTime()) && StringUtils.isNotBlank(dto.getEndTime())){
            dto.getConditions().add(Restrict.between("createTime",new String[]{dto.getStartTime(),dto.getEndTime()}));
        }
        List<Site> sites = super.findByCis(dto);
        List<SiteBO> siteBOs = BeanTransform.copyProperties(sites,SiteBO.class);
        return siteBOs;
    }

    @Override
    public SiteBO getById(String id) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        Site entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        SiteBO bo =  BeanTransform.copyProperties(entity, SiteBO.class);
        // TODO: 18-1-5 判断是否是vip
        Boolean tar = false;
        if (!tar) {
            bo.setPhone(transPhone(bo.getPhone()));
        }
        return bo;
    }

    @Override
    public Long getTotal(SiteDTO dto) throws SerException {
        search(dto);
        return super.count(dto);
    }

    private String transPhone(String phone) throws SerException {
        phone = phone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1****$2");
        return phone;
    }
    @Override
    public List<String> getProvinces() throws SerException {
        Set<String> set = new HashSet<>();
        SiteDTO dto = new SiteDTO();
        List<Site> sites = super.findByCis(dto);
        if(sites != null && sites.size()>0){
            for(Site site :sites){
                set.add(site.getProvinces());
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> getCity(String provinces) throws SerException {
        Set<String> set = new HashSet<>();
        SiteDTO dto = new SiteDTO();
        dto.getConditions().add(Restrict.eq("provinces",provinces));
        List<Site> sites = super.findByCis(dto);
        if(sites != null && sites.size()>0){
            for(Site site :sites){
                set.add(site.getCity());
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> getArea(String provinces,String city) throws SerException {
        Set<String> set = new HashSet<>();
        SiteDTO dto = new SiteDTO();
        dto.getConditions().add(Restrict.eq("provinces",provinces));
        dto.getConditions().add(Restrict.eq("city",city));
        List<Site> sites = super.findByCis(dto);
        if(sites != null && sites.size()>0){
            for(Site site :sites){
                set.add(site.getArea());
            }
        }
        return new ArrayList<>(set);
    }
}