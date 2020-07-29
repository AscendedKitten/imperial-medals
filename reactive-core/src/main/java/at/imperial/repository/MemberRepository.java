package at.imperial.repository;

import at.imperial.domain.Member;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface MemberRepository extends ReactiveCrudRepository<Member, Long> {
    @Query("SELECT * FROM MEMBER WHERE USER_NAME = :NAME")
    Mono<Member> findByName(String name);
}
