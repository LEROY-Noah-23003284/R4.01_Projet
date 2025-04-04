<?php
/**
 * Point d'entrée principal de l'application web "Annonces".
 *
 * Ce fichier initialise les dépendances (modèles, contrôleurs, vues, services, API),
 * établit la connexion à la base de données,
 * gère l'authentification utilisateur et la création de compte,
 * et route les différentes requêtes vers les contrôleurs/vues appropriés.
 *
 * @author Nathan
 * @version 1.0
 */

// Chargement des bibliothèques nécessaires (modèles, contrôleurs, services, vues, APIs)
include_once 'data/AnnonceSqlAccess.php';
include_once 'data/UserSqlAccess.php';
include_once 'data/ApiAlternance.php';
include_once 'data/ApiEmploi.php';
include_once 'data/ApiClient.php';

include_once 'control/Controllers.php';
include_once 'control/Presenter.php';

include_once 'service/AnnoncesChecking.php';
include_once 'service/UserChecking.php';
include_once 'service/UserCreation.php';

include_once 'gui/Layout.php';
include_once 'gui/ViewLogin.php';
include_once 'gui/ViewAnnonces.php';
include_once 'gui/ViewPost.php';
include_once 'gui/ViewError.php';
include_once 'gui/ViewCreate.php';
include_once 'gui/ViewAnnoncesAlternance.php';
include_once 'gui/ViewCompanyAlternance.php';
include_once 'gui/ViewAnnoncesEmploi.php';
include_once 'gui/ViewOffreEmploi.php';

// Alias pour un chargement plus lisible des classes
use gui\{ViewLogin, ViewAnnonces, ViewPost, ViewError, ViewCreate, Layout, ViewAnnoncesAlternance, ViewCompanyAlternance, ViewAnnoncesEmploi, ViewOffreEmploi};
use control\{Controllers, Presenter};
use data\{AnnonceSqlAccess, UserSqlAccess, ApiAlternance, ApiEmploi};
use service\{AnnoncesChecking, UserChecking, UserCreation};

// Connexion à la base de données
$data = null;
try {
    $bd = new PDO('mysql:host=mysql-thelu.alwaysdata.net;dbname=thelu_annonces_db', 'thelu_annonces', 'theluNathan2005');
    $dataAnnonces = new AnnonceSqlAccess($bd);
    $dataUsers = new UserSqlAccess($bd);
} catch (PDOException $e) {
    print "Erreur de connexion !: " . $e->getMessage() . "<br/>";
    die();
}

// Initialisation des composants principaux
$controller = new Controllers();
$annoncesCheck = new AnnoncesChecking();
$userCheck = new UserChecking();
$userCreation = new UserCreation();
$presenter = new Presenter($annoncesCheck);

$apiAlternance = new ApiAlternance();
$apiEmploi = new ApiEmploi();

// Récupération de l'URI demandée
$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);

// Démarrage de la session si nécessaire
if (session_status() === PHP_SESSION_NONE) {
    ini_set('session.gc_maxlifetime', 3600);
    session_set_cookie_params(3600);
    session_start();
}

// Authentification obligatoire sauf sur certaines pages
if ($uri !== '/' && $uri !== '/index.php' && $uri !== '/index.php/logout' && $uri !== '/index.php/create') {
    $error = $controller->authenticateAction($userCreation, $userCheck, $dataUsers);

    if ($error !== null) {
        $uri = '/index.php/error';

        // Redirection en fonction du type d'erreur
        if ($error === 'bad login or pwd' || $error === 'not connected') {
            $redirect = '/index.php';
        } elseif ($error === 'creation impossible') {
            $redirect = '/index.php/create';
        }
    }
}

// ROUTAGE des différentes pages

// Page de connexion (ou déconnexion)
if ($uri === '/' || $uri === '/index.php' || $uri === '/index.php/logout') {
    session_destroy();
    $layout = new Layout("gui/layout.html");
    $vueLogin = new ViewLogin($layout);
    $vueLogin->display();
}
// Formulaire de création de compte
elseif ($uri === '/index.php/create') {
    $layout = new Layout("gui/layout.html");
    $vueCreate = new ViewCreate($layout);
    $vueCreate->display();
}
// Liste des annonces depuis la base de données
elseif ($uri === '/index.php/annonces') {
    $controller->annoncesAction($dataAnnonces, $annoncesCheck);
    $layout = new Layout("gui/layoutLogged.html");
    $vueAnnonces = new ViewAnnonces($layout, $_SESSION['login'], $presenter);
    $vueAnnonces->display();
}
// Détail d'une annonce spécifique
elseif ($uri === '/index.php/post' && isset($_GET['id'])) {
    $controller->postAction($_GET['id'], $dataAnnonces, $annoncesCheck);
    $layout = new Layout("gui/layoutLogged.html");
    $vuePost = new ViewPost($layout, $_SESSION['login'], $presenter);
    $vuePost->display();
}
// Page d'erreur
elseif ($uri === '/index.php/error') {
    $layout = new Layout("gui/layout.html");
    $vueError = new ViewError($layout, $error, $redirect);
    $vueError->display();
}
// Annonces d'alternance (depuis API)
elseif ($uri === '/index.php/annoncesAlternance') {
    $controller->annoncesAction($apiAlternance, $annoncesCheck);
    $layout = new Layout("gui/layoutLogged.html");
    $vueAnnoncesAlternance = new ViewAnnoncesAlternance($layout, $_SESSION['login'], $presenter);
    $vueAnnoncesAlternance->display();
}
// Détail d'une entreprise d'alternance (depuis API)
elseif ($uri === '/index.php/companyAlternance' && isset($_GET['id'])) {
    $controller->postAction($_GET['id'], $apiAlternance, $annoncesCheck);
    $layout = new Layout("gui/layoutLogged.html");
    $vuePostAlternance = new ViewCompanyAlternance($layout, $_SESSION['login'], $presenter);
    $vuePostAlternance->display();
}
// Liste des offres d'emploi (depuis API)
elseif ($uri === '/index.php/annoncesEmploi') {
    $controller->annoncesAction($apiEmploi, $annoncesCheck);
    $layout = new Layout("gui/layoutLogged.html");
    $vueAnnoncesEmploi = new ViewAnnoncesEmploi($layout, $_SESSION['login'], $presenter);
    $vueAnnoncesEmploi->display();
}
// Détail d'une offre d'emploi (depuis API)
elseif ($uri === '/index.php/offreEmploi' && isset($_GET['id'])) {
    $controller->postAction($_GET['id'], $apiEmploi, $annoncesCheck);
    $layout = new Layout("gui/layoutLogged.html");
    $vuePostEmploi = new ViewOffreEmploi($layout, $_SESSION['login'], $presenter);
    $vuePostEmploi->display();
}
// Test d'appel à l'API des utilisateurs
elseif ($uri === '/index.php/api-test') {
    $layout = new Layout("gui/layoutLogged.html");
    $content = "<h2>Réponse de l'API</h2>";

    try {
        $apiClient = new \data\ApiClient();
        $users = $apiClient->getAllUsers();
        $content .= "<pre>" . print_r($users, true) . "</pre>";
    } catch (Exception $e) {
        $content .= "Erreur: " . $e->getMessage();
    }

    echo $layout->display("Test API", "", $content);
}
// Test d'appel à l'API pour les admins uniquement
elseif ($uri === '/index.php/admin-user') {
    $layout = new Layout("gui/layoutLogged.html");
    $content = "<h2>Réponse de l'API</h2>";

    try {
        $apiClient = new \data\ApiClient();
        $users = $apiClient->getAllAdmin();
        $content .= "<pre>" . print_r($users, true) . "</pre>";
    } catch (Exception $e) {
        $content .= "Erreur: " . $e->getMessage();
    }

    echo $layout->display("Test API", "", $content);
}
// 404 Not Found pour les chemins inconnus
else {
    header('Status: 404 Not Found');
    echo '<html><body><h1>My Page NotFound</h1></body></html>';
}
?>
