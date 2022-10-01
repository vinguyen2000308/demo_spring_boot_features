package com.example.demo_spring_boot_features.repo.jdbc;

import com.example.demo_spring_boot_features.domain.model.Author;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class JoiningComponent {

    private static final String SQL_INSERT = "INSERT INTO author (author_id, name, genre, age) VALUES  (?,?,?,?);";

    private final JdbcTemplate jdbcTemplate;

    public JoiningComponent(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void executeBatch(List<Author> authors) {
        jdbcTemplate.batchUpdate(SQL_INSERT,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement pStmt, int i) throws SQLException {
                        Long authorId = authors.get(i).getId();
                        String name = authors.get(i).getName();
                        String gender = authors.get(i).getGender();
                        Integer age = authors.get(i).getAge();
                        pStmt.setLong(1, authorId);
                        pStmt.setString(2, name);
                        pStmt.setString(3, gender);
                        pStmt.setInt(4, age);
                    }

                    @Override
                    public int getBatchSize() {
                        return authors.size();
                    }
                });
    }
}
