# Instructions

This assignment is adapted from one used by Jim X. Chen in the Operating Systems course he taught at George Mason University.  Implement the following in Java.

The goal of this assignment is to give you experience writing multi-threaded programs. In your system, each person arriving at a restroom will be represented by a thread:

  OnePerson(int id, int gender, int time) {

  Arrive(id, gender);

  UseFacilities(id, gender, time);

  Depart(id, gender);

  }

In the code above

id is an integer which uniquely identifies each person (ids will be assigned sequentially based on their arrival as 1,2,3, …),
gender is either 0 or 1 for men and women
time is the amount of time in minutes the person will stay in the restroom (use a random number integer value between 3 and 7, inclusive).
At most 3 people can be in the restroom at a time.

Implement a policy that imposes a limit of 2 on the number of people that can enter the restroom while persons of the opposite gender wait to enter.

NOTE: if fewer than 3 women (men) are currently in the restroom and if no men (women) are waiting to enter, more women can be allowed to enter. In other words, your policy should be smart enough to provide fairness if there are people waiting of both genders but should never force a woman (man) to wait to enter if there are no men (women) who want to enter.

Write the procedures Arrive, UseFacilities and Depart, using mutex locks and condition variables for synchronization.

The Arrive procedure must not return until it is okay for the person to enter the restroom (it must guarantee that there will either only women or only men in the restroom and that the room limit of 3 is not exceeded).
The UseFacilities method should just delay for time x.
Depart is called to indicate that the person is ready to exit; Depart should take steps to let additional people enter the restroom. In addition, Depart should update a shared variable departureIndex, which keeps track of the order in which the people leave the restroom, i.e., the first person to leave the restroom has departure index 1, the second person has departure index 2, and so on. The Depart procedure should also print out the departure index for that person.
NOTES:

Your solution must not employ busy waiting.
Your solution must use two gender-based queues where people wait until it is okay for them to enter the restroom. In terms of threads, this means that you need two Java objects where a thread is suspended if it cannot enter the UseFacilities
For full credit, your solution must not use the notifyAll() method, i.e. your solution must only use notify() for signaling any threads blocked on a condition variable. Invoking notify() repeatedly in a loop is equivalent to notifyAll().
You do not have to ensure that people leave the restroom in the same order as they entered it.
The message printed out should provide a snapshot of the restroom and the gender queues (see below).
        Time =  0; Person  1 (F) arrives

        Time =  0; Person  2 (F) arrives

        Time =  0; Person  3 (M) arrives

        Time =  0; Person  4 (M) arrives

        Time =  0; Person  5 (F) arrives

        Time =  0; Person  1 (F) enters the facilities for 4 minutes

        Time =  0; Person  2 (F) enters the facilities for 6 minutes

        Time =  4; Person  1 (F) exits (departure = 1)

        Time =  4; Person  5 (F) enters the facilities for 5 minutes

        Time =  6; Person  2 (F) exits (departure = 2)

        Time =  9; Person  5 (F) exits (departure = 3)

        Time =  9; Person  3 (M) enters the facilities for 3 minutes

        Time =  9; Person  4 (M) enters the facilities for 5 minutes

        Time = 10; Person  6 (M) arrives

        Time = 10; Person  7 (F) arrives

        Time = 10; Person  8 (F) arrives

        Time = 10; Person  9 (F) arrives

        Time = 10; Person 10 (M) arrives

        …

In this assignment, you have to run your program for the three arrival schedules given below.  It is best to prompt the user for one of the following sequences (you can make it a menu and have them choose, a, b, or c)

5 : DELAY(10) : 5 : DELAY(10) : 5 : DELAY(10) : 5

10 : DELAY(10) : 10

20

The numbers indicate the number of people arriving simultaneously at the restroom, while the numbers in parentheses indicate the delay in minutes before the next arrival(s). For example, under schedule (a) 5 people arrive simultaneously at the restroom at the start of the experiment, five more people arrive simultaneously 10 minutes after the arrival of the first five people and so on. In each of the three schedules, twenty people arrive at the restroom during the experiment.
Note that people arriving simultaneously do not imply that they are all of the same gender.
Assume that the probability that a person is a woman is 0.6 (you will use a random number generator to determine this).
