-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 16 jan. 2021 à 22:32
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `centreformation`
--

-- --------------------------------------------------------

--
-- Structure de la table `donner`
--

CREATE TABLE `donner` (
  `IdFormation` int(11) NOT NULL,
  `IdUtilisateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `donner`
--

INSERT INTO `donner` (`IdFormation`, `IdUtilisateur`) VALUES
(1, 22),
(4, 22),
(5, 22),
(8, 22),
(17, 22),
(1, 13),
(4, 13),
(5, 13),
(8, 13),
(17, 13),
(18, 14),
(23, 14),
(25, 14),
(26, 14),
(28, 14),
(29, 14),
(30, 14),
(9, 15),
(10, 15),
(14, 15),
(16, 15);

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `IdFormation` int(11) NOT NULL,
  `Intitule` varchar(50) NOT NULL,
  `Prix` float NOT NULL,
  `Duree` int(11) NOT NULL,
  `MaxParticipant` int(11) NOT NULL,
  `NbreParticipantMin` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`IdFormation`, `Intitule`, `Prix`, `Duree`, `MaxParticipant`, `NbreParticipantMin`, `enable`) VALUES
(1, 'anglais niveau A1', 120, 60, 30, 0, 1),
(4, 'java', 300, 300, 20, 0, 1),
(5, 'certification java se 11', 300, 60, 50, 0, 1),
(8, 'introduction a la programmation c#', 250, 120, 100, 0, 1),
(9, 'anglais niveau A2', 300, 120, 30, 0, 1),
(10, 'anglais niveau b1', 250, 300, 20, 0, 1),
(14, 'fiscalite de base', 600, 200, 40, 0, 1),
(16, 'fiscalite de l\'entreprise', 600, 200, 30, 0, 1),
(17, 'projet d\'integration de developpement', 300, 90, 30, 0, 1),
(18, 'Stage d\'integration prof', 80, 40, 20, 0, 1),
(23, 'epreuve integre en informatique de gestion ', 360, 120, 30, 0, 1),
(25, 'Développement Android débutant', 500, 90, 25, 0, 1),
(26, 'Développement Android débutant', 500, 90, 25, 0, 1),
(28, 'Developpement IOS ', 250, 60, 20, 0, 1),
(29, 'Formation MIC ', 600, 30, 30, 0, 1),
(30, 'JavaScript', 150, 90, 60, 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `inscrire`
--

CREATE TABLE `inscrire` (
  `IdUtilisateur` int(255) NOT NULL,
  `IdSession` int(255) NOT NULL,
  `EstPaye` tinyint(255) DEFAULT NULL,
  `Signalisation` tinyint(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `inscrire`
--

INSERT INTO `inscrire` (`IdUtilisateur`, `IdSession`, `EstPaye`, `Signalisation`) VALUES
(3, 1, 1, NULL),
(5, 1, 1, NULL),
(7, 1, 1, NULL),
(10, 1, 1, NULL),
(11, 1, 1, NULL),
(12, 1, 1, NULL),
(5, 4, NULL, NULL),
(5, 4, NULL, NULL),
(13, 1, NULL, NULL),
(13, 2, NULL, NULL),
(14, 3, NULL, NULL),
(14, 4, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `locaux`
--

CREATE TABLE `locaux` (
  `IdLocal` int(255) NOT NULL,
  `DenomLocal` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `locaux`
--

INSERT INTO `locaux` (`IdLocal`, `DenomLocal`) VALUES
(1, 'a100'),
(2, 'a101'),
(3, 'a102'),
(4, 'a103'),
(5, 'a104'),
(6, 'a105'),
(7, 'a106'),
(8, 'a107'),
(9, 'a108'),
(10, 'a109'),
(11, 'a110'),
(12, 'a111'),
(13, 'a112'),
(14, 'a113'),
(15, 'a114'),
(16, 'a115'),
(17, 'a116'),
(18, 'a117'),
(19, 'a118'),
(20, 'a119');

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `IdRole` int(255) NOT NULL,
  `DenomRole` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`IdRole`, `DenomRole`) VALUES
(1, 'admin'),
(2, 'stagiaire'),
(3, 'enseignant');

-- --------------------------------------------------------

--
-- Structure de la table `session`
--

CREATE TABLE `session` (
  `IdSession` int(255) NOT NULL,
  `IdFormation` int(255) NOT NULL,
  `IdFormateur` int(255) NOT NULL,
  `DateDebut` datetime NOT NULL,
  `DateFin` datetime NOT NULL,
  `Local` int(255) NOT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `session`
--

INSERT INTO `session` (`IdSession`, `IdFormation`, `IdFormateur`, `DateDebut`, `DateFin`, `Local`, `enable`) VALUES
(1, 1, 1, '2020-11-20 11:35:49', '2021-01-20 18:05:19', 1, 1),
(2, 4, 3, '2020-11-26 11:37:20', '2021-01-26 18:05:45', 2, 1),
(3, 5, 5, '2020-12-02 11:37:44', '2021-03-02 18:06:05', 3, 1),
(4, 8, 7, '2021-01-11 11:38:35', '2021-04-11 18:06:43', 4, 1),
(5, 9, 10, '2021-01-14 11:39:06', '2021-04-14 18:06:57', 5, 1),
(6, 1, 14, '2020-11-26 18:44:11', '2021-02-26 18:07:13', 6, 1),
(7, 4, 15, '2020-11-20 18:45:17', '2021-02-20 18:07:29', 7, 1);

-- --------------------------------------------------------

--
-- Structure de la table `status`
--

CREATE TABLE `status` (
  `IdStatus` int(255) NOT NULL,
  `DenomStatus` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `status`
--

INSERT INTO `status` (`IdStatus`, `DenomStatus`) VALUES
(1, 'etudiant'),
(2, 'demandeur d\'emplois'),
(3, 'enseignant');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `IdUtilisateur` int(11) NOT NULL,
  `Nom` varchar(50) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `Adresse` varchar(255) NOT NULL,
  `Telephone` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Login` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Role` int(11) NOT NULL,
  `Status` int(11) DEFAULT NULL,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`IdUtilisateur`, `Nom`, `Prenom`, `Adresse`, `Telephone`, `Email`, `Login`, `Password`, `Role`, `Status`, `enable`) VALUES
(1, 'Gandouzz', 'el aminz', 'rue de l\'école 666z', '0486 666 666z', 'amin1910@hotmail.com', 'amin', 'amin1910', 1, NULL, 1),
(3, 'luis', 'save', 'rue du souvenir 107', '0251515151', 'luis', 'luis1234', 'stagiaire', 2, 1, 1),
(5, 'knuts', 'jordan', 'rue de douvre 13', '0485858585', 'jordan@gmail.com', 'jordan', 'jordan1234', 2, 1, 1),
(7, 'bizidudu', 'demandeur d emploie', 'rue bbc echarpe 30 ', '0486667788', 'olivier@hotmail.com', 'olivier', 'olivier1234', 2, 1, 1),
(10, 'krs', ' olivier', 'r', '9', 'krs@gmail.com', 'olivier', 'olivier1234', 2, 1, 1),
(11, 'a', ' a', 'a', 'a', 'a', 'pierre', 'pierre1234', 2, 1, 1),
(12, 'b', ' b', 'rue de la f ', '1452', '@', 'fabien', 'fabien1234', 2, 1, 1),
(13, 'formateurAA', 'formatzueAA', 'adresse formateurA ', '0125544', 'formateurA@epfc.eu', 'formateurA', 'formateurA1234', 3, 3, 1),
(14, 'formateurB', 'formateurB', 'rue de la formation ', '25253636', 'formateurB@formateur', 'formateurb', 'formateurb1234', 3, 3, 1),
(15, 'formateurC', 'formateurC', 'rue de la formation ', '89898989', 'formateurC@formateur', 'formateurc', 'formateurc1234', 3, 3, 1),
(16, 'marc', 'pichot', 'il habite eterbeek', '454545', 'marc@marc.be', 'marc', 'marc1234', 2, 1, 1),
(17, 'ponente', 'carmelo', 'rue des brodeurs 9 1000 bruxelles', '0485968574', 'carmelo@carmelo.be', 'carmelo', 'carmelo1234', 2, 1, 1),
(22, 'Pennel', 'Benoit', 'EPFC', '02555444666', 'pennel@epfc.eu', 'pennel', 'pennel1234', 3, 3, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`IdFormation`) USING BTREE,
  ADD KEY `IdFormation` (`IdFormation`),
  ADD KEY `IdFormation_2` (`IdFormation`),
  ADD KEY `IdFormation_3` (`IdFormation`),
  ADD KEY `IdFormation_4` (`IdFormation`),
  ADD KEY `IdFormation_5` (`IdFormation`),
  ADD KEY `IdFormation_6` (`IdFormation`),
  ADD KEY `IdFormation_7` (`IdFormation`),
  ADD KEY `IdFormation_8` (`IdFormation`),
  ADD KEY `IdFormation_9` (`IdFormation`),
  ADD KEY `IdFormation_10` (`IdFormation`),
  ADD KEY `IdFormation_11` (`IdFormation`),
  ADD KEY `IdFormation_12` (`IdFormation`);

--
-- Index pour la table `inscrire`
--
ALTER TABLE `inscrire`
  ADD KEY `numUtilisateur` (`IdUtilisateur`),
  ADD KEY `numSession` (`IdSession`);

--
-- Index pour la table `locaux`
--
ALTER TABLE `locaux`
  ADD PRIMARY KEY (`IdLocal`) USING BTREE;

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`IdRole`);

--
-- Index pour la table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`IdSession`) USING BTREE,
  ADD KEY `idSession` (`IdSession`),
  ADD KEY `numLocal` (`Local`),
  ADD KEY `IdSession_2` (`IdSession`),
  ADD KEY `numFormateur` (`IdFormateur`),
  ADD KEY `numFormation` (`IdFormation`);

--
-- Index pour la table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`IdStatus`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`IdUtilisateur`) USING BTREE,
  ADD KEY `idUtilisateur` (`IdUtilisateur`),
  ADD KEY `IdUtilisateur_2` (`IdUtilisateur`),
  ADD KEY `IdUtilisateur_3` (`IdUtilisateur`),
  ADD KEY `IdUtilisateur_4` (`IdUtilisateur`),
  ADD KEY `IdUtilisateur_5` (`IdUtilisateur`),
  ADD KEY `IdUtilisateur_6` (`IdUtilisateur`),
  ADD KEY `IdUtilisateur_7` (`IdUtilisateur`),
  ADD KEY `numRole` (`Role`),
  ADD KEY `numStatus` (`Status`),
  ADD KEY `IdUtilisateur_8` (`IdUtilisateur`),
  ADD KEY `IdUtilisateur_9` (`IdUtilisateur`),
  ADD KEY `IdUtilisateur_10` (`IdUtilisateur`),
  ADD KEY `IdUtilisateur_11` (`IdUtilisateur`),
  ADD KEY `IdUtilisateur_12` (`IdUtilisateur`),
  ADD KEY `IdUtilisateur_13` (`IdUtilisateur`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
  MODIFY `IdFormation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT pour la table `locaux`
--
ALTER TABLE `locaux`
  MODIFY `IdLocal` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `IdRole` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `session`
--
ALTER TABLE `session`
  MODIFY `IdSession` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `status`
--
ALTER TABLE `status`
  MODIFY `IdStatus` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `IdUtilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `inscrire`
--
ALTER TABLE `inscrire`
  ADD CONSTRAINT `numSession` FOREIGN KEY (`IdSession`) REFERENCES `session` (`IdSession`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `numUtilisateur` FOREIGN KEY (`IdUtilisateur`) REFERENCES `utilisateur` (`IdUtilisateur`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `numFormateur` FOREIGN KEY (`IdFormateur`) REFERENCES `utilisateur` (`IdUtilisateur`),
  ADD CONSTRAINT `numFormation` FOREIGN KEY (`IdFormation`) REFERENCES `formation` (`IdFormation`),
  ADD CONSTRAINT `numLocal` FOREIGN KEY (`Local`) REFERENCES `locaux` (`IdLocal`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `numRole` FOREIGN KEY (`Role`) REFERENCES `roles` (`IdRole`),
  ADD CONSTRAINT `numStatus` FOREIGN KEY (`Status`) REFERENCES `status` (`IdStatus`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
