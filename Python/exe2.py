import nltk
from nltk.tokenize import word_tokenize
from nltk.corpus import movie_reviews
from nltk.classify import NaiveBayesClassifier
from nltk.classify.util import accuracy
import random

#download the necessary NLTK data
nltk.download('movie_reviews')
nltk.download('punkt')

#function to extract features from words
def extractFeatures(words):
    return {word: True for word in words}

#prepare the dataset by loading movie reviews and their categories
documents = [(list(movie_reviews.words(fileid)), category)
             for category in movie_reviews.categories()
             for fileid in movie_reviews.fileids(category)]

#shuffle the documents for randomization
random.shuffle(documents)

#prepare training and testing datasets
featureSets = [(extractFeatures(d), c) for (d, c) in documents]
trainSet = featureSets[:1500]
testSet = featureSets[1500:]

#train the Naive Bayes Classifier
classifier = NaiveBayesClassifier.train(trainSet)

#evaluate the classifier and print the accuracy
print(f'Accuracy: {accuracy(classifier, testSet) * 100}%')

#function to analyze sentiment of the input text
def sentimentAnalyzer(text):
    words = word_tokenize(text)
    features = extractFeatures(words)
    return classifier.classify(features)

#sample user text input for sentiment analysis
userText = "I really loved the movie. It was fantastic and very engaging!"
print(f'Sentiment: {sentimentAnalyzer(userText)}')

userText = "The movie was boring and I didn't like it at all."
print(f'Sentiment: {sentimentAnalyzer(userText)}')
