package hello.core.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final MyLogger myLogger;
    private final LogDemoService logDemoService;
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        String requestURL=request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);
        myLogger.log("Controller Test");
        logDemoService.logic("testId");
        return "OK";
    }

}
