package com.yjf.services;

import com.yjf.dao.MeetDao;
import com.yjf.dao.MeetingJoinDao;
import com.yjf.dao.UserDao;
import com.yjf.entity.Meeting;
import com.yjf.entity.MeetingJoin;
import com.yjf.entity.Page;
import com.yjf.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 余俊锋
 * @date 2020/9/25 12:06
 * @Description
 */
public class MeetingService {

    MeetDao meetDao = new MeetDao();
    MeetingJoinDao meetingJoinDao = new MeetingJoinDao();
    UserDao userDao = new UserDao();

    public List<Meeting> listAllMeet(Page<List<Meeting>> page) {
        return meetDao.listMeet(page);
    }

    public void addMeeting(Meeting meeting) {
        meetDao.addMeet(meeting);
    }

    public int getCount() {
        return meetDao.getMeetCount();
    }

    public List<User> getUserByDeptId(int deptId) {
        return meetDao.getUserByDeptId(deptId);
    }

    /**
     * @return void
     * @Description 定时任务 核心业务 修改该会议的状态
     * @author 余俊锋
     * @date 2020/9/25 17:57
     * @params
     */


    public void updateStatusTimer() {
        //查询所有的Meeting
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Meeting> list = meetDao.listMeet();
        for (Meeting meeting : list) {
            Integer status = meeting.getStatus();
            Integer id = meeting.getId();
            try {
                long startTime = format.parse(meeting.getStartTime()).getTime();
                long endTime = format.parse(meeting.getEndTime()).getTime();
                long nowTime = new Date().getTime();
                if (nowTime > startTime && nowTime < endTime && status != 1) {
                    meetDao.updateStatusTimer(1, id);
                    System.out.println("修改了id状态-->" + id + "改为会议进行中");
                } else if (nowTime > endTime && status != 2) {
                    meetDao.updateStatusTimer(2, id);
                    System.out.println("修改了id状态-->" + id + "改为会议已结束");
                } else if (nowTime < startTime && status != 0) {
                    meetDao.updateStatusTimer(0, id);
                    System.out.println("修改了id状态-->" + id + "改为会议未开始");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return com.yjf.entity.Meeting
     * @Description 根据id 查询会议
     * @author 余俊锋
     * @date 2020/9/25 17:56
     * @params id
     */
    public Meeting getMeetingById(int id) {
        return meetDao.getMeetingById(id);
    }


    /**
     * @return java.util.List<java.lang.Integer>
     * @Description 查询该  id会议  的实到人数（size）
     * @author 余俊锋
     * @date 2020/9/26 9:15
     * @params meetingId
     */
    public List<Integer> getUsersIdBymeetingId(int meetingId) {
        return meetingJoinDao.getUserIdsByMeetId(meetingId);
    }

    public List<Integer> getUsersidByDeptid(int deptId) {
        return userDao.getUsersIdByDeptId(deptId);
    }

    /**
     *@Description 参加会议
     *@author 余俊锋
     *@date 2020/9/26 10:42
     *@params userId
     * @param meetId
     *@return void
     */
    public void JoinTheMeet(int userId, int meetId) {
        meetingJoinDao.addMeetingJoin(userId, meetId);
    }
    /**
     *@Description 取消参加会议
     *@author 余俊锋
     *@date 2020/9/26 15:11
     *@params userId
     * @param meetId
     *@return void
     */
    public void cancleJoinMeeting(int userId, int meetId) {
        meetingJoinDao.deleteMeetJoin(userId,meetId);
    }

    /**
     *@Description 删除会议
     *@author 余俊锋
     *@date 2020/9/26 15:55
     *@params id
     *@return void
     */
    public void deleteMeetingById(int id){
        meetDao.deleteByMeetId(id);
    }

    public void updateMeet(Meeting meeting){
        meetDao.updateMeet(meeting);
    }

}
