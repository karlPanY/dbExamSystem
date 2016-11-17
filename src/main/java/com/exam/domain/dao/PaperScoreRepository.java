package com.exam.domain.dao;

import com.exam.domain.PaperScore;
import com.exam.domain.PaperScoreId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by NeilHY on 2016/11/14.
 */
public interface PaperScoreRepository extends JpaRepository<PaperScore,PaperScoreId> {

}
