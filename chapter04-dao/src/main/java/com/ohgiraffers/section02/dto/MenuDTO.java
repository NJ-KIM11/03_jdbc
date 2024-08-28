package com.ohgiraffers.section02.dto;

public class MenuDTO {
    private String menuName;
    private int menuPrice;
    private int categoryCode;
    private String orderableStatus;

    public MenuDTO() {
    }

    public MenuDTO(String menuName, int menuPrice, int categoryCode, String orderableStatus) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public String getMenuName() {
        return menuName;
    }

    public MenuDTO setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public MenuDTO setPrice(int menuPrice) {
        if (menuPrice <= 0) {
            System.out.println("음수가 입력됨...");
        }else {
            this.menuPrice = menuPrice;
        }
        return this;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public MenuDTO setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
        return this;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public MenuDTO setOrderableStatus(String orderableStatus) {
        if(orderableStatus.equals("예") || orderableStatus.equals("Y")){
            this.orderableStatus = "Y";
        } else if (orderableStatus.equals("아니오") || orderableStatus.equals("N")){
            this.orderableStatus = "N";
        } else {
            System.out.println("잘못 입력됨..");
        }
        return this;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", categoryCode=" + categoryCode +
                ", orderableStatus=" + orderableStatus +
                '}';
    }
}
