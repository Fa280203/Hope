-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : jeu. 16 jan. 2025 à 16:31
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `hope`
--

-- --------------------------------------------------------

--
-- Structure de la table `feedback`
--

CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL,
  `contenu` varchar(255) DEFAULT NULL,
  `date_creation` datetime(6) DEFAULT NULL,
  `outil_id` bigint(20) DEFAULT NULL,
  `utilisateur_id` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `feedback`
--

INSERT INTO `feedback` (`id`, `contenu`, `date_creation`, `outil_id`, `utilisateur_id`, `uuid`) VALUES
(1, 'jupyter notebook', '2025-01-16 16:11:33.374365', 4, 1, '704bf8ff-6303-40d2-aeeb-571f24fc2eec');

-- --------------------------------------------------------

--
-- Structure de la table `outil`
--

CREATE TABLE `outil` (
  `id` bigint(20) NOT NULL,
  `acces` varchar(5000) DEFAULT NULL,
  `boolean_with_default_value` bit(1) NOT NULL,
  `description_detaillee` varchar(5000) DEFAULT NULL,
  `description_simple` varchar(255) DEFAULT NULL,
  `domaine` varchar(255) DEFAULT NULL,
  `lien` varchar(5000) DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `outil`
--

INSERT INTO `outil` (`id`, `acces`, `boolean_with_default_value`, `description_detaillee`, `description_simple`, `domaine`, `lien`, `titre`, `uuid`) VALUES
(1, 'a) Aller sur le lien indiqué\r\nb) Remplir les différents champs sur la page\r\nc) Choisir \"French School of Electronics and Computer Science\" pour l\'école\r\nd) Envoyer un justificatif (idéalement la carte d\'étudiant)\r\ne) Attendre la validation par l\'équipe de GitHub\r\nf) Profiter des différentes offres incluses dans le programme', b'1', 'Sélection d\'outils et de services pour booster votre productivité en tant qu\'élève-ingénieur du numérique. Le GitHub Student Developer Pack vous donne un accès gratuit à un impressionnant catalogue de services et d\'outils. Ex: GitHub pro, GitHub Copilot, DigitalOcean, Outils JetBrains (IntelliJ IDEA Ultimate, PyCharm,...), Azure, Heroku, etc... Une courte vidéo de présentation est disponible ici  :https://www.youtube.com/watch?v=HIVFdN9VGgw', 'git', 'Bouquet de services', 'https://education.github.com/discount_requests/application', 'GitHub Global Campus (GitHub Education)', '65d75b12-7bcb-4502-86e5-4d5da63daa6f'),
(2, 'L\'enseignant partage un lien d\'invitation unique pour rejoindre son espace de cours sur la plateforme Coding Rooms.\r\nOn peut ensuite accéder aux exercices et aux ressources du cours.', b'1', 'Exercices pratiques de programmation, en ligne', 'coding\r\n', 'Codage / Développement', 'https://www.codingrooms.com/', 'Coding Rooms', '5a3c2597-5420-4239-8ef5-5707f7bc8bfb'),
(3, 'a) Allez sur : https://nowledgeable.com/login\r\nb) Saisissez votre adresse mail EFREI\r\nc) Un lien vous sera envoyé pour créer et activer un compte', b'1', 'Exercices pratiques de programmation, en ligne', 'Nowledgeable', 'Codage / Développement', 'https://nowledgeable.com/', 'Nowledgeable', '5656278f-95ce-4cc0-b2c0-1fb1c03b1cc2'),
(4, 'OPTION A\r\na) Installer PyCharm Pro\r\nb) Ouvrir le notebook à partir de l\'IDE.\r\n\r\nOPTION B\r\nInitialiser un notebook depuis Google Colab\r\n\r\nOPTION C\r\na) Installer Anaconda (gratuit)\r\nb) Lancer le notebook Jupyter via le lien présent dans le navigateur Anaconda', b'1', 'Jupyter Notebook est une application web qui vous permet de stocker des lignes de code Python, les résultats de l’exécution de ces dernières (graphiques, tableaux, etc.) et du texte formaté. Il permet donc de combiner code, visualisations et explications dans un même document, ce qui facilite l\'exploration et le partage de données.', 'notebook', 'Codage / Développement', 'https://jupyter.org/', 'Jupyter notebook', 'e35cbecb-800e-4473-b255-cf5b18907c5a'),
(5, 'a) Créer un compte (option gratuite)\r\nb) Vous aurez alors accès à la plateforme et à l\'ensemble de ses services', b'1', 'Exercices pratiques de programmation (notamment de programmation fonctionnelle) en ligne ou via une app', 'replyit', 'Codage / Développement', 'https://replit.com/', 'repl.it', '1871f56b-08e4-45d4-af11-f5f7ee768765'),
(6, 'a) Se connecter à Colab avec son compte Google\r\nb) Créer un nouveau notebook\r\nc) Commencer à coder en Python directement dans le navigateur.', b'1', 'Colab est une plateforme très pratique pour exécuter du code Python sans avoir besoin d\'installer Python localement. Elle est souvent utilisée pour les démonstrations, les TP et les projets en Machine Learning car elle donne accès à des ressources de calcul gratuites (CPU, GPU, TPU).', 'google', 'Codage / Développement', 'https://colab.research.google.com/?hl=fr', 'Google Colaboratory (Colab)', 'ddaf5e11-06cb-4ad7-9678-4e254d2b6a7b'),
(7, 'OPTION I : Utilisation d\'un hyperviseur\r\na) Installer VirtualBox ou VMWare\r\nb) Télécharger un fichier image *.iso\r\nc) L\'ouvrir dans l\'hyperviseur\r\n\r\nOPTION II : Utilisation de Vagrant\r\na) Installer Vagrant (gratuit)\r\nb) Créer sa VM en passant par une box de Vagrant', b'1', 'Machines virtuelles', 'env', 'Hyperviseurs', 'https://jupyter.org/', 'Environnements virtuels', 'e080c34c-8229-41b7-ad57-e665dc730c77'),
(8, 'a) Installer Docker\nb) Aller sur le Docker Hub pour récupérer une ou des image(s) Docker correspondant au(x) besoin(s)\nc) Créer un ou des container(s) à partir de cette/ces image(s)', b'1', 'Containers Docker', '?', 'Containers', 'https://www.docker.com/', 'Environnements virtuels', '3dc1bf60-32fe-4444-a536-3096a06f1593'),
(9, 'a) Aller sur le site https://tryhackme.com/\nb) Cliquer sur connexion\nc) Entrer vos identifiants\nd) Commencer à travailler\n\nRemarque : Il y a 3 plans correspondant à 3 tarifs différents. Le plan gratuit devrait convenir pour la plupart des cours.', b'1', 'TryHackMe est une excellente ressource pour se former à la cybersécurité de manière pratique. Les exercices, souvent présentés sous forme de scénarios réalistes, permettent d\'apprendre en s\'amusant. Des challenges concrets permettent de pratiquer de façon ludique.  #cyber #hacking', '?', 'Cyber sécurité', 'https://tryhackme.com/', 'TryHackme', 'f937ea47-b7a7-4209-a020-9b8a70e0d76a'),
(10, 'Créer un compte standard sur le site via le lien indiqué', b'1', 'Hack The Box est une plateforme de cybersécurité réputée pour ses challenges de type \"capture de drapeaux\" (CTF). Elle propose des machines virtuelles à attaquer légalement pour s\'entraîner au hacking éthique.', '?', 'Cyber sécurité', 'https://www.hackthebox.com/', 'Hack The Box', 'fc946eef-5d83-47b9-a2f7-1977cfa9dcb1'),
(11, '?', b'1', 'Fournisseur Cloud', '?', 'Cloud provider', 'https://signin.aws.amazon.com/signup?request_type=register', 'AWS', '14031f38-9a45-41b0-bdf3-43ab3d5ba472'),
(12, '?', b'1', 'Plateforme d\'AWS de formations en ligne', '?', 'Formations en ligne', 'https://aws.amazon.com/fr/training/awsacademy/', 'AWS Academy', 'cb37791d-e74c-4b56-a25e-a6049028beb5'),
(13, '?', b'1', 'Fournisseur Cloud', '?', 'Cloud provider', 'https://azure.microsoft.com/en-us', 'Azure', 'efef0ee1-3716-4b1b-822b-d9c6cd0e4b89'),
(14, '?', b'1', 'Calcul d\'intégrales en ligne', '?', 'Mathématiques', 'https://www.integral-calculator.com/', 'Integral Calculator', '285c255f-f49b-4ed3-a098-bae5b60a9738'),
(15, '?', b'1', 'Résolution de problèmes mathématiques, étape par étape', '?', 'Mathématiques', 'https://www.emathhelp.net/en/', 'eMathHelp', 'd3b6fd19-69df-4a6b-b28f-a7ec6d1f2478'),
(16, '?', b'1', 'Simulation de programmation de cartes electroniques', '?', 'Electronique', 'https://www.multisim.com/', 'MultisimLive', '0097c902-f852-4691-8788-0268df49e936'),
(17, 'Un accompagnement clair et détaillé est proposé dans les cours utilsant l\'outil', b'1', 'Calcul numérique / Analyse de données', '?', 'Mathématiques', 'https://fr.mathworks.com/products/matlab/student.html', 'MATLAB & Simulink (MathWorks)', 'bf3369e4-04a1-4723-9dd8-c733ed849a78'),
(18, 'S\'informer auprès de l\'enseignant', b'1', 'Modélisation 3D / Rendu RV', '?', 'Réalité virtuelle / augmentée', 'https://resources.lumiscaphe.com/Software_Suite/2023/en/accel-vr.html <br>https://resources.lumiscaphe.com/Software_Suite/2023/en/patchwork-3d.html', 'Patchwork3D & AccelVR (Lumiscaphe)', 'd6890eee-057e-4977-908e-2e7143dba44e'),
(19, 'S\'informer auprès de l\'enseignant', b'1', 'Kaggle est une plateforme web qui fournit des outils et des ressources puissants pour aider à progresser en Data Science. Vous trouverez plus de 50 000 jeux de données publics et 400 000 notebooks publics disponibles pour tous.', '?', 'Data Science', 'https://www.kaggle.com/', 'Kaggle', '4f78f2f1-840a-404d-9c9d-b85ac03a5079'),
(20, 'S\'informer auprès de l\'enseignant', b'1', 'Notion est une application/plateforme de prise de notes, de gestion de projet et de collaboration. Notion est conçu pour permettre aux utilisateurs d\'organiser leurs informations de manière flexible, en utilisant une variété de formats tels que des notes, des bases de données relationnelles, des listes de tâches, des calendriers et des tableaux, le tout dans un seul espace de travail intégré.', '?', 'Gestion de projets et collaboration', 'https://www.notion.so/fr-fr', 'Notion', 'd8614367-6395-41fb-a32b-33de867b6efc'),
(21, 'a) Créer un compte (version gratuite)\nb) Créer un espace puis un premier tableau\nc) Commencer à utiliser la plateforme', b'1', 'Trello est une plateforme de gestion de projet très visuelle et intuitive. Elle est idéale pour les travaux de groupe, car elle permet de suivre facilement l\'avancement des tâches et de communiquer efficacement entre les membres de l\'équipe.', '?', 'Gestion de projets et collaboration', 'https://trello.com/home', 'Trello', '41faf268-ebc7-4452-87b2-c342c74cc367'),
(22, 'S\'informer auprès de l\'enseignant', b'1', 'Code Ocean une plateforme centralisée pour la création, le partage, la publication, la préservation et la réutilisation de code et de données exécutables. Avec Code Ocean, les chercheurs peuvent facilement analyser, organiser et exécuter des travaux de recherche et les publier dans des dépôts et des revues.', '?', 'Data Science', 'https://codeocean.com/', 'Code Ocean', '3cce51d4-5e1a-4d52-a0e2-ff04dc8ad1c7'),
(23, 'La documentation sur le site officiel est très détaillée et suffisamment précise pour permettre une prise en main autonome', b'1', 'Création de slides à prtir de documents Markdown', '?', 'Génération de documents', 'https://marp.app/', 'Marp', '13fb0002-8f4b-45b3-9f64-6e195f49e67d'),
(24, 'La documentation sur le site officiel est très détaillée et suffisamment précise pour permettre une prise en main autonome', b'1', 'LaTeX est un langage et un système de composition de documents', '?', 'Génération de documents', 'https://www.latex-project.org/', 'LaTeX', '7d23b358-5348-48a8-bbb7-1fa5e326522b'),
(25, 'La documentation sur le site officiel est très détaillée et suffisamment précise pour permettre une prise en main autonome', b'1', 'Création de documentation (de code)', '?', 'Génération de documents', 'https://squidfunk.github.io/mkdocs-material/', 'Material for mkdocs', '72ffe5aa-c04d-4666-8d8e-171baeb4476c'),
(26, 'Télécharger et installer', b'1', 'Modélisation conceptuelle de données', '?', 'Bases de données', 'https://www.looping-mcd.fr/', 'Looping', '200a44f7-8404-42b3-8bf1-c768bd9fc1da'),
(27, 'Télécharger et installer', b'1', 'GUI pour MongoDB', '?', 'Bases de données', 'https://www.mongodb.com/products/tools/compass', 'MongoDB Compass', '9b21aebb-c9be-4081-a735-c83cdf9e64f3'),
(28, 'Télécharger et installer', b'1', 'Wireshark est un analyseur de paquets libre et gratuit. Il est notamment utilisé dans le dépannage et l’analyse de réseaux informatiques et le développement de protocoles.', '?', 'Réseaux', 'https://www.wireshark.org/', 'Wireshark', '6be40dba-830e-443e-8774-46bdada11b3f');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` bigint(20) NOT NULL,
  `login` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `role` enum('ADMIN','ENSEIGNANT','ETUDIANT') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `login`, `mot_de_passe`, `nom`, `prenom`, `role`) VALUES
(1, 'lili', '$2a$10$p3ODQW042KvOHVXGzPjKwuz1NrsaukrLDSylDGR0GvqzKAhCGNRJO', 'samb', 'fatou', 'ADMIN'),
(3, 'kiwi', '$2a$10$3Km1kZHFM1FYDWPH0sEfEuiwvE7YjusejcAnfiEZ0WLGRmHYTrGA.', 'Deme', 'kewe', 'ETUDIANT'),
(5, 'ish', '$2a$10$gRyobxb0Oy5gkmzA3mkY8OtUHgzb4I9oRxAEnVNki5VszwtMidKJS', 'Salehuddin', 'ishrat', 'ENSEIGNANT');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKomxx8l4rur537ednjm3goeplj` (`uuid`),
  ADD KEY `FK378wq4xogaym9fb9pnj4ghe2t` (`outil_id`),
  ADD KEY `FKh93mnw7avcaf82edxmm6ju0n6` (`utilisateur_id`);

--
-- Index pour la table `outil`
--
ALTER TABLE `outil`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKm1k35tswkuc3dr7ulunpy6por` (`uuid`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK18vwp4resqussqmlpqnymfqxk` (`login`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `outil`
--
ALTER TABLE `outil`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `FK378wq4xogaym9fb9pnj4ghe2t` FOREIGN KEY (`outil_id`) REFERENCES `outil` (`id`),
  ADD CONSTRAINT `FKh93mnw7avcaf82edxmm6ju0n6` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
