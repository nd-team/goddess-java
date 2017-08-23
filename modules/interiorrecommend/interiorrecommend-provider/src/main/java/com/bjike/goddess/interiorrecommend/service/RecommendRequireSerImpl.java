package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.interiorrecommend.bo.*;
import com.bjike.goddess.interiorrecommend.dto.*;
import com.bjike.goddess.interiorrecommend.entity.*;
import com.bjike.goddess.interiorrecommend.enums.GuideAddrStatus;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.RecommendRequireTO;
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
 * 推荐要求业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 03:29 ]
 * @Description: [ 推荐要求业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "interiorrecommendSerCache")
@Service
public class RecommendRequireSerImpl extends ServiceImpl<RecommendRequire, RecommendRequireDTO> implements RecommendRequireSer {

    @Autowired
    private RecommendSchemeSer recommendSchemeSer;
    @Autowired
    private RecommendTypeSer recommendTypeSer;
    @Autowired
    private RecommendAssessDetailSer recommendAssessDetailSer;

    @Autowired
    private RecommendContentSer recommendContentSer;

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private RecommendInfoSer recommendInfoSer;

    @Autowired
    private AwardStandardSer awardStandardSer;

    @Autowired
    private AwardInfoSer awardInfoSer;


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
    public RecommendRequireBO insertModel(RecommendRequireTO to) throws SerException {
        checkAddIdentity();

        RecommendScheme recommendScheme = recommendSchemeSer.findById(to.getRecommendSchemeId());
        RecommendType recommendType = recommendTypeSer.findById(to.getRecommendTypeId());
        if (recommendScheme != null) {
            if (recommendType != null) {
                RecommendRequire model = BeanTransform.copyProperties(to, RecommendRequire.class, true);
                model.setRecommendScheme(recommendScheme);
                model.setRecommendType(recommendType);
                //保存推荐考核内容
                if (!CollectionUtils.isEmpty(to.getDetailList())) {
                    Set<RecommendAssessDetail> detailSet = new HashSet<RecommendAssessDetail>();
                    List<RecommendAssessDetail> detailList = BeanTransform.copyProperties(to.getDetailList(), RecommendAssessDetail.class);
                    detailSet.addAll(detailList);
                    for (RecommendAssessDetail detail : detailSet) {
                        detail.setRecommendRequire(model);
                    }
                    model.setDetailSet(detailSet);
                    model.setRecommendType(recommendType);
                    model.setRecommendScheme(recommendScheme);
                    super.save(model);
                    to.setId(model.getId());
                    return BeanTransform.copyProperties(model, RecommendRequireBO.class);
                } else {
                    throw new SerException("推荐考核内容不能为空!");
                }
            } else {
                throw new SerException("非法推荐类型Id,推荐方案对象不能为空!");
            }
        } else {
            throw new SerException("非法推荐方案Id,推荐方案对象不能为空!");
        }
    }


    @Override
    public RecommendRequireBO updateModel(RecommendRequireTO to) throws SerException {
        checkAddIdentity();
        RecommendScheme recommendScheme = recommendSchemeSer.findById(to.getRecommendSchemeId());
        RecommendType recommendType = recommendTypeSer.findById(to.getRecommendTypeId());
        if (recommendScheme != null) {
            if (recommendType != null) {
                RecommendRequire model = super.findById(to.getId());
                if (model != null) {
                    BeanTransform.copyProperties(to, model, true);
                    model.setModifyTime(LocalDateTime.now());

                    //保存推荐考核内容
                    if (!CollectionUtils.isEmpty(to.getDetailList())) {
                        Set<RecommendAssessDetail> detailSet = new HashSet<RecommendAssessDetail>();
                        List<RecommendAssessDetail> detailList = BeanTransform.copyProperties(to.getDetailList(), RecommendAssessDetail.class);
                        detailSet.addAll(detailList);
                        for (RecommendAssessDetail detail : detailSet) {
                            if (!StringUtils.isEmpty(detail.getId())) {
                                RecommendAssessDetail assessDetail = recommendAssessDetailSer.findById(detail.getId());
                                detail.setCreateTime(assessDetail.getCreateTime());
                                detail.setModifyTime(LocalDateTime.now());
                                detail.setRecommendRequire(model);
                            }else{
                                detail.setRecommendRequire(model);
                                recommendAssessDetailSer.save(detail);
                            }
                        }
                        RecommendAssessDetailDTO detailDTO = new RecommendAssessDetailDTO();
                        detailDTO.getConditions().add(Restrict.eq("recommendRequire.id",to.getId()));
                        List<RecommendAssessDetail> details = recommendAssessDetailSer.findByCis(detailDTO);
                        Set<RecommendAssessDetail> assessDetailSet = new HashSet<RecommendAssessDetail>();
                        assessDetailSet.addAll(details);
                        model.setDetailSet(assessDetailSet);
                        model.setRecommendType(recommendType);
                        model.setRecommendScheme(recommendScheme);
                        super.update(model);
                        return BeanTransform.copyProperties(to, RecommendRequireBO.class);
                    } else {
                        throw new SerException("推荐考核内容不能为空!");
                    }
                } else {
                    throw new SerException("非法Id,推荐要求对象不能为空!");
                }
            } else {
                throw new SerException("非法推荐类型Id,推荐方案对象不能为空!");
            }
        } else {
            throw new SerException("非法推荐方案Id,推荐方案对象不能为空!");
        }
    }

    @Override
    public List<RecommendRequireBO> pageList(RecommendRequireDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        List<RecommendRequire> list = super.findByPage(dto);
        if (!CollectionUtils.isEmpty(list)) {
            List<RecommendRequireBO> boList = new ArrayList<RecommendRequireBO>();
            for (RecommendRequire model : list) {
                RecommendRequireBO bo = BeanTransform.copyProperties(model, RecommendRequireBO.class);
                bo.setOpenTime(DateUtil.dateToString(model.getRecommendScheme().getOpenTime()));
                bo.setCloseTime(DateUtil.dateToString(model.getRecommendScheme().getCloseTime()));
                bo.setRecommendTypeName(model.getRecommendType().getTypeName());
                bo.setDetailList(BeanTransform.copyProperties(model.getDetailSet(), RecommendAssessDetailBO.class));
                boList.add(bo);
            }
            return boList;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(String id) throws SerException {
        checkAddIdentity();
        if (id != null) {
            RecommendInfoDTO infoDTO = new RecommendInfoDTO();
            infoDTO.getConditions().add(Restrict.eq("recommendRequire.id", id));
            List<RecommendInfo> info = recommendInfoSer.findByCis(infoDTO);
            if (info != null && info.size() > 0) {
                for (RecommendInfo recommendInfo : info) {
                    RecommendContentDTO contentDTO = new RecommendContentDTO();
                    contentDTO.getConditions().add(Restrict.eq("recommendInfo.id", recommendInfo.getId()));
                    List<RecommendContent> contents = recommendContentSer.findByCis(contentDTO);
                    if (contents != null && contents.size() > 0) {
                        for (RecommendContent recommendContent : contents) {
                            StringBuilder sql = new StringBuilder("DELETE FROM");
                            sql.append(" interiorrecommend_recommendcontent WHERE id = '" + recommendContent.getId() + "'");
                            recommendContentSer.executeSql(sql.toString());
                        }
                    }
                    AwardInfoDTO awardInfoDTO = new AwardInfoDTO();
                    awardInfoDTO.getConditions().add(Restrict.eq("recommendInfo.id", recommendInfo.getId()));
                    List<AwardInfo> awardInfos = awardInfoSer.findByCis(awardInfoDTO);
                    if (awardInfos != null && awardInfos.size() > 0) {
                        for (AwardInfo awardInfo : awardInfos) {
                            StringBuilder sql = new StringBuilder("DELETE FROM");
                            sql.append(" interiorrecommend_awardinfo WHERE id = '" + awardInfo.getId() + "'");
                            awardInfoSer.executeSql(sql.toString());
                        }
                    }
                    recommendInfoSer.remove(recommendInfo.getId());
                }
            }
            RecommendAssessDetailDTO detailDTO = new RecommendAssessDetailDTO();
            detailDTO.getConditions().add(Restrict.eq("recommendRequire.id", id));
            List<RecommendAssessDetail> details = recommendAssessDetailSer.findByCis(detailDTO);
            if (details != null && details.size() > 0) {
                for (RecommendAssessDetail detail : details) {
                    StringBuilder sql = new StringBuilder("DELETE FROM");
                    sql.append(" interiorrecommend_recommendassessdetail WHERE id = '" + detail.getId() + "'");
                    recommendAssessDetailSer.executeSql(sql.toString());
                }
            }
            AwardStandardDTO standardDTO = new AwardStandardDTO();
            standardDTO.getConditions().add(Restrict.eq("recommendRequire.id", id));
            List<AwardStandard> standards = awardStandardSer.findByCis(standardDTO);
            if (standards != null && standards.size() > 0) {
                for (AwardStandard awardStandard : standards) {
                    StringBuilder sql = new StringBuilder("DELETE FROM");
                    sql.append(" interiorrecommend_awardstandard WHERE id = '" + awardStandard.getId() + "'");
                    awardStandardSer.executeSql(sql.toString());
                }
            }
            StringBuilder sql = new StringBuilder("DELETE FROM");
            sql.append(" interiorrecommend_recommendrequire WHERE id = '" + id + "'");
            super.executeSql(sql.toString());
        }
    }


    @Override
    public List<RecommendSchemeBO> findRecommend() throws SerException {
        List<RecommendScheme> recommendSchemes = recommendSchemeSer.findAll();
        List<RecommendSchemeBO> recommendSchemeBOS = BeanTransform.copyProperties(recommendSchemes, RecommendSchemeBO.class);
        return recommendSchemeBOS;
    }

    @Override
    public List<RecommendTypeBO> findType() throws SerException {
        List<RecommendType> recommendTypes = recommendTypeSer.findAll();
        List<RecommendTypeBO> recommendTypeBOS = BeanTransform.copyProperties(recommendTypes, RecommendTypeBO.class);
        return recommendTypeBOS;
    }

    @Override
    public List<RecommendAssessDetailBO> findAssess() throws SerException {
        List<RecommendAssessDetail> list = recommendAssessDetailSer.findAll();
        List<RecommendAssessDetailBO> boList = BeanTransform.copyProperties(list, RecommendAssessDetailBO.class);
        return boList;
    }

    @Override
    public List<RecommendContentBO> findContent() throws SerException {
        List<RecommendContent> content = recommendContentSer.findAll();
        List<RecommendContentBO> contentBOS = BeanTransform.copyProperties(content, RecommendContentBO.class);
        return contentBOS;
    }

    @Override
    public RecommendRequireBO findOne(String id) throws SerException {
        RecommendRequire require = super.findById(id);
        RecommendAssessDetailDTO dto = new RecommendAssessDetailDTO();
        dto.getConditions().add(Restrict.eq("recommendRequire.id",require.getId()));
        List<RecommendAssessDetail> detail = recommendAssessDetailSer.findByCis(dto);
        List<RecommendAssessDetailBO> detailBO = BeanTransform.copyProperties(detail,RecommendAssessDetailBO.class);
        RecommendRequireBO recommendRequireBO = BeanTransform.copyProperties(require,RecommendRequireBO.class);
        recommendRequireBO.setDetailList(detailBO);
        return recommendRequireBO;
    }
}