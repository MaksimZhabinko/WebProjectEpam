package edu.epam.project;

import edu.epam.project.exception.DaoException;
import edu.epam.project.model.dao.impl.UserDaoImpl;
import edu.epam.project.model.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws ParseException {

        UserDaoImpl userDao = new UserDaoImpl();
        Optional<User> byEmail = null;
        try {
            byEmail = userDao.findByEmail("maxim.style@mail.ru");
        } catch (DaoException e) {
            e.printStackTrace();
        }
        System.out.println(byEmail.get().toString());
    }


    private void DateToIntAndIntToString () {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        String string = sdf.format(new Date());
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
