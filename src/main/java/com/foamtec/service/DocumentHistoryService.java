package com.foamtec.service;

import com.foamtec.dao.DocumentHistoryDao;
import com.foamtec.domain.DocumentHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by apichat on 6/14/2016 AD.
 */
@Service
public class DocumentHistoryService {

    @Autowired
    private DocumentHistoryDao documentHistoryDao;

    public List<DocumentHistory> findByStartDateEndDateAndStatus(Date start, Date end, String status) {
        return documentHistoryDao.findByStartDateEndDateAndStatus(start, end, status);
    }
}
