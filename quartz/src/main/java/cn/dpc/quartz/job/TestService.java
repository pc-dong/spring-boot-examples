package cn.dpc.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author dongpeichao
 * @version v1.0
 * @email dongpeichao@boco.com.cn
 * @time 2019年07月11日16:54
 * @modify <BR/>
 * 修改内容：<BR/>
 * 修改人员：<BR/>
 * 修改时间：<BR/>
 */
@Component
@Slf4j
public class TestService {

	@Scheduled(cron = "*/6 * * * * ?")
	public void testSchedule() {
		log.info("ServiceJobId：" + new Random(10).nextInt());
	}
}
