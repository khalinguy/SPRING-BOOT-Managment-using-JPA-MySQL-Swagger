package com.demo.xlsReader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.User;

@RestController
@RequestMapping("/reader")
public class FileReaderController {
	@Autowired 
	xlsReader reader;
	
	private static String STORED_FOLDER = "D:\\uploads\\";
	
	@RequestMapping(path = "/uploadxlsx", method = RequestMethod.GET)
	public List<User> download(@RequestParam(name = "fileName") String fileName, HttpServletRequest request) throws IOException {
		String param = STORED_FOLDER + fileName + ".xlsx";
		File file = new File(param);
		return reader.importUsers(file);
	}
}
