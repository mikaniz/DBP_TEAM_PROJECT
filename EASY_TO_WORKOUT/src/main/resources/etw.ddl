
CREATE SEQUENCE ClubIdSeq
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE DiaryIdSeq
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE ExerciseIdSeq
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE RoutineIdSeq
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE ScheduleIdSeq
	INCREMENT BY 1
	START WITH 1;

DROP TABLE Choice CASCADE CONSTRAINTS PURGE;

DROP TABLE Exercise CASCADE CONSTRAINTS PURGE;

DROP TABLE Diary CASCADE CONSTRAINTS PURGE;

DROP TABLE Membership CASCADE CONSTRAINTS PURGE;

DROP TABLE Usage CASCADE CONSTRAINTS PURGE;

DROP TABLE Routine CASCADE CONSTRAINTS PURGE;

DROP TABLE ClubSchedule CASCADE CONSTRAINTS PURGE;

DROP TABLE Club CASCADE CONSTRAINTS PURGE;

DROP TABLE Member CASCADE CONSTRAINTS PURGE;

CREATE TABLE Club
(
	clubId               NUMBER(5) NOT NULL ,
	signUp               CHAR(1) NOT NULL  CONSTRAINT  signUp_values CHECK (signUp BETWEEN 0 AND 1),
	openCycle            CHAR(1) NOT NULL  CONSTRAINT  openCycle_values CHECK (openCycle BETWEEN 0 AND 1),
	clubIntro            VARCHAR2(300) NULL ,
	clubName             VARCHAR2(30) NOT NULL ,
	clubMaster           VARCHAR2(20) NOT NULL 
);

CREATE UNIQUE INDEX XPKClub ON Club
(clubId   ASC);

ALTER TABLE Club
	ADD CONSTRAINT  XPKClub PRIMARY KEY (clubId);

CREATE TABLE ClubSchedule
(
	scheduleId           NUMBER(5) NOT NULL ,
	clubId               NUMBER(5) NOT NULL ,
	contactAddress       VARCHAR2(100) NOT NULL ,
	notice               VARCHAR2(1000) NULL ,
	creationDate         TIMESTAMP NOT NULL 
);

CREATE UNIQUE INDEX XPKClubSchedule ON ClubSchedule
(clubId   ASC,scheduleId   ASC);

ALTER TABLE ClubSchedule
	ADD CONSTRAINT  XPKClubSchedule PRIMARY KEY (clubId,scheduleId);

CREATE TABLE Member
(
	MemberId             VARCHAR2(20) NOT NULL ,
	pw                   VARCHAR2(24) NOT NULL ,
	name                 VARCHAR2(20) NOT NULL ,
	phone                VARCHAR2(11) NULL ,
	email                VARCHAR2(30) NULL ,
	grade                VARCHAR2(10) NOT NULL  CONSTRAINT  grade_valid_values CHECK (grade IN ('green', 'master'))
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
	private              CHAR(1) NOT NULL  CONSTRAINT  private_valid_values CHECK (private BETWEEN 0 AND 1),
	author               VARCHAR2(20) NOT NULL ,
	diaryDate            TIMESTAMP NULL 
);

CREATE UNIQUE INDEX XPKDiary ON Diary
(diaryId   ASC);

ALTER TABLE Diary
	ADD CONSTRAINT  XPKDiary PRIMARY KEY (diaryId);

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

CREATE TABLE Membership
(
	clubId               NUMBER(5) NOT NULL ,
	MemberId             VARCHAR2(20) NOT NULL ,
	subDate              TIMESTAMP NULL 
);

CREATE UNIQUE INDEX XPKMembership ON Membership
(clubId   ASC,MemberId   ASC);

ALTER TABLE Membership
	ADD CONSTRAINT  XPKMembership PRIMARY KEY (clubId,MemberId);

CREATE TABLE Routine
(
	routineId            NUMBER(5) NOT NULL ,
	rTime                NUMBER(3) NOT NULL ,
	difficulty           NUMBER(1) NOT NULL  CONSTRAINT  level_minmax CHECK (difficulty BETWEEN 1 AND 5),
	rType                CHAR(1) NOT NULL  CONSTRAINT  type_valid_values CHECK (rType BETWEEN 0 AND 1),
	part                 VARCHAR2(30) NOT NULL ,
	routineCreater       VARCHAR2(20) NOT NULL ,
	rName                VARCHAR2(100) NOT NULL 
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
	repetition           NUMBER(1) DEFAULT  1  NOT NULL 
);

CREATE UNIQUE INDEX XPKChoice ON Choice
(exerciseId   ASC,routineId   ASC);

ALTER TABLE Choice
	ADD CONSTRAINT  XPKChoice PRIMARY KEY (exerciseId,routineId);

CREATE TABLE Usage
(
	scheduleId           NUMBER(5) NOT NULL ,
	clubId               NUMBER(5) NOT NULL ,
	routineId            NUMBER(5) NOT NULL 
);

CREATE UNIQUE INDEX XPKUsage ON Usage
(clubId   ASC,scheduleId   ASC,routineId   ASC);

ALTER TABLE Usage
	ADD CONSTRAINT  XPKUsage PRIMARY KEY (clubId,scheduleId,routineId);

ALTER TABLE Club
	ADD (CONSTRAINT R_54 FOREIGN KEY (clubMaster) REFERENCES Member (MemberId));

ALTER TABLE ClubSchedule
	ADD (CONSTRAINT R_8 FOREIGN KEY (clubId) REFERENCES Club (clubId));

ALTER TABLE Diary
	ADD (CONSTRAINT R_7 FOREIGN KEY (author) REFERENCES Member (MemberId));

ALTER TABLE Membership
	ADD (CONSTRAINT R_46 FOREIGN KEY (clubId) REFERENCES Club (clubId));

ALTER TABLE Membership
	ADD (CONSTRAINT R_47 FOREIGN KEY (MemberId) REFERENCES Member (MemberId));

ALTER TABLE Routine
	ADD (CONSTRAINT R_49 FOREIGN KEY (routineCreater) REFERENCES Member (MemberId));

ALTER TABLE Choice
	ADD (CONSTRAINT R_6 FOREIGN KEY (exerciseId) REFERENCES Exercise (exerciseId));

ALTER TABLE Choice
	ADD (CONSTRAINT R_5 FOREIGN KEY (routineId) REFERENCES Routine (routineId));

ALTER TABLE Usage
	ADD (CONSTRAINT R_60 FOREIGN KEY (clubId, scheduleId) REFERENCES ClubSchedule (clubId, scheduleId));

ALTER TABLE Usage
	ADD (CONSTRAINT R_61 FOREIGN KEY (routineId) REFERENCES Routine (routineId));
