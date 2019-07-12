package cn.dpc.shedlock.job;

import net.javacrumbs.shedlock.core.DefaultLockingTaskExecutor;
import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.core.LockingTaskExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class WithoutSpringJob {

	@Autowired
	LockProvider lockProvider;
	private static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

	@PostConstruct
	public void testWithoutSpring() {
		long initdelay = 60*1000 - new Date().getTime()%(60*1000);
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			doExecute();
		}, initdelay,10*1000, TimeUnit.MILLISECONDS);
	}

	private void doExecute() {
		LockingTaskExecutor executor = new DefaultLockingTaskExecutor(lockProvider);
		Instant lockAtMostUntil = Instant.now().plusSeconds(600);
		executor.executeWithLock((Runnable) () -> System.out.println("withoutSpring:" +
						LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
				, new LockConfiguration("lockWithoutSpring", lockAtMostUntil));
	}
}
