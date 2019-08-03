package spring.model;

import javax.persistence.*;

@Entity
@Table(name = "user_reason",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_user", "country"}))
public class UserReason {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_reason")
    private Reason reason;

    @Column(name = "country")
    private String country;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
