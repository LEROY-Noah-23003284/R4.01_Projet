package fr.univamu.iut.prodanduser;

/**
 * Classe représentant un user
 */
public class User {

    /**
     * Admin du user
     */
    protected int admin;

    /**
     * nom du user
     */
    protected String name;

    /**
     * Auteurs du user
     */
    protected String pwd;

    /**
     * Mail du user
     * ('r' pour réservé, 'e' pour emprunté, et 'd' pour disponible)
     */
    protected String mail;

    /**
     * Constructeur par défaut
     */
    public User(){
    }

    /**
     * Constructeur de user
     * @param admin admin du user
     * @param name nom du user
     * @param pwd auteurs du user
     */
    public User(String mail, String name, int admin, String pwd) {
        this.admin = admin;
        this.name = name;
        this.pwd = pwd;
        this.mail = mail;
    }

    /**
     * Méthode permettant d'accéder à la réference du user
     * @return un chaîne de caractères avec la admin du user
     */
    public int getAdmin() {
        return admin;
    }

    /**
     * Méthode permettant d'accéder au nom du user
     * @return un chaîne de caractères avec le nom du user
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode permettant d'accéder aux auteurs du user
     * @return un chaîne de caractères avec la liste des auteurs
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * Méthode permettant d'accéder au mail du user
     * @return un caractère indiquant lestatu du user ('r' pour réservé, 'e' pour emprunté, et 'd' pour disponible)
     */
    public String getMail() {
        return mail;
    }

    /**
     * Méthode permettant de modifier la admin du user
     * @param admin une chaîne de caractères avec la admin à utiliser
     */
    public void setAdmin(int admin) {
        this.admin = admin;
    }

    /**
     * Méthode permettant de modifier le nom du user
     * @param name une chaîne de caractères avec le nom à utiliser
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Méthode permettant de modifier les pwd du user
     * @param pwd une chaîne de caractères avec la liste des auteurs
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * Méthode permettant de modifier le mail du user
     * @param mail le caractère 'r' pour réservé, 'e' pour emprunté, ou 'd' pour disponible
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "User{" +
                ", mail=" + mail + '\'' +
                ", nom='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                "admin='" + admin +
                '}';
    }
}