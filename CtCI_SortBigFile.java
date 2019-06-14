/* 10.6 Sort Big File: Imagine you have a 20 GB file with one string per line. Explain how you would sort
the file. */

/*
1. since the file is big, 20 GB, we dont want to bring all the data into memory. So, we can devide the file into chunks, each chunk is X GB where X is the amount of memory we have available. 
2. each chunk is sorted seperately and then save back to the file. Once all the chunks are sorted, we merge these chunks one by one and get a fully sorted file.
3. the algorithm is called external sort.
*/



