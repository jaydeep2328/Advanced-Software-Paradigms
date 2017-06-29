package com.inventory.utility;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.log4j.Logger;



public class ImageOperations {
	public static void main(String args[]) {
		System.out.println(System.getProperty("user.dir"));
	}
	static Logger logForStaticMethods = Logger.getLogger(ImageOperations.class);
	public static boolean compressAndSaveImageOnSpecificPath(BufferedImage image, String outputPath, float compressionQuality) {
		
		try {
			
			File outputFile = new File(outputPath);
			OutputStream os = new FileOutputStream(outputFile);
			logForStaticMethods.info("outputPath ImageOperations >> "+outputPath);
			Iterator<ImageWriter>  writers = ImageIO.getImageWritersByFormatName("jpg");
			ImageWriter writer = writers.next();
			
			ImageOutputStream ios = ImageIO.createImageOutputStream(os);
			writer.setOutput(ios);
			
			
			ImageWriteParam param = writer.getDefaultWriteParam();
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			if((image.getWidth() * image.getHeight()) > 500) {
				param.setCompressionQuality(compressionQuality);
			}
			
			
			writer.write(null, new IIOImage(image, null, null), param);
			
			os.close();
			ios.close();
			writer.dispose();
			
			return true;
			
		} catch (IOException e) {
			e.printStackTrace();
			logForStaticMethods.error("error in ImageOperations >> "+e);
			return false;
		}
	}
	
	
	public static BufferedImage resizeImage(BufferedImage image, int newW, int newH) {
		
		int w = image.getWidth();
		int h = image.getHeight();
		
		BufferedImage newImage = new BufferedImage(newW, newH, image.getType());
		
		Graphics2D g = newImage.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(image, 0, 0, newW, newH, 0, 0, w, h, null);
		
		return newImage;
		
	}
	
	public static String resizeDataUrlImage(String dataUrl, byte[] imageData, int newW, int newH) {
		
		try {
			
			if(dataUrl != null && !dataUrl.equalsIgnoreCase("") && dataUrl.contains("base64,")){
				
				//Split encoded string and data:image/jpeg;base64, 
				String dataUrlArray[] = dataUrl.split("base64,");
				
				//Decode Encoded String which is byte array.
				imageData = Base64.decode(dataUrlArray[1]);
			}		
			
			
			//Convert Byte array to buffered image and resize it.
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageData));
			image = resizeImage(image, newW, newH);
			
			
			//convert resized image to byte array.
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			
			//Convert byte array to encoded data url.
			dataUrl ="data:image/jpeg;base64,"+Base64.encode(imageInByte);
					
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dataUrl;
		
	}
	
}
