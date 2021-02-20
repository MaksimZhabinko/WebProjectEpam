package edu.epam.course;

import edu.epam.course.exception.DaoException;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.dao.CourseDao;
import edu.epam.course.model.dao.impl.CourseDaoImpl;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.Lecture;
import edu.epam.course.model.service.LectureService;
import edu.epam.course.model.service.impl.LectureServiceImpl;
import edu.epam.course.validator.CourseValidator;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;


public class Main {
    public static void main(String[] args) throws DaoException {
        System.out.println(CourseValidator.isValidCourseId("10"));

    }


    private void DateToIntAndIntToString () {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        String string = sdf.format(LocalDate.now());
        System.out.println(string);
        Long i = Long.parseLong(string.replaceAll("[- :]", ""));
        System.out.println(i);
        int year = (int) (i/Math.pow(10,10));
        System.out.println(year);
        int month = (int) (i/Math.pow(10,8)) % 100;
        System.out.println(month);
        int day = (int) (i/Math.pow(10,6)) % 100;
        System.out.println(day);
        int hour = (int) (i/Math.pow(10,4)) % 100;
        System.out.println(hour);
        long min = (long) (i/Math.pow(10,2) % 100);
        System.out.println(min);
        int sec = (int) (i % 100);
        System.out.println(sec);
        String stringDate = year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
        System.out.println(stringDate);
    }
}
