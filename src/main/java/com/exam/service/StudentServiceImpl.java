package com.exam.service;

import com.exam.domain.Paper;
import com.exam.domain.dao.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by NeilHY on 2016/11/17.
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private PaperRepository paperRepository;

    @Override
    public Paper getPaper(Long paperId) {
        return paperRepository.findOne(paperId);
    }
}
