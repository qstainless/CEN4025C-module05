# To-do List using Hibernate

## Overview
This exercise uses a GUI (Graphical User Interface) that allows a user to create to-do items that are stored to and retrieved from a MySQL database.

## What does this program do?
The program allows the user to create (add), view, and delete to-do items. The to-do items are saved to a MySQL database, which is queried every time the application runs.

## System requirements
The program is a JavaFX application using version 8 of Amazon's distribution of the Open Java Development Kit (OpenJDK) [Corretto 8](https://aws.amazon.com/corretto/), which includes JavaFX 8. It also uses Hibernate to perform JDBC operations on a MySQL database.

## Database connection defaults
The program assumes that a database named `module_05_db` exists in the local MySQL database and a user with all database privileges exists with username/password: `module05user`/`module05pass`. It also assumes that it will connect to localhost using default port 3306. 

The user may change these initial configuration options by editing lines 16, 20, and 24 in the hibernate configuration file: 

```
/src/hibernate.cfg.xml

16    jdbc:mysql://localhost:3306/module_05_db?serverTimezone=America/New_York
...
20    module05user
...
24    module05pass
```

## How to use this program.
The GUI consists of a single stage (window) and a single scene (window content). The user can create to-do items by specifying a description of the to-do item, additional details of the item, and a due date. New items can be added by selecting the "Add new item" menu option in the Edit menu or by pressing `CTRL-N`.

The user may also delete a selected item by selecting the "Delete selected item" menu option in the Edit menu or by pressing the `Delete` key.

Newly added to-do items will be saved to the MySQL database.

The user may exit the application by closing the application window, by selecting the "Exit" menu opion in the File menu, or by pressing `ALT-Q`.

## Installation.
Clone the repo and import it into your favorite Java IDE. Make sure that:
 1. The project SDK is Java 8 with project language level 8, and
 2. JavaFX 8, Hibernate, and the Oracle [JDBC Driver](https://dev.mysql.com/downloads/connector/j/) for MySQL are installed in your system.

## Known Issues
None at this time
