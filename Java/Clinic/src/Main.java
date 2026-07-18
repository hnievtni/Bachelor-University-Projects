import java.io.*;
import java.nio.file.Path;
import java.util.*;
import static java.lang.System.exit;

class Clinic{
    String name;
    String type;
    String managerName;
    String managerSalary;
    String managerUsername;
    String managerPass;
}
class Section{
    String name;
    String type;
    String recepName;
    String doctorNames;
    String patientNames;
}
class Receptionist{
    String name;
    String sectionName;
    String salary;
    String user;
    String pass;
}
class Doctor{
    String name;
    String type;
    String sectionName;
    String salary;
    String visitPayment;
    String user;
    String pass;
}
class Patient{
    String name;
    String sicknessType;
    String wallet;
    String user;
    String pass;
}
class History{
    String doctorName;
    String patientName;
    String dateOfVisit;
}
class Clinics{
    Vector<Clinic> clinicInfo=new Vector<>();
    Vector<Section> sectionInfo=new Vector<>();
    Vector<Receptionist> receptionistInfo=new Vector<>();
    Vector<Doctor> doctorInfo=new Vector<>();
    Vector<Patient> patientInfo=new Vector<>();
    Vector<History> historyInfo=new Vector<>();
}

public class Main {
    static Scanner input=new Scanner(System.in);
    static Vector<String> clinicsName=new Vector<>();

    public static void main(String[] args) throws IOException {

        System.out.println("Please enter your text file, in order to be arranged.");

        File file=new File(input.nextLine()); //input file
        Scanner fileReader = new Scanner(file);

        setClinics(fileReader); //to save all the data
        mainMenu();
    }
    public static void setClinics(Scanner scanner){

        var clinicsArray=new Vector<Vector<String>>();
        int clinicIndex=-1,clinicCounter=0;

        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            if (nextLine.contains("**")) {
                clinicIndex++;
                clinicCounter++;
                clinicsArray.add(new Vector<>());
            }
            clinicsArray.get(clinicIndex).add(nextLine);
        }
        for (int i=0;i<clinicCounter;i++){
            StringBuilder clinicStringBuilder=new StringBuilder();
            for (int j=0;j<clinicsArray.get(i).size();j++) {
                if (Objects.equals(clinicsArray.get(i).get(j)," "))
                    break;
                else
                    clinicStringBuilder.append(clinicsArray.get(i).get(j)).append("\n");
            }
            clinicsData(String.valueOf(clinicStringBuilder));
        }
    }
    public static void clinicsData(String clinicStr){

        Clinics clinics=new Clinics();
        Scanner clinicScanner=new Scanner(clinicStr);
        String clinicName=null,clinicType=null;

         while (clinicScanner.hasNextLine()){
            String lines=clinicScanner.nextLine();
            if (lines.contains("**")) { //new clinic

                clinicName = lines.split("-")[0].substring(2).strip();
                clinicType=lines.split("-")[1].strip();
            }
            switch (lines.charAt(1)) {
                case '+' -> { //history
                    while (clinicScanner.hasNextLine()) {
                        String historyDataLine = clinicScanner.nextLine();
                        if(historyDataLine.contains("@")){
                            History result = historyInfo(historyDataLine);
                            clinics.historyInfo.add(result);
                            formattingHistoryInfo(clinicName, clinics);
                        }
                        else
                            break;
                    }
                }
                case '&' -> { //section
                    Section result=sectionInfo(lines);
                    clinics.sectionInfo.add(result);
                    formattingSectionInfo(clinicName,clinics);
                }
                case 'M' -> { //manager
                    Clinic result=clinicInfo(clinicName,clinicType,lines);
                    clinics.clinicInfo.add(result);
                    formattingClinicInfo(result,clinics);
                }
                case 'R' -> { //receptionist
                    Receptionist result=receptionistInfo(lines);
                    clinics.receptionistInfo.add(result);
                    formattingReceptionistInfo(clinicName,clinics);
                }
                case '@' -> { //doctor
                    Doctor result=doctorInfo(lines);
                    clinics.doctorInfo.add(result);
                    formattingDoctorInfo(clinicName,clinics);
                }
                case '#' -> { //patient
                    Patient result=patientInfo(lines);
                    clinics.patientInfo.add(result);
                    formattingPatientInfo(clinicName,clinics);
                }
            }
        }
    }
    public static Clinic clinicInfo(String name,String type,String dataLine){

        Clinic clinic=new Clinic();
        clinic.name=name;
        clinic.type=type;
        clinicsName.add(clinic.name);

        if (!dataLine.split("-")[0].isBlank())
            clinic.managerName = dataLine.split("-")[0].substring(3).strip();
        else
            clinic.managerName = "Null";
        if (!dataLine.split("-")[1].isBlank())
            clinic.managerSalary = dataLine.split("-")[1].strip() + "$";
        else
            clinic.managerSalary = "Null";
        if (!dataLine.split("-")[2].isBlank())
            clinic.managerUsername = dataLine.split("-")[2].strip();
        else
            clinic.managerUsername = "Null";
        if (!dataLine.split("-")[3].isBlank())
            clinic.managerPass = dataLine.split("-")[3].strip();
        else
            clinic.managerPass = "Null";
        return clinic;
    }
    public static Section sectionInfo(String dataLine){

        Section section=new Section();
        StringBuilder doctorNamesStr=new StringBuilder();
        StringBuilder patientNamesStr=new StringBuilder();

        for (String info:dataLine.split("-")){
            if (info.contains("@"))
                doctorNamesStr.append(info.substring(2).strip()).append(", ");
            else if (info.contains("#"))
                patientNamesStr.append(info.substring(2).strip()).append(", ");
        }

        if (!dataLine.split("-")[0].isBlank())
            section.name=dataLine.split("-")[0].substring(2).strip();
        else
            section.name="Null";
        if (!dataLine.split("-")[1].isBlank())
            section.type=dataLine.split("-")[1].strip();
        else
            section.type="Null";
        if (!dataLine.split("-")[2].isBlank())
            section.recepName=dataLine.split("-")[2].strip();
        else
            section.recepName="Null";
        if (doctorNamesStr.isEmpty())
            section.doctorNames="Null";
        else
            section.doctorNames= String.valueOf(doctorNamesStr).
                    substring(0,String.valueOf(doctorNamesStr).length()-2);
        if (patientNamesStr.isEmpty())
            section.patientNames="Null";
        else{
            section.patientNames= String.valueOf(patientNamesStr).
                    substring(0,String.valueOf(patientNamesStr).length()-2);
        }
        return section;
    }
    public static Receptionist receptionistInfo(String dataLine) {

        Receptionist receptionist=new Receptionist();

        if (!dataLine.split("-")[0].isBlank())
            receptionist.name =dataLine.split("-")[0].substring(3).strip();
        else
            receptionist.name ="Null";
        if (!dataLine.split("-")[1].isBlank())
            receptionist.sectionName=dataLine.split("-")[1].strip();
        else
            receptionist.sectionName="Null";
        if (!dataLine.split("-")[2].isBlank())
            receptionist.salary=dataLine.split("-")[2].strip()+"$";
        else
            receptionist.salary="Null";
        if (!dataLine.split("-")[3].isBlank())
            receptionist.user =dataLine.split("-")[3].strip();
        else
            receptionist.user ="Null";
        if (!dataLine.split("-")[4].isBlank())
            receptionist.pass =dataLine.split("-")[4].strip();
        else
            receptionist.pass ="Null";
        return receptionist;
    }
    public static Doctor doctorInfo(String dataLine){

        Doctor doctor=new Doctor();

        if (!dataLine.split("-")[0].isBlank())
            doctor.name=dataLine.split("-")[0].substring(2).strip();
        else
            doctor.name="Null";
        if (!dataLine.split("-")[1].isBlank())
            doctor.type=dataLine.split("-")[1].strip();
        else
            doctor.type="Null";
        if (!dataLine.split("-")[2].isBlank())
            doctor.sectionName=dataLine.split("-")[2].strip();
        else
            doctor.sectionName="Null";
        if (!dataLine.split("-")[3].isBlank())
            doctor.salary=dataLine.split("-")[3].strip()+"$";
        else
            doctor.salary="Null";
        if (!dataLine.split("-")[4].isBlank())
            doctor.visitPayment=dataLine.split("-")[4].strip()+"$";
        else
            doctor.visitPayment="Null";
        if (!dataLine.split("-")[5].isBlank())
            doctor.user=dataLine.split("-")[5].strip();
        else
            doctor.user="Null";
        if (!dataLine.split("-")[6].isBlank())
            doctor.pass=dataLine.split("-")[6].strip();
        else
            doctor.pass="Null";
        return doctor;
    }
    public static Patient patientInfo(String dataLine){

        Patient patient=new Patient();

        if (!dataLine.split("-")[0].isBlank())
            patient.name=dataLine.split("-")[0].substring(2).strip();
        else
            patient.name="Null";
        if (!dataLine.split("-")[1].isBlank())
            patient.sicknessType=dataLine.split("-")[1].strip();
        else
            patient.sicknessType="Null";
        if (!dataLine.split("-")[2].isBlank())
            patient.wallet=dataLine.split("-")[2].strip()+"$";
        else
            patient.wallet="Null";
        if (!dataLine.split("-")[3].isBlank())
            patient.user=dataLine.split("-")[3].strip();
        else
            patient.user="Null";
        if (!dataLine.split("-")[4].isBlank())
            patient.pass=dataLine.split("-")[4].strip();
        else
            patient.pass="Null";
        return patient;
    }
    public static History historyInfo(String dataLine){

        History history=new History();

        if (!dataLine.split("-")[0].isBlank())
            history.doctorName=dataLine.split("-")[0].substring(2).strip();
        else
            history.doctorName="Null";
        if (!dataLine.split("-")[1].isBlank())
            history.patientName=dataLine.split("-")[1].substring(2).strip();
        else
            history.patientName="Null";
        if (!dataLine.split("-")[2].isBlank())
            history.dateOfVisit=dataLine.split("-")[2].strip();
        else
            history.dateOfVisit="Null";
        return history;
    }
    public static String centerString(String str,int count){
        return " ".repeat(Math.max(0, (count-str.length())/2)) + str;
    }
    public static void formattingClinicInfo (Clinic clinic,Clinics clinics) {

        Formatter formatter = new Formatter();
        String format = "|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%n";

        formatter.format(format, centerString("ClinicName", 15), centerString("ClinicType", 15),
                centerString("ManagerName", 15), centerString("ManagerSalary", 15),
                centerString("ManagerUser", 15), centerString("ManagerPass", 15));

        for (Clinic Clinic :clinics.clinicInfo)
            formatter.format(format,centerString(Clinic.name, 15), centerString(Clinic.type, 15),
                    centerString(Clinic.managerName, 15), centerString(Clinic.managerSalary, 15),
                    centerString(Clinic.managerUsername, 15), centerString(Clinic.managerPass, 15));

        File clinicFolder = new File(clinic.name);
        if (!clinicFolder.mkdir()&&!clinicFolder.exists())
            return;

        File clinicInfoFile=new File(Path.of(clinicFolder.getAbsolutePath(),"ClinicInfo.txt").toString());
        try{
            FileWriter writer=new FileWriter(clinicInfoFile);
            writer.write(formatter.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR!");
        }
    }
    public static void formattingSectionInfo(String folderName,Clinics clinics){

        StringBuilder sectionInfo = new StringBuilder();

        for (Section Section:clinics.sectionInfo)
            sectionInfo.append("\r\nSectionName: ").append(Section.name).append("\r\nSectionType: ").append(Section.type).
                    append("\r\nRecepName: ").append(Section.recepName).append("\r\nDoctorNames: ").
                    append(Section.doctorNames).append("\r\nPatientNames: ").append(Section.patientNames)
                    .append("\r\n\r\n  ***  ***  ***\r\n");

        File clinicFolder = new File(folderName);
        if (!clinicFolder.mkdir()&&!clinicFolder.exists())
            return;

        File SectionInfo=new File(Path.of(clinicFolder.getAbsolutePath(),"SectionInfo.txt").toString());
        try{
            FileWriter writer=new FileWriter(SectionInfo);
            writer.write(sectionInfo.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR!");
        }
    }
    public static void formattingReceptionistInfo(String folderName,Clinics clinics){

        Formatter formatter = new Formatter();
        String format = "|%-15s|%-15s|%-15s|%-15s|%-15s|%n";

        formatter.format(format, centerString("RecepName", 15), centerString("RecepSection", 15),
                centerString("RecepSalary", 15),centerString("RecepUser", 15),
                centerString("RecepPass", 15));

        for (Receptionist Receptionist:clinics.receptionistInfo)
            formatter.format(format,centerString(Receptionist.name, 15),
                    centerString(Receptionist.sectionName, 15), centerString(Receptionist.salary, 15),
                    centerString(Receptionist.user, 15), centerString(Receptionist.pass, 15));

        File clinicFolder = new File(folderName);
        if (!clinicFolder.mkdir()&&!clinicFolder.exists())
            return;

        File ReceptionistInfo=new File(Path.of(clinicFolder.getAbsolutePath(),"ReceptionistInfo.txt").toString());
        try{
            FileWriter writer=new FileWriter(ReceptionistInfo);
            writer.write(formatter.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR!");
        }
    }
    public static void formattingDoctorInfo(String folderName,Clinics clinics){

        Formatter formatter = new Formatter();
        String format = "|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%n";

        formatter.format(format, centerString("DoctorName", 15), centerString("DoctorType", 15),
                centerString("DoctorSection", 15),centerString("DoctorSalary", 15),
                centerString("DoctorVisit", 15),centerString("DoctorUser", 15),
                centerString("DoctorPass", 15));

        for (Doctor doctor:clinics.doctorInfo)
            formatter.format(format,centerString(doctor.name, 15), centerString(doctor.type, 15),
                    centerString(doctor.sectionName, 15), centerString(doctor.salary, 15),
                    centerString(doctor.visitPayment, 15),centerString(doctor.user, 15),
                    centerString(doctor.pass, 15));

        File clinicFolder = new File(folderName);
        if (!clinicFolder.mkdir()&&!clinicFolder.exists())
            return;

        File DoctorInfo=new File(Path.of(clinicFolder.getAbsolutePath(),"DoctorInfo.txt").toString());
        try{
            FileWriter writer=new FileWriter(DoctorInfo);
            writer.write(formatter.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR!");
        }
    }
    public static void formattingPatientInfo(String folderName,Clinics clinics){

        Formatter formatter = new Formatter();
        String format = "|%-15s|%-15s|%-15s|%-15s|%-15s|%n";

        formatter.format(format, centerString("PatientName", 15), centerString("SicknessType", 15),
                centerString("PatientWallet", 15),centerString("PatientUser", 15),
                centerString("PatientPass", 15));

        for (Patient patient:clinics.patientInfo)
            formatter.format(format,centerString(patient.name, 15), centerString(patient.sicknessType, 15),
                    centerString(patient.wallet, 15), centerString(patient.user, 15),
                    centerString(patient.pass, 15));

        File clinicFolder = new File(folderName);
        if (!clinicFolder.mkdir()&&!clinicFolder.exists())
            return;

        File PatientInfo=new File(Path.of(clinicFolder.getAbsolutePath(),"PatientInfo.txt").toString());
        try{
            FileWriter writer=new FileWriter(PatientInfo);
            writer.write(formatter.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR!");
        }
    }
    public static void formattingHistoryInfo(String folderName,Clinics clinics){

        StringBuilder historyInfoStr=new StringBuilder();

        for (History history:clinics.historyInfo)
            historyInfoStr.append("Dr.").append(history.doctorName).append(" visited ").append(history.patientName).
                    append(" on ").append(history.dateOfVisit).append("\r\n");

        File clinicFolder = new File(folderName);
        if (!clinicFolder.mkdir()&&!clinicFolder.exists())
            return;

        File historyInfo=new File(Path.of(clinicFolder.getAbsolutePath(),"History.txt").toString());
        try{
            FileWriter writer=new FileWriter(historyInfo);
            writer.write(historyInfoStr.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR!");
        }
    }
    public static void mainMenu() throws FileNotFoundException {

        System.out.println("1. Clinics\r\n2. Exit");
        int inputNumber= input.nextInt();
        switch (inputNumber){
            case 1 -> clinicsMenu();
            case 2 -> exit(0);
            default -> {
                System.out.println("There is no such option.Please try again");
                mainMenu();
            }
        }
    }
    public static void clinicsMenu() throws FileNotFoundException {

        for (int i=0;i<clinicsName.size();i++) {
            int index=i+1;
            System.out.println(index+". "+clinicsName.get(i));
        }
        int indexPreviousMenu= clinicsName.size()+1;

        System.out.println(indexPreviousMenu+". Previous Menu");
        int inputNumber= input.nextInt();

        for (int i=0;i<clinicsName.size();i++) {
            int index=i+1;
            if(inputNumber==clinicsName.size()+1)
                mainMenu();
            else{
                if (inputNumber<0||inputNumber>clinicsName.size()) {
                    System.out.println("There is no such option.Please try again");
                    clinicsMenu();
                }
                else if(inputNumber==index)
                    clinicDataMenu(clinicsName.get(i));
            }
        }
    }
    public static void clinicDataMenu(String clinicName) throws FileNotFoundException {

        System.out.println("""
                1. ClinicInfo\r
                2. SectionInfo\r
                3. ReceptionistInfo\r
                4. DoctorInfo\r
                5. PatientsInfo\r
                6. History\r
                7. previous Menu""");
        int inputNumber0= input.nextInt();
        switch (inputNumber0){
            case 1 -> {
                File clinicInfoFile=new File(clinicName);
                try {
                    FileReader fileReader=new FileReader(Path.of(clinicInfoFile.getAbsolutePath(),
                            "ClinicInfo.txt").toString());
                    BufferedReader reader=new BufferedReader(fileReader);

                    String line;
                    StringBuilder clinicFile=new StringBuilder();

                    while((line=reader.readLine())!=null) {
                        clinicFile.append(line).append("\r\n");
                        System.out.println(line);
                    }
                    reader.close();

                    lastMenu(clinicName,clinicFile.toString());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 2 ->{
                File sectionInfoFile=new File(clinicName);
                try {
                    FileReader fileReader=new FileReader(Path.of(sectionInfoFile.getAbsolutePath(),
                            "SectionInfo.txt").toString());
                    BufferedReader reader=new BufferedReader(fileReader);

                    String line;
                    StringBuilder sectionFile=new StringBuilder();

                    while((line=reader.readLine())!=null){
                        sectionFile.append(line).append("\r\n");
                        System.out.println(line);
                    }
                    reader.close();

                    lastMenu(clinicName,sectionFile.toString());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 3 ->{
                File receptionistInfoFile=new File(clinicName);
                try {
                    FileReader fileReader=new FileReader(Path.of(receptionistInfoFile.getAbsolutePath(),
                            "ReceptionistInfo.txt").toString());
                    BufferedReader reader=new BufferedReader(fileReader);

                    String line;
                    StringBuilder receptionistFile=new StringBuilder();

                    while((line=reader.readLine())!=null)
                        receptionistFile.append(line).append("\r\n");
                    reader.close();

                    descendingOrderOutput(receptionistFile.toString(),3);
                    lastMenu(clinicName,receptionistFile.toString());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 4 ->{
                File doctorInfoFile=new File(clinicName);
                try {
                    FileReader fileReader=new FileReader(Path.of(doctorInfoFile.getAbsolutePath(),
                            "DoctorInfo.txt").toString());
                    BufferedReader reader=new BufferedReader(fileReader);

                    String line;
                    StringBuilder doctorFile=new StringBuilder();

                    while((line=reader.readLine())!=null)
                        doctorFile.append(line).append("\r\n");
                    reader.close();

                    descendingOrderOutput(doctorFile.toString(),4);
                    lastMenu(clinicName,doctorFile.toString());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 5 ->{
                File patientInfoFile=new File(clinicName);
                try {
                    FileReader fileReader=new FileReader(Path.of(patientInfoFile.getAbsolutePath(),
                            "PatientInfo.txt").toString());
                    BufferedReader reader=new BufferedReader(fileReader);

                    String line;
                    StringBuilder patientFile=new StringBuilder();

                    while((line=reader.readLine())!=null)
                        patientFile.append(line).append("\r\n");
                    reader.close();

                    descendingOrderOutput(patientFile.toString(),3);
                    lastMenu(clinicName,patientFile.toString());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 6 ->{
                File historyInfoFile=new File(clinicName);
                try {
                    FileReader fileReader=new FileReader(Path.of(historyInfoFile.getAbsolutePath(),
                            "History.txt").toString());
                    BufferedReader reader=new BufferedReader(fileReader);

                    String line;
                    StringBuilder historyFile=new StringBuilder();

                    while((line=reader.readLine())!=null) {
                        historyFile.append(line).append("\r\n");
                        System.out.println(line);
                    }
                    reader.close();

                    lastMenu(clinicName,historyFile.toString());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 7 -> clinicsMenu();
            default -> {
                System.out.println("There is no such option.Please try again");
                clinicDataMenu(clinicName);
            }
        }
    }
    public static void lastMenu(String clinicName,String textFile) throws FileNotFoundException {

        System.out.println("\r\n1. Search User\r\n2. Alphabetic Order\r\n3. Previous Menu\r\n4. Exit");
        int inputNumber1= input.nextInt();
        switch (inputNumber1){
            case 1 -> searchUser(clinicName,textFile);
            case 2 -> alphabeticOrderOutput(textFile,clinicName);
            case 3 -> clinicDataMenu(clinicName);
            case 4 -> exit(0);
            default -> {
                System.out.println("There is no such option.Please try again");
                lastMenu(clinicName,textFile);
            }
        }
    }
    public static void descendingOrderOutput(String text,int columnNumber){

        int startIndex=(columnNumber-1)*15,endIndex=columnNumber*15;
        Scanner scanner =new Scanner(text);
        var columnInfo=new Vector<String>();

        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            columnInfo.add(line.substring(line.indexOf("|", startIndex), line.indexOf("|", endIndex)).
                    substring(1).strip());
        }

        String[] info=new String[columnInfo.size()];
        for (int i=0;i<columnInfo.size();i++){
            if (Objects.equals(columnInfo.get(i), "Null"))
                info[i]="0";
            else
                info[i]=columnInfo.get(i).substring(0,columnInfo.get(i).length()-1);
        }
        Arrays.sort(info);

        String[] finalOutput=new String[columnInfo.size()+1];
        for (int i=1;i<columnInfo.size()+1;)
            for (int j=0;j<columnInfo.size();j++){
                if (Objects.equals(info[j], "0"))
                    info[j] = "Null";
                centerString
                Scanner textOutput=new Scanner(text);
                finalOutput[0]=textOutput.nextLine();

                while (textOutput.hasNextLine()){
                    String line = textOutput.nextLine();
                    if (line.substring(line.indexOf("|", startIndex), line.indexOf("|", endIndex)).contains(info[j]))
                        finalOutput[i]=line;
                }
                i++;
            }

        for (String output : finalOutput)
            System.out.println(output);

    }
    public static void searchUser(String clinicName,String textFile) throws FileNotFoundException {

        System.out.println("\r\nPlease enter your user to start the search.");
        String user= input.next();
        Scanner scanner=new Scanner(textFile);
        while (scanner.hasNextLine()){
            String line=scanner.nextLine();
            if (line.contains(user))
                System.out.println(line);
        }
        lastMenu(clinicName,textFile);
    }
    public static void alphabeticOrderOutput(String text,String clinicName) throws FileNotFoundException {

        System.out.println("\r\nplease enter the column number you want to put in alphabetic order.");
        int columnNumber=input.nextInt();

        int startIndex=(columnNumber-1)*15,endIndex=columnNumber*15;
        Scanner scanner =new Scanner(text);
        var columnInfo=new Vector<String>();

        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            columnInfo.add(line.substring(line.indexOf("|", startIndex), line.indexOf("|", endIndex)).
                    substring(1).strip());
        }

        String[] info=new String[columnInfo.size()];
        for (int i=0;i<columnInfo.size();i++){
            info[i]=columnInfo.get(i);
        }
        Arrays.sort(info);

        String[] finalOutput=new String[columnInfo.size()+1];
        for (int i=1;i<columnInfo.size()+1;)
            for (int j=0;j<columnInfo.size();j++){
                Scanner textOutput=new Scanner(text);
                finalOutput[0]=textOutput.nextLine();
                while (textOutput.hasNextLine()){
                    String line = textOutput.nextLine();
                    if (line.contains(info[j]))
                        finalOutput[i]=line;
                }
                i++;
            }

        for (String output : finalOutput)
            System.out.println(output);
        lastMenu(clinicName,text);
    }
}














