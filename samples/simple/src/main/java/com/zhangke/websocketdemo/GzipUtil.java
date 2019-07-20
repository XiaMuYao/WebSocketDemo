package com.zhangke.websocketdemo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {
 
	public static byte[] compress(String str)  {
		ByteArrayOutputStream out =null;
		GZIPOutputStream gzip=null;
		try{
			if (str == null || str.length() == 0) {
				return null;
			}
			out = new ByteArrayOutputStream();
			gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes("utf-8"));
			gzip.finish();
			return out.toByteArray();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{
				if(out!=null){
					out.close();
				}
				if(gzip!=null){
					gzip.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
 
	public static String unCompress(byte []by) {
		ByteArrayOutputStream out=null;
		GZIPInputStream gunzip=null;
		try{
			if(by==null || by.length==0){
				return "";
			}
			out=new ByteArrayOutputStream();
			gunzip= new GZIPInputStream(new ByteArrayInputStream(by));
			byte[] buffer = new byte[1024];
			int n;
			while ((n=gunzip.read(buffer))!=-1) {
				out.write(buffer, 0, n);
			}
			out.flush();
			return new String(out.toByteArray(),"utf-8");
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}finally{
			try{
				if(out!=null){
					out.close();
				}
				if(gunzip!=null){
					gunzip.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		
		String str = "806715668,1091464537,1061006120,1142513520";
		
		System.out.println(str.getBytes("utf-8").length);
		System.out.println(compress(str).length);
		System.out.println(unCompress(compress(str)));
		
	}
 
}
