# SiegeTD Game

## Table of contents
- [General info](#general-info)
- [Main game file structure](#main-game-file-structure)
- [Game technologies](#game-technologies)
- [Start development](#start-development)

## General info
This project aims to solve the client-side of the SiegeTD game, a multiplayer tower defense game. In SiegeTD a player
must defend their base from scorpions, ogres and ghosts. A player can play with their friends in multiplayer sessions
where live data from all players in the session is displayed in real time.

## Main game file structure
    .
    ├── .idea                   # IDE related files
    ├── android                 # Files related for running game on android
    ├── core                    # Source files, where the core logic of game is located
    ├── desktop                 # Files related for running game on desktop
    └── gradle                  # Files for the gradle building tool

## Game technologies
- [Java](https://www.java.com/en/)
- [Gradle](https://gradle.org/)
- [Android Studio](https://developer.android.com/studio?gclid=CjwKCAjwjZmTBhB4EiwAynRmD-PntKNuDDdaDGWR8Na5QqfMgK1qSJ31JhJyo-WgRXLGBhtSaFbx7BoC934QAvD_BwE&gclsrc=aw.ds)
- [libGDX](https://libgdx.com/)
- [ashley](https://github.com/libgdx/ashley)
- [socket.io-client-java](https://github.com/socketio/socket.io-client-java)

## Start development

### Requirements
- Java JDK
- Android Studio

### Start
1. Open project in Android Studio
2. Sync gradle, this can be done in two ways:
    - Press the gradle sync button in android studio
    - Run `./gradlew`
3. Run game, this can be done in two ways:
    - Run the DesktopLauncer class found in the desktop folder
    - Run `./gradlew desktop:run`
