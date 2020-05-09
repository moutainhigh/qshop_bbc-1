package co.lq.modules.quartz.config;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import co.lq.modules.quartz.domain.QuartzJob;
import co.lq.modules.quartz.repository.QuartzJobRepository;
import co.lq.modules.quartz.utils.QuartzManage;

/**
 * @author billy
 * @date 2019-01-07
 */
@Component
public class JobRunner implements ApplicationRunner {

    private final QuartzJobRepository quartzJobRepository;

    private final QuartzManage        quartzManage;

    public JobRunner(QuartzJobRepository quartzJobRepository, QuartzManage quartzManage) {
        this.quartzJobRepository = quartzJobRepository;
        this.quartzManage = quartzManage;
    }

    /**
     * 项目启动时重新激活启用的定时任务
     *
     * @param applicationArguments /
     */
    @Override
    public void run(ApplicationArguments applicationArguments) {
        System.out.println("--------------------注入定时任务---------------------");
        List<QuartzJob> quartzJobs = quartzJobRepository.findByIsPauseIsFalse();
        quartzJobs.forEach(quartzManage::addJob);
        System.out.println("--------------------定时任务注入完成---------------------");
    }
}
