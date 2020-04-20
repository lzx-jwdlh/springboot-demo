package com.alzz.demo.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Component
public class SCPClientUtils {
    //资源服务器的IP
    @Value("${xnjserviceUrl}")
    private String serviceIP;
    //资源服务器的用户名
    @Value("${xnjuser}")
    private String username;
    //资源服务器的密码
    @Value("${xnjpassword}")
    private String password;

    /**
     * 上传文件
     *
     * @param data     文件字节数组
     * @param basePath 资源服务器基础路径
     * @param filePath 资源服务器保存路径
     * @param fileName 文件名称
     * @return
     */
    public boolean uploadFile(byte[] data, String basePath, String filePath, String fileName) {
        boolean flag = false;
        Connection conn = new Connection(serviceIP);
        if (conn == null) throw new RuntimeException("服务器连接出错");

        //方便关闭
        Session session = null;
        SCPClient client = null;
        try {
            //建立连接
            conn.connect();
            //认证校验
            boolean b = conn.authenticateWithPassword(username, password);
            if (!b) throw new RuntimeException("资源服务器用户名或密码错误");

            //创建session,执行命令
            session = conn.openSession();
            //创建文件夹,执行创建多级目录命令 mkdir " + basePath+filePath +" -p"
            session.execCommand("mkdir " + basePath + filePath + " -p");
            //打印执行命令后的信息   StreamGobbler:把控制台返回信息包装成inputStream
            InputStream is = new StreamGobbler(session.getStdout());
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            //输出信息
            while (true) {
                String s = bf.readLine();
                if (s == null) break;
                System.out.println(serviceIP + " Info: " + s);
            }
            //获取结束状态
            System.out.println("session,exitCode:" + session.getExitStatus());

            //建立SCP客户端
            client = new SCPClient(conn);
            String savePath = basePath + filePath;
            //上传文件
            client.put(data, fileName, savePath);
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        //返回上传结果
        return flag;
    }

    /**
     * 上传文件
     *
     * @param data     文件字节数组
     * @param filePath 资源服务器保存路径
     * @param fileName 文件名称
     * @return location 上传路径
     */
    @SuppressWarnings("unused")
    public String uploadFile(byte[] data, String filePath, String fileName) {
        String location = null;
        Connection conn = new Connection(serviceIP);
        if (conn == null)
			try {
				throw new Exception("资源服务器连接出错");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

        //方便关闭
        Session session = null;
        SCPClient client = null;
        try {
            //建立连接
            conn.connect();
            //认证校验
            boolean b = conn.authenticateWithPassword(username, password);
            if (!b)
				try {
					throw new Exception("资源服务器用户名或密码错误");
				} catch (Exception e) {
					e.printStackTrace();
				}

            //创建session,执行命令
            session = conn.openSession();
            //创建文件夹,执行创建多级目录命令 mkdir " + basePath+filePath +" -p"
            session.execCommand("mkdir " + filePath + " -p");

            //建立SCP客户端
            client = new SCPClient(conn);
            //上传文件
            client.put(data, fileName, filePath);
            location = filePath + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return location;
    }

    /**
     * 下载文件
     *
     * @param resourcePath
     * @param response
     * @return
     */
    public  boolean downloadFile(String resourcePath, HttpServletResponse response) {
        boolean flag = false;
        //建立连接
        Connection conn = new Connection(serviceIP);
        //方便关闭
        Session session = null;
        SCPClient client = null;
        try {
            //认证校验
            conn.connect();
            boolean b = conn.authenticateWithPassword(username, password);
            if (!b) throw new RuntimeException("资源服务器用户名或密码错误");

            //建立SCP客户端
            client = new SCPClient(conn);
            //下载文件
            client.get(resourcePath, response.getOutputStream());
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        //返回下载结果
        return flag;
    }

    /**
     * 把文件下载到本地临时文件夹
     * @param resourcePaths  资源路径数组
     * @return
     */
    public  void downloadFile(List<String> resourcePaths) {
        //建立连接
        Connection conn = new Connection(serviceIP);
        //方便关闭
        Session session = null;
        SCPClient client = null;
        try {
            //认证校验
            conn.connect();
            boolean b = conn.authenticateWithPassword(username, password);
            if (!b) throw new RuntimeException("资源服务器用户名或密码错误");

            //建立SCP客户端
            client = new SCPClient(conn);

            //建立文件夹
            File file = new File("TempFiles");
            //判断文件夹是否存在
            if(!file.exists()){
                file.mkdirs();    //没有创建
            }
            //下载文件
            for (String resourcePath : resourcePaths) {
                client.get(resourcePath,file.getPath());
            }
        } catch (IOException e) {
            throw new RuntimeException("文件下载失败:",e);
        } finally {
            if (session != null) {
                session.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}