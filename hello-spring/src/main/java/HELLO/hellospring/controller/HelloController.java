package HELLO.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello") //Get 이 http요구할 때 get,post이런 거임
    public String hello(Model model){//함수명은 상관없나봐
        model.addAttribute("data","hello~~~!!");//모델에 이 Map을 넘기는거야 key,value
        return "hello"; //리턴값에 문자열로 보내면 resources/templates 밑에있는 html과 매치되는걸 찾아감
    }
    @GetMapping("mvc_example")
    public String mvc(@RequestParam("nameinput")String name,Model model){
        model.addAttribute("name",name);
        return "hello_templates";
    }

    @GetMapping("mvc_class")
    @ResponseBody//이게 있으면 http body에 문자내용 직접반환
    public hi mmm(@RequestParam("name")String name) {
        hi h=new hi();
        h.setName(name);
        return h;//객체가 오면 기본적으로 json으로 반환하기로함
    }
    static class hi {
        private String name;
        public String getName() {//단축키 ALT+INSERT로 override가능하다.
            return name;
        }

        public void setName(String name) {//프로퍼티 접근방식
            this.name = name;
        }
    }


}
