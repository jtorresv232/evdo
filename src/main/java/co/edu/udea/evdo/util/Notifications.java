/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.util;

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
    Scheduler scheduler;

    public Notifications() throws SchedulerException {
        this.scheduler = StdSchedulerFactory.getDefaultScheduler();
        this.scheduler.start();
    }
    
    public void notificar() throws SchedulerException{
        JobDetail job = newJob(Job_Notification.class)
                .withIdentity("job", "grupo")
                .build();
        
        //Trigger
        Trigger trigger = newTrigger()
                .withIdentity("trigger", "grupo")
                .startNow()
                .build();
        
        //Ejecutar
        this.scheduler.scheduleJob(job, trigger);
    }
}
