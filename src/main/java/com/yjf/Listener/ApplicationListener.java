package com.yjf.Listener;



import com.yjf.Timer.MeetingTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;
import java.util.Timer;

/**
 * @author 余俊锋
 * @date 2020/9/25 17:01
 * @Description
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Timer timer = new Timer();
        MeetingTask myTask = new MeetingTask();
        timer.schedule(myTask, new Date(), 1000*30);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
