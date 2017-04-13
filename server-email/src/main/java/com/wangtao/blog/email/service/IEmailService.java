package com.wangtao.blog.email.service;

import com.wangtao.blog.email.entity.EmailEntity;

/**
 * @ClassName:IEmailService
 * @author: KarlWang
 * @Description: TODO(邮件发送接口层) 
 * @date:2017年4月11日 下午7:54:50
 * @see com.wangtao.blog.email.service.IEmailService
 */
public interface IEmailService {
    
    /**
     * @Title: syncSendEmailByMessage 
     * @Description: TODO(同步发送邮件) 
     * @param @param email
     * @param @return
     * @param @throws Exception 设定文件 
     * @return String 返回类型 
     * @throws
     */
    public String syncSendEmailByMessage(EmailEntity email) throws Exception;
    
    /**
     * @Title: asyncSendEmailByMessage 
     * @Description: TODO(异步发送邮件) 
     * @param @param email
     * @param @throws Exception 设定文件 
     * @return void 返回类型 
     * @throws
     */
    public void asyncSendEmailByMessage(EmailEntity email) throws Exception;

}