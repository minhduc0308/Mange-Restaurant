CREATE DATABASE Mange_Restaurant;
GO
USE Mange_Restaurant;
GO

-- Tạo bảng Users
CREATE TABLE Users (
    UserID INT PRIMARY KEY IDENTITY(1,1),
    Username NVARCHAR(50) UNIQUE,
    PasswordHash NVARCHAR(256),
    Email NVARCHAR(100),
    FullName NVARCHAR(100),
    Phone NVARCHAR(15),
    Address NVARCHAR(200), -- Thêm cột Address để lưu thông tin địa chỉ của khách hàng
    UserType NVARCHAR(50) -- Thêm cột UserType để phân biệt loại người dùng (nhân viên, khách hàng, v.v.)
);

-- Tạo bảng Roles
CREATE TABLE Roles (
    RoleID INT PRIMARY KEY IDENTITY(1,1),
    RoleName NVARCHAR(50) UNIQUE
);

-- Tạo bảng UserRoles
CREATE TABLE UserRoles (
    UserRoleID INT PRIMARY KEY IDENTITY(1,1),
    UserID INT,
    RoleID INT,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (RoleID) REFERENCES Roles(RoleID)
);

-- Tạo bảng Dishes
CREATE TABLE Dishes (
    DishID INT PRIMARY KEY IDENTITY(1,1),
    DishName NVARCHAR(100),
    Description NVARCHAR(500),
    Price DECIMAL(18, 2),
    Category NVARCHAR(50)
);

-- Tạo bảng Orders
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY IDENTITY(1,1),
    CustomerID INT, -- Sử dụng UserID để lưu thông tin khách hàng
    UserID INT, -- Thay thế EmployeeID bằng UserID
    OrderDate DATETIME,
    TotalAmount DECIMAL(18, 2),
    FOREIGN KEY (CustomerID) REFERENCES Users(UserID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- Tạo bảng OrderDetails
CREATE TABLE OrderDetails (
    OrderDetailID INT PRIMARY KEY IDENTITY(1,1),
    OrderID INT,
    DishID INT,
    Quantity INT,
    Price DECIMAL(18, 2),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (DishID) REFERENCES Dishes(DishID)
);

-- Tạo bảng Tables
CREATE TABLE Tables (
    TableID INT PRIMARY KEY IDENTITY(1,1),
    TableNumber INT,
    Capacity INT,
    Status NVARCHAR(50)
);

-- Tạo bảng Reservations
CREATE TABLE Reservations (
    ReservationID INT PRIMARY KEY IDENTITY(1,1),
    CustomerID INT, -- Sử dụng UserID để lưu thông tin khách hàng
    TableID INT,
    ReservationDate DATETIME,
    NumberOfGuests INT,
    FOREIGN KEY (CustomerID) REFERENCES Users(UserID),
    FOREIGN KEY (TableID) REFERENCES Tables(TableID)
);

-- Tạo bảng Reviews
CREATE TABLE Reviews (
    ReviewID INT PRIMARY KEY IDENTITY(1,1),
    CustomerID INT, -- Sử dụng UserID để lưu thông tin khách hàng
    DishID INT,
    Rating INT,
    Comment NVARCHAR(500),
    ReviewDate DATETIME,
    FOREIGN KEY (CustomerID) REFERENCES Users(UserID),
    FOREIGN KEY (DishID) REFERENCES Dishes(DishID)
);

-- Tạo bảng Inventory
CREATE TABLE Inventory (
    InventoryID INT PRIMARY KEY IDENTITY(1,1),
    IngredientName NVARCHAR(100),
    Quantity INT,
    Unit NVARCHAR(50),
    Threshold INT
);

-- Tạo bảng Payments
CREATE TABLE Payments (
    PaymentID INT PRIMARY KEY IDENTITY(1,1),
    OrderID INT,
    PaymentMethod NVARCHAR(50),
    PaymentDate DATETIME,
    Amount DECIMAL(18, 2),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);

-- Tạo bảng Notifications
CREATE TABLE Notifications (
    NotificationID INT PRIMARY KEY IDENTITY(1,1),
    UserID INT,
    Message NVARCHAR(500),
    NotificationDate DATETIME,
    IsRead BIT,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);




-- Tạo bảng OrderStatus để quản lý trạng thái đơn hàng
CREATE TABLE OrderStatus (
    StatusID INT PRIMARY KEY IDENTITY(1,1),
    StatusName NVARCHAR(50) UNIQUE -- VD: Đang xử lý, Đã giao, Đã hủy
);

-- Thêm cột trạng thái cho bảng Orders
ALTER TABLE Orders
ADD OrderStatusID INT;

-- Tạo khóa ngoại cho cột OrderStatusID
ALTER TABLE Orders
ADD CONSTRAINT FK_Order_OrderStatus FOREIGN KEY (OrderStatusID) REFERENCES OrderStatus(StatusID);

-- Tạo bảng ReservationStatus để quản lý trạng thái đặt bàn
CREATE TABLE ReservationStatus (
    StatusID INT PRIMARY KEY IDENTITY(1,1),
    StatusName NVARCHAR(50) UNIQUE -- VD: Đang chờ, Đã xác nhận, Đã hủy
);

-- Thêm cột trạng thái cho bảng Reservations
ALTER TABLE Reservations
ADD ReservationStatusID INT;

-- Tạo khóa ngoại cho cột ReservationStatusID
ALTER TABLE Reservations
ADD CONSTRAINT FK_Reservation_ReservationStatus FOREIGN KEY (ReservationStatusID) REFERENCES ReservationStatus(StatusID);

-- Tối ưu hóa bảng Inventory
-- Thêm cột ReorderLevel để chỉ định mức độ cần đặt hàng lại
ALTER TABLE Inventory
ADD ReorderLevel INT;

-- Tạo bảng InventoryTransaction để theo dõi biến động kho
CREATE TABLE InventoryTransaction (
    TransactionID INT PRIMARY KEY IDENTITY(1,1),
    InventoryID INT,
    TransactionType NVARCHAR(50), -- VD: Nhập, Xuất
    Quantity INT,
    TransactionDate DATETIME,
    FOREIGN KEY (InventoryID) REFERENCES Inventory(InventoryID)
);

-- Thêm ràng buộc để đảm bảo tên món ăn trong bảng Dishes là duy nhất
ALTER TABLE Dishes
ADD CONSTRAINT UQ_DishName UNIQUE (DishName);

-- Thêm ràng buộc để đảm bảo không có giá trị âm trong cột Quantity của bảng Inventory
ALTER TABLE Inventory
ADD CONSTRAINT CHK_Inventory_Quantity CHECK (Quantity >= 0);

-- Thêm chỉ số cho cột OrderDate trong bảng Orders để tối ưu hóa truy vấn theo ngày
CREATE INDEX IDX_Orders_OrderDate ON Orders(OrderDate);

-- Thêm chỉ số cho cột DishID trong bảng OrderDetails để tối ưu hóa truy vấn món ăn theo đơn hàng
CREATE INDEX IDX_OrderDetails_DishID ON OrderDetails(DishID);

-- Thêm chỉ số cho cột ReservationDate trong bảng Reservations để tối ưu hóa truy vấn đặt bàn theo ngày
CREATE INDEX IDX_Reservations_ReservationDate ON Reservations(ReservationDate);

-- Thêm ràng buộc khóa ngoại cho bảng Reviews để tự động xóa các đánh giá nếu món ăn bị xóa
ALTER TABLE Reviews
ADD CONSTRAINT FK_Reviews_Dishes_Cascade FOREIGN KEY (DishID) REFERENCES Dishes(DishID) ON DELETE CASCADE;
