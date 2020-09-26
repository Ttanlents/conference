package com.yjf.dao;

import java.util.List;

/**
 * @author 余俊锋
 * @date 2020/9/25 18:41
 * @Description
 */
public class MeetingJoinDao extends BaseDao {
    public List<Integer> getUserIdsByMeetId(int meetId) {
        String sql = "select u_id from meeting_join where m_id=?";
        return jdbcTemplate.queryForList(sql, Integer.class, meetId);
    }


    /**
     *@Description 添加一条记录
     *@author 余俊锋
     *@date 2020/9/26 10:40
     *@params userId
     * @param meetId
     *@return void
     */
    public void addMeetingJoin(int userId, int meetId) {
        String sql = "insert into meeting_join values(?,?)";
        jdbcTemplate.update(sql,userId,meetId);
    }

    public void deleteMeetJoin(int userId, int meetId){
        String sql="delete from meeting_join where u_id=? and m_id=?";
        jdbcTemplate.update(sql,userId,meetId);
    }


}
