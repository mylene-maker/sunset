package com.humanbooster.sunset.services;


import com.humanbooster.sunset.models.Command;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class PdfService {
    public String output = "src/main/resources/static/pdf/facture.pdf";

    @Autowired
    TemplateEngine templateEngine;

    public String parseThymeleafTemplate(Command command){
        Context context = new Context();
        context.setVariable("command", command);
        return templateEngine.process("pdf/facture", context);
    }

    public void generatePdfFromHtml(Command command) throws IOException, DocumentException {
        String html = this.parseThymeleafTemplate(command);
        OutputStream os = new FileOutputStream(output);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(os);
        os.close();
    }
}
