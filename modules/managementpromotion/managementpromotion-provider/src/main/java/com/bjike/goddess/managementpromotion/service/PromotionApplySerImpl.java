package com.bjike.goddess.managementpromotion.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.managementpromotion.api.LevelDesignAPI;
import com.bjike.goddess.managementpromotion.api.LevelShowAPI;
import com.bjike.goddess.managementpromotion.bo.LevelShowBO;
import com.bjike.goddess.managementpromotion.bo.PromotionApplyBO;
import com.bjike.goddess.managementpromotion.dto.LevelShowDTO;
import com.bjike.goddess.managementpromotion.dto.PromotionApplyDTO;
import com.bjike.goddess.managementpromotion.entity.PromotionApply;
import com.bjike.goddess.managementpromotion.enums.ManagerOpinion;
import com.bjike.goddess.managementpromotion.to.LevelShowTO;
import com.bjike.goddess.managementpromotion.to.PromotionApplyTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 管理等级晋升申请业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 02:04 ]
 * @Description: [ 管理等级晋升申请业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managementpromotionSerCache")
@Service
public class PromotionApplySerImpl extends ServiceImpl<PromotionApply, PromotionApplyDTO> implements PromotionApplySer {
    @Autowired
    private LevelDesignAPI levelDesignAPI;
    @Autowired
    private LevelShowAPI levelShowAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MessageAPI messageAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public PromotionApplyBO save(PromotionApplyTO to) throws SerException {
        PromotionApply entity = BeanTransform.copyProperties(to, PromotionApply.class, true);
        //TODO：根据员工编号获取入职时间，计算在司工龄
        super.save(entity);
        return BeanTransform.copyProperties(entity, PromotionApplyBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        PromotionApply p = super.findById(id);
        if (p == null) {
            throw new SerException("对象不存在!");
        }
        boolean b1 = p.getIsConform() == null;
        boolean b2 = p.getProjectManagerOpinion() == null;
        boolean b3 = p.getResourceDepartmentOpinion() == null;
        boolean b4 = p.getCommerceDepartmentOpinion() == null;
        boolean b5 = p.getModulerOpinion() == null;
        boolean b6 = p.getManagerOpinion() == null;
        if (b1 && b2 && b3 && b4 && b5 && b6) {
            super.remove(id);
        } else {
            throw new SerException("已审核，不能删除");
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(PromotionApplyTO to) throws SerException {
        PromotionApply entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("对象不存在!");
        }
        LocalDateTime a = entity.getCreateTime();
        boolean b1 = entity.getIsConform() == null;
        boolean b2 = entity.getProjectManagerOpinion() == null;
        boolean b3 = entity.getResourceDepartmentOpinion() == null;
        boolean b4 = entity.getCommerceDepartmentOpinion() == null;
        boolean b5 = entity.getModulerOpinion() == null;
        boolean b6 = entity.getManagerOpinion() == null;
        if (b1 && b2 && b3 && b4 && b5 && b6) {
            entity = BeanTransform.copyProperties(to, PromotionApply.class, true);
            entity.setCreateTime(a);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
        } else {
            throw new SerException("已审核，不能编辑");
        }
    }

    @Override
    public List<PromotionApplyBO> find(PromotionApplyDTO dto) throws SerException {
//        String employeeId = userAPI.currentUser().getEmployeeNumber();
        //TODO:需查看用户是什么权限，不同权限的人看到的申请记录数不同
//        List<PromotionApply> list=super.findAll();
//        for (PromotionApply p:list){
//            if (employeeId.equals(p.getEmployeeId())){   //当前用户只能看到自己的那条申请记录
//                PromotionApplyBO bo=BeanTransform.copyProperties(p,PromotionApplyBO.class);
//                List<PromotionApplyBO> boList=new ArrayList<PromotionApplyBO>();
//                boList.add(bo);
//                return boList;
//            }
//        }
        return BeanTransform.copyProperties(super.findByCis(dto, true), PromotionApplyBO.class);
    }

    @Override
    public PromotionApplyBO findByID(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), PromotionApplyBO.class);
    }

    @Override
    public Long count(PromotionApplyDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //规划填写是否符合晋升条件
    public void conform(PromotionApplyTO to) throws SerException {
        PromotionApply entity = super.findById(to.getId());
        entity.setIsConform(to.getIsConform());
        super.update(entity);
//        if ((entity.getIsConform() != null) && (entity.getPromotionCriteria() != null)) {
//            MessageTO messageTO = new MessageTO();
//            messageTO.setTitle("有管理等级晋升申请需审核");
//            messageTO.setContent("您有一个管理等级晋升申请需您去审核，请登陆issp系统完成审核");
//            messageTO.setMsgType(MsgType.SYS);
//            messageTO.setSendType(SendType.MSG);
//            messageTO.setRangeType(RangeType.SPECIFIED);
//            messageTO.setGroups();
//            messageAPI.send(messageTO);
//        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //综合素养模块填写晋升标准达标数
    public void writePromotionCriteria(PromotionApplyTO to) throws SerException {
        PromotionApply entity = super.findById(to.getId());
        entity.setPromotionCriteria(to.getPromotionCriteria());
        super.update(entity);
//        if ((entity.getIsConform() != null) && (entity.getPromotionCriteria() != null)) {
//            MessageTO messageTO = new MessageTO();
//            messageTO.setTitle("有管理等级晋升申请需审核");
//            messageTO.setContent("您有一个管理等级晋升申请需您去审核，请登陆issp系统完成审核");
//            messageTO.setGroups();
//            messageAPI.send(messageTO);
//        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //项目经理审核
    public void writeProjectManager(PromotionApplyTO to) throws SerException {
        PromotionApply entity = super.findById(to.getId());
        entity.setProjectManagerOpinion(to.getProjectManagerOpinion());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //综合资源部规划模块审核
    public void writeResourceDepartment(PromotionApplyTO to) throws SerException {
        PromotionApply entity = super.findById(to.getId());
        entity.setResourceDepartmentOpinion(to.getResourceDepartmentOpinion());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //运营商务部审核
    public void writeCommerceDepartment(PromotionApplyTO to) throws SerException {
        PromotionApply entity = super.findById(to.getId());
        entity.setCommerceDepartmentOpinion(to.getCommerceDepartmentOpinion());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //模块负责人审核
    public void writeModuler(PromotionApplyTO to) throws SerException {
        PromotionApply entity = super.findById(to.getId());
        entity.setModulerOpinion(to.getModulerOpinion());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //总经办审核和填写本次晋升等级获得时间
    public void writeManager(PromotionApplyTO to) throws SerException {
        PromotionApply entity = super.findById(to.getId());
        entity.setManagerOpinion(to.getManagerOpinion());
        LocalDate time = null;
        if (to.getPromotionTakeTime() != null) {
            try {
                time = DateUtil.parseDate(to.getPromotionTakeTime());
            } catch (Exception e) {
                throw new SerException("日期格式错误");
            }
            entity.setPromotionTakeTime(time);
        }
        super.update(entity);
        if (ManagerOpinion.PASS.equals(entity.getManagerOpinion())) {
            List<LevelShowBO> list = levelShowAPI.findAll(new LevelShowDTO());
            if ((list != null) && (list.size() != 0)) {
                boolean b=true;
                for (LevelShowBO l : list) {
                    if (entity.getEmployeeId().equals(l.getEmployeeId())) {
                        LevelShowTO levelShowTO = new LevelShowTO();
                        BeanUtils.copyProperties(entity, levelShowTO);
                        levelShowTO.setId(levelShowAPI.findBySql(l.getEmployeeId()).getId());
                        levelShowAPI.update(levelShowTO);
                        b=false;
                    }
                }
                if (b){
                    LevelShowTO levelShowTO = new LevelShowTO();
                    BeanUtils.copyProperties(entity, levelShowTO);
                    levelShowAPI.save(levelShowTO);
                }
            } else {
                LevelShowTO levelShowTO = new LevelShowTO();
                BeanUtils.copyProperties(entity, levelShowTO);
                levelShowAPI.save(levelShowTO);
            }
        }
    }

    @Override
    public List<PromotionApplyBO> rank() throws SerException {
        LocalDate now = LocalDate.now();
        String s = DateUtil.dateToString(now);
        s = s.substring(0, s.length() - 2);
        LocalDate start = DateUtil.parseDate(s + "21");
        LocalDate end = DateUtil.parseDate(s + "20");
        LocalDate[] time = null;
        PromotionApplyDTO dto = new PromotionApplyDTO();
        if (now.getDayOfMonth() < 21) {     //排名的日子是21号前，则排前2个月的21号到上个月20号区间
            start = start.minusMonths(2);
            end = end.minusMonths(1);
            time = new LocalDate[]{start, end};
            dto.getConditions().add(Restrict.between("applyDate", time));
            dto.getSorts().add("direction=desc");
            dto.getSorts().add("promotionCriteria=desc");
            List<PromotionApply> list = super.findByCis(dto);
            List<PromotionApplyBO> boList = new ArrayList<PromotionApplyBO>();
            for (String classification : allClassifications()) {
                for (String direction : allDirections()) {
                    int rank = 0;
                    for (PromotionApply p : list) {
                        if ((p.getPromotionCriteria() != null) && classification.equals(p.getClassification()) && direction.equals(p.getDirection())) {
                            rank++;
                            PromotionApplyBO bo = new PromotionApplyBO();
                            bo.setDirection(direction);
                            bo.setSkillLevel(p.getSkillLevel());
                            String grade = levelDesignAPI.findGrade(classification, direction, p.getSkillLevel());
                            if (grade == null) {
                                throw new SerException("获取等级失败");
                            }
                            bo.setGrade(grade);
                            bo.setName(p.getName());
                            bo.setPromotionCriteria(p.getPromotionCriteria());
                            bo.setRank(rank);
                            boList.add(bo);
                        }
                    }
                }
            }
            return boList;
        } else {  //21号排序的是上月21号至本月20号的数据
            start = start.minusMonths(1);
            time = new LocalDate[]{start, end};
            dto.getConditions().add(Restrict.between("applyDate", time));
            dto.getSorts().add("direction=desc");
            dto.getSorts().add("promotionCriteria=desc");
            List<PromotionApply> list = super.findByCis(dto);
            List<PromotionApplyBO> boList = new ArrayList<PromotionApplyBO>();
            for (String classification : allClassifications()) {
                for (String direction : allDirections()) {
                    int rank = 0;
                    for (PromotionApply p : list) {
                        if ((p.getPromotionCriteria() != null) && classification.equals(p.getClassification()) && direction.equals(p.getDirection())) {
                            rank++;
                            PromotionApplyBO bo = new PromotionApplyBO();
                            bo.setDirection(direction);
                            bo.setSkillLevel(p.getSkillLevel());
                            String grade = levelDesignAPI.findGrade(classification, direction, p.getSkillLevel());
                            if (grade == null) {
                                throw new SerException("获取等级失败");
                            }
                            bo.setGrade(grade);
                            bo.setName(p.getName());
                            bo.setPromotionCriteria(p.getPromotionCriteria());
                            bo.setRank(rank);
                            boList.add(bo);
                        }
                    }
                }
            }
            return boList;
        }
    }

    //TODO:定时器，先添加任务调度组,再添加任务调度
    @Override
    //定时发邮件给总经办
    public void send() throws SerException {
        rank();
        System.out.println("sadsa");
    }


    /**
     * 查找所有分类
     *
     * @throws SerException
     */
    private Set<String> allClassifications() throws SerException {
        Set<String> set = new HashSet<String>();
        List<PromotionApply> list = super.findAll();
        for (PromotionApply p : list) {
            set.add(p.getClassification());
        }
        return set;
    }

    /**
     * 查找所有管理方向
     *
     * @throws SerException
     */
    private Set<String> allDirections() throws SerException {
        Set<String> set = new HashSet<String>();
        List<PromotionApply> list = super.findAll();
        for (PromotionApply p : list) {
            set.add(p.getDirection());
        }
        return set;
    }
}