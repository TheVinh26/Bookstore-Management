create database Bookstore_Management
use Bookstore_Management
-- 1. Tạo bảng Nhân Viên
CREATE TABLE NhanVien (
    MaNV int identity(1,1),
    TenNV NVARCHAR(50),
	ChucVu NVARCHAR(30),
    DiaChi NVARCHAR(100),
    SDT VARCHAR(10),
	NgaySinh DATE,
	CONSTRAINT PK_NhanVien PRIMARY KEY (MaNV),
	constraint CK_NhanVien_NgaySinh check ((year(getdate()) - year(NgaySinh)) >= 18),
	constraint CK_NhanVien_SDT check (SDT LIKE '[0-9]%' AND LEN(SDT) = 10),
	constraint UNI_NhanVien_SDT unique (SDT)
)
-- 2. Tạo bảng Tài Khoản
create table TaiKhoan(
	MaNV int,
	UserName varchar(51),
	Pass varchar(21),
	Role_ varchar(21),
	constraint PK_TaiKhoan primary key (MaNV),
	constraint FK_TaiKhoan_NhanVien foreign key (MaNV) references NhanVien(MaNV)
)
-- 3. Tạo bảng Khách Hàng
CREATE TABLE KhachHang (
    MaKH int identity(1,1),
    TenKH NVARCHAR(50),
    DiaChi NVARCHAR(100),
    SDT VARCHAR(10),
    Email VARCHAR(50),
	CONSTRAINT PK_KhachHang PRIMARY KEY (MaKH),
	constraint CK_KhachHang_SDT check (SDT LIKE '[0-9]%' AND LEN(SDT) = 10),
	constraint UNI_KhachHang_SDT unique (SDT),
	constraint UNI_KhachHang_Email unique (Email),
	constraint CK_KhachHang_Email check(Email LIKE '%_@__%.com')
)

-- 4. Tạo bảng Nhà Cung Cấp
CREATE TABLE NCC (
    MaNCC int identity(1,1),
    TenNCC NVARCHAR(100),
	SDT VARCHAR(10),
	Email VARCHAR(50),
	DiaChi NVARCHAR(100),
	CONSTRAINT PK_NCC PRIMARY KEY (MaNCC),
	constraint CK_NCC_SDT check (SDT LIKE '[0-9]%' AND LEN(SDT) = 10),
	constraint UNI_NCC_SDT unique (SDT),
	constraint UNI_NCC_Email unique (Email),
	constraint CK_NCC_Email check(Email LIKE '%_@__%.com')
)

-- 4. Tạo bảng Loại Sản Phẩm
CREATE TABLE LoaiSP (
    MaLoai int identity(1,1),
    TenLoai NVARCHAR(100),
	CONSTRAINT PK_LoaiSP PRIMARY KEY (MaLoai)
)

-- 5. Tạo bảng Sản Phẩm
CREATE TABLE SanPham (
    MaSP int identity(1,1),
    MaNCC int,
    MaLoai int,
    GiaBan DECIMAL(10,0),
    TenSP NVARCHAR(200),
	ImageSP varchar(101),
	CONSTRAINT PK_SanPham PRIMARY KEY (MaSP),
	constraint FK_SanPham_NCC foreign key (MaNCC) references NCC(MaNCC),
	constraint FK_SanPham_LoaiSP foreign key (MaLoai) references LoaiSP(MaLoai),
	constraint CK_SanPham_GiaBan check (GiaBan > 0)
)

-- 6. Tạo bảng Kho
CREATE TABLE Kho (
	MaKho int identity(1,1),
	TenKho nvarchar(101),
	DiaChi nvarchar(256),
	CONSTRAINT PK_Kho PRIMARY KEY (MaKho)
)

---- 7. Tạo bảng Sản phẩm tồn
CREATE TABLE SanPhamTon (
    MaSP int,
	MaKho int,
    SoLuongTon int,
	CONSTRAINT PK_DonHang PRIMARY KEY (MaSp, MaKho),
	constraint FK_SanPhamTon_SanPham foreign key (MaSP) references SanPham(MaSP),
	constraint FK_SanPhamTon_Kho foreign key (MaKho) references Kho(MaKho),
	constraint CK_SanPhamTon_SoLuongTon check (SoLuongTon >= 0)
)
-- 8. Tạo bảng Phiếu Nhập
CREATE TABLE PhieuNhap (
	MaPhieuNhap int identity(1,1),
	MaNV int,
	MaNCC int,
	NgayLap DATE default getdate(),
	TongTien DECIMAL(10, 0),
	MaKho int,
	CONSTRAINT PK_PhieuNhap PRIMARY KEY (MaPhieuNhap),
	constraint FK_PhieuNhap_NhanVien foreign key (MaNV) references NhanVien(MaNV),
	constraint FK_PhieuNhap_NCC foreign key (MaNCC) references NCC(MaNCC),
	constraint FK_PhieuNhap_Kho foreign key (MaKho) references Kho(MaKho),
	constraint CK_PhieuNhap_TongTien check (TongTien > 0)
)

-- 9. Tạo bảng Chi Tiết Phiếu Nhập
CREATE TABLE ChiTietPhieuNhap (
	MaPhieuNhap int,
	MaSP int,
	SoLuong INT,
	GiaNhap DECIMAL(10, 0),
	ThanhTien AS (SoLuong * GiaNhap)
	CONSTRAINT PK_CTPN PRIMARY KEY (MaPhieuNhap, MaSP),
	constraint FK_CTPN_PhieuNhap foreign key (MaPhieuNhap) references PhieuNhap(MaPhieuNhap),
	constraint FK_CTPN_SanPham foreign key (MaSP) references SanPham(MaSP),
	constraint CK_CTPN_SoLuong check (SoLuong > 0),
	constraint CK_CTPN_DonGia check (GiaNhap > 0)
)

-- 10. Tạo bảng Phiếu Xuất
CREATE TABLE PhieuXuat (
	MaPhieuXuat int identity(1,1),
	MaNV int,
	MaKho int,
	NgayXuat DATE default getdate(),
	CONSTRAINT PK_PhieuXuat PRIMARY KEY (MaPhieuXuat),
	constraint FK_PhieuXuat_NhanVien foreign key (MaNV) references NhanVien(MaNV),
	constraint FK_PhieuXuat_Kho foreign key (MaKho) references Kho(MaKho)
)

-- 11. Tạo bảng Chi Tiết Phiếu Xuất
CREATE TABLE ChiTietPhieuXuat (
	MaPhieuXuat int,
	MaSP int,
	SoLuong INT,
	CONSTRAINT PK_CTPX PRIMARY KEY (MaPhieuXuat, MaSP),
	constraint FK_CTPX_PhieuXuat foreign key (MaPhieuXuat) references PhieuXuat(MaPhieuXuat),
	constraint FK_CTPX_SanPham foreign key (MaSP) references SanPham(MaSP),
	constraint CK_CTPX_SoLuong check (SoLuong > 0)
)

-- 11. Tạo bảng HoaDon
CREATE TABLE HoaDon (
	MaHoaDon int identity(1,1),
	MaNV int,
	MaKH int,
	TongTien DECIMAL(10, 0),
	NgayLap DATE default getdate(),
	CONSTRAINT PK_HoaDon PRIMARY KEY (MaHoaDon),
	constraint FK_HoaDon_NhanVien foreign key (MaNV) references NhanVien(MaNV),
	constraint FK_HoaDon_KhachHang foreign key (MaKH) references KhachHang(MaKH),
	constraint CK_HoaDon_TongTien check (TongTien > 0)
)

-- 12. Tạo bảng Chi Tiết Hóa Đơn
CREATE TABLE ChiTietHoaDon (
	MaHoaDon int,
	MaSP int,
	SoLuong INT,
	DonGia DECIMAL(10, 0),
	ThanhTien AS (SoLuong * DonGia)
	CONSTRAINT PK_CTHD PRIMARY KEY (MaHoaDon, MaSP),
	constraint FK_CTHD_HoaDon foreign key (MaHoaDon) references HoaDon(MaHoaDon),
	constraint FK_CTHD_SanPham foreign key (MaSP) references SanPham(MaSP),
	constraint CK_CTHD_SoLuong check (SoLuong > 0),
	constraint CK_CTHD_DonGia check (DonGia > 0)
)

set dateformat DMY

insert into KhachHang
values
	(N'Nguyễn Thị Thắm', N'466 Đường Nguyễn Văn Thụ, Phường 3, Quận 8, Thành phố Hồ Chí Minh', N'0356123569', 'thamnguyen@gmail.com'),
	(N'Nguyễn Thị Hồng', N'456 Đường Nguyễn Văn Linh, Phường 2, Quận 7, Thành phố Hồ Chí Minh', N'0356123789', 'hongnguyen@gmail.com'),
	(N'Trần Văn Hải', N'789 Đường Lê Lợi, Phường 3, Quận Bình Thạnh, Thành phố Hồ Chí Minh', N'0909876543', 'haivt@gmail.com'),
	(N'Phạm Thị Lan', N'101 Đường Nguyễn Trãi, Phường Tân Bình, Quận Tân Phú, Thành phố Hồ Chí Minh', N'0987658321', 'lanpham@gmail.com'),
	(N'Bùi Minh Tuấn', N'222 Đường 3/2, Phường 5, Quận 10, Thành phố Hồ Chí Minh', N'0398795432', 'tuanbui@gmail.com'),
	(N'Võ Văn Hoàng', N'444 Đường Hòa Hảo, Phường 6, Quận 11, Thành phố Hồ Chí Minh', N'0321654987', 'hoangvo@gmail.com'),
	(N'Nguyễn Minh Đức', N'555 Đường Bạch Đằng, Phường 7, Quận 1, Thành phố Hồ Chí Minh', N'0912342678', 'ducnguyen@gmail.com'),
	(N'Thái Thị Thủy', N'666 Đường Nguyễn Thị Minh Khai, Phường 8, Quận Tân Bình, Thành phố Hồ Chí Minh', N'0378965412', 'thuythai@gmail.com'),
	(N'Đinh Văn Đạt', N'777 Đường Lý Thường Kiệt, Phường 9, Quận 4, Thành phố Hồ Chí Minh', N'0945632178', 'datdinh@gmail.com')	

--select * from KhachHang
INSERT INTO NCC
VALUES
    (N'CÔNG TY TNHH MTV AN LỘC VIỆT', '0899189499', 'vppanlocviet@gmail.com', N'30 Kha Vạn Cân,Hiệp Bình Chánh,Thủ Đức,TPHCM'),
    (N'CÔNG TY CỔ PHẦN TẬP ĐOÀN THIÊN LONG', '0961531616', 'info@thienlonggroup.com', N'Số 10 Đường Mai Chí Thọ,Phường Thủ Thiêm,Thủ Đức,TPHCM'),
    (N'Văn phòng phẩm Hoàng Hà', '0919542541', 'vpphoangha48@gmail.com', N'247/13 Độc Lập,Phường Tân Quý,Quận Tân Phú,TPHCM');
--select * from NCC
INSERT INTO NhanVien
VALUES
    (N'Nguyễn Hữu Thông', N'Quản lý', N'123 Đường ABC, Quận 1, TP.HCM', '0123456789', '2003-01-15'),
    (N'Phạm Thị Thu Phương', N'Nhân viên bán hàng', N'456 Đường XYZ, Quận 2, TP.HCM', '0987654321', '2003-03-22'),
    (N'Nguyễn Hoài Nam', N'Nhân viên kho', N'789 Đường KLM, Quận 3, TP.HCM', '0369852147', '2003-07-10'),
    (N'Mai Thế Vinh', N'Nhân viên bán hàng', N'101 Đường LMN, Quận 4, TP.HCM', '0932154768', '2003-09-18'),
    (N'Võ Thị Kim Giàu', N'Nhân viên bán hàng', N'222 Đường XYZ, Quận 5, TP.HCM', '0765432109', '2003-12-05'),
    (N'Nguyễn Hồ Phúc Thịnh', N'Nhân viên kho', N'316/14 Tây Thạnh, Quận Tân Phú, TP.HCM', '0764047814', '2003-04-23');
--select * from NhanVien
insert into LoaiSP
values 
	(N'Sách'),
	(N'Văn phòng phẩm'),
	(N'Dụng cụ học tập');

insert into SanPham
values
	('1', '1', '179000', N'Không Gia Đình', null),
    ('1', '1', '40000', N'Ông già và biển cả', null),
    ('1', '1', '129000', N'Âm Thanh Và Cuồng Nộ', null),

	('2', '2', '3500', N'Bút Mực Xanh', null),
    ('2', '2', '4000', N'Bút Bi Đen', null),
    ('2', '2', '40000', N'Tẩy Keo', null),

	('3', '3', '7900', N'Gôm Tẩy Bút Chì', null),
    ('3', '3', '17900', N'Bảng Trắng', null),
    ('3', '3', '13000', N'Bút Chì Đen', null)

INSERT INTO SanPhamTon
values 
	(1, 2, 50),
	(2, 2, 100),
	(3, 2, 45)

--Phiếu Xuất
--select px.MaPhieuXuat, nv.TenNV, k.TenKho, px.NgayXuat from PhieuXuat px inner join NhanVien nv on px.MaNV = nv.MaNV inner join Kho k on px.MaKho = k.MaKh
insert into PhieuXuat
values
	('1', '2', '2024-05-05'),
	('2', '2', '2024/05/05'),
	('3', '2', '2024/05/05')

insert into ChiTietPhieuXuat
values
	(3, 1,	10),
	(4, 2, 50),
	(5, 3, 120)
insert into TaiKhoan
values
	(3, 'thevinh_kho', '123', 'nhan vien kho'),
	(4, 'thevinh_ql', '123', 'quan li'),
	(5, 'thevinh_nv', '123', 'nhan vien');

go
-- Trigger sau khi thêm mới vào bảng ChiTietPhieuNhap
CREATE TRIGGER trg_AfterInsertCTPN ON ChiTietPhieuNhap
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    -- Cập nhật số lượng tồn trong bảng SanPhamTon
    UPDATE SanPhamTon
    SET SoLuongTon = SoLuongTon + i.SoLuong
    FROM inserted i
    INNER JOIN PhieuNhap pn ON i.MaPhieuNhap = pn.MaPhieuNhap
    WHERE SanPhamTon.MaSP = i.MaSP AND SanPhamTon.MaKho = pn.MaKho;
END;
GO

-- Trigger sau khi thêm mới vào bảng ChiTietPhieuXuat
CREATE TRIGGER trg_AfterInsertCTPX ON ChiTietPhieuXuat
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    -- Cập nhật số lượng tồn trong bảng SanPhamTon
    UPDATE SanPhamTon
    SET SoLuongTon = SoLuongTon - i.SoLuong
    FROM inserted i
    INNER JOIN PhieuXuat px ON i.MaPhieuXuat = px.MaPhieuXuat
    WHERE SanPhamTon.MaSP = i.MaSP AND SanPhamTon.MaKho = px.MaKho;
END;
GO
