package com.covid.vaccination.bookingslots.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.covid.vaccination.bookingslots.model.Booking;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PDFGeneratorService {
	public void export(HttpServletResponse response,Booking booking) throws DocumentException, IOException {
		Document document=new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		Font fontTitle=FontFactory.getFont(FontFactory.COURIER_BOLD);
		fontTitle.setSize(18);
		Paragraph paragraph = new Paragraph("Covid Vaccine Certificate",fontTitle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		Font fontBody=FontFactory.getFont(FontFactory.COURIER);
		String content="Certificate holder : "+booking.getUser().getName()+"\nGot vaccinated on "+booking.getSlot().getDate()+"\nCentre address : "+booking.getSlot().getCentre().getAddressLine1()+","+
		booking.getSlot().getCentre().getAddressLine2()+","+booking.getSlot().getCentre().getPin()+"\n";
		Paragraph bodyPara=new Paragraph(content,fontBody);
		bodyPara.setAlignment(Paragraph.ALIGN_LEFT);
		document.add(paragraph);
		document.add(bodyPara);
		document.close();
	}
}
