/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Jonathan
 */
public class Notifications {

    static final Logger logger = Logger.getLogger(Notifications.class);
    private static Notifications notifications;
    private Scheduler scheduler;

    private Notifications() throws SchedulerException {
        this.scheduler = StdSchedulerFactory.getDefaultScheduler();
        logger.debug("HEY SCHEDULER: " + this.scheduler.getSchedulerName());
        this.scheduler.start();
    }

    public static Notifications getInstance() throws SchedulerException {
        if (notifications == null) {
            notifications = new Notifications();
        } else {
            logger.debug("Ya se ha creado el objeto");
        }
        return notifications;
    }

    public void notificar() throws SchedulerException, ParseException {

        String startDateStr = "2018-12-13 16:22:00.0";

        JobDetail job = newJob(JobNotification.class)
                .withIdentity("job18", "grupo")
                .usingJobData("fecha", startDateStr)
                .build();

        //Trigger
        Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(startDateStr);
        Trigger trigger = newTrigger()
                .withIdentity("trigger18", "grupo")
                .startAt(startDate)
                .build();

        //Ejecutar
        this.scheduler.scheduleJob(job, trigger);
    }
}
