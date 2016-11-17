package com.exam.domain.dao;

import com.exam.domain.StuClass;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by NeilHY on 2016/11/17.
 */
public interface ClassRepository extends JpaRepository<StuClass,Long> {

}
