/* 16.8 English Int: Given any integer, print an English phrase that describes the integer (e.g ., "One
Thousand, Two Hundred Thirty Four"). */

/*
1. negative? zero?
2. every three digits is a group: billion, million, thousand, less than 1k
3. same pattern for each group: hundred, tens (twenty ~ ninety), less than 20 (zero ~ nineteen)
*/
String negative = "Negative";
String[] zeroToNineteen = {"Zero", "One", "Two", "Three", "Four", "Five","Six","Seven","Eight","Nine","Ten","Eleven","Twelve","Thirteen", "Fourteen","Fifteen", "Sixteen","Seventeen", "Eighteen", "Nineteen"};
String[] tens = {"Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
String hundred = "Hundred";
String[] groups = {"Thousand","Million","Billion"};

String englishInt(int num){
	if (num == 0){
		return zeroToNineteen[0];
	}
	else if (num  < 0){
		return negative + englishInt(-1 * num);
	}
	LinkedList<String> strs = new LinkedList<String>();
	int groupNum = 0;
	while (num > 0){
		int curGroup = num % 1000;
		if (curGroup != 0){
			String curStr = convertThreeDigitsGroup(curGroup);
			if (groupNum >= 1){
				curStr += " " + groups[groupNum-1];
			}
			strs.addFirst(curStr);
		}
		num /= 1000;
		groupNum++;
	}
	return listToString(strs);
}

String convertThreeDigitsGroup(int num){
	LinkedList<String> strs = new LinkedList<String>();
	if (num >= 100){
		strs.addLast(zeroToNineteen[num/100] + " " + hundred);
		num %= 100;
	}
	if (num >= 20){
		strs.addLast(tens[num/10 - 2]);
		num %= 10;
	}
	if (num > 0){
		strs.addLast(zeroToNineteen[num]);
	}
	return listToString(strs);
}

String listToString(LinkedList<String> list){
	StringBuilder sb = new StringBuilder();
	while(list.size() > 1){
		sb.append(list.pop());
		sb.append(" ");
	}
	sb.append(list.pop());
	return sb.toString();
}