package com.whs3.playground.repository;

import com.whs3.playground.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Optional;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // DB 결과를 User 객체로 변환하는 RowMapper
    private final RowMapper<User> userRowMapper = (rs, rowNum) -> new User(
            rs.getLong("USERNUM"),
            rs.getString("USERID"),
            rs.getString("USERPW"),
            rs.getString("ROLE")
    );

    // 회원가입
    @Override
    public Long save(User user) {
        String sql = "INSERT INTO PLAY.USERS (USERNUM, USERID, USERPW, ROLE) VALUES (PLAY.USER_SEQ.NEXTVAL, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 데이터베이스에서 생성된 키(usernum)를 저장할 KeyHolder 객체 생성; INSERT 후 자동 생성된 PK 값을 가져온다

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[] {"USERNUM"});
                    ps.setString(1, user.getUserid());
                    ps.setString(2, user.getUserpw());
                    ps.setString(3, user.getRole());
                    return ps;
                }, keyHolder);

        // 시퀀스 값 반환
        return keyHolder.getKey().longValue();
    }

    // userid로 사용자 조회 (로그인 시 사용)
    @Override
    public Optional<User> findByUserid(String userid) {
        String sql = "SELECT * FROM PLAY.USERS WHERE USERID = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, userRowMapper, userid);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
