package com.ohgiraffers.section02.controller;

import com.ohgiraffers.section02.dao.MenuDAO;
import com.ohgiraffers.section02.dto.MenuDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection2;

public class MenuController {

    private MenuDAO menuDAO = new MenuDAO("src/main/resources/mapper/menu-query.xml");

    public void findMaxCode(){

        int result = menuDAO.selectLastMenuCode(getConnection2());
        System.out.println("최신 메뉴 코드 : " + result);

    }

    public void findMenu(){
        List<Map> list = menuDAO.selectAllCategoryList(getConnection2());

        System.out.println("카테고리 리스트 : " + list);
    }

    public void insertMenu(){
        Scanner scr = new Scanner(System.in);
        MenuDTO menuDTO = new MenuDTO();

        System.out.println("추가할 메뉴 입력 : ");
        menuDTO.setMenuName(scr.nextLine());
        System.out.println("추가할 메뉴의 가격 입력 : ");
        menuDTO.setPrice(scr.nextInt());
        System.out.println("추가할 메뉴의 카테고리 코드 입력 : ");
        menuDTO.setCategoryCode(scr.nextInt());
        scr.nextLine();
        System.out.println("추가할 메뉴의 주문가능 여부 입력 : ");
        menuDTO.setOrderableStatus(scr.nextLine());

        int result = menuDAO.insertMenu(getConnection2(), menuDTO);
        if(result == 1){
            System.out.println("성공");
        }else{
            System.out.println("실패");
        }

    }

    /*public void insertMenu(){
        MenuDTO menuDTO = menuDAO.insertMenu(getConnection2());
        System.out.println("추가된 메뉴 : " + menuDTO);
    }*/



}
