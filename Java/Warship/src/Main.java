import javax.swing.*; //GUI project
import java.awt.*; //graphic class
import java.io.*; //input output package
import java.util.Objects; //static utility methods
import java.util.Random; //generates a stream of pseudorandom numbers

public class Main {
    static JFrame mainFrame = new JFrame("BattleShip");
    static JFrame startFrame = new JFrame("Start the Game");
    static JFrame recordsFrame = new JFrame("Records");
    static JFrame introductionFrame = new JFrame("Introduction");
    static JFrame dimensionInputFrame = new JFrame("Initialization");

    static JMenuBar menuBar = new JMenuBar();

    static JMenu menu = new JMenu("Menu");
    static JMenu helpMenu = new JMenu("Help");

    static JTextField textField0 = new JTextField(); //players name text field

    static String playerName = textField0.getText();
    static String[] dimensionOption = {"5", "7", "9", "11", "13", "15", "17", "19"};

    static JComboBox<String> dimensionComboBox = new JComboBox<>(dimensionOption);

    static int dimension = Integer.parseInt((String) Objects.requireNonNull(dimensionComboBox.getSelectedItem()));
    static int playerCorrectShots = 0, playerWrongShots = 0, playerRemainingShips = dimension, playerScore;
    static int enemyCorrectShots = 0, enemyWrongShots = 0, enemyRemainingShips = dimension, enemyScore;
    static int timesPlayed = 0;

    static long startTime, endTime, timeElapsed;

    static JButton[][] playerShip;
    static JButton[][] enemyShip;

    static BufferedWriter writer;

    static {
        try {
            writer = new BufferedWriter(new FileWriter("records.txt", true));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static JPanel playerGameDetailPanel = new JPanel();
    static JPanel enemyGameDetailPanel = new JPanel();
    static JPanel playerSide = new JPanel();
    static JPanel enemySide = new JPanel();

    static JLabel playerCorrectShotsLabel = new JLabel();
    static JLabel playerWrongShotsLabel = new JLabel();
    static JLabel playerRemainingShipsLabel = new JLabel();
    static JLabel playerScoreLabel = new JLabel();
    static JLabel enemyCorrectShotsLabel = new JLabel();
    static JLabel enemyWrongShotsLabel = new JLabel();
    static JLabel enemyRemainingShipsLabel = new JLabel();
    static JLabel enemyScoreLabel = new JLabel();
    static JLabel timeLabel = new JLabel();

    static ImageIcon framesIcon = new ImageIcon("frameIcon");
    static ImageIcon backButtonIcon = new ImageIcon("back.png");
    static ImageIcon shipIcon = new ImageIcon("warship.png");
    static ImageIcon nullIcon = new ImageIcon("null.jpg");

    static Random randNumber = new Random();

    public static void main(String[] args) {
        summonMethods();
    }
    public static void summonMethods() {
        setMenu();
        setMainFrame();
        setStartFrame();
        setIntroductionFrame();
        setDimensionComboBox();
        playerShip = new JButton[dimension][dimension / 2];
        enemyShip = new JButton[dimension][dimension / 2];
        trackingScore();
    }
    public static void setMainFrame() {
        JLabel mainLabel1 = new JLabel("Welcome to BattleShip !");
        JLabel mainLabel2 = new JLabel("Please enter your name in the box below");

        ImageIcon ImageIcon2 = new ImageIcon("mainIcon");

        JButton submit = new JButton("Submit");

        mainLabel1.setHorizontalTextPosition(JLabel.CENTER);
        mainLabel1.setVerticalTextPosition(JLabel.BOTTOM);
        mainLabel1.setBounds(145, 100, 550, 130);
        mainLabel1.setIcon(ImageIcon2);

        mainLabel2.setBounds(252, 240, 550, 15);

        textField0.setBounds(325, 280, 200, 20);

        submit.setBounds(230, 280, 90, 20);
        submit.setBackground(Color.LIGHT_GRAY);
        submit.setFocusable(false);
        submit.setBorder(BorderFactory.createEtchedBorder());
        submit.addActionListener(a -> {
            playerName = textField0.getText();
            JOptionPane.showMessageDialog(null, "Please choose the next Window in the menu bar");
            submit.setEnabled(false);
        });

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setBounds(300, 100, 750, 550);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
        mainFrame.add(mainLabel1);
        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(mainLabel2);
        mainFrame.add(submit);
        mainFrame.add(textField0);
        mainFrame.setIconImage(framesIcon.getImage());
        mainFrame.setVisible(true);
    }
    public static void setStartFrame() {
        JButton backButtonStartFrame = new JButton();
        backButtonStartFrame.setBounds(700, 5, 18, 15);
        backButtonStartFrame.setIcon(backButtonIcon);
        backButtonStartFrame.setBackground(Color.WHITE);
        backButtonStartFrame.setHorizontalAlignment(JButton.CENTER);
        backButtonStartFrame.setVerticalAlignment(JButton.CENTER);
        backButtonStartFrame.setBorder(BorderFactory.createEtchedBorder());
        backButtonStartFrame.addActionListener(e -> {
            startFrame.setVisible(false);
            mainFrame.setVisible(true);
        });

        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setBounds(300, 100, 750, 550);
        startFrame.setLayout(null);
        startFrame.setResizable(false);
        startFrame.setIconImage(framesIcon.getImage());
        startFrame.add(backButtonStartFrame);
    }
    public static void setIntroductionFrame() {
        JButton backButtonIntroductionFrame = new JButton();
        JPanel introductionPanel = new JPanel();

        JLabel introductionLine1 = new JLabel("Hi and welcome to BattleShip again.");
        JLabel introductionLine2 = new JLabel("In order to win this game, you need to destroy all the enemy ships;" +
                " otherwise the enemy will take over your land.");
        JLabel introductionLine3 = new JLabel("you each have ships,as many as the dimension of the battlefield.");
        JLabel introductionLine4 = new JLabel("you have to choose the dimension of the field," +
                " and enter the coordinates of your ships.");
        JLabel introductionLine5 = new JLabel("your enemy also put their ships in random positions which you are uninformed of.");
        JLabel introductionLine6 = new JLabel("you each take turns to shoot at your opponents land; you start first.");
        JLabel introductionLine7 = new JLabel("during the game, your performance will be checked based on your score.");
        JLabel introductionLine8 = new JLabel("the number of your correct and wrong shots will be tracked," +
                " and they will be shown to you as well as your score.");
        JLabel introductionLine9 = new JLabel("in the end you can see your record among others.");
        JLabel introductionLine10 = new JLabel("I hope you'll enjoy the game.");

        introductionLine1.setBounds(0, 90, 650, 25);
        introductionLine1.setHorizontalAlignment(JLabel.CENTER);
        introductionLine1.setVerticalAlignment(JLabel.CENTER);
        introductionLine2.setBounds(0, 137, 650, 25);
        introductionLine2.setHorizontalAlignment(JLabel.CENTER);
        introductionLine2.setVerticalAlignment(JLabel.CENTER);
        introductionLine3.setBounds(0, 162, 650, 25);
        introductionLine3.setHorizontalAlignment(JLabel.CENTER);
        introductionLine3.setVerticalAlignment(JLabel.CENTER);
        introductionLine4.setBounds(0, 187, 650, 25);
        introductionLine4.setHorizontalAlignment(JLabel.CENTER);
        introductionLine4.setVerticalAlignment(JLabel.CENTER);
        introductionLine5.setBounds(0, 212, 650, 25);
        introductionLine5.setHorizontalAlignment(JLabel.CENTER);
        introductionLine5.setVerticalAlignment(JLabel.CENTER);
        introductionLine6.setBounds(0, 237, 650, 25);
        introductionLine6.setHorizontalAlignment(JLabel.CENTER);
        introductionLine6.setVerticalAlignment(JLabel.CENTER);
        introductionLine7.setBounds(0, 262, 650, 25);
        introductionLine7.setHorizontalAlignment(JLabel.CENTER);
        introductionLine7.setVerticalAlignment(JLabel.CENTER);
        introductionLine8.setBounds(0, 287, 650, 25);
        introductionLine8.setHorizontalAlignment(JLabel.CENTER);
        introductionLine8.setVerticalAlignment(JLabel.CENTER);
        introductionLine9.setBounds(0, 312, 650, 25);
        introductionLine9.setHorizontalAlignment(JLabel.CENTER);
        introductionLine9.setVerticalAlignment(JLabel.CENTER);
        introductionLine10.setBounds(0, 337, 650, 25);
        introductionLine10.setHorizontalAlignment(JLabel.CENTER);
        introductionLine10.setVerticalAlignment(JLabel.CENTER);

        backButtonIntroductionFrame.setBounds(700, 5, 18, 15);
        backButtonIntroductionFrame.setIcon(backButtonIcon);
        backButtonIntroductionFrame.setBackground(Color.WHITE);
        backButtonIntroductionFrame.setHorizontalAlignment(JButton.CENTER);
        backButtonIntroductionFrame.setVerticalAlignment(JButton.CENTER);
        backButtonIntroductionFrame.setBorder(BorderFactory.createEtchedBorder());
        backButtonIntroductionFrame.addActionListener(e -> {
            introductionFrame.setVisible(false);
            mainFrame.setVisible(true);
        });

        introductionPanel.setBounds(43, 30, 650, 450);
        introductionPanel.setLayout(null);
        introductionPanel.setBackground(Color.LIGHT_GRAY);
        introductionPanel.add(introductionLine1);
        introductionPanel.add(introductionLine2);
        introductionPanel.add(introductionLine3);
        introductionPanel.add(introductionLine4);
        introductionPanel.add(introductionLine5);
        introductionPanel.add(introductionLine6);
        introductionPanel.add(introductionLine7);
        introductionPanel.add(introductionLine8);
        introductionPanel.add(introductionLine9);
        introductionPanel.add(introductionLine10);

        introductionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        introductionFrame.setBounds(300, 100, 750, 550);
        introductionFrame.setLayout(null);
        introductionFrame.setResizable(false);
        introductionFrame.setIconImage(framesIcon.getImage());
        introductionFrame.add(introductionPanel);
        introductionFrame.add(backButtonIntroductionFrame);
    }
    public static void setDimensionComboBox() {
        JLabel initializationLabel = new JLabel("Please choose the dimension of your game field");
        JLabel dimensionLabel = new JLabel("Dimension:");

        initializationLabel.setBounds(3, 80, 300, 50);
        dimensionLabel.setBounds(65, 108, 75, 50);

        dimensionInputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dimensionInputFrame.setBounds(550, 225, 300, 300);
        dimensionInputFrame.add(initializationLabel);
        dimensionInputFrame.add(dimensionComboBox);
        dimensionInputFrame.add(dimensionLabel);
        dimensionInputFrame.setLayout(null);
        dimensionInputFrame.setResizable(false);
        dimensionInputFrame.setIconImage(framesIcon.getImage());

        dimensionComboBox.setBounds(140, 125, 50, 20);
        dimensionComboBox.setBackground(Color.LIGHT_GRAY);
        dimensionComboBox.setSelectedIndex(-1);
        dimensionComboBox.addActionListener(e -> {
            dimension = Integer.parseInt((String) Objects.requireNonNull(dimensionComboBox.getSelectedItem()));
            playerShip = new JButton[dimension][dimension / 2];
            enemyShip = new JButton[dimension][dimension / 2];
            gameField();
            coordinateInput();
        });
    }
    public static void coordinateInput() {
        JFrame[] shipCoordinateFrame = new JFrame[dimension];

        JLabel[] rowPosition = new JLabel[dimension];
        JLabel[] columnPosition = new JLabel[dimension];
        JLabel[] rowLabel = new JLabel[dimension];
        JLabel[] columnLabel = new JLabel[dimension];

        String[] shipRowPosition = new String[dimension];
        String[] shipColumnPosition = new String[dimension / 2];

        for (int i = 0; i < dimension; i++)
            shipRowPosition[i] = String.valueOf(i);
        for (int j = 0; j < dimension / 2; j++)
            shipColumnPosition[j] = String.valueOf(j);

        dimensionInputFrame.setVisible(false);

        for (int playerShipCounter = 0; playerShipCounter < dimension; playerShipCounter++) {
            rowPosition[playerShipCounter] = new JLabel("Please choose the Row Position of your ship");
            columnPosition[playerShipCounter] = new JLabel("Please choose the Column Position of your ship");
            rowLabel[playerShipCounter] = new JLabel("Row:");
            columnLabel[playerShipCounter] = new JLabel("Column:");

            JComboBox<String> shipRowPositionComboBox = new JComboBox<>(shipRowPosition);
            JComboBox<String> shipColumnPositionComboBox = new JComboBox<>(shipColumnPosition);
            shipRowPositionComboBox.setSelectedIndex(-1);
            shipColumnPositionComboBox.setSelectedIndex(-1);
            shipColumnPositionComboBox.setEnabled(false);

            rowPosition[playerShipCounter].setBounds(13, 50, 270, 50);
            columnPosition[playerShipCounter].setBounds(5, 120, 270, 50);

            rowLabel[playerShipCounter].setBounds(82, 83, 50, 50);
            columnLabel[playerShipCounter].setBounds(65, 153, 50, 50);

            shipRowPositionComboBox.setBackground(Color.LIGHT_GRAY);
            shipColumnPositionComboBox.setBackground(Color.LIGHT_GRAY);
            shipRowPositionComboBox.setBounds(125, 100, 50, 20);
            shipColumnPositionComboBox.setBounds(125, 170, 50, 20);

            int shipNumber = playerShipCounter + 1;
            shipCoordinateFrame[playerShipCounter] = new JFrame("ship number " + shipNumber);
            shipCoordinateFrame[playerShipCounter].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            shipCoordinateFrame[playerShipCounter].setBounds(550, 225, 300, 300);
            shipCoordinateFrame[playerShipCounter].setLayout(null);
            shipCoordinateFrame[playerShipCounter].setResizable(false);
            shipCoordinateFrame[playerShipCounter].setIconImage(framesIcon.getImage());
            shipCoordinateFrame[playerShipCounter].add(rowLabel[playerShipCounter]);
            shipCoordinateFrame[playerShipCounter].add(columnLabel[playerShipCounter]);
            shipCoordinateFrame[playerShipCounter].add(rowPosition[playerShipCounter]);
            shipCoordinateFrame[playerShipCounter].add(columnPosition[playerShipCounter]);
            shipCoordinateFrame[playerShipCounter].add(shipRowPositionComboBox);
            shipCoordinateFrame[playerShipCounter].add(shipColumnPositionComboBox);
            shipCoordinateFrame[playerShipCounter].setVisible(true);

            int finalPlayerShipCounter = playerShipCounter;
            shipRowPositionComboBox.addActionListener(e1 -> {
                int row = Integer.parseInt((String) Objects.requireNonNull(shipRowPositionComboBox.getSelectedItem()));
                shipColumnPositionComboBox.setEnabled(true);
                shipColumnPositionComboBox.addActionListener(e2 -> {
                    int column = Integer.parseInt((String) Objects.requireNonNull(shipColumnPositionComboBox.getSelectedItem()));
                    playerShip[row][column].setIcon(shipIcon);
                    playerShip[row][column].setBackground(Color.WHITE);
                    playerShip[row][column].setHorizontalAlignment(JButton.CENTER);
                    playerShip[row][column].setVerticalAlignment(JButton.CENTER);
                    playerShip[row][column].addActionListener(e -> {
                        enemyCorrectShots++;
                        playerRemainingShips--;
                        enemyScore = enemyCorrectShots + (dimension / (enemyWrongShots + 1)) + enemyRemainingShips;
                        startFrame.setVisible(false);
                        endTime = System.currentTimeMillis();
                        trackingScore();
                        victory();
                        startFrame.setVisible(true);
                        playerShip[row][column].setEnabled(false);
                    });
                    shipCoordinateFrame[finalPlayerShipCounter].setVisible(false);
                    if (finalPlayerShipCounter == 0) {
                        startTime = System.currentTimeMillis();
                        startFrame.setVisible(true);
                    }
                });
            });
        }
    }
    public static void gameField() {
        int shipButtonWidth = 600 / dimension, shipButtonHeight = 250 / dimension;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension / 2; j++) {
                int finalI = i, finalJ = j;
                playerShip[i][j] = new JButton();
                playerShip[i][j].setBounds(i * shipButtonWidth, j * shipButtonHeight, shipButtonWidth, shipButtonHeight);
                playerShip[i][j].setFocusable(false);
                playerShip[i][j].setBorder(BorderFactory.createEtchedBorder());
                playerShip[i][j].setIcon(nullIcon);
                playerShip[i][j].setBackground(Color.WHITE);
                playerShip[i][j].setHorizontalAlignment(JButton.CENTER);
                playerShip[i][j].setVerticalAlignment(JButton.CENTER);
                playerShip[i][j].addActionListener(e -> {
                    enemyWrongShots++;
                    enemyScore = enemyCorrectShots + (dimension / (enemyWrongShots + 1)) + enemyRemainingShips;
                    startFrame.setVisible(false);
                    endTime = System.currentTimeMillis();
                    trackingScore();
                    startFrame.setVisible(true);
                    playerShip[finalI][finalJ].setEnabled(false);
                });
                playerSide.add(playerShip[i][j]);
                enemyShip[i][j] = new JButton();
                enemyShip[i][j].setBounds(i * shipButtonWidth, j * shipButtonHeight, shipButtonWidth, shipButtonHeight);
                enemyShip[i][j].setFocusable(false);
                enemyShip[i][j].setBorder(BorderFactory.createEtchedBorder());
                enemyShip[i][j].setBackground(Color.WHITE);
                enemyShip[i][j].setIcon(nullIcon);
                enemyShip[i][j].setHorizontalAlignment(JButton.CENTER);
                enemyShip[i][j].setVerticalAlignment(JButton.CENTER);
                enemyShip[i][j].addActionListener(e -> {
                    playerWrongShots++;
                    playerScore = playerCorrectShots + (dimension / (playerWrongShots + 1)) + playerRemainingShips;
                    startFrame.setVisible(false);
                    endTime = System.currentTimeMillis();
                    trackingScore();
                    startFrame.setVisible(true);
                    enemyShip[finalI][finalJ].setEnabled(false);
                    enemyAttack();
                });
                enemySide.add(enemyShip[i][j]);
            }
        }
        int[] randEnemyShipRowPosition = new int[dimension];
        int[] randEnemyShipColumnPosition = new int[dimension];
        for (int enemyShipCounter = 0; enemyShipCounter < dimension; ) {
            boolean findRandom = true;
            randEnemyShipRowPosition[enemyShipCounter] = randNumber.nextInt(dimension);
            randEnemyShipColumnPosition[enemyShipCounter] = randNumber.nextInt(dimension / 2);
            for (int randomCheck = enemyShipCounter - 1; randomCheck >= 0; randomCheck--) {
                if (randEnemyShipRowPosition[enemyShipCounter] == randEnemyShipRowPosition[randomCheck]) {
                    findRandom = false;
                    break;
                }
            }
            if (findRandom) {
                int finalEnemyShipCounter = enemyShipCounter;
                enemyShip[randEnemyShipRowPosition[enemyShipCounter]]
                        [randEnemyShipColumnPosition[enemyShipCounter]].addActionListener(e1 -> {
                    playerCorrectShots++;
                    enemyRemainingShips--;
                    playerScore = playerCorrectShots + (dimension / (playerWrongShots + 1)) + playerRemainingShips;
                    startFrame.setVisible(false);
                    endTime = System.currentTimeMillis();
                    trackingScore();
                    victory();
                    startFrame.setVisible(true);
                    enemyShip[randEnemyShipRowPosition[finalEnemyShipCounter]]
                            [randEnemyShipColumnPosition[finalEnemyShipCounter]].setEnabled(false);
                });
                enemyShipCounter++;
            }
        }
        playerSide.setLayout(new GridLayout(dimension, dimension / 2));
        enemySide.setLayout(new GridLayout(dimension, dimension / 2));
        playerSide.setBounds(50, 250, 300, 250);
        enemySide.setBounds(400, 250, 300, 250);
        playerSide.setBackground(Color.GRAY);
        enemySide.setBackground(Color.GRAY);

        startFrame.add(playerSide);
        startFrame.add(enemySide);
    }
    public static void trackingScore() {
        timeElapsed = endTime - startTime;
        playerCorrectShotsLabel.setText("player correct Shots: " + playerCorrectShots);
        playerWrongShotsLabel.setText("player wrong shots: " + playerWrongShots);
        playerRemainingShipsLabel.setText("player remaining ships: " + playerRemainingShips);
        playerScoreLabel.setText("player score: " + playerScore);
        enemyCorrectShotsLabel.setText("enemy correct shots: " + enemyCorrectShots);
        enemyWrongShotsLabel.setText("enemy wrong shots: " + enemyWrongShots);
        enemyRemainingShipsLabel.setText("enemy remaining ships: " + enemyRemainingShips);
        enemyScoreLabel.setText("enemy score: " + enemyScore);
        timeLabel.setText("Current time: " + timeElapsed / 1000);

        playerGameDetailPanel.setBounds(50, 25, 300, 200);
        playerGameDetailPanel.setLayout(null);
        enemyGameDetailPanel.setBounds(400, 25, 300, 200);
        enemyGameDetailPanel.setLayout(null);

        timeLabel.setBounds(300, 0, 350, 25);
        timeLabel.setHorizontalTextPosition(JLabel.CENTER);
        timeLabel.setVerticalTextPosition(JLabel.CENTER);

        playerCorrectShotsLabel.setBounds(0, 0, 300, 50);
        playerCorrectShotsLabel.setHorizontalAlignment(JLabel.CENTER);
        playerCorrectShotsLabel.setVerticalAlignment(JLabel.CENTER);

        playerWrongShotsLabel.setBounds(0, 50, 300, 50);
        playerWrongShotsLabel.setHorizontalAlignment(JLabel.CENTER);
        playerWrongShotsLabel.setVerticalAlignment(JLabel.CENTER);

        playerRemainingShipsLabel.setBounds(0, 100, 300, 50);
        playerRemainingShipsLabel.setHorizontalAlignment(JLabel.CENTER);
        playerRemainingShipsLabel.setVerticalAlignment(JLabel.CENTER);

        playerScoreLabel.setBounds(0, 150, 300, 50);
        playerScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        playerScoreLabel.setVerticalAlignment(JLabel.CENTER);

        enemyCorrectShotsLabel.setBounds(0, 0, 300, 50);
        enemyCorrectShotsLabel.setHorizontalAlignment(JLabel.CENTER);
        enemyCorrectShotsLabel.setVerticalAlignment(JLabel.CENTER);

        enemyWrongShotsLabel.setBounds(0, 50, 300, 50);
        enemyWrongShotsLabel.setHorizontalAlignment(JLabel.CENTER);
        enemyWrongShotsLabel.setVerticalAlignment(JLabel.CENTER);

        enemyRemainingShipsLabel.setBounds(0, 100, 300, 50);
        enemyRemainingShipsLabel.setHorizontalAlignment(JLabel.CENTER);
        enemyRemainingShipsLabel.setVerticalAlignment(JLabel.CENTER);

        enemyScoreLabel.setBounds(0, 150, 300, 50);
        enemyScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        enemyScoreLabel.setVerticalAlignment(JLabel.CENTER);

        playerGameDetailPanel.add(playerCorrectShotsLabel);
        playerGameDetailPanel.add(playerWrongShotsLabel);
        playerGameDetailPanel.add(playerRemainingShipsLabel);
        playerGameDetailPanel.add(playerScoreLabel);
        playerGameDetailPanel.setEnabled(true);
        enemyGameDetailPanel.add(enemyCorrectShotsLabel);
        enemyGameDetailPanel.add(enemyWrongShotsLabel);
        enemyGameDetailPanel.add(enemyRemainingShipsLabel);
        enemyGameDetailPanel.add(enemyScoreLabel);
        enemyGameDetailPanel.setEnabled(true);

        startFrame.add(playerGameDetailPanel);
        startFrame.add(enemyGameDetailPanel);
        startFrame.add(timeLabel);
    }
    public static void setMenu() {
        JMenuItem startItem = new JMenuItem("1. Start");
        JMenuItem recordsItem = new JMenuItem("2. Records");
        JMenuItem introductionItem = new JMenuItem("1. Introduction");

        menuBar.add(menu);
        menuBar.add(helpMenu);
        menu.add(startItem);
        menu.add(recordsItem);
        helpMenu.add(introductionItem);

        startItem.addActionListener(e -> {
            if (textField0.getText().trim().isEmpty())
                JOptionPane.showMessageDialog(null, "you haven't submitted your name yet");
            else {
                mainFrame.setVisible(false);
                if (timesPlayed == 0) {
                    dimensionInputFrame.setVisible(true);
                    timesPlayed++;
                } else
                    startFrame.setVisible(true);
            }
        });
        recordsItem.addActionListener(e -> {
            setRecordsFrame();
            mainFrame.setVisible(false);
            recordsFrame.setVisible(true);
        });
        introductionItem.addActionListener(e -> {
            mainFrame.setVisible(false);
            introductionFrame.setVisible(true);
        });
    }
    public static void enemyAttack() {
        int randEnemyAttackRowPosition = randNumber.nextInt(dimension);
        int randEnemyAttackColumnPosition = randNumber.nextInt(dimension / 2);
        if (playerShip[randEnemyAttackRowPosition][randEnemyAttackColumnPosition].isEnabled())
            playerShip[randEnemyAttackRowPosition][randEnemyAttackColumnPosition].doClick();
        else
            enemyAttack();
    }
    public static void setRecordsFrame() {
        JButton backButtonRecordsFrame = new JButton();
        JPanel recordsPanel = new JPanel();
        JLabel[] line = new JLabel[10];

        try {
            BufferedReader reader = new BufferedReader(new FileReader("records.txt"));
            String fileLine;
            int recordLineCounter = 0;
            while ((fileLine = reader.readLine()) != null) {
                line[recordLineCounter] = new JLabel(fileLine);
                line[recordLineCounter].setBounds(0, (recordLineCounter + 2) * 20, 500, 40);
                line[recordLineCounter].setHorizontalAlignment(JLabel.CENTER);
                line[recordLineCounter].setVerticalAlignment(JLabel.CENTER);
                recordsPanel.add(line[recordLineCounter]);
                recordLineCounter++;
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        recordsPanel.setBounds(120, 80, 500, 350);
        recordsPanel.setLayout(null);
        recordsPanel.setBackground(Color.LIGHT_GRAY);

        backButtonRecordsFrame.setBounds(700, 5, 18, 15);
        backButtonRecordsFrame.setIcon(backButtonIcon);
        backButtonRecordsFrame.setBackground(Color.WHITE);
        backButtonRecordsFrame.setHorizontalAlignment(JButton.CENTER);
        backButtonRecordsFrame.setVerticalAlignment(JButton.CENTER);
        backButtonRecordsFrame.setBorder(BorderFactory.createEtchedBorder());
        backButtonRecordsFrame.addActionListener(e -> {
            recordsFrame.setVisible(false);
            mainFrame.setVisible(true);
        });
        recordsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        recordsFrame.setBounds(300, 100, 750, 550);
        recordsFrame.setLayout(null);
        recordsFrame.setResizable(false);
        recordsFrame.setIconImage(framesIcon.getImage());
        recordsFrame.add(backButtonRecordsFrame);
        recordsFrame.add(recordsPanel);
    }
    public static void victory() {
        if (playerCorrectShots == dimension) {
            try {
                writer.write(playerName + "    |  " + playerScore + "\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setRecordsFrame();
            JOptionPane.showMessageDialog(null, "Congrats! you win.Your score is " + playerScore);
            System.exit(0);
        }
        if (enemyCorrectShots == dimension) {
            try {
                writer.write(playerName + "    |  " + playerScore + "\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setRecordsFrame();
            JOptionPane.showMessageDialog(null, "Oops! you lost.Your score is " + playerScore);
            System.exit(0);
        }
    }
}