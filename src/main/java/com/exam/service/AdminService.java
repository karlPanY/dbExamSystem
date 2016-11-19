package com.exam.service;

import com.exam.domain.Teacher;

import java.util.Set;

/**
 * Created by NeilHY on 2016/11/15.
 */
public interface AdminService {
    Set<Teacher> getTeacher();//获得所有老师名单
}
