package com.ohgiraffers.adregamdi.place.query.infra.repository;

import com.ohgiraffers.adregamdi.place.command.domain.aggregate.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryQueryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByCategoryName(String categoryName);
}