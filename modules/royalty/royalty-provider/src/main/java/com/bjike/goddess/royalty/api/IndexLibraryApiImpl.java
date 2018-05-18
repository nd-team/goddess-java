package com.bjike.goddess.royalty.api;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.royalty.bo.IndexLibraryBO;
import com.bjike.goddess.royalty.bo.RoyaltyCollectBO;
import com.bjike.goddess.royalty.dto.IndexLibraryDTO;
import com.bjike.goddess.royalty.entity.IndexLibrary;
import com.bjike.goddess.royalty.excel.SonPermissionObject;
import com.bjike.goddess.royalty.service.IndexLibrarySer;
import com.bjike.goddess.royalty.to.GuidePermissionTO;
import com.bjike.goddess.royalty.to.IndexLibraryTO;
import com.bjike.goddess.royalty.to.RoyaltyCollectTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 指标库业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 11:23 ]
 * @Description: [ 指标库业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("indexLibraryApiImpl")
public class IndexLibraryApiImpl implements IndexLibraryAPI {
    @Autowired
    private IndexLibrarySer indexLibrarySer;
    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return indexLibrarySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return indexLibrarySer.guidePermission( guidePermissionTO );
    }

    @Override
    public Long count(IndexLibraryDTO dto) throws SerException {
        return indexLibrarySer.count(dto);
    }

    @Override
    public IndexLibraryBO getOne(String id) throws SerException {
        return indexLibrarySer.getOne(id);
    }

    @Override
    public List<IndexLibraryBO> list(IndexLibraryDTO dto) throws SerException {
        return indexLibrarySer.list(dto);
    }

    @Override
    public IndexLibraryBO insert(IndexLibraryTO indexLibraryTO) throws SerException {
        return indexLibrarySer.insert(indexLibraryTO);
    }

    @Override
    public IndexLibraryBO edit(IndexLibraryTO indexLibraryTO) throws SerException {
        return indexLibrarySer.edit(indexLibraryTO);
    }

    @Override
    public void remove(String id) throws SerException {
        indexLibrarySer.remove(id);
    }
    @Override
    public List<String> getIndexNum() throws SerException {
        return indexLibrarySer.getIndexNum();
    }

    @Override
    public List<String> getIndexName() throws SerException {
        return indexLibrarySer.getIndexName();
    }

    @Override
    public IndexLibraryBO getIndexLibrary(String indexNum) throws SerException {
        return indexLibrarySer.getIndexLibrary(indexNum);
    }
    @Override
    public List<RoyaltyCollectBO> dayRoyalty(RoyaltyCollectTO to) throws SerException {
        return indexLibrarySer.dayRoyalty(to);
    }

    @Override
    public List<RoyaltyCollectBO> weekRoyalty(RoyaltyCollectTO to) throws SerException {
        return indexLibrarySer.weekRoyalty(to);
    }

    @Override
    public List<RoyaltyCollectBO> monthRoyalty(RoyaltyCollectTO to) throws SerException {
        return indexLibrarySer.monthRoyalty(to);
    }

    @Override
    public List<RoyaltyCollectBO> totalRoyalty(RoyaltyCollectTO to) throws SerException {
        return indexLibrarySer.totalRoyalty(to);
    }

}