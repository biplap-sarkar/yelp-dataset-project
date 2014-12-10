yelp-dataset-project
====================

Yelp Academic Dataset Project

This project is divided into following directories:-
1.) DSParser:-	
	a.) Contains libraries to parse the dataset
	b.) Interact with database
	c.) Utilites to filter the data and to convert the data from text files to Sequence Files
	d.) Utility to label the data to create training and test data
2.) ReviewAggregator:- MapReduce tools to compute unigrams, bigrams for words in the dataset and tools to analyse them.
3.) ReviewAnnotator:- Python utilites to label the dataset. Not being used now, instead developed features in DSParser project to do the same thing in java.
4.) DSOutputAnalyser:- Contains tools to analyse unigrams, bigrams results.
5.) SLURM_Scripts:- Contains SLURM scripts to run Mahout Naive Bayes
6.) Classification_Results:- Contains result files for Mahout Naive Bayes classification for each category.

*) The dataset can be downloaded from http://www.yelp.com/dataset_challenge

