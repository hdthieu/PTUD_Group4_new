CREATE DATABASE GROUP4
USE GROUP4
CREATE TABLE KhachHang(
	maKH varchar(15) PRIMARY KEY,
	tenKH nvarChar(25) NOT NULL,
	diaChi nvarchar(50) NOT NULL,
	soDienThoai varchar(11) NOT NULL,
	gioiTinh nvarchar(5) CHECK (gioiTinh IN (N'Nam', N'Nữ'))
);

CREATE TABLE NhanVien(
    maNhanVien varchar(10) PRIMARY KEY ,
    tenNhanVien nvarchar(25) NOT NULL,
	gioiTinh nvarchar(5) NOT NULL, 
    ngaySinh nvarchar(30) NOT NULL,
	soDienThoai varchar(11) NOT NULL,
		diaChi nvarchar(50) NOT NULL,
	chucVu nvarchar(20) NOT NULL,
<<<<<<< HEAD
	soDienThoai varchar(11) NOT NULL,
	diaChi nvarchar(50) NOT NULL,
	hinhAnh VARCHAR(MAX),
=======
	hinhAnh image
>>>>>>> 78513f62ecc32600449ee16a105d0fc5a625d264
);

CREATE TABLE TaiKhoan(
	
	tenTaiKhoan varchar(40) PRIMARY KEY,
	matKhau varchar(20) NOT NULL,
	quyenTruyCap nvarchar(MAX) NOT NULL,
	maNhanVien varchar(10) ,
	tenNhanVien nvarchar(30),
	FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien)
);

CREATE TABLE NhaCungCap(
	maNhaCungCap varchar(15) PRIMARY KEY,
	tenNhaCungCap nvarchar(25) NOT NULL,
	soDienThoai varchar(11) NOT NULL,
	diaChi nvarchar(50) NOT NULL,
	
);
CREATE TABLE LoaiSP (
    maLoai varchar(10) PRIMARY KEY ,
    tenLoai nvarchar(MAX) NOT NULL,
);

CREATE TABLE SanPham (
    maSP VARCHAR(15) PRIMARY KEY,
    tenSP NVARCHAR(15) NOT NULL,
    giaBan float,
    giaNhap float,
    soLuong INT NOT NULL,
    maLoaiSP VARCHAR (10),
    maNhaCungCap VARCHAR(15),
    hinhAnh VARCHAR(MAX),
	FOREIGN KEY (maNhaCungCap) REFERENCES NhaCungCap(maNhaCungCap),
<<<<<<< HEAD
	FOREIGN KEY (maloaiSP) REFERENCES LoaiSP(maLoai)
=======
<<<<<<< HEAD
	FOREIGN KEY (maLoaiSP) REFERENCES LoaiSP(maLoaiSP)
=======
	FOREIGN KEY (maloaiSP) REFERENCES LoaiSP(maLoai)
>>>>>>> d33b5b60c944af709ca5ca87118ce4b3511dc005
>>>>>>> 78513f62ecc32600449ee16a105d0fc5a625d264
);
Alter table SanPham 
ADD Constraint checkGia Check (giaBan > giaNhap)

CREATE TABLE ChiTietHoaDon(
	maHoaDon varchar(15) PRIMARY KEY,
	maSP varchar(15),
	soLuong int NOT NULL,
	donGiaBan float NOT NULL
	FOREIGN KEY (maSP) REFERENCES SanPham(maSP)
);

CREATE TABLE HoaDon(
	maHoaDon varchar(15) PRIMARY KEY,
	ngayTao Date,
	maKH varchar(15),
	maNhanVien varchar(10),
	FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
	FOREIGN KEY (maHoaDon) REFERENCES ChiTietHoaDon(maHoaDon),
	FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien)
);

CREATE TABLE PhieuDatHang (
    maPhieuDatHang varchar(15) NOT NULL PRIMARY KEY,
    ngayTaoPhieuDatHang Date NOT NULL,
    maKH varchar(15),
    maNhanVien varchar(10),
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien)
);

CREATE TABLE ChiTietPhieuDatHang (
    maPhieuDatHang varchar(15) PRIMARY KEY,
    donGiaBan float NOT NULL,
    soLuong int NOT NULL,
    maSP varchar(15),
    FOREIGN KEY (maPhieuDatHang) REFERENCES PhieuDatHang(maPhieuDatHang),
    FOREIGN KEY (maSP) REFERENCES SanPham(maSP)
);


<<<<<<< HEAD

=======
<<<<<<< HEAD
CREATE TABLE LoaiSP (
    maLoaiSP varchar(15) PRIMARY KEY ,
    tenLoai nvarchar(MAX) NOT NULL
);
=======

>>>>>>> d33b5b60c944af709ca5ca87118ce4b3511dc005
>>>>>>> 78513f62ecc32600449ee16a105d0fc5a625d264


-- Thêm dữ liệu vào bảng KhachHang
INSERT INTO KhachHang (maKH, tenKH, diaChi, soDienThoai, gioiTinh)
VALUES 
  ('KH001', N'Nguyễn Văn Tèo', N'123 Lê Văn, HCM', '0123456789', N'Nam'),
  ('KH002', N'Tran Thi Bích', N'256 Nguyễn Trãi, Hà Nội', '0987654321', N'Nữ'),
  ('KH003', N'Phạm Văn Đồng', N'789 Đường Ba Tháng Hai, Đà Nẵng', '0123456789', N'Nam'),
  ('KH004', N'Nguyễn Thị Mai', N'456 Lê Lợi, Quảng Ninh', '0987654321', N'Nữ'),
  ('KH005', N'Lê Minh Tuấn', N'789 Trần Hưng Đạo, HCM', '0123456789', N'Nam');

-- Thêm dữ liệu vào bảng NhanVien
INSERT INTO NhanVien (maNhanVien, tenNhanVien, diaChi, soDienThoai, gioiTinh, ngaySinh, hinhAnh, chucVu)
VALUES 
<<<<<<< HEAD
  ('QL001', N'Nguyễn Văn', N'Quận Gò Vấp, HCM', '0987563874', N'Nam', '1990-01-15', 'link_hinh_anh1', N'Quản Lý'), 
  ('NV002', N'Lại Văn Tạo', N'Quận Bình Thạnh, HCM', '0123456789', N'Nữ', '1995-05-05', 'path/to/avatar2.jpg', N'Nhân Viên Bán Hàng'),
  ('NV003', N'Trần Thị Hương', N'567 Lê Lai, Hà Nội', '0987654321', N'Nữ', '1992-03-20', 'path/to/avatar3.jpg', N'Nhân Viên Kho'),
  ('NV004', N'Nguyễn Đình Minh', N'789 Nguyễn Văn Linh, Đà Nẵng', '0123456789', N'Nam', '1988-12-10', 'path/to/avatar4.jpg', N'Nhân Viên Bán Hàng'),
  ('NV005', N'Phạm Thị Hoa', N'123 Trần Phú, HCM', '0987654321', N'Nữ', '1993-07-18', 'path/to/avatar5.jpg', N'Nhân Viên Kho');

-- Thêm dữ liệu vào bảng TaiKhoan
INSERT INTO TaiKhoan (tenTaiKhoan, matKhau, quyenTruyCap, maNhanVien)
VALUES (N'nguyenvan', '123', N'Quản Lý', 'QL001'), 
(N'laivantao', '123', N'Nhân Viên Bán Hàng', 'NV002'),
(N'huongtran', '123', N'Nhân Viên Kho', 'NV003'),
(N'minhnguyen', '123', N'Nhân Viên Bán Hàng', 'NV004'),
(N'hoapham', '123', N'Nhân Viên Kho', 'NV005');
=======
  ('NV001', N'Nguyễn Văn', N'Quận Gò Vấp, HCM', '0987563874', N'Nam', '1990-01-15', 'link_hinh_anh1', N'Nhân Viên Quản Lý'), 
  ('NV002', N'Lại Văn Tạo', N'Quận Bình Thạnh, HCM', '0123456789', N'Nữ', '1995-05-05', 'path/to/avatar2.jpg', N'Nhân Viên Bán Hàng'),
  ('NV003', N'Trần Thị Hương', N'567 Lê Lai, Hà Nội', '0987654321', N'Nữ', '1992-03-20', 'path/to/avatar3.jpg', N'Nhân Viên Quản Lý'),
  ('NV004', N'Nguyễn Đình Minh', N'789 Nguyễn Văn Linh, Đà Nẵng', '0123456789', N'Nam', '1988-12-10', 'path/to/avatar4.jpg', N'Nhân Viên Bán Hàng'),
  ('NV005', N'Phạm Thị Hoa', N'123 Trần Phú, HCM', '0987654321', N'Nữ', '1993-07-18', 'path/to/avatar5.jpg', N'Nhân Viên Bán Hàng');

-- Thêm dữ liệu vào bảng TaiKhoan
INSERT INTO TaiKhoan (tenTaiKhoan, matKhau, quyenTruyCap, maNhanVien,tenNhanVien)
VALUES (N'nguyenvan', '123', N'Nhân Viên Quản Lý', 'NV001','Đinh Văn Hưng'), 
(N'laivantao', '123', N'Nhân Viên Bán Hàng', 'NV002','Đinh Văn Hoàng')

>>>>>>> 78513f62ecc32600449ee16a105d0fc5a625d264

-- Thêm dữ liệu vào bảng NhaCungCap
INSERT INTO NhaCungCap (maNhaCungCap, tenNhaCungCap, soDienThoai, diaChi)
VALUES 
  ('NCC001', N'Công Ty Quần Jean', '0987654321', N'789 Hoàng Vân, Đồng Tháp'),
  ('NCC002', N'Công Ty Áo Yame', '0923456789', N'456 Lê Văn Việt, HCM' );

-- Thêm dữ liệu vào bảng LoaiSP
INSERT INTO LoaiSP (maLoai, tenLoai)
VALUES 
  ('L001', N'Quần'), 
  ('L002', N'Áo');

-- Thêm dữ liệu vào bảng SanPham
INSERT INTO SanPham (maSP, tenSP, giaBan, giaNhap, soLuong, maLoaiSP, maNhaCungCap, hinhAnh)
VALUES 
  ('SP001', N'Quần Louis', 2000000.0, 1500000.0, 10, 'L001', 'NCC001', 'path/to/image1.jpg'),
  ('SP002', N'Áo Yame SCVT', 1800000.0, 1300000.0, 8, 'L002', 'NCC002', 'path/to/image2.jpg'),
  ('SP003', N'Áo Sơ Mi Trắng', 1200000.0, 900000.0, 15, 'L002', 'NCC002', 'path/to/image3.jpg'),
  ('SP004', N'Quần Jean Nam', 1500000.0, 1100000.0, 12, 'L001', 'NCC001', 'path/to/image4.jpg'),
  ('SP005', N'Áo Khoác Đen', 2500000.0, 1800000.0, 8, 'L002', 'NCC002', 'path/to/image5.jpg');

-- Thêm dữ liệu vào bảng ChiTietHoaDon
INSERT INTO ChiTietHoaDon (maHoaDon, maSP, soLuong, donGiaBan)
VALUES 
  ('HD001', 'SP001', 5, 1500000.0), 
  ('HD002', 'SP002', 3, 1200000.0),
  ('HD003', 'SP003', 4, 1000000.0),
  ('HD004', 'SP004', 6, 1300000.0),
  ('HD005', 'SP005', 2, 2000000.0);

-- Thêm dữ liệu vào bảng HoaDon
INSERT INTO HoaDon (maHoaDon, ngayTao, maKH, maNhanVien)
VALUES 
  ('HD001', '2023-10-13', 'KH001', 'QL001'), 
  ('HD002', '2023-10-14', 'KH002', 'NV002'),
  ('HD003', '2023-10-15', 'KH003', 'NV003'),
  ('HD004', '2023-10-16', 'KH004', 'NV004'),
  ('HD005', '2023-10-17', 'KH005', 'NV005');

<<<<<<< HEAD
=======
<<<<<<< HEAD
-- Thêm dữ liệu vào bảng LoaiSP
INSERT INTO LoaiSP (maLoaiSP, tenLoai)
VALUES 
  ('L001', N'Quần'), 
  ('L002', N'Áo'); 
=======
>>>>>>> d33b5b60c944af709ca5ca87118ce4b3511dc005
>>>>>>> 78513f62ecc32600449ee16a105d0fc5a625d264

-- Chèn dữ liệu vào PhieuDatHang
INSERT INTO PhieuDatHang (maPhieuDatHang, ngayTaoPhieuDatHang, maKH, maNhanVien)
VALUES 
  ('PDH001', '2023-10-15', 'KH001', 'NV002'),
  ('PDH002', '2023-10-16', 'KH002', 'NV002'),
  ('PDH003', '2023-10-17', 'KH003', 'NV003'),
  ('PDH004', '2023-10-18', 'KH004', 'NV004'),
  ('PDH005', '2023-10-19', 'KH005', 'NV005');


  -- Thêm Chi Tiết Phiếu Đặt Hàng
INSERT INTO ChiTietPhieuDatHang (maPhieuDatHang, donGiaBan, soLuong, maSP)
VALUES 
  ('PDH001', 1500000.0, 5, 'SP001'),
  ('PDH002', 1200000.0, 3, 'SP002'),
  ('PDH003', 1000000.0, 4, 'SP003'),
  ('PDH004', 1300000.0, 6, 'SP004'),
  ('PDH005', 2000000.0, 2, 'SP005');