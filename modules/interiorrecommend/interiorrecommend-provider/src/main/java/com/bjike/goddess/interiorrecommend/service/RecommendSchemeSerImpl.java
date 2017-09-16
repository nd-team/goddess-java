package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.interiorrecommend.bo.RecommendSchemeBO;
import com.bjike.goddess.interiorrecommend.dto.*;
import com.bjike.goddess.interiorrecommend.entity.*;
import com.bjike.goddess.interiorrecommend.enums.GuideAddrStatus;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.RecommendSchemeTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.recruit.api.InterviewInforAPI;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.recruit.dto.InterviewInforDTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 推荐方案业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 10:31 ]
 * @Description: [ 推荐方案业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "interiorrecommendSerCache")
@Service
public class RecommendSchemeSerImpl extends ServiceImpl<RecommendScheme, RecommendSchemeDTO> implements RecommendSchemeSer {

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private UserDetailAPI userDetailAPI;

    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    @Autowired
    private OldRecommendRequireSer recommendRequireSer;

    @Autowired
    private OldRecommendInfoSer recommendInfoSer;

    @Autowired
    private OldRecommendAssessDetailSer recommendAssessDetailSer;

    @Autowired
    private OldAwardStandardSer awardStandardSer;

    @Autowired
    private OldRecommendContentSer recommendContentSer;

    @Autowired
    private OldAwardInfoSer awardInfoSer;

    @Autowired
    private InterviewInforAPI interviewInforAPI;

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
     * 核对是否有审核权限（部门级别）
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
     * 核对添加修改删除权限（岗位级别）
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

//    /**
//     * 综合资源部负责人审核权限
//     */
//    private Boolean guideSynthesize() throws SerException {
//        Boolean flag = false;
//        String userToken = RpcTransmit.getUserToken();
//        UserBO userBO = userAPI.currentUser();
//        RpcTransmit.transmitUserToken(userToken);
//        String userName = userBO.getUsername();
//        if (!"admin".equals(userName.toLowerCase())) {
//            flag = cusPermissionSer.busCusPermission("3");
//        } else {
//            flag = true;
//        }
//        return flag;
//    }
//
//    /**
//     * 运营商务部负责人审核权限
//     */
//    private Boolean guideOperation() throws SerException {
//        Boolean flag = false;
//        String userToken = RpcTransmit.getUserToken();
//        UserBO userBO = userAPI.currentUser();
//        RpcTransmit.transmitUserToken(userToken);
//        String userName = userBO.getUsername();
//        if (!"admin".equals(userName.toLowerCase())) {
//            flag = cusPermissionSer.busCusPermission("4");
//        } else {
//            flag = true;
//        }
//        return flag;
//    }
//
//    /**
//     * 总经办审核权限
//     */
//    private Boolean guideManager() throws SerException {
//        Boolean flag = false;
//        String userToken = RpcTransmit.getUserToken();
//        UserBO userBO = userAPI.currentUser();
//        RpcTransmit.transmitUserToken(userToken);
//        String userName = userBO.getUsername();
//        if (!"admin".equals(userName.toLowerCase())) {
//            flag = cusPermissionSer.busCusPermission("5");
//        } else {
//            flag = true;
//        }
//        return flag;
//    }

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
//            case SYNTHESIZEAUDIT:
//                flag = guideSynthesize();
//                break;
//            case MANAGEADUIT:
//                flag = guideManager();
//                break;
//            case OPERATIONAUDIT:
//                flag = guideOperation();
//                break;
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
    public RecommendSchemeBO insertModel(RecommendSchemeTO to) throws SerException {
        RecommendScheme model = BeanTransform.copyProperties(to, RecommendScheme.class, true);
        LocalDateTime openTime = DateUtil.parseDateTime(to.getOpenTime()+" 00:00:00");
        LocalDateTime closeTime = DateUtil.parseDateTime(to.getCloseTime()+" 00:00:00");
        long time=closeTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                - openTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        if (time<0){
            throw new SerException("推荐奖开通时间不能晚于关闭时间");
        }
        long day=time/86400000;
        model.setRecommendTime((int)day);
        model.setMakeTime(LocalDate.now());
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, RecommendSchemeBO.class);
    }

    @Override
    public RecommendSchemeBO updateModel(RecommendSchemeTO to) throws SerException {
        setUpdate(to);
        return BeanTransform.copyProperties(to, RecommendSchemeBO.class);
    }

    @Override
    public List<RecommendSchemeBO> pageList(RecommendSchemeDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<RecommendScheme> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, RecommendSchemeBO.class);
    }

//    @Override
//    public void resourcesAudit(String id, String resourcesSuggest, Boolean resourcesAudit) throws SerException {
//        //判断是否为综合部负责人
//        if (guideSynthesize() == true) {
//            String userBO = RpcTransmit.getUserToken();
//            String userId = userAPI.currentUser().getId();
//            RecommendScheme model = super.findById(id);
//            if (model != null) {
//                //todo 尚未明确组织结构,需要判断当前用户是否为综合资源部负责人
//                model.setResourcesSuggest(resourcesSuggest);
//                model.setResourcesAudit(resourcesAudit);
//                super.update(model);
//            } else {
//                throw new SerException("非法Id,推荐方案对象不能为空!");
//            }
//        } else {
//            throw new SerException("你没有审核权限");
//        }
//    }

//    @Override
//    public void operateAudit(String id, String operateSuggest, Boolean operateAudit) throws SerException {
//        //判断是否为运营部负责人
//        if (guideOperation() == true) {
//            String userBO = RpcTransmit.getUserToken();
//            String userId = userAPI.currentUser().getId();
//                RecommendScheme model = super.findById(id);
//                if (model != null) {
//                    //todo 尚未明确组织结构，需要判断当前用户是否为运营商务部负责人zzzzzz
//                    model.setOperateSuggest(operateSuggest);
//                    model.setOperateAudit(operateAudit);
//                    super.update(model);
//                } else {
//                    throw new SerException("非法Id,推荐方案对象不能为空!");
//                }
//            } else {
//                throw new SerException("你没有审核权限");
//            }
//    }
//
//    @Override
//    public void generalAudit(String id, String generalSuggest, Boolean generalAudit) throws SerException {
//        //判断为是否为总经办
//        if (guideManager() == true) {
//            String userBO = RpcTransmit.getUserToken();
//            String userId = userAPI.currentUser().getId();
//                RecommendScheme model = super.findById(id);
//                if (model != null) {
//                    //todo 尚未明确组织结构，需要判断当前用户是否为总经办
//                    model.setGeneralSuggest(generalSuggest);
//                    model.setGeneralAudit(generalAudit);
//                    super.update(model);
//                } else {
//                    throw new SerException("非法Id,推荐方案对象不能为空!");
//                }
//            } else {
//                throw new SerException("你没有审核权限");
//            }
//    }


    /**
     * 更新数据（编辑、审核）
     *
     * @param to 推荐方案
     */
    public void setUpdate(RecommendSchemeTO to) throws SerException {

        if (!StringUtils.isEmpty(to.getId())) {
            RecommendScheme model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }
    //由于关联了很多的表,所以不能直接调用remove方法来删除,我之前试过了用remove的方法删除其他的表,发现报错,最后只能用sql语句这简单暴力的方法——————江载旋　2017-08-09 22:07
//    @Override
//    @Transactional
//    public void delete(String id) throws SerException {
//        if(null != id){
//            OldRecommendRequireDTO dto = new OldRecommendRequireDTO();
//            dto.getConditions().add(Restrict.eq("recommendScheme.id",id));
//            List<OldRecommendRequire> list = recommendRequireSer.findByCis(dto);
//            if(list != null && list.size() >0) {
//                for (OldRecommendRequire recommendRequire : list) {
//                    OldRecommendInfoDTO infoDTO = new OldRecommendInfoDTO();
//                    infoDTO.getConditions().add(Restrict.eq("recommendRequire.id", recommendRequire.getId()));
//                    List<OldRecommendInfo> info = recommendInfoSer.findByCis(infoDTO);
//                    if(info != null && info.size() > 0) {
//                        for (OldRecommendInfo recommendInfo : info) {
//                            OldRecommendContentDTO contentDTO = new OldRecommendContentDTO();
//                            contentDTO.getConditions().add(Restrict.eq("recommendInfo.id", recommendInfo.getId()));
//                            List<OldRecommendContent> contents = recommendContentSer.findByCis(contentDTO);
//                            if(contents != null && contents.size() > 0) {
//                                for (OldRecommendContent recommendContent : contents) {
//                                    StringBuilder sql = new StringBuilder("DELETE FROM");
//                                    sql.append(" interiorrecommend_recommendcontent WHERE id = '" + recommendContent.getId()+"'");
//                                    recommendContentSer.executeSql(sql.toString());
//                                }
//                            }
//                            OldAwardInfoDTO awardInfoDTO = new OldAwardInfoDTO();
//                            awardInfoDTO.getConditions().add(Restrict.eq("recommendInfo.id", recommendInfo.getId()));
//                            List<OldAwardInfo> awardInfos = awardInfoSer.findByCis(awardInfoDTO);
//                            if(awardInfos != null && awardInfos.size() >0 ) {
//                                for (OldAwardInfo awardInfo : awardInfos) {
//                                    StringBuilder sql = new StringBuilder("DELETE FROM");
//                                    sql.append(" interiorrecommend_awardinfo WHERE id = '" + awardInfo.getId()+"'");
//                                    awardInfoSer.executeSql(sql.toString());
//                                }
//                            }
//                            recommendInfoSer.remove(recommendInfo.getId());
//                        }
//                    }
//                    OldRecommendAssessDetailDTO detailDTO = new OldRecommendAssessDetailDTO();
//                    detailDTO.getConditions().add(Restrict.eq("recommendRequire.id", recommendRequire.getId()));
//                    List<OldRecommendAssessDetail> details = recommendAssessDetailSer.findByCis(detailDTO);
//                    if(details != null && details.size() > 0) {
//                        for (OldRecommendAssessDetail detail : details) {
//                            StringBuilder sql = new StringBuilder("DELETE FROM");
//                            sql.append(" interiorrecommend_recommendassessdetail WHERE id = '" + detail.getId()+"'");
//                            recommendAssessDetailSer.executeSql(sql.toString());
//                        }
//                    }
//                    OldAwardStandardDTO standardDTO = new OldAwardStandardDTO();
//                    standardDTO.getConditions().add(Restrict.eq("recommendRequire.id", recommendRequire.getId()));
//                    List<OldAwardStandard> standards = awardStandardSer.findByCis(standardDTO);
//                    if(standards != null && standards.size() > 0) {
//                        for (OldAwardStandard awardStandard : standards) {
//                            StringBuilder sql = new StringBuilder("DELETE FROM");
//                            sql.append(" interiorrecommend_awardstandard WHERE id = '" + awardStandard.getId()+"'");
//                            awardStandardSer.executeSql(sql.toString());
//                        }
//                    }
//                    StringBuilder sql = new StringBuilder("DELETE FROM");
//                    sql.append(" interiorrecommend_recommendrequire WHERE id = '" + recommendRequire.getId()+"'");
//                    recommendRequireSer.executeSql(sql.toString());
//                }
//            }
//            super.remove(id);
//        }else {
//            throw new SerException("id不能为空");
//        }
//    }


    @Override
    public void delete(String id) throws SerException {
        if(id != null){
            super.remove(id);
        }else{
            throw new SerException("id不能为空");
        }
    }

    @Override
    public Set<String> findPosition() throws SerException {
        List<InterviewInforBO> boList = interviewInforAPI.findInterview();
        Set<String> strings=boList.stream().map(interviewInforBO -> interviewInforBO.getPosition()).collect(Collectors.toSet());
        return strings;
    }
}