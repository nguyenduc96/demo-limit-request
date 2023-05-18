package com.example.demo.controller;

import com.example.demo.model.Req;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.fit.pdfdom.PDFDomTree;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping(path = "/pdf-to-word")
    public Object pdfToWord(@RequestParam("file") MultipartFile file) throws IOException {
        FileCopyUtils.copy(file.getBytes(), new File(file.getOriginalFilename()));
        try {
            String rs = pdfToWord(file.getOriginalFilename());
//        FileOutputStream fileOutputStream = new FileOutputStream(new File("file.doc"));
            File filea = new File("output.doc");

            // if file doesnt exists, then create it
            if (!filea.exists()) {
                filea.createNewFile();
            }

            FileWriter fw = new FileWriter(filea.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(rs);
            bw.close();

            System.out.println("Done");
            return bw;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String pdfToWord(String fileName) {
        PDFParser parser;
        String parsedText = null;
        PDFTextStripper pdfStripper = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        File file = new File(fileName);
        if (!file.isFile()) {
            System.err.println("File " + fileName + " does not exist.");
            return null;
        }
        try {
            parser = new PDFParser(new RandomAccessBufferedFileInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            System.err.println("Unable to open PDF Parser. " + e.getMessage());
            return null;
        }
        try {
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);
        } catch (Exception e) {
            System.err
                    .println("An exception occured in parsing the PDF Document."
                            + e.getMessage());
        } finally {
            try {
                if (cosDoc != null)
                    cosDoc.close();
                if (pdDoc != null)
                    pdDoc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return parsedText;
    }
}
