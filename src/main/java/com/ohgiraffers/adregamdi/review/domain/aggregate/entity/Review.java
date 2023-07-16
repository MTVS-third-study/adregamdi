package com.ohgiraffers.adregamdi.review.domain.aggregate.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "TBL_REVIEW")
public class Review {

    @Id
    @Column(name = "REVIEW_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewNo;   // 기본 키
    @Column
    private int likeNum;
    @Column
    private int starPoint;
    @Column
    private String originReviewImageName;
    @Column
    private String savedReviewImageName;
    @Column
    private String imageFilePath;
    @Column
    private String reviewContent;
    @Column(name = "regDate", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp regDate;


////    @Embedded
//    @Column(nullable = true)
//    private ReviewWriterNo reviewWriterNo;
////    @Embedded
//    @Column
//    private ReviewPlaceNo reviewPlaceNo;
}