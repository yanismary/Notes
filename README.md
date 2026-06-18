# Notes

## Description

Notes est une application Android de prise de notes réalisée en Kotlin avec Jetpack Compose. Elle permet d'ajouter, modifier, épingler et supprimer des notes, enregistrées localement avec Room.

## Technologies

- Kotlin ;
- Jetpack Compose ;
- Room ;
- Dagger Hilt ;
- ViewModel / StateFlow ;
- KSP ;
- Navigation Compose.

## Installation

```bash
git clone https://github.com/yanismary/Notes.git
cd Notes
```

- Ouvrir le projet avec Android Studio ;
- attendre la synchronisation Gradle ;
- lancer l'application sur un émulateur ou un téléphone Android.

## APK

Pour générer un APK :

```bash
./gradlew assembleDebug
```

Sous Windows :

```powershell
.\gradlew.bat assembleDebug
```

L'APK généré se trouve dans :

```text
app/build/outputs/apk/debug/
```

## Démonstration et compte-rendu

Une [vidéo de démonstration](Démo_appli.mp4) est disponible à la racine du dépôt, au chemin `Démo_appli.mp4`.

Le compte-rendu du projet est également disponible dans le dépôt Git.

## Auteur

Yanis MARY
