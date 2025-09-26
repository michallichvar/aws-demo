package sk.aws.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@RestController
public class RegionController {

    @GetMapping("/region")
    public String getRegionAndAZ() {
        try {
            URL url = new URL("http://169.254.169.254/latest/meta-data/placement/availability-zone");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            Scanner scanner = new Scanner(url.openStream());
            String az = scanner.nextLine();
            scanner.close();

            String region = az.substring(0, az.length() - 1); // Remove the last character to get the region
            return "Region: " + region + ", Availability Zone: " + az;
        } catch (Exception e) {
            return "Error fetching region and AZ: " + e.getMessage();
        }
    }
}