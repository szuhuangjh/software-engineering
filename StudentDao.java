package com.dao;

import com.pojo.Student;
import com.pojo.Teacher;
import com.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentDao类，用于处理与学生相关的数据库操作
 * 包含查询、更新、删除和添加学生信息的功能
 * 这里的StudentDao类继承自Dao类，并实现了StudentDao接口
 */
public class StudentDao {

    /**
     * 获取所有学生信息列表
     * @return 返回包含所有学生信息的List集合
     * @throws SQLException 如果数据库操作出错则抛出异常
     */
    public List<Student> getStuList() throws SQLException {
        // 查询所有学生信息的SQL语句
        String sql="select * from student";
        // 执行查询操作
        ResultSet resultSet = DBUtil.executeQuery (sql);
        // 创建ArrayList用于存储学生对象
        ArrayList<Student> objects = new ArrayList<> ();
        // 判断结果集是否不为空
        if (resultSet!=null){
            // 遍历结果集
            while (resultSet.next ()) {
                // 将每条记录封装成一个Student对象并添加到List中
                objects.add (new Student (resultSet.getString ("stuId"),resultSet.getString ("stuPwd"),resultSet.getString ("stuName"),
                        resultSet.getString ("stuSex"),resultSet.getString ("stuCardId"),resultSet.getString ("stuClass")
                ));
            }
        }
        // 返回学生列表
        return objects;
    }
    /**
     * 更新学生信息
     * @param student 包含更新后学生信息的Student对象
     * @return 返回受影响的行数
     */
    public int updateStu(Student student){
        // 更新学生信息的SQL语句
        String sql="UPDATE `student` SET `stuPwd`='"+student.getStuPwd ()+"', `stuName`='"+student.getStuName ()+"', `stuSex`='"+student.getStuSex ()+"'," +
                " `stuCardId`='"+student.getStuCardId ()+"', `stuClass`='"+student.getStuClass ()+"' WHERE (`stuId`='"+student.getStuId ()+"')";
        // 执行更新操作并返回受影响的行数
        return DBUtil.executeUpdate (sql);
    }
    /**
     * 根据学生ID删除学生信息
     * @param stuId 要删除的学生ID
     * @return 返回受影响的行数
     */
    public int delStu(String stuId){
        // 删除学生信息的SQL语句
        String sql="DELETE FROM `student` WHERE (`stuId`='"+stuId+"')";
        // 执行删除操作并返回受影响的行数
        return DBUtil.executeUpdate (sql);
    }
    /**
     * 添加新学生信息
     * @param student 包含新学生信息的Student对象
     * @return 返回受影响的行数
     */
    public int addStu(Student student){
        // 添加学生信息的SQL语句
        String sql="INSERT INTO `student` (`stuId`, `stuPwd`, `stuName`, `stuSex`, `stuCardId`, `stuClass`) VALUES" +
                " ('"+student.getStuId ()+"', '"+student.getStuPwd ()+"', '"+student.getStuName ()+"', '"+student.getStuSex ()+"', " +
                "'"+student.getStuCardId ()+"', '"+student.getStuClass ()+"')";
        // 执行添加操作并返回受影响的行数
        return DBUtil.executeUpdate (sql);
    }

    public Student getStu(String stuId) throws SQLException {
        Student student=null;
        String sql="select * from student where stuId = \""+stuId+"\"";
        ResultSet resultSet = DBUtil.executeQuery (sql);
        while (resultSet.next ()) {
            student=new Student (resultSet.getString ("stuId"),resultSet.getString ("stuPwd"),resultSet.getString ("stuName"),
                    resultSet.getString ("stuSex"),resultSet.getString ("stuCardId"),resultSet.getString ("stuClass"));
        }
        return student;
    }
}

