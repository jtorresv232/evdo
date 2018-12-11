/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.util;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Jonathan
 */
public class Job_Notification implements org.quartz.Job{

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        System.out.println("Hola, soy un trabajo programado");
    }
    
}
