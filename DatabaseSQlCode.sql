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
	TicketID varchar(20) primary key not null ,
	TicketTypeID varchar(20) foreign key references TicketType(TicketTypeID) not null,
	TDescription nvarchar(50),
	TPrice varchar(20) not null,
	TicketDate date
)
go
create table Departments(
	PartID varchar(20) primary key not null,
	ParkName nvarchar(50) not null
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
go 
create table Bill(
	BillID varchar(20) primary key not null,
	BillDate date not null,
	EmployeeID varchar(20) foreign key references Employee(EmployeeID) not null,
	TicketID varchar(20) foreign key references Ticket(TicketID) not null,
	CustomerID varchar(20) foreign key references Customer(CustomerID) not null
)
go
create table DeviceReport(
	FeedBackID varchar(20) primary key not null,
	EmployeeID varchar(20) foreign key references Employee(EmployeeID) not null,
	Content nvarchar(100) not null
)
go
create table Suppliers(
	supplierID varchar(20) primary key not null,
	supplierName nvarchar(50) not null,
	phone varchar(20),
	Sdescription nvarchar(100)
)
go
create table Category(
	CategoryID varchar(20) primary key not null,
	CategoryName nvarchar(50) not null,
	Cdescription nvarchar(100)
)
go
create table FoodType(
	FoodTypeID varchar(20) primary key not null,
	FTypeName nvarchar(50) not null
)
go
create table Food(
	FoodID varchar(20) primary key not null,
	FoodName nvarchar(50) not null,
	FoodPrice varchar(20) not null,
	Picture nvarchar(50),
	FDescription nvarchar(50),
	FoodTypeID varchar(20) foreign key references FoodType(FoodTypeID) not null
)
go
create table FoodIngredients(
	IngredientsID varchar(20) primary key not null,
	FIName nvarchar(50) not null,
	supplierID varchar(20) references Suppliers(supplierID) not null,
	CategoryID varchar(20) references Category(CategoryID) not null,
	unit nvarchar(20) not null 
)
go
create table IngNeededForRecipe(
	FoodID varchar(20) foreign key references Food(FoodID) not null,
	IngredientsID varchar(20) foreign key references FoodIngredients(IngredientsID),
	quantity int
)
go
create table FoodlistNextWeek(
	FoodlistID varchar(20) primary key not null,
	EmployeeID varchar(20) foreign key references Employee(EmployeeID) not null,
	[Week] int not null,
	[year] int not null
)
go
create table List(
	FoodID varchar(20) foreign key references Food(FoodID) not null,
	FoodlistID varchar(20) foreign key references FoodlistNextWeek(FoodlistID) not null,
	Quantity int not null
)
go
create table Drinks(
	DrinksID varchar(20) primary key not null,
	DrinkNamee nvarchar(50) not null,
	supplierID varchar(20) foreign key references Suppliers(supplierID) not null,
	CategoryID varchar(20) foreign key references Category(CategoryID) not null,
	DPrice varchar(20) not null
)
go
create table DrinkExport(
	DrinkExportID varchar(20) primary key not null,
	EmployeeID varchar(20) foreign key references Employee(EmployeeID) not null,
	EDate date not null
)