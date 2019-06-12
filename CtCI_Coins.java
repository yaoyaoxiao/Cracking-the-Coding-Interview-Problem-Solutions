/* 8.11 Coins: Given an innnite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and
pennies (1 cent), write code to calculate the number of ways of representing n cents. */

/* this is similar to the 'Triple Step' problem but the order or the coins doesn't matter */

int numOfWays (int n){
	int[] coins = new int[]{25, 10, 5, 1};
	int[][] ways = new int[n+1][coins.length];
	return numOfWays(n, coins, ways, 0);
}

int numOfWays(int amount, int[] coins, int[][] ways, int index){
	if (ways[amount][index] > 0) {
		return ways[amount][index];
	}
	if (index >= coins.length -1) {
		ways[amount][index] = 1;
		return 1;
	}

	int result = 0;
	for (int i = 0, i * coins[index] <= amount, i++){
		int remainder = amount - i * coins[index];
		result += numOfWays(remainder, coins, ways, index+1);
	}
	ways[amount][index] = result;
	return result;
}

