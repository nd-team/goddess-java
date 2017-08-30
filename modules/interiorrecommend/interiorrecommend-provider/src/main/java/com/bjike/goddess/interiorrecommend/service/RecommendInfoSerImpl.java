package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.interiorrecommend.bo.RecommendContentBO;
import com.bjike.goddess.interiorrecommend.bo.RecommendInfoBO;
import com.bjike.goddess.interiorrecommend.bo.RecommendRequireBO;
import com.bjike.goddess.interiorrecommend.dto.AwardInfoDTO;
import com.bjike.goddess.interiorrecommend.dto.AwardStandardDTO;
import com.bjike.goddess.interiorrecommend.dto.RecommendContentDTO;
import com.bjike.goddess.interiorrecommend.dto.RecommendInfoDTO;
import com.bjike.goddess.interiorrecommend.entity.*;
import com.bjike.goddess.interiorrecommend.enums.GuideAddrStatus;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.RecommendInfoTO;
import com.bjike.goddess.interiorrecommend.vo.RecommendInfoVO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.enterprise.inject.spi.Bean;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 推荐信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 02:54 ]
 * @Description: [ 推荐信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "interiorrecommendSerCache")
@Service
public class RecommendInfoSerImpl extends ServiceImpl<RecommendInfo, RecommendInfoDTO> implements RecommendInfoSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private AwardInfoSer awardInfoSer;
    @Autowired
    private RecommendRequireSer recommendRequireSer;
    @Autowired
    private RecommendAssessDetailSer recommendAssessDetailSer;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private RecommendContentSer recommendContentSer;

    @Autowired
    private AwardStandardSer awardStandardSer;

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
    public RecommendInfoBO insertModel(RecommendInfoTO to) throws SerException {
        checkSeeIdentity();
        UserBO userBO = userAPI.currentUser();
        RecommendRequire recommendRequire = recommendRequireSer.findById(to.getRequireId());
        if (recommendRequire != null) {
            RecommendInfo model = BeanTransform.copyProperties(to, RecommendInfo.class, true);
            //保存推荐考核内容
            if (!CollectionUtils.isEmpty(to.getContentList())) {
                List<RecommendContent> contentList = BeanTransform.copyProperties(to.getContentList(), RecommendContent.class);
                model.setRecommendUser(userBO.getUsername());
                model.setRecommendRequire(recommendRequire);
                super.save(model);
                contentList.forEach(entity -> {
                    entity.setRecommendInfo(model);
                });
                recommendContentSer.save(contentList);
                to.setId(model.getId());
                return BeanTransform.copyProperties(to, RecommendInfoBO.class);
            } else {
                throw new SerException("推荐考核内容不能为空!");
            }
        } else {
            throw new SerException("非法推荐要求Id,推荐要求对象不能为空!");
        }
    }

    @Override
    public RecommendInfoBO updateModel(RecommendInfoTO to) throws SerException {
        checkSeeIdentity();
        RecommendRequire recommendRequire = recommendRequireSer.findById(to.getRequireId());
        if (recommendRequire != null) {
            RecommendInfo model = super.findById(to.getId());
            //保存推荐考核内容
            if (!CollectionUtils.isEmpty(to.getContentList())) {
                Set<RecommendContent> detailSet = new HashSet<RecommendContent>();
                List<RecommendContent> detailList = BeanTransform.copyProperties(to.getContentList(), RecommendContent.class);
                detailSet.addAll(detailList);
                for (RecommendContent detail : detailSet) {
                    if (!StringUtils.isEmpty(detail.getId())) {
                        RecommendContent content = recommendContentSer.findById(detail.getId());
                        detail.setCreateTime(content.getCreateTime());
                        detail.setModifyTime(LocalDateTime.now());
                        detail.setRecommendInfo(model);
                    } else {
                        detail.setRecommendInfo(model);
                        recommendContentSer.save(detail);
                    }
                }
                RecommendContentDTO contentDTO = new RecommendContentDTO();
                contentDTO.getConditions().add(Restrict.eq("recommendInfo.id", to.getId()));
                List<RecommendContent> contents = recommendContentSer.findByCis(contentDTO);
                Set<RecommendContent> contentSet = new HashSet<RecommendContent>();
                contentSet.addAll(contents);
                model.setContentSet(contentSet);
                model.setRecommendRequire(recommendRequire);
                super.update(model);
                return BeanTransform.copyProperties(to, RecommendInfoBO.class);
            } else {
                throw new SerException("非法Id,推荐信息对象不能为空");
            }
        } else {
            throw new SerException("非法推荐要求Id,推荐要求对象不能为空!");
        }
    }

    @Override
    @Transactional
    public void delete(String id) throws SerException {
        checkSeeIdentity();
        if (null != id) {
            RecommendContentDTO contentDTO = new RecommendContentDTO();
            contentDTO.getConditions().add(Restrict.eq("recommendInfo.id", id));
            List<RecommendContent> contents = recommendContentSer.findByCis(contentDTO);
            if (contents != null && contents.size() > 0) {
                for (RecommendContent recommendContent : contents) {
                    StringBuilder sql = new StringBuilder("DELETE FROM");
                    sql.append(" interiorrecommend_recommendcontent WHERE id = '" + recommendContent.getId() + "'");
                    recommendContentSer.executeSql(sql.toString());
                }
            }
            AwardInfoDTO awardInfoDTO = new AwardInfoDTO();
            awardInfoDTO.getConditions().add(Restrict.eq("recommendInfo.id", id));
            List<AwardInfo> awardInfos = awardInfoSer.findByCis(awardInfoDTO);
            if (awardInfos != null && awardInfos.size() > 0) {
                for (AwardInfo awardInfo : awardInfos) {
                    StringBuilder sql = new StringBuilder("DELETE FROM");
                    sql.append(" interiorrecommend_awardinfo WHERE id = '" + awardInfo.getId() + "'");
                    awardInfoSer.executeSql(sql.toString());
                }
            }
            super.remove(id);
        }
    }


    @Override
    public List<RecommendInfoBO> pageList(RecommendInfoDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        List<RecommendInfo> list = super.findByPage(dto);
        if (!CollectionUtils.isEmpty(list)) {
            List<RecommendInfoBO> boList = new ArrayList<RecommendInfoBO>();
            for (RecommendInfo model : list) {
                RecommendRequire recommendRequire = model.getRecommendRequire();
                RecommendInfoBO bo = BeanTransform.copyProperties(model, RecommendInfoBO.class);
                bo.setOpenTime(DateUtil.dateToString(recommendRequire.getRecommendScheme().getOpenTime()));
                bo.setCloseTime(DateUtil.dateToString(recommendRequire.getRecommendScheme().getCloseTime()));
                bo.setPurpose(recommendRequire.getRecommendScheme().getPurpose());
                bo.setTypeName(recommendRequire.getRecommendType().getTypeName());
                boList.add(bo);
            }
            return boList;
        } else {
            return null;
        }
    }

    @Override
    public void acceptAudit(String id, String reason, Boolean accept) throws SerException {
        checkAddIdentity();
        RecommendInfo model = super.findById(id);
        if (model != null) {
            model.setReason(reason);
            model.setAccept(accept);
            super.update(model);
        } else {
            throw new SerException("非法Id,推荐信息对象不能为空");
        }
    }

    @Override
    public void conformAudit(String id, Boolean conform) throws SerException {
        checkAddIdentity();
        RecommendInfo model = super.findById(id);
        if (model != null) {
            if (model.getAccept() != Boolean.TRUE) {
                throw new SerException("推荐信息审核未通过,无法进行奖励审核!");
            }
            if (model.getConform() == Boolean.FALSE) {
                throw new SerException("已经审核不符合!");
            }
            model.setConform(conform);
            super.update(model);
            if (conform) {
                AwardInfo awardInfo = new AwardInfo();
                awardInfo.setRecommendInfo(model);
                awardInfoSer.save(awardInfo);
            }
        } else {
            throw new SerException("非法Id,推荐信息对象不能为空");
        }
    }

    @Override
    public List<RecommendInfoBO> awardlist() throws SerException {
        checkSeeIdentity();
        RecommendInfoDTO dto = new RecommendInfoDTO();
        dto.getConditions().add(Restrict.eq("accept", 1));
        dto.getConditions().add(Restrict.eq("conform",1));
        List<RecommendInfo> list = super.findByCis(dto);
        List<RecommendInfoBO> boList = BeanTransform.copyProperties(list, RecommendInfoBO.class);
        if (boList != null && boList.size() > 0) {
            for (RecommendInfoBO info : boList) {
                RecommendRequire require = recommendRequireSer.findById(info.getRequireId());
                AwardStandardDTO standardDTO = new AwardStandardDTO();
                standardDTO.getConditions().add(Restrict.eq("recommendRequire.id", require.getId()));
                List<AwardStandard> standards = awardStandardSer.findByCis(standardDTO);
                info.setAwardType(standards.get(0).getAwardType());
                info.setAwardAmount(standards.get(0).getAwardAmount());
                info.setAwardContent(standards.get(0).getAwardContent());
                info.setAwardSendWay(standards.get(0).getAwardSendWay());
                AwardInfoDTO awardInfoDTO = new AwardInfoDTO();
                awardInfoDTO.getConditions().add(Restrict.eq("recommendInfo.id", info.getId()));
                List<AwardInfo> awardInfos = awardInfoSer.findByCis(awardInfoDTO);
                awardInfos.forEach(award -> {
                    if (award.getAwardTime() != null) {
                        info.setAwardTime(award.getAwardTime().toString());
                    }
                    if (award.getGetAward() != null) {
                        info.setGetAward(award.getGetAward());
                    }
                });

            }
        }
        return boList;
    }

    @Override
    public List<String[]> findRequire() throws SerException {
        List<RecommendRequire> requireList = recommendRequireSer.findAll();
        List<String[]> list = new ArrayList<>();
        for (RecommendRequire recommendRequire : requireList) {
            String[] require = {recommendRequire.getRecommendType().getTypeName(), recommendRequire.getId()};
            list.add(require);
        }
        return list;
    }

    @Override
    public RecommendInfoBO findOne(String id) throws SerException {
        if (null != id) {
            RecommendInfo info = super.findById(id);
            RecommendInfoBO bo = BeanTransform.copyProperties(info, RecommendInfoBO.class);
            bo.setRequireId(info.getRecommendRequire().getId());
            RecommendContentDTO dto = new RecommendContentDTO();
            dto.getConditions().add(Restrict.eq("recommendInfo.id", id));
            List<RecommendContent> content = recommendContentSer.findByCis(dto);
            List<RecommendContentBO> contentBOS = BeanTransform.copyProperties(content, RecommendContentBO.class);
            bo.setContentList(contentBOS);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public Long count(RecommendInfoDTO dto) throws SerException {
        return super.count(dto);
    }
}