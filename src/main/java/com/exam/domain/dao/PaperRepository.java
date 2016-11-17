package com.exam.domain.dao;

import com.exam.domain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by NeilHY on 2016/11/14.
 */
public interface PaperRepository extends JpaRepository<Paper,Long> {

}
