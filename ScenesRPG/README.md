README
======

ScenesRPG is a basic project to help me build out my toolkit.  It will consist
of typical game menu screens, and then progress to slightly more complicated
screens that include basic game mechanics (e.g. shops, inns, etc).

The purpose of this game is to help me investigate approaches to creating
screen management systems.  The project will attempt to introduce some basic RPG
elements, but will primarily focus on how to present the user with meaningful
menuing and display systems with which they can easily interact.

Command to build Jar file:

$ jar -cvfe ScenesRPG-0.1.jar cglosser.Main resources/ -C build/ .

Command to run Jar file:

$ java -jar ScenesRPG-0.1.jar

2017-05-08
==========

Dev log;

- Reviewing this project.  Looks like I didn't spend much actual time on it.
- Creating a documentation folder to copy over the notes I had captured for
this game in my word-vomit repo months ago.
- Cleaning up this README
- Copying over skeleton from Fill and cleaning it up
- Adding some notes for todo items to tackle.


2017-05-12
==========

Spent the last few days transitioning over from manual images and checking for
clicks between the images x,y perimeter to just using JButtons.

Learned a little about inner classes and referencing the parent panels. (Outer
clases).

would like to eventually clean this up as seperate classes with divided
responsibilities, but will need to learn more to get there.

Meanwhile, finally have a framework to wrap up the rest of the wireframing
which, I hope will help formulate the plans for future refactoring.
