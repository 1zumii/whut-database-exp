# 数据库系统综合实验 - Attendance Assistant
## 实验介绍：数据库应用开发大作业
#### 一、实验目的
掌握数据库设计的基本方法，完成基于数据库的应用开发,培养传播类应用的设计思路。
#### 二、实验内容
设计传播类应用的数据库管理系统，掌握数据库设计的基本步骤，包括数据库概念结构设计、逻辑结构设计、数据库模式SQL语句生成等，实现应用系统的各项功能，撰写设计报告，提供数据库创建和加载、系统应用基本功能的设计和实现等
#### 三、实验任务
分小组团队合作，设计一个基于数据库的传播类应用，要求：
- 给出应用背景描述和系统需求分析，包括系统功能描述
- 给出数据库概念结构设计，分析实体之间的联系，确定实体之间的关系，画出ER图，可利用PowerDesigner、workbench等工具
- 给出数据库逻辑结构设计，包括需要哪些基本表，每个基本表包含哪些字段，以及哪个字段为主码等
- 设计生成该数据库的SQL语句
- 设计数据库加载的接口
- 设计并实现系统应用基本功能
#### 四、实验要求
- 实验报告要求
    - 完成实验任务，列出详细的设计过程，分小组提交设计报告
- 小组演讲要求
    - 每个小组完成10分钟的PPT演讲展示，包括应用背景、系统数据库设计、系统功能设计等，并提供demo演示
    - 一定要说明具体的分工
---
## 项目介绍：基于SpringBoot的考勤助手小程序
#### 一、项目依赖
- MySQL 8.0
- JDK 1.8
- SpringBoot 2.2.2
#### 二、IDE相关
- IntelliJ IDEA 2018.2.5
- IDEA插件：Lombok plugin
#### 三、Maven依赖
```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.1</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.20</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.google.guava/guava -->
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>28.0-jre</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.62</version>
        </dependency>
    </dependencies>
```
#### 四、数据库相关
- 数据库：`aa`<br/>
详细配置见resource目录下的`application.yml`中<br/>
    ```
    url: jdbc:mysql://127.0.0.1:3306/aa?serverTimezone=GMT%2B8
    ```
- 数据库表
    - classes
    - courses
    - records
    - stu_crs_map
    - students
    - users
1. classes表

|字段名|类型|长度|小数点|not null|主键|备注|
|:--|:-:|:-:|:-:|:-:|:-:|:--|
|id|tinyint|4|0|√|||
|className|varchar|255|0|√||班级名|
|major|varchar|255|0|√||专业|
|classSize|int|11|0|√||班级人数|
|monitor|tinyint|4|0|√||班长的学生ID|