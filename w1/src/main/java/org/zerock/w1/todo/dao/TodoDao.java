package org.zerock.w1.todo.dao;

import lombok.Cleanup;
import org.zerock.w1.todo.domain.TodoVo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {
    public String getTime() throws Exception {
        String now = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement statement = connection.prepareStatement("select now()");
        @Cleanup ResultSet resultSet = statement.executeQuery();

        resultSet.next();
        now = resultSet.getString(1);

        return now;
    }

    public void insert(TodoVo todoVo) throws Exception {
        String sql = "insert into tbl_todo(title, dueDate, finished) values(?,?,?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, todoVo.getTitle());
        statement.setDate(2, Date.valueOf(todoVo.getDueDate()));
        statement.setBoolean(3, todoVo.isFinished());

        statement.executeUpdate();
    }

    public List<TodoVo> selectAll() throws Exception{
        String sql = "select * from tbl_todo";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement statement = connection.prepareStatement(sql);
        @Cleanup ResultSet rs = statement.executeQuery();
        List<TodoVo> list = new ArrayList<TodoVo>();

        while(rs.next()) {
            TodoVo vo = TodoVo.builder()
                    .title(rs.getString("title"))
                    .tno(rs.getLong("tno"))
                    .dueDate(rs.getDate("dueDate").toLocalDate())
                    .finished(rs.getBoolean("finished"))
                    .build();

            list.add(vo);
        }
        return list;
    }

    public TodoVo selectOne(Long tno) throws Exception {
        String sql = "select * from tbl_todo where tno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, tno);
        @Cleanup ResultSet rs = statement.executeQuery();

        if(rs.next()) {
            TodoVo vo = TodoVo.builder()
                    .title(rs.getString("title"))
                    .tno(rs.getLong("tno"))
                    .dueDate(rs.getDate("dueDate").toLocalDate())
                    .build();
            return vo;
        }else{
            return null;
        }
    }

    public void delete(Long tno) throws Exception {
        String sql = "delete from tbl_todo where tno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, tno);
        statement.executeUpdate();
    }

    public void update(TodoVo todoVo) throws Exception {
        String sql = "update tbl_todo set title=?, dueDate=?, finished=? where tno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, todoVo.getTitle());
        statement.setDate(2, Date.valueOf(todoVo.getDueDate()));
        statement.setBoolean(3, todoVo.isFinished());
        statement.setLong(4, todoVo.getTno());
        statement.executeUpdate();
    }
}
