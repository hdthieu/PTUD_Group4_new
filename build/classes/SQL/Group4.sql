CREATE DATABASE GROUP4
USE GROUP4
CREATE TABLE KhachHang(
	maKH varchar(15) PRIMARY KEY,
	tenKH nvarChar(25) NOT NULL,
	diaChi nvarchar(30) NOT NULL,
	soDienThoai int NOT NULL,
	gioiTinh nvarchar(5) CHECK (gioiTinh IN (N'Nam', N'Nữ'))
);

CREATE TABLE NhanVien(
        maNhanVien varchar(10) PRIMARY KEY ,
        tenNhanVien nvarchar(25) NOT NULL,
	gioiTinh nvarchar(5),
        ngaySinh Date,
	soDienThoai int NOT NULL,
	diaChi nvarchar(30) NOT NULL,
	chucVu nvarchar(20) NOT NULL,
	hinhAnh image NULL
);

CREATE TABLE TaiKhoan(
	maNhanVien varchar(10) ,
	tenNhanVien nvarchar(MAX),
	tenTaiKhoan varchar(30) PRIMARY KEY,
	matKhau varchar(30) NOT NULL,
	quyenTruyCap nvarchar(MAX) NOT NULL,
	FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien)
);

CREATE TABLE NhaCungCap(
	maNhaCungCap varchar(15) PRIMARY KEY,
	tenNhaCungCap nvarchar(25) NOT NULL,
	soDienThoai int NOT NULL,
	diaChi nvarchar(30) NOT NULL,
	
);

CREATE TABLE SanPham (
    maSP VARCHAR(15) PRIMARY KEY,
    tenSP NVARCHAR(15) NOT NULL,
    giaBan float,
	giaNhap float,
    soLuong INT NOT NULL,
    maloaiSP VARCHAR (10),
    maNhaCungCap VARCHAR(15),
    hinhAnh VARCHAR(MAX),
	FOREIGN KEY (maNhaCungCap) REFERENCES NhaCungCap(maNhaCungCap),
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


CREATE TABLE LoaiSP (
    maLoai varchar(15) PRIMARY KEY ,
    tenLoai nvarchar(MAX) NOT NULL,
    maSP varchar(15),
    FOREIGN KEY (maSP) REFERENCES SanPham(maSP)
);


-- Thêm dữ liệu vào bảng KhachHang
INSERT INTO KhachHang (maKH, tenKH, diaChi, soDienThoai, gioiTinh)
VALUES 
  ('KH001', N'Nguyễn Văn Tèo', N'123 Lê Văn, HCM', 123456789, N'Nam'),
  ('KH002', N'Tran Thi Bích', N'256 Nguyễn Trãi, Hà Nội', 987654321, N'Nữ');

-- Thêm dữ liệu vào bảng NhanVien
-- Thêm dữ liệu mẫu vào bảng NhanVien
INSERT INTO NhanVien (maNhanVien, tenNhanVien, gioiTinh, ngaySinh, soDienThoai, diaChi, chucVu, hinhAnh)
VALUES
    ('NV001', N'Nguyễn Văn A', N'Nam', '1990-01-15', 123456789, N'Hà Nội', N'Nhân Viên Quản Lý', NULL),
    ('NV002', N'Nguyễn Thị B', N'Nữ', '1995-03-20', 987654321, N'Hồ Chí Minh', N'Nhân Viên Quản Lý', NULL),
    ('NV003', N'Trần Văn C', N'Nam', '1985-05-10', 111222333, N'Đà Nẵng', N'Nhân Viên Thống Kê', NULL),
    ('NV004', N'Lê Thị D', N'Nữ', '1998-12-05', 999888777, N'Hải Phòng', N'Nhân viên Bán Hàng', NULL);

-- Thêm dữ liệu vào bảng TaiKhoan
INSERT INTO TaiKhoan (maNhanVien,tenNhanVien,tenTaiKhoan, matKhau, quyenTruyCap)
VALUES ('NV001',N'Nguyễn Văn A',N'nguyenvan', '123', N'Nhân Viên Quản Lý'), 
('NV002',N'Nguyễn Thị B',N'nguyenthib', '123', N'Nhân Viên Bán Hàng')

-- Thêm dữ liệu vào bảng NhaCungCap
INSERT INTO NhaCungCap (maNhaCungCap, tenNhaCungCap, soDienThoai, diaChi)
VALUES
  ('NCC001', N'Gucci', 0987654321, N'Địa chỉ 1'),
  ('NCC002', N'Yame', 0123456789, N'Địa chỉ 2'),
  ('NCC003', N'Dior', 0123454789, N'Địa chỉ 3'),
  ('NCC004', N'LV', 0123456739, N'Địa chỉ 4'),
  ('NCC005', N'Adidas', 0123426789, N'Địa chỉ 5'),
  ('NCC006', N'Nike', 0323456789, N'Địa chỉ 6'),
  ('NCC007', N'Tommy', 0126456789, N'Địa chỉ 7'),
  ('NCC008', N'Vans', 0123456789, N'Địa chỉ 8'),
  ('NCC009', N'Converse', 0123476789, N'Địa chỉ 9'),
  ('NCC010', N'Ananas', 0123466789, N'Địa chỉ 10');


-- Thêm dữ liệu vào bảng SanPham
INSERT INTO SanPham (maSP, tenSP, giaBan, giaNhap, soLuong, maloaiSP, maNhaCungCap, hinhAnh)
VALUES 
  ('SP001', N'Quần Louis', 2000000.0, 1500000.0, 10, 'L001', 'NCC001', 'path/to/image1.jpg'),
  ('SP002', N'Áo Yame SCVT', 1800000.0, 1300000.0, 8, 'L002', 'NCC002', 'path/to/image2.jpg');

-- Thêm dữ liệu vào bảng ChiTietHoaDon
INSERT INTO ChiTietHoaDon (maHoaDon, maSP, soLuong, donGiaBan)
VALUES 
  ('HD001', 'SP001', 5, 1500000.0), 
  ('HD002', 'SP002', 3, 1200000.0); 

-- Thêm dữ liệu vào bảng HoaDon
INSERT INTO HoaDon (maHoaDon, ngayTao, maKH, maNhanVien)
VALUES 
  ('HD001', '2023-10-13', 'KH001', 'QL001'), 
  ('HD002', '2023-10-14', 'KH002', 'NV002'); 

-- Thêm dữ liệu vào bảng LoaiSP
INSERT INTO LoaiSP (maLoai, tenLoai, maSP)
VALUES 
  ('L001', N'Quần', 'SP001'), 
  ('L002', N'Áo', 'SP002'); 

-- Thêm Chi Tiết Phiếu Đặt Hàng
INSERT INTO ChiTietPhieuDatHang (maPhieuDatHang, donGiaBan, soLuong, maSP)
VALUES 
  ('PDH001', 1500000.0, 5, 'SP001'),
  ('PDH002', 1200000.0, 3, 'SP002');
-- Chèn dữ liệu vào PhieuDatHang
INSERT INTO PhieuDatHang (maPhieuDatHang, ngayTaoPhieuDatHang, maKH, maNhanVien)
VALUES 
  ('PDH001', '2023-10-15', 'KH001', 'NV002'),
  ('PDH002', '2023-10-16', 'KH002', 'NV002');