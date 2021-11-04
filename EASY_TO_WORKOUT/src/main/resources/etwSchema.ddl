
DROP TABLE Choice CASCADE CONSTRAINTS PURGE;

DROP TABLE Routine CASCADE CONSTRAINTS PURGE;

DROP TABLE ClubSchedule CASCADE CONSTRAINTS PURGE;

DROP TABLE Exercise CASCADE CONSTRAINTS PURGE;

DROP TABLE Diary CASCADE CONSTRAINTS PURGE;

DROP TABLE Memebership CASCADE CONSTRAINTS PURGE;

DROP TABLE Member CASCADE CONSTRAINTS PURGE;

DROP TABLE Club CASCADE CONSTRAINTS PURGE;

CREATE TABLE Club
(
	clubId               NUMBER(5) NOT NULL ,
	clubMaster           CHAR(18) NOT NULL ,
	signUp               CHAR(1) NOT NULL  CONSTRAINT  signUp_values CHECK (signUp BETWEEN 0 AND 1),
	openCycle            CHAR(1) NOT NULL  CONSTRAINT  openCycle_values CHECK (openCycle BETWEEN 0 AND 1),
	clubIntro            VARCHAR2(300) NULL ,
	clubName             VARCHAR2(30) NOT NULL 
);

CREATE UNIQUE INDEX XPKClub ON Club
(clubId   ASC);

ALTER TABLE Club
	ADD CONSTRAINT  XPKClub PRIMARY KEY (clubId);

CREATE TABLE ClubSchedule
(
	scheduleId           CHAR(10) NOT NULL ,
	clubId               NUMBER(5) NOT NULL ,
	contactAddress       VARCHAR2(100) NOT NULL ,
	notice               CHAR(18) NULL ,
	creationDate         TIMESTAMP NOT NULL 
);

CREATE UNIQUE INDEX XPKClubSchedule ON ClubSchedule
(clubId   ASC,scheduleId   ASC);

ALTER TABLE ClubSchedule
	ADD CONSTRAINT  XPKClubSchedule PRIMARY KEY (clubId,scheduleId);

CREATE TABLE Exercise
(
	exerciseId           NUMBER(5) NOT NULL ,
	name                 VARCHAR2(100) NOT NULL ,
	part                 VARCHAR2(30) NOT NULL ,
	method               VARCHAR2(300) NOT NULL 
);

CREATE UNIQUE INDEX XPKExercise ON Exercise
(exerciseId   ASC);

ALTER TABLE Exercise
	ADD CONSTRAINT  XPKExercise PRIMARY KEY (exerciseId);

CREATE TABLE Member
(
	MemberId             VARCHAR2(20) NOT NULL ,
	pw                   VARCHAR2(24) NOT NULL ,
	name                 VARCHAR2(20) NOT NULL ,
	phone                VARCHAR2(11) NULL ,
	email                VARCHAR2(30) NULL ,
	grade                VARCHAR2(10) NOT NULL  CONSTRAINT  grade_valid_values CHECK (grade IN ('Valid_Value_448', 'Valid_Value_449'))
);

CREATE UNIQUE INDEX XPKMember ON Member
(MemberId   ASC);

ALTER TABLE Member
	ADD CONSTRAINT  XPKMember PRIMARY KEY (MemberId);

CREATE TABLE Diary
(
	diaryId              NUMBER(5) NOT NULL ,
	title                VARCHAR2(100) NOT NULL ,
	workTime             NUMBER(2) NULL ,
	contents             VARCHAR2(4000) NULL ,
	private              CHAR(1) NOT NULL  CONSTRAINT  private_valid_values CHECK (private IN ('Valid_Value_463', 'Valid_Value_464')),
	author               VARCHAR2(20) NOT NULL ,
	diaryDate            TIMESTAMP NULL 
);

CREATE UNIQUE INDEX XPKDiary ON Diary
(diaryId   ASC);

ALTER TABLE Diary
	ADD CONSTRAINT  XPKDiary PRIMARY KEY (diaryId);

CREATE TABLE Memebership
(
	clubId               NUMBER(5) NOT NULL ,
	MemberId             VARCHAR2(20) NOT NULL ,
	subDate              TIMESTAMP NULL 
);

CREATE UNIQUE INDEX XPKMembership ON Memebership
(clubId   ASC,MemberId   ASC);

ALTER TABLE Memebership
	ADD CONSTRAINT  XPKMembership PRIMARY KEY (clubId,MemberId);

CREATE TABLE Routine
(
	routineId            NUMBER(5) NOT NULL ,
	rTime                NUMBER(3) NOT NULL ,
	difficulty           NUMBER(1) NOT NULL  CONSTRAINT  level_minmax CHECK (difficulty BETWEEN 1 AND 5),
	rType                CHAR(1) NOT NULL  CONSTRAINT  type_valid_values CHECK (rType IN ('Valid_Value_449', 'Valid_Value_450')),
	part                 VARCHAR2(30) NOT NULL ,
	routineCreater       VARCHAR2(20) NOT NULL ,
	clubId               NUMBER(5) NOT NULL ,
	scheduleId           CHAR(10) NOT NULL 
);

CREATE UNIQUE INDEX XPKRoutine ON Routine
(routineId   ASC);

ALTER TABLE Routine
	ADD CONSTRAINT  XPKRoutine PRIMARY KEY (routineId);

CREATE TABLE Choice
(
	routineId            NUMBER(5) NOT NULL ,
	exerciseId           NUMBER(5) NOT NULL ,
	sequence             NUMBER(1) NOT NULL ,
	repetition           NUMBER(1) DEFAULT  1  NULL 
);

CREATE UNIQUE INDEX XPKChoice ON Choice
(exerciseId   ASC,routineId   ASC);

ALTER TABLE Choice
	ADD CONSTRAINT  XPKChoice PRIMARY KEY (exerciseId,routineId);

ALTER TABLE ClubSchedule
	ADD (CONSTRAINT R_8 FOREIGN KEY (clubId) REFERENCES Club (clubId));

ALTER TABLE Diary
	ADD (CONSTRAINT R_7 FOREIGN KEY (author) REFERENCES Member (MemberId));

ALTER TABLE Memebership
	ADD (CONSTRAINT R_46 FOREIGN KEY (clubId) REFERENCES Club (clubId));

ALTER TABLE Memebership
	ADD (CONSTRAINT R_47 FOREIGN KEY (MemberId) REFERENCES Member (MemberId));

ALTER TABLE Routine
	ADD (CONSTRAINT R_49 FOREIGN KEY (routineCreater) REFERENCES Member (MemberId));

ALTER TABLE Routine
	ADD (CONSTRAINT R_42 FOREIGN KEY (clubId, scheduleId) REFERENCES ClubSchedule (clubId, scheduleId));

ALTER TABLE Choice
	ADD (CONSTRAINT R_5 FOREIGN KEY (routineId) REFERENCES Routine (routineId));

ALTER TABLE Choice
	ADD (CONSTRAINT R_6 FOREIGN KEY (exerciseId) REFERENCES Exercise (exerciseId));
