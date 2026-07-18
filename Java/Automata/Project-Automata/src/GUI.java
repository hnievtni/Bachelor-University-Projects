import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.*;
import java.util.Timer;

class RoundJTextField extends JTextField {
    private Shape shape;
    public RoundJTextField(int size) {
        super(size);
        setOpaque(false);
    }
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-  1, getHeight() - 1, 45, 45);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
        g.setColor(null);
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 45, 45);
    }
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 45, 45);
        }
        return shape.contains(x, y);
    }
}

public class GUI {

    JFrame frame = new JFrame("Stack Automata");

    String[] statesArray;
    String[] alphabetsArray;
    String[] stackAlphabetsArray;

    Dimension fieldSize = new Dimension(600,40);
    Font font = new Font("Comic Sans MS", Font.ITALIC, 15);

    public GUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);

        mainPanel();

        frame.setVisible(true);
    }

    public void mainPanel() {
        frame.getContentPane();
        JPanel panel = new JPanel();

        JLabel background = new JLabel();
        background.setBounds(0,0,800,600);

        ImageIcon backgroundIcon = new ImageIcon("mainFrame.gif");
        background.setIcon(backgroundIcon);

        JButton startButton = new JButton();
        startButton.setBounds(0,0, 800, 600);

        startButton.addActionListener(e -> {
            panel.setVisible(false);
            initializeAutomata();
        });

        panel.setLayout(null);
        panel.add(background);
        panel.add(startButton);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
    }
    public void initializeAutomata() {
        JPanel panel = new JPanel();

        JLabel background = new JLabel();
        background.setBounds(0,0,800,600);

        ImageIcon backgroundIcon = new ImageIcon("redFrame.gif");
        background.setIcon(backgroundIcon);

        //States
        JLabel statesLabel= new JLabel("States:", SwingConstants.CENTER);
        statesLabel.setBounds(0,80,150,40);
        statesLabel.setFont(font);
        statesLabel.setForeground(Color.WHITE);

        JLabel comma0 = new JLabel("(comma-separated)", SwingConstants.CENTER);
        comma0.setBounds(0, 100, 150, 40);
        comma0.setFont(font);
        comma0.setForeground(Color.WHITE);

        JTextField states = new RoundJTextField(15);
        states.setBounds(150, 90, fieldSize.width, fieldSize.height);
        states.setFont(font);
        states.setBorder(null);
        states.setHorizontalAlignment(SwingConstants.CENTER);

        //Input alphabet
        JLabel inputLabel= new JLabel("Input Alphabet:", SwingConstants.CENTER);
        inputLabel.setBounds(0,180,150,40);
        inputLabel.setFont(font);
        inputLabel.setForeground(Color.WHITE);

        JLabel comma1 = new JLabel("(comma-separated)", SwingConstants.CENTER);
        comma1.setBounds(0, 200, 150, 40);
        comma1.setFont(font);
        comma1.setForeground(Color.WHITE);

        JTextField inputAlphabet = new RoundJTextField(15);
        inputAlphabet.setBounds(150, 190, fieldSize.width, fieldSize.height);
        inputAlphabet.setFont(font);
        inputAlphabet.setBorder(null);
        inputAlphabet.setHorizontalAlignment(SwingConstants.CENTER);

        //Stack alphabet
        JLabel stackLabel= new JLabel("Stack Alphabet:", SwingConstants.CENTER);
        stackLabel.setBounds(0,280,150,40);
        stackLabel.setFont(font);
        stackLabel.setForeground(Color.WHITE);

        JLabel comma2 = new JLabel("(comma-separated)", SwingConstants.CENTER);
        comma2.setBounds(0, 300, 150, 40);
        comma2.setFont(font);
        comma2.setForeground(Color.WHITE);

        JTextField stackAlphabet = new RoundJTextField(15);
        stackAlphabet.setBounds(150, 290, fieldSize.width, fieldSize.height);
        stackAlphabet.setFont(font);
        stackAlphabet.setBorder(null);
        stackAlphabet.setHorizontalAlignment(SwingConstants.CENTER);


        JButton generateButton = new JButton("Generate");
        generateButton.setBounds(350,410, 120, 120);
        generateButton.setBorderPainted(false);
        generateButton.setFocusPainted(false);
        generateButton.setBorder(null);
        generateButton.addActionListener(e -> {
            if (!Objects.equals(states.getText(), "") && !Objects.equals(inputAlphabet.getText(), "") && !Objects.equals(stackAlphabet.getText(), "")) {
                Main.setStates(states.getText());
                Main.setAlphabet(inputAlphabet.getText());
                Main.setStackAlphabet(stackAlphabet.getText());

                setStatesArray();
                setAlphabetsArray();
                setStackAlphabetArray();

                panel.setVisible(false);
                detailFrame();
            }
            else {
                panel.setVisible(false);
                initializeAutomata();
            }
        });

        ImageIcon generateIcon = new ImageIcon("next.png");
        generateButton.setIcon(generateIcon);

        panel.setLayout(null);
        background.add(statesLabel); background.add(states); background.add(comma0);
        background.add(inputLabel); background.add(inputAlphabet); background.add(comma1);
        background.add(stackLabel); background.add(stackAlphabet); background.add(comma2);
        background.add(generateButton);
        panel.add(background);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
    }
    public void detailFrame() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);

        JLabel background = new JLabel();
        background.setBounds(0,0,800,600);

        ImageIcon backgroundIcon = new ImageIcon("inputFrame.png");
        background.setIcon(backgroundIcon);

        //Final States
        JLabel finalStateLabel = new JLabel("Final States:", SwingConstants.CENTER);
        finalStateLabel.setBounds(325,20,150,40);
        finalStateLabel.setFont(font);
        finalStateLabel.setForeground(Color.WHITE);

        ArrayList<JCheckBox> finalStates = new ArrayList<>();

        int xPosition, yPosition = 75;
        if (statesArray.length <= 10)
            xPosition = (800 - (statesArray.length * 50) - ((statesArray.length - 1) * 10)) / 2;
        else
            xPosition = 105;
        int counter = 0, count = statesArray.length;

        for (String state : statesArray) {
            JCheckBox stateBox = new JCheckBox(state);
            stateBox.setBounds(xPosition, yPosition, 60, 60);
            stateBox.setAlignmentX(Component.CENTER_ALIGNMENT);
            stateBox.setAlignmentY(Component.CENTER_ALIGNMENT);
            stateBox.setVerticalTextPosition(SwingConstants.TOP);
            stateBox.setHorizontalTextPosition(SwingConstants.CENTER);
            stateBox.setFont(font);
            stateBox.setForeground(Color.WHITE);
            stateBox.setLayout(null);

            xPosition += 60; //+= width + 10
            counter++;

            if (counter == 10) {
                count -= 10;
                xPosition = (800 - (count * 50) - ((count - 1) * 10)) / 2;
                yPosition += 40;
                counter = 0;
            }

            finalStates.add(stateBox);
            background.add(stateBox);
        }

        //Start State
        JLabel startStateLabel = new JLabel("Start State:", SwingConstants.CENTER);
        startStateLabel.setBounds(50,220,200,40);
        startStateLabel.setFont(font);
        startStateLabel.setForeground(Color.WHITE);

        JComboBox<String> startState = new JComboBox<>(statesArray);
        startState.setBounds(50,250,200,40);
        startState.setFont(font);
        startState.setBorder(null);
        startState.setAlignmentX(SwingConstants.CENTER);

        //Start Stack Alphabet
        JLabel alphabetLabel = new JLabel("Start Stack Alphabet:", SwingConstants.CENTER);
        alphabetLabel.setBounds(550,220,200,40);
        alphabetLabel.setFont(font);
        alphabetLabel.setForeground(Color.WHITE);

        JComboBox<String> startStack = new JComboBox<>(stackAlphabetsArray);
        startStack.setBounds(550,250,200,40);
        startStack.setFont(font);
        startStack.setBorder(null);
        startStack.setAlignmentX(SwingConstants.CENTER);

        JButton nextPageButton = new JButton();
        nextPageButton.setBounds(340,410, 120, 120);
        nextPageButton.setBorderPainted(false);
        nextPageButton.setFocusPainted(false);
        nextPageButton.setBorder(null);
        nextPageButton.addActionListener(e -> {
            Main.setStartState(String.valueOf(startState.getSelectedItem()));
            Main.setStackStart(String.valueOf(startStack.getSelectedItem()));
            for (JCheckBox checkBox : finalStates) {
                if (checkBox.isSelected())
                    Main.addFinalState(checkBox.getText());
            }

            panel.setVisible(false);
            transitionsFrame();
        });

        ImageIcon nextPageIcon = new ImageIcon("next.png");
        nextPageButton.setIcon(nextPageIcon);

        background.add(finalStateLabel);
        background.add(startStateLabel); background.add(startState);
        background.add(alphabetLabel); background.add(startStack);
        background.add(nextPageButton);
        panel.add(background);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
    }
    public void transitionsFrame() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);

        JLabel background = new JLabel();
        background.setBounds(0,0,800,600);

        ImageIcon backgroundIcon = new ImageIcon("inputFrame.png");
        background.setIcon(backgroundIcon);

        //current State
        JLabel currentStateLabel = new JLabel("Current State:", SwingConstants.CENTER);
        currentStateLabel.setBounds(0,100,300,40);
        currentStateLabel.setFont(font);
        currentStateLabel.setForeground(Color.WHITE);

        //State
        JLabel statesLabel = new JLabel("State:", SwingConstants.CENTER);
        statesLabel.setBounds(0,200,100,40);
        statesLabel.setFont(font);
        statesLabel.setForeground(Color.WHITE);

        JComboBox<String> states = new JComboBox<>(statesArray);
        states.setBounds(100,200,200,40);
        states.setFont(font);
        states.setBorder(null);
        states.setAlignmentX(SwingConstants.CENTER);

        //Alphabets
        JLabel alphabetsLabel = new JLabel("Alphabet:", SwingConstants.CENTER);
        alphabetsLabel.setBounds(0,230,100,40);
        alphabetsLabel.setFont(font);
        alphabetsLabel.setForeground(Color.WHITE);

        JComboBox<String> alphabets = new JComboBox<>(alphabetsArray);
        alphabets.setBounds(100,230,200,40);
        alphabets.setFont(font);
        alphabets.setBorder(null);
        alphabets.setAlignmentX(SwingConstants.CENTER);

        //Stack Alphabets
        JLabel stackAlphabetsLabel1 = new JLabel("Stack", SwingConstants.CENTER);
        stackAlphabetsLabel1.setBounds(0,260,100,40);
        stackAlphabetsLabel1.setFont(font);
        stackAlphabetsLabel1.setForeground(Color.WHITE);

        JLabel stackAlphabetsLabel2 = new JLabel("Alphabets:", SwingConstants.CENTER);
        stackAlphabetsLabel2.setBounds(0,275,100,40);
        stackAlphabetsLabel2.setFont(font);
        stackAlphabetsLabel2.setForeground(Color.WHITE);

        JComboBox<String> stackAlphabets = new JComboBox<>(stackAlphabetsArray);
        stackAlphabets.setBounds(100,260,200,40);
        stackAlphabets.setFont(font);
        stackAlphabets.setBorder(null);
        stackAlphabets.setAlignmentX(SwingConstants.CENTER);

        //Next State
        JLabel nextStateLabel = new JLabel("Next State:", SwingConstants.CENTER);
        nextStateLabel.setBounds(520,100,280,40);
        nextStateLabel.setFont(font);
        nextStateLabel.setForeground(Color.WHITE);

        //New State
        JLabel newStateLabel = new JLabel("New State:", SwingConstants.CENTER);
        newStateLabel.setBounds(470,215,100,40);
        newStateLabel.setFont(font);
        newStateLabel.setForeground(Color.WHITE);

        JComboBox<String> newState = new JComboBox<>(statesArray);
        newState.setBounds(570,215,200,40);
        newState.setFont(font);
        newState.setBorder(null);
        newState.setAlignmentX(SwingConstants.CENTER);

        //New Stack Alphabets
        JLabel newStackAlphabetsLabel1 = new JLabel("New Stack", SwingConstants.CENTER);
        newStackAlphabetsLabel1.setBounds(470,245,100,40);
        newStackAlphabetsLabel1.setFont(font);
        newStackAlphabetsLabel1.setForeground(Color.WHITE);

        JLabel newStackAlphabetsLabel2 = new JLabel("Alphabets:", SwingConstants.CENTER);
        newStackAlphabetsLabel2.setBounds(470,260,100,40);
        newStackAlphabetsLabel2.setFont(font);
        newStackAlphabetsLabel2.setForeground(Color.WHITE);

        JTextField newStackAlphabets = new RoundJTextField(5);
        newStackAlphabets.setBounds(570,260,200,23);
        newStackAlphabets.setFont(font);
        newStackAlphabets.setForeground(Color.BLACK);
        newStackAlphabets.setBackground(Color.WHITE);
        newStackAlphabets.setBorder(null);
        newStackAlphabets.setHorizontalAlignment(SwingConstants.CENTER);

        JButton addButton = new JButton();
        addButton.setBounds(250,410, 120, 120);
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        addButton.setBorder(null);
        addButton.addActionListener(e -> {
            if (!Objects.equals(newStackAlphabets.getText(), "") && Main.checkStackAlphabet(newStackAlphabets.getText())) {
                Main.addTransition(String.valueOf(states.getSelectedItem()), String.valueOf(alphabets.getSelectedItem())
                        , String.valueOf(stackAlphabets.getSelectedItem()), String.valueOf(newState.getSelectedItem())
                        , newStackAlphabets.getText());
            }
            panel.setVisible(false);
            transitionsFrame();
        });

        ImageIcon addIcon = new ImageIcon("add.png");
        addButton.setIcon(addIcon);

        JButton saveButton = new JButton();
        saveButton.setBounds(430,410, 120, 120);
        saveButton.setBorderPainted(false);
        saveButton.setFocusPainted(false);
        saveButton.setBorder(null);
        saveButton.addActionListener(e -> {
            if (!Objects.equals(newStackAlphabets.getText(), "") && Main.checkStackAlphabet(newStackAlphabets.getText())) {
                Main.addTransition(String.valueOf(states.getSelectedItem()), String.valueOf(alphabets.getSelectedItem())
                        , String.valueOf(stackAlphabets.getSelectedItem()), String.valueOf(newState.getSelectedItem())
                        , newStackAlphabets.getText());
                panel.setVisible(false);
                loadingFrame("input");
            }
            else {
                panel.setVisible(false);
                transitionsFrame();
            }
        });

        ImageIcon saveIcon = new ImageIcon("checkmark.png");
        saveButton.setIcon(saveIcon);

        background.add(currentStateLabel);
        background.add(statesLabel); background.add(states);
        background.add(alphabetsLabel); background.add(alphabets);
        background.add(stackAlphabetsLabel1); background.add(stackAlphabetsLabel2); background.add(stackAlphabets);
        background.add(nextStateLabel);
        background.add(newStateLabel); background.add(newState);
        background.add(newStackAlphabetsLabel1); background.add(newStackAlphabetsLabel2); background.add(newStackAlphabets);
        background.add(addButton); background.add(saveButton);
        panel.add(background);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
    }
    public void loadingFrame(String frameTxt) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);

        JLabel background = new JLabel();
        background.setBounds(0,0,800,600);

        ImageIcon backgroundIcon = new ImageIcon("loading.gif");
        background.setIcon(backgroundIcon);

        JLabel loadingLabel = new JLabel("Generating...");
        loadingLabel.setBounds(300,0,250,50);
        loadingLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setHorizontalTextPosition(JLabel.CENTER);

        background.add(loadingLabel);
        panel.add(background);

        frame.getContentPane().add(BorderLayout.CENTER, panel);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (frameTxt.equals("input"))
                    inputFrame();
                else if (frameTxt.equals("output"))
                    outputFrame();
                panel.setVisible(false);
            }
        }, 4000);
    }
    public void inputFrame() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);

        JLabel background = new JLabel();
        background.setBounds(0,0,800,600);

        ImageIcon backgroundIcon = new ImageIcon("inputFrame.png");
        background.setIcon(backgroundIcon);

        //Input String
        JLabel inputLabel = new JLabel("Input String", SwingConstants.CENTER);
        inputLabel.setBounds(325,50,150,40);
        inputLabel.setFont(font);
        inputLabel.setForeground(Color.WHITE);

        JTextField input = new RoundJTextField(15);
        input.setBounds(100, 100, fieldSize.width, fieldSize.height);
        input.setFont(font);
        input.setForeground(Color.WHITE);
        input.setBackground(new Color(107, 128, 133));
        input.setBorder(null);
        input.setHorizontalAlignment(SwingConstants.CENTER);

        //Initial State
        JLabel startState = new JLabel("Start State", SwingConstants.CENTER);
        startState.setBounds(50,250,150,40);
        startState.setFont(font);
        startState.setForeground(Color.WHITE);

        JTextField initial = new RoundJTextField(15);
        initial.setBounds(50,300,150,40);
        initial.setText(Main.getStartState());
        initial.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        initial.setForeground(Color.WHITE);
        initial.setBackground(new Color(107, 128, 133));
        initial.setBorder(null);
        initial.setEditable(false);
        initial.setHorizontalAlignment(SwingConstants.CENTER);

        //Stack Start
        JLabel stackStartLabel = new JLabel("Stack Start", SwingConstants.CENTER);
        stackStartLabel.setBounds(600,250,150,40);
        stackStartLabel.setFont(font);
        stackStartLabel.setForeground(Color.WHITE);

        JTextField stackStart = new RoundJTextField(15);
        stackStart.setBounds(600,300,150,40);
        stackStart.setText(Main.getStackStart());
        stackStart.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        stackStart.setForeground(Color.WHITE);
        stackStart.setBackground(new Color(107, 128, 133));
        stackStart.setBorder(null);
        stackStart.setEditable(false);
        stackStart.setHorizontalAlignment(SwingConstants.CENTER);

        JButton generateButton = new JButton();
        generateButton.setBounds(340,410, 120, 120);
        generateButton.setBorderPainted(false);
        generateButton.setFocusPainted(false);
        generateButton.setBorder(null);
        generateButton.addActionListener(e -> {
            if (!Objects.equals(input.getText(), "")) {
                if (Main.checkInput(input.getText())) {
                    Main.setInputString(input.getText());
                    Main.checkAcceptance();
                    panel.setVisible(false);
                    loadingFrame("output");
                }
            }
            else {
                panel.setVisible(false);
                inputFrame();
            }
        });

        ImageIcon generateIcon = new ImageIcon("generate.png");
        generateButton.setIcon(generateIcon);

        background.add(inputLabel); background.add(input);
        background.add(startState); background.add(initial);
        background.add(stackStartLabel); background.add(stackStart);
        background.add(generateButton);
        panel.add(background);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
    }
    public void outputFrame() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);

        JLabel background = new JLabel();
        background.setBounds(0,0,800,600);

        ImageIcon backgroundIcon = new ImageIcon("redFrame.gif");
        background.setIcon(backgroundIcon);

        String[] columns = {"Row", "State", "Input", "Transition", "Stack", "State after move"};
        String[][] data = setTableDate();
        int rowsCount = data.length, columnsCount = 6;

        JTable table = new JTable(data, columns);
        table.setBounds(0,0,columnsCount * 100, rowsCount * 25);
        table.setBorder(null);
        table.setFocusable(false);
        table.setFont(font);
        table.setBackground(Color.darkGray);
        table.setForeground(Color.WHITE);
        table.getTableHeader().setFont(font);
        table.getTableHeader().setBackground(new Color(140, 14, 14));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setAlignmentX(Component.CENTER_ALIGNMENT);
        table.setAlignmentY(Component.CENTER_ALIGNMENT);

        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer)table.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);

        //setting each column width
        table. getColumnModel(). getColumn(0). setPreferredWidth(40);
        table. getColumnModel(). getColumn(1). setPreferredWidth(70);
        table. getColumnModel(). getColumn(2). setPreferredWidth(110);
        table. getColumnModel(). getColumn(3). setPreferredWidth(200);
        table. getColumnModel(). getColumn(4). setPreferredWidth(120);
        table. getColumnModel(). getColumn(5). setPreferredWidth(110);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,0,650, rowsCount * 25);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setFocusable(false);
        scrollPane.setFont(font);
        scrollPane.setBackground(Color.darkGray);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setAlignmentY(Component.CENTER_ALIGNMENT);
        scrollPane.getViewport().setBackground(Color.BLACK);

        JTextField output = new RoundJTextField(15);
        output.setBounds(650,20,130,40);
        output.setFont(font);
        output.setForeground(Color.BLACK);
        output.setBackground(Color.WHITE);
        output.setBorder(null);
        output.setEditable(false);
        output.setHorizontalAlignment(SwingConstants.CENTER);

        if (Main.getAccept())
            output.setText("Acceptable!");
        else
            output.setText("Not-Acceptable!");

        JButton exitButton = new JButton("Restart");
        exitButton.setBounds(700,410, 100, 100);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setBorder(null);
        exitButton.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
        exitButton.setForeground(new Color(140, 14, 14));
        exitButton.setBackground(Color.BLACK);
        exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
        exitButton.setVerticalTextPosition(SwingConstants.CENTER);
        exitButton.addActionListener(e -> {
            panel.setVisible(false);
            Main.initialize();
            mainPanel();
        });

        background.add(exitButton);
        background.add(output);
        panel.setLayout(null);
        panel.add(scrollPane);
        panel.add(background);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
    }

    public void setStatesArray() {
        statesArray = new String[Main.getStates().size()];
        for (int i = 0; i < statesArray.length; i++) {
            statesArray[i] = Main.getStates().get(i);
        }
    }
    public void setAlphabetsArray() {
        alphabetsArray = new String[Main.getAlphabets().size()];
        for (int i = 0; i < alphabetsArray.length; i++) {
            alphabetsArray[i] = Main.getAlphabets().get(i);
        }
    }
    public void setStackAlphabetArray() {
        stackAlphabetsArray = new String[Main.getStackAlphabet().size()];
        for (int i = 0; i < stackAlphabetsArray.length; i++) {
            stackAlphabetsArray[i] = Main.getStackAlphabet().get(i);
        }
    }
    public String[][] setTableDate() {
        String[][] tableData = new String[Main.getStepsCount()][6];
        for (int i = 0; i < Main.getStepsCount(); i++) {
            String[] rowData = Main.getSteps().get(i).split("/");

            tableData[i][0] = String.valueOf(i);
            System.arraycopy(rowData, 0, tableData[i], 1, 5);
        }
        return tableData;
    }
}
