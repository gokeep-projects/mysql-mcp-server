package org.gokeep.elasticsearch.mcp.server;

import io.agroal.api.AgroalDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@ApplicationScoped
public class BaseMcpServer {

    private static final Logger log = LoggerFactory.getLogger(BaseMcpServer.class);
    @Inject
    AgroalDataSource agroalDataSource;

    public JdbcTemplate jdbcTemplate;

    @PostConstruct
    void init() {
        jdbcTemplate = new JdbcTemplate(agroalDataSource);
    }

    /**
     * SQL执行
     *
     * @param sql  sql语句
     * @param args 参数
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> execute(String sql, Object... args) {
        log.info("SQL: {}\n params={}", sql, args);
        if (args == null) {
            return jdbcTemplate.queryForList(sql);
        }else {
            return jdbcTemplate.queryForList(sql, args);
        }
    }

    /**
     * SQL执行
     *
     * @param sqlGe  sql枚举
     * @param args 参数
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> execute(SqlGe sqlGe, Object... args) {
        return this.execute(sqlGe.getSql(), args);
    }

    /**
     * SQL执行
     *
     * @param sqlGe  sql枚举
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> execute(SqlGe sqlGe) {
        return this.execute(sqlGe.getSql(),  null);
    }
}
