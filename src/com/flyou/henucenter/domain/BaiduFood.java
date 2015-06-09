package com.flyou.henucenter.domain;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;

/**
 * ============================================================ ��Ŀ���ƣ�HenuCenter
 * 
 * �����ƣ�BaiduFood
 * 
 * ��������
 * 
 * �����ˣ�flyou
 * 
 * ����ʱ�䣺2015-5-4 ����5:07:23
 * 
 * �޸ı�ע��
 * 
 * �汾��@version ============================================================
 */
public class BaiduFood {
  private String name;
  private String address;
  private String phone;
  private LatLng location;
  private double distance;
  private String city;

  public BaiduFood(String name, String address, String phone, LatLng location, double distance, String city) {
    super();
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.location = location;
    this.distance = distance;
    this.city = city;
  }

  public BaiduFood() {

  }

  /*
   * @return
   */
  @Override
  public String toString() {
    return "BaiduFood [name=" + name + ", address=" + address + ", phone=" + phone + ", location=" + location
        + ", distance=" + distance + ", city=" + city + "]";
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * @param address
   *          the address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * @return the phone
   */
  public String getPhone() {
    return phone;
  }

  /**
   * @param phone
   *          the phone to set
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * @return the location
   */
  public LatLng getLocation() {
    return location;
  }

  /**
   * @param location
   *          the location to set
   */
  public void setLocation(LatLng location) {
    this.location = location;
  }

  /**
   * @return the distance
   */
  public double getDistance() {
    return distance;
  }

  /**
   * @param distance
   *          the distance to set
   */
  public void setDistance(double distance) {
    this.distance = distance;
  }

  /**
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * @param city
   *          the city to set
   */
  public void setCity(String city) {
    this.city = city;
  }

}
