# COMP2013 DEVELOPING MAINTAINABLE SOFTWARE - BREAKOUT

**Name** : Salaar Mir

**Student Number** : 20275881

**Email** : psysm13@nottingham.ac.uk

## How to compile and run the application

Assuming maven is installed in the user's path and they are in the project directory, the following command needs to be entered into the command prompt/terminal to compile and run the application:

`~$ mvn clean javafx:run`

## Where to find the Javadoc documentation

The path to the Javadoc documentation is:

MirSalaar_17/JavaDoc

## Working features

- GUI class has been added that is responsible for displaying graphics to the user as well as a Controls class in charge of initiating the game controls. This is to better support the idea of single functionality as well as make the game fit more to the MVC design pattern
- Large classes such as the GameBoard class have been broken up into smaller ones (the GUI and Controls class) to make the code more understandable
- Classes have been renamed for more clarity
- Packages have been renamed for better organization
- Singleton design patterns have been implemented for better code functionality
- Start menu with modifiable background colors has been added 
- Background music users can listen to while playing the game has been added
- New gold brick type has been added
- 2 more levels have been added
- Popups have been added that allows users to submit username and score to a permanent highscore file if they complete the game successfuly or lose the game
- Another popup has been added that allows users to select whether they would like to play the game in light or dark mode
- JUnit test for the RubberBall class has been made to make sure it works in the way it is expected
- Provided implementation of 'A' and 'D' keys to move the paddle left and right


## Incorrectly functioning features

- A bug that incorrectly counts the number of bricks that are left to break is included in the project but has not yet been fixed. This is due to not being able to figure out how to fix it and a lack of time management while trying to understand how to resolve it

- A bug that causes the ball to roll across the paddle and bounce back up if the ball makes contact with the edge of the paddle has also been included in the project but not yet fixed. This has been due to a lack of understanding on how to fix this bug.

## Unimplemented features

- An extensive list of JUnit tests have not been implemented. The reasoning behind this is a lack of proper time management.

- Complete translation from Java Swing to Java FX has also not been implemented. The reasoning for this is a lack in confidence to attempt to convert the code.

## New Java classes introduced

- Controls
- GUI
- StartGame
- EndGame
- LostGame
- Levels
- ColorMode
- Music
- GoldBrick
- RubberBallTest

## Modified Java classes

- Ball
- Brick
- Ball1
- Brick1
- Brick2
- Brick3
- DebugConsole
- GameBoard
- GameFrame
- Paddle
- Wall
- StartGame