package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.interiorrecommend.bo.OldRecommendContentBO;
import com.bjike.goddess.interiorrecommend.bo.OldRecommendInfoBO;
import com.bjike.goddess.interiorrecommend.dto.OldAwardInfoDTO;
import com.bjike.goddess.interiorrecommend.dto.OldAwardStandardDTO;
import com.bjike.goddess.interiorrecommend.dto.OldRecommendContentDTO;
import com.bjike.goddess.interiorrecommend.dto.OldRecommendInfoDTO;
import com.bjike.goddess.interiorrecommend.entity.*;
import com.bjike.goddess.interiorrecommend.enums.GuideAddrStatus;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.RecommendInfoTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
public class OldRecommendInfoSerImpl extends ServiceImpl<OldRecommendInfo, OldRecommendInfoDTO> implements OldRecommendInfoSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private OldAwardInfoSer awardInfoSer;
    @Autowired
    private OldRecommendRequireSer recommendRequireSer;
    @Autowired
    private OldRecommendAssessDetailSer recommendAssessDetailSer;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private OldRecommendContentSer recommendContentSer;

    @Autowired
    private OldAwardStandardSer awardStandardSer;

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
    public OldRecommendInfoBO insertModel(RecommendInfoTO to) throws SerException {
        checkSeeIdentity();
        UserBO userBO = userAPI.currentUser();
        OldRecommendRequire recommendRequire = recommendRequireSer.findById(to.getRequireId());
        if (recommendRequire != null) {
            OldRecommendInfo model = BeanTransform.copyProperties(to, OldRecommendInfo.class, true);
            //保存推荐考核内容
            if (!CollectionUtils.isEmpty(to.getContentList())) {
                List<OldRecommendContent> contentList = BeanTransform.copyProperties(to.getContentList(), OldRecommendContent.class);
                model.setRecommendUser(userBO.getUsername());
                model.setRecommendRequire(recommendRequire);
                super.save(model);
                contentList.forEach(entity -> {
                    entity.setRecommendInfo(model);
                });
                recommendContentSer.save(contentList);
                to.setId(model.getId());
                return BeanTransform.copyProperties(to, OldRecommendInfoBO.class);
            } else {
                throw new SerException("推荐考核内容不能为空!");
            }
        } else {
            throw new SerException("非法推荐要求Id,推荐要求对象不能为空!");
        }
    }

    @Override
    public OldRecommendInfoBO updateModel(RecommendInfoTO to) throws SerException {
        checkSeeIdentity();
        OldRecommendRequire recommendRequire = recommendRequireSer.findById(to.getRequireId());
        if (recommendRequire != null) {
            OldRecommendInfo model = super.findById(to.getId());
            //保存推荐考核内容
            if (!CollectionUtils.isEmpty(to.getContentList())) {
                Set<OldRecommendContent> detailSet = new HashSet<OldRecommendContent>();
                List<OldRecommendContent> detailList = BeanTransform.copyProperties(to.getContentList(), OldRecommendContent.class);
                detailSet.addAll(detailList);
                for (OldRecommendContent detail : detailSet) {
                    if (!StringUtils.isEmpty(detail.getId())) {
                        OldRecommendContent content = recommendContentSer.findById(detail.getId());
                        detail.setCreateTime(content.getCreateTime());
                        detail.setModifyTime(LocalDateTime.now());
                        detail.setRecommendInfo(model);
                    } else {
                        detail.setRecommendInfo(model);
                        recommendContentSer.save(detail);
                    }
                }
                OldRecommendContentDTO contentDTO = new OldRecommendContentDTO();
                contentDTO.getConditions().add(Restrict.eq("recommendInfo.id", to.getId()));
                List<OldRecommendContent> contents = recommendContentSer.findByCis(contentDTO);
                Set<OldRecommendContent> contentSet = new HashSet<OldRecommendContent>();
                contentSet.addAll(contents);
                model.setContentSet(contentSet);
                model.setRecommendRequire(recommendRequire);
                super.update(model);
                return BeanTransform.copyProperties(to, OldRecommendInfoBO.class);
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
            OldRecommendContentDTO contentDTO = new OldRecommendContentDTO();
            contentDTO.getConditions().add(Restrict.eq("recommendInfo.id", id));
            List<OldRecommendContent> contents = recommendContentSer.findByCis(contentDTO);
            if (contents != null && contents.size() > 0) {
                for (OldRecommendContent recommendContent : contents) {
                    StringBuilder sql = new StringBuilder("DELETE FROM");
                    sql.append(" interiorrecommend_recommendcontent WHERE id = '" + recommendContent.getId() + "'");
                    recommendContentSer.executeSql(sql.toString());
                }
            }
            OldAwardInfoDTO awardInfoDTO = new OldAwardInfoDTO();
            awardInfoDTO.getConditions().add(Restrict.eq("recommendInfo.id", id));
            List<OldAwardInfo> awardInfos = awardInfoSer.findByCis(awardInfoDTO);
            if (awardInfos != null && awardInfos.size() > 0) {
                for (OldAwardInfo awardInfo : awardInfos) {
                    StringBuilder sql = new StringBuilder("DELETE FROM");
                    sql.append(" interiorrecommend_awardinfo WHERE id = '" + awardInfo.getId() + "'");
                    awardInfoSer.executeSql(sql.toString());
                }
            }
            super.remove(id);
        }
    }


    @Override
    public List<OldRecommendInfoBO> pageList(OldRecommendInfoDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        List<OldRecommendInfo> list = super.findByPage(dto);
        if (!CollectionUtils.isEmpty(list)) {
            List<OldRecommendInfoBO> boList = new ArrayList<OldRecommendInfoBO>();
            for (OldRecommendInfo model : list) {
                OldRecommendRequire recommendRequire = model.getRecommendRequire();
                OldRecommendInfoBO bo = BeanTransform.copyProperties(model, OldRecommendInfoBO.class);
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
        OldRecommendInfo model = super.findById(id);
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
        OldRecommendInfo model = super.findById(id);
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
                OldAwardInfo awardInfo = new OldAwardInfo();
                awardInfo.setRecommendInfo(model);
                awardInfoSer.save(awardInfo);
            }
        } else {
            throw new SerException("非法Id,推荐信息对象不能为空");
        }
    }

    @Override
    public List<OldRecommendInfoBO> awardlist() throws SerException {
        checkSeeIdentity();
        OldRecommendInfoDTO dto = new OldRecommendInfoDTO();
        dto.getConditions().add(Restrict.eq("accept", 1));
        dto.getConditions().add(Restrict.eq("conform",1));
        List<OldRecommendInfo> list = super.findByCis(dto);
        List<OldRecommendInfoBO> boList = BeanTransform.copyProperties(list, OldRecommendInfoBO.class);
        if (boList != null && boList.size() > 0) {
            for (OldRecommendInfoBO info : boList) {
                OldRecommendRequire require = recommendRequireSer.findById(info.getRequireId());
                OldAwardStandardDTO standardDTO = new OldAwardStandardDTO();
                standardDTO.getConditions().add(Restrict.eq("recommendRequire.id", require.getId()));
                List<OldAwardStandard> standards = awardStandardSer.findByCis(standardDTO);
                info.setAwardType(standards.get(0).getAwardType());
                info.setAwardAmount(standards.get(0).getAwardAmount());
                info.setAwardContent(standards.get(0).getAwardContent());
                info.setAwardSendWay(standards.get(0).getAwardSendWay());
                OldAwardInfoDTO awardInfoDTO = new OldAwardInfoDTO();
                awardInfoDTO.getConditions().add(Restrict.eq("recommendInfo.id", info.getId()));
                List<OldAwardInfo> awardInfos = awardInfoSer.findByCis(awardInfoDTO);
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
        List<OldRecommendRequire> requireList = recommendRequireSer.findAll();
        List<String[]> list = new ArrayList<>();
        for (OldRecommendRequire recommendRequire : requireList) {
            String[] require = {recommendRequire.getRecommendType().getTypeName(), recommendRequire.getId()};
            list.add(require);
        }
        return list;
    }

    @Override
    public OldRecommendInfoBO findOne(String id) throws SerException {
        if (null != id) {
            OldRecommendInfo info = super.findById(id);
            OldRecommendInfoBO bo = BeanTransform.copyProperties(info, OldRecommendInfoBO.class);
            bo.setRequireId(info.getRecommendRequire().getId());
            OldRecommendContentDTO dto = new OldRecommendContentDTO();
            dto.getConditions().add(Restrict.eq("recommendInfo.id", id));
            List<OldRecommendContent> content = recommendContentSer.findByCis(dto);
            List<OldRecommendContentBO> contentBOS = BeanTransform.copyProperties(content, OldRecommendContentBO.class);
            bo.setContentList(contentBOS);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public Long count(OldRecommendInfoDTO dto) throws SerException {
        return super.count(dto);
    }
}