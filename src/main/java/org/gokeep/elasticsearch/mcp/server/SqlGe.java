package org.gokeep.elasticsearch.mcp.server;

public enum SqlGe {
    SHOW_DATABASES("""
            SELECT SCHEMA_NAME AS databaseName FROM information_schema.SCHEMATA ORDER BY SCHEMA_NAME;
            """
            , "查询所有数据库信息"),

    SHOW_TABLES("""
            SELECT TABLE_NAME AS tableName,TABLE_COMMENT AS tableComment FROM information_schema.TABLES WHERE TABLE_SCHEMA = ? ORDER BY TABLE_NAME
            """, "查询所有表信息，包含描述信息"),

    DESC_TABLE("""
               SELECT COLUMN_NAME AS columnName,
               DATA_TYPE   AS dataType,
               IS_NULLABLE AS nullable,
               COLUMN_KEY  AS columnKey,
               COLUMN_COMMENT AS columnComment
               FROM information_schema.COLUMNS
               WHERE TABLE_SCHEMA = ?
               AND TABLE_NAME   = ?
               ORDER BY ORDINAL_POSITION
            """, "查询表结构信息");


    SqlGe(String sql, String desc) {
        this.sql = sql;
        this.desc = desc;
    }

    private final String sql;
    private final String desc;

    public String getSql() {
        return sql;
    }

    public String getDesc() {
        return desc;
    }
}
