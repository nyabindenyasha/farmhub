package com.xplug.tech.cropprograms;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.xplug.tech.crop.*;
import com.xplug.tech.cropfertilizerschedule.CropFertilizerScheduleService;
import com.xplug.tech.croppesticideschedule.CropPesticideScheduleService;
import com.xplug.tech.cropstagesofgrowth.CropStagesOfGrowthService;
import com.xplug.tech.enums.CropScheduleType;
import com.xplug.tech.enums.PeriodUnit;
import com.xplug.tech.enums.PesticideType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Service
public class CropProgramPDFGeneratorTest {

    private final CropService cropService;

    private final CropStagesOfGrowthService cropStagesOfGrowthService;

    private final CropPesticideScheduleService cropPesticideScheduleService;
    private final CropFertilizerScheduleService cropFertilizerScheduleService;

    public CropProgramPDFGeneratorTest(CropService cropService,
                                       CropStagesOfGrowthService cropStagesOfGrowthService, CropPesticideScheduleService cropPesticideSchedule,
                                       CropFertilizerScheduleService cropFertilizerScheduleService) {
        this.cropService = cropService;
        this.cropStagesOfGrowthService = cropStagesOfGrowthService;
        this.cropPesticideScheduleService = cropPesticideSchedule;
        this.cropFertilizerScheduleService = cropFertilizerScheduleService;
    }

    public byte[] generateCabbageProgramPdf(Long cropId) {

        var cropFertilizerScheduleList = cropFertilizerScheduleService
                .getByCropAndScheduleType(cropId, CropScheduleType.PRIMARY);

        var cropPesticideScheduleList = cropPesticideScheduleService
                .getByCropAndScheduleType(cropId, CropScheduleType.PRIMARY);

        var crop = cropService.getById(cropId);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);

            document.open();

            // Add Title
            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph(crop.getName() + " Program", titleFont);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            document.add(Chunk.NEWLINE);

            Font customFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

            Font stagesFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.GRAY);

            // Create Table
            PdfPTable table = new PdfPTable(7); // 10 columns
            float[] columnWidths = {1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1f, 1.5f}; // Adjusted widths
            table.setWidths(columnWidths);
            table.setWidthPercentage(100);

            // Add Headers
//            table.addCell("Week");
            table.addCell("Age");
            table.addCell("Crop Stage");
            table.addCell("Fertilizer");
            table.addCell("Pesticide");
            table.addCell("Fungicide");
            table.addCell("Foliar Spray");
            table.addCell("Herbicide");

            var cropStagesOfGrowthList = cropStagesOfGrowthService.getByCropId(cropId);


            // Add Data Rows
            for (CropStagesOfGrowth data : cropStagesOfGrowthList) {
                //week
//                PdfPCell weekCell = new PdfPCell(new Phrase((data.getStageStartDate().getPeriodUnit().equals(PeriodUnit.DAYS) && data.getStageStartDate().getPeriodValue() == 1) ? "0" : String.valueOf(data.getStageStartDate().getPeriodValue()), customFont));
//                table.addCell(weekCell);

                //age
                PdfPCell ageCell = new PdfPCell(new Phrase(String.valueOf(data.getStageStartDate().getPeriodValue()).concat(" ").concat(String.valueOf(data.getStageStartDate().getPeriodUnit())) , customFont));
                table.addCell(ageCell);

                //crop stage
                PdfPCell cell = new PdfPCell(new Phrase(StringUtils.capitalize(data.getStageOfGrowth().name().toLowerCase().replaceAll("_", " ")), stagesFont));
                table.addCell(cell);

                //fertilizer
                var cropFertilizerSchedule = getFirstMatchingFertilizer(cropFertilizerScheduleList, data.getStageStartDate());
                log.info("cropFertilizerSchedule {}", cropFertilizerSchedule);
                PdfPCell fertilizerCell = new PdfPCell(new Phrase(isNull(cropFertilizerSchedule) ? " " : cropFertilizerSchedule.getFertilizer().getName() + " " + cropFertilizerSchedule.getRate() + "g", customFont));
                table.addCell(fertilizerCell);

//               var cropPesticideSchedules = _getFirstMatchingPesticide(cropPesticideScheduleList, data.getStageStartDate());
//                table.addCell(cropPesticideSchedules.stream().filter(cropPesticideSchedule -> cropPesticideSchedule.getPesticide().getPesticideType().equals(PesticideType.PESTICIDE)).findFirst().orElse(null).getPesticide().getName() + " " + cropPesticideSchedule.getPesticide().getApplicationRate()  getName() + " " + cropPesticideSchedule.getPesticide().getApplicationRate())
//                var cropPesticideSchedule = getFirstMatchingPesticide(cropPesticideScheduleList, data.getStageStartDate());


                //pesticide

                var cropPesticideSchedule = getFirstMatchingPesticide(cropPesticideScheduleList, data.getStageStartDate(), PesticideType.PESTICIDE);
                PdfPCell pesticideCell = new PdfPCell(new Phrase(nonNull(cropPesticideSchedule) ? cropPesticideSchedule.getPesticide().getName() + " " + cropPesticideSchedule.getPesticide().getApplicationRate() : "", customFont));
                table.addCell(pesticideCell);

                //fungicide
                cropPesticideSchedule = getFirstMatchingPesticide(cropPesticideScheduleList, data.getStageStartDate(), PesticideType.FUNGICIDE);
                PdfPCell fungicideCell = new PdfPCell(new Phrase(nonNull(cropPesticideSchedule) ? cropPesticideSchedule.getPesticide().getName() + " " + cropPesticideSchedule.getPesticide().getApplicationRate() : "", customFont));
                table.addCell(fungicideCell);

                //foliar todo
                table.addCell("");

                //herbicide
                cropPesticideSchedule = getFirstMatchingPesticide(cropPesticideScheduleList, data.getStageStartDate(), PesticideType.HERBICIDE);
                PdfPCell herbicideCell = new PdfPCell(new Phrase(nonNull(cropPesticideSchedule) ? cropPesticideSchedule.getPesticide().getName() + " " + cropPesticideSchedule.getPesticide().getApplicationRate() : "", customFont));
                table.addCell(herbicideCell);
            }

            document.add(table);
            document.close();

            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF", e);
        }
    }


    private CropFertilizerSchedule getFirstMatchingFertilizer(List<CropFertilizerSchedule> objects, Period stageOfGrowth) {
        return objects.stream()
                .filter(cropFertilizerSchedule ->
                        cropFertilizerSchedule.getStageOfGrowth().equals(stageOfGrowth)
                )
                .findFirst()
                .orElse(null);
    }

    private CropPesticideSchedule getFirstMatchingPesticide(List<CropPesticideSchedule> objects, Period stageOfGrowth, PesticideType pesticideType) {
        return objects.stream()
                .filter(cropPesticideSchedule ->
                        cropPesticideSchedule.getStageOfGrowth().equals(stageOfGrowth)
                )
                .filter(cropPesticideSchedule ->
                        cropPesticideSchedule.getPesticide().getPesticideType().equals(pesticideType)
                )
                .findFirst()
                .orElse(null);
    }

    private List<CropPesticideSchedule> _getFirstMatchingPesticide(List<CropPesticideSchedule> objects, Period stageOfGrowth) {
        return objects.stream()
                .filter(cropPesticideSchedule ->
                        cropPesticideSchedule.getStageOfGrowth().equals(stageOfGrowth)
                ).collect(Collectors.toList());
    }


    public String getCropName(Long cropId) {
        return cropService.getById(cropId).getName();
    }
}
