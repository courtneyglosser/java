README
======

Fill is a basic color matching game ported from previous implementations in
JavaScript and C.

The purpose of this game is to familiarize myself with some basic Java
development concepts; implement some basic gaming elements; have a little fun
with some basic pixel art; and finally, to convince myself that I can create
things rather than always just consuming them.

Command to build Jar file:

$ jar -cvfe fill-0.1.jar cglosser.Main resources/ -C build/ .

Command to run Jar file:

$ java -jar fill-0.1.jar

2017-01-21
==========

Dev log:

- Cleaning up code today.  Focusing on refactoring tasks.  The checkWin
  conditional was actaully almost there already, so really didn't require much.
- Working on the ButtonManager clean up, now.  Pulling out display and click
  logic from the other classes and consolidating in the Manager:
-- in FillPanel, drawing is handed over efficiently in the "drawBoardButtons"
-- in the FillPanel class, I cleaned up some function names.
-- Need to move the drawing and click-checking functions from the Screen class
over to the ButtonManager class.
 
