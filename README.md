# Database-assignment
CREATE TABLE Client (
    clientNo NUMBER PRIMARY KEY,
    clientName VARCHAR2(100) NOT NULL,
    clientStreet VARCHAR2(100),
    clientCity VARCHAR2(100),
    clientState VARCHAR2(100),
    clientZipCode VARCHAR2(10)
);

CREATE TABLE Delegate (
    delegateNo NUMBER PRIMARY KEY,
    delegateTitle VARCHAR2(10),
    delegateFName VARCHAR2(50),
    delegateLName VARCHAR2(50),
    delegateStreet VARCHAR2(100),
    delegateCity VARCHAR2(100),
    delegateState VARCHAR2(100),
    delegateZipCode VARCHAR2(10),
    attTelNo VARCHAR2(20),
    attFaxNo VARCHAR2(20),
    attEmailAddress VARCHAR2(100),
    clientNo NUMBER,
    FOREIGN KEY (clientNo) REFERENCES Client(clientNo)
);

CREATE TABLE Employee (
    employeeNo NUMBER PRIMARY KEY,
    employeeName VARCHAR2(100) NOT NULL
);

CREATE TABLE Location (
    locationNo NUMBER PRIMARY KEY,
    locationName VARCHAR2(100) NOT NULL,
    maxSize NUMBER NOT NULL
);

CREATE TABLE CourseType (
    courseTypeNo NUMBER PRIMARY KEY,
    courseTypeDescription VARCHAR2(100) NOT NULL
);

CREATE TABLE Course (
    courseNo NUMBER PRIMARY KEY,
    courseName VARCHAR2(100) NOT NULL,
    courseDescription VARCHAR2(500),
    startDate DATE,
    startTime VARCHAR2(10),
    endDate DATE,
    endTime VARCHAR2(10),
    maxDelegates NUMBER NOT NULL,
    confirmed CHAR(1) CHECK (confirmed IN ('Y', 'N')),
    delivererEmployeeNo NUMBER,
    courseTypeNo NUMBER,
    FOREIGN KEY (delivererEmployeeNo) REFERENCES Employee(employeeNo),
    FOREIGN KEY (courseTypeNo) REFERENCES CourseType(courseTypeNo)
);

CREATE TABLE CourseFee (
    courseFeeNo NUMBER PRIMARY KEY,
    feeDescription VARCHAR2(100),
    fee NUMBER NOT NULL,
    courseNo NUMBER,
    FOREIGN KEY (courseNo) REFERENCES Course(courseNo)
);

CREATE TABLE Booking (
    bookingNo NUMBER PRIMARY KEY,
    bookingDate DATE NOT NULL,
    locationNo NUMBER,
    courseNo NUMBER,
    bookingEmployeeNo NUMBER,
    FOREIGN KEY (locationNo) REFERENCES Location(locationNo),
    FOREIGN KEY (courseNo) REFERENCES Course(courseNo),
    FOREIGN KEY (bookingEmployeeNo) REFERENCES Employee(employeeNo)
);

CREATE TABLE PaymentMethod (
    pMethodNo NUMBER PRIMARY KEY,
    pMethodName VARCHAR2(100) NOT NULL
);

CREATE TABLE Registration (
    registrationNo NUMBER PRIMARY KEY,
    registrationDate DATE NOT NULL,
    delegateNo NUMBER,
    courseFeeNo NUMBER,
    registerEmployeeNo NUMBER,
    courseNo NUMBER,
    FOREIGN KEY (delegateNo) REFERENCES Delegate(delegateNo),
    FOREIGN KEY (courseFeeNo) REFERENCES CourseFee(courseFeeNo),
    FOREIGN KEY (registerEmployeeNo) REFERENCES Employee(employeeNo),
    FOREIGN KEY (courseNo) REFERENCES Course(courseNo)
);

CREATE TABLE Invoice (
    invoiceNo NUMBER PRIMARY KEY,
    dateRaised DATE NOT NULL,
    datePaid DATE,
    creditCardNo VARCHAR2(20),
    holdersName VARCHAR2(100),
    expiryDate DATE,
    registrationNo NUMBER,
    pMethodNo NUMBER,
    FOREIGN KEY (registrationNo) REFERENCES Registration(registrationNo),
    FOREIGN KEY (pMethodNo) REFERENCES PaymentMethod(pMethodNo)
);
