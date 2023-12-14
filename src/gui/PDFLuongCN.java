package gui;

import java.io.File;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.DAO_CongNhan;
import dao.DAO_LuongCongNhanSanXuat;
import dao.DAO_LuongNhanVienHanhChinh;
import dao.DAO_NhanVienHanhChinh;
import entity.LuongCongNhanSanXuat;
import entity.LuongNhanVienHanhChinh;

public class PDFLuongCN {
	private static final Font GLOBAL_FONT = getNormalFont();
	private static final Font BOLD_FONT = getBoldFont();
	private static final Font BOLD_ITALIC_FONT = getBoldItalicFont();
	
	private static final DAO_CongNhan dao_cn = new DAO_CongNhan();
	private static final DAO_LuongCongNhanSanXuat dao_luongCN = new DAO_LuongCongNhanSanXuat();
	
	private static List<LuongCongNhanSanXuat> dsLuongCN = new ArrayList<LuongCongNhanSanXuat>();
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
	
	
	private static void addTopPanel (Document document, LuongCongNhanSanXuat luongCN) throws DocumentException {
	    PdfPTable topTable = new PdfPTable(2);
	    topTable.setWidthPercentage(100);
	    
	    PdfPCell leftCell = new PdfPCell();
	    leftCell.addElement(new Paragraph("Nội thất Hoàng Gia Phát\n", BOLD_FONT));
	    leftCell.addElement(new Paragraph("12 Nguyễn Văn Bảo, Quận Gò Vấp, Thành phố Hồ Chí Minh \n", GLOBAL_FONT));
	    leftCell.setBorder(Rectangle.NO_BORDER);
	    topTable.addCell(leftCell);

	    PdfPCell rightCell = new PdfPCell();
	    rightCell.addElement(new Paragraph("Ngày tính lương\n", BOLD_ITALIC_FONT));
	    rightCell.addElement(new Paragraph(luongCN.getNgayTinhLuong().toString()));
	    rightCell.setBorder(Rectangle.NO_BORDER);
	    topTable.addCell(rightCell);

	    document.add(topTable);
	    document.add(new Paragraph("\n"));
	}
	
	
	private static void addMiddlePanel (Document document, LuongCongNhanSanXuat luongCN, 
			List<LuongCongNhanSanXuat> dsLuongCN) throws DocumentException {
		
		
		Paragraph title = new Paragraph("PHIẾU LƯƠNG CÔNG NHÂN", BOLD_FONT);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph maPhieuLuong = new Paragraph("Mã phiếu lương: " + luongCN.getMaBangLuongCN(), GLOBAL_FONT);
        maPhieuLuong.setAlignment(Element.ALIGN_LEFT);
        document.add(maPhieuLuong);
        
        Paragraph maCongNhan = new Paragraph("Mã công nhân: " + luongCN.getCongNhan().getMaCN(), GLOBAL_FONT);
        maCongNhan.setAlignment(Element.ALIGN_LEFT);
        document.add(maCongNhan);

        Paragraph hoTenCongNhan = new Paragraph("Họ tên công nhân: " + dao_cn.getCongNhanTheoMa(luongCN.getCongNhan().getMaCN()).getHoTenCN(), GLOBAL_FONT);
        hoTenCongNhan.setAlignment(Element.ALIGN_LEFT);
        document.add(hoTenCongNhan);
        
        Paragraph tayNghe = new Paragraph("Tay nghề: " + dao_cn.layTayNghe(luongCN.getCongNhan().getMaCN()), GLOBAL_FONT);
        tayNghe.setAlignment(Element.ALIGN_LEFT);
        document.add(tayNghe);
        
        Paragraph tongTienPhuCap = new Paragraph("Tổng số tiền phụ cấp trong tháng: " + convertMoney(dao_cn.layTienPhuCap(luongCN.getCongNhan().getMaCN())), GLOBAL_FONT);
        tongTienPhuCap.setAlignment(Element.ALIGN_LEFT);
        document.add(tongTienPhuCap);
        
//        Paragraph tongSoNgayTinhLuong = new Paragraph("Tổng số ngày tính lương: 26", GLOBAL_FONT);
//        tongSoNgayTinhLuong.setAlignment(Element.ALIGN_LEFT);
//        document.add(tongSoNgayTinhLuong);
//        
//        Paragraph tongSoNgayDiLam = new Paragraph("Tổng số ngày đi làm thực tế: " + dao_luongNV.laySoNgayDiLam(luongNV.getNhanVien().getMaNV(), luongNV.getNam(), luongNV.getThang()), GLOBAL_FONT);
//        tongSoNgayDiLam.setAlignment(Element.ALIGN_LEFT);
//        document.add(tongSoNgayDiLam);
//        
//        Paragraph tongSoNgayNghi = new Paragraph("Tổng số ngày nghỉ: " + dao_luongNV.laySoNgayNghi(luongNV.getNhanVien().getMaNV(), luongNV.getNam(), luongNV.getThang()), GLOBAL_FONT);
//        tongSoNgayNghi.setAlignment(Element.ALIGN_LEFT);
//        document.add(tongSoNgayNghi);
        
        document.add(new Paragraph("\n"));

        PdfPTable tblLuongCN = new PdfPTable(3);
        tblLuongCN.setWidthPercentage(100);
        
        PdfPTable tblGiamTru = new PdfPTable(3);
        tblGiamTru.setWidthPercentage(100);

        addTblLuongHeader(tblLuongCN);
        addTblGiamTruHeader(tblGiamTru);
        
        int i = 1;
        for (LuongCongNhanSanXuat luongCongNhan : dsLuongCN) {
        	addTableRow(tblLuongCN, i++, 
        			"Lương sản phẩm", 
        			convertMoney(luongCongNhan.getLuongSanPham()),
        			GLOBAL_FONT
        	);
        }
        
        int j = 1;
        for (LuongCongNhanSanXuat luongCongNhan : dsLuongCN) {
        	addTableRow(tblGiamTru, j++, 
        			"Bảo hiểm xã hội (8%)",
        			convertMoney(luongCongNhan.getBaoHiemXaHoi()),
        			GLOBAL_FONT
        	);
        	addTableRow(tblGiamTru, j++, 
        			"Bảo hiểm y tế (1.5%)", convertMoney(luongCongNhan.getBaoHiemYTe()), 
        			GLOBAL_FONT
        	);
        	addTableRow(tblGiamTru, j++, 
        			"Bảo hiểm thất nghiệp (1%)", convertMoney(luongCongNhan.getBaoHiemThatNghiep()), 
        			GLOBAL_FONT
        	);
        	addTableRow(tblGiamTru, j++, 
        			"Thuế TNCN", convertMoney(luongCongNhan.getThueTNCN()), 
        			GLOBAL_FONT
        	);
        	addTableRow(tblGiamTru, j++, 
        			"Tiền tạm ứng", convertMoney(luongCongNhan.getTamUng()), 
        			GLOBAL_FONT
        	);
        }
        

        document.add(tblLuongCN);
        document.add(new Paragraph("\n"));
        document.add(tblGiamTru);
        document.add(new Paragraph("\n"));
	}
	
	
	private static void addBottomPanel (Document document, LuongCongNhanSanXuat luongCN) throws DocumentException {
		Paragraph luongThucLanh = new Paragraph("Lương thực lãnh nhận được: " + convertMoney(luongCN.getLuongThucLanh()), BOLD_ITALIC_FONT);
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
    
    
    public static void InPhieuLuongCN (String maCN) {
    	String outputPdfPath = "data/exportedPDF/" + maCN + ".pdf";
        DAO_LuongCongNhanSanXuat dao_luongCN = new DAO_LuongCongNhanSanXuat();
        DAO_CongNhan dao_cn = new DAO_CongNhan();
        List<LuongCongNhanSanXuat> dsLuongCN = new ArrayList<LuongCongNhanSanXuat>();
        dsLuongCN = dao_luongCN.timLuongTheoMaCN(maCN);
        LuongCongNhanSanXuat luongCongNhan = dao_luongCN.timLuongCNTheoMaCN(maCN);

        try {
        	Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(new File(outputPdfPath)));

            document.open();
            addTopPanel(document, luongCongNhan);
            addMiddlePanel(document, luongCongNhan, dsLuongCN);
            addBottomPanel(document, luongCongNhan);
            document.close();

            JOptionPane.showMessageDialog(null, "Tạo và lưu tệp PDF thành công: " + outputPdfPath);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tạo và lưu tệp PDF!");
        }
    	
    }
}
