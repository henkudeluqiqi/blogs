package org.king2.blogs.appoint;

import org.king2.blogs.dto.OpenBLogsDto;
import org.king2.blogs.enums.BLogSystemState;
import org.king2.blogs.excep.CheckValueException;
import org.king2.blogs.locks.BLogsWriteLock;
import org.king2.blogs.pojo.BLogsArticle;
import org.king2.blogs.pojo.BLogsArticleWithBLOBs;
import org.king2.blogs.pojo.BLogsUser;
import org.king2.blogs.result.SystemResult;
import org.king2.blogs.utils.MD5Utils;
import org.king2.blogs.utils.MinioUtil;
import org.king2.webkcache.cache.interfaces.WebKingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 写文章的委派类
 */
@Component
@SuppressWarnings("all")
public class BLogsWriteAppoint {


    @Autowired
    private WebKingCache webKingCache;

    /**
     * 用户是否在缓存中存过文章的key
     */
    public static final String USER_LOGS_KEY = "WRITE_LOGS_KEY";

    /**
     * 用户文章的详细信息
     */
    public static final String GET_USER_LOGS_CONTENT = "GET_USER_LOGS_CONTENT";

    /**
     * 该用户编辑中的文章的图片缓存Key
     */
    public static final String IMAGE_CACHE_KEY = "IMAGE_CACHE_KEY";

    /**
     * 图片服务器的地址、账号、密码
     */
    public static final String FILE_SERVER_URL = "http://localhost:9000";
    public static final String FILE_USER_NAME = "luqiqiuser";
    public static final String FILE_PASS_WORD = "luqiqipass";
    public static final String FILE_BUCKET_NAME = "blogs";

    @Value("${USER_DEFAULT_IMAGE}")
    private String USER_DEFAULT_IMAGE;


    /**
     * 初始化写文章的页面信息
     *
     * @param request
     * @return
     */
    public SystemResult init(HttpServletRequest request) throws Exception {

        // 加锁 以防数据安全问题
        ReentrantReadWriteLock lock = BLogsWriteLock.getInstance().getLock();
        lock.writeLock().lock();
        try {
            // 获取用户的信息
            BLogsUser userInfo = (BLogsUser) request.getAttribute("user");
            // 创建返回的信息
            OpenBLogsDto dto = new OpenBLogsDto();
            // 从缓存中获取数据
            String logsToken = (String) webKingCache.get(USER_LOGS_KEY + userInfo.getUserName());
            if (StringUtils.isEmpty(logsToken)) {
                logsToken = userInitLogs(userInfo);
                dto.setToken(logsToken);
            } else {
                // 这边是有数据
                String contentToken = GET_USER_LOGS_CONTENT + "#" + userInfo.getUserName() + "#" + logsToken;
                String logsContent = (String) webKingCache.get(contentToken);
                dto.setContent(logsContent);
                dto.setToken(logsToken);
            }
            return new SystemResult(dto);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * 用户第一次点击写文章后 需要进行初始化
     *
     * @param userInfo
     */
    public String userInitLogs(BLogsUser userInfo) throws Exception {
        // 说明该用户是第一次点击
        String newLogsToken = MD5Utils.md5(userInfo.getUserName() + UUID.randomUUID().toString() +
                System.currentTimeMillis());
        // 重新写回用户的缓存
        webKingCache.set(USER_LOGS_KEY + userInfo.getUserName(), newLogsToken, true);
        return newLogsToken;
    }


    /**
     * 上传图片
     *
     * @param request
     * @param file
     * @param token
     * @return
     */
    public SystemResult upload(HttpServletRequest request, MultipartFile file, String token) throws Exception {
        // 加锁 以防数据安全问题
        ReentrantReadWriteLock lock = BLogsWriteLock.getInstance().getLock();
        lock.writeLock().lock();
        try {
            // 获取用户的信息
            BLogsUser userInfo = (BLogsUser) request.getAttribute("user");
            // 判断一下 当前的文章是否是该用户的
            checkHandleIsLoginUser(userInfo, token);

            // 然后我们需要调用MINIO进行图片的上传
            // 上传之前 我们需要对file进行判断
            StringBuffer fileName = new StringBuffer();
            checkUploadFile(file, fileName);
            MinioUtil util = new MinioUtil(FILE_SERVER_URL, FILE_USER_NAME, FILE_PASS_WORD,
                    FILE_BUCKET_NAME);
            SystemResult uploadResult = util.uploadFile(file, fileName.toString());

            // 取出图片地址
            String data = (String) uploadResult.getData();
            // 存入到图片缓存中去
            imageAddressWriteCahce(userInfo, data);
            return new SystemResult(BLogSystemState.SUCCESS.getVALUE(),
                    BLogSystemState.SUCCESS.getKEY(), data);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            lock.writeLock().unlock();
        }

    }

    /**
     * 将图片写到缓存
     *
     * @param userInfo
     * @param imageAddress
     */
    public void imageAddressWriteCahce(BLogsUser userInfo, String imageAddress) throws Exception {
        // 判断缓存中是否有我们当前用户的图片集合
        List<String> images = (List<String>) webKingCache.get(IMAGE_CACHE_KEY + userInfo.getUserName());
        if (CollectionUtils.isEmpty(images)) {
            // 说明这个用户在缓存中没有图片的缓存
            images = new ArrayList<>();
        }

        images.add(imageAddress);
        // 重新写入缓存
        webKingCache.set(IMAGE_CACHE_KEY + userInfo.getUserName(), images, true);

    }

    /**
     * 判断本次操作的是否是登录的用户
     *
     * @param userInfo
     * @param token
     */
    public void checkHandleIsLoginUser(BLogsUser userInfo, String token) throws Exception {
        String logsToken = (String) webKingCache.get(USER_LOGS_KEY + userInfo.getUserName());
        if (StringUtils.isEmpty(logsToken)) {
            throw new CheckValueException("加载失败，请重新打开。");
        }
        // 判断是否是登录的用户
        if (!token.equals(logsToken)) {
            throw new CheckValueException("加载失败，请重新打开。");
        }
    }

    /**
     * 对上传的图片进行校验
     *
     * @param file
     * @return
     */
    public void checkUploadFile(MultipartFile file, StringBuffer fileName) throws IOException, CheckValueException {
        if (file == null) {
            throw new CheckValueException("图片不能为空");
        } else if (file.getInputStream() == null) {
            throw new CheckValueException("图片不能为空");
        } else if (file.getBytes() == null || file.getBytes().length <= 0) {
            throw new CheckValueException("图片不能为空");
        }
        String fileNamefix = MD5Utils.md5("IMAGE_" + UUID.randomUUID().toString() +
                System.currentTimeMillis());

        String originalFilename = file.getOriginalFilename();
        String fileNameEnd = originalFilename.substring(originalFilename.lastIndexOf("."));
        fileName.append(fileNamefix).append(fileNameEnd);
    }

    /**
     * 保存草稿
     *
     * @param request
     * @param token
     * @param content
     * @return
     */
    public SystemResult saveDraft(HttpServletRequest request, String token,
                                  String content) throws Exception {

        // 加锁 以防数据安全问题
        ReentrantReadWriteLock lock = BLogsWriteLock.getInstance().getLock();
        lock.writeLock().lock();
        try {
            // 获取用户的信息
            BLogsUser userInfo = (BLogsUser) request.getAttribute("user");
            // 判断一下 当前的文章是否是该用户的
            checkHandleIsLoginUser(userInfo, token);
            // 需要保存到他的草稿中
            webKingCache.set(GET_USER_LOGS_CONTENT + "#" + userInfo.getUserName() + "#" + token,
                    content, true);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            lock.writeLock().unlock();
        }
        return new SystemResult(BLogSystemState.SUCCESS);
    }

    /**
     * 检查数据的安全性
     *
     * @param bLogsArticle
     */
    public static void checkInfo(BLogsArticle bLogsArticle) {
    }

    /**
     * 插入数据库之前的一个操作
     */
    public void beforeCreateMySqlProceeeor(BLogsArticleWithBLOBs article, BLogsUser bLogsUser) throws Exception {

        // 补全信息
        article.setAuthorNumber(bLogsUser.getUserName());
        // 显示的图片
        // 判断缓存中是否有我们当前用户的图片集合
        List<String> images = (List<String>) webKingCache.get(IMAGE_CACHE_KEY + bLogsUser.getUserName());
        if (CollectionUtils.isEmpty(images)) {
            // 我们需要给他注入一个默认的图片
            article.setShowImage(USER_DEFAULT_IMAGE);
        } else {
            article.setShowImage(images.get(0));
            article.setImageLists(Arrays.toString(images.toArray()));
        }

        article.setCreateTime(new Date());
        article.setLike("0");
        article.setReadSize("0");
        article.setRetain1("1");
    }

    /**
     * 删除缓存的数据
     */
    public void deleteSuccess(BLogsUser userInfo, String token) throws Exception {
        // 加锁 以防数据安全问题
        ReentrantReadWriteLock lock = BLogsWriteLock.getInstance().getLock();
        lock.writeLock().lock();
        try {
            // 删除用户对应的token
            webKingCache.set(USER_LOGS_KEY + userInfo.getUserName(), null);
            // 删除图片
            webKingCache.set(IMAGE_CACHE_KEY + userInfo.getUserName(), null);
            // 删除用户的内容
            webKingCache.set(GET_USER_LOGS_CONTENT + "#" + userInfo.getUserName() + "#" + token,
                    null);
        } catch (Exception e) {
            throw e;
        } finally {
            lock.writeLock().unlock();
        }
    }
}
