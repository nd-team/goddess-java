package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.bo.OldRecommendTypeBO;
import com.bjike.goddess.interiorrecommend.dto.*;
import com.bjike.goddess.interiorrecommend.entity.*;
import com.bjike.goddess.interiorrecommend.enums.GuideAddrStatus;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.OldRecommendTypeTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 推荐类型设定业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 02:10 ]
 * @Description: [ 推荐类型设定业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "interiorrecommendSerCache")
@Service
public class OldRecommendTypeSerImpl extends ServiceImpl<OldRecommendType, OldRecommendTypeDTO> implements OldRecommendTypeSer {

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private OldRecommendRequireSer recommendRequireSer;

    @Autowired
    private OldRecommendInfoSer recommendInfoSer;

    @Autowired
    private OldRecommendContentSer recommendContentSer;

    @Autowired
    private OldAwardInfoSer awardInfoSer;

    @Autowired
    private OldRecommendAssessDetailSer recommendAssessDetailSer;

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
            flag = cusPermissionSer.getCusPermission("2");
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
    @Transactional(rollbackFor = SerException.class)
    public OldRecommendTypeBO insertModel(OldRecommendTypeTO to) throws SerException {
        checkAddIdentity();
        OldRecommendType recommendType = isExist(to.getTypeName());
        if (recommendType == null) {
            OldRecommendType model = BeanTransform.copyProperties(to, OldRecommendType.class, true);
            model.setCreateUser(userAPI.currentUser().getUsername());
            super.save(model);
            to.setId(model.getId());
            return BeanTransform.copyProperties(to, OldRecommendTypeBO.class);
        } else {
            throw new SerException("该推荐类型名称已存在!");
        }
    }

    public OldRecommendType isExist(String typeName) throws SerException {
        OldRecommendTypeDTO dto = new OldRecommendTypeDTO();
        dto.getConditions().add(Restrict.eq("typeName", typeName));
        List<OldRecommendType> list = super.findByCis(dto);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public OldRecommendTypeBO updateModel(OldRecommendTypeTO to) throws SerException {
        checkAddIdentity();
        if (!StringUtils.isEmpty(to.getId())) {
            OldRecommendType model = super.findById(to.getId());
            if (model != null) {
                OldRecommendType recommendType = isExist(to.getTypeName());
                if (recommendType != null && recommendType.getId().equals(model.getId())) {
                    BeanTransform.copyProperties(to, model, true);
                    model.setUpdateUser(userAPI.currentUser().getUsername());
                    model.setModifyTime(LocalDateTime.now());
                    super.update(model);
                }else{
                    throw new SerException("该推荐类型名称已存在!");
                }
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, OldRecommendTypeBO.class);
    }

    @Override
    public List<OldRecommendTypeBO> pageList(OldRecommendTypeDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        List<OldRecommendType> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, OldRecommendTypeBO.class);
    }


    //由于关联了很多的表,所以不能直接调用remove方法来删除,我之前试过了用remove的方法删除其他的表,发现报错,最后只能用sql语句这简单暴力的方法——————江载旋　2017-08-09 22:10
    @Override
    @Transactional
    public void delete(String id) throws SerException {
        if(null != id){
            OldRecommendRequireDTO dto = new OldRecommendRequireDTO();
            dto.getConditions().add(Restrict.eq("recommendType.id",id));
            List<OldRecommendRequire> list = recommendRequireSer.findByCis(dto);
            if(list != null && list.size() >0) {
                for (OldRecommendRequire recommendRequire : list) {
                    OldRecommendInfoDTO infoDTO = new OldRecommendInfoDTO();
                    infoDTO.getConditions().add(Restrict.eq("recommendRequire.id", recommendRequire.getId()));
                    List<OldRecommendInfo> info = recommendInfoSer.findByCis(infoDTO);
                    if(info != null && info.size() > 0) {
                        for (OldRecommendInfo recommendInfo : info) {
                            OldRecommendContentDTO contentDTO = new OldRecommendContentDTO();
                            contentDTO.getConditions().add(Restrict.eq("recommendInfo.id", recommendInfo.getId()));
                            List<OldRecommendContent> contents = recommendContentSer.findByCis(contentDTO);
                            if(contents != null && contents.size() > 0) {
                                for (OldRecommendContent recommendContent : contents) {
                                    StringBuilder sql = new StringBuilder("DELETE FROM");
                                    sql.append(" interiorrecommend_recommendcontent WHERE id = '" + recommendContent.getId()+"'");
                                    recommendContentSer.executeSql(sql.toString());
                                }
                            }
                            OldAwardInfoDTO awardInfoDTO = new OldAwardInfoDTO();
                            awardInfoDTO.getConditions().add(Restrict.eq("recommendInfo.id", recommendInfo.getId()));
                            List<OldAwardInfo> awardInfos = awardInfoSer.findByCis(awardInfoDTO);
                            if(awardInfos != null && awardInfos.size() >0 ) {
                                for (OldAwardInfo awardInfo : awardInfos) {
                                    StringBuilder sql = new StringBuilder("DELETE FROM");
                                    sql.append(" interiorrecommend_awardinfo WHERE id = '" + awardInfo.getId()+"'");
                                    awardInfoSer.executeSql(sql.toString());
                                }
                            }
                            recommendInfoSer.remove(recommendInfo.getId());
                        }
                    }
                    OldRecommendAssessDetailDTO detailDTO = new OldRecommendAssessDetailDTO();
                    detailDTO.getConditions().add(Restrict.eq("recommendRequire.id", recommendRequire.getId()));
                    List<OldRecommendAssessDetail> details = recommendAssessDetailSer.findByCis(detailDTO);
                    if(details != null && details.size() > 0) {
                        for (OldRecommendAssessDetail detail : details) {
                            StringBuilder sql = new StringBuilder("DELETE FROM");
                            sql.append(" interiorrecommend_recommendassessdetail WHERE id = '" + detail.getId()+"'");
                            recommendAssessDetailSer.executeSql(sql.toString());
                        }
                    }
                    OldAwardStandardDTO standardDTO = new OldAwardStandardDTO();
                    standardDTO.getConditions().add(Restrict.eq("recommendRequire.id", recommendRequire.getId()));
                    List<OldAwardStandard> standards = awardStandardSer.findByCis(standardDTO);
                    if(standards != null && standards.size() > 0) {
                        for (OldAwardStandard awardStandard : standards) {
                            StringBuilder sql = new StringBuilder("DELETE FROM");
                            sql.append(" interiorrecommend_awardstandard WHERE id = '" + awardStandard.getId()+"'");
                            awardStandardSer.executeSql(sql.toString());
                        }
                    }
                    StringBuilder sql = new StringBuilder("DELETE FROM");
                    sql.append(" interiorrecommend_recommendrequire WHERE id = '" + recommendRequire.getId()+"'");
                    recommendRequireSer.executeSql(sql.toString());
                }
            }
            super.remove(id);
        }else {
            throw new SerException("id不能为空");
        }
    }
}