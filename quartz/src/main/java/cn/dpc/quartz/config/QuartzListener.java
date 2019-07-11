package cn.dpc.quartz.config;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextStartedEvent;

/**
 * quartz监听器
 */
@Configuration
@Slf4j
public class QuartzListener implements ApplicationListener<ContextStartedEvent> {
	@Autowired
	private Scheduler scheduler;

	@Override
	public void onApplicationEvent(ContextStartedEvent event) {
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			log.error("启动调度异常", e);
		}
	}
}
