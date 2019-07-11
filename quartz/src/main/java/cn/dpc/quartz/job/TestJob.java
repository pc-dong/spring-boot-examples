package cn.dpc.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;


@PersistJobDataAfterExecution
@Slf4j
public class TestJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) {
		String jobId = jobExecutionContext.getJobDetail().getJobDataMap().getString("jobId");
		log.info("jobId:" + jobId);
	}
}
