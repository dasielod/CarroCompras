package com.carrocompra.demo.controller.utils;

import com.carrocompra.demo.controller.ClientController;
import com.carrocompra.demo.controller.ProductController;
import com.carrocompra.demo.controller.VentaController;
import com.carrocompra.demo.controller.UsuarioController;

public class ControllerMapper {

    public static void writeLog(String name, String text) {
        if (name.contains(ClientController.class.getCanonicalName())) {
            ClientController.writeLog(text);

        } else {
            if (name.contains(VentaController.class.getCanonicalName())) {
                VentaController.writeLog(text);
            } else {
                if (name.contains(ProductController.class.getCanonicalName())) {
                    ProductController.writeLog(text);

                } else {
                    if (name.contains(UsuarioController.class.getCanonicalName())) {
                        UsuarioController.writeLog(text);

                    }
                }
            }
        }
    }
}