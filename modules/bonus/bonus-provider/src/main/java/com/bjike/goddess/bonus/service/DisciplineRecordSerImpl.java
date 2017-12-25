package com.bjike.goddess.bonus.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.bonus.bo.*;
import com.bjike.goddess.bonus.dto.DisciplineRecordDTO;
import com.bjike.goddess.bonus.entity.DisciplineRecord;
import com.bjike.goddess.bonus.enums.GuideAddrStatus;
import com.bjike.goddess.bonus.to.CollectFilterTO;
import com.bjike.goddess.bonus.to.DisciplineRecordTO;
import com.bjike.goddess.bonus.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.organize.bo.PositionUserDetailBO;
import com.bjike.goddess.organize.enums.WorkStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 奖罚记录业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-10 11:54 ]
 * @Description: [ 奖罚记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "bonusSerCache")
@Service
public class DisciplineRecordSerImpl extends ServiceImpl<DisciplineRecord, DisciplineRecordDTO> implements DisciplineRecordSer {

    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private ModuleAPI moduleAPI;

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
     * 调整(总经办)
     *
     * @throws SerException
     */
    private Boolean checkPosin() throws SerException {
        Boolean flag = true;
        String userToken = RpcTransmit.getUserToken();
        flag = cusPermissionSer.positCusPermission("2");
        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 调整(决策层)
     *
     * @throws SerException
     */
    private Boolean checkLeve() throws SerException {
        Boolean flag = true;
        String userToken = RpcTransmit.getUserToken();
        flag = cusPermissionSer.leveCusPermission("3");
        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 调整(规划模块)
     *
     * @throws SerException
     */
    private Boolean checkModule() throws SerException {
        Boolean flag = true;
        String userToken = RpcTransmit.getUserToken();
        flag = cusPermissionSer.getCusPermission("4");
        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 调整权限(总经办,决策层,规划模块)
     *
     * @throws SerException
     */
    private void checkAdjustPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        Boolean posinFlag = checkPosin();
        Boolean leveFlag = checkLeve();
        Boolean moduleFlag = checkModule();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            if (posinFlag || leveFlag || moduleFlag) {
                flag = true;
            }
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 发起奖励处罚(综合资源部)
     *
     * @throws SerException
     */
    private Boolean checkDetail() throws SerException {
        Boolean flag = true;
        String userToken = RpcTransmit.getUserToken();
        flag = cusPermissionSer.positCusPermission("5");
        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 发起奖励处罚(福利模块或规划模块)
     *
     * @throws SerException
     */
    private Boolean checkModulePlan() throws SerException {
        Boolean flag = true;
        String userToken = RpcTransmit.getUserToken();
        flag = cusPermissionSer.leveCusPermission("6");
        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 发起奖励处罚(总经办,决策层,规划模块)
     *
     * @throws SerException
     */
    private void checkInitiPerm() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        Boolean detailFlag = checkDetail();
        Boolean planFlag = checkModulePlan();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            if (detailFlag || planFlag) {
                flag = true;
            }
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员,没有该操作权限");
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

    /**
     * 调整权限(总经办,决策层,规划模块)
     */
    private Boolean guideAdjustIdentity() throws SerException {
        Boolean flag = false;
        Boolean posinFlag = checkPosin();
        Boolean leveFlag = checkLeve();
        Boolean moduleFlag = checkModule();
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            if (posinFlag || leveFlag || moduleFlag) {
                flag = true;
            }
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 发起奖励处罚(总经办,决策层,规划模块)
     */
    private Boolean guideInitiPerm() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        Boolean detailFlag = checkDetail();
        Boolean planFlag = checkModulePlan();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            if (detailFlag || planFlag) {
                flag = true;
            }
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 权限
     */
    private Boolean guideAllTrue() throws SerException {
        return true;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdjust = guideAdjustIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagIniti = guideInitiPerm();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagTrue = guideAllTrue();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagAdjust || flagIniti || flagTrue) {
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
                flag = guideIdentity() || guideAdjustIdentity();
                break;
            case ADD:
                flag = guideIdentity() || guideAdjustIdentity();
                break;
            case EDIT:
                flag = guideIdentity() || guideAdjustIdentity();
                break;
            case DELETE:
                flag = guideIdentity() || guideAdjustIdentity();
                break;
            case OPERS:
                flag = guideIdentity() || guideAdjustIdentity();
                break;
            case CLOSES:
                flag = guideIdentity() || guideAdjustIdentity();
                break;
            case SUMMARY:
                flag = guideInitiPerm();
                break;
            case PROJECTRANK:
                flag = guideInitiPerm();
                break;
            case PERSONRANK:
                flag = guideAllTrue();
                break;
            case JCLIST:
                flag = guideInitiPerm();
                break;
            case JCADD:
                flag = guideInitiPerm();
                break;
            case JCEDIT:
                flag = guideInitiPerm();
                break;
            case JCDELETE:
                flag = guideInitiPerm();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public DisciplineRecordBO save(DisciplineRecordTO to) throws SerException {
        checkDetail();
        UserBO user = userAPI.currentUser();
        DisciplineRecord entity = BeanTransform.copyProperties(to, DisciplineRecord.class, true);
        entity = this.checkEntity(entity);
        if (StringUtils.isBlank(entity.getLaunch()))
            entity.setLaunch(user.getUsername());
        if (StringUtils.isBlank(entity.getName()))
            entity.setName(" ");
        if (StringUtils.isBlank(entity.getProject())) {
            if (moduleAPI.isCheck("organize")) {
                List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.getPositionDetail(user.getUsername());
                if (!CollectionUtils.isEmpty(positionDetailBOs)) {
                    PositionDetailBO positionDetailBO = positionDetailBOs.get(0);
                    entity.setArea(positionDetailBO.getDepartmentName());
                }
            }
        }
        if (StringUtils.isBlank(entity.getArea())) {
            if (moduleAPI.isCheck("organize")) {
                List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.getPositionDetail(user.getUsername());
                if (!CollectionUtils.isEmpty(positionDetailBOs)) {
                    PositionDetailBO positionDetailBO = positionDetailBOs.get(0);
                    entity.setArea(positionDetailBO.getArea());
                }
            }
        }
        super.save(entity);
        return BeanTransform.copyProperties(entity, DisciplineRecordBO.class);
    }

    /**
     * 检测奖罚记录实体规范
     *
     * @param entity 奖罚记录实体数据
     * @return
     * @throws SerException
     */
    private DisciplineRecord checkEntity(DisciplineRecord entity) throws SerException {

        UserBO user = null;
        if (moduleAPI.isCheck("organize")) {
            List<UserBO> userBOList = positionDetailUserAPI.findUserListInOrgan();
            if (!CollectionUtils.isEmpty(userBOList)) {
                userBOList = userBOList.stream().filter(obj -> obj.getUsername().equals(entity.getUsername())).collect(Collectors.toList());
                user = userBOList.get(0);
            }

        }
//        UserBO user = userAPI.findByUsername(entity.getUsername());
        if (null == user)
            throw new SerException("该用户不存在");
        entity.setSerialNumber(user.getEmployeeNumber());
        PositionDetailUserBO detailBO = null;
        if (moduleAPI.isCheck("organize")) {
            detailBO = positionDetailUserAPI.findOneByUser(user.getId());
        }
        entity.setArea("");
        entity.setProject("");
        if (null != detailBO) {
            List<PositionUserDetailBO> positionUserDetailBOSList = detailBO.getDetailS();
            if (null != positionUserDetailBOSList) {
                for (PositionUserDetailBO p : positionUserDetailBOSList) {
                    if (WorkStatus.MAIN.equals(p.getWorkStatus())) {
                        for (String id : p.getPositionId().split(",")) {
                            if (moduleAPI.isCheck("organize")) {
                                PositionDetailBO position = positionDetailAPI.findBOById(id);
                                entity.setProject(entity.getProject() + "," + position.getDepartmentName());
                                entity.setArea(entity.getArea() + "," + position.getArea());
                            }
                        }
                    }
                }
            }
        }
        if (entity.getStatus()) {//检测奖罚分数填写是否符合规范 true 为奖励 false 为处罚
            if (entity.getBallot() < 0)
                entity.setBallot(-entity.getBallot());
        } else {
            if (entity.getBallot() > 0)
                entity.setBallot(-entity.getBallot());
        }
        if (entity.getOccurrence() == null)
            entity.setOccurrence(LocalDateTime.now());
        return entity;
    }

    @Override
    public DisciplineRecordBO update(DisciplineRecordTO to) throws SerException {
        checkDetail();
        UserBO user = userAPI.currentUser();
        if (StringUtils.isNotBlank(to.getId())) {
            DisciplineRecord entity = super.findById(to.getId());
            if (null == entity)
                throw new SerException("数据对象不能为空");
            if (!user.getUsername().equals(entity.getLaunch()))
                throw new SerException("不要修改他人的数据");
            BeanTransform.copyProperties(to, entity, true);
            entity = this.checkEntity(entity);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return BeanTransform.copyProperties(entity, DisciplineRecordBO.class);
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public DisciplineRecordBO delete(String id) throws SerException {
        checkDetail();
        UserBO user = userAPI.currentUser();
        DisciplineRecord entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        if (!user.getUsername().equals(entity.getLaunch()))
            throw new SerException("不要修改他人的数据");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, DisciplineRecordBO.class);
    }

    @Override
    public List<DisciplineRecordRankBO> projectRank(CollectFilterTO to, Boolean status) throws SerException {
        checkDetail();
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        dto.getSorts().add("project=asc");
        List<DisciplineRecord> list = this.getListByFilter(to, dto).stream()
                .filter(d -> d.getStatus() == status)
                .collect(Collectors.toList());
        List<DisciplineRecordRankBO> rankBOs = new ArrayList<>(0);
        String project = "";
        for (DisciplineRecord entity : list)
            if (!entity.getProject().equals(project)) {
                project = entity.getProject();
                DisciplineRecordRankBO rank = new DisciplineRecordRankBO();
                rank.setArea(entity.getArea());
                rank.setDepartment(entity.getProject());
                rank.setStart(to.getStart());
                rank.setEnd(to.getEnd());
                List<DisciplineRecord> countList = list.stream()
                        .filter(d -> d.getProject().equals(entity.getProject()))
                        .collect(Collectors.toList());
                rank.setFrequency(countList.size());
                rank.setTotal(countList.stream().mapToDouble(DisciplineRecord::getBallot).sum());
                rankBOs.add(rank);
            }
        if (status)
            rankBOs = rankBOs.stream()
                    .sorted(Comparator.comparing(DisciplineRecordRankBO::getTotal).reversed()
                            .thenComparing(DisciplineRecordRankBO::getFrequency))
                    .collect(Collectors.toList());
        else
            rankBOs = rankBOs.stream()
                    .sorted(Comparator.comparing(DisciplineRecordRankBO::getTotal)
                            .thenComparing(DisciplineRecordRankBO::getFrequency))
                    .collect(Collectors.toList());
        for (int i = 0, size = rankBOs.size(); i < size; )
            rankBOs.get(i).setRank(++i);
        return rankBOs;
    }

    @Override
    public List<DisciplineRecordRankBO> personalRank(CollectFilterTO to, Boolean status) throws SerException {
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        dto.getSorts().add("username=asc");
        List<DisciplineRecord> list = this.getListByFilter(to, dto).stream()
                .filter(d -> d.getStatus() == status)
                .collect(Collectors.toList());
        List<DisciplineRecordRankBO> rankBOs = new ArrayList<>(0);
        String username = "";
        for (DisciplineRecord entity : list)
            if (!entity.getUsername().equals(entity.getProject())) {
                DisciplineRecordRankBO rank = new DisciplineRecordRankBO();
                rank.setArea(entity.getArea());
                rank.setDepartment(entity.getProject());
                rank.setStart(to.getStart());
                rank.setEnd(to.getEnd());
                rank.setSerialNumber(entity.getSerialNumber());
                rank.setUsername(entity.getUsername());
                List<DisciplineRecord> countList = list.stream()
                        .filter(d -> d.getUsername().equals(entity.getUsername()))
                        .collect(Collectors.toList());
                rank.setFrequency(countList.size());
                rank.setTotal(countList.stream().mapToDouble(DisciplineRecord::getBallot).sum());
                rankBOs.add(rank);
            }
        if (status)
            rankBOs = rankBOs.stream()
                    .sorted(Comparator.comparing(DisciplineRecordRankBO::getTotal).reversed())
                    .collect(Collectors.toList());
        else
            rankBOs = rankBOs.stream()
                    .sorted(Comparator.comparing(DisciplineRecordRankBO::getTotal))
                    .collect(Collectors.toList());
        for (int i = 0, size = rankBOs.size(); i < size; )
            rankBOs.get(i).setRank(++i);
        return rankBOs;
    }

    @Override
    public List<DisciplineRecordDetailBO> disciplineDetailCollect(CollectFilterTO to) throws SerException {
        checkDetail();
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        dto.getSorts().add("occurrence=asc");
        dto.getSorts().add("username=asc");
        List<DisciplineRecord> list = this.getListByFilter(to, dto);
        List<DisciplineRecordDetailBO> detailBOs = new ArrayList<>(0);
        String occurrence = "", username = "";
        for (DisciplineRecord entity : list)
            if (!entity.getOccurrence().toLocalDate().toString().equals(occurrence) || !entity.getUsername().equals(username)) {
                occurrence = entity.getOccurrence().toLocalDate().toString();
                username = entity.getUsername();
                DisciplineRecordDetailBO detail = BeanTransform.copyProperties(entity, DisciplineRecordDetailBO.class);
                String time = occurrence;
                List<DisciplineRecord> pushList = list.stream()
                        .filter(d -> d.getOccurrence().toLocalDate().toString().equals(time)
                                && d.getUsername().equals(entity.getUsername())
                                && d.getStatus() == Boolean.FALSE)
                        .collect(Collectors.toList()), rewardList = list.stream()
                        .filter(d -> d.getOccurrence().toLocalDate().toString().equals(time)
                                && d.getUsername().equals(entity.getUsername())
                                && d.getStatus() == Boolean.TRUE)
                        .collect(Collectors.toList());
                detail.setOccurrence(occurrence);
                detail.setPush(pushList.size());
                detail.setPushTotal(pushList.stream().mapToDouble(DisciplineRecord::getBallot).sum());
                detail.setReward(rewardList.size());
                detail.setRewardTotal(rewardList.stream().mapToDouble(DisciplineRecord::getBallot).sum());
                detail.setTotal(detail.getPushTotal() + detail.getRewardTotal());
                detailBOs.add(detail);
            }
        return detailBOs;
    }

    @Override
    public List<DisciplineRecordQuantityBO> disciplineQuantityCollect(CollectFilterTO to) throws SerException {
        checkDetail();
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        dto.getSorts().add("area=asc");
        dto.getSorts().add("project=asc");
        List<DisciplineRecord> list = this.getListByFilter(to, dto);
        List<DisciplineRecordQuantityBO> quantityBOs = new ArrayList<>(0);
        String area = "", project = "", name = "";
        for (DisciplineRecord entity : list)
            if (!entity.getArea().equals(area) || !entity.getProject().equals(project) || !entity.getName().equals(name)) {
                project = entity.getProject();
                area = entity.getArea();
                name = entity.getName();
                DisciplineRecordQuantityBO quantity = new DisciplineRecordQuantityBO();
                quantity.setStart(to.getStart());
                quantity.setEnd(to.getEnd());
                quantity.setDepartment(entity.getProject());
                quantity.setArea(entity.getArea());
                quantity.setName(entity.getName());
                quantity.setReward(list.stream()
                        .filter(d -> d.getProject().equals(entity.getProject()) && d.getStatus() == Boolean.TRUE)
                        .collect(Collectors.toList()).size());
                quantity.setPush(list.stream()
                        .filter(d -> d.getProject().equals(entity.getProject()) && d.getStatus() == Boolean.FALSE)
                        .collect(Collectors.toList()).size());
                quantity.setTotal(list.stream()
                        .filter(d -> d.getProject().equals(entity.getProject()))
                        .mapToDouble(DisciplineRecord::getBallot).sum());
                quantityBOs.add(quantity);
            }
        return quantityBOs;
    }

    @Override
    public List<DisciplineRecordScoreBO> disciplineScoreCollect(CollectFilterTO to) throws SerException {
        checkDetail();
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        dto.getSorts().add("area=asc");
        dto.getSorts().add("project=asc");
        dto.getSorts().add("username=asc");
        dto.getSorts().add("name=asc");
        List<DisciplineRecord> list = this.getListByFilter(to, dto);
        List<DisciplineRecordScoreBO> scoreBOs = new ArrayList<>(0);
        String area = "", project = "", username = "", target = "";
        for (DisciplineRecord entity : list)
            if (!entity.getArea().equals(area) || !entity.getProject().equals(project)
                    || !entity.getUsername().equals(username) || !entity.getName().equals(target)) {
                area = entity.getArea();
                project = entity.getProject();
                username = entity.getUsername();
                target = entity.getName();
                DisciplineRecordScoreBO score = BeanTransform.copyProperties(entity, DisciplineRecordScoreBO.class, true);
                score.setPush(0);
                score.setPushTotal(0d);
                score.setReward(0);
                score.setRewardTotal(0d);
                for (DisciplineRecord reward : list)
                    if (entity.getArea().equals(reward.getArea()) && entity.getName().equals(reward.getName())
                            && entity.getProject().equals(reward.getProject()) && entity.getStatus() == Boolean.TRUE
                            && entity.getUsername().equals(reward.getUsername())) {
                        score.setReward(score.getReward() + 1);
                        score.setRewardTotal(score.getRewardTotal() + reward.getBallot());
                    }
                for (DisciplineRecord push : list)
                    if (entity.getArea().equals(push.getArea()) && entity.getName().equals(push.getName())
                            && entity.getProject().equals(push.getProject()) && entity.getStatus().equals(Boolean.TRUE)
                            && entity.getUsername().equals(push.getUsername())) {
                        score.setPush(score.getPush() + 1);
                        score.setPushTotal(score.getPushTotal() + push.getBallot());
                    }
                score.setName(target);
                score.setArea(area);
                score.setProject(project);
                score.setUsername(username);
                score.setTotal(score.getPushTotal() + score.getRewardTotal());
                scoreBOs.add(score);
            }
        return scoreBOs;
    }

    /**
     * 根据过滤条件传获取数据
     *
     * @param to 过滤条件传输对象
     * @return
     * @throws SerException
     */
    private List<DisciplineRecord> getListByFilter(CollectFilterTO to, DisciplineRecordDTO dto) throws SerException {
        if (StringUtils.isNotBlank(to.getStart()) && StringUtils.isNotBlank(to.getEnd())) {
            LocalDateTime start = LocalDateTime.parse(to.getStart(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    end = LocalDateTime.parse(to.getEnd(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime[] time = {start, end};
            dto.getConditions().add(Restrict.between("occurrence", time));
        }
        if (StringUtils.isNotBlank(to.getArea()))
            dto.getConditions().add(Restrict.eq("area", to.getArea()));
        if (StringUtils.isNotBlank(to.getProject()))
            dto.getConditions().add(Restrict.eq("project", to.getProject()));
        if (StringUtils.isNotBlank(to.getTarget()))
            dto.getConditions().add(Restrict.eq("name", to.getTarget()));
        return super.findByCis(dto);
    }

    @Override
    public List<DisciplineRecordBO> findByFilter(CollectFilterTO to) throws SerException {
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        dto.getSorts().add("occurrence=desc");
        return BeanTransform.copyProperties(this.getListByFilter(to, dto), DisciplineRecordBO.class);
    }

    @Override
    public List<DisciplineRecordBO> rewardMaps(DisciplineRecordDTO dto) throws SerException {
        checkDetail();
        dto.getConditions().add(Restrict.eq("status", !Boolean.TRUE));
        return BeanTransform.copyProperties(super.findByPage(dto), DisciplineRecordBO.class);
    }

    @Override
    public List<DisciplineRecordBO> pushMaps(DisciplineRecordDTO dto) throws SerException {
        checkDetail();
        dto.getConditions().add(Restrict.eq("status", !Boolean.FALSE));
        return BeanTransform.copyProperties(super.findByPage(dto), DisciplineRecordBO.class);
    }

    @Override
    public DisciplineRecordBO getById(String id) throws SerException {
        DisciplineRecord entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return BeanTransform.copyProperties(entity, DisciplineRecordBO.class);
    }

    @Override
    public Long getRewardTotal() throws SerException {
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        dto.getConditions().add(Restrict.eq("status", !Boolean.TRUE));
        return super.count(dto);
    }

    @Override
    public Long getPushTotal() throws SerException {
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        dto.getConditions().add(Restrict.eq("status", !Boolean.FALSE));
        return super.count(dto);
    }

    @Override
    public List<String> getarea() throws SerException {
        List<DisciplineRecord> disciplineRecords = super.findAll();
        List<String> list = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(disciplineRecords)) {
            for (DisciplineRecord entity : disciplineRecords) {
                list.add(entity.getArea());
            }
        }
        return list;
    }

    @Override
    public List<String> getGroup() throws SerException {
        List<DisciplineRecord> disciplineRecords = super.findAll();
        List<String> list = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(disciplineRecords)) {
            for (DisciplineRecord entity : disciplineRecords) {
                list.add(entity.getProject());
            }
        }
        return list;
    }

    @Override
    public List<String> getTarget() throws SerException {
        List<DisciplineRecord> disciplineRecords = super.findAll();
        List<String> list = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(disciplineRecords)) {
            for (DisciplineRecord entity : disciplineRecords) {
                list.add(entity.getName());
            }
        }
        return list;
    }

    @Override
    public Integer getPushNum(String userName) throws SerException {
        CollectFilterTO to = new CollectFilterTO();
        List<DisciplineRecordRankBO> disciplineRecordRankBOList = personalRank(to, false);
        if (!CollectionUtils.isEmpty(disciplineRecordRankBOList)) {
            List<DisciplineRecordRankBO> disciplineRecordRankBOs = disciplineRecordRankBOList.stream().filter(str -> str.getUsername().equals(userName)).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(disciplineRecordRankBOs)) {
                return disciplineRecordRankBOs.get(0).getFrequency();
            }
        }
        return 0;
    }

    @Override
    public Integer getRewardNum(String userName) throws SerException {
        CollectFilterTO to = new CollectFilterTO();
        List<DisciplineRecordRankBO> disciplineRecordRankBOList = personalRank(to, true);
        if (!CollectionUtils.isEmpty(disciplineRecordRankBOList)) {
            List<DisciplineRecordRankBO> disciplineRecordRankBOs = disciplineRecordRankBOList.stream().filter(str -> str.getUsername().equals(userName)).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(disciplineRecordRankBOs)) {
                return disciplineRecordRankBOs.get(0).getFrequency();
            }
        }
        return 0;
    }

    @Override
    public ScoreBO getRePuTotal(String userName) throws SerException {
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        dto.getConditions().add(Restrict.eq("username", userName));
        List<DisciplineRecord> disciplineRecords = super.findByCis(dto);
        Double rewardTotal = 0d;
        Double pushTotal = 0d;
        if (disciplineRecords != null && disciplineRecords.size() > 0) {
            for (DisciplineRecord disciplineRecord : disciplineRecords) {
                if (disciplineRecord.getStatus()) {
                    rewardTotal += disciplineRecord.getBallot();
                } else {
                    pushTotal += disciplineRecord.getBallot();
                }
            }
        }
        ScoreBO scoreBO = new ScoreBO();
        scoreBO.setRewardTotal(rewardTotal);
        scoreBO.setPushTotal(pushTotal);
        return scoreBO;
    }

    public String getRewardBallot(String name) throws SerException {
        StringBuilder sql = new StringBuilder("select sum(ballot) as ballot ");
        sql.append(" from bonus_discipline_record ");
        sql.append(" where name = '" + name + "' ");
        sql.append(" and is_status = 1 ");
        String[] fields = new String[]{"ballot"};
        List<DisciplineRecord> disciplineRecords = super.findBySql(sql.toString(), DisciplineRecord.class, fields);
        if (!CollectionUtils.isEmpty(disciplineRecords)) {
            Double ballots = disciplineRecords.stream().map(DisciplineRecord::getBallot).distinct().collect(Collectors.toList()).get(0);
            if (null != ballots) {
                return ballots.toString();
            }
        }
        return null;
    }

    @Override
    public String getPushBallot(String name) throws SerException {
        StringBuilder sql = new StringBuilder("select sum(ballot) as ballot ");
        sql.append(" from bonus_discipline_record ");
        sql.append(" where name = '" + name + "' ");
        sql.append(" and is_status = 0 ");
        String[] fields = new String[]{"ballot"};
        List<DisciplineRecord> disciplineRecords = super.findBySql(sql.toString(), DisciplineRecord.class, fields);
        if (!CollectionUtils.isEmpty(disciplineRecords)) {
            Double ballots = disciplineRecords.stream().map(DisciplineRecord::getBallot).distinct().collect(Collectors.toList()).get(0);
            if (null != ballots) {
                return ballots.toString();
            }
        }
        return null;
    }
}