package fr.univamu.iut.prodanduser;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.sql.SQLException;
import java.util.ArrayList;

@ApplicationScoped
public class UserService {

    protected UserRepositoryInterface userRepo;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param userRepo objet implémentant l'interface d'accès aux données
     */
    @Inject
    public UserService( UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    public UserService() {}

    /**
     * Méthode retournant les informations sur les utilisateurs au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllUsersJSON(){
        ArrayList<User> allUsers = userRepo.getAllUsers();

        // création du json et conversion de la liste de livres
        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allUsers);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }

    /**
     * Méthode retournant les informations sur les utilisateurs administrateurs au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllUsersAdminJSON(){
        ArrayList<User> allUsers = userRepo.getAllUsersAdmin();

        // création du json et conversion de la liste de livres
        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allUsers);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }

    /**
     * Méthode retournant les informations sur les utilisateurs non administrateurs au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllUsersNonAdminJSON(){
        ArrayList<User> allUsers = userRepo.getAllUsersNonAdmin();

        // création du json et conversion de la liste de livres
        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allUsers);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }

    /**
     * Méthode retournant au format JSON les informations sur un utilisateur recherché
     * @param mail l'identifiant de l'utilisateur recherché
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getUserJSON( String mail ){
        String result = null;
        User myUser = userRepo.getUser(mail);

        // si le livre a été trouvé
        if( myUser != null ) {

            // création du json et conversion du livre
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myUser);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }
}