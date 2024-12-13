package example.hopeproject.modele;

import jakarta.persistence.*;

/**
 * Classe Entity qui est "bindée" avec la table "User"
 * chaque ligne de la table User correspond à une instance de cette classe Entity
 *
 */

@Entity
@Table(name = "user")

public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ROLE", nullable = false, length = 50)
    private String role;




}