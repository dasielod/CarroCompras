package com.shoppingcar.demo.controller.utils;

import com.shoppingcar.demo.controller.*;

public class ControllerMapper {

    public static void writeLog(String name, String text) {
        if (name.contains(ClientController.class.getCanonicalName())) {
            ClientController.writeLog(text);

        } else {
            if (name.contains(SaleController.class.getCanonicalName())) {
                SaleController.writeLog(text);
            } else {
                if (name.contains(ProductController.class.getCanonicalName())) {
                    ProductController.writeLog(text);

                } else {
                    if (name.contains(UserController.class.getCanonicalName())) {
                        UserController.writeLog(text);

                    }
                }
            }
        }
    }
}