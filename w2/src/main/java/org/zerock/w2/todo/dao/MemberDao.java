package org.zerock.w2.todo.dao;

import lombok.Cleanup;
import org.zerock.w2.todo.domain.MemberVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao {
    public MemberVo getWithPassword(String mid, String mpw) throws Exception{
        String sql = "select * from tbl_member where mid=? and mpw=?";
        MemberVo memberVo = null;

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, mid);
        ps.setString(2, mpw);
        @Cleanup ResultSet rs = ps.executeQuery();

        rs.next();

        memberVo = MemberVo.builder()
                .mid(rs.getString(1))
                .mpw(rs.getString(2))
                .mname(rs.getString(3))
                .build();

        return memberVo;
    }
}
