package com.webrixtec.buildandbeautify.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.webrixtec.buildandbeautify.exception.ExceptionController;
import com.webrixtec.buildandbeautify.pojo.FileStorageProperties;

@Service
@PropertySource(value = { "classpath:application.properties" })
public class FileStorageService extends ExceptionController{
	
	@Value("${file.upload-dir}")
	String filepath;

	private final String endpointUrl="https://buildandbeautify.s3.ap-south-1.amazonaws.com";
	private final String bucketName="buildandbeautify";
	 private String accessKey ="AKIA2RJHWFHFI57K4NOS";
	 private String secretKey="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";
	 
	 
	  private AmazonS3 s3client;
		
	@PostConstruct
	    private void initializeAmazon() {
	        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
	         s3client = AmazonS3ClientBuilder.standard().withRegion("ap-south-1").withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
	    }
	
	 private final Path fileStorageLocation;
	    int length = 15;
	    boolean useLetters = true;
	    boolean useNumbers = true;
	    @Autowired
	    public FileStorageService(FileStorageProperties fileStorageProperties) throws Exception {
	        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
	                .toAbsolutePath().normalize();

	        try {
	            Files.createDirectories(this.fileStorageLocation);
	        } catch (Exception ex) {
	            throw new Exception("Could not create the directory where the uploaded files will be stored.", ex);
	        }
	    }

	    public String storeFile(MultipartFile file) throws Exception {
	        // Normalize file name
	        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
	        String name = FilenameUtils.removeExtension(file.getOriginalFilename());
	        String fileName = name+new Date().getTime()+"."+ext;
	        try {
	            // Check if the file's name contains invalid characters
	            if(fileName.contains("..")) {
	                throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
	            }

	            // Copy file to the target location (Replacing existing file with the same name)
	            Path targetLocation = this.fileStorageLocation.resolve(fileName);
	            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

	            return fileName;
	        } catch (IOException ex) {
	            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
	        }
	    }

	    public Resource loadFileAsResource(String fileName) throws Exception {
	        try {
	            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	                return resource;
	            } else {
	                throw new Exception("File not found " + fileName);
	            }
	        } catch (MalformedURLException ex) {
	            throw new Exception("File not found " + fileName, ex);
	        }
	    }
	    
	    
		public void writeByte(byte bytes[], String filename) throws Exception {
			File file = new File(filepath + filename);
			System.out.println(filename);

			ImageIO.read(file);
		}

		public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
			Path filepath1 = Paths.get(filepath, multipart.getOriginalFilename());
			 String ext = FilenameUtils.getExtension(multipart.getOriginalFilename());
		        String name = FilenameUtils.removeExtension(multipart.getOriginalFilename());
		        
			multipart.transferTo(filepath1);
//			String fileName = multipart.getOriginalFilename();
			String fileName = name+new Date().getTime()+"."+ext;
//			String[] dStirng = fileName.split(Pattern.quote("."));
			File convFile1 = File.createTempFile(filepath + fileName, null);
			return convFile1;
		}

		public static byte[] readContentIntoByteArray(File file) {
			FileInputStream fileInputStream = null;
			byte[] bFile = new byte[(int) file.length()];
			try {
				fileInputStream = new FileInputStream(file);
				fileInputStream.read(bFile);
				fileInputStream.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return bFile;
		}
		

//		 BasicAWSCredentials creds = new BasicAWSCredentials("access_key", "secret_key"); 
		

		    public String uploadFile(MultipartFile multipartFile,String folderName) {
		        String fileUrl = "";
		        try {
		            File file = convertMultiPartToFile(multipartFile);
		            String fileName = generateFileName(multipartFile);
		            fileUrl = endpointUrl + "/" + bucketName + "/"+folderName+"/" + fileName;
		            uploadFileTos3bucket(fileName, file,folderName);
		            file.delete();
		        } catch (Exception e) {
		           e.printStackTrace();
		        }
		        return fileUrl;
		    }
		    private File convertMultiPartToFile(MultipartFile file) throws IOException {
		        File convFile = new File(file.getOriginalFilename());
		        FileOutputStream fos = new FileOutputStream(convFile);
		        fos.write(file.getBytes());
		        fos.close();
		        return convFile;
		    }

		    private String generateFileName(MultipartFile multiPart) {
		        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
		    }

		    private void uploadFileTos3bucket(String fileName, File file,String folderName) {
		        s3client.putObject(new PutObjectRequest(bucketName, folderName+"/"+fileName, file)
		                .withCannedAcl(CannedAccessControlList.PublicRead));
		    }
}
