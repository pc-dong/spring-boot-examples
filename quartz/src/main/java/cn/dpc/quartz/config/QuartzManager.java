package cn.dpc.quartz.config;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Quartz 任务调度工具
 */
@Service
@Transactional
public class QuartzManager {
    private static Logger log = LoggerFactory.getLogger(QuartzManager.class);
    @Autowired
    private Scheduler scheduler;


    /**
     * 添加立即执行任务
     *
     * @param jobClass  任务实现类
     * @param jobName   任务名称
     * @param groupName 任务组名
     * @param param
     */
    public void addJob(Class<? extends Job> jobClass, String jobName, String groupName, Map<String, Object> param) {
        try {
            //创建jobDetail实例，绑定Job实现类
            //指明job的名称，所在组的名称，以及绑定job类
            JobDetail jobDetail = newJob(jobClass)
                    .withIdentity(jobName, groupName)//任务名称和组构成任务key
                    .build();
            // 设置任务参数
            if (null != param) {
                param.forEach(jobDetail.getJobDataMap()::put);
            }

            //使用cornTrigger规则
            Trigger trigger = newTrigger()
                    .withIdentity(jobName, groupName)//触发器key
                    .startNow().build();
            //把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            log.error("新增任务异常", e);
        }
    }

    /**
     * 添加立即执行任务
     *
     * @param jobClass  任务实现类
     * @param jobName   任务名称
     * @param groupName 任务组名
     * @param param
     * @param delay     延时生效时间（秒）
     */
    public void addJob(Class<? extends Job> jobClass, String jobName, String groupName, Map<String, Object> param, int delay) {
        try {
            //创建jobDetail实例，绑定Job实现类
            //指明job的名称，所在组的名称，以及绑定job类
            JobDetail jobDetail = newJob(jobClass)
                    .withIdentity(jobName, groupName)//任务名称和组构成任务key
                    .build();
            // 设置任务参数
            if (null != param) {
                param.forEach(jobDetail.getJobDataMap()::put);
            }

            //使用cornTrigger规则
            Trigger trigger = newTrigger()
                    .withIdentity(jobName, groupName)//触发器key
                    .startAt(DateBuilder.futureDate(delay, DateBuilder.IntervalUnit.SECOND))
                    .build();
            //把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            log.error("新增任务异常", e);
        }
    }

    /**
     * 增加一个job
     *
     * @param jobClass  任务实现类
     * @param jobName   任务名称
     * @param groupName 任务组名
     * @param cron      时间表达式 （如：0/5 * * * * ? ）
     * @param delay     延时生效时间（秒）
     */
    public void addJob(Class<? extends Job> jobClass, String jobName, String groupName, Map<String, Object> param, String cron, int delay) {
        try {
            //创建jobDetail实例，绑定Job实现类
            //指明job的名称，所在组的名称，以及绑定job类
            JobDetail jobDetail = newJob(jobClass)
                    .withIdentity(jobName, groupName)//任务名称和组构成任务key
                    .build();
            // 设置任务参数
            if (null != param) {
                param.forEach(jobDetail.getJobDataMap()::put);
            }
            //定义调度触发规则
            //使用cornTrigger规则
            Trigger trigger = newTrigger()
                    .withIdentity(jobName, groupName)//触发器key
                    .startAt(DateBuilder.futureDate(delay, DateBuilder.IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();
            //把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            log.error("新增任务异常", e);
        }
    }

    /**
     * 增加一个job
     *
     * @param jobClass  任务实现类
     * @param jobName   任务名称
     * @param groupName 任务组名
     * @param cron      时间表达式 （如：0/5 * * * * ? ）
     */
    public void addJob(Class<? extends Job> jobClass, String jobName, String groupName, Map<String, Object> param, String cron) {
        addJob(jobClass, jobName, groupName, param, cron, 1);
    }

    /**
     * 增加一个job
     *
     * @param jobClass  任务实现类
     * @param jobName   任务名称
     * @param groupName 任务组名
     * @param date      某一时间点
     */
    public void addJob(Class<? extends Job> jobClass, String jobName, String groupName, Map<String, Object> param, Date date) {
        try {
            //创建jobDetail实例，绑定Job实现类
            //指明job的名称，所在组的名称，以及绑定job类
            JobDetail jobDetail = newJob(jobClass)
                    .withIdentity(jobName, groupName)//任务名称和组构成任务key
                    .build();
            // 设置任务参数
            if (null != param) {
                param.forEach(jobDetail.getJobDataMap()::put);
            }
            //定义调度触发规则
            //使用cornTrigger规则
            Trigger trigger = newTrigger()
                    .withIdentity(jobName, groupName)//触发器key
                    .startAt(date)
                    .build();
            //把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            log.error("新增任务异常", e);
        }
    }

    /**
     * 删除Job
     *
     * @param jobName
     * @param groupName
     */
    public void deleteJob(String jobName, String groupName) {
        try {
            scheduler.deleteJob(new JobKey(jobName, groupName));
        } catch (Exception e) {
            log.error("结束任务异常", e);
        }
    }

    /**
     * 按照组名批量删除Job
     *
     * @param groupName 任务组名
     */
    public void deleteJobByGroup(String groupName) {
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.groupEquals(groupName);
            Set<JobKey> jobkeySet = scheduler.getJobKeys(matcher);
            List<JobKey> jobkeyList = new ArrayList<>();
            jobkeyList.addAll(jobkeySet);
            scheduler.deleteJobs(jobkeyList);
        } catch (Exception e) {
            log.error("结束任务异常", e);
        }
    }

    @PostConstruct
    public void init() {
        try {
            if (scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            log.error("开启任务异常", e);
        }
    }

}
