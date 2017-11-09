package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.organize.dto.PositionDetailUserDTO;
import com.bjike.goddess.organize.dto.PositionUserDetailDTO;
import com.bjike.goddess.organize.entity.PositionDetail;
import com.bjike.goddess.organize.entity.PositionDetailUser;
import com.bjike.goddess.organize.entity.PositionUserDetail;
import com.bjike.goddess.organize.enums.WorkStatus;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ike on 17-9-6.
 */
@Service
public class PositionUserDetailSerImpl extends ServiceImpl<PositionUserDetail, PositionUserDetailDTO> implements PositionUserDetailSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailSer positionDetailSer;
    @Autowired
    private PositionDetailUserSer positionDetailUserSer;
    @Autowired
    private ArrangementSer arrangementSer;
    @Autowired
    private DepartmentDetailSer departmentDetailSer;

    @Override
    public List<String> findMainUser() throws SerException {
        PositionUserDetailDTO dto = new PositionUserDetailDTO();
        dto.getConditions().add(Restrict.eq("workStatus", WorkStatus.MAIN));
        List<PositionUserDetail> positionUserDetails = super.findByCis(dto);
        List<String> listName = new ArrayList<>(0);
        if (null != positionUserDetails && positionUserDetails.size() > 0) {
            List<String> list = positionUserDetails.stream().map(PositionUserDetail::getUserId).distinct().collect(Collectors.toList());
            for (String id : list) {
                System.out.println(id);
                PositionDetailUserBO positionDetailUserBO = positionDetailUserSer.getById(id);
//                String name = userAPI.findNameById(id);
                System.out.println(id);
                if (null != positionDetailUserBO) {
                    listName.add(positionDetailUserBO.getName());
                    System.out.println(listName);
                }
            }
        }
//        String[] arr = (String[]) listName.toArray(new String[listName.size()]);

        return listName;
    }

    @Override
    public List<String> findAgentUser() throws SerException {
        PositionUserDetailDTO dto = new PositionUserDetailDTO();
        dto.getConditions().add(Restrict.eq("agent", 1));
        List<PositionUserDetail> positionUserDetails = super.findByCis(dto);

        List<String> listName = new ArrayList<>(0);
        if (null != positionUserDetails && positionUserDetails.size() > 0) {
            List<String> list = positionUserDetails.stream().map(PositionUserDetail::getUserId).distinct().collect(Collectors.toList());
            for (String id : list) {
//                String name = userAPI.findNameById(id);
//                String name = positionDetailUserSer.getById(id).getName();
                PositionDetailUser positionDetailUser = positionDetailUserSer.findById(id);
                if (null != positionDetailUser) {
                    listName.add(positionDetailUser.getName());
                }

            }
        }
//        String[] arr = (String[]) listName.toArray(new String[listName.size()]);
        return listName;
    }

    @Override
    public List<String> getPosition() throws SerException {
        List<PositionUserDetail> positionUserDetails = super.findAll();
        if (null != positionUserDetails && positionUserDetails.size() > 0) {
            List<String> list = positionUserDetails.stream().map(PositionUserDetail::getPositionId).distinct().collect(Collectors.toList());
            String[] ids = (String[]) list.toArray(new String[list.size()]);
            List<PositionDetailBO> positionDetailBOs = positionDetailAPI.findByPostIds(ids);
            List<String> listName = positionDetailBOs.stream().map(PositionDetailBO::getPosition).distinct().collect(Collectors.toList());
            return listName;
        }
        return null;
    }

    @Override
    public Long getAreaNum(String startTime, String endTime) throws SerException {
        String fields[] = new String[]{""};
        StringBuilder sql = new StringBuilder("select count(area) ");
        return null;
    }

    @Override
    public PositionDetailBO getPosition(String name) throws SerException {
        PositionDetailUserDTO positionDetailUserDTO = new PositionDetailUserDTO();
        positionDetailUserDTO.getConditions().add(Restrict.eq("name", name));
        PositionDetailUser positionDetailUser = positionDetailUserSer.findOne(positionDetailUserDTO);
        if (null != positionDetailUser) {
            PositionUserDetailDTO dto = new PositionUserDetailDTO();
            dto.getConditions().add(Restrict.eq("userId", positionDetailUser.getId()));
            dto.getConditions().add(Restrict.eq("workStatus", WorkStatus.MAIN));
            PositionUserDetail userDetail = super.findOne(dto);
            if (null != userDetail) {
                String positionId = userDetail.getPositionId();
                PositionDetailBO bo = positionDetailSer.findBOById(positionId);
                return bo;
            }
        }
        return null;
    }

    @Override
    public Map<String, String> departPosition(String name) throws SerException {
        PositionDetailUserDTO positionDetailUserDTO = new PositionDetailUserDTO();
        positionDetailUserDTO.getConditions().add(Restrict.eq("name", name));
        PositionDetailUser positionDetailUser = positionDetailUserSer.findOne(positionDetailUserDTO);
        if (null != positionDetailUser) {
            PositionUserDetailDTO dto = new PositionUserDetailDTO();
            dto.getConditions().add(Restrict.eq("userId", positionDetailUser.getId()));
            dto.getConditions().add(Restrict.eq("workStatus", WorkStatus.MAIN));
            PositionUserDetail userDetail = super.findOne(dto);
            if (null != userDetail) {
                String positionId = userDetail.getPositionId();
                PositionDetailBO bo = positionDetailSer.findBOById(positionId);
                Map<String, String> map = new HashMap<>();
                map.put(bo.getDepartmentName(), bo.getPosition());
                return map;
            }
        }
        return null;
    }

    @Override
    public List<String> arrangementAndDepartId(String name) throws SerException {
        PositionDetailUserDTO positionDetailUserDTO = new PositionDetailUserDTO();
        positionDetailUserDTO.getConditions().add(Restrict.eq("name", name));
        PositionDetailUser positionDetailUser = positionDetailUserSer.findOne(positionDetailUserDTO);
        if (null != positionDetailUser) {
            PositionUserDetailDTO dto = new PositionUserDetailDTO();
            dto.getConditions().add(Restrict.eq("userId", positionDetailUser.getId()));
            dto.getConditions().add(Restrict.eq("workStatus", WorkStatus.MAIN));
            PositionUserDetail userDetail = super.findOne(dto);
            if (null != userDetail) {
                String positionId = userDetail.getPositionId();
                PositionDetail positionDetail = positionDetailSer.findById(positionId);
                List<String> list = new ArrayList<>();
                String arrangement = arrangementSer.findById(positionDetail.getArrangement().getId()).getArrangement();
                String departId = positionDetail.getDepartment().getId();
                list.add(arrangement);
                list.add(departId);
                return list;
            }
        }
        return null;
    }

    @Override
    public Set<String> findMains(String name) throws SerException {
        List<String> stringList = arrangementAndDepartId(name);
        Set<String> set = new HashSet<>();
        String[] fields = new String[]{"name"};
        if (null != stringList) {
            String arrangement = stringList.get(0);
            String departId = stringList.get(1);
            if ("执行层".equals(arrangement)) {
                String sql = "SELECT name " +
                        "FROM organize_position_detail_user " +
                        "WHERE id IN (SELECT user_id " +
                        "             FROM organize_position_detail_user_table " +
                        "             WHERE position_id IN (SELECT id " +
                        "                                   FROM organize_position_detail " +
                        "                                   WHERE arrangement_id IN ((SELECT id " +
                        "                                                             FROM organize_arrangement " +
                        "                                                             WHERE arrangement IN ('管理层','决策层')))and department_id='"+departId+"'))";
                List<PositionDetailUserBO> list = super.findBySql(sql, PositionDetailUserBO.class, fields);
                if (null != list) {
                    set = list.stream().map(positionDetailUserBO -> positionDetailUserBO.getName()).collect(Collectors.toSet());
                }
            } else if ("管理层".equals(arrangement) || "决策层".equals(arrangement)) {
                String sql = "SELECT name " +
                        "FROM organize_position_detail_user " +
                        "WHERE id IN (SELECT user_id " +
                        "             FROM organize_position_detail_user_table " +
                        "             WHERE position_id IN (SELECT id " +
                        "                                   FROM organize_position_detail " +
                        "                                   WHERE arrangement_id IN ((SELECT id " +
                        "                                                             FROM organize_arrangement " +
                        "                                                             WHERE arrangement IN ('决策层')))and department_id='"+departId+"'))";
                List<PositionDetailUserBO> list = super.findBySql(sql, PositionDetailUserBO.class, fields);
                if (null != list) {
                    set = list.stream().map(positionDetailUserBO -> positionDetailUserBO.getName()).collect(Collectors.toSet());
                    if (set.contains(name)) {
                        set.remove(name);
                    }
                }
            }
        }
        List<PositionDetailUserBO> list = findManager();   //总经理
        if (null != list) {
            for (PositionDetailUserBO p : list) {
                set.add(p.getName());
            }
        }
        return set;
    }

    @Override
    public Set<String> findCarbons(String name) throws SerException {
        PositionDetailUserDTO positionDetailUserDTO = new PositionDetailUserDTO();
        positionDetailUserDTO.getConditions().add(Restrict.eq("name", name));
        PositionDetailUser positionDetailUser = positionDetailUserSer.findOne(positionDetailUserDTO);
        Set<String> set = new HashSet<>();
        if (null != positionDetailUser) {
            PositionUserDetailDTO dto = new PositionUserDetailDTO();
            dto.getConditions().add(Restrict.eq("userId", positionDetailUser.getId()));
            dto.getConditions().add(Restrict.eq("workStatus", WorkStatus.MAIN));
            PositionUserDetail userDetail = super.findOne(dto);
            if (null != userDetail) {
                PositionDetail positionDetail = positionDetailSer.findById(userDetail.getPositionId());
                String departId = positionDetail.getDepartment().getId();
                set = departmentDetailSer.departPersons(departId);
            }
        }
        return set;
    }

    @Override
    public List<PositionDetailUserBO> findManager() throws SerException {
        String[] fields = new String[]{"name"};
        String sql = "SELECT name " +
                "FROM organize_position_detail_user " +
                "WHERE id IN (" +
                "  SELECT user_id" +
                "  FROM organize_position_detail_user_table" +
                "  WHERE position_id = (SELECT id " +
                "                       FROM organize_position_detail" +
                "                       WHERE position = '总经理'))";     //查找总经理
        List<PositionDetailUserBO> list = super.findBySql(sql, PositionDetailUserBO.class, fields);
        return list;
    }

    @Override
    public List<String> findCharge() throws SerException {
        String[] fields = new String[]{"name"};
        String sql = "SELECT name " +
                "FROM organize_position_detail_user " +
                "WHERE id IN (" +
                "  SELECT user_id" +
                "  FROM organize_position_detail_user_table" +
                "  WHERE position_id IN (" +
                "    SELECT id" +
                "      FROM organize_position_detail" +
                "    WHERE arrangement_id IN (" +
                "     SELECT id" +
                "      FROM organize_arrangement" +
                "      WHERE arrangement in ('管理层','决策层'))))";
        List<PositionDetailUserBO> list = super.findBySql(sql, PositionDetailUserBO.class, fields);
        if (null != list) {
            return list.stream().map(PositionDetailUserBO::getName).distinct().collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<String> projectManager() throws SerException {
        String[] fields = new String[]{"name"};
        String sql = "SELECT name " +
                "FROM organize_position_detail_user " +
                "WHERE id IN (" +
                "  SELECT user_id" +
                "  FROM organize_position_detail_user_table" +
                "  WHERE position_id IN (" +
                "    SELECT id" +
                "    FROM organize_position_detail" +
                "    WHERE position LIKE '项目经理'))";
        List<PositionDetailUserBO> list = super.findBySql(sql, PositionDetailUserBO.class, fields);
        if (null != list) {
            return list.stream().map(PositionDetailUserBO::getName).distinct().collect(Collectors.toList());
        }
        return null;
    }
}
