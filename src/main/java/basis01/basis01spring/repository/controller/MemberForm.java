package basis01.basis01spring.repository.controller;

public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) { //form의 name이 들어옴 -> getName으로 꺼낸다
        this.name = name;
    }
}
