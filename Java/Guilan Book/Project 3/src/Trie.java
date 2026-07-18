class TrieNode {
    TrieNode[] children; //array to store child nodes
    boolean isWord; //indicates if a word ends at this node

    public TrieNode() { //constructor to initialize the TrieNode
        children = new TrieNode[128]; //use an array of size 128 to accommodate ASCII characters
        isWord = false;
    }
}

class Trie {
    private final TrieNode root; //root
    private int last; //index of last element of preResult that is full + 1
    private String[] result; //prefix(autocomplete) suggestions result

    public Trie() {
        root = new TrieNode();
        result = new String[100]; //initialize the prefix result array with size of 50
        last = 0; //prefix suggestion array starts at 0
    } //constructor to initialize the Trie with a root node

    public void insert(String word) {
        word = word.toLowerCase();
        TrieNode current = root; //new node
        for (char character : word.toCharArray()) { //for each character of the word
            int index = getIndex(character);
            if (current.children[index] == null)
                current.children[index] = new TrieNode(); //create a new node if the current character is not found
            current = current.children[index];
        }
        current.isWord = true; //marks the end of the word
    } //insert a word into the trie
    public String[] autocomplete(String prefix) {
        result = new String[100]; //empty the result array
        prefix = prefix.toLowerCase();
        TrieNode current = getLastNode(prefix); //gets the last node of the prefix

        if (current != null && search(prefix))
            autocompleteHelper(prefix, current); //if node it's not null then use helper to get all the suggestions
        return result;
    } //retrieve all words with a given prefix
    private void autocompleteHelper(String prefix, TrieNode node) {
        prefix = prefix.toLowerCase();
        if (node.isWord) { //if node is a word that ended then add them to autocomplete suggestions result
            result[last] = prefix;
            last++;
        }
        for (int index = 0; index < 128; index++) { //checks through all possible child nodes
            if (node.children[index] != null) {
                if (index != 32)
                    autocompleteHelper(prefix + (char) ('a' + index), node.children[index]); //recursively call the helper function
                else //index = 32 is for ' '
                    autocompleteHelper(prefix + ' ', node.children[index]); //recursively call the helper function
            }
        }
    } //collect words with a given prefix

    private TrieNode getLastNode(String input) {
        input = input.toLowerCase();
        TrieNode current = root;
        for (char character : input.toCharArray()) { //for each character of the prefix
            int index = getIndex(character);
            if (current.children[index] == null)
                return current; //return null list if the character is not found in the trie
            current = current.children[index]; //get the node at the end od the word
        }
        return current;
    } //returns the last node of the input
    private int getIndex(char character) {
        if (character == ' ')
            return 32; //ASCII index of whitespace is 32
        else
            return character - 'a';
    } //returns calculated ASCII index of the character
    private boolean search(String word) {
        word = word.toLowerCase();
        TrieNode current = root;
        boolean bool = false;

        for (char character : word.toCharArray()) { //for each character in the word
            int index = getIndex(character);
            if (current.children[index] != null) {
                bool = true;
                current = current.children[index]; //next node
            }
            else {
                bool = false; //even if one character is not then the word is not at the top
                break;
            }
        }
        return bool;
    } //returns true if the words is at the top in the trie
}