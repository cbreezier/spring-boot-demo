package com.example.demo;

import com.example.demo.model.Counter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class CounterDao {
    private final JdbcTemplate jdbcTemplate;
    private final CounterRowMapper counterRowMapper;

    public CounterDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        this.counterRowMapper = new CounterRowMapper();
    }

    public Optional<Counter> getCounter() {
        final List<Counter> counters = jdbcTemplate.query("select value from counters where key = 'counter'", counterRowMapper);

        if (counters.size() > 0) {
            return Optional.of(counters.get(0));
        } else {
            return Optional.empty();
        }
    }

    public void incrementCounter() {
        jdbcTemplate.update("insert into counters (key, value) values ('counter', 1) on conflict (key) do update set value = counters.value + 1");
    }

    private static class CounterRowMapper implements RowMapper<Counter> {

        @Override
        public Counter mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Counter(resultSet.getInt("value"));
        }
    }
}
