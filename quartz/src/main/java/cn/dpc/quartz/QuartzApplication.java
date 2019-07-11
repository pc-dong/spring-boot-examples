package cn.dpc.quartz;

import cn.dpc.quartz.config.QuartzManager;
import cn.dpc.quartz.job.TestJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@SpringBootApplication
@EnableScheduling
public class QuartzApplication implements CommandLineRunner {
	@Autowired
	QuartzManager quartzManager;

	public static void main(String[] args) {
		SpringApplication.run(QuartzApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("jobId", String.valueOf(new Random(22).nextInt()));
		quartzManager.addJob(TestJob.class, "test_job", "test_group", paramMap, "*/5 * * * * ?");
	}
}
