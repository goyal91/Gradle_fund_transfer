package com.innoviti.retail.fundTransfer.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MailUtils {

	public static void sendZipEmailWithAttachment(String to,String cc, String userName, String password, String subject,
			String body, String attachmentFileName, XSSFWorkbook hssWorkBook) {

		// 1) get the session object
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName));
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
			message.setSubject(subject);
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(body);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			if (hssWorkBook.getNumberOfSheets() > 0) {
				try {
					messageBodyPart = new MimeBodyPart();
					File temp = File.createTempFile(attachmentFileName, ".xlsx");
					String absolutePath = temp.getAbsolutePath(); // return full path
					FileOutputStream fop = new FileOutputStream(absolutePath);
					hssWorkBook.write(fop);
					fop.close();
					String zipFileName = attachmentFileName + ".zip";
					FileOutputStream fos = new FileOutputStream(zipFileName);
					ZipOutputStream zos = new ZipOutputStream(fos);
					zos.putNextEntry(new ZipEntry(temp.getName()));
					byte[] bytes = Files.readAllBytes(Paths.get(absolutePath));
					zos.write(bytes, 0, bytes.length);
					zos.closeEntry();
					zos.close();
					temp.delete();
					DataSource source = new FileDataSource(new File(attachmentFileName + ".zip"));
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(attachmentFileName + ".zip");
					multipart.addBodyPart(messageBodyPart);
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
			message.setContent(multipart);
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
