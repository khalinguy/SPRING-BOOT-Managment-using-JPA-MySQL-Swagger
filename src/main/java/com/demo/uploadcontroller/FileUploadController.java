package com.demo.uploadcontroller;


import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class FileUploadController {

	//private final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "D:\\uploads\\";
	

	// ======================== SINGLE UPLOAD =============
	@PostMapping("/file/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile) {

		//logger.debug("Single file upload!");

		if (uploadfile.isEmpty()) {
			return new ResponseEntity<>("You must select a file!", HttpStatus.OK);
		}

		try {

			saveUploadedFiles(Arrays.asList(uploadfile));

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Successfully uploaded - " + uploadfile.getOriginalFilename() + "["+uploadfile.getSize()+"]", new HttpHeaders(), HttpStatus.OK);

	}

    // ====================== MULTIPLE UPLOADS ====================
	@RequestMapping(value = "/file/multipleupload", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public ResponseEntity<?> uploadFile(@RequestParam("uploadfiles") MultipartFile[] uploadfiles) {
		
		System.out.println("Length: " + uploadfiles.length);

		// Get file name 
		
		String uploadedFileName = Arrays.stream(uploadfiles).map(x
		-> x.getOriginalFilename()) .filter(x ->
		!StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));
		System.out.println(uploadedFileName); if
		(StringUtils.isEmpty(uploadedFileName)) { return new
				ResponseEntity<>("please select a file!", HttpStatus.OK); }

		try {

			saveUploadedFiles(Arrays.asList(uploadfiles));

		} catch (IOException e) { return new
				ResponseEntity<>(HttpStatus.BAD_REQUEST); }

		return new ResponseEntity<>("Successfully uploaded - " + uploadedFileName,
				HttpStatus.OK); }
	 
		
		/*String names = "";

		for (MultipartFile uploadfile: uploadfiles) { 
			
			if (uploadfile.isEmpty()) {
			return new ResponseEntity<>("You must select a file!", HttpStatus.OK); 		
			}

			try {
	
				saveUploadedFiles(Arrays.asList(uploadfile)); names +=
						uploadfile.getOriginalFilename() + ": "+ (int) (uploadfile.getSize()); names
						+= "&/n";
	
			} catch (IOException e) { return new
					ResponseEntity<>(HttpStatus.BAD_REQUEST); }
	
	
	
			}
	
			return new ResponseEntity<>("Successfully uploaded - " + names, headers, HttpStatus.OK);
		
		}	*/

		
		  
		
	// ==================== DOWNLOAD FILE ========================
		@RequestMapping(path = "/file/download/", method = RequestMethod.GET)
		public ResponseEntity<Resource> download(@RequestParam(name = "fileName") String fileName, HttpServletRequest request) throws IOException {
			
			
			String param = UPLOADED_FOLDER + fileName;
			File file = new File(param);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			headers.add("Pragma", "no-cache");
			headers.add("Expires", "0");
				
			
			//****
			Path path = Paths.get(file.getAbsolutePath());
			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

			return ResponseEntity.ok().headers(headers).contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
		}
		//====================== LIST ALL FILES IN FOLDER ================
		@RequestMapping(path = "/file/allfiles",  method = RequestMethod.GET)
		public List<ResponseFile> listFilesUsingDirectoryStream() throws IOException {
			
		    List<ResponseFile> fileList = new ArrayList<>();
		    try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(UPLOADED_FOLDER))) {
		        for (Path path : stream) {
		            if (!Files.isDirectory(path)) {
		            	ResponseFile newFile = new ResponseFile();
		            	newFile.setFileName(path.getFileName().toString());
		            	newFile.setLink(UPLOADED_FOLDER+path.getFileName().toString());
		            	newFile.setFileSize(Long.toString(new File(newFile.getLink()).length()) + " byte");
		            	fileList.add(newFile);
		            		
		            }
		        }
		            
		    }
		    
		    return fileList;
		}
		
		// ====================== SAVE FILE ===========================
		private void saveUploadedFiles(List<MultipartFile> files) throws IOException {
			for (MultipartFile file : files) {
				if (file.isEmpty()) {
					continue;
				}
				byte[] bytes = file.getBytes();
				Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
				Files.write(path, bytes);
			}
		}
	}