package com.ohgiraffers.adregamdi.place.command.domain.aggregate.entity;

import com.ohgiraffers.adregamdi.place.command.domain.aggregate.vo.TagVO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TBL_PLACETAGS")
public class PlaceTags {

    @Id
    @Column(name = "place_tags_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeTagsNo;

    @ManyToOne
    private Place place;

    @Embedded
    private TagVO tagVO;

}
