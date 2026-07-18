-- Create the Hospital Database
CREATE DATABASE HospitalDB;

-- Use the Hospital Database
USE HospitalDB;

-- Create the Patient table
CREATE TABLE Patient (
    Patient_ID INT PRIMARY KEY,       -- Unique identifier for each patient
    First_Name VARCHAR(50),           -- Patient's first name
    Last_Name VARCHAR(50),            -- Patient's last name
    Age INT,                          -- Patient's age
    Gender VARCHAR(10),               -- Patient's gender
    Address VARCHAR(100),             -- Patient's address
    Phone_Number VARCHAR(15),         -- Patient's phone number
    Registration_Date DATE            -- Date of patient registration
);

-- Create the Doctor table
CREATE TABLE Doctor (
    Doctor_ID INT PRIMARY KEY,        -- Unique identifier for each doctor
    First_Name VARCHAR(50),           -- Doctor's first name
    Last_Name VARCHAR(50),            -- Doctor's last name
    Specialty VARCHAR(100),           -- Doctor's specialty
    Phone_Number VARCHAR(15),         -- Doctor's phone number
    Office_Address VARCHAR(100)       -- Doctor's office address
);

-- Create the Staff table
CREATE TABLE Staff (
    Staff_ID INT PRIMARY KEY,         -- Unique identifier for each staff member
    First_Name VARCHAR(50),           -- Staff's first name
    Last_Name VARCHAR(50),            -- Staff's last name
    Role VARCHAR(50),                 -- Role of the staff member (e.g., Nurse, Lab Technician)
    Department_ID INT NULL            -- Foreign key to Department table (allow NULL initially)
);

-- Create the Department table
CREATE TABLE Department (
    Department_ID INT PRIMARY KEY,    -- Unique identifier for each department
    Department_Name VARCHAR(100),     -- Name of the department (e.g., Emergency, Surgery)
    Department_Manager INT NULL       -- Foreign key to Staff table (allow NULL initially)
);

-- Create the Lab table
CREATE TABLE Lab (
    Lab_ID INT PRIMARY KEY,           -- Unique identifier for each lab
    Lab_Name VARCHAR(100),            -- Name of the lab
    Lab_Manager INT,                  -- Foreign key to Staff table (manager of the lab)
    FOREIGN KEY (Lab_Manager) REFERENCES Staff(Staff_ID)
);

-- Create the Room table
CREATE TABLE Room (
    Room_ID INT PRIMARY KEY,          -- Unique identifier for each room
    Room_Number VARCHAR(20),          -- Room number
    Room_Type VARCHAR(50),            -- Type of room (e.g., General, ICU, Operating Room)
    Department_ID INT,                -- Foreign key to Department table
    FOREIGN KEY (Department_ID) REFERENCES Department(Department_ID)
);

-- Create the Appointment table
CREATE TABLE Appointment (
    Appointment_ID INT PRIMARY KEY,   -- Unique identifier for each appointment
    Patient_ID INT,                   -- Foreign key to Patient table
    Doctor_ID INT,                    -- Foreign key to Doctor table
    Appointment_Date DATETIME,        -- Date and time of the appointment
    Status VARCHAR(20),               -- Status of the appointment (e.g., Scheduled, Canceled, Completed)
    FOREIGN KEY (Patient_ID) REFERENCES Patient(Patient_ID),
    FOREIGN KEY (Doctor_ID) REFERENCES Doctor(Doctor_ID)
);

-- Create the MedicalRecord table
CREATE TABLE MedicalRecord (
    Record_ID INT PRIMARY KEY,        -- Unique identifier for each medical record
    Patient_ID INT,                   -- Foreign key to Patient table
    Doctor_ID INT,                    -- Foreign key to Doctor table
    Record_Date DATE,                 -- Date the record was created
    Diagnosis VARCHAR(255),           -- Diagnosis information
    Tests VARCHAR(255),               -- Tests performed
    Imaging_Results VARCHAR(255),     -- Imaging results (e.g., X-Ray, MRI)
    FOREIGN KEY (Patient_ID) REFERENCES Patient(Patient_ID),
    FOREIGN KEY (Doctor_ID) REFERENCES Doctor(Doctor_ID)
);

-- Create the Medication table
CREATE TABLE Medication (
    Medication_ID INT PRIMARY KEY,    -- Unique identifier for each medication
    Medication_Name VARCHAR(100),     -- Name of the medication
    Dosage VARCHAR(50),               -- Dosage of the medication
    Category VARCHAR(50),             -- Category of the medication (e.g., Antibiotic, Painkiller)
    Price DECIMAL(10, 2)              -- Price of the medication
);

-- Create the Prescription table
CREATE TABLE Prescription (
    Prescription_ID INT PRIMARY KEY,  -- Unique identifier for each prescription
    Patient_ID INT,                   -- Foreign key to Patient table
    Doctor_ID INT,                    -- Foreign key to Doctor table
    Prescription_Date DATE,           -- Date the prescription was issued
    Instructions TEXT,                -- Instructions for taking the medication
    FOREIGN KEY (Patient_ID) REFERENCES Patient(Patient_ID),
    FOREIGN KEY (Doctor_ID) REFERENCES Doctor(Doctor_ID)
);

-- Create the Prescription_Medication table (Junction Table)
CREATE TABLE Prescription_Medication (
    Prescription_ID INT,              -- Foreign key to Prescription table
    Medication_ID INT,                -- Foreign key to Medication table
    Quantity INT,                     -- Quantity of the medication prescribed
    PRIMARY KEY (Prescription_ID, Medication_ID),
    FOREIGN KEY (Prescription_ID) REFERENCES Prescription(Prescription_ID),
    FOREIGN KEY (Medication_ID) REFERENCES Medication(Medication_ID)
);

-- Create the Test table
CREATE TABLE Test (
    Test_ID INT PRIMARY KEY,          -- Unique identifier for each test
    Test_Name VARCHAR(100),           -- Name of the test
    Test_Type VARCHAR(50),            -- Type of the test (e.g., Blood Test, X-Ray)
    Cost DECIMAL(10, 2),              -- Cost of the test
    Lab_ID INT,                       -- Foreign key to Lab table
    Patient_ID INT,                   -- Foreign key to Patient table
    FOREIGN KEY (Lab_ID) REFERENCES Lab(Lab_ID),
    FOREIGN KEY (Patient_ID) REFERENCES Patient(Patient_ID)
);

-- Create the Surgery table
CREATE TABLE Surgery (
    Surgery_ID INT PRIMARY KEY,       -- Unique identifier for each surgery
    Patient_ID INT,                   -- Foreign key to Patient table
    Doctor_ID INT,                    -- Foreign key to Doctor table
    Room_ID INT,                      -- Foreign key to Room table
    Surgery_Date DATE,                -- Date of the surgery
    Surgery_Type VARCHAR(100),        -- Type of surgery (e.g., Appendectomy, Knee Replacement)
    FOREIGN KEY (Patient_ID) REFERENCES Patient(Patient_ID),
    FOREIGN KEY (Doctor_ID) REFERENCES Doctor(Doctor_ID),
    FOREIGN KEY (Room_ID) REFERENCES Room(Room_ID)
);

-- Create the Invoice table
CREATE TABLE Invoice (
    Invoice_ID INT PRIMARY KEY,       -- Unique identifier for each invoice
    Patient_ID INT,                   -- Foreign key to Patient table
    Invoice_Date DATE,                -- Date the invoice was issued
    Total_Amount DECIMAL(10, 2),      -- Total amount of the invoice
    Payment_Status VARCHAR(20),       -- Payment status (e.g., Paid, Pending)
    FOREIGN KEY (Patient_ID) REFERENCES Patient(Patient_ID)
);

-- Create the Insurance table
CREATE TABLE Insurance (
    Insurance_ID INT PRIMARY KEY,     -- Unique identifier for each insurance policy
    Patient_ID INT,                   -- Foreign key to Patient table
    Insurance_Company VARCHAR(100),   -- Name of the insurance company
    Policy_Number VARCHAR(50),        -- Policy number
    Expiry_Date DATE,                 -- Expiry date of the insurance policy
    FOREIGN KEY (Patient_ID) REFERENCES Patient(Patient_ID)
);

-- Add foreign key constraints for circular dependencies
ALTER TABLE Staff
ADD CONSTRAINT FK_Staff_Department
FOREIGN KEY (Department_ID) REFERENCES Department(Department_ID);

ALTER TABLE Department
ADD CONSTRAINT FK_Department_Manager
FOREIGN KEY (Department_Manager) REFERENCES Staff(Staff_ID);

-- Add foreign key constraints for Appointment table
ALTER TABLE Appointment
ADD CONSTRAINT FK_Patient_Appointment
FOREIGN KEY (Patient_ID) REFERENCES Patient(Patient_ID);

ALTER TABLE Appointment
ADD CONSTRAINT FK_Doctor_Appointment
FOREIGN KEY (Doctor_ID) REFERENCES Doctor(Doctor_ID);

-- Add foreign key constraints for MedicalRecord table
ALTER TABLE MedicalRecord
ADD CONSTRAINT FK_Patient_MedicalRecord
FOREIGN KEY (Patient_ID) REFERENCES Patient(Patient_ID);

ALTER TABLE MedicalRecord
ADD CONSTRAINT FK_Doctor_MedicalRecord
FOREIGN KEY (Doctor_ID) REFERENCES Doctor(Doctor_ID);

-- Add foreign key constraints for Prescription table
ALTER TABLE Prescription
ADD CONSTRAINT FK_Patient_Prescription
FOREIGN KEY (Patient_ID) REFERENCES Patient(Patient_ID);

ALTER TABLE Prescription
ADD CONSTRAINT FK_Doctor_Prescription
FOREIGN KEY (Doctor_ID) REFERENCES Doctor(Doctor_ID);

-- Add foreign key constraints for Surgery table
ALTER TABLE Surgery
ADD CONSTRAINT FK_Patient_Surgery
FOREIGN KEY (Patient_ID) REFERENCES Patient(Patient_ID);

ALTER TABLE Surgery
ADD CONSTRAINT FK_Doctor_Surgery
FOREIGN KEY (Doctor_ID) REFERENCES Doctor(Doctor_ID);

ALTER TABLE Surgery
ADD CONSTRAINT FK_Room_Surgery
FOREIGN KEY (Room_ID) REFERENCES Room(Room_ID);

-- Add foreign key constraints for Room table
ALTER TABLE Room
ADD CONSTRAINT FK_Department_Room
FOREIGN KEY (Department_ID) REFERENCES Department(Department_ID);

-- Add foreign key constraints for Staff table
ALTER TABLE Staff
ADD CONSTRAINT FK_Department_Staff
FOREIGN KEY (Department_ID) REFERENCES Department(Department_ID);

-- Add foreign key constraints for Lab table
ALTER TABLE Lab
ADD CONSTRAINT FK_Staff_Lab
FOREIGN KEY (Lab_Manager) REFERENCES Staff(Staff_ID);

-- Add foreign key constraints for Test table
ALTER TABLE Test
ADD CONSTRAINT FK_Lab_Test
FOREIGN KEY (Lab_ID) REFERENCES Lab(Lab_ID);

ALTER TABLE Test
ADD CONSTRAINT FK_Patient_Test
FOREIGN KEY (Patient_ID) REFERENCES Patient(Patient_ID);

-- Add foreign key constraints for Invoice table
ALTER TABLE Invoice
ADD CONSTRAINT FK_Patient_Invoice
FOREIGN KEY (Patient_ID) REFERENCES Patient(Patient_ID);

-- Add foreign key constraints for Insurance table
ALTER TABLE Insurance
ADD CONSTRAINT FK_Patient_Insurance
FOREIGN KEY (Patient_ID) REFERENCES Patient(Patient_ID);