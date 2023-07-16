package com.ohgiraffers.adregamdi.place.command.domain.aggregate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_TAG")
public class Tag {

    @Id
    @Column(name = "TAG_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagNo;
    @Column(name = "TAG_NAME")
    private String tagName;
}
