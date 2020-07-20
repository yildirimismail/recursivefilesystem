insert into fsystem.folder (NAME) VALUES('C:/')
insert into fsystem.folder (FOLDER_ID,NAME) select ID,'Documents' from fsystem.folder where FOLDER_ID is null
insert into fsystem.folder (FOLDER_ID,NAME) select ID,'Program Files' from fsystem.folder where FOLDER_ID is null
insert into fsystem.folder (FOLDER_ID,NAME) select ID,'images' from fsystem.folder where NAME = 'Documents'
insert into fsystem.file (FOLDER_ID,NAME) select ID, 'image1.jpg' from fsystem.folder where NAME = 'images'
insert into fsystem.file (FOLDER_ID,NAME) select ID, 'image2.jpg' from fsystem.folder where NAME = 'images'
insert into fsystem.file (FOLDER_ID,NAME) select ID, 'image3.png' from fsystem.folder where NAME = 'images'
insert into fsystem.folder (FOLDER_ID,NAME) select ID,'Works' from fsystem.folder where NAME = 'Documents'
insert into fsystem.folder (FOLDER_ID,NAME) select ID,'Accountant' from fsystem.folder where NAME = 'Works'
insert into fsystem.file (FOLDER_ID,NAME) select ID, 'Accounting.xls' from fsystem.folder where NAME = 'Accountant'
insert into fsystem.file (FOLDER_ID,NAME) select ID, 'AnnualReport.xls' from fsystem.folder where NAME = 'Accountant'
insert into fsystem.file (FOLDER_ID,NAME) select ID, 'Letter.doc' from fsystem.folder where NAME = 'Works'
insert into fsystem.folder (FOLDER_ID,NAME) select ID,'Skype' from fsystem.folder where NAME = 'Program Files'
insert into fsystem.file (FOLDER_ID,NAME) select ID, 'Skype.exe' from fsystem.folder where NAME = 'Skype'
insert into fsystem.file (FOLDER_ID,NAME) select ID, 'Readme.txt' from fsystem.folder where NAME = 'Skype'
insert into fsystem.folder (FOLDER_ID,NAME) select ID,'Mysql' from fsystem.folder where NAME = 'Program Files'
insert into fsystem.file (FOLDER_ID,NAME) select ID, 'Mysql.exe' from fsystem.folder where NAME = 'Mysql'
insert into fsystem.file (FOLDER_ID,NAME) select ID, 'Mysql.com' from fsystem.folder where NAME = 'Mysql'