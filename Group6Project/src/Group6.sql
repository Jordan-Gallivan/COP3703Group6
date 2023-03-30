--this is the file into which we'll code our SQL for the group project
--first commit
--how to pull a data attribute in a specific format
-- SELECT
--     phone_num,
--     CONCAT(
--             '(',
--             SUBSTR(phone_num, 1, 3),
--             ') ',
--             SUBSTR(phone_num, 4, 3),
--             '-',
--             SUBSTR(phone_num, 7)
--         ) AS num_formatted
-- FROM
--     EmpDetails
-- https://docs.oracle.com/database/121/SQLRF/sql_elements004.htm#SQLRF00211

-- Questions for Professor
--      o are we sure this will only be US Citizens?
--      o default values
--      o how to make a person and a patient at the same time
--      x how to make first char of patient ID be P -> must do this in Java on front end
--      o on update ??
--      o optional values for attributes (male, female, user defined)
--          patient conditions

-- Notes for classmates
--      o needed to change State, patient condition,
--           oracle reserved words
--      o for our update method in the JAVA code, we need to assert the following for
--          all attributes that are numeric but stored as characters
--          UPDATE myTable SET ColumnA = NewValue WHERE ColumnA REGEXP '^[0-9]+$'

create table PERSON (
    SSN CHAR(9) not null,       -- need to format output to be xxx-xx-xxx
    FName VARCHAR(15) not null,
    MInitial CHAR(1),
    LName VARCHAR(15), not null,
    Curr_address VARCHAR(50) not null,
    Curr_phone CHAR(10) not null,
    Perm_phone CHAR(10) not null,        -- need to format output to be xxx-xxx-xxxx
    DOB DATE not null,      -- need to format to MM-DD-YYYY
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
        primary key (Pt_ID),
    constraint PATIENT_SSN_FK
        foreign key (SSN) references PERSON(SSN)
                    on delete SET NULL
                    on update CASCADE,
    constraint PATIENT_PRIDR_FK
        foreign key (Pri_Care_Dr) references DOCTOR(Dr_ID)
                     on delete SET NULL
                     on update CASCADE,
    constraint PATIENT_SECDR_FK
        foreign key (Sec_Care_Dr) references DOCTOR(Dr_ID)
            on delete SET NULL
            on update CASCADE
);

create table DOCTOR (
    SSN CHAR(9) not null,
    Dr_ID CHAR(9) not null,
    Dept VARCHAR(4) not null,

    constraint DOCTOR_PK
        primary key (Dr_ID),
    constraint DOCTOR_SSN_FK
        foreign key (SSN) references PERSON(SSN)
            on delete SET NULL
            on update CASCADE,
    constraint DOCTOR_DEPT_FK
        foreign key (Dept) references DEPARTMENT(Dept_Code)
            on delete SET NULL
            on update CASCADE
);

create table DEPARTMENT(
   Dept_code VARCHAR(4) not null,
   Dept_name VARCHAR(15) not null,
   Office_num CHAR(4) not null,
   Phone CHAR(10),
   Dept_head CHAR(9) not null,

   constraint DEPARTMENT_PK
        primary key (Dept_code),
    constraint DEPARTMENT_FK
        foreign key (Dept_head) references DOCTOR
            on delete SET NULL
            on update CASCADE
);