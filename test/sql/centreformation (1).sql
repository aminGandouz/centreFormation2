-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : Dim 14 fév. 2021 à 16:52
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

DELIMITER $$
--
-- Procédures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_cleanDB` ()  UPDATE `session` SET `enable`= false where DATEDIFF(now(),session.DateFin)>365$$

DELIMITER ;

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
(16, 15),
(1, 32),
(4, 32);

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
(4, 'java', 300, 300, 20, 0, 0),
(5, 'certification java se 11', 300, 60, 37, 0, 1),
(8, 'introduction a la programmation c#', 250, 120, 35, 0, 1),
(9, 'anglais niveau A2', 300, 120, 30, 0, 1),
(10, 'anglais niveau b1', 250, 300, 20, 0, 1),
(14, 'fiscalite de base', 600, 200, 40, 0, 1),
(16, 'fiscalite de l\'entreprise', 600, 200, 30, 0, 1),
(17, 'projet d\'integration de developpement', 300, 90, 30, 0, 1),
(18, 'Stage d\'integration prof', 80, 40, 20, 0, 0),
(23, 'epreuve integre', 300, 80, 32, 1, 1),
(25, 'Développement Android débutant', 500, 90, 25, 0, 1),
(26, 'Développement Android pro', 500, 90, 25, 0, 1),
(28, 'Developpement IOS ', 250, 60, 20, 0, 1),
(29, 'Formation MIC ', 600, 30, 30, 0, 1),
(30, 'JavaScript', 150, 90, 49, 0, 1),
(34, 'poo', 26, 25, 32, 0, 1);

--
-- Déclencheurs `formation`
--
DELIMITER $$
CREATE TRIGGER `trigg_update_sess` AFTER UPDATE ON `formation` FOR EACH ROW UPDATE session as s  SET enable = new.enable WHERE s.IdFormation = new.IdFormation AND s.enable = true 

AND s.IdSession NOT IN (SELECT inscrire.IdSession FROM inscrire,session WHERE session.IdFormation = new.IdFormation AND session.IdSession=inscrire.IdSession ) 

AND s.IdSession NOT IN (SELECT idSession FROM session WHERE DATEDIFF(session.DateDebut,CURRENT_DATE)<0 )
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `inscrire`
--

CREATE TABLE `inscrire` (
  `IdInscription` int(11) NOT NULL,
  `IdUtilisateur` int(255) NOT NULL,
  `IdSession` int(255) NOT NULL,
  `EstPaye` tinyint(255) DEFAULT 0,
  `Signalisation` tinyint(255) DEFAULT 0,
  `prix` float NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `inscrire`
--

INSERT INTO `inscrire` (`IdInscription`, `IdUtilisateur`, `IdSession`, `EstPaye`, `Signalisation`, `prix`) VALUES
(1, 3, 1, 1, 1, 25),
(3, 7, 1, 1, 1, 90),
(4, 10, 1, 1, 1, 125),
(5, 11, 1, 1, 1, 30),
(6, 12, 1, 1, 1, 25),
(7, 5, 4, 1, 1, 101),
(8, 5, 4, 0, 0, 25),
(9, 13, 1, 1, 1, 99),
(10, 13, 2, 0, 1, 90),
(11, 14, 3, 0, 0, 66),
(12, 14, 4, 0, 0, 35),
(13, 5, 7, 1, 1, 233),
(14, 5, 5, 1, 1, 150),
(15, 5, 1, 0, 1, 70),
(16, 5, 3, 0, 0, 150),
(17, 16, 1, 0, 1, 95),
(18, 16, 2, 0, 1, 225);

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
(20, 'a119'),
(21, 'a120');

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
(3, 'enseignant'),
(2, 'stagiaire');

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
(1, 1, 32, '2020-11-20 11:35:49', '2021-01-13 18:05:19', 17, 1),
(2, 4, 27, '2020-11-26 11:37:20', '2021-01-13 18:05:45', 2, 1),
(3, 5, 13, '2020-12-02 11:37:44', '2021-01-08 18:06:05', 3, 1),
(4, 8, 22, '2021-01-11 11:38:35', '2021-04-11 18:06:43', 4, 1),
(5, 9, 15, '2021-01-14 11:39:06', '2021-04-14 18:06:57', 5, 1),
(7, 4, 22, '2020-11-20 18:45:17', '2021-02-20 18:07:29', 7, 1),
(8, 34, 27, '2021-01-21 00:00:00', '2021-07-07 00:00:00', 20, 1),
(11, 30, 14, '2021-02-01 00:00:00', '2021-06-04 00:00:00', 18, 1),
(12, 4, 22, '2021-09-01 00:00:00', '2021-10-25 00:00:00', 21, 0);

-- --------------------------------------------------------

--
-- Structure de la table `status`
--

CREATE TABLE `status` (
  `IdStatus` int(255) NOT NULL,
  `DenomStatus` varchar(255) NOT NULL,
  `reduction` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `status`
--

INSERT INTO `status` (`IdStatus`, `DenomStatus`, `reduction`) VALUES
(1, 'etudiant', 25),
(2, 'demandeur d\'emplois', 50),
(3, 'enseignant', 15),
(4, 'soldat', 60);

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
  `Password` varchar(255) NOT NULL,
  `Role` int(11) NOT NULL,
  `Status` int(11) DEFAULT NULL,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`IdUtilisateur`, `Nom`, `Prenom`, `Adresse`, `Telephone`, `Email`, `Login`, `Password`, `Role`, `Status`, `enable`) VALUES
(1, 'Gandouzz', 'el aminz', 'rue de l\'école 666z', '0486 666 666', 'amin1910@hotmail.com', 'amin', '$2a$10$/M0H665vN0SueM75RN9yeObkET8q0PnSdmNR1N2momW1aX8KeAW/y', 1, NULL, 1),
(3, 'luis', 'save', 'rue du souvenir 107', '0251515151', 'luis@luis.be', 'luis1234', '$2a$10$/M0H665vN0SueM75RN9yeObkET8q0PnSdmNR1N2momW1aX8KeAW/y', 2, 1, 1),
(5, 'Knuts', 'Jordan', 'rue du poulain 66', '85859696', 'jordan@jordan.be', 'jordan', '$2a$10$jXGw45P/WlDZD0s6mrNdkeRPspotWJxxBYNfqsiymxU3obwKAUYMq', 2, 2, 1),
(7, 'bizidudu', 'demandeur d emploie', 'rue bbc echarpe 30 ', '0486667788', 'olivier@hotmail.com', 'olivier', '$2a$10$/M0H665vN0SueM75RN9yeObkET8q0PnSdmNR1N2momW1aX8KeAW/y', 2, 1, 1),
(10, 'Krs', 'Nico', 'rue', '33333333', 'nico@nico.be', 'laxo', '$2a$10$NfygDACXHXx4ObP5wEvkleL/DipFPExkdxIs4Z4d1Mua0kbHG9Eju', 2, 1, 1),
(11, 'a', ' a', 'a', '5555', 'a@a.be', 'pierre', '$2a$10$/M0H665vN0SueM75RN9yeObkET8q0PnSdmNR1N2momW1aX8KeAW/y', 2, 1, 1),
(12, 'b', ' b', 'rue de la f ', '1452', 'b@b.be', 'fabien', '$2a$10$/M0H665vN0SueM75RN9yeObkET8q0PnSdmNR1N2momW1aX8KeAW/y', 2, 1, 1),
(13, 'formateurAA', 'formatzueAA', 'adresse formateurA ', '0125544', 'formateurA@epfc.eu', 'formateurA', '$2a$10$/M0H665vN0SueM75RN9yeObkET8q0PnSdmNR1N2momW1aX8KeAW/y', 3, 3, 1),
(14, 'formateurB', 'formateurB', 'rue de la formation ', '25253636', 'formateurB@formateur', 'formateurb', '$2a$10$/M0H665vN0SueM75RN9yeObkET8q0PnSdmNR1N2momW1aX8KeAW/y', 3, 3, 1),
(15, 'formateurC', 'formateurC', 'rue de la formation ', '89898989', 'formateurC@formateur', 'formateurc', '$2a$10$/M0H665vN0SueM75RN9yeObkET8q0PnSdmNR1N2momW1aX8KeAW/y', 3, 3, 1),
(16, 'marc', 'pichot', 'il habite eterbeek', '454545', 'marc@marc.be', 'marc', '$2a$10$/M0H665vN0SueM75RN9yeObkET8q0PnSdmNR1N2momW1aX8KeAW/y', 2, 1, 1),
(17, 'ponente', 'carmelo', 'rue des brodeurs 9 1000 bruxelles', '0485968574', 'carmelo@carmelo.be', 'carmelo', '$2a$10$/M0H665vN0SueM75RN9yeObkET8q0PnSdmNR1N2momW1aX8KeAW/y', 2, 1, 1),
(22, 'Pennel', 'Benoit', 'EPFC', '02555444666', 'pennel@epfc.eu', 'pennel', '$2a$10$/M0H665vN0SueM75RN9yeObkET8q0PnSdmNR1N2momW1aX8KeAW/y', 3, 3, 1),
(27, 'Silovy', 'Alain', 'rue de l\'astronomie 24', '025050505', 'alain@alain.eu', 'alain', '$2a$10$/M0H665vN0SueM75RN9yeObkET8q0PnSdmNR1N2momW1aX8KeAW/y', 3, 3, 0),
(30, 'Boris', 'Verheaghen', 'Bld de l\'astronomie 24 ', '025656565', 'boris@boris.be', 'boris', '$2a$10$haVnAu6EfXBMRkhuDlVvwedvmWIc57q3eAnIIiW3dEAcPvbyqgkQW', 3, 3, 1),
(31, 'Krstev', 'Nicolas', 'Bld de l\'astronomie 24 , Bruxelles', '02 888 88 88 ', 'nicolas@nicolas.be', 'nicolas', '$2a$10$4hIQDChhfGxNSkZfiV9ibO1HFiY8FuNZne68k5OCOo6sqiALrbiS6', 2, 1, 1),
(32, 'Smets', 'Alain', 'rue de l\'astro 24 ', '4545454545', 'smets@smets.be', 'smets', '$2a$10$blm51EatN/RxBrUG/xsAge0EHpIA0j7C7/shepXC9MHYzF6QtCZQe', 3, 3, 1),
(35, 'Hance', 'Fabio', 'rue de Fabio 66 ', '65656565', 'fabio@fabio.be', 'fabio', '$2a$10$nQQY/3qZ1ZEb8/XvmOrx2.Gu.O0h4Pn2toSz.JaoucAXSNXQgZCjK', 2, 4, 1),
(38, 'Romano', 'Carmelo', 'place de la concorde 13', '025444444', 'romano@romano.be', 'romano', '$2a$10$cb50WDAHXuADOZ1RBEvhE.umHN15g3EsTKWRud3OgccpdHkVn9oFK', 3, 3, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`IdFormation`) USING BTREE,
  ADD UNIQUE KEY `IdFormation_13` (`IdFormation`),
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
  ADD PRIMARY KEY (`IdInscription`),
  ADD KEY `numUtilisateur` (`IdUtilisateur`),
  ADD KEY `numSession` (`IdSession`);

--
-- Index pour la table `locaux`
--
ALTER TABLE `locaux`
  ADD PRIMARY KEY (`IdLocal`) USING BTREE,
  ADD UNIQUE KEY `DenomLocal` (`DenomLocal`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`IdRole`),
  ADD UNIQUE KEY `DenomRole` (`DenomRole`);

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
  ADD PRIMARY KEY (`IdStatus`),
  ADD UNIQUE KEY `DenomStatus` (`DenomStatus`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`IdUtilisateur`) USING BTREE,
  ADD UNIQUE KEY `Nom_2` (`Nom`),
  ADD UNIQUE KEY `Nom_3` (`Nom`),
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
  MODIFY `IdFormation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT pour la table `inscrire`
--
ALTER TABLE `inscrire`
  MODIFY `IdInscription` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `locaux`
--
ALTER TABLE `locaux`
  MODIFY `IdLocal` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `IdRole` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `session`
--
ALTER TABLE `session`
  MODIFY `IdSession` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `status`
--
ALTER TABLE `status`
  MODIFY `IdStatus` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `IdUtilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

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
