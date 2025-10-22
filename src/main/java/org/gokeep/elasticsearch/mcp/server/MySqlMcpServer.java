package org.gokeep.elasticsearch.mcp.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolArg;
import io.quarkiverse.mcp.server.ToolResponse;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.gokeep.elasticsearch.mcp.server.config.BaseMcpServer;
import org.gokeep.elasticsearch.mcp.server.enmus.SqlGe;

import java.util.List;
import java.util.Map;

/**
 * Elasticsearch mcp server
 */
@ApplicationScoped
public class MySqlMcpServer extends BaseMcpServer implements QuarkusApplication {


    @Inject
    ObjectMapper objectMapper;


    @Tool(description = """
            CN: 查询所有的数据库信息
            EN: Query all databases info
            """)
    public ToolResponse showDatabases() throws JsonProcessingException {
        List<Map<String, Object>> result = this.execute(SqlGe.SHOW_DATABASES);
        return ToolResponse.success(objectMapper.writeValueAsString(result));
    }


    @Tool(description = """
            CN: 查询所有的表信息
            EN: Query all tables info
            """)
    public ToolResponse showTables(@ToolArg(description = "数据库名") String databaseName) throws JsonProcessingException {
        List<Map<String, Object>> result = this.execute(SqlGe.SHOW_TABLES, databaseName);
        return ToolResponse.success(objectMapper.writeValueAsString(result));

    }

    @Tool(description = """
            CN: 查询所有的表，并带有注释
            EN: Query table description without comment
            """)
    public ToolResponse descTable(
            @ToolArg(description = "数据库名") String databaseName,
            @ToolArg(description = "表名") String tableName) throws JsonProcessingException {
        List<Map<String, Object>> result = this.execute(SqlGe.DESC_TABLE, databaseName, tableName);
        return ToolResponse.success(objectMapper.writeValueAsString(result));
    }

    @Tool(description = """
            CN: 执行sql查询数据
            EN: Execute sql query data
            """)
    public ToolResponse queryBySql(@ToolArg(description = "执行sql语句") String sql) throws JsonProcessingException {
        List<Map<String, Object>> result = this.execute(sql);
        return ToolResponse.success(objectMapper.writeValueAsString(result));
    }

    @Override
    public int run(String... args) throws Exception {
        return 0;
    }

    /**
     * 主应用启动，适用于本地开发模式
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        Quarkus.run(args);
    }
}
