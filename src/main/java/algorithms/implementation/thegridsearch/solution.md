


Constraints

$$1\le t \le5$$
$$1\le R,r,C,c \le 1000$$
$$1\le r \le R$$
$$1\le c \le C$$

Array searched for pattern

* Between 1 and 5 Array v. Pattern searches.
* Array max size is 1000x1000
* Pattern max size is equal to or smaller than searched grid 

## Notes
In every case, you have to at least check every element in your target pattern.
I think the size of the array is a good baseline minimum complexity measure. It's probably the best

## Edge Case Scenarios 

1. Grid 1000x1000. Target pattern is 1x1. Target not in Grid. Every Grid element has to be compared.
2. Grid 1000x1000. Target pattern is 1000x1000. Every Grid element has to be compared. 
3. Grid is RxC and pattern is 1x1.
   * Best Case : The first element of the array you check contains the pattern. 1 check
   * Worst Case : The last element of the array you check contains the pattern. RxC checks
   * 
## Best-Worst Case Scenario
Assume we are given the largest array 1000x1000. What pattern size would be easiest to search for. A 1x1 pattern requires 1 million checks. A 1000x1000 pattern also requires 1 million checks. Does every pattern require 1 million? If not, is there an optimal pattern size that minimizes the number of checks?

1 million checks might actually be the best case.



## Adversarial Test Cases
If using a "reading" style scan:
Consider an array and a pattern where every element is 1 except the final element which is 0.

Array 	 	 
|  |   |   |
|---|---|---|
| 1 | 1 | 1 |
| 1 | 1 | 1 |
| 1 | 1 | 0 |

Pattern
|  |   |
|---|---|
| 1 | 1 |
| 1 | 0 |

Each time the pattern is tested it will fail on the last element of the pattern. It will test the pattern 3 times before success on the 4th test.
Each test, success or failure in this setup uses 4 checks. 16 checks for an array of size 9.

**It can be worse.**

Assume a similar array and pattern blown up so that the array is 1000x1000 and pattern is 999x999. As before, each time the pattern is tested it will fail on the last element of the pattern. It will test the pattern 3 times before success on the 4th test. Each test uses 999x999 checks. That's approximately 4x more checks than the size of the array.

**It can be much worse**

Assume a similar array and pattern blown up so that the array is 1000x1000 and pattern is 500x500. As before, each time the pattern is tested it will fail on the last element of the pattern. But this time it will test the pattern 500x500 times. Each test using 500x500 checks. 500^4 checks in total. $500^4/10^6 = 62,500$ more checks than the size of the array!

In general there will be $(R-r)\times(C-c)\times(r\times c)$ checks in this adversarial test scenario. I think this might be the upper limit for any case.


## Question
What $r$ and $c$ maximize the following equation?
 $$(1000-r)\times(1000-c)\times(r\times c)$$ 
 I'm pretty sure its when $r=c=500$ but I'd like to prove it.
 $$((1000-r)\times r) \times ((1000-c)\times c)$$ 
 Assume we've found a $c$ that maximizes $((1000-c)\times c)$.
Take the derivative with respect to $r$ of $((1000-r)\times r)$ and we get $1000-2r$ Which is zero when r is 500. 500 is a max/min. 


## Question
Are there search patterns or techniques that help with adversarial test cases?

1. Run a single scan of the array and pattern to make sure that the array at least contains the elements found in the pattern. If the pattern has the digit 9 and the array does not contain that digit then you can stop there. 



# Pixelation approach 

Let a pixel be single element of an array.
Let a token be a value that pixel can hold.
In our original array and pattern the pixels can take on the digits 0-9, thus the token set is of size 10.
But what if we combined each 2x2 block of pixels into a new super pixel? The token set for this pixel would be 10^4 in size. Just as there are arrays that do not use all of the original 10 tokens (i.e. imagine an array containing only zeros), an array made of these new pixels wouldn't necessarily use all 10^4 tokens.

## Question
How much work is required to map an array of size RxC to a pixel size of rxc?


Our given image and pattern have only 10 levels/tokens/pixel values to each element. These tokens are the digits 0-9.
What if we were to combine each 2x2 block of pixels into a new pixel? If the original image was RxC then the new image would be (R-1)x(C-1). Each sub-pixel in one of these new 2x2 block pixels could be any of the 10 original tokens. Thus the new token set would have 10^4 possible elements. The new image might not use







# Unique tokens

Problem would be easy if we knew that the given pattern had a token $t$ and a simple search of the array did not turn up that token, or if that token was rare so that we could narrow down the search space.

We want a token that is rare in the pattern and in the array ideally. 

What if we convert the pattern and the array into these tokens. This is a bit like the pixelization approach but we don't pixilize until the pattern is a single pixel, which can be expensive.

Lets consider a line of 4 pixels to be our target grouping. What is the worst case cost of this operation. 
Want the reduced images, the row by row list of unique tokens and their count.
Let an n-token be a unique set of n-digets
10^4 = 10,000 possible tokens
A single row of 1000 can have at most how many unique 4-tokens?
Create a token map object
'''
{
    token_id : 1234
    pattern_count : 
    grid_count : 
}  
'''

A single scan of each could yield what n-tokens exist and their count.
I think it could also yield the reduced images.

Grab n. If not in map, add it and set count to 1, otherwise increment count. Add n-token pixel to new image.

Why use a dict? An array of length 10^n would work just fine and take constant time. There will be empty slots but who cares as long as we are using suitably small n.



| Type     | Bits    | Distinct Values             | 1 million elm size |
|-------|----|-------------|-----------|
| byte  | 8  | 256         | 1mb       |
| short | 16 | 65536       | 2mb       |
| int   | 32 | 4294967296  | 4mb       |
| long  | 64 | 1.84467E+19 | 8mb       |







# Idea
Find the most unique string of length x in the pattern. This can be done in one pass. Find all locations of that string in the grid. The string is long enough to be pretty unique but short enough to make the search quick.
Example:
Length 6. There are 10^6 possible strings given our token set of the digits 0-9. 
A worst case search of the grid. Would take 6x grid size operations.
(000001 in a grid of all 0's)

Is 6 a good number for our string length? Consider a completely random grid and arbitrary string. How many starting points would this give us on average?
Assume 1000x1000 grid. Such a grid contains 995 substrings of length 6 per row or 995,000. On average one then as our string is 10^6?


One pass of the grid could yield a new grid of size R-5xC.
A single scan of this grid would find all start points given the 6-string.
999999


The string in the pattern that is most unique may not be the best choice.
Consider a pattern of all 0's and a grid 

All strings found in the pattern must exist in the grid.
If not then stop. No match possible.
Also, for each distinct string, the count from the grid must be equal to or greater than the count from the pattern. If not then stop. No match possible.
Otherwise, of the strings shared between the Grid and the Pattern, find the string whose Grid occurrence count is the least.
Might be better to find the string who minimizes the product of its grid and pattern occurrence count.
Fewest combinations to check. Wait. We don't need to check each. Fix the pattern string. If the pattern is in the grid then there must be at least one corresponding string in the grid where everything lines up.

For this string, pick any single occurrence in the pattern as a fixed anchor point. For each of this string in the grid, check if the pattern matches the grid when the pattern is positioned according to the anchor and grid string.

# First attempt

Test Case 4		Timeout
Test Case 5		Timeout
Test Case 6		Wrong
Test Case 10	Wrong
Test Case 12	Wrong