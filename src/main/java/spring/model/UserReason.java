package spring.model;

import javax.persistence.*;

@Entity
@Table(name = "user_reason")
public class UserReason {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_reason")
    private Long idReason;

    @Column(name = "country")
    private String country;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdReason() {
        return idReason;
    }

    public void setIdReason(Long idReason) {
        this.idReason = idReason;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
