package com.changgou.file.util;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * @Author caoqian
 * @ClassName FastDFSUtil 文件上传工具类
 * @Date 2020/3/24 10:41
 * @Version 1.0
 */
public class FastDFSUtil {

    /**
     * 加载Tracker链接信息
     */
    static {
        try {
            String fileName = new ClassPathResource("fdfs_client.conf").getPath();
            //加载Tracker链接信息
            ClientGlobal.init(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     *
     * @param fastDFSFile 文件上传信息封装
     * @return String[]
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception {
        //附加参数
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", fastDFSFile.getAuthor());
        //创建一个Tracker访问的客户端对象TrackerClient
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient访问TrackerServer服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer的链接信息可以获取Strange的链接信息，创建StrangeClient对象存储Strange的链接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        /**
         * 通过StorageClient访问Storage，实现文件上传，并且获取文件上传后的存储信息
         * @prarm 上传文件的字节数组+文件的扩展名+附加参数
         *
         * @retrun uploads[]
         *         uploads[0]:文件上传所存储的Storage的组的名字 group1
         *         uploads[1]:文件存储到Storage上的文件名字 M00/02/xxx.jpg
         */
        String[] uploads = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), meta_list);
        return uploads;

    }

    /**
     * 文件下载
     *
     * @param groupName      文件的组名
     * @param remoteFileName 文件存储的路径的名字
     * @return InputStream
     */
    public static InputStream downloadFile(String groupName, String remoteFileName) throws Exception {
        //创建一个TrackerClient对象，通过TrackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer连接对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer获取Storage信息，创建StorageClint对象存储Storage信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //文件下载
        byte[] download_file = storageClient.download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(download_file);

    }

    /**
     * 文件删除
     *
     * @param groupName      文件的组名
     * @param remoteFileName 文件存储的路径的名字
     * @throws Exception
     */
    public static void deleteFile(String groupName, String remoteFileName) throws Exception {
        //创建一个TrackerClient对象，通过TrackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer连接对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer获取Storage信息，创建StorageClint对象存储Storage信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //删除文件
        storageClient.delete_file(groupName, remoteFileName);
    }

    /**
     * 获取文件信息
     *
     * @param groupName      文件的组名
     * @param remoteFileName 文件存储的路径的名字
     * @return FileInfo
     * @throws IOException
     * @throws MyException
     */
    public static FileInfo getFile(String groupName, String remoteFileName) throws IOException, MyException {
        //创建一个TrackerClient对象，通过TrackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer连接对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer获取Storage信息，创建StorageClint对象存储Storage信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //获取文件信息
        return storageClient.get_file_info(groupName, remoteFileName);
    }

    /**
     * 获取Storage信息
     *
     * @return StorageServer
     * @throws Exception
     */
    public static StorageServer getStorage() throws Exception {
        //创建一个TrackerClint对象，通过TrackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer
        TrackerServer trackerServer = trackerClient.getConnection();

        //获取Storage信息
        return trackerClient.getStoreStorage(trackerServer);
    }

    /**
     * 获取Storage的ip和端口信息
     *
     * @param groupName      文件的组名
     * @param remoteFileName 文件存储的路径的名字
     * @return
     */
    public ServerInfo[] getServerInfo(String groupName, String remoteFileName) throws Exception {
        //创建一个TrackerClint对象，通过TrackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer
        TrackerServer trackerServer = trackerClient.getConnection();

        //获取ip和端口信息
        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
    }

    /**
     * 获取Tracker信息
     *
     * @return
     * @throws Exception
     */
    public static String getTrackerInfo() throws Exception {
        //创建一个TrackerClint对象，通过TrackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer
        TrackerServer trackerServer = trackerClient.getConnection();
        //Tracker的IP、HTTP端口
        String ip = trackerServer.getInetSocketAddress().getHostString();
        int tracker_http_port = ClientGlobal.getG_tracker_http_port();
        String url = "http://" + ip + ":" + tracker_http_port;
        return url;
    }
}
