package com.ecommerce.backend.service.services;

import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
public class InvoiceService {

    public void generateInvoice(String html, String outputPath){

        try {
            File file = new File(outputPath);
            File parentDir = file.getParentFile();
            if(parentDir != null && !parentDir.exists()){
                parentDir.mkdir();
            }


        try(OutputStream outputStream = new FileOutputStream(outputPath)){
            ITextRenderer  renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
        }
        }catch (Exception e){
            throw new RuntimeException("Failed to generate invoice", e);
        }
    }
}
