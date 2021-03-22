package edu.epam.course;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.dao.UserDao;
import edu.epam.course.model.dao.impl.UserDaoImpl;
import edu.epam.course.model.entity.User;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws DaoException {


    }


    private void forUploadFile() {
        String uploadName = "fdbfdbfdbfdbfd.jpeg";
        UUID uuid = UUID.randomUUID();
        String name = uuid.toString();
        int index = uploadName.lastIndexOf(".");
        String extension = uploadName.substring(index);
        System.out.println(name + extension);
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
    }

    public static String getDecoder(String string) {
        byte[] decode = Base64.getDecoder().decode(string.getBytes());
        String decodeString = new String(decode);
        return decodeString;
    }
}
