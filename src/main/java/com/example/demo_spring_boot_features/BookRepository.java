package com.example.demo_spring_boot_features;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookRepository extends ElasticsearchRepository<Book,String> {

    Page<Book> findByAuthor(String author, Pageable pageable);


}
