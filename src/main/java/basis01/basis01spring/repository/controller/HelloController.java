package basis01.basis01spring.repository.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //MVC 방식
    @GetMapping("hello") //http GET 요청 -> 특정 method 실행해라
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello"; //resources/templates/hello.html로 가서 model넘겨라(렌더링)
        // -> model의 key값으로 value를 가져올 수 있음 (${data})
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { //params로 name값 받음
        model.addAttribute("name", name);
        return "hello-template";
    }

    
    //API 방식
    @GetMapping("hello-string")
    @ResponseBody //viewResolver사용하지 않고 HTTP BODY에 직접 return
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }


    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); //객체 생성
        hello.setName(name); //params로 받은 name으로 초기화
        return hello; //@ResponseBody를 써서 객체 반환 시 json으로 변환
    }


    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
