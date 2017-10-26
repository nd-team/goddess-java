package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.interiorrecommend.bo.SchemeImplementBO;
import com.bjike.goddess.interiorrecommend.dto.RecommendSchemeDTO;
import com.bjike.goddess.interiorrecommend.dto.SchemeImplementDTO;
import com.bjike.goddess.interiorrecommend.entity.RecommendScheme;
import com.bjike.goddess.interiorrecommend.entity.SchemeImplement;
import com.bjike.goddess.interiorrecommend.enums.GuideAddrStatus;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.SchemeImplementTO;
import com.bjike.goddess.interiorrecommend.vo.SchemeImplementVO;
import com.bjike.goddess.regularization.api.RegularizationAPI;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.dto.EntryBasicInfoDTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
* 内部推荐方案实施业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-12 11:29 ]
* @Description:	[ 内部推荐方案实施业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="interiorrecommendSerCache")
@Service
public class SchemeImplementSerImpl extends ServiceImpl<SchemeImplement, SchemeImplementDTO> implements SchemeImplementSer {
    @Autowired
    private RecommendSchemeSer recommendSchemeSer;

    @Autowired
    private EntryBasicInfoAPI entryBasicInfoAPI;
    @Autowired
    private RegularizationAPI regularizationAPI;

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
    public void add(SchemeImplementTO to) throws SerException {
        SchemeImplement schemeImplement = BeanTransform.copyProperties(to,SchemeImplement.class,true);
        List<EntryBasicInfoBO> boList = entryBasicInfoAPI.getEntryBasicInfoByName(to.getBeRecommender());
        if(boList !=null && boList.size() > 0){
            schemeImplement.setIsEntry(true);
            Boolean isRegular = regularizationAPI.checkTran(to.getBeRecommender());
            schemeImplement.setIsRegular(isRegular);
            if(isRegular == true){
                schemeImplement.setIsAcquire(true);
            }else{
                schemeImplement.setIsAcquire(false);
            }

        }else{
            schemeImplement.setIsEntry(false);
            schemeImplement.setIsRegular(false);
            schemeImplement.setIsAcquire(false);
        }
        RecommendSchemeDTO dto = new RecommendSchemeDTO();
        dto.getConditions().add(Restrict.eq("recommendPosition",to.getRecommendPosition()));
        dto.getConditions().add(Restrict.eq("type",to.getType()));
        RecommendScheme recommendScheme = recommendSchemeSer.findOne(dto);
        schemeImplement.setReferralBonus(recommendScheme.getAwardMoney());
        super.save(schemeImplement);
    }

    @Override
    public void delete(String id) throws SerException {
        if(id != null){
            super.remove(id);
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public void modify(SchemeImplementTO to) throws SerException {
        SchemeImplement model = super.findById(to.getId());
        if(to.getGrantDate() !=null) {
           if(model.getIsAcquire() !=null && model.getIsAcquire() == true){
               model.setGrantDate(DateUtil.parseDate(to.getGrantDate()));
           }else{
               throw new SerException("该推荐人没有获得推荐奖，不能录入奖金发放时间");
           }
        }
        model.setBeRecommender(to.getBeRecommender());
        model.setType(to.getType());
        model.setInitiator(to.getInitiator());
        model.setPathway(to.getPathway());
        model.setRecommendTime(DateUtil.parseDate(to.getRecommendTime()));
        model.setRecommendType(to.getRecommendType());
        model.setRecommendPosition(to.getRecommendPosition());
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    @Override
    public List<SchemeImplementBO> pageList(SchemeImplementDTO dto) throws SerException {
        List<SchemeImplement> list = super.findByPage(dto);
        List<SchemeImplementBO> boList = BeanTransform.copyProperties(list,SchemeImplementBO.class,false);
        return boList;
    }

    @Override
    public Long count(SchemeImplementDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public SchemeImplementBO findOne(String id) throws SerException {
        SchemeImplement schemeImplement = super.findById(id);
        SchemeImplementBO bo = BeanTransform.copyProperties(schemeImplement,SchemeImplementBO.class,false);
        return bo;
    }

    @Override
    public Integer findAward(String type, String recommendPosition) throws SerException {
        RecommendSchemeDTO dto = new RecommendSchemeDTO();
        dto.getConditions().add(Restrict.eq("type",type));
        dto.getConditions().add(Restrict.eq("recommendPosition",recommendPosition));
        RecommendScheme recommendScheme = recommendSchemeSer.findOne(dto);
        Integer awardMoney = recommendScheme.getAwardMoney();
        return awardMoney;
    }

    @Override
    public String findPosition(String type) throws SerException {
        RecommendSchemeDTO dto = new RecommendSchemeDTO();
        dto.getConditions().add(Restrict.eq("type",type));
        RecommendScheme recommendScheme = recommendSchemeSer.findOne(dto);
        String position = recommendScheme.getRecommendPosition();
        return position;
    }

    @Override
    public List<EntryBasicInfoBO> findEntry() throws SerException {
        List<EntryBasicInfoBO> boList = entryBasicInfoAPI.listEntryBasicInfo();
        return boList;
    }



}