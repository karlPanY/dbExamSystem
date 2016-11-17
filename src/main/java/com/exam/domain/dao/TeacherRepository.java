package com.exam.domain.dao;

import com.exam.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by NeilHY on 2016/11/14.
 */
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Teacher findByTeacherId(Long teacherId);
}
