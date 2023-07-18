package com.ohgiraffers.adregamdi.report.command.application.dto;

import com.ohgiraffers.adregamdi.report.command.domain.aggregate.entity.ReportedReview;
import com.ohgiraffers.adregamdi.report.command.domain.aggregate.vo.ReportUserNo;
import com.ohgiraffers.adregamdi.report.command.domain.aggregate.vo.RespondentNo;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReportDTO {

    private int reportNo;   // pk
    private int reportCategoryCode;
    private String reportContents;
    private Date reportedDate;
    private ReportUserNo reportUserNo;  // 신고자
    private RespondentNo respondentNo; // 피신고자
    private ReportedReview reportedReview;
}