USE [QLyLuong]
GO
INSERT [dbo].[SanPham] ([maSP], [tenSP], [soLuongTon], [giaThanh], [trangThai]) VALUES (N'SP01', N'Bàn gỗ', 5000, 500000.0000, 1)
GO
INSERT [dbo].[SanPham] ([maSP], [tenSP], [soLuongTon], [giaThanh], [trangThai]) VALUES (N'SP02', N'Ghế gỗ', 2000, 450000.0000, 1)
GO
INSERT [dbo].[SanPham] ([maSP], [tenSP], [soLuongTon], [giaThanh], [trangThai]) VALUES (N'SP03', N'Giường', 100, 1000000.0000, 1)
GO
INSERT [dbo].[SanPham] ([maSP], [tenSP], [soLuongTon], [giaThanh], [trangThai]) VALUES (N'SP04', N'Ghế sofa', 200, 800000.0000, 1)
GO
INSERT [dbo].[SanPham] ([maSP], [tenSP], [soLuongTon], [giaThanh], [trangThai]) VALUES (N'SP05', N'Tủ gỗ', 500, 500000.0000, 1)
GO
INSERT [dbo].[SanPham] ([maSP], [tenSP], [soLuongTon], [giaThanh], [trangThai]) VALUES (N'SP06', N'Bàn tròn', 100, 450000.0000, 0)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP01CD01', N'Thiết kế', 20000, N'SP01', 1)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP01CD02', N'Xẻ gỗ', 30000, N'SP01', 2)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP01CD03', N'Lắp ráp', 50000, N'SP01', 3)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP02CD01', N'Cắt gỗ', 30000, N'SP02', 1)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP02CD02', N'Ghép gỗ', 40000, N'SP02', 2)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP02CD03', N'Dũa ', 20000, N'SP02', 3)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP02CD04', N'Sơn', 30000, N'SP02', 4)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP03CD01', N'Chọn gỗ', 20000, N'SP03', 1)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP03CD02', N'Chỉnh kích thước', 10000, N'SP03', 2)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP03CD03', N'Xử lý gỗ', 40000, N'SP03', 3)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP03CD04', N'Lắp ghép', 30000, N'SP03', 4)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP03CD05', N'Mài', 20000, N'SP03', 5)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP04CD01', N'Chọn bông', 20000, N'SP04', 1)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP04CD02', N'May', 40000, N'SP04', 2)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP04CD03', N'Ghép ghế', 50000, N'SP04', 3)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP04CD04', N'Phủ nhựa', 30000, N'SP04', 4)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP05CD01', N'Chọn gỗ nhỏ', 20000, N'SP05', 1)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP05CD02', N'Cắt xẻ', 50000, N'SP05', 2)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP05CD03', N'Ghép tủ', 60000, N'SP05', 3)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP06CD01', N'Tỉa tròn', 40000, N'SP06', 1)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP06CD02', N'Dựng đế', 30000, N'SP06', 2)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP06CD03', N'Ghép bàn', 40000, N'SP06', 3)
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [luongTheoSP], [maSP], [thuTu]) VALUES (N'SP06CD04', N'Phủ sơn', 50000, N'SP06', 4)
GO
INSERT [dbo].[ToSanXuat] ([maToSX], [moTa], [soLuongCN]) VALUES (1, N'example data', 20)
GO
INSERT [dbo].[ToSanXuat] ([maToSX], [moTa], [soLuongCN]) VALUES (2, N'example data', 20)
GO
INSERT [dbo].[ToSanXuat] ([maToSX], [moTa], [soLuongCN]) VALUES (3, N'example data', 20)
GO
INSERT [dbo].[ToSanXuat] ([maToSX], [moTa], [soLuongCN]) VALUES (4, N'example data', 20)
GO
INSERT [dbo].[ToSanXuat] ([maToSX], [moTa], [soLuongCN]) VALUES (5, N'example data', 20)
GO
INSERT [dbo].[ToSanXuat] ([maToSX], [moTa], [soLuongCN]) VALUES (6, N'example data', 20)
GO
INSERT [dbo].[ToSanXuat] ([maToSX], [moTa], [soLuongCN]) VALUES (7, N'example data', 20)
GO
INSERT [dbo].[ToSanXuat] ([maToSX], [moTa], [soLuongCN]) VALUES (8, N'example data', 20)
GO
INSERT [dbo].[ToSanXuat] ([maToSX], [moTa], [soLuongCN]) VALUES (9, N'example data', 20)
GO
INSERT [dbo].[ToSanXuat] ([maToSX], [moTa], [soLuongCN]) VALUES (10, N'example data', 20)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN01', N'Nguyen Van A', 1, CAST(0x05080B00 AS Date), N'123 Le Loi, Quan 1, TP.HCM', N'123456789012', N'0971234567', CAST(0x4E2C0B00 AS Date), 1, 1, N'Trung Cap', 2, 3500, 500)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN02', N'Tran Thi B', 0, CAST(0x930A0B00 AS Date), N'456 Nguyen Hue, Quan 1, TP.HCM', N'234567890123', N'0972345678', CAST(0x792D0B00 AS Date), 2, 0, N'Cao Dang', 1, 3000, 450)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN03', N'Le Duc C', 1, CAST(0x5F0F0B00 AS Date), N'789 Dong Khoi, Quan 1, TP.HCM', N'345678901234', N'0973456789', CAST(0x872E0B00 AS Date), 3, 1, N'Dai Hoc', 3, 4000, 550)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN04', N'Pham Minh D', 0, CAST(0x2F140B00 AS Date), N'101 Nguyen Trai, Quan 5, TP.HCM', N'456789012345', N'0974567890', CAST(0xBE2F0B00 AS Date), 4, 0, N'Trung Cap', 2, 3200, 480)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN05', N'Hoang Anh E', 1, CAST(0x21160B00 AS Date), N'202 Le Van Tam, Quan 3, TP.HCM', N'567890123456', N'0975678901', CAST(0x2E320B00 AS Date), 5, 1, N'Cao Dang', 1, 3100, 460)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN06', N'Vu Thu F', 0, CAST(0x56010B00 AS Date), N'303 Vo Thi Sau, Quan 3, TP.HCM', N'678901234567', N'0976789012', CAST(0x06350B00 AS Date), 6, 1, N'Trung Cap', 4, 3800, 520)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN07', N'Bui Tuan G', 1, CAST(0xD90B0B00 AS Date), N'404 Phan Dinh Phung, Quan Phu Nhuan, TP.HCM', N'789012345678', N'0977890123', CAST(0x32360B00 AS Date), 7, 0, N'Dai Hoc', 3, 3900, 530)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN08', N'Doan Thi H', 0, CAST(0xB1100B00 AS Date), N'505 Hoang Van Thu, Quan Tan Binh, TP.HCM', N'890123456789', N'0978901234', CAST(0x5A370B00 AS Date), 8, 1, N'Trung Cap', 2, 3300, 490)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN09', N'Luong Hoang I', 1, CAST(0x80150B00 AS Date), N'606 Nguyen Van Cu, Quan 5, TP.HCM', N'901234567890', N'0979012345', CAST(0x66380B00 AS Date), 9, 0, N'Cao Dang', 1, 3200, 480)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN10', N'Ngoc Trang J', 0, CAST(0xF7180B00 AS Date), N'707 Tran Hung Dao, Quan 1, TP.HCM', N'012345678901', N'0970123456', CAST(0x94390B00 AS Date), 10, 1, N'Trung Cap', 3, 3700, 510)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN11', N'Tran Van K', 1, CAST(0x7B050B00 AS Date), N'808 Le Duc Tho, Quan Go Vap, TP.HCM', N'123450987654', N'0971234509', CAST(0xF03B0B00 AS Date), 1, 1, N'Dai Hoc', 4, 4200, 580)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN12', N'Le Thi L', 0, CAST(0x4A0A0B00 AS Date), N'909 Pham Ngu Lao, Quan 1, TP.HCM', N'234509876543', N'0972345098', CAST(0xFD3C0B00 AS Date), 2, 0, N'Cao Dang', 1, 3100, 460)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN13', N'Pham Van M', 1, CAST(0xBF0D0B00 AS Date), N'010 Ly Tu Trong, Quan 1, TP.HCM', N'345098765432', N'0973450987', CAST(0x0A3E0B00 AS Date), 3, 1, N'Trung Cap', 2, 3500, 500)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN14', N'Hoang Thi N', 0, CAST(0x8E120B00 AS Date), N'121 Nguyen Cong Tru, Quan 1, TP.HCM', N'456987654321', N'0974569876', CAST(0x363F0B00 AS Date), 4, 0, N'Dai Hoc', 3, 4000, 550)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN15', N'Vu Van O', 1, CAST(0x5D170B00 AS Date), N'212 Le Quoc Hung, Quan 4, TP.HCM', N'567876543210', N'0975678765', CAST(0xCF410B00 AS Date), 5, 1, N'Cao Dang', 1, 3000, 450)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN16', N'Bui Thi P', 0, CAST(0xDB1A0B00 AS Date), N'323 Le Van Sy, Quan 3, TP.HCM', N'678765432109', N'0976787654', CAST(0xFA420B00 AS Date), 6, 1, N'Trung Cap', 4, 3800, 520)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN17', N'Do Van Q', 1, CAST(0x22030B00 AS Date), N'434 Phan Van Tri, Quan Go Vap, TP.HCM', N'789654321098', N'0977896543', CAST(0x25440B00 AS Date), 7, 0, N'Dai Hoc', 3, 3900, 530)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN18', N'Nguyen Thi R', 0, CAST(0x82060B00 AS Date), N'545 Huynh Van Banh, Quan Phu Nhuan, TP.HCM', N'890543219876', N'0978905432', CAST(0x51450B00 AS Date), 8, 1, N'Trung Cap', 2, 3300, 490)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN19', N'Tran Van S', 1, CAST(0x520B0B00 AS Date), N'656 Vo Van Kiet, Quan 5, TP.HCM', N'901234567890', N'0979012345', CAST(0x7D460B00 AS Date), 9, 0, N'Cao Dang', 1, 3200, 480)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN20', N'Le Thi T', 0, CAST(0xB40E0B00 AS Date), N'767 Nguyen Van Linh, Quan 7, TP.HCM', N'012345678901', N'0970123456', CAST(0xD9480B00 AS Date), 10, 1, N'Trung Cap', 3, 3700, 510)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN21', N'Pham Van U', 1, CAST(0x82130B00 AS Date), N'878 Le Van Luong, Quan 7, TP.HCM', N'123450987654', N'0971234509', CAST(0xC8490B00 AS Date), 1, 1, N'Dai Hoc', 4, 4200, 580)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN22', N'Hoang Thi V', 0, CAST(0x02170B00 AS Date), N'989 Nguyen Thi Thap, Quan 7, TP.HCM', N'234509876543', N'0972345098', CAST(0xD64A0B00 AS Date), 2, 0, N'Cao Dang', 1, 3100, 460)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN23', N'Vu Van W', 1, CAST(0x811A0B00 AS Date), N'100 Truong Van Bang, Quan 2, TP.HCM', N'345098765432', N'0973450987', CAST(0x334D0B00 AS Date), 3, 1, N'Trung Cap', 2, 3500, 500)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN24', N'Bui Thi X', 0, CAST(0x35040B00 AS Date), N'200 Nguyen Binh Khiem, Quan 1, TP.HCM', N'456987654321', N'0974569876', CAST(0x5F4E0B00 AS Date), 4, 0, N'Dai Hoc', 3, 4000, 550)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN25', N'Do Van Y', 1, CAST(0xB5070B00 AS Date), N'300 Cao Thang, Quan 3, TP.HCM', N'567876543210', N'0975678765', CAST(0x6C4F0B00 AS Date), 5, 1, N'Cao Dang', 1, 3000, 450)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN26', N'Nguyen Van Z', 1, CAST(0x100E0B00 AS Date), N'400 Dien Bien Phu, Quan 10, TP.HCM', N'678765432109', N'0976787654', CAST(0x5D500B00 AS Date), 6, 1, N'Trung Cap', 4, 3800, 520)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN27', N'Tran Thi AA', 0, CAST(0x90110B00 AS Date), N'500 Le Van Si, Quan 3, TP.HCM', N'789654321098', N'0977896543', CAST(0xB9520B00 AS Date), 7, 0, N'Dai Hoc', 3, 3900, 530)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN28', N'Le Van BB', 1, CAST(0xCB170B00 AS Date), N'600 Vo Thi Sau, Quan 3, TP.HCM', N'890543219876', N'0978905432', CAST(0xE4530B00 AS Date), 8, 1, N'Trung Cap', 2, 3300, 490)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN29', N'Pham Thi CC', 0, CAST(0xB91C0B00 AS Date), N'700 Truong Chinh, Quan Tan Binh, TP.HCM', N'012345678901', N'0970123456', CAST(0xD3540B00 AS Date), 9, 0, N'Cao Dang', 1, 3200, 480)
GO
INSERT [dbo].[CongNhanSanXuat] ([maCN], [hoTenCN], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maToSX], [trangThai], [bangCap], [tayNghe], [luongSanPham], [phuCap]) VALUES (N'CN30', N'Hoang Van DD', 1, CAST(0x00050B00 AS Date), N'800 Pham Van Dong, Quan Go Vap, TP.HCM', N'123450987654', N'0971234509', CAST(0x2E570B00 AS Date), 10, 1, N'Trung Cap', 3, 3700, 510)
GO
INSERT [dbo].[PhongBan] ([maPhongBan], [tenPhongBan], [soLuongNV], [moTa]) VALUES (N'PB01', N'Hành chính', 300, N'Không')
GO
INSERT [dbo].[PhongBan] ([maPhongBan], [tenPhongBan], [soLuongNV], [moTa]) VALUES (N'PB02', N'Kế toán', 200, N'Không')
GO
INSERT [dbo].[PhongBan] ([maPhongBan], [tenPhongBan], [soLuongNV], [moTa]) VALUES (N'PB03', N'Nhân sự', 400, N'Không')
GO
INSERT [dbo].[PhongBan] ([maPhongBan], [tenPhongBan], [soLuongNV], [moTa]) VALUES (N'PB04', N'Kinh doanh', 500, N'Không')
GO
INSERT [dbo].[PhongBan] ([maPhongBan], [tenPhongBan], [soLuongNV], [moTa]) VALUES (N'PB05', N'Công nghệ thông tin', 300, N'Không')
GO
INSERT [dbo].[TaiKhoan] ([tenTK], [matKhau]) VALUES (N'admin', N'1234')
GO
INSERT [dbo].[TaiKhoan] ([tenTK], [matKhau]) VALUES (N'anlevan', N'P@ssw0rd2')
GO
INSERT [dbo].[TaiKhoan] ([tenTK], [matKhau]) VALUES (N'ducle', N'P@ssw0rd8')
GO
INSERT [dbo].[TaiKhoan] ([tenTK], [matKhau]) VALUES (N'hiennhuyen', N'P@ssw0rd7')
GO
INSERT [dbo].[TaiKhoan] ([tenTK], [matKhau]) VALUES (N'hoangtruong', N'P@ssw0rd6')
GO
INSERT [dbo].[TaiKhoan] ([tenTK], [matKhau]) VALUES (N'maipham', N'P@ssw0rd4')
GO
INSERT [dbo].[TaiKhoan] ([tenTK], [matKhau]) VALUES (N'minhtran', N'P@ssw0rd1')
GO
INSERT [dbo].[TaiKhoan] ([tenTK], [matKhau]) VALUES (N'phuctran', N'P@ssw0rd10')
GO
INSERT [dbo].[TaiKhoan] ([tenTK], [matKhau]) VALUES (N'qlnv', N'1234')
GO
INSERT [dbo].[TaiKhoan] ([tenTK], [matKhau]) VALUES (N'tamvo', N'P@ssw0rd5')
GO
INSERT [dbo].[TaiKhoan] ([tenTK], [matKhau]) VALUES (N'tudo', N'P@ssw0rd9')
GO
INSERT [dbo].[TaiKhoan] ([tenTK], [matKhau]) VALUES (N'tungnguyen', N'P@ssw0rd3')
GO
INSERT [dbo].[NhanVienHanhChinh] ([maNV], [hoTenNV], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maPhongBan], [trangThai], [bangCap], [luongCoBan], [phuCap], [heSoLuong], [tenTK], [email]) VALUES (N'NV01', N'Trần Thị Minh', 0, CAST(0x8C070B00 AS Date), N'123 Lê Lợi, Quận 1, TP.HCM', N'123456789012', N'0971234567', CAST(0x3B450B00 AS Date), N'PB01', 1, N'Cử nhân', 5000000, 1000000, 2, N'minhtran', N'minhtran@example.com')
GO
INSERT [dbo].[NhanVienHanhChinh] ([maNV], [hoTenNV], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maPhongBan], [trangThai], [bangCap], [luongCoBan], [phuCap], [heSoLuong], [tenTK], [email]) VALUES (N'NV02', N'Lê Văn An', 1, CAST(0xE60A0B00 AS Date), N'456 Nguyễn Huệ, Quận 3, TP.HCM', N'234567890123', N'0972345678', CAST(0x12450B00 AS Date), N'PB02', 0, N'Thạc sĩ', 6000000, 1200000, 2.5, N'anlevan', N'anlevan@example.com')
GO
INSERT [dbo].[NhanVienHanhChinh] ([maNV], [hoTenNV], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maPhongBan], [trangThai], [bangCap], [luongCoBan], [phuCap], [heSoLuong], [tenTK], [email]) VALUES (N'NV03', N'Nguyễn Thanh Tùng', 1, CAST(0x6B0E0B00 AS Date), N'789 Võ Văn Tân, Quận 10, TP.HCM', N'345678901234', N'0973456789', CAST(0xA0450B00 AS Date), N'PB03', 1, N'Tiến sĩ', 7000000, 1500000, 3, N'tungnguyen', N'tungnguyen@example.com')
GO
INSERT [dbo].[NhanVienHanhChinh] ([maNV], [hoTenNV], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maPhongBan], [trangThai], [bangCap], [luongCoBan], [phuCap], [heSoLuong], [tenTK], [email]) VALUES (N'NV04', N'Phạm Hoàng Mai', 0, CAST(0x59100B00 AS Date), N'101 Nguyễn Thị Minh Khai, Quận 1, TP.HCM', N'456789012345', N'0974567890', CAST(0xDC440B00 AS Date), N'PB04', 1, N'Cử nhân', 8000000, 1800000, 3.5, N'maipham', N'maipham@example.com')
GO
INSERT [dbo].[NhanVienHanhChinh] ([maNV], [hoTenNV], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maPhongBan], [trangThai], [bangCap], [luongCoBan], [phuCap], [heSoLuong], [tenTK], [email]) VALUES (N'NV05', N'Võ Thanh Tâm', 1, CAST(0xA0130B00 AS Date), N'202 Lê Văn Sỹ, Quận Tân Bình, TP.HCM', N'567890123456', N'0975678901', CAST(0xBA450B00 AS Date), N'PB05', 0, N'Thạc sĩ', 9000000, 2000000, 4, N'tamvo', N'tamvo@example.com')
GO
INSERT [dbo].[NhanVienHanhChinh] ([maNV], [hoTenNV], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maPhongBan], [trangThai], [bangCap], [luongCoBan], [phuCap], [heSoLuong], [tenTK], [email]) VALUES (N'NV06', N'Trương Minh Hoàng', 0, CAST(0xC1160B00 AS Date), N'303 Nguyễn Văn Trỗi, Quận Phú Nhuận, TP.HCM', N'678901234567', N'0976789012', CAST(0x2B450B00 AS Date), N'PB01', 1, N'Cử nhân', 10000000, 2200000, 4.5, N'hoangtruong', N'hoangtruong@example.com')
GO
INSERT [dbo].[NhanVienHanhChinh] ([maNV], [hoTenNV], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maPhongBan], [trangThai], [bangCap], [luongCoBan], [phuCap], [heSoLuong], [tenTK], [email]) VALUES (N'NV07', N'Nguyễn Thị Hiền', 1, CAST(0x071A0B00 AS Date), N'404 Võ Văn Kiệt, Quận 4, TP.HCM', N'789012345678', N'0977890123', CAST(0x76450B00 AS Date), N'PB02', 0, N'Tiến sĩ', 11000000, 2400000, 5, N'hiennhuyen', N'hiennhuyen@example.com')
GO
INSERT [dbo].[NhanVienHanhChinh] ([maNV], [hoTenNV], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maPhongBan], [trangThai], [bangCap], [luongCoBan], [phuCap], [heSoLuong], [tenTK], [email]) VALUES (N'NV08', N'Lê Văn Đức', 0, CAST(0xD21B0B00 AS Date), N'505 Trần Hưng Đạo, Quận 5, TP.HCM', N'890123456789', N'0978901234', CAST(0x5B450B00 AS Date), N'PB03', 1, N'Thạc sĩ', 12000000, 2600000, 5.5, N'ducle', N'ducle@example.com')
GO
INSERT [dbo].[NhanVienHanhChinh] ([maNV], [hoTenNV], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maPhongBan], [trangThai], [bangCap], [luongCoBan], [phuCap], [heSoLuong], [tenTK], [email]) VALUES (N'NV09', N'Đỗ Minh Tú', 1, CAST(0x2F1F0B00 AS Date), N'606 Nguyễn Đình Chiểu, Quận 3, TP.HCM', N'901234567890', N'0979012345', CAST(0x08450B00 AS Date), N'PB04', 0, N'Cử nhân', 13000000, 2800000, 6, N'tudo', N'tudo@example.com')
GO
INSERT [dbo].[NhanVienHanhChinh] ([maNV], [hoTenNV], [gioiTinh], [ngaySinh], [diaChi], [CCCD], [SDT], [ngayVao], [maPhongBan], [trangThai], [bangCap], [luongCoBan], [phuCap], [heSoLuong], [tenTK], [email]) VALUES (N'NV10', N'Trần Văn Phúc', 0, CAST(0x58220B00 AS Date), N'707 Cách Mạng Tháng 8, Quận 10, TP.HCM', N'012345678901', N'0970123456', CAST(0x8D450B00 AS Date), N'PB05', 1, N'Tiến sĩ', 14000000, 3000000, 6.5, N'phuctran', N'phuctran@example.com')
GO
INSERT INTO [dbo].[LuongNhanVienHanhChinh] ([maBangLuongHC], [ngayTinhLuong], [nam], [thang], [soNgayDiLam], [soNgayNghi], [soNghiPhep], [luongChinh], [tienPhuCapTrongThang], [tienTamUng], [baoHiemXaHoi], [baoHiemYTe], [baoHiemThatNghiep], [thueTNCN], [luongThucLanh], [maNV])
VALUES
('LHCNV01', '2023-12-01', 2023, 12, 20, 2, 0, 15000000.00, 1000000.00, 500000.00, 500000.00, 300000.00, 200000.00, 1500000.00, 14500000.00, 'NV01'),
('LHCNV02', '2023-12-01', 2023, 12, 22, 1, 0, 16000000.00, 1200000.00, 600000.00, 600000.00, 350000.00, 220000.00, 1600000.00, 15500000.00, 'NV02'),
('LHCNV03', '2023-12-01', 2023, 12, 18, 3, 1, 14000000.00, 900000.00, 480000.00, 480000.00, 280000.00, 180000.00, 1300000.00, 12500000.00, 'NV03'),
('LHCNV04', '2023-12-01', 2023, 12, 21, 2, 0, 15500000.00, 1100000.00, 520000.00, 520000.00, 320000.00, 210000.00, 1550000.00, 15050000.00, 'NV04'),
('LHCNV05', '2023-12-01', 2023, 12, 19, 2, 1, 14500000.00, 950000.00, 500000.00, 500000.00, 290000.00, 190000.00, 1400000.00, 13500000.00, 'NV05'),
('LHCNV06', '2023-12-01', 2023, 12, 23, 0, 0, 17000000.00, 1300000.00, 620000.00, 620000.00, 380000.00, 250000.00, 1800000.00, 17500000.00, 'NV06'),
('LHCNV07', '2023-12-01', 2023, 12, 20, 3, 0, 16000000.00, 1150000.00, 550000.00, 550000.00, 340000.00, 230000.00, 1650000.00, 16000000.00, 'NV07'),
('LHCNV08', '2023-12-01', 2023, 12, 24, 1, 1, 18000000.00, 1400000.00, 700000.00, 700000.00, 420000.00, 280000.00, 2000000.00, 19500000.00, 'NV08'),
('LHCNV09', '2023-12-01', 2023, 12, 18, 4, 0, 14000000.00, 900000.00, 480000.00, 480000.00, 280000.00, 180000.00, 1300000.00, 12500000.00, 'NV09'),
('LHCNV10', '2023-12-01', 2023, 12, 22, 1, 0, 16500000.00, 1250000.00, 600000.00, 600000.00, 360000.00, 240000.00, 1750000.00, 17000000.00, 'NV10');
GO
INSERT INTO LuongCongNhanSanXuat (maBangLuongCN, ngayTinhLuong, nam, thang, soLuongSanPhamLam, soNgayDiLam, soNgayNghi, soNghiPhep, luongTheoThang, phuCapTrongThang, tienTamUng, baoHiemXaHoi, baoHiemYTe, baoHiemThatNghiep, thueTNCN, luongThucLanh, maCN)
VALUES
('LSPCN01', '2023-12-01', 2023, 12, 100, 25, 5, 2, 5000000, 1000000, 500000, 1000000, 500000, 200000, 500000, 4500000, 'CN01'),
('LSPCN02', '2023-12-01', 2023, 12, 120, 28, 3, 1, 6000000, 1200000, 600000, 1200000, 600000, 240000, 600000, 4800000, 'CN02'),
('LSPCN03', '2023-12-01', 2023, 12, 90, 20, 10, 3, 4500000, 800000, 400000, 900000, 400000, 180000, 400000, 3700000, 'CN03'),
('LSPCN04', '2023-12-01', 2023, 12, 110, 26, 4, 2, 5500000, 1100000, 550000, 1100000, 550000, 220000, 550000, 4950000, 'CN04'),
('LSPCN05', '2023-12-01', 2023, 12, 95, 22, 8, 4, 4800000, 900000, 480000, 900000, 480000, 200000, 480000, 4320000, 'CN05'),
('LSPCN06', '2023-12-01', 2023, 12, 105, 24, 6, 3, 5200000, 1000000, 520000, 1000000, 520000, 210000, 520000, 4680000, 'CN06'),
('LSPCN07', '2023-12-01', 2023, 12, 115, 27, 3, 1, 5800000, 1100000, 580000, 1100000, 580000, 240000, 580000, 5220000, 'CN07'),
('LSPCN08', '2023-12-01', 2023, 12, 80, 18, 12, 5, 4000000, 700000, 350000, 800000, 350000, 160000, 350000, 3150000, 'CN08'),
('LSPCN09', '2023-12-01', 2023, 12, 125, 30, 0, 0, 6250000, 1300000, 625000, 1300000, 625000, 250000, 625000, 5625000, 'CN09'),
('LSPCN10', '2023-12-01', 2023, 12, 100, 23, 7, 3, 5000000, 950000, 500000, 950000, 500000, 200000, 500000, 4505000, 'CN10');
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV01', CAST(0x08460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV01', CAST(0x09460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV01', CAST(0x0A460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV01', CAST(0x0B460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV01', CAST(0x0C460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV01', CAST(0x0D460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV02', CAST(0x08460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV02', CAST(0x09460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV02', CAST(0x0A460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV02', CAST(0x0B460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV02', CAST(0x0C460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV02', CAST(0x0D460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV03', CAST(0x08460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV03', CAST(0x09460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV03', CAST(0x0A460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV03', CAST(0x0B460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV03', CAST(0x0C460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV03', CAST(0x0D460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV04', CAST(0x08460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV04', CAST(0x09460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV04', CAST(0x0A460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV04', CAST(0x0B460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV04', CAST(0x0C460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV04', CAST(0x0D460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV05', CAST(0x08460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV05', CAST(0x09460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV05', CAST(0x0A460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV05', CAST(0x0B460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV05', CAST(0x0C460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV05', CAST(0x0D460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV06', CAST(0x08460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV06', CAST(0x09460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV06', CAST(0x0A460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV06', CAST(0x0B460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV06', CAST(0x0C460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV06', CAST(0x0D460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV07', CAST(0x08460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV07', CAST(0x09460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV07', CAST(0x0A460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV07', CAST(0x0B460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV07', CAST(0x0C460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV07', CAST(0x0D460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV08', CAST(0x08460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV08', CAST(0x09460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV08', CAST(0x0A460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV08', CAST(0x0B460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV08', CAST(0x0C460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV08', CAST(0x0D460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV09', CAST(0x08460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV09', CAST(0x09460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV09', CAST(0x0A460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV09', CAST(0x0B460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV09', CAST(0x0C460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV09', CAST(0x0D460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV10', CAST(0x08460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV10', CAST(0x09460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV10', CAST(0x0A460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV10', CAST(0x0B460B00 AS Date), N'P')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV10', CAST(0x0C460B00 AS Date), N'K')
GO
INSERT [dbo].[DiemDanh] ([maNV], [ngayNghi], [trangThai]) VALUES (N'NV10', CAST(0x0D460B00 AS Date), N'P')
GO
