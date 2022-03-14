package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    /**
     * MEMO: requestParamV1
     * 반환 타입 void, 응답에 값을 직접 넣음: view 조회X
     * */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    /**
     * MEMO: requestParamV2
     * @Controller 클래스 내 메서드의 반환 타입 String 인 경우에 view 조회O
     * - 1) 클래스: @Controller =>  @RestController 변경
     * - 2) 메서드: @ResponseBody 추가
     * @ResponseBody 추가
     * - view 조회X, HTTP Message Body 에 직접 내용 입력
     * @RequestParam 사용
     * - 파라미터 이름으로 바인딩
     * */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") String memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    /**
     * MEMO: requestParamV3
     * @RequestParam 사용
     * - HTTP 파라미터 이름, 변수 이름이 동일하면 @RequestParam(name="xx") 생략O
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * MEMO: requestParamV4
     * @RequestParam 사용
     * - String, int 등의 단순 타입이면 @RequestParam 생략O
     * */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }
}
