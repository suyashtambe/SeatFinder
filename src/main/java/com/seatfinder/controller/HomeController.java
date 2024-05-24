package com.seatfinder.controller;

import com.seatfinder.arrangement.ExamSeatingArrangement;
import com.seatfinder.exception.ClassCapacityExceededException;
import com.seatfinder.repository.UserRepository;
import com.seatfinder.service.FileStorageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {


    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    public HomeController(UserRepository userRepository, FileStorageService fileStorageService) {
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    //    @PostMapping("/")
//    public String csv(@RequestParam("csv") MultipartFile csv ,@RequestParam("room") int room)  throws IOException, ClassCapacityExceededException {
////        if (!csv.isEmpty()) {
////            byte[] bytes = csv.getBytes();
////            String csvData = new String(bytes); // Convert bytes to string
//            ExamSeatingArrangement.seating(csv,room); // Call the seating method with CSV data
////        }
//        return "index";
//    }
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("csv") MultipartFile file,
                                   @RequestParam("room") int numberOfRooms) {
        // Check if the file is not empty
        if (file.isEmpty()) {
            // Handle empty file scenario
            return "redirect:/?error=emptyfile";
        }

        try {
            String fname = fileStorageService.storeFile(file);
            String filePath = "src/main/resources/static/seating_csv/" + fname;
            ExamSeatingArrangement seatingArrangement = new ExamSeatingArrangement(userRepository);
            seatingArrangement.seating(filePath, numberOfRooms);
        } catch (ClassCapacityExceededException e) {
            e.printStackTrace();
        }
        return "redirect:/";
//        } catch (IOException | ClassCapacityExceededException e) {
//            // Handle file processing error
//            e.printStackTrace();
//            return "redirect:/?error=fileprocessing";
//        }
    }

}
