use master
go
create database HuongRungBuffet
go
use HuongRungBuffet 
go
create table SlotTime(
	TimeID varchar(20) primary key,
	STime time
)
create table TicketType(
	TicketTypeID varchar(20) primary key,
	TTypeName nvarchar(50)
)
go
create table Customer(
	CustomerID varchar(20) primary key,
	CsName nvarchar(50),
	CsPhone varchar(20),
	csBirthDay date,
	CsIdentificationNumber varchar(50),
	[Point] int
)
go
create table Departments(
	DepartmentID varchar(20) primary key,
	ParkName nvarchar(50)
)
go
create table Employee(
	EmployeeID varchar(20) primary key,
	FullName nvarchar(50),
	Title nvarchar(50),
	Phone varchar(20),
	BirthDay date,
	EAddress nvarchar(50),
	DepartmentID varchar(20) foreign key references Departments(DepartmentID),
	UserName varchar(20),
	[Password] varchar(20),
	IdentificationNumber varchar(20)
)
go
create table Suppliers(
	supplierID varchar(20) primary key,
	supplierName nvarchar(50),
	phone varchar(20),
	Sdescription nvarchar(100)
)
go
create table Category(
	CategoryID varchar(20) primary key,
	CategoryName nvarchar(50),
	Cdescription nvarchar(100)
)
go
create table Drinks(
	DrinksID varchar(20) primary key,
	DrinkName nvarchar(50),
	supplierID varchar(20) foreign key references Suppliers(supplierID),
	CategoryID varchar(20) foreign key references Category(CategoryID),
	DPrice int
)
go
create table Bill(
	BillID varchar(20) primary key,
	BillDate date,
	EmployeeID varchar(20) foreign key references Employee(EmployeeID),
	CustomerID varchar(20) foreign key references Customer(CustomerID),
	Price int
)
go
create table Ticket(
	TicketID varchar(20) primary key,
	TicketTypeID varchar(20) foreign key references TicketType(TicketTypeID),
	TPrice varchar(20),
	TicketDate date,
	timeID varchar(20) foreign key references SlotTime(timeID),
	BillID varchar(20) foreign key references Bill(BillID)
)
go 
create table DrinkDetails(
	DrinkDetailsID varchar(20) primary key,
	DrinkID varchar(20) foreign key references Drinks(DrinksID),
	Quantity int,
	Price int,
	BillID varchar(20) foreign key references Bill(BillID)
)
go
create table FoodType(
	FoodTypeID varchar(20) primary key,
	FTypeName nvarchar(50)
)
go
create table Food(
	FoodID varchar(20) primary key,
	FoodName nvarchar(50),
	Picture nvarchar(200),
	FDescription nvarchar(50),
	FoodTypeID varchar(20) foreign key references FoodType(FoodTypeID)
)
go
create table FoodIngredients(
	IngredientsID varchar(20) primary key,
	FIName nvarchar(50),
	supplierID varchar(20) references Suppliers(supplierID),
	CategoryID varchar(20) references Category(CategoryID),
	IPrice varchar(20),
	unit nvarchar(20)
)
go
create table IngRequest(
	FoodID varchar(20) foreign key references Food(FoodID),
	IngredientsID varchar(20) foreign key references FoodIngredients(IngredientsID),
	quantity int,
	IRDate date,
	[status] varchar(20)
)
go
create table FoodlistNextDays(
	FLDate date primary key,
	EmployeeID varchar(20) foreign key references Employee(EmployeeID)
)
go
create table List(
	FoodID varchar(20) foreign key references Food(FoodID),
	FLDate date foreign key references FoodlistNextDays(FLDate),
	Quantity int not null
)

go
create table DrinkExport(
	DrinkExportID varchar(20) primary key,
	EmployeeID varchar(20) foreign key references Employee(EmployeeID),
	EDate date
)
go
create table DrinkExportDetails(
	DrinkExportID varchar(20) foreign key references DrinkExport(DrinkExportID),
	Quantity int,
	DrinksID varchar(20) foreign key references Drinks(DrinksID)
)




/*
/*Bảng Departments*/
insert into [dbo].[Departments] values ('DP01', 'Manager'),
										('DP02', 'Kitchen'),
										('DP03', 'WareHouse'),
										('DP04', 'Cahiers'),
										('DP05', 'Bar')

/*bảng employee*/
insert into [dbo].[Employee] values ('E01', N'Người thứ nhất', N'Bếp trưởng', '012345784','1992/08/12','01 Queen Liberty city','DP01','daubep1','123456','031214542154')

/*bảng foodType*/
insert into [dbo].[FoodType] values ('FT01','Appetizer'),('FT02','MainCourse'),('FT03','dessert')

/*customer*/
insert into [dbo].[Customer] values ('C02','Alo Alo member','1234567895','2002/06/12','12321312312','200')

*/
insert into [dbo].[Category] values('CT01',N'Đồ tươi',N'không có gì')
insert into [dbo].[Suppliers] values('SP01',N'bà tạp hóa','1234567891',N'thích thì bán không thì nghỉ')
insert into [dbo].[FoodIngredients] values('IG02',N'Tôm xấu','SP01','CT01','500000',N'kí')
insert into [dbo].[IngRequest] values('F01','IG01','20','2021/09/10','Y')
insert into [dbo].[IngRequest] values('F01','IG02','30','2021/09/10','Y')
insert into DrinkExport values('DEX01','E03','2021/09/10')
insert into Drinks values('D01','Cocacola','SP01','CT01','20000')
insert into DrinkExportDetails values('DEX01','20','D01')
 
select IRDate, FIName, sum(quantity) from IngRequest as a join FoodIngredients as b on a.IngredientsID = b.IngredientsID where IRDate = '2021/09/10' group by IRDate, FIName

select FoodID, IngredientsID, quantity from IngRequest
select IRDate, FIName, quantity from IngRequest as a join FoodIngredients as b on a.IngredientsID = b.IngredientsID where IRDate = '2021/09/10'
