BEGIN TRANSACTION;
CREATE TABLE ProductList
            (ProductiD Numeric(11,0) Not Null,
			 ProductName Varchar(30) Not Null,
			 Company Varchar(30) Not Null,
			 Quantity Varchar(30),
			 Purchasedate Varchar(10),
			 Purchaseprice Varchar(30),
			 Supplierid Numeric(12,1) Not Null,
			 suppliername Varchar(30) ,
			 Constraint ProductList_PK Primary Key(Productid,Supplierid));
INSERT INTO `ProductList` VALUES (6,'Robotic Starter Kit','China','20','20-01-2016','210',16,'PLab');
INSERT INTO `ProductList` VALUES (5,'
Bluetooth Module Breakout','Japan','80','23-1-2016','255',15,'PLab');
INSERT INTO `ProductList` VALUES (4,'Digital IC Tester','China','26','22-1-2016','222',14,'SLab');
INSERT INTO `ProductList` VALUES (3,'BreadBoard Power Supply Kit','Bangladesh','55','22-1-2016','223',13,'Techshop');
INSERT INTO `ProductList` VALUES (2,'Power Supply Kit','China','31','22-1-2016','221',12,'PLab

');
INSERT INTO `ProductList` VALUES (1,'Soldering Kit','Bangladesh','30','20-2-2016','200',11,'Eshop');
INSERT INTO `ProductList` VALUES (7,'Light Sensor Module','Bangladesh','30','22-1-2016','220',17,'Techshop');
INSERT INTO `ProductList` VALUES (8,'LCD Display','Bangladesh','50','23-1-2016','240',18,'PLab');
INSERT INTO `ProductList` VALUES (9,'1.8'''' Graphic LCD','Bangladesh','50','20-1-2016','230',19,'SLab');
INSERT INTO `ProductList` VALUES (10,'Zener 5.1V','China','60','20-2-2016','110',20,'Techshop');
INSERT INTO `ProductList` VALUES (11,'LED Green','Bangladesh','50','20-1-2016','200',21,'PLab');
INSERT INTO `ProductList` VALUES (12,'Battery 9V','China','60','21-3-2016','203',22,'Techshop');
INSERT INTO `ProductList` VALUES (13,'Signal Buzzer','China','40','22-1-2016','204',23,'PLab');
INSERT INTO `ProductList` VALUES (14,'Electret Microphone','Bangladesh','60','23-1-2016','222',24,'SLab');
INSERT INTO `ProductList` VALUES (15,'Reflective Optical Sensor','Bangladesh','50','23-1-2016','250',25,'Techshop');
INSERT INTO `ProductList` VALUES (16,'Ic Base 8pin','Japan','50','22-2-2016','300',26,'Techshop');
INSERT INTO `ProductList` VALUES (17,'DC Socket - Small','Bangladesh','50','22-2-2016','255',27,'PLab');
INSERT INTO `ProductList` VALUES (18,'IR Transmitter','China','60','22-2-2016','220',28,'SLab');
INSERT INTO `ProductList` VALUES (19,'LDR','Bangladesh','60','22-2-2016','200',29,'PLab');
INSERT INTO `ProductList` VALUES (20,'Servo Motor SG90','China','60','22-2-2016','201',30,'Techshop');
COMMIT;
