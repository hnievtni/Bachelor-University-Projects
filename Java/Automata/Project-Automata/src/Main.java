import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Transition {
    String currentState;
    String alphabet;
    String stackTop;
    String nextState;
    String newStackTop;

    public Transition(String currentState, String alphabet, String stackTop, String nextState, String newStackTop) {
        this.currentState = currentState;
        this.alphabet = alphabet;
        this.stackTop = stackTop;
        this.nextState = nextState;
        this.newStackTop = newStackTop;
    }
    public String getCurrentState() {
        return currentState;
    }
    public String getAlphabet() {
        return alphabet;
    }
    public String getStackTop() {
        return stackTop;
    }
    public String getNextState() {
        return nextState;
    }
    public String getNewStackTop() {
        return newStackTop;
    }
}

public class Main {
    static ArrayList<Transition> transitions;

    static ArrayList<String> states;
    static ArrayList<String> stackAlphabet;
    static ArrayList<String> alphabets;
    static ArrayList<String> finalStates;
    static String startState;
    static String stackStart;

    static String currentState;
    static String inputString;
    static Stack<String> stack;

    static boolean accept;

    static HashMap<Integer, String> steps;
    static int stepsCount;

    public static void main(String[] args) {
        initialize();
        new GUI();
    }

    public static void initialize() {
        transitions = new ArrayList<>();

        states = new ArrayList<>();
        stackAlphabet = new ArrayList<>();
        alphabets = new ArrayList<>();
        finalStates = new ArrayList<>();
        startState = null;
        stackStart = null;

        currentState = null;
        stack = new Stack<>();

        accept = false;

        steps = new HashMap<>();
        stepsCount = 0;
    }
    public static void addTransition(String currentState, String alphabet, String stackTop, String nextState, String newStackTop) {
        Transition transition = new Transition(currentState, alphabet, stackTop, nextState, newStackTop);
        transitions.add(transition);
    }
    public static boolean checkInput(String input) {
        StringBuilder alphabet = new StringBuilder();
        for (String alphabet0 : alphabets) {
            if (alphabet0 != null)
                alphabet.append(alphabet0);
            else
                break;
        }

        String regex = "^[" + alphabet + "]*$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
    public static boolean checkStackAlphabet(String input) {
        StringBuilder alphabet = new StringBuilder();
        for (String alphabet0 : stackAlphabet) {
            if (alphabet0 != null)
                alphabet.append(alphabet0);
            else
                break;
        }

        String regex = "^[" + alphabet + "]*$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
    public static void checkAcceptance() {
        if (String.valueOf(inputString.charAt(0)).equals("$")) {
            if (finalStates.contains(currentState)) {
                accept = true;
            }
        }
        else {
            for (Transition transition : transitions) {
                if (transition != null) {
                    if (currentState.equals(transition.getCurrentState())
                            && !stack.isEmpty() && stack.peek().equals(transition.getStackTop())) {
                        if (String.valueOf(inputString.charAt(0)).equals(transition.getAlphabet())) {
                            String transitionStr = "T(" + currentState + "," + inputString.charAt(0)
                                    + "," + stack.peek() + ") = (" + transition.getNextState() + "," + transition.getNewStackTop() + ")";

                            steps.put(stepsCount, currentState + "/" + inputString + "/" + transitionStr + "/"
                                    + getStackString() + "/" + transition.getNextState());
                            inputString = inputString.substring(1);
                            currentState = transition.getNextState();

                            if (!stack.isEmpty()) {
                                stack.pop();
                            }
                            if (!Objects.equals(transition.getNewStackTop(), "E")) {
                                for (int i = transition.getNewStackTop().toCharArray().length - 1; i >= 0; i--) {
                                    stack.push(String.valueOf(transition.getNewStackTop().toCharArray()[i]));
                                }
                            }
                            stepsCount++;
                            checkAcceptance();
                        }
                    }
                } else {
                    break;
                }
            }
        }
    }

    public static void setStates(String input) {
        states = new ArrayList<>(Arrays.asList(input.split(",")));
    }
    public static void setAlphabet(String input) {
        alphabets = new ArrayList<>(Arrays.asList(input.split(",")));
    }
    public static void setStackAlphabet(String input) {
        stackAlphabet = new ArrayList<>(Arrays.asList(input.split(",")));
    }
    public static void setStartState(String input) {
        startState = input;
        currentState = startState;
    }
    public static void addFinalState(String input) {
        finalStates.add(input);
    }
    public static void setStackStart(String input) {
        stackStart = input;
        stack.push(stackStart);
    }
    public static void setInputString(String input) {
        inputString = input + "$";
    }

    public static ArrayList<String> getStates() {
        return states;
    }
    public static ArrayList<String> getAlphabets() {
        return alphabets;
    }
    public static ArrayList<String> getStackAlphabet() {
        return stackAlphabet;
    }
    public static ArrayList<String> getFinalState() {
        return finalStates;
    }
    public static ArrayList<Transition> getTransitions() {
        return transitions;
    }
    public static HashMap<Integer, String> getSteps() {
        return steps;
    }
    public static String getStartState() {
        return startState;
    }
    public static String getStackStart() {
        return stackStart;
    }
    public static int getStepsCount() {
        return stepsCount;
    }
    public static String getCurrentState() {
        return currentState;
    }
    public static String getInputString() {
        return inputString;
    }
    public static String getStackString() {
        Stack<String> stack1 = stack;
        StringBuilder stackStr = new StringBuilder();
        while (!stack1.isEmpty()) {
            stackStr.append(stack1.peek());
            stack1.pop();
        }
        return String.valueOf(stackStr);
    }
    public static boolean getAccept() {
        return accept;
    }
}