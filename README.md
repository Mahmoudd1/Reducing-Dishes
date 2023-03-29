# Reducing-Dishes
A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.

Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes multiplied by its satisfaction level i.e. time[i] * satisfaction[i].

Return the maximum sum of like-time coefficient that the chef can obtain after dishes preparation.

Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.

 

## Example 1:

Input: satisfaction = [-1,-8,0,5,-9]
Output: 14
Explanation: After Removing the second and last dish, the maximum total like-time coefficient will be equal to (-1*1 + 0*2 + 5*3 = 14).
Each dish is prepared in one unit of time.
 
## Constraints:

n == satisfaction.length
1 <= n <= 500
-1000 <= satisfaction[i] <= 1000

# Solution
## Top-Down Dynamic Programming
Let's start with the first dish; we have two options here. First, we can cook this dish, add its satisfaction value multiplied by 1 (as this is the first dish) to our answer, and move on to the next dish whose time taken would be 2 (1 unit for the second dish and 1 unit for the previous one). The second option is to skip this dish and move to the second dish with the time still at 1. We will follow the same options for the second dish and so on, and for each dish, we will choose the option with a greater sum.

This way, we will iterate over every possibility and find the best out of them. For this recursive approach, what are the parameters that we need to track? The first parameter is the index of the dish that we are currently considering as we traverse the dishes. Since the time taken for a dish depends on the number of dishes we have cooked so far, time is another parameter that we need, which would be the count of dishes we have cooked so far, including the current one.

Hence, we need to keep track of two things:

The index of the dish that we are currently considering.
The number of dishes we have cooked so far, i.e. time.
This recursive approach will have repeated subproblems; this can be observed in the figure below. Notice the green nodes are repeated subproblems signifying that we have already solved these subproblems before.
![image](https://user-images.githubusercontent.com/52376760/228539979-5c7a1b63-5908-406d-83ed-690a5cdae3c5.png)

To avoid recalculating results for previously seen subproblems, we will memoize the result for each subproblem. So the next time we need to calculate the result for the same set of parameters {index, time}, we can simply look up the result in constant time instead of recalculating the result.

## Algorithm

Sort the array satisfaction in ascending order.

Create a memoization table memo with size N x N, and initialize all the values with -1, representing that the answer for all the states has not been calculated yet.

Implement the following function, to be called with index = 0 and time = 1 to find the answer:

- If we have reached the end of the array, i.e. index = satisfaction.length, we should return 0 because there are no more dishes to cook, so we can't gain any more value.

- If the value in the array memo for the pair {index, time} is not -1, then return that value as it implies that we have already encountered this subproblem; thus a recursive call is not needed and we can return the value stored in the table memo
-  Check the below two options, calculate, memoize, and return the maximum of them:
-- Add the coefficient value for this dish as `satisfaction[index] * time` to the recursive result for with `index = index + 1` and `time = time + 1`
-- Skip the dish and make the recursive call for `index = index + 1` and `time = time`. 

