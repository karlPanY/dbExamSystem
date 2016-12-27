package com.exam.web.response;

import com.exam.domain.StuClass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by uio5 on 2016/12/23.
 */
public class AdminInit {
    public ArrayList<Classes> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Classes> classes) {
        this.classes = classes;
    }

    ArrayList<Classes> classes = new ArrayList<>();

    public AdminInit(Set<StuClass> classSet )
    {
        Iterator classIte = classSet.iterator();
        while(classIte.hasNext())
        {
            StuClass stuClass = (StuClass) classIte.next();
            Classes classes = new Classes();
            classes.setClass_name(stuClass.getClassName());
            classes.setClass_id(stuClass.getClassId());
            this.classes.add(classes);
        }
    }
    class Classes {
        public long getClass_id() {
            return class_id;
        }

        public void setClass_id(long class_id) {
            this.class_id = class_id;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        long class_id;
        String class_name;
    }

}