package com.ruoyi.admin;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * flyway测试案例
 *
 * @author weibocy
 */
@Disabled
@SpringBootTest
@DisplayName("flyway单元测试案例")
public class FlywayUnitTest {

    private Flyway flyway;

    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @BeforeEach
    public void init(){

    }

    @DisplayName("测试 assertEquals 方法")
    @Test
    public void testAssertEquals() {
        final Flyway flyway = Flyway.configure().load();
        flyway.baseline();
    }

    @DisplayName("测试 assertSame 方法")
    @Test
    public void testAssertSame() {
        Object obj = new Object();
        Object obj1 = obj;
        Assertions.assertSame(obj, obj1);
        Assertions.assertNotSame(obj, obj1);
    }

    @DisplayName("测试 assertTrue 方法")
    @Test
    public void testAssertTrue() {
        Assertions.assertTrue(true);
        Assertions.assertFalse(true);
    }

    @DisplayName("测试 assertNull 方法")
    @Test
    public void testAssertNull() {
        Assertions.assertNull(null);
        Assertions.assertNotNull(null);
    }

}
