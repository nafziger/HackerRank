# [Problem](https://www.hackerrank.com/challenges/strange-code/problem)

# Strange Code / Strange Counter

# Problem:
Given a t, find i
# Pattern:
| t  | i  |
|----|----|
| 1  | 3  |
| 2  | 2  |
| 3  | 1  |
| 4  | 6  |
| 5  | 5  |
| 6  | 4  |
| 7  | 3  |
| 8  | 2  |
| 9  | 1  |
| 10 | 12 |
| 11 | 11 |
| 12 | 10 |
| 13 | 9  |
| 14 | 8  |
| 15 | 7  |
| 16 | 6  |
| 17 | 5  |
| 18 | 4  |
| 19 | 3  |
| 20 | 2  |
| 21 | 1  |


# Sub-Problem
Given an $i$, find $block$

| t  | i  | block |
|----|----|-------|
| 1  | 3  | 1     |
| 2  | 2  | 1     |
| 3  | 1  | 1     |
| 4  | 6  | 2     |
| 5  | 5  | 2     |
| 6  | 4  | 2     |
| 7  | 3  | 2     |
| 8  | 2  | 2     |
| 9  | 1  | 2     |
| 10 | 12 | 3     |
| 11 | 11 | 3     |
| 12 | 10 | 3     |
| 13 | 9  | 3     |
| 14 | 8  | 3     |
| 15 | 7  | 3     |
| 16 | 6  | 3     |
| 17 | 5  | 3     |
| 18 | 4  | 3     |
| 19 | 3  | 3     |
| 20 | 2  | 3     |
| 21 | 1  | 3     |


We could, starting from point $t$, build the $i$ pattern b

We could build the $i$ pattern until we get the first numbers before and after our given $t$.
Then subtract the before number from $t$ to get how deep into that block we are and subtract that from the block size for our final number.
The crux is that generating the before and after numbers could be somewhat expensive? Can't we directly calc them?

Sub-Problem 2
Find a general formula for: $3+6+12+24...$
$$3+6+12+24... = 3\times(1+2+4+8+...)$$
$$3\times(1+2+4+8...) =3\times\sum_{i=0}^{}2^i$$

Given the number of blocks $n$ , find the max $t$.
Look for a pattern in:
| $n$ | $2^n$ | $\sum2^n$ |
|-----|-------|-----------|
| 0   | 1     | 1         |
| 1   | 2     | 3         |
| 2   | 4     | 7         |
| 3   | 8     | 15        |
| 4   | 16    | 31        |
| 5   | 32    | 63        |
| 6   | 64    | 127       |
| 7   | 128   | 255       |
| 8   | 256   | 511       |
| 9   | 512   | 1023      |
| 10  | 1024  | 2047      |
| 11  | 2048  | 4095      |

$\sum2^n$ is always 1 less than a power of 2. 
For $2^n$, $\sum2^n$ appears to be  $2^{n+1}-1$, so our formula for $3\times2^n$ may be $3\times(2^{n+1}-1)$
# Proof:
Use induction

So, given a block number $n$, we can calculate the final $t$ in that block.
| $n$ | $2^n$ | $\sum2^n$ | $3\times\sum2^n$ |
|-----|-------|-----------|------------------|
| 0   | 1     | 1         | 3                |
| 1   | 2     | 3         | 9                |
| 2   | 4     | 7         | 21               |

| t  | i  | block |
|----|----|-------|
| 1  | 3  | 1     |
| 2  | 2  | 1     |
| <span style="color:red">3</span>   | 1  | 1     |
| 4  | 6  | 2     |
| 5  | 5  | 2     |
| 6  | 4  | 2     |
| 7  | 3  | 2     |
| 8  | 2  | 2     |
| <span style="color:red">9</span>  | 1  | 2     |
| 10 | 12 | 3     |
| 11 | 11 | 3     |
| 12 | 10 | 3     |
| 13 | 9  | 3     |
| 14 | 8  | 3     |
| 15 | 7  | 3     |
| 16 | 6  | 3     |
| 17 | 5  | 3     |
| 18 | 4  | 3     |
| 19 | 3  | 3     |
| 20 | 2  | 3     |
| <span style="color:red">21</span>  | 1  | 3     |

| $n$ | $2^n$ | $\sum2^n$ | $3\times\sum2^n$ | $3\times(2^{n+1}-1)$ |
|-----|-------|-----------|------------------|----------------------|
| 0   | 1     | 1         | 3                | 3                    |
| 1   | 2     | 3         | 9                | 9                    |
| 2   | 4     | 7         | 21               | 21                   |
| 3   | 8     | 15        | 45               | 45                   |
| 4   | 16    | 31        | 93               | 93                   |
| 5   | 32    | 63        | 189              | 189                  |
| 6   | 64    | 127       | 381              | 381                  |
| 7   | 128   | 255       | 765              | 765                  |
| 8   | 256   | 511       | 1533             | 1533                 |
| 9   | 512   | 1023      | 3069             | 3069                 |
| 10  | 1024  | 2047      | 6141             | 6141                 |
| 11  | 2048  | 4095      | 12285            | 12285                |
Can we do the reverse? Can we, given a block's final $t$ value, calculate the block number $n$?  
We can.

Solve for n.
$$3\times(2^{n+1}-1)=t$$
$$2^{n+1}=\frac{t}{3}+1$$
$$2 \cdot 2^{n}=\frac{t+3}{3}$$
$$2^{n}=\frac{t+3}{6}$$
$$\ln(2^{n})=\ln\left(\frac{t+3}{6}\right)$$
$$n=\ln\left(\frac{t+3}{6}\right)\div\ln(2)$$


| $n$ | $2^n$ | $\sum2^n$ |t= $3\times\sum2^n$ | t=$3\times(2^{n+1}-1)$ | n=$\ln\left(\frac{t+3}{6}\right)\div\ln(2)$ |
|-----|-------|-----------|------------------|----------------------|-------------------------------------------|
| 0   | 1     | 1         | 3                | 3                    | 0                                         |
| 1   | 2     | 3         | 9                | 9                    | 1                                         |
| 2   | 4     | 7         | 21               | 21                   | 2                                         |
| 3   | 8     | 15        | 45               | 45                   | 3                                         |
| 4   | 16    | 31        | 93               | 93                   | 4                                         |
| 5   | 32    | 63        | 189              | 189                  | 5                                         |
| 6   | 64    | 127       | 381              | 381                  | 6                                         |
| 7   | 128   | 255       | 765              | 765                  | 7                                         |
| 8   | 256   | 511       | 1533             | 1533                 | 8                                         |
| 9   | 512   | 1023      | 3069             | 3069                 | 9                                         |
| 10  | 1024  | 2047      | 6141             | 6141                 | 10                                        |
| 11  | 2048  | 4095      | 12285            | 12285                | 11                                        |

If we feed our block formula an $i$ that isn't the maximum value in a block then we get a decimal number. The upper bounding integer of this decimal corresponds to the block containing the $i$, the lower to the block just previous to the one containing it. 

Procedure, given an $i$ which we will call $i_p$ calculate it's block number. From this get the bounding block numbers $b_1$ below and $b_2$ above. For each bounding block, calculate the final $i$ value for each block, $i_1$ below and $i_2$ above. $i_p-i_1$
* The block containing $i_p$ is $i_2-i_1$ wide.
* $i_p$'s distance into the block that contains it is $i_p - i_1$
* $i_p$'s distance from the end of the block is $i_2 - i_p$
  
We want: (Width of block) - (distance into the block) 
Therefore 
$$(i_2-i_1)-(i_p - i_1) =  i_2 -i_p $$

True procedure:
1. Given an $i$ which we will call $i_p$ calculate it's containing block number.
 $$n=\left\lceil\ln\left(\frac{t+3}{6}\right)\div\ln(2)\right\rceil$$
3. Using this block number get the final $i$ value of the block
$$3\times(2^{n+1}-1)=t$$
4. Subtract $i$ from the block's final $i$ value. add 1.
5. Done

Constraints 10^12

2^64