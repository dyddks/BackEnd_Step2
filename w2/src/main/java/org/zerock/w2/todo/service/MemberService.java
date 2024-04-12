package org.zerock.w2.todo.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.w2.todo.dao.MemberDao;
import org.zerock.w2.todo.dao.TodoDao;
import org.zerock.w2.todo.domain.MemberVo;
import org.zerock.w2.todo.dto.MemberDto;
import org.zerock.w2.todo.util.MapperUtil;

@Log4j2
public enum MemberService {
    INSTANCE;

    private MemberDao memberDao;
    private ModelMapper modelMapper;

    MemberService() {
        memberDao = new MemberDao();
        modelMapper = MapperUtil.INSTANCE.getModelMapper();
    }

    public MemberDto login(String email, String password) throws Exception {
        MemberVo memberVo = memberDao.getWithPassword(email, password);
        MemberDto memberDto = modelMapper.map(memberVo, MemberDto.class);

        return memberDto;
    }

    public void updateUuid(String mid, String uuid) throws Exception {
        memberDao.updateUuid(mid, uuid);
    }

    public MemberDto getByUUID(String uuid) throws Exception {
        MemberVo vo = memberDao.selectUUID(uuid);
        MemberDto memberDto = modelMapper.map(vo, MemberDto.class);
        return memberDto;
    }
}
