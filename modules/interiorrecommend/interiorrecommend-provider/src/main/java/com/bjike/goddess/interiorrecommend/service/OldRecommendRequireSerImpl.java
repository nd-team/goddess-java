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
import com.bjike.goddess.interiorrecommend.to.OldRecommendRequireTO;
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
public class OldRecommendRequireSerImpl extends ServiceImpl<OldRecommendRequire, OldRecommendRequireDTO> implements OldRecommendRequireSer {

    @Autowired
    private RecommendSchemeSer recommendSchemeSer;
    @Autowired
    private OldRecommendTypeSer recommendTypeSer;
    @Autowired
    private OldRecommendAssessDetailSer recommendAssessDetailSer;

    @Autowired
    private OldRecommendContentSer recommendContentSer;

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private OldRecommendInfoSer recommendInfoSer;

    @Autowired
    private OldAwardStandardSer awardStandardSer;

    @Autowired
    private OldAwardInfoSer awardInfoSer;


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
            flag = cusPermissionSer.getCusPermission("2");
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
    public OldRecommendRequireBO insertModel(OldRecommendRequireTO to) throws SerException {
        checkAddIdentity();

        RecommendScheme recommendScheme = recommendSchemeSer.findById(to.getRecommendSchemeId());
        OldRecommendType recommendType = recommendTypeSer.findById(to.getRecommendTypeId());
        if (recommendScheme != null) {
            if (recommendType != null) {
                OldRecommendRequire model = BeanTransform.copyProperties(to, OldRecommendRequire.class, true);
                model.setRecommendScheme(recommendScheme);
                model.setRecommendType(recommendType);
                //保存推荐考核内容
                if (!CollectionUtils.isEmpty(to.getDetailList())) {
                    Set<OldRecommendAssessDetail> detailSet = new HashSet<OldRecommendAssessDetail>();
                    List<OldRecommendAssessDetail> detailList = BeanTransform.copyProperties(to.getDetailList(), OldRecommendAssessDetail.class);
                    detailSet.addAll(detailList);
                    for (OldRecommendAssessDetail detail : detailSet) {
                        detail.setRecommendRequire(model);
                    }
                    model.setDetailSet(detailSet);
                    model.setRecommendType(recommendType);
                    model.setRecommendScheme(recommendScheme);
                    super.save(model);
                    to.setId(model.getId());
                    return BeanTransform.copyProperties(model, OldRecommendRequireBO.class);
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
    public OldRecommendRequireBO updateModel(OldRecommendRequireTO to) throws SerException {
        checkAddIdentity();
        RecommendScheme recommendScheme = recommendSchemeSer.findById(to.getRecommendSchemeId());
        OldRecommendType recommendType = recommendTypeSer.findById(to.getRecommendTypeId());
        if (recommendScheme != null) {
            if (recommendType != null) {
                OldRecommendRequire model = super.findById(to.getId());
                if (model != null) {
                    BeanTransform.copyProperties(to, model, true);
                    model.setModifyTime(LocalDateTime.now());

                    //保存推荐考核内容
                    if (!CollectionUtils.isEmpty(to.getDetailList())) {
                        Set<OldRecommendAssessDetail> detailSet = new HashSet<OldRecommendAssessDetail>();
                        List<OldRecommendAssessDetail> detailList = BeanTransform.copyProperties(to.getDetailList(), OldRecommendAssessDetail.class);
                        detailSet.addAll(detailList);
                        for (OldRecommendAssessDetail detail : detailSet) {
                            if (!StringUtils.isEmpty(detail.getId())) {
                                OldRecommendAssessDetail assessDetail = recommendAssessDetailSer.findById(detail.getId());
                                detail.setCreateTime(assessDetail.getCreateTime());
                                detail.setModifyTime(LocalDateTime.now());
                                detail.setRecommendRequire(model);
                            }else{
                                detail.setRecommendRequire(model);
                                recommendAssessDetailSer.save(detail);
                            }
                        }
                        OldRecommendAssessDetailDTO detailDTO = new OldRecommendAssessDetailDTO();
                        detailDTO.getConditions().add(Restrict.eq("recommendRequire.id",to.getId()));
                        List<OldRecommendAssessDetail> details = recommendAssessDetailSer.findByCis(detailDTO);
                        Set<OldRecommendAssessDetail> assessDetailSet = new HashSet<OldRecommendAssessDetail>();
                        assessDetailSet.addAll(details);
                        model.setDetailSet(assessDetailSet);
                        model.setRecommendType(recommendType);
                        model.setRecommendScheme(recommendScheme);
                        super.update(model);
                        return BeanTransform.copyProperties(to, OldRecommendRequireBO.class);
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
    public List<OldRecommendRequireBO> pageList(OldRecommendRequireDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        List<OldRecommendRequire> list = super.findByPage(dto);
        if (!CollectionUtils.isEmpty(list)) {
            List<OldRecommendRequireBO> boList = new ArrayList<OldRecommendRequireBO>();
            for (OldRecommendRequire model : list) {
                OldRecommendRequireBO bo = BeanTransform.copyProperties(model, OldRecommendRequireBO.class);
                bo.setOpenTime(DateUtil.dateToString(model.getRecommendScheme().getOpenTime()));
                bo.setCloseTime(DateUtil.dateToString(model.getRecommendScheme().getCloseTime()));
                bo.setRecommendTypeName(model.getRecommendType().getTypeName());
                bo.setDetailList(BeanTransform.copyProperties(model.getDetailSet(), OldRecommendAssessDetailBO.class));
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
            OldRecommendInfoDTO infoDTO = new OldRecommendInfoDTO();
            infoDTO.getConditions().add(Restrict.eq("recommendRequire.id", id));
            List<OldRecommendInfo> info = recommendInfoSer.findByCis(infoDTO);
            if (info != null && info.size() > 0) {
                for (OldRecommendInfo recommendInfo : info) {
                    OldRecommendContentDTO contentDTO = new OldRecommendContentDTO();
                    contentDTO.getConditions().add(Restrict.eq("recommendInfo.id", recommendInfo.getId()));
                    List<OldRecommendContent> contents = recommendContentSer.findByCis(contentDTO);
                    if (contents != null && contents.size() > 0) {
                        for (OldRecommendContent recommendContent : contents) {
                            StringBuilder sql = new StringBuilder("DELETE FROM");
                            sql.append(" interiorrecommend_recommendcontent WHERE id = '" + recommendContent.getId() + "'");
                            recommendContentSer.executeSql(sql.toString());
                        }
                    }
                    OldAwardInfoDTO awardInfoDTO = new OldAwardInfoDTO();
                    awardInfoDTO.getConditions().add(Restrict.eq("recommendInfo.id", recommendInfo.getId()));
                    List<OldAwardInfo> awardInfos = awardInfoSer.findByCis(awardInfoDTO);
                    if (awardInfos != null && awardInfos.size() > 0) {
                        for (OldAwardInfo awardInfo : awardInfos) {
                            StringBuilder sql = new StringBuilder("DELETE FROM");
                            sql.append(" interiorrecommend_awardinfo WHERE id = '" + awardInfo.getId() + "'");
                            awardInfoSer.executeSql(sql.toString());
                        }
                    }
                    recommendInfoSer.remove(recommendInfo.getId());
                }
            }
            OldRecommendAssessDetailDTO detailDTO = new OldRecommendAssessDetailDTO();
            detailDTO.getConditions().add(Restrict.eq("recommendRequire.id", id));
            List<OldRecommendAssessDetail> details = recommendAssessDetailSer.findByCis(detailDTO);
            if (details != null && details.size() > 0) {
                for (OldRecommendAssessDetail detail : details) {
                    StringBuilder sql = new StringBuilder("DELETE FROM");
                    sql.append(" interiorrecommend_recommendassessdetail WHERE id = '" + detail.getId() + "'");
                    recommendAssessDetailSer.executeSql(sql.toString());
                }
            }
            OldAwardStandardDTO standardDTO = new OldAwardStandardDTO();
            standardDTO.getConditions().add(Restrict.eq("recommendRequire.id", id));
            List<OldAwardStandard> standards = awardStandardSer.findByCis(standardDTO);
            if (standards != null && standards.size() > 0) {
                for (OldAwardStandard awardStandard : standards) {
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
    public List<OldRecommendTypeBO> findType() throws SerException {
        List<OldRecommendType> recommendTypes = recommendTypeSer.findAll();
        List<OldRecommendTypeBO> recommendTypeBOS = BeanTransform.copyProperties(recommendTypes, OldRecommendTypeBO.class);
        return recommendTypeBOS;
    }

    @Override
    public List<OldRecommendAssessDetailBO> findAssess() throws SerException {
        List<OldRecommendAssessDetail> list = recommendAssessDetailSer.findAll();
        List<OldRecommendAssessDetailBO> boList = BeanTransform.copyProperties(list, OldRecommendAssessDetailBO.class);
        return boList;
    }

    @Override
    public List<OldRecommendContentBO> findContent() throws SerException {
        List<OldRecommendContent> content = recommendContentSer.findAll();
        List<OldRecommendContentBO> contentBOS = BeanTransform.copyProperties(content, OldRecommendContentBO.class);
        return contentBOS;
    }

    @Override
    public OldRecommendRequireBO findOne(String id) throws SerException {
        OldRecommendRequire require = super.findById(id);
        OldRecommendAssessDetailDTO dto = new OldRecommendAssessDetailDTO();
        dto.getConditions().add(Restrict.eq("recommendRequire.id",require.getId()));
        List<OldRecommendAssessDetail> detail = recommendAssessDetailSer.findByCis(dto);
        List<OldRecommendAssessDetailBO> detailBO = BeanTransform.copyProperties(detail,OldRecommendAssessDetailBO.class);
        OldRecommendRequireBO recommendRequireBO = BeanTransform.copyProperties(require,OldRecommendRequireBO.class);
        recommendRequireBO.setDetailList(detailBO);
        return recommendRequireBO;
    }
}