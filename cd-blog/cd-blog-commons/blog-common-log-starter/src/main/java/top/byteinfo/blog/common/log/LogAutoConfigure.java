package top.byteinfo.blog.common.log;

import org.springframework.context.annotation.ComponentScan;

/**
 * 日志自动配置
 *
 * @author zlt
 * @date 2019/8/13
 */
@ComponentScan
//@EnableConfigurationProperties({TraceProperties.class, AuditLogProperties.class})
public class LogAutoConfigure {
    /**
     * 日志数据库配置
     */
//    @Configuration
//    @ConditionalOnClass(HikariConfig.class)
//    @EnableConfigurationProperties(LogDbProperties.class)
//    public static class LogDbAutoConfigure {}
}
