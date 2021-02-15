package edu.epam.course;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.connection.ConnectionPool;
import edu.epam.course.model.dao.*;
import edu.epam.course.model.dao.impl.*;
import edu.epam.course.model.entity.*;
import edu.epam.course.validator.UserValidator;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


public class Main {
    public static void main(String[] args)  {
        CourseDetailsDao courseDetailsDao = new CourseDetailsDaoImpl();
        LectureDao lectureDao = new LectureDaoImpl();
        List<Lecture> lectureOptional = null;
        try {
            lectureOptional = lectureDao.findAllById(1L);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        lectureOptional.stream().forEach(e -> System.out.println(e.toString()));

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
