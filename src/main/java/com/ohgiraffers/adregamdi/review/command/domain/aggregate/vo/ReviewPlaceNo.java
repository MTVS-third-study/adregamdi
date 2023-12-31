package com.ohgiraffers.adregamdi.review.command.domain.aggregate.vo;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@ToString
public class ReviewPlaceNo implements Serializable {

    @Column(name = "place_no")
    private Long reviewPlaceNo;

    protected ReviewPlaceNo() {}

    public ReviewPlaceNo(Long reviewPlaceNo) {
        this.reviewPlaceNo = reviewPlaceNo;
    }

}
