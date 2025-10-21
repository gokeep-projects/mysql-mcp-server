package org.gokeep.elasticsearch.mcp.test;

import io.quarkiverse.mcp.server.ToolResponse;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.gokeep.elasticsearch.mcp.server.MySqlMcpServer;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusTest
class MySqlMcpServerApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(MySqlMcpServerApplicationTests.class);
    @Inject
    MySqlMcpServer mySqlMcpServer;

    @Test
    void testShowDatabase() throws Exception {
        ToolResponse response = mySqlMcpServer.showDatabases();
        log.info("ToolResponse: {}", response);
    }

    @Test
    void testTables() throws Exception {
        ToolResponse response = mySqlMcpServer.showTables("mysql");
        log.info("ToolResponse: {}", response);
    }

    @Test
    void testTableDesc() throws Exception {
        ToolResponse response = mySqlMcpServer.descTable("mysql", "user");
        log.info("ToolResponse: {}", response);
    }

    @Test
    void testQuery() throws Exception {
        ToolResponse response = mySqlMcpServer.queryBySql("select * from mysql.user limit 10");
        log.info("ToolResponse: {}", response);
    }
}
