package ex08_10;
import java.io.*;

public class BinaryCopyEx {
	public static void main(String[] args) {
		File src = new File("c:\\Temp\\srcimg.jpg");
		File dest = new File("c:\\Temp\\copyimg.jpg");
		long start = System.currentTimeMillis();
		int c;
		try {
			FileInputStream fi = new FileInputStream(src);
			FileOutputStream fo = new FileOutputStream(dest);
			while((c = fi.read()) != -1) {
				fo.write((byte)c);
			}
			fi.close();
			fo.close();
			long end = System.currentTimeMillis();
			double seconds = (end-start)/1000.0;
			System.out.println(src.getPath() + "를 " + dest.getPath() + "로 복사하였습니다.");
			System.out.println("복사 시간은 " + seconds + "초입니다.");
		} catch(IOException e) {
			System.out.println("파일 복사 오류");
		}
	}
}
