
-- To-Do's
--      o add unique tags where appropriate

create table PERSON (
    SSN CHAR(9) not null,       -- need to format output to be xxx-xx-xxx
    FName VARCHAR(15) not null,
    MInitial CHAR(1),
    LName VARCHAR(15) not null,
    Curr_address VARCHAR(50),
    Curr_phone CHAR(10),
    Perm_phone CHAR(10) not null,        -- need to format output to be xxx-xxx-xxxx
    DOB CHAR(10) not null,      -- need to format to MM-DD-YYYY
    Sex VARCHAR(10) not null,
    Street VARCHAR(15) not null,
    City VARCHAR(15) not null,
    PermState CHAR(2) not null,
    Zip CHAR(5) not null,
    constraint  PERSON_PK
        primary key (SSN)
);

create table PATIENT (
     SSN CHAR(9) not null,
     Pt_ID CHAR(9) not null,
     Pt_condition VARCHAR(8) not null,
     Pri_Care_Dr CHAR(9) not null,
     Sec_Care_Dr CHAR(9),

     constraint PATIENT_PK
         primary key (Pt_ID)
);

create table DOCTOR (
    SSN CHAR(9) not null,
    Dr_ID CHAR(9) not null,
    Dept VARCHAR(4) not null,

    constraint DOCTOR_PK
        primary key (Dr_ID)
);

create table DEPARTMENT(
    Dept_code VARCHAR(4) not null,
    Dept_name VARCHAR(15) not null,
    Office_num CHAR(4) not null,
    Phone CHAR(10),
    Dept_head CHAR(9) not null,

    constraint DEPARTMENT_PK
       primary key (Dept_code)
);

create table INTERACTION (
    Int_ID  integer not null,
    Int_Pt CHAR(9) not null,
    Int_Date  CHAR(10) not null,
    Int_Time  CHAR(4) not null,
    Int_Desc  VARCHAR(150)  not null,

    constraint INTERACTION_PK_PT
        primary key (Int_Pt, Int_ID)
--     constraint INTERACTION_PK_ID
--         primary key (Int_ID)
 );
 
Create table PROCEDURE (
    Proc_Num   CHAR(7) not null,
    Proc_Name  VARCHAR(50) not null,
    Description VARCHAR(50)  not null,
    Duration   float not null,
    Proc_Dept   VARCHAR(4) not null,

    constraint PROCEDURE_PK
        primary key(Proc_Num)
);

create table PERFORMS(
    Proc_Dr     CHAR(9) not null,
    Proc        CHAR(7) not null,

    constraint PERFORMS_PK_DR
        primary key (Proc_Dr, Proc)
--     constraint PERFORMS_PK_PROC
--         primary key (Proc)
);

create table PRESCRIBED_MEDICINE (
    RX_Name  VARCHAR(15)  not null,
    Manufacturer  VARCHAR(15) not null,
    RX_Desc    VARCHAR(50)  not null,

    constraint PRESCRIBED_MEDICINE_PK
        primary key(RX_Name)

);
create table PRESCRIPTION (
    Pres_Dr CHAR(9) not null,
    Pres_Rx VARCHAR(15) not null,
    Pres_Pt CHAR(9) not null,
    Date_Rx  CHAR(10)  not null,

    constraint PRESCRIPTION_PK_DR
        primary key (Pres_Dr, Pres_Rx, Pres_Pt)
--     constraint PRESCRIPTION_PK_RX
--         primary key (Pres_Rx),
--     constraint PRESCRIPTION_PK_PT
--         primary key (Pres_Pt)
);
create table UNDERGOES (
    Proc_Pt     CHAR(9) not null,
    Proc_Num    CHAR(7) not null,
    Proc_Notes  VARCHAR(200),
    Proc_Date  CHAR(10),
    Proc_Time CHAR(4),

    constraint UNDERGOES_PK_PT
        primary key (Proc_Pt, Proc_Num)
--     constraint UNDERGOES_PK_PT
--         primary key (Proc_Num)
);

-- Add Foreign Keys to PATIENT
Alter  table PATIENT
ADD constraint PATIENT_SSN_FK
    foreign key (SSN) references PERSON(SSN)
    on delete SET NULL;
Alter  table PATIENT
ADD constraint PATIENT_PRIDR_FK
    foreign key (Pri_Care_Dr) references DOCTOR(Dr_ID)
    on delete SET NULL;
Alter  table PATIENT
ADD constraint PATIENT_SECDR_FK
    foreign key (Sec_Care_Dr) references DOCTOR(Dr_ID)
    on delete SET NULL;

-- Add Foreign Keys to DOCTOR
Alter  table DOCTOR
ADD constraint DOCTOR_SSN_FK
    foreign key (SSN) references PERSON(SSN)
    on delete SET NULL;
Alter  table DOCTOR
ADD constraint DOCTOR_DEPT_FK
    foreign key (Dept) references DEPARTMENT(Dept_Code)
    on delete SET NULL;

-- Add Foreign Keys to DEPARTMENT
Alter  table DEPARTMENT
ADD constraint DEPARTMENT_FK
    foreign key (Dept_head) references DOCTOR
    on delete SET NULL;

-- Add Foreign Keys to INTERACTION
Alter table INTERACTION
ADD constraint INTERACTION_FK
    foreign key (Int_Pt) references Patient(Pt_ID)
    on delete SET NULL;

-- Add Foreign Keys to PROCEDURE
Alter table PROCEDURE
ADD constraint PROCEDURE_FK_DEPT
    foreign key (Proc_Dept) references DEPARTMENT(Dept_code)
    on delete SET NULL;

-- Add Foreign Keys to PERFORMS
Alter table PERFORMS
ADD constraint PERFORMS_FK_DR
    foreign key (Proc_Dr) references DOCTOR(Dr_ID)
    on delete SET NULL;
Alter table PERFORMS
ADD constraint PERFORMS_FK_PROC
    foreign key (Proc) references PROCEDURE(Proc_Num)
    on delete SET NULL;

-- Add Foreign Keys to PRESCRIPTION
Alter table PRESCRIPTION
ADD constraint PRESCRIPTION_FK_DR
    foreign key (Pres_Dr) references DOCTOR(Dr_ID)
    on delete SET NULL;
Alter table PRESCRIPTION
ADD constraint PRESCRIPTION_FK_RX
    foreign key (Pres_Rx) references PRESCRIBED_MEDICINE(RX_Name)
    on delete SET NULL;
Alter table PRESCRIPTION
ADD constraint PRESCRIPTION_FK_PT
    foreign key (Pres_Pt) references PATIENT(Pt_ID)
    on delete SET NULL;

-- Add Foreign Keys to UNDERGOES
Alter table UNDERGOES
ADD constraint UNDERGOES_FK_PT
    foreign key (Proc_Pt) references PATIENT(Pt_ID)
    on delete SET NULL;
Alter table UNDERGOES
ADD constraint UNDERGOES_FK_PROC
    foreign key (Proc_Num) references PROCEDURE(Proc_Num)
    on delete SET NULL;