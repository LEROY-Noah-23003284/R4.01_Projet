package fr.univamu.iut.prodanduser.user;

/**
 * Classe représentant un utilisateur du système.
 */
public class User {

    /**
     * Indique si l'utilisateur est administrateur (1 pour admin, 0 pour utilisateur standard).
     */
    protected int admin;

    /**
     * Nom de l'utilisateur.
     */
    protected String name;

    /**
     * Mot de passe de l'utilisateur.
     */
    protected String pwd;

    /**
     * Adresse email de l'utilisateur.
     */
    protected String mail;

    /**
     * Constructeur par défaut.
     */
    public User() {
    }

    /**
     * Constructeur de la classe User.
     * @param mail Adresse email de l'utilisateur.
     * @param name Nom de l'utilisateur.
     * @param admin Statut administrateur (1 pour admin, 0 pour utilisateur standard).
     * @param pwd Mot de passe de l'utilisateur.
     */
    public User(String mail, String name, int admin, String pwd) {
        this.admin = admin;
        this.name = name;
        this.pwd = pwd;
        this.mail = mail;
    }

    /**
     * Obtient le statut administrateur de l'utilisateur.
     * @return 1 si l'utilisateur est admin, 0 sinon.
     */
    public int getAdmin() {
        return admin;
    }

    /**
     * Obtient le nom de l'utilisateur.
     * @return Nom de l'utilisateur.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtient le mot de passe de l'utilisateur.
     * @return Mot de passe de l'utilisateur.
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * Obtient l'adresse email de l'utilisateur.
     * @return Adresse email de l'utilisateur.
     */
    public String getMail() {
        return mail;
    }

    /**
     * Définit le statut administrateur de l'utilisateur.
     * @param admin 1 pour admin, 0 pour utilisateur standard.
     */
    public void setAdmin(int admin) {
        this.admin = admin;
    }

    /**
     * Définit le nom de l'utilisateur.
     * @param name Nom de l'utilisateur.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Définit le mot de passe de l'utilisateur.
     * @param pwd Mot de passe de l'utilisateur.
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * Définit l'adresse email de l'utilisateur.
     * @param mail Adresse email de l'utilisateur.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Retourne une représentation textuelle de l'utilisateur.
     * @return Chaîne de caractères représentant l'utilisateur.
     */
    @Override
    public String toString() {
        return "User{" +
                "mail='" + mail + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", admin='" + admin +
                "}";
    }
}
