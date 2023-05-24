package com.jun.plugin.system.common.job.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 测试定时任务(演示Demo，可删除)
 * testTask为spring bean的名称， 方法名称必须是run
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Component
public class TestTask12 {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "*/10 * * * * ?")
	public void run(){
		//@TODO,新增定时任务，更新项目管理功能的当前处理人跟当前处理流程节点；
		logger.info("TestTask12 定时任务正在执行，每分钟执行一次，参数为：{}");
	}
}
