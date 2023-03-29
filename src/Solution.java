import java.util.Arrays;

public class Solution {
	
	public int maxSatisfaction(int[] satisfaction) {
		Arrays.sort(satisfaction);
		int [][]memo=new int[satisfaction.length+1][satisfaction.length+1];
		for (int i=0;i<=satisfaction.length;i++)
		{
			Arrays.fill(memo[i], -1); 
		}
        return recursion(0,1,satisfaction,memo);
    }
	int recursion(int index,int time ,int [] satisfaction,int[][] memo)
	{
		if (index>=satisfaction.length)
			return 0;
		if (memo[index][time]!=-1)
			return memo[index][time];
		return memo[index][time]= Math.max(recursion(index+1,time,satisfaction,memo),time*satisfaction[index]+recursion(index+1,time+1,satisfaction,memo));
	}
}
