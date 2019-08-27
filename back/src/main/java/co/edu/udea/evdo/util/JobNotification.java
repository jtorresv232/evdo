/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.util;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Jonathan
 */
public class JobNotification implements org.quartz.Job {

    static final Logger logger = Logger.getLogger(JobNotification.class);

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {

        JobDataMap dataMap = jec.getJobDetail().getJobDataMap();

        String fecha = dataMap.getString("fecha");
        logger.debug("Hola, soy un trabajo programado " + fecha);
    }

}
