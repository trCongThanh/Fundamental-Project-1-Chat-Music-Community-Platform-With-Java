package DAO;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;


public class cloudAction {
	private Cloudinary cloudinary;
	public cloudAction()
	{
		  String cloudinaryUrl = "cloudinary://762113227428253:D-yUSTUfDhZvp6i9-g-fIse9gyo@dpijpoaed";

	        // Create a Cloudinary object from the URL
	      cloudinary = new Cloudinary(cloudinaryUrl);
	}
	public ArrayList<String> layLinkvalayPic(String idBaihat)
	{
		ArrayList<String> arrString = new ArrayList<String>();
		try {
			ApiResponse response = cloudinary.api().resources(ObjectUtils.asMap(
				    "resource_type", "video",
				    "type", "upload",
				    "prefix", "File nhac/",
				    "max_results", 500 // Adjust max_results as needed
				));
	            // Extract URLs from the response
	            @SuppressWarnings("unchecked")
	            List<Map<String, Object>> resources = (List<Map<String, Object>>) response.get("resources");
	            for (Map<String, Object> resource : resources) {
	                String publicId = (String) resource.get("public_id");
	                String url = (String) resource.get("url");
	                if(publicId.contains("id"+idBaihat)) {
	                arrString.add(url);
	                System.out.println("Public ID: " + publicId);
	                System.out.println("URL: " + url);
	                break;
	                }
	            }
			ApiResponse imgResponse = cloudinary.api().resources(ObjectUtils.asMap(
				    "resource_type", "image",
				    "type", "upload",
				    "prefix", "File Anh/",
				    "max_results", 500
				));

				@SuppressWarnings("unchecked")
				java.util.List<java.util.Map<String, Object>> imgResources = (java.util.List<java.util.Map<String, Object>>) imgResponse.get("resources");

				for (java.util.Map<String, Object> resource : imgResources) {
				    String publicId = (String) resource.get("public_id");
				    String urlJpg = cloudinary.url().resourceType("image").format("jpg").version(resource.get("version")).generate(publicId);
				    String urlJfif = cloudinary.url().resourceType("image").format("jfif").version(resource.get("version")).generate(publicId);
				    String urlPng = cloudinary.url().resourceType("image").format("png").version(resource.get("version")).generate(publicId);
				    // Kiểm tra xem publicId chứa "File Anh" và "idBaihat"
				    if (publicId.contains("File Anh") && publicId.contains("id"+idBaihat)) {
				        arrString.add(urlJpg); // Thêm URL jpg vào danh sách
				        System.out.println("Tên: " + publicId);
				        break;
				    }
				}
      } 
		catch (Exception e) {
			System.out.println("No bro");
          e.printStackTrace();
      }
		return arrString;
	}
}
