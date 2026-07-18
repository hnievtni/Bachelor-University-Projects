import nltk
from nltk.tokenize import word_tokenize 
from nltk.util import ngrams
from collections import Counter

#download the necessary NLTK data
nltk.download('punkt')

#input text
text = """Natural Language Processing (NLP) is an interdisciplinary subfield that merges concepts from linguistics, 
computer science, and artificial intelligence. Its primary focus is on the interactions between computers and human 
language. NLP seeks to develop algorithms and models that enable computers to process, analyze, and understand vast 
amounts of natural language data in a way that is both meaningful and useful."""

#tokenize the text into words
tokens = word_tokenize(text)

#generate Unigrams (individual words)
unigrams = tokens

#generate Bigrams (pairs of consecutive words)
bigrams = list(ngrams(tokens, 2))

#generate Trigrams (triplets of consecutive words)
trigrams = list(ngrams(tokens, 3))

#count the occurrences of Unigrams, Bigrams, and Trigrams
unigramsCount = Counter(unigrams)
bigramsCount = Counter(bigrams)
trigramsCount = Counter(trigrams)

#display Unigrams and their counts
print("Unigrams and their counts:")
for unigram, count in unigramsCount.items():
    print(f"{unigram}: {count}")

#display Bigrams and their counts
print("\nBigrams and their counts:")
for bigram, count in bigramsCount.items():
    print(f"{bigram}: {count}")

#display Trigrams and their counts
print("\nTrigrams and their counts:")
for trigram, count in trigramsCount.items():
    print(f"{trigram}: {count}")
