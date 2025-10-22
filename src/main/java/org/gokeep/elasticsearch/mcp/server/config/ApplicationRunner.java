package org.gokeep.elasticsearch.mcp.server.config;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.gokeep.elasticsearch.mcp.server.config.MySqlConfigProperties.*;

@ApplicationScoped
public class ApplicationRunner {
    Logger log = LoggerFactory.getLogger(ApplicationRunner.class);

    @Inject
    MySqlConfigProperties mySqlConfigProperties;

    /**
     * 打印MySql配置
     */
    void printMySqlConfig() {
        log.info("- MySQL host={}", mySqlConfigProperties.host().orElse(DEFAULT_MYSQL_HOST));
        log.info("- MySQL port={}", mySqlConfigProperties.port().orElse(DEFAULT_MYSQL_PORT));
        log.info("- MySQL username={}", mySqlConfigProperties.username().orElse(DEFAULT_MYSQL_USERNAME));
        log.info("- MySQL password={}", null == mySqlConfigProperties.password().orElse(null) ? null : "hidden password! ******");
        log.info("- MySQL database={}", mySqlConfigProperties.database().orElse(null));
    }

    void onStartup(@Observes StartupEvent event) {
        printMySqlConfig();
    }
}
