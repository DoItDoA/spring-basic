package hello.hellospring.controller;

public class MemberForm {
    private String name; // @PostMapping을 통해 입력인자로 들어온 값 중 <input name="name"> 값이 여기에 저장이 된다(input name과 명칭이 같아야한다)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
