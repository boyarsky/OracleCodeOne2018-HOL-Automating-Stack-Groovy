# 3.2 Looking in the Nexus task log on the command line

## Task

In your browser, re-run your Groovy task. How many lines do you see added to the tail output? It’s not 8 lines. Can you figure out the difference between the nexus log and task log?

## Solution

6 lines

| Nexus Log                    | Task Log        |
| ---------------------------- |:---------------:|
| Task name start              | Task information| 
| Task state change to start   | Task id         | 
| Location of log file         | Task type       | 
| 2 lines output               | Task name       | 
| Task state change to waiting | Task start      | 
|                              | 2 lines output  | 
|                              | Task complete   | 