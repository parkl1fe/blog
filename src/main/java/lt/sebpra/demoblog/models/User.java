package lt.sebpra.demoblog.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

}
