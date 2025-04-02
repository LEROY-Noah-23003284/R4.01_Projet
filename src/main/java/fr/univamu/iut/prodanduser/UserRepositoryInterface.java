package fr.univamu.iut.prodanduser;

import java.util.ArrayList;

public interface UserRepositoryInterface {
    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les livres
     */
    public void close();


    /**
     * Méthode retournant le livre dont la référence est passée en paramètre
     * @param mail Clé primaire du User
     * @return un objet User représentant l'utilisateur recherché
     */
    public User getUser( String mail );

    /**
     * Méthode retournant la liste des utilisateurs
     * @return une liste d'objets utilisateur
     */
    public ArrayList<User> getAllUsers() ;

    /**
     * Méthode retournant la liste des utilisateurs
     * @return une liste d'objets utilisateur administrateurs
     */
    public ArrayList<User> getAllUsersAdmin() ;

    /**
     * Méthode retournant la liste des utilisateurs
     * @return une liste d'objets utilisateur non administrateurs
     */
    public ArrayList<User> getAllUsersNonAdmin() ;
}
