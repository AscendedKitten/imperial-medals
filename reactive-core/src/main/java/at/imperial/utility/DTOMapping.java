package at.imperial.utility;

import at.imperial.domain.Member;
import at.imperial.domain.MemberDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOMapping {
    DTOMapping INSTANCE = Mappers.getMapper(DTOMapping.class);

    MemberDTO memberToDTO(Member member);
    Member memberFromDTO(MemberDTO memberDTO);
}