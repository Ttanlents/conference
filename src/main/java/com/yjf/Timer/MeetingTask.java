package com.yjf.Timer;

import com.yjf.services.MeetingService;

import java.util.TimerTask;

/**
 * @author 余俊锋
 * @date 2020/9/25 17:01
 * @Description
 */
public class MeetingTask extends TimerTask {
    private boolean isRunning = false;

    //定时任务执行体
    private MeetingService meetingService = new MeetingService();

    @Override
    public void run() {
        if (!isRunning) {
            isRunning = true;
            meetingService.updateStatusTimer();
            isRunning = false;
        }
    }
}
