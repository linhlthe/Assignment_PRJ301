/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myLibs;

import java.sql.Date;
import java.util.Random;

/**
 *
 * @author DELL
 */
public class Rand {

    java.util.Random r = new java.util.Random();

    public String genSurname() {
        String[] surname = {"Lê", "Nguyễn", "Hoàng", "Ngô", "Trần", "Đinh", "Lại", "Phạm", "Lý", "Bùi", "Võ", "Huỳnh", "Đỗ", "Vũ", "Kiều", "Khuất", "Lương"};
        return surname[r.nextInt(surname.length)];
    }

    public String genMiddlename() {
        String[] middlemane = {"Văn", "Thị", "Thuỳ", "Anh", "Tuấn", "Hưng", "Ngọc", "Khánh"};
        return middlemane[r.nextInt(middlemane.length)];
    }

    public String genGivenname() {
        String[] givenname = {"Linh", "Lan", "Quốc", "Mai", "Tùng", "Sơn", "Hiệp", "Thái", "Hoa", "Trang", "Bình", "Phương", "Ngọc", "Lâm", "Khánh", "Bảo", "Bích", "Trâm", "Trinh", "Lan", "Hoa", "Hòa", "Tú", "Quỳnh", "Hương", "Hải", "Phong", "Đạt", "Nga", "Tiên", "Nhi", "Bảo", "Dung", "Đăng", "Duy", "Minh", "Dương", "Tuấn", "Anh"};
        return givenname[r.nextInt(givenname.length)];
    }

    public String genStudentCode() {
        String[] start = {"HE1", "HA1", "HS1"};
        String studentCode = start[r.nextInt(start.length)];
        for (int i = 0; i < 5; i++) {
            studentCode += r.nextInt(9);
        }

        return studentCode;
    }

    public String idCard() {
        String num = '0' + "";
        for (int i = 0; i < 12; i++) {
            num += r.nextInt(9);
        }
        return num;
    }

    public String phone() {
        String num = '0' + "";
        for (int i = 0; i < 9; i++) {
            num += r.nextInt(9);
        }
        return num;
    }

    public String place() {
        String[] place = {"Hà Nội", "Hải Phòng", "Thanh Hóa", "Nghệ An", "Hưng Yên", "Vĩnh Phúc", "Hà Nam", "Tuyên Quang", "Bắc Giang", "Bắc Ninh", "Thái Bình", "Nam Định", "Lào Cai", "Quảng Ninh", "Hải Dương"};
        return place[r.nextInt(place.length)];
    }

    public String nationality() {
        String[] nation = {"Việt Nam", "Thái Lan", "Trung Quốc", "Đài Loan", "Campuchia", "Lào", "Việt Nam", "Việt Nam", "Việt Nam", "Việt Nam", "Việt Nam", "Việt Nam", "Việt Nam", "Việt Nam", "Việt Nam", "Việt Nam", "Việt Nam", "Việt Nam", "Việt Nam", "Việt Nam", "Việt Nam"};
        return nation[r.nextInt(nation.length)];
    }

    public String major() {
        String[] major = {"CNTT", "Ngôn Ngữ", "Kinh tế"};
        return major[r.nextInt(major.length)];
    }

    public String curriculum() {
        String[] curriculum = {"SE", "IA", "IOT", "IS", "MC", "IB", "MKT", "GD", "FIN"};
        return curriculum[r.nextInt(curriculum.length)];
    }

    public String peoples() {
        String[] peoples = {"Kinh", "Tày", "Hơ Mông", "Mường", "Kinh", "Kinh", "Kinh", "Kinh", "Kinh", "Kinh", "Kinh", "Kinh", "Kinh", "Kinh", "Kinh"};
        return peoples[r.nextInt(peoples.length)];
    }

    public String name() {
        return genSurname() + " " + genMiddlename() + " " + genGivenname();
    }

    public String job() {
        String[] job = {"Nông dân", "Công nhân", "Kinh doanh", "Lao động tự do", "Công chức"};
        return job[r.nextInt(job.length)];
    }

    public int getRandomIntInRange(int min, int max) {

        int randomNum = r.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    
}
