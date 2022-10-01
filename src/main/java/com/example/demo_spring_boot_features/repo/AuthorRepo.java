package com.example.demo_spring_boot_features.repo;


import com.example.demo_spring_boot_features.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepo extends JpaRepository<Author, Long> {
    @Modifying
    @Query(value = "update author set age = age + 1 where author_id in (:authorIdList);", nativeQuery = true)
    int updateAgeByListId(@Param("authorIdList") List<Long> authorIdList);

    @Query(value = " select* from author where genre = :gender limit 1;", nativeQuery = true)
    Author findFirstByGenre(@Param("gender") String gender);
}
