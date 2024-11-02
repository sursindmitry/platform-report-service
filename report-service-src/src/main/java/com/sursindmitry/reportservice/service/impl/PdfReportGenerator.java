package com.sursindmitry.reportservice.service.impl;

import com.sursindmitry.reportservice.domain.entity.User;
import com.sursindmitry.reportservice.exception.PlatformException;
import com.sursindmitry.reportservice.service.ReportGenerator;
import com.sursindmitry.reportservice.service.UserService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Генерация отчёта PDF.
 */
@Component("PDF")
@RequiredArgsConstructor
public class PdfReportGenerator implements ReportGenerator {

    private final UserService userService;

    /**
     * Генерация PDF.
     *
     * @return байтовое представление PDF файла
     */
    @Override
    public byte[] generateReport() {

        List<User> users = userService.findAll();

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);

        JasperReport jasperReport = getCompiledReport();

        Map<String, Object> parameters = loadParameters();

        return exportReportToPdf(jasperReport, parameters, dataSource);
    }

    /**
     * Получение скомпилированного шаблона.
     *
     * @return {@link JasperReport}
     */
    private JasperReport getCompiledReport() {
        JasperReport jasperReport;
        try (InputStream reportStream = getClass().getResourceAsStream(
            "/templates/Blank_A4.jrxml")
        ) {
            if (reportStream == null) {
                throw new IllegalStateException("Report template not found");
            }
            jasperReport = JasperCompileManager.compileReport(reportStream);
        } catch (IOException | JRException e) {
            throw new PlatformException("Error compiling Jasper report template",
                HttpStatus.NO_CONTENT);
        }

        return jasperReport;
    }

    /**
     * Загрузка параметров в {@link HashMap}.
     *
     * @return {@link HashMap}
     */
    private Map<String, Object> loadParameters() {
        Map<String, Object> parameters = new HashMap<>();
        try (InputStream imageStream = getClass().getClassLoader().getResourceAsStream(
            "images/RR3V8sq.png")
        ) {
            if (imageStream == null) {
                throw new IllegalStateException("Image not found: images/RR3V8sq.png");
            }
            byte[] imageData = imageStream.readAllBytes();
            parameters.put("ImagePath1", new ByteArrayInputStream(imageData));
        } catch (IOException e) {
            throw new PlatformException("Error loading image for report", HttpStatus.NO_CONTENT);
        }
        return parameters;
    }

    /**
     * Загрузка параметров в скомпилированный отчёт и получение pdf.
     *
     * @param report     скомпилированный отчёт
     * @param parameters параметры отчёта
     * @param dataSource данные для
     * @return байтовое представление PDF файла
     */
    private byte[] exportReportToPdf(JasperReport report,
                                     Map<String, Object> parameters,
                                     JRBeanCollectionDataSource dataSource) {
        try (ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, pdfOutputStream);

            return pdfOutputStream.toByteArray();
        } catch (JRException | IOException e) {
            throw new PlatformException("Error exporting report to PDF", HttpStatus.NO_CONTENT);
        }
    }
}
