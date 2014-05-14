import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FileRecv {
	public static void main(String...s)
    {

        File file=new File("../IncidentReport.mp4");
        try    
        {      
        	@SuppressWarnings("resource")
			ServerSocket serverSock=new ServerSocket(12345);
        	while (true) {
	        	Socket sock = serverSock.accept();
	            BufferedInputStream bis=new BufferedInputStream(sock.getInputStream());
	            FileOutputStream fos = new FileOutputStream(file);
	            int n;
	            byte[] buffer = new byte[8192];
	            System.out.println("Connected");
	            while ((n = bis.read(buffer)) > -1) {
	                System.out.println("bytes="+n);
	                fos.write(buffer, 0, n);
	                /*if(n<(8192)){
	                    fos.close();
	                    bis.close();
	                    break;
	                } */
	                fos.flush();
	            }
	            fos.close();
	            System.out.println("Recieved at" + 
	            		new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        	}
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
