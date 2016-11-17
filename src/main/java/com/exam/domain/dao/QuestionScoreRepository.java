package com.exam.domain.dao;

import com.exam.domain.QuestionScore;
import com.exam.domain.QuestionScoreId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by NeilHY on 2016/11/14.
 */
public interface QuestionScoreRepository extends JpaRepository<QuestionScore,QuestionScoreId> {

}
