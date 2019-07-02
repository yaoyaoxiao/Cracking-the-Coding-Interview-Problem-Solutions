/* 16.10 Living People: Given a list of people with their birth and death years, implement a method to
compute the year with the most number of people alive. You may assume that all people were born
between 1900 and 2000 (inclusive). If a person was alive during any portion of that year, they should
be included in that year's count. For example, Person (birth = 1908, death = 1909) is included in the
counts for both 1908 and 1909. */

/*
1. after 2000, no new birth, so the number of living people would just decrease
2. sort the people based on their birth year and death year seperately, iterate and update the the num of living people. 
3. we can also use some space to record the num of living people for each year: use an array with 101 elements (1900 ~ 2000), interate all the people and update the number in the array (+1 if birth, -1 on the next year if death - people who die in 1900 should still be count as 'living' for 1900), at the last, return the largest num in the array. Runtime (O(num of years + num of people)) 
*/

int yearWithMostPeopleAlive(Person[] people){
	if (people == null || people.length == 0) return -1; // invalid input

	int[] sortedBirths = sortYears(people, true);
	int[] sortedDeaths = sortYears(people, false);

	int maxAlive = 0, currentlyAlive = 0;
	int maxAliveYear = sortedBirths[0];
	int birthIndex = 0;
	int deathIndex = 0;

	while(birthIndex < sortedBirths.length){
		if (sortedBirths[birthIndex] <= sortedDeaths[deathIndex]){
			currentlyAlive++;
			if (currentlyAlive > maxAlive){
				maxAlive = currentlyAlive;
				maxAliveYear = sortedBirths[birthIndex];
			}
		}
		else{
			currentlyAlive--;
			deathIndex++;
		}
	}

	return maxAliveYear;
}

//sort all the birth/death years
Person[] sortYears(Person[] people, boolean onBirth){
	int[] res = new int[people.length];
	for (int i = 0; i < people.length; i++){
		res[i] = onebirth ? people[i].birth : people[i].death;
	}
	Arrays.sort(res);
	return res;
}

public class Person{
	public int birth;
	public int death;
	public Person(int b, int d){
		birth = b;
		death = d;
	}
}

// This algorithm is good for when the range of startYear and endYear are relatively small
int yearWithMostPeopleAlive(Person[] people, int startYear, int endYear){
	int size = endYear - startYear + 2; //+2 since people who die in 2000 are still counted as 'alive' in 2001
	int[] counts = new int[size]; 

	for (Person p : people){
		counts[p.birth - startYear]++;
		counts[p.death - startYear + 1]--; // count the death onto the next year
	}

	int maxAlive = 0, maxAliveYear = startYear;
	int count = 0;
	for (int i = 0; i < size -1; i++){ //size-1 since we only want to count to 2000 since no new birth in 2001 anyways
		count += counts[i];  //the number of the living people for the current year is the number of alive people from last year plus the net of this year (birth of this year - death of last year)
		if (count > maxAlive){
			maxAlive = count;
			maxAliveYear = i + startYear;
		}
	}
	return maxAliveYear;
}