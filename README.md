# Notes

## Description

Notes est une application Android de prise de notes développée en Kotlin avec Jetpack Compose. Elle permet de créer, afficher, modifier, épingler et supprimer des notes. Toutes les données sont enregistrées localement sur l'appareil avec Room : aucun serveur ni compte utilisateur n'est nécessaire.

## Fonctionnalités

- Affichage de la liste des notes, classées avec les notes épinglées en premier puis par date de modification ;
- ajout d'une note avec un titre et un contenu ;
- modification d'une note existante ;
- suppression d'une note après confirmation ;
- épinglage et désépinglage des notes ;
- stockage local persistant avec Room.

## Démonstration vidéo

Une vidéo de démonstration est disponible à la racine du projet, au chemin `Démo_appli.mp4`.

[Voir la vidéo de démonstration](Démo_appli.mp4)

Cette vidéo montre l'utilisation de l'application Notes : affichage des notes, ajout, modification, épinglage et suppression avec confirmation.

## Technologies utilisées

- Kotlin 2.2.10 ;
- Jetpack Compose et Material 3 ;
- Room 2.8.4 ;
- Dagger Hilt 2.59.1 ;
- ViewModel AndroidX ;
- StateFlow et Flow ;
- KSP 2.3.5 pour la génération de code Room et Hilt ;
- Navigation Compose 2.8.4 avec routes typées ;
- Kotlin Serialization ;
- Gradle Kotlin DSL ;
- Android Gradle Plugin 9.1.1 et Gradle 9.4.1.

## Informations techniques

| Élément | Valeur |
| --- | --- |
| Nom affiché de l'application | `Notes` |
| Nom du projet Gradle | `User App` |
| Module Android | `app` |
| Namespace / Application ID | `com.neac.userapp` |
| Version de l'application | `1.0` (`versionCode` 1) |
| Version Android minimale | API 26 — Android 8.0 |
| SDK cible | API 36 |
| SDK de compilation | Android 36.1 |
| Compatibilité source Java | Java 11 |
| JVM du daemon Gradle | JDK 21 |
| Base de données locale | `notes_db`, version 1 |

## Prérequis

Avant de récupérer le projet, installer :

- [Git](https://git-scm.com/) ;
- une version récente d'[Android Studio](https://developer.android.com/studio) compatible avec AGP 9.1.1 et Gradle 9.4.1 ;
- un JDK 21, de préférence le JetBrains Runtime fourni avec Android Studio ;
- Android SDK Platform 36 correspondant à la configuration `compileSdk` 36.1 ;
- les Android SDK Platform-Tools et Build-Tools proposés par le SDK Manager ;
- un émulateur Android ou un téléphone physique sous Android 8.0/API 26 minimum.

Pour utiliser un téléphone physique, activer les **Options pour les développeurs** puis le **Débogage USB**.

## Installation du projet

1. Cloner le dépôt GitHub :

   ```bash
   git clone https://github.com/yanismary/Notes.git
   cd Notes
   ```

2. Ouvrir Android Studio, puis sélectionner **Open** et choisir le dossier `Notes`.
3. Attendre la fin de la synchronisation Gradle et du téléchargement des dépendances.
4. Ouvrir **Tools > SDK Manager** et vérifier que la plateforme Android 36 requise par le projet est installée.
5. Sélectionner le JDK d'Android Studio dans **Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK**.
6. Créer ou démarrer un émulateur depuis **Tools > Device Manager**, ou connecter un téléphone en mode développeur.
7. Sélectionner la configuration `app`, choisir l'appareil cible, puis cliquer sur **Run**.

## Configuration

Le projet ne nécessite ni clé d'API, ni serveur, ni variable d'environnement. Les notes sont conservées uniquement dans la base Room locale de l'application.

Android Studio crée normalement un fichier `local.properties` à la racine afin d'indiquer l'emplacement du SDK Android. Exemple sous Windows :

```properties
sdk.dir=C\:\\Users\\VOTRE_UTILISATEUR\\AppData\\Local\\Android\\Sdk
```

Ce fichier dépend de la machine et ne doit pas être ajouté à Git.

Le fichier `gradle.properties` du projet contient actuellement un chemin Windows vers le JDK intégré à Android Studio :

```properties
org.gradle.java.home=C\:\\Program Files\\Android\\Android Studio\\jbr
```

Si Android Studio est installé ailleurs, ou si le projet est ouvert sous macOS/Linux, adapter ce chemin ou supprimer cette ligne puis sélectionner le JDK 21 depuis les paramètres Gradle d'Android Studio.

## Lancement en ligne de commande

Le Gradle Wrapper fourni avec le dépôt permet d'exécuter le projet sans installer Gradle séparément.

Sous Windows :

```powershell
.\gradlew.bat assembleDebug
.\gradlew.bat installDebug
```

Sous macOS ou Linux :

```bash
./gradlew assembleDebug
./gradlew installDebug
```

La commande `installDebug` nécessite qu'un émulateur soit démarré ou qu'un téléphone autorisé soit connecté. L'APK de développement généré par `assembleDebug` se trouve dans :

```text
app/build/outputs/apk/debug/app-debug.apk
```

## Commandes Gradle utiles

| Commande | Description |
| --- | --- |
| `./gradlew tasks --all` | Affiche toutes les tâches Gradle disponibles |
| `./gradlew assembleDebug` | Compile l'APK de développement |
| `./gradlew installDebug` | Compile et installe l'application sur un appareil connecté |
| `./gradlew build` | Compile le projet et exécute les vérifications principales |
| `./gradlew test` | Exécute les tests unitaires |
| `./gradlew connectedAndroidTest` | Exécute les tests instrumentés sur un appareil |
| `./gradlew lint` | Lance l'analyse statique Android Lint |
| `./gradlew clean` | Supprime les fichiers de build générés |

Sous Windows, remplacer `./gradlew` par `.\gradlew.bat`.

## Dépannage

### Erreur liée à `jlink`

Si une compilation en ligne de commande échoue avec un message indiquant que `jlink` est introuvable, Gradle utilise probablement un JRE incomplet au lieu du JDK d'Android Studio. Sous Windows, forcer temporairement l'utilisation du JDK intégré :

```powershell
$env:JAVA_HOME = "C:\Program Files\Android\Android Studio\jbr"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
.\gradlew.bat --stop
.\gradlew.bat assembleDebug
```

Pour une correction permanente, définir `JAVA_HOME` vers un JDK 21 complet ou sélectionner le JDK d'Android Studio comme **Gradle JDK** dans les paramètres de l'IDE.

### Avertissement sur la version XML du SDK

Si Gradle affiche un avertissement concernant une version XML du SDK non reconnue, mettre à jour **Android SDK Command-line Tools** depuis **Tools > SDK Manager > SDK Tools**.

## Architecture du projet

```text
app/src/main/
├── AndroidManifest.xml
└── java/com/neac/userapp/
    ├── MainActivity.kt
    ├── NotesApplication.kt
    ├── data/
    │   ├── local/
    │   │   ├── NoteEntity.kt
    │   │   ├── NoteDao.kt
    │   │   └── NoteDatabase.kt
    │   └── repository/
    │       └── NoteRepository.kt
    ├── di/
    │   └── DatabaseModule.kt
    └── ui/
        ├── navigation/
        ├── noteedit/
        ├── notelist/
        └── theme/
```

Le projet suit une organisation simple par couches :

- **UI** : écrans Jetpack Compose pour la liste et l'édition des notes ;
- **ViewModel** : gestion de l'état de l'interface et des actions utilisateur ;
- **Repository** : point d'accès aux données de notes ;
- **Room** : entité, DAO et base de données locale ;
- **Hilt** : création et injection de la base, du DAO, du repository et des ViewModel ;
- **Navigation Compose** : navigation entre la liste des notes et l'écran de création/modification.

## Persistance des données

Room stocke les notes dans la table `notes`. Une note contient notamment un identifiant, un titre, un contenu, son état d'épinglage ainsi que ses dates de création et de modification.

Les données restent disponibles après la fermeture de l'application. Elles sont supprimées si l'utilisateur efface les données de l'application ou désinstalle celle-ci.
