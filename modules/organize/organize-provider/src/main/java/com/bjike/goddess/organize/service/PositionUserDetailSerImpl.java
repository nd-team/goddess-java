package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.dto.PositionDetailUserDTO;
import com.bjike.goddess.organize.dto.PositionUserDetailDTO;
import com.bjike.goddess.organize.entity.PositionDetailUser;
import com.bjike.goddess.organize.entity.PositionUserDetail;
import com.bjike.goddess.organize.enums.WorkStatus;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Override
    public List<String> findMainUser() throws SerException {
        PositionUserDetailDTO dto = new PositionUserDetailDTO();
        dto.getConditions().add(Restrict.eq("workStatus", WorkStatus.MAIN));
        List<PositionUserDetail> positionUserDetails = super.findByCis(dto);
        List<String> listName = new ArrayList<>(0);
        if (null != positionUserDetails && positionUserDetails.size() > 0) {
            List<String> list = positionUserDetails.stream().map(PositionUserDetail::getUserId).distinct().collect(Collectors.toList());
            for (String id : list) {
                String name = positionDetailUserSer.findOneByUser(id).getName();
                listName.add(name);
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
                String name = positionDetailUserSer.findOneByUser(id).getName();
                listName.add(name);
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
}
