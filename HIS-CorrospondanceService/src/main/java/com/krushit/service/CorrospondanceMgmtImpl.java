package com.krushit.service;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.krushit.entity.CitizenRegistrationEntity;
import com.krushit.entity.CoTriggersEntity;
import com.krushit.entity.DcCaseEntity;
import com.krushit.entity.EligibilityDetailsEntity;
import com.krushit.model.CoSummary;
import com.krushit.repository.ICitizenAppRegistrationRepository;
import com.krushit.repository.ICoTriggerRepository;
import com.krushit.repository.IDcCaseRepository;
import com.krushit.repository.IEligibilityDeterminationRepository;
import com.krushit.utils.EmailUtils;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.mail.internet.MimeMessage;

@Service
public class CorrospondanceMgmtImpl implements ICorrospondanceMgmtService {

	@Autowired
	private ICoTriggerRepository triggerRepo;

	@Autowired
	private IEligibilityDeterminationRepository elgiRepo;

	@Autowired
	private IDcCaseRepository caseRepo;

	@Autowired
	private ICitizenAppRegistrationRepository citizenRepo;

	@Autowired
	private Environment env;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public CoSummary processPendingTriggers() throws Exception {
		CitizenRegistrationEntity citizenEntity = null;
		EligibilityDetailsEntity elgiEntity = null;
		CoSummary summary = new CoSummary();
		int successTriggers = 0;
		int totalTriggers = 0;

		// get all pending applications
		List<CoTriggersEntity> list = triggerRepo.findByTriggerStatus("pending");
		System.out.println("List :: " + list);

		totalTriggers = list.size();
		/*for (CoTriggersEntity entity : list) {
			elgiEntity = elgiRepo.findByCaseNo(entity.getCaseNo());
			System.out.println("Case No :: " + entity.getCaseNo());
			System.out.println("Elg Entity :: " + elgiEntity);
			if (elgiEntity != null) {
				// get appid based on case no
				Optional<DcCaseEntity> opt = caseRepo.findById(entity.getCaseNo());
				if (opt.isPresent()) {
					DcCaseEntity caseEntity = opt.get();
					Integer appId = caseEntity.getAppId();
		
					// get citizen details based on app id
					Optional<CitizenRegistrationEntity> citizenOpt = citizenRepo.findById(appId);
					if (citizenOpt.isPresent()) {
						citizenEntity = citizenOpt.get();
					}
				}
				// generate docs
				generateDocument(elgiEntity, citizenEntity);
				successTriggers++;
			} else {
				// Handle the case where elgiEntity is null
				System.out.println("No eligibility details found for case number: " + entity.getCaseNo());
			}
		}*/

		for (CoTriggersEntity entity : list) {
			processTrigger(summary, entity);
			successTriggers++;
		}

		summary.setTotalTriggers(totalTriggers);
		summary.setSuccessTriggers(successTriggers);
		summary.setPendingTriggers(totalTriggers - successTriggers);
		return summary;
	}

	private CitizenRegistrationEntity processTrigger(CoSummary summary, CoTriggersEntity triggerEntity)
			throws Exception {
		CitizenRegistrationEntity citizenEntity = null;
		EligibilityDetailsEntity elgiEntity = elgiRepo.findByCaseNo(triggerEntity.getCaseNo());
		System.out.println("Case No :: " + triggerEntity.getCaseNo());
		System.out.println("Elg Entity :: " + elgiEntity);
		if (elgiEntity != null) {
			// get appid based on case no
			Optional<DcCaseEntity> opt = caseRepo.findById(triggerEntity.getCaseNo());
			if (opt.isPresent()) {
				DcCaseEntity caseEntity = opt.get();
				Integer appId = caseEntity.getAppId();

				// get citizen details based on app id
				Optional<CitizenRegistrationEntity> citizenOpt = citizenRepo.findById(appId);
				if (citizenOpt.isPresent()) {
					citizenEntity = citizenOpt.get();
				}
			}
			generateDocument(elgiEntity, citizenEntity);
		} else {
			System.out.println("No eligibility details found for case number: " + triggerEntity.getCaseNo());
		}
		return citizenEntity;
	}

	private void generateDocument(EligibilityDetailsEntity eliEntity, CitizenRegistrationEntity citizenEntity)
			throws Exception {
		Document doc = new Document(PageSize.A4);

		File file = new File(eliEntity.getCaseNo() + ".pdf");
		FileOutputStream fos = new FileOutputStream(file);

		PdfWriter.getInstance(doc, fos);

		doc.open();

		Font font = FontFactory.getFont("Alata");
		font.setSize(30);

		Paragraph para = new Paragraph("California State Government", font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
		doc.add(para);

		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 1.0f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 2.0f });
		table.setSpacingBefore(0.5f);

		PdfPCell cell = new PdfPCell();
		cell.setBorder(5);
		cell.setPadding(5);
		Font cellFont = FontFactory.getFont("Alata");
		cellFont.setColor(Color.BLACK);

		String[] headers = { "Case No", "Holder Name", "Holder SSN", "Plan Name", "Plan Status", "Benefit Amount",
				"Plan Start Date", "Plan End Date", "Denial Reason" };
		for (String header : headers) {
			cell.setPhrase(new Phrase(header, cellFont));
			table.addCell(cell);
		}

		table.addCell(String.valueOf(eliEntity.getCaseNo()));
		table.addCell(eliEntity.getHolderName());
		table.addCell(String.valueOf(eliEntity.getHolderSSN()));
		table.addCell(eliEntity.getPlanName());
		table.addCell(eliEntity.getPlanStatus());
		table.addCell(String.valueOf(eliEntity.getBenifitAmt()));
		table.addCell(eliEntity.getPlanStartDate().toString());
		table.addCell(eliEntity.getPlanEndDate().toString());
		table.addCell(eliEntity.getDenialReason() != null ? eliEntity.getDenialReason() : "N/A");

		doc.add(table);

		doc.close();

		String subject = "User Registration Success";
		String body = readEmailMessageBody(env.getProperty("mailBody.citizenreport.location"), eliEntity,
				citizenEntity);

		emailUtils.sendEmailMessage(citizenEntity.getEmail(), subject, body, file);
		System.out.println("Citizen :: " + citizenEntity.getFirstName());

		updateCoTrigger(eliEntity.getCaseNo(), file);

	}

	private void updateCoTrigger(Integer caseNo, File file) throws IOException {
		CoTriggersEntity triggerEntity = triggerRepo.findByCaseNo(caseNo);

		//store pdf into table byte[]
		byte[] pdfContent = new byte[(int) file.length()];

		FileInputStream inputStream = new FileInputStream(file);
		inputStream.read(pdfContent);
		if (triggerEntity != null) {
			triggerEntity.setCoNoticePdf(pdfContent);
			triggerEntity.setTriggerStatus("completed");
			triggerRepo.save(triggerEntity);
		}
		inputStream.close();
	}

	private String readEmailMessageBody(String fileName, EligibilityDetailsEntity elgiEntity,
			CitizenRegistrationEntity citizenEntity) throws Exception {
		String mailBody = null;

		try (FileReader reader = new FileReader(fileName); BufferedReader br = new BufferedReader(reader)) {
			// Read file content to StringBuffer object line by line
			StringBuffer buffer = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			mailBody = buffer.toString();

			// Replace placeholders with actual values from the entities
			String fullName = (citizenEntity.getFirstName() != null ? citizenEntity.getFirstName() : "") + " "
					+ (citizenEntity.getLastName() != null ? citizenEntity.getLastName() : "");
			String caseNo = elgiEntity.getCaseNo() != null ? String.valueOf(elgiEntity.getCaseNo()) : "";
			String holderName = elgiEntity.getHolderName() != null ? elgiEntity.getHolderName() : "";
			String holderSSN = elgiEntity.getHolderSSN() != null ? String.valueOf(elgiEntity.getHolderSSN()) : "";
			String planName = elgiEntity.getPlanName() != null ? elgiEntity.getPlanName() : "";
			String planStatus = elgiEntity.getPlanStatus() != null ? elgiEntity.getPlanStatus() : "";
			String benifitAmt = elgiEntity.getBenifitAmt() != null ? String.valueOf(elgiEntity.getBenifitAmt()) : "";
			String planStartDate = elgiEntity.getPlanStartDate() != null ? elgiEntity.getPlanStartDate().toString()
					: "";
			String planEndDate = elgiEntity.getPlanEndDate() != null ? elgiEntity.getPlanEndDate().toString() : "";
			String denialReason = elgiEntity.getDenialReason() != null ? elgiEntity.getDenialReason() : "";

			// Replace placeholders with actual values from the entities

			mailBody = mailBody.replace("{firstName}", citizenEntity.getFirstName());
			mailBody = mailBody.replace("{LastName}", citizenEntity.getLastName());
			mailBody = mailBody.replace("{FULL-NAME}", fullName);
			mailBody = mailBody.replace("{caseNo}", caseNo);
			mailBody = mailBody.replace("{holderName}", holderName);
			mailBody = mailBody.replace("{holderSSN}", holderSSN);
			mailBody = mailBody.replace("{planName}", planName);
			mailBody = mailBody.replace("{planStatus}", planStatus);
			mailBody = mailBody.replace("{benifitAmt}", benifitAmt);
			mailBody = mailBody.replace("{planStartDate}", planStartDate);
			mailBody = mailBody.replace("{planEndDate}", planEndDate);
			mailBody = mailBody.replace("{denialReason}", denialReason);
			mailBody = mailBody.replace("{URL}", "your-url-here");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return mailBody;
	}

}
