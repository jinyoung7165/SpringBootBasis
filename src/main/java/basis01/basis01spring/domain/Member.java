package basis01.basis01spring.domain;
import javax.persistence.*;

@Entity
public class Member {
    //PK, DB에서 자동으로 1,2,...IDENTITY생성
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
