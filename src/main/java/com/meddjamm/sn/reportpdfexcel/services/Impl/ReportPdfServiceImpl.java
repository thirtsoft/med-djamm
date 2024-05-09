package com.meddjamm.sn.reportpdfexcel.services.Impl;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.meddjamm.sn.dossiermedical.assembler.ConsultationMedicalAssembler;
import com.meddjamm.sn.dossiermedical.assembler.HospitalisationAssembler;
import com.meddjamm.sn.dossiermedical.assembler.PDFExportAssembler;
import com.meddjamm.sn.dossiermedical.remote.model.ConsultationMedicalDs;
import com.meddjamm.sn.dossiermedical.remote.model.HospitalisationDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientMinDs;
import com.meddjamm.sn.dossiermedical.remote.model.TraitementMedicalItemDs;
import com.meddjamm.sn.dossiermedical.services.ConsultationMedicalService;
import com.meddjamm.sn.dossiermedical.services.HospitalisationService;
import com.meddjamm.sn.reportpdfexcel.services.ReportPdfService;
import com.meddjamm.sn.utils.ReportAbstract;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static java.lang.String.valueOf;

@Service
@AllArgsConstructor
public class ReportPdfServiceImpl extends ReportAbstract implements ReportPdfService {

    private final HospitalisationService hospitalisationService;
    private final HospitalisationAssembler hospitalisationAssembler;

    private final ConsultationMedicalAssembler consultationMedicalAssembler;
    private final ConsultationMedicalService consultationMedicalService;

    @Override
    public void exportToPDF(HttpServletResponse response, Object data) throws IOException {

        // init response
        response = initResponseForExportPdf(response, "Patients");

        // define paper size
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        // start document
        document.open();

        // title
        Paragraph title = new Paragraph("La liste des Patients", getFontTitle());
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        // subtitel
        Paragraph subtitel = new Paragraph("Report Date : " + DateTime.now().toLocalDate(), getFontSubtitle());
        subtitel.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(subtitel);

        enterSpace(document);

        // table header
//        String[] headers = new String[]{PDFExportAssembler.headers()};
        PdfPTable tableHeader = new PdfPTable(PDFExportAssembler.headers().size());
        writeTableHeaderPdf(tableHeader, PDFExportAssembler.headers());
        document.add(tableHeader);

        // table content
        PdfPTable tableData = new PdfPTable(PDFExportAssembler.headers().size());
        writeTableData(tableData, data);
        document.add(tableData);

        document.close();
    }

    @Override
    public void writeTableData(PdfPTable table, Object data) {
        List<PatientMinDs> list = (List<PatientMinDs>) data;

        // for auto wide by paper  size
        table.setWidthPercentage(100);
        // cell
        PdfPCell cell = new PdfPCell();
//        int number = 0;
        for (PatientMinDs item : list) {
//            number += 1;
//            cell.setPhrase(new Phrase(valueOf(number), getFontContent()));
//            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getCode(), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(valueOf(item.getDateAdmission()), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getPrenom(), getFontContent()));
            table.addCell(cell);

//            String active = item.getActive() == 1 ? "Active" : "Non Active";
            cell.setPhrase(new Phrase(item.getNom(), getFontContent()));
            table.addCell(cell);

//            String blocked = item.getBlocked() == 1 ? "Blocked" : "Non Blocked";
            cell.setPhrase(new Phrase(valueOf(item.getDateNaissance()), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(valueOf(item.getTelephone()), getFontContent()));
            table.addCell(cell);
        }

    }

    @Override
    public void writeTableDataMedical(PdfPTable table, Object data) {
        PatientDetailDs patientDetailDs = (PatientDetailDs) data;
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(patientDetailDs.getCode(), getFontContent()));
        table.addCell(cell);
        cell.setPhrase(new Phrase(valueOf(patientDetailDs.getDateAdmission()), getFontContent()));
        table.addCell(cell);
        cell.setPhrase(new Phrase(patientDetailDs.getPrenom(), getFontContent()));
        table.addCell(cell);
        cell.setPhrase(new Phrase(patientDetailDs.getNom(), getFontContent()));
        table.addCell(cell);
        cell.setPhrase(new Phrase(valueOf(patientDetailDs.getDateNaissance()), getFontContent()));
        table.addCell(cell);
        cell.setPhrase(new Phrase(valueOf(patientDetailDs.getNumeroTelephone()), getFontContent()));
        table.addCell(cell);
        cell.setPhrase(new Phrase(patientDetailDs.getAddress(), getFontContent()));
        table.addCell(cell);
        cell.setPhrase(new Phrase(patientDetailDs.getSexe(), getFontContent()));
        table.addCell(cell);
        cell.setPhrase(new Phrase(patientDetailDs.getSituationMatrimonial(), getFontContent()));
        table.addCell(cell);

        List<HospitalisationDetailDs> hospitalisationDetailDsList = hospitalisationAssembler.assembleHospitalisationsDetailsFromEntity(
                hospitalisationService.findAllByPatient(patientDetailDs.getCode())
        );
        List<ConsultationMedicalDs> consultationMedicalDsList = consultationMedicalAssembler.assembleEntitiesFrom(
                consultationMedicalService.findConsultationMedicalByPatientId(patientDetailDs.getCode())
        );

        for (ConsultationMedicalDs consultationMedicalDs : consultationMedicalDsList) {
            cell.setPhrase(new Phrase(consultationMedicalDs.getConsultationDs().getResume(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(valueOf(consultationMedicalDs.getConsultationDs().getCreatedDate()), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(consultationMedicalDs.getExamenBiologiqueDs().getBiologie(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(consultationMedicalDs.getExamenBiologiqueDs().getImmunologie(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(consultationMedicalDs.getExamenBiologiqueDs().getImagerie(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(consultationMedicalDs.getExamenBiologiqueDs().getAnatomopathologie(), getFontContent()));
            table.addCell(cell);
        }
        for (HospitalisationDetailDs itemHospitalisation : hospitalisationDetailDsList) {
            cell.setPhrase(new Phrase(itemHospitalisation.getNumeroHospitalisation(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(valueOf(itemHospitalisation.getCreatedDate()), getFontContent()));
            table.addCell(cell);

            // observation clinique
            cell.setPhrase(new Phrase(itemHospitalisation.getObservationCliniqueDs().getMotifsHospitalisation(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(itemHospitalisation.getObservationCliniqueDs().getHistoireMaladie(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(itemHospitalisation.getObservationCliniqueDs().getAntecedentDs().getAntecedentsMedicaux(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(itemHospitalisation.getObservationCliniqueDs().getAntecedentDs().getAntecedentsChirurgicaux(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(itemHospitalisation.getObservationCliniqueDs().getAntecedentDs().getAntecedentsGynecologiques(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(itemHospitalisation.getObservationCliniqueDs().getAntecedentDs().getAntecedentsFamilialsAscendant(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(itemHospitalisation.getObservationCliniqueDs().getAntecedentDs().getAntecedentsFamilialsCollateral(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(itemHospitalisation.getObservationCliniqueDs().getAntecedentDs().getAntecedentsFamilialsDescendant(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(itemHospitalisation.getObservationCliniqueDs().getAntecedentDs().getModeVies(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(itemHospitalisation.getObservationCliniqueDs().getExamenPhysiqueDs().getExamenGeneral(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(itemHospitalisation.getObservationCliniqueDs().getExamenPhysiqueDs().getExamenAppareil(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(itemHospitalisation.getObservationCliniqueDs().getExamenPhysiqueDs().getPressionArterielS()), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(itemHospitalisation.getObservationCliniqueDs().getExamenPhysiqueDs().getPressionArterielD()), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(itemHospitalisation.getObservationCliniqueDs().getExamenPhysiqueDs().getFrequenceC()), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(itemHospitalisation.getObservationCliniqueDs().getExamenPhysiqueDs().getFrequenceR()), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(itemHospitalisation.getObservationCliniqueDs().getExamenPhysiqueDs().getGlycemie()), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(itemHospitalisation.getObservationCliniqueDs().getExamenPhysiqueDs().getSaturationOxygene()), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(itemHospitalisation.getObservationCliniqueDs().getExamenPhysiqueDs().getTemperature()), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(itemHospitalisation.getObservationCliniqueDs().getExamenPhysiqueDs().getPoids()), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(itemHospitalisation.getObservationCliniqueDs().getExamenPhysiqueDs().getTaille()), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(itemHospitalisation.getObservationCliniqueDs().getExamenPhysiqueDs().getImc()), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(itemHospitalisation.getObservationCliniqueDs().getExamenPhysiqueDs().getTourTaille()), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(itemHospitalisation.getObservationCliniqueDs().getExamenPhysiqueDs().getTourHanche()), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(itemHospitalisation.getObservationCliniqueDs().getExamenPhysiqueDs().getDiurese()), getFontContent()));
            table.addCell(cell);

            // traitement
            cell.setPhrase(new Phrase(itemHospitalisation.getTraitementMedicalDs().getProtocole(), getFontContent()));
            table.addCell(cell);

            List<TraitementMedicalItemDs> traitementMedicalItemDsList = itemHospitalisation.getTraitementMedicalDs().getTraitementMedicalItemDs();
            for (TraitementMedicalItemDs traitementMedicalItemDs : traitementMedicalItemDsList) {
                cell.setPhrase(new Phrase(traitementMedicalItemDs.getMedicamentDs().getLibelle(), getFontContent()));
                table.addCell(cell);
                cell.setPhrase(new Phrase(traitementMedicalItemDs.getPsologie(), getFontContent()));
                table.addCell(cell);
                cell.setPhrase(new Phrase(traitementMedicalItemDs.getNbrePrise(), getFontContent()));
                table.addCell(cell);
                cell.setPhrase(new Phrase(traitementMedicalItemDs.getAdministrePar(), getFontContent()));
                table.addCell(cell);
                cell.setPhrase(new Phrase(String.valueOf(traitementMedicalItemDs.getEst_administre()), getFontContent()));
                table.addCell(cell);
            }

            // examen complementaire
            cell.setPhrase(new Phrase(itemHospitalisation.getExamenComplementaireDs().getBiologie(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(itemHospitalisation.getExamenComplementaireDs().getImmunologie(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(itemHospitalisation.getExamenComplementaireDs().getImagerie(), getFontContent()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(itemHospitalisation.getExamenComplementaireDs().getAnatomopathologie(), getFontContent()));
            table.addCell(cell);

            // discussion
            cell.setPhrase(new Phrase(itemHospitalisation.getDiscussionDs().getResume(), getFontContent()));
            table.addCell(cell);

            // synthese
            cell.setPhrase(new Phrase(itemHospitalisation.getSyntheseDs().getObservation(), getFontContent()));
            table.addCell(cell);

        }

    }
}