-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 09 jan. 2025 à 14:05
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.0.28

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
-- Structure de la table `outil`
--

CREATE TABLE `outil` (
  `id` bigint(20) NOT NULL,
  `acces` varchar(5000) DEFAULT NULL,
  `description_detaillee` varchar(5000) DEFAULT NULL,
  `description_simple` varchar(255) DEFAULT NULL,
  `domaine` varchar(255) DEFAULT NULL,
  `lien` varchar(5000) DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `boolean_with_default_value` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `outil`
--

INSERT INTO `outil` (`id`, `acces`, `description_detaillee`, `description_simple`, `domaine`, `lien`, `titre`, `boolean_with_default_value`) VALUES
(1, 'a) Aller sur le lien indiqué\nb) Remplir les différents champs sur la page\nc) Choisir \"French School of Electronics and Computer Science\" pour l\'école\nd) Envoyer un justificatif (idéalement la carte d\'étudiant)\ne) Attendre la validation par l\'équipe de GitHub\nf) Profiter des différentes offres incluses dans le programme', 'Sélection d\'outils et de services pour booster votre productivité en tant qu\'élève-ingénieur du numérique. Le GitHub Student Developer Pack vous donne un accès gratuit à un impressionnant catalogue de services et d\'outils. Ex: GitHub pro, GitHub Copilot, DigitalOcean, Outils JetBrains (IntelliJ IDEA Ultimate, PyCharm,...), Azure, Heroku, etc... Une courte vidéo de présentation est disponible ici  :https://www.youtube.com/watch?v=HIVFdN9VGgw', '?', 'Bouquet de services', 'https://education.github.com/discount_requests/application', 'GitHub Global Campus (GitHub Education)', b'0'),
(2, 'L\'enseignant partage un lien d\'invitation unique pour rejoindre son espace de cours sur la plateforme Coding Rooms.\nOn peut ensuite accéder aux exercices et aux ressources du cours.', 'Exercices pratiques de programmation, en ligne', '?', 'Codage / Développement', 'https://www.codingrooms.com/', 'Coding Rooms', b'0'),
(3, 'a) Allez sur : https://nowledgeable.com/login\nb) Saisissez votre adresse mail EFREI\nc) Un lien vous sera envoyé pour créer et activer un compte', 'Exercices pratiques de programmation, en ligne', '?', 'Codage / Développement', 'https://nowledgeable.com/', 'Nowledgeable', b'0'),
(4, 'OPTION A\na) Installer PyCharm Pro\nb) Ouvrir le notebook à partir de l\'IDE.\n\nOPTION B\nInitialiser un notebook depuis Google Colab\n\nOPTION C\na) Installer Anaconda (gratuit)\nb) Lancer le notebook Jupyter via le lien présent dans le navigateur Anaconda', 'Jupyter Notebook est une application web qui vous permet de stocker des lignes de code Python, les résultats de l’exécution de ces dernières (graphiques, tableaux, etc.) et du texte formaté. Il permet donc de combiner code, visualisations et explications dans un même document, ce qui facilite l\'exploration et le partage de données.', '?', 'Codage / Développement', 'https://jupyter.org/', 'Jupyter notebook', b'0'),
(5, 'a) Créer un compte (option gratuite)\nb) Vous aurez alors accès à la plateforme et à l\'ensemble de ses services', 'Exercices pratiques de programmation (notamment de programmation fonctionnelle) en ligne ou via une app', '?', 'Codage / Développement', 'https://replit.com/', 'repl.it', b'0'),
(6, 'a) Se connecter à Colab avec son compte Google\nb) Créer un nouveau notebook\nc) Commencer à coder en Python directement dans le navigateur.', 'Colab est une plateforme très pratique pour exécuter du code Python sans avoir besoin d\'installer Python localement. Elle est souvent utilisée pour les démonstrations, les TP et les projets en Machine Learning car elle donne accès à des ressources de calcul gratuites (CPU, GPU, TPU).', '?', 'Codage / Développement', 'https://colab.research.google.com/?hl=fr', 'Google Colaboratory (Colab)', b'0'),
(7, 'OPTION I : Utilisation d\'un hyperviseur\na) Installer VirtualBox ou VMWare\nb) Télécharger un fichier image *.iso\nc) L\'ouvrir dans l\'hyperviseur\n\nOPTION II : Utilisation de Vagrant\na) Installer Vagrant (gratuit)\nb) Créer sa VM en passant par une box de Vagrant', 'Machines virtuelles', '?', 'Hyperviseurs', '???', 'Environnements virtuels', b'0'),
(8, 'a) Installer Docker\nb) Aller sur le Docker Hub pour récupérer une ou des image(s) Docker correspondant au(x) besoin(s)\nc) Créer un ou des container(s) à partir de cette/ces image(s)', 'Containers Docker', '?', 'Containers', 'https://www.docker.com/', 'Environnements virtuels', b'0'),
(9, 'a) Aller sur le site https://tryhackme.com/\nb) Cliquer sur connexion\nc) Entrer vos identifiants\nd) Commencer à travailler\n\nRemarque : Il y a 3 plans correspondant à 3 tarifs différents. Le plan gratuit devrait convenir pour la plupart des cours.', 'TryHackMe est une excellente ressource pour se former à la cybersécurité de manière pratique. Les exercices, souvent présentés sous forme de scénarios réalistes, permettent d\'apprendre en s\'amusant. Des challenges concrets permettent de pratiquer de façon ludique.  #cyber #hacking', '?', 'Cyber sécurité', 'https://tryhackme.com/', 'TryHackme', b'0'),
(10, 'Créer un compte standard sur le site via le lien indiqué', 'Hack The Box est une plateforme de cybersécurité réputée pour ses challenges de type \"capture de drapeaux\" (CTF). Elle propose des machines virtuelles à attaquer légalement pour s\'entraîner au hacking éthique.', '?', 'Cyber sécurité', 'https://www.hackthebox.com/', 'Hack The Box', b'0'),
(11, '?', 'Fournisseur Cloud', '?', 'Cloud provider', 'https://signin.aws.amazon.com/signup?request_type=register', 'AWS', b'0'),
(12, '?', 'Plateforme d\'AWS de formations en ligne', '?', 'Formations en ligne', 'https://aws.amazon.com/fr/training/awsacademy/', 'AWS Academy', b'0'),
(13, '?', 'Fournisseur Cloud', '?', 'Cloud provider', 'https://azure.microsoft.com/en-us', 'Azure', b'0'),
(14, '?', 'Calcul d\'intégrales en ligne', '?', 'Mathématiques', 'https://www.integral-calculator.com/', 'Integral Calculator', b'0'),
(15, '?', 'Résolution de problèmes mathématiques, étape par étape', '?', 'Mathématiques', 'https://www.emathhelp.net/en/', 'eMathHelp', b'0'),
(16, '?', 'Simulation de programmation de cartes electroniques', '?', 'Electronique', 'https://www.multisim.com/', 'MultisimLive', b'0'),
(17, 'Un accompagnement clair et détaillé est proposé dans les cours utilsant l\'outil', 'Calcul numérique / Analyse de données', '?', 'Mathématiques', 'https://fr.mathworks.com/products/matlab/student.html', 'MATLAB & Simulink (MathWorks)', b'0'),
(18, 'S\'informer auprès de l\'enseignant', 'Modélisation 3D / Rendu RV', '?', 'Réalité virtuelle / augmentée', 'https://resources.lumiscaphe.com/Software_Suite/2023/en/accel-vr.html <br>https://resources.lumiscaphe.com/Software_Suite/2023/en/patchwork-3d.html', 'Patchwork3D & AccelVR (Lumiscaphe)', b'0'),
(19, 'S\'informer auprès de l\'enseignant', 'Kaggle est une plateforme web qui fournit des outils et des ressources puissants pour aider à progresser en Data Science. Vous trouverez plus de 50 000 jeux de données publics et 400 000 notebooks publics disponibles pour tous.', '?', 'Data Science', 'https://www.kaggle.com/', 'Kaggle', b'0'),
(20, 'S\'informer auprès de l\'enseignant', 'Notion est une application/plateforme de prise de notes, de gestion de projet et de collaboration. Notion est conçu pour permettre aux utilisateurs d\'organiser leurs informations de manière flexible, en utilisant une variété de formats tels que des notes, des bases de données relationnelles, des listes de tâches, des calendriers et des tableaux, le tout dans un seul espace de travail intégré.', '?', 'Gestion de projets et collaboration', 'https://www.notion.so/fr-fr', 'Notion', b'0'),
(21, 'a) Créer un compte (version gratuite)\nb) Créer un espace puis un premier tableau\nc) Commencer à utiliser la plateforme', 'Trello est une plateforme de gestion de projet très visuelle et intuitive. Elle est idéale pour les travaux de groupe, car elle permet de suivre facilement l\'avancement des tâches et de communiquer efficacement entre les membres de l\'équipe.', '?', 'Gestion de projets et collaboration', 'https://trello.com/home', 'Trello', b'0'),
(22, 'S\'informer auprès de l\'enseignant', 'Code Ocean une plateforme centralisée pour la création, le partage, la publication, la préservation et la réutilisation de code et de données exécutables. Avec Code Ocean, les chercheurs peuvent facilement analyser, organiser et exécuter des travaux de recherche et les publier dans des dépôts et des revues.', '?', 'Data Science', 'https://codeocean.com/', 'Code Ocean', b'0'),
(23, 'La documentation sur le site officiel est très détaillée et suffisamment précise pour permettre une prise en main autonome', 'Création de slides à prtir de documents Markdown', '?', 'Génération de documents', 'https://marp.app/', 'Marp', b'0'),
(24, 'La documentation sur le site officiel est très détaillée et suffisamment précise pour permettre une prise en main autonome', 'LaTeX est un langage et un système de composition de documents', '?', 'Génération de documents', 'https://www.latex-project.org/', 'LaTeX', b'0'),
(25, 'La documentation sur le site officiel est très détaillée et suffisamment précise pour permettre une prise en main autonome', 'Création de documentation (de code)', '?', 'Génération de documents', 'https://squidfunk.github.io/mkdocs-material/', 'Material for mkdocs', b'0'),
(26, 'Télécharger et installer', 'Modélisation conceptuelle de données', '?', 'Bases de données', 'https://www.looping-mcd.fr/', 'Looping', b'0'),
(27, 'Télécharger et installer', 'GUI pour MongoDB', '?', 'Bases de données', 'https://www.mongodb.com/products/tools/compass', 'MongoDB Compass', b'0'),
(28, 'Télécharger et installer', 'Wireshark est un analyseur de paquets libre et gratuit. Il est notamment utilisé dans le dépannage et l’analyse de réseaux informatiques et le développement de protocoles.', '?', 'Réseaux', 'https://www.wireshark.org/', 'Wireshark', b'0'),
(29, 'gcfhjklm', NULL, 'poijuh', 'oijuhgv', 'https://www.google.com/search?q=mhd&rlz=1C1GCEU_frFR1075FR1075&oq=mhd&gs_lcrp=EgZjaHJvbWUyBggAEEUYOdIBCDEwNTBqMGo3qAIAsAIA&sourceid=chrome&ie=UTF-8', 'bonjoiuhygv', b'1'),
(30, 'sdf', NULL, 'fg', 'dfvgb', 'https://www.google.com/search?q=tarte+aux+fraises&rlz=1C1GCEU_frFR1075FR1075&oq=tarte+aux+fraises&gs_lcrp=EgZjaHJvbWUyBggAEEUYOdIBCDI2MThqMGo3qAIAsAIA&sourceid=chrome&ie=UTF-8', 'hello', b'0'),
(31, 'szde', NULL, 'sxcdv', 'sdcfvbghn', 'https://www.google.com/search?q=tarte+aux+fraises&rlz=1C1GCEU_frFR1075FR1075&oq=tarte+aux+fraises&gs_lcrp=EgZjaHJvbWUyBggAEEUYOdIBCDI2MThqMGo3qAIAsAIA&sourceid=chrome&ie=UTF-8', 'test', b'0');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `outil`
--
ALTER TABLE `outil`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `outil`
--
ALTER TABLE `outil`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
