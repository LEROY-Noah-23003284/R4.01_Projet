<?php
namespace data;

/**
 * Class ApiClient
 *
 * Gère les interactions avec une API externe REST pour les utilisateurs.
 * Cette classe permet de récupérer les utilisateurs, les administrateurs
 * et de gérer l'authentification via des requêtes HTTP.
 *
 * @package data
 */
class ApiClient {

    /**
     * L'URL de base de l'API.
     *
     * @var string
     */
    private $baseUrl = "http://localhost:8080/prodAndUser-1.0-SNAPSHOT/api";

    /**
     * Récupère les informations d'un utilisateur via son adresse email.
     *
     * @param string $mail L'adresse email de l'utilisateur.
     * @return array|null Les données de l'utilisateur ou null en cas d'erreur.
     */
    public function getUser($mail) {
        $response = file_get_contents($this->baseUrl . "/user/" . urlencode($mail));
        return json_decode($response, true);
    }

    /**
     * Récupère tous les utilisateurs.
     *
     * @return array|null Liste de tous les utilisateurs ou null en cas d'erreur.
     */
    public function getAllUsers() {
        $response = file_get_contents($this->baseUrl . "/user");
        return json_decode($response, true);
    }

    /**
     * Récupère tous les utilisateurs avec un rôle administrateur.
     *
     * @return array|null Liste des administrateurs ou null en cas d'erreur.
     */
    public function getAllAdmin() {
        $response = file_get_contents($this->baseUrl . "/user/admin");
        return json_decode($response, true);
    }

    /**
     * Authentifie un utilisateur via son email et son mot de passe.
     *
     * @param string $email L'adresse email de l'utilisateur.
     * @param string $password Le mot de passe de l'utilisateur.
     * @return array|null Les données de l'utilisateur authentifié ou null en cas d'erreur.
     */
    public function login($email, $password) {
        $data = json_encode(['mail' => $email, 'pwd' => $password]);
        $context = stream_context_create([
            'http' => [
                'method' => 'POST',
                'header' => "Content-Type: application/json\r\n",
                'content' => $data
            ]
        ]);
        return json_decode(file_get_contents($this->baseUrl . "/user/login", false, $context), true);
    }
}
