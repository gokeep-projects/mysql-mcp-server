

<p align="center">
  <img src="https://www.mysql.com/common/logos/logo-mysql-170x115.png" alt="mysql-mcp-server" hight="15%" width="15%">
</p>
<h1 align="center">mysql MCP Server<h1>
<h4 align="center">极易部署 • 高性能 • 低内存占用 • 云原生支持 •Java版本的mysql MCP服务</h4>

[![Java 21](https://img.shields.io/badge/Java-21-blue.svg)](https://openjdk.org/projects/jdk/21/)
[![Quarkus](https://img.shields.io/badge/Quarkus-3.27.0-blue.svg)](https://quarkus.io/)
[![MCP Server](https://img.shields.io/badge/MCP-1.6.1-green.svg)](https://quarkiverse.github.io/quarkiverse-docs/quarkus-mcp-server/dev/index.html)
![MCP Server](https://img.shields.io/badge/License-MIT-yellow.svg)



**[📖 项目文档](#-项目介绍) • [🚀 快速开始](#-快速开始) • [🔗 MCP连接](#-MCP连接)  [🔧 启动参数](#-启动参数)   [📦 项目构建](#-项目构建) • [🛠️ 项目部署](#-项目部署) • [🔧 二次开发](#-二次开发)**

---

## 📖 项目介绍

<div>

### 🌟 基于Quarkus的轻量级mysql MCP服务

mysql MCP Server 是一个基于 [Model Context Protocol (MCP)](https://modelcontextprotocol.io/) 标准的高性能服务器实现，专门为mysql数据源提供AI助手交互能力。本项目采用 **Quarkus框架** 构建，具有以下核心优势：

### 🚀 核心特性

| 特性 | 描述 |
|------|------|
| **⚡ 快速启动** | 基于Quarkus原生编译，毫秒级快速启动 |
| **🧠 低内存占用** | 相比传统Spring Boot应用，内存使用减少85%，原生程序运行仅需要9MB，非原生13MB |
| **🔄 双协议支持** | 同时支持SSE和Streamable HTTP协议，也可以支持studio |
| **🛠️ 丰富的工具集** | 提供7种核心mysql查询工具 |
| **🌐 云原生设计** | 专为Kubernetes和容器化环境优化，如果非云原生，直接部署也是非常容易的 |
| **📦 快速打包** | 支持JVM和原生二进制两种部署模式 |

### 🎯 支持的工具功能

- **🔍 查询数据库** - 查询数据有哪些
- **📋 查询数据库表** - 查询数据库表有哪些
- **🗺️ 查询表结构** - 查询表结构
- **📝 SQL查询** - 通过SQL语句执行sql查询
- 后续功能根据实际需求和issue持续更新...

---

## 🚀 快速开始

### 📋 环境要求

- **Java 17+** - 推荐使用JDK 21 版本或更高，云原生打包需要grallvm版本jdk支持
- **Maven 3.8+** - 项目构建工具
- **mysql 5.x,6.x,7.x,8.x,9.xx** - 支持的mysql版本

### 🏃‍♂️ 立即运行

- 点击[Release](https://github.com/gokeep-projects/mysql-mcp-server/releases)下载对应的启动包，如mysql-mcp-server-runner.jar

  > [!CAUTION]
  >
  > **注意**：以下示例启动方式默认连接本地127.0.0.1:3306，未设置密码连接，如需要改动地址或设置密码，请参考[🔧 启动参数](#-启动参数)指定参数或环境变量来启动应用

  #### 1. （Anything）mysql-mcp-server-runner.jar 启动方式

  > [!NOTE]
  >
  > 需要依赖本地环境安装JDK21+，但是该包不依赖任何架构，可以在任意架构运行

  ```shell
  java -jar mysql-mcp-server-runner.jar
  #该启动方式默认连接本地http://localhost:9200，未设置密码连接，如需要改动地址或设置密码请参考🔧快速配置指定参数或环境变量
  ```

  

  #### 2. （Windows）mysql-mcp-server-runner.exe 启动方式

  ```powershell
  ./mysql-mcp-server-runner.exe
  # 或者双击运行均可
  ```

  

  #### 3. （Linux） mysql-mcp-server-runner 启动方式

  ```shell
  chmod 755 mysql-mcp-server-runner
  ./mysql-mcp-server-runner
  ```



### 🔗 MCP连接

> [!NOTE]
>
> 启动完成后，会自动启动sse和streamable两种通信方式, 并默认监听 0.0.0.0:29000
>
> sse的endpoint为: /mcp/sse
>
> streamable的endpoint为：/mcp

- **streamable:** http://{ip}:29000/mcp

- **sse:** http://{ip}:29000/mcp/sse

  

### 🔧 启动参数

> [!NOTE]
>
> 启动参数非必须的，比如需要连接远程mysql，或者需要设置用户名密码，以下两种启动参数设置，二选一即可

	#### 1. 命令行启动参数

```
mysql.host=<your-es-server-address>
mysql.username=<Your username>
mysql.password=<Your password>
mysql.database=<your db name>
```

该命令行方式启动示例入下：

```shell
java -jar mysql-mcp-server-runner.jar -Dmysql.host=127.0.0.1 -Dmysql.port=3306 -Dmysql.username=<Your username> -Dmysql.password=<Your password> -Dmysql.database=<Your database name>
```

#### 2. 环境变量设置启动参数

```she
# 临时生效以下环境变量，如果写入/etc/profile，也不用每次指定)：
export MYSQL_HOST=<your-mysql-server-host>
export MYSQL_PORT=<your-mysql-server-port>
export MYSQL_USERNAME=<your-mysql-useranme>
export MYSQL_PASSWORD=<your_password>
export MYSQL_DATABASE=<your_database_name>
```

该环境变量方式启动示例入下(需要再指定配置，会自动读取环境变量值

```shell
java -jar mysql-mcp-server-runner.jar
```



---

## 📦 项目构建

### 🏗️ 构建选项

本项目基于 **Quarkus** 构建，提供两种构建模式：

#### 1. JVM模式构建（常用构建方式，无任何工具依赖）

```bash
# 清理并打包
./mvnw clean package
或者
mvn clean package

# 生成的文件
# target/mysql-mcp-server-runner.jar
# target/lib/ - 依赖库目录
```

#### 2. 原生模式构建（如果需要云原生或对性能有极致要求）

```bash
# 原生镜像构建（需要安装GraalVM）
./mvnw package -Dnative
或者
mvn package -Dnative

# 生成的文件
# target/mysql-mcp-server-runner
# 特点：启动更快，内存占用更低
```

### 📊 性能对比

| 构建模式 | 启动时间 | 内存占用 | 文件大小 | 适用场景 |
|---------|---------|---------|---------|---------|
| **JVM模式** | 2-3秒 | ≈10MB | ≈22MB | 开发环境/生产环境 |
| **原生模式** | 1秒 | ≈5MB | ≈70MB | 开发环境/生产环境 |



---

## 🛠️ 项目部署

### 🖥️ 传统部署

#### 1. JVM模式部署

```bash
# 上传jar包和lib目录到服务器
scp target/mysql-mcp-server-runner.jar user@server:/opt/
scp -r target/lib user@server:/opt/

# 在服务器上运行
java -jar /opt/mysql-mcp-server-runner.jar
```

#### 2. 原生模式部署

```bash
# 上传原生可执行文件
scp target/mysql-mcp-server-runner user@server:/opt/

# 在服务器上运行
./mysql-mcp-server-runner
```

---

### 🐳 Docker部署

敬请期待，当前版本仅支持传统部署，后续肯定考虑支持，如有需要，可以在issue说出您的需求



## 🔧 二次开发

### 📁 项目结构

```
mysql-mcp-server/
├── src/main/java/org/gokeep/elasticsearch/mcp/server
│   ├── BaseMcpServer.java           # 基础抽象类
│   ├── SqlGe.java          # sql
│   └── MySqlMcpServer.java  # 主服务类
├── src/main/resources/
│   └── application.properties       # 配置文件
├── pom.xml              # Maven配置文件
└── README.md            # 项目文档
```

### 🛠️ 添加新工具

要添加新的mysql操作工具，只需在 `MysqlMcpServer.java` 中添加新方法：

```java
@Tool(description = "CN: 自定义mysql操作\nEN: Custom mysql operation")
public ToolResponse customOperation(
        @ToolArg(description = "参数描述") String param) throws Exception {
    return ToolResponse.success(response);
}
```


### 📚 二次开发文档

#### 核心依赖

- **Quarkus 3.27.0** - 核心框架
- **quarkus-mcp-server-sse 1.6.1** - MCP服务器实现
- **quarkus-rest-client-jackson** - REST客户端
- **Java 21** - 编程语言

#### 配置参考

```properties
# 服务器配置
quarkus.http.port=8080

# mysql配置
mysql.host=127.0.0.1
mysql.port=3306
mysql.username=
mysql.password=
mysql.database=
```

#### 测试工具

```bash
# 运行测试
./mvnw test

# 集成测试
./mvnw verify

# 开发模式（支持热部署）
./mvnw quarkus:dev
```

---



## 📄 许可证

本项目采用 [MIT]([MIT License](https://mit-license.org/)) 许可证，支持任何商用和任何修改，无需版权声明



## 🤝 贡献

欢迎提交Issue和Pull Request来改进这个项目！

---

<div align="center">

### ⭐ 如果这个项目对你有帮助，请给个Star！不胜感激

<svg height="32" aria-hidden="true" viewBox="0 0 24 24" version="1.1" width="32" data-view-component="true" class="octicon octicon-mark-github v-align-middle" src="https://github.com/gokeep-projects/mysql-mcp-server/">
    <path d="M12 1C5.923 1 1 5.923 1 12c0 4.867 3.149 8.979 7.521 10.436.55.096.756-.233.756-.522 0-.262-.013-1.128-.013-2.049-2.764.509-3.479-.674-3.699-1.292-.124-.317-.66-1.293-1.127-1.554-.385-.207-.936-.715-.014-.729.866-.014 1.485.797 1.691 1.128.99 1.663 2.571 1.196 3.204.907.096-.715.385-1.196.701-1.471-2.448-.275-5.005-1.224-5.005-5.432 0-1.196.426-2.186 1.128-2.956-.111-.275-.496-1.402.11-2.915 0 0 .921-.288 3.024 1.128a10.193 10.193 0 0 1 2.75-.371c.936 0 1.871.123 2.75.371 2.104-1.43 3.025-1.128 3.025-1.128.605 1.513.221 2.64.111 2.915.701.77 1.127 1.747 1.127 2.956 0 4.222-2.571 5.157-5.019 5.432.399.344.743 1.004.743 2.035 0 1.471-.014 2.654-.014 3.025 0 .289.206.632.756.522C19.851 20.979 23 16.854 23 12c0-6.077-4.922-11-11-11Z"></path>
</svg>

**[🔝 回到顶部](#-mysql-mcp-server)**

</div>