package com.demo.xlsReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.User;
import com.demo.module.user.UserService;
@Service
public class xlsReader {
	@Autowired
	private UserService userService;
	List<User> userList = new ArrayList<>();
	
	public List<User> importUsers(File rfile){
		try
        {
			FileInputStream file = new FileInputStream(rfile);
            //Create Workbook instance holding reference 
            XSSFWorkbook workbook = new XSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
 
            
            //Ignore header
            for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
            	User user = new User();
            	Row row = sheet.getRow(i);
            	for (int x=row.getFirstCellNum(); x<=row.getLastCellNum(); x++) {
            		Cell cell = row.getCell(x);
            		
            		if (x == 0) {
            			
            		}
            		
            		if (x == 1) {
            			user.setAddress(cell.getStringCellValue());
            		}
            		
            		if (x == 2) {
            			user.setAge((int)(cell.getNumericCellValue()));
            		}
            		
            		if (x == 3) {
            			user.setCode(Integer.toString((int)(cell.getNumericCellValue())));
            		}
            		
            		if (x == 4) {
            			user.setName(cell.getStringCellValue());
            		}
            	}
            	if (userService.isUserDuplicate(user)) {
            		System.out.println("UPDATED!");
            		updateUserInList(user);
            		
            	} else {
            		System.out.println("CREATED!");
            		userList.add(user);
            		userService.createUser(userService.convertToDTO(user));
				 } 
            
            	
            }
            
            file.close();
            return this.userList;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
		
	}
	
	public void updateUserInList(User user){
		for (User u: this.userList) {
			if (user.getCode().equals(u.getCode())) {
				u.setAddress(user.getAddress());
				u.setAge(user.getAge());
				u.setName(user.getName());
			}
		}
		
		
	}
}
