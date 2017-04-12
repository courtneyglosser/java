README
======

Trying to learn more about how the thread class works and how it is used to
create basic game loops.

Chapter 3 appears to focus on building out a snake game, so let's see how it
differs from my existing snake game!

Immediately upon reading the chapter, I see that it differes dramatically.  It
is actually a game where you're trying to chase the snake around the screen and
click on its head.

Command to build Jar file:

$ jar -cvfe chapter3-0.1.jar cglosser.Main resources/ -C build/ .

Command to run Jar file:

$ java -jar chapter3-0.1.jar

Should just work by using ant build:  ant jardist
