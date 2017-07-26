package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.interiorrecommend.bo.RecommendAssessDetailBO;
import com.bjike.goddess.interiorrecommend.bo.RecommendRequireBO;
import com.bjike.goddess.interiorrecommend.dto.RecommendRequireDTO;
import com.bjike.goddess.interiorrecommend.entity.RecommendAssessDetail;
import com.bjike.goddess.interiorrecommend.entity.RecommendRequire;
import com.bjike.goddess.interiorrecommend.entity.RecommendScheme;
import com.bjike.goddess.interiorrecommend.entity.RecommendType;
import com.bjike.goddess.interiorrecommend.enums.GuideAddrStatus;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.RecommendRequireTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
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
    public RecommendRequireBO insertModel(RecommendRequireTO to) throws SerException {
        checkAddIdentity();

        RecommendScheme recommendScheme = recommendSchemeSer.findById(to.getRecommendSchemeId());
        RecommendType recommendType = recommendTypeSer.findById(to.getRecommendTypeId());
        if (recommendScheme != null) {
            if (recommendType != null) {
                RecommendRequire model = BeanTransform.copyProperties(to, RecommendRequire.class, true);
                //保存推荐考核内容
                if (!CollectionUtils.isEmpty(to.getAssessDetailList())) {
                    Set<RecommendAssessDetail> detailSet = new HashSet<RecommendAssessDetail>();
                    List<RecommendAssessDetail> detailList = BeanTransform.copyProperties(to.getAssessDetailList(), RecommendAssessDetail.class);
                    detailSet.addAll(detailList);
                    for (RecommendAssessDetail detail : detailSet) {
                        detail.setRecommendRequire(model);
                    }
                    model.setDetailSet(detailSet);
                    model.setRecommendType(recommendType);
                    model.setRecommendScheme(recommendScheme);
                    super.save(model);
                    to.setId(model.getId());
                    return BeanTransform.copyProperties(to, RecommendRequireBO.class);
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
                    if (!CollectionUtils.isEmpty(to.getAssessDetailList())) {
                        Set<RecommendAssessDetail> detailSet = new HashSet<RecommendAssessDetail>();
                        List<RecommendAssessDetail> detailList = BeanTransform.copyProperties(to.getAssessDetailList(), RecommendAssessDetail.class);
                        detailSet.addAll(detailList);
                        for (RecommendAssessDetail detail : detailSet) {
                            if (!StringUtils.isEmpty(detail.getId())) {
                                RecommendAssessDetail assessDetail = recommendAssessDetailSer.findById(detail.getId());
                                detail.setCreateTime(assessDetail.getCreateTime());
                                detail.setModifyTime(assessDetail.getModifyTime());
                                detail.setRecommendRequire(model);
                            }
                        }

                        model.setDetailSet(detailSet);
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
    public void delete(String id) throws SerException {
        checkAddIdentity();
        RecommendRequire model = super.findById(id);
        if (model != null) {
            super.remove(id);
        } else {
            throw new SerException("非法Id,推荐要求对象不能为空!");
        }
    }
}