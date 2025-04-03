<?php
namespace data;

class ApiClient {
    private $baseUrl = "http://localhost:8080/prodAndUser-1.0-SNAPSHOT/api";

    public function getUser($mail) {
        $response = file_get_contents($this->baseUrl . "/user/" . urlencode($mail));
        return json_decode($response, true);
    }

    public function getAllUsers() {
        $response = file_get_contents($this->baseUrl . "/user");
        return json_decode($response, true);
    }
    public function getAllAdmin() {
        $response = file_get_contents($this->baseUrl . "/user/admin");
        return json_decode($response, true);
    }

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