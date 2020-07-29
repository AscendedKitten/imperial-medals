package at.imperial.service;

import at.imperial.domain.Member;
import at.imperial.repository.MemberRepository;
import at.imperial.utility.CsvUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class MemberService {
    private final MemberRepository repository;
    private ResourceLoader resourceLoader;

    @Autowired
    public MemberService(MemberRepository repository, ResourceLoader resourceLoader) throws IOException {
        this.repository = repository;
        this.resourceLoader = resourceLoader;

        Resource resource = resourceLoader.getResource(ResourceUtils.CLASSPATH_URL_PREFIX + "medals.csv");
        Logger.getLogger("UWUUWU").info("" + CsvUtility.read(Member.class, resource.getInputStream()));
        saveAll(CsvUtility.read(Member.class, resource.getInputStream())).subscribe();
    }

    public Mono<Member> save(Member member) {
        return repository.save(member);
    }

    public Flux<Member> saveAll(List<Member> members) {
        return repository.saveAll(members);
    }

    public Flux<Member> getAll() {
        return repository.findAll();
    }

    public Mono<Member> getByName(String name) {
        return repository.findByName(name);
    }
}
