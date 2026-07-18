import java.util.Arrays;
import java.util.Scanner;
import java.lang.*;

class Project {
    int deadline;
    int profit;
    public Project(int deadline, int profit) {
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.next();
        input = input.replace("{", "");
        input = input.replace("}", "");
        String[] projectsStr = input.split("\\(");
        Project[] projects = new Project[projectsStr.length - 1];

        for (int index = 1; index < projectsStr.length; index++) {
            projectsStr[index] = projectsStr[index].replace(")", "");
            String[] line = projectsStr[index].split(",");

            String deadlineStr = line[1];
            String profitStr = line[2];

            Project project = new Project(Integer.parseInt(deadlineStr), Integer.parseInt(profitStr));
            projects[index - 1] = project;
        }

        maximumWage(projects);
    }
    public static void maximumWage(Object[] projects) {
        int capacity = 24, max = 0, count = 0;
        boolean[] schedule = new boolean[capacity];

        Arrays.sort(projects, (p1, p2) -> Double.compare(((Project) p2).profit, ((Project) p1).profit));

        for (Object project : projects) {
            int deadline = ((Project) project).deadline;
            int profit = ((Project) project).profit;

            int hour = deadline - 1; //available
            while (hour >= 0) {
                if (!schedule[hour]) {
                    schedule[hour] = true;
                    max += profit;
                    count++;
                    break;
                }
                hour--;
            }
        }
        System.out.println(count + " " + max);
    }
}