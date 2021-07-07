--teachers and student tables as they are in problem 6
drop table students;
CREATE TABLE students (
  first_name varchar2(20) NOT NULL,
  last_name varchar2(20) NOT NULL,
  grade number NOT NULL,
  gender varchar2(2) NOT NULL
);
drop table teachers;
CREATE TABLE teachers (
  first_name varchar2(20) NOT NULL,
  last_name varchar2(20) NOT NULL,
  subject varchar2(20) NOT NULL,
  gender varchar2(2) NOT NULL
);

insert into students values ('Giorgi', 'a', 12, 'M');
insert into students values ('Giorgi', 'b', 11, 'M');
insert into students values ('Ana', 'c', 10, 'F');
insert into students values ('Nini', 'a', 9, 'F');
insert into students values ('Anna', 'a', 7, 'F');

insert into teachers values ('Mathteach', 'B', 'Mathematics', 'F');
insert into teachers values ('Physteach', 'L', 'Physics', 'M');
insert into teachers values ('Litteach', 'B', 'Literature', 'M');
insert into teachers values ('Histteach', 'K', 'History', 'F');
insert into teachers values ('Bioteach', 'B', 'Biology', 'F');

-- sequence to generate id for students
drop sequence s;
CREATE Sequence s
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 1000
NOCYCLE ;

--sequence to generate id for teachers
drop sequence s2;
CREATE Sequence s2
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 1000
NOCYCLE ;

--sequence to generate id for student-teachers join table
drop sequence s3;
CREATE Sequence s3
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 1000
NOCYCLE ;

--student table with ids
drop table student_ids;
CREATE TABLE student_ids (
  student_id varchar2(20) NOT NULL,
  first_name varchar2(20) NOT NULL,
  last_name varchar2(20) NOT NULL,
  grade INT NOT NULL,
  gender varchar2(2) NOT NULL,
  constraints student_id_pk primary key (student_id)
);

select* from student_ids;

--fill student-idss table
DECLARE 
  fname students.first_name%TYPE;
  lname students.last_name%TYPE;
  grd students.grade%type;
  gndr students.gender%type;
  CURSOR c is select first_name, last_name, grade, gender from students;
BEGIN
 open c; 
 LOOp
 fetch c into fname, lname, grd, gndr;
 insert into student_ids values (s.nextval, fname, lname, grd, gndr);
 EXIT WHEN c%NOTFOUND;
 END LOOP;
END;

--teachers table with ids
drop table teacher_ids;
CREATE TABLE teacher_ids (
  teacher_id varchar2(20) NOT NULL,
  first_name varchar2(20) NOT NULL,
  last_name varchar2(20) NOT NULL,
  gender varchar2(20) NOT NULL,
  subject varchar2(20) NOT NULL,
  constraints teacher_id_pk primary key (teacher_id)
);
--fill teacher-ids table
DECLARE 
  fname teachers.first_name%TYPE;
  lname teachers.last_name%TYPE;
  subj teachers.subject%type;
  gndr students.gender%type;
  CURSOR c is select first_name, last_name, subject, gender from teachers;
BEGIN
 open c; 
 Loop
 fetch c into fname, lname, subj, gndr;
 EXIT WHEN c%NOTFOUND;
 insert into teacher_ids values (s.nextval, fname, lname, subj, gndr);
 end loop;
END;

--join table
drop table student_teacher_table;
CREATE TABLE student_teacher_table (
   student_teacher_id varchar2(20) NOT NULL,
   st_id varchar2(20) NOT NULL,
   tch_id varchar2(20) NOT NULL,
   constraints stud_teach_id_pk primary key (student_teacher_id),
   constraints teachch_id_fk foreign key (tch_id)  REFERENCES teacher_ids (teacher_id),
   constraints sstud_id_fk foreign key (st_id)  REFERENCES student_ids (student_id)
);

select * from teacher_ids;
select * from student_ids;
insert into student_teacher_table values (s3.nextval, '2', '8');
insert into student_teacher_table values (s3.nextval, '2', '7');
insert into student_teacher_table values (s3.nextval, '3', '7');
insert into student_teacher_table values (s3.nextval, '5', '10');

select * from student_teacher_table;

Select t.first_name,
       t.last_name,
       t.gender,
       t.subject 
from teacher_ids t
join student_teacher_table st on st.tch_id = t.teacher_id
join student_ids s on st.st_id = s.student_id
where s.first_name = 'Giorgi';






