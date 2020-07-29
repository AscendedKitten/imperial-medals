package at.imperial.presentation;

import at.imperial.domain.MemberDTO;
import at.imperial.service.MemberService;
import at.imperial.utility.DTOMapping;
import at.imperial.utility.MinecraftHeadUtility;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
        return new ResponseEntity<>(memberService.getByName(name).map(DTOMapping.INSTANCE::memberToDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/{name}/uuid")
    public ResponseEntity<byte[]> getHeadBlobByName(@PathVariable("name") String uuid) throws IOException {
        //CacheControl cacheCtrl =  CacheControl.maxAge(60, TimeUnit.SECONDS);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(ImageIO.read(new URL(MinecraftHeadUtility.headFromUUID(uuid))), "png", bos);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(bos.toByteArray(), headers, HttpStatus.OK);

    }


}