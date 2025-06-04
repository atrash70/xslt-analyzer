package postnl.cbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import postnl.cbs.service.XsltService;
import postnl.cbs.model.XsltAnalysisResult;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/xslt")
public class XsltController {

    private static final Logger logger = LoggerFactory.getLogger(XsltController.class);

    @Autowired
    private XsltService xsltService;

    @PostMapping("/analyze")
    public ResponseEntity<?> analyzeXslt(@RequestParam("xslt") MultipartFile xsltFile) {
        logger.info("Received XSLT file for analysis");
        try {
            String xsltContent = new String(xsltFile.getBytes(), StandardCharsets.UTF_8);
            XsltAnalysisResult result = xsltService.analyzeXslt(xsltContent);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error processing XSLT", e);
            return ResponseEntity.badRequest().body("Invalid XSLT: " + e.getMessage());
        }
    }
}