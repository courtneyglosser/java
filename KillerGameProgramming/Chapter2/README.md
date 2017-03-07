README
======

Trying to learn more about how the thread class works and how it is used to
create basic game loops.

Chapter 2 in the Killer Game Programming (in Java) book focuses on basic game
animation, and starts with threaded game loop that evolves over the course of
the chapter.

Command to build Jar file:

$ jar -cvfe chapter2-0.1.jar cglosser.Main resources/ -C build/ .

Command to run Jar file:

$ java -jar chapter2-0.1.jar

Should just work by using ant build:  ant jardist
