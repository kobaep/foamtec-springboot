package com.foamtec.service;

import com.foamtec.dao.FileBackupDao;
import com.foamtec.domain.DocumentHistory;
import com.foamtec.domain.FileBackup;
import com.foamtec.domain.Matter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * Created by apichat on 5/5/2016 AD.
 */
public class FileBackupService {

    @Autowired
    private FileBackupDao fileBackupDao;

    public void createFileBackup(Matter matter, HttpServletRequest request, DocumentHistory documentHistory) throws IOException {
        FileBackup fileBackup = new FileBackup();
        fileBackupDao.create(fileBackup);

        if(matter.getSpecUrl() != null) {
            backupSpec(matter, request, fileBackup, documentHistory);
        }
        if (matter.getRohsUrl() != null) {
            backupRohsUrl(matter, request, fileBackup, documentHistory);
        }
        if (matter.getMsdsUrl() != null) {
            backupMsdsUrl(matter, request, fileBackup, documentHistory);
        }
        if (matter.getHalogenUrl() != null) {
            backupHalogenUrl(matter, request, fileBackup, documentHistory);
        }

        fileBackupDao.update(fileBackup);
    }

    public void backupSpec(Matter matter, HttpServletRequest request, FileBackup fileBackup, DocumentHistory documentHistory) throws IOException {
        String pathCur = request.getRealPath("./resources/filePDF");
        File convFile = new File(pathCur + matter.getSpecUrl());
        InputStream inStream = new FileInputStream(convFile);

        String pathBackup = request.getRealPath("./resources/filePDFBackup/");
        String dirBackup = "BACKUP" + fileBackup.getId();
        documentHistory.setBackupDocumentNUmber(dirBackup);
        File backupFile = new File(pathBackup + dirBackup + "/SPEC/spec.pdf");
        backupFile.getParentFile().mkdirs();
        OutputStream outStream = new FileOutputStream(backupFile);

        byte[] buffer = new byte[(int)convFile.length()];
        int length;
        while ((length = inStream.read(buffer)) > 0){
            outStream.write(buffer, 0, length);
        }
        inStream.close();
        outStream.close();
        fileBackup.setSpecUrl(dirBackup + "/SPEC/spec.pdf");
    }

    public void backupRohsUrl(Matter matter, HttpServletRequest request, FileBackup fileBackup, DocumentHistory documentHistory) throws IOException {
        String pathCur = request.getRealPath("./resources/filePDF");
        File convFile = new File(pathCur + matter.getRohsUrl());
        InputStream inStream = new FileInputStream(convFile);

        String pathBackup = request.getRealPath("./resources/filePDFBackup/");
        String dirBackup = "BACKUP" + fileBackup.getId();
        documentHistory.setBackupDocumentNUmber(dirBackup);
        File backupFile = new File(pathBackup + dirBackup + "/ROHS/spec.pdf");
        backupFile.getParentFile().mkdirs();
        OutputStream outStream = new FileOutputStream(backupFile);

        byte[] buffer = new byte[(int)convFile.length()];
        int length;
        while ((length = inStream.read(buffer)) > 0){
            outStream.write(buffer, 0, length);
        }
        inStream.close();
        outStream.close();
        fileBackup.setSpecUrl(dirBackup + "/ROHS/spec.pdf");
    }

    public void backupMsdsUrl(Matter matter, HttpServletRequest request, FileBackup fileBackup, DocumentHistory documentHistory) throws IOException {
        String pathCur = request.getRealPath("./resources/filePDF");
        File convFile = new File(pathCur + matter.getMsdsUrl());
        InputStream inStream = new FileInputStream(convFile);

        String pathBackup = request.getRealPath("./resources/filePDFBackup/");
        String dirBackup = "BACKUP" + fileBackup.getId();
        documentHistory.setBackupDocumentNUmber(dirBackup);
        File backupFile = new File(pathBackup + dirBackup + "/MSDS/spec.pdf");
        backupFile.getParentFile().mkdirs();
        OutputStream outStream = new FileOutputStream(backupFile);

        byte[] buffer = new byte[(int)convFile.length()];
        int length;
        while ((length = inStream.read(buffer)) > 0){
            outStream.write(buffer, 0, length);
        }
        inStream.close();
        outStream.close();
        fileBackup.setSpecUrl(dirBackup + "/MSDS/spec.pdf");
    }

    public void backupHalogenUrl(Matter matter, HttpServletRequest request, FileBackup fileBackup, DocumentHistory documentHistory) throws IOException {
        String pathCur = request.getRealPath("./resources/filePDF");
        File convFile = new File(pathCur + matter.getHalogenUrl());
        InputStream inStream = new FileInputStream(convFile);

        String pathBackup = request.getRealPath("./resources/filePDFBackup/");
        String dirBackup = "BACKUP" + fileBackup.getId();
        documentHistory.setBackupDocumentNUmber(dirBackup);
        File backupFile = new File(pathBackup + dirBackup + "/HALOGEN/spec.pdf");
        backupFile.getParentFile().mkdirs();
        OutputStream outStream = new FileOutputStream(backupFile);

        byte[] buffer = new byte[(int)convFile.length()];
        int length;
        while ((length = inStream.read(buffer)) > 0){
            outStream.write(buffer, 0, length);
        }
        inStream.close();
        outStream.close();
        fileBackup.setSpecUrl(dirBackup + "/HALOGEN/spec.pdf");
    }
}
