package cn.dpc.quartz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSourceInitializer;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

/**
 * Quartz任务调度配置
 */
@Configuration
public class QuartzScheduleConfig implements SchedulerFactoryBeanCustomizer {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private QuartzProperties quartzProperties;

    @Bean
    public QuartzDataSourceInitializer quartzDataSourceInitializer() {
        return new QuartzDataSourceInitializer(dataSource, resourceLoader, quartzProperties);
    }

	@Override
	public void customize(SchedulerFactoryBean schedulerFactoryBean) {
		schedulerFactoryBean.setSchedulerName(quartzProperties.getSchedulerName());
	}
}
