package com.bjike.goddess.interiorrecommend.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.interiorrecommend.enums.PathWay;
import com.bjike.goddess.interiorrecommend.enums.RecommendType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
* 内部推荐方案实施
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-12 11:29 ]
* @Description:	[ 内部推荐方案实施 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SchemeImplementTO extends BaseTO {
    /**
     * 方案名称
     */
    @NotBlank(message = "方案名称不能为空", groups = {ADD.class, EDIT.class})
    private String  type;

    /**
     * 推荐发起人
     */
    @NotBlank(message = "推荐发起人不能为空", groups = {ADD.class, EDIT.class})
    private String  initiator;

    /**
     * 推荐的途径
     */
    @NotNull(message = "推荐的途径不能为空", groups = {ADD.class, EDIT.class})
    private PathWay pathway;

    /**
     * 推荐时间
     */
    @NotNull(message = "推荐时间不能为空", groups = {ADD.class, EDIT.class})
    private String  recommendTime;

    /**
     * 推荐类型
     */
    @NotNull(message = "推荐类型不能为空", groups = {ADD.class, EDIT.class})
    private RecommendType  recommendType;

    /**
     * 推荐岗位
     */
    @NotBlank(message = "推荐岗位不能为空", groups = {ADD.class, EDIT.class})
    private String  recommendPosition;

    /**
     * 被推荐人
     */
    @NotBlank(message = "被推荐人不能为空", groups = {ADD.class, EDIT.class})
    private String  beRecommender;


    /**
     * 奖励金额发放时间
     */
    private String grantDate;



    public String getType () {
        return type;
    }
    public void setType (String type ) {
        this.type = type ;
    }
    public String getInitiator () {
        return initiator;
    }
    public void setInitiator (String initiator ) {
        this.initiator = initiator ;
    }

    public PathWay getPathway() {
        return pathway;
    }

    public void setPathway(PathWay pathway) {
        this.pathway = pathway;
    }

    public String getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(String recommendTime) {
        this.recommendTime = recommendTime;
    }

    public String getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(String grantDate) {
        this.grantDate = grantDate;
    }

    public RecommendType getRecommendType () {
        return recommendType;
    }
    public void setRecommendType (RecommendType recommendType ) {
        this.recommendType = recommendType ;
    }
    public String getRecommendPosition () {
        return recommendPosition;
    }
    public void setRecommendPosition (String recommendPosition ) {
        this.recommendPosition = recommendPosition ;
    }
    public String getBeRecommender () {
        return beRecommender;
    }
    public void setBeRecommender (String beRecommender ) {
        this.beRecommender = beRecommender ;
    }

}