package org.gokeep.elasticsearch.mcp.server.config;

import io.smallrye.config.ConfigMapping;

import java.util.Optional;

/**
 * 数据库配置信息
 */
@ConfigMapping(prefix = "mysql")
public interface MySqlConfigProperties {

    String DEFAULT_MYSQL_HOST = "127.0.0.1";
    String DEFAULT_MYSQL_PORT = "3306";
    String DEFAULT_MYSQL_USERNAME = "root";

    Optional<String> host();

    Optional<String> port();

    Optional<String> username();

    Optional<String> password();

    Optional<String> database();

}
