package at.imperial.presentation;

import at.imperial.domain.MemberDTO;
import at.imperial.service.MemberService;
import at.imperial.utility.DTOMapping;
import at.imperial.utility.MinecraftHeadUtility;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String getMembers(Model m) {
        IReactiveDataDriverContextVariable reactiveMembers
                = new ReactiveDataDriverContextVariable(memberService.getAll().map(DTOMapping.INSTANCE::memberToDTO), 100);
        m.addAttribute("members", reactiveMembers);
        return "show/members";
    }

    @GetMapping("/{name}")
    public ResponseEntity<Mono<MemberDTO>> getByName(@PathVariable("name") String name) {
        //TODO: Update with name history fetched from UUID
        return new ResponseEntity<>(memberService.getByName(name).map(DTOMapping.INSTANCE::memberToDTO), HttpStatus.OK);
    }

    @Cacheable("mcHeads")
    @GetMapping(value = "/{uuid}/head")
    public ResponseEntity<byte[]> getHeadBlobByName(@PathVariable("uuid") UUID uuid) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(ImageIO.read(new URL(MinecraftHeadUtility.headFromUUID(uuid.toString().replace("-", "")))), "png", bos);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(bos.toByteArray(), headers, HttpStatus.OK);

    }
}