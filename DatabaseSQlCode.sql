use master
go
create database HuongRungBuffet
go
use HuongRungBuffet
create table TicketType(
	TicketTypeID varchar(20) primary key not null,
	TTypeName nvarchar(50) not null
)
go
create table Customer(
	CustomerID varchar(20) primary key not null,
	CsName nvarchar(50) not null,
	CsPhone varchar(20) not null,
	csBirthDay date not null,
	CsIdentificationNumber varchar(20) not null,
	Point int not null
)
go
create table Ticket(
	TicketID varchar(20) not null,
	TicketTypeID varchar(20) foreign key references TicketType(TicketTypeID) not null,
	[Description] nvarchar(50),
	Price varchar(20) not null,
	TicketDate date
)
go
create table Departments(
	PartID varchar(20) primary key not null,
	ParkName nvarchar()50) not null
)
go
create table Employee(
	EmployeeID varchar(20) primary key not null,
	FullName nvarchar(50) not null,
	Title nvarchar(50) not null,
	Phone varchar(20) not null,
	BirthDay date not null,
	EAddress nvarchar(50) not null,
	PartID varchar(20) foreign key references Departments(PartID) not null,
	UserName varchar(20) not null,
	[Password] varchar(20) not null,
	IdentificationNumber varchar(20) not null
)
go create table Bill(
	
)
