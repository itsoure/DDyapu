package cn.itsource.mybatis.domain;

import java.util.Objects;

public class Student {
    private long id;
    private long group_id;
    private long creator;
    private int ad_type;
    private String ad_img_url;
    private String ad_val;
    private String slogan;
    private int sort;
    private String creat_time;
    private int status;
    private int show_in_detail;

    public Student(){};

    public Student(long id, long group_id, long creator, int ad_type, String ad_img_url, String ad_val, String slogan, int sort, String creat_time, int status, int show_in_detail) {
        this.id = id;
        this.group_id = group_id;
        this.creator = creator;
        this.ad_type = ad_type;
        this.ad_img_url = ad_img_url;
        this.ad_val = ad_val;
        this.slogan = slogan;
        this.sort = sort;
        this.creat_time = creat_time;
        this.status = status;
        this.show_in_detail = show_in_detail;
    }

    public Student(long group_id, long creator, int ad_type, String ad_img_url, String ad_val, String slogan, int sort, String creat_time, int status, int show_in_detail) {
        this.group_id = group_id;
        this.creator = creator;
        this.ad_type = ad_type;
        this.ad_img_url = ad_img_url;
        this.ad_val = ad_val;
        this.slogan = slogan;
        this.sort = sort;
        this.creat_time = creat_time;
        this.status = status;
        this.show_in_detail = show_in_detail;
    }

    public Student(String ad_img_url, String ad_val) {
        this.ad_img_url = ad_img_url;
        this.ad_val = ad_val;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public long getCreator() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public int getAd_type() {
        return ad_type;
    }

    public void setAd_type(int ad_type) {
        this.ad_type = ad_type;
    }

    public String getAd_img_url() {
        return ad_img_url;
    }

    public void setAd_img_url(String ad_img_url) {
        this.ad_img_url = ad_img_url;
    }

    public String getAd_val() {
        return ad_val;
    }

    public void setAd_val(String ad_val) {
        this.ad_val = ad_val;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(String creat_time) {
        this.creat_time = creat_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getShow_in_detail() {
        return show_in_detail;
    }

    public void setShow_in_detail(int show_in_detail) {
        this.show_in_detail = show_in_detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && group_id == student.group_id && creator == student.creator && ad_type == student.ad_type && sort == student.sort && status == student.status && show_in_detail == student.show_in_detail && Objects.equals(ad_img_url, student.ad_img_url) && Objects.equals(ad_val, student.ad_val) && Objects.equals(slogan, student.slogan) && Objects.equals(creat_time, student.creat_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, group_id, creator, ad_type, ad_img_url, ad_val, slogan, sort, creat_time, status, show_in_detail);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", group_id=" + group_id +
                ", creator=" + creator +
                ", ad_type=" + ad_type +
                ", ad_img_url='" + ad_img_url + '\'' +
                ", ad_val='" + ad_val + '\'' +
                ", slogan='" + slogan + '\'' +
                ", sort=" + sort +
                ", creat_time='" + creat_time + '\'' +
                ", status=" + status +
                ", show_in_detail=" + show_in_detail +
                '}';
    }
}
