package com.bjike.goddess.financeinit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.financeinit.enums.ProofCharacter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 凭证字
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 03:09 ]
 * @Description: [ 凭证字 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "financeinit_proofwords")
public class ProofWords extends BaseEntity {

    /**
     * 凭证字
     */
    @Column(name = "proofCharacter", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '凭证字'")
    private ProofCharacter proofCharacter;


    public ProofCharacter getProofCharacter() {
        return proofCharacter;
    }

    public void setProofCharacter(ProofCharacter proofCharacter) {
        this.proofCharacter = proofCharacter;
    }
}