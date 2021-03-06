CREATE TABLE CHUYENDE(
	MACD varchar(10) NOT NULL PRIMARY KEY,
	TENCD nvarchar(30) NOT NULL,
	HOCPHI float,
	THOILUONG DATETIME,
	MOTA nvarchar(50),
	HINH varchar(30)
)

CREATE TABLE NHANVIEN(
	MANV varchar(10) NOT NULL PRIMARY KEY,
	TENNV nvarchar(30) NOT NULL,
	MATKHAU varchar(10) NOT NULL,
	VAITRO BIT
)

 CREATE TABLE KHOAHOC(
	MAKH varchar(10) NOT NULL PRIMARY KEY,
	TENKH nvarchar(30) NOT NULL,
	THOILUONG DATE,
	HOCPHI FLOAT,
	NGAYTAO DATETIME,
	GHICHU nvarchar(50),
	NGAYKG DATETIME,
	MACD VARCHAR(10) FOREIGN KEY(MACD) REFERENCES CHUYENDE(MACD),
	MANV VARCHAR(10) FOREIGN KEY(MANV) REFERENCES NHANVIEN(MANV)
 )
 CREATE TABLE NGUOIHOC(
	MANH varchar(10) NOT NULL PRIMARY KEY,
	TENNH nvarchar(30) NOT NULL,
	GIOITINH BIT,
	SDT varchar(15),
	EMAIL varchar(30),
	GHICHU Nvarchar(30),
	NGAYDK DATE,
	MANV varchar(10) NOT NULL FOREIGN KEY(MANV) REFERENCES NHANVIEN(MANV)
 )

 CREATE TABLE HOCVIEN(
	MAHV varchar(10) NOT NULL PRIMARY KEY,
	DIEM FLOAT,
	MANH varchar(10) NOT NULL FOREIGN KEY(MANH) REFERENCES NGUOIHOC(MANH),
	MAKH varchar(10) NOT NULL FOREIGN KEY(MAKH) REFERENCES KHOAHOC(MAKH)
 )