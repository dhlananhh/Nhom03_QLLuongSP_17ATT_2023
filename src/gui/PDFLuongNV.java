package gui;


import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.itextpdf.text.pdf.PdfWriter;

import dao.DAO_LuongNhanVienHanhChinh;
import dao.DAO_NhanVienHanhChinh;
import entity.LuongNhanVienHanhChinh;
import entity.NhanVienHanhChinh;

public class PDFLuongNV {
	private static final Font GLOBAL_FONT = getNormalFont();
	private static final Font BOLD_FONT = getBoldFont();
	private static final Font BOLD_ITALIC_FONT = getBoldItalicFont();
	
	private static final DAO_NhanVienHanhChinh dao_nv = new DAO_NhanVienHanhChinh();
	private static final DAO_LuongNhanVienHanhChinh dao_luongNV = new DAO_LuongNhanVienHanhChinh();
	
	private static List<LuongNhanVienHanhChinh> dsLuongNV = new ArrayList<LuongNhanVienHanhChinh>();
	private static double luongThucLanh = 0;
	
	
	public static String convertMoney(double gia) {
		return NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(gia);
	}
	
	
	private static Font getBoldFont() {
		try {
            BaseFont baseFont = BaseFont.createFont("fonts/BeVietnamPro-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            return new Font(baseFont, 13, Font.BOLD, BaseColor.BLACK);
        } catch (Exception e) {
            e.printStackTrace();
            return FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD, BaseColor.BLACK);
        }
	}
	
	
	private static Font getBoldItalicFont() {
		try {
            BaseFont baseFont = BaseFont.createFont("fonts/BeVietnamPro-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            return new Font(baseFont, 13, Font.BOLDITALIC, BaseColor.BLACK);
        } catch (Exception e) {
            e.printStackTrace();
            return FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLDITALIC, BaseColor.BLACK);
        }
	}
	
	
	private static Font getNormalFont() {
		
		try {
            BaseFont baseFont = BaseFont.createFont("fonts/BeVietnamPro-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            return new Font(baseFont, 12, Font.NORMAL, BaseColor.BLACK);
        } catch (Exception e) {
            e.printStackTrace();
            return FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
        }
	}
	
	
	private static void addTopPanel (Document document, LuongNhanVienHanhChinh luongNV) throws DocumentException {
	    PdfPTable topTable = new PdfPTable(2);
	    topTable.setWidthPercentage(100);
	    
	    PdfPCell leftCell = new PdfPCell();
	    leftCell.addElement(new Paragraph("Nội thất Hoàng Gia Phát\n", BOLD_FONT));
	    leftCell.addElement(new Paragraph("12 Nguyễn Văn Bảo, Quận Gò Vấp, Thành phố Hồ Chí Minh \n", GLOBAL_FONT));
	    leftCell.setBorder(Rectangle.NO_BORDER);
	    topTable.addCell(leftCell);

	    PdfPCell rightCell = new PdfPCell();
	    rightCell.addElement(new Paragraph("Ngày tính lương\n", BOLD_ITALIC_FONT));
	    rightCell.addElement(new Paragraph(luongNV.getNgayTinhLuong().toString()));
	    rightCell.setBorder(Rectangle.NO_BORDER);
	    topTable.addCell(rightCell);

	    document.add(topTable);
	    document.add(new Paragraph("\n"));
	}
	
	
	private static void addMiddlePanel (Document document, LuongNhanVienHanhChinh luongNV, 
			List<LuongNhanVienHanhChinh> dsLuongNV) throws DocumentException {
		
		
		Paragraph title = new Paragraph("PHIẾU LƯƠNG NHÂN VIÊN", BOLD_FONT);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph maPhieuLuong = new Paragraph("Mã phiếu lương: " + luongNV.getMaBangLuongHC(), GLOBAL_FONT);
        maPhieuLuong.setAlignment(Element.ALIGN_LEFT);
        document.add(maPhieuLuong);
        
        Paragraph maNhanVien = new Paragraph("Mã nhân viên: " + luongNV.getNhanVien().getMaNV(), GLOBAL_FONT);
        maNhanVien.setAlignment(Element.ALIGN_LEFT);
        document.add(maNhanVien);

        Paragraph hoTenNhanVien = new Paragraph("Họ tên nhân viên: " + dao_nv.layNhanVienTheoMa(luongNV.getNhanVien().getMaNV()).getHoTenNV(), GLOBAL_FONT);
        hoTenNhanVien.setAlignment(Element.ALIGN_LEFT);
        document.add(hoTenNhanVien);
        
        Paragraph heSoLuong = new Paragraph("Hệ số lương: " + dao_nv.layHeSoLuong(luongNV.getNhanVien().getMaNV()), GLOBAL_FONT);
        heSoLuong.setAlignment(Element.ALIGN_LEFT);
        document.add(heSoLuong);
        
        Paragraph tongTienPhuCap = new Paragraph("Tổng số tiền phụ cấp trong tháng: " + convertMoney(dao_nv.layTienPhuCap(luongNV.getNhanVien().getMaNV())), GLOBAL_FONT);
        tongTienPhuCap.setAlignment(Element.ALIGN_LEFT);
        document.add(tongTienPhuCap);
        
        Paragraph tongSoNgayTinhLuong = new Paragraph("Tổng số ngày tính lương: 26", GLOBAL_FONT);
        tongSoNgayTinhLuong.setAlignment(Element.ALIGN_LEFT);
        document.add(tongSoNgayTinhLuong);
        
        Paragraph tongSoNgayDiLam = new Paragraph("Tổng số ngày đi làm thực tế: " + dao_luongNV.laySoNgayDiLam(luongNV.getNhanVien().getMaNV(), luongNV.getNam(), luongNV.getThang()), GLOBAL_FONT);
        tongSoNgayDiLam.setAlignment(Element.ALIGN_LEFT);
        document.add(tongSoNgayDiLam);
        
        Paragraph tongSoNgayNghi = new Paragraph("Tổng số ngày nghỉ: " + dao_luongNV.laySoNgayNghi(luongNV.getNhanVien().getMaNV(), luongNV.getNam(), luongNV.getThang()), GLOBAL_FONT);
        tongSoNgayNghi.setAlignment(Element.ALIGN_LEFT);
        document.add(tongSoNgayNghi);
        
        document.add(new Paragraph("\n"));

        PdfPTable tblLuongNV = new PdfPTable(3);
        tblLuongNV.setWidthPercentage(100);
        
        PdfPTable tblGiamTru = new PdfPTable(3);
        tblGiamTru.setWidthPercentage(100);

        addTblLuongHeader(tblLuongNV);
        addTblGiamTruHeader(tblGiamTru);
        
        int i = 1;
        for (LuongNhanVienHanhChinh luongNhanVien : dsLuongNV) {
        	addTableRow(tblLuongNV, i++, 
        			"Lương chính", 
        			convertMoney(luongNhanVien.getLuongChinh()),
        			GLOBAL_FONT
        	);
        }
        
        int j = 1;
        for (LuongNhanVienHanhChinh luongNhanVien : dsLuongNV) {
        	addTableRow(tblGiamTru, j++, 
        			"Bảo hiểm xã hội (8%)",
        			convertMoney(luongNhanVien.getBaoHiemXaHoi()),
        			GLOBAL_FONT
        	);
        	addTableRow(tblGiamTru, j++, 
        			"Bảo hiểm y tế (1.5%)", convertMoney(luongNhanVien.getBaoHiemYTe()), 
        			GLOBAL_FONT
        	);
        	addTableRow(tblGiamTru, j++, 
        			"Bảo hiểm thất nghiệp (1%)", convertMoney(luongNhanVien.getBaoHiemThatNghiep()), 
        			GLOBAL_FONT
        	);
        	addTableRow(tblGiamTru, j++, 
        			"Thuế TNCN", convertMoney(luongNhanVien.getThueTNCN()), 
        			GLOBAL_FONT
        	);
        	addTableRow(tblGiamTru, j++, 
        			"Tiền tạm ứng", convertMoney(luongNhanVien.getTienTamUng()), 
        			GLOBAL_FONT
        	);
        }
        

        document.add(tblLuongNV);
        document.add(new Paragraph("\n"));
        document.add(tblGiamTru);
        document.add(new Paragraph("\n"));
	}
	
	
	private static void addBottomPanel (Document document, LuongNhanVienHanhChinh luongNV) throws DocumentException {
		Paragraph luongThucLanh = new Paragraph("Lương thực lãnh nhận được: " + convertMoney(luongNV.getLuongThucLanh()), BOLD_ITALIC_FONT);
		luongThucLanh.setAlignment(Element.ALIGN_LEFT);
		document.add(luongThucLanh);
    }
	
	
	private static void addTblLuongHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Paragraph("STT", BOLD_FONT));
        table.addCell(cell);
        
        cell.setPhrase(new Paragraph("Khoản thu nhập", BOLD_FONT));
        table.addCell(cell);

        cell.setPhrase(new Paragraph("Số tiền", BOLD_FONT));
        table.addCell(cell);
    }
	
	
	private static void addTblGiamTruHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Paragraph("STT", BOLD_FONT));
        table.addCell(cell);
		
        cell.setPhrase(new Paragraph("Các khoản tiền giảm trừ", BOLD_FONT));
        table.addCell(cell);

        cell.setPhrase(new Paragraph("Số tiền", BOLD_FONT));
        table.addCell(cell);
    }
	
	
    private static PdfPCell createCell(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));

        return cell;
    }
    
    
    public static void addTableRow (PdfPTable table, int stt, String deMuc, String soTien, Font globalFont) {
    	table.addCell(createCell(String.valueOf(stt), GLOBAL_FONT));
    	table.addCell(createCell(deMuc, GLOBAL_FONT));
    	table.addCell(createCell(soTien, GLOBAL_FONT));
    }
    
    
    public static void InPhieuLuongNV (String maNV) {
    	
        String outputPdfPath = "data/exportedPDF/" + maNV + ".pdf";
        DAO_LuongNhanVienHanhChinh dao_luongNV = new DAO_LuongNhanVienHanhChinh();
        DAO_NhanVienHanhChinh dao_nv = new DAO_NhanVienHanhChinh();
        List<LuongNhanVienHanhChinh> dsLuongNV = new ArrayList<LuongNhanVienHanhChinh>();
        dsLuongNV = dao_luongNV.timLuongTheoMaNV(maNV);
        LuongNhanVienHanhChinh luongNhanVien = dao_luongNV.timLuongNVTheoMaNV(maNV);

        try {
        	Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(new File(outputPdfPath)));

            document.open();
            addTopPanel(document, luongNhanVien);
            addMiddlePanel(document, luongNhanVien, dsLuongNV);
            addBottomPanel(document, luongNhanVien);
            document.close();

            JOptionPane.showMessageDialog(null, "Tạo và lưu tệp PDF thành công: " + outputPdfPath);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tạo và lưu tệp PDF!");
        }
    	
    }
}
