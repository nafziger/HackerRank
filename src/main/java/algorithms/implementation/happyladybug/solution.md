# [Problem](https://www.hackerrank.com/challenges/happy-ladybugs/problem)

Seems really easy which means I probably misunderstand the problem.

* If the count of elements of a color is odd then no solution. NOT TRUE
  * XXXAA is a solution 
  * 


  * 
  * 


Consider a board that 
 


Must be at least 2 of every color present. If not then then no solution. A single element of a color can't possibly be placed next to another of its color.

Otherwise the following two are evaluated:

1. If the starting condition is no-blanks and the board does not start in a solved state then no solution. No moves can be made and so the unsolved state can not be corrected.

2. If the stating condition is anything and there is at least one blank then there is a solution. Is this provably true? I feel like it's almost obvious.

Prove 2. 
Given:
1. There is at least 2 of every color present on the board. 
2. The board starts as unsolved.
3. The board has at least 1 blank spot.


If a solution exists for every case then we should be able to write an alg for it.
If an alg exists we should be able to write a proof for the alg
Think of this as a sorting problem. This is just a sorting problem in different clothes.
Is this bubble sort?


