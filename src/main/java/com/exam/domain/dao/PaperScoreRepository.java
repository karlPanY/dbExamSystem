package com.exam.domain.dao;

import com.exam.domain.PaperScore;
import com.exam.domain.PaperScoreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by NeilHY on 2016/11/14.
 */
public interface PaperScoreRepository extends JpaRepository<PaperScore,PaperScoreId> {

//    @Query("select p from Project p where p.projectName= :name and p.rank= :rank and p.projectDate= :date")
//    Project withNameAndRankAndDateQuery(@Param("name") String name, @Param("rank") String rank, @Param("date") Date date);
    @Query("select p from PaperScore p where p.id.paper.paperId=:paperId ")
    List<PaperScore> getAllPaperScoreByPaperId(@Param("paperId") Long paperId);

    @Query("select p.id.student.id from PaperScore p where p.id.paper.paperId=:paperId ")
    List<Long> getAllStudentIdByPaperId(@Param("paperId") Long paperId);

    @Query("select p from PaperScore p where p.id.paper.paperId=:paperId and p.id.student.id=:studentId")
    PaperScore getExactPaperScore(@Param("paperId") Long paperId,@Param("studentId")Long studentId);
}
