package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 컨트롤러 실행하기 위해 사용
public class HelloController {

    @GetMapping("2222") // url에 /2222입력시 밑의 함수 실행
    public String hello(Model model) {
        model.addAttribute("data", "hello!!!"); // data에 hello값 넣기
        return "hello"; // resources/templates/hello.html을 실행한다는 의미
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("param") String name, Model model) {
        // @RequestParam("param") String name은 url에 /hello-mvc?param=입력값 입력시 '입력값'은 name에 저장된다
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // 템플릿이용 안하고 바로 출력되게 함
    public String helloString(@RequestParam("name") String name) {
        return "하이 " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setMan(name);
        return hello; // 출력시 "man": "입력값" 나옴 return 객체이면 출력을 json형식으로 함
    }
    static class Hello {
        private String man;
        public String getMan() {
            return man;
        }
        public void setMan(String man) {
            this.man = man;
        }
    }
}
