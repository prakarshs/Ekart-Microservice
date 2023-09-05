package com.project.UserService.Services;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.project.UserService.Configs.KafkaConfigs;
import com.project.UserService.Entities.Session;
import com.project.UserService.Entities.User;
import com.project.UserService.Models.UserRequest;
import com.project.UserService.Models.UserResponse;
import com.project.UserService.Repositories.SessionRepository;
import com.project.UserService.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@Data
@AllArgsConstructor
public class UserServiceIMPL implements UserService {
    @Autowired
    private KafkaConfigs kafkaConfigs;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        log.info("CHECKING USER...");
        if (userRepository.findByUserName(userRequest.getUserName()) == null) {
            log.info("CREATING USER...");
            User user = User.builder()
                    .userName(userRequest.getUserName())
                    .userEmail(userRequest.getUserEmail())
                    .build();
            userRepository.save(user);
            return UserResponse.builder()
                    .message("USER HAS BEEN ADDED.")
                    .userName(user.getUserName())
                    .userEmail(user.getUserEmail())
                    .build();
        } else {
            log.info("USER ALREADY EXISTS");
            return UserResponse.builder()
                    .message("USER ALREADY EXISTS.")
                    .build();
        }
    }

    @Override
    public UserResponse addSession(UserRequest userRequest) {

        log.info("CREATING SESSION...");
        Session session = Session.builder()
                .userName(userRequest.getUserName())
                .userEmail(userRequest.getUserEmail())
                .startTime(Instant.now())
                .build();
        sessionRepository.save(session);
        return UserResponse.builder()
                .message("SESSION HAS BEEN ADDED.")
                .userName(session.getUserName())
                .userEmail(session.getUserEmail())
                .build();
    }

    @Override
    public UserResponse showLatestSession() {
        Optional<Session> latestSession = sessionRepository.findFirstByOrderByUsersIdDesc();

        UserResponse userResponse = new UserResponse();
        latestSession.ifPresent(session -> {
            userResponse.setMessage("THIS IS THE LATEST SESSION");
            userResponse.setUserName(session.getUserName()); // Assuming getName() returns name
            userResponse.setUserEmail(session.getUserEmail()); // Assuming getEmail() returns email
        });
        return userResponse;
    }

    @Override
    public byte[] generatePdf() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Rectangle pageSize= new Rectangle(PageSize.A4.getHeight(), PageSize.A4.getWidth()); // Swap width and height
        Document document = new Document(pageSize);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Font Styling
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 36, new Color(84, 105, 212));
        Font subheadingFont = FontFactory.getFont(FontFactory.HELVETICA, 23, new Color(0,0,129));
        Font subtitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 13, new Color(0,0,0));
        Font pathlistFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 20, new Color(54, 69, 79));
        Font endcontentFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 13, new Color(54, 69, 79));
        Font footertextFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, new Color(105,105,105));
        Font footeremailFont = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10, new Color(0,0,129));

        // Element Declaration
        Image logo = Image.getInstance("D:/Microservices/Ekart/UserService/src/main/resources/images/logo.png");
        logo.setAlignment(Element.ALIGN_CENTER);
        logo.scaleToFit(90, 90);

        Image design = Image.getInstance("D:/Microservices/Ekart/UserService/src/main/resources/images/design.png");
        design.scaleToFit(300, 280);

        Paragraph subtitle = new Paragraph("Ekart Microservice Project",subtitleFont);
        subtitle.setAlignment(Element.ALIGN_CENTER);


        Paragraph heading = new Paragraph("Explore Your Journey In Our Ekart Website",headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);


        Paragraph space = new Paragraph(" ");
        heading.setAlignment(Element.ALIGN_CENTER);


        Paragraph subheading = new Paragraph("You traversed the following path and interacted likewise in your session:", subheadingFont);
        subheading.setAlignment(Element.ALIGN_CENTER);


        Paragraph pathlist = new Paragraph(String.valueOf(kafkaConfigs.getMessageWindow()),pathlistFont);
        pathlist.setAlignment(Element.ALIGN_CENTER);


        Paragraph endcontent = new Paragraph("Thanks For Your Visit !!", endcontentFont);
        endcontent.setAlignment(Element.ALIGN_CENTER);

        Paragraph footertext = new Paragraph("Project Developed By © Prakarsh Srivastava", footertextFont);


        Paragraph footeremail = new Paragraph("prakarsh2101@gmail.com", footeremailFont);

        // Positioning design
        float pageWidth = document.getPageSize().getWidth();
        float pageHeight = document.getPageSize().getHeight();
        // Set the X and Y coordinates
        float x = pageWidth - design.getScaledWidth();
        float y = 0; // You can adjust this value to control the vertical position
        design.setAbsolutePosition(x, y);








        // Arranging Elements in Doc
        document.add(logo);
        document.add(subtitle);
        document.add(heading);
        document.add(space);
        document.add(subheading);
        document.add(space);
        document.add(space);
        document.add(pathlist);
        document.add(space);
        document.add(space);
        document.add(endcontent);
        document.add(space);
        document.add(space);
        document.add(space);
        document.add(space);
        document.add(design);
        document.add(footertext);
        document.add(footeremail);




        document.close();
        return outputStream.toByteArray();
    }


}

