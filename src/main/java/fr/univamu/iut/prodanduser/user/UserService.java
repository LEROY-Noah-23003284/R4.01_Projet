package fr.univamu.iut.prodanduser.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

/**
 * Service gérant les opérations liées aux utilisateurs.
 * Il permet la récupération, la création, la mise à jour et la suppression des utilisateurs.
 * Les données sont stockées dans un dépôt implémentant {@link UserRepositoryInterface}.
 */
@ApplicationScoped
public class UserService {

    /**
     * Dépôt utilisé pour accéder aux données des utilisateurs.
     */
    protected UserRepositoryInterface userRepo;

    /**
     * Constructeur permettant d'injecter l'accès aux données.
     *
     * @param userRepo objet implémentant l'interface d'accès aux données.
     */
    @Inject
    public UserService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Constructeur par défaut.
     */
    public UserService() {}

    /**
     * Récupère tous les utilisateurs sous forme de JSON.
     *
     * @return Une chaîne de caractères contenant les utilisateurs au format JSON.
     */
    public String getAllUsersJSON() {
        ArrayList<User> allUsers = userRepo.getAllUsers();
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allUsers);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Récupère tous les utilisateurs ayant le rôle "Administrateur" sous forme de JSON.
     *
     * @return Une chaîne de caractères contenant les utilisateurs administrateurs au format JSON.
     */
    public String getAllUsersAdminJSON() {
        ArrayList<User> allUsers = userRepo.getAllUsersAdmin();
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allUsers);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Récupère tous les utilisateurs ayant le rôle "Non Administrateur" sous forme de JSON.
     *
     * @return Une chaîne de caractères contenant les utilisateurs non administrateurs au format JSON.
     */
    public String getAllUsersNonAdminJSON() {
        ArrayList<User> allUsers = userRepo.getAllUsersNonAdmin();
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allUsers);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Récupère un utilisateur spécifique sous forme de JSON.
     *
     * @param mail L'adresse e-mail de l'utilisateur recherché.
     * @return Une chaîne de caractères contenant les informations de l'utilisateur au format JSON,
     * ou {@code null} si l'utilisateur n'existe pas.
     */
    public String getUserJSON(String mail) {
        String result = null;
        User myUser = userRepo.getUser(mail);
        if (myUser != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myUser);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Met à jour les informations d'un utilisateur existant.
     *
     * @param user L'utilisateur avec les nouvelles informations.
     */
    public void updateUser(User user) {
        userRepo.updateUser(user);
    }

    /**
     * Supprime un utilisateur en fonction de son adresse e-mail.
     *
     * @param mail L'adresse e-mail de l'utilisateur à supprimer.
     */
    public void deleteUser(String mail) {
        userRepo.deleteUser(mail);
    }

    /**
     * Ajoute un nouvel utilisateur à la base de données.
     *
     * @param user L'utilisateur à ajouter.
     */
    public void createUser(User user) {
        userRepo.createUser(user);
    }
}