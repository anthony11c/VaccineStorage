package hr.tvz.cosic.job;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail studentPrintJobDetail() {
        return JobBuilder.newJob(AvailableVaccinesJob.class).withIdentity("available_vaccines_print_job").storeDurably().build();
    }

//    @Bean
//    public SimpleTrigger studentPrintTrigger() {
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever();
//
//        return TriggerBuilder.newTrigger().forJob(studentPrintJobDetail()).withIdentity("vaccines_print_trigger").withSchedule(scheduleBuilder).build();
//    }

    @Bean
    public CronTrigger triggerEveryWeekDay(){
        return TriggerBuilder.newTrigger()
                .withIdentity("everyWeekday")
                .withSchedule(cronSchedule("0 0 12 ? * 1,5 "))
                .forJob("available_vaccines_print_job")
                .build();
    }
}
