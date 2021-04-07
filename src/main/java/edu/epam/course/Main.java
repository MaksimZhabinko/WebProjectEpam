package edu.epam.course;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.dao.*;
import edu.epam.course.model.dao.impl.*;
import edu.epam.course.model.entity.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws DaoException {

        CourseDao courseDao = new CourseDaoImpl();
        Optional<Course> course = courseDao.findEntityById(1L);
        CourseDetailsDao courseDetailsDao = new CourseDetailsDaoImpl();
        Optional<CourseDetails> courseDetails = courseDetailsDao.findCourseDetailsByCourseId(course.get().getId());
        LectureDao lectureDao = new LectureDaoImpl();
        List<Lecture> lectures = lectureDao.findAllByCourseId(course.get().getId());
        courseDao.example(course.get(), courseDetails.get(), lectures);
    }

    private void deleteRepeatNumber() {
        List<String> array = new ArrayList<>();
        array.add("11");
        array.add("11");
        array.add("12");
        array.add("12");
        array.add("12");
        array.add("12");
        array.add("13");
        array.add("13");
        array.add("14");
        array.add("15");

        HashSet<String> set = new HashSet<>(array);

        List<String> collect = array.stream().distinct().collect(Collectors.toList());
        collect.stream().forEach(e -> System.out.println(e));


        List<String> secondArray = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            boolean isNeedAdd = true;
            String a = array.get(i);
            if (secondArray.size() == 0) {
                secondArray.add(a);
                continue;
            }
            for (int j = 0; j < secondArray.size(); j++) {
                String s = secondArray.get(j);
                if (a.equals(s)) {
                    isNeedAdd = false;
                    continue;
                }
            }
            if (isNeedAdd) {
                secondArray.add(a);
            }
        }
        secondArray.stream().forEach(e -> System.out.println(e));
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







        String startString = new String("2020-04-02");
        String endString = new String("2020-04-02");
        Long start = Long.parseLong(startString.replaceAll("[-]", ""));
        Long end = Long.parseLong(endString.replaceAll("[-]", ""));
        int yearStart = (int) (start/Math.pow(10,4));
        int yearEnd = (int) (end/Math.pow(10,4));
//        System.out.println(yearStart);
//        System.out.println(yearEnd);
        int monthStart = (int) (start/Math.pow(10,2)) % 100;
        int monthEnd = (int) (end/Math.pow(10,2)) % 100;
        System.out.println(monthStart);
        System.out.println(monthEnd);
        int dayStart = (int) (start % 100);
        int dayEnd = (int) (end % 100);
//        System.out.println(dayStart);
//        System.out.println(dayEnd);

//        if (yearEnd >= yearStart) {
//            if (monthEnd >= monthStart) {
//                if (dayEnd >= dayStart) {
//                    System.out.println("all good");
//                } else {
//                    System.out.println("bad day end < start");
//                }
//            } else {
//                System.out.println("bad month end < start");
//            }
//        } else {
//            System.out.println("bad year end < start");
//        }
        if (yearEnd >= yearStart && monthEnd > monthStart && dayEnd >= dayStart) {
            System.out.println("all good");
        } else {
            System.out.println("all bad");
        }
    }

    public static String getDecoder(String string) {
        byte[] decode = Base64.getDecoder().decode(string.getBytes());
        String decodeString = new String(decode);
        return decodeString;
    }
}
