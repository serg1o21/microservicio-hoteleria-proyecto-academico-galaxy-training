package com.proyectosmera.ms_hotelgema_gestion_reservas.utils;

import java.time.LocalDate;
import java.util.Random;

public class CodigoReservaUtil {

  private static final Random random = new Random();

  public static String generarCodigoReserva() {
    String prefijo = "HG";
    String fecha = LocalDate.now().toString().replace("-", "");
    String randomCode = generarRandomAlfanumerico(6);

    return prefijo + "-" + fecha + "-" + randomCode;
  }

  private static String generarRandomAlfanumerico(int length) {
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < length; i++) {
      sb.append(chars.charAt(random.nextInt(chars.length())));
    }

    return sb.toString();
  }
}
