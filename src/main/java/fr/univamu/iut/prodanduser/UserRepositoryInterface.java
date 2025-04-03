package fr.univamu.iut.prodanduser.user;

import java.util.ArrayList;

/**
 * Interface définissant les opérations possibles sur un dépôt d'utilisateurs.
 */
public interface UserRepositoryInterface {

    /**
     * Ferme la connexion au dépôt des utilisateurs.
     */
    public void close();

    /**
     * Recherche un utilisateur par son adresse email.
     *
     * @param mail Adresse email de l'utilisateur (clé primaire).
     * @return Un objet {@link User} correspondant à l'email fourni, ou null si l'utilisateur n'existe pas.
     */
    public User getUser(String mail);

    /**
     * Récupère la liste de tous les utilisateurs.
     *
     * @return Une liste contenant tous les utilisateurs.
     */
    public ArrayList<User> getAllUsers();

    /**
     * Récupère la liste de tous les utilisateurs ayant un statut administrateur.
     *
     * @return Une liste d'objets {@link User} correspondant aux administrateurs.
     */
    public ArrayList<User> getAllUsersAdmin();

    /**
     * Récupère la liste de tous les utilisateurs qui ne sont pas administrateurs.
     *
     * @return Une liste d'objets {@link User} correspondant aux utilisateurs non administrateurs.
     */
    public ArrayList<User> getAllUsersNonAdmin();

    /**
     * Ajoute un nouvel utilisateur à la base de données.
     *
     * @param user L'objet {@link User} représentant l'utilisateur à ajouter.
     */
    public void createUser(User user);

    /**
     * Met à jour les informations d'un utilisateur existant.
     *
     * @param user L'objet {@link User} contenant les nouvelles informations.
     */
    public void updateUser(User user);

    /**
     * Supprime un utilisateur de la base de données en fonction de son adresse email.
     *
     * @param mail L'adresse email de l'utilisateur à supprimer.
     */
    public void deleteUser(String mail);
}