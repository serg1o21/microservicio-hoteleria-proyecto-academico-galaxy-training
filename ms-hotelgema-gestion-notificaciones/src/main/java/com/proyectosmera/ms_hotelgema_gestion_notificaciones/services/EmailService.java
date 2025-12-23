package com.proyectosmera.ms_hotelgema_gestion_notificaciones.services;
import com.proyectosmera.ms_hotelgema_gestion_notificaciones.dto.DetalleDto;
import jakarta.mail.internet.MimeMessage;
import java.util.List;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class EmailService {

  private final JavaMailSender mailSender;
  private final TemplateEngine templateEngine;

  public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
    this.mailSender = mailSender;
    this.templateEngine = templateEngine;
  }

  public void sendReservaEmail(String to, String nombre, String numReserva, String checkIn, String checkOut, Double total, List<DetalleDto> detalle) throws Exception {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

    Context context = new Context();
    context.setVariable("nombre", nombre);
    context.setVariable("numReserva", numReserva);
    context.setVariable("checkIn", checkIn);
    context.setVariable("checkOut", checkOut);
    context.setVariable("total", total);
    context.setVariable("detalle", detalle);

    String html = templateEngine.process("emailtemplate.html", context);

    helper.setTo(to);
    helper.setSubject("Confirmación de Reserva");
    helper.setText(html, true);

    mailSender.send(message);
  }
}
